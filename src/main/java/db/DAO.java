package db;

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

	@Override
	public Personaggio load(String type, int id) {
		Personaggio p = db.loadPersonaggi("eroe", id);
		return p;
	}

	@Override
	public ArrayList<Personaggio> load(String type) {
		// TODO Auto-generated method stub
		return null;
	}
}
