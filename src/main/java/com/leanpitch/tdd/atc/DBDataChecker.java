package com.leanpitch.tdd.atc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.leanpitch.tdd.atc.db.DBManager;

public class DBDataChecker {
	
	private DBManager dbManager;
	private Connection connection;
	
	public DBDataChecker() throws SQLException, ClassNotFoundException{
		dbManager = new DBManager();
		dbManager.startDb();
		connection = dbManager.getConnection();
	}

	public void test() throws SQLException{
		System.out.println(connection.isClosed());
		Statement statement = connection.createStatement();
		//statement.execute("CREATE TABLE ATTACH_FLIGHT_TBL(ID INT PRIMARY KEY,OPERATOR_NAME VARCHAR2(300), FLIGHT_NAME VARCHAR2(300))");
		//statement.execute("INSERT INTO ATTACH_FLIGHT_TBL(ID,OPERATOR_NAME,FLIGHT_NAME) VALUES (111022,'EMIRITES','E002')");
		
		//Table for holding gateway to flight mappings. Will return 0000 if gateway is free
		/*statement.execute("CREATE TABLE GATEWAY_FLIGHT_MAP_TBL(GATEID VARCHAR2(100) PRIMARY KEY,FLIGHT_NAME VARCHAR2(300), ENGAGE_START_TIME"
				+ " DATETIME,ENGAGE_END_TIME DATETIME)");*/
		//statement.execute("INSERT INTO GATEWAY_FLIGHT_MAP_TBL(GATEID,FLIGHT_NAME, ENGAGE_START_TIME,ENGAGE_END_TIME) VALUES ('GW01','EM001',sysdate,sysdate)");
		//statement.execute("INSERT INTO GATEWAY_FLIGHT_MAP_TBL(GATEID,FLIGHT_NAME, ENGAGE_START_TIME,ENGAGE_END_TIME) VALUES ('GW02','EM002',sysdate,(sysdate+(2/24)))");
		//statement.execute("INSERT INTO GATEWAY_FLIGHT_MAP_TBL(GATEID,FLIGHT_NAME, ENGAGE_START_TIME,ENGAGE_END_TIME) VALUES ('GW03','EM003',sysdate,(sysdate+(1/24)))");
		//statement.execute("INSERT INTO GATEWAY_FLIGHT_MAP_TBL(GATEID,FLIGHT_NAME, ENGAGE_START_TIME,ENGAGE_END_TIME) VALUES ('GW04','EM004',sysdate,(sysdate+(2/24)))");
		
		//statement.execute("CREATE SEQUENCE FLIGHT_ATT_ID_SEQ START WITH 1001 INCREMENT BY 1");
		
		/*ResultSet rs0 = statement.executeQuery("VALUES NEXT VALUE FOR FLIGHT_ATT_ID_SEQ");
		while(rs0.next()){
			System.out.println("SEQ : "+rs0.getLong(1));
		}*/
		
		ResultSet rs1 = statement.executeQuery("SELECT * FROM ATTACH_FLIGHT_TBL");
		while(rs1.next()){
			System.out.println(" id : "+rs1.getInt("ID")+" Operator : "+rs1.getString("OPERATOR_NAME")+". Flight Name : "+rs1.getString("FLIGHT_NAME"));
		}
		ResultSet rs = statement.executeQuery("SELECT * FROM GATEWAY_FLIGHT_MAP_TBL");
		while(rs.next()){
			System.out.println(" id : "+rs.getString("GATEID")+" Flight Name : "+rs.getString("FLIGHT_NAME")+". Engage Start Time : "+rs.getString("ENGAGE_START_TIME")
					+". Engage End Time : "+rs.getString("ENGAGE_END_TIME"));
			
		}
		dbManager.shutDownDb();
	}
	
	public static void main(String args[]) throws SQLException, ClassNotFoundException{
		DBDataChecker dbDataChecker = new DBDataChecker();
		dbDataChecker.test();
		
		
	}

}
