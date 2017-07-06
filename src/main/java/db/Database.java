package db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.sql.DriverManager;
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
	public Database(String path)
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			db = DriverManager.getConnection(path);
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
	public Hash
}
