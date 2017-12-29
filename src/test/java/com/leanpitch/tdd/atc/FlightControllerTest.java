package com.leanpitch.tdd.atc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.leanpitch.tdd.atc.db.DBManager;

import junit.framework.TestCase;

public class FlightControllerTest extends TestCase {
	
	private FlightController flightController;
	private FlightControllerDao flightControllerDao;
	private static boolean[] gates = new boolean[5];

	protected void setUp() throws Exception {
		super.setUp();
		flightControllerDao = new FlightControllerDao();
		flightController = new FlightController(flightControllerDao);
	}


	public void testAttachFlightWithSuccess() throws ClassNotFoundException, SQLException {
		Flight flight = new Flight("AI001","Air India");
		long attachId = flightController.attachFlight(flight);
		assertTrue(attachId > 0);
	}
	
	public void testAssignGatewayToFlightSuccessfully() throws ClassNotFoundException, SQLException{
		Flight flight = new Flight("LF001","Lufthansa");
		String gatewayId = flightController.assignGateway(flight);
		assertNotNull(gatewayId);
		assertTrue(gatewayId.equalsIgnoreCase("GW02") || gatewayId.equalsIgnoreCase("GW03") 
				|| gatewayId.equalsIgnoreCase("GW04"));
	}
	
	public void testAssignGatewayToFlightUnsuccessfull() throws ClassNotFoundException, SQLException{
		flightControllerDao.engageGateway(30, "GW02");
		flightControllerDao.engageGateway(30, "GW03");
		flightControllerDao.engageGateway(30, "GW04");
		Flight flight = new Flight("IG001","IndiGo");
		String gatewayId = flightController.assignGateway(flight);
		assertEquals(gatewayId, "");
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
		DBManager dbManager = new DBManager();
		if(dbManager.getServer()!=null && !dbManager.getServer().isRunning(false)){
		  dbManager.startDb();
		}
		Connection conn = dbManager.getConnection();
		PreparedStatement statement = conn.prepareStatement("UPDATE GATEWAY_FLIGHT_MAP_TBL SET ENGAGE_END_TIME=?"
				   		+ " WHERE GATEID=?");
		Timestamp endTime = new Timestamp(System.currentTimeMillis() - (24*60*60*1000));
		statement.setTimestamp(1, endTime);
		statement.setString(2, "GW02");
		statement.executeUpdate();
		statement.setString(2, "GW03");
		statement.executeUpdate();
		statement.setString(2, "GW04");
		statement.executeUpdate();
		statement.close();
		conn.close();
		dbManager.shutDownDb();
	}
	
	public static void main(String args[]){
		System.out.println(gates[0]);
	}

}
