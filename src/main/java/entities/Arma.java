package entities;

public class Arma extends Personaggio{
	
	private int id;
	private String nome;
	private int attacco;
	
	public Arma(String nome, int attacco) {
		set("nome", nome);
		set("attacco", attacco+"");
	}

	public int getId() {
		return Integer.parseInt(get("id"));
	}

	public void setId(int id) {
		set("id", id+"");
	}

	public String getNome() {
		return get("nome");
	}

	public void setNome(String nome) {
		set("nome", nome);
	}

	public int getAttacco() {
		return Integer.parseInt(get("attacco"));
	}

	public void setAttacco(int attacco) {
		set("attacco", attacco+"");
	}
	
	
	
}
