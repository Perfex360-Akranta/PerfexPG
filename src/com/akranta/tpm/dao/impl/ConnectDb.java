package com.akranta.tpm.dao.impl;

import java.sql.Connection;
/*import java.sql.DriverManager;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;*/
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//import org.apache.poi.ss.usermodel.charts.DataSources;

import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

import oracle.jdbc.pool.OracleDataSource;

//import java.sql.Connection;

public class ConnectDb {
public   static  Connection getConnection() throws Exception{
		
	/*	Connection con=null;
		Properties properties =new Properties();
		Locale locale = new Locale("en", "IN");  
		ResourceBundle labels =ResourceBundle.getBundle("com.akranta.tpm.dao.impl.DBConnect", locale);
		String driver_class=labels.getString("connection.driver_class");
		String url=labels.getString("connection.url");
		String username=labels.getString("connection.username");
		String password=labels.getString("connection.password");
				    
		try
		{
			Class.forName(driver_class);
			con= DriverManager.getConnection(url,username,password);
		}	
		catch (Exception e)
		{
			CommonMessage.debugMsg("Connect Error");	
			CommonMessage.debugMsg(e.toString());
		}
		return con;
	*/
		
		try{
			   Connection con=null;
			 
			   
			 
			   OracleDataSource ds = getDataSource();
			   
			   if (ds == null) throw new Exception("Error: No DataSource");
			   if (ds != null) con = ds.getConnection();
		   
			   return con;
			   
		}catch(NamingException e){
				CommonMessage.debugMsg("conne " + e.getMessage());	
			   new Exception("Error: " + e.getMessage());
		}catch(Exception e){
			CommonMessage.debugMsg("conne " + e.getMessage());
		}
		return null;
	}

	private static OracleDataSource getDataSource() throws Exception{
		
		  Context initContext = new InitialContext();
		  Context envContext = (Context) initContext.lookup("java:/comp/env");
		  OracleDataSource ds = (OracleDataSource) envContext.lookup("jdbc/tpm");
		  if (envContext == null) throw new Exception("Error: No Context");
		  return ds;
	}
	
	public static String getDataBaseUser() throws Exception{
		  OracleDataSource ds = getDataSource();
		  return ds.getUser(); 
	}
}
