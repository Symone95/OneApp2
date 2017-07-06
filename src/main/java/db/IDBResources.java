package db;
import entities.*;
import java.util.ArrayList;

public interface IDBResources 
{
	//I nostri metodi di lettura e scrittura su db
	//Questa interfaccia "pensa" in termini di Entit�
	//La classe si chiamer� DBResourcesSQL, e deve essere implementato come Singleton
	//verr� ingrandito gradualmente.
		
	//Salva una Entit�. 
	//boolean save(Entity e);
	
	// Carica una Entit�
	// Adottiamo una tecnica: baster� l'id per caricarlo
	// Non serve il nome della tabella
	// Vedremo dopo come
	Entity load(int id);
	
	ArrayList<Resource> resources();
	
	String login(String name, String pass);	
		
	
}
