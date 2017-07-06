package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Eroe extends Personaggio{

	private String nome;
	private String cognome;
//	private String foto;
	private int vita;
	private int attacco;
	private int stamina;
	private int efficacia;
	
	String attacchi[] = new String[]{"Attacco semplice", "Attacco pesante", "Attacco speciale", "Mossa finale"};
	
	
	public Eroe(String nome, String cognome, int vita, int attacco, int stamina, int efficacia) {
		set("nome", nome);
		set("cognome", cognome);
		set("vita", vita+"");
		set("attacco", attacco+"");
		set("stamina", stamina+"");
		set("efficacia", efficacia+"");
	}
	
//	@Override
//	public String toString() {
//		return "Nome: " + get("nome") + 
//				"\nCognome: " + get("cognome") + 
//				"\nAttacco: " + get("attacco") + 
//				"\nStamina: " + get("stamina") + 
//				"\nEfficacia: " + get("efficacia") + 
//				"\n";
//	}
	
	public String scegliAttacco(){
		return attacchi[(int)(Math.random()*attacchi.length)];
	}
	
	public int calcolaAttacco(String attacco){
		int ris = 0;
		
		switch (attacco) {
		case "Attacco semplice":
			ris = (int)(1 + Math.random()*24);
			break;

		case "Attacco pesante":
			ris = (int)(25 + Math.random()*25);
			break;
		
		case "Attacco speciale":
			ris = (int)(50 + Math.random()*25);
			break;
			
		case "Mossa finale":
			ris = (int)(75 + Math.random()*25);
			break;
			
		default:
			break;
		}
		
		return ris;
	}

	public String getNome() {
		return get("nome");
	}

	public void setNome(String nome) {
		set("nome", nome);
	}

	public String getCognome() {
		return get("cognome");
	}

	public void setCognome(String cognome) {
		set("cognome", cognome);
	}

	public int getVita() {
		return Integer.parseInt(get("vita"));
	}

	public void setVita(int vita) {
		set("vita", vita+"");
	}
	
	public int getAttacco() {
		return Integer.parseInt(get("attacco"));
	}

	public void setAttacco(int attacco) {
		set("attacco", attacco+"");
	}

	public int getStamina() {
		return Integer.parseInt(get("stamina"));
	}

	public void setStamina(int stamina) {
		set("stamina", stamina+"");
	}

	public int getEfficacia() {
		return Integer.parseInt(get("efficacia"));
	}

	public void setEfficacia(int efficacia) {
		set("efficacia", efficacia+"");
	}

	public String[] getAttacchi() {
		return attacchi;
	}

	public void setAttacchi(String[] attacchi) {
		this.attacchi = attacchi;
	}
	
}
