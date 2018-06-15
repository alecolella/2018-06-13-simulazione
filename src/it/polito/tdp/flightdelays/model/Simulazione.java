package it.polito.tdp.flightdelays.model;

import java.util.ArrayList;
import java.util.List;

public class Simulazione {
	
	private int k;
	private int v;
	private Model model;
	private List<Passeggero> passeggeri;
	private int totRitardo;
	
	public class Event{
		private Passeggero passeggero;
		
	}
	
	public Simulazione(int k, int v, Model model) {
		
		this.k = k;
		this.v = v;
		this.model = model;
		totRitardo = 0;
		passeggeri = new ArrayList<Passeggero>();
	}
	
	public void init() {
		
		for(int i = 0; i < k; i++) {
			//Ogni passeggero ha aeroporto di partenza casuale
			passeggeri.get(i).setOrigine(model.getAirports().get((int) Math.random()*model.getAirports().size()));
					
		}
		
		for(Passeggero p : passeggeri) {
			Flight primoVolo = p.getOrigine().getVoliPartenza().get(0);
			p.setPartenza(primoVolo.getScheduledDepartureDate());
			p.setDestinazione(model.getAeroportiIdMap().get(primoVolo.getDestinationAirportId()));
			p.setArrivo(primoVolo.getArrivalDate());
			p.setRitardo(primoVolo.getArrivalDelay());

		}
	}

	public void run() {
		int i = 0;
		for(Passeggero p : passeggeri) {
			while(i < v &&)
		}
		
	}
	
	

}
