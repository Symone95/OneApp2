package db;
import entities.*;
import java.util.ArrayList;

public interface IDBResources 
{
	//I nostri metodi di lettura e scrittura su db
	//Questa interfaccia "pensa" in termini di Entità
	//La classe si chiamerà DBResourcesSQL, e deve essere implementato come Singleton
	//verrà ingrandito gradualmente.
		
	//Salva una Entità. 
	//boolean save(Entity e);
	
	// Carica una Entità
	// Adottiamo una tecnica: basterà l'id per caricarlo
	// Non serve il nome della tabella
	// Vedremo dopo come
	Entity load(int id);
	
	ArrayList<Resource> resources();
	
	String login(String name, String pass);	
		
	
}
