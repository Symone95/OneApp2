package interfaces;

import java.util.ArrayList;
import entities.*;

/**
 * interface with all method firms of DAO
 * @author Naga01
 */
public interface IDAO 
{
	/**
	 * check if the email and password are correct and returns the level of the user
	 * @param String email, String password
	 * @return String lvl
	 */
	public int lvl(String email, String password);
	
	/**
	 * load objects from the database
	 * @param String type (personaggi/eroi/mostri/scontri)
	 * @param int id
	 * @return Object
	 */
	public Entity load(String type, int id);
	
	/**
	 * load a list with all entities of one kind
	 * @param String type (personaggi/eroi/mostri/scontri)
	 * @return ArrayList<Entity> entities
	 */
	public ArrayList<Entity> load(String type);
	
	/**
	 * saves or updates an elemnt of the database
	 * @param Personaggio personaggio
	 * @return boolean result
	 */
}
