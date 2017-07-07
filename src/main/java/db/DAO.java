package db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import entities.*;
import interfaces.*;

/**
 * Data Access Object wich implements the methods of IDAO, it handles of the persistance:
 * load, save and update elements of the database.
 * HACK: the projects methods
 * @author Naga01
 */
public class DAO implements IDAO 
{
	private Database db;
	
	/**
	 * constructor
	 * @param
	 */
	public DAO(Database db)
	{
		this.db = db;
	}
	
	/**
	 * checks out that the inserted email and password are correct and returns the level of the user
	 * @param
	 * @return boolean result
	 */
	public int lvl(String email, String password)
	{
		String[] data = new String[]
				{
						email,
						password
				};
		return 1;
	}

	//START SIMONE
	public Personaggio loadPersonaggio(String type, int id) {
		
		Personaggio p = null;
		ResultSet rs = null;
		switch (type) {
		case "eroe":
			p = (Eroe) db.load("eroe", id);
			break;

		case "mostro":
			p = (Mostro) db.load("mostro", id);
			break;
			
		default:
			break;
		}
		
		
		return p;
	}
	//END SIMONE

	@Override
	public ArrayList<Entity> load(String type) {
		return db.load(type);
	}

	@Override
	public Entity load(String type, int id) {
		return db.load(type, id);
	}
}
