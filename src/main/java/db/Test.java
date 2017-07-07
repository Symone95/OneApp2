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
		
	}
}