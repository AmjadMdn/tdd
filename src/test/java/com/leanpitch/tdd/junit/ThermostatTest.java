package com.leanpitch.tdd.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ThermostatTest {
	
	private Thermostat thermostat;

	@Before
	public void setUp() throws Exception {
		thermostat = new Thermostat();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCurrTemperature() {
		thermostat.setDesiredTemperature(98.00F);
		thermostat.increaseCurrTemperature(56.00F);
		assertEquals(56.00F, thermostat.getCurrTemperature(),0);
	}

	@Test
	public void testIncreaseCurrTemperature() {
		thermostat.setDesiredTemperature(98.00F);
		thermostat.increaseCurrTemperature(56.00F);
		assertEquals(56.00F, thermostat.getCurrTemperature(),0);
		thermostat.increaseCurrTemperature(56.00F);
		assertEquals(98.00F, thermostat.getCurrTemperature(),0);
	}

	@Test
	public void testGetDesiredTemperature() {
		thermostat.setDesiredTemperature(98.00F);
		assertEquals(98.00F, thermostat.getDesiredTemperature(),0);
	}

	@Test
	public void testSetDesiredTemperature() {
		thermostat.setDesiredTemperature(98.00F);
		assertEquals(98.00F, thermostat.getDesiredTemperature(),0);
		thermostat.setDesiredTemperature(56.00F);
		assertEquals(56.00F, thermostat.getDesiredTemperature(),0);
	}
	
	@Test
	public void testIsSwitchOn(){
		thermostat.setDesiredTemperature(98.00F);
		thermostat.increaseCurrTemperature(56.00F);
		assertTrue(thermostat.isSwitchOn());
		thermostat.increaseCurrTemperature(56.00F);
		assertFalse(thermostat.isSwitchOn());
		
	}

}
