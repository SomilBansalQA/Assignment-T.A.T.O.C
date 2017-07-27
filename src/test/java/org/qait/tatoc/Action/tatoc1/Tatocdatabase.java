package org.qait.tatoc.Action.tatoc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tatocdatabase {

	final private String host = "10.0.1.86";
	final private String user = "tatocuser";
	final private String passwd = "tatoc01";
	
	
	Connection con;
	public void mySQLConnection() {
	
	}
 
	public int getIdFromIdentityTable(String Symbol,Connection con) {
		int id = 0;
		
   			Statement stmtForIdentity;
			try {
				stmtForIdentity = con.createStatement();
			
				ResultSet resultSetForIdentity = stmtForIdentity
	   					.executeQuery("select id from identity where symbol='" + Symbol+"'");
				

				resultSetForIdentity.next();
				id = resultSetForIdentity.getInt(1);
			System.out.println(id);

			}
			catch (SQLException e) {
				
				e.printStackTrace();
			}	
			return id;

	}
   
  public String getUserNameAndPasswordFromCredential(String Symbol){
	  String username = null;
		String password = null;
    
  	Statement stmtForCredentials;
  	try {Class.forName("com.mysql.jdbc.Driver");
	 con = DriverManager.getConnection("jdbc:mysql://" + host + "/tatoc", user, passwd);
  	stmtForCredentials = con.createStatement();
	ResultSet resultSetForCredentials = stmtForCredentials.executeQuery("select name,passkey from credentials where id='"+getIdFromIdentityTable(Symbol,con)+"'");
	resultSetForCredentials.next();
	
	username = resultSetForCredentials.getString(1);
	password = resultSetForCredentials.getString(2);
  	System.out.println("username"+username);
  	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} 
	catch (SQLException e) {
		
		e.printStackTrace();
	}	
  	return username+" "+password;
  }	
	

}
