package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlNewMommstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlActionplandtlSql;
import com.akranta.tpm.dao.sql.GenTlActionplanmstSql;
import com.akranta.tpm.dao.sql.GenTlMomKpiLinkSql;
import com.akranta.tpm.dao.sql.GenTlMomattendanceSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.dao.sql.GenTlVisitorsSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlActionplandtl;
import com.akranta.tpm.model.GenTlMomKpiLink;
import com.akranta.tpm.model.GenTlMomattendance;
import com.akranta.tpm.model.GenTlMomdtl;
import com.akranta.tpm.model.GenTlMommst;
import com.akranta.tpm.model.GenTlVisitors;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.service.api.NewMomServiceApi;
import com.akranta.tpm.model.GenTlActionplanmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

import net.sf.json.JSONObject;

public class GenTlNewMommstDaoImpl implements GenTlNewMommstDao{
	private DBActionTemplate dbActionTemplate; 
	FunctionCallApi fnCallApi;
	NewMomServiceApi newMomServiceApi;

	public GenTlNewMommstDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void GenTlNewMommstDaoImplJwt(String JwtToken) 
	{
		try{
			newMomServiceApi = new NewMomServiceApi(JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	@Override
	public GenTlMommst select(String momKeyid) throws Exception 
	{
		GenTlMommst genTlMommst = new GenTlMommst();		
		String sql = GenTlMommstSql.getMomsFrmDataSql();				
		Object [] args =  new Object [] { momKeyid };
		genTlMommst.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+genTlMommst.getMomsKeyid());
		return  genTlMommst;
	}
	@Override
	public List<String[]> selectRecalling(String shift, String mstDate,
			String flid, String type, String pillarid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> params = new ArrayList<String>();
			String sql = GenTlMommstSql.mommstrecalngGrid(shift,mstDate,flid,type,pillarid);
			List<String[]> operator = dbActionTemplate.getDataList(sql,params);
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}
public List<String[]> getNewMomGrid(CommonFilter commonFilter,String KeyId, String momdate, String shift, String pillarid)	throws Exception {
		
		try
		{
			List<String> params = new ArrayList<String>();
			String flid=commonFilter.getFlid();
			String type=commonFilter.getType();
			String sql = GenTlMommstSql.NewmomGrid(commonFilter,KeyId,flid,momdate,shift,type,pillarid);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
		    return operator;
			
		}catch (Exception e){
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}
@Override
public List<String[]> getNewMomeetingAtt(CommonFilter commonFilter,String KeyId,String location,String flid,String Momdate, String shift, String recall) throws Exception 
{
			
		String sql=null;
		List<String> paramValues = new ArrayList<String>();
		String empl = commonFilter.getEmployee() != null ? commonFilter.getEmployee().getId():"";
		String dept = commonFilter.getDept() != null ? commonFilter.getDept().getId():"";
		String condParms = "";
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		if(UIUtils.isValidKeyId(empl)){
			condParms +=";EMPLOYEE="+empl;
		}
		if(UIUtils.isValidKeyId(dept)){
			condParms +=";DEPARTMENT="+dept;
		}
		if(UIUtils.isValidKeyId(flid)){
			condParms +=";FNLN="+flid;
		}
		if(UIUtils.isValidKeyId(Momdate)){
			condParms +=";MOMDATE="+Momdate;
		}
		if(UIUtils.isValidKeyId(shift)){
			condParms +=";MOMS_SHIFT="+shift;
		}
		if(UIUtils.isValidKeyId(KeyId)){
			condParms +=";MKEYID="+KeyId;
			 condParms +=";MENUMODE="+commonFilter.getRange();
		}
		
		if(UIUtils.isValidKeyId(location)){
			condParms +=";LOCATION="+location;
		}		
		 if(UIUtils.isValidKeyId(commonFilter.getActionKeyId())){
			 
			 if(commonFilter.getKey().length()==0||commonFilter.getKey()==null)
			 {  
				    String sqll = "select MOMS_PILLARID from GEN_TL_MOMMST where MOMS_KEYID='"+KeyId+"' ";
					String pillarid = dbActionTemplate.getSingleValue(sqll);
				    condParms +=";PILLARKEYID="+pillarid; 
			 }
			 else{
			 condParms +=";PILLARKEYID="+commonFilter.getKey();
			 }
			 condParms +=";MEETINGTYPE="+commonFilter.getActionKeyId();
			 condParms +=";FNLN="+flid;
			 condParms +=";MOMDATE="+Momdate;
			 condParms +=";CELLID="+commonFilter.getCellId();
			 condParms +=";MKEYID="+KeyId;
			 condParms +=";PILLARGROUP="+commonFilter.getKK();
			 
		 }		 
		paramValues.add(condParms);
		paramValues.add(commonParams);
		//List<String[]> dataList =   dbActionTemplate.processFunctionCalls("GEN_FN_MOMATTENDENCE", paramValues);
		
		List<String[]> dataList =   fnCallApi.callFunction("GEN_FN_MOMATTENDENCE_SB", paramValues, 3, true);
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			if(  isInteger ){
				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
			  }
		}
		CommonMessage.debugMsg(dataList);
		return dataList; 
}
public List<String[]> getMomAttendanceEmpMailIds(String momKeyId, String flid)throws Exception{
	String sql = GenTlMommstSql.getMomAttendanceEmpMailIdSql();
	Object [] args = {momKeyId};
	List<String []> data= dbActionTemplate.getDataList(sql,args);
	return data;
}

@Override
public List<String[]> getMomReleatedFileManager(String momKeyId)
		throws Exception {
	// TODO Auto-generated method stub
	String sql = GenTlMommstSql.selectgetMomReleatedFileManager(momKeyId);
	List<String []> gridData = dbActionTemplate.getDataList(sql);
	return gridData;
}
public String updateIsmail(String momid,String val) throws Exception {
	// TODO Auto-generated method stub
	if(val.equals("false"))
	{	
	String sql="update GEN_TL_MOMMST set MOMS_ISMAILTRIG='Y' where MOMS_KEYID='"+momid+"'";
	dbActionTemplate.executeStatement(sql.toString());
	}
	else{
		String sql="update GEN_TL_MOMMST set MOMS_ISMAILTRIG='N' where MOMS_KEYID='"+momid+"'";
		dbActionTemplate.executeStatement(sql.toString());
	}
	return "success";
}


@Override
public List<String[]> getfillmstdata(String momKeyId, String flid)
	throws Exception {
// TODO Auto-generated method stub
//Swetha - Query conversion Simplified MOM 25 October
StringBuilder sql = new StringBuilder();
sql.append(" SELECT MOMS_MEETINGTITLE, ");
sql.append("        CASE MOMS_MEETINGTYPE ");
sql.append("            WHEN 'J' THEN 'JH' ");
sql.append("            WHEN 'D' THEN 'DMT' ");
sql.append("            WHEN 'P' THEN 'PILLAR' ");
sql.append("            WHEN 'FIP' THEN 'FI PROJECT' ");
sql.append("            WHEN 'DEC' THEN 'DEPT EHS COMMITTEE' ");
sql.append("            WHEN 'CEC' THEN 'CENTRAL EHS COMMITTEE' ");
sql.append("            WHEN 'O' THEN 'OTHERS' ");
sql.append("            WHEN 'PD' THEN 'PRODUCTION MEETING' ");
sql.append("        END AS MOMS_MEETINGTYPE, ");
sql.append("        TPMP_CODE, ");
sql.append("        TO_CHAR(MOMS_DATE, 'DD-MON-YYYY') AS MOMS_DATE, ");
sql.append("        MOMS_AGENDA, MOMS_SAFETYTALK, MOMS_REMARKS, functionalloc, MOMS_MEETINGNO ");
sql.append(" FROM gen_tl_mommst ");
sql.append(" LEFT JOIN gen_tl_tpmpillarmst ON MOMS_PILLARID = TPMP_KEYID ");
sql.append(" INNER JOIN GEN_VW_FNLN ON MOMS_FLID = fnln_keyid ");
sql.append(" WHERE MOMS_KEYID = '" + momKeyId + "' ");
List<String[]>  mstrdata  = dbActionTemplate.getDataList(sql.toString());
return mstrdata;
//Swetha - Query conversion Simplified MOM 25 October
}


	
	@Override
public List<String[]> getfilldetaildata(String momKeyId, String flid)
	throws Exception {
// TODO Auto-generated method stub
	//Swetha - Query conversion Simplified MOM 25 October
StringBuilder sql = new StringBuilder();
sql.append(" SELECT momdetails, kink_indicatorname, apld_actionplan, empm_name, APLD_TARGETDATE, status ");
sql.append(" FROM GEN_TL_TPMPILLARMST ");
sql.append(" RIGHT JOIN ( ");
sql.append("     SELECT momd_keyid, momd_discussion_type AS momtype, momd_pillar AS mompillar, ");
sql.append("            momd_discussion_details AS momdetails, kink_keyid, KINK_INDICATORNAME, ");
sql.append("            MOMD_ACTIONPLAN_ID, apld_actionplan, empm_name, ");
sql.append("            TO_CHAR(APLD_TARGETDATE, 'DD-MON-YYYY') AS APLD_TARGETDATE, ");
sql.append("            CASE APLD_STATUS ");
sql.append("                WHEN 'P' THEN 'PENDING' ");
sql.append("                WHEN 'C' THEN 'COMPLETED' ");
sql.append("            END AS status ");
sql.append("     FROM gen_tl_mommst ");
sql.append("     INNER JOIN gen_tl_momdtl ON moms_keyid = momd_moms_keyid ");
sql.append("     LEFT JOIN gen_tl_actionplanmst ON moms_keyid = aplm_masterrefid AND momd_keyid = APLM_DETAILREFID ");
sql.append("     LEFT JOIN gen_tl_actionplandtl ON apld_aplm_keyid = aplm_keyid ");
sql.append("     LEFT JOIN gen_tl_employeemst ON empm_keyid = apld_responsibility ");
sql.append("     LEFT JOIN ( ");
sql.append("         SELECT STRING_AGG(kink_keyid::TEXT, ',' ORDER BY kink_keyid) AS kink_keyid, ");
sql.append("                STRING_AGG(kink_indicatorname, ',' ORDER BY kink_indicatorname) AS kink_indicatorname, ");
sql.append("                MOKP_MOMD_KEYID ");
sql.append("         FROM gen_tl_mom_kpi_link ");
sql.append("         LEFT JOIN kpi_tl_indicator ON mokp_kink_keyid = kink_keyid ");
sql.append("         GROUP BY MOKP_MOMD_KEYID ");
sql.append("     ) kpi_agg ON momd_keyid = MOKP_MOMD_KEYID ");
sql.append("     WHERE MOMD_MOMS_KEYID = '" + momKeyId + "' ");
sql.append(" ) sub ON TPMP_KEYID = mompillar ");
sql.append(" ORDER BY momd_keyid ");
//Swetha - Query conversion Simplified MOM 25 October
List<String[]>  momdetaildata  = dbActionTemplate.getDataList(sql.toString());
return momdetaildata;

}

	
@Override
public List<String[]> getfillactnplndata(String momKeyId, String flid)
	throws Exception {
// TODO Auto-generated method stub
	//Swetha - Query conversion Simplified MOM 25 October
StringBuilder sql = new StringBuilder();
sql.append(" SELECT apld_actionplan, empm_name, APLD_TARGETDATE, ");
sql.append("        CASE APLD_STATUS ");
sql.append("            WHEN 'P' THEN 'PENDING' ");
sql.append("            WHEN 'C' THEN 'COMPLETED' ");
sql.append("        END AS APLD_STATUS ");
sql.append(" FROM gen_tl_actionplandtl ");
sql.append(" INNER JOIN gen_tl_employeemst ON empm_keyid = apld_responsibility ");
sql.append(" WHERE apld_aplm_keyid = (SELECT aplm_keyid FROM gen_tl_actionplanmst WHERE APLM_MASTERREFID = '" + momKeyId + "') ");
//Swetha - Query conversion Simplified MOM 25 October
return null;
}

@Override
public List<String[]> getfillattdanceData(String momKeyId, String flid)
	throws Exception {
// TODO Auto-generated method stub
	//Swetha - Query conversion Simplified MOM 25 October
StringBuilder sql = new StringBuilder();
sql.append(" SELECT empm_keyid, Employee, EmployeeCode, ");
sql.append("        STRING_AGG(ROLENAME, ',' ORDER BY ROLENAME) AS ROLENAME, ");
sql.append("        attendancename ");
sql.append(" FROM ( ");
sql.append("     SELECT DISTINCT empm_keyid, empm_name AS Employee, empm_code AS EmployeeCode, ");
sql.append("            COALESCE(m.role_name, orl.role_name) AS ROLENAME, ");
sql.append("            CASE moma_attandance ");
sql.append("                WHEN 'A' THEN 'ABSENT' ");
sql.append("                WHEN 'L' THEN 'LEAVE' ");
sql.append("                WHEN 'P' THEN 'PRESENT' ");
sql.append("                WHEN 'D' THEN 'ON-DUTY' ");
sql.append("                WHEN 'W' THEN 'WEEKLY-OFF' ");
sql.append("            END AS attendancename ");
sql.append("     FROM gen_tl_employeemst ");
sql.append("     INNER JOIN gen_tl_momattendance ON moma_employeeid = empm_keyid ");
sql.append("     INNER JOIN GEN_TL_MOMMST ON MOMA_MOMS_KEYID = MOMS_KEYID ");
sql.append("     INNER JOIN gen_mv_flidhierarchy ON MOMS_FLID = FLID ");
sql.append("     LEFT JOIN GEN_TL_FNLNROLETEAM ON moma_employeeid = FRT_EMPM_KEYID AND MOMS_FLID = FRT_FNLN_KEYID ");
sql.append("     LEFT JOIN ADM_tl_rolemst m ON FRT_ROLE_KEYID = m.ROLE_KEYID ");
sql.append("     LEFT JOIN ( ");
sql.append("         SELECT DISTINCT FRT_EMPM_KEYID AS OTHER_EMP, FRT_ROLE_KEYID AS OTHER_ROLE ");
sql.append("         FROM GEN_TL_FNLNROLETEAM ");
sql.append("         WHERE FRT_FNLN_KEYID <> '" + flid + "' ");
sql.append("     ) other_roles ON OTHER_EMP = moma_employeeid ");
sql.append("     LEFT JOIN ADM_tl_rolemst orl ON orl.role_keyid = OTHER_ROLE ");
sql.append("     WHERE MOMS_FLID = '" + flid + "' ");
sql.append("     AND MOMA_MOMS_KEYID = '" + momKeyId + "' ");
sql.append(" ) subquery ");
sql.append(" GROUP BY empm_keyid, Employee, EmployeeCode, attendancename ");
sql.append(" ORDER BY Employee ");
//Swetha - Query conversion Simplified MOM 25 October
List<String[]>  attdanceData  = dbActionTemplate.getDataList(sql.toString());
return attdanceData;
}



@Override
public List<String[]> getfillexternalData(String momKeyId, String flid)
	throws Exception {
// TODO Auto-generated method stub
StringBuilder sql = new StringBuilder();
sql.append("  SELECT VISI_VISITORNAME,VISI_PURPOSE ");
sql.append("  FROM GEN_TL_VISITORS ,GEN_TL_MOMMST WHERE  VISI_MOMS_KEYID = MOMS_KEYID ");
sql.append("  AND VISI_MOMS_KEYID = '"+momKeyId+"' ");
List<String[]>  externalData  = dbActionTemplate.getDataList(sql.toString());
return externalData;

}
@Override
public List<String[]> getAttVistor(CommonFilter commonFilter,String MasterKeyid, String shift, String date, String flid, String type,String pillarid,String recall) throws Exception {// TODO Auto-generated method stub
	List<String> params = new ArrayList<String>();
	String sql = GenTlMommstSql.momGridVidtor(commonFilter,MasterKeyid,shift,date,flid,type,pillarid,recall);
	List<String[]> operator = dbActionTemplate.getDataList(sql, params);
    return operator;
	
}
@Override
public GenTlVisitors createVisitor(GenTlVisitors newGenTlVisitors) throws Exception {
// TODO Auto-generated method stub
    List<String> sqls = new ArrayList<String>(); 
    GenTlVisitorsSql genTlVisitorsSql = new GenTlVisitorsSql();
	newGenTlVisitors.setVisiKeyid(dbActionTemplate.getSequenceNumber(GenTlVisitorsSql.TBL_GEN_TL_VISITORS,10,"VISI", "MMYY", "Y")); 
	sqls.add(GenTlVisitorsSql.getInsertSql(genTlVisitorsSql.getVisiDbFields(), newGenTlVisitors.getSaveArray())); 		
	dbActionTemplate.executeStatements(sqls);	
    return newGenTlVisitors;
}

@Override
public GenTlVisitors updateVisitor(GenTlVisitors newGenTlVisitors) throws Exception 
{
	List<String> sqls = new ArrayList<String>();
	GenTlVisitorsSql genTlVisitorsSql = new GenTlVisitorsSql();
	sqls.add(GenTlVisitorsSql.getUpdateSql(genTlVisitorsSql.getVisiDbFields(), newGenTlVisitors.getSaveArray()));
	dbActionTemplate.executeStatements(sqls);		
	return newGenTlVisitors;
}	
@Override
public void DeleteATTVisitorRow(String keyid) throws Exception
{
	// TODO Auto-generated method stub
	String sql = GenTlVisitorsSql.DeleteATTRow(keyid);
	this.dbActionTemplate.executeStatement(sql);
	}
@Override
public String getmailidTrigger(String mailid) throws Exception {
	// TODO Auto-generated method stub
	 String sql="select MOMS_ISMAILTRIG from GEN_TL_MOMMST WHERE MOMS_KEYID='"+mailid+"'";
	 return dbActionTemplate.getSingleValue(sql.toString());
}

@Override
public GenTlVisitors selectVisitor(String visitorkey) throws NoDataFoundException, SQLException, Exception {
// TODO Auto-generated method stub
	GenTlVisitors newGenTlVisitors = new GenTlVisitors();
	String sql = GenTlVisitorsSql.getMomsFrmDataSql1();
	Object [] args =  new Object [] { visitorkey };
	newGenTlVisitors.setSaveArray(dbActionTemplate.getDataArr(sql,args));
	return  newGenTlVisitors;
}
@Override
public GenTlMommst create(GenTlMommst newGenTlMommst) throws BusinessApplicationExceptions, Exception {
	// TODO Auto-generated method stub
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	GenTlMommstSql genTlMommstSql = new GenTlMommstSql();// contains dbtable,field names, Field types and related sqls  of master table
	GenTlMomdtlSql genTlMomdtlSql = new GenTlMomdtlSql();
	GenTlMomKpiLinkSql genTlMomKpiLinkSql =new GenTlMomKpiLinkSql();
	String elementId = newGenTlMommst.getElementid();
 	String location = null;
 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlMommstSql.TBL_GEN_TL_MOMMST);
 	newGenTlMommst.setMomsKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi, 10, "MOM", "YY", "Y"));  
 	newGenTlMommst.setMomsMeetingno(newGenTlMommst.getMomsKeyid());
	sqls.add(GenTlMommstSql.getInsertSql(genTlMommstSql.getMomsDbFields(), newGenTlMommst.getSaveArray())); // add insert sql for master table
	List<GenTlMomdtl> genTlMomdtls= newGenTlMommst.getMomeetingDetail();
	List<GenTlMomKpiLink> genTlMomKpiLinks=newGenTlMommst.getMomeetingKPI();
		if( genTlMomdtls != null && genTlMomdtls.size()> 0 )
		{	
			for( GenTlMomdtl genTlMomdtl : genTlMomdtls)
			{
				genTlMomdtl.setMomdKeyid(dbActionTemplate.getSequenceNumber(GenTlMomdtlSql.TBL_GEN_TL_MOMDTL, 10, "MOD", "YYMM","Y"));
				genTlMomdtl.setMomdMomsKeyid(newGenTlMommst.getMomsKeyid());
				sqls.add(GenTlMomdtlSql.getInsertSql(genTlMomdtlSql.getMomdDbFields(), genTlMomdtl.getSaveArray()));
				if( genTlMomKpiLinks !=null && genTlMomKpiLinks.size()>0){
	            for(GenTlMomKpiLink genTlMomKpiLink:genTlMomKpiLinks)
				{
					genTlMomKpiLink.setMokpKeyid(dbActionTemplate.getSequenceNumber(GenTlMomKpiLinkSql.TBL_GEN_TL_MOM_KPI_LINK,10,"MOK",null,null));
					genTlMomKpiLink.setMokpMomsKeyid(newGenTlMommst.getMomsKeyid());
					genTlMomKpiLink.setMokpMomdKeyid(genTlMomdtl.getMomdKeyid());
					sqls.add(GenTlMomKpiLinkSql.getInsertSql(genTlMomKpiLinkSql.getMokpDbFields(), genTlMomKpiLink.getSaveArray()));// add insert sql for detail table
				} 
	            
			}
			}
	    }
	dbActionTemplate.executeStatements(sqls); // execute the block of sqls
	return newGenTlMommst;
}
public GenTlMommst update(GenTlMommst genTlMommst)	throws Exception {

List<String> sqls = new ArrayList<String>();
GenTlMommstSql genTlMommstSql = new GenTlMommstSql();
GenTlMomdtlSql genTlMomdtlSql = new GenTlMomdtlSql();
GenTlMomKpiLinkSql genTlMomKpiLinkSql =new GenTlMomKpiLinkSql();
sqls.add(GenTlMommstSql.getUpdateSql(genTlMommstSql.getMomsDbFields(), genTlMommst.getSaveArray()));
List<GenTlMomdtl> genTlMomdtls= genTlMommst.getMomeetingDetail();
List<GenTlMomKpiLink> genTlMomKpiLinks=genTlMommst.getMomeetingKPI();

if( genTlMomdtls != null && genTlMomdtls.size()> 0 )
{	
	for( GenTlMomdtl genTlMomdtl : genTlMomdtls){
		
		if(!UIUtils.isValidKeyId(genTlMomdtl.getMomdKeyid()))
		{
			genTlMomdtl.setMomdKeyid(dbActionTemplate.getSequenceNumber(GenTlMomdtlSql.TBL_GEN_TL_MOMDTL, 10, "MOD", "YYMM","Y"));
			genTlMomdtl.setMomdMomsKeyid(genTlMommst.getMomsKeyid());
			sqls.add(GenTlMomdtlSql.getInsertSql(genTlMomdtlSql.getMomdDbFields(), genTlMomdtl.getSaveArray()));
			if( genTlMomKpiLinks.size()>0 ){//!=null && genTlMomKpiLinks.size()>0  
				for(GenTlMomKpiLink genTlMomKpiLink:genTlMomKpiLinks)
				{

					if(UIUtils.isValidKeyId(genTlMomKpiLink.getMokpKinkKeyid())){
						
						if(!UIUtils.isValidKeyId(genTlMomKpiLink.getMokpMomdKeyid())){
							genTlMomKpiLink.setMokpKeyid(dbActionTemplate.getSequenceNumber(GenTlMomKpiLinkSql.TBL_GEN_TL_MOM_KPI_LINK,10,"MOK",null,null));
							genTlMomKpiLink.setMokpMomdKeyid(genTlMomdtl.getMomdKeyid());
							sqls.add(GenTlMomKpiLinkSql.getInsertSql(genTlMomKpiLinkSql.getMokpDbFields(), genTlMomKpiLink.getSaveArray()));// add insert sql for detail table
						}
					}
				}
			}
		}
		else
		{
			String sql="";
			String cnt;
			sql = " select Count(*) From gen_tl_mom_kpi_link   where 1=1 AND MOKP_MOMD_KEYID= '"+genTlMomdtl.getMomdKeyid()+"'";
			genTlMomdtl.setMomdMomsKeyid(genTlMommst.getMomsKeyid());
			sqls.add(GenTlMomdtlSql.getUpdateSql(genTlMomdtlSql.getMomdDbFields(), genTlMomdtl.getSaveArray()));
			cnt=dbActionTemplate.getSingleValue(sql);
			if(Integer.parseInt(cnt)>0)
			sqls.add(GenTlMomKpiLinkSql.getDeleteSqlKpi(genTlMomdtl.getMomdKeyid()));
			for(GenTlMomKpiLink genTlMomKpiLink:genTlMomKpiLinks)
			{
				if(UIUtils.isValidKeyId(genTlMomKpiLink.getMokpMomdKeyid()) && (genTlMomKpiLink.getMokpMomdKeyid().equals(genTlMomdtl.getMomdKeyid())))
				{
					if(UIUtils.isValidKeyId(genTlMomKpiLink.getMokpKinkKeyid())){
					   genTlMomKpiLink.setMokpKeyid(dbActionTemplate.getSequenceNumber(GenTlMomKpiLinkSql.TBL_GEN_TL_MOM_KPI_LINK,10,"MOK",null,null));
					   genTlMomKpiLink.setMokpMomdKeyid(genTlMomdtl.getMomdKeyid());
					   sqls.add(GenTlMomKpiLinkSql.getInsertSql(genTlMomKpiLinkSql.getMokpDbFields(), genTlMomKpiLink.getSaveArray()));// add insert sql for detail table
					}
				}
			}
		}	
	
	}

}
dbActionTemplate.executeStatements(sqls);
return genTlMommst;
}
@Override
public GenTlMommst updateatt(GenTlMommst newGenTlMommst) throws Exception {
	List<String> sqls = new ArrayList<String>();
	GenTlMommstSql genTlMommstSql = new GenTlMommstSql();
	GenTlMomattendanceSql genTlMomattendanceSql = new GenTlMomattendanceSql();	
	sqls.add(GenTlMommstSql.getUpdateSql(genTlMommstSql.getMomsDbFields(), newGenTlMommst.getSaveArray()));
	List<GenTlMomattendance> genTlMomattendances= newGenTlMommst.getMomeetinMomattendances();	
	if( genTlMomattendances != null && genTlMomattendances.size()> 0 )
	{	
		for( GenTlMomattendance genTlMomattendance : genTlMomattendances){
			if(!UIUtils.isValidKeyId(genTlMomattendance.getMomaKeyid()))
			{											
				genTlMomattendance.setMomaKeyid(dbActionTemplate.getSequenceNumber(GenTlMomattendanceSql.TBL_GEN_TL_MOMATTENDANCE,10,"MOA",  "YYMM", null)); // set the sequnce number\
				genTlMomattendance.setMomaMomsKeyid(newGenTlMommst.getMomsKeyid());
				sqls.add(GenTlMomattendanceSql.getInsertSql(genTlMomattendanceSql.getMomaDbFields(), genTlMomattendance.getSaveArray()));								
			}					
			else{						
				sqls.add(GenTlMomattendanceSql.getUpdateSql(genTlMomattendanceSql.getMomaDbFields(), genTlMomattendance.getSaveArray()));
			}	
		}
	}		
	dbActionTemplate.executeStatements(sqls);
	return newGenTlMommst;
}
public GenTlActionplanmst createActionPlan(GenTlActionplanmst genTlActionplanmst,
		GenTlActionplandtl genTlActionplandtl,GenTlMommst newGenTlMommst) throws BusinessApplicationExceptions, Exception {
	List<String> sqls = new ArrayList<String>();
	GenTlActionplanmstSql genTlActionplanmstSql = new GenTlActionplanmstSql(); 
	GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql();
	String elementId = genTlActionplanmst.getAplmElementid();	
 	String seqIdentf = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlActionplanmstSql.TBL_GEN_TL_ACTIONPLANMST);
	try {
		genTlActionplanmst.setAplmKeyid(dbActionTemplate.getSequenceNumber(seqIdentf, 10, "AP","", ""));
		genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
		genTlActionplanmst.setAplmMasterrefid(newGenTlMommst.getMomsKeyid());
		String Momdetailid=dbActionTemplate.getSingleValue("GEN_TL_MOMDTL", "MOMD_KEYID", "MOMD_MOMS_KEYID",newGenTlMommst.getMomsKeyid());
	    genTlActionplanmst.setAplmDetailrefid(Momdetailid); 
		sqls.add(GenTlActionplanmstSql.getInsertSql(genTlActionplanmstSql.getAplmDbFields(),genTlActionplanmst.getSaveArray()));
	    List<GenTlActionplandtl> genTlActionplandtls= genTlActionplandtl.getActionplanlist();
	    if( genTlActionplandtls !=null && genTlActionplandtls.size()>0){
	            for(GenTlActionplandtl genTlActionplandtlss:genTlActionplandtls)
				{
	            	CommonMessage.debugMsg("Inside the for Details:::");
	             	genTlActionplandtlss.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,10,"APLD", "", ""));
	            	genTlActionplandtlss.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
	            	genTlActionplandtlss.setApldHowtodo(genTlActionplandtl.getApldHowtodo());
	              	genTlActionplandtlss.setApldActionplan(genTlActionplandtl.getApldActionplan());
	            	genTlActionplandtlss.setApldTradeid(genTlActionplandtl.getApldTradeid());
	            	genTlActionplandtlss.setApldStatus(genTlActionplandtl.getApldStatus());
	            	genTlActionplandtlss.setApldTargetdate(genTlActionplandtl.getApldTargetdate());
	            	genTlActionplandtlss.setApldCompleatedon(genTlActionplandtl.getApldCompleatedon());
	            	genTlActionplandtlss.setApldCompletedby(genTlActionplandtl.getApldCompletedby());
	            	genTlActionplandtlss.setApldCountermeasure(genTlActionplandtl.getApldCountermeasure());
	            	genTlActionplandtlss.setApldCreatedby(genTlActionplandtl.getApldCreatedby());
	            	genTlActionplandtlss.setApldCreatedon(genTlActionplandtl.getApldCreatedon());
	            	genTlActionplandtlss.setApldRemarks(genTlActionplandtl.getApldRemarks());
	            	genTlActionplandtlss.setApldOthers(genTlActionplandtl.getApldOthers());
	            	genTlActionplandtlss.setApldTempfiled2(genTlActionplandtl.getApldTempfiled2());
	            	genTlActionplandtlss.setApldTempfiled3(genTlActionplandtl.getApldTempfiled3());
	            	genTlActionplandtlss.setApldTempfiled4(genTlActionplandtl.getApldTempfiled4());
	            	genTlActionplandtlss.setApldTempfiled5(genTlActionplandtl.getApldTempfiled5());
	            	genTlActionplandtlss.setApldActive(genTlActionplandtl.getApldActive());
	            	genTlActionplandtlss.setApldModifiedon(genTlActionplandtl.getApldModifiedon());
	            	genTlActionplandtlss.setApldResponsibility(genTlActionplandtlss.getApldResponsibility());
					sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtlss.getSaveArray()));// add insert sql for detail table
				} 
	    }
		 else{
		   if (!CommonFunctions.isValidKeyId(genTlActionplandtl.getApldKeyid())){
			   CommonMessage.debugMsg("Inside the else if Details:::");
			genTlActionplandtl.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,10, "APLD", "", "")); 
			sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtl.getSaveArray()));
		}}
		sqls.add(GenTlActionplanmstSql.getUpdateStausSql(genTlActionplanmstSql.getAplmDbFields(),genTlActionplanmst.getSaveArray())); 		
		String sql = GenTlActionplanmstSql.getRefDocUpdateSql(genTlActionplanmst);
		if (CommonFunctions.isValidKeyId(sql))
			sqls.add(sql);
		dbActionTemplate.executeStatements(sqls); 													
	} catch (Exception e) {
		throw new Exception(e.getMessage());
	}
	return genTlActionplanmst;
}

public GenTlActionplanmst updateActionPlan(GenTlActionplanmst genTlActionplanmst,GenTlActionplandtl genTlActionplandtl,GenTlMommst newGenTlMommst,
		String Rowid,String ActionPlanId,String ActionplanDetailId) throws BusinessApplicationExceptions, Exception {
	List<String> sqls = new ArrayList<String>();
	GenTlActionplanmstSql genTlActionplanmstSql = new GenTlActionplanmstSql(); 
	GenTlActionplandtlSql genTlActionplandtlSql = new GenTlActionplandtlSql();
	String elementId = genTlActionplanmst.getAplmElementid();
	try {
		if(Rowid!=null){
		sqls.add(GenTlActionplandtlSql.getUpdateSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtl.getSaveArray()));
		}
		else if(ActionPlanId.length()>0 && ActionplanDetailId.length()>0){
		    	genTlActionplandtl.setApldKeyid(ActionplanDetailId);
		    	genTlActionplandtl.setApldAplmKeyid(ActionPlanId);
		    	sqls.add(GenTlActionplandtlSql.getUpdateSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtl.getSaveArray()));
		    	dbActionTemplate.executeStatements(sqls); 	
		}
		else{
		String seqIdentf = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,GenTlActionplanmstSql.TBL_GEN_TL_ACTIONPLANMST);
		genTlActionplanmst.setAplmKeyid(dbActionTemplate.getSequenceNumber(seqIdentf, 10, "AP","", ""));
		genTlActionplandtl.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
		genTlActionplanmst.setAplmMasterrefid(newGenTlMommst.getMomsKeyid());		
		String Momdetailid=dbActionTemplate.getSingleValue("GEN_TL_MOMDTL", "MAX(MOMD_KEYID)", "MOMD_MOMS_KEYID",newGenTlMommst.getMomsKeyid());
		genTlActionplanmst.setAplmDetailrefid(Momdetailid); 
		sqls.add(GenTlActionplanmstSql.getInsertSql(genTlActionplanmstSql.getAplmDbFields(),genTlActionplanmst.getSaveArray()));
	    List<GenTlActionplandtl> genTlActionplandtls= genTlActionplandtl.getActionplanlist();
	    if( genTlActionplandtls !=null && genTlActionplandtls.size()>0){
	            for(GenTlActionplandtl genTlActionplandtlss:genTlActionplandtls)
				{	             
	            	genTlActionplandtlss.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,10,"APLD", "", ""));
	            	genTlActionplandtlss.setApldAplmKeyid(genTlActionplanmst.getAplmKeyid());
	            	genTlActionplandtlss.setApldHowtodo(genTlActionplandtl.getApldHowtodo());
	              	genTlActionplandtlss.setApldActionplan(genTlActionplandtl.getApldActionplan());
	            	genTlActionplandtlss.setApldTradeid(genTlActionplandtl.getApldTradeid());
	            	genTlActionplandtlss.setApldStatus(genTlActionplandtl.getApldStatus());
	            	genTlActionplandtlss.setApldTargetdate(genTlActionplandtl.getApldTargetdate());
	            	genTlActionplandtlss.setApldCompleatedon(genTlActionplandtl.getApldCompleatedon());
	            	genTlActionplandtlss.setApldCompletedby(genTlActionplandtl.getApldCompletedby());
	            	genTlActionplandtlss.setApldCountermeasure(genTlActionplandtl.getApldCountermeasure());
	            	genTlActionplandtlss.setApldCreatedby(genTlActionplandtl.getApldCreatedby());
	            	genTlActionplandtlss.setApldCreatedon(genTlActionplandtl.getApldCreatedon());
	            	genTlActionplandtlss.setApldRemarks(genTlActionplandtl.getApldRemarks());
	            	genTlActionplandtlss.setApldOthers(genTlActionplandtl.getApldOthers());
	            	genTlActionplandtlss.setApldTempfiled2(genTlActionplandtl.getApldTempfiled2());
	            	genTlActionplandtlss.setApldTempfiled3(genTlActionplandtl.getApldTempfiled3());
	            	genTlActionplandtlss.setApldTempfiled4(genTlActionplandtl.getApldTempfiled4());
	            	genTlActionplandtlss.setApldTempfiled5(genTlActionplandtl.getApldTempfiled5());
	            	genTlActionplandtlss.setApldActive(genTlActionplandtl.getApldActive());
	            	genTlActionplandtlss.setApldModifiedon(genTlActionplandtl.getApldModifiedon());
	            	genTlActionplandtlss.setApldResponsibility(genTlActionplandtlss.getApldResponsibility());
					sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtlss.getSaveArray()));// add insert sql for detail table
				} 
	   }
	    else{
		   if (!CommonFunctions.isValidKeyId(genTlActionplandtl.getApldKeyid())) {
			genTlActionplandtl.setApldKeyid(dbActionTemplate.getSequenceNumber(GenTlActionplandtlSql.TBL_GEN_TL_ACTIONPLANDTL,10, "APLD", "", "")); 
			sqls.add(GenTlActionplandtlSql.getInsertSql(genTlActionplandtlSql.getApldDbFields(),genTlActionplandtl.getSaveArray()));
		}
		   }
		sqls.add(GenTlActionplanmstSql.getUpdateStausSql(genTlActionplanmstSql.getAplmDbFields(),genTlActionplanmst.getSaveArray())); 		
		String sql = GenTlActionplanmstSql.getRefDocUpdateSql(genTlActionplanmst);
		if (CommonFunctions.isValidKeyId(sql))
			sqls.add(sql);
		dbActionTemplate.executeStatements(sqls); 													
	}
		
	}
	
	catch (Exception e) {
		throw new Exception(e.getMessage());
	}
	return genTlActionplanmst;
}

@Override
public List<String[]> getNewMomeetingList(CommonFilter commonFilter) throws Exception {
	// TODO Auto-generated method stub
	List<String> paramValues = new ArrayList<String>();		
	String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	
	if(UIUtils.isValidKeyId(commonFilter.getRefdocid())){
		condParms +="MOMREFID="+commonFilter.getRefdocid()+";";
	}
	if(UIUtils.isValidKeyId(commonFilter.getType())){
		condParms +="MOMREFDATE="+commonFilter.getType()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getFlid())){
		condParms +="FLID="+commonFilter.getFlid()+";";
	}
	
	if(UIUtils.isValidKeyId(commonFilter.getTaskid())){
		condParms +="MOMTYPE="+commonFilter.getTaskid()+";";
	}

	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	
	//List<String[]> dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_NEWMOMODIFICATIONGRID", paramValues);
	List<String[]> dataList =  fnCallApi.callFunction("GEN_FN_NEWMOMODIFICATIONGRID_SB", paramValues,3,true);
	if( commonFilter.getViewClick() == 'Y')
	{
		String totalCnt = paramValues.get(0); 
		CommonMessage.debugMsg("totalCnt..."+totalCnt);
		boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
		if(  isInteger )
		{
			commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
		}
	}
	return dataList; 
}
@Override
public void DeleteNewMomRow(String keyid,String ActionPMasterId) throws Exception{
	List<String > sqls = new ArrayList<String>();
	sqls.add(GenTlMomKpiLinkSql.DeleteMomRow(keyid));
	sqls.add( GenTlMomdtlSql.DeleteMomRow(keyid));
    sqls.add(GenTlActionplandtlSql.getActionPDetailDeleteSql(ActionPMasterId));
    sqls.add(GenTlActionplandtlSql.getActionPMasterDeleteMasterSql(ActionPMasterId));
	dbActionTemplate.executeStatements(sqls);
}

@Override
public Workbook getMomeetingExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception{
ResultSet rs = null;
   try{
	rs =getNewMomeetingReportResultSet(commonFilter);
	ExcelUtils excelUtils = new ExcelUtils(colModel);
	List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();	
	XLConditionalFormats condFormat = new XLConditionalFormats();
	condFormat.setFontColor(new RGB(254,0,0)); //red font
	condFormat.setFontName("Wingdings");
	condFormat.setFontHeightPoint((short)104);
	condFormat.setFontBoldWeight((short)20);
	condFormat.setFromCol(103);
	condFormat.setToCol(-10);
	condFormat.setOperator(ComparisonOperator.EQUAL);
	condFormat.setCondValue( (char)252+""); //Tick
	condFormat.setIdentfier("tick");
	condFormats.add(condFormat);
	excelUtils.setCondFormats(condFormats);
	return excelUtils.writeToExcel(rs,rptFormat,2,1,0 );//Changed for Excel Download
   }finally{
	   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
   }  
}

private ResultSet getNewMomeetingReportResultSet(CommonFilter commonFilter) throws Exception 
{
	List<String> paramValues = new ArrayList<String>();
	String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
	if(UIUtils.isValidKeyId(commonFilter.getTaskid())){
		condParms +="MOMTYPE="+commonFilter.getTaskid()+";";
	}	
	paramValues.add(condParms);
	paramValues.add(commonParams);
	return dbActionTemplate.NewdbFunctionCall2("GEN_FN_NEWMOMODIFICATIONGRID", paramValues);
}
}
