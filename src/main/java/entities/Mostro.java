package entities;

import java.util.HashMap;

public class Mostro extends Personaggio{

	private String minaccia;
	
	public Mostro() {
		super();
	}
	
	public Mostro(String nome, String cognome, int vita, int attacco, int stamina, int minaccia) {
		super(nome, cognome, vita, attacco, stamina);
		set("minaccia", minaccia+"");
	}
		
	public String getMinaccia() {
		return get("minaccia");
	}

	public void setEfficacia(String minaccia) {
		set("minaccia", minaccia);
	}
	

}
