package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Eroe extends Personaggio{

	private int efficacia;
	
	public Eroe() {
		super();
	}
	
	public Eroe(String nome, String cognome, int vita, int attacco, int stamina, int efficacia) {
		super(nome, cognome, vita, attacco, stamina);
		set("efficacia", efficacia+"");
	}
		
	public int getEfficacia() {
		return Integer.parseInt(get("efficacia"));
	}

	public void setEfficacia(int efficacia) {
		set("efficacia", efficacia+"");
	}
	
}
