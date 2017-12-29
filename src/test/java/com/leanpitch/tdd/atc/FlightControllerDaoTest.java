package com.leanpitch.tdd.atc;

import java.sql.SQLException;
import java.util.List;

import com.leanpitch.tdd.atc.db.DBManager;

import junit.framework.TestCase;

public class FlightControllerDaoTest extends TestCase {
	
	private FlightControllerDao flightControllerDao;
	private DBManager dbManager = new DBManager();

	protected void setUp() throws Exception {
		super.setUp();
		flightControllerDao = new FlightControllerDao(dbManager);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
    public void testRegisterNewFlight() throws SQLException, ClassNotFoundException{
    	dbManager.startDb();
    	long registerId = flightControllerDao.registerNewFlight("emirates","EM001");
    	assertTrue(registerId>0);
    	dbManager.shutDownDb();
    }
    
    public void testEngageGatewaySuccessfull() throws SQLException, ClassNotFoundException{
    	dbManager.startDb();
    	int result = flightControllerDao.engageGateway(30,"GW01");
    	assertEquals(result,1);
    	dbManager.shutDownDb();
    }
    
    public void testEngageGatewayUnsuccessfull() throws SQLException, ClassNotFoundException{
    	dbManager.startDb();
    	int result = flightControllerDao.engageGateway(30,"GW08");
    	assertEquals(result,0);
    	dbManager.shutDownDb();
    }
    
    public void testAvailableGatewaysForAllGates() throws SQLException, ClassNotFoundException{
    	dbManager.startDb();
    	List<String> availableGateWays = flightControllerDao.getAvailableGateways();
    	assertNotNull(availableGateWays);
    	//assertTrue(availableGateWays.contains("GW01"));
    	assertTrue(availableGateWays.contains("GW02"));
    	assertTrue(availableGateWays.contains("GW03"));
    	assertTrue(availableGateWays.contains("GW04"));
    	dbManager.shutDownDb();
    }
    
    public void testNonAvailibilityOfGateway() throws SQLException, ClassNotFoundException{
    	dbManager.startDb();
    	flightControllerDao.engageGateway(30,"GW01");
    	List<String> availableGateWays = flightControllerDao.getAvailableGateways();
    	assertTrue(!availableGateWays.contains("GW01"));
    	dbManager.shutDownDb();
    }
    

}
