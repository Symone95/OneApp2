package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import entities.Eroe;
import entities.Personaggio;

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
	public Personaggio loadPersonaggi(String type, int id){
		PreparedStatement ps = null ;
//		System.out.println("Nel metodo loadTest, prima dell'Entity -> DBResource");
		Personaggio ris = null;
//		System.out.println("Nel metodo loadTest, prima dell'if iniziale -> DBResource");
			try
			{
									
				switch (type) {
				case "eroe":
					//select resources.*, users.id as idUs, users.email, users.password, users.level, users.shortnote, users.vote from users, resources where users.level = 'client' and users.id = resources.id and users.id = ?;
					System.out.println("Nel metodo loadTest, prima del preparedStatement -> DBResource");
					ps = db.prepareStatement("select * from eroe where id = ?");
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						{
								System.out.println("Ho creato un client");
								ris = new Eroe();
								for(int i=0;i<rs.getMetaData().getColumnCount();i++)
									ris.set(rs.getMetaData().getColumnName(i+1), rs.getString(i+1));
						}
					
					System.out.println("Sono nel try: Query del metodo loadUser: " + ps.toString());
				break;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			
			return ris;
	}
	//END SIMONE
	
}
