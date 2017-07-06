package db;

import java.util.ArrayList;
import entities.*;

public class Test 
{
	public static void main(String[] args)
	{
		DAO d = new DAO(new Database("jdbc:mysql//localhost3306/oneapp2?user=root&password=Naga01"));
		
		ArrayList<Personaggio> p = (ArrayList<Personaggio>)d.load("personaggi");
		System.out.println(p.size());
		
		for(Personaggio pg: p)
			System.out.println(pg.toJson());
	}
}
