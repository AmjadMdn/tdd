package com.leanpitch.tdd.atc;

import java.sql.SQLException;
import java.util.List;

public class FlightController {
	
	private FlightControllerDao flightControllerDao;

	public FlightController(FlightControllerDao flightControllerDao) {
		super();
		this.flightControllerDao = flightControllerDao;
	}


	public long attachFlight(Flight flight) throws ClassNotFoundException, SQLException {
		/*List<String> availaleGateways = flightControllerDao.getAvailableGateways();
        String gatewayToAttach = availaleGateways.get(0);
        flightControllerDao.	*/
		return flightControllerDao.registerNewFlight( 
				flight.getOperatorName(), flight.getFlightName());
	}


	public String assignGateway(Flight flight) throws ClassNotFoundException, SQLException {
		String assignedGateway = "";
		List<String> availableGateways = flightControllerDao.getAvailableGateways();
		int gatewayUpdateNo = -1;
		if(availableGateways.size()>0){
			gatewayUpdateNo = flightControllerDao.engageGateway(30, availableGateways.get(0));
		}
		if(gatewayUpdateNo > 0){
			assignedGateway = availableGateways.get(0);
		}
		return assignedGateway;
	}

}
