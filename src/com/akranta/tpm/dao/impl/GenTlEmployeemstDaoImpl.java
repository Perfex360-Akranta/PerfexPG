


package com.akranta.tpm.dao.impl;
import com.akranta.tpm.utils.CommonMessage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlEmployeemstDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlEmployeedtlSql;
import com.akranta.tpm.dao.sql.GenTlEmployeeimgSql;
import com.akranta.tpm.dao.sql.GenTlEmployeemstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EmpmailreportModel;
import com.akranta.tpm.model.GenTlEmployeedtl;
import com.akranta.tpm.model.GenTlEmployeeimg;
import com.akranta.tpm.model.GenTlEmployeemst;
import com.akranta.tpm.service.api.EmployeeCreationServiceApi;
import com.akranta.tpm.service.api.EmployeeGroupServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.utils.ExcelUtils;
//import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class GenTlEmployeemstDaoImpl implements GenTlEmployeemstDao {


	private DBActionTemplate dbActionTemplate; 
	private GenTlEmployeemstSql genTlEmployeemstsql = null;
	private GenTlEmployeedtlSql genTlEmployeedtlSql = null;
	private GenTlEmployeeimgSql genTlEmployeeimgSql = null;
	EmployeeCreationServiceApi serviceApi;
	
	public GenTlEmployeemstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		genTlEmployeemstsql = new GenTlEmployeemstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		genTlEmployeedtlSql = new GenTlEmployeedtlSql();
		genTlEmployeeimgSql = new GenTlEmployeeimgSql();
	}
	public void GenTlEmployeemstDaoImplJwt(String JwtToken) 
	{
		try{
			serviceApi = new EmployeeCreationServiceApi(JwtToken);
		//fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public GenTlEmployeemst create(GenTlEmployeemst genTlEmployeemst) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		
			genTlEmployeemst.setEmpmKeyid(dbActionTemplate.getSequenceNumber(GenTlEmployeemstSql.TBL_GEN_TL_EMPLOYEEMST,8,"EMP","","")); // set the sequnce number 
						
			sqls.add(GenTlEmployeemstSql.getInsertSql(genTlEmployeemstsql.getEmpmDbFields(), genTlEmployeemst.getSaveArray())); // add insert sql for master table
			if(genTlEmployeemst.getEmployeeDetail()!= null && genTlEmployeemst.getEmployeeDetail().size()>0) // check for detail table data
			{	
				GenTlEmployeedtl employeeDetail = (GenTlEmployeedtl)genTlEmployeemst.getEmployeeDetail().get(0); // get detail info from list in empployee object
				employeeDetail.setEmpdKeyid(genTlEmployeemst.getEmpmKeyid());
				sqls.add(GenTlEmployeedtlSql.getInsertSql(genTlEmployeedtlSql.getEmpdDbFields(), employeeDetail.getSaveArray()));// add insert sql for detail table
			}
			
			
			
			insertEmployeeImage(genTlEmployeemst);
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
			

		CommonMessage.debugMsg("Return DBAction:"+genTlEmployeemst);
		
		return genTlEmployeemst;
		
	}
	
	public GenTlEmployeemst insertEmployeeImage(GenTlEmployeemst genTlEmployeemst) throws Exception {
		
		List<String> sqls = new ArrayList<String>();
		List<GenTlEmployeeimg> genTlEmployeeimgs = genTlEmployeemst.getEmployeeImg();
		if( genTlEmployeeimgs != null && genTlEmployeeimgs.size()> 0)
			CommonMessage.debugMsg("Inside the Images table..."+genTlEmployeemst.getEmpmKeyid());
		for( GenTlEmployeeimg genTlEmployeeimg : genTlEmployeeimgs ){
			genTlEmployeeimg.setEmpiEmployeeid(genTlEmployeemst.getEmpmKeyid());
			sqls.add(GenTlEmployeeimgSql.getDeleteSql(genTlEmployeeimgSql.getEmpiDbFields(),genTlEmployeeimg.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}
		
		
		List<String> imgSqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		if(genTlEmployeemst.getEmployeeImg()!= null && genTlEmployeemst.getEmployeeImg().size()>0) // check for detail table data
		{	
			GenTlEmployeeimg employeeImage = (GenTlEmployeeimg)genTlEmployeemst.getEmployeeImg().get(0);
			imgSqls.add(GenTlEmployeedtlSql.getInsertSqlForEmp());
		
			java.sql.Timestamp  timeStamp = CommonFunctions.convertoSqlTimeStamp(employeeImage.getEmpiModifiedon()); 
			CommonMessage.debugMsg("Time Stamp....."+timeStamp);
			Object [] insValues = { genTlEmployeemst.getEmpmKeyid(),employeeImage.getEmpiBlobimage(),employeeImage.getEmpiBloblength(),
									employeeImage.getEmpiFilename(),timeStamp};
			int [] insDataType = { Types.VARCHAR, Types.BLOB,Types.INTEGER ,Types.VARCHAR,Types.TIMESTAMP};
		
		valueList.add(insValues);
		
		
		dataTypes.add(insDataType);
		
		
		dbActionTemplate.saveByteFile(imgSqls, valueList, dataTypes);
		
		}
		return genTlEmployeemst;
		
	}

	
	public GenTlEmployeeimg selectImg(GenTlEmployeemst genTlEmployeemst) throws Exception
	{
		try
		{
			CommonMessage.debugMsg("Inside Image Recall");
			GenTlEmployeeimg genTlEmployeeimg = new GenTlEmployeeimg();
			String sql = null;
			String empmKeyid=genTlEmployeemst.getEmpmKeyid();
			Object args [] = new Object [] {empmKeyid};
			sql = GenTlEmployeeimgSql.selectSql(empmKeyid);			
			CommonMessage.debugMsg("DAO SQL : "+sql);			
			genTlEmployeeimg.setSaveArray(dbActionTemplate.getDataArr(sql,args));
			return  genTlEmployeeimg;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}
	
	
	public GenTlEmployeemst update(GenTlEmployeemst genTlEmployeemst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();

		//GenTlEmployeedtlSql genTlEmployeedtlSql = new GenTlEmployeedtlSql();
		
		try {
			
			sqls.add(GenTlEmployeemstSql.getUpdateSql(genTlEmployeemstsql.getEmpmDbFields(), genTlEmployeemst.getSaveArray()));
			List<GenTlEmployeedtl> genTlEmployeedtls= genTlEmployeemst.getEmployeeDetail();
			
			CommonMessage.debugMsg("SQL ---------"+sqls.get(0));
			if( genTlEmployeedtls != null && genTlEmployeedtls.size()> 0 )
			{	
				//CommonMessage.debugMsg();
				for( GenTlEmployeedtl genTlEmployeedtl : genTlEmployeedtls ){
					genTlEmployeedtl.setEmpdKeyid(genTlEmployeemst.getEmpmKeyid());
					if( ! dbActionTemplate.checkDuplicateValue(GenTlEmployeedtlSql.TBL_GEN_TL_EMPLOYEEDTL,
									"EMPD_KEYID", genTlEmployeemst.getEmpmKeyid(), "") )
					{	
						sqls.add(GenTlEmployeedtlSql.getInsertSql(genTlEmployeedtlSql.getEmpdDbFields(), genTlEmployeedtl.getSaveArray()));
					}	
					else{
						sqls.add(GenTlEmployeedtlSql.getUpdateSql(genTlEmployeedtlSql.getEmpdDbFields(), genTlEmployeedtl.getSaveArray()));
					}	
				}
			}
			
			insertEmployeeImage(genTlEmployeemst);
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
		return genTlEmployeemst;
	}
	
	public GenTlEmployeemst delete(GenTlEmployeemst genTlEmployeemst) throws Exception 
	{
		com.akranta.tpm.utils.CommonMessage.debugMsg("keyid.."+genTlEmployeemst.getEmpmKeyid());
		String sqls;
		GenTlEmployeemstSql genTlEmployeemstSql = new GenTlEmployeemstSql();
		
		sqls =genTlEmployeemstSql.getDelete(genTlEmployeemst.getEmpmKeyid());
		dbActionTemplate.executeStatement(sqls);
			CommonMessage.debugMsg("Inside the DaoImpl Delete");
			/*List<GenTlEmployeedtl> genTlEmployeedtls= genTlEmployeemst.getEmployeeDetail();
			
			if( genTlEmployeedtls != null && genTlEmployeedtls.size()> 0 )
			{	
				CommonMessage.debugMsg("Inside the detail table");
				for( GenTlEmployeedtl genTlEmployeedtl : genTlEmployeedtls ){
					genTlEmployeedtl.setEmpdKeyid(genTlEmployeemst.getEmpmKeyid());
					sqls.add(GenTlEmployeedtlSql.getDeleteSql(genTlEmployeedtlSql.getEmpdDbFields(), genTlEmployeedtl.getSaveArray()));
					//sqls.add(GenTlEmployeedtlSql.getDeleteSql(genTlEmployeedtlSql.getEmpdDbFields(), genTlEmployeedtl.getSaveArray()));
					sqls.add(GenTlEmployeemstSql.getDeleteSql(genTlEmployeemstSql.getEmpmDbFields(), genTlEmployeemst.getSaveArray()));
				}
				
			}
			List<GenTlEmployeeimg> genTlEmployeeimgs = genTlEmployeemst.getEmployeeImg();
			if( genTlEmployeeimgs != null && genTlEmployeeimgs.size()> 0)
				CommonMessage.debugMsg("Inside the Images table");
			for( GenTlEmployeeimg genTlEmployeeimg : genTlEmployeeimgs ){
				genTlEmployeeimg.setEmpiEmployeeid(genTlEmployeemst.getEmpmKeyid());
				sqls.add(GenTlEmployeeimgSql.getDeleteSql(genTlEmployeeimgSql.getEmpiDbFields(),genTlEmployeeimg.getSaveArray()));
			}
			dbActionTemplate.executeStatements(sqls);
			
	*/
		return genTlEmployeemst;
		
		
		
		
	}
	
	
	
	public List<String[]> getAllEmployee() throws Exception {
		try
		{
			String sql = GenTlEmployeemstSql.selectSql();
			CommonMessage.debugMsg("Sql Data"+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String[]> getAllEMPMdepartment() throws Exception {
		
		return null;
	}

	@Override
	public GenTlEmployeemst select(String empmKeyid) throws Exception {
		GenTlEmployeemst genTlEmployeemst = new GenTlEmployeemst();
		GenTlEmployeedtl genTlEmployeedtl = new GenTlEmployeedtl();
	//	GenTlEmployeeimg genTlEmployeeimg = new GenTlEmployeeimg();
		CommonMessage.debugMsg("Before fetch the data");
		String sql = GenTlEmployeemstSql.getEmployeemstSql();
		
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] {empmKeyid};
		genTlEmployeemst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		CommonMessage.debugMsg("Dao Impl"+genTlEmployeemst);
		sql = GenTlEmployeedtlSql.getEmployeedtlSql();
		
		
		CommonMessage.debugMsg("sql..."+sql+empmKeyid);
		try{
			genTlEmployeedtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));			
			genTlEmployeemst.getEmployeeDetail().add(genTlEmployeedtl);
		//	genTlEmployeemst.getEmployeeImg().add(genTlEmployeeimg);
			CommonMessage.debugMsg("DaoKey"+genTlEmployeedtl.getEmpdKeyid());
			//CommonMessage.debugMsg("image"+genTlEmployeeimg.getEmpiEmployeeid());
			
			
		}catch(NoDataFoundException e)
		{
			CommonMessage.debugMsg(e.getMessage());
		}
		CommonMessage.debugMsg("Dao Impl"+genTlEmployeemst);
		//if(genTlEmployeeimg != null)
			//selectImg(genTlEmployeemst);
		return genTlEmployeemst;
	}

	
	public GenTlEmployeedtl getselect(String empdKeyid) throws NoDataFoundException, SQLException, Exception {
		GenTlEmployeemst genTlEmployeemst = new GenTlEmployeemst();
		GenTlEmployeedtl genTlEmployeedtl = new GenTlEmployeedtl();
		CommonMessage.debugMsg("Before fetch the data");
		String sql = GenTlEmployeemstSql.getEmployeemstSql();
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] {empdKeyid};
		genTlEmployeemst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		CommonMessage.debugMsg("Dao Impl"+genTlEmployeemst);
		sql = GenTlEmployeedtlSql.getEmployeedtlSql();
		CommonMessage.debugMsg("sql"+sql);
		try{
			genTlEmployeedtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));
			genTlEmployeemst.getEmployeeDetail().add(genTlEmployeedtl);
			CommonMessage.debugMsg("DaoKey"+genTlEmployeedtl.getEmpdKeyid());
		}catch(NoDataFoundException e)
		{
			CommonMessage.debugMsg(e.getMessage());
		}
		CommonMessage.debugMsg("Dao Impl"+genTlEmployeemst);
		return genTlEmployeedtl;
	}

	@Override
	public GenTlEmployeeimg selectImg(String nodeId) throws Exception {
		try
		{
			CommonMessage.debugMsg("EMPLOYEEE ");
			GenTlEmployeeimg genTlEmployeeimg = new GenTlEmployeeimg();
			String sql = null;
			
			sql = GenTlEmployeeimgSql.selectSql(nodeId);			
			CommonMessage.debugMsg("DAO SQL Image: "+sql);
			Object [] args =  new Object [] { nodeId };
			genTlEmployeeimg.setSaveArray(dbActionTemplate.getDataArr(sql,args) );
			CommonMessage.debugMsg("DAO SQL Image.... "+genTlEmployeeimg.getEmpiModifiedon());
			return  genTlEmployeeimg;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}


	public GenTlEmployeeimg getLayoutImg(GenTlEmployeeimg genTlEmployeeimg)	throws Exception {
		
		String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_EMPLOYEEIMG, "EMPI_FILENAME", "EMPI_EMPLOYEEID", genTlEmployeeimg.getEmpiEmployeeid());
		if( fileName != null ){
			if( fileName.lastIndexOf("/") > -1 )
				fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);			
			String fileNamePath = genTlEmployeeimg.getEmpiBlobimage()+  fileName; 
			String condSql = " AND EMPI_EMPLOYEEID = '" + genTlEmployeeimg.getEmpiEmployeeid() + "'";			
			String imgFileName = genTlEmployeeimg.getEmpiFilename()+fileName;
			genTlEmployeeimg.setEmpiFilename(imgFileName);
			//genTlEmployeeimg.setEmpiFilename(fileNamePath);
			CommonMessage.debugMsg(" fileName IN DAO IMPL................" + fileNamePath);		
			
				dbActionTemplate.restoreFile1(TableNames.TBL_GEN_TL_EMPLOYEEIMG, "EMPI_BLOBIMAGE", condSql, fileNamePath);
		
			return 	genTlEmployeeimg;
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> getAllFactoryname(String Employeeid,String Funloclink) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		CommonMessage.debugMsg("Employeeid dao impl"+Employeeid);
		
		GenTlEmployeemstSql genTlEmployeemstsql =new GenTlEmployeemstSql();		
		String sql= GenTlEmployeemstSql.getfunloclink(Employeeid,Funloclink);
		//return (List<String[]>) genTlEmployeemstsql;
		
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData ;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> getAllSectionName(String Employeeid,String Funloclink)
			throws Exception {
		
		List<String> sqls = new ArrayList<String>();
		CommonMessage.debugMsg("Employeeid dao impl"+Employeeid);
		
		GenTlEmployeemstSql genTlEmployeemstsql =new GenTlEmployeemstSql();
		
		String sql= GenTlEmployeemstSql.getfunloclink(Employeeid,Funloclink);
		//return (List<String[]>) genTlEmployeemstsql;
		// TODO Auto-generated method stub
		//return null;
	    
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData ;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String[]> getAllLineName(String Employeeid,String Funloclink) throws Exception {
		
		List<String> sqls = new ArrayList<String>();
		
		GenTlEmployeemstSql genTlEmployeemstsql =new GenTlEmployeemstSql();
		
		CommonMessage.debugMsg("Employeeid dao impl"+Employeeid);
		
		String sql= GenTlEmployeemstSql.getfunloclink(Employeeid,Funloclink);
		//return (List<String[]>) genTlEmployeemstsql;
		// TODO Auto-generated method stub
		//return null;
	    
		 List<String []> gridData = dbActionTemplate.getDataList(sql);
			return gridData ;
	
	}

	@Override
	public List<String[]> getAllEquipmentName(String Employeeid,String Funloclink)throws Exception {
		// TODO Auto-generated method stub
List<String> sqls = new ArrayList<String>();
		
		GenTlEmployeemstSql genTlEmployeemstsql =new GenTlEmployeemstSql();
		
		CommonMessage.debugMsg("Employeeid dao impl"+Employeeid);
		
		String sql= GenTlEmployeemstSql.getfunloclink(Employeeid,Funloclink);
		//return (List<String[]>) genTlEmployeemstsql;
		// TODO Auto-generated method stub
		//return null;
	    
		 List<String []> gridData = dbActionTemplate.getDataList(sql);
			return gridData ; 
	}

	@Override
	public List<String[]> getRoleEmpGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms = "";
		if(com.akranta.tpm.utils.CommonFunctions.isValidKeyId(commonFilter.getKey())){
			condParms="EMPMID="+commonFilter.getKey()+";";
		}
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.processFunctionCalls("GEN_FN_ROLEEMPFNLNLIST", paramValues);		
	}

	public List<String[]> empreport(CommonFilter commonFilter,GridParams gridparams)throws Exception{
		    
		    StringBuffer sb=new StringBuffer();
		    List<String> paramValues=new ArrayList<String>();		  
		    sb.append(getempmailreport());
		    String flid=commonFilter.getFlid();
      		 if(UIUtils.isValidKeyId(flid)){
      			sb.append(" AND flid IN (SELECT flid FROM gen_mv_flidhierarchy WHERE POSITION('"+flid+"' IN parentflids || flid) > 0)");
      			sb.append(" ORDER BY EMPM_NAME");
		    }
      		
      		 CommonMessage.debugMsg("The final Query"+sb.toString());
      		String conditionalparam=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		    String commonparam=FilterCondSql.getGridCommonParams(commonFilter);
		    paramValues.add(conditionalparam);
		    paramValues.add(commonparam);
		    String countSql=CommonFilterSqls.countSql(sb.toString(),gridparams.getGridFilters());
			String viewinfo=dbActionTemplate.getSingleValue(countSql);
		    long counts=Long.parseLong(viewinfo);
		    if( counts >0){
		    	String sql = CommonFilterSqls.addPaginationParams(sb.toString(),gridparams);
		    	gridparams.setTotalRecordCnt(counts);
		    	CommonMessage.debugMsg("The sql Data"+sql);
			    List<String[]> datacon=dbActionTemplate.getDataList(sql);
		    	return datacon;
		    }
		    throw new NoDataFoundException("No Data Found");
	}
	
	public Workbook getExcelreport(CommonFilter commonFilter, JSONObject tblJSONObj,
			String format) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			rs =gettasknoteRptResultSet(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			return excelUtils.writeToExcel(rs,format,0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	private ResultSet gettasknoteRptResultSet(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		List<String> paramValues = getFilterParamValue(commonFilter);
		StringBuffer sb=new StringBuffer();	  
	    sb.append(GenTlEmployeemstSql.getempmailreport());
	    String flid=commonFilter.getFlid();
  		 if(UIUtils.isValidKeyId(flid)){
  		sb.append("  AND flid IN (SELECT flid FROM gen_mv_flidhierarchy WHERE INSTR (parentflids || flid, '"+flid+"') >0)");
        sb.append(" order by EMPM_NAME");
	    }
	    return dbActionTemplate.getData(sb.toString());
	}
	private List<String> getFilterParamValue(CommonFilter commonFilter){
		// TODO Auto-generated method stub
		List<String> paramValues = new ArrayList<String>();
		String condParms ="";
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	}
public List<EmpmailreportModel>  updateEmail(List<EmpmailreportModel> empMailEnableList)throws Exception{
	
	 List<Object[]> valueList = new ArrayList<Object[]>();	 
	 String sql = "UPDATE GEN_TL_EMPLOYEEMST SET EMPM_EMAIL =?,EMPM_ENABLEEMAIL =? WHERE EMPM_KEYID =?";
	 CommonMessage.debugMsg("Update the sql"+sql);
	 for(EmpmailreportModel empmailreportModel : empMailEnableList)
	 {  
      Object[] value={empmailreportModel.getEmpmailid(),empmailreportModel.getEmpenablemail(),empmailreportModel.getEmpkeyid()};    
      valueList.add(value);
    }
  int [] dataTypes = { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR };
  dbActionTemplate.executeBatch(sql,valueList,dataTypes);
  return empMailEnableList;
}
public String EmployeeData(String empKeyid,String empName,String empPhoneNo,String empEmail) throws Exception{
    StringBuilder sql=new StringBuilder("UPDATE GEN_TL_EMPLOYEEMST SET EMPM_NAME='"+empName+"',EMPM_MOBILE='"+empPhoneNo+"', ");
    sql.append("EMPM_EMAIL='"+empEmail+"' WHERE EMPM_KEYID='"+empKeyid+"' ");
    CommonMessage.debugMsg("The Employee Data::"+sql.toString());    
   // insertEmployeeImages(imageName);
    //insertEmployeeImage(genTlEmployeemst);
	dbActionTemplate.executeStatement(sql.toString());
    return sql.toString();
}

  public GenTlEmployeemst imagecreate(GenTlEmployeemst genTlEmployeemst,String userkeyid) throws Exception {

   List<String> sqls = new ArrayList<String>();
	List<GenTlEmployeeimg> genTlEmployeeimgs = genTlEmployeemst.getEmployeeImg();
	if( genTlEmployeeimgs != null && genTlEmployeeimgs.size()> 0)
	for( GenTlEmployeeimg genTlEmployeeimg : genTlEmployeeimgs ){
		genTlEmployeeimg.setEmpiEmployeeid(userkeyid);
		sqls.add(GenTlEmployeeimgSql.getDeleteSql(genTlEmployeeimgSql.getEmpiDbFields(),genTlEmployeeimg.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
		
	}
	List<String> imgSqls = new ArrayList<String>();
	List<Object[]> valueList  = new ArrayList<Object[]>();
	List<int[]> dataTypes  = new ArrayList<int[]>();
	if(genTlEmployeemst.getEmployeeImg()!= null && genTlEmployeemst.getEmployeeImg().size()>0) // check for detail table data
	{	
		GenTlEmployeeimg employeeImage = (GenTlEmployeeimg)genTlEmployeemst.getEmployeeImg().get(0);
		imgSqls.add(GenTlEmployeedtlSql.getInsertSqlForEmp());
	
		java.sql.Timestamp  timeStamp = CommonFunctions.convertoSqlTimeStamp(employeeImage.getEmpiModifiedon()); 
		CommonMessage.debugMsg("Time Stamp....."+timeStamp);
		Object [] insValues = { employeeImage.getEmpiEmployeeid(),employeeImage.getEmpiBlobimage(),employeeImage.getEmpiBloblength(),
								employeeImage.getEmpiFilename(),timeStamp};
		int [] insDataType = { Types.VARCHAR, Types.BLOB,Types.INTEGER ,Types.VARCHAR,Types.TIMESTAMP};
	
	valueList.add(insValues);
	dataTypes.add(insDataType);
	dbActionTemplate.saveByteFile(imgSqls, valueList, dataTypes);
	}
	return genTlEmployeemst;
}
	@Override
	public List<String[]> getEmpData(String userKeyid) throws Exception {
		// TODO Auto-generated method stub
		/*String sql ="select empm_name ,empm_code,EMPM_TEMPFIELD4 as pillar from gen_tl_employeemst where empm_keyid = '"+userKeyid+"'";*/
	
		String sql ="select empm_name ,empm_code,empm_email,empm_mobile,'' as pillar from gen_tl_employeemst where empm_keyid = '"+userKeyid+"'";
		List<String[]> getEmployeeData = dbActionTemplate.getDataList(sql);
		return getEmployeeData;
	}
	public static String getempmailreport()	
	{ 
		StringBuffer sb=new StringBuffer();	
//		sb.append(" SELECT DISTINCT EMPM_KEYID as empm_keyid,decode(USRM_LOGINID,null,'-',USRM_LOGINID) as txtloginid,EMPM_EMPLOYEENUMBER AS txtempno ,EMPM_NAME AS empm_name,");
//		sb.append(" FNLN_DESCRIPTION AS fnln_description,DECODE(EMPM_EMAIL,'-','',EMPM_EMAIL,EMPM_EMAIL) AS txtEmpmailid,");
//	    sb.append(" EMPM_ENABLEEMAIL  AS EMPM_ENABLEEMAIL");
//		sb.append(" FROM GEN_TL_EMPLOYEEMST,adm_tl_usermst,GEN_MV_FLIDHIERARCHY,GEN_TL_FNLNROLETEAM");
//		sb.append(" WHERE empm_keyid=FRT_EMPM_KEYID");
//		sb.append(" AND usrm_ccno(+)= empm_keyid");
//		//sb.append(" AND FLID=FRT_FNLN_KEYID AND EMPM_ACTIVE='Y' AND USRM_ISACTIVE='Y'");
//		sb.append(" AND FLID=FRT_FNLN_KEYID AND EMPM_ACTIVE='Y'");
		sb.append(" SELECT DISTINCT e.empm_keyid AS empm_keyid, ");
		sb.append(" CASE WHEN u.usrm_loginid IS NULL THEN '-' ELSE u.usrm_loginid END AS txtloginid, ");
		sb.append(" e.empm_employeenumber AS txtempno, ");
		sb.append(" e.empm_name AS empm_name, ");
		sb.append(" fh.fnln_description AS fnln_description, ");
		sb.append(" CASE WHEN e.empm_email = '-' THEN '' ELSE e.empm_email END AS txtEmpmailid, ");
		sb.append(" e.empm_enableemail AS empm_enableemail ");

		sb.append(" FROM gen_tl_employeemst e ");

		sb.append(" JOIN gen_tl_fnlnroleteam rt ON e.empm_keyid = rt.frt_empm_keyid ");
		sb.append(" JOIN gen_mv_flidhierarchy fh ON fh.flid = rt.frt_fnln_keyid ");

		sb.append(" LEFT JOIN adm_tl_usermst u ON u.usrm_ccno = e.empm_keyid ");

		sb.append(" WHERE e.empm_active = 'Y' ");
	    return sb.toString();
	}
}

