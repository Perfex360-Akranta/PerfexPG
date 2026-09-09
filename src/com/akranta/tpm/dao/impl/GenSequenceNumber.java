package com.akranta.tpm.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.akranta.tpm.Exceptions.SequenceNumGenException;

import oracle.jdbc.pool.OracleDataSource;

public class GenSequenceNumber {
	private String tableName;
	private String prefix;
	private int keyLength;
	private String dateFormat;
	private String formatReset;
	
	private Connection dataSource;
	
	private Connection connection = null;
	CallableStatement stmt = null;
	
	public GenSequenceNumber(Connection connection, String tableName,int keyLength,String preFix,String dateFormat,String formatReset){
		this.connection =connection;
		this.tableName = tableName;
		this.keyLength =keyLength;
		this.prefix =preFix;
		this.dateFormat = dateFormat;
		this.formatReset = formatReset;
		try {
		//	connection = dataSource.getConnection();
			String procName = "{ ? = call RUNSEQUENCE(?,?,?,?,?)}";
			
			stmt = connection.prepareCall(procName);
			stmt.setString(2 , tableName);
			stmt.setInt(3 , keyLength);
			stmt.setString(4 , preFix);
			stmt.setString(5 , dateFormat);
			stmt.setString(6 , formatReset);
			stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
		}catch(SQLException e){
			
		}
	}
	
	public String getSequnceNumber() throws SequenceNumGenException{
		
		try{	
			stmt.execute();
			return stmt.getString(1);
				
		 }catch (SQLException exception) {
			throw new SequenceNumGenException("Sequence No. Generation :" +exception.getMessage(),exception.getCause());
		 }		

	}
	
	public void closeConnection(){
		
		if( stmt!= null ){
			try {
				stmt.close();
			} catch (SQLException e) {
			}
		}	
		if(connection != null ){
			try {
				connection.close();
			} catch (SQLException e) {
			}
		}	
	}
	
	
}