package com.leanpitch.tdd.atc;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;


public class FlightControllerWithMockingTest {
	
	private FlightController flightController;
	private FlightControllerDao flightControllerDao;

	@Before
	public void setUp() throws Exception {
		flightControllerDao = mock(FlightControllerDao.class);
		/*when(flightControllerDao.registerNewFlight("Emirates", "EM001")).thenReturn(1001L);
		when(flightControllerDao.registerNewFlight("JetAirways", "JA001")).thenReturn(2001L);
		when(flightControllerDao.registerNewFlight("Indigo", "IO001")).thenReturn(3001L);*/
		given(flightControllerDao.registerNewFlight("Emirates", "EM001")).willThrow(SQLException.class);
		flightController = new FlightController(flightControllerDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	
	@Test
	public void testRegisterFlight() throws ClassNotFoundException, SQLException{
		Flight flight = new Flight("JA001","JetAirways");
		long attachId = flightController.attachFlight(flight);
		assertTrue(attachId==1001L);
	}
	
	@Test(expected=SQLException.class)
	public void testForException() throws ClassNotFoundException, SQLException{
		Flight flight = new Flight("EM001","Emirates");
		long attachId = flightController.attachFlight(flight);
		
	}

}
