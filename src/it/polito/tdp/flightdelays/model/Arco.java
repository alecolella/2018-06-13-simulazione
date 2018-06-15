package it.polito.tdp.flightdelays.model;

public class Arco implements Comparable<Arco>{
	
	private Airport source;
	private Airport destination;
	private double peso;
	public Arco(Airport source, Airport destination, double peso) {
		super();
		this.source = source;
		this.destination = destination;
		this.peso = peso;
	}
	public Airport getSource() {
		return source;
	}
	public void setSource(Airport source) {
		this.source = source;
	}
	public Airport getDestination() {
		return destination;
	}
	public void setDestination(Airport destination) {
		this.destination = destination;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public String toString() {
		return source.getName() + " "+ destination.getName() + " " + peso+"\n";
	}
	@Override
	public int compareTo(Arco o) {
		
		return -(Double.compare(this.peso, o.peso));
	}
	
	
	
	

}
