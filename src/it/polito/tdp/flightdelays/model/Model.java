package it.polito.tdp.flightdelays.model;

import java.util.List;

import it.polito.tdp.flightdelays.db.FlightDelaysDAO;

public class Model {
	
	private FlightDelaysDAO dao;
	
	public Model() {
		dao = new FlightDelaysDAO();
	}

	public List<Airline> getAirlines() {
		
		return dao.loadAllAirlines();
	}

}
