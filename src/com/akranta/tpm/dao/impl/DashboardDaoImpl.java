package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.DashboardDispbean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.DashboardDao;
import com.akranta.tpm.dao.sql.AdmTlScrollmsgmstSql;
import com.akranta.tpm.dao.sql.DashboardSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlMessageboardSql;
import com.akranta.tpm.model.AdmTlScrollmsgmst;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlMessageboard;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class DashboardDaoImpl implements DashboardDao {
	private DBActionTemplate dbActionTemplate;
	
	public DashboardDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate =dbActionTemplate;
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public List<String[]> getDashboardPillars() throws Exception{
		
		return dbActionTemplate.getDataList(DashboardSqls.getDashboardPillars());
		
	}
	
	public List<DashboardDispbean> getDashboardRptDetails(String pillarCode,String userid) throws Exception{
		DashboardDispbean dashboardDispbean = new DashboardDispbean();
		CommonMessage.debugMsg("dASHBOARD DAOIMPL");
		return (List<DashboardDispbean>) dbActionTemplate.getDataList(DashboardSqls.getDashboardRptDetailsSql(pillarCode,userid),dashboardDispbean);
	}

	@Override
	public List<String[]> getMessage(String flid, String roleId)
			throws Exception {

		
		CommonMessage.debugMsg(" resultList size 1234 " );
		//StringBuffer sql=new StringBuffer();
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT '' AS title, smsg_message AS message ");
		sql.append("FROM adm_tl_scrollmsgmst, gen_mv_flidhierarchy ");
		sql.append("WHERE CURRENT_DATE BETWEEN DATE(smsg_fromdate) AND DATE(smsg_todate) ");
		sql.append("AND smsg_istobedisplayed = 'Y' AND flid = smsg_flid ");
		sql.append("AND (smsg_roleid = '{}' OR smsg_roleid = ?) ");
		sql.append("AND smsg_dis_alllevel = 'Y' ");
		sql.append("AND POSITION(smsg_flid IN (parentflids || flid)) > 0 ");
		sql.append("AND POSITION(( ");
		sql.append("  SELECT loc.fnln_keyid ");
		sql.append("  FROM gen_vw_fnln a ");
		sql.append("  JOIN gen_vw_fnln loc ON a.locn_keyid = loc.fnln_originalid ");
		sql.append("  WHERE a.fnln_keyid = ? ");
		sql.append(") IN (parentflids || flid)) > 0 ");

		sql.append("UNION ");
		sql.append("SELECT '' AS title, smsg_message AS message ");
		sql.append("FROM adm_tl_scrollmsgmst, gen_mv_flidhierarchy ");
		sql.append("WHERE CURRENT_DATE BETWEEN DATE(smsg_fromdate) AND DATE(smsg_todate) ");
		sql.append("AND smsg_istobedisplayed = 'Y' AND flid = smsg_flid ");
		sql.append("AND (smsg_roleid = '{}' OR smsg_roleid = ?) ");
		sql.append("AND smsg_dis_alllevel = 'N' AND flid = ? ");

		sql.append("UNION ");
		sql.append("SELECT moms_meetingno AS title, momd_discussion_details AS message ");
		sql.append("FROM gen_tl_mommst ");
		sql.append("JOIN gen_tl_momdtl ON momd_moms_keyid = moms_keyid ");
		sql.append("WHERE moms_ismessageboard = 'Y' ");
		sql.append("AND moms_flid = ? ");
		sql.append("AND DATE(moms_date) = CURRENT_DATE ");
		sql.append("ORDER BY title, message;");
		
		List<String> params = Arrays.asList(
			    roleId, // for 1st ?
			    flid,   // for 2nd ?
			    roleId, // for 3rd ?
			    flid,   // for 4th ?
			    flid    // for 5th ?
			);
//		List<String> params=[roleId,flid];
//		sql.append(" select '' AS title,SMSG_MESSAGE AS message  ");
//		sql.append(" FROM ADM_TL_SCROLLMSGMST, GEN_MV_FLIDHIERARCHY ");
//		sql.append(" WHERE TRUNC(SYSDATE) BETWEEN TRUNC(SMSG_FROMDATE) AND TRUNC(SMSG_TODATE) ");
//		sql.append(" AND SMSG_ISTOBEDISPLAYED = 'Y' AND FLID = SMSG_FLID ");
//		sql.append(" AND (SMSG_ROLEID = '{}' OR  SMSG_ROLEID ='" + roleId + "' )  ");
//		sql.append(" AND SMSG_DIS_ALLLEVEL ='Y' AND INSTR(PARENTFLIDS || FLID, SMSG_FLID ) > 0   ");
//		
//		//ADDING LOCN ID COND ON 06-SEP-2014
//		sql.append(" AND INSTR(PARENTFLIDS || FLID, ( SELECT LOC.FNLN_KEYID FROM GEN_VW_FNLN A, GEN_VW_FNLN LOC "); 
//		sql.append(" WHERE A.FNLN_KEYID='" + flid + "' AND A.LOCN_KEYID = LOC.FNLN_ORIGINALID ))>0 ");
//		
//		
//		sql.append(" UNION " );
//		//DISPLAY FOR NOT ALL LEVEL
//		sql.append(" select '' AS title,SMSG_MESSAGE AS message  ");
//		sql.append(" FROM ADM_TL_SCROLLMSGMST, GEN_MV_FLIDHIERARCHY ");
//		sql.append(" WHERE TRUNC(SYSDATE) BETWEEN TRUNC(SMSG_FROMDATE) AND TRUNC(SMSG_TODATE) ");
//		sql.append(" AND SMSG_ISTOBEDISPLAYED = 'Y' AND FLID = SMSG_FLID ");
//		sql.append(" AND (SMSG_ROLEID = '{}' OR  SMSG_ROLEID ='" + roleId + "' )  ");
//		sql.append(" AND SMSG_DIS_ALLLEVEL ='N' AND FLID = '" + flid + "' ");
//		
//		sql.append(" UNION " );
//		sql.append(" select MOMS_MEETINGNO title, MOMD_DISCUSSION_DETAILS as message ");
//		sql.append(" FROM  gen_TL_mommst, gen_TL_momdtl where MOMD_MOMS_KEYID = MOMS_KEYID ");
//		sql.append(" AND  MOMS_ISMESSAGEBOARD='Y' AND MOMS_FLID ='" + flid + "'  ");
//		sql.append(" AND TRUNC(MOMS_DATE) = TRUNC(SYSDATE) ");
//		sql.append(" order by title,message ");
		
//		sql.append("SELECT  '' AS title,  smsg_message AS message "
//				+ "FROM adm_tl_scrollmsgmst, gen_mv_flidhierarchy\r\n"
//				+ "WHERE CURRENT_DATE BETWEEN DATE(smsg_fromdate) AND DATE(smsg_todate) "
//				+ "  AND smsg_istobedisplayed = 'Y'\r\n"
//				+ "  AND flid = smsg_flid\r\n"
//				+ "  AND (smsg_roleid = '{}' OR smsg_roleid = ?) "
//				+ "  AND smsg_dis_alllevel = 'Y'\r\n"
//				+ "  AND POSITION(smsg_flid IN (parentflids || flid)) > 0 "
//				+ "  AND POSITION((\r\n"
//				+ "        SELECT loc.fnln_keyid\r\n"
//				+ "        FROM gen_vw_fnln a\r\n"
//				+ "        JOIN gen_vw_fnln loc ON a.locn_keyid = loc.fnln_originalid "
//				+ "        WHERE a.fnln_keyid = ? "
//				+ "    ) IN (parentflids || flid)) > 0\r\n"
//				+ "\r\n"
//				+ "UNION\r\n"
//				+ "\r\n"
//				+ "SELECT \r\n"
//				+ "    '' AS title,\r\n"
//				+ "    smsg_message AS message\r\n"
//				+ "FROM adm_tl_scrollmsgmst, gen_mv_flidhierarchy\r\n"
//				+ "WHERE CURRENT_DATE BETWEEN DATE(smsg_fromdate) AND DATE(smsg_todate)\r\n"
//				+ "  AND smsg_istobedisplayed = 'Y'\r\n"
//				+ "  AND flid = smsg_flid\r\n"
//				+ "  AND (smsg_roleid = '{}' OR smsg_roleid = '"+roleId+"')\r\n"
//				+ "  AND smsg_dis_alllevel = 'N'\r\n"
//				+ "  AND flid = '"+flid+"'\r\n"
//				+ "\r\n"
//				+ "UNION\r\n"
//				+ "\r\n"
//				+ "SELECT \r\n"
//				+ "    moms_meetingno AS title,\r\n"
//				+ "    momd_discussion_details AS message\r\n"
//				+ "FROM gen_tl_mommst\r\n"
//				+ "JOIN gen_tl_momdtl ON momd_moms_keyid = moms_keyid\r\n"
//				+ "WHERE moms_ismessageboard = 'Y'\r\n"
//				+ "  AND moms_flid = '"+flid+"'\r\n"
//				+ "  AND DATE(moms_date) = CURRENT_DATE\r\n"
//				+ "\r\n"
//				+ "ORDER BY title, message;");
//		
		CommonMessage.debugMsg("Sqls are"+sql);
		List<String []> gridData = dbActionTemplate.getDataListWithColHeader(sql.toString(), params);
		
		return gridData;
	}

	@Override
	public List<String[]> getjhAct(CommonFilter commonFilter) throws Exception {
		/*String sql=new String();
		List<String> params=null;
		sql=" select ACHM_ACTIVITY, decode(ACHM_FREQUENCY,'D','Daily','W','Weekly Once', "; 
		sql=" to_CHAR(to_date(ACHM_FREQUENCY, 'DD'),'Day')) as Frequency,SFTM_CODE,JACD_DESCRIPTION,JACD_TIME ";
		sql=" from  gen_TL_JHACTIVITYCHARTMST,gen_TL_JHACTIVITYCHARTDTL,GEN_TL_SHIFTMST ";
		sql=" where  ACHM_KEYID = JACD_ACHM_KEYID(+) AND SFTM_KEYID(+)=  JACD_SHIFTID  and  ACHM_FREQUENCY = decode(ACHM_FREQUENCY, is_numeric( ACHM_FREQUENCY),  to_char(sysdate,'D'),ACHM_FREQUENCY) ";
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg(" resultList size " + gridData.size());
		return gridData;*/
		try
		{
			CommonMessage.debugMsg("Inside daoimpl");
			List<String> paramValues = new ArrayList<String>();	
			String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			paramValues.add(condParms);
			paramValues.add(commonParams);
			List<String[]> dataList =   dbActionTemplate.processFunctionCallsWithColHeaders("GEN_FN_JHACTIVITY", paramValues);
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				CommonMessage.debugMsg("totalCnt...."+totalCnt);
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			CommonMessage.debugMsg(dataList);
			return dataList; 
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public GenTlMessageboard createMessage(GenTlMessageboard newGenTlMessageboard,GenTlMessageboard existGenTlMessageboard) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlMessageboardSql genTlMessageboardSql = new GenTlMessageboardSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			newGenTlMessageboard.setMsgbKeyid(dbActionTemplate.getSequenceNumber(GenTlMessageboardSql.TBL_GEN_TL_MESSAGEBOARD,6, "MS", "", "Y")); // set the sequnce number 
			sqls.add(GenTlMessageboardSql.getInsertSql(genTlMessageboardSql.getMsgbDbFields(), newGenTlMessageboard.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newGenTlMessageboard;
	}

	@Override
	public GenTlMessageboard updateMessage(GenTlMessageboard newGenTlMessageboard,GenTlMessageboard existGenTlMessageboard) throws Exception {
		List<String> sqls = new ArrayList<String>();
		GenTlMessageboardSql genTlMessageboardSql = new GenTlMessageboardSql();
		try {

			sqls.add(GenTlMessageboardSql.getUpdateSql(genTlMessageboardSql.getMsgbDbFields(), newGenTlMessageboard.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return newGenTlMessageboard;
	}

	@Override
	public GenTlMessageboard selectMessage(String mesgkeyid) throws NoDataFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("Inside the dao impl");
		CommonMessage.debugMsg("ID:" +mesgkeyid);
		GenTlMessageboard newGenTlMessageboard = new GenTlMessageboard();	
		GenTlMessageboardSql genTlMessageboardSql = new GenTlMessageboardSql();
		String sql = genTlMessageboardSql.getSelectSql();				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		Object [] args =  new Object [] { mesgkeyid };
		newGenTlMessageboard.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+newGenTlMessageboard.getMsgbKeyid());
		return newGenTlMessageboard;
	}

	@Override
	public GenTlMessageboard deleteMessageBoard(GenTlMessageboard newGenTlMessageboard) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		GenTlMessageboardSql genTlMessageboardSql = new GenTlMessageboardSql();
		try {
			
			sqls.add(genTlMessageboardSql.getDeleteSql(genTlMessageboardSql.getMsgbDbFields(), newGenTlMessageboard.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return newGenTlMessageboard;
	}

	@Override
	public List<String[]> getFillMsggrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		List<String[]> dataList=new ArrayList<String[]>();
		try
		{
			List<String> paramValues = new ArrayList<String>();		
			String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			CommonMessage.debugMsg("test to............");
			
			if(UIUtils.isValidKeyId(commonFilter.getKey()))
				condParms +=";KEYID="+commonFilter.getKey();
			
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			
			dataList =  dbActionTemplate.processFunctionCalls("GEN_FN_MESSAGEBOARD", paramValues);
			
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
				 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return dataList;

	}

	@Override
	public AdmTlScrollmsgmst createMessageBoard(AdmTlScrollmsgmst newadmTlScrollmsgmst,AdmTlScrollmsgmst existadmTlScrollmsgmst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		AdmTlScrollmsgmstSql admTlScrollmsgmstSql = new AdmTlScrollmsgmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			
			newadmTlScrollmsgmst.setSmsgKeyid(dbActionTemplate.getSequenceNumber("ADM_TL_SCROLLMSGMST",10,"MSGB","","Y")); // set the sequnce number
			sqls.add(AdmTlScrollmsgmstSql.getInsertSql(admTlScrollmsgmstSql.getSmsgDbFields(), newadmTlScrollmsgmst.getSaveArray())); // add insert sql for master table
            dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return newadmTlScrollmsgmst;
	}

	@Override
	public AdmTlScrollmsgmst updateMessageBoard(AdmTlScrollmsgmst newadmTlScrollmsgmst,AdmTlScrollmsgmst existadmTlScrollmsgmst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		AdmTlScrollmsgmstSql admTlScrollmsgmstSql = new AdmTlScrollmsgmstSql();
		try {

			sqls.add(AdmTlScrollmsgmstSql.getUpdateSql(admTlScrollmsgmstSql.getSmsgDbFields(), newadmTlScrollmsgmst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return newadmTlScrollmsgmst;
	}
	
	@Override
	public AdmTlScrollmsgmst deleteMessageBoardNew(
			AdmTlScrollmsgmst newadmTlScrollmsgmst) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		AdmTlScrollmsgmstSql admTlScrollmsgmstSql = new AdmTlScrollmsgmstSql(); 
		
			CommonMessage.debugMsg("sql.......");
			sqls.add(AdmTlScrollmsgmstSql.getDeleteSql(admTlScrollmsgmstSql.getSmsgDbFields(), newadmTlScrollmsgmst.getSaveArray()));
			CommonMessage.debugMsg("sql......."+sqls);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
			dbActionTemplate.executeStatement(AdmTlScrollmsgmstSql.getDeleteSql(admTlScrollmsgmstSql.getSmsgDbFields(), newadmTlScrollmsgmst.getSaveArray()));
			
		
		return newadmTlScrollmsgmst;
	
	}

	@Override
	public List<String[]> FillControlData(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = AdmTlScrollmsgmstSql.selectData(keyid);
		CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;

	}

	@Override
	public String Functionallocn(String flid) throws Exception {
		// TODO Auto-generated method stub
		return dbActionTemplate.getSingleValue("GEN_MV_FLIDHIERARCHY", "FNLN_DESCRIPTION", "FLID", flid);
	}
	public String FunctionallocnDMTID(String DMTId)throws Exception{
		String DMTid="SELECT SECT_NAME FROM GEN_VW_FNLN WHERE FNLN_KEYID='"+DMTId+"'";
     	return dbActionTemplate.getSingleValue(DMTid);
	}
	
	public String FunctionallocnJHID(String Flid)throws Exception{
			 String sql="select CELL_NAME from gen_vw_fnln where fnln_keyid='"+Flid+"' ";
			 CommonMessage.debugMsg("Sql:"+sql);
			 return dbActionTemplate.getSingleValue(sql);
	}
	public String LocationName(String Location)throws Exception{
	    String sql="select LOCN_NAME from gen_vw_fnln where fnln_keyid='"+Location+"' ";
		CommonMessage.debugMsg("Sql:"+sql);
		return dbActionTemplate.getSingleValue(sql);
}
	public String getDmtFlid(String Flid)throws Exception{
		    String sql="SELECT FLID FROM GEN_MV_FLIDHIERARCHY WHERE FNLN_ORIGINALID='"+Flid+"' ";
			CommonMessage.debugMsg("Sql:"+sql);
			return dbActionTemplate.getSingleValue(sql);
		
	}

	public List<String[]> MOMReviewDetail(String flid,String QuarterFirst1,String QuarterEnd,String FromDate,String ToDate,String Finance)throws Exception{
		  StringBuilder sql2=new StringBuilder();
		  CommonMessage.debugMsg("The QuarterEnd"+QuarterEnd+"QuarterFirst1"+QuarterFirst1); 
		  if(Finance.equals("Y")){
		  sql2.append(" SELECT FNLN_DESCRIPTION AS FunctionLocation,MOMS_MEETINGNO AS MeetingNo, ");
		  sql2.append(" TO_CHAR(MOMS_DATE,'DD-MON-YYYY') AS MeetingDate,MOMD_DISCUSSION_DETAILS AS DiscussionDetails, ");
		  sql2.append(" EMPM_NAME Responsibility, TO_CHAR(APLD_TARGETDATE,'DD-MON-YYYY') Target,aplm_keyid AS ACTIONPLANNO,DECODE(apld_status,'C', 'COMPLETED','P', 'PENDING') AS Status12 ");
		  sql2.append(" FROM GEN_TL_MOMMST,GEN_TL_MOMDTL,GEN_TL_TPMPILLARMST,gen_mv_flidhierarchy,gen_tl_actionplanmst,gen_tl_actionplandtl, ");
		  sql2.append(" gen_tl_employeemst,gen_tl_trademst ");
		  sql2.append(" WHERE  APLM_KEYID = APLD_APLM_KEYID(+) AND trdm_keyid(+) = apld_tradeid ");
		  sql2.append("  AND  APLD_RESPONSIBILITY=empm_keyid(+) AND MOMS_FLID(+)=FLID ");
		  sql2.append(" AND MOMD_MOMS_KEYID(+) = MOMS_KEYID  AND TPMP_KEYID(+) = MOMD_PILLAR  AND MOMS_KEYID = APLM_MASTERREFID(+) ");
		  sql2.append(" AND MOMD_KEYID = APLM_DETAILREFID(+) AND MOMS_DATE BETWEEN TO_DATE('"+QuarterFirst1+"', 'Mon-YYYY') AND TO_DATE('"+QuarterEnd+"','Mon-YYYY') AND MOMS_FLID IN (SELECT flid  FROM gen_mv_flidhierarchy ");
		  sql2.append(" WHERE INSTR (parentflids || '-' || flid,'"+flid+"') > 0) ORDER BY MOMS_DATE DESC");
		  CommonMessage.debugMsg("The MOM Review Data Finance"+sql2.toString());
		  
		  }
		  else{
			  sql2.append(" SELECT FNLN_DESCRIPTION AS FunctionLocation,MOMS_MEETINGNO AS MeetingNo, ");
			  sql2.append(" TO_CHAR(MOMS_DATE,'DD-MON-YYYY') AS MeetingDate,MOMD_DISCUSSION_DETAILS AS DiscussionDetails, ");
			  sql2.append(" EMPM_NAME Responsibility, TO_CHAR(APLD_TARGETDATE,'DD-MON-YYYY') Target,aplm_keyid AS ACTIONPLANNO,DECODE(apld_status,'C', 'COMPLETED','P', 'PENDING') AS Status12 ");
			  sql2.append(" FROM GEN_TL_MOMMST,GEN_TL_MOMDTL,GEN_TL_TPMPILLARMST,gen_mv_flidhierarchy,gen_tl_actionplanmst,gen_tl_actionplandtl, ");
			  sql2.append(" gen_tl_employeemst,gen_tl_trademst ");
			  sql2.append(" WHERE  APLM_KEYID = APLD_APLM_KEYID(+) AND trdm_keyid(+) = apld_tradeid ");
			  sql2.append("  AND  APLD_RESPONSIBILITY=empm_keyid(+) AND MOMS_FLID(+)=FLID ");
			  sql2.append(" AND MOMD_MOMS_KEYID(+) = MOMS_KEYID  AND TPMP_KEYID(+) = MOMD_PILLAR  AND MOMS_KEYID = APLM_MASTERREFID(+) ");
			  sql2.append(" AND MOMD_KEYID = APLM_DETAILREFID(+) AND MOMS_DATE BETWEEN TO_DATE('"+FromDate+"', 'DD-Mon-YYYY') AND TO_DATE('"+ToDate+"','DD-Mon-YYYY') AND MOMS_FLID IN (SELECT flid  FROM gen_mv_flidhierarchy ");
			  sql2.append(" WHERE INSTR (parentflids || '-' || flid,'"+flid+"') > 0) ORDER BY MOMS_DATE DESC");
			  CommonMessage.debugMsg("The MOM Review Data"+sql2.toString());  
			  
		  }
		  List<String[]> MomReview=dbActionTemplate.getDataList(sql2.toString());
		  return MomReview;
	}	
	
	public List<String[]> TransactionDetail(String flid,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception{
		 
		StringBuilder sql2=new StringBuilder();
		if(Finance.equals("Y")){
		  sql2.append(" SELECT MNTH, ");
		  sql2.append(" TO_CHAR(NVL (SUM (DECODE (ENTRYTYPE, 'SUG', TRNCNT)), 0)) SUGGESTION,  ");
		  sql2.append(" TO_CHAR(NVL (SUM (DECODE (ENTRYTYPE, 'SUGAPP', TRNCNT)), 0)) SUGGESTIONAPPROVED, ");
		  sql2.append(" TO_CHAR(NVL (SUM (DECODE (ENTRYTYPE, 'KZN', TRNCNT)), 0))  KAIZEN,  ");
		  sql2.append(" TO_CHAR(NVL (SUM (DECODE (ENTRYTYPE, 'OPL', TRNCNT)), 0))  OPL,  ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'ABN', TRNCNT)), 0)) ABNORMALITY, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'MOM', TRNCNT)), 0)) MEETINGS, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'NEARMISS', TRNCNT)), 0)) NEARMISS, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'WHYWHY', TRNCNT)), 0)) WHYWHY, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'ACTIONPLAN', TRNCNT)), 0)) ACTIONPLAN, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'FISHBONE', TRNCNT)), 0)) FISHBONE, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'VISUALCONTROL', TRNCNT)), 0)) VISUALCONTROL ");
		  sql2.append("  FROM ( SELECT 'SUG' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT, TO_CHAR(KZBN_DATE,'MON-YYYY') MNTH FROM KZN_TL_KAIZENBANKMST, GEN_MV_FLIDHIERARCHY WHERE KZBN_FLID=FLID ");
		  sql2.append("  AND TRUNC (KZBN_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,KZBN_DATE UNION ALL "); 
		  sql2.append("  SELECT 'SUGAPP' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(KZBN_DATE,'MON-YYYY') MNTH FROM KZN_TL_KAIZENBANKMST, GEN_MV_FLIDHIERARCHY WHERE KZBN_FLID=FLID ");
		  sql2.append(" AND KZBN_STATUS='V' AND TRUNC (KZBN_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,KZBN_DATE UNION ALL ");
		  sql2.append("  SELECT 'KZN' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(KZNM_DATE,'MON-YYYY') MNTH FROM KZN_TL_MST, GEN_MV_FLIDHIERARCHY WHERE KZNM_FLID=FLID ");
		  sql2.append("  AND TRUNC (KZNM_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,KZNM_DATE UNION ALL ");
		  sql2.append("  SELECT 'OPL' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(OPLM_DATE,'MON-YYYY') MNTH  FROM OPL_TL_MST, GEN_MV_FLIDHIERARCHY WHERE OPLM_FLID=FLID ");
		  sql2.append("  AND TRUNC (OPLM_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,OPLM_DATE UNION ALL ");
		  sql2.append("  SELECT 'ABN' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(ABNM_DETECTIONDATE,'MON-YYYY') MNTH FROM ABN_TL_ABNORMALITY, GEN_MV_FLIDHIERARCHY WHERE ABNM_FLID=FLID AND ABNM_ACTIVE = 'Y'");
		  sql2.append("  AND TRUNC (ABNM_DETECTIONDATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,ABNM_DETECTIONDATE UNION ALL ");
		  sql2.append("  SELECT 'MOM' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(MOMS_DATE,'MON-YYYY') MNTH FROM GEN_TL_MOMMST, GEN_MV_FLIDHIERARCHY WHERE MOMS_FLID=FLID ");
		  sql2.append("  AND TRUNC (MOMS_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,MOMS_DATE UNION ALL ");
		  sql2.append("  SELECT 'NEARMISS' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(NMRN_OCCURRENCEDATETIME,'MON-YYYY') MNTH FROM GEN_TL_NEARMISSREPORTMSTNEW, GEN_MV_FLIDHIERARCHY WHERE NMRN_FLNID=FLID ");
		  sql2.append("  AND TRUNC (NMRN_OCCURRENCEDATETIME) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,NMRN_OCCURRENCEDATETIME UNION ALL ");  
		  sql2.append("  SELECT 'WHYWHY' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(WWMS_DATE,'MON-YYYY') MNTH  FROM BDM_TL_WHYWHYMST, GEN_MV_FLIDHIERARCHY WHERE WWMS_FLID=FLID ");
		  sql2.append("  AND TRUNC (WWMS_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,WWMS_DATE UNION ALL ");
		  sql2.append("  SELECT 'ACTIONPLAN' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT, TO_CHAR(APLM_PLANDATE,'MON-YYYY') MNTH FROM GEN_TL_ACTIONPLANMST, GEN_MV_FLIDHIERARCHY WHERE APLM_FLID=FLID ");
		  sql2.append("  AND TRUNC (APLM_PLANDATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");  
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,APLM_PLANDATE UNION ALL ");  
		  sql2.append("  SELECT 'FISHBONE' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(FISM_PREPAREDDATE,'MON-YYYY') MNTH FROM GEN_TL_FISHBONEMST, GEN_MV_FLIDHIERARCHY WHERE FISM_FLID=FLID ");
		  sql2.append(" AND TRUNC (FISM_PREPAREDDATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,FISM_PREPAREDDATE UNION ALL ");
		  sql2.append("  SELECT 'VISUALCONTROL' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(VCCL_DATE,'MON-YYYY') MNTH FROM GEN_TL_VISUALCONTROLCHECKLIST, GEN_MV_FLIDHIERARCHY WHERE VCCL_FLID=FLID ");
		  sql2.append(" AND TRUNC (VCCL_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,VCCL_DATE) GROUP BY MNTH ORDER BY TO_DATE(MNTH,'MON-YYYY') ");
		  CommonMessage.debugMsg("The Transaction Data Finance"+sql2.toString());
	}
	else{
		  sql2.append(" SELECT MNTH, ");
		  sql2.append(" TO_CHAR(NVL (SUM (DECODE (ENTRYTYPE, 'SUG', TRNCNT)), 0)) SUGGESTION,  ");
		  sql2.append(" TO_CHAR(NVL (SUM (DECODE (ENTRYTYPE, 'SUGAPP', TRNCNT)), 0)) SUGGESTIONAPPROVED, ");
		  sql2.append(" TO_CHAR(NVL (SUM (DECODE (ENTRYTYPE, 'KZN', TRNCNT)), 0))  KAIZEN,  ");
		  sql2.append(" TO_CHAR(NVL (SUM (DECODE (ENTRYTYPE, 'OPL', TRNCNT)), 0))  OPL,  ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'ABN', TRNCNT)), 0)) ABNORMALITY, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'MOM', TRNCNT)), 0)) MEETINGS, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'NEARMISS', TRNCNT)), 0)) NEARMISS, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'WHYWHY', TRNCNT)), 0)) WHYWHY, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'ACTIONPLAN', TRNCNT)), 0)) ACTIONPLAN, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'FISHBONE', TRNCNT)), 0)) FISHBONE, ");
		  sql2.append(" TO_CHAR (NVL (SUM (DECODE (ENTRYTYPE, 'VISUALCONTROL', TRNCNT)), 0)) VISUALCONTROL ");
		  sql2.append("  FROM ( SELECT 'SUG' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT, TO_CHAR(KZBN_DATE,'MON-YYYY') MNTH FROM KZN_TL_KAIZENBANKMST, GEN_MV_FLIDHIERARCHY WHERE KZBN_FLID=FLID ");
		  sql2.append("  AND TRUNC (KZBN_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,KZBN_DATE UNION ALL ");  
		  sql2.append("  SELECT 'SUGAPP' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(KZBN_DATE,'MON-YYYY') MNTH FROM KZN_TL_KAIZENBANKMST, GEN_MV_FLIDHIERARCHY WHERE KZBN_FLID=FLID ");
		  sql2.append(" AND KZBN_STATUS='V' AND TRUNC (KZBN_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,KZBN_DATE UNION ALL ");
		  sql2.append("  SELECT 'KZN' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(KZNM_DATE,'MON-YYYY') MNTH FROM KZN_TL_MST, GEN_MV_FLIDHIERARCHY WHERE KZNM_FLID=FLID ");
		  sql2.append("  AND TRUNC (KZNM_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,KZNM_DATE UNION ALL ");
		  sql2.append("  SELECT 'OPL' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(OPLM_DATE,'MON-YYYY') MNTH  FROM OPL_TL_MST, GEN_MV_FLIDHIERARCHY WHERE OPLM_FLID=FLID ");
		  sql2.append("  AND TRUNC (OPLM_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,OPLM_DATE UNION ALL ");
		  sql2.append("  SELECT 'ABN' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(ABNM_DETECTIONDATE,'MON-YYYY') MNTH FROM ABN_TL_ABNORMALITY, GEN_MV_FLIDHIERARCHY WHERE ABNM_FLID=FLID AND ABNM_ACTIVE = 'Y'");
		  sql2.append("  AND TRUNC (ABNM_DETECTIONDATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,ABNM_DETECTIONDATE UNION ALL ");
		  sql2.append("  SELECT 'MOM' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(MOMS_DATE,'MON-YYYY') MNTH FROM GEN_TL_MOMMST, GEN_MV_FLIDHIERARCHY WHERE MOMS_FLID=FLID ");
		  sql2.append("  AND TRUNC (MOMS_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,MOMS_DATE UNION ALL ");
		  sql2.append("  SELECT 'NEARMISS' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(NMRN_OCCURRENCEDATETIME,'MON-YYYY') MNTH FROM GEN_TL_NEARMISSREPORTMSTNEW, GEN_MV_FLIDHIERARCHY WHERE NMRN_FLNID=FLID ");
		  sql2.append("  AND TRUNC (NMRN_OCCURRENCEDATETIME) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,NMRN_OCCURRENCEDATETIME UNION ALL ");  
		  sql2.append("  SELECT 'WHYWHY' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(WWMS_DATE,'MON-YYYY') MNTH  FROM BDM_TL_WHYWHYMST, GEN_MV_FLIDHIERARCHY WHERE WWMS_FLID=FLID ");
		  sql2.append("  AND TRUNC (WWMS_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,WWMS_DATE UNION ALL ");
		  sql2.append("  SELECT 'ACTIONPLAN' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT, TO_CHAR(APLM_PLANDATE,'MON-YYYY') MNTH FROM GEN_TL_ACTIONPLANMST, GEN_MV_FLIDHIERARCHY WHERE APLM_FLID=FLID ");
		  sql2.append("  AND TRUNC (APLM_PLANDATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");  
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,APLM_PLANDATE UNION ALL ");  
		  sql2.append("  SELECT 'FISHBONE' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(FISM_PREPAREDDATE,'MON-YYYY') MNTH FROM GEN_TL_FISHBONEMST, GEN_MV_FLIDHIERARCHY WHERE FISM_FLID=FLID ");
		  sql2.append(" AND TRUNC (FISM_PREPAREDDATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,FISM_PREPAREDDATE UNION ALL ");
		  sql2.append("  SELECT 'VISUALCONTROL' ENTRYTYPE,NVL (DMT, FNLN_DISPLAYCODE) AS SECT_NAME,JH AS CELL_NAME, ");
		  sql2.append(" COUNT (*) TRNCNT,TO_CHAR(VCCL_DATE,'MON-YYYY') MNTH FROM GEN_TL_VISUALCONTROLCHECKLIST, GEN_MV_FLIDHIERARCHY WHERE VCCL_FLID=FLID ");
		  sql2.append(" AND TRUNC (VCCL_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  ");
		  sql2.append("  AND INSTR (PARENTFLIDS || '/' || FLID,'"+flid+"') > 0 ");
		  sql2.append("GROUP BY FLID,DMT,JH,FNLN_DISPLAYCODE,VCCL_DATE) GROUP BY MNTH ORDER BY TO_DATE(MNTH,'MON-YYYY') ");
		  CommonMessage.debugMsg("The Transaction Data"+sql2.toString());
	     }
		  List<String[]> Transaction=dbActionTemplate.getDataList(sql2.toString());
		  return Transaction;
	}
	
	public List<String[]> EHSMAtrixDetail(String flid,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception{
		 StringBuilder sql=new StringBuilder();
		  
		 if(Finance.equals("Y")){
		 sql.append("select EHS_ID,EHS_METRIC,nvl(APR,'0'),nvl(MAY,'0'),nvl(JUN,'0'),nvl(JUL,'0'),nvl(AUG,'0'),nvl(SEP,'0'),nvl(OCT,'0'),nvl(NOV,'0'),nvl(DEC,'0'),nvl(JAN,'0'),nvl(FEB,'0'),nvl(MAR,'0') from( ");
		 sql.append("SELECT EHS_ID,EHS_METRIC,month,COUNT FROM(select 1 DATAORDER,'Incidents Identified' as Matrics, month,count from (");
		 sql.append(" select to_char(SINM_OCCURRED_DATE,'mm') month,count(*) count from SHE_TL_INCIDENTMSTNEW,GEN_MV_FLIDHIERARCHY ");
		 sql.append("where SINM_OCCURRED_DATE between '"+FYearStart+"' and '"+FYearEnd+"'  AND SINM_FLID=FLID AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0 ");
		 sql.append("group by to_char(SINM_OCCURRED_DATE,'mm')) group by month,count UNION  ");
		
		 sql.append("select 2 DATAORDER,'% of Incidents Closed' as Matrics, month,count from (");
		 sql.append(" select to_char(SINM_OCCURRED_DATE,'mm') month,count(*) count from SHE_TL_INCIDENTMSTNEW,GEN_MV_FLIDHIERARCHY ");
		 sql.append("where SINM_STATUS='C'  AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  AND SINM_FLID=FLID AND SINM_OCCURRED_DATE between '"+FYearStart+"' and '"+FYearEnd+"' ");
		 sql.append("group by to_char(SINM_OCCURRED_DATE,'mm')) group by month,count UNION  ");
		 
		 sql.append("select 3 DATAORDER,'Safety Suggestions Received' as Matrics, month,count from (");
		 sql.append(" select to_char(KZBN_DATE,'mm') month,count(*) count from KZN_TL_KAIZENBANKMST,GEN_MV_FLIDHIERARCHY ");
		 sql.append("where KZBN_PQCDSME='S'  AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0 AND KZBN_FLID=FLID AND KZBN_DATE between '"+FYearStart+"' and '"+FYearEnd+"' ");
		 sql.append("group by to_char(KZBN_DATE,'mm')) group by month,count UNION "); 
		 
		 sql.append("select 4 DATAORDER,'% Safety Suggestions Implemented' as Matrics, month,count from (");
		 sql.append(" select to_char(KZNM_DATE,'mm') month,count(*) count from KZN_TL_MST,GEN_MV_FLIDHIERARCHY ");
		 sql.append("where  KZNM_RESULTAREA='S'  AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  AND KZNM_FLID=FLID AND KZNM_DATE between '"+FYearStart+"' and '"+FYearEnd+"' ");
		 sql.append("group by to_char(KZNM_DATE,'mm')) group by month,count UNION "); 
		
		 sql.append("select 5 DATAORDER,'Plant Inspection' as Matrics, month,count from (");
		 sql.append(" select to_char(SPSM_DATE,'mm') month,count(*) count from SHE_TL_PLANTSAFETYMST,GEN_MV_FLIDHIERARCHY ");
		 sql.append("where SPSM_DATE  BETWEEN '"+FYearStart+"' and '"+FYearEnd+"' AND SPSM_FLID=FLID AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  ");
		 sql.append("group by to_char(SPSM_DATE,'mm')) group by month,count UNION ");
		 
		 sql.append("select 6 DATAORDER,'Planned Job Observation' as Matrics, month,count from (");
		 sql.append(" select to_char(PJOB_DATE,'mm') month,count(*) count from SHE_TL_PLANNEDJOBOBSERVATION,GEN_MV_FLIDHIERARCHY ");
		 sql.append("where PJOB_DATE between '"+FYearStart+"' and '"+FYearEnd+"' AND PJOB_FLID=FLID AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  ");
		 sql.append("group by to_char(PJOB_DATE,'mm')) group by month,count UNION ");
		 

		 sql.append("select 7 DATAORDER,'Risk Assessment' as Matrics, month,count from (");
		 sql.append(" select to_char(RASM_DATE,'mm') month,count(*) count from SHE_TL_RISKASSESSMENTMST,GEN_MV_FLIDHIERARCHY ");
		 sql.append("where RASM_DATE between '"+FYearStart+"' and '"+FYearEnd+"' AND RASM_FLID=FLID AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  ");
		 sql.append("group by to_char(RASM_DATE,'mm')) group by month,count UNION ");
		 
		 sql.append("select 8 DATAORDER,'SUSA' as Matrics, month,count from (");
		 sql.append(" select to_char(SUSN_DATE,'mm') month,count(*) count from GEN_TL_SUSAMSTNEW,GEN_MV_FLIDHIERARCHY ");
		 sql.append("where SUSN_DATE between '"+FYearStart+"' and '"+FYearEnd+"' AND SUSN_FLID=FLID AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  ");
		 sql.append("group by to_char(SUSN_DATE,'mm')) group by month,count UNION "); 
	
		 sql.append("select 9 DATAORDER,'NearMiss Identified' as Matrics, month,count from (");
		 sql.append(" select to_char(NMRN_OCCURRENCEDATETIME,'mm') month,count(*) count from GEN_TL_NEARMISSREPORTMSTNEW,GEN_MV_FLIDHIERARCHY ");
		 sql.append("where NMRN_OCCURRENCEDATETIME between '"+FYearStart+"' and '"+FYearEnd+"' AND NMRN_STATUS='P' AND NMRN_FLNID=FLID AND INSTR(PARENTFLIDS || FLID, '"+flid+"') > 0  ");
		 sql.append("group by to_char(NMRN_OCCURRENCEDATETIME,'mm')) group by month,count UNION "); 
		 
		 sql.append("select 10 DATAORDER,'NearMiss Closed' as Matrics, month,count from (");
		 sql.append(" select to_char(NMRN_OCCURRENCEDATETIME,'mm') month,count(*) count from GEN_TL_NEARMISSREPORTMSTNEW,GEN_MV_FLIDHIERARCHY ");
		 sql.append("where NMRN_OCCURRENCEDATETIME between '"+FYearStart+"' and '"+FYearEnd+"' AND NMRN_STATUS='C' AND NMRN_FLNID=FLID AND INSTR(PARENTFLIDS || FLID, '"+flid+"') > 0  ");
		 sql.append("group by to_char(NMRN_OCCURRENCEDATETIME,'mm')) group by month,count UNION "); 
	
		 sql.append("select 11 DATAORDER,'KPI' as Matrics, month,count from (");
		 sql.append(" select to_char(KAUK_CREATEDON,'mm') month,count(*) count from KPI_TL_ACTUAL,GEN_MV_FLIDHIERARCHY ");
		 sql.append("where KAUK_CREATEDON between '"+FYearStart+"' and '"+FYearEnd+"'  AND KAUK_DEPTID=FLID AND INSTR(PARENTFLIDS || FLID, '"+flid+"') > 0  ");
		 sql.append("group by to_char(KAUK_CREATEDON,'mm')) group by month,count), "); 
		 
		 sql.append(" EHS_TL_METRICS WHERE EHS_ID=DATAORDER(+))");
		 
		 sql.append("pivot (sum(count) for month in ('04' as APR,'05' as MAY,'06' as JUN,'07' as JUL,'08' as AUG,'09' as SEP,'10' AS OCT,'11' AS NOV,'12' AS DEC,'01' AS JAN,'02' AS FEB,'03' AS MAR)) ORDER BY EHS_ID,EHS_METRIC ");
		 CommonMessage.debugMsg("The EHS Matrics Data Finance"+sql.toString());
		 
		 }
		 else{
			 
			 CommonMessage.debugMsg("Else FromDate"+FromDate);
			 String Date1=FromDate.substring(3,6).toUpperCase();
			 CommonMessage.debugMsg("To Date"+ToDate);			  
			// sql.append("select EHS_ID,EHS_METRIC,nvl("+Date1+",'0') from( ");
			 sql.append("select EHS_ID,EHS_METRIC,nvl(APR,'0'),nvl(MAY,'0'),nvl(JUN,'0'),nvl(JUL,'0'),nvl(AUG,'0'),nvl(SEP,'0'),nvl(OCT,'0'),nvl(NOV,'0'),nvl(DEC,'0'),nvl(JAN,'0'),nvl(FEB,'0'),nvl(MAR,'0') from( ");

			// sql.append("select EHS_ID,EHS_METRIC,nvl(APR,'0'),nvl(MAY,'0'),nvl(JUN,'0'),nvl(JUL,'0'),nvl(AUG,'0'),nvl(SEP,'0'),nvl(OCT,'0'),nvl(NOV,'0'),nvl(DEC,'0'),nvl(JAN,'0'),nvl(FEB,'0'),nvl(MAR,'0') from( ");
			 sql.append("SELECT EHS_ID,EHS_METRIC,month,COUNT FROM(select 1 DATAORDER,'Incidents Identified' as Matrics, month,count from (");
			 sql.append(" select to_char(SINM_OCCURRED_DATE,'mm') month,count(*) count from SHE_TL_INCIDENTMSTNEW,GEN_MV_FLIDHIERARCHY ");
			 sql.append("where SINM_OCCURRED_DATE between '"+FromDate+"' and '"+ToDate+"'  AND SINM_FLID=FLID AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0 ");
			 sql.append("group by to_char(SINM_OCCURRED_DATE,'mm')) group by month,count UNION  ");
			
			 sql.append("select 2 DATAORDER,'% of Incidents Closed' as Matrics, month,count from (");
			 sql.append(" select to_char(SINM_OCCURRED_DATE,'mm') month,count(*) count from SHE_TL_INCIDENTMSTNEW,GEN_MV_FLIDHIERARCHY ");
			 sql.append("where SINM_STATUS='C'  AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  AND SINM_FLID=FLID AND SINM_OCCURRED_DATE between '"+FromDate+"' and '"+ToDate+"' ");
			 sql.append("group by to_char(SINM_OCCURRED_DATE,'mm')) group by month,count UNION  ");
			 
			 sql.append("select 3 DATAORDER,'Safety Suggestions Received' as Matrics, month,count from (");
			 sql.append(" select to_char(KZBN_DATE,'mm') month,count(*) count from KZN_TL_KAIZENBANKMST,GEN_MV_FLIDHIERARCHY ");
			 sql.append("where KZBN_PQCDSME='S'  AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0 AND KZBN_FLID=FLID AND KZBN_DATE between '"+FromDate+"' and '"+ToDate+"' ");
			 sql.append("group by to_char(KZBN_DATE,'mm')) group by month,count UNION "); 
			 
			 sql.append("select 4 DATAORDER,'% Safety Suggestions Implemented' as Matrics, month,count from (");
			 sql.append(" select to_char(KZNM_DATE,'mm') month,count(*) count from KZN_TL_MST,GEN_MV_FLIDHIERARCHY ");
			 sql.append("where  KZNM_RESULTAREA='S'  AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  AND KZNM_FLID=FLID AND KZNM_DATE between '"+FromDate+"' and '"+ToDate+"' ");
			 sql.append("group by to_char(KZNM_DATE,'mm')) group by month,count UNION "); 
			
			 sql.append("select 5 DATAORDER,'Plant Inspection' as Matrics, month,count from (");
			 sql.append(" select to_char(SPSM_DATE,'mm') month,count(*) count from SHE_TL_PLANTSAFETYMST,GEN_MV_FLIDHIERARCHY ");
			 sql.append("where SPSM_DATE  BETWEEN '"+FromDate+"' and '"+ToDate+"' AND SPSM_FLID=FLID AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  ");
			 sql.append("group by to_char(SPSM_DATE,'mm')) group by month,count UNION ");
			 
			 sql.append("select 6 DATAORDER,'Planned Job Observation' as Matrics, month,count from (");
			 sql.append(" select to_char(PJOB_DATE,'mm') month,count(*) count from SHE_TL_PLANNEDJOBOBSERVATION,GEN_MV_FLIDHIERARCHY ");
			 sql.append("where PJOB_DATE between '"+FromDate+"' and '"+ToDate+"' AND PJOB_FLID=FLID AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  ");
			 sql.append("group by to_char(PJOB_DATE,'mm')) group by month,count UNION ");
			 

			 sql.append("select 7 DATAORDER,'Risk Assessment' as Matrics, month,count from (");
			 sql.append(" select to_char(RASM_DATE,'mm') month,count(*) count from SHE_TL_RISKASSESSMENTMST,GEN_MV_FLIDHIERARCHY ");
			 sql.append("where RASM_DATE between '"+FromDate+"' and '"+ToDate+"' AND RASM_FLID=FLID AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  ");
			 sql.append("group by to_char(RASM_DATE,'mm')) group by month,count UNION ");
			 
			 sql.append("select 8 DATAORDER,'SUSA' as Matrics, month,count from (");
			 sql.append(" select to_char(SUSN_DATE,'mm') month,count(*) count from GEN_TL_SUSAMSTNEW,GEN_MV_FLIDHIERARCHY ");
			 sql.append("where SUSN_DATE between '"+FromDate+"' and '"+ToDate+"' AND SUSN_FLID=FLID AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0  ");
			 sql.append("group by to_char(SUSN_DATE,'mm')) group by month,count UNION "); 
		
			 sql.append("select 9 DATAORDER,'NearMiss Identified' as Matrics, month,count from (");
			 sql.append(" select to_char(NMRN_OCCURRENCEDATETIME,'mm') month,count(*) count from GEN_TL_NEARMISSREPORTMSTNEW,GEN_MV_FLIDHIERARCHY ");
			 sql.append("where NMRN_OCCURRENCEDATETIME between '"+FromDate+"' and '"+ToDate+"' AND NMRN_STATUS='P' AND NMRN_FLNID=FLID AND INSTR(PARENTFLIDS || FLID, '"+flid+"') > 0  ");
			 sql.append("group by to_char(NMRN_OCCURRENCEDATETIME,'mm')) group by month,count UNION "); 
			 
			 sql.append("select 10 DATAORDER,'NearMiss Closed' as Matrics, month,count from (");
			 sql.append(" select to_char(NMRN_OCCURRENCEDATETIME,'mm') month,count(*) count from GEN_TL_NEARMISSREPORTMSTNEW,GEN_MV_FLIDHIERARCHY ");
			 sql.append("where NMRN_OCCURRENCEDATETIME between '"+FromDate+"' and '"+ToDate+"' AND NMRN_STATUS='C' AND NMRN_FLNID=FLID AND INSTR(PARENTFLIDS || FLID, '"+flid+"') > 0  ");
			 sql.append("group by to_char(NMRN_OCCURRENCEDATETIME,'mm')) group by month,count UNION "); 
		
			 sql.append("select 11 DATAORDER,'KPI' as Matrics, month,count from (");
			 sql.append(" select to_char(KAUK_CREATEDON,'mm') month,count(*) count from KPI_TL_ACTUAL,GEN_MV_FLIDHIERARCHY ");
			 sql.append("where KAUK_CREATEDON between '"+FromDate+"' and '"+ToDate+"'  AND KAUK_DEPTID=FLID AND INSTR(PARENTFLIDS || FLID, '"+flid+"') > 0  ");
			 sql.append("group by to_char(KAUK_CREATEDON,'mm')) group by month,count), "); 
			 
			 sql.append(" EHS_TL_METRICS WHERE EHS_ID=DATAORDER(+))");
			 
		//	sql.append("pivot (sum(count) for month in ('01' as "+Date1+")) ORDER BY EHS_ID,EHS_METRIC ");
			 
			 sql.append("pivot (sum(count) for month in ('04' as APR,'05' as MAY,'06' as JUN,'07' as JUL,'08' as AUG,'09' as SEP,'10' AS OCT,'11' AS NOV,'12' AS DEC,'01' AS JAN,'02' AS FEB,'03' AS MAR)) ORDER BY EHS_ID,EHS_METRIC ");
			// CommonMessage.debugMsg("The EHS Matrics Data Finance"+sql.toString());

		//	 sql.append("pivot (sum(count) for month in ('04' as APR,'05' as MAY,'06' as JUN,'07' as JUL,'08' as AUG,'09' as SEP,'10' AS OCT,'11' AS NOV,'12' AS DEC,'01' AS JAN,'02' AS FEB,'03' AS MAR)) ORDER BY EHS_ID,EHS_METRIC ");
			 CommonMessage.debugMsg("The EHS Matrics Data Else Condition"+sql.toString());	 
		 }
		  List<String[]> Transaction=dbActionTemplate.getDataList(sql.toString());
		  return Transaction;
	}
	
	public List<String[]> 	KaizenStatusDetail(String flid,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception{
		  StringBuilder sql=new StringBuilder();
		if(Finance.equals("Y")){
		  sql.append("SELECT ResultType,KNRT_SORTNUMBER,nvl(APR,'0'),nvl(MAY,'0'),nvl(JUN,'0'),nvl(JUL,'0'),nvl(AUG,'0'),nvl(SEP,'0'), ");
		  sql.append("nvl(OCT,'0'),nvl(NOV,'0'),nvl(DEC,'0'),nvl(JAN,'0'),nvl(FEB,'0'),nvl(MAR,'0') FROM(SELECT KZNM_RESULTAREA AS ResultType,KNRT_SORTNUMBER, month, COUNT ");
		  sql.append(" FROM(SELECT TO_CHAR(KZNM_DATE,'mm') month, COUNT(*) COUNT,KZNM_RESULTAREA,KNRT_SORTNUMBER ");
		  sql.append("FROM KZN_TL_MST,GEN_TL_KAIZENRESULTTYPE,GEN_MV_FLIDHIERARCHY WHERE KZNM_FLID = FLID(+) AND KNRT_CODE(+)=KZNM_RESULTAREA AND INSTR(PARENTFLIDS || FLID,'"+flid+"') >0 ");
		  sql.append("AND KZNM_DATE BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  GROUP BY TO_CHAR (KZNM_DATE, 'mm'),KZNM_RESULTAREA,KNRT_SORTNUMBER) ");
		  sql.append("GROUP BY month, COUNT,KZNM_RESULTAREA,KNRT_SORTNUMBER) PIVOT (SUM (COUNT)   FOR MONTH IN ");     
		  sql.append("('04' AS APR,'05' AS MAY,'06' AS JUN, '07' AS JUL,'08' AS AUG, '09' AS SEP, '10' AS OCT, '11' AS NOV,'12' AS DEC,'01' AS JAN,'02' AS FEB,'03' AS MAR)) ORDER BY KNRT_SORTNUMBER");
		  CommonMessage.debugMsg("The Kaizen Status Data"+sql.toString());
		}
		else{
			  sql.append("SELECT ResultType,KNRT_SORTNUMBER,nvl(APR,'0'),nvl(MAY,'0'),nvl(JUN,'0'),nvl(JUL,'0'),nvl(AUG,'0'),nvl(SEP,'0'), ");
			  sql.append("nvl(OCT,'0'),nvl(NOV,'0'),nvl(DEC,'0'),nvl(JAN,'0'),nvl(FEB,'0'),nvl(MAR,'0') FROM(SELECT KZNM_RESULTAREA AS ResultType,KNRT_SORTNUMBER, month, COUNT ");
			  sql.append(" FROM(SELECT TO_CHAR(KZNM_DATE,'mm') month, COUNT(*) COUNT,KZNM_RESULTAREA,KNRT_SORTNUMBER ");
			  sql.append("FROM KZN_TL_MST,GEN_TL_KAIZENRESULTTYPE,GEN_MV_FLIDHIERARCHY WHERE KZNM_FLID = FLID(+) AND KNRT_CODE(+)=KZNM_RESULTAREA AND INSTR(PARENTFLIDS || FLID,'"+flid+"') >0 ");
			  sql.append("AND KZNM_DATE BETWEEN '"+FromDate+"' AND '"+ToDate+"'  GROUP BY TO_CHAR (KZNM_DATE, 'mm'),KZNM_RESULTAREA,KNRT_SORTNUMBER) ");
			  sql.append("GROUP BY month, COUNT,KZNM_RESULTAREA,KNRT_SORTNUMBER) PIVOT (SUM (COUNT)   FOR MONTH IN ");     
			  sql.append("('04' AS APR,'05' AS MAY,'06' AS JUN, '07' AS JUL,'08' AS AUG, '09' AS SEP, '10' AS OCT, '11' AS NOV,'12' AS DEC,'01' AS JAN,'02' AS FEB,'03' AS MAR)) ORDER BY KNRT_SORTNUMBER");
			  CommonMessage.debugMsg("The Kaizen Status Data"+sql.toString());	
		}
		  List<String[]> Transaction=dbActionTemplate.getDataList(sql.toString());
		  return Transaction;
	}
	public List<String[]> 	AbnormalityStatusDetail(String flid,String QuarterFirst1,String QuarterSecMonth,String QuarterEnd,String FYearStart,String FYearEnd,String Currentdate,String FromDate,String ToDate,String Finance)throws Exception{
		 StringBuilder sql=new StringBuilder(); 
		 String CurrentYear=CommonFunctions.getCurrentYear();
		 Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
		 if(Finance.equals("Y")){
		 sql.append("SELECT ABTM_NAME,NVL (TID_CNT, 0),NVL (TRF_CNT, 0),NVL (APRI_CNT, 0),NVL (APR_CNT, 0),NVL (MAYI_CNT, 0),");
		 sql.append("NVL (MAY_CNT, 0),NVL (JUNI_CNT, 0),NVL (JUN_CNT, 0),NVL (JULI_CNT, 0),NVL (JUL_CNT, 0),NVL (AUGI_CNT, 0),");
		 sql.append("NVL (AUG_CNT, 0),NVL (SEPTI_CNT, 0),NVL (SEP_CNT, 0),NVL (OCTI_CNT, 0),NVL (OCT_CNT, 0),");
		 sql.append("NVL (NOVI_CNT, 0),NVL (NOV_CNT, 0),NVL (DECI_CNT, 0),NVL (DEC_CNT, 0),NVL (JANI_CNT, 0),NVL (JAN_CNT, 0),");
		 sql.append("NVL (FEBI_CNT, 0),NVL (FEB_CNT, 0),NVL (MARI_CNT, 0),NVL (MAR_CNT, 0) ");
	     sql.append("FROM (SELECT * FROM (   SELECT distinct ABTM_NAME,'TillDateIdentified' AS RUNDATE,");
		 sql.append("COUNT (*)  as Instance FROM ABN_TL_TYPEMST,ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY ");
		 sql.append(" WHERE 1=1 AND ABNM_FLID=FLID AND ABNM_STATUS IN ('P', 'C', 'W') AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0 ");
		 sql.append("AND ABNM_TYPEID=abtm_keyid AND ABTM_NAME NOT IN ('-') AND  ");
		 sql.append("TRUNC(ABNM_DETECTIONDATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"' GROUP BY ABTM_NAME ");
		 sql.append("UNION ALL SELECT distinct ABTM_NAME,'TillDateCompleted' AS RUNDATE, COUNT(*) as Instance ");
		 sql.append("FROM ABN_TL_TYPEMST,ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY WHERE 1=1 and ABNM_FLID=FLID AND ABNM_STATUS = 'C' ");
		 sql.append("AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0 AND ABNM_TYPEID = abtm_keyid AND ABTM_NAME NOT IN ('-') ");
		 sql.append("AND TRUNC (ABNM_WOENDTIME) BETWEEN '"+FYearStart+"'AND '"+FYearEnd+"' GROUP BY ABTM_NAME ");
		 sql.append("UNION ALL SELECT ABTM_NAME,RUNDATE,SUM(INSTANCE) FROM (SELECT DISTINCT ABTM_NAME,ABNM_KEYID,TO_CHAR (ABNM_DETECTIONDATE, 'Mon-YYYY') AS RUNDATE, ");
		 sql.append("COUNT (*) AS INSTANCE FROM ABN_TL_TYPEMST,ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY WHERE ABNM_FLID=FLID(+) ");
		 sql.append("AND ABNM_REFDOCTYPE <> 'SHE' AND ABTM_NAME NOT IN ('-')  AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0 ");
		 sql.append("AND ABNM_TYPEID = abtm_keyid(+) AND TRUNC(ABNM_DETECTIONDATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"' ");
		 sql.append("GROUP BY ABNM_FLID,ABNM_DETECTIONDATE,ABTM_NAME,ABNM_KEYID,TO_CHAR (ABNM_DETECTIONDATE, 'Mon-YYYY'))GROUP BY ABTM_NAME,RUNDATE ");
		 sql.append("UNION ALL SELECT ABTM_NAME AS ABNType, RUNDATE, sum(Instance) FROM (SELECT DISTINCT ABTM_NAME,ABNM_KEYID,TO_CHAR (ABNM_WOENDTIME, 'MM') RUNDATE,");
		 sql.append("COUNT (*) AS Instance FROM ABN_TL_TYPEMST,ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY  WHERE ABNM_FLID=FLID(+) ");
		 sql.append("AND ABNM_REFDOCTYPE <> 'SHE' AND ABTM_NAME NOT IN ('-') AND INSTR (PARENTFLIDS || FLID,'"+flid+"')> 0 AND ABNM_TYPEID=abtm_keyid(+) ");
		 sql.append("AND ABNM_STATUS='C' AND TRUNC (ABNM_WOENDTIME) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"' ");
		 sql.append("GROUP BY ABNM_FLID,ABNM_WOENDTIME,ABTM_NAME,ABNM_KEYID,TO_CHAR (ABNM_WOENDTIME, 'Mon-YYYY')) ");
		 sql.append("GROUP BY RUNDATE, ABTM_NAME) PIVOT (SUM(NVL(INSTANCE,0)) AS CNT FOR (RUNDATE) IN ('TillDateIdentified' AS TID, 'TillDateCompleted' AS TRF, ");
		 sql.append(" 'APR-"+PreviousYear+"' AS APRI,'04' AS APR, 'MAY-"+PreviousYear+"' AS MAYI,'05' AS MAY,'JUN-"+PreviousYear+"' AS JUNI,'06' AS JUN, 'JUL-"+PreviousYear+"' AS JULI,'07' AS JUL, ");
		 sql.append("'Aug-"+PreviousYear+"' AS AUGI,'08' AS AUG,'SEP-"+PreviousYear+"' AS SEPTI,'09' AS SEP,'Oct-"+PreviousYear+"' AS OCTI, '10' AS OCT, ");
		 sql.append("'Nov-"+PreviousYear+"' AS NOVI, '11' AS NOV,'Dec-"+PreviousYear+"' AS DECI, '12' AS DEC,'Jan-"+CurrentYear+"' AS JANI,'01' AS JAN,");
		 sql.append("'Feb-"+CurrentYear+"' AS FEBI, '02' AS FEB,'Mar-"+CurrentYear+"' AS MARI,'03' AS MAR)))");
		 
		 CommonMessage.debugMsg("The Abnormality Status Data"+sql.toString());
		 }
		 else{
			 sql.append("SELECT ABTM_NAME,NVL (TID_CNT, 0),NVL (TRF_CNT, 0),NVL (APRI_CNT, 0),NVL (APR_CNT, 0),NVL (MAYI_CNT, 0),");
			 sql.append("NVL (MAY_CNT, 0),NVL (JUNI_CNT, 0),NVL (JUN_CNT, 0),NVL (JULI_CNT, 0),NVL (JUL_CNT, 0),NVL (AUGI_CNT, 0),");
			 sql.append("NVL (AUG_CNT, 0),NVL (SEPTI_CNT, 0),NVL (SEP_CNT, 0),NVL (OCTI_CNT, 0),NVL (OCT_CNT, 0),");
			 sql.append("NVL (NOVI_CNT, 0),NVL (NOV_CNT, 0),NVL (DECI_CNT, 0),NVL (DEC_CNT, 0),NVL (JANI_CNT, 0),NVL (JAN_CNT, 0),");
			 sql.append("NVL (FEBI_CNT, 0),NVL (FEB_CNT, 0),NVL (MARI_CNT, 0),NVL (MAR_CNT, 0) ");
		     sql.append("FROM (SELECT * FROM (   SELECT distinct ABTM_NAME,'TillDateIdentified' AS RUNDATE,");
			 sql.append("COUNT (*)  as Instance FROM ABN_TL_TYPEMST,ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY ");
			 sql.append(" WHERE 1=1 AND ABNM_FLID=FLID AND ABNM_STATUS IN ('P', 'C', 'W') AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0 ");
			 sql.append("AND ABNM_TYPEID=abtm_keyid AND ABTM_NAME NOT IN ('-') AND  ");
			 sql.append("TRUNC(ABNM_DETECTIONDATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"' GROUP BY ABTM_NAME ");
			 sql.append("UNION ALL SELECT distinct ABTM_NAME,'TillDateCompleted' AS RUNDATE, COUNT(*) as Instance ");
			 sql.append("FROM ABN_TL_TYPEMST,ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY WHERE 1=1 and ABNM_FLID=FLID AND ABNM_STATUS = 'C' ");
			 sql.append("AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0 AND ABNM_TYPEID = abtm_keyid AND ABTM_NAME NOT IN ('-') ");
			 sql.append("AND TRUNC (ABNM_WOENDTIME) BETWEEN '"+FromDate+"'AND '"+ToDate+"' GROUP BY ABTM_NAME ");
			 sql.append("UNION ALL SELECT ABTM_NAME,RUNDATE,SUM(INSTANCE) FROM (SELECT DISTINCT ABTM_NAME,ABNM_KEYID,TO_CHAR (ABNM_DETECTIONDATE, 'Mon-YYYY') AS RUNDATE, ");
			 sql.append("COUNT (*) AS INSTANCE FROM ABN_TL_TYPEMST,ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY WHERE ABNM_FLID=FLID(+) ");
			 sql.append("AND ABNM_REFDOCTYPE <> 'SHE' AND ABTM_NAME NOT IN ('-')  AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0 ");
			 sql.append("AND ABNM_TYPEID = abtm_keyid(+) AND TRUNC(ABNM_DETECTIONDATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"' ");
			 sql.append("GROUP BY ABNM_FLID,ABNM_DETECTIONDATE,ABTM_NAME,ABNM_KEYID,TO_CHAR (ABNM_DETECTIONDATE, 'Mon-YYYY'))GROUP BY ABTM_NAME,RUNDATE ");
			 sql.append("UNION ALL SELECT ABTM_NAME AS ABNType, RUNDATE, sum(Instance) FROM (SELECT DISTINCT ABTM_NAME,ABNM_KEYID,TO_CHAR (ABNM_WOENDTIME, 'MM') RUNDATE,");
			 sql.append("COUNT (*) AS Instance FROM ABN_TL_TYPEMST,ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY  WHERE ABNM_FLID=FLID(+) ");
			 sql.append("AND ABNM_REFDOCTYPE <> 'SHE' AND ABTM_NAME NOT IN ('-') AND INSTR (PARENTFLIDS || FLID,'"+flid+"')> 0 AND ABNM_TYPEID=abtm_keyid(+) ");
			 sql.append("AND ABNM_STATUS='C' AND TRUNC (ABNM_WOENDTIME) BETWEEN '"+FromDate+"' AND '"+ToDate+"' ");
			 sql.append("GROUP BY ABNM_FLID,ABNM_WOENDTIME,ABTM_NAME,ABNM_KEYID,TO_CHAR (ABNM_WOENDTIME, 'Mon-YYYY')) ");
			 sql.append("GROUP BY RUNDATE, ABTM_NAME) PIVOT (SUM(NVL(INSTANCE,0)) AS CNT FOR (RUNDATE) IN ('TillDateIdentified' AS TID, 'TillDateCompleted' AS TRF, ");
			 sql.append(" 'APR-"+PreviousYear+"' AS APRI,'04' AS APR, 'MAY-"+PreviousYear+"' AS MAYI,'05' AS MAY,'JUN-"+PreviousYear+"' AS JUNI,'06' AS JUN, 'JUL-"+PreviousYear+"' AS JULI,'07' AS JUL, ");
			 sql.append("'Aug-"+PreviousYear+"' AS AUGI,'08' AS AUG,'SEP-"+PreviousYear+"' AS SEPTI,'09' AS SEP,'Oct-"+PreviousYear+"' AS OCTI, '10' AS OCT, ");
			 sql.append("'Nov-"+PreviousYear+"' AS NOVI, '11' AS NOV,'Dec-"+PreviousYear+"' AS DECI, '12' AS DEC,'Jan-"+CurrentYear+"' AS JANI,'01' AS JAN,");
			 sql.append("'Feb-"+CurrentYear+"' AS FEBI, '02' AS FEB,'Mar-"+CurrentYear+"' AS MARI,'03' AS MAR)))");
			 
			 CommonMessage.debugMsg("The Abnormality Status Data"+sql.toString());
				 
		 }
		 List<String[]> AbnormalityStatus=dbActionTemplate.getDataList(sql.toString());
		 return AbnormalityStatus;
	}
		
	public List<String[]> 	AetAdherence(String DmtOriginalId,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception{
		StringBuilder sql=new StringBuilder();
		if(Finance.equals("Y")){
		sql.append("SELECT DISTINCT EMPLOYEE,EMPLOYEECODE,TO_CHAR (MEETINGS),");
		sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'P', CNT, 0))) PRESENT, ");
		sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'A', CNT, 0))) ABSENT, ");
		sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'L', CNT, 0))) LEAVE, ");
		sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'W', CNT, 0))) WEEKLYOFF, ");
		sql.append(" TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'D', CNT, 0))) ONDUTY, ");
		sql.append(" TO_CHAR (ROUND (SUM (DECODE (MOMA_ATTANDANCE,'P', CNT,'D', CNT, 0))/ DECODE (MEETINGS, 0, 1, MEETINGS)* 100, 2)) Per ");
		sql.append("FROM (SELECT DISTINCT EMPM_NAME EMPLOYEE, EMPM_CODE EMPLOYEECODE,MOMA_ATTANDANCE,MOMA_DATE, COUNT(MOMS_DATE)  OVER(PARTITION BY MOMA_EMPLOYEEID) MEETINGS, ");
		sql.append("COUNT(DISTINCT MOMS_DATE)  OVER (PARTITION BY MOMA_EMPLOYEEID, MOMS_DATE) CNT  FROM GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM,(SELECT DISTINCT MOMS_DATE,");
		sql.append("   MOMA_EMPLOYEEID, MOMA_ATTANDANCE,MOMS_FLID, MOMA_DATE  FROM GEN_TL_MOMMST M1,  GEN_TL_MOMATTENDANCE M2  ");
		sql.append(" WHERE M1.MOMS_KEYID=M2.MOMA_MOMS_KEYID  AND moma_attandance <> '-'   AND MOMS_MEETINGTYPE='D' ");
		sql.append(" AND TRUNC (MOMS_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  AND MOMS_FLID ='"+DmtOriginalId+"' ) ");
		sql.append("WHERE 1=1 AND EMPM_ACTIVE = 'Y' AND MOMS_FLID=FRT_FNLN_KEYID AND FRT_EMPM_KEYID=MOMA_EMPLOYEEID AND EMPM_KEYID = MOMA_EMPLOYEEID  AND MOMA_DATE IS NOT NULL  AND TRUNC (MOMA_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"') ");
		sql.append("GROUP BY EMPLOYEE, EMPLOYEECODE, MEETINGS ORDER BY  EMPLOYEE ");
		CommonMessage.debugMsg("The Aet Adherence Data Finance"+sql.toString());

		}
		else{
			sql.append("SELECT DISTINCT EMPLOYEE,EMPLOYEECODE,TO_CHAR (MEETINGS),");
			sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'P', CNT, 0))) PRESENT, ");
			sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'A', CNT, 0))) ABSENT, ");
			sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'L', CNT, 0))) LEAVE, ");
			sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'W', CNT, 0))) WEEKLYOFF, ");
			sql.append(" TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'D', CNT, 0))) ONDUTY, ");
			sql.append(" TO_CHAR (ROUND (SUM (DECODE (MOMA_ATTANDANCE,'P', CNT,'D', CNT, 0))/ DECODE (MEETINGS, 0, 1, MEETINGS)* 100, 2)) Per ");
			sql.append("FROM (SELECT DISTINCT EMPM_NAME EMPLOYEE, EMPM_CODE EMPLOYEECODE,MOMA_ATTANDANCE,MOMA_DATE, COUNT(MOMS_DATE)  OVER(PARTITION BY MOMA_EMPLOYEEID) MEETINGS, ");
			sql.append("COUNT(DISTINCT MOMS_DATE)  OVER (PARTITION BY MOMA_EMPLOYEEID, MOMS_DATE) CNT  FROM GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM,(SELECT DISTINCT MOMS_DATE,");
			sql.append("   MOMA_EMPLOYEEID, MOMA_ATTANDANCE,MOMS_FLID, MOMA_DATE  FROM GEN_TL_MOMMST M1,  GEN_TL_MOMATTENDANCE M2  ");
			sql.append(" WHERE M1.MOMS_KEYID=M2.MOMA_MOMS_KEYID  AND moma_attandance <> '-'   AND MOMS_MEETINGTYPE='D' ");
			sql.append(" AND TRUNC (MOMS_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  AND MOMS_FLID ='"+DmtOriginalId+"' ) ");
			sql.append("WHERE 1=1 AND EMPM_ACTIVE = 'Y' AND MOMS_FLID=FRT_FNLN_KEYID AND FRT_EMPM_KEYID=MOMA_EMPLOYEEID AND EMPM_KEYID = MOMA_EMPLOYEEID  AND MOMA_DATE IS NOT NULL  AND TRUNC (MOMA_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"') ");
			sql.append("GROUP BY EMPLOYEE, EMPLOYEECODE, MEETINGS ORDER BY  EMPLOYEE ");
			CommonMessage.debugMsg("The Aet Adherence Data"+sql.toString());	
		}
		
		List<String[]> AdherenceData=dbActionTemplate.getDataList(sql.toString());
		return AdherenceData;
	}

  public List<String[]> PactAdherence(String flid,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception{
	StringBuilder sql=new StringBuilder();
	if(Finance.equals("Y")){
	sql.append("SELECT DISTINCT EMPLOYEE,EMPLOYEECODE,TO_CHAR (MEETINGS),");
	sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'P', CNT, 0))) PRESENT, ");
	sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'A', CNT, 0))) ABSENT, ");
	sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'L', CNT, 0))) LEAVE, ");
	sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'W', CNT, 0))) WEEKLYOFF, ");
	sql.append(" TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'D', CNT, 0))) ONDUTY, ");
	sql.append(" TO_CHAR (ROUND (SUM (DECODE (MOMA_ATTANDANCE,'P', CNT,'D', CNT, 0))/ DECODE (MEETINGS, 0, 1, MEETINGS)* 100, 2)) Per ");
	sql.append("FROM (SELECT DISTINCT EMPM_NAME EMPLOYEE, EMPM_CODE EMPLOYEECODE,MOMA_ATTANDANCE,MOMA_DATE, COUNT(MOMS_DATE)  OVER(PARTITION BY MOMA_EMPLOYEEID) MEETINGS, ");
	sql.append("COUNT(DISTINCT MOMS_DATE)  OVER (PARTITION BY MOMA_EMPLOYEEID, MOMS_DATE) CNT  FROM GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM,(SELECT DISTINCT MOMS_DATE,");
	sql.append("   MOMA_EMPLOYEEID, MOMA_ATTANDANCE,MOMS_FLID, MOMA_DATE  FROM GEN_TL_MOMMST M1,  GEN_TL_MOMATTENDANCE M2  ");
	sql.append(" WHERE M1.MOMS_KEYID=M2.MOMA_MOMS_KEYID  AND moma_attandance <> '-'   AND MOMS_MEETINGTYPE='J' ");
	sql.append(" AND TRUNC (MOMS_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"'  AND MOMS_FLID ='"+flid+"' ) ");
	sql.append("WHERE 1=1 AND EMPM_ACTIVE = 'Y'  AND MOMS_FLID=FRT_FNLN_KEYID AND FRT_EMPM_KEYID=MOMA_EMPLOYEEID AND EMPM_KEYID = MOMA_EMPLOYEEID  AND MOMA_DATE IS NOT NULL  AND TRUNC (MOMA_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"') ");
	sql.append("GROUP BY EMPLOYEE, EMPLOYEECODE, MEETINGS ORDER BY  EMPLOYEE ");
	CommonMessage.debugMsg("The Pact Adherence Data Finance"+sql.toString());
	}
	else{
		sql.append("SELECT DISTINCT EMPLOYEE,EMPLOYEECODE,TO_CHAR (MEETINGS),");
		sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'P', CNT, 0))) PRESENT, ");
		sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'A', CNT, 0))) ABSENT, ");
		sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'L', CNT, 0))) LEAVE, ");
		sql.append("TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'W', CNT, 0))) WEEKLYOFF, ");
		sql.append(" TO_CHAR (SUM (DECODE (MOMA_ATTANDANCE, 'D', CNT, 0))) ONDUTY, ");
		sql.append(" TO_CHAR (ROUND (SUM (DECODE (MOMA_ATTANDANCE,'P', CNT,'D', CNT, 0))/ DECODE (MEETINGS, 0, 1, MEETINGS)* 100, 2)) Per ");
		sql.append("FROM (SELECT DISTINCT EMPM_NAME EMPLOYEE, EMPM_CODE EMPLOYEECODE,MOMA_ATTANDANCE,MOMA_DATE, COUNT(MOMS_DATE)  OVER(PARTITION BY MOMA_EMPLOYEEID) MEETINGS, ");
		sql.append("COUNT(DISTINCT MOMS_DATE)  OVER (PARTITION BY MOMA_EMPLOYEEID, MOMS_DATE) CNT  FROM GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM,(SELECT DISTINCT MOMS_DATE,");
		sql.append("   MOMA_EMPLOYEEID, MOMA_ATTANDANCE,MOMS_FLID, MOMA_DATE  FROM GEN_TL_MOMMST M1,  GEN_TL_MOMATTENDANCE M2  ");
		sql.append(" WHERE M1.MOMS_KEYID=M2.MOMA_MOMS_KEYID  AND moma_attandance <> '-'   AND MOMS_MEETINGTYPE='J' ");
		sql.append(" AND TRUNC (MOMS_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"'  AND MOMS_FLID ='"+flid+"' ) ");
		sql.append("WHERE 1=1 AND EMPM_ACTIVE = 'Y'  AND MOMS_FLID=FRT_FNLN_KEYID AND FRT_EMPM_KEYID=MOMA_EMPLOYEEID AND EMPM_KEYID = MOMA_EMPLOYEEID  AND MOMA_DATE IS NOT NULL  AND TRUNC (MOMA_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"') ");
		sql.append("GROUP BY EMPLOYEE, EMPLOYEECODE, MEETINGS ORDER BY  EMPLOYEE ");
		CommonMessage.debugMsg("The Pact Adherence Data"+sql.toString());
	}
	List<String[]> AdherenceData=dbActionTemplate.getDataList(sql.toString());
	return AdherenceData;	
}
  

	public List<String[]> AbnormalityPendingDetail(String flid,String FYearStart,String FYearEnd,String JHkeyid,String FromDate,String ToDate,String Finance)throws Exception{
		StringBuilder sql=new StringBuilder();
		CommonMessage.debugMsg("JHkeyid"+JHkeyid);
		if(Finance.equals("Y")){
		  if(UIUtils.isValidKeyId(JHkeyid)){
			   sql.append("SELECT JH,SUM(day1),SUM (day2),SUM (day3),SUM(day4) FROM (SELECT ABNM_FLID,DMT,JH,");
			    sql.append("TO_CHAR(CASE WHEN (ElapsedDays<30) THEN 1 ELSE 0 END)Day1,");
			    sql.append("TO_CHAR(CASE WHEN (ElapsedDays>=30 AND ElapsedDays<60) THEN 1 ELSE 0 END)Day2,");
			    sql.append("TO_CHAR(CASE WHEN (ElapsedDays>=60 AND ElapsedDays<90) THEN 1 ELSE 0 END)Day3,");
			    sql.append("TO_CHAR (CASE WHEN (ElapsedDays>=90) THEN 1 ELSE 0 END) Day4 ");
			    sql.append("FROM(SELECT DISTINCT ABNM_FLID,DMT,JH AS JH,ABNM_DETECTIONDATE,abtm_name,ABNM_KEYID AS ABNNO, ");
			    sql.append("TRUNC(SYSDATE) - TRUNC(ABNM_DETECTIONDATE) AS ElapsedDays ");
			    sql.append("FROM ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY,ABN_TL_TYPEMST ");
			    sql.append("WHERE ABNM_FLID = FLID(+) AND ABNM_STATUS = 'P' ");
			    sql.append("AND TRUNC (ABNM_DETECTIONDATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"' ");
			    sql.append("AND ABNM_TYPEID = ABTM_KEYID  AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0");
			    sql.append("GROUP BY ABNM_DETECTIONDATE,abtm_name,ABNM_FLID,JH,DMT,ABNM_KEYID ");
			    sql.append("ORDER BY ElapsedDays ASC)) GROUP BY abnm_flid,DMT,JH");
			    CommonMessage.debugMsg(" if Abnormality Pending Detail Finance"+sql);
		  }
		  else{	  	  
			    
			    sql.append("SELECT ABTM_NAME,SUM (day1),SUM (day2),SUM (day3),SUM(day4) FROM (SELECT ABNM_FLID,ABTM_KEYID,ABTM_NAME,");
			    sql.append("TO_CHAR(CASE WHEN (ElapsedDays<30) THEN 1 ELSE 0 END)Day1,");
			    sql.append("TO_CHAR(CASE WHEN (ElapsedDays>=30 AND ElapsedDays<60) THEN 1 ELSE 0 END)Day2,");
			    sql.append("TO_CHAR(CASE WHEN (ElapsedDays>=60 AND ElapsedDays<90) THEN 1 ELSE 0 END)Day3,");
			    sql.append("TO_CHAR (CASE WHEN (ElapsedDays>=90) THEN 1 ELSE 0 END) Day4 ");
			    sql.append("FROM (SELECT DISTINCT ABNM_FLID,ABNM_DETECTIONDATE,ABNM_KEYID,ABTM_KEYID, ");
			    sql.append("ROUND(SYSDATE - TO_DATE(ABNM_DETECTIONDATE)) AS ElapsedDays,abtm_name ");
			    sql.append("FROM ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY,ABN_TL_TYPEMST WHERE  ABNM_FLID=FLID(+) ");
			    sql.append("AND ABNM_TYPEID = ABTM_KEYID AND ABNM_STATUS = 'P'  ");
			    sql.append("AND TRUNC (ABNM_DETECTIONDATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"' ");
			    sql.append(" AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0");
			    sql.append("GROUP BY ABNM_KEYID,ABNM_DETECTIONDATE,abtm_name,ABNM_FLID,ABTM_KEYID)) GROUP BY abnm_flid, abtm_name,ABTM_KEYID ");
			    sql.append(" ORDER BY ABTM_KEYID");
	            CommonMessage.debugMsg(" Else Abnormality Pending Detail Finance"+sql);

		  }
		}
		else{
			  if(UIUtils.isValidKeyId(JHkeyid)){
				    sql.append("SELECT JH,SUM(day1),SUM (day2),SUM (day3),SUM(day4) FROM (SELECT ABNM_FLID,DMT,JH,");
				    sql.append("TO_CHAR(CASE WHEN (ElapsedDays<30) THEN 1 ELSE 0 END)Day1,");
				    sql.append("TO_CHAR(CASE WHEN (ElapsedDays>=30 AND ElapsedDays<60) THEN 1 ELSE 0 END)Day2,");
				    sql.append("TO_CHAR(CASE WHEN (ElapsedDays>=60 AND ElapsedDays<90) THEN 1 ELSE 0 END)Day3,");
				    sql.append("TO_CHAR (CASE WHEN (ElapsedDays>=90) THEN 1 ELSE 0 END) Day4 ");
				    sql.append("FROM(SELECT DISTINCT ABNM_FLID,DMT,JH AS JH,ABNM_DETECTIONDATE,abtm_name,ABNM_KEYID AS ABNNO, ");
				    sql.append("TRUNC(SYSDATE) - TRUNC(ABNM_DETECTIONDATE) AS ElapsedDays ");
				    sql.append("FROM ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY,ABN_TL_TYPEMST ");
				    sql.append("WHERE ABNM_FLID = FLID(+) AND ABNM_TYPEID=ABTM_KEYID  AND ABNM_STATUS='P' ");
				    sql.append("AND TRUNC (ABNM_DETECTIONDATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"' ");
				    sql.append("AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0");
				    sql.append("GROUP BY ABNM_DETECTIONDATE,abtm_name,ABNM_FLID,JH,DMT,ABNM_KEYID ");
				    sql.append("ORDER BY ElapsedDays ASC)) GROUP BY abnm_flid,DMT,JH");
				    CommonMessage.debugMsg("If Abnormality Pending Detail"+sql);

			  }
			  else{
				    sql.append("SELECT ABTM_NAME,SUM (day1),SUM (day2),SUM (day3),SUM(day4) FROM (SELECT ABNM_FLID,ABTM_KEYID,ABTM_NAME,");
				    sql.append("TO_CHAR(CASE WHEN (ElapsedDays<30) THEN 1 ELSE 0 END)Day1,");
				    sql.append("TO_CHAR(CASE WHEN (ElapsedDays>=30 AND ElapsedDays<60) THEN 1 ELSE 0 END)Day2,");
				    sql.append("TO_CHAR(CASE WHEN (ElapsedDays>=60 AND ElapsedDays<90) THEN 1 ELSE 0 END)Day3,");
				    sql.append("TO_CHAR (CASE WHEN (ElapsedDays>=90) THEN 1 ELSE 0 END) Day4 ");
				    sql.append("FROM (SELECT DISTINCT ABNM_FLID,ABNM_DETECTIONDATE,ABNM_KEYID,ABTM_KEYID, ");
				    sql.append("ROUND(SYSDATE - TO_DATE(ABNM_DETECTIONDATE)) AS ElapsedDays,abtm_name ");
				    sql.append("FROM ABN_TL_ABNORMALITY,GEN_MV_FLIDHIERARCHY,ABN_TL_TYPEMST WHERE  ABNM_FLID=FLID(+) ");
				    sql.append("AND ABNM_TYPEID=ABTM_KEYID AND ABNM_STATUS='P'  ");
				    sql.append("AND TRUNC (ABNM_DETECTIONDATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"' ");
				    sql.append(" AND INSTR (PARENTFLIDS || FLID, '"+flid+"') > 0");
				    sql.append("GROUP BY ABNM_KEYID,ABNM_DETECTIONDATE,abtm_name,ABNM_FLID,ABTM_KEYID)) GROUP BY abnm_flid, abtm_name,ABTM_KEYID ");
				    sql.append(" ORDER BY ABTM_KEYID");
		            CommonMessage.debugMsg("Else Abnormality Pending Detail"+sql);
			  }	
		}
		List<String[]> Transaction=dbActionTemplate.getDataList(sql.toString());
		return Transaction;	
	}

public List<String[]> PActAdherencePercentage(String flid,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception{
		    StringBuilder sql=new StringBuilder();
		  
		    String CurrentYear=CommonFunctions.getCurrentYear();
		    Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
		    if(Finance=="Y"){
	 	    sql.append("SELECT EMPLOYEE,EMPLOYEECODE,TO_CHAR(ROUND (SUM (DECODE (MOMA_DATE,'APR-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS APR,");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'MAY-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS MAY, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JUN-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JUN, ");
	  	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JUL-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JUL, ");
	 	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'AUG-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS AUG, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'SEP-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS SEP, ");
	 	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'OCT-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS OCT, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'NOV-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS NOV, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'DEC-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS DEC, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JAN-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JAN, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'FEB-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS FEB, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'MAR-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS MAR, ");
		    sql.append(" TO_CHAR (round((SUM (PRESENT)+SUM (ONDUTY)) /( SUM (meetings))*100)) AS TOTAL,");		    
		    sql.append("TO_CHAR (SUM (meetings)),TO_CHAR (SUM (PRESENT)), TO_CHAR (SUM (ABSENT)),TO_CHAR (SUM (LEAVE)),TO_CHAR (SUM (WEEKLYOFF)),TO_CHAR (SUM (ONDUTY)) ");
          sql.append(" FROM (  SELECT DISTINCT EMPM_NAME EMPLOYEE,EMPM_CODE EMPLOYEECODE,MOMA_DATE,SUM (MEETINGS) MEETINGS, SUM (PRESENT) PRESENT, ");
          sql.append("SUM (ABSENT) ABSENT,SUM (LEAVE) LEAVE,SUM (WEEKLYOFF) WEEKLYOFF,SUM (ONDUTY) ONDUTY  FROM GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM, ");
          sql.append("(SELECT DISTINCT TO_CHAR (MOMA_DATE, 'MON-YYYY') MOMA_DATE,MOMA_EMPLOYEEID,MOMS_FLID,COUNT(MOMA_ATTANDANCE) MEETINGS, ");
          sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'P', 1, 0))) AS PRESENT,TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'A', 1, 0))) AS ABSENT, ");        
          sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'L', 1, 0))) AS LEAVE,TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'W', 1, 0))) AS WEEKLYOFF, ");  
          sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'D', 1, 0))) AS ONDUTY FROM GEN_TL_MOMMST M1,GEN_TL_MOMATTENDANCE M2 "); 
          sql.append("WHERE M1.MOMS_KEYID = M2.MOMA_MOMS_KEYID AND MOMA_ATTANDANCE <> '-' AND TRUNC(MOMS_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"' ");
          sql.append(" AND MOMS_FLID='"+flid+"' AND MOMS_MEETINGTYPE(+)='J'  GROUP BY TO_CHAR(MOMA_DATE,'MON-YYYY'), MOMA_EMPLOYEEID, MOMS_FLID) ");
          sql.append("WHERE 1=1 AND EMPM_ACTIVE='Y' AND MOMS_FLID=FRT_FNLN_KEYID AND FRT_EMPM_KEYID=MOMA_EMPLOYEEID AND EMPM_KEYID=MOMA_EMPLOYEEID  ");
          sql.append(" AND MOMA_DATE IS NOT NULL GROUP BY EMPM_NAME, EMPM_CODE, MOMA_DATE) GROUP BY EMPLOYEE, EMPLOYEECODE");
          CommonMessage.debugMsg("The PACT Percentage"+sql.toString());
		    }
		    else{
		        sql.append("SELECT EMPLOYEE,EMPLOYEECODE,TO_CHAR(ROUND (SUM (DECODE (MOMA_DATE,'APR-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS APR,");
			    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'MAY-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS MAY, ");
			    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JUN-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JUN, ");
		  	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JUL-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JUL, ");
		 	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'AUG-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS AUG, ");
			    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'SEP-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS SEP, ");
		 	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'OCT-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS OCT, ");
			    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'NOV-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS NOV, ");
			    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'DEC-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS DEC, ");
			    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JAN-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JAN, ");
			    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'FEB-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS FEB, ");
			    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'MAR-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS MAR, ");
			    sql.append(" TO_CHAR (round((SUM (PRESENT)+SUM (ONDUTY)) /( SUM (meetings))*100)) AS TOTAL,");		    
			    sql.append("TO_CHAR (SUM (meetings)),TO_CHAR (SUM (PRESENT)), TO_CHAR (SUM (ABSENT)),TO_CHAR (SUM (LEAVE)),TO_CHAR (SUM (WEEKLYOFF)),TO_CHAR (SUM (ONDUTY)) ");
	          sql.append(" FROM (  SELECT DISTINCT EMPM_NAME EMPLOYEE,EMPM_CODE EMPLOYEECODE,MOMA_DATE,SUM (MEETINGS) MEETINGS, SUM (PRESENT) PRESENT, ");
	          sql.append("SUM (ABSENT) ABSENT,SUM (LEAVE) LEAVE,SUM (WEEKLYOFF) WEEKLYOFF,SUM (ONDUTY) ONDUTY  FROM GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM, ");
	          sql.append("(SELECT DISTINCT TO_CHAR (MOMA_DATE, 'MON-YYYY') MOMA_DATE,MOMA_EMPLOYEEID,MOMS_FLID,COUNT(MOMA_ATTANDANCE) MEETINGS, ");
	          sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'P', 1, 0))) AS PRESENT,TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'A', 1, 0))) AS ABSENT, ");        
	          sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'L', 1, 0))) AS LEAVE,TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'W', 1, 0))) AS WEEKLYOFF, ");  
	          sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'D', 1, 0))) AS ONDUTY FROM GEN_TL_MOMMST M1,GEN_TL_MOMATTENDANCE M2 "); 
	          sql.append("WHERE M1.MOMS_KEYID = M2.MOMA_MOMS_KEYID AND MOMA_ATTANDANCE <> '-' AND TRUNC(MOMS_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"' ");
	          sql.append(" AND MOMS_FLID='"+flid+"' AND MOMS_MEETINGTYPE(+)='J'  GROUP BY TO_CHAR(MOMA_DATE,'MON-YYYY'), MOMA_EMPLOYEEID, MOMS_FLID) ");
	          sql.append("WHERE 1=1 AND EMPM_ACTIVE='Y' AND MOMS_FLID=FRT_FNLN_KEYID AND FRT_EMPM_KEYID=MOMA_EMPLOYEEID AND EMPM_KEYID=MOMA_EMPLOYEEID  ");
	          sql.append(" AND MOMA_DATE IS NOT NULL GROUP BY EMPM_NAME, EMPM_CODE, MOMA_DATE) GROUP BY EMPLOYEE, EMPLOYEECODE");
	          CommonMessage.debugMsg("The PACT Percentage"+sql.toString());	
		    }
          
          List<String[]> AETPercentage=dbActionTemplate.getDataList(sql.toString());
  	    return AETPercentage;
	}

public List<String[]> AetAdherencePercentage(String DmtOriginalId,String FYearStart,String FYearEnd,String FromDate,String ToDate,String Finance)throws Exception{
	    StringBuilder sql=new StringBuilder();
	    String CurrentYear=CommonFunctions.getCurrentYear();
	    Integer PreviousYear=Integer.parseInt(CurrentYear)-1;
	    if(Finance.equals("Y")){
	    sql.append("SELECT EMPLOYEE,EMPLOYEECODE,TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'APR-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS APR,");
	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'MAY-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS MAY, ");
	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JUN-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JUN, ");
	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JUL-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JUL, ");
	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'AUG-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS AUG, ");
	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'SEP-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS SEP, ");
	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'OCT-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS OCT, ");
	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'NOV-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS NOV, ");
	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'DEC-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS DEC, ");
	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JAN-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JAN, ");
	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'FEB-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS FEB, ");
	    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'MAR-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS MAR, ");
	    sql.append(" TO_CHAR (round((SUM (PRESENT)+SUM (ONDUTY)) /( SUM (meetings))*100)) AS TOTAL,");		    
	    sql.append("TO_CHAR (SUM (meetings)),TO_CHAR (SUM (PRESENT)), TO_CHAR (SUM (ABSENT)),TO_CHAR (SUM (LEAVE)),TO_CHAR (SUM (WEEKLYOFF)),TO_CHAR (SUM (ONDUTY)) ");
      sql.append(" FROM (  SELECT DISTINCT EMPM_NAME EMPLOYEE,EMPM_CODE EMPLOYEECODE,MOMA_DATE,SUM (MEETINGS) MEETINGS, SUM (PRESENT) PRESENT, ");
      sql.append("SUM (ABSENT) ABSENT,SUM (LEAVE) LEAVE,SUM (WEEKLYOFF) WEEKLYOFF,SUM (ONDUTY) ONDUTY  FROM GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM, ");
      sql.append("(SELECT DISTINCT TO_CHAR (MOMA_DATE, 'MON-YYYY') MOMA_DATE,MOMA_EMPLOYEEID,MOMS_FLID,COUNT(MOMA_ATTANDANCE) MEETINGS, ");
      sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'P', 1, 0))) AS PRESENT,TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'A', 1, 0))) AS ABSENT, ");        
      sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'L', 1, 0))) AS LEAVE,TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'W', 1, 0))) AS WEEKLYOFF, ");  
      sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'D', 1, 0))) AS ONDUTY FROM GEN_TL_MOMMST M1,GEN_TL_MOMATTENDANCE M2 "); 
      sql.append("WHERE M1.MOMS_KEYID = M2.MOMA_MOMS_KEYID AND MOMA_ATTANDANCE <> '-' AND TRUNC(MOMS_DATE) BETWEEN '"+FYearStart+"' AND '"+FYearEnd+"' ");
      sql.append(" AND MOMS_FLID='"+DmtOriginalId+"' AND MOMS_MEETINGTYPE(+)='D'  GROUP BY TO_CHAR(MOMA_DATE,'MON-YYYY'), MOMA_EMPLOYEEID, MOMS_FLID) ");
      sql.append("WHERE 1=1 AND EMPM_ACTIVE='Y' AND MOMS_FLID=FRT_FNLN_KEYID AND FRT_EMPM_KEYID=MOMA_EMPLOYEEID AND EMPM_KEYID=MOMA_EMPLOYEEID  ");
      sql.append(" AND MOMA_DATE IS NOT NULL GROUP BY EMPM_NAME, EMPM_CODE, MOMA_DATE) GROUP BY EMPLOYEE, EMPLOYEECODE");
      CommonMessage.debugMsg("The AET Percentage"+sql.toString());
	    }
	    else{
	        sql.append("SELECT EMPLOYEE,EMPLOYEECODE,TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'APR-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS APR,");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'MAY-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS MAY, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JUN-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JUN, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JUL-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JUL, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'AUG-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS AUG, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'SEP-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS SEP, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'OCT-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS OCT, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'NOV-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS NOV, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'DEC-"+PreviousYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS DEC, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'JAN-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS JAN, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'FEB-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS FEB, ");
		    sql.append("TO_CHAR (ROUND (SUM (DECODE (MOMA_DATE,'MAR-"+CurrentYear+"',(PRESENT + ONDUTY)/ (MEETINGS), 0))* 100,2)) AS MAR, ");
		    sql.append(" TO_CHAR (round((SUM (PRESENT)+SUM (ONDUTY)) /( SUM (meetings))*100)) AS TOTAL,");		    
		    sql.append("TO_CHAR (SUM (meetings)),TO_CHAR (SUM (PRESENT)), TO_CHAR (SUM (ABSENT)),TO_CHAR (SUM (LEAVE)),TO_CHAR (SUM (WEEKLYOFF)),TO_CHAR (SUM (ONDUTY)) ");
	      sql.append(" FROM (  SELECT DISTINCT EMPM_NAME EMPLOYEE,EMPM_CODE EMPLOYEECODE,MOMA_DATE,SUM (MEETINGS) MEETINGS, SUM (PRESENT) PRESENT, ");
	      sql.append("SUM (ABSENT) ABSENT,SUM (LEAVE) LEAVE,SUM (WEEKLYOFF) WEEKLYOFF,SUM (ONDUTY) ONDUTY  FROM GEN_TL_EMPLOYEEMST,GEN_TL_FNLNROLETEAM, ");
	      sql.append("(SELECT DISTINCT TO_CHAR (MOMA_DATE, 'MON-YYYY') MOMA_DATE,MOMA_EMPLOYEEID,MOMS_FLID,COUNT(MOMA_ATTANDANCE) MEETINGS, ");
	      sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'P', 1, 0))) AS PRESENT,TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'A', 1, 0))) AS ABSENT, ");        
	      sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'L', 1, 0))) AS LEAVE,TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'W', 1, 0))) AS WEEKLYOFF, ");  
	      sql.append(" TO_CHAR(SUM(DECODE(MOMA_ATTANDANCE,'D', 1, 0))) AS ONDUTY FROM GEN_TL_MOMMST M1,GEN_TL_MOMATTENDANCE M2 "); 
	      sql.append("WHERE M1.MOMS_KEYID = M2.MOMA_MOMS_KEYID AND MOMA_ATTANDANCE <> '-' AND TRUNC(MOMS_DATE) BETWEEN '"+FromDate+"' AND '"+ToDate+"' ");
	      sql.append(" AND MOMS_FLID='"+DmtOriginalId+"' AND MOMS_MEETINGTYPE(+)='D'  GROUP BY TO_CHAR(MOMA_DATE,'MON-YYYY'), MOMA_EMPLOYEEID, MOMS_FLID) ");
	      sql.append("WHERE 1=1 AND EMPM_ACTIVE='Y' AND MOMS_FLID=FRT_FNLN_KEYID AND FRT_EMPM_KEYID=MOMA_EMPLOYEEID AND EMPM_KEYID=MOMA_EMPLOYEEID  ");
	      sql.append(" AND MOMA_DATE IS NOT NULL GROUP BY EMPM_NAME, EMPM_CODE, MOMA_DATE) GROUP BY EMPLOYEE, EMPLOYEECODE");
	      CommonMessage.debugMsg("The AET Percentage"+sql.toString());	
	    }
      
      List<String[]> AETPercentage=dbActionTemplate.getDataList(sql.toString());
	    return AETPercentage;
} 
	public List<String[]> getReportFunctllocn(String flid)throws Exception{
		
		    StringBuilder sql = new StringBuilder();
			sql.append(" select SECT_NAME||'-'|| CELL_NAME ");
			sql.append(" from gen_vw_fnln where fnln_keyid='"+flid+"' ");
			CommonMessage.debugMsg("Sql:"+sql);
			List<String[]>  functllocn  = dbActionTemplate.getDataList(sql.toString());
		    return functllocn;
	}
	
	public List<String[]> getDMTId(String flid)throws Exception{
	     StringBuilder sql = new StringBuilder();
		sql.append(" select SECT_NAME ");
		sql.append(" from gen_vw_fnln where fnln_keyid='"+flid+"' ");
		CommonMessage.debugMsg("Sql:"+sql);
		List<String[]>  functllocn  = dbActionTemplate.getDataList(sql.toString());
	    return functllocn;
}
	
	public List<String[]> getJHId(String flid)throws Exception{
		     StringBuilder sql = new StringBuilder();
			sql.append(" select CELL_NAME ");
			sql.append(" from gen_vw_fnln where fnln_keyid='"+flid+"' ");
			CommonMessage.debugMsg("Sql:"+sql);
			List<String[]>  functllocn  = dbActionTemplate.getDataList(sql.toString());
		    return functllocn;
	}
	
	@Override
	public Map<Integer, List<String[]>> MOMAetAdherence(String flid,String fromMonth,String toMonth,String FromDate ,String ToDate,String Finance) throws Exception {
		try
		{
			CommonMessage.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();				
			paramValues.add(flid);
			Map<Integer, List<String[]>> cnvMtxExport= dbActionTemplate.processDbFunCallMultCursor("ADM_PC_NEWDASHBOARD.GEN_FN_ATTDNCMONTHWISERPTNEW", paramValues,1);
			
			for(int i = 0; i <  cnvMtxExport.size();i++ )
			{
				CommonMessage.debugMsg("Inside v: " +cnvMtxExport.get(i).size() );
			}
			return cnvMtxExport;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage()); 
			
		}
	}

}
