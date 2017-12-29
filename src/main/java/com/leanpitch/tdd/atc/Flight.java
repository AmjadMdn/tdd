package com.leanpitch.tdd.atc;

public class Flight {
	
	private String flightName;
	private String operatorName;
	
	public Flight(String flightName, String operatorName) {
		super();
		this.flightName = flightName;
		this.operatorName = operatorName;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	
	

}
