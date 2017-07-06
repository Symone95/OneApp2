package entities;

import java.util.ArrayList;

public class Scontro {
	
	ArrayList<Eroe> eroi = new ArrayList<Eroe>();
	ArrayList<Mostro> mostri = new ArrayList<Mostro>();
	
	public Scontro(ArrayList<Eroe> eroi, ArrayList<Mostro>mostri) {
		this.eroi = eroi;
		this.mostri = mostri;
	}
	
	public Eroe eroeScelto(){
		return this.eroi.get((int)(Math.random()*eroi.size()));
	}
	
	public Mostro mostroScelto(){
		return this.mostri.get((int)(Math.random()*mostri.size()));
	}
	
	
	
	
}
