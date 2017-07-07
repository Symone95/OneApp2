package entities;

import java.util.ArrayList;
import java.util.HashMap;

import db.DAO;
import db.Database;

public class Test {
	
	public static void main(String[] args) {
	
//		DAO d = new DAO(new Database());
//		Entity p = d.load("eroe", 2);
//		System.out.println(p.toJson());
		
		Mostro m = new Mostro("mostro", "forte", 2000, 200, 900, 5, new Arma("Spada", 200), new Armatura("Casco", 90));
		
		System.out.println(m.toString());
		
		
//		Eroe e = new Eroe("Goku", "Son", 100, 100, 100, 90);
//		Eroe e2 = new Eroe("Gohan", "Son", 90, 100, 90, 90);
//		Mostro m = new Mostro("Frezeer", "Alieno", 100, 100, 95, "Super");
//		Mostro m2 = new Mostro("Cell", "Android", 100, 100, 95, "Super");
//		
//		ArrayList<Eroe> eroi = new ArrayList<Eroe>();
//		eroi.add(e);eroi.add(e2);
//		
//		ArrayList<Mostro> mostri = new ArrayList<Mostro>();
//		mostri.add(m);mostri.add(m2);
//		
//		Scontro s = new Scontro(eroi, mostri);
//		
//		Eroe eroeScelto = s.eroeScelto();
//		Mostro mostroScelto = s.mostroScelto();
//		
//		
//		do{
//			
//			System.out.println(eroeScelto.toString() + "\n\nVS\n");
//			System.out.println(mostroScelto.toString());
//			
//			String attaccoEroe = eroeScelto.scegliAttacco();
//			String attaccoMostro = mostroScelto.scegliAttacco();
//			
//			int attaccoEro = eroeScelto.calcolaAttacco(attaccoEroe);
//			int attaccoMos = mostroScelto.calcolaAttacco(attaccoMostro);
//			
//			System.out.println(eroeScelto.get("nome") + " attacca con: " + attaccoEroe + " ed infligge " + attaccoEro + 
//							   " danni, mentre " + mostroScelto.get("nome") + " attacca con: " + attaccoMostro + " ed infligge " + attaccoMos + " danni.");
//			
//			
//			
//			eroeScelto.setVita(eroeScelto.getVita()-attaccoMos);
//			mostroScelto.setVita(mostroScelto.getVita()-attaccoEro);
//			
//			System.out.println("Statistiche dopo l'attacco:\nEroe\n" + eroeScelto.toString() + 
//							   "\n\nMostro\n" + mostroScelto.toString());
//			
//		}while(mostroScelto.getVita() >= 1 & eroeScelto.getVita() >= 1);
//		
//		if(mostroScelto.getVita() >= 1 && eroeScelto.getVita() <= 0)
//			System.out.println("Il vincitore è: " + mostroScelto.getNome() + "!!!");
//		if(eroeScelto.getVita() >= 1 && mostroScelto.getVita() <= 0)
//			System.out.println("Il vincitore è: " + eroeScelto.getNome() + "!!!");
		
	}

}
