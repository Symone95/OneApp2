package entities;

public class Armatura extends Entity{
	
	private int id;
	private String nome;
	private int difesa;
	
	public Armatura(String nome, int difesa) {
		set("nome", nome);
		set("difesa", difesa+"");
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

	public int getDifesa() {
		return Integer.parseInt(get("difesa"));
	}

	public void setDifesa(int difesa) {
		set("difesa", difesa+"");
	}
	
	
	
}