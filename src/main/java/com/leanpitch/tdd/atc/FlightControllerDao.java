package com.leanpitch.tdd.atc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.leanpitch.tdd.atc.db.DBManager;

public class FlightControllerDao {
	
	private DBManager dbManager;
	private Connection connection;
	
	public FlightControllerDao() throws SQLException, ClassNotFoundException{
		dbManager = new DBManager();
		dbManager.startDb();
	}
	
	public FlightControllerDao(DBManager dbManager){
		this.dbManager = dbManager;
	}


	private synchronized void obtainConnection() throws ClassNotFoundException, SQLException {
		connection = dbManager.getConnection();
	}
	
	
	public long registerNewFlight(String operatorName, String flightName) throws SQLException, ClassNotFoundException {
		long attachmentId;
		try{
			obtainConnection();
		   Statement statement = connection.createStatement();
		   attachmentId = getAttachmentId(statement);
		   statement.execute("INSERT INTO ATTACH_FLIGHT_TBL(ID,OPERATOR_NAME,FLIGHT_NAME) VALUES"
		   		+ "("+attachmentId+",'"+operatorName+"','"+flightName+"')");
		}finally{
			connection.close();
		}
		return attachmentId;
	}


	private long getAttachmentId(Statement statement) throws SQLException {
		long attachmentId;
		ResultSet rs0 = statement.executeQuery("VALUES NEXT VALUE FOR FLIGHT_ATT_ID_SEQ");
			rs0.next();
			attachmentId = rs0.getLong(1);
		return attachmentId;
	}


	public List<String> getAvailableGateways() throws SQLException, ClassNotFoundException {
		obtainConnection();
		List<String> gateways;
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM GATEWAY_FLIGHT_MAP_TBL");
			gateways = processAndReturnGateways(rs);
		}finally{
			connection.close();
		}
		return gateways;
	}


	private List<String> processAndReturnGateways(ResultSet rs) throws SQLException {
		List<String> freeGateways = new ArrayList<String>();
		while(rs.next()){
			Timestamp time = rs.getTimestamp("ENGAGE_END_TIME");
			Timestamp currTimestamp = new Timestamp(System.currentTimeMillis());
			if(time.before(currTimestamp)){
				freeGateways.add(rs.getString("GATEID"));
			}
		}
		return freeGateways;
	}


	public int engageGateway(long engageTime, String gateId) throws SQLException, ClassNotFoundException {
		try{
			obtainConnection();
			Timestamp startTime = new Timestamp(System.currentTimeMillis());
			Timestamp endTime = new Timestamp(System.currentTimeMillis() + (engageTime*60*1000));
		   PreparedStatement preparedStatement = connection.prepareStatement
				   ("UPDATE GATEWAY_FLIGHT_MAP_TBL SET ENGAGE_START_TIME=?,ENGAGE_END_TIME=?"
				   		+ " WHERE GATEID=?");
		   preparedStatement.setTimestamp(1, startTime);
		   preparedStatement.setTimestamp(2, endTime);
		   preparedStatement.setString(3, gateId);
		   return preparedStatement.executeUpdate();
		   
		}finally{
			connection.close();
		}
	}
	
	

}
