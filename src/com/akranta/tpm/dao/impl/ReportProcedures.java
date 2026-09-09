package com.akranta.tpm.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import com.akranta.tpm.utils.CommonMessage;

public class ReportProcedures {
	
	
	private String functionName;
	
	
	private List<String> paramValues;

	public ReportProcedures()
	{
		paramValues = new ArrayList();
	}
	
	
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public void setParamValues(List<String> paramValues) {
		this.paramValues = paramValues;
	}

	public List<String> getParamValues() {
		return paramValues;
	}
	
	public List<String[]> execute() throws Exception
	{
		StringBuffer query = new StringBuffer();
		
		query.append("{ ? = call ");
		query.append( functionName);
		query.append("( ");
		int i= 0;
		for(  i = 0; i < paramValues.size() + 1; query.append(" ?,"), i++ );
		
		query.deleteCharAt(query.length()-1);
		query.append(") }");
		Connection conn = null;
		CommonMessage.debugMsg(" query " + query);
		CallableStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = ConnectDb.getConnection();
			
			stmt = conn.prepareCall( query.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
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
			
		    List resultList = convertResultsettoList(rs);                           
			
		  /*  String [][] resultArr =  conbertListToArr(resultList);
		  */  
			//CommonMessage.debugMsg()
			return resultList;
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}finally{
			if( rs != null ){
				rs.close();
				rs = null;
			}
			if( stmt != null )
				stmt.close();
			if( conn != null){
				conn.close();
				conn = null;
			}	
		}
	}
	
	public static String[][] conbertListToArr(List dataList)
	{
		String [][] dataArr = new String[ dataList.size() ][ ((String[]) dataList.get(0)).length ];
		
		for( int i= 0 ; i < dataList.size() ;i++ )
		{
			String [] row = (String[])dataList.get(i);
			dataArr[ i ] = row;
			//for( int j = 0; j< row.length; j++)
			//	dataArr[i][j]= row[ j ];
		}
				
		
		return dataArr;
	}
	
	public static List abnormalitySummaryForCompany(String fromDate, String toDate) throws Exception
	{
		String query = "{ call  ABN_PC_ABNORMALITY.ABN_FN_ABNSUMMARYFORCMP(?,?,?)}";

		Connection conn = ConnectDb.getConnection();
		ResultSet rs = null;
		CallableStatement stmt = conn.prepareCall( query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		try{
		
			stmt.setString(2, fromDate);
			stmt.setString(3, toDate);
			
			// register the type of the out param - an Oracle specific type
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.execute();
			rs = (ResultSet)stmt.getObject(1);
			
		    List resultArr = convertResultsettoList(rs)  ;                           
			
			rs.close();
			stmt.close();
			
			return resultArr;
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
			
		}finally{
			if( rs != null ){
				rs.close();
				rs = null;
			}
			if( stmt != null )
				stmt.close();
			if( conn != null){
				conn.close();
				conn = null;
			}				
		}
		
	}

	public static List<String []> convertResultsettoList(ResultSet rs) throws SQLException
	{
		ResultSetMetaData rsmd;
		int rowCount = 0;
		int colCount = 0;
		try {
			rsmd = rs.getMetaData();
			colCount = rsmd.getColumnCount();
			
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		
		List<String []> dataList = new ArrayList<String[]>();
		
		
		while( rs.next() )
		{
			String[] row = new String[ colCount ];
			for( int i = 0; i < colCount; i++)
			{
				row[ i ] = rs.getString(i+1); 
			}
			
			dataList.add(row);
			rowCount++;
		}
		return dataList;

	}

	public static String[][] convertResultsettoArr(ResultSet rs) throws SQLException
	{
		ResultSetMetaData rsmd;
		int rowCount = 0;
		int colCount = 0;
		try {
			rsmd = rs.getMetaData();
			colCount = rsmd.getColumnCount();
			
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
		rs.last();
		rowCount = rs.getRow();
		
		String [][] dataArr = new String[ rowCount ][ colCount ];
		
		int i =0 ; 
		rs.beforeFirst();
		while( rs.next() )
		{

			for( int j = 0; j < colCount; j++)
			{
				dataArr[ i ][ j ] = rs.getString(j+1); 
			}
			
			i++;
		}
		return dataArr;

	}

}
