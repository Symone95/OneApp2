package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import entities.Eroe;
import entities.Mostro;
import entities.Personaggio;
import entities.Arma;
import entities.Armatura;
import entities.Entity;

import java.io.File;
import java.sql.*;

/**
 * facade of JDBC that uses object of java.sql and has methods for loading and executing query
 * @author Naga01
 *
 */
public class Database 
{
	private Connection db = null;
	
	/**
	 * constructor
	 * @param
	 */
	public Database()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			db = DriverManager.getConnection("jdbc:mysql://localhost:3306/oneapp2?user=root&password=root");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * compiles a PreparedStatement
	 * @param
	 * @return PreparedStatement
	 */
	private PreparedStatement compilePreparedStatement(String sql, String[] data)
	{
		PreparedStatement prs = null;
		
		try
		{
			prs = db.prepareStatement(sql);
			for(int i=1;i<=data.length;i++)
				prs.setString(i, data[i-1]);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return prs;
	}
	
	/**
	 * loads all queries from the repository starting from the name of the file
	 * @param
	 * @return String query
	 */
	public String loadQuery(String queryName)
	{
		String path = "C:\\Users\\...."+queryName+".sql";
		String prs = "";
		Scanner data = null;
		try
		{
			data = new Scanner(new File(path));
			while(data.hasNextLine())
				prs+=data.nextLine()+"\n";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		data.close();
		return prs;
	}
	
	/**
	 * returns the result of a query like an ArrayList of HashMap<String,String>
	 * @param
	 * @return ArrayList<HashMap<String,String>> data
	 */
	public ArrayList<HashMap<String,String>> read(String sql) 
	{
		ArrayList<HashMap<String,String>> prs = new ArrayList<HashMap<String,String>>();
		
		try 
		{
			Statement s = db.createStatement();
			ResultSet rs = s.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			while(rs.next())
			{
				HashMap<String,String> newMap = new HashMap<String,String>();
				for(int i=1;i<metaData.getColumnCount();i++)
					newMap.put(metaData.getColumnName(i), rs.getString(i));
				prs.add(newMap);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return prs;
	}
	
	/**
	 * returns the result of a query like an HashMap<String,String>
	 * @param
	 * @return HashMap<String,String> data
	 */
	public HashMap<String,String> readMap(String sql)
	{
		HashMap<String,String> prs = new HashMap<String,String>();
		
		try
		{
			Statement s = db.createStatement();
			ResultSet rs = s.executeQuery(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return prs;
	}
	
	//Metodo leggi
	public ResultSet queryResult(String sql) throws SQLException
	{
		PreparedStatement p = db.prepareStatement(sql);
		ResultSet rtn = p.executeQuery();
		
		try
		{
			PreparedStatement s = db.prepareStatement(sql);
			rtn = s.executeQuery(sql);
		}
		catch(SQLException e)
		{
			rtn = null;
			e.printStackTrace();
		}
		
		return rtn;
	}
	
	//START SIMONE
	public Entity load(String type, int id){
		PreparedStatement ps = null;
//		System.out.println("Nel metodo loadTest, prima dell'Entity -> DBResource");
		Entity ris = null;
//		System.out.println("Nel metodo loadTest, prima dell'if iniziale -> DBResource");
			try
			{	
				
				switch (type) {
				case "eroe":
					//select resources.*, users.id as idUs, users.email, users.password, users.level, users.shortnote, users.vote from users, resources where users.level = 'client' and users.id = resources.id and users.id = ?;
					ps = db.prepareStatement("select * from eroi where id = ?");
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						{
//								System.out.println("Ho creato un eroe");
								ris = new Eroe();
								for(int i=0;i<rs.getMetaData().getColumnCount();i++)
									ris.set(rs.getMetaData().getColumnName(i+1), rs.getString(i+1));
						}
					
//					System.out.println("Sono nel try dell'eroe: Query del metodo load: " + ps.toString());
				break;
				case "mostro":
//					System.out.println("Nel metodo load, prima del preparedStatement -> DBResource");
					ps = db.prepareStatement("select * from mostri where id = ?");
					ps.setInt(1, id);
					ResultSet rs2 = ps.executeQuery();
					if(rs2.next())
						{
//								System.out.println("Ho creato un mostro");
								ris = new Mostro();
								for(int i=0;i<rs2.getMetaData().getColumnCount();i++)
									ris.set(rs2.getMetaData().getColumnName(i+1), rs2.getString(i+1));
						}
					
					System.out.println("Sono nel try del mostro: Query del metodo load: " + ps.toString());
				break;
				case "arma":
					System.out.println("Nel metodo load, prima del preparedStatement -> DBResource");
					ps = db.prepareStatement("select * from armi where id = ?");
					ps.setInt(1, id);
					ResultSet rs3 = ps.executeQuery();
					if(rs3.next())
						{
//								System.out.println("Ho creato un'arma");
								ris = new Arma();
								for(int i=0;i<rs3.getMetaData().getColumnCount();i++)
									ris.set(rs3.getMetaData().getColumnName(i+1), rs3.getString(i+1));
						}
					
//					System.out.println("Sono nel try dell'arma: Query del metodo load: " + ps.toString());
				break;
				case "armatura":
					ps = db.prepareStatement("select * from armature where id = ?");
					ps.setInt(1, id);
					ResultSet rs4 = ps.executeQuery();
					if(rs4.next())
						{
//								System.out.println("Ho creato un'armatura");
								ris = new Armatura();
								for(int i=0;i<rs4.getMetaData().getColumnCount();i++)
									ris.set(rs4.getMetaData().getColumnName(i+1), rs4.getString(i+1));
						}
					
//					System.out.println("Sono nel try dell'arma: Query del metodo load: " + ps.toString());
				break;
				default:
					break;
				}
			
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			System.out.println("Classe dell'oggetto creato: " + ris.getClass().getSimpleName());
			
			return ris;
	}
	
	public ArrayList<Entity> load(String type){
		PreparedStatement ps = null;
//		System.out.println("Nel metodo loadTest, prima dell'Entity -> DBResource");
		ArrayList<Entity> ris = new ArrayList<Entity>();
//		System.out.println("Nel metodo loadTest, prima dell'if iniziale -> DBResource");
			try
			{	
				
				switch (type) {
				case "eroe":
					//select resources.*, users.id as idUs, users.email, users.password, users.level, users.shortnote, users.vote from users, resources where users.level = 'client' and users.id = resources.id and users.id = ?;
					ps = db.prepareStatement("select * from eroi");
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						{
								for(int i=0;i<rs.getMetaData().getColumnCount();i++)
									ris.add((Personaggio) this.load(type, rs.getInt("id")));
						}
					
//					System.out.println("Sono nel try dell'eroe: Query del metodo load: " + ps.toString());
				break;
				case "mostro":
//					System.out.println("Nel metodo load, prima del preparedStatement -> DBResource");
					ps = db.prepareStatement("select * from mostri");
					ResultSet rs2 = ps.executeQuery();
					if(rs2.next())
						{
								for(int i=0;i<rs2.getMetaData().getColumnCount();i++)
									ris.add((Personaggio) this.load(type, rs2.getInt("id")));
						}
					
					System.out.println("Sono nel try del mostro: Query del metodo load: " + ps.toString());
				break;
				default:
					break;
				}
			
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			System.out.println("Classe dell'oggetto creato: " + ris.getClass().getSimpleName());
			
			return ris;
	}
	
	public ResultSet leggi(String sql, int id){
		PreparedStatement ps = null ;
		ResultSet rs = null;
		try{
			ps = db.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	
	//END SIMONE
	
}
