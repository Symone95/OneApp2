package entities;

import java.util.HashMap;

public abstract class Entity {
	
	HashMap<String, String>data = new HashMap<String, String>();
	
	public Entity(){}
	
	public Entity(HashMap<String, String> data) {
		this.data = data;
	}
	
	public String get(String key) {
		return data.get(key);
	}

	public void set(String key, String value) {
		data.put(key, value);
	}
	
	public String toJson(){
		String ris = "";
		
		for(String campo: data.keySet())
			ris += "{\"" + campo + "\":\"" + data.get(campo) + "\"},";
			ris = ris.substring(0, ris.length()-1);
	
			return ris;
	}
	
	public String toString(){
		String ris = "";
		
		for(String campo: data.keySet())
			ris += campo + ": " + data.get(campo) + "\n";
		ris = ris.substring(0, ris.length()-1);
		return ris;
	}
	

}
