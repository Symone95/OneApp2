package entities;

import java.util.HashMap;

public class Mostro extends Personaggio{

	private String minaccia;
	
	public Mostro() {
		super();
	}
	
	public Mostro(String nome, String cognome, int vita, int attacco, int stamina, int minaccia, Arma arma, Armatura armatura) {
		super(nome, cognome, vita, attacco, stamina, arma, armatura);
		set("minaccia", minaccia+"");
	}
		
	public String getMinaccia() {
		return get("minaccia");
	}

	public void setEfficacia(String minaccia) {
		set("minaccia", minaccia);
	}
	
}