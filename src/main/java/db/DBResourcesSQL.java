package db;

import entities.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.naming.spi.DirStateFactory.Result;

// Ho aggiunto due metodi "categories" e "qualifications"

public class DBResourcesSQL implements IDBResources{
	
	//16/03/2017 Simone e Davide
	public static String SELECTUSERS = "select resources.*, users.* from users, resources where users.level = 'client' and users.id = resources.id and users.id = ?";
	public static String SELECTADMIN = "select users.* from users where level = 'admin' and users.id = ?;";
	public static String SELECTAZIENDA = "select users.*, company.* from users, company where level = 'azienda' and users.id = ?;";
	public static String SELECTDEVELOPER = "select users.* from users where level = 'developer' and users.id = ?;";
	
	public static String SELECTUSERSBYIDRESOURCE = "select * from users, resources where users.idResource = resources.id and users.idResource = ?;";
	
	//simone e davide 24/2/17
	private static String DELETEHAVE="DELETE FROM antlers.have WHERE idresource=?";
	private static String DELETETEC="DELETE FROM antlers.tecnicsinfo WHERE idresource=?";
	private static String DELETEEXP="DELETE FROM antlers.experiences WHERE idresource=?";
	private static String DELETESKILL="DELETE FROM antlers.skills WHERE id=?";
	private static String DELETESPEAK="DELETE FROM antlers.speak WHERE idResource=?";
	private static String DELETERECORDQUIZ="DELETE FROM antlers.recordquiz WHERE iduser=?";
	private static String DELETERECORDQUIZTYPE = "DELETE FROM antlers.recordquiz WHERE idtypequiz=?";
	//ren po giovanni 01-02-17
	//Simone e Dennis 10/02/2017
	private static String SELECTRESOURCESSKILLS="SELECT antlers.skills.name as name, antlers.skills.id as id, antlers.have.years as years, antlers.skills.category as category, antlers.skillcategory.name as namecategory FROM antlers.skills, antlers.have, resources, antlers.skillcategory WHERE antlers.skills.category = antlers.skillcategory.id and antlers.have.idskill = antlers.skills.id and antlers.have.idresource = resources.id and resources.id = ? ORDER BY years DESC;";
	private static String SELECTRESOURCESEXP = "SELECT antlers.experiences.id as id, antlers.experiences.idresource as idresource, antlers.experiences.datefrom as datefrom, antlers.experiences.dateto as dateto, antlers.experiences.idresource, antlers.experiences.company, antlers.experiences.mansions, antlers.experiences.role, antlers.experiences.description as description, antlers.experiences.verificated as verificated FROM antlers.experiences, resources where antlers.experiences.idresource = resources.id and resources.id = ?;";
	private static String SELECTRESOURCESCATEGORIES = "SELECT antlers.users.name as name, antlers.categories.name as category, antlers.categories.description as description FROM resources, antlers.users, antlers.tecnicsinfo, antlers.categories WHERE resources.id = antlers.users.id and resources.id=antlers.tecnicsinfo.idresource and antlers.tecnicsinfo.idcategory=antlers.categories.id and resources.id=?";
	private static String MAXIDRESOURCE ="SELECT MAX(id) as id FROM resources";
	private static String DELETESKILLFROMRESOURCE = "DELETE FROM antlers.have WHERE idresource=? and idskill=?";
	private static String ADDUSER = "INSERT INTO users(password, level, vote, shortnote, email, name, surname) values(md5(?), ?, ?, ?, ?, ?, ?);";
	private static String ADDNEWSKILL = "INSERT INTO antlers.skills(name,category) values(?,?)";
	private static String DELETEUSER = "DELETE FROM users where id = ?";
	private static String DELETEEXPERIENCE = "DELETE FROM antlers.experiences where id=?";
	private static String STARTQUIZ="INSERT INTO antlers.recordquiz(idcourse, idtypequiz, iduser, startdate) values(?, ?, ?, ?)";
	private static String STARTEDQUIZ = "SELECT * FROM antlers.recordquiz WHERE idcourse=? and idtypequiz=? and iduser=?";
	private static String FINISHQUIZ="update recordquiz set mark=? , answers=? , finishdate=? where id = ?";
	//Simone e Dennis 13/02/2017
	private static String UPDATEUSER = "UPDATE users SET level = ?, vote = ? , shortnote = ?, email = ?, name = ?, surname = ?, banned=?, vendibile=? WHERE id = ?;";
	private static String UPDATEUSERPASS = "UPDATE users SET level = ?, vote = ? , shortnote = ?, email = ?, name = ?, surname = ?, banned=?, password=md5(?), vendibile = ? WHERE id = ?;";
	
	private static String MAILUSERS="users.email";
	private static String TABLEUSERS = "users";
	private static String TABRESOURCES="resources";
	private static String TABSKILLS="skills";
	private static String IDRESOURCE="resources.id";
	private static String IDCOMPANY="company.id";
	private static String IDSKILL="skills.id";
	private static String EMAILRESOURCE="resources.email";
	private static String TABHAVE="have";
	private static String TABCATEGORIES="categories";
	private static String TABRESOURCESJOINTABHAVE="resources.id=have.idresources";
	private static String TABSKILLSJOINTABHAVE="skills.id=have.idskills";
	private static String TABCATEGORIESJOINTABSKILLS="skills.idcategories=categories.id";
	private static String CATEGORYNAME="categories.name";
	private static String SKILLNAME="skills.name";
	private static String SKILLEVEL="have.level";
	private static String SPLIT="=";
	private static String COURSELIST ="SELECT * FROM antlers.attend WHERE idresource=?";
	private static String CANDIDATE="INSERT INTO antlers.attend(idcourse, idresource) VALUES (?,?)";
	private static String UPDATERESOURCE="UPDATE resources SET nationality=?, drivingLicense=?, car=?, workingstate=?, birthdate=?, CF=?, domicile=?, idqualification=?, typequalification=?, telephon=?, gender=?, nativeLanguage=? WHERE id=?"; 
	private static String EDITEXPERIENCE="UPDATE experiences SET role=? , mansions=? , company=? , datefrom = ? , dateto = ?, description = ?, verificated=? WHERE idresource = ? and id = ?";
	private static String DELETERESOURCE="DELETE FROM resources WHERE id = ?";
	private static String SAVERESOURCE="INSERT INTO resources(email, name, surname, workingstate, birthdate, idqualification, CF, domicile, typequalification, gender, telephon, drivingLicense, car, nationality, nativeLanguage) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 
	private static String SAVEEXPERIENCE="insert into experiences (idresource,datefrom,dateto,mansions,role,company, description)values(?,?,?,?,?,?,?)";
	private static String SAVESKILL="INSERT INTO skills(name,level) VALUES(?, ?)"; 
	// ADD START 06/02/2017 HIROKI
	private static String SELECT_SKILLS_CON_ID = "SELECT id FROM skills WHERE name = ?";
	private static String ADDSKILL="INSERT INTO have (idresource,idskill,years) VALUES (?,?,?)";
	//ren po giovanni 22-02-17
	private static String SELECTIDEXPERIENCE="select MAX(id) as id from experiences where datefrom=? and dateto=? and idresource=?";
	// ADD E N D 06/02/2017 HIROKI
	// ADD START 07/02/2017 HIROKI
	private static String SELECT_ALL_CATEGORIES = "SELECT * FROM categories";
	private static String SELECT_ALL_QUALIFICATION = "SELECT * FROM qualification";
	// ADD E N D 07/02/2017 HIROKI
	private static String LOGIN = "select email, password, level, banned from resources, users where email = ? and password = md5(?)";
	
	//Simone e Davide 02/03/2017
	private static String SELECTALLFL = "SELECT * FROM foreignlanguages;";
	private static String SELECTRESOURCEFL1 = "SELECT speak.*, foreignlanguages.* FROM speak, foreignlanguages, resources where foreignlanguages.id = speak.idForeignLanguage and resources.id = speak.idResource;";

	private static String SELECTRESOURCEFL = "SELECT speak.*, foreignlanguages.* FROM speak, foreignlanguages, resources where foreignlanguages.id = speak.idForeignLanguage and resources.id = speak.idResource and resources.id = ?;";
	private static String EDITFL="update foreignlanguages set foreignlanguages.name = ? where id = ?;";
	private static String EDITRESOURCESPEAK = "update speak set idForeignLanguage = ?, level = ? where idResource = ? and idForeignLanguage = ?; ";
	private static String SELECTIDFL="select id from foreignlanguages where name = ?;";
	private static String SELECT_EMAILINFO = "SELECT * FROM emailsinfo WHERE address = ?";
	private static String REMOVERESOURCEFL = "delete from speak where idResource = ? and idForeignLanguage = ?; ";
	private static String ADDRESOURCEFL = "insert into speak(idResource, idForeignLanguage, level) values(?, ?, ?);";
	private static String ADDQUIZ="insert into quiz(idtype,question,answer1,answer2,answer3,answer4,answer,obbligatory) values (?,?,?,?,?,?,?,?)";
	private static String SELECTALLQUIZ="select * from quiz";
	private static String EDITQUIZ="update quiz set question=? , idtype=? , answer1=? , answer2=? , answer3=? , answer4=? , answer=? , obbligatory=? where id=?";
	//ren 06-03-17
	private static String ADDTYPE="insert into type(name) values (?)";
	private static String DELETETYPE="delete from type where id=?";
	private static String DELETEQUIZ="delete from quiz where idtype=?";
	private static String SAVECOURSE="insert into courses (name,datefrom,dateto,notes)values(?,?,?,?)";
	private static String SELECTTYPEQUIZ ="SELECT * FROM type";
	private static String SELECTALLCOURSES = "SELECT * FROM courses";
	private static String SELECTALLFUTURECOURSES ="SELECT * FROM courses WHERE datefrom>current_date()";
	private static String REMOVEQUIZ="delete from compile where idcourse=? and idtype=?";
	private static String ADDQUIZTOCOMPILE="insert into compile (idcourse,idtype) values (?,?)";
	
	private static String SELECTCOMPILE = "SELECT * FROM compile";
	private static String SELECTALLUSERS = "SELECT * FROM users";
	private static String DELETEONEQUIZ = "delete from quiz where id=?";
	
	//START Simone e Davide 13/03/2017
	private static String DELETECOURSE = "DELETE FROM courses WHERE id = ?";
	private static String DELETECOURSEATTEND = "DELETE FROM attend WHERE idcourse = ?";
	private static String DELETECOURSERECORDQUIZ = "DELETE FROM recordquiz WHERE idcourse = ?";
	private static String DELETECOMPILE ="DELETE FROM compile WHERE idcourse=?";
	private static String DELETECOMPILETYPEQUIZ = "DELETE FROM compile WHERE idtype=?";
	//END Simone e Davide 13/03/2017
	
	private Connection db = null;
	
	public DBResourcesSQL(String path)
	{
		try 
		{
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
//			db = DriverManager.getConnection(path,"antlers","antlers_sgi01");
			
			Class.forName("com.mysql.jdbc.Driver");
			db = DriverManager.getConnection(path);
			
		}
		catch(Exception e) 
		{
			System.out.println("No DB. terminating.");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	//ren po giovanni 01-02-17
	//Yari, modifiche alla gestione eccezioni e aggiunta riga 56. 2/2/2017
	public Entity load(int id)
	{
		Entity ris = null;
		
		if(id<=30000)
		{
			try
			{
				PreparedStatement ps = db.prepareStatement("select resources.*,users.name, users.surname, users.email, users.vendibile from resources, antlers.users where resources.id=? and resources.id=users.id");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				ris = new Resource();
				if(rs.next())
				{
					for(int i=0;i<rs.getMetaData().getColumnCount();i++)
						ris.set(rs.getMetaData().getColumnName(i+1), rs.getString(i+1));
					//Trovare i suoi skill e metterli nella ArrayList di Skill
						PreparedStatement ps2=
							db.prepareStatement
							(
									SELECTRESOURCESSKILLS
							);
						//Id della risorsa...
						ps2.setInt(1, id);
						ResultSet rs2 = ps2.executeQuery();
						
						if(!(ris==null) && (!(rs2==null)))
							while(rs2.next())
								((Resource) ris).skills.add(new Skill(rs2.getInt("id"), rs2.getString("name"), rs2.getInt("years"), rs2.getInt("category") , rs2.getString("namecategory")));
						//Simone e Dennis 09/02/2017
						//Select delle esperienze
						PreparedStatement ps3 =
								db.prepareStatement
								(
									SELECTRESOURCESEXP
								);
							
							//Id della risorsa...
							ps3.setInt(1, id);
							ResultSet rs3 = ps3.executeQuery();
							
							if(!(ris==null) && (!(rs3==null)))
								while(rs3.next())//new Date(Integer.parseInt(rs3.getString("datefrom")) - new Date(Integer.parseInt(rs3.getString("dateto")))
									((Resource) ris).experiences.add(new Experience(rs3.getString("datefrom"), 
																					rs3.getString("dateto"), 
																					rs3.getString("role"), 
																					rs3.getString("mansions"), 
																					rs3.getString("company"),
																					rs3.getString("id"),
																					rs3.getString("idresource"),
																					rs3.getString("description"),
																					rs3.getString("verificated")));
							//Yari 16/2/17
							//Select delle categories
							PreparedStatement ps4 =
									db.prepareStatement
									(
										SELECTRESOURCESCATEGORIES
									);
								
								//Id della risorsa...
								ps4.setInt(1, id);
								ResultSet rs4 = ps4.executeQuery();
								
								if(!(ris==null) && (!(rs4==null)))
									while(rs4.next())//new Date(Integer.parseInt(rs3.getString("datefrom")) - new Date(Integer.parseInt(rs3.getString("dateto")))
										((Resource) ris).categories.add(new Category (rs4.getString("category"), rs4.getString("description")));
								
								//Simone e Davide 01/03/2017
								PreparedStatement ps5 =
										db.prepareStatement
										(
											SELECTRESOURCEFL
										);
									
									//Id della risorsa...
									ps5.setInt(1, id);
									ResultSet rs5 = ps5.executeQuery();
									
									if(!(ris==null) && (!(rs5==null)))
										while(rs5.next())
											((Resource) ris).fl.add(new ForeignLanguages(rs5.getString("id"), 
																					 	 rs5.getString("name"), 
																					 	 rs5.getString("level")));
				}
				else
				{
					return null;
				}
			}
			catch(SQLException e)
			{
				System.out.println("Errore nel caricamento di "+id+". select * from "+TABRESOURCES+" where id="+id);
				e.printStackTrace();
			}
		}		

		//monella yari 3/2/17
		if(id>30000)
		{
			try
			{
				PreparedStatement ps = db.prepareStatement("select * from "+TABSKILLS+" where id=?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					ris = new Skill(rs.getInt("id"),rs.getString("name"),rs.getInt("category"));
				}
				else
				{
					System.out.println("i did not find you");
					System.exit(-1);
				}
			}
			catch(SQLException e)
			{
				System.out.println("Errore nel caricamento di "+id+". select * from "+TABSKILLS+" where id="+id);
				e.printStackTrace();
			}
		}		
		return ris;
	}
	
	public Company loadCompany(int id){
		
		Company ris = null;
		PreparedStatement p = null;
		try{
			
			p = db.prepareStatement("select users.*, company.* from company, users where company.id = ? and company.id = users.id");
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			
			if(rs.next())
				ris = new Company(rs.getString("id"), rs.getString("level"), rs.getString("email"), rs.getString("verificated"), rs.getString("banned"), rs.getString("password"), rs.getString("personaRiferimento"), rs.getString("telephone"), rs.getString("name"), rs.getString("shortnote"), rs.getString("vote"));
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella query: " + p.toString());
		}
		
		return ris;
	}

	public Developer loadDeveloper(int id){
		
		Developer ris = null;
		PreparedStatement p = null;
		try{
			
			p = db.prepareStatement("select users.* from users where users.id = ?");
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			
			if(rs.next())
				ris = new Developer(rs.getString("id"), rs.getString("level"), rs.getString("email"), rs.getString("verificated"), rs.getString("banned"), rs.getString("password"), rs.getString("name"), rs.getString("shortnote"), rs.getString("vote"));
			
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella query: " + p.toString());
		}
		
		return ris;
	}
	
	//Simone e Dennys 14/02/2017
	//PROBLEMA: PRENDE L'ID DELLA RISORSA E NON L'ID DELL'UTENTE
	public Entity loadUser(int id, String level)
	{
		PreparedStatement ps = null ;
//		System.out.println("Nel metodo loadTest, prima dell'Entity -> DBResource");
		Entity ris = null;
//		System.out.println("Nel metodo loadTest, prima dell'if iniziale -> DBResource");
			try
			{
									
				switch (level) {
				case "client":
					//select resources.*, users.id as idUs, users.email, users.password, users.level, users.shortnote, users.vote from users, resources where users.level = 'client' and users.id = resources.id and users.id = ?;
//					System.out.println("Nel metodo loadTest, prima del preparedStatement -> DBResource");
					ps = db.prepareStatement(SELECTUSERS);
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					if(rs.next())
						{
//							System.out.println("Ho creato un client");
							ris = new ExternalCompany();
							for(int i=0;i<rs.getMetaData().getColumnCount();i++)
								ris.set(rs.getMetaData().getColumnName(i+1), rs.getString(i+1));
						}
					
//					System.out.println("Sono nel try: Query del metodo loadUser: " + ps.toString());
					break;
					
				case "admin":
					//select users.* from users where level = 'admin' and users.id = ?;
//					System.out.println("Nel metodo loadTest, prima del preparedStatement -> DBResource");
					ps = db.prepareStatement(SELECTADMIN);
					ps.setInt(1, id);
					rs = ps.executeQuery();
//					System.out.println("Nel metodo loadTest, dopo il preparedStatement e l'executeQuery -> DBResource");
					if(rs.next())
						{
//							System.out.println("Ho creato un client");
							ris = new ExternalCompany();
							for(int i=0;i<rs.getMetaData().getColumnCount();i++)
								ris.set(rs.getMetaData().getColumnName(i+1), rs.getString(i+1));
						}
					
//					System.out.println("Sono nell'else: Query del metodo loadUser: " + ps.toString());
					break;
					
				case "azienda":
					ps = db.prepareStatement(SELECTAZIENDA);
					ps.setInt(1, id);
					rs = ps.executeQuery();
					System.out.println("Nel metodo loadTest, dopo il preparedStatement e l'executeQuery -> DBResource");
					if(rs.next())
						{
							System.out.println("Ho creato un azienda");
							ris = new ExternalCompany();
							for(int i=0;i<rs.getMetaData().getColumnCount();i++)
								ris.set(rs.getMetaData().getColumnName(i+1), rs.getString(i+1));
						}
					
					System.out.println("Sono nell'else: Query del metodo loadUser: " + ps.toString());
					break;
					
				case "developer":
					ps = db.prepareStatement(SELECTDEVELOPER);
					ps.setInt(1, id);
					rs = ps.executeQuery();
					System.out.println("Nel metodo loadTest, dopo il preparedStatement e l'executeQuery -> DBResource");
					if(rs.next())
						{
							System.out.println("Ho creato un developer");
							ris = new ExternalCompany();
							for(int i=0;i<rs.getMetaData().getColumnCount();i++)
								ris.set(rs.getMetaData().getColumnName(i+1), rs.getString(i+1));
						}
					
					System.out.println("Sono nell'else: Query del metodo loadUser: " + ps.toString());
					break;
			}

			}catch(SQLException e){
				e.getMessage();
				System.out.println("Sono nel catch: Query del metodo loadUser: " + ps.toString());
			}
		
		return ris;
	}
	
	public String generaPass(){
		
		String password = "";
		
		for (int i = 0; i < 10; i++) {
			password = RandomStringUtils.randomAlphanumeric(10);
			System.out.println(password);
			}
		
		
		return password;
	}
	
	public boolean registra(String email, String password, String level, int vote, String note, String CF, String name, String surname, String idQualification, String birthdate){
		
		boolean ris = false;
		int idUs = 0;
		PreparedStatement p = null;
		System.out.println("Sono nel metodo registra");
		System.out.println("ADD USER "+email + " " + password+" - Livello passato: " + level+" - "+vote+" - "+note+" - "+name+" - "+surname);
			try{
				
				if(level.equals("client")){
					System.out.println("Sono nell'if");
					//INSERT INTO users(password, level, vote, shortnote, email, name, surname) values(md5(?), ?, ?, ?, ?, ?, ?);
					p = db.prepareStatement(ADDUSER);
					p.setString(1, password);
					p.setString(2, level);
					p.setInt(3, vote);
					p.setString(4, note);
					p.setString(5, email);
					p.setString(6, name);
					p.setString(7, surname);
					p.executeUpdate();
					System.out.println("Sono nel try e questa � la query dell'adduser" + p.toString());
					
					//Prendo l'id del nuovo user creato e lo inserisco nella tabella resources
					p = db.prepareStatement("select max(id) as id from users");
					ResultSet rs = p.executeQuery();
					if(rs.next())
						idUs = rs.getInt("id");
					System.out.println("Sono nel try e questa � la query per l'id: " + p.toString());
					
					//MODIFICARE ANCHE I METODI DELETEUSER E EDITUSER
					p = db.prepareStatement("insert into resources(CF, id, idqualification, birthdate, typequalification, nativeLanguage, telephon , car, gender, drivingLicense, workingstate, domicile, nationality) values(?, ?, ?, ?, \"\", \"\", \"\", \"No\",\"M\", \"\", \"Libero\", \"\", \"\")");
					p.setString(1, CF);
					p.setInt(2, idUs);
					p.setInt(3, Integer.parseInt(idQualification));
					p.setString(4, birthdate);
					p.executeUpdate();
					System.out.println("Sono dopo l'insert resource");
					System.out.println("Sono nel try e questa � la query dell'addResource: " + p.toString());
					ris = true;		
					System.out.println("Sono nel try e questa � la query dell'adduser: " + p.toString());	
				}else{
					System.out.println("Sono nell'else");
					//INSERT INTO users(password, level, vote, shortnote, email, name, surname) values(md5(?), ?, ?, ?, ?, ?, ?);
					p = db.prepareStatement(ADDUSER);
					p.setString(1, password);
					p.setString(2, level);
					p.setInt(3, vote);
					p.setString(4, note);
					p.setString(5, email);
					p.setString(6, name);
					p.setString(7, surname);
					p.executeUpdate();
//					System.out.println("Sono nel try e questa � la query dell'adduser: " + p.toString());
					ris = true;							
				}	
			}catch(SQLException e){
				e.getMessage();
//				System.out.println("Sono nel catch e questa � la query: " + p.toString());
			}
		return ris;
		
	}
	
	//Simone e Dennys 10/02/2017
	public boolean addUser(String email, String level, int vote, String note, String CF, String name, String surname, String idQualification, String birthdate){
		boolean ris = false;
		int idUs = 0;
		String password = generaPass();
		PreparedStatement p = null;
		System.out.println("Sono nel metodo adduser");
		System.out.println("ADD USER "+email + " " + password+" - Livello passato: " + level+" - "+vote+" - "+note+" - "+name+" - "+surname);
//		System.out.println(level); 
			try{
				
				if(level.equals("client")){
					System.out.println("Sono nell'if");
					//INSERT INTO users(password, level, vote, shortnote, email, name, surname) values(md5(?), ?, ?, ?, ?, ?, ?);
					p = db.prepareStatement(ADDUSER);
					p.setString(1, password);
					p.setString(2, level);
					p.setInt(3, vote);
					p.setString(4, note);
					p.setString(5, email);
					p.setString(6, name);
					p.setString(7, surname);
					p.executeUpdate();
//					System.out.println("Sono nel try e questa � la query dell'adduser" + p.toString());
					
					//Prendo l'id del nuovo user creato e lo inserisco nella tabella resources
					p = db.prepareStatement("select max(id) as id from users");
					ResultSet rs = p.executeQuery();
					if(rs.next())
						idUs = rs.getInt("id");
//					System.out.println("Sono nel try e questa � la query per l'id: " + p.toString());
					
					//MODIFICARE ANCHE I METODI DELETEUSER E EDITUSER
					p = db.prepareStatement("insert into resources(CF, id, idqualification, birthdate, typequalification, nativeLanguage, telephon , car, gender, drivingLicense, workingstate, domicile, nationality) values(?, ?, ?, ?, \"\", \"\", \"\", \"No\",\"M\", \"\", \"Libero\", \"\", \"\")");
					p.setString(1, CF);
					p.setInt(2, idUs);
					p.setInt(3, Integer.parseInt(idQualification));
					p.setString(4, birthdate);
					p.executeUpdate();
//					System.out.println("Sono nel try e questa � la query dell'addResource: " + p.toString());
					ris = true;		
//					System.out.println("Sono nel try e questa � la query dell'adduser: " + p.toString());	
				}else{
					System.out.println("Sono nell'else");
					//INSERT INTO users(password, level, vote, shortnote, email, name, surname) values(md5(?), ?, ?, ?, ?, ?, ?);
					p = db.prepareStatement(ADDUSER);
					p.setString(1, password);
					p.setString(2, level);
					p.setInt(3, vote);
					p.setString(4, note);
					p.setString(5, email);
					p.setString(6, name);
					p.setString(7, surname);
					p.executeUpdate();
//					System.out.println("Sono nel try e questa � la query dell'adduser: " + p.toString());
					ris = true;							
				}	
			}catch(SQLException e){
				e.getMessage();
				System.out.println("Sono nel catch e questa � la query: " + p.toString());
			}
		return ris;
	}
	
	public boolean addAzienda(String name, String emailAzienda, String voteAzienda, String telephone, String personaRif, String note){
		boolean ris = false;
		int idAzienda = 0;
		String password = generaPass();
		PreparedStatement p = null;
		System.out.println("Sono nel metodo adduser");
		System.out.println("ADD USER "+emailAzienda + " " + password+" - Livello passato: " + " - "+note+" - "+name);
//		System.out.println(level); 
		try{
			
			System.out.println("Sono nell'if");
			//INSERT INTO users(password, level, vote, shortnote, email, name, surname) values(md5(?), ?, ?, ?, ?, ?, ?);
			p = db.prepareStatement(ADDUSER);
			p.setString(1, password);
			p.setString(2, "azienda");
			p.setString(3, voteAzienda);
			p.setString(4, note);
			p.setString(5, emailAzienda);
			p.setString(6, name);
			p.setString(7, " ");
			p.executeUpdate();
//			System.out.println("Sono nel try e questa � la query dell'adduser" + p.toString());
			
			//Prendo l'id del nuovo user creato e lo inserisco nella tabella resources
			p = db.prepareStatement("select max(id) as id from users");
			ResultSet rs = p.executeQuery();
			if(rs.next())
				idAzienda = rs.getInt("id");
//			System.out.println("Sono nel try e questa � la query per l'id: " + p.toString());
			
			//MODIFICARE ANCHE I METODI DELETEUSER E EDITUSER
			p = db.prepareStatement("insert into company(id, personaRiferimento, telephone) values(?, ?, ?)");
			p.setInt(1, idAzienda);
			p.setString(2, personaRif);
			p.setString(3, telephone);
			p.executeUpdate();
//			System.out.println("Sono nel try e questa � la query dell'addResource: " + p.toString());
			ris = true;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella query: " + p.toString());
		}
//					System.out.println("Sono nel try e questa � la query dell'adduser: " + p.toString());	
		
		return ris;
	}
	
	public boolean addAzienda(String name, String emailAzienda, String voteAzienda, String telephone, String personaRif, String note, String password){
		boolean ris = false;
		int idAzienda = 0;
		PreparedStatement p = null;
		try{
			
			System.out.println("Sono nell'if");
			//INSERT INTO users(password, level, vote, shortnote, email, name, surname) values(md5(?), ?, ?, ?, ?, ?, ?);
			p = db.prepareStatement(ADDUSER);
			p.setString(1, password);
			p.setString(2, "azienda");
			p.setString(3, voteAzienda);
			p.setString(4, note);
			p.setString(5, emailAzienda);
			p.setString(6, name);
			p.setString(7, " ");
			p.executeUpdate();
//			System.out.println("Sono nel try e questa � la query dell'adduser" + p.toString());
			
			//Prendo l'id del nuovo user creato e lo inserisco nella tabella resources
			p = db.prepareStatement("select max(id) as id from users");
			ResultSet rs = p.executeQuery();
			if(rs.next())
				idAzienda = rs.getInt("id");
//			System.out.println("Sono nel try e questa � la query per l'id: " + p.toString());
			
			//MODIFICARE ANCHE I METODI DELETEUSER E EDITUSER
			p = db.prepareStatement("insert into company(id, personaRiferimento, telephone) values(?, ?, ?)");
			p.setInt(1, idAzienda);
			p.setString(2, personaRif);
			p.setString(3, telephone);
			p.executeUpdate();
//			System.out.println("Sono nel try e questa � la query dell'addResource: " + p.toString());
			ris = true;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Errore nella query: " + p.toString());
		}
//					System.out.println("Sono nel try e questa � la query dell'adduser: " + p.toString());	
		
		return ris;
	}
	
	public boolean changePass(String email,String oldPsw,String password, String confirmPassword)
	{
		boolean ris = false;
		int idUs = 0;
		PreparedStatement p =null;
		System.out.println("sono nel changePass");
		try{
			// prendere l'id tramite la mail
			System.out.println("sono nel try");
			p = db.prepareStatement("select id from users where email =? and password = md5(?);");
			p.setString(1, email);
			p.setString(2, oldPsw);
			ResultSet rs =p.executeQuery();

			System.out.println("sono nel try"+ p.toString());
			if(rs.next())
				idUs = rs.getInt("id");
				System.out.println("questo � l'id user:"+ idUs);
			
			// cambiare la password di quella mail
			p = db.prepareStatement("UPDATE users SET password = md5(?) WHERE id=?;");
			p.setString(1, password);
			p.setInt(2, idUs);
			p.executeUpdate();
			ris = true;
			System.out.println("Sono nel try: Query del changePass: " + p.toString());
		}catch(SQLException e){
			System.out.println("sono nel catch");
			e.printStackTrace();
		}
		
		return ris;
	}
	
	public boolean randomPass(String email)
	{
		boolean ris = false;
		int idUs = 0;
		String password = "";
		PreparedStatement p =null;
		System.out.println("sono nel changePass");
		try{
			// prendere l'id tramite la mail
			System.out.println("sono nel try");
			p = db.prepareStatement("select id from users where email =?;");
			p.setString(1, email);
			ResultSet rs =p.executeQuery();

			System.out.println("sono nel try"+ p.toString());
			if(rs.next())
				idUs = rs.getInt("id");
				System.out.println("questo � l'id user:"+ idUs);
			//generare una password 
				for (int i = 0; i < 10; i++) {
					password = RandomStringUtils.randomAlphanumeric(10);
					System.out.println(password);
					}
			// cambiare la password di quella mail
			p = db.prepareStatement("UPDATE users SET password = md5(?) WHERE id=?;");
			p.setString(1, password);
			p.setInt(2, idUs);
			p.executeUpdate();
			ris = true;
			System.out.println("Sono nel try: Query del changePass: " + p.toString());
			
			//inviare una mail con la password
			
			SendMailTLS d = new SendMailTLS();
			
			System.out.println(d.main(new String[]{email}, "password temporanea", "Salve, la sua password � stata cambiata come da lei richiesto. \nPassword = "+password));
			
		}catch(SQLException e){
			System.out.println("sono nel catch");
			e.printStackTrace();
		}
		
		return ris;
	}
	//Dennis 10/02/2017
	//START Simone e Davide 17/03/2017 
	//START Simone e Davide 17/03/2017 
		public boolean deleteUser(String email, String level){
			boolean ris = false;
			int idRes = 0;
			PreparedStatement p = null;
//			System.out.println("SONO NEL METODO DELETEUSER");
			try{
				
				if(level.equals("client")){
					
					p = db.prepareStatement("select id from users where email = ?");
					p.setString(1, email);
					System.out.println("Query dell'id: " + p.toString());
					ResultSet rs = p.executeQuery();
					if(rs.next())
						idRes = rs.getInt("id");
					
					//DELETE FROM antlers.have WHERE idresource=?
					p = db.prepareStatement(DELETEHAVE);
					p.setInt(1, idRes);
					p.executeUpdate();
					System.out.println("Query delete dell'have: " + p.toString());
					
					//DELETE FROM antlers.tecnicsinfo WHERE idresource=?
					p = db.prepareStatement(DELETETEC);
					p.setInt(1, idRes);
					p.executeUpdate();
					System.out.println("Query delete del tecnicsinfo: " + p.toString());
					
					//DELETE FROM antlers.experiences WHERE idresource=?
					p = db.prepareStatement(DELETEEXP);
					p.setInt(1, idRes);
					p.executeUpdate();
					System.out.println("Query delete dell'experience: " + p.toString());
					
					//DELETE FROM antlers.speak WHERE idResource=?
					p = db.prepareStatement(DELETESPEAK);
					p.setInt(1, idRes);
					p.executeUpdate();
					System.out.println("Query delete del speak: " + p.toString());
					
					//DELETE FROM antlers.recordquiz WHERE iduser=?
					p = db.prepareStatement(DELETERECORDQUIZ);
					p.setInt(1, idRes);
					p.executeUpdate();
					System.out.println("Query delete del RECORDqUIZ: " + p.toString());
					
					//DELETE FROM users where idResource = ?
					p = db.prepareStatement(DELETEUSER);
					p.setInt(1, idRes);
					p.executeUpdate();
					System.out.println("Query dell'user: " + p.toString());
					System.out.println("Query delete dell'user: " + p.toString());
					
					//DELETE FROM resources WHERE id=?
					p = db.prepareStatement(DELETERESOURCE);
					p.setInt(1, idRes);
					p.executeUpdate();
					System.out.println("Query della resource: " + p.toString());
				}else{
					p = db.prepareStatement("select id from users where email = ?");
					p.setString(1, email);
					System.out.println("Query dell'id: " + p.toString());
					ResultSet rs = p.executeQuery();
					if(rs.next())
						idRes = rs.getInt("id");

					//DELETE FROM users where idResource = ?
					p = db.prepareStatement(DELETEUSER);
					p.setInt(1, idRes);
					p.executeUpdate();
					System.out.println("Query delete dell'user: " + p.toString());
					
					}
				
				ris = true;
				System.out.println("Sono nel try: Query del delete: " + p.toString());
			}catch(SQLException e){
				e.getMessage();
				System.out.println("Sono nel catch: Query del delete: " + p.toString());
			}
			
			return ris;
		}
	//Simone e Dennis 10/02/2017

	//Simone e Dennis 13/02/2017
	public boolean updateUser(int id, String email, String vote, String shortnote, String nome, String cognome, String level, String banned, String vendibile){
		boolean ris = false;
		int idRes = 0;
		PreparedStatement p = null;
		try{
				//UPDATE users SET level = ?, vote = ? , shortnote = ?, email = ?, name = ?, surname = ?, banned=?, vendibile=? WHERE id = ?;
				p = db.prepareStatement(UPDATEUSER);
				p.setString(1, level);
				p.setString(2, vote);
				p.setString(3, shortnote);
				p.setString(4, email);
				p.setString(5, nome);
				p.setString(6, cognome);
				p.setString(7, banned);
				p.setString(8, vendibile);
				p.setInt(9, id);
				p.executeUpdate();
				System.out.println("Query dell'update user: " + p.toString());

			
			ris = true;
	
		}catch(SQLException e){
			e.getMessage();
			System.out.println("Sono nel catch: Query dell'update: " + p.toString());
		}
		return ris;
	}
	
	public boolean updateUser(int id, String email, String vote, String shortnote, String nome, String cognome, String level, String banned, String password, String vendibile){
		boolean ris = false;
		int idRes = 0;
		PreparedStatement p = null;
		try{
			
				
				//UPDATE users SET level = ?, vote = ? , shortnote = ?, email = ?, name = ?, surname = ?, banned=?, password=md5(?), vendibile = ? WHERE id = ?;
				p = db.prepareStatement(UPDATEUSERPASS);
				p.setString(1, level);
				p.setString(2, vote);
				p.setString(3, shortnote);
				p.setString(4, email);
				p.setString(5, nome);
				p.setString(6, cognome);
				p.setString(7, banned);
				p.setString(8, password);
				p.setString(9, vendibile);
				p.setInt(10, id);
				p.executeUpdate();

			
			ris = true;
	
		}catch(SQLException e){
			e.getMessage();
			System.out.println("Sono nel catch: Query dell'update: " + p.toString());
		}
		return ris;
	}

	
	public ArrayList<Resource> resources() {
		ArrayList<Resource> r=new ArrayList<Resource>();
		try{
			PreparedStatement p = db.prepareStatement("select resources.id from resources, users where resources.id=users.id and users.level='client' order by workingstate, surname");
			ResultSet rs=p.executeQuery();
			while(rs.next())
			{
				r.add( (Resource) this.load(rs.getInt(IDRESOURCE)) );
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		return r;
	}
	
	public ArrayList<Resource> resourcesVendibili() {
		ArrayList<Resource> r=new ArrayList<Resource>();
		try{
			PreparedStatement p = db.prepareStatement("select resources.id from resources, users where resources.id=users.id and users.level='client' and users.vendibile='Si' order by workingstate, surname");
			ResultSet rs=p.executeQuery();
			while(rs.next())
			{
				Resource res = (Resource) this.load(rs.getInt(IDRESOURCE));
				res.set("name", "Risorsa");
				res.set("surname", "ANT");
				res.set("telephon", "");
				res.set("CF", "");
				res.set("email", "");
				if(res.get("typequalificationverificated").equalsIgnoreCase("No"))
					res.set("typequalification", "");
				r.add(res);
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		return r;
	}
		//Carica le aziende
		public ArrayList<Company> companies() {
			ArrayList<Company> r = new ArrayList<Company>();
			try{
				PreparedStatement p = db.prepareStatement("select company.id from company, users where company.id = users.id and users.level='azienda' order by surname");
				ResultSet rs=p.executeQuery();
				while(rs.next())
				{
					r.add( (Company) this.loadCompany(rs.getInt(IDCOMPANY)) );
				}
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
		return r;
	}
		
		public ArrayList<Developer> developers() {
			ArrayList<Developer> r = new ArrayList<Developer>();
			try{
				PreparedStatement p = db.prepareStatement("select users.id from users where users.level='developer' order by surname");
				ResultSet rs=p.executeQuery();
				while(rs.next())
				{
					r.add( (Developer) this.loadDeveloper(rs.getInt("users.id")) );
				}
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
		return r;
	}
	
		public boolean deleteCompany(int id){
			
			boolean ris = false;
			
			PreparedStatement p = null;
			
			try{
				p = db.prepareStatement("delete from users where id = " + id);
				p.executeUpdate();
				
				p = db.prepareStatement("delete from company where id = " + id);
				p.executeUpdate();
				
				ris = true;
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("Errore nella query: " + p.toString());
			}
			
			return ris;
		}
		
	//Simone, Dennys, Chiara e Martina 13-14/02/2017
	public ArrayList<User> users() {
		ArrayList<User> users = new ArrayList<User>();
		try{											//Cambio * con MAILUSER
			PreparedStatement p = db.prepareStatement("select id, level from antlers.users order by banned, level, surname, name");
//			System.out.println("Dentro il try, dopo il preparedStatement -> DBResource");
			ResultSet rs = p.executeQuery();
//			System.out.println("Dentro il try, dopo il resultset -> DBResource");
			while(rs.next()){
//				System.out.println("Dentro il while, prima del metodo loadTest() ciclo numero " + rs.getRow() + " -> DBResource");
				users.add( (User) this.loadUser(rs.getInt("id"), rs.getString("level")));	
//				System.out.println("Dentro il while, dopo il metodo loadTest() -> DBResource");
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return users;
	}
	
	//Monella yari 7/2/2017
	public ArrayList<Resource> completeSearch(int ageMin, int ageMax, ArrayList<String> categories, String skill, int yearsSkill, String region, String qualification, String type){
		ArrayList<Resource> r=new ArrayList<Resource>();
		try
		{
			String istruzioniselect = "select * ";
			String istruzionifrom= "from categories as c0, tecnicsinfo as t0,resources, have, skills, qualification ";
			String istruzioniwhere= "where c0.id=t0.idcategory and "
					+"t0.idresource=resources.id and resources.id=have.idresource and have.idskill=skills.id and qualification.id=resources.idqualification "
					+"and (year(now())-year(resources.birthdate)) > ? "
					+"and (year(now())-year(resources.birthdate)) < ? "
					+"and skills.name LIKE ? "
					+"and have.years>=? "
					+"and resources.domicile LIKE ? "
					+"and qualification.name LIKE ? "
					+"and qualification.type LIKE ? "
					;
			String querycategories =" and ";
			
			if (categories.size()==0|| categories==null)
				querycategories="";
			if (categories.size()==1)
				querycategories +=" c0.name = '"+categories.get(0)+"'";
			if (categories.size()>1)
			{
				for(int q = 0; q < categories.size();q++)
				{
					if(q!=0)
					{
						istruzionifrom += ", categories as c"+q+", tecnicsinfo as t"+q+" ";
						istruzioniwhere+= " and c"+q+".id=t"+q+".idcategory and t"+q+".idresource=resources.id";
					}	
					querycategories += "c"+q+".name = '"+categories.get(q)+"' ";
					if(q!=categories.size()-1) querycategories += " and ";
				}
			}
			PreparedStatement query = db.prepareStatement(istruzioniselect+istruzionifrom+istruzioniwhere+querycategories);
			//CONTROLLO ANNO MINIMO
			try
			{
				//QUESTO CONTROLLO SEMBRA INUTILE, TUTTAVIA SE L'UTENTE NON HA INSERITO NULLA OPPURE 
				//HA INSERITO UNA STRINGA SI VA DIRETTAMENTE NEL CATCH CHE SETTA NELLA QUERY IL VALORE 0
				if(ageMin == 0 || ageMin != 0)
					query.setInt(1, ageMin);
			}
			catch(Exception e)
			{
				query.setInt(1, 0);
			}
			//CONTROLLO ANNO MASSIMO
			try
			{
				if(ageMax == 0 || ageMax != 0)
					query.setInt(2, ageMax);
			}
			catch(Exception e)
			{
				query.setInt(2, 1000);
			}
			//INSERIMENTO SKILL NELLA QUERY
			query.setString(3, "%"+skill+"%");
			//INSERIMENTO ANNI DELLA SKILL NELLA QUERY
			try
			{
				if(yearsSkill == 0 || yearsSkill != 0)
					query.setInt(4, yearsSkill);
			}
			catch(Exception e)
			{
				query.setInt(4, 0);
			}
			//INSERIMENTO REGIONE DI PROVENIENZA NELLA QUERY
			query.setString(5, "%"+region+"%");
			//INSERIMENTO NOME DELLA QUALIFICA NELLA QUERY
			query.setString(6, "%"+qualification+"%");
			//INSERIMENTO TIPOLOGIA DELLA QUALIFICA NELLA QUERY
			query.setString(7, "%"+type+"%");
			
			System.out.println(query.toString());
			ResultSet rs = query.executeQuery();
			while(rs.next())
				r.add( (Resource) this.load(rs.getInt("resources.id")) );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return r;
	}
	
	/* ren po giovanni 02-02-17
	public int getId(String email){
		try{
			PreparedStatement p=db.prepareStatement("select "+IDRESOURCE+ "from "+TABRESOURCES+" where "+EMAILRESOURCE + );
		}
		catch(SQLException se){
			
		}
		return 0;
		
	} */
	
	
	//SALVA UNA ENTITA' NEL DB E NELLA TABELLA CORRETTA IN BASE ALL'ISTANZA DELL'OGGETTO PASSATO
			//CREATO DA YARI E DAVIDE 1/1/17 ORE 18	
			//MODIFICATO DA YARI, AGGIUNTA LA PARTE DELL'UPDATE RESOURCE 7/2/17
	//MODIFICATO DA YARI E DAVIDE, AGGIUNTO DUE CAMPI ALL'UPDATE 8/2/17
	public boolean save(Entity a)
	{
				boolean ris = false;
				System.out.println("SONO NEL SAVE CON LA RISORSA " + a.getData().toString());
				System.out.println(a.toString());
				if(a instanceof Resource)
				{
					Resource r = (Resource) a;
					try
					{
						try
						{
							System.out.println(r.get("id"));
							if(load(Integer.parseInt(r.get("id")))!=null)
							{
								PreparedStatement p = db.prepareStatement(UPDATERESOURCE);

								p.setString(1, r.get("nationality"));
								p.setString(2, r.get("drivingLicense"));
								p.setString(3, r.get("car"));
								p.setString(4, r.get("workingstate"));
								p.setString(5, r.get("birthdate"));
								p.setString(6, r.get("CF"));
								p.setString(7, r.get("domicile"));
								p.setInt(8, Integer.parseInt(r.get("idqualification")));
								p.setString(9, r.get("typequalification"));
								p.setString(10, r.get("telephon"));
								p.setString(11, r.get("gender"));
								p.setString(12, r.get("nativeLanguage"));
								p.setString(13, r.get("id"));
								System.out.println("ECCO LA QUERY nell'if: "+p.toString());
								p.executeUpdate();
								PreparedStatement p2 = db.prepareStatement("UPDATE antlers.users SET email = ?, name = ? , surname = ? WHERE id=?");

								p2.setString(1, r.get("email"));
								p2.setString(2, r.get("name"));
								p2.setString(3, r.get("surname"));
								p2.setString(4, r.get("id"));
								p2.executeUpdate();
								ris = true;
							}
							
							else
							{
								System.out.println("SONO NELL ELSE");
								PreparedStatement p = db.prepareStatement(SAVERESOURCE);
								p.setString(1, r.get("email"));
								p.setString(2, r.get("name"));
								p.setString(3, r.get("surname"));
								p.setString(4, r.get("workingstate"));
//								p.setInt(5, Integer.parseInt(r.get("salary")));
								p.setString(5, r.get("birthdate"));
								p.setInt(6, Integer.parseInt(r.get("idqualification")));
								p.setString(7, r.get("CF"));
								p.setString(8, r.get("domicile"));
								p.setString(9, r.get("typequalification"));
								p.setString(10, r.get("gender"));
								p.setString(11, r.get("telephon"));
								p.setString(12, r.get("drivingLicense"));
								p.setString(13, r.get("car"));
								p.setString(14, r.get("nationality"));
								p.setString(15, r.get("nativeLanguage"));
								
								System.out.println("ECCO LA QUERY nell'else: "+p.toString());
								p.executeUpdate();
								ris = true;
								return ris;
							}
						}
						catch(Exception e)
						{
							System.out.println("SONO NEL CATCH");
							PreparedStatement p = db.prepareStatement(SAVERESOURCE);
							p.setString(1, r.get("email"));
							p.setString(2, r.get("name"));
							p.setString(3, r.get("surname"));
							p.setString(4, r.get("workingstate"));
//							p.setInt(5, Integer.parseInt(r.get("salary")));
							p.setString(5, r.get("birthdate"));
							p.setInt(6, Integer.parseInt(r.get("idqualification")));
							p.setString(7, r.get("CF"));
							p.setString(8, r.get("domicile"));
							p.setString(9, r.get("typequalification"));
							p.setString(10, r.get("gender"));
							p.setString(11, r.get("telephon"));
							p.setString(12, r.get("drivingLicense"));
							p.setString(13, r.get("car"));
							p.setString(14, r.get("nationality"));
							p.setString(15, r.get("nativeLanguage"));
							
							System.out.println("ECCO LA QUERY nel catch: " + p.toString());
								p.executeUpdate();
								ris = true;
								return ris;
							
						}
					}
					catch(SQLException e){
						e.getMessage();
					}
				}
					
				if(a instanceof Skill)
				{
					Skill r = (Skill) a;
					try
					{
						PreparedStatement p = db.prepareStatement(SAVESKILL);
						p.setString(1, r.get("name"));			
						p.executeUpdate();
						ris = true;
					}
					catch(SQLException e){
						e.getMessage();
					}
				}
		return ris;
	}
	
	//03-05-2017 yari
	
	public String login(String email, String password)
	{
		String b = "credenziali";
		try
		{
			PreparedStatement p = db.prepareStatement(LOGIN);
			p.setString(1, email);
			p.setString(2, password);
			ResultSet rs = p.executeQuery();
			if(rs.next())
			{
				if(rs.getString("banned").equalsIgnoreCase("No"))
					b = "ok";
				else
					b = "banned";
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return b;
	}
	
	public boolean deleteSkillFromResource(int idresource, int idskill)
	{
		boolean ris = false;
		try
		{
			PreparedStatement p = db.prepareStatement(DELETESKILLFROMRESOURCE);
			p.setInt(1, idresource);
			p.setInt(2, idskill);
			p.executeUpdate();
			ris = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ris;
	}

	//monella yari 3/2/17
	//metodo che restituisce un arraylist con tutte le skill inserite nel db 
	public ArrayList<Skill> skills() {
		ArrayList<Skill> r=new ArrayList<Skill>();
		try
		{
			PreparedStatement p = db.prepareStatement("select * from "+TABSKILLS);
			ResultSet rs=p.executeQuery();
			while(rs.next())
				r.add( (Skill) load(rs.getInt("id")) );
		}
		catch(SQLException se){
			se.printStackTrace();
		}
				
		return r;
	}
	
	//creato da yari 6/2/17
	//aggiunge un'esperienza lavorativa alla tabella experience, passando come parametri una risorsa e una esperienza
	public boolean addExperience(Resource r,Experience e)
	{
		boolean ris = false;
		/*try
		{
			String[] date1;
			String[] date2;
			try
			{
				date1 = e.get("datefrom").split("/");
			}
			catch(Exception q)
			{
				date1 = new String[] {"1","1","2000"};
			}
			Date dateFrom = new Date(Integer.parseInt(date1[2])-1900,
								   	 Integer.parseInt(date1[1])-1,
									 Integer.parseInt(date1[0])
									);
			try{
				date2 = e.get("datefrom").split("/");
			}
			catch(Exception q)
			{
				date2 = new String[] {"1","1","2000"};
			}
			Date dateTo = new Date(Integer.parseInt(date2[2])-1900,
								   	 Integer.parseInt(date2[1])-1,
									 Integer.parseInt(date2[0])
									);
									*/
		try{	
			PreparedStatement inserimento = db.prepareStatement(SAVEEXPERIENCE);				
			inserimento.setInt(1, Integer.parseInt(r.get("id")));
			inserimento.setString(4,   e.get("mansion"));
			inserimento.setString(5,  e.get("role"));
			inserimento.setString(6, e.get("company"));
			inserimento.setString(2, e.get("datefrom"));
			inserimento.setString(3, e.get("dateto"));
			inserimento.setString(7, e.get("description"));
			System.out.println("Sto inserendo una nuova esperienza: "+inserimento);
			inserimento.executeUpdate();
			ris = true;
		}
		catch(SQLException se){
			
		}
		/*}
		catch(Exception s)
		{
			s.printStackTrace();
		}*/
		return ris;
	}

	// ADD START 06/02/2017 HIROKI
	// parametro HashMap contiene String= nome di skills, Integer= esperienza di skills
	public boolean addSkills(HashMap<Integer,Integer> skills, int idResource) {
		
		boolean ris = true;
		
		Iterator it = skills.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>)it.next();
			if(addSkill(pair.getKey(), pair.getValue(), idResource)) {
				ris = false;
				break;
			}
			it.remove();
		}
		System.out.println(ris);
		return ris;
	}

	// aggiungo dati nella tabella di "have"
	private boolean addSkill(int idSkill, int experience, int idResource) {
		
		boolean ris = false;
		
		try
		{
			PreparedStatement p = db.prepareStatement(ADDSKILL);
			p.setInt(1, idResource);					
			p.setInt(2, idSkill);
			p.setInt(3, experience);	
			System.out.println(p);
			p.executeUpdate();
			ris = true;
		}
		catch(SQLException e){
			e.getMessage();
		}
		
		return ris;
	}

	public int getIdSkill(String skillName) {

		int idSkill = 0;
		
		try
		{
			PreparedStatement p = db.prepareStatement(SELECT_SKILLS_CON_ID);
			p.setString(1, skillName);					
			ResultSet rs=p.executeQuery();
			while(rs.next())
				idSkill = rs.getInt("id");
		}
		catch(SQLException se){
			se.printStackTrace();
		}
				
		return idSkill;
	}
	// ADD E N D 06/02/2017 HIROKI 
	
	// ADD E N D 07/02/2017 HIROKI 
	//metodo che restituisce un arraylist con tutte le categories inserite nel db 
	public ArrayList<Category> categories() {
		ArrayList<Category> rtnList =new ArrayList<Category>();
		try
		{
			Statement s = s = db.createStatement();
			ResultSet rs = s.executeQuery(SELECT_ALL_CATEGORIES);
			while(rs.next())
				rtnList.add(new Category(rs.getString("name"),rs.getString("description")));
		}
		catch(SQLException se){
			se.printStackTrace();
		}
				
		return rtnList;
	}
	
	//MODIFICATO DA YARI E DAVIDE 8/2/17 CONTROLLA ANCHE IL COSTRUTTORE DI QUALIFICATION
	public ArrayList<Qualification> qualifications() {
		ArrayList<Qualification> rtnList =new ArrayList<Qualification>();
		try
		{
			Statement s = s = db.createStatement();
			ResultSet rs = s.executeQuery(SELECT_ALL_QUALIFICATION);
			while(rs.next())
				rtnList.add(new Qualification(rs.getString("id"),rs.getString("name")));
		}
		catch(SQLException se){
			se.printStackTrace();
		}
				
		return rtnList;
	}
	public boolean deleteExperience(int id) {
		
		boolean ris = false;
		try
		{
			PreparedStatement p = db.prepareStatement(DELETEEXPERIENCE);
			p.setInt(1, id);
			p.executeUpdate();
			ris = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ris;
		
	}
	
	public boolean editExperience(Experience e)
	{
		boolean ris = false;
		
		try
		{
			//UPDATE experiences SET role=? , mansions=? , company=? , datefrom = ? , dateto = ?, description = ? WHERE idresource = ? and id = ?
			PreparedStatement p = db.prepareStatement(EDITEXPERIENCE);
			p.setString(1, e.get("role"));
			p.setString(2, e.get("mansions"));
			p.setString(3, e.get("company"));
			p.setString(4, e.get("datefrom"));
			p.setString(5, e.get("dateto"));
			p.setString(6, e.get("description"));
			p.setString(7, "no");
			p.setString(8, e.get("idresource"));
			p.setString(9, e.get("id"));
			
			System.out.println(p.toString());
			
			p.executeUpdate();
			ris = true;
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		
		return ris;
	}
	
	//Simone e Davide 02/03/2017
	public boolean editFL(ForeignLanguages fl, int idResource)
	{
		boolean ris = false;
		PreparedStatement p;
		try
		{
			
			//Query per id  e nome nella tabella foreignLanguages
			p = db.prepareStatement(EDITFL);
			p.setString(1, fl.get("name"));
			p.setString(2, fl.get("id"));
			System.out.println(p.toString());
			p.executeUpdate();
			System.out.println(p.toString());
			p.executeUpdate();
			
			ris = true;
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		
		return ris;
	}
	
	//Simone e Davide 07/03/2017
	public boolean removeResourceFL(int idRes, int idFL){
		boolean ris = false;
		PreparedStatement p;
		try
		{
			
			//Query per id  e nome nella tabella foreignLanguages
			p = db.prepareStatement(REMOVERESOURCEFL);
			p.setInt(1, idRes);
			p.setInt(2, idFL);
			p.executeUpdate();
			ris = true;
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		
		return ris;
	}
	
	//Simone e Davide 07/03/2017
	public boolean addResourceFL(int idRes, int idFL, String level){
		boolean ris = false;
		PreparedStatement p;
		try
		{
			
//			insert into speak(idResource, idForeignLanguage, reading, listening, interaction, oral, writing) values(?, ?, ?, ?, ?, ?, ?);
			//Query per id  e nome nella tabella foreignLanguages
			p = db.prepareStatement(ADDRESOURCEFL);
			p.setInt(1, idRes);
			p.setInt(2, idFL);
			p.setString(3, level);
			p.executeUpdate();
			ris = true;
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		
		return ris;
	}
	
	//Simone e Davide 07/03/2017 FUNZIONA!!!!
	public boolean editResourceFL(String nameFL, String level, int idResource, String oldIdFL){
		
		boolean ris = false;
		PreparedStatement p = null;
		String idFL = "";
		try
		{
			System.out.println("Sono dentro il try");
			p = db.prepareStatement(SELECTIDFL);
			p.setString(1, nameFL);
			System.out.println("Query 1: " + p.toString());
			ResultSet rs = p.executeQuery();
				if(rs.next())
					idFL = rs.getString("id");
				
				System.out.println("id =" + idFL);
			
//				update speak set idForeignLanguage = ?, level = ? where idResource = ? and idForeignLanguage = ?;	
			//Query per il resto nella tabella speak;
			p = db.prepareStatement(EDITRESOURCESPEAK);
			p.setString(1, idFL);
			p.setString(2, level);
			p.setString(3, idResource+"");
			p.setString(4, oldIdFL);
			System.out.println("Query giusta edit resourceSpeak:" + p.toString());
			p.executeUpdate();
			ris = true;
			
		}catch(SQLException e){
			System.out.println("Query sbagliata edit resourceSpeak:" + p.toString());
			e.getMessage();
		}
		System.out.println("Sono fuori dal try");
		
		return ris;
	}

	//Simone e Davide 07/03/2017
	public int newIDFL(String nameFL){
		
		int ris = 0;
		PreparedStatement p;
		
		try
		{
			System.out.println("Sono dentro il try");  
			p = db.prepareStatement("select id from foreignLanguages where name = ?");
			p.setString(1, nameFL);
			System.out.println("Query 1: " + p.toString());
			ResultSet rs = p.executeQuery();
				if(rs.next())
					ris = rs.getInt("id");
				
		}catch(SQLException e){
			e.getMessage();
		}
		System.out.println("Sono fuori dal try");
		
		return ris;
	}
	
	public String getNewExperienceID(HashMap<String,String> e, int i){
		String id="";
		System.out.println("//////////////////////////////////////////");
		try{
			//select MAX(id) as id from experiences where datefrom=? and dateto=? and idresource=?
			PreparedStatement p=db.prepareStatement(SELECTIDEXPERIENCE);
			p.setString(1, e.get("datefrom"));
			p.setString(2, e.get("dateto"));
			p.setString(3, ""+i);
			ResultSet r=p.executeQuery();
			if(r.next())
				id=""+r.getInt("id");
			System.out.println(id+"\n oooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
		}
		catch(SQLException se){
			
		}
		return id;
	}
	
	public String lastresource(){
		String ris="";
		try{
			PreparedStatement p = db.prepareStatement(MAXIDRESOURCE);
			ResultSet r = p.executeQuery();
			if(r.next())
				ris = ""+r.getInt("id");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ris;
	}
	
	public boolean delete(int id)
	{
		boolean ris = false;
		PreparedStatement p;
		try
		{
			if(id<30000)
			{
				p = db.prepareStatement(DELETEHAVE);
				p.setInt(1, id);
				p.executeUpdate();
				
				p = db.prepareStatement(DELETETEC);
				p.setInt(1, id);
				p.executeUpdate();
				
				p = db.prepareStatement(DELETEEXP);
				p.setInt(1, id);
				p.executeUpdate();
				
				p = db.prepareStatement(DELETERESOURCE);
				p.setInt(1, id);
				p.executeUpdate();
				ris = true;
			}
			
			if(id>30000)
			{
				p = db.prepareStatement("DELETE FROM have WHERE idskill = ?");
				p.setInt(1, id);
				p.executeUpdate();
				p = db.prepareStatement(DELETESKILL);
				p.setInt(1, id);
				p.executeUpdate();
				ris = true;
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ris;
	}
	
	// ADD START 01/03/2017 HIROKI
		public EmailInfo getEmailInfo(String email) {
			EmailInfo emailInfo = new EmailInfo();

			try{
				PreparedStatement p=db.prepareStatement(SELECT_EMAILINFO);
				p.setString(1, email);
				ResultSet r=p.executeQuery();
				if(r.next()) {
					emailInfo.setAddress(r.getString("address"));
					emailInfo.setPassword(r.getString("password"));
					emailInfo.setSmtphost(r.getString("smtphost"));
					emailInfo.setTlsport(r.getInt("tlsport"));
					emailInfo.setAuthentication(r.getString("authentication"));
					emailInfo.setStarttls(r.getString("starttls"));
				}
			}
			catch(SQLException se){
				se.printStackTrace();
			}

			return emailInfo;
		}
		// ADD E N D 01/03/2017 HIROKI
	
	public boolean addnewskill(String name, String category){
		boolean ris = false;
		try
		{
			PreparedStatement p = db.prepareStatement(ADDNEWSKILL);
			p.setString(1, name);
			p.setString(2, category);
			p.executeUpdate();
			ris = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ris;
	}
	
	public ArrayList<ForeignLanguages> loadResourceForeignLanguages() {
		ArrayList<ForeignLanguages> fl = new ArrayList<ForeignLanguages>();
		try
		{
			PreparedStatement p = db.prepareStatement(SELECTRESOURCEFL1);
			ResultSet rs = p.executeQuery();
			while(rs.next())
				fl.add(new ForeignLanguages(rs.getString("id"), rs.getString("name"), rs.getString("level")));
		}
		catch(SQLException se){
			se.printStackTrace();
		}
				
		return fl;
	}
	
	public ArrayList<ForeignLanguages> loadAllForeignLanguages() {
		ArrayList<ForeignLanguages> fl = new ArrayList<ForeignLanguages>();
		try
		{
			PreparedStatement p = db.prepareStatement(SELECTALLFL);
			ResultSet rs = p.executeQuery();
			while(rs.next())
				fl.add(new ForeignLanguages(rs.getString("id"), rs.getString("name")));
		}
		catch(SQLException se){
			se.printStackTrace();
		}
				
		return fl;
	}
	public ArrayList<ForeignLanguages> loadAllNationalities() {
		ArrayList<ForeignLanguages> fl = new ArrayList<ForeignLanguages>();
		try
		{
			PreparedStatement p = db.prepareStatement(SELECTALLFL);
			ResultSet rs = p.executeQuery();
			while(rs.next())
				fl.add(new ForeignLanguages(rs.getString("id"), rs.getString("nationality")));
		}
		catch(SQLException se){
			se.printStackTrace();
		}
				
		return fl;
	}
public TypeQuiz loadTypeQuiz(int id){
		TypeQuiz ris = null;
		try
		{
			PreparedStatement ps = db.prepareStatement(SELECTTYPEQUIZ+" where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				ris = new TypeQuiz(rs.getInt("id"),rs.getString("name"));
			}
			else
			{
				System.out.println("i did not find you");
				System.exit(-1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return ris;
	}
	
	public Quiz loadQuiz(int id){
		Quiz ris = null;
		try
		{
			PreparedStatement ps = db.prepareStatement(SELECTALLQUIZ+" where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				ris = new Quiz(rs.getInt("id"),rs.getString("question"), rs.getString("answer1"), rs.getString("answer2"),
						rs.getString("answer3"), rs.getString("answer4"), rs.getInt("answer"), 
						rs.getInt("idtype"), rs.getInt("obbligatory"));
//				System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n"+ris.toJSON());
			}
			else
			{
				System.out.println("i did not find you");
				System.exit(-1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return ris;
	}
	
	//Prende dal db tutti i tipi/categorie di quiz possibili
		public ArrayList<TypeQuiz> typeQuiz (int idcourse)
	{
		ArrayList<TypeQuiz> ris = new ArrayList<TypeQuiz>();
		
		try
		{
			PreparedStatement p = db.prepareStatement(SELECTCOMPILE + " where idcourse = "+idcourse);
			ResultSet rs=p.executeQuery();
			while(rs.next())
				ris.add(loadTypeQuiz(rs.getInt("idtype")) );
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		return ris;
	}
		
		public ArrayList<TypeQuiz> typeQuiz()
		{
			ArrayList<TypeQuiz> r = new ArrayList<TypeQuiz>();
			
			try
			{
				PreparedStatement p = db.prepareStatement(SELECTTYPEQUIZ);
				ResultSet rs=p.executeQuery();
				while(rs.next())
					r.add(loadTypeQuiz(rs.getInt("id")) );
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			
			return r;
		}
	
	//Prende dal db tutte le domande che ci sono
	public ArrayList<Quiz> quiz(){
		ArrayList<Quiz> r=new ArrayList<Quiz>();
		try
		{
			PreparedStatement p = db.prepareStatement(SELECTALLQUIZ);
			ResultSet rs=p.executeQuery();
			while(rs.next())
				r.add(loadQuiz(rs.getInt("id")) );
		}
		catch(SQLException se){
			se.printStackTrace();
		}
				
		return r;
	}
	//Aggiunge una domanda ad un determinato quiz
	public boolean addQuiz(int idtype, String question, int answer, String answer1, String answer2, 
			String answer3, String answer4, int obbligatory)
	{
		boolean ris = false;
		try{
			PreparedStatement p= db.prepareStatement(ADDQUIZ);
			p.setInt(1, idtype);
			p.setString(2, question);
			p.setString(3, answer1);
			p.setString(4, answer2);
			p.setString(5, answer3);
			p.setString(6, answer4);
			p.setInt(7, answer);
			p.setInt(8, obbligatory);
			p.executeUpdate();
			ris=true;
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		
		return ris;
	}
	
	public boolean deletequiz(int id) {
		try{
			PreparedStatement p=db.prepareStatement(DELETEONEQUIZ);
			p.setInt(1, id);
			p.executeUpdate();
			
			return true;
		}
		catch(SQLException se){
			return false;
		}
	}
	public boolean editQuiz(Quiz q){
		try{
			PreparedStatement p=db.prepareStatement(EDITQUIZ);
			p.setString(1, q.get("question"));
			p.setInt(2, Integer.parseInt(q.get("idtype")));
			p.setString(3, q.get("answer1"));
			p.setString(4, q.get("answer2"));
			p.setString(5, q.get("answer3"));
			p.setString(6, q.get("answer4"));
			p.setInt(7, Integer.parseInt(q.get("answer")));
			p.setInt(8, Integer.parseInt(q.get("obbligatory")));
			p.setInt(9, Integer.parseInt(q.get("id")));
			p.executeUpdate();
			return true;
		}
		catch(SQLException se){
				se.printStackTrace();
				return false;
		}	
		
	}
	
	public boolean addtype(String name){
		
		try{
			PreparedStatement p=db.prepareStatement(ADDTYPE);
			p.setString(1, name);
			p.executeUpdate();
			return true;
		}
		catch(SQLException se){
			se.printStackTrace();
			return false;
		}
	}
	
	public boolean deletetype(int id){
		try
		{
			PreparedStatement o= db.prepareStatement(DELETERECORDQUIZTYPE);
			o.setInt(1,id);
			o.executeUpdate();
			PreparedStatement oo = db.prepareStatement(DELETECOMPILETYPEQUIZ);
			oo.setInt(1, id);
			oo.executeUpdate();
			PreparedStatement p=db.prepareStatement(DELETEQUIZ);
			p.setInt(1, id);
			p.executeUpdate();
			PreparedStatement pp=db.prepareStatement(DELETETYPE);
			pp.setInt(1, id);
			pp.executeUpdate();
			
			return true;
		}
		catch(SQLException se){
			return false;
		}
		
	}
	
	
	public boolean addCourse(Course e)
	{
		boolean ris = false;
		try{	
			PreparedStatement inserimento = db.prepareStatement(SAVECOURSE);
			inserimento.setString(1, e.get("name"));
			inserimento.setString(2, e.get("datefrom"));
			inserimento.setString(3, e.get("dateto"));
			inserimento.setString(4,   e.get("notes"));
			System.out.println("Sto inserendo un nuovo corso: "+inserimento);
			inserimento.executeUpdate();
			ris = true;
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return ris;
	}
	
	public Course loadCourse(int id){
		Course ris = null;
		try
		{
			PreparedStatement ps = db.prepareStatement(SELECTALLCOURSES+" where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				HashMap <String,String> info = new HashMap<String,String>();
				info.put("id", rs.getString("id"));
				info.put("name", rs.getString("name"));
				info.put("datefrom", rs.getString("datefrom"));
				info.put("dateto", rs.getString("dateto"));
				info.put("notes", rs.getString("notes"));
				ris = new Course(info);
//				System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\n"+ris.toJSON());
			}
			else
			{
				System.out.println("i did not find you");
				System.exit(-1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return ris;
	}
	
	public ArrayList<Course> courses(){
		ArrayList<Course> r=new ArrayList<Course>();
		try
		{
			PreparedStatement p = db.prepareStatement(SELECTALLCOURSES);
			ResultSet rs=p.executeQuery();
			while(rs.next())
				r.add(loadCourse(rs.getInt("id")) );
		}
		catch(SQLException se){
			se.printStackTrace();
		}
				
		return r;
	}
	
	public ArrayList<Course> futurecourses(){
		ArrayList<Course> r=new ArrayList<Course>();
		try
		{
			PreparedStatement p = db.prepareStatement(SELECTALLFUTURECOURSES);
			ResultSet rs=p.executeQuery();
			while(rs.next())
				r.add(loadCourse(rs.getInt("id")) );
		}
		catch(SQLException se){
			se.printStackTrace();
		}
				
		return r;
	}

	//START Simone e Davide 13/03/2017
	public boolean deleteCourse(int idCourse){
		
		boolean ris = false;
		try
		{
			PreparedStatement p3 = db.prepareStatement(DELETECOMPILE);
			p3.setInt(1, idCourse);
			p3.executeUpdate();
			PreparedStatement p1 = db.prepareStatement(DELETECOURSEATTEND);
			p1.setInt(1, idCourse);
			p1.executeUpdate();
			PreparedStatement p2 = db.prepareStatement(DELETECOURSERECORDQUIZ);
			p2.setInt(1, idCourse);
			p2.executeUpdate();
		
			PreparedStatement p = db.prepareStatement(DELETECOURSE);
			p.setInt(1, idCourse);
			p.executeUpdate();
			ris = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ris;
	}
	//END Simone e Davide 13/03/2017
	
	public ArrayList<Quiz> quiz(int idtype){
		ArrayList<Quiz> r=new ArrayList<Quiz>();
		try
		{
			PreparedStatement p = db.prepareStatement(SELECTALLQUIZ+" where idtype="+idtype);
			ResultSet rs=p.executeQuery();
			while(rs.next())
				r.add(loadQuiz(rs.getInt("id")) );
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		while(r.size()>30)
		{
			int numerodatogliere = (int) Math.random() * r.size();
			while(numerodatogliere>=r.size())
				numerodatogliere--;
			if(numerodatogliere < 0)
				numerodatogliere=0;
			r.remove(numerodatogliere);
		}
		
		return r;
	}
	
	public int iduser (String email){
		int ris=-1;
		try
		{
			System.out.println(SELECTALLUSERS +" where email='"+email+"'");
			PreparedStatement p = db.prepareStatement(SELECTALLUSERS +" where users.email = '"+email+"'");
			ResultSet rs=p.executeQuery();
			while(rs.next())
				ris=rs.getInt("id");
			System.out.println("aahahaahahahahhahahahaha///////////////////////"+ris);
			
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return ris;
	}
	public boolean isAdmin (String email){
		boolean b=false;
		try
		{
			System.out.println(SELECTALLUSERS +" where email='"+email+"'");
			PreparedStatement p = db.prepareStatement(SELECTALLUSERS +" where users.email = '"+email+"'");
			ResultSet rs=p.executeQuery();
//			System.out.println("ahahahahahahaha. Ma che te ridi");
			String s = "";
			while(rs.next())
				s = rs.getString("level");
			if(s.equals("admin")) b=true; 
			
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return b;
	}
	
	public boolean isAzienda (String email){
		boolean b=false;
		try
		{
			System.out.println(SELECTALLUSERS +" where email='"+email+"'");
			PreparedStatement p = db.prepareStatement(SELECTALLUSERS +" where users.email = '"+email+"'");
			ResultSet rs=p.executeQuery();
//			System.out.println("ahahahahahahaha. Ma che te ridi");
			String s = "";
			while(rs.next())
				s = rs.getString("level");
			if(s.equals("azienda")) b=true; 
			
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return b;
	}
	
	public boolean isDeveloper (String email){
		boolean b=false;
		try
		{
			System.out.println(SELECTALLUSERS +" where email='"+email+"'");
			PreparedStatement p = db.prepareStatement(SELECTALLUSERS +" where users.email = '"+email+"'");
			ResultSet rs=p.executeQuery();
//			System.out.println("ahahahahahahaha. Ma che te ridi");
			String s = "";
			while(rs.next())
				s = rs.getString("level");
			if(s.equals("developer")) b=true; 
			
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return b;
	}
	
	public boolean startQuiz(int idcourse, int idtype, int idlogged , String start){
		boolean ris=false;
		
		try{
			PreparedStatement p= db.prepareStatement(STARTQUIZ);
			p.setInt(1, idcourse);
			p.setInt(2, idtype);
			p.setInt(3, idlogged);
			p.setString(4, start);
			System.out.println(p.toString());
			p.executeUpdate();
			
			ris=true;
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		
		return ris;
	}
	public int startedQuiz(int idcourse, int idtypequiz, int idlogged) {
		int ris = 0;
		try{
			PreparedStatement p= db.prepareStatement(STARTEDQUIZ);
			p.setInt(1, idcourse);
			p.setInt(2, idtypequiz);
			p.setInt(3, idlogged);
			ResultSet rs=p.executeQuery();
			while(rs.next())
				ris=rs.getInt("id");
			
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return ris;
	}
	
	public boolean finishquiz(String mark,String answers,String finish, int id){
		try
		{
			PreparedStatement p= db.prepareStatement(FINISHQUIZ);
			p.setString(1, mark);
			p.setString(2, answers);
			p.setString(3, finish);
			p.setInt(4, id);
			p.executeUpdate();
			return true;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			return false;
		}
	}
	public boolean candidate(int idcourse, int idlogged) {
		try
		{
			PreparedStatement p = db.prepareStatement(CANDIDATE);
			p.setInt(1, idcourse);
			p.setInt(2, idlogged);
			p.executeUpdate();
			return true;
		}
		catch(SQLException se)
		{
			se.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Integer> courseslist(int iduser) {
		ArrayList<Integer> ris = new ArrayList<Integer>();
		try{
			PreparedStatement p = db.prepareStatement(COURSELIST);
			p.setInt(1, iduser);
			ResultSet rs=p.executeQuery();
			while(rs.next())
			{
				ris.add(rs.getInt("idcourse"));
			}
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		return ris;
	}
	
	public boolean removeQuizFromCourse(int idc,int idt){
		try{
			PreparedStatement p=db.prepareStatement(REMOVEQUIZ);
			p.setInt(1, idc);
			p.setInt(2, idt);
			p.executeUpdate();
			return true;
		}
		catch(SQLException se){
			se.printStackTrace();
			return false;
		}
	}
	public boolean addQuizToCourse(int idc,int idt){
		try{
			PreparedStatement p=db.prepareStatement(ADDQUIZTOCOMPILE);
			p.setInt(1, idc);
			p.setInt(2, idt);
			p.executeUpdate();
			return true;
		}
		catch(SQLException se){
			se.printStackTrace();
			return false;
		}
	}
	public Entity loadattend(int iduser){
		HashMap<String,String> hash = new HashMap<String,String>();
		try
		{
			PreparedStatement p = db.prepareStatement("SELECT name, surname, id FROM users WHERE id="+iduser);
			ResultSet r = p.executeQuery();
			while(r.next())
			{
				hash.put("name", r.getString("name"));
				hash.put("surname", r.getString("surname"));
				hash.put("id", r.getInt("id")+"");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Entity ris = new Entity(hash);
		return ris;
	}
	public ArrayList<Entity> peopleattend(int idcourse) {
		ArrayList<Entity> ris = new ArrayList<Entity>();
		try
		{
			PreparedStatement p = db.prepareStatement("SELECT * FROM antlers.attend WHERE idcourse ="+idcourse);
			ResultSet r = p.executeQuery();
			while(r.next())
			{
				ris.add(loadattend(r.getInt("idresource")));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ris;
	}
	public String getQuizInformation(int iduser, int idcourse) {
		String ris = "[";
		try
		{
			PreparedStatement p = db.prepareStatement("SELECT * FROM antlers.recordquiz WHERE iduser="+iduser+" and idcourse="+idcourse);
			ResultSet r = p.executeQuery();
			while(r.next())
			{
				PreparedStatement q = db.prepareStatement("SELECT * FROM antlers.type WHERE id="+r.getInt("idtypequiz"));
				ResultSet rr = q.executeQuery();
				ris +=  "{\"idtypequiz\":\""+r.getString("idtypequiz")+
						"\",\"answers\":"+r.getString("answers")
						.replace("id", "\"id\"")
						.replace("chosen", "\"chosen\"")
						+
						",\"startdate\":\""+r.getString("startdate")+
						"\",\"finishdate\":\""+r.getString("finishdate");
				while(rr.next())
					ris +="\",\"name\":\""+rr.getString("name");
				
				ris+="\",\"mark\":\""+r.getString("mark") +"\"},";
			}
			ris = ris.substring(0, ris.length()-1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		ris += "]";
		return ris;
	}
	public Quiz getSingleQuiz(int iddomanda) {
		HashMap <String,String> hash = new HashMap <String,String>();
		try
		{
			PreparedStatement p = db.prepareStatement("SELECT * FROM antlers.quiz WHERE id="+iddomanda);
			ResultSet r = p.executeQuery();
			while(r.next())
			{
				hash.put("id",  r.getInt("id")+"");
				hash.put("question", r.getString("question"));
				hash.put("answer1", r.getString("answer1"));
				hash.put("answer2", r.getString("answer2"));
				hash.put("answer3", r.getString("answer3"));
				hash.put("answer4", r.getString("answer4"));
				hash.put("answer", r.getString("answer"));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		Quiz q = new Quiz(hash); 
		return q;
	}
	public ArrayList<SkillCategory> skillcategory() {
		ArrayList<SkillCategory> ris = new ArrayList<SkillCategory>();
		
		try
		{
			PreparedStatement p = db.prepareStatement("SELECT * FROM skillcategory");
			ResultSet r = p.executeQuery();
			while(r.next())
			{
				ris.add(loadskillcategory(Integer.parseInt(r.getString("id"))));
			}
		}
		catch(Exception e)
		{
			
		}
		
		System.out.println("2005 dbresource: "+ris.size());
		
		return ris;
	}
	private SkillCategory loadskillcategory(int id) {
		SkillCategory ris = null;
		HashMap<String,String> hash = new HashMap<String,String>();
		try
		{
			PreparedStatement p = db.prepareStatement("SELECT * FROM skillcategory WHERE id="+id);
			ResultSet r = p.executeQuery();
			while(r.next())
			{
				hash.put("id", r.getInt("id")+"");
				hash.put("name", r.getString("name"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		ris = new SkillCategory(hash);
		return ris;
	}
	public boolean newskillcategory(String parameter) {
		boolean ris = false;
		
		try
		{
			PreparedStatement p = db.prepareStatement("INSERT INTO skillcategory (name) VALUES (?)");
			p.setString(1, parameter);
			p.executeUpdate();
			ris = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return ris;
	}
	public boolean deleteSkillCategory(int id) {
		boolean ris = false;
		
		try
		{
			PreparedStatement p = db.prepareStatement("DELETE FROM skillcategory WHERE ID="+id);
			p.executeUpdate();
			ris = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return ris;
		
	}
	public boolean editSkillCategory(int id, String name) {
		boolean ris = false;
		
		try
		{
			PreparedStatement p = db.prepareStatement("UPDATE skillcategory SET name=? WHERE id=?");
			p.setString(1, name);
			p.setString(2, id+"");
			p.executeUpdate();
			ris = true;
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		
		return ris;
	}
	public boolean editSkill(int id, String name, int category) {
		boolean ris = false;
		
		try
		{
			PreparedStatement p = db.prepareStatement("UPDATE skills SET name=? , category=? WHERE id=?");
			p.setString(1, name);
			p.setString(2, category+"");
			p.setString(3, id+"");
			p.executeUpdate();
			ris = true;
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		
		return ris;
	}
	public boolean editCourse(int id, String name, String datefrom, String dateto, String notes) {
		boolean ris = false;
		try
		{
			PreparedStatement p = db.prepareStatement("UPDATE courses SET name=? , datefrom=? , dateto=? , notes=? WHERE id=?");
			p.setString(1, name);
			p.setString(2, datefrom);
			p.setString(3, dateto);
			p.setString(4, notes);
			p.setString(5, id+"");
			p.executeUpdate();
			ris = true;
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		
		return ris;
	}
	public boolean deletenullquizrecord(int idresource) {
		boolean ris = false;
		try
		{
			PreparedStatement p = db.prepareStatement("DELETE FROM antlers.recordquiz WHERE answers is null and iduser=?;");
			p.setString(1, idresource+"");
			p.executeUpdate();
			ris = true;
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		
		return ris;
		
	}
	public String quizgiaeseguiti(int idcorso, int idpersona) {
		String ris="[";
		try
		{
			PreparedStatement p = db.prepareStatement("SELECT idtypequiz FROM recordquiz WHERE idcourse = ? and iduser = ?");
			p.setString(1, idcorso+"");
			p.setString(2, idpersona+"");
			ResultSet r = p.executeQuery();
			while(r.next())
			{
				ris += "{\"id\":\""+r.getString("idtypequiz")+"\"},";
			}
			if(!ris.equals("["))
				ris = ris.substring(0, ris.length()-1);
			ris += "]";
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		
		return ris;
	}
	public boolean validateexperience(String id) {
		boolean ris = false;
		
		try
		{
			PreparedStatement p = db.prepareStatement("UPDATE experiences SET verificated='si' WHERE id=?");
			p.setString(1, id);
			p.executeUpdate();
			ris = true;
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		
		return ris;
	}
	public boolean validatequalification(String id, String s) {
		boolean ris = false;
		
		try
		{
			PreparedStatement p = db.prepareStatement("UPDATE resources SET typequalificationverificated=? WHERE id=?");
			p.setString(1, s);
			p.setString(2, id);
			p.executeUpdate();
			ris = true;
		}
		catch(Exception a)
		{
			a.printStackTrace();
		}
		
		return ris;
		
	}
	public void keepDbAlive() {
		try
		{
			PreparedStatement p = db.prepareStatement("select * from users");
			p.executeQuery();
			System.out.println("db vivo");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public boolean updateCompany(int idc, String bannedc, String emailc, String votec, String notec, String namec,
			String personaRiferimento, String telephonec, String passwordc) {
		boolean ris = false;
		
		PreparedStatement p = null;
		PreparedStatement p1= null;
		try{
				System.out.println("*******************************************\n\n**************ora faccio l'update della company");
				p = db.prepareStatement("UPDATE company SET personaRiferimento = ? , telephone = ? WHERE id=?");
				p.setString(1, personaRiferimento);
				p.setString(2, telephonec);
				p.setInt(3, idc);
				p.executeUpdate();
				System.out.println(p.toString()+"\n\n\n\n+++"+passwordc);
				if(passwordc.equals(""))
				{
					p1 = db.prepareStatement("UPDATE users SET banned = ? , email = ? , vote = ? , shortnote = ? , name = ?  WHERE id=?");
					p1.setString(1, bannedc);
					p1.setString(2, emailc);
					p1.setString(3, votec);
					p1.setString(4, notec);
					p1.setString(5, namec);
					p1.setInt(6, idc);
					p1.executeUpdate();
					System.out.println(p1.toString());
				}
				else
				{
					p1 = db.prepareStatement("UPDATE users SET banned = ? , email = ? , vote = ? , shortnote = ? , name = ? , password = md5(?) WHERE id=?");
					p1.setString(1, bannedc);
					p1.setString(2, emailc);
					p1.setString(3, votec);
					p1.setString(4, notec);
					p1.setString(5, namec);
					p1.setString(6, passwordc);
					p1.setInt(7, idc);
					p1.executeUpdate();
					System.out.println(p1.toString());
				}
			ris = true;
	
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Sono nel catch: Query dell'update: " + p1.toString());
		}
		
		return ris;
	}
}
