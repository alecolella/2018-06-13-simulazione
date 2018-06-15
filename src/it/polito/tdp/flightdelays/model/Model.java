package it.polito.tdp.flightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

import it.polito.tdp.flightdelays.db.FlightDelaysDAO;

public class Model {
	
	private FlightDelaysDAO dao;
	private SimpleDirectedWeightedGraph<Airport, DefaultWeightedEdge> grafo;
	private List<Airport> aeroporti;
	private Map<Flight, Double> mappaArchiPeso;
	private AeroportiIdMap aeroportiIdMap;
	private List<Arco> archi;
	
	
	public Model() {
		dao = new FlightDelaysDAO();
		aeroportiIdMap = new AeroportiIdMap();
		aeroporti = dao.loadAllAirports(aeroportiIdMap);
		
	}

	public List<Airline> getAirlines() {
		
		return dao.loadAllAirlines();
	}

	public void creaGrafo(Airline a) {
		archi = new ArrayList<Arco>();
		
		grafo = new SimpleDirectedWeightedGraph<Airport,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, aeroporti);
		
		System.out.println("Numero vertici: "+grafo.vertexSet().size());
		
		mappaArchiPeso = dao.getArchi(a);
		
		for(Flight f : mappaArchiPeso.keySet()) {
			double peso;
						
			Airport sourceAirport = aeroportiIdMap.get(f.getOriginAirportId());
			Airport destinationAirport = aeroportiIdMap.get(f.getDestinationAirportId());
			
			 if(sourceAirport != null && destinationAirport != null) {
			double dist = LatLngTool.distance(new LatLng(sourceAirport.getLatitude(), 
					sourceAirport.getLongitude()), new LatLng(destinationAirport.getLatitude(), 
							destinationAirport.getLongitude()), LengthUnit.KILOMETER);
			
			 peso = mappaArchiPeso.get(f)/dist;
			 mappaArchiPeso.put(f, peso);
			
			 Graphs.addEdge(grafo, sourceAirport, destinationAirport, peso);
			 archi.add(new Arco(sourceAirport,destinationAirport,peso));
			 }
		}
		System.out.println("Numero vertici: "+grafo.edgeSet().size());
	}
	
	public List<Arco> getPeggiori(){
		Collections.sort(archi);
		List<Arco> peggiori = new ArrayList<Arco>();
		if(archi.size()>10) {
		for(int i = 0 ; i<10; i++) {
			peggiori.add(archi.get(i));
			System.out.println(archi.get(i).toString());
		}
		return peggiori;
		}
		else 
			return archi;
		
	}

	public void simula(int k, int v) {
		for(Airport a : this.aeroporti) {
			a.setVoliPartenza(dao.getVoliPartenza(a));
		}
		Simulazione sim = new Simulazione (k,v,this);
		sim.init();
		sim.run();
		
	}

	public List<Airport> getAirports() {
	
		return this.aeroporti;
	}

	public AeroportiIdMap getAeroportiIdMap() {
		return aeroportiIdMap;
	}

}
