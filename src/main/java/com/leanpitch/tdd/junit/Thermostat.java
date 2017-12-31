package com.leanpitch.tdd.junit;

public class Thermostat {

	enum Switch {
		ON,OFF
	}
	
	private float currTemperature;
	private float desiredTemperature;
	private Switch heatSwitch = Switch.OFF;
	
	public Thermostat(){
		heatSwitch = Switch.ON;
	}
	
	public float getCurrTemperature() {
		return currTemperature;
	}
	public void increaseCurrTemperature(float tempToIncrease) {
		if((currTemperature+tempToIncrease) <= desiredTemperature){
		   currTemperature = currTemperature+tempToIncrease;
		}else{
			currTemperature = desiredTemperature;
		}
		if(currTemperature==desiredTemperature){
			heatSwitch = Switch.OFF;
		}
	}
	public float getDesiredTemperature() {
		return desiredTemperature;
	}
	public void setDesiredTemperature(float desiredTemperature) {
		this.desiredTemperature = desiredTemperature;
	}

}


