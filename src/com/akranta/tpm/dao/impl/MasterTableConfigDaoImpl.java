package com.akranta.tpm.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import net.sf.json.JSONObject;

//import org.apache.poi.hssf.usermodel.DateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
//import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.MasterTableConfigDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMmcdtlSql;
import com.akranta.tpm.dao.sql.GenTlMmcmstSql;
import com.akranta.tpm.dao.sql.MasterTableConfigSql;
import com.akranta.tpm.model.GenTlMmcdtl;
import com.akranta.tpm.model.GenTlMmcmst;
import com.akranta.tpm.model.MastTblConfigColMeta;
import com.akranta.tpm.model.MastTblConfigTableMeta;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.CommonMessage;

public class MasterTableConfigDaoImpl implements MasterTableConfigDao{
	
	private static final String REPORT_FORMAT_EXL_2007 = "xlsx";
	private static final String REPORT_FORMAT_EXL_2003 = "xls";
	private static final String TableNames="";
	private final int  ERROR_LENGTH = 0;
	private final int  ERROR_UNIQUE = 1;
	private List<CellStyle> errorStyles;
	        private final short [] ERROR_COLORS = { IndexedColors.RED.index,
			IndexedColors.YELLOW.index,IndexedColors.GREY_25_PERCENT.index,
			IndexedColors.INDIGO.index,IndexedColors.LAVENDER.index,IndexedColors.LIGHT_BLUE.index };
	
	
	DBActionTemplate dbActionTemplate =null;
	String locationflid;
	public MasterTableConfigDaoImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate =  dbActionTemplate;
	}
	  
	
	@Override
	public String locnflid(String loginLocnId) throws SQLException {
		// TODO Auto-generated method stub
		
		 locationflid = dbActionTemplate.getSingleValue("GEN_MV_FLIDHIERARCHY", "FLID", "FNLN_ORIGINALID", loginLocnId);
		return locationflid;
	}
	public GenTlMmcmst  getMasterTableConfigDetails(String menuId) throws NoDataFoundException, SQLException, Exception
	{
		
		GenTlMmcmst genTlMmcmst = new GenTlMmcmst();
		Object [] args =   { menuId };
		String sql = GenTlMmcmstSql.getSelectSqlForMenu();		
		CommonMessage.debugMsg(" menuId " + menuId + " SQl __ " + sql);
		genTlMmcmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		GenTlMmcdtl genTlMmcdtl = new GenTlMmcdtl();
		sql = GenTlMmcdtlSql.getSelectSql(genTlMmcmst.getMmcnKeyid());
		CommonMessage.debugMsg("sql 2 " + sql);
		List<GenTlMmcdtl> denTlMmcdtlList =  (List<GenTlMmcdtl>) dbActionTemplate.getDataList(sql, genTlMmcdtl);
		
		genTlMmcmst.setGenTlMmcdtl(denTlMmcdtlList);
		
		return genTlMmcmst;
	}
	
	public List<String[]> getMasterTableData(GenTlMmcmst genTlMmcmst,GridParams gridParmas) throws Exception{
		
		String sql = genMasterTableSql(genTlMmcmst,gridParmas);
		

		
		return  dbActionTemplate.getDataList(sql);
	}
	
	public int getMasterTableCount(GenTlMmcmst genTlMmcmst,GridParams gridParams) throws Exception{
		
		StringBuffer sql = getTotalCountSql(genTlMmcmst,gridParams);
		
		
		String countStr = dbActionTemplate.getSingleValue(sql.toString());
		return Integer.parseInt(countStr);
	}
	
	public MastTblConfigTableMeta getMasterTableMeta(String tableName) throws NoDataFoundException, Exception{
		
		String sql = MasterTableConfigSql.getMasterTableMetaDataSql(tableName);
		//Object [] args = new Object [] { tableName };
		CommonMessage.debugMsg("The SQL TableName"+sql);
		
		ResultSet rs =null;
		Connection connection = null;
		try{

			rs = dbActionTemplate.getData(sql) ;
			connection = rs.getStatement().getConnection();
			ResultSetMetaData rsmeta = rs.getMetaData();
			int colCount =  rsmeta.getColumnCount();
			List<MastTblConfigColMeta> mastTblConfigCols = new ArrayList<MastTblConfigColMeta>();
			
			MastTblConfigTableMeta  mastTblConfigTableMeta= new MastTblConfigTableMeta();
			for( int i = 0; i < colCount; i++)
			{
				MastTblConfigColMeta  mastTblConfigColMeta= new MastTblConfigColMeta(); 
				mastTblConfigColMeta.setColName(rsmeta.getColumnName(i+1));
				
				mastTblConfigColMeta.setType(rsmeta.getColumnType(i+1));
				mastTblConfigColMeta.setSize(rsmeta.getColumnDisplaySize(i+1));
				
				mastTblConfigCols.add(mastTblConfigColMeta);
				CommonMessage.debugMsg(mastTblConfigCols.toString() +"");
			}
			
			mastTblConfigTableMeta.setMastTblConfigCols(mastTblConfigCols);
			
			return mastTblConfigTableMeta;
			
		}finally{
			
			DBActionTemplate.closeConnection(rs, null,null, null,connection);
		}
	}
	
//	public MastTblConfigTableMeta populateMasterTableData(GenTlMmcmst genTlMmcmst,MastTblConfigTableMeta mastTblConfigTableMeta, String keyid) throws Exception{
//		String sql = genMastTblSelectSql(genTlMmcmst) ;
//		Object [] args = {keyid};
//		ResultSet rs=null;
//		Connection connection = null;
//		try{
//			CommonMessage.debugMsg(" sql " + sql);
//			
//			rs = dbActionTemplate.getData(sql, args);
//			ResultSetMetaData rsmeta = rs.getMetaData();
//			connection = rs.getStatement().getConnection();
//			
//			int colCount = rsmeta.getColumnCount();
//			String value =null;
//			if(rs.next()){
//				List<MastTblConfigColMeta> mastTblConfigColMetaList =  mastTblConfigTableMeta.getMastTblConfigCols();
//				for(int i=0; i< colCount;i++){
//					
//					int index = getIndexOfColMeta(mastTblConfigColMetaList,rsmeta.getColumnName(i+1));
//					if( index > -1 ){
//						if( rsmeta.getColumnType(i+1) == java.sql.Types.VARCHAR )
//							value = rs.getString(i+1).replace("{}","");
//						else if( rsmeta.getColumnType(i+1) == java.sql.Types.DATE )
//							value =CommonFunctions.convertSqlTimeStampToString(rs.getTimestamp(i+1));
//						else if( rsmeta.getColumnType(i+1) == java.sql.Types.NUMERIC )
//							value = Integer.toString(rs.getInt(i+1));
//						else if( rsmeta.getColumnType(i+1) == java.sql.Types.TIMESTAMP)
//							value =CommonFunctions.convertSqlTimeStampToString(rs.getTimestamp(i+1));
//						else	
//							value = (String)rs.getObject(i+1);
//						
//						mastTblConfigColMetaList.get(index).setValue(value);
//					}	
//						
//				}
//				mastTblConfigTableMeta.setMastTblConfigCols(mastTblConfigColMetaList);
//				return mastTblConfigTableMeta;
//			}
//		}finally{	
//			
//			DBActionTemplate.closeConnection(rs,null,null,null,connection);
//		}
//		return null;
//	}
	
	
	// =================== vignesh 01Nov2025   --------------------------------------------------------------------// 
	
	
	public MastTblConfigTableMeta populateMasterTableData(GenTlMmcmst genTlMmcmst,
            MastTblConfigTableMeta mastTblConfigTableMeta,
            String keyid) throws Exception {
String sql = genMastTblSelectSql(genTlMmcmst);
CommonMessage.debugMsg("printig key id before sql " + keyid );
Object[] args = { keyid };
//Object[] args = new Object[] { keyid };
//List<String> args = Arrays.asList(keyid);

ResultSet rs = null;
Connection connection = null;
try {
CommonMessage.debugMsg(" sql " + sql);

rs = dbActionTemplate.getData(sql, args);



ResultSetMetaData rsmeta = rs.getMetaData();
connection = rs.getStatement().getConnection();

int colCount = rsmeta.getColumnCount();
String value = null;

if (rs.next()) {
	CommonMessage.debugMsg("row 1");
List<MastTblConfigColMeta> mastTblConfigColMetaList = mastTblConfigTableMeta.getMastTblConfigCols();

for (int i = 0; i < colCount; i++) {
int index = getIndexOfColMeta(mastTblConfigColMetaList, rsmeta.getColumnName(i + 1));
if (index > -1) {
int colType = rsmeta.getColumnType(i + 1);

if (colType == java.sql.Types.VARCHAR
|| colType == java.sql.Types.CHAR
|| colType == java.sql.Types.LONGVARCHAR
|| colType == java.sql.Types.NVARCHAR
|| colType == java.sql.Types.NCHAR) {

String s = rs.getString(i + 1);
CommonMessage.debugMsg("row "+i+" :"+s);
value = (s == null) ? null : s.replace("{}", "");

} else if (colType == java.sql.Types.DATE) {

value = CommonFunctions.convertSqlTimeStampToString(rs.getTimestamp(i + 1));

} else if (colType == java.sql.Types.TIMESTAMP
|| colType == 2014 /* TIMESTAMP_WITH_TIMEZONE (numeric to avoid JDK diffs) */) {

value = CommonFunctions.convertSqlTimeStampToString(rs.getTimestamp(i + 1));

} else if (colType == java.sql.Types.INTEGER
|| colType == java.sql.Types.SMALLINT
|| colType == java.sql.Types.TINYINT
|| colType == java.sql.Types.BIGINT
|| colType == java.sql.Types.NUMERIC
|| colType == java.sql.Types.DECIMAL
|| colType == java.sql.Types.REAL
|| colType == java.sql.Types.FLOAT
|| colType == java.sql.Types.DOUBLE) {

// Preserve NULLs; avoid getInt() which returns 0 for NULL
Object obj = rs.getObject(i + 1);
value = (obj == null) ? null : obj.toString();

} else if (colType == java.sql.Types.BOOLEAN
|| colType == java.sql.Types.BIT) {

Object obj = rs.getObject(i + 1);
value = (obj == null) ? null : obj.toString();

} else {
// Fallback for other types
Object obj = rs.getObject(i + 1);
value = (obj == null) ? null : obj.toString();
}

mastTblConfigColMetaList.get(index).setValue(value);
}
}

mastTblConfigTableMeta.setMastTblConfigCols(mastTblConfigColMetaList);
return mastTblConfigTableMeta;
}
} finally {
DBActionTemplate.closeConnection(rs, null, null, null, connection);
}
return null;
}
	
// public MastTblConfigTableMeta insertMasterTableData(MastTblConfigTableMeta mastTblConfigTableMeta) throws Exception{
//		
//		StringBuffer sql = new StringBuffer();
//		StringBuffer valueSql = new StringBuffer();
//		//EntTlFacultymst entlfacultymst=new EntTlFacultymst();
//		
//		//entlfacultymst.setFtymFactKeyid("FNL000000002");
//		//CommonMessage.debugMsg("entlfacultymst.getFtymFactKeyid()>>>>"+entlfacultymst.getFtymFactKeyid());
//		sql.append(" INSERT INTO ");
//		sql.append(mastTblConfigTableMeta.getTableName() );
//		sql.append("( ");
//		CommonMessage.debugMsg("SQL:::"+sql);
//		List<MastTblConfigColMeta> mastTblConfigColMetaList =mastTblConfigTableMeta.getMastTblConfigCols();
//		
//	
//		
//		
//		CommonMessage.debugMsg("mastTblConfigColMetaList:::"+mastTblConfigColMetaList);
//		String value = null;
//		for(MastTblConfigColMeta mastTblConfigColMeta:mastTblConfigColMetaList )
//		{
//			
//			CommonMessage.debugMsg("mastTblConfigColMeta::::"+mastTblConfigColMeta);
//			CommonMessage.debugMsg("mastTblConfigColMeta.getColName()::"+mastTblConfigColMeta.getColName());
//			sql.append(mastTblConfigColMeta.getColName());
//	        sql.append(",");
//	        
//	       // if(mastTblConfigColMeta.getColName().equals("FTYM_FACT_KEYID"))
//	       // {
//	      //  	entlfacultymst.setFtymFactKeyid("FNL000000002");
//	     //   }
//	        
//			CommonMessage.debugMsg("SQL:::"+sql);
//			if(mastTblConfigColMeta.isPrimaryKey()){
//                CommonMessage.debugMsg("INSIDE THE IF:::");
//				value = dbActionTemplate.getSequenceNumber(mastTblConfigTableMeta.getTableName());
//				CommonMessage.debugMsg("value:::"+value);
//				mastTblConfigColMeta.setNewValue(value);
//			}
//			else
//				{
//				CommonMessage.debugMsg("INSIDE THE ELSE VALUE::::");
//				//CommonMessage.debugMsg("locationflid indie the if:::"+locationflid.length());
//			   if(mastTblConfigColMeta.getColName().equals("FTYM_FACT_KEYID"))
//		        {  
//				   if(locationflid!=null)
//				   {
//					   
//				    value =locationflid;
//				   }
//				   else
//				   {
//					   value = mastTblConfigColMeta.getNewValue();  
//				   }
//		        }
//			   else
//			   {
//				value = mastTblConfigColMeta.getNewValue();
//			   }
//				}
//			CommonMessage.debugMsg("value:::"+value);
//			setFormatValue(valueSql,mastTblConfigColMeta.getType(),value);
//			valueSql.append(",");
//
//		}	
//		valueSql.deleteCharAt(valueSql.lastIndexOf(","));
//		sql.deleteCharAt(sql.lastIndexOf(",")) ;
//		sql.append(") VALUES( ");
//		sql.append(valueSql);
//		sql.append(")");
//		CommonMessage.debugMsg(" sql insertMasterTableData-- " + sql);
//		dbActionTemplate.executeStatement(sql.toString());
//		return mastTblConfigTableMeta;
//	}
  
	
	
	// =================== vignesh 01Nov2025   --------------------------------------------------------------------// 
 public MastTblConfigTableMeta insertMasterTableData(MastTblConfigTableMeta mastTblConfigTableMeta) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		StringBuffer valueSql = new StringBuffer();
		//EntTlFacultymst entlfacultymst=new EntTlFacultymst();
		
		//entlfacultymst.setFtymFactKeyid("FNL000000002");
		//CommonMessage.debugMsg("entlfacultymst.getFtymFactKeyid()>>>>"+entlfacultymst.getFtymFactKeyid());
		sql.append(" INSERT INTO ");
		sql.append(mastTblConfigTableMeta.getTableName() );
		sql.append("( ");
		CommonMessage.debugMsg("SQL:::"+sql);
		List<MastTblConfigColMeta> mastTblConfigColMetaList =mastTblConfigTableMeta.getMastTblConfigCols();
		
	
		
		
		CommonMessage.debugMsg("mastTblConfigColMetaList:::"+mastTblConfigColMetaList);
		String value = null;
		for(MastTblConfigColMeta mastTblConfigColMeta:mastTblConfigColMetaList )
		{
			
			CommonMessage.debugMsg("mastTblConfigColMeta::::"+mastTblConfigColMeta);
			CommonMessage.debugMsg("mastTblConfigColMeta.getColName()::"+mastTblConfigColMeta.getColName());
			sql.append(mastTblConfigColMeta.getColName());
	        sql.append(",");
	        
	       // if(mastTblConfigColMeta.getColName().equals("FTYM_FACT_KEYID"))
	       // {
	      //  	entlfacultymst.setFtymFactKeyid("FNL000000002");
	     //   }
	        
			CommonMessage.debugMsg("SQL:::"+sql);
			if(mastTblConfigColMeta.isPrimaryKey()){
                CommonMessage.debugMsg("INSIDE THE IF:::");
                CommonMessage.debugMsg("printing table name " + mastTblConfigTableMeta.getTableName() );
                
     //           keyId = dbActionTemplate.getSequenceNumber(TableNames.TBL_GEN_TL_WORKFLOW_INFO, 10, "WF", null,null);
         //       value = dbActionTemplate.getSequenceNumber(mastTblConfigTableMeta.getTableName(),8,"AROL",null,null);  
		
                value = dbActionTemplate.getSequenceNumber(mastTblConfigTableMeta.getTableName());
				
				CommonMessage.debugMsg("value:::"+value);
				mastTblConfigColMeta.setNewValue(value);
			}
			else
				{
				CommonMessage.debugMsg("INSIDE THE ELSE VALUE::::");
				//CommonMessage.debugMsg("locationflid indie the if:::"+locationflid.length());
			   if(mastTblConfigColMeta.getColName().equals("FTYM_FACT_KEYID"))
		        {  
				   if(locationflid!=null)
				   {
					   
				    value =locationflid;
				   }
				   else
				   {
					   value = mastTblConfigColMeta.getNewValue();  
				   }
		        }
			   else
			   {
				value = mastTblConfigColMeta.getNewValue();
			   }
				}
			// --- Commented By Vignesh 17Oct2025 -----------//
			CommonMessage.debugMsg("value:::"+value);
			setFormatValue(valueSql,mastTblConfigColMeta.getType(),value);
			valueSql.append(",");
//			
//			CommonMessage.debugMsg("value:::"+value);
//
//			// Normalize value for SQL types so we don't send '' to numeric/timestamp columns.
//			String v = (value == null) ? null : value.trim();
//			// Treat string "null" as SQL NULL
//			if ("null".equalsIgnoreCase(v) || v.isEmpty()) {
//			    v = null;
//			}
//
//			// For numeric types, empty/blank must become NULL (not '')
//			int t = mastTblConfigColMeta.getType();
//			if (t == java.sql.Types.INTEGER ||
//			    t == java.sql.Types.BIGINT ||
//			    t == java.sql.Types.SMALLINT ||
//			    t == java.sql.Types.TINYINT ||
//			    t == java.sql.Types.NUMERIC ||
//			    t == java.sql.Types.DECIMAL) {
//			    // v already set to null if blank
//			}
//
//			// For DATE/TIMESTAMP, blank should also be NULL (not to_date('null',...))
//			if (t == java.sql.Types.DATE || t == java.sql.Types.TIMESTAMP) {
//			    // v already set to null if blank/"null"
//			}
//
//			setFormatValue(valueSql, t, v);
//			valueSql.append(",");


		}	
		valueSql.deleteCharAt(valueSql.lastIndexOf(","));
		sql.deleteCharAt(sql.lastIndexOf(",")) ;
		sql.append(") VALUES( ");
		sql.append(valueSql);
		sql.append(")");
		CommonMessage.debugMsg(" sql insertMasterTableData-- " + sql);
		dbActionTemplate.executeStatement(sql.toString());
		return mastTblConfigTableMeta;
	}
	
	public MastTblConfigTableMeta updateMasterTableData(MastTblConfigTableMeta mastTblConfigTableMeta) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer();
		
		sql.append(" UPDATE ");
		sql.append(mastTblConfigTableMeta.getTableName());
		sql.append(" SET ");
		
		whereSql.append(" WHERE 1 = 1 " );
		
		List<MastTblConfigColMeta> mastTblConfigColMetaList =   mastTblConfigTableMeta.getMastTblConfigCols();
		String value = null;
		for(MastTblConfigColMeta mastTblConfigColMeta:mastTblConfigColMetaList )
		{
			
			sql.append(mastTblConfigColMeta.getColName());
			sql.append("=");
			 
			if(mastTblConfigColMeta.getColName().equals("FTYM_FACT_KEYID"))
		        {
				   value =locationflid;
		        }
			else
			{
			value = mastTblConfigColMeta.getNewValue();
			}
			setFormatValue(sql,mastTblConfigColMeta.getType(),value);
			
			sql.append(",");

			if(mastTblConfigColMeta.isPrimaryKey()){
				whereSql.append(" AND ");
				whereSql.append(mastTblConfigColMeta.getColName());
				whereSql.append(" = '");
				whereSql.append(value);
				whereSql.append("'");
			}	
		}
		
		sql.deleteCharAt(sql.lastIndexOf(",")) ;
		sql.append(whereSql);
		
		CommonMessage.debugMsg(" sql UpdateMasterTableData -- " + sql);
		
		dbActionTemplate.executeStatement(sql.toString());
		return mastTblConfigTableMeta;
	}

	public MastTblConfigTableMeta deleteMasterTableData(MastTblConfigTableMeta mastTblConfigTableMeta) throws BusinessApplicationExceptions, Exception{
		
		StringBuffer sql = new StringBuffer();

		
		sql.append(" DELETE FROM ");
		sql.append(mastTblConfigTableMeta.getTableName() );
		
		sql.append(" WHERE 1 = 1 " );
		
		List<MastTblConfigColMeta> mastTblConfigColMetaList =   mastTblConfigTableMeta.getMastTblConfigCols();
		for(MastTblConfigColMeta mastTblConfigColMeta:mastTblConfigColMetaList )
		{
			
			if(mastTblConfigColMeta.isPrimaryKey()){
				sql.append(" AND ");
				sql.append(mastTblConfigColMeta.getColName());
				sql.append(" = '");
				sql.append(mastTblConfigColMeta.getNewValue());
				sql.append("'");
				break;
			}	
		}
		
		
		CommonMessage.debugMsg(" sql DeleteMasterTableData-- " + sql);
		
		dbActionTemplate.executeStatement(sql.toString());
		return mastTblConfigTableMeta;
	}


	private void setFormatValue(StringBuffer valueSql,int type , String value){
		
		CommonMessage.debugMsg("valueSql::"+valueSql);
		CommonMessage.debugMsg("type::"+type);
		CommonMessage.debugMsg("type::"+value);
		if( type == Types.DATE  ){
			valueSql.append("to_date('");
			valueSql.append(value);
			valueSql.append("','yyyy-mm-dd hh24:mi:ss')");
		}
		else if( type == Types.TIMESTAMP  ){
			valueSql.append("to_date('");
			valueSql.append(value);
			valueSql.append("','yyyy-mm-dd hh24:mi:ss')");
		}
		else{
			valueSql.append("'");
			valueSql.append(value);
			valueSql.append("'");
		}
		
		CommonMessage.debugMsg(" valueSql " + valueSql);

	}
	private int getIndexOfColMeta(List<MastTblConfigColMeta> mastTblConfigColMetaList , String colName){
		
		for(int index =0; index< mastTblConfigColMetaList.size();index++ ){
			if( mastTblConfigColMetaList.get(index).getColName().equals(colName))
				return index;
		}
		return -1;
	}
	private String genMasterTableSql(GenTlMmcmst genTlMmcmst,GridParams gridParams) {
		
		
		StringBuffer sql = new StringBuffer();
//		sql.append(" select * from (select ROWNUM rnum,  a.* FROM ( ");
//		sql.append( masterTableMainSql(genTlMmcmst, gridParams));
//		sql.append( " ) a ) where rnum between " );
//		sql.append( gridParams.getFromRow());
//		sql.append(" and ");
//		sql.append(gridParams.getToRow());
		
		//sriram 06-nov-2025
		
		
		sql.append( masterTableMainSql(genTlMmcmst, gridParams) );

		// Sorting FIRST
		if(gridParams.getGridSortColumn() != null && gridParams.getGridSortOrder() != null){
		    sql.append(" ORDER BY " + gridParams.getGridSortColumn() + " " + gridParams.getGridSortOrder());
		}

		// Then Pagination
		int limit  = Integer.parseInt(gridParams.getToRow());
		int offset = Integer.parseInt(gridParams.getFromRow()) - 1;

		sql.append(" LIMIT " + limit);
		sql.append(" OFFSET " + offset);



//		if(FilterCondSql.isValidKeyId(gridParams.getGridSortColumn()) && FilterCondSql.isValidKeyId(gridParams.getGridSortOrder()))
//			sql.append(" order by " + (Integer.parseInt(gridParams.getGridSortColumn() ) + 1 ) +" "+ gridParams.getGridSortOrder());
		
		

		com.akranta.tpm.utils.CommonMessage.debugMsg( sql );
		return sql.toString();
	}

	private StringBuffer getTotalCountSql(GenTlMmcmst genTlMmcmst,GridParams gridParams){
		
		StringBuffer sql = new StringBuffer();
		sql.append("select count(*) FROM (  ");
		sql.append( masterTableMainSql(genTlMmcmst,gridParams));
		sql.append( " ) " );
		com.akranta.tpm.utils.CommonMessage.debugMsg( " sql " + sql);
		return sql;
	}
	private StringBuffer masterTableMainSql(GenTlMmcmst genTlMmcmst, GridParams gridParms)
	{
		
		List<GenTlMmcdtl> genTlMmcdtlList =  genTlMmcmst.getGenTlMmcdtl();
		
		StringBuffer selectSql = new StringBuffer();
		StringBuffer fromSql = new StringBuffer();
		StringBuffer whereSql = new StringBuffer();
		StringBuffer defaultCondSql = new StringBuffer();
		StringBuffer outerSql = new StringBuffer();
			
		
		selectSql.append(" Select  ");
		
		fromSql.append(" FROM ");
		whereSql.append(" Where 1= 1 ");
		
		if( genTlMmcdtlList.size() > 0)
			fromSql.append(genTlMmcdtlList.get(0).getMscnTablename());
		
		String colName = null, fldCond = null;
		for( GenTlMmcdtl genTlMmcdtl : genTlMmcdtlList ){
			
			colName = genTlMmcdtl.getMscnColumnname();
			 
			if( genTlMmcdtl.getMscnDefaultcheck() != null && genTlMmcdtl.getMscnDefaultcheck().equals("Y") )
			{	
				defaultCondSql.append(" " +  genTlMmcdtl.getMscnDefaultcondition() );
			}
			if( ! genTlMmcdtl.getMscnColumndisplayorder().equals("-1") && genTlMmcdtl.getMscnColumntobedisplayed().equals("H") )
				continue;	
			
			if( genTlMmcdtl.getMscnSelectiontype().equals("P") || genTlMmcdtl.getMscnSelectiontype().equals("F") ){
				selectSql.append( genTlMmcdtl.getMscnPopupcolumnname());
				/*fromSql.append("," + genTlMmcdtl.getMscnPopuptablename());
				
				 * whereSql.append(" AND "); whereSql.append(colName); whereSql.append(" = ");
				 * whereSql.append(genTlMmcdtl.getMscnPopuptablekeyid());
				 * whereSql.append(" (+) ");
				 */
				
				//sriram 06-nov-2025
				fromSql.append(" LEFT JOIN " + genTlMmcdtl.getMscnPopuptablename() + " ON ");
				fromSql.append(colName + " = " + genTlMmcdtl.getMscnPopuptablekeyid());

				//GridParams gridParms1=new GridParams();
				CommonParams commonParams =new CommonParams();
				if( genTlMmcdtl.getMscnSelectiontype().equals("F") && CommonFunctions.isValidKeyId(commonParams.getFlid() ) ){
					whereSql.append(  " AND instr(PARENTFLIDS||FLID,'").append(commonParams.getFlid()).append("') > 0 ") ;
				}
			}
			else if( genTlMmcdtl.getMscnSelectiontype().equals("D") ){
				selectSql.append(  " Replace( to_char(" + colName + ",'dd-Mon-yyyy hh24:mi'), '01-Jan-1801 00:00','') " );
			}
			
			else if( genTlMmcdtl.getMscnIscomboselection().equals("Y") ){
				selectSql.append(getComboSelectionDecodeString(colName,genTlMmcdtl.getMscnCombodisplayname(),genTlMmcdtl.getMscnCombosaveinfo()));
			}
			else
				selectSql.append(colName);
			
			selectSql.append("::TEXT AS "+colName.replace("_","") +",");
			
		}	
		StringBuffer sql =new StringBuffer();
		sql.append(selectSql.substring(0, selectSql.length()-1));
		sql.append(fromSql);
		sql.append(whereSql);
		sql.append(defaultCondSql);
		outerSql.append(" select * from (  "  +sql + " )  where 1 = 1 ");
		outerSql.append(FilterCondSql.makeGridFilterCond(gridParms.getGridFilters())); 
		return outerSql;
	}
	
public void uploadmasterexcel(String excelName,String tableName,String userid)throws UploadException, BusinessApplicationExceptions, Exception	
{	
  try{
	   PopulateTable(excelName,tableName,userid);
  }
 finally{ 
 }
}

  private void PopulateTable(String excelName,String tableName,String userid)throws UploadException,BusinessApplicationExceptions,Exception
 {
    FileInputStream file=null;
    Sheet sheet=null;
    Workbook workbook=null;
    file=new FileInputStream(new File(excelName));
    workbook=excelName.endsWith(REPORT_FORMAT_EXL_2007)? new XSSFWorkbook(
		  file): new XSSFWorkbook(file);
	   sheet = workbook.getSheetAt(0);
		com.akranta.tpm.utils.CommonMessage.debugMsg("Last row number in the sheet:"
				+ sheet.getLastRowNum());
		com.akranta.tpm.utils.CommonMessage.debugMsg("workbook description:" + workbook);	
		int fromRow = 0;
		int batch = 1000;
		int batchCount =1;
		boolean dataExists =true;
		
		List<Object[]> datalist=new ArrayList<Object[]>();
		while(dataExists){	
			 com.akranta.tpm.utils.CommonMessage.debugMsg(" fromRow " + fromRow + " to row "
					+ (fromRow + batch) + " sheet" + sheet.getLastRowNum());
			if(convertExcelRowstoList(sheet,fromRow+1,(fromRow+batch),3,datalist)){
			    com.akranta.tpm.utils.CommonMessage.debugMsg("No data");
				     dataExists =false;
				        String gettableName=MasterTableConfigSql.getTableNameinfo(tableName);
				        String getseqValueKeyid=null;
				        List<String> sql  = new ArrayList<String>();
				        for(int i=0;i<datalist.size();i++)
				        	
				        {
				        StringBuilder sb=new StringBuilder();
				        getseqValueKeyid = dbActionTemplate.getSequenceNumber(tableName);
				        sb.append(" INSERT INTO ");
				        sb.append(tableName);
				        sb.append("(");
				        sb.append("select ");
				        sb.append(" '"+getseqValueKeyid+"' ");
				        sb.append(", ");
				        String  strColName =null;
				        String RowContent=null;
				        String Active="Y";
				        String datetime  =com.akranta.tpm.utils.CommonFunctions.getDate();			
			            String datetime1 =com.akranta.tpm.utils.CommonFunctions.getDate();		
											        
				        	  Object[]str=datalist.get(i);
				        	  CommonMessage.debugMsg("Size of Array "+str.length);
				        	  for(int j=1;j<str.length;j++)
						       {    
						    	   if(j != 3)
						    	   	sb.append("'"+str[j]+"',");   
						    	   else
								   {
						    		  sb.append("flid,'"+Active+"','"+userid+"','"+datetime+"','"+datetime1+"' from gen_mv_flidhierarchy where ALLPARENTS='"+str[j]+"'");
									}
						       }
				        	  sb.append(")");
				        	  sql.add(sb.toString());
				        }
				        com.akranta.tpm.utils.CommonMessage.debugMsg("Upload Mastertable Sql"+sql); 
				        dbActionTemplate.executeStatements(sql);
                   }
			else
			{				
				fromRow = (fromRow + batch);
				datalist = null;
				batchCount++;
			}	
		}
validateExcel(sheet);
   }

	private boolean convertExcelRowstoList(Sheet sheet, int fromRow, int toRow,
			int noCols, List<Object[]> dataList) throws UploadException {
		// TODO Auto-generated method stub

		try {
			com.akranta.tpm.utils.CommonMessage.debugMsg(fromRow + "  fromRow  " + toRow
					+ " row num " + sheet.getLastRowNum());
			if (fromRow > sheet.getLastRowNum())
				return false;
			toRow = sheet.getLastRowNum() > toRow ? toRow : sheet
					.getLastRowNum();
			com.akranta.tpm.utils.CommonMessage.debugMsg(toRow + " torow");
			for (int rowNo = (fromRow); rowNo <= toRow; rowNo++) {
				com.akranta.tpm.utils.CommonMessage.debugMsg(" aftr for loop" + rowNo);
				
				String[] row = new String[noCols + 1];
				int colNo = 0;

				for (; colNo < (noCols + 1); colNo++) {
					Cell cell = null;
					cell = sheet.getRow(rowNo).getCell(colNo);
					switch (cell.getCellType()) {
					case BOOLEAN:
						if (cell.getBooleanCellValue())
							row[colNo] = "1";
						else
							row[colNo] = "0";

						row[colNo] = (row[colNo] != null ? row[colNo] : "");
						break;
					case FORMULA:
						row[colNo] = Double
								.toString(cell.getNumericCellValue());
						row[colNo] = (row[colNo] != null ? row[colNo] : "");
						break;
					case NUMERIC:
				
						if (DateUtil.isCellDateFormatted(cell)) {
							Date date = cell.getDateCellValue();
							if (date != null) {
								String format = cell.getCellStyle()
										.getDataFormatString();
								format = format.replaceAll("\\\\-", "-")
										.replace("mmm", "MMM");
								SimpleDateFormat dFormat = new SimpleDateFormat(
										format,Locale.ENGLISH);
								String datVal = dFormat.format(date).replace(
										"[$-409]", "");
								datVal = datVal.replace("@", "");
								datVal = datVal.replace(";", "");
								row[colNo] = datVal;
					
							}
							com.akranta.tpm.utils.CommonMessage.debugMsg("row[ colNo ] "
									+ row[colNo]);
						} else {
							cell.setCellType(CellType.STRING);
							com.akranta.tpm.utils.CommonMessage.debugMsg(cell.getStringCellValue()
									+ " cell.getNumericCellValue() " + rowNo
									+ " rowNo colNo colNo colNo colNo colNo "
									+ colNo);
							row[colNo] = cell.getStringCellValue();
							row[colNo] = (row[colNo] != null ? row[colNo] : "0");
						}
						row[colNo] = (row[colNo] != null ? row[colNo] : "");
						break;
					case STRING:
						row[colNo] = cell.getStringCellValue();
						row[colNo] = (row[colNo] != null ? row[colNo]
								.replace("'", "''").replace("{}", "")
								.replace("<**>", "").replace("<*", "")
								.replace("*>", "") : "");
						row[colNo] = (row[colNo] == null ? "{}" : row[colNo]);
						break;
					case ERROR:
						row[colNo] = "";
						break;
					case BLANK:
						com.akranta.tpm.utils.CommonMessage.debugMsg(rowNo
								+ " rowNo colNo colNo colNo colNo colNo "
								+ colNo);
						if (colNo == 4 || colNo == 5)
							row[colNo] = "0";
						else
							row[colNo] = "{}";
						break;

					}
					com.akranta.tpm.utils.CommonMessage.debugMsg(cell.getCellType() + "row[ "
							+ colNo + " ]: " + row[colNo]);
				}
				dataList.add(row);			
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new UploadException(e.getMessage());
		}
	return true;
		
	}
  
private void populateErrorStyle(Sheet sheet){
	this.errorStyles = null;
	this.errorStyles = new ArrayList<CellStyle>();
	for(short i:ERROR_COLORS){
		this.errorStyles.add(getCellStyle(sheet,i));
	}
}
private CellStyle getCellStyle(Sheet sheet, short colorIndex){
	CellStyle style = sheet.getWorkbook().createCellStyle();
	style.setFillForegroundColor(colorIndex);
	style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	style.setBorderRight(BorderStyle.THIN);
    style.setBorderBottom(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);  
	return style;
}
private void validateExcel(Sheet sheet) throws UploadException{
	
	com.akranta.tpm.utils.CommonMessage.debugMsg("Inside the Validate Excel");
    populateErrorStyle(sheet);
	List<String[]> errorLength=new ArrayList<String[]>();
	List<String[]> duplicateCodeList=new ArrayList<String[]>();
	List<String[]> duplicateNameList=new ArrayList<String[]>();
	List<String[]> duplicateFlocationList=new ArrayList<String[]>();
	
	String Table="";
	boolean flag=false;
try{
 //errorLength =dbActionTemplate.getDataList(MasterTableConfigSql.validateLengthSql(Table));
 //com.akranta.tpm.utils.CommonMessage.debugMsg("The ErrorLength"+errorLength);
 //duplicateCodeList =dbActionTemplate.getDataList(MasterTableConfigSql.codeDuplicateSql(Table));
 //com.akranta.tpm.utils.CommonMessage.debugMsg("The DuplicateCostList"+duplicateCodeList);
 //duplicateNameList=dbActionTemplate.getDataList(MasterTableConfigSql.nameDuplicateSql(Table));
 //com.akranta.tpm.utils.CommonMessage.debugMsg("The DuplicateNameList"+duplicateNameList);
 //duplicateFlocationList=dbActionTemplate.getDataList(MasterTableConfigSql.flocationDuplicateSql(Table));       			
 //com.akranta.tpm.utils.CommonMessage.debugMsg("The duplicateFlocationList"+duplicateFlocationList);
}
	catch(Exception e){
		throw new UploadException(e.getMessage());
	}
	if(errorLength.size()>0 || duplicateCodeList.size()>0 || duplicateNameList.size()>0 || duplicateFlocationList.size()>0){
	
		if(errorLength.size()>0){
			com.akranta.tpm.utils.CommonMessage.debugMsg("inside length error"+ERROR_LENGTH);
			recordErrorInExcel(sheet,errorLength,ERROR_LENGTH);
		}
		
	if(duplicateCodeList.size()>0){
		
		com.akranta.tpm.utils.CommonMessage.debugMsg("inside length error"+ERROR_UNIQUE);
		recordErrorInExcel(sheet,duplicateCodeList,ERROR_UNIQUE);
	}

if(duplicateNameList.size()>0){

	com.akranta.tpm.utils.CommonMessage.debugMsg("inside length error"+ERROR_UNIQUE);
	recordErrorInExcel(sheet,duplicateNameList,ERROR_UNIQUE);
}

else if(duplicateFlocationList.size()>0){
	
	com.akranta.tpm.utils.CommonMessage.debugMsg("inside length error"+ERROR_UNIQUE);
	recordErrorInExcel(sheet,duplicateFlocationList,ERROR_UNIQUE);
	
}	
 throw new UploadException(sheet.getWorkbook(),-2);
 }	
}

private void recordErrorInExcel(Sheet sheet,List<String[]> errorRecords,int errorNumber){
	
	String [] celValues = null;
	int rowNo;
	int colNo ;
	CellStyle style = errorStyles.get(errorNumber);
	for(String[] row : errorRecords){
		for(String cellVal: row){
			if( cellVal == null || cellVal.isEmpty()) continue;
			celValues = (cellVal+"##").split("##");

			rowNo = Integer.parseInt( celValues[0] );
			colNo = Integer.parseInt( celValues[1]);

			Row rowExl = sheet.getRow(rowNo);
			Cell cell = rowExl.getCell(colNo); 
			cell.setCellStyle(style);
			
		}
	}	
}

	private StringBuffer getComboSelectionDecodeString(String colName, String combDisplaName,String comboSaveInfo){
		StringBuffer sql = new StringBuffer();
		StringTokenizer shortNames = new StringTokenizer(comboSaveInfo, ",");
		StringTokenizer displyNames = new StringTokenizer(combDisplaName, ",");
		
		/*
		 * sql.append(" DECODE("+colName ); while(shortNames.hasMoreElements()){
		 * sql.append(",'"+shortNames.nextToken() +"'");
		 * sql.append(",'"+displyNames.nextToken()+"'"); } sql.append("," +
		 * colName+")");
		 */
		
		//sriram 06-nov-2025
		 sql.append(" CASE ");

		    while (shortNames.hasMoreElements()) {
		        String shortName = shortNames.nextToken();
		        String displayName = displyNames.nextToken();
		        sql.append(" WHEN ").append(colName).append(" = '").append(shortName).append("' THEN '").append(displayName).append("' ");
		    }

		    sql.append(" ELSE ").append(colName).append(" END ");
		return sql;
	}

	private String genMastTblSelectSql(GenTlMmcmst genTlMmcmst )
	{
		StringBuffer  sql = new StringBuffer();
		List<GenTlMmcdtl> genTlMmcdtlList =  genTlMmcmst.getGenTlMmcdtl();
		
		sql.append(" SELECT * FROM ");
		
		String tableName = genTlMmcmst.getGenTlMmcdtl().get(0).getMscnTablename();
		sql.append( tableName );
		sql.append( " WHERE ");
		for(GenTlMmcdtl genTlMmcdtl : genTlMmcdtlList){
			if(genTlMmcdtl.getMscnColumndisplayorder().equals("-1")){
				sql.append(genTlMmcdtl.getMscnColumnname());
				break;
			}	
		}
		
		sql.append("  =  ?");
		return sql.toString();
	}
	public Workbook getMasrerTblMasterExcel(GenTlMmcmst genTlMmcmst,JSONObject colModel,String format,GridParams gridParams) throws Exception{
		
		  ResultSet rs = null;
		   try{
			
				
				ExcelUtils excelUtils = new ExcelUtils(colModel); 
				StringBuffer sql = masterTableMainSql(genTlMmcmst,gridParams);
				
				com.akranta.tpm.utils.CommonMessage.debugMsg( " sql " + sql);
				rs = dbActionTemplate.getData(sql.toString());
								
				return excelUtils.writeToExcel(rs,format, 0,0,0 );
			
		   }finally{
			   if( rs != null)
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }

	}


}
