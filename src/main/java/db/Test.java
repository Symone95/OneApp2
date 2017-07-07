package db;

import java.util.ArrayList;
import entities.*;

public class Test 
{
	public static void main(String[] args)
	{
		DAO d = new DAO(new Database());
		
//		ArrayList<Personaggio> p = (ArrayList<Personaggio>)d.load("personaggi");
//		System.out.println(p.size());
//		
//		for(Personaggio pg: p)
//			System.out.println(pg.toJson());
		
		Personaggio p = d.load("eroe", 1);
		
		
		System.out.println(p.toJson());
		
	}
}
