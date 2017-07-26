package org.qait.tatoc.Action.tatoc1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Tatocdatabase {

	
	final private String host = "10.0.1.86";
	final private String user = "tatocuser";
	final private String passwd = "tatoc01";
	String username=null;
	String password=null;
	int id = 0;
	public void FetchUserNameAndPassWordAndThenSetinDatabasePropertyFile(String Symbol)
	{
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection( "jdbc:mysql://" + host + "/tatoc",user,passwd);
			
			/*
			DatabaseMetaData md = con.getMetaData();
			//show tables;
			ResultSet rs = md.getTables(null, null, "%", null);
			while (rs.next()) {
			  System.out.println(rs.getString(3));
			}
			*/
			Statement stmtForIdentity=con.createStatement();  
			ResultSet resultSetForIdentity=stmtForIdentity.executeQuery("select * from identity");  
		
		 
			/*
			// Code For Getting Identiny Tables Metadata Details  
			ResultSetMetaData resultSetMetaDataForIdentity=resultSetForIdentity.getMetaData();  
			int totalColumnofIdentityTables= resultSetMetaDataForIdentity.getColumnCount();
			
			
			System.out.println("Total columns: "+totalColumnofIdentityTables);
			  
			
			String coloumName=null;
			String coloumTypeName=null;
			for(int index=1;index<=totalColumnofIdentityTables;index++){
				 coloumName=resultSetMetaDataForIdentity.getColumnName(index);
				 coloumTypeName=resultSetMetaDataForIdentity.getColumnTypeName(index);
			System.out.println(coloumName);  
			System.out.println(coloumTypeName); 
			
			}
			
			=================================================================
			 Result is:
		    
		     Total columns: 2
             Column Name of 1st column: id
             Column Type Name of 1st column: INT
             Column Name of 2nd column: symbol
             Column Type Name of 2nd column: VARCHAR
             =====================================================================
          
			*/
			
			while(resultSetForIdentity.next())  
			{
				if(Symbol.equalsIgnoreCase(resultSetForIdentity.getString(2))){
					id=resultSetForIdentity.getInt(1);
				}			     		
			}
			
			
			System.out.println("id is:"+id);
			
			
			Statement stmtForCredentials=con.createStatement();  
			ResultSet resultSetForCredentials=stmtForCredentials.executeQuery("select * from credentials");  
		
			
			
			/*
			/* Code For Getting Credentials Tables Metadata Details 
			ResultSetMetaData resultSetMetaDataForCredentials=resultSetForCredentials.getMetaData();  
			
			int TotalColumnofCredentialsTables= resultSetMetaDataForCredentials.getColumnCount();
			System.out.println("Total columns: "+TotalColumnofCredentialsTables);
			  
			
			String coloumName=null;
			String coloumTypeName=null;
			for(int index=1;index<=TotalColumnofCredentialsTables;index++){
				 coloumName=resultSetMetaDataForCredentials.getColumnName(index);
				 coloumTypeName=resultSetMetaDataForCredentials.getColumnTypeName(index);
			System.out.println(coloumName);  
			System.out.println(coloumTypeName); 
			
			}

			=================================================================
			 Result is:
		    
		     Total columns: 3
             Column Name of 1st column: id
             Column Type Name of 1st column: INT
             Column Name of 2nd column: name
             Column Type Name of 2nd column: VARCHAR

             Column Name of 3rd column: passkey
             Column Type Name of 3rd column: VARCHAR
             =====================================================================id
			*/
			
		 
		  
			while(resultSetForCredentials.next())  
			{
				if(resultSetForCredentials.getInt(1)==id){
					username= resultSetForCredentials.getString(2);
					password=resultSetForCredentials.getString(3);
				}
				}
			con.close();  
            addOption();
		}	
		catch(Exception e){ System.out.println(e);}  
	}
	
	
	
	
	 public void addOption() throws IOException {
			File file = new File("C:" + File.separator + "Users" + File.separator + "somilbansal"
					+ File.separator + "Desktop" + File.separator + "Eclipse_Workspace" + File.separator + "tatoc2"
					+ File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator
					+ "testdata" + File.separator + "database.properties");
	  

	    	Properties p=new Properties(); 

	    	p.setProperty("username",username);

	    	p.setProperty("password",password);
	    	p.store(new FileWriter(file),"Written");
	
	    }
}
