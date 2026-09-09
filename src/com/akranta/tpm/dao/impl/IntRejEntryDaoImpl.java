package com.akranta.tpm.dao.impl;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.IntRejEntryBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.IntRejEntryDao;
import com.akranta.tpm.dao.sql.BdmTlShiftwisesplitSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.JhnTlSlamstSql;
import com.akranta.tpm.dao.sql.PcsTlMstSql;
import com.akranta.tpm.dao.sql.QtmTlIntrejectiondtlSql;
import com.akranta.tpm.dao.sql.QtmTlIntrejectionmstSql;
import com.akranta.tpm.dao.sql.QtmTlTestscrapbrkupSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.BdmTlDtl;
import com.akranta.tpm.model.BdmTlShiftwisesplit;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.JhnTlSlamst;
import com.akranta.tpm.model.QtmTlIntrejectiondtl;
import com.akranta.tpm.model.QtmTlIntrejectionmst;
import com.akranta.tpm.model.QtmTlTestscrapbrkup;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.InternalRejectionSerivceApi;
import com.akranta.tpm.service.api.MomServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

public class IntRejEntryDaoImpl implements IntRejEntryDao {

	private DBActionTemplate dbActionTemplate;
	private QtmTlIntrejectionmstSql qtmTlIntrejectionmstSql;
	private QtmTlIntrejectiondtlSql qtmTlIntrejectiondtlSql;
	private QtmTlTestscrapbrkupSql qtmTlTestscrapbrkupSql;
	private InternalRejectionSerivceApi irServiceApi;
	FunctionCallApi fnCallApi;
	public IntRejEntryDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;	
		qtmTlIntrejectionmstSql= new QtmTlIntrejectionmstSql();
		qtmTlIntrejectiondtlSql= new QtmTlIntrejectiondtlSql();
		qtmTlTestscrapbrkupSql = new QtmTlTestscrapbrkupSql();
	}
	
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public void IntRejEntryDaoImplJwt(String JwtToken) 
	{
		try{
			irServiceApi = new InternalRejectionSerivceApi (JwtToken);
		fnCallApi = new FunctionCallApi(JwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public List<String[]> getInternalRejectionViewGrid(String condParam,String detailTableName) throws Exception {
		try
		{			
			CommonMessage.debugMsg("dao impl");
			List<String> paramValues = new ArrayList<String>();
			List<String[]> openMsrList =null;
			
			//String condParam="MACHINEID=MCH000002;CELLID=CEL024;SHIFTID=SFT008;FROMDATE=05-Jul-2012;TODATE=05-Jul-2012;IsForHeat=N;PLDetailsTbl=PCS_TL_MLD#IP";
			String commonParam="";
	
			CommonMessage.debugMsg("detailTableName"+detailTableName);
			CommonMessage.debugMsg("condParam"+condParam);
			condParam+=";PLDetailsTbl="+detailTableName;
			
			paramValues.add(condParam);
			paramValues.add(commonParam);
			

			//openMsrList = dbActionTemplate.processFunctionCalls("QTM_PC_QUALITY.FN_RECALLQTPRODUCTIONGROUP", paramValues);
			openMsrList = dbActionTemplate.processFunctionCalls("QTM_PC_QUALITY.QTM_FN_QTPRODUCTIONGROUP", paramValues);
			

			
			CommonMessage.debugMsg("openMsrList.size"+openMsrList.size());
			
			return openMsrList;	
		}	
		catch(Exception e)
		{
			e.printStackTrace();
			CommonMessage.debugMsg("Exception in CALENDAR dao impl"+e.getMessage());
		}
		return null;		
	}
	
	public List<String[]> getInternalRejectionEntryGrid(String condParam) throws Exception {
		try
		{			
			CommonMessage.debugMsg("dao impl");
			List<String> paramValues = new ArrayList<String>();
			List<String[]> openMsrList =null;
						
/*			condParam="";
			condParam+= "MACHINEID=MCH000001;DATE=01-Mar-2012;MASTERID=QTM/12/00007";
			condParam+=";ENTRYTYPE=DIR;PROCESSID=PRS0000015;PHENOMENAID=QPH0000010;PRODUCTID=PRD0001399";
*/			
			String commonParam="";	
			//CommonMessage.debugMsg("detailTableName"+detailTableName);
			CommonMessage.debugMsg("condParam"+condParam);			
			
			paramValues.add(condParam);
			paramValues.add(commonParam);			

			openMsrList = dbActionTemplate.processFunctionCalls("QTM_PC_QUALITY.QTM_FN_PROCESSPHENCAUSEDETAILS", paramValues);

			
			CommonMessage.debugMsg("openMsrList.size"+openMsrList.size());
			
			return openMsrList;	
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
			CommonMessage.debugMsg("Exception in CALENDAR dao impl"+e.getMessage());
		}
		return null;		
	}
	


	public List<String[]> getInternalRejectionEntryTestGrid(String condParam,CommonFilter commonFilter) throws Exception {
		try
		{			
			CommonMessage.debugMsg("dao impl");
			List<String> paramValues = new ArrayList<String>();
			List<String[]> openMsrList =null;
			
						
			//condParam="";
			//condParam+= "MACHINEID=MCH000001;DATE=01-Mar-2012;MASTERID=QTM/12/00007";
			//condParam+=";ENTRYTYPE=DIR;PROCESSID=PRS0000015;PHENOMENAID=QPH0000010;PRODUCTID=PRD0001399";
			
			String commonParam="";	
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
			//CommonMessage.debugMsg("detailTableName"+detailTableName);
			CommonMessage.debugMsg("condParam"+condParam);			
			
			paramValues.add(condParam);
			paramValues.add(commonParams);			

			//openMsrList = dbActionTemplate.processFunctionCalls("QTM_FN_INTERNALREJECTION", paramValues);
			
			openMsrList = fnCallApi.callFunction("QTM_FN_INTERNALREJECTION_SB", paramValues,3,true);

			
			CommonMessage.debugMsg("openMsrList.size"+openMsrList.size());
			
			return openMsrList;	
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
			CommonMessage.debugMsg("Exception in CALENDAR dao impl"+e.getMessage());
		}
		return null;		
	}

	@Override
	public Workbook intRejExportExcel( String condParam, JSONObject colmodel, String format) throws Exception {
		
			ResultSet rs = null;
			   try{
				
				String commonParam="";	
				CommonMessage.debugMsg("condParam"+condParam);			
				List<String> paramValues = new ArrayList<String>();
				paramValues.add(condParam);
				paramValues.add(commonParam);			

				rs  = dbActionTemplate.dbFunctionCall("QTM_FN_INTERNALREJECTION", paramValues);

				
				ExcelUtils excelUtils = new ExcelUtils(colmodel);
				List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
				
				XLConditionalFormats condFormat = new XLConditionalFormats();
				condFormat.setFontColor(new RGB(254,0,0)); //red font
				condFormat.setFontName("Wingdings");
				condFormat.setFontHeightPoint((short)14);
				condFormat.setFontBoldWeight((short)20);
				condFormat.setFromCol(11);
				
				condFormat.setToCol(-1);
				condFormat.setOperator(ComparisonOperator.EQUAL);
				condFormat.setCondValue( (char)252+""); //Tick
				condFormat.setIdentfier("tick");
				condFormats.add(condFormat);
				excelUtils.setCondFormats(condFormats);
				return excelUtils.writeToExcel(rs,format,2,0,0 );
			   }finally{
				   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
			   }  
		}
		
		public List<String[]> getIntRejHourBreakGrid(String qirmKeyid) throws Exception {
		try
		{			
			CommonMessage.debugMsg("dao impl");
			
			List<String[]> openMsrList =null;
			StringBuffer sqls=new StringBuffer();
			sqls.append(" SELECT ORDERNO,  QTMMASTERID,  HOURLYKEYID,  DECODE(ORDERNO,NULL,'TOTAL',  ORDERNO||' - Hour') ORDERHOUR, SUM(INSPECTED) INSPECTED,  ");
			sqls.append(" SUM(ACCEPTEDQTY) ACCEPTEDQTY,  SUM(REJECTEDQTY) REJECTEDQTY,  SUM(TESTINGSCRAP) TESTINGSCRAP, ");
			sqls.append(" SUM(MRBSCRAP) MRBSCRAP,  SUM(QAHOLD) QAHOLD,  ");
			sqls.append(" ROUND(DECODE(SUM(INSPECTED),0,0,SUM(QAHOLD)/SUM(INSPECTED)*100),2) AS QAHOLDPCT ");
			sqls.append(" FROM ( ");
			sqls.append(" SELECT ROWNUM AS ORDERNO FROM TAB ");
			sqls.append(" WHERE ROWNUM <=8 ");
			sqls.append(" ),( ");			
			sqls.append(" SELECT DISTINCT QIHB_QTM_KEYID AS QTMMASTERID,  QIHB_KEYID  AS HOURLYKEYID,  QIHB_SHIFTHOUR, ");
			sqls.append(" QIHB_INSPECTEDQTY   AS INSPECTED,  QIHB_ACCEPTEDQTY    AS ACCEPTEDQTY,  QIHB_REJECTEDQTY    AS REJECTEDQTY,  ");
			sqls.append(" QIHB_TESTINGQTY     AS TESTINGSCRAP,  QIHB_MRBQTY  AS MRBSCRAP,  QIHB_QAHOLD  AS QAHOLD  ");			 
			//sqls.append(" ,QIRM_INSPECTIONID   AS INSPECTBY,  QIRM_INSPECTEDSHIFTID AS INSPECTSHIFT");
			sqls.append(" FROM QTM_TL_INTREJECTIONMST,  QTM_TL_INTERNALREJECTIONHOURLY ");
			sqls.append(" WHERE QIRM_KEYID = QIHB_QTM_KEYID ");
			sqls.append(" AND QIRM_KEYID   ='" + qirmKeyid + "' AND QIHB_ACTIVE='Y' ");
			sqls.append(" ) WHERE QIHB_SHIFTHOUR (+) = ORDERNO ");
			sqls.append(" GROUP BY GROUPING SETS((ORDERNO, QTMMASTERID, HOURLYKEYID,ORDERNO),()) ");
			sqls.append(" ORDER BY ORDERNO ");
			
			CommonMessage.debugMsg("sqls.toString()"+sqls.toString());
			openMsrList =dbActionTemplate.getDataList(sqls.toString());
			
			CommonMessage.debugMsg("openMsrList.size"+openMsrList.size());
			
			return openMsrList;	
		}
	
		catch(Exception e)
		{
			e.printStackTrace();
			CommonMessage.debugMsg("Exception in CALENDAR dao impl"+e.getMessage());
		}
		return null;		
	}
	
	public String checkQirm(String QirmPldetailsid,String QirmProductid) throws Exception {
		StringBuffer sql1 = new StringBuffer();
		String sql = "";
		sql1.append(" SELECT QIRM_KEYID FROM QTM_TL_INTREJECTIONMST");
		sql1.append(" WHERE QIRM_PLDETAILID='" + QirmPldetailsid + "' AND QIRM_PRODUCTID ='" + QirmProductid + "'");

		sql=sql1.toString();
		CommonMessage.debugMsg("sqlsss: " +sql);
		List<String[]> qirmList = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg("EntryExistsDates: " +qirmList.size());
		if (qirmList.size() >0 ) { 
			CommonMessage.debugMsg("EntryExistsDates: " +qirmList.get(0)[0]);
			return qirmList.get(0)[0];
		}
		else
			return "";
	}
	public List<String[]> getEntryExistsDates(String detailTable, String entryDate, String cellId, String mchId) throws Exception {
		StringBuffer sql1 = new StringBuffer();
		String sql = "";
/*		sql1.append("  Select  DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG) AS PDE_COMPLETEDFLAG,  SFTM_KEYID,SFTM_SHIFTORDER,TO_CHAR(PRLM_ENTRYDATE,'DD-Mon-YYYY')  ");			 
		sql1.append(" from PCS_TL_MST,GEN_TL_SHIFTMST, (SELECT DISTINCT PLMASTERID FROM " + detailTable + " WHERE MACHINEID  = '" + mchId + "'  AND ACTIVE = 'Y'   ");
		sql1.append("  AND (NOPLANINMINS > 0 OR NVL(PRODUCTID,'{}') <> '{}') ) PLD  ");
		sql1.append("  	WHERE   to_char(PRLM_ENTRYDATE,'YYYYMM') = to_char(to_date('" + entryDate + "'),'YYYYMM')  ");
		sql1.append(" AND PRLM_SHIFTID = SFTM_KEYID AND PLD.PLMASTERID = PRLM_KEYID   ");
		sql1.append(" AND PRLM_LOGTYPE = 'S' AND PRLM_TIMEORQTY = 'T'   ");
		sql1.append(" GROUP BY DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG),  SFTM_KEYID,SFTM_SHIFTORDER,PRLM_ENTRYDATE   ");
		sql1.append(" ORDER BY PRLM_ENTRYDATE,SFTM_SHIFTORDER   ");
*/
		/*sql1.append("  Select PDE_COMPLETEDFLAG,SFTM_KEYID,SFTM_SHIFTORDER,PRLM_ENTRYDATE, REJEXISTS, NVL(INTREJEXISTS,0) FROM ( ");		
		
		sql1.append("  Select PRLM_KEYID, DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG) AS PDE_COMPLETEDFLAG,  SFTM_KEYID,");
		sql1.append("  SFTM_SHIFTORDER,TO_CHAR(PRLM_ENTRYDATE,'DD-Mon-YYYY') AS PRLM_ENTRYDATE, REJEXISTS ");			 
		sql1.append(" from PCS_TL_MST,GEN_TL_SHIFTMST, ");
		sql1.append(" (SELECT DISTINCT PLMASTERID, DECODE(REJ_MASTERID ,NULL,0,1) AS REJEXISTS ");
		sql1.append("   FROM " + detailTable + ", (SELECT DISTINCT PLMASTERID REJ_MASTERID  ");
		sql1.append("  FROM  PCS_TL_LOSSREASONLINK, PCS_TL_LOGCONFIGURATION, " + detailTable + " " );
		sql1.append("  WHERE PLRK_LOSSID =PLCM_KEYID  AND PLCM_UOM IN (SELECT UOMM_KEYID FROM ADM_TL_UOMMST WHERE UOMM_CODE = 'NOS') " );
		sql1.append("    AND PLRK_PLDETAILID = PLDETAILSID) ");
		sql1.append("    WHERE REJ_MASTERID (+) = PLMASTERID ");*/
		/*sql1.append("  Select PDE_COMPLETEDFLAG,SFTM_KEYID,SFTM_SHIFTORDER,PRLM_ENTRYDATE, REJEXISTS,  MIN(NVL(INTREJEXISTS,0)) AS INTREJEXISTS FROM ( ");		
		sql1.append("  Select PRLM_KEYID,PLDETAILSID, DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG) AS PDE_COMPLETEDFLAG,  SFTM_KEYID,");
		sql1.append("  SFTM_SHIFTORDER,TO_CHAR(PRLM_ENTRYDATE,'DD-Mon-YYYY') AS PRLM_ENTRYDATE, REJEXISTS ");			 
		sql1.append(" from PCS_TL_MST,GEN_TL_SHIFTMST, ");
		sql1.append(" (SELECT DISTINCT PLMASTERID,PLDETAILSID,  DECODE(REJ_MASTERID ,NULL,0,1) AS REJEXISTS ");
		sql1.append("   FROM " + detailTable + ", (SELECT DISTINCT PLMASTERID REJ_MASTERID  ");
		sql1.append("  FROM  PCS_TL_LOSSREASONLINK, PCS_TL_LOGCONFIGURATION, " + detailTable + " " );
		sql1.append("  WHERE PLRK_LOSSID =PLCM_KEYID  AND PLCM_UOM IN (SELECT UOMM_KEYID FROM ADM_TL_UOMMST WHERE UOMM_CODE = 'NOS') " );
		if (UIUtils.isValidKeyId(mchId))	
			sql1.append(" AND MACHINEID  = '" + mchId + "' ");
		sql1.append("    AND PLRK_PLDETAILID = PLDETAILSID) ");
		sql1.append("    WHERE REJ_MASTERID (+) = PLMASTERID ");
	
		if (UIUtils.isValidKeyId(cellId))	
			sql1.append(" AND CELLID  = '" + cellId + "' ");
		
		if (UIUtils.isValidKeyId(mchId))	
			sql1.append(" AND MACHINEID  = '" + mchId + "' ");
		
		sql1.append(" AND ACTIVE = 'Y'   ");
		sql1.append("  AND (NOPLANINMINS > 0 OR NVL(PRODUCTID,'{}') <> '{}') ) PLD  ");
		sql1.append("  	WHERE   to_char(PRLM_ENTRYDATE,'YYYYMM') = to_char(to_date('" + entryDate + "'),'YYYYMM')  ");
		sql1.append(" AND PRLM_SHIFTID = SFTM_KEYID AND PLD.PLMASTERID = PRLM_KEYID   ");
		sql1.append(" AND PRLM_LOGTYPE = 'S' AND PRLM_TIMEORQTY = 'T'   ");
		//sql1.append(" GROUP BY PRLM_KEYID,DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG),  SFTM_KEYID,SFTM_SHIFTORDER,PRLM_ENTRYDATE ,REJEXISTS  ");
		sql1.append(" GROUP BY PRLM_KEYID,PLDETAILSID,DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG),  SFTM_KEYID,SFTM_SHIFTORDER,PRLM_ENTRYDATE ,REJEXISTS  ");
		sql1.append(" ),( ");
		//sql1.append(" SELECT DISTINCT QIRM_PLMASTERID, 1 AS INTREJEXISTS FROM QTM_TL_INTREJECTIONMST ");
		sql1.append(" SELECT DISTINCT QIRM_PLMASTERID, 1 AS INTREJEXISTS,QIRM_PLDETAILID  FROM QTM_TL_INTREJECTIONMST ");
		sql1.append(" WHERE   to_char(QIRM_PRODUCTIONDATE,'YYYYMM') = to_char(to_date('" + entryDate + "'),'YYYYMM')    ");
		sql1.append(" ) WHERE QIRM_PLMASTERID (+) = PRLM_KEYID ");	
		sql1.append("AND QIRM_PLDETAILID (+) = PLDETAILSID ");
		sql1.append("GROUP BY PDE_COMPLETEDFLAG,SFTM_KEYID,SFTM_SHIFTORDER,PRLM_ENTRYDATE, REJEXISTS");
		sql1.append(" ORDER BY PRLM_ENTRYDATE,SFTM_SHIFTORDER   "); */
		
		sql1.append("  Select PDE_COMPLETEDFLAG,SFTM_KEYID,SFTM_SHIFTORDER,PRLM_ENTRYDATE, REJEXISTS,  MIN(NVL(INTREJEXISTS,0)) AS INTREJEXISTS FROM ( ");		
		sql1.append("  Select PRLM_KEYID,DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG) AS PDE_COMPLETEDFLAG,  SFTM_KEYID,");
		sql1.append("  SFTM_SHIFTORDER,TO_CHAR(PRLM_ENTRYDATE,'DD-Mon-YYYY') AS PRLM_ENTRYDATE, REJEXISTS ");			 
		sql1.append(" from PCS_TL_MST,GEN_TL_SHIFTMST, ");
		sql1.append(" (SELECT DISTINCT PLMASTERID, DECODE(REJ_MASTERID ,NULL,0,1) AS REJEXISTS ");
		sql1.append("   FROM " + detailTable + ", (SELECT DISTINCT PLMASTERID REJ_MASTERID  ");
		sql1.append("  FROM  PCS_TL_LOSSREASONLINK, PCS_TL_LOGCONFIGURATION, " + detailTable + " " );
		sql1.append("  WHERE PLRK_LOSSID =PLCM_KEYID  AND PLCM_UOM IN (SELECT UOMM_KEYID FROM ADM_TL_UOMMST WHERE UOMM_CODE = 'NOS') " );

		if (UIUtils.isValidKeyId(mchId))	
			sql1.append(" AND MACHINEID  = '" + mchId + "' ");
		
		if (UIUtils.isValidKeyId(cellId))	
			sql1.append(" AND CELLID  = '" + cellId + "' ");
		
		sql1.append("    AND PLRK_PLDETAILID = PLDETAILSID) ");
		sql1.append("    WHERE REJ_MASTERID (+) = PLMASTERID ");
	
		if (UIUtils.isValidKeyId(cellId))	
			sql1.append(" AND CELLID  = '" + cellId + "' ");
		
		if (UIUtils.isValidKeyId(mchId))	
			sql1.append(" AND MACHINEID  = '" + mchId + "' ");
		
		sql1.append(" AND ACTIVE = 'Y'   ");
		sql1.append("  AND (NOPLANINMINS > 0 OR NVL(PRODUCTID,'{}') <> '{}') ) PLD  ");
		sql1.append("  	WHERE   to_char(PRLM_ENTRYDATE,'YYYYMM') = to_char(to_date('" + entryDate + "'),'YYYYMM')  ");
		sql1.append(" AND PRLM_SHIFTID = SFTM_KEYID AND PLD.PLMASTERID = PRLM_KEYID   ");
		sql1.append(" AND PRLM_LOGTYPE = 'S' AND PRLM_TIMEORQTY = 'T'   ");
		sql1.append(" GROUP BY PRLM_KEYID,DECODE(PRLM_APPORVEDFLAG,'Y','A',PRLM_APPORVEDFLAG),  SFTM_KEYID,SFTM_SHIFTORDER,PRLM_ENTRYDATE ,REJEXISTS  ");
		sql1.append(" ),( ");
		sql1.append(" SELECT DISTINCT QIRM_PLMASTERID, 1 AS INTREJEXISTS FROM QTM_TL_INTREJECTIONMST ");
		sql1.append(" WHERE   to_char(QIRM_PRODUCTIONDATE,'YYYYMM') = to_char(to_date('" + entryDate + "'),'YYYYMM')    ");
		sql1.append(" ) WHERE QIRM_PLMASTERID (+) = PRLM_KEYID ");	
		sql1.append("GROUP BY PDE_COMPLETEDFLAG,SFTM_KEYID,SFTM_SHIFTORDER,PRLM_ENTRYDATE, REJEXISTS");
		sql1.append(" ORDER BY PRLM_ENTRYDATE,SFTM_SHIFTORDER   ");
		

		
		sql=sql1.toString();
		CommonMessage.debugMsg("sqlsss: " +sql);
		List<String[]> eedList = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg("EntryExistsDates: " +eedList.size());
		if (eedList.size() >0 )
			CommonMessage.debugMsg("EntryExistsDates: " +eedList.get(0)[0]);
		return eedList;	
			
	}
	public List<String[]> getScrapBreakup(String rejectionId,String referenceId) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		sql.append("Select Distinct Decode(Qsbr_Rejectionid,'"+rejectionId+"',Qsbr_Rejectionid,'') As Rejectionid,");
		sql.append("Decode(Qtsc_Keyid,'{}','',Qtsc_Keyid) As Masterid,QTSC_NAME AS NAME,QtSC_Code As Code,");
		sql.append("Decode(Qsbr_Rejectionid,'"+rejectionId+"',Qsbr_Quantity,'') As Qty,");
		sql.append("Decode(Qsbr_Rejectionid,'"+rejectionId+"',Decode(Qsbr_Remarks,'<*{}*>','',Qsbr_Remarks)) As Remarks,");
		sql.append("Decode(Qsbr_Backlogflag,'','N',QSBR_Backlogflag) As Flag,");
		sql.append("DECODE(QSBR_REJECTIONID,'"+rejectionId+"',QSBR_REFERENCEKEYID,'') AS REFERENCEID");
		sql.append(" From QTM_TL_TESTSCRAP,Qtm_Tl_Testscrapbrkup	Where Qsbr_Masterid(+)    =Qtsc_Keyid");
		sql.append(" And Qsbr_Rejectionid(+)   ='"+rejectionId+"' And Qsbr_Referencekeyid(+)='"+referenceId+"'	ORDER BY QTSC_NAME");
		CommonMessage.debugMsg("sqlsss: " +sql.toString());
		List<String[]> ScrapBreakupList = dbActionTemplate.getDataList(sql.toString());
		return ScrapBreakupList;
	}
	public List<String[]> getPendingColor(String entryDate,String cellId,String mchId,String detailTable) throws Exception
	{
		String currentDate = CommonFunctions.dateTimeNow();
		currentDate = currentDate.indexOf(" ")>0?currentDate.substring(0, currentDate.indexOf(" ")):currentDate;
		entryDate = entryDate.substring(entryDate.indexOf("-")+1);
		List<String[]> pendColList = dbActionTemplate.getDataList(QtmTlIntrejectionmstSql.getPendingColorSql(entryDate, currentDate,cellId,mchId,detailTable));
		return pendColList;
	}
	public QtmTlTestscrapbrkup createBreakup(QtmTlTestscrapbrkup newQtmTlTestscrapbrkup,QtmTlTestscrapbrkup oldQtmTlTestscrapbrkup) throws Exception
	{
		List<String> sqls = new ArrayList<String>();
		int qty = 0;
		if(newQtmTlTestscrapbrkup.getQtmTlTestscrapbrkup()!= null && newQtmTlTestscrapbrkup.getQtmTlTestscrapbrkup().size()>0) // check for detail table data
		{	
			for(int i =0;i<newQtmTlTestscrapbrkup.getQtmTlTestscrapbrkup().size();i++)
			{								
				QtmTlTestscrapbrkup breakup = (QtmTlTestscrapbrkup)newQtmTlTestscrapbrkup.getQtmTlTestscrapbrkup().get(i);
				if(UIUtils.isValidKeyId(breakup.getQsbrQuantity()))
				{
					if(!UIUtils.isValidKeyId(breakup.getQsbrReferencekeyid()))
						breakup.setQsbrReferencekeyid(newQtmTlTestscrapbrkup.getQsbrReferencekeyid());					
					if(!UIUtils.isValidKeyId(breakup.getQsbrRejectionid()))
						breakup.setQsbrRejectionid(newQtmTlTestscrapbrkup.getQsbrRejectionid());					
					if(!UIUtils.isValidKeyId(breakup.getQsbrBacklogflag()))
						breakup.setQsbrBacklogflag(newQtmTlTestscrapbrkup.getQsbrBacklogflag());						
					if(!UIUtils.isValidKeyId(breakup.getQsbrQuantity().trim()))
						breakup.setQsbrQuantity(newQtmTlTestscrapbrkup.getQsbrQuantity());					
					if(!UIUtils.isValidKeyId(breakup.getQsbrRemarks().trim()))
						breakup.setQsbrRemarks(newQtmTlTestscrapbrkup.getQsbrRemarks());
					breakup.setQsbrActive("Y");
					breakup.setQsbrCreatedby(newQtmTlTestscrapbrkup.getQsbrCreatedby());
					breakup.setQsbrCreatedon(newQtmTlTestscrapbrkup.getQsbrCreatedon());
					breakup.setQsbrModifiedon(newQtmTlTestscrapbrkup.getQsbrCreatedon());
						
					int quantity = Integer.parseInt(breakup.getQsbrQuantity().trim());					
					qty = qty + quantity;
				
					String checkExist = dbActionTemplate.getSingleValue(QtmTlTestscrapbrkupSql.checkBreakupExist(breakup.getQsbrRejectionid(), breakup.getQsbrMasterid(), breakup.getQsbrReferencekeyid()));
					boolean update = false;
					CommonMessage.debugMsg("Exist "+checkExist);
					if(UIUtils.isValidKeyId(checkExist))
					{
						if(Integer.parseInt(checkExist)>0)
							update = true;							
					}					
					if(update)
						sqls.add(QtmTlTestscrapbrkupSql.getUpdateSql(qtmTlTestscrapbrkupSql.getQsbrDbFields(), breakup.getSaveArray()));
					else
						sqls.add(QtmTlTestscrapbrkupSql.getInsertSql(qtmTlTestscrapbrkupSql.getQsbrDbFields(), breakup.getSaveArray()));// add insert sql for detail table
				}	
			}
		}
		if(qty != 0)
		{
			String qy = Integer.toString(qty);
			sqls.add("UPDATE "+qtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST+" SET QIRM_TESTINGSCRAP = "+qy+" where qirm_keyid = '"+newQtmTlTestscrapbrkup.getQsbrReferencekeyid()+"'");
			sqls.add("UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_TESTINGQTY = "+qy+" where QIHB_KEYID = '"+newQtmTlTestscrapbrkup.getQsbrRejectionid()+"'");
		}
		dbActionTemplate.executeStatements(sqls); 
		return newQtmTlTestscrapbrkup;
	}
	public String getIsHourlyEntry() throws Exception {
		String hourlyEntry = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE", "CNFM_CODE", "INT_HOURLY");
		CommonMessage.debugMsg("hourlyEntry: " +hourlyEntry);
		if (hourlyEntry.isEmpty() || hourlyEntry.equals(null))
			hourlyEntry="";
		return hourlyEntry; 
	}
	
	public String getBalanceQty(String machineId,String entryDate,String shiftId,String sectId) throws Exception {
		String detailTable = dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", sectId);
		
		if(UIUtils.isValidKeyId(detailTable)){
			detailTable = detailTable.replace("-", "#");
			detailTable = "PCS_TL_"+detailTable;
		}
		else
			detailTable = "PCS_TL_DTL";
		String balQty = dbActionTemplate.getSingleValue(QtmTlIntrejectionmstSql.getBalanceQtySql(machineId,entryDate,shiftId,detailTable));
		return balQty;
	}

	public QtmTlIntrejectiondtl create(QtmTlIntrejectionmst qtmTlIntrejectionmst, QtmTlIntrejectiondtl qtmTlIntrejectiondtl,
			IntRejEntryBean intRejEntryBean) throws Exception  
	{
		boolean afterUpdate = true;
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		List<String> charList  = new ArrayList<String>();
		String masterID = qtmTlIntrejectionmst.getQirmKeyid();
		if(qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl()!= null && qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl().size()>0) // check for detail table data
		{
			for(int i =0;i<qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl().size();i++)
			{	
				QtmTlIntrejectiondtl intRejection = qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl().get(i);
				intRejection.setQirdQhbKeyid(qtmTlIntrejectiondtl.getQirdQhbKeyid());
				intRejection.setQirdEntrytype(qtmTlIntrejectiondtl.getQirdEntrytype());
				detailFillValues(intRejection);
				
				if(UIUtils.isValidKeyId(masterID))
				{
					CommonMessage.debugMsg("Master Id : "+masterID);
					String parentId = dbActionTemplate.getSingleValue(QtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST, "QIRM_PARENTMASTERID", "QIRM_KEYID", masterID);
					String linkId = dbActionTemplate.getSingleValue(QtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST, "QIRM_LINKMASTERID", "QIRM_KEYID", masterID);
					qtmTlIntrejectionmst.setQirmKeyid(masterID);
					if (!UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmParentmasterid())) 
					{
						if(!CommonFunctions.isValidKeyId(parentId))
							qtmTlIntrejectionmst.setQirmParentmasterid(qtmTlIntrejectionmst.getQirmKeyid());
						else
							qtmTlIntrejectionmst.setQirmParentmasterid(parentId);
						
						if(!CommonFunctions.isValidKeyId(linkId))
							qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());
						else
							qtmTlIntrejectionmst.setQirmLinkmasterid(linkId);
						//qtmTlIntrejectionmst.setQirmParentmasterid(qtmTlIntrejectionmst.getQirmKeyid());
					}
					if (!UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmLinkmasterid())) 
					{
						if(!CommonFunctions.isValidKeyId(linkId))
							qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());
						else
							qtmTlIntrejectionmst.setQirmLinkmasterid(linkId);
					}
				//	qtmTlIntrejectionmst.setQirmElementid("{}");
					qtmTlIntrejectionmst.setQirmReferencekeyid(qtmTlIntrejectionmst.getQirmKeyid());
					sqls.add(QtmTlIntrejectionmstSql.getUpdateSql(qtmTlIntrejectionmstSql.getQirmDbFields(), qtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table
					valueList.add(null);
					dataTypes.add(null);
					charList.add("Q");
				}
				else				
				{
					
					qtmTlIntrejectionmst.setQirmKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST)); // set the sequnce number
					masterID = qtmTlIntrejectionmst.getQirmKeyid();					
					//qtmTlIntrejectionmst.setQirmElementid("{}");//for demo purpose
					//qtmTlIntrejectionmst.setQirmParentmasterid(qtmTlIntrejectionmst.getQirmKeyid());
					//qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());
					qtmTlIntrejectionmst.setQirmReferencekeyid(qtmTlIntrejectionmst.getQirmKeyid());
					sqls.add(QtmTlIntrejectionmstSql.getInsertSql(qtmTlIntrejectionmstSql.getQirmDbFields(), qtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table
					valueList.add(null);
					dataTypes.add(null);
					charList.add("Q");
				}
				
				intRejection.setQirdMasterid(qtmTlIntrejectionmst.getQirmKeyid());
				intRejection.setQirdReferenceid(qtmTlIntrejectionmst.getQirmKeyid());
				intRejection.setQirdCreatedby(qtmTlIntrejectiondtl.getQirdCreatedby());
				if (UIUtils.isValidKeyId(intRejection.getQirdKeyid()))
				{	
					intRejection.setQirdKeyid(intRejection.getQirdKeyid());
					if(UIUtils.isValidKeyId(intRejection.getQirdQuantity()))
					{
						sqls.add(qtmTlIntrejectiondtlSql.getUpdateSql( qtmTlIntrejectiondtlSql.getQirdDbFields(), intRejection.getSaveArray()));// add insert sql for detail table
						valueList.add(null);
						dataTypes.add(null);
						charList.add("Q");
					}
					else
					{
						if(!UIUtils.isValidKeyId(intRejection.getQirdQuantity()))
							intRejection.setQirdQuantity("0");
						if(!UIUtils.isValidKeyId(intRejection.getQird4mtype()))
							intRejection.setQird4mtype("{}");						
						
						if(!UIUtils.isValidKeyId(intRejection.getQirdType()))
							intRejection.setQirdType("X");
						
						//sqls.add("DELETE "+QtmTlIntrejectiondtlSql.TBL_QTM_TL_INTREJECTIONDTL +" WHERE QIRD_KEYID='"+intRejection.getQirdKeyid()+"'");
						sqls.add(qtmTlIntrejectiondtlSql.getUpdateSql( qtmTlIntrejectiondtlSql.getQirdDbFields(), intRejection.getSaveArray()));// add insert sql for detail table
						valueList.add(null);
						dataTypes.add(null);
						charList.add("Q");
						 afterUpdate = false;
					}
				}
				else {
					intRejection.setQirdKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectiondtlSql.TBL_QTM_TL_INTREJECTIONDTL)); // set the sequnce number
					sqls.add(QtmTlIntrejectiondtlSql.getInsertSql(qtmTlIntrejectiondtlSql.getQirdDbFields(), intRejection.getSaveArray()));// add insert sql for detail table
					valueList.add(null);
					dataTypes.add(null);
					charList.add("Q");
				}
				//if(afterUpdate)
				//{
					String qihbKeyid=intRejection.getQirdQhbKeyid();
					CommonMessage.debugMsg("Entry type"+qtmTlIntrejectiondtl.getQirdEntrytype());
					CommonMessage.debugMsg(intRejection.getQirdEntrytype());
					boolean toUpdate = false;
					if (intRejection.getQirdEntrytype().equals("MRB") ) {
						sqls.add(QtmTlIntrejectionmstSql.MRBUpdateSql(qihbKeyid));
						valueList.add(null);
						dataTypes.add(null);
						charList.add("Q");
						toUpdate = true;
					}
					else if (intRejection.getQirdEntrytype().equals("QAH")) {
						sqls.add(QtmTlIntrejectionmstSql.QAHUpdateSql(qihbKeyid));
						valueList.add(null);
						dataTypes.add(null);
						charList.add("Q");	
						toUpdate = true;
					}
					
					if(toUpdate)
					{
						
						sqls.add(QtmTlIntrejectionmstSql.UpdateHourlySql());
						Object [] hourlyDatas	= {qihbKeyid};
						int [] hourlyTypes =  {Types.VARCHAR};
						valueList.add(hourlyDatas);
						dataTypes.add(hourlyTypes);
						charList.add("Q");
						
						
						// FROM INT_REJE TO PCS CHANGES			
						String qirmKeyid=qtmTlIntrejectionmst.getQirmKeyid();
						String pldetailId=qtmTlIntrejectionmst.getQirmPldetailid();
						sqls.add(QtmTlIntrejectionmstSql.UpdateRejectionMstSql(qirmKeyid));
						valueList.add(null);
						dataTypes.add(null);
						charList.add("Q");	
						
						CommonMessage.debugMsg("qtmTlIntrejectionmst.getQirmCellid()"+qtmTlIntrejectionmst.getQirmSectionid());
						
						String detailTableName = "PCS_TL_" + dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", qtmTlIntrejectionmst.getQirmSectionid());
						detailTableName = detailTableName.replaceAll("-", "#");
						
						CommonMessage.debugMsg("detailTableName"+detailTableName);
						sqls.add(QtmTlIntrejectionmstSql.PCSUpdateFuncn());
						Object [] insertPCSDatas = {detailTableName,pldetailId,"N","N"};
						int [] insertPCSTypes =  {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
						valueList.add(insertPCSDatas);
						dataTypes.add(insertPCSTypes);
						charList.add("P");
					//}
				}
				
			}
		}
		char [] sqlType = new char[charList.size()];
		CommonMessage.debugMsg("lENGTH : "+sqlType.length);
		for(int c=0;c<sqlType.length;c++)
		{
			CommonMessage.debugMsg(c +" : "+charList.get(c));
			sqlType[c] = charList.get(c).charAt(0);
			CommonMessage.debugMsg(c +" : "+sqlType[c]);
		}
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes,sqlType);
		if(CommonFunctions.isValidKeyId(qtmTlIntrejectionmst.getQirmMachineid()))
			qtmTlIntrejectiondtl.setQirdTempfield5(qtmTlIntrejectionmst.getQirmMachineid());
		return qtmTlIntrejectiondtl;	
		
	}
	/*public QtmTlIntrejectiondtl create(QtmTlIntrejectionmst qtmTlIntrejectionmst, QtmTlIntrejectiondtl qtmTlIntrejectiondtl,
			IntRejEntryBean intRejEntryBean) throws Exception  
	{		
		List<String> sqls = new ArrayList<String>();  //sqls for execution 
		String masterID = qtmTlIntrejectionmst.getQirmKeyid();
		if(qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl()!= null && qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl().size()>0) // check for detail table data
		{
			for(int i =0;i<qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl().size();i++)
			{	
				QtmTlIntrejectiondtl intRejection = qtmTlIntrejectiondtl.getQtmTlIntrejectiondtl().get(i);
				intRejection.setQirdQhbKeyid(qtmTlIntrejectiondtl.getQirdQhbKeyid());
				intRejection.setQirdEntrytype(qtmTlIntrejectiondtl.getQirdEntrytype());
				detailFillValues(intRejection);
				StringBuffer sql1 =new StringBuffer();
				StringBuffer sql2  =new StringBuffer();
				StringBuffer sql3  =new StringBuffer();
				StringBuffer sql4  =new StringBuffer();
				CommonMessage.debugMsg("Master Id : "+qtmTlIntrejectionmst.getQirmKeyid());
				if (! UIUtils.isValidKeyId(qtmTlIntrejectionmst.getQirmKeyid()))
				{
					
					qtmTlIntrejectionmst.setQirmKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST)); // set the sequnce number
					masterID = qtmTlIntrejectionmst.getQirmKeyid();
					qtmTlIntrejectionmst.setQirmParentmasterid(qtmTlIntrejectionmst.getQirmKeyid());
					qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());
					qtmTlIntrejectionmst.setQirmReferencekeyid(qtmTlIntrejectionmst.getQirmKeyid());
					sqls.add(QtmTlIntrejectionmstSql.getInsertSql(qtmTlIntrejectionmstSql.getQirmDbFields(), qtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table
				}
				else {
						
						if(UIUtils.isValidKeyId(masterID))
						{
							CommonMessage.debugMsg("Else Master Id : "+masterID);
							qtmTlIntrejectionmst.setQirmKeyid(masterID);
							qtmTlIntrejectionmst.setQirmParentmasterid(qtmTlIntrejectionmst.getQirmKeyid());
							qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());			
							qtmTlIntrejectionmst.setQirmReferencekeyid(qtmTlIntrejectionmst.getQirmKeyid());
							sqls.add(QtmTlIntrejectionmstSql.getUpdateSql(qtmTlIntrejectionmstSql.getQirmDbFields(), qtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table
						}
						else
						{
							qtmTlIntrejectionmst.setQirmKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST)); // set the sequnce number
							masterID = qtmTlIntrejectionmst.getQirmKeyid();
							qtmTlIntrejectionmst.setQirmParentmasterid(qtmTlIntrejectionmst.getQirmKeyid());
							qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());
							qtmTlIntrejectionmst.setQirmReferencekeyid(qtmTlIntrejectionmst.getQirmKeyid());
							sqls.add(QtmTlIntrejectionmstSql.getInsertSql(qtmTlIntrejectionmstSql.getQirmDbFields(), qtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table
						}
					//qtmTlIntrejectionmst.setQirmKeyid(intRejection.getQirdMasterid());
					//qtmTlIntrejectionmst.setQirmParentmasterid(qtmTlIntrejectionmst.getQirmKeyid());
					//qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());			
					//qtmTlIntrejectionmst.setQirmReferencekeyid(qtmTlIntrejectionmst.getQirmKeyid());
					//sqls.add(QtmTlIntrejectionmstSql.getUpdateSql(qtmTlIntrejectionmstSql.getQirmDbFields(), qtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table
				}
				
				CommonMessage.debugMsg("qtmTlIntrejectionmst.getQirmKeyid()"+qtmTlIntrejectionmst.getQirmKeyid());
				
				intRejection.setQirdMasterid(qtmTlIntrejectionmst.getQirmKeyid());
				intRejection.setQirdReferenceid(qtmTlIntrejectionmst.getQirmKeyid());
				intRejection.setQirdCreatedby(qtmTlIntrejectiondtl.getQirdCreatedby());
					if (! UIUtils.isValidKeyId(intRejection.getQirdKeyid()))
					{	
						intRejection.setQirdKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectiondtlSql.TBL_QTM_TL_INTREJECTIONDTL)); // set the sequnce number
						sqls.add(QtmTlIntrejectiondtlSql.getInsertSql(qtmTlIntrejectiondtlSql.getQirdDbFields(), intRejection.getSaveArray()));// add insert sql for detail table
					}
					else {
						intRejection.setQirdKeyid(intRejection.getQirdKeyid());
						sqls.add(qtmTlIntrejectiondtlSql.getUpdateSql( qtmTlIntrejectiondtlSql.getQirdDbFields(), intRejection.getSaveArray()));// add insert sql for detail table
					}
		
				String qihbKeyid=intRejection.getQirdQhbKeyid();
				CommonMessage.debugMsg("Entry type"+qtmTlIntrejectiondtl.getQirdEntrytype());
				CommonMessage.debugMsg(intRejection.getQirdEntrytype());
				if (intRejection.getQirdEntrytype().equals("MRB") ) {
					sql1.append(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_MRBQTY=( ");
					sql1.append(" SELECT SUM(QIRD_QUANTITY) FROM QTM_TL_INTREJECTIONDTL  ");
					sql1.append(" WHERE QIRD_QHB_KEYID='" +qihbKeyid + "' AND QIRD_ENTRYTYPE ='MRB')");
					sql1.append(" WHERE QIHB_KEYID='" +qihbKeyid + "' ");			
				}
				
				else if (intRejection.getQirdEntrytype().equals("QAH")) {
					sql1.append(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_QAHOLD =( ");
					sql1.append(" SELECT SUM(QIRD_QUANTITY) FROM QTM_TL_INTREJECTIONDTL  ");
					sql1.append(" WHERE QIRD_QHB_KEYID='" +qihbKeyid + "' AND QIRD_ENTRYTYPE ='QAH')");
					sql1.append(" WHERE QIHB_KEYID='" +qihbKeyid + "' ");			
				}
					
				if (intRejection.getQirdEntrytype().equals("MRB") || intRejection.getQirdEntrytype().equals("QAH")) {
					
					sqls.add(sql1.toString());
					
					sql2.append(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_ACCEPTEDQTY = (QIHB_INSPECTEDQTY - (QIHB_MRBQTY+QIHB_QAHOLD)), ") ;
					sql2.append(" QIHB_REJECTEDQTY= (QIHB_MRBQTY+QIHB_QAHOLD) ");
					sql2.append(" WHERE QIHB_KEYID='" +qihbKeyid + "'");
					sqls.add(sql2.toString());
					
					
					// FROM INT_REJE TO PCS CHANGES			
					String qirmKeyid=qtmTlIntrejectionmst.getQirmKeyid();
					String pldetailId=qtmTlIntrejectionmst.getQirmPldetailid();
					
					sql3.append(" UPDATE QTM_TL_INTREJECTIONMST SET   ") ;
					sql3.append(" QIRM_INSPECTIONQTY = (SELECT nvl(SUM(QIHB_INSPECTEDQTY),0) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'), ");
					sql3.append(" QIRM_ACCEPTEDQTY = (SELECT nvl(SUM(QIHB_ACCEPTEDQTY),0) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'),");
					sql3.append(" QIRM_BACKLOGQTY = (SELECT nvl(SUM(QIHB_REJECTEDQTY),0) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'),");
					sql3.append(" QIRM_QAHOLDQTY = (SELECT nvl(SUM(QIHB_QAHOLD),0) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'),");
					sql3.append(" QIRM_QAHOLDPERCENTAGE = (SELECT nvl(ROUND(DECODE(SUM(QIHB_INSPECTEDQTY),0,0,SUM(QIHB_QAHOLD)/SUM(QIHB_INSPECTEDQTY)*100),2),0) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "')");
					sql3.append("  WHERE QIRM_KEYID='" + qirmKeyid + "' ");
					sqls.add(sql3.toString());
					
					CommonMessage.debugMsg("qtmTlIntrejectionmst.getQirmCellid()"+qtmTlIntrejectionmst.getQirmSectionid());
					
					String detailTableName = "PCS_TL_" + dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", qtmTlIntrejectionmst.getQirmSectionid());
					detailTableName = detailTableName.replaceAll("-", "#");
					
					CommonMessage.debugMsg("detailTableName"+detailTableName);
					
					sql4.append("  UPDATE " + detailTableName + " SET "); 
					sql4.append("  INSPECTEDQTY = ( SELECT SUM(QIRM_INSPECTIONQTY) FROM QTM_TL_INTREJECTIONMST WHERE QIRM_PLDETAILID='" + pldetailId + "'), ");
					sql4.append("  QAACCEPTEDQTY = ( SELECT SUM(QIRM_ACCEPTEDQTY ) FROM QTM_TL_INTREJECTIONMST WHERE QIRM_PLDETAILID='" + pldetailId + "') ");
					sql4.append("  WHERE PLDETAILSID=  '" + pldetailId + "' ");
					
					sqls.add(sql4.toString());
				}
			}
			
		}
			

			
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
			
		if(CommonFunctions.isValidKeyId(qtmTlIntrejectionmst.getQirmMachineid()))
			qtmTlIntrejectiondtl.setQirdTempfield5(qtmTlIntrejectionmst.getQirmMachineid());
		return qtmTlIntrejectiondtl;
	}*/
	
	public QtmTlIntrejectiondtl createYY(QtmTlIntrejectionmst qtmTlIntrejectionmst, QtmTlIntrejectiondtl qtmTlIntrejectiondtl,
			IntRejEntryBean intRejEntryBean) throws Exception  
	{		
				List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		
				StringBuffer sql1 =new StringBuffer();
				StringBuffer sql2  =new StringBuffer();
				StringBuffer sql3  =new StringBuffer();
				StringBuffer sql4  =new StringBuffer();
		
				if (!UIUtils.isValidKeyId(intRejEntryBean.getQirmKeyid()))
				{
					qtmTlIntrejectionmst.setQirmKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST)); // set the sequnce number
					qtmTlIntrejectionmst.setQirmParentmasterid(qtmTlIntrejectionmst.getQirmKeyid());
					qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());
					qtmTlIntrejectionmst.setQirmReferencekeyid(qtmTlIntrejectionmst.getQirmKeyid());
					sqls.add(QtmTlIntrejectionmstSql.getInsertSql(qtmTlIntrejectionmstSql.getQirmDbFields(), qtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table
				}
				else {
					CommonMessage.debugMsg("Inserttttttttt");
					String parentId = dbActionTemplate.getSingleValue(QtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST, "QIRM_PARENTMASTERID", "QIRM_KEYID", intRejEntryBean.getQirmKeyid());
					String linkId = dbActionTemplate.getSingleValue(QtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST, "QIRM_LINKMASTERID", "QIRM_KEYID", intRejEntryBean.getQirmKeyid());
					qtmTlIntrejectionmst.setQirmKeyid(intRejEntryBean.getQirmKeyid());
					if(!CommonFunctions.isValidKeyId(parentId))
						qtmTlIntrejectionmst.setQirmParentmasterid(qtmTlIntrejectionmst.getQirmKeyid());
					else
						qtmTlIntrejectionmst.setQirmParentmasterid(parentId);
					
					if(!CommonFunctions.isValidKeyId(linkId))
						qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());
					else
						qtmTlIntrejectionmst.setQirmLinkmasterid(linkId);
					
					qtmTlIntrejectionmst.setQirmLinkmasterid(qtmTlIntrejectionmst.getQirmKeyid());			
					qtmTlIntrejectionmst.setQirmReferencekeyid(qtmTlIntrejectionmst.getQirmKeyid());
					sqls.add(QtmTlIntrejectionmstSql.getUpdateSql(qtmTlIntrejectionmstSql.getQirmDbFields(), qtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table
				}
				
				CommonMessage.debugMsg("qtmTlIntrejectionmst.getQirmKeyid()"+qtmTlIntrejectionmst.getQirmKeyid());
				
				qtmTlIntrejectiondtl.setQirdMasterid(qtmTlIntrejectionmst.getQirmKeyid());
				qtmTlIntrejectiondtl.setQirdReferenceid(qtmTlIntrejectionmst.getQirmKeyid());
					
					if (! UIUtils.isValidKeyId(intRejEntryBean.getQirdKeyid()))
					{	
						qtmTlIntrejectiondtl.setQirdKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectiondtlSql.TBL_QTM_TL_INTREJECTIONDTL)); // set the sequnce number
						sqls.add(QtmTlIntrejectiondtlSql.getInsertSql(qtmTlIntrejectiondtlSql.getQirdDbFields(), qtmTlIntrejectiondtl.getSaveArray()));// add insert sql for detail table
					}
					else {
						qtmTlIntrejectiondtl.setQirdKeyid(intRejEntryBean.getQirdKeyid());
						sqls.add(qtmTlIntrejectiondtlSql.getUpdateSql( qtmTlIntrejectiondtlSql.getQirdDbFields(), qtmTlIntrejectiondtl.getSaveArray()));// add insert sql for detail table
					}
		
		/*
				String qihbKeyid=qtmTlIntrejectiondtl.getQirdQhbKeyid();
				
				if (qtmTlIntrejectiondtl.getQirdEntrytype().equals("MRB") ) {
					sql1.append(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_MRBQTY=( ");
					sql1.append(" SELECT SUM(QIRD_QUANTITY) FROM QTM_TL_INTREJECTIONDTL  ");
					sql1.append(" WHERE QIRD_QHB_KEYID='" +qihbKeyid + "' AND QIRD_ENTRYTYPE ='MRB')");
					sql1.append(" WHERE QIHB_KEYID='" +qihbKeyid + "' ");			
				}
				
				else if (qtmTlIntrejectiondtl.getQirdEntrytype().equals("QAH")) {
					sql1.append(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_QAHOLD =( ");
					sql1.append(" SELECT SUM(QIRD_QUANTITY) FROM QTM_TL_INTREJECTIONDTL  ");
					sql1.append(" WHERE QIRD_QHB_KEYID='" +qihbKeyid + "' AND QIRD_ENTRYTYPE ='QAH')");
					sql1.append(" WHERE QIHB_KEYID='" +qihbKeyid + "' ");			
				}
					
				if (qtmTlIntrejectiondtl.getQirdEntrytype().equals("MRB") || qtmTlIntrejectiondtl.getQirdEntrytype().equals("QAH")) {
					
					sqls.add(sql1.toString());
					
					sql2.append(" UPDATE QTM_TL_INTERNALREJECTIONHOURLY SET QIHB_ACCEPTEDQTY = (QIHB_INSPECTEDQTY - (QIHB_MRBQTY+QIHB_QAHOLD)), ") ;
					sql2.append(" QIHB_REJECTEDQTY= (QIHB_MRBQTY+QIHB_QAHOLD) ");
					sql2.append(" WHERE QIHB_KEYID='" +qihbKeyid + "'");
					sqls.add(sql2.toString());
					
					
					// FROM INT_REJE TO PCS CHANGES			
					String qirmKeyid=qtmTlIntrejectionmst.getQirmKeyid();
					String pldetailId=qtmTlIntrejectionmst.getQirmPldetailid();
					
					sql3.append(" UPDATE QTM_TL_INTREJECTIONMST SET   ") ;
					sql3.append(" QIRM_INSPECTIONQTY = (SELECT SUM(QIHB_INSPECTEDQTY) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'), ");
					sql3.append(" QIRM_ACCEPTEDQTY = (SELECT SUM(QIHB_ACCEPTEDQTY) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'),");
					sql3.append(" QIRM_BACKLOGQTY = (SELECT SUM(QIHB_REJECTEDQTY) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'),");
					sql3.append(" QIRM_QAHOLDQTY = (SELECT SUM(QIHB_QAHOLD) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "'),");
					sql3.append(" QIRM_QAHOLDPERCENTAGE = (SELECT ROUND(DECODE(SUM(QIHB_INSPECTEDQTY),0,0,SUM(QIHB_QAHOLD)/SUM(QIHB_INSPECTEDQTY)*100),2) FROM QTM_TL_INTERNALREJECTIONHOURLY WHERE QIHB_QTM_KEYID='" + qirmKeyid + "')");
					sql3.append("  WHERE QIRM_KEYID='" + qirmKeyid + "' ");
					sqls.add(sql3.toString());
					
					CommonMessage.debugMsg("qtmTlIntrejectionmst.getQirmCellid()"+qtmTlIntrejectionmst.getQirmSectionid());
					
					String detailTableName = "PCS_TL_" + dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE", "SECT_KEYID", qtmTlIntrejectionmst.getQirmSectionid());
					detailTableName = detailTableName.replaceAll("-", "#");
					
					CommonMessage.debugMsg("detailTableName"+detailTableName);
					
					sql4.append("  UPDATE " + detailTableName + " SET "); 
					sql4.append("  INSPECTEDQTY = ( SELECT SUM(QIRM_INSPECTIONQTY) FROM QTM_TL_INTREJECTIONMST WHERE QIRM_PLDETAILID='" + pldetailId + "'), ");
					sql4.append("  QAACCEPTEDQTY = ( SELECT SUM(QIRM_ACCEPTEDQTY ) FROM QTM_TL_INTREJECTIONMST WHERE QIRM_PLDETAILID='" + pldetailId + "') ");
					sql4.append("  WHERE PLDETAILSID=  '" + pldetailId + "' ");
					
					sqls.add(sql4.toString());
				}
			*/
			
			
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		
			
		if(CommonFunctions.isValidKeyId(qtmTlIntrejectionmst.getQirmMachineid()))
			qtmTlIntrejectiondtl.setQirdTempfield5(qtmTlIntrejectionmst.getQirmMachineid());
		return qtmTlIntrejectiondtl;
	}
		

	private QtmTlIntrejectiondtl detailFillValues(QtmTlIntrejectiondtl qtmTlIntrejectiondtl)
	{
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdProdfamilyid()))
			qtmTlIntrejectiondtl.setQirdProdfamilyid("{}");
		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdBacklogflag()))
			qtmTlIntrejectiondtl.setQirdBacklogflag("N");
		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdRemarks()))
			qtmTlIntrejectiondtl.setQirdRemarks("{}");

		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdWwmasterid()))
			qtmTlIntrejectiondtl.setQirdWwmasterid("{}");
		

		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdEntrytype()))
			qtmTlIntrejectiondtl.setQirdEntrytype("DIR");		
	
		
		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdQhbKeyid()))
			qtmTlIntrejectiondtl.setQirdQhbKeyid("{}");

		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQird4mtype()))
			qtmTlIntrejectiondtl.setQird4mtype("{}");
		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdType()))
			qtmTlIntrejectiondtl.setQirdType("X");
		
		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdSubprocessid())) 
			qtmTlIntrejectiondtl.setQirdSubprocessid("{}");
		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdQty())) 
			qtmTlIntrejectiondtl.setQirdQty("0");
		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdTempfield4())) 
			qtmTlIntrejectiondtl.setQirdTempfield4("{}");
		if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdTempfield5())) 
			qtmTlIntrejectiondtl.setQirdTempfield5("{}");
		
	
		qtmTlIntrejectiondtl.setQirdActive("Y");

		qtmTlIntrejectiondtl.setQirdCreatedon(dateTime);
		qtmTlIntrejectiondtl.setQirdModifiedon(dateTime);
		return qtmTlIntrejectiondtl;
		
	}

	@Override
	public QtmTlIntrejectionmst create(
			QtmTlIntrejectionmst newQtmTlIntrejectionmst,
			List<QtmTlIntrejectiondtl> newQtmTlIntrejectiondtl) throws Exception {
			List<String> sqls = new ArrayList<String>();
			QtmTlIntrejectionmstSql qtmTlIntrejectionmstSql = new QtmTlIntrejectionmstSql();
			QtmTlIntrejectiondtlSql qtmTlIntrejectiondtlSql = new QtmTlIntrejectiondtlSql();
			
			if (!UIUtils.isValidKeyId(newQtmTlIntrejectionmst.getQirmKeyid()))
			{
				newQtmTlIntrejectionmst.setQirmKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST,15,"QIRM","MMYY", "Y")); // set the sequnce number)
				sqls.add(QtmTlIntrejectionmstSql.getInsertSql(qtmTlIntrejectionmstSql.getQirmDbFields(), newQtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table
			}
			else 
				sqls.add(QtmTlIntrejectionmstSql.getUpdateSql(qtmTlIntrejectionmstSql.getQirmDbFields(), newQtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table				
			
					
			
				for(QtmTlIntrejectiondtl qtmTlIntrejectiondtl :  newQtmTlIntrejectiondtl)
				{ 
					qtmTlIntrejectiondtl.setQirdMasterid(newQtmTlIntrejectionmst.getQirmKeyid());
							
					if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getQirdKeyid())) {
						qtmTlIntrejectiondtl.setQirdKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectiondtlSql.TBL_QTM_TL_INTREJECTIONDTL,15,"QIRD","MMYY","Y"));
						CommonMessage.debugMsg("QirdKeyid" + qtmTlIntrejectiondtl.getQirdKeyid());
						qtmTlIntrejectiondtl.setQirdMasterid(newQtmTlIntrejectionmst.getQirmKeyid());
						sqls.add(QtmTlIntrejectiondtlSql.getInsertSql(qtmTlIntrejectiondtlSql.getQirdDbFields(), qtmTlIntrejectiondtl.getSaveArray())); // add insert sql for master table
					}
					else {
						
						sqls.add(QtmTlIntrejectiondtlSql.getUpdateSql(qtmTlIntrejectiondtlSql.getQirdDbFields(), qtmTlIntrejectiondtl.getSaveArray())); 
					}	
				}
				
				dbActionTemplate.executeStatements(sqls);
			
		
		return newQtmTlIntrejectionmst;
	}
	
	public QtmTlIntrejectionmst update(
			QtmTlIntrejectionmst newQtmTlIntrejectionmst,
			List<QtmTlIntrejectiondtl> newQtmTlIntrejectiondtl) throws Exception {
			List<String> sqls = new ArrayList<String>();
			QtmTlIntrejectionmstSql qtmTlIntrejectionmstSql = new QtmTlIntrejectionmstSql();
			QtmTlIntrejectiondtlSql qtmTlIntrejectiondtlSql = new QtmTlIntrejectiondtlSql();
			if (UIUtils.isValidKeyId(newQtmTlIntrejectionmst.getQirmKeyid()))
			{
				//newQtmTlIntrejectionmst.setQirmKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectionmstSql.TBL_QTM_TL_INTREJECTIONMST,15,"QIRM","MMYY", "Y")); // set the sequnce number)
				sqls.add(QtmTlIntrejectionmstSql.getUpdateSql(qtmTlIntrejectionmstSql.getQirmDbFields(), newQtmTlIntrejectionmst.getSaveArray())); // add insert sql for master table
				
				for(QtmTlIntrejectiondtl qtmTlIntrejectiondtl :  newQtmTlIntrejectiondtl)
				{  
					if (!UIUtils.isValidKeyId(qtmTlIntrejectiondtl.getPlrkKeyid()))
					{
						qtmTlIntrejectiondtl.setPlrkKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectiondtlSql.TBL_QTM_TL_INTREJECTIONDTL,15,"QIRD","MMYY","Y"));
						qtmTlIntrejectiondtl.setQirdMasterid(newQtmTlIntrejectionmst.getQirmKeyid());
						sqls.add(QtmTlIntrejectiondtlSql.getInsertSql(qtmTlIntrejectiondtlSql.getQirdDbFields(), qtmTlIntrejectiondtl.getSaveArray())); // add insert sql for master table
					}
					else
					{
						//qtmTlIntrejectiondtl.setPlrkKeyid(dbActionTemplate.getSequenceNumber(QtmTlIntrejectiondtlSql.TBL_QTM_TL_INTREJECTIONDTL,15,"QIRD","MMYY","Y"));
						qtmTlIntrejectiondtl.setQirdMasterid(newQtmTlIntrejectionmst.getQirmKeyid());
						sqls.add(QtmTlIntrejectiondtlSql.getUpdateSql(qtmTlIntrejectiondtlSql.getQirdDbFields(), qtmTlIntrejectiondtl.getSaveArray())); // add insert sql for master table
					}
				}
				
				dbActionTemplate.executeStatements(sqls);
			}
		
		return newQtmTlIntrejectionmst;
	}
	//Change in query  - Swetha - 6 Dec
	@Override
	public List<String[]> getInternalRejectionMstGrid(CommonFilter commonFilter) {
		try
		{			
			
			StringBuilder sql = new StringBuilder();

			sql.append(" SELECT 'Keyid','Functional Location','Grade Specification','Inspection Date','Inspected Shift' ");
			sql.append(" UNION ALL ");
			sql.append(" SELECT QIrm_Keyid, PARENTS, GSPC_NAME || '-' || GSPC_CODE, to_char(qirm_inspectiondate, 'YYYY-MM-DD'), SFTM_NAME ");
			sql.append(" FROM QTM_TL_INTREJECTIONMST ");
			sql.append(" CROSS JOIN GEN_MV_FLIDHIERARCHY ");
			sql.append(" LEFT JOIN PCS_TL_GRADESPECMST ON GSPC_KEYID = qirm_productid ");
			sql.append(" INNER JOIN GEN_TL_SHIFTMST ON SFTM_KEYID = qirm_inspectedshiftid ");
			sql.append(" WHERE QIRM_FLID = FLID ");

			if(commonFilter.getFlid() != null)
			{
			    sql.append(" AND POSITION('" + commonFilter.getFlid() + "' IN PARENTFLIDS || FLID) > 0 ");
			}
			 
			 
			List<String[]> ScrapBreakupList = dbActionTemplate.getDataList(sql.toString());
			return ScrapBreakupList;
		}	
		catch(Exception e)
		{
			e.printStackTrace();
			CommonMessage.debugMsg("Exception in CALENDAR dao impl"+e.getMessage());
		}
		return null;	
	}

	@Override
	public QtmTlIntrejectionmst getInternalRejectionMstdata(String keyid) throws Exception {
		QtmTlIntrejectionmst qtmTlIntrejectionmst = new QtmTlIntrejectionmst();
		QtmTlIntrejectionmstSql qtmTlIntrejectionmstSql = new QtmTlIntrejectionmstSql();
		String sql = qtmTlIntrejectionmstSql.selectmst(keyid);
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] { keyid };
		qtmTlIntrejectionmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return qtmTlIntrejectionmst;
	}
	
	@Override
	public Workbook getMstGridExcelData(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		
		ResultSet rs = null;
		   try{
			CommonMessage.debugMsg("test to export excel--new");		
			
					
			StringBuilder sql = new StringBuilder();
			 
			 sql.append(" select 'Keyid','Functional Location','Grade Specification','Inspection Date','Inspected Shift'  from dual ");
			 sql.append( " Union ");
			 sql.append( " SELECT QIrm_Keyid,PARENTS,PRDM_NAME||'-'||PRDM_CODE,to_char(qirm_inspectiondate),SFTM_NAME ");
			 sql.append( " FROM QTM_TL_INTREJECTIONMST ,GEN_MV_FLIDHIERARCHY ,PCS_TL_PRODUCTMST,GEN_TL_SHIFTMST  WHERE QIRM_FLID = FLID ");
			 sql.append( " AND PRDM_KEYID = qirm_productid AND SFTM_KEYID = qirm_inspectedshiftid ");
			 
			if( commonFilter.getFlid() != null )
			{
				sql.append( " and instr(PARENTFLIDS||FLID  ,'"+ commonFilter.getFlid() +"' ) > 0 ");
			}
			//CommonMessage.debugMsg("SQL :"+sql.toString());
			rs=dbActionTemplate.getData(sql.toString());
			
			//CommonMessage.debugMsg("Result set :" + rs);
			
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			
			//CommonMessage.debugMsg("excelutil :"+excelUtils +"Colmodel :" + colModel);
			
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}
	/*
	public Workbook getAllExcelDataNew(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {
		ResultSet rs = null;
		   try{
			CommonMessage.debugMsg("test to export excel--new");
			
			CommonMessage.debugMsg("Inside export daoimpl Excel");
			List<String > paramValues = new ArrayList<String>();
			
			String condParms = FilterCondSql.getQualityRelatedStr(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			condParms += "ISFORPERCENT=N;";
			
			paramValues.add(condParms);
			paramValues.add(commonParams);
			CommonMessage.debugMsg("paramValues:"+paramValues);
			//QTM_PC_QUALITY.QTM_FN_MONTHWISEREJQTY
			
			rs= dbActionTemplate.dbFunctionCall("QTM_PC_QUALITY.QTM_FN_MONTHWISEREJQTY",paramValues);
			//rs= dbActionTemplate.dbFunctionCall("QTM_PC_QUALITY.QTM_FN_INTERNALREJRPTPRC2",paramValues);		
			
			
			ExcelUtils excelUtils = new ExcelUtils(colModel);
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			
			XLConditionalFormats condFormat = new XLConditionalFormats();
			condFormat.setFontColor(new RGB(254,0,0)); //red font
			condFormat.setFontName("Wingdings");
			condFormat.setFontHeightPoint((short)14);
			condFormat.setFontBoldWeight((short)20);
			condFormat.setFromCol(13);
			condFormat.setToCol(-1);
			condFormat.setOperator(ComparisonOperator.EQUAL);
			condFormat.setCondValue( (char)252+""); //Tick
			condFormat.setIdentfier("tick");
			condFormats.add(condFormat);
			excelUtils.setCondFormats(condFormats);
			return excelUtils.writeToExcel(rs,rptFormat, 1,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}*/
}
