package clavard;

//import java.net.InetAddress;
//import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BDD {
	private String DBPath = "Chemin aux base de donn�e SQLite";
    private Connection connection = null;
    private Statement statement = null;
 
    public BDD(String dBPath) {
        DBPath = dBPath;
    }
    public void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + DBPath);
            statement = connection.createStatement();
            System.out.println("Connexion � " + DBPath + " avec succ�s");
        } catch (ClassNotFoundException notFoundException) {
            notFoundException.printStackTrace();
            System.out.println("Erreur de connexion");
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.out.println("Erreur de connexion");
        }
    }
    public ArrayList<Message> lire_mess(String login_emet,String login_recept) throws ParseException {
    	String emereplaced = login_emet.replace("'", "\\\\");
    	String receplaced = login_recept.replace("'", "\\\\");
    	ResultSet resultSet = query("SELECT * FROM messages WHERE (loginemetteur = '"+emereplaced+"' AND loginrecepteur = '"+receplaced+"') OR (loginemetteur = '"+receplaced+"' AND loginrecepteur = '"+emereplaced+"')");
    	
    	ArrayList<Message> liste = new ArrayList<Message>();
    	
    	try {
            while (resultSet.next()) {
            	//Cr�er une liste (map ?), mettre tous les messages dedans et envoyer la liste
            	String strdate = resultSet.getString("date");
            	String contenu = resultSet.getString("mess");
            	String loem=resultSet.getString("loginemetteur");
               	String lore=resultSet.getString("loginrecepteur");
               	String loemreplaced=loem.replace("\\\\", "'");
               	String lorereplaced=lore.replace("\\\\", "'");
            	String creplaced=contenu.replace("\\\\", "'");
            	Date date=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(strdate);
            	Message mess=new Message(loemreplaced,lorereplaced,date,creplaced);
            	liste.add(mess);
                //System.out.println("Le "+resultSet.getString("date")+" : "+resultSet.getString("mess"));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    	
    	return liste;
    }
    public void ecrire(String login_emet,String login_recept,Date date,String mess) {
    	DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");  
    	String strDate = dateFormat.format(date); 
    	String emetrplcd = login_emet.replace("'", "\\\\");
    	String receptrplcd = login_recept.replace("'", "\\\\");
    	
    	String messrplcd = mess.replace("'", "\\\\");
    	

    	query("INSERT INTO messages(loginemetteur,loginrecepteur, date, mess) VALUES ( '" +emetrplcd+"','"+receptrplcd+"','"+strDate+"','"+messrplcd+"')");
    	
    }
    
    
    
    
    /*public InetAddress getIP(String pseudo) {
    	ResultSet resultSet = query("SELECT IP FROM users WHERE pseudo = '" +pseudo+"'");
    	String ip=null;
    	InetAddress inetAddress=null;
    	
		try {
			System.out.println("IP : "+resultSet.getString("IP"));
                
            ip = resultSet.getString("IP");
			inetAddress = InetAddress.getByName(ip);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Mauvais pseudo ou pas d'IP associ�");
			
		}catch (UnknownHostException e) {
			e.printStackTrace();
			
		}
			return inetAddress;
	}*/
    
    public void close() {
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }
    public ResultSet query(String requet) {
        ResultSet resultat = null;
        try {
            resultat = statement.executeQuery(requet);
        } catch (SQLException e) {
            //e.printStackTrace();
            
        	//System.out.println("Erreur dans la requ�te : " + requet);
        }
        return resultat;
  
    }
}
