package db;

import java.util.ArrayList;
import entities.*;

public class Test 
{
	public static void main(String[] args)
	{
		DAO d = new DAO(new Database());
		
		Entity p = d.load("eroe", 2);
		System.out.println(p.toJson());
		
		Entity p2 = d.load("mostro", 2);
		System.out.println(p2.toJson());
		
		System.out.println("\n\n");
		Entity p3 = d.load("arma", 2);
		System.out.println(p3.toJson());
		System.out.println("\n\n");
		Entity p4 = d.load("armatura", 2);
		System.out.println(p4.toJson());
		
	}
}