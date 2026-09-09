package com.akranta.tpm.dao.sql;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class GenTlEmployeemstSql {

	public static final String TBL_GEN_TL_EMPLOYEEMST = "GEN_TL_EMPLOYEEMST";  

	TableFieldType [] empmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, name, code, employeetype, employeenumber, joineddate, departmentid
		, designationid, factoryid, isshiftincharge, sectionid, iscellmanager
		, cellid, tradeid, extensionphone,gender, mobile, email, personalinfo
		, remarks, issectionmanager, skillcategory, gradeid, isoperator
		, company, roleid, sbuid, embmenablemail, location
		, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getEmpmDbFields() {
		return empmDbFields;
	}

	public GenTlEmployeemstSql()
	{
		empmDbFields = new TableFieldType[ 33 ];
		for(int i = 0;i < 33; i++)
		{	
			empmDbFields[ i ] = new TableFieldType();
		}
		empmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "EMPM_KEYID";
		empmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.name.ordinal() ].fieldName = "EMPM_NAME";
		empmDbFields[ tableFldConstants.name.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.code.ordinal() ].fieldName = "EMPM_CODE";
		empmDbFields[ tableFldConstants.code.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.employeetype.ordinal() ].fieldName = "EMPM_EMPLOYEETYPE";
		empmDbFields[ tableFldConstants.employeetype.ordinal() ].fieldType = 'C';

		empmDbFields[ tableFldConstants.employeenumber.ordinal() ].fieldName = "EMPM_EMPLOYEENUMBER";
		empmDbFields[ tableFldConstants.employeenumber.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.joineddate.ordinal() ].fieldName = "EMPM_JOINEDDATE";
		empmDbFields[ tableFldConstants.joineddate.ordinal() ].fieldType = 'D';

		empmDbFields[ tableFldConstants.departmentid.ordinal() ].fieldName = "EMPM_DEPARTMENTID";
		empmDbFields[ tableFldConstants.departmentid.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.designationid.ordinal() ].fieldName = "EMPM_DESIGNATIONID";
		empmDbFields[ tableFldConstants.designationid.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "EMPM_FACTORYID";
		empmDbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.isshiftincharge.ordinal() ].fieldName = "EMPM_ISSHIFTINCHARGE";
		empmDbFields[ tableFldConstants.isshiftincharge.ordinal() ].fieldType = 'C';

		empmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "EMPM_SECTIONID";
		empmDbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.iscellmanager.ordinal() ].fieldName = "EMPM_ISCELLMANAGER";
		empmDbFields[ tableFldConstants.iscellmanager.ordinal() ].fieldType = 'C';

		empmDbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "EMPM_CELLID";
		empmDbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "EMPM_TRADEID";
		empmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.extensionphone.ordinal() ].fieldName = "EMPM_EXTENSIONPHONE";
		empmDbFields[ tableFldConstants.extensionphone.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.gender.ordinal() ].fieldName = "EMPM_GENDER";
		empmDbFields[ tableFldConstants.gender.ordinal() ].fieldType = 'C';

		empmDbFields[ tableFldConstants.mobile.ordinal() ].fieldName = "EMPM_MOBILE";
		empmDbFields[ tableFldConstants.mobile.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.email.ordinal() ].fieldName = "EMPM_EMAIL";
		empmDbFields[ tableFldConstants.email.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.personalinfo.ordinal() ].fieldName = "EMPM_PERSONALINFO";
		empmDbFields[ tableFldConstants.personalinfo.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "EMPM_REMARKS";
		empmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.issectionmanager.ordinal() ].fieldName = "EMPM_ISSECTIONMANAGER";
		empmDbFields[ tableFldConstants.issectionmanager.ordinal() ].fieldType = 'C';

		empmDbFields[ tableFldConstants.skillcategory.ordinal() ].fieldName = "EMPM_SKILLCATEGORY";
		empmDbFields[ tableFldConstants.skillcategory.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.gradeid.ordinal() ].fieldName = "EMPM_GRADEID";
		empmDbFields[ tableFldConstants.gradeid.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.isoperator.ordinal() ].fieldName = "EMPM_ISOPERATOR";
		empmDbFields[ tableFldConstants.isoperator.ordinal() ].fieldType = 'C';

		empmDbFields[ tableFldConstants.company.ordinal() ].fieldName = "EMPM_COMPANY";
		empmDbFields[ tableFldConstants.company.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.roleid.ordinal() ].fieldName = "EMPM_ROLEID";
		empmDbFields[ tableFldConstants.roleid.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.sbuid.ordinal() ].fieldName = "EMPM_SBUID";
		empmDbFields[ tableFldConstants.sbuid.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.embmenablemail.ordinal() ].fieldName = "EMPM_ENABLEEMAIL";
		empmDbFields[ tableFldConstants.embmenablemail.ordinal() ].fieldType = 'C';

		empmDbFields[ tableFldConstants.location.ordinal() ].fieldName = "EMPM_LOCATION";
		empmDbFields[ tableFldConstants.location.ordinal() ].fieldType = 'V';
		
		empmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "EMPM_ACTIVE";
		empmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		empmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "EMPM_CREATEDBY";
		empmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		empmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "EMPM_CREATEDON";
		empmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		empmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "EMPM_MODIFIEDON";
		empmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_GEN_TL_EMPLOYEEMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_GEN_TL_EMPLOYEEMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_GEN_TL_EMPLOYEEMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	
	public static String selectSql()
	{
		//String sql = "SELECT * from " + TBL_GEN_TL_EMPLOYEEMST ;
		String sql="Select EMPM_KEYID, FACT_CODE ,EMPM_NAME,EMPM_CODE,EMPM_EMPLOYEENUMBER,DESG_NAME AS DESIGNATION,DEPT_NAME AS DEPARTMENT,REPLACE(EMPM_EXTENSIONPHONE,'{}','')AS EXTNPHONE,REPLACE(EMPM_FAX,'{}','') AS EMPM_FAX,REPLACE(EMPM_MOBILE,'{}','') AS EMPM_MOBILE ,REPLACE(EMPM_EMAIL,'{}','')AS EMPM_EMAIL,REPLACE(EMPM_PERSONALINFO,'{}','') AS   PERSONAL_INFO,REPLACE(EMPM_REMARKS,'{}') AS EMPM_REMARKS, GRDM_NAME AS GRADE from     GEN_TL_EMPLOYEEMST    ,    GEN_TL_DEPARTMENTMST    ,    GEN_TL_EMPLOYEEDTL    , GEN_TL_DESIGNATIONMST    ,    GEN_TL_FACTORYMST    ,    GEN_TL_EMPGRADEMST WHERE EMPM_DESIGNATIONID = DESG_KEYID(+) AND EMPM_DEPARTMENTID = DEPT_KEYID(+) AND EMPM_KEYID=EMPD_KEYID(+) AND EMPM_FACTORYID=FACT_KEYID (+) AND EMPM_GRADEID = GRDM_KEYID(+) AND EMPM_ACTIVE = 'Y'";
		//String sql="select * from "+ TBL_GEN_TL_EMPLOYEEMST +" , "+TBL_GEN_TL_EMPLOYEEDTL +" where EMPM_KEYID=EMPD_KEYID(+) and  EMPM_KEYID= ? ";
		return sql;
	}
	
	public static String getEmployeemstSql() {
		// TODO Auto-generated method stub
		return " SELECT * from " + TBL_GEN_TL_EMPLOYEEMST + " where EMPM_KEYID= ?";
		//CommonMessage.debugMsg("before + ");
		//return "select * from "+ TBL_GEN_TL_EMPLOYEEMST+","+TBL_GEN_TL_EMPLOYEEDTL +" where EMPM_KEYID=EMPD_KEYID and  EMPM_KEYID= ?";
		
	}

	/*public static String getEmployeeFunctionalLocationSql(){
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT comp_keyid compid, comp_code, comp_name, locn_keyid locaid, " );
		sql.append(" locn_code, locn_name, sbut_keyid sbuid, sbut_code, sbut_name, ");
		sql.append(" pbut_keyid pbuid, pbut_code, pbut_name, sect_keyid sectid, ");
		sql.append(" sect_code, sect_name, cell_keyid cellid, cell_code, cell_name, ");
		sql.append(" mchm_keyid machid, mchm_machineno, mchm_machinename, ");
		sql.append(" fnln_keyid flid, fnln_elementid, functionalloc,DISPLAYCODE ");
		sql.append(" FROM gen_vw_fnln,GEN_TL_FNLNROLETEAM ");
		sql.append(" WHERE fnln_keyid = FRT_FNLN_KEYID and FRT_EMPM_KEYID =  ? "); 

		return sql.toString();
		
	}*/
	
	public static String getEmployeeFunctionalLocationSql(String roleId, String flid){
		StringBuffer sql = new StringBuffer();
		/*sql.append(" select distinct  COMP_KEYID,COMP_CODE,COMP_NAME, LOCN_KEYID, LOCN_CODE, LOCN_NAME, FACT_KEYID,FACT_CODE,");
		sql.append(" FACT_NAME, SECT_KEYID,SECT_CODE,SECT_NAME, CELL_KEYID,CELL_CODE,CELL_NAME "); 
		sql.append(" from " );
		sql.append( TableNames.TBL_GEN_TL_EMPLOYEEMST +","+ TableNames.TBL_GEN_TL_CELLMST );
		sql.append("," +TableNames.TBL_GEN_TL_SECTIONMST + "," + TableNames.TBL_GEN_TL_FACTORYMST +"," + TableNames.TBL_GEN_TL_LOCATIONMST + "," + TableNames.TBL_GEN_TL_COMPANYMST );
		sql.append(" where EMPM_FACTORYID = FACT_KEYID(+) AND EMPM_CELLID = CELL_KEYID(+) ");
		sql.append(" AND EMPM_SECTIONID = SECT_KEYID(+)	AND FACT_COMPANYID = COMP_KEYID(+) ");
		sql.append(" AND  FACT_LOCATIONID = LOCN_KEYID(+)" );
		sql.append(" AND EMPM_KEYID = ? ");
		*/
		
		CommonMessage.debugMsg("flid Flidddd555=="+flid);
		sql.append(" SELECT DISTINCT comp_keyid compid, comp_code, comp_name, locn_keyid locaid, " );
		sql.append(" locn_code, locn_name, sbut_keyid sbuid, sbut_code, sbut_name, ");
		sql.append(" pbut_keyid pbuid, pbut_code, pbut_name, sect_keyid sectid, ");
		sql.append(" sect_code, sect_name, cell_keyid cellid, cell_code, cell_name, ");
		sql.append(" mchm_keyid machid, mchm_machineno, mchm_machinename, ");
		sql.append(" fnln_keyid flid, fnln_elementid, functionalloc,DISPLAYCODE ");
		sql.append(" FROM gen_vw_fnln,GEN_TL_FNLNROLETEAM ");
		sql.append(" WHERE fnln_keyid = FRT_FNLN_KEYID and FRT_EMPM_KEYID =  ? ");
		System.out.print(" roleId " + roleId  );
		if( UIUtils.isValidKeyId(flid ) )
			sql.append(" AND FRT_FNLN_KEYID = '" + flid + "'  ");

		if( UIUtils.isValidKeyId(roleId ) )
			sql.append(" AND FRT_ROLE_KEYID = ? ");
		
		
		return sql.toString();
		
	}
	public String getempusr(String empmKeyid) {
		return "Select count(*) from adm_tl_usermst WHERE USRM_CCNO='"+empmKeyid+"'";
		
	}
	public List<String> getDeleteemp(String empmKeyid){
		List<String> sqls=new ArrayList<String>();
		sqls.add("UPDATE  "+TBL_GEN_TL_EMPLOYEEMST+" SET EMPM_ACTIVE='N' WHERE EMPM_KEYID='"+empmKeyid+"'");
		sqls.add("INSERT INTO GEN_TL_FNLNROLETEAM_HIST (SELECT * FROM GEN_TL_FNLNROLETEAM WHERE FRT_KEYID IN(Select FRT_KEYID from GEN_TL_FNLNROLETEAM WHERE FRT_EMPM_KEYID='"+empmKeyid+"') AND FRT_EMPM_KEYID='"+empmKeyid+"') ");
		sqls.add("INSERT INTO GEN_TL_TEAMTRADELINK_HIST (SELECT * FROM GEN_TL_TEAMTRADELINK WHERE FRP_FRT_KEYID IN(Select FRT_KEYID from GEN_TL_FNLNROLETEAM WHERE FRT_EMPM_KEYID='"+empmKeyid+"'))");
		sqls.add("DELETE FROM GEN_TL_TEAMTRADELINK WHERE FRP_FRT_KEYID IN(Select FRT_KEYID from GEN_TL_FNLNROLETEAM WHERE FRT_EMPM_KEYID='"+empmKeyid+"')");
		sqls.add("DELETE FROM GEN_TL_FNLNROLETEAM WHERE FRT_EMPM_KEYID='"+empmKeyid+"' AND FRT_KEYID IN(Select FRT_KEYID from GEN_TL_FNLNROLETEAM WHERE FRT_EMPM_KEYID='"+empmKeyid+"')");
		return sqls;
	}
	public String getDelete(String empmKeyid) {
		return "UPDATE  "+TBL_GEN_TL_EMPLOYEEMST+" SET EMPM_ACTIVE='N' WHERE EMPM_KEYID='"+empmKeyid+"'";
		
	}
	
	public static String getfunloclink(String Employeeid,String Funloclink){
		
		   String TblName = null;
		   String name = null;
		   String keyid = null;
		   if(Funloclink.equals("FACT")){
		      TblName ="GEN_TL_FACTORYMST";
		      keyid ="Fact_keyid";
		      name ="Fact_name";
		    }
		    else if(Funloclink.equals("SECT")){
		    TblName ="GEN_TL_SECTIONMST";
		    keyid ="SECT_KEYID";
		    name ="SECT_name";
		    }
		    else if(Funloclink.equals("CELL")){
		    TblName ="GEN_TL_CELLMST";
		    keyid ="CELL_KEYID";
		    name ="CELL_name"; 
		    }
		    else  if(Funloclink.equals("MCHM")){
			    TblName ="GEN_TL_machinemst";
			    keyid ="MCHM_KEYID";
			    name ="MCHM_MACHINENAME";	
			    }
		   
//		    String sql="SELECT  " +keyid+"," +name+",'',EFLL_KEYID";
//		    sql+=" FROM  GEN_TL_EMPFUNCLOCNLINK, " +TblName+ " ";
//		    sql+="where EFLL_FUNCLOCN(+) =  " +keyid+ " AND EFLL_EMPLOYEEID(+)='" +Employeeid+"'";
		   
		   //sriram m convert to postgress compatible
		   String sql = "SELECT "+keyid+", "+name+", '' AS dummy, EFLL_KEYID " +
	                 "FROM " + TblName + " T " +
	                 "LEFT JOIN GEN_TL_EMPFUNCLOCNLINK E " +
	                 "ON E.EFLL_FUNCLOCN = T." + keyid + " " +
	                 "AND E.EFLL_EMPLOYEEID = '" + Employeeid + "'";
		    CommonMessage.debugMsg("sql string...."+sql);
		    return sql;
	}
public static String getempmailreport()	
{ 
	StringBuffer sb=new StringBuffer();	
	sb.append(" SELECT DISTINCT EMPM_KEYID as empm_keyid,decode(USRM_LOGINID,null,'-',USRM_LOGINID) as txtloginid,EMPM_EMPLOYEENUMBER AS txtempno ,EMPM_NAME AS empm_name,");
	sb.append(" FNLN_DESCRIPTION AS fnln_description,DECODE(EMPM_EMAIL,'-','',EMPM_EMAIL,EMPM_EMAIL) AS txtEmpmailid,");
    sb.append(" EMPM_ENABLEEMAIL  AS EMPM_ENABLEEMAIL");
	sb.append(" FROM GEN_TL_EMPLOYEEMST,adm_tl_usermst,GEN_MV_FLIDHIERARCHY,GEN_TL_FNLNROLETEAM");
	sb.append(" WHERE empm_keyid=FRT_EMPM_KEYID");
	sb.append(" AND usrm_ccno(+)= empm_keyid");
	//sb.append(" AND FLID=FRT_FNLN_KEYID AND EMPM_ACTIVE='Y' AND USRM_ISACTIVE='Y'");
	sb.append(" AND FLID=FRT_FNLN_KEYID AND EMPM_ACTIVE='Y'");
    return sb.toString();
}
}
