package db;

import static constants.DbEnvironmentVariables.DBENVIRONMENTVARIABLES;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.servlet.ServletContext;

import controller.UserController;
import entities.Category;
import entities.Company;
import entities.Experience;
import entities.ForeignLanguages;
import entities.Resource;
import entities.Skill;
import entities.User;
import it.sauronsoftware.base64.Base64;
import web.ConfigurationProperties;

public class Testami {

//	public static void main(String[] args) throws IOException {
//		
//		DBResourcesSQL db = new DBResourcesSQL(DBENVIRONMENTVARIABLES);
//		
////		ArrayList<Resource> listResource = db.resources();
////		//strPage += view.contentFile("C:\\Users\\corso1\\git\\project_ANT\\LinkedIn_versione_ANT\\WebContent\\views\\viewperson.html");
////		
////			String strPage = "[";
////			for (int i = 0; i < listResource.size(); i++)
////			{
////				strPage+=listResource.get(i).toJSON();
////				strPage = strPage.substring(0, strPage.length()-1) + "";
////				
////				if(listResource.get(i).getSkills().size()>0)
////				{
////					strPage += ",\"skills\":[";
////					
////					for(Skill skill:listResource.get(i).getSkills())
////						strPage+=skill.toJSON()+",";
////					
////					strPage = strPage.substring(0, strPage.length()-1);
////					strPage += "]";
////				}
////				if(listResource.get(i).getExperiences().size()>0)
////				{
////					strPage += ",\"experiences\":[";
////					for(Experience experience:listResource.get(i).getExperiences())
////						strPage+=experience.toJSON()+",";
////					
////					strPage = strPage.substring(0, strPage.length()-1);
////					strPage += "]";
////				}
////				if(listResource.get(i).getCategories().size()>0)
////				{	
////					strPage += ",\"categories\":[";
////					for(Category category:listResource.get(i).getCategories())
////						strPage+=category.toJSON()+",";
////					
////					strPage = strPage.substring(0, strPage.length()-1);
////					strPage += "]";
////				}
////				if(listResource.get(i).getFl().size()>0)
////				{	
////					strPage += ",\"foreign languages\":[";
////					for(ForeignLanguages fl:listResource.get(i).getFl())
////						strPage+=fl.toJSON()+",";
////					
////					strPage = strPage.substring(0, strPage.length()-1);
////					strPage += "]";
////				}
////					strPage += "},";
////			}	
////			strPage = strPage.substring(0, strPage.length()-1);
////			strPage+="]";
////			System.out.println(strPage);
////		
////		ArrayList<ForeignLanguages> f = db.loadAllForeignLanguages();
////		
////		for(ForeignLanguages s: f)
////		System.out.println(s.toJSON());
////		System.out.println("Modificato?: " + db.editFL(f.get(0), 5));
//		
////		System.out.println("Mod: " + db.editResourceFL("russo", "A1", 1, "2"));
//		
////		System.out.println("salvato: " + db.addResourceFL(1, 1, "C1"));
//		
////		System.out.println("Id: " + db.newIDFL("inglese"));
//
////		System.out.println("Delittato? " + db.deleteCourse(6));
//		
////		Writer w = new Writer();
////		w.scriviFile("prova.pdf", "Ciao sono un file pdf");
//		
////		String cod = Base64.encode("Ciao");
////		String doc = Base64.decode(cod);
////		System.out.println(cod);
////		System.out.println(doc);
////		
////		ArrayList<User> u = db.users();
////		System.out.println("Dimensioni: " + u.size());
////				for(int i = 0; i < u.size(); i++)
////					System.out.println("1: " + u.get(i).toJSON());
////				
////				System.out.println("Singolo user: " + db.loadUser(3).toJSON());
//		
////		System.out.println("Salvato: " + db.addUser("email", "pass", "pass", "client", 1, "ciao", "p", "dav"));
////		System.out.println("Cancellato?: " + db.deleteUser("dove"));
////		System.out.println("email inviata?:"+db.randomPass("dnapo01@gmail.com"));
//		
////		System.out.println("Updato? " + db.updateUser(1, "Euguale@shs.it", "5", "Ip man", "Win", "Chun", "client"));
//		
////		String g = "Ciao";
//
//		
////		System.out.println(g.substring(0, 3));
//		
////		ConfigurationProperties c = new ConfigurationProperties("C:/esercizinew/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/Antlers/WEB-INF/");
////		c.getProperties();				
////		System.out.println(c.getProperty("nameDB"));
////		c.setProperties("percorsoViews", "C:/esercizinew/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/Antlers/views");
//		
////		Properties p = System.getProperties();
////		p.setProperty("Ciao", "Come va?");
////		System.out.println(p.get("Ciao"));
////		
////		Session s = Session.getDefaultInstance(p);
////		System.out.println(s.toString());
//		
////		String ci = "è?";
////		System.out.println("Codice: " + ci.hashCode());
////		for(int i = 0; i < ci.length(); i++)
////			System.out.println((int)ci.charAt(i));
////		
////		System.out.println(db.isAzienda("azienda@gmail.com"));
//		
////	System.out.println(db.isAzienda("azienda@gmail.com"));
//		
////		UserController u = new UserController(db);
////		
//////		System.out.println("Ci sono");
////		ArrayList<Company>comp = db.companies();
////		for(Company c: comp)
////			System.out.println(c.toJSON());
////		
////		System.out.println("Lunghezza: " + comp.size());
//		
////		String c = u.allcompanies(comp, "json");
////		System.out.println(c);
////		
////		
////		int[] v = new int[]{89, 24, 29, 8, 14, 68, 74, 1};
////		System.out.println("\n" + v.length + "\n");
////		System.out.println("Prima del bubble: ");
////		
////		for(Integer i: v)
////			System.out.println(i);
////		
////		Testami.bubbleSort(v);
////		
////		System.out.println("Dopo il bubble: ");
////		for(Integer i: v)
////			System.out.println(i);
////		
//		String percorsoApp = "C:/esercizinew/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/Antlers/";
//		
//		String percorsoDocuments = percorsoApp + "Documents/";
//		
//		String percorsoAllegati = percorsoApp + "allegati/";
//		
//		String percorsoDesktop = "C:/Users/admin/Desktop/";
//		
//		System.out.println("Percorso tot: " + percorsoApp);
//		
//		System.out.println("\nPercorso Documents: " + percorsoDocuments);
//		
//		System.out.println("Percorso allegati: " + percorsoAllegati);
//		
//		File from = new File(percorsoDocuments);
//		File to = new File(percorsoDesktop);
//		
//		String[] files = from.list();
//		
//		System.out.println("n files: " + files.length);
//		
//		for(int z = 0; z < files.length; z++)
//			System.out.println(files[z].toString());
//		
//		System.out.println("Esiste: " + from.exists() + ", è un file: " + from.isDirectory());
//		
//		if(from.exists()){
//			
//			System.out.println("La cartella esiste");
//			try{
//				copiaCartella(from, to);
//			}catch(IOException e){
//				e.printStackTrace();
//			}
//			
//		} else {
//			System.out.println("La cartella non esiste");
//		}
//		
//		
//}//Main
//		
//		public static void copiaCartella(File from, File to){
//		
//			if(from.isDirectory()){
//				
//				if(!to.exists()){	
//					System.out.println("La cartella di destinazione non esiste, ne creo una per consentirti di salvare la cartella da copiare al suo interno.");
//					to.mkdir();
//				}
//				
//				String[] files = from.list();
//				
//				//Copio ed incollo nella cartella destinazione i files contenuti nella cartella "cartella"
//				for(String f : files){
//					File fileCartella = new File(from, f);
//					File fileDestinazione = new File(to, f);
//					copiaCartella(fileCartella, fileDestinazione);
//				}
//				
//			}
//			
//		}
//	
//		public static void bubbleSort(int [] array) {
//
//	        for(int i = 0; i < array.length; i++) {
//	            boolean flag = false;
//	            for(int j = 0; j < array.length-1; j++) {
//
//	                //Se l' elemento j e maggiore del successivo allora
//	                //scambiamo i valori
//	                if(array[j]>array[j+1]) {
//	                    int k = array[j];
//	                    array[j] = array[j+1];
//	                    array[j+1] = k;
//	                    flag=true; //Lo setto a true per indicare che é avvenuto uno scambio
//	                }
//	                
//
//	            }
//
//	            if(!flag) break; //Se flag=false allora vuol dire che nell' ultima iterazione
//	                             //non ci sono stati scambi, quindi il metodo può terminare
//	                             //poiché l' array risulta ordinato
//	        }
//	    }
	
	 public static void main(String[] args)
	    {   
		 
//		 System.out.println("Percorso automatico: " + System.getProperty("user.home"));
//		 
//		 	String percorsoApp = "C:/esercizinew/.metadata/.plugins/org.eclipse.wst.server.core/tmp2/wtpwebapps/Antlers/";
//		 	String percorsoDocumentsLocale = "C:/Users/admin/Desktop/CartellaDocuments";
//		 	String percorsoAllegatiLocale = "C:/Users/admin/Desktop/CartellaAllegati";
//		 	
//		 	File srcFolderDocuents = new File(percorsoApp + "Documents/");
//		 	File srcFolderAllegati = new File(percorsoApp + "allegati/");
//	        File destFolderDocuments = new File(percorsoDocumentsLocale);
//	        File destFolderAllegati = new File(percorsoAllegatiLocale);
//	        
//	        
//	        //make sure source exists
//	        if(!(srcFolderDocuents.exists()) && !(srcFolderAllegati.exists())){
//
//	           System.out.println("Directory does not exist.");
//	           //just exit
//	           System.exit(0);
//
//	        }else{
//
//	           try{
//	            copyFolder(srcFolderDocuents,destFolderDocuments);
//	            copyFolder(srcFolderAllegati,destFolderAllegati);
//	           }catch(IOException e){
//	            e.printStackTrace();
//	            //error, just exit
//	                System.exit(0);
//	           }
//	        }
//
//	        System.out.println("Done");
		 
		 try {
			    // Execute a command to terminate your server
			    String[] command = new String[]{"cmd", "/C", "start", "C:/Users/admin/Desktop/apache-tomcat-8.0.41/bin/startup.bat"};
			    Process child = Runtime.getRuntime().exec(command);

			} catch (IOException e) {
			}
		 
	    }

//	    public static void copyFolder(File src, File dest)
//	        throws IOException{
//
//	        if(src.isDirectory()){
//
//	            //if directory not exists, create it
//	            if(!dest.exists()){
//	               dest.mkdir();
//	               System.out.println("Directory copied from " 
//	                              + src + "  to " + dest);
//	            }
//
//	            //list all the directory contents
//	            String files[] = src.list();
//
//	            for (String file : files) {
//	               //construct the src and dest file structure
//	               File srcFile = new File(src, file);
//	               File destFile = new File(dest, file);
//	               //recursive copy
//	               copyFolder(srcFile,destFile);
//	            }
//
//	        }else{
//	            //if file, then copy it
//	            //Use bytes stream to support all file types
//	            InputStream in = new FileInputStream(src);
//	                OutputStream out = new FileOutputStream(dest); 
//
//	                byte[] buffer = new byte[1024];
//
//	                int length;
//	                //copy the file content in bytes 
//	                while ((length = in.read(buffer)) > 0){
//	                   out.write(buffer, 0, length);
//	                }
//
//	                in.close();
//	                out.close();
//	                System.out.println("File copied from " + src + " to " + dest);
//	        }
//	    }
		
		
		
}