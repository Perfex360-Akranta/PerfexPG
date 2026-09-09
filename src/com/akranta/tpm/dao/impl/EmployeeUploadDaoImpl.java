package com.akranta.tpm.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EmployeeUploadDao;
import com.akranta.tpm.dao.sql.AdmTlRoleMenuLinkSql;
import com.akranta.tpm.dao.sql.AdmTlUsermstSql;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.DcmTlDocumentmanagerSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.AdmTlRoleMenuLink;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.DcmTlDocumentmanager;
import com.akranta.tpm.model.GenTlEmployeemstUpl;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class EmployeeUploadDaoImpl implements EmployeeUploadDao{
	private DBActionTemplate dbActionTemplate;
	private static final String REPORT_FORMAT_EXL_2007 = "xlsx";

	private CommonFilterDao commonFilterdao;
	public EmployeeUploadDaoImpl(DBActionTemplate dbActionTemplate) {
	  commonFilterdao = new CommonFilterDaoImpl(dbActionTemplate);
		
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public List<String[]> getEmployeeList(CommonFilter commonFilter)throws Exception{
		try
		{
			
			CommonMessage.debugMsg("Inside dao");
			List<String> paramValues = new ArrayList<String>();
			String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =  null;
			dataList =  dbActionTemplate.processFunctionCalls("GEN_TL_EMPLOYEEUPLLIST",paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt....."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}		
	}
	
	public String EmployeeUploadDelete(String errflag) throws Exception{
		
		String DelSql="DELETE FROM GEN_TL_EMPLOYEEMSTUPL";
		dbActionTemplate.executeStatement(DelSql);
		return DelSql.toString();
	}

	
    public String EmployeeUploadValidate(String errflag) throws Exception{
    
    	List<String> updatesql=new ArrayList<String>();
    	updatesql.add(" UPDATE GEN_TL_EMPLOYEEMSTUPL SET EMPU_ERRORFLAG='"+errflag+"',EMPU_ERRORMSG='Code Already Exists' WHERE EMPU_CODE IN(SELECT EMPM_CODE FROM GEN_TL_EMPLOYEEMST) ");
        //CommonMessage.debugMsg("Update Sql::"+updatesql);
        dbActionTemplate.executeStatements(updatesql);
        CommonMessage.debugMsg("After Update::");
        UpdateLocationFlag(errflag);
        UpdateDepartmentFlag(errflag);
        //UpdateDesignationFlag(errflag);
        //ValidateDuplicateRecord(errflag);
        return errflag;
    }
    
    public String UpdateLocationFlag(String errflag) throws Exception{
    	List<String> updatesql=new ArrayList<String>();
    	updatesql.add(" UPDATE GEN_TL_EMPLOYEEMSTUPL SET EMPU_ERRORFLAG='"+errflag+"', EMPU_ERRORMSG=(CASE WHEN length(EMPU_ERRORMSG)>1 THEN EMPU_ERRORMSG|| ', ' else '' end ) || 'Location Code Invalid' WHERE EMPU_LOCATIONCODE NOT IN(SELECT LOCN_CODE FROM GEN_TL_LOCATIONMST) ");
        CommonMessage.debugMsg("Update Location Sql::"+updatesql);
        dbActionTemplate.executeStatements(updatesql);
        return errflag;   
    }
    
    public String UpdateDepartmentFlag(String errflag) throws Exception{
    	List<String> updatesql=new ArrayList<String>();
    	updatesql.add(" UPDATE GEN_TL_EMPLOYEEMSTUPL SET EMPU_ERRORFLAG='"+errflag+"', EMPU_ERRORMSG=(CASE WHEN length(EMPU_ERRORMSG)>1 THEN EMPU_ERRORMSG|| ', ' else '' end ) || 'Department Code Invalid' WHERE EMPU_DEPARTMENTCODE NOT IN(SELECT DEPT_CODE FROM GEN_TL_DEPARTMENTMST) ");
        CommonMessage.debugMsg("Update Sql::"+updatesql);
        dbActionTemplate.executeStatements(updatesql);
        return errflag;   
    }
    
    public String UpdateDesignationFlag(String errflag) throws Exception{
    	List<String> updatesql=new ArrayList<String>();
    	updatesql.add(" UPDATE GEN_TL_EMPLOYEEMSTUPL SET EMPU_ERRORFLAG='"+errflag+"', EMPU_ERRORMSG=(CASE WHEN length(EMPU_ERRORMSG)>1 THEN EMPU_ERRORMSG|| ', ' else '' end ) || 'Designation Code Invalid' WHERE EMPU_DESIGNATIONCODE NOT IN(SELECT DESG_NAME FROM GEN_TL_DESIGNATIONMST) ");
        CommonMessage.debugMsg("Update Sql::"+updatesql);
        dbActionTemplate.executeStatements(updatesql);
        return errflag;   
    }
    
    public String ErrorFlagCount() throws Exception{
     String Count=dbActionTemplate.getSingleValue("SELECT COUNT(*) FROM GEN_TL_EMPLOYEEMSTUPL WHERE EMPU_ERRORFLAG='Y' ");
     CommonMessage.debugMsg("Count"+Count);
     return  Count; 
     
    }

	
	@Override
	public String populateTempTable(String excelFileName,
			GenTlEmployeemstUpl genTlEmployeemstUpl) throws UploadException {

		int [] colDataTypes = {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR ,Types.VARCHAR,
				Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		FileInputStream file = null;
		Sheet sheet = null;
		
		Workbook workbook = null;
		StringBuilder sql =new StringBuilder();
		sql.append( " INSERT INTO GEN_TL_EMPLOYEEMSTUPL(EMPU_KEYID,EMPU_EXCELROWNO,EMPU_NAME,EMPU_CODE,EMPU_EMPLOYEETYPE,EMPU_EMPLOYEENUMBER, ");
		sql.append( "EMPU_COMPANYID,EMPU_COMPANYCODE,EMPU_LOCATIONID,EMPU_LOCATIONCODE,EMPU_CELLID,EMPU_SECTIONID,EMPU_LOGINID,EMPU_DEFAULTPASSWORD, ");
		sql.append("EMPU_ROLE1,EMPU_ROLE2,EMPU_ROLE3,EMPU_ROLE4,EMPU_ROLE5,EMPU_ROLE6, ");
		sql.append("EMPU_ROLE7,EMPU_ROLE8,EMPU_ROLE9,EMPU_ROLE10,EMPU_JOINEDDATE,EMPU_DEPARTMENTID, ");
		sql.append("EMPU_DEPARTMENTCODE,EMPU_DESIGNATIONID,EMPU_DESIGNATIONCODE,EMPU_FACTORYID,EMPU_ISSHIFTINCHARGE, ");
		sql.append("EMPU_ISCELLMANAGER,EMPU_TRADEID,EMPU_EXTENSIONPHONE,EMPU_GENDER,EMPU_MOBILE,EMPU_EMAIL, ");
		sql.append("EMPU_PERSONALINFO,EMPU_REMARKS,EMPU_ISSECTIONMANAGER,EMPU_SKILLCATEGORY,EMPU_GRADEID, ");
		sql.append("EMPU_ISOPERATOR,EMPU_SBUID,EMPU_ENABLEEMAIL,EMPU_ACTIVE,EMPU_CREATEDBY,EMPU_CREATEDON, ");
		sql.append("EMPU_MODIFIEDON,EMPU_ERRORFLAG,EMPU_ERRORMSG) VALUES( ");
		sql.append( " ?,?,?,?,?,?,'"+genTlEmployeemstUpl.getEmpuCompanyid()+"',?,'"+genTlEmployeemstUpl.getEmpuLocationid()+"',?,'"+genTlEmployeemstUpl.getEmpuCellid()+"','"+genTlEmployeemstUpl.getEmpuSectionid()+"',?,?,'"+genTlEmployeemstUpl.getEmpuRoleid1()+"', ");
		sql.append("'"+genTlEmployeemstUpl.getEmpuRoleid2()+"','"+genTlEmployeemstUpl.getEmpuRoleid3()+"','"+genTlEmployeemstUpl.getEmpuRoleid4()+"',  ");
		sql.append("'"+genTlEmployeemstUpl.getEmpuRoleid5()+"','"+genTlEmployeemstUpl.getEmpuRoleid6()+"','"+genTlEmployeemstUpl.getEmpuRoleid7()+"', ");
		sql.append("'"+genTlEmployeemstUpl.getEmpuRoleid8()+"','"+genTlEmployeemstUpl.getEmpuRoleid9()+"','"+genTlEmployeemstUpl.getEmpuRoleid10()+"', ");
		sql.append("  to_date(?,'dd-Mon-yyyy hh24:mi:ss'),'"+genTlEmployeemstUpl.getEmpuDepartmentid()+"',?,'"+genTlEmployeemstUpl.getEmpuDesignationid()+"',?,'"+genTlEmployeemstUpl.getEmpuFactoryid()+"','"+genTlEmployeemstUpl.getEmpuIsshiftincharge()+"','"+genTlEmployeemstUpl.getEmpuIscellmanager()+"', ");
		sql.append("'"+genTlEmployeemstUpl.getEmpuTradeid()+"','"+genTlEmployeemstUpl.getEmpuExtensionphone()+"',?,?,?, ");
		sql.append(" '"+genTlEmployeemstUpl.getEmpuPersonalinfo()+"','"+genTlEmployeemstUpl.getEmpuRemarks()+"','"+genTlEmployeemstUpl.getEmpuIssectionmanager()+"', ");
		sql.append(" '"+genTlEmployeemstUpl.getEmpuSkillcategory()+"','"+genTlEmployeemstUpl.getEmpuGradeid()+"','"+genTlEmployeemstUpl.getEmpuIsoperator()+"', ");
		sql.append(" '"+genTlEmployeemstUpl.getEmpuSbuId()+"','"+genTlEmployeemstUpl.getEmpuenablemail()+"','"+genTlEmployeemstUpl.getEmpuActive()+"', ");
		sql.append( "'"+genTlEmployeemstUpl.getEmpuCreatedby()+"', to_date( '"+genTlEmployeemstUpl.getEmpuCreatedon()+"','dd-Mon-yyyy hh24:mi:ss'),");
		sql.append( "to_date('"+genTlEmployeemstUpl.getEmpuModifiedon()+"','dd-Mon-yyyy hh24:mi:ss'),'"+genTlEmployeemstUpl.getEmpuErrorflag()+"','"+genTlEmployeemstUpl.getEmpuErrormsg()+"' ) ");
		try{
			file = new FileInputStream(new File(excelFileName));     
			CommonMessage.debugMsg(file +  "  file");
			workbook =  excelFileName.endsWith(REPORT_FORMAT_EXL_2007) ? new XSSFWorkbook(file):new HSSFWorkbook(file);
			//String Sheet1 = "Sheet1";
			sheet = workbook.getSheetAt(0);
			CommonMessage.debugMsg(sheet.getLastRowNum() +  "  file");
			CommonMessage.debugMsg(workbook +  " workbook");
			int fromRow = 0;
			int batch = 1000; 
			int batchCount = 1; 
			while(true){ 
					
				List<Object[]> dataList = new ArrayList<Object[]>();
				CommonMessage.debugMsg(" fromRow " + fromRow + " to row " + (fromRow + batch)+" sheet" + sheet.getLastRowNum());
				if( ! convertExcelRowstoList(sheet,fromRow+1,(fromRow + batch),15,dataList) ){
					CommonMessage.debugMsg("No data");
					return "No";
				}else
				{
					CommonMessage.debugMsg("dataList.size()  "+dataList.size());
					if(dataList.size()>0){
						CommonMessage.debugMsg("Data List");
						dbActionTemplate.executeBatch(sql.toString(), dataList, colDataTypes);
						fromRow = (fromRow + batch);
						dataList = null;
						batchCount++;
						UpdateTempTable();
						return excelFileName;
					}else
						return "No";
				}
			}
		}catch(Exception e){
			throw new UploadException(e.getMessage());
		}
	}
	
	private void UpdateTempTable() throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE GEN_TL_EMPLOYEEMSTUPL SET EMPU_COMPANYID=(SELECT COMP_KEYID FROM GEN_TL_COMPANYMST)");
		CommonMessage.debugMsg("Update Company"+sql);
		dbActionTemplate.executeStatement(sql.toString());
		UpdateLocation();
		UpdateDepartment();
		UpdateDesignation();
	}
	
	private void UpdateLocation() throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE GEN_TL_EMPLOYEEMSTUPL SET EMPU_LOCATIONID=(SELECT LOCN_KEYID FROM GEN_TL_LOCATIONMST WHERE EMPU_LOCATIONCODE=LOCN_CODE)");
		CommonMessage.debugMsg("Update Location"+sql);
		dbActionTemplate.executeStatement(sql.toString());
	}
	
	
	private void UpdateDepartment() throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE GEN_TL_EMPLOYEEMSTUPL SET EMPU_DEPARTMENTID=(SELECT DEPT_KEYID FROM GEN_TL_DEPARTMENTMST WHERE EMPU_DEPARTMENTCODE=DEPT_CODE)");
		CommonMessage.debugMsg("Update Department"+sql);
		dbActionTemplate.executeStatement(sql.toString());
	
	}
	
	private void UpdateDesignation() throws Exception{
		StringBuilder sql=new StringBuilder();
		sql.append("UPDATE GEN_TL_EMPLOYEEMSTUPL SET EMPU_DESIGNATIONID=(SELECT DESG_KEYID FROM GEN_TL_DESIGNATIONMST WHERE EMPU_DESIGNATIONCODE=DESG_CODE)");
		CommonMessage.debugMsg("Update Designation"+sql);
		dbActionTemplate.executeStatement(sql.toString());
	
	}
	
	private boolean convertExcelRowstoList(Sheet sheet, int fromRow, int toRow,
			int noCols, List<Object[]> dataList) {
		try {
			CommonMessage.debugMsg(noCols+"  noCols  "+fromRow+"  fromRow  "+ toRow+" row num " + sheet.getLastRowNum());
		    if( fromRow > sheet.getLastRowNum()  )
		    	return false;
			
		    toRow =  sheet.getLastRowNum() > toRow ?toRow:  sheet.getLastRowNum();
		    CommonMessage.debugMsg(toRow + " torow");
		    GenSequenceNumber key=new GenSequenceNumber(dbActionTemplate.getDataSource().getConnection(), "GEN_TL_EMPLOYEEMSTUPL",10, "EMPU", "", "");
            for(int rowNo = (fromRow);rowNo<=toRow;rowNo++){      
            	CommonMessage.debugMsg(" aftr for loop" + rowNo);
            	String Keyid =key.getSequnceNumber();//dbActionTemplate.getSequenceNumber(PcsTlOtherlossentrySql.TBL_PCS_TL_OTHERLOSSENTRY,10,"OLS","",""); // set the sequnce number
            	CommonMessage.debugMsg(Keyid + " keyid");
             	String[] row = new String[ noCols + 1];
                int colNo = 0 ;
                row[colNo++] = Keyid;//Add primary keyid
                
                for(; colNo< (noCols+1);colNo++){ 
                	Cell cell = null;	               
		        	cell = sheet.getRow(rowNo).getCell(colNo-1);
                    switch(cell.getCellType()) {		                    
                        case BOOLEAN:
                            if (cell.getBooleanCellValue()) 
                            	row[ colNo ]="1";                            
                            else
                            	row[ colNo ]="0";
                            
                            row[ colNo ] = (row[ colNo ] != null ? row[ colNo ]:"");
                            break;
                        case FORMULA:
                            row[ colNo ] = Double.toString(cell.getNumericCellValue());
                            row[ colNo ] = (row[ colNo ] != null ? row[ colNo ]:"");
                            break;
                        case NUMERIC:
                        	if( DateUtil.isCellDateFormatted(cell)){
                        		Date date = cell.getDateCellValue();
                        		if( date != null ){
	                        		String format = cell.getCellStyle().getDataFormatString();
	                        		format = format.replaceAll("\\\\-", "-").replace("mmm", "MMM");
	                        		SimpleDateFormat dFormat = new SimpleDateFormat(format,Locale.ENGLISH);
	                        	CommonMessage.debugMsg("dFormat.format(date) " + dFormat.format(date));
	                        		String datVal=dFormat.format(date).replace("[$-409]", "");
	                        		row[ colNo ] = datVal.replace("@", "");
                        		}	
                        		CommonMessage.debugMsg("row[ colNo ] " + row[ colNo ]);
                        		//row[ colNo ] =  +"";
                        	}
                        	else{
                        		cell.setCellType(CellType.STRING);
                        		//row[ colNo ] = Double.toString(cell.getNumericCellValue());
	                            row[ colNo ] = cell.getStringCellValue();
                        	}
                        	row[ colNo ] = (row[ colNo ] != null ? row[ colNo ]:"");
                            break;
                        case STRING:
                            row[ colNo ] = cell.getStringCellValue();
    						row[ colNo ] = (row[ colNo ] != null ? row[ colNo ].replace("'", "''").replace("{}", "").replace("<**>","").replace("<*", "").replace("*>", ""):"");
    						row[ colNo ] = (row[ colNo ] == null ? "{}":row[ colNo ]);
                            break;
                        case ERROR:
                        	row[ colNo ] ="";
                        	break;
                        case BLANK:
                        	row[ colNo ] ="{}";
                        	break;
                	}
                    CommonMessage.debugMsg(cell.getCellType()+"row[ " + colNo + " ]: " + row[colNo ]);
                }	
               
                dataList.add(row);
            }
            key.closeConnection();
            
          //  file.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		 return true;
	}
	
	public String EmployeeCreate(String errflag) throws Exception{
		StringBuilder sql =new StringBuilder();	
		sql.append("INSERT INTO GEN_TL_EMPLOYEEMST(EMPM_KEYID,EMPM_NAME,EMPM_CODE,EMPM_EMPLOYEETYPE,EMPM_EMPLOYEENUMBER,EMPM_JOINEDDATE, ");
		sql.append("EMPM_DEPARTMENTID,EMPM_DESIGNATIONID,EMPM_FACTORYID,EMPM_ISSHIFTINCHARGE,EMPM_SECTIONID,EMPM_ISCELLMANAGER, ");
		sql.append("EMPM_CELLID,EMPM_TRADEID,EMPM_EXTENSIONPHONE,EMPM_GENDER,EMPM_MOBILE,EMPM_EMAIL,EMPM_PERSONALINFO, ");
		sql.append("EMPM_REMARKS,EMPM_ISSECTIONMANAGER,EMPM_SKILLCATEGORY,EMPM_GRADEID,EMPM_ISOPERATOR,EMPM_COMPANY, ");
		sql.append("EMPM_ROLEID,EMPM_SBUID,EMPM_ENABLEEMAIL,EMPM_LOCATION,EMPM_ACTIVE,EMPM_CREATEDBY,EMPM_CREATEDON,EMPM_MODIFIEDON) ");
		sql.append("SELECT EMPU_KEYID,EMPU_NAME,EMPU_CODE,EMPU_EMPLOYEETYPE,EMPU_EMPLOYEENUMBER,EMPU_JOINEDDATE, ");
		sql.append("EMPU_DEPARTMENTID,EMPU_DESIGNATIONID,EMPU_FACTORYID,EMPU_ISSHIFTINCHARGE,EMPU_SECTIONID, ");
		sql.append("EMPU_ISCELLMANAGER,EMPU_CELLID,EMPU_TRADEID,EMPU_EXTENSIONPHONE,EMPU_GENDER,EMPU_MOBILE,EMPU_EMAIL, ");
		sql.append("EMPU_PERSONALINFO,EMPU_REMARKS,EMPU_ISSECTIONMANAGER,EMPU_SKILLCATEGORY,EMPU_GRADEID,EMPU_ISOPERATOR, ");
		sql.append("EMPU_COMPANYID,EMPU_ROLE1,EMPU_SBUID,EMPU_ENABLEEMAIL,EMPU_LOCATIONID,EMPU_ACTIVE,EMPU_CREATEDBY, ");
		sql.append("EMPU_CREATEDON,EMPU_MODIFIEDON FROM GEN_TL_EMPLOYEEMSTUPL WHERE EMPU_ERRORFLAG='"+errflag+"' ");
		CommonMessage.debugMsg("Insert Query"+sql);
		dbActionTemplate.executeStatement(sql.toString());
		UserCreate(errflag);
		return sql.toString();	
	}
	
	public String UserCreate(String errflag) throws Exception{	
		StringBuilder sql =new StringBuilder();
		String CurrDate=CommonFunctions.getDate();
		CommonMessage.debugMsg("CurrentDate"+CurrDate);
		sql.append("INSERT INTO ADM_TL_USERMST(USRM_KEYID,USRM_USERPIN,USRM_USERNAME,USRM_CCNO,USRM_LOGINID, ");
		sql.append("USRM_PASSWORD,USRM_DEFAULTPASSWORD,USRM_SECURITYPOLICYID,USRM_DESIGNATIONID,USRM_DEPARTMENTID, ");
		sql.append("USRM_EXTENSIONPHONE,USRM_LASTPWDCHANGED,USRM_LASTLOGINDATE,USRM_ISUSERLOCKED,USRM_ISACTIVE, ");
		sql.append("USRM_ISADMINISTARTOR,USRM_ISPWDLOCKENABLED,USRM_ISTEMPLATEUSER,USRM_REMARKS,USRM_LOGINATTEMPT, ");
		sql.append("USRM_ISVALIDITYREQ,USRM_VALIDFROM,USRM_VALIDTILL,USRM_CREATEDBY,USRM_CREATEDON,USRM_MODIFIEDON) ");
		sql.append("SELECT EMPU_KEYID,SUBSTR(EMPU_KEYID,-2,2),EMPU_NAME,EMPU_KEYID,EMPU_LOGINID, ");
		sql.append("EMPU_DEFAULTPASSWORD,EMPU_DEFAULTPASSWORD,'SPF0001',EMPU_DESIGNATIONID,EMPU_DEPARTMENTID, ");
		sql.append("'15','"+CurrDate+"','"+CurrDate+"','N','Y',");
	  	sql.append("'N','N','N','-',0,");
	  	sql.append("'N','"+CurrDate+"','"+CurrDate+"',EMPU_CREATEDBY,EMPU_CREATEDON,EMPU_MODIFIEDON ");
	  	sql.append("FROM GEN_TL_EMPLOYEEMSTUPL WHERE EMPU_ERRORFLAG='"+errflag+"' ");
	  	CommonMessage.debugMsg("Insert User Query"+sql);
	  	dbActionTemplate.executeStatement(sql.toString());
	  
	  	//RemoveEmpUplData();
	  	return sql.toString();
	}
	
	@Override
	public void resetpwd(List<String> loginIds) throws Exception {

		    ArrayList<String> sqls=new ArrayList<String>();
			for(String loginId:loginIds){
				StringBuffer sql =new StringBuffer();
				CommonMessage.debugMsg("loginId"+loginId);
				AdmTlUsermst admTlUsermst =  getUserByLoginID(loginId);
				String password = encriptPassword(admTlUsermst.getUsrm_loginid(),Integer.parseInt(admTlUsermst.getUsrm_userpin()));			
				sql.append("UPDATE ADM_TL_USERMST SET USRM_PASSWORD = '" + password + "' , USRM_DEFAULTPASSWORD = '" + password + "' , USRM_LOGINATTEMPT = 0 , USRM_ISUSERLOCKED = 'N' , USRM_ISACTIVE='Y' WHERE USRM_LOGINID='"+loginId+"'");
				CommonMessage.debugMsg("sql :" +sql);
				sqls.add(sql.toString());
				
			}
			dbActionTemplate.executeStatements(sqls);	
			
		}
	
/*	public List<String> UpdateUserPassword() throws Exception{
		ArrayList<String> sqls=new ArrayList<String>();
		String loginId=null;
        StringBuffer sql =new StringBuffer();
		CommonMessage.debugMsg("loginId"+loginId);
		AdmTlUsermst admTlUsermst =  getUserByLoginID();
		String password = encriptPassword(admTlUsermst.getUsrm_loginid(),Integer.parseInt(admTlUsermst.getUsrm_userpin()));			
		sql.append("UPDATE ADM_TL_USERMST SET USRM_PASSWORD = '" + password + "' , USRM_DEFAULTPASSWORD = '" + password + "' , USRM_LOGINATTEMPT = 0 , USRM_ISUSERLOCKED = 'N' , USRM_ISACTIVE='Y' WHERE USRM_EXTENSIONPHONE=15");
		CommonMessage.debugMsg("sql :" +sql);
		sqls.add(sql.toString());
		dbActionTemplate.executeStatements(sqls);
		return sqls;	
	}*/
	
	public AdmTlUsermst getUserByLoginID(String loginId ) throws NoDataFoundException, SQLException, Exception
	{
		
		String sql = AdmTlUsermstSql.getUserSqlByLoginId();
		CommonMessage.debugMsg("The Sql::"+sql);
		Object args[] = new Object [] {loginId.toUpperCase()};
		
		AdmTlUsermst admTlUsermst = new AdmTlUsermst() ;
		
		admTlUsermst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
		return admTlUsermst;

	}
	
	public  String encriptPassword(String  password, int userPin) throws BusinessApplicationExceptions
	{
		int tempUserPin =  getFormatedUserPin(userPin);

		String encriptPass ="";
		for(int i=0; i< password.length(); i++){
			int sum = password.charAt(i) + tempUserPin;
			encriptPass  +=  (sum) < 128 ? (char)(sum): (char) (32+(sum-128)+ tempUserPin);		
		}
		return encriptPass;

	}
	
	private int getFormatedUserPin(int userPin) throws BusinessApplicationExceptions 
	{
		NumberFormat formatter = new DecimalFormat("0000");
		String pin = formatter.format(userPin); 
		
		
		if( Integer.parseInt(pin) == 0)
			throw new BusinessApplicationExceptions("tpmusr-000005"); // invalid user pin
		
		int digit = 0, sum = 0;

		for( int i = 0 ; i < pin.length(); i++ )
		{	
			digit = (int)pin.charAt(i) ;
			sum +=  digit;
		}
		String sumStr = Integer.toString(sum);

		do{
			sum = 0;
			for( int i = 0; i < sumStr.length(); i++ )
			{
				digit = (int)(sumStr.charAt(i)-'0');
				sum += digit;
			}
			sumStr = Integer.toString(sum);
		}while( sum > 9 );	
		return sum; 
	}

	
  public String RemoveEmpUplData() throws Exception{
	  String sql="DELETE FROM GEN_TL_EMPLOYEEMSTUPL";
	  dbActionTemplate.executeStatement(sql);
	  return sql;
  }

@Override
public List<String[]> ActiveMenuList(CommonFilter commonFilter,GridParams gridParams) throws Exception {
	
	
	StringBuffer sql= new StringBuffer();
	  String RoleId=commonFilter.getKey();
	  CommonMessage.debugMsg("RoleId"+RoleId);
	  String MenuId=commonFilter.getEmpch();
	  String RootId=commonFilter.getRefdocid();
	  CommonMessage.debugMsg("MenuId"+MenuId);
	  CommonMessage.debugMsg("RootId"+RootId);
	sql.append(GenTlEmployeemstUpl.getMenuList(RoleId,MenuId,RootId));
	List<String> paramValues = new ArrayList<String>();
		
	String conditionalparam=FilterCondSql.getAbnRelatedConditionStr(commonFilter);
    String commonparam=FilterCondSql.getGridCommonParams(commonFilter);
    paramValues.add(conditionalparam);
    paramValues.add(commonparam);
    String countSql=CommonFilterSqls.countSql(sql.toString(),gridParams.getGridFilters());
	String viewinfo=dbActionTemplate.getSingleValue(countSql);
    long counts=Long.parseLong(viewinfo);
    if( counts >0){
    	String sb = CommonFilterSqls.addPaginationParams(sql.toString(),gridParams);
    	gridParams.setTotalRecordCnt(counts);
    	CommonMessage.debugMsg("The sql Data"+sql);
	    List<String[]> datacon=dbActionTemplate.getDataList(sb);
    	return datacon;
    }
    throw new NoDataFoundException("No Data Found");
	 
}

public List<AdmTlRoleMenuLink> createBasis(List<AdmTlRoleMenuLink> employeeAddList, String RoleId) throws Exception{

    List<String> sqls = new ArrayList<String>();
    List <AdmTlRoleMenuLink> methodslist = employeeAddList;
    AdmTlRoleMenuLinkSql admTlRoleMenuLinkSql=new AdmTlRoleMenuLinkSql();
    try
    {
    String dateTime = CommonFunctions.dateTimeNow();
    CommonMessage.debugMsg("Date Time"+dateTime);
   // GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource(),MocRfcBasismstSql.TBL_MOC_TL_RFCBASIS,14,"RFCB","","");
	// CommonMessage.debugMsg("sequenceNumber in IF"+sequenceNumber);
	 for(AdmTlRoleMenuLink CreatedescLink:methodslist)
	 {
		    CommonMessage.debugMsg("Inside the for");
			//String seqNo=sequenceNumber.getSequnceNumber();
		//	CommonMessage.debugMsg("The seqNo::::"+seqNo);
            
			CreatedescLink.setArmlRoleid(RoleId);
			CreatedescLink.setArmlActive("Y");
			CreatedescLink.setArmlCreatedby("EMP0001");
			CreatedescLink.setArmlCreatedon(dateTime);
			CreatedescLink.setArmlModifiedon(dateTime);
			sqls.add(AdmTlRoleMenuLinkSql.getInsertSql(admTlRoleMenuLinkSql.getArmlDbFields(), CreatedescLink.getSaveArray())); // add insert sql for master table
	        CommonMessage.debugMsg("The sqls Query:"+sqls);
	 }
	 dbActionTemplate.executeStatements(sqls);
	// popSqlsForDescData(detlKeyid); 
}
catch(BusinessApplicationExceptions e)
{
CommonMessage.debugMsg("Business Application   :"+e.getMessage());
throw new BusinessApplicationExceptions(e.getMessage()); 
}
return employeeAddList;
	
	
}

@Override
public List<String[]> getEmpData(GridParams gridParams, String location) throws Exception {
	// TODO Auto-generated method stub
                   
	
	String sql = getLocEmpMasterGrid(location);
	
	String outerSql="select * from("+sql.toString()+")where 1=1 ";	
	if(gridParams.getGridFilters()!=null)
		outerSql+=FilterCondSql.makeGridFilterCond(gridParams.getGridFilters());
	String count="select count(*) from("+outerSql+")";
	CommonMessage.debugMsg("count in daoimpl"+count);
	String rowCount=dbActionTemplate.getSingleValue(count);
	CommonMessage.debugMsg("rowCount in daoimpl"+rowCount);
	long counts=Long.parseLong(rowCount);
	gridParams.setTotalRecordCnt(counts);
	List<String> params= new ArrayList<String>();
	params.add(gridParams.getFromRow());
	
	params.add(gridParams.getToRow());
	String finalSql="SELECT  * from ( select ROWNUM as slno, a.* from ("+outerSql+") a ) where slno >= ? and slno <= ?";
	CommonMessage.debugMsg("finalSql in Dao"+finalSql);
	List<String[]> result=dbActionTemplate.getDataList(finalSql, params);
	
	return result;
	
}

public String getLocEmpMasterGrid(String location) {
	
	StringBuffer sql =new StringBuffer();
	
	sql.append(" Select '-' as CHKBOX ,EMPM_KEYID, EMPM_NAME AS EMPLOYEENAME,EMPM_CODE AS EMPLOYEENUMBER,LOCN_NAME AS EMPLOCATION, EMPM_EMPLOYEETYPE AS EMPTYPE,EMPM_JOINEDDATE AS JDATE ");
	sql.append(" FROM GEN_TL_EMPLOYEEMST, GEN_TL_LOCATIONMST ");
	sql.append(" WHERE LOCN_KEYID(+)=EMPM_LOCATION ");
	if(UIUtils.isValidKeyId(location))
	    sql.append(" AND EMPM_LOCATION='"+location+"'");
	
	sql.append(" order by EMPM_NAME");
	CommonMessage.debugMsg("sql in loc Employee"+sql.toString());
	return sql.toString();
}

@Override
public void EmpActive(List<String> EmpIds) throws Exception {
	    ArrayList<String> sqls=new ArrayList<String>();
		for(String loginId:EmpIds){
			StringBuffer sql =new StringBuffer();			
			//GenTlEmployeemst employeemst =  getUserByLogin(loginId);
			String Active="Y";
			sql.append("UPDATE GEN_TL_EMPLOYEEMST SET EMPM_ACTIVE = '" + Active + "' , WHERE EMPM_KEYID='"+loginId+"'");
			CommonMessage.debugMsg("sql :" +sql);
			sqls.add(sql.toString());
		}
		dbActionTemplate.executeStatements(sqls);		
	}

@Override
public void EmpInActive(List<String> EmpIds,String ValidTillDate) throws Exception {
	    ArrayList<String> sqls=new ArrayList<String>();
	    StringBuffer sql =new StringBuffer();
	    String DateTime=CommonFunctions.dateTimeNow();
	    String Date=CommonFunctions.getDate();
	    String ConDate=Date.substring(1,11);
	    CommonMessage.debugMsg("CurrentDate"+Date+"ConDate"+ConDate);
	    CommonMessage.debugMsg("DateTime"+DateTime);
	    CommonMessage.debugMsg("ValidTillDate:::"+ValidTillDate);
	    
		for(String loginId:EmpIds){
             if(ConDate.equals(ValidTillDate)){
			CommonMessage.debugMsg("Inside Date Equals");			
			String Active="N";
			sql.append("UPDATE GEN_TL_EMPLOYEEMST SET EMPM_ACTIVE = '" + Active + "'  WHERE EMPM_KEYID='"+loginId+"'");
			CommonMessage.debugMsg("sql :" +sql);
			sqls.add(sql.toString());
			getUserInactiveUpdate(loginId,ValidTillDate);
			}
             else{
            	    CommonMessage.debugMsg("Else Called");
            		String Active="N";
        			sql.append("UPDATE GEN_TL_EMPLOYEEMST SET EMPM_ACTIVE = '" + Active + "'  WHERE EMPM_KEYID='"+loginId+"'");
        			CommonMessage.debugMsg("sql :" +sql);
        			sqls.add(sql.toString());
        			getUserInactiveValidDateUpdate(loginId,ValidTillDate);
             }
			
			
		}
		dbActionTemplate.executeStatements(sqls);		
	}

public String getUserInactiveValidDateUpdate(String loginId,String ValidTillDate) throws NoDataFoundException, SQLException, Exception{
	CommonMessage.debugMsg("Inside GetUserByLogin Method");
	StringBuffer sql=new StringBuffer();
	String Remarks="LWD SET ON-"+ValidTillDate;
	sql.append("UPDATE ADM_TL_USERMST SET USRM_ISACTIVE='N',USRM_ISVALIDITYREQ='Y',USRM_VALIDTILL=TO_DATE('"+ValidTillDate+"','DD-MON-YYYY'),USRM_REMARKS='"+Remarks+"' WHERE USRM_CCNO='"+loginId+"' ");
	CommonMessage.debugMsg("The User Master Sql:"+sql);
 //   return sql.toString();	
	dbActionTemplate.executeStatement(sql.toString());
	return loginId;
}
 

 public String getUserInactiveUpdate(String loginId,String ValidTillDate) throws NoDataFoundException, SQLException, Exception{
	CommonMessage.debugMsg("Inside GetUserByLogin Method");
	StringBuffer sql=new StringBuffer();
	String Remarks="LWD SET ON-"+ValidTillDate;
	sql.append("UPDATE ADM_TL_USERMST SET USRM_ISACTIVE='N',USRM_ISVALIDITYREQ='Y',USRM_VALIDTILL=TO_DATE('"+ValidTillDate+"','DD-MON-YYYY'),USRM_REMARKS='"+Remarks+"' WHERE USRM_CCNO='"+loginId+"' ");
	CommonMessage.debugMsg("The User Master Sql:"+sql);
 //   return sql.toString();	
	dbActionTemplate.executeStatement(sql.toString());
	getDeleteTradeLink(loginId);
	return loginId;
}
 
public  String getDeleteTradeLink(String loginId) throws NoDataFoundException,SQLException,Exception{
	 StringBuffer sql=new StringBuffer("DELETE  FROM GEN_TL_TEAMTRADELINK WHERE FRP_FRT_KEYID ");
	 sql.append("IN (SELECT FRT_KEYID FROM GEN_TL_FNLNROLETEAM WHERE ");
	 sql.append("FRT_EMPM_KEYID IN (SELECT  USRM_CCNO FROM ADM_TL_USERMST ");
	 sql.append("WHERE 1=1 AND USRM_CCNO='"+loginId+"')) ");
	
	 dbActionTemplate.executeStatement(sql.toString());
	 getDeleteRoleTeam(loginId);
	 return loginId;
	
}
 
 
 public String getDeleteRoleTeam(String loginId) throws NoDataFoundException,SQLException,Exception{
	 StringBuilder sql=new StringBuilder();
	 sql.append("DELETE FROM GEN_TL_FNLNROLETEAM WHERE FRT_EMPM_KEYID='"+loginId+"' ");
	 CommonMessage.debugMsg("RoleTeam Delete"+sql);
	 dbActionTemplate.executeStatement(sql.toString());
	 return loginId;
 }
 //Swetha changing 25 November
@Override
	public List<String[]> getDocData(String RefDocType) throws Exception{
		String sql=null;
		try{
			CommonMessage.debugMsg("getDocData DaoImpl.....");
			
			if(UIUtils.isValidKeyId(RefDocType)){
			    sql = "SELECT DMDM_KEYID, '', DMDM_REFDOCNO, DMDM_REFDOCTYPE, DMDM_ISODOCTYPE, DMDM_SLNO, ";
			    sql += "DMDM_KEYWORDS, DMDM_CATEGORY, DMDM_OWNER, DMDM_APPROVEDBY, DMDM_SUBJECTAREA, ";
			    sql += "DMDM_TITLE, DMDM_TYPE, DMDM_FILENAME, DMCM_NAME, DMDM_DESCRIPTION, ";
			    sql += "TO_CHAR(DMDM_CREATEDON, 'DD-Mon-YYYY'), TO_CHAR(DMDM_MODIFIEDON, 'DD-Mon-YYYY') ";
			    sql += "FROM DCM_TL_DOCUMENTMANAGER ";
			    sql += "LEFT JOIN DCM_TL_CATEGORYMST ON DMCM_KEYID = DMDM_CATEGORY ";
			    sql += "WHERE UPPER(DMDM_REFDOCTYPE) = UPPER('" + RefDocType + "')";
			}
			else{
			    sql = "SELECT DMDM_KEYID, '', DMDM_REFDOCNO, DMDM_REFDOCTYPE, DMDM_ISODOCTYPE, DMDM_SLNO, ";
			    sql += "DMDM_KEYWORDS, DMDM_CATEGORY, DMDM_OWNER, DMDM_APPROVEDBY, DMDM_SUBJECTAREA, ";
			    sql += "DMDM_TITLE, DMDM_TYPE, DMDM_FILENAME, DMCM_NAME, DMDM_DESCRIPTION, ";
			    sql += "TO_CHAR(DMDM_CREATEDON, 'DD-Mon-YYYY'), TO_CHAR(DMDM_MODIFIEDON, 'DD-Mon-YYYY') ";
			    sql += "FROM DCM_TL_DOCUMENTMANAGER ";
			    sql += "LEFT JOIN DCM_TL_CATEGORYMST ON DMCM_KEYID = DMDM_CATEGORY";
			}
			//sql+= " AND DMDM_REFDOCNO = '" + documentNo + "'  AND  upper(DMDM_REFDOCTYPE) = upper('" + documentType + "')";
			CommonMessage.debugMsg("sqls of doc"+sql);	
			}catch(Exception e){
				e.printStackTrace();
			}
			return dbActionTemplate.getDataList(sql);
	}
	
	public DcmTlDocumentmanager getDocMgr(String id)throws Exception
	{
		DcmTlDocumentmanager dcmTlDocumentmanager = new DcmTlDocumentmanager();
		String sql = DcmTlDocumentmanagerSql.getFileDetails();		
		CommonMessage.debugMsg("SQL "+sql);
		Object args [] = new Object [] { id };
		dcmTlDocumentmanager.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return dcmTlDocumentmanager;
		
	}


}	


