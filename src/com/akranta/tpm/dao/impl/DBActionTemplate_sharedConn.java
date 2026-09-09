package com.akranta.tpm.dao.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import oracle.jdbc.OracleTypes;
//import oracle.jdbc.pool.OracleDataSource;
import com.akranta.tpm.utils.CommonMessage;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;





public class DBActionTemplate_sharedConn {
	
/*	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet rs = null;
	private CallableStatement stmt   = null;
*/	
	private final String APP_NAME = "TPMToolKit";
	private static DataSource dataSource;
	private static Connection sharedConnection;
	
	/*public DBActionTemplate(OracleDataSource ds) throws Exception
	{
		try {
			CommonMessage.debugMsg(" connecting .........");
			//Connection connection = ConnectDb.getConnection();
			//statement =  connection.createStatement();
			this.dataSource= ds;
		}catch (Exception e) {
			CommonMessage.debugMsg(" connection error " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	*/
	/*public DBActionTemplate(){
		
	}
	*/
	public DBActionTemplate_sharedConn(DataSource ds) 
	{
		this.dataSource= ds;
	}
	
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}
	public DataSource getDataSource(){
		return this.dataSource ;
	}
	
	public  Connection getNewConnection() throws SQLException {
        // 🔍 Log pool status before getting a connection
		if (!(dataSource instanceof HikariDataSource)) {
	        throw new IllegalStateException("DataSource is not a HikariDataSource");
	    }

	    HikariDataSource hikariDS = (HikariDataSource) dataSource;
        HikariPoolMXBean poolBean = hikariDS.getHikariPoolMXBean();
        CommonMessage.debugMsgCon("---------- Before getConnection() ----------");
        CommonMessage.debugMsgCon("Active: " + poolBean.getActiveConnections()+" - Idle: " + poolBean.getIdleConnections()+" - Total: " + poolBean.getTotalConnections()+" - Waiting: " + poolBean.getThreadsAwaitingConnection());
//        CommonMessage.debugMsg("Idle: " + poolBean.getIdleConnections());
//        CommonMessage.debugMsg("Total: " + poolBean.getTotalConnections());
//        CommonMessage.debugMsg("Waiting: " + poolBean.getThreadsAwaitingConnection());

        Connection conn =dataSource.getConnection();

        // 🔍 Log after getting a connection
        CommonMessage.debugMsgCon("✅ Connection created successfully!");
        CommonMessage.debugMsgCon("After getConnection(): Active: " + poolBean.getActiveConnections());
        CommonMessage.debugMsgCon("--------------------------------------------");

        return conn;
    }
	
	public  Connection getSharedConnection() throws SQLException {

		if (sharedConnection == null) { 
			 sharedConnection = dataSource.getConnection();
			  }
		else if( sharedConnection.isClosed()) {
			  sharedConnection = getNewConnection();
		  }
       
        return sharedConnection;
    }
	
	public  void getConnectionStatus() throws SQLException {
        // 🔍 Log pool status before getting a connection
		if (!(dataSource instanceof HikariDataSource)) {
	        throw new IllegalStateException("DataSource is not a HikariDataSource");
	    }

	    HikariDataSource hikariDS = (HikariDataSource) dataSource;
        HikariPoolMXBean poolBean = hikariDS.getHikariPoolMXBean();
        CommonMessage.debugMsgCon("---------- close Connection ----------");
        CommonMessage.debugMsgCon("Active: " + poolBean.getActiveConnections()+" - Idle: " + poolBean.getIdleConnections()+" - Total: " + poolBean.getTotalConnections()+" - Waiting: " + poolBean.getThreadsAwaitingConnection());

        CommonMessage.debugMsgCon("✅ Connection closed successfully!");
        CommonMessage.debugMsgCon("After closed: Active: " + poolBean.getActiveConnections());
        CommonMessage.debugMsgCon("--------------------------------------------");

    }
	
	/*
	 * import java.sql.Connection; import java.sql.DriverManager; import
	 * java.sql.Statement;
	 * 
	 * public class getConnection { public static void main(String args[]) {
	 */
	     // Connection connection = null;
	      Statement stmt = null;
//	     public Connection dbConnect() {
//	    	 try {
//		         Class.forName("org.postgresql.Driver");
//		         dataSource = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PERFEXPRD",
//		            "perfexdev", "perfexdev");
//		      }catch(Exception e) {
//		    	  e.printStackTrace();
//		      }
//			return dataSource;
//	     }
	public void executeStatement(String sql, Object[] values, int[] dataTypes) throws Exception
	{
		Connection connection =  getNewConnection() ; //dataSource.getConnection();//ConnectDb.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		FileInputStream fis = null;
		File file; 
		
		int i = 0;
		//connection.setAutoCommit(false);
		for( Object value : values )
		{
			if( dataTypes[ i ] == Types.VARCHAR)
			{	
				preparedStatement.setString(i+1,(String)value);
			}	
			else if( dataTypes[ i ] == Types.INTEGER)
			{
				int number = Integer.parseInt((String) value);
				preparedStatement.setLong(i+1,number);
			}
			else if( dataTypes[ i ] == Types.DATE)	
			{
				preparedStatement.setDate(i+1,(java.sql.Date)value);
			}
			else if( dataTypes[ i ] == Types.TIMESTAMP)	
			{
				preparedStatement.setTimestamp(i+1,(java.sql.Timestamp)value);
			}
			else if( dataTypes[ i ] == Types.DOUBLE)
			{
				preparedStatement.setDouble(i+1,(Double) value);
			}
			else if( dataTypes[ i ] == Types.BLOB)
			{
				file=new File((String)value);
				fis=new FileInputStream(file);
				preparedStatement.setBinaryStream(i+1,fis,file.length());

			}
			else
				preparedStatement.setString( i+1,(String)value);
			
			i++;
		}
		try{
			preparedStatement.execute();
			connection.commit();
		}catch (SQLException e) {
			CommonMessage.debugMsg(" err DBAC 2" + e.getMessage());
			throw new Exception( e.getMessage());
		}catch( Exception e){
			CommonMessage.debugMsg(" err DBAC " + e.getMessage());
			throw new Exception( e.getMessage());
			
		}finally{
			if( fis != null){
				fis.close();
			}
			//closeConnection();
			closeConnection(null, null, null,preparedStatement,connection);
		}
		
		
	}
	
/*	public void executeStatements(List<String> sqls)  throws Exception
	{
		PreparedStatement preparedStatement = null;
		connection.setAutoCommit(false);
		for( String sql: sqls)
		{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.addBatch();
		}

		preparedStatement.executeBatch();
		
		preparedStatement.clearBatch();
	}
*/
	public  void executeStatement(String sql)  throws BusinessApplicationExceptions,Exception
	{
		Connection connection = null;
		Statement statement =null;
		try{
			connection = getSharedConnection() ; //dataSource.getConnection();
			statement = connection.createStatement();
			statement.execute(sql);
						
		}catch(SQLException e){
			String errorMsg = e.getMessage();	
			if(errorMsg.indexOf("ORA-00001") > -1 )
			{
				errorMsg = errorMsg.substring( errorMsg.indexOf(".")+1, errorMsg.indexOf(")"));	
				
			}
			throw new BusinessApplicationExceptions(errorMsg+",");
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}finally{
		
			closeConnection(null,statement,null,null,null);		
		}

		
	}
	
	public void processPLSQLProcedures(String procedureName, List<String> inParams, Object[] outParams) throws Exception {
	    Connection connection = null;
	    CallableStatement stmt = null;

	    try {
	        connection = dataSource.getConnection();
	        StringBuilder query = new StringBuilder();

	        // Build procedure call string: { call proc_name(?, ?, ?) }
	        query.append(" CALL "+procedureName+"(");

	        int i = 0;
	        if (inParams != null)
	            for (i = 0; i < inParams.size(); query.append("?,"), i++);

	        if (outParams != null)
	            for (i = 0; i < outParams.length; query.append("?,"), i++);

	        // remove last comma if exists
	        if (query.charAt(query.length() - 1) == ',')
	            query.deleteCharAt(query.length() - 1);

	        query.append(") ");

	        stmt = connection.prepareCall(query.toString());

	        // Set input params
	        int params = 0;
	        if (inParams != null) {
	            for (params = 0; params < inParams.size(); params++) {
	                stmt.setString(params + 1, inParams.get(params));
	            }
	        }

	        // Register output params
	        if (outParams != null) {
	            for (int j = 0; j < outParams.length; j++, params++) {
	                stmt.registerOutParameter(params + 1, java.sql.Types.VARCHAR); // PostgreSQL-compatible
	            }
	        }

	        // Execute
	        stmt.execute();

	        int totInParams = (inParams != null) ? inParams.size() : 0;
	        if (outParams != null) {
	            for (int k = 0; k < outParams.length; k++) {
	                outParams[k] = stmt.getObject(++totInParams);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new Exception("Error executing procedure: " + e.getMessage(), e);
	    } finally {
	        closeConnection(null, null, stmt, null, connection);}
	}

	
	public  void executeStatements(List<String> sqls)  throws BusinessApplicationExceptions,Exception
	{
		Connection connection = null;
		Statement statement = null;
		try{
			connection = getNewConnection() ; //dataSource.getConnection();
			statement = connection.createStatement();
			connection.setAutoCommit(false);
			for( String sql: sqls)
			{
				CommonMessage.debugMsg("Sql -"  +sql);
				statement.addBatch(sql);
			}
			statement.executeBatch();

			connection.commit();
			connection.setAutoCommit(true);
			statement.clearBatch();
			
		}catch(SQLException e){
			CommonMessage.debugMsg(" execute sts " + e.getMessage());
			String errorMsg = e.getMessage();
			if( connection != null && ! connection.isClosed())
				connection.rollback();
			
			if(errorMsg.indexOf("ORA-00001") > -1 )
			{
				errorMsg = errorMsg.substring( errorMsg.indexOf(".")+1, errorMsg.indexOf(")"));	
			}
			throw new BusinessApplicationExceptions(errorMsg+",");
		}catch(Exception e){
			CommonMessage.debugMsg(" execute sts 1" + e.getMessage());
			if( connection != null && ! connection.isClosed())
				connection.rollback();
			throw new Exception(e.getMessage());
		}finally{
			
			closeConnection(null,statement,null, null, connection);		
		}

		
	}
	
	
	public void saveByteFile(List<String> sqls, List<Object[]> valueList, List<int[]> dataTypes) throws Exception {
		   FileInputStream fis = null;
		   File file = null;
		   Connection connection = null;
		   PreparedStatement ps = null;
		   try {
		       connection =  getNewConnection() ; //dataSource.getConnection();
		       connection.setAutoCommit(false);

		       int index = 0;
		       for (String sql : sqls) {
		           ps = connection.prepareStatement(sql);

		           int i = 0;
		           int[] types = dataTypes.get(index);
		           Object[] values = valueList.get(index);

		           if (values != null) {
		               for (Object value : values) {
		                   int t = types[i];

		                   if (t == Types.VARCHAR) {
		                       ps.setString(i + 1, value == null ? null : String.valueOf(value));
		                   } else if (t == Types.INTEGER) {
		                       if (value == null) {
		                           ps.setNull(i + 1, Types.INTEGER);
		                       } else if (value instanceof Number) {
		                           ps.setInt(i + 1, ((Number) value).intValue());
		                       } else {
		                           ps.setInt(i + 1, Integer.parseInt(String.valueOf(value)));
		                       }
		                   } else if (t == Types.DATE) {
		                       ps.setDate(i + 1, (java.sql.Date) value);
		                   } else if (t == Types.DOUBLE) {
		                       if (value == null) {
		                           ps.setNull(i + 1, Types.DOUBLE);
		                       } else if (value instanceof Number) {
		                           ps.setDouble(i + 1, ((Number) value).doubleValue());
		                       } else {
		                           ps.setDouble(i + 1, Double.parseDouble(String.valueOf(value)));
		                       }
		                   } else if (t == Types.BLOB /* Oracle-style */ || t == Types.BINARY || t == Types.VARBINARY /* PG bytea */) {
		                       // Accept path / bytes / stream
		                       if (value == null) {
		                           ps.setNull(i + 1, Types.BINARY);
		                       } else if (value instanceof byte[]) {
		                           ps.setBytes(i + 1, (byte[]) value); // PG bytea preferred
		                       } else if (value instanceof java.io.InputStream) {
		                           ps.setBinaryStream(i + 1, (java.io.InputStream) value);
		                       } else {
		                           // assume it's a temp file path string
		                           file = new File(String.valueOf(value));
		                           fis = new FileInputStream(file);
		                           // PG JDBC accepts binary stream for bytea
		                           ps.setBinaryStream(i + 1, fis, file.length());
		                       }
		                   } else if (t == Types.TIMESTAMP) {
		                       ps.setTimestamp(i + 1, (java.sql.Timestamp) value);
		                   } else {
		                       // fallback as text
		                       ps.setString(i + 1, value == null ? null : String.valueOf(value));
		                   }

		                   i++;
		               }
		           }

		           ps.execute();

		           // close per-statement resources
		           if (fis != null) { try { fis.close(); } catch (Exception ignore) {} fis = null; }
		           if (ps != null) { try { ps.close(); } catch (Exception ignore) {} ps = null; }

		           index++;
		       }

		       // *** THIS WAS MISSING ***
		       connection.commit();

		   } catch (SQLException e) {
		       if (connection != null) try { connection.rollback(); } catch (Exception ignore) {}
		       throw new Exception(e.getMessage(), e);
		   } catch (Exception e) {
		       if (connection != null) try { connection.rollback(); } catch (Exception ignore) {}
		       throw e;
		   } finally {
		       if (fis != null) try { fis.close(); } catch (Exception ignore) {}
		       closeConnection(null, null, null, ps, connection);
		   }
		}

	public void executeStatement(List<String> sqls, List<Object[]> valueList, List<int[]> dataTypes)  throws Exception
	{
		FileInputStream fis = null;
		File file; 
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = getNewConnection() ; //dataSource.getConnection();
			int index = 0;
			connection.setAutoCommit(false);
			for( String sql: sqls)
			{	
				CommonMessage.debugMsg(" sql " + sql);
				preparedStatement = connection.prepareStatement(sql);
				
				int i = 0;
				int [] types = dataTypes.get(index);
				
				Object [] values = valueList.get(index);
				if( values != null ){
					for( Object value : values )
					{
						
						if( types[ i ] == Types.VARCHAR)
							preparedStatement.setString(i+1,(String)value);
						else if( types[ i ] == Types.INTEGER)
						{
							preparedStatement.setInt(i+1,Integer.parseInt((String) value));
						}
						else if( types[ i ] == Types.DATE)	
						{
							preparedStatement.setDate(i+1,(java.sql.Date)value);
						}
						else if( types[ i ] == Types.DOUBLE)
						{
							preparedStatement.setDouble(i+1,(Double) value);
						}
						else if( types[ i ] == Types.VARCHAR)
						{
							preparedStatement.setString(i+1,(String)value);
						}
						else if( types[ i ] == Types.BLOB)
						{
							file=new File((String)value);
							fis=new FileInputStream(file);
							preparedStatement.setBinaryStream(i+1,fis,file.length());
	
						}
						else if( types[ i ] == Types.TIMESTAMP)	
						{
							preparedStatement.setTimestamp(i+1,(java.sql.Timestamp)value);
						}
						else
							preparedStatement.setString( i+1,(String)value);
						
						i++;
					}
				}	
				//preparedStatement.addBatch();
				preparedStatement.execute();
				index++;
			}
			
	
			//preparedStatement.executeBatch();
//			connection.commit();
				
		}catch (SQLException e) {
			// TODO: handle exception
			if( connection != null && ! connection.isClosed())
				connection.rollback();
			throw new Exception( e.getMessage());
			
		}catch( Exception e){
			if( connection != null && ! connection.isClosed())
				connection.rollback();
			throw new Exception( e.getMessage());
		}finally{
			if( fis != null)
				fis.close();
			closeConnection(null,null,null, preparedStatement, connection);			
		}

		
	}
	
	public ResultSet getData(String sql, Object [] args   ) throws  NoDataFoundException ,Exception
	{
		Connection connection = null;
		PreparedStatement  preparedStatement   = null;
		ResultSet rs =null;
		try{
			connection = getNewConnection() ; //dataSource.getConnection();
CommonMessage.debugMsg(args.length +"args.lengthargs.lengthargs.length");
			preparedStatement  = connection.prepareStatement(sql) ;
			//preparedStatement  = dataSource.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for( int i =0 ; i < args.length; i++)
			{	
				//com.akranta.tpm.utils.CommonMessage.debugMsg("comb " + (String)args[i]);
				preparedStatement.setString(i+1, (String)args[i]);
			}
			preparedStatement.executeQuery();
			rs = preparedStatement.getResultSet();

			return rs;
		}catch(SQLException e){
			e.printStackTrace();
			com.akranta.tpm.utils.CommonMessage.debugMsg(e.getMessage());
			throw new NoDataFoundException("NO-DATA");
		}finally{
			
//			closeConnection(rs,null,null,preparedStatement,connection);
		}
	}
	
	public List<String[]> getDataList(String sql, Object [] args   ) throws  NoDataFoundException ,Exception
	{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet rs =null;
		try{
CommonMessage.debugMsg(args.length+ "  sql:::::::"+sql);
			connection = getSharedConnection(); //getNewConnection() ; //dataSource.getConnection();
			preparedStatement  = connection.prepareStatement(sql);
			//preparedStatement  = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

			for( int i =0 ; i < args.length; i++)
			{	
				preparedStatement.setString(i+1, (String)args[i]);
			}
			preparedStatement.executeQuery();
			rs = preparedStatement.getResultSet();

			return convertResultsettoList(rs,false);
		}catch(SQLException e){
			e.printStackTrace();
			//CommonMessage.debugMsg("DB " + e.getMessage());
		//	throw new NoDataFoundException("NO-DATA");
		}finally{
			
			closeConnection( rs,null,null, preparedStatement, null);
		}
		return null;
	}

	
	public ResultSet getData(String sql   ) throws Exception
	{
		Connection connection = getNewConnection() ; //dataSource.getConnection();
		
		PreparedStatement  preparedStatement  = connection.prepareStatement(sql) ;
		//PreparedStatement	preparedStatement  = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		return preparedStatement.executeQuery();
	}
	
	public List<String[]> getDataList(String sql   ) throws Exception
	{
		Connection connection =null;
		PreparedStatement preparedStatement=null;
		ResultSet rs = null;
		try{
			CommonMessage.debugMsg(" In side the get Data List Method 1   "+sql);
			connection = getSharedConnection(); //getNewConnection() ; //dataSource.getConnection();
			preparedStatement  = connection.prepareStatement(sql) ;
			//preparedStatement  = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs =  preparedStatement.executeQuery();
			CommonMessage.debugMsg(" In side the get Data List Method 2 "+rs);
			return convertResultsettoList(rs,false);
		
		}finally{
			
			closeConnection(rs, null, null, preparedStatement, null);
		}
	}

	public void populateDataList(String sql, List<String[]> dataList   ) throws Exception
	{
		Connection connection =null;
		PreparedStatement preparedStatement=null;
		ResultSet rs = null;
		try{
			//connection = getNewConnection() ; //dataSource.getConnection();
			preparedStatement  = connection.prepareStatement(sql) ;
			
			rs =  preparedStatement.executeQuery();
			convertResultsettoList(rs,false,dataList);
		
		}finally{
			
			closeConnection(rs, null, null, preparedStatement, connection);
		}
	}
	
	public List<String[]> getDataList(String sql, List<String> params   ) throws Exception
	{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet  rs = null;
		try{
			com.akranta.tpm.utils.CommonMessage.debugMsg("sql " + sql);
			connection = getSharedConnection(); // getNewConnection() ; //dataSource.getConnection();
			preparedStatement  = connection.prepareStatement(sql) ;
			//preparedStatement  = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//int i =1;
			for( int i =0 ; i < params.size(); i++)
			{	
				preparedStatement.setString(i+1, params.get(i));
			}
			rs =  preparedStatement.executeQuery();
			return convertResultsettoList(rs,false);
		}finally{
			
			closeConnection(rs, null, null, preparedStatement, null);
		}
	}
	
	

	public List<?> getDataList(String sql,Object classObject   ) throws Exception
	{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet rs = null;
		try{	
			connection = getSharedConnection(); // getNewConnection() ; //dataSource.getConnection();
			preparedStatement  = connection.prepareStatement(sql) ;
			//preparedStatement  = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			com.akranta.tpm.utils.CommonMessage.debugMsg( sql ); 
			rs =  preparedStatement.executeQuery();
		   	
			return convertToListTpmObject(rs,classObject);
		}finally{
		
			closeConnection(rs, null,null, preparedStatement, null);
		}

		
	}
	
	public List<?> getDataList(String sql, Object [] args , Object classObject   ) throws Exception
	{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet rs = null;
		try{	
			com.akranta.tpm.utils.CommonMessage.debugMsg(sql);
			//com.akranta.tpm.utils.CommonMessage.debugMsg(" dataSource " + dataSource.getDatabaseName() );
			connection = getSharedConnection(); // getNewConnection() ; //dataSource.getConnection();
			preparedStatement  = connection.prepareStatement(sql) ;
			//preparedStatement  = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for( int i =0 ; i < args.length; i++)
			{	
				preparedStatement.setString(i+1, (String)args[i]);
				com.akranta.tpm.utils.CommonMessage.debugMsg(args[i]);
			}
			preparedStatement.executeQuery();
			rs = preparedStatement.getResultSet();

		   	
			return convertToListTpmObject(rs,classObject);
		}finally{
		
			closeConnection(rs, null,null, preparedStatement, null);
		}

		
	}
	
	
	private List<Object> convertToListTpmObject(ResultSet rs, Object tpmModel) throws SQLException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		Class<?> cls  = tpmModel.getClass();
				
		ResultSetMetaData rsmeta = rs.getMetaData();
		int colCount =  rsmeta.getColumnCount();
		
		String [ ] methodNames = new String[ colCount ];
		Class<?> [ ] methodType = new Class[] { String.class };
		int [] colType = new int[colCount];

 
		for( int i = 0; i < colCount; i++)
		{
			String tblColName = rsmeta.getColumnName(i+1);

			colType[ i ] =  rsmeta.getColumnType(i+1);
			methodNames [ i ] = "set"+convertColNameToSetProperty(  tblColName);
			
		}
		
		Method method;
		String value;
		List<Object> resultObjects = new ArrayList<Object>();
		while( rs.next() ){
			
			Object dynMyClass  = cls.newInstance();
			for( int i = 0; i < colCount; i++){
			
				method = cls.getDeclaredMethod(methodNames [ i ],  methodType );
				
				if(colType[ i ] == java.sql.Types.VARCHAR ){
					value = rs.getString(i+1);
				}
				else if( colType[ i ] == java.sql.Types.DATE || colType[ i ] == java.sql.Types.TIMESTAMP )
					value = CommonFunctions.convertSqlTimeStampToString(rs.getTimestamp(i+1));
				else if(colType[ i ] == java.sql.Types.NUMERIC ){
					
					String numVal = rs.getString(i+1);
					if( numVal != null && numVal.indexOf(".") >=0 ) {
						value = Double.toString(rs.getDouble(i+1));
					}
					else if(numVal != null && Double.parseDouble(numVal) < Integer.MAX_VALUE )
						value = Integer.toString(rs.getInt(i+1));
					else if( numVal != null)
						value = Long.toString(rs.getLong(i+1));
					else
						value ="";
				}
				// ------ FIXED by Vignesh 16Oct2025 - Handle INTEGER type ------//
				else if(colType[ i ] == java.sql.Types.INTEGER ){
					// Handle INTEGER type from PostgreSQL
					Object objVal = rs.getObject(i+1);
					value = (objVal == null) ? "" : objVal.toString();
				}
				// ------ FIXED by Vignesh 16Oct2025 - Safe conversion for other types ------//
				else if(colType[ i ] == java.sql.Types.BLOB ){
					
					String fileName = getImageFileName();
					Blob blob=rs.getBlob(i+1);
					InputStream fin=blob.getBinaryStream();
					OutputStream out = null;
					try {
						int size=fin.available();
						
						out=new FileOutputStream(fileName);
						byte b[]= new byte[size];
						fin.read(b);
						out.write(b);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						fileName =null;
					}
					value = fileName;
					
				}
				else	
					value = (String)rs.getObject(i+1);
				
				method.invoke(dynMyClass, value);

			}
			resultObjects.add(dynMyClass);
		}
		
		return resultObjects;
	}

	private static String convertColNameToSetProperty(String columnName)
	{
		String tmpColName = columnName.toUpperCase();
		StringBuffer propertyName = new StringBuffer();
		int index ;
		while( (index = tmpColName.indexOf('_') ) >= 0){
			propertyName.append(tmpColName.charAt(0) + tmpColName.substring(1, index).toLowerCase() );
			tmpColName = tmpColName.substring(index+1);
			
		}
	
		propertyName.append(tmpColName.charAt(0) + tmpColName.substring(1).toLowerCase());
	
		return (propertyName.length() == 0 ?  propertyName.append(columnName): propertyName).toString().replace("_", ""); 
	}

	/* This used to call oracle functions have the first argument returns  ref-cursor;  [ out ]
	 *  @List<String> paramValues : contains the in paramters to the function 
	 */
	public ResultSet dbFunctionCall(String functionName, List<String> paramValues) throws Exception{
		
		Connection connection =null;	
		CallableStatement stmt =null;
		ResultSet rs =null;
		
		connection = getNewConnection() ; //dataSource.getConnection();
		
		StringBuffer query = new StringBuffer();
		
		query.append("{  call ");
		query.append( functionName);
		query.append("( ");
		int i= 0;
		for(  i = 0; i < paramValues.size() ; query.append(" ?,"), i++ );
		
		query.deleteCharAt(query.length()-1);
		query.append(") }");
	
		CommonMessage.debugMsg(""+query);
		stmt = connection.prepareCall( query.toString());
	
		//stmt = dataSource.prepareCall( query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    for( int params = 0; params < paramValues.size(); params++)
	    {
	    	com.akranta.tpm.utils.CommonMessage.debugMsg("params : "+paramValues.get(params));
	    	
	    	
	    	stmt.setString(params+3, paramValues.get(params) );
	    }
		
		// register the type of the out param - an Oracle specific type
	  
	    stmt.registerOutParameter(1, OracleTypes.INTEGER);
	    stmt.registerOutParameter(2, OracleTypes.CURSOR);
	    
		stmt.execute();
		
		int retVal = stmt.getInt(1);
	
		com.akranta.tpm.utils.CommonMessage.debugMsg(" dbFunctionCall  relval " + retVal);
		rs = (ResultSet)stmt.getObject(2);
	
		if( paramValues == null)
			paramValues = new ArrayList<String>();
		
		paramValues.add(0, retVal+"");
		
		return rs;
	}
	
	
	
	
	/*
	 * public ResultSet NewdbFunctionCall(String functionName, List<String>
	 * paramValues) throws Exception{
	 * 
	 * Connection connection =null; CallableStatement stmt =null; ResultSet rs
	 * =null;
	 * 
	 * //connection = getNewConnection() ; //dataSource.getConnection();
	 * 
	 * StringBuffer query = new StringBuffer();
	 * CommonMessage.debugMsg(" paramValues.size() paramValues.size() "+paramValues.size
	 * ());
	 * 
	 * query.append("{  call "); query.append( functionName); query.append("( ");
	 * int i= 0; for( i = 0; i < paramValues.size() ; query.append(" ?,"), i++ );
	 * 
	 * query.deleteCharAt(query.length()-1); query.append(") }");
	 * 
	 * CommonMessage.debugMsg(" "+query); stmt = dataSource.prepareCall(
	 * query.toString());
	 * 
	 * //stmt = dataSource.prepareCall( query.toString(),
	 * ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); for( int
	 * params = 0; params < paramValues.size()+1; params++) {
	 * com.akranta.tpm.utils.CommonMessage.debugMsg("params : "+paramValues.get(
	 * params));
	 * 
	 * 
	 * 
	 * }
	 * 
	 * // register the type of the out param - an Oracle specific type
	 * 
	 * stmt.registerOutParameter(1, OracleTypes.INTEGER);
	 * stmt.registerOutParameter(2, OracleTypes.CURSOR);
	 * 
	 * stmt.execute();
	 * 
	 * int retVal = stmt.getInt(1);
	 * 
	 * CommonMessage.debugMsg(" dbFunctionCall  relval   " + retVal); rs =
	 * (ResultSet)stmt.getObject(2);
	 * 
	 * if( paramValues == null) paramValues = new ArrayList<String>();
	 * 
	 * paramValues.add(0, retVal+"");
	 * 
	 * return rs; }
	 */
	public ResultSet NewdbFunctionCall2(String functionName, List<String> paramValues) throws Exception {
	    Connection connection = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    
	    try {
	        // Get the database connection
	     //  connection = ((Statement) dataSource).getConnection(); // Assuming dataSource is initialized elsewhere
	        // Build the callable query
	    	
	    	connection = getNewConnection() ; //dataSource.getConnection();
	        StringBuilder query = new StringBuilder();
	        query.append("{ CALL  ");
	        query.append(functionName);
	        query.append("(");

	        // Add placeholders for parameters
	        int paramCount = paramValues != null ? paramValues.size() : 0;
	        for (int i = 0; i < paramCount + 2; i++) { // +2 for return code and cursor
	            query.append("?,");
	        }

	        query.deleteCharAt(query.length() - 1); // Remove last comma
	        query.append(") }");

	        CommonMessage.debugMsg("Constructed query: " + query);
	        
	        
	        // Prepare the call
	        stmt = connection.prepareCall(query.toString());
	        connection.setAutoCommit(false);
//	        stmt = connection.prepareCall(query.toString());
//	        connection.setAutoCommit(false);

	        // Register OUT parameters first
	        stmt.registerOutParameter(1, Types.INTEGER); // return code
	        stmt.registerOutParameter(2, Types.OTHER);  // result cursor

	        // Bind IN parameters starting from index 3
	        if (paramValues != null) {
	            for (int i = 0; i < paramValues.size(); i++) {
	                String value = paramValues.get(i);
	                CommonMessage.debugMsg("Binding param " + (i + 3) + ": " + value);
	                stmt.setString(i + 3, value);
	            }
	        }
	        CommonMessage.debugMsg(" before Return value: ");
	        // Execute the call
	        stmt.execute();
	        connection.commit();
//	        connection.setAutoCommit(false);
//	        connection.commit();
//	        connection.setAutoCommit(true);
	        CommonMessage.debugMsg("After Return value: ");
	        
	        // Get OUT parameter values
	        int retVal = stmt.getInt(1);
	        CommonMessage.debugMsg("Return value: " + retVal);
	        rs = (ResultSet) stmt.getObject(2);

	        // Add return value to paramValues at index 0
	        if (paramValues == null) {
	            paramValues = new ArrayList<>();
	        }
	        paramValues.add(0, String.valueOf(retVal));
	        CommonMessage.debugMsg("Return value size: " + rs.getFetchSize());

	        return rs;
	    }catch (SQLException e) {
			// TODO: handle exception
			if( connection != null && ! connection.isClosed())
				connection.rollback();
			throw new Exception( e.getMessage());
			
		}catch( Exception e){
			if( connection != null && ! connection.isClosed())
				connection.rollback();
			throw new Exception( e.getMessage());
		} finally {
	        // Note: Do NOT close the ResultSet or Statement here if the caller needs the ResultSet
	        // Ideally use try-with-resources in the caller to handle ResultSet and Statement cleanup
	        if (connection != null) {
//	        	connection.close(); // May defer this if ResultSet is still in use
	        	closeConnection(null,null,null,null,connection);
	        }
	    }
	}
	public ResultSet NewdbFunctionCall1(String functionName, List<String> paramValues) throws Exception {
	    Connection connection = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;
	    
	    try {
	        // Get the database connection
	       connection = getNewConnection() ; //dataSource.getConnection(); // Assuming dataSource is initialized elsewhere
	        // Build the callable query
	        StringBuilder query = new StringBuilder();
	        query.append("{ CALL  ");
	        query.append(functionName);
	        query.append("(");

	        // Add placeholders for parameters
	        int paramCount = paramValues != null ? paramValues.size() : 0;
	        for (int i = 0; i < paramCount + 3; i++) { // +2 for return code and cursor
	            query.append("?,");
	        }

	        query.deleteCharAt(query.length() - 1); // Remove last comma
	        query.append(") }");

	        CommonMessage.debugMsg("Constructed query: " + query);
	        
	        
	        // Prepare the call
	        stmt = connection.prepareCall(query.toString());
	        connection.setAutoCommit(false);

	        // Register OUT parameters first
	        stmt.registerOutParameter(1, Types.INTEGER); // return code
	        stmt.registerOutParameter(2, Types.OTHER);  // result cursor
	        stmt.registerOutParameter(3, Types.LONGVARCHAR);

	        // Bind IN parameters starting from index 3
	        if (paramValues != null) {
	            for (int i = 0; i < paramValues.size(); i++) {
	                String value = paramValues.get(i);
	                CommonMessage.debugMsg("Binding param " + (i + 3) + ": " + value);
	                stmt.setString(i + 4, value);
	            }
	        }
	        CommonMessage.debugMsg(" before Return value: ");
	        // Execute the call
	        stmt.execute();
	        connection.commit();
	        connection.setAutoCommit(true);
	        CommonMessage.debugMsg("After Return value: ");
	        
	        // Get OUT parameter values
	        int retVal = stmt.getInt(1);
	        CommonMessage.debugMsg("Return value: " + retVal);
	        rs = (ResultSet) stmt.getObject(2);

	        // Add return value to paramValues at index 0
	        if (paramValues == null) {
	            paramValues = new ArrayList<>();
	        }
	        paramValues.add(0, String.valueOf(retVal));

	        return rs;
	    }catch (SQLException e) {
			// TODO: handle exception
			if( connection != null && ! connection.isClosed())
				connection.rollback();
			throw new Exception( e.getMessage());
			
		}catch( Exception e){
			if( connection != null && ! connection.isClosed())
				connection.rollback();
			throw new Exception( e.getMessage());
		} finally {
	        // Note: Do NOT close the ResultSet or Statement here if the caller needs the ResultSet
	        // Ideally use try-with-resources in the caller to handle ResultSet and Statement cleanup
	        if (connection != null) {
//	        	connection.close(); // May defer this if ResultSet is still in use
	        	closeConnection(rs,null,stmt,null,connection);
	        }
	    }
	}
	public ResultSet NewdbFunctionCall(String functionName, List<String> paramValues) throws Exception {
	    Connection connection = null;
	    CallableStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        // Get the database connection
	       connection = getNewConnection() ; //dataSource.getConnection(); // Assuming dataSource is initialized elsewhere

	        // Build the callable query
	        StringBuilder query = new StringBuilder();
	        query.append("{ CALL  ");
	        query.append(functionName);
	        query.append("(");

	        // Add placeholders for parameters
	        int paramCount = paramValues != null ? paramValues.size() : 0;
	        for (int i = 0; i < paramCount + 2; i++) { // +2 for return code and cursor
	            query.append("?,");
	        }

	        query.deleteCharAt(query.length() - 1); // Remove last comma
	        query.append(") }");

	        CommonMessage.debugMsg("Constructed query: " + query);

	        // Prepare the call
	        stmt = connection.prepareCall(query.toString());

	        // Register OUT parameters first
	        stmt.registerOutParameter(1, Types.INTEGER); // return code
	        stmt.registerOutParameter(2, Types.OTHER);  // result cursor

	        // Bind IN parameters starting from index 3
	        if (paramValues != null) {
	            for (int i = 0; i < paramValues.size(); i++) {
	                String value = paramValues.get(i);
	                CommonMessage.debugMsg("Binding param " + (i + 3) + ": " + value);
	                stmt.setString(i + 3, value);
	            }
	        }

	        // Execute the call
	        stmt.execute();

	        // Get OUT parameter values
	        int retVal = stmt.getInt(1);
	        CommonMessage.debugMsg("Return value: " + retVal);
	        rs = (ResultSet) stmt.getObject(2);

	        // Add return value to paramValues at index 0
	        if (paramValues == null) {
	            paramValues = new ArrayList<>();
	        }
	        paramValues.add(0, String.valueOf(retVal));

	        return rs;
	    } finally {
	        // Note: Do NOT close the ResultSet or Statement here if the caller needs the ResultSet
	        // Ideally use try-with-resources in the caller to handle ResultSet and Statement cleanup
	        if (connection != null) {
//	        	connection.close(); // May defer this if ResultSet is still in use
	        	closeConnection(null,null,null,null,connection);
	        }
	    }
	}

	public List<String[]> processFunctionCalls(String functionName, List<String> paramValues) throws Exception
	{
		Connection connection =null;	
		CallableStatement stmt =null;
		ResultSet rs =null;
		try{
	
		    //List<String[]> resultList = convertResultsettoList(rs,false);                           
			
		    //com.akranta.tpm.utils.CommonMessage.debugMsg(" after convert to res " + resultList.size());
		    rs = NewdbFunctionCall2(functionName, paramValues);
			return convertResultsettoList(rs,false);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			closeConnection( rs,null,stmt, null, connection);
		}	
	}
	
	private static List<String []> convertResultsettoList(ResultSet rs,boolean fillColName ) throws SQLException
	{
		
		List<String []> dataList = new ArrayList<String[]>();
		
		CommonMessage.debugMsg(" In side the Convert result set");
		convertResultsettoList(rs,fillColName,dataList);
		return dataList;

	}
	private static List<String []> convertResultsettoList(ResultSet rs,boolean fillColName,List<String []> dataList ) throws SQLException
	{
	
		ResultSetMetaData rsmd;
		//int rowCount = 0;
		int colCount = 0;
		try {
			rsmd = rs.getMetaData();
			colCount = rsmd.getColumnCount();
					
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		
	
		if( fillColName ){
			
			String[] colName = new String[ colCount ];
			for( int i = 0; i < colCount; i++){
				colName[ i ] = rsmd.getColumnName(i+1);
			}
			dataList.add(colName);
			fillColName= false;
		}	
		while( rs.next() )
		{
			String[] row = new String[ colCount ];
			
			for( int i = 0; i < colCount; i++){
				if( rsmd.getColumnType(i+1) == java.sql.Types.VARCHAR ){
					row[ i ] = rs.getString(i+1);
					row[ i ] = (row[ i ] != null ? row[ i ].replace("{}", "").replace("<**>","").replace("<*", "").replace("*>", ""):"");
				}	
				else if( rsmd.getColumnType(i+1) == java.sql.Types.DATE )
					row[ i ] = CommonFunctions.convertSqlTimeStampToString(rs.getTimestamp(i+1));
				else if( rsmd.getColumnType(i+1) == java.sql.Types.NUMERIC )
				{
					String numVal = rs.getString(i+1);
					if( numVal != null && numVal.indexOf(".") >=0 ) {
						row[ i ] = Double.toString(rs.getDouble(i+1));
					}
					else if( numVal != null && Double.parseDouble(numVal) < Integer.MAX_VALUE )
						row[ i ] = Integer.toString(rs.getInt(i+1));
					else if( numVal != null ) 
						row[ i ] = Long.toString(rs.getLong(i+1));
					else
						row[ i ] ="";
				}					
				else if( rsmd.getColumnType(i+1) == java.sql.Types.TIMESTAMP)
					row[ i ] = CommonFunctions.convertSqlTimeStampToString(rs.getTimestamp(i+1));
				else	
					row[ i ] = String.valueOf(rs.getObject(i+1));
			}
			dataList.add(row);
			//rowCount++;
		}
		return dataList;
	}	

	public String  getSequenceNumber(String tableName)	throws Exception
	{
		Connection connection =null;
		CallableStatement stmt = null;
		try {
			String newSeqnumber = "";
			connection = getNewConnection() ; //dataSource.getConnection();
			String procName = "{ call GENERATE_NEWCODE(?,?)}";
			
			stmt = connection.prepareCall(procName);
			stmt.setString(1 , tableName);
			stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
			stmt.execute();
			newSeqnumber = stmt.getString(2);
			
			return newSeqnumber;
			
		}catch (Exception exception) {
				throw new Exception("Sequence No. Generation :" +exception.getMessage());
			
		}finally{
			
			closeConnection(null,null,stmt,null,connection);
		}
	} 
	/* 	@tableName : name of the table
	 * 	@keyLength : length of key to generate
	 * 	@preFix	   : prefix of key ( EMP ) [null]
	 *  @dateFormat: if key has to generate based on date (YYYYMMDD,MMYY,YY): [ null ]
	 *  @formatReset: onece reached max length, if so (Y)  	[ null ] 
	 */
	
	public String  getSequenceNumber(String tableName,int keyLength,String preFix,String dateFormat,String formatReset)	throws Exception
	{
		Connection connection = null;
		CallableStatement stmt = null;
		try {
			String newSeqnumber = "";
			connection = getNewConnection() ; //dataSource.getConnection();
			String procName = "{ ? = call RUNSEQUENCE(?,?,?,?,?)}";
			
			stmt = connection.prepareCall(procName);
			CommonMessage.debugMsg(" Table Name  "+tableName+""+keyLength);	
	
			stmt.setString(2 , tableName);
			stmt.setInt(3 , keyLength);
			stmt.setString(4 , preFix);
			stmt.setString(5 , dateFormat);
			stmt.setString(6 , formatReset);
			stmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			stmt.execute();
			newSeqnumber = stmt.getString(1);
			CommonMessage.debugMsg("s no::"+newSeqnumber);
			return newSeqnumber;
			
		}catch (Exception exception) {
				throw new Exception("Sequence No. Generation :" +exception.getMessage());
			
		}finally{
			
			closeConnection(null,null, stmt, null,connection);
		}
	} 
	
	public boolean checkDuplicateValue(String tableName, String checkField,String checkValue,String condSql) throws SQLException
	{
		Connection connection = null;
		Statement statement =null;
		ResultSet rs =null;
		
		try{
			connection = getSharedConnection();// getNewConnection() ; //dataSource.getConnection();
			statement = connection.createStatement();
			String sql = " select " + checkField + " from " + tableName + " where " +
						 checkField  + " = '" + checkValue + "'" + (condSql != null ? condSql:"");
			
			rs = statement.executeQuery(sql );
			
			if( rs != null)
			{	
				rs.next();
				if( rs.getString(checkField) != null )
					return true;
			}
		}catch(Exception e){
			
		}finally{
			
			closeConnection(rs, statement, null,null,null );
		}

		return false;
	}
	
	public String getSingleValue(String tableName,String returnField,String checkField,String checkValue) throws SQLException
	{
		String sql = " SELECT " + returnField + " FROM " + tableName + " WHERE " + checkField + " = '" + checkValue +"'";
		CommonMessage.debugMsg(" sql " + sql);
		return executeSqlForSingleValue(sql);
	}
	public String getSingleValue(String tableName,String returnField,String checkField,String checkValue,String conditionSql) throws SQLException
	{
		String sql = " SELECT " + returnField + " FROM " + tableName + " WHERE " + checkField + " = '" + checkValue +"' AND " +conditionSql;
		CommonMessage.debugMsg(" sql " + sql);
		return executeSqlForSingleValue(sql);
	}
	
	private String executeSqlForSingleValue(String sql) throws SQLException
	{
		Connection connection =null;
		Statement statement = null;
		ResultSet rs= null;
		try{
			
			connection = getSharedConnection();// getNewConnection() ; //dataSource.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(sql );
			
			if( rs.next())
			{	
				
				return rs.getString(1); 
			}
		}catch( Exception e ){
			throw new  SQLException(e.getMessage());
		}finally{
			closeConnection(rs,statement,null,null, null);
		 }

		return null; 
	}

	private Object[] convertResultSetToObjectArr(ResultSet rs) throws NoDataFoundException,SQLException
	{
		Connection connection = rs.getStatement().getConnection();
		try{
			if( rs != null && rs.next())
			{
				ResultSetMetaData rsmeta = rs.getMetaData();
				int colCount =  rsmeta.getColumnCount();
				Object [] rowData = new Object [ colCount ];
				String value = null;
				for( int i = 0; i < colCount; i++)
				{
					if( rsmeta.getColumnType(i+1) == java.sql.Types.VARCHAR ){
						value = rs.getString(i+1);
						
						rowData[ i ] = value != null ? value.replace("{}","").replace("<**>","").replace("<*", "").replace("*>", ""):"";
					}	
					else if( rsmeta.getColumnType(i+1) == java.sql.Types.DATE )
						rowData[ i ] = CommonFunctions.convertSqlTimeStampToString(rs.getTimestamp(i+1));
					else if( rsmeta.getColumnType(i+1) == java.sql.Types.NUMERIC )
					{					
						String numVal = rs.getString(i+1);
						
						if( numVal != null && numVal.indexOf(".") >=0 ) {
							rowData[ i ] = Double.toString(rs.getDouble(i+1));
						}
						else if(numVal != null && Double.parseDouble(numVal) < Integer.MAX_VALUE )
							rowData[ i ] = Integer.toString(rs.getInt(i+1));
						else if( numVal != null ) 
							rowData[ i ] = Long.toString(rs.getLong(i+1));
						else
							rowData[ i ] ="";
					}else if (rsmeta.getColumnType(i+1) == java.sql.Types.INTEGER) {
					    int intVal = rs.getInt(i+1);
					    rowData[i] = rs.wasNull() ? "" : Integer.toString(intVal);
					}
					else if( rsmeta.getColumnType(i+1) == java.sql.Types.TIMESTAMP)
						rowData[ i ] = CommonFunctions.convertSqlTimeStampToString(rs.getTimestamp(i+1));
					else	
						rowData[ i ] = rs.getObject(i+1);
					
			
				}
				return rowData;
			}
			throw new  NoDataFoundException("NO-DATA");
		}finally{
			closeConnection(rs, null, null, null, connection);
		}
	}
	
	
	public Object[] getDataArr(String sql, Object [] args) throws NoDataFoundException, SQLException, Exception
	{	
		ResultSet rs = null;
		try{
			rs = getData(sql,args);
			return convertResultSetToObjectArr( rs );
		}finally{
			
			//closeConnection(rs,null,null,null,null);
		}
	}

	public List<String[]> getDataListWithColHeader(String sql, List<String> params   ) throws Exception
	{
		Connection connection =null;
		PreparedStatement preparedStatement =null;
		ResultSet rs=null;
		try{
			connection = getSharedConnection(); // getNewConnection() ; //dataSource.getConnection();
			preparedStatement  = connection.prepareStatement(sql) ;
			//preparedStatement  = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//int i =1;
			if( params != null ){
				for( int i =0 ; i < params.size(); i++)
				{	
					preparedStatement.setString(i+1, params.get(i));
				}
			}
			CommonMessage.debugMsg("preparedStatement"+preparedStatement.toString());
			rs =  preparedStatement.executeQuery();
			return convertResultsettoList(rs,true);
			
		}finally{
			closeConnection(rs, null,null,preparedStatement, null);
		}
	}
	
	public List<String[]> processFunctionCallsWithColHeaders(String functionName, List<String> paramValues) throws Exception
	{
	 /*	Connection connection =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try{
			//connection = getNewConnection() ; //dataSource.getConnection();
			StringBuffer query = new StringBuffer();
			
			
			query.append("{ ? = call ");
			query.append( functionName);
			query.append("( ");
			int i= 0;
			for(  i = 0; i < paramValues.size() + 1; query.append(" ?,"), i++ );
			
			query.deleteCharAt(query.length()-1);
			query.append(") }");

			stmt = connection.prepareCall( query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
		    for( int params = 0; params < paramValues.size(); params++)
		    {
		    	stmt.setString(params+3, paramValues.get(params) );
		    	CommonMessage.debugMsg(paramValues.get(params));
		    }
		 
			// register the type of the out param - an Oracle specific type
		    stmt.registerOutParameter(1, OracleTypes.INTEGER);
		   
		    stmt.registerOutParameter(2, OracleTypes.CURSOR);
		   
			stmt.execute();
			 
			int retVal = stmt.getInt(1);
			rs = (ResultSet)stmt.getObject(2);
	*/	
		Connection connection =null;	
		CallableStatement stmt =null;
		ResultSet rs =null;
		try{
	
		    rs = NewdbFunctionCall2(functionName, paramValues);
		    List<String[]> resultList = convertResultsettoList(rs,true);                           

			return resultList;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}finally{
			closeConnection(rs, null,stmt, null,connection);
		}
	}

	public List<?> processFunctionCalls(String functionName, List<String> paramValues,Object classObject) throws Exception
	{
		/*
		 * Connection connection =null;
		CallableStatement stmt = null;
		ResultSet rs = null;
		try{
			//connection = getNewConnection() ; //dataSource.getConnection();
			StringBuffer query = new StringBuffer();
			
			query.append("{ ? = call ");
			query.append( functionName);
			query.append("( ");
			int i= 0;
			for(  i = 0; i < paramValues.size() + 1; query.append(" ?,"), i++ );
			
			query.deleteCharAt(query.length()-1);
			query.append(") }");

			stmt = connection.prepareCall( query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
		    for( int params = 0; params < paramValues.size(); params++)
		    {
		    	stmt.setString(params+3, paramValues.get(params) );
		    	//	CommonMessage.debugMsg(paramValues.get(params));
		    }
			
			// register the type of the out param - an Oracle specific type
		    stmt.registerOutParameter(1, OracleTypes.INTEGER);
		    stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();
			
			int retVal = stmt.getInt(1);
			
			rs = (ResultSet)stmt.getObject(2);
			
			return convertToListTpmObject(rs,classObject);
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}finally{
			closeConnection(rs, null, stmt, null, connection);
		}
		*/
		Connection connection =null;	
		CallableStatement stmt =null;
		ResultSet rs =null;
		try{
		    rs = dbFunctionCall(functionName, paramValues);
		    return convertToListTpmObject(rs,classObject);                           
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}finally{
			closeConnection(rs, null,stmt, null,connection);
		}
	}

	/*
	 * public void processPLSQLProcedures(String procedureName,List<String>
	 * inParams, Object[] outParams) throws Exception{ Connection connection =null;
	 * CallableStatement stmt = null;
	 * 
	 * try{ connection = getNewConnection() ; //dataSource.getConnection();
	 * StringBuffer query = new StringBuffer();
	 * 
	 * query.append("{ call "); query.append( procedureName); query.append("( ");
	 * int i= 0; if( inParams != null ) for( i = 0; i < inParams.size();
	 * query.append(" ?,"), i++ );
	 * 
	 * if( outParams != null ){ for( i = 0; i < outParams.length ;
	 * query.append(" ?,"), i++ ); }
	 * 
	 * query.deleteCharAt(query.length()-1); query.append(") }");
	 * 
	 * 
	 * stmt = connection.prepareCall( query.toString()); int params =0; if( inParams
	 * != null ){ for( params = 0; params < inParams.size(); params++) {
	 * stmt.setString(params+1, inParams.get(params) ); } } if(outParams != null){
	 * for( int j=0; j < outParams.length;j++, params++){
	 * stmt.registerOutParameter(params+1, OracleTypes.VARCHAR); } } stmt.execute();
	 * int totInParm = inParams != null ?inParams.size():0;
	 * 
	 * if(outParams != null){ for(int outParamStart = 0; outParamStart <
	 * outParams.length; outParamStart++) outParams[ outParamStart ] =
	 * stmt.getObject(++totInParm); }
	 * 
	 * }catch(Exception e) { e.printStackTrace(); throw new
	 * Exception(e.getMessage()); }finally{ closeConnection(null,null,stmt, null,
	 * connection); }
	 */

			
	//}
	
	//public void closeConnection(){
		
	//	  com.akranta.tpm.utils.CommonMessage.debugMsg(" closing conn..");	
	/*	   if( rs != null){	
			   try {
				   if( ! rs.isClosed() )
					   rs.close();
			   } catch (SQLException e) {;}
			   rs =null;
		   }
		   if (statement  != null) {
		      try {
		    	  if( ! statement.isClosed() )
		    		  statement.close();
		      } catch (SQLException e) {;}
		      statement  = null;
		   }

		   if(stmt  != null) {
			      try {
			    	  if( ! stmt.isClosed() )
			    		  stmt.close();
			      } catch (SQLException e) {;}
			      stmt  = null;
		   }
		   if(preparedStatement != null) {
			  try {
				  if( ! preparedStatement.isClosed() )
					  preparedStatement.close();
			   } catch (SQLException e) {;}
			   preparedStatement = null;
		   }
		   
		   if (dataSource != null) {
		      try {
		    	  if( ! connection.isClosed())
		    		  connection.close();
		      } catch (SQLException e) {;}
		      connection = null;
		   }
		 */  
//	}

	
	
	public String getSingleValue(String sql) throws Exception{
		
		Connection connection = null;
		PreparedStatement  preparedStatement = null;
		ResultSet rs =null;
		try{
			String retVal = null;
			connection = getSharedConnection(); // getNewConnection() ; //dataSource.getConnection();
			preparedStatement  = connection.prepareStatement(sql) ;
			//preparedStatement  = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs =  preparedStatement.executeQuery();
			if( rs.next()){
				retVal = rs.getString(1);

			}
			
			return retVal;	
		
		}finally{
			
			closeConnection(rs,null,null,preparedStatement, null);
		}
	}
	
	public void restoreFile(String tableName,String blobFieldName, String condSql, String fileName) throws NoDataFoundException, Exception{
			
		
		InputStream fin =null;
		OutputStream out = null;
		Connection connection =null;
		PreparedStatement preparedStatement = null;
		ResultSet rs= null;
		try{
			String path = fileName.substring(0,fileName.lastIndexOf("/"));
			File file = new File( path);

			if(file.exists()){
				connection = getNewConnection() ; //dataSource.getConnection();
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT ");
				sql.append( blobFieldName );
				sql.append( " FROM ");
				sql.append(tableName);
				sql.append(" WHERE  1 = 1 " );
				sql.append(condSql);
				CommonMessage.debugMsg(" sql " + sql );
				preparedStatement  = connection.prepareStatement(sql.toString()) ;
				
				
				rs =  preparedStatement.executeQuery();
			   	if(rs.next())
			   	{	
			   		Blob blob=rs.getBlob(1);
			   		byte [] imgData = blob.getBytes(1,(int)blob.length());
			   		out=new FileOutputStream(fileName);
			   		out.write(imgData);
			   		out.flush();
			   		/*fin=blob.getBinaryStream();
					
					int size=fin.available();
					out=new FileOutputStream(fileName);
					byte b[]= new byte[size];
					fin.read(b);
					out.write(b);
					out.flush();
					*/
			   		
			   	}else
			   		throw new NoDataFoundException("NO-DATA");
			}	   	
		   	
		} catch (IOException e) {
			throw new Exception(e.getMessage());
		}finally{
			if( fin != null)
			{
				fin.close();
				fin = null;
			}
			if( out != null){
				out.close();
				out = null;
			}	
			closeConnection(rs, null, null,preparedStatement,connection );
		}
	}
	
	
	public void restoreFile1(String tableName, String blobFieldName, String condSql, String fileName)
	        throws NoDataFoundException, Exception {

	    // normalize and ensure parent dir exists (works on Win/Linux)
	    String outPath = fileName.replace('\\','/');
	    int cut = outPath.lastIndexOf('/');
	    if (cut > 0) {
	        File dir = new File(outPath.substring(0, cut));
	        if (!dir.exists()) dir.mkdirs();
	    }

	    final String sql = "SELECT " + blobFieldName + " FROM " + tableName + " WHERE 1 = 1 " + condSql;
	    CommonMessage.debugMsg("Printing the Query");
	    CommonMessage.debugMsg(" sql " + sql);

	    // ALWAYS close Connection/Statement/ResultSet to return them to the pool
	    try (Connection con = getNewConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        if (!rs.next()) {
	            throw new NoDataFoundException("NO-DATA");
	        }

	        byte[] imgData = null;

	        // Preferred for PostgreSQL (BYTEA)
	        try {
	            imgData = rs.getBytes(1);
	            if (imgData != null && imgData.length > 0) {
	                CommonMessage.debugMsg(" restoreFile: read using getBytes (bytea). length=" + imgData.length);
	            }
	        } catch (Throwable ignore) { /* fall back below */ }

	        // Stream fallback (works for both BYTEA and BLOB drivers)
	        if (imgData == null || imgData.length == 0) {
	            try (InputStream fin = rs.getBinaryStream(1)) {
	                if (fin != null) {
	                    CommonMessage.debugMsg(" restoreFile: read using getBinaryStream.");
	                    try (OutputStream out = new FileOutputStream(outPath)) {
	                        byte[] buf = new byte[8192];
	                        int n;
	                        while ((n = fin.read(buf)) > 0) out.write(buf, 0, n);
	                        out.flush();
	                    }
	                    return;
	                }
	            } catch (Throwable ignore) { /* try getBlob next */ }
	        }

	        // Last resort: Blob (won’t usually be used with PG bytea, but harmless)
	        if (imgData == null || imgData.length == 0) {
	            try {
	                Blob blob = rs.getBlob(1);
	                if (blob != null) {
	                    int len = (int) blob.length();
	                    imgData = blob.getBytes(1, len);
	                    CommonMessage.debugMsg(" restoreFile: read using getBlob. length=" + len);
	                }
	            } catch (Throwable ignore) { /* nothing left */ }
	        }

	        if (imgData == null || imgData.length == 0) {
	            throw new NoDataFoundException("NO-DATA");
	        }

	        try (OutputStream out = new FileOutputStream(outPath)) {
	            out.write(imgData);
	            out.flush();
	        }

	    } catch (Exception e) {
	        // show real root cause (SQL/driver/pool), not just IO
	        throw new Exception(e.getMessage(), e);
	    }
	}

	
	private String getImageFileName()
	{
		File f = new File("temp.txt"); 
		String path=f.getAbsoluteFile().getParent().replaceFirst("bin","")+"webapps"+ f.separator+APP_NAME + "TMP" +f.separator + "IMAGES"+f.separator;
		new File(path).mkdirs();
		
		Random generator = new Random();
		int r = Math.abs(generator.nextInt());
		return  path + r;
	}
	
	public void executeBatch(String sql, List<Object[]> valueList, int[] dataTypes) throws Exception
	{
		Connection connection = getNewConnection() ; //dataSource.getConnection();
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		connection.setAutoCommit(false);
		for(Object [] values:valueList ){
			int i = 0;
			for( Object value : values )
			{
				//CommonMessage.debugMsg(" values " +(String) value);
				if( dataTypes[ i ] == Types.VARCHAR)
				{	
					preparedStatement.setString(i+1,(String)value);
				}	
				else if( dataTypes[ i ] == Types.INTEGER)
				{
					preparedStatement.setInt(i+1,Integer.parseInt((String) value));
				}
				else if( dataTypes[ i ] == Types.DATE)	
				{
					preparedStatement.setDate(i+1,(java.sql.Date)value);
				}
				else if( dataTypes[ i ] == Types.DOUBLE)
				{
					preparedStatement.setDouble(i+1,(Double) value);
				}
				else if( dataTypes[ i ] == Types.VARCHAR)
				{
					preparedStatement.setString(i+1,(String)value);
				}
				else
					preparedStatement.setString( i+1,(String)value);
				
				i++;
			}
			preparedStatement.addBatch();
		}CommonMessage.debugMsg("SQl    :"+sql);	
		try{
			int[] updateCounts = preparedStatement.executeBatch();
			 
			connection.commit();
		}catch (SQLException e) {
			if( connection != null && ! connection.isClosed() )
				connection.rollback();
			throw new Exception( e.getMessage());
		}catch( Exception e){
			if( connection != null && ! connection.isClosed() )
				connection.rollback();
			CommonMessage.debugMsg(" err DBAC " + e.getMessage());
			throw new Exception( e.getMessage());
			
		}finally{
			
			closeConnection(null,null,null,preparedStatement,connection);			
		}
	}
	
	public void executeBatch(List<String> sqls, Map<Integer,List<Object[]>> valueListMap, List<int[]> dataTypesList) throws Exception
	{
		Connection connection = getNewConnection() ; //dataSource.getConnection();
		PreparedStatement preparedStatement  =null;
		connection.setAutoCommit(false);
		int index =0;
		try{
			for(String sql:sqls ){
				preparedStatement = connection.prepareStatement(sql);
				List<Object[]> valuesList = valueListMap.get(index);
				int [] dataTypes = dataTypesList.get(index);
				for(Object [] values:valuesList ){
					int i = 0;
					for( Object value : values )
					{
						if( dataTypes[ i ] == Types.VARCHAR)
						{	
							preparedStatement.setString(i+1,(String)value);
						}	
						else if( dataTypes[ i ] == Types.INTEGER)
						{
							preparedStatement.setInt(i+1,Integer.parseInt((String) value));
						}
						else if( dataTypes[ i ] == Types.DATE)	
						{
							preparedStatement.setDate(i+1,(java.sql.Date)value);
						}
						else if( dataTypes[ i ] == Types.DOUBLE)
						{
							preparedStatement.setDouble(i+1,(Double) value);
						}
						else if( dataTypes[ i ] == Types.VARCHAR)
						{
							preparedStatement.setString(i+1,(String)value);
						}
						else
							preparedStatement.setString( i+1,(String)value);
						
						i++;
					}
					preparedStatement.addBatch();
				}
				index++;
				preparedStatement.executeBatch();
				preparedStatement.clearBatch();
			}	
			connection.commit();
		}catch (SQLException e) {
			if( connection != null && ! connection.isClosed() )
				connection.rollback();
			throw new Exception( e.getMessage());
		}catch( Exception e){
			if( connection != null && ! connection.isClosed() )
				connection.rollback();
			throw new Exception( e.getMessage());
			
		}finally{
			
			closeConnection(null,null,null,preparedStatement,connection);			
		}
	}

	
	public static void closeConnection(ResultSet rs, Statement statement, CallableStatement stmt,PreparedStatement preparedStatement,Connection connection){
		
		  com.akranta.tpm.utils.CommonMessage.debugMsg(" closing conn..");	
		   if( rs != null){	
			   try {
				   if( ! rs.isClosed() )
					   rs.close();
			   } catch (SQLException e) {;}
			   rs =null;
		   }
		   if (statement  != null) {
		      try {
		    	  if( ! statement.isClosed() )
		    		  statement.close();
		      } catch (SQLException e) {;}
		      //statement  = null;
		   }

		   if(stmt  != null) {
			      try {
			    	  if( ! stmt.isClosed() )
			    		  stmt.close();
			      } catch (SQLException e) {;}
			      //stmt  = null;
		   }
		   if(preparedStatement != null) {
			  try {
				  if( ! preparedStatement.isClosed() )
					  preparedStatement.close();
			   } catch (SQLException e) {;}
			   //preparedStatement = null;
		   }
		   
			
			  if (connection != null) { 
				  try { 
					  if( ! connection.isClosed()) {
						  if (!(dataSource instanceof HikariDataSource)) {
						        throw new IllegalStateException("DataSource is not a HikariDataSource");
						    }
						    HikariDataSource hikariDS = (HikariDataSource) dataSource;
					        HikariPoolMXBean poolBean = hikariDS.getHikariPoolMXBean();
					        CommonMessage.debugMsg("---------- close Connection ----------");
					        CommonMessage.debugMsg("Active: " + poolBean.getActiveConnections()+" - Idle: " + poolBean.getIdleConnections()+" - Total: " + poolBean.getTotalConnections()+" - Waiting: " + poolBean.getThreadsAwaitingConnection());
					        
					        connection.close(); 
					        
					        CommonMessage.debugMsg("✅ Connection closed successfully!");
					        CommonMessage.debugMsg("After closed: Active: " + poolBean.getActiveConnections());
					        CommonMessage.debugMsg("--------------------------------------------");
					  }
			             
				  } catch (SQLException e) {;} //connection = null; 
				  }
			 
	}
	
	public static String getValueFromResultSet(ResultSetMetaData rsmd, ResultSet rs, int colIndex ) throws SQLException{
		String tmpValue  = null;
		
		if( rsmd.getColumnType(colIndex+1) == java.sql.Types.VARCHAR ){
			tmpValue = rs.getString(colIndex+1);
			tmpValue = (tmpValue != null ? tmpValue.replace("{}", "").replace("<*", "").replace("*>", "") :"");
		}	
		else if( rsmd.getColumnType(colIndex+1) == java.sql.Types.DATE )
			tmpValue = CommonFunctions.convertSqlTimeStampToString(rs.getTimestamp(colIndex+1));
		else if( rsmd.getColumnType(colIndex+1) == java.sql.Types.NUMERIC )
		{
			String numVal = rs.getString(colIndex+1);
			if( numVal != null && numVal.indexOf(".") >=0 ) {
				tmpValue = Double.toString(rs.getDouble(colIndex+1));
			}
			else if( numVal != null && Double.parseDouble(numVal) < Integer.MAX_VALUE )
				tmpValue = Integer.toString(rs.getInt(colIndex+1));
			else if( numVal != null ) 
				tmpValue = Long.toString(rs.getLong(colIndex+1));
			else
				tmpValue ="";
		}					
		else if( rsmd.getColumnType(colIndex+1) == java.sql.Types.TIMESTAMP)
			tmpValue = CommonFunctions.convertSqlTimeStampToString(rs.getTimestamp(colIndex+1));
		else	
			tmpValue = (String)rs.getObject(colIndex+1);
		
		return tmpValue;

	}

	/*
	 * public Map<Integer,List<String[]>> processDbFunCallMultCursor(String
	 * functionName, List<String> paramValues,int curCount) throws Exception{
	 * 
	 * 
	 * Connection connection =null; CallableStatement stmt =null; ResultSet rs
	 * =null; try{ connection = getNewConnection() ; //dataSource.getConnection();
	 * 
	 * StringBuffer query = new StringBuffer();
	 * 
	 * query.append("{ ? = call "); query.append( functionName); query.append("( ");
	 * int i= 0; for( i = 0; i < curCount ; query.append(" ?,"), i++ ); for( i = i -
	 * curCount ; i < paramValues.size() ; query.append(" ?,"), i++ );
	 * 
	 * query.deleteCharAt(query.length()-1); query.append(") }");
	 * 
	 * //com.akranta.tpm.utils.CommonMessage.debugMsg(query); stmt =
	 * connection.prepareCall( query.toString()); //stmt = dataSource.prepareCall(
	 * query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE,
	 * ResultSet.CONCUR_READ_ONLY);
	 * 
	 * 
	 * // register the type of the out param - an Oracle specific type
	 * stmt.registerOutParameter(1, OracleTypes.INTEGER); for( i = 2; i < curCount+2
	 * ; i++ ) stmt.registerOutParameter(i, OracleTypes.CURSOR);
	 * 
	 * for( int params = 0; params < paramValues.size(); params++) { stmt.setString(
	 * (params+curCount+2), paramValues.get(params) ); } stmt.execute(); int retVal
	 * = stmt.getInt(1); Map<Integer,List<String[]>> resultMap = new
	 * HashMap<Integer, List<String[]>>(); for( i = 0; i < curCount ; i++ ){ try{ rs
	 * = (ResultSet)stmt.getObject(i+2);
	 * 
	 * resultMap.put(i, convertResultsettoList(rs,true)); }catch(SQLException e){
	 * resultMap.put(i, null); com.akranta.tpm.utils.CommonMessage.debugMsg(
	 * "DBAction... :"+e.getMessage()); } } return resultMap;
	 * 
	 * }finally{ closeConnection(rs, null,stmt, null,connection); }
	 * 
	 * }
	 */
	public Map<Integer, List<String[]>> processDbFunCallMultCursorPG(
	        String functionName,
	        List<String> paramValues,
	        int curCount
	) throws Exception {

	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Map<Integer, List<String[]>> resultMap = new HashMap<>();

	    try {
	        connection =getNewConnection();
	        connection.setAutoCommit(false); // Required for refcursors

	        // --- Step 1: Build SQL Query ---
	        StringBuilder query = new StringBuilder();
	        query.append("SELECT * FROM ");
	        query.append(functionName);
	        query.append("(");

	        for (int i = 0; i < paramValues.size(); i++) {
	            query.append("?");
	            if (i < paramValues.size() - 1)
	                query.append(", ");
	        }
	        query.append(")");

	        ps = connection.prepareStatement(query.toString());

	        // --- Step 2: Bind parameters ---
	        for (int i = 0; i < paramValues.size(); i++) {
	            ps.setString(i + 1, paramValues.get(i));
	        }

	        // --- Step 3: Execute and retrieve cursor names + status ---
	        rs = ps.executeQuery();
	        
//	        connection.commit();
	        if (rs.next()) {
	            // Handle multiple cursors
	            for (int i = 0; i < curCount; i++) {
	                String cursorName = rs.getString(i + 1); // e.g., curexcel, curloss, etc.

	                if (cursorName != null && !cursorName.isEmpty()) {
	                    String fetchQuery = "FETCH ALL IN \"" + cursorName + "\";";
	                    try (PreparedStatement fetchStmt = connection.prepareStatement(fetchQuery);
	                         ResultSet cursorRs = fetchStmt.executeQuery()) {

	                        resultMap.put(i, convertResultsettoList(cursorRs, true));

	                    } catch (SQLException e) {
	                        System.err.println("Error fetching cursor " + cursorName + ": " + e.getMessage());
	                        resultMap.put(i, null);
	                    }
	                } else {
	                    resultMap.put(i, null);
	                }
	            }

	            // --- Step 4: Capture status/output values after cursors ---
	            int statusIndex = curCount + 1; // e.g., position after cursors
	            ResultSetMetaData meta = rs.getMetaData();
	            int colCount = meta.getColumnCount();

	            // Example: Capture extra OUT parameters (like kznresult, integer codes, etc.)
	            for (int i = statusIndex; i <= colCount; i++) {
	                String colName = meta.getColumnName(i);
	                String colValue = rs.getString(i);
	                CommonMessage.debugMsg("OUT param: " + colName + " = " + colValue);
	                // You can store these in a separate map if needed
	            }
	        }

	        return resultMap;

	    }catch (Exception ex) {
			// If we were in a PG transaction, try to rollback
			try {
			if (connection != null && !connection.getAutoCommit()) {
			connection.rollback();
			}
			} catch (Exception ignore) {}
			throw ex;
			} finally {closeConnection(rs, null, null, ps, connection);}
//			closeConnection(rs, null, null, ps, connection);
			}
//	    }
	

	public Map<Integer, List<String[]>> processDbFunCallMultCursor(String functionName,
            List<String> paramValues,
            int curCount) throws Exception {
			Connection connection = null;
			CallableStatement stmt = null;
			ResultSet rs = null;
			try {
			connection = getNewConnection();

			
			connection.setAutoCommit(false);
			StringBuffer query = new StringBuffer();
			query.append("{ call ").append(functionName).append("( ");
			int i = 0;
			for (; i < curCount; query.append(" ?,"), i++);
			for (i = i - curCount; i < paramValues.size()+1; query.append(" ?,"), i++);
			query.deleteCharAt(query.length() - 1).append(") }");
			
			stmt = connection.prepareCall(query.toString());
			stmt.registerOutParameter(2, Types.INTEGER);
			for (i = 3; i < curCount + 3 ; i++)
			stmt.registerOutParameter(i, Types.OTHER);
			for (int p = 0; p < paramValues.size(); p++)
			stmt.setString(p + 1, paramValues.get(p));
			
			CommonMessage.debugMsg("Query : "+ query.toString());
			
			stmt.execute();
			connection.commit();
//			connection.setAutoCommit(true);
			Map<Integer, List<String[]>> resultMap = new HashMap<Integer, List<String[]>>();
			for (i = 0; i < curCount; i++) {
			try {
			rs = (ResultSet) stmt.getObject(i + 3);
			resultMap.put(i, convertResultsettoList(rs, true));
			} catch (SQLException e) {
			resultMap.put(i, null);
			com.akranta.tpm.utils.CommonMessage.debugMsg("DBAction... :" + e.getMessage());
			} finally {
//			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
//			rs = null;
			}
			}
			return resultMap;
			
			} catch (Exception ex) {
			// If we were in a PG transaction, try to rollback
			try {
			if (connection != null && !connection.getAutoCommit()) {
			connection.rollback();
			}
			} catch (Exception ignore) {}
			throw ex;
			} finally {
//			closeConnection(rs, null, stmt, null, connection);
			}
			}
	
	public Map<Integer, List<String[]>> processDbFunCallMultCursor1(String functionName,
            List<String> paramValues,
            int curCount) throws Exception {
			Connection connection = null;
			CallableStatement stmt = null;
			ResultSet rs = null;
			try {
			connection = getNewConnection();

			
			connection.setAutoCommit(false);
			StringBuffer query = new StringBuffer();
			query.append("{ call ").append(functionName).append("( ");
			int i = 0;
			for (; i < curCount; query.append(" ?,"), i++);
			for (i = i - curCount; i < paramValues.size(); query.append(" ?,"), i++);
			query.deleteCharAt(query.length() - 1).append(") }");
			
			stmt = connection.prepareCall(query.toString());
//			stmt.registerOutParameter(2, Types.INTEGER);
			for (i = 1; i < curCount + 1 ; i++)
			stmt.registerOutParameter(i, Types.OTHER);
			for (int p = 0; p < paramValues.size(); p++)
			stmt.setString(p + curCount+1, paramValues.get(p));
			
			CommonMessage.debugMsg("Query : "+ query.toString());
			
			stmt.execute();
//			connection.commit();
//			connection.setAutoCommit(true);
			Map<Integer, List<String[]>> resultMap = new HashMap<Integer, List<String[]>>();
			for (i = 0; i < curCount; i++) {
			try {
			rs = (ResultSet) stmt.getObject(i + 1);
			resultMap.put(i, convertResultsettoList(rs, true));
			} catch (SQLException e) {
			resultMap.put(i, null);
			com.akranta.tpm.utils.CommonMessage.debugMsg("DBAction... :" + e.getMessage());
			} finally {
//			if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
//			rs = null;
			}
			}
			
			connection.commit();
			return resultMap;
			
			} catch (Exception ex) {
			// If we were in a PG transaction, try to rollback
			try {
			if (connection != null && !connection.getAutoCommit()) {
			connection.rollback();
			}
			} catch (Exception ignore) {}
			throw ex;
			} finally {
//			closeConnection(rs, null, stmt, null, connection);
			}
			}
	
	public void executeStatement(List<String> sqls, List<Object[]> valueList, List<int[]> dataTypes, char [] sqlType)  throws Exception
	{
		if( sqls.size() <= 0)
			throw new Exception("No statement found");
		
		FileInputStream fis = null;
		File file; 
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		CallableStatement stmt = null;
		try{
			connection = getNewConnection() ; //dataSource.getConnection();
			int index = 0;
			connection.setAutoCommit(false);
			for( String sql: sqls)
			{	
				
				CommonMessage.debugMsg(" sql " + sql);
			
				Object [] values = valueList.size()>0?valueList.get(index):null;
				if( sqlType[index] == 'Q'){
			       preparedStatement = connection.prepareStatement(sql);
				
				   int i = 0;
				   int [] types = dataTypes.size()>0?dataTypes.get(index):null;
				
				   if(values != null)
				   {
				
					   for( Object value : values )
					   {
						
						    if( types[ i ] == Types.VARCHAR)
							   preparedStatement.setString(i+1,(String)value);
						    else if( types[ i ] == Types.INTEGER)
						    {
							   preparedStatement.setInt(i+1,Integer.parseInt((String) value));
						    }
						    else if( types[ i ] == Types.DATE)	
						    {
							   preparedStatement.setDate(i+1,(java.sql.Date)value);
						    }
						    else if( types[ i ] == Types.DOUBLE)
						    {
							   preparedStatement.setDouble(i+1,(Double) value);
						    }
						    else if( types[ i ] == Types.VARCHAR)
						    {
							   preparedStatement.setString(i+1,(String)value);
						    }
						    else if( types[ i ] == Types.BLOB)
						    {
							   file=new File((String)value);
							   fis=new FileInputStream(file);
							   preparedStatement.setBinaryStream(i+1,fis,file.length());
	
						    }
						    else if( types[ i ] == Types.TIMESTAMP)	
						    {
							   preparedStatement.setTimestamp(i+1,(java.sql.Timestamp)value);
						    }
						    else
							   preparedStatement.setString( i+1,(String)value);
						
						    i++;
					     }
				   }
 				    //   preparedStatement.addBatch();
				     preparedStatement.execute();
				}
				else if( sqlType[index] == 'P') //
				{
					StringBuffer query = new StringBuffer();
					
					query.append("{ ? = call ");
					query.append( sql );
					query.append("( ");
					int i= 0;
					if( values != null )
						for(  i = 0; i < values.length; query.append(" ?,"), i++ );
					
					
					query.deleteCharAt(query.length()-1);
					query.append(") }");
					
					
					stmt = connection.prepareCall( query.toString());
					int params =0;
					if( values != null ){
					    for( params = 0; params < values.length; params++)
					    {		
					    	
					    	stmt.setString(params+2, values[params].toString() );
					    }
					}
				    stmt.registerOutParameter(1, OracleTypes.INTEGER);
					stmt.execute();
					
					int retVal = stmt.getInt(1);
					if( retVal == -1  ) //error
					{
						//com.akranta.tpm.utils.CommonMessage.debugMsg( " retVal " + retVal);
						connection.rollback();
						throw new Exception("DATA NOT SAVED");
					}
					else if(retVal == -2 || retVal == -3)
					{
						connection.rollback();
						throw new Exception(Integer.toString(retVal));
					}
						
				}
				index++;
			}
			
		//	com.akranta.tpm.utils.CommonMessage.debugMsg( " retVal " );
			//preparedStatement.executeBatch();
			connection.commit();
				
		}catch (SQLException e) {
			// TODO: handle exception
			if( connection != null && ! connection.isClosed())
				connection.rollback();
			throw new Exception( e.getMessage());
			
		}catch( Exception e){
			if( connection != null && ! connection.isClosed())
				connection.rollback();
			throw new Exception( e.getMessage());
		}finally{
			if( fis != null)
				fis.close();
			closeConnection(null,null,null, preparedStatement, connection);			
		}
	}
	
public List<ResultSet>  retResultSetFunCallMultiCur(String functionName, List<String> paramValues,int curCount) throws Exception{
		
		Connection connection =null;	
		CallableStatement stmt =null;
		ResultSet rs =null;
		
		connection = getNewConnection() ; //dataSource.getConnection();
		
		StringBuffer query = new StringBuffer();
		
		query.append("{ ? = call ");
		query.append( functionName);
		query.append("( ");
		int i= 0;
		for(  i = 0; i < curCount ; query.append(" ?,"), i++ );
		if( paramValues != null )
			for( i = i - curCount  ; i < paramValues.size() ; query.append(" ?,"), i++ );
		
		query.deleteCharAt(query.length()-1);
		query.append(") }");

		//com.akranta.tpm.utils.CommonMessage.debugMsg(query);
		stmt = connection.prepareCall( query.toString());
		//stmt = dataSource.prepareCall( query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	    
		
		// register the type of the out param - an Oracle specific type
	    stmt.registerOutParameter(1, OracleTypes.INTEGER);
	    for(  i = 2; i < curCount+2 ; i++ )
	    	stmt.registerOutParameter(i, OracleTypes.CURSOR);
	    if( paramValues != null ){
		    for( int params = 0; params < paramValues.size(); params++)
		    {
		    	stmt.setString( (params+curCount+2), paramValues.get(params) );
		    }
	    }    
		stmt.execute();
		int retVal = stmt.getInt(1);
		List<ResultSet>  resultSetList = new ArrayList<ResultSet>();
		for(  i = 0; i < curCount  ; i++ ){
			try{
			rs = (ResultSet)stmt.getObject(i+2);
			 
			resultSetList.add(rs);
			}catch(SQLException e){
				resultSetList =null;
				com.akranta.tpm.utils.CommonMessage.debugMsg( "DBAction... :"+e.getMessage());
			}
		}
		return resultSetList;
	}

	public static String getLogRequire()  
	{
		String logReq = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "LOG_REQUIRE");
		return logReq;
	}
	public static String getUrlLogRequire()  
	{
		String logReq = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "URL_LOG_REQUIRE");
		return logReq;
	}
	public static String getConLogRequire()  
	{
		String logReq = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "CON_LOG_REQUIRE");
		return logReq;
	}

}
