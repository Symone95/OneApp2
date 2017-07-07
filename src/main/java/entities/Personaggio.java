package entities;

public class Personaggio extends Entity{
	
	private String nome;
//	private String foto;
	private int vita;
	private int attacco;
	private int stamina;
	String attacchi[] = new String[]{"Attacco semplice", "Attacco pesante", "Attacco speciale", "Mossa finale"};
	private Arma arma;
	private Armatura armatura;
	
	public Personaggio() {}
	
	public Personaggio(String nome, int vita, int attacco, int stamina, Arma arma, Armatura armatura) {
		set("nome", nome);
		if(this.armatura != null)
			set("vita", (vita + this.armatura.getDifesa()) + "");
		else
			set("vita", vita+"");
		set("stamina", stamina+"");
		
		if(this.arma != null)
			set("attacco", (attacco + this.arma.getAttacco()) + "");
		else
			set("attacco", attacco + "");
		set("stamina", stamina+"");
		
		this.arma = arma;
		this.armatura = armatura;
	}
	
	
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
	
	public String[] getAttacchi() {
		return attacchi;
	}

	public void setAttacchi(String[] attacchi) {
		this.attacchi = attacchi;
	}
	
	public String toString(){
		return super.toString() + "\n" + 
		(this.arma!=null?this.arma.toString():"Nessun'arma") + "\n" + 
		(this.armatura!=null?this.armatura.toString():"Nessuna armatura");
	}
	
}
