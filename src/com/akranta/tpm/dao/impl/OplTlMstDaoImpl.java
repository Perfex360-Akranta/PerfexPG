/** ----  Author : Siddharth.A ----  **/
package com.akranta.tpm.dao.impl;


import java.io.File;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.OplTlMstDao;
import com.akranta.tpm.dao.sql.BdmTlYycountermeasurelinkSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlAllmoduleimgfileSql;
import com.akranta.tpm.dao.sql.GenTlDocupdatesSql;
import com.akranta.tpm.dao.sql.GenTlMomKpiLinkSql;
import com.akranta.tpm.dao.sql.GenTlMomdtlSql;
import com.akranta.tpm.dao.sql.GenTlMommstSql;
import com.akranta.tpm.dao.sql.OplTlLessonSql;
import com.akranta.tpm.dao.sql.OplTlMstSql;
import com.akranta.tpm.dao.sql.OplTlPillarlinkSql;
import com.akranta.tpm.dao.sql.OplTlStudentSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.BdmTlYycountermeasurelink;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlDocupdates;
import com.akranta.tpm.model.OplTlLesson;
import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.model.OplTlPillarlink;
import com.akranta.tpm.model.OplTlStudent;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.utils.CommonMessage;

/* dao implementation */
public class OplTlMstDaoImpl implements OplTlMstDao {


	private DBActionTemplate dbActionTemplate; 
	private OplTlMstSql oplTlMstSql ;
	private OplTlPillarlinkSql oplTlPillarlinkSql;
	private OplTlLessonSql oplTlLessonSql;
	private GenTlAllmoduleimgfileSql genTlAllmoduleimgfileSql; 
	private BdmTlYycountermeasurelinkSql bdmTlYycountermeasurelinkSql;
	private GenTlDocupdatesSql genTlDocupdatesSql;
	//-- added by vignesh -- //
	OplTlMstServiceApi oplServiceApi;
    FunctionCallApi fnCallApi;
	
	
	public OplTlMstDaoImpl(DBActionTemplate dbActionTemplate) 
	
	{
		this.dbActionTemplate = dbActionTemplate;
		oplTlMstSql = new OplTlMstSql();
		oplTlPillarlinkSql = new OplTlPillarlinkSql();
		oplTlLessonSql = new OplTlLessonSql();
		genTlAllmoduleimgfileSql = new GenTlAllmoduleimgfileSql();
		bdmTlYycountermeasurelinkSql = new BdmTlYycountermeasurelinkSql();
		genTlDocupdatesSql = new GenTlDocupdatesSql();
	}
	
	//-- added by vignesh -- //
	public void OplTlMstDaoImplJwt(String JwtToken) {
	    try {
	        oplServiceApi = new OplTlMstServiceApi(JwtToken);
	        fnCallApi = new FunctionCallApi(JwtToken);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	//-- added by vignesh -- //

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	// -Vignesh --//
	
	
	
	
	// -Vignesh --//

	public OplTlMst create(OplTlMst oplTlMst,BdmTlYycountermeasurelink bdmTlYycountermeasurelink,GenTlDocupdates genTlDocupdates) 	throws BusinessApplicationExceptions,Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		String sqlss = "" ;
		try
		{   
			//OplTlStudent newOplTlStudent=new OplTlStudent();
			//CommonMessage.debugMsg(" Inside ["+i+"]::::: now "+newOplTlStudent.get(i).getOpllMtrxKeyid());
			
			//Adding This line for sequence generation no
			
			String elementId = oplTlMst.getOplmElementid();
			CommonMessage.debugMsg(" Inside Dao Impl elementId "+elementId);

			String location = null;
		 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,OplTlMstSql.TBL_OPL_TL_MST);
		 	
		 	oplTlMst.setOplmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,12,"OP","YY","Y"));
			
		 	//oplTlMst.setOplmKeyid(dbActionTemplate.getSequenceNumber(OplTlMstSql.TBL_OPL_TL_MST,12,"OP","YY","Y")); // set the sequence number 
			sqls.add(OplTlMstSql.getInsertSql(oplTlMstSql.getOplmDbFields(), oplTlMst.getSaveArray())); // add insert sql for master table
			//CommonMessage.debugMsg(" Inside :: If create 8");
			setPillarLinkSqls(sqls,oplTlMst);
			//CommonMessage.debugMsg(" Inside :: If create 9");
			List <OplTlStudent> StudentLinkList = oplTlMst.getStudentLink();
			OplTlStudent oplTlStudent =new OplTlStudent();
			OplTlStudentSql oplTlStudentSql =new OplTlStudentSql();
			//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson  "+StudentLinkList);
			
			if(StudentLinkList != null && StudentLinkList.size()>0)
			{
				for(OplTlStudent StudentLnk:StudentLinkList)
				{
					
					/*//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 11 "+StudentLnk.getOpllMtrxKeyid());
					//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 22 "+StudentLnk.getOpllStudent());
					//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 33 "+StudentLnk.getOpllTeacher());
					//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 44 "+StudentLnk.getOpllDate());
					//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 55 "+"1".equals(StudentLnk.getOpllMtrxKeyid()));*/
					
					if("1".equals(StudentLnk.getOpllMtrxKeyid())){
						
						sqlss="select MTRX_KEYID from OPL_TL_MATRIXMST where MTRX_CODE='"+ StudentLnk.getOpllMtrxKeyid()+"'";
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 66 "+sqlss.toString());
						String MatrixId=dbActionTemplate.getSingleValue(sqlss.toString());
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 77 "+MatrixId);
						StudentLnk.setOpllMtrxKeyid(MatrixId);
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 88 "+StudentLnk.getOpllMtrxKeyid());
						fillValues(StudentLnk,oplTlMst,MatrixId);
					    if(oplTlStudent.getOpllKeyid()==null){
					    StudentLnk.setOpllKeyid(dbActionTemplate.getSequenceNumber(OplTlStudentSql.TBL_OPL_TL_STUDENT, 10, "OPLS", "", "")); // set the sequnce number  TBL_GEN_TL_DMTNOTEBOOKDETAIL
						sqls.add(OplTlStudentSql.getInsertSql(oplTlStudentSql.getOpllDbFields(), StudentLnk.getSaveArray()));
					    }
						//CommonMessage.debugMsg(" Inside DaoImpl One Point Lesson 1234567890 :::: "+sqls);
						
					}else if("2".equals(StudentLnk.getOpllMtrxKeyid())){
						
						sqlss="select MTRX_KEYID from OPL_TL_MATRIXMST where MTRX_CODE='"+ StudentLnk.getOpllMtrxKeyid()+"'";
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 16th Jan "+StudentLnk.getOpllMtrxKeyid());
						String MatrixId=dbActionTemplate.getSingleValue(sqlss.toString());
						StudentLnk.setOpllMtrxKeyid(MatrixId);
						fillValues(StudentLnk,oplTlMst,MatrixId);
					    if(oplTlStudent.getOpllKeyid()==null){
					    StudentLnk.setOpllKeyid(dbActionTemplate.getSequenceNumber(OplTlStudentSql.TBL_OPL_TL_STUDENT, 10, "OPLS", "", "")); // set the sequnce number  TBL_GEN_TL_DMTNOTEBOOKDETAIL
						sqls.add(OplTlStudentSql.getInsertSql(oplTlStudentSql.getOpllDbFields(), StudentLnk.getSaveArray()));
					    }
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 66 "+MatrixId);
						
					}else if("3".equals(StudentLnk.getOpllMtrxKeyid())){
						
						sqlss="select MTRX_KEYID from OPL_TL_MATRIXMST where MTRX_CODE='"+ StudentLnk.getOpllMtrxKeyid()+"'";
						String MatrixId=dbActionTemplate.getSingleValue(sqlss.toString());
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 66 "+MatrixId);
						StudentLnk.setOpllMtrxKeyid(MatrixId);
						fillValues(StudentLnk,oplTlMst,MatrixId);
					    if(oplTlStudent.getOpllKeyid()==null){
					    StudentLnk.setOpllKeyid(dbActionTemplate.getSequenceNumber(OplTlStudentSql.TBL_OPL_TL_STUDENT, 10, "OPLS", "", "")); // set the sequnce number  TBL_GEN_TL_DMTNOTEBOOKDETAIL
						sqls.add(OplTlStudentSql.getInsertSql(oplTlStudentSql.getOpllDbFields(), StudentLnk.getSaveArray()));
					    }
						
					}else if("4".equals(StudentLnk.getOpllMtrxKeyid())){
						
						sqlss="select MTRX_KEYID from OPL_TL_MATRIXMST where MTRX_CODE='"+ StudentLnk.getOpllMtrxKeyid()+"'";
						String MatrixId=dbActionTemplate.getSingleValue(sqlss.toString());
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 66 "+MatrixId);
						StudentLnk.setOpllMtrxKeyid(MatrixId);
						fillValues(StudentLnk,oplTlMst,MatrixId);
					    if(oplTlStudent.getOpllKeyid()==null){
					    StudentLnk.setOpllKeyid(dbActionTemplate.getSequenceNumber(OplTlStudentSql.TBL_OPL_TL_STUDENT, 10, "OPLS", "", "")); // set the sequnce number  TBL_GEN_TL_DMTNOTEBOOKDETAIL
						sqls.add(OplTlStudentSql.getInsertSql(oplTlStudentSql.getOpllDbFields(), StudentLnk.getSaveArray()));
					    }
						
					}
					
					
					//pillarLnk.setOpplOplid(oplTlMst.getOplmKeyid());
					//if( pillarLnk.getDbMode().equals("INSERT"))
						//sqls.add(OplTlPillarlinkSql.getInsertSql(oplTlPillarlinkSql.getOpplDbFields(),pillarLnk.getSaveArray()));
					//else if( pillarLnk.getDbMode().equals("UPDATE"))
						//sqls.add(OplTlPillarlinkSql.getUpdateSql(oplTlPillarlinkSql.getOpplDbFields(),pillarLnk.getSaveArray()));
					//else if( pillarLnk.getDbMode().equals("DELETE"))
						//sqls.add(OplTlPillarlinkSql.getDeleteSql(oplTlPillarlinkSql.getOpplDbFields(),pillarLnk.getSaveArray()));
				}
			}
			
			if(bdmTlYycountermeasurelink!=null)
			{
				bdmTlYycountermeasurelink.setYycmCountermsrid(oplTlMst.getOplmKeyid());
				bdmTlYycountermeasurelink.setYycmRefdoctype("OPL");
				bdmTlYycountermeasurelink.setYycmKeyid(dbActionTemplate.getSequenceNumber(BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK ,11, "YCM", "YYMM", "Y"));
				sqls.add(BdmTlYycountermeasurelinkSql.getDeleteYYSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),bdmTlYycountermeasurelink.getSaveArray()));
				sqls.add(BdmTlYycountermeasurelinkSql.getDeleteCounterMeasureLinkSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),oplTlMst.getOplmKeyid(),"OPL"));
				sqls.add(BdmTlYycountermeasurelinkSql.getInsertSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(), bdmTlYycountermeasurelink.getSaveArray()));
			}
			if(genTlDocupdates != null)
			{
				//CommonMessage.debugMsg(" Update this :: 22 ");
				genTlDocupdates.setDcupDetailid(oplTlMst.getOplmKeyid());
				sqls.add(GenTlDocupdatesSql.getUpdateDocSql(genTlDocupdatesSql.getDcupDbFields(), genTlDocupdates.getSaveArray()));
				
			}
				
			//CommonMessage.debugMsg(" Inside :: If create 10");
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			//CommonMessage.debugMsg(" Inside :: If create 11");
		}
		
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return oplTlMst;
	}
	
	private void fillValues(OplTlStudent StudentLnk, OplTlMst oplTlMst,String MatrixId) {
		// TODO Auto-generated method stub
		
		
		//CommonMessage.debugMsg(" Inside Fill values "+MatrixId);
		//CommonMessage.debugMsg(" Inside Fillvalues 11 "+StudentLnk.getOpllMtrxKeyid());
		//CommonMessage.debugMsg(" Inside Fillvalues 22 "+StudentLnk.getOpllStudent());
		//CommonMessage.debugMsg(" Inside Fillvalues 33 "+StudentLnk.getOpllTeacher());
		//CommonMessage.debugMsg(" Inside Fillvalues 44 "+StudentLnk.getOpllDate());
		
		if((StudentLnk.getOpllKeyid()==null))
			StudentLnk.setOpllOplmKeyid(oplTlMst.getOplmKeyid());
		if(StudentLnk.getOpllDate()==null)
			StudentLnk.setOpllDate("11-JAN-2014");
		if(StudentLnk.getOpllTeacher()==null)
			StudentLnk.setOpllTeacher("{}");
		if((StudentLnk.getOpllStudent()==null))
			StudentLnk.setOpllStudent("{}");
		if((StudentLnk.getOpllMtrxKeyid()==null))
			StudentLnk.setOpllMtrxKeyid(MatrixId);
		if((StudentLnk.getOpllTempfield1()==null))
			StudentLnk.setOpllTempfield1("-");
		if((StudentLnk.getOpllTempfield2()==null))
			StudentLnk.setOpllTempfield2("-");
		if((StudentLnk.getOpllTempfield3()==null))
			StudentLnk.setOpllTempfield3("-");
		if((StudentLnk.getOpllTempfield4()==null))
			StudentLnk.setOpllTempfield4("-");
		if((StudentLnk.getOpllCreatedby()==null))
			StudentLnk.setOpllCreatedby("USR0001");
		if((StudentLnk.getOpllActive()==null))
			StudentLnk.setOpllActive("Y");
		if((StudentLnk.getOpllCreatedon()==null))
			StudentLnk.setOpllCreatedon("11-JAN-2014");
		if((StudentLnk.getOpllModifiedon()==null))
			StudentLnk.setOpllModifiedon("11-JAN-2014");
		
	}

	public Integer getApprovalCount(String oplmKeyid) throws Exception {
		
		String sqll = "select count(*) from GEN_TL_WORKFLOW_INFO where WRIN_REF_ID='"+oplmKeyid+"' ";
		String apprvCnt = dbActionTemplate.getSingleValue(sqll);
		
		return Integer.parseInt(apprvCnt);
	}
	
	
	public OplTlMst update(OplTlMst oplTlMst ,BdmTlYycountermeasurelink bdmTlYycountermeasurelink,GenTlDocupdates genTlDocupdates)	throws Exception 
	{ 
		//CommonMessage.debugMsg(" Inside :: Else update 9");
		List<String> sqls = new ArrayList<String>();
		String sqlss = "" ;
		
		
		//if (getApprovalCount(oplTlMst.getOplmKeyid())>0)
			//throw new BusinessApplicationExceptions("approve_ModifyMode"+",");

		try 
		{
			//CommonMessage.debugMsg(" Inside :: Else update 10");
			sqls.add(OplTlMstSql.getUpdateSql(oplTlMstSql.getOplmDbFields(), oplTlMst.getSaveArray()));
			//CommonMessage.debugMsg(" Inside :: Else update 11");
			setPillarLinkSqls(sqls,oplTlMst);
			//CommonMessage.debugMsg(" Inside :: Else update 12");
			List <OplTlStudent> StudentLinkList = oplTlMst.getStudentLink();
			OplTlStudent oplTlStudent =new OplTlStudent();
			OplTlStudentSql oplTlStudentSql =new OplTlStudentSql();
			//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson  "+StudentLinkList);
			
			if(StudentLinkList != null && StudentLinkList.size()>0)
			{
				for(OplTlStudent StudentLnk:StudentLinkList)
				{
                       if("1".equals(StudentLnk.getOpllMtrxKeyid())){
						
						sqlss="select MTRX_KEYID from OPL_TL_MATRIXMST where MTRX_CODE='"+ StudentLnk.getOpllMtrxKeyid()+"'";
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 66 "+sqlss.toString());
						String MatrixId=dbActionTemplate.getSingleValue(sqlss.toString());
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 77 "+MatrixId);
						StudentLnk.setOpllMtrxKeyid(MatrixId);
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 88 "+StudentLnk.getOpllMtrxKeyid());
						fillValues(StudentLnk,oplTlMst,MatrixId);
					    if(oplTlStudent.getOpllKeyid()==null){
					    StudentLnk.setOpllKeyid(dbActionTemplate.getSequenceNumber(OplTlStudentSql.TBL_OPL_TL_STUDENT, 10, "OPLS", "", "")); // set the sequnce number  TBL_GEN_TL_DMTNOTEBOOKDETAIL
						sqls.add(OplTlStudentSql.getInsertSql(oplTlStudentSql.getOpllDbFields(), StudentLnk.getSaveArray()));
					    }
						//CommonMessage.debugMsg(" Inside DaoImpl One Point Lesson 1234567890 :::: "+sqls);
						
					}else if("2".equals(StudentLnk.getOpllMtrxKeyid())){
						
						sqlss="select MTRX_KEYID from OPL_TL_MATRIXMST where MTRX_CODE='"+ StudentLnk.getOpllMtrxKeyid()+"'";
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 16th Jan "+StudentLnk.getOpllMtrxKeyid());
						String MatrixId=dbActionTemplate.getSingleValue(sqlss.toString());
						StudentLnk.setOpllMtrxKeyid(MatrixId);
						fillValues(StudentLnk,oplTlMst,MatrixId);
					    if(oplTlStudent.getOpllKeyid()==null){
					    StudentLnk.setOpllKeyid(dbActionTemplate.getSequenceNumber(OplTlStudentSql.TBL_OPL_TL_STUDENT, 10, "OPLS", "", "")); // set the sequnce number  TBL_GEN_TL_DMTNOTEBOOKDETAIL
						sqls.add(OplTlStudentSql.getInsertSql(oplTlStudentSql.getOpllDbFields(), StudentLnk.getSaveArray()));
					    }
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 66 "+MatrixId);
						
					}else if("3".equals(StudentLnk.getOpllMtrxKeyid())){
						
						sqlss="select MTRX_KEYID from OPL_TL_MATRIXMST where MTRX_CODE='"+ StudentLnk.getOpllMtrxKeyid()+"'";
						String MatrixId=dbActionTemplate.getSingleValue(sqlss.toString());
						//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 66 "+MatrixId);
						StudentLnk.setOpllMtrxKeyid(MatrixId);
						fillValues(StudentLnk,oplTlMst,MatrixId);
					    if(oplTlStudent.getOpllKeyid()==null){
					    StudentLnk.setOpllKeyid(dbActionTemplate.getSequenceNumber(OplTlStudentSql.TBL_OPL_TL_STUDENT, 10, "OPLS", "", "")); // set the sequnce number  TBL_GEN_TL_DMTNOTEBOOKDETAIL
						sqls.add(OplTlStudentSql.getInsertSql(oplTlStudentSql.getOpllDbFields(), StudentLnk.getSaveArray()));
				
			    }
				
			}else if("4".equals(StudentLnk.getOpllMtrxKeyid())){
				
				sqlss="select MTRX_KEYID from OPL_TL_MATRIXMST where MTRX_CODE='"+ StudentLnk.getOpllMtrxKeyid()+"'";
				String MatrixId=dbActionTemplate.getSingleValue(sqlss.toString());
				//CommonMessage.debugMsg(" Inside Dao Impl One Point Lesson 66 "+MatrixId);
				StudentLnk.setOpllMtrxKeyid(MatrixId);
				fillValues(StudentLnk,oplTlMst,MatrixId);
			    if(oplTlStudent.getOpllKeyid()==null){
			    StudentLnk.setOpllKeyid(dbActionTemplate.getSequenceNumber(OplTlStudentSql.TBL_OPL_TL_STUDENT, 10, "OPLS", "", "")); // set the sequnce number  TBL_GEN_TL_DMTNOTEBOOKDETAIL
				sqls.add(OplTlStudentSql.getInsertSql(oplTlStudentSql.getOpllDbFields(), StudentLnk.getSaveArray()));
				
			    }
			}}
		
				}
			
			//CommonMessage.debugMsg(" Inside :: Else update 12");
			//sqls.add(OplTlLessonSql.getInsertSql(oplTlLessonSql.getOpllDbFields(), oplTlLesson.getSaveArray()));
			if(bdmTlYycountermeasurelink!=null)
			{	//CommonMessage.debugMsg(" Inside :: Else update 13");
				bdmTlYycountermeasurelink.setYycmRefdoctype("OPL");
				bdmTlYycountermeasurelink.setYycmKeyid(dbActionTemplate.getSequenceNumber(BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK ,11, "YCM", "YYMM", "Y"));
				sqls.add(BdmTlYycountermeasurelinkSql.getDeleteYYSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),bdmTlYycountermeasurelink.getSaveArray()));
				sqls.add(BdmTlYycountermeasurelinkSql.getDeleteCounterMeasureLinkSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),oplTlMst.getOplmKeyid(),"OPL"));
				sqls.add(BdmTlYycountermeasurelinkSql.getInsertSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(), bdmTlYycountermeasurelink.getSaveArray()));
			}
			
			if(genTlDocupdates != null)
			{
				
				//CommonMessage.debugMsg(" Inside :: Else update 14");
				sqls.add(GenTlDocupdatesSql.getUpdateDocSql(genTlDocupdatesSql.getDcupDbFields(), genTlDocupdates.getSaveArray()));
				
			}
			
			dbActionTemplate.executeStatements(sqls);
			//CommonMessage.debugMsg(" Inside :: Else update 15");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return oplTlMst;
	}
	
	public OplTlMst delete(OplTlMst oplTlMst)	throws Exception 
	{

		List<String> sqls = new ArrayList<String>();
		OplTlStudent oplTlStudent =new OplTlStudent();
		OplTlStudentSql oplTlStudentSql =new OplTlStudentSql();
		String oplloplid=oplTlMst.getOplmKeyid();
		
		if (getApprovalCount(oplTlMst.getOplmKeyid())>0)
			throw new BusinessApplicationExceptions("approve_DelMode"+",");
		
		try 
		{
			
			
			//CommonMessage.debugMsg(" Inside dao Impl :: oplloplid "+oplloplid);
			
			//select WRIN_REF_ID,WRIN_STATUS from GEN_TL_WORKFLOW_INFO where WRIN_REF_ID='OP1400000231';
		
			sqls.add(OplTlLessonSql.getDeleteSql(oplTlLessonSql.getOpllDbFields(),oplloplid));
			
			sqls.add(BdmTlYycountermeasurelinkSql.getDeleteCounterMeasureLinkSql(bdmTlYycountermeasurelinkSql.getYycmDbFields(),oplloplid,"OPL"));
			
			sqls.add(OplTlPillarlinkSql.getOplDeleteSql(oplTlPillarlinkSql.getOpplDbFields(), oplloplid));
			sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql(genTlAllmoduleimgfileSql.getImflDbFields(),oplTlMst.getSaveArray()));
			sqls.add(OplTlStudentSql.getDeleteSql(oplTlStudentSql.getOpllDbFields(), oplloplid));
			sqls.add(OplTlMstSql.getDeleteSql(oplTlMstSql.getOplmDbFields(), oplTlMst.getSaveArray()));
			
			//CommonMessage.debugMsg( " This is deleting opl Master Record "+oplTlMst.getOplmKeyid());
			
			dbActionTemplate.executeStatements(sqls);
		}
		catch( Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return oplTlMst;
	}
	
	@Override
	public OplTlMst select(String oplKeyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = OplTlMstSql.selectSql();
		CommonMessage.debugMsg("oplKeyid in dao impl="+oplKeyid);
		//CommonMessage.debugMsg("String sql="+sql);
		OplTlMst oplTlMst = new OplTlMst();  
		Object args [] = new Object [] { oplKeyid };
		oplTlMst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		CommonMessage.debugMsg("select from sql= "+ oplTlMst.getOplmMpworthy());
		return oplTlMst;
	}
	 //---------- vignesh ------------------------------------------------------------//
	
	


	 //---------- vignesh ------------------------------------------------------------//

	@Override
	public List<String[]> getStudents(String opllOplid, String flid, String oplKeyid) throws Exception {
	    List<String[]> gridData = new ArrayList<>();
	    try {
	        // ---- 1) Resolve anchor FLID + original id (same behavior as your current code) ----
	        String anchorFlid = flid;
	        String anchorOriginalId = null;

	        String metaSql =
	            "SELECT DISPLAYCODE, COALESCE(CELL_KEYID,'') AS CELL_KEYID, COALESCE(FNLN_ORIGINALID,'') AS ORG_ID " +
	            "FROM GEN_VW_FNLN WHERE FNLN_KEYID = '" + flid + "'";
	        List<String[]> meta = dbActionTemplate.getDataList(metaSql);

	        if (meta != null && !meta.isEmpty()) {
	            String displayCode = meta.get(0)[0];
	            String cellOrigId  = meta.get(0)[1];
	            String ownOrigId   = meta.get(0)[2];

	            if ("MCHM".equalsIgnoreCase(displayCode) && cellOrigId != null && !cellOrigId.isEmpty()) {
	                anchorOriginalId = cellOrigId;
	                String cellFlidSql = "SELECT FNLN_KEYID FROM GEN_VW_FNLN WHERE FNLN_ORIGINALID='" + anchorOriginalId + "'";
	                String resolvedCellFlid = dbActionTemplate.getSingleValue(cellFlidSql);
	                if (resolvedCellFlid != null && !resolvedCellFlid.isEmpty()) {
	                    anchorFlid = resolvedCellFlid;
	                }
	            } else {
	                if (ownOrigId != null && !ownOrigId.isEmpty()) {
	                    anchorOriginalId = ownOrigId;
	                }
	            }
	        }

	        // ---- 2) Optional OPL filter pieces (kept exactly, but moved into JOIN later) ----
	        boolean hasOpl = (oplKeyid != null && !oplKeyid.trim().isEmpty());
	        String joinOplFilterA = hasOpl ? (" AND a.oplmkeyid = '" + oplKeyid + "' ") : "";
	        String joinOplFilterB = hasOpl ? (" AND b.oplmkeyid = '" + oplKeyid + "' ") : "";
	        String decodeExpr     = hasOpl ? ("t.oplmkeyid = '" + oplKeyid + "'") : "false";

	        // ---- 3) Build SQL (ONLY CHANGE: move oplKeyid filtering into LEFT JOIN) ----
	        StringBuilder sql = new StringBuilder();
	        sql.append("\nWITH frt AS (\n")
	           .append("  SELECT DISTINCT rt.FRT_FNLN_KEYID, rt.FRT_EMPM_KEYID, rt.FRT_ROLE_KEYID\n")
	           .append("  FROM GEN_TL_FNLNROLETEAM rt\n")
	           .append("  WHERE rt.FRT_FNLN_KEYID IN (\n")
	           .append("    SELECT h.flid FROM gen_mv_flidhierarchy h\n")
	           .append("    WHERE ");

	        if (anchorOriginalId != null && !anchorOriginalId.isEmpty()) {
	            sql.append("h.FNLN_ORIGINALID = '").append(anchorOriginalId).append("' OR ");
	        }
	        sql.append("POSITION('").append(anchorFlid)
	           .append("' IN (COALESCE(h.parentflids,'') || '-' || COALESCE(h.flid,''))) > 0\n")
	           .append("  )\n")
	           .append("),\n")
	           .append("agg AS (\n")
	           .append("  SELECT s.oplmkeyid, s.opll_teacher, s.opll_student, s.oplm_keyid,\n")
	           .append("         SUM(CASE WHEN s.matrix = 'MTX0000001' THEN 1 ELSE 0 END) AS mtx1,\n")
	           .append("         MAX(CASE WHEN s.mtrx_code = '1' THEN s.keyid END) AS keyid1,\n")
	           .append("         MAX(CASE WHEN s.mtrx_code = '2' THEN s.keyid END) AS keyid2,\n")
	           .append("         MAX(CASE WHEN s.mtrx_code = '3' THEN s.keyid END) AS keyid3,\n")
	           .append("         MAX(CASE WHEN s.mtrx_code = '4' THEN s.keyid END) AS keyid4,\n")
	           .append("         SUM(CASE WHEN s.matrix = 'MTX0000002' THEN 1 ELSE 0 END) AS mtx2,\n")
	           .append("         SUM(CASE WHEN s.matrix = 'MTX0000003' THEN 1 ELSE 0 END) AS mtx3,\n")
	           .append("         SUM(CASE WHEN s.matrix = 'MTX0000004' THEN 1 ELSE 0 END) AS mtx4,\n")
	           .append("         MAX(s.opll_date) AS opll_date\n")
	           .append("  FROM (\n")
	           .append("    SELECT s.opll_oplm_keyid AS oplmkeyid,\n")
	           .append("           s.opll_keyid      AS keyid,\n")
	           .append("           m.oplm_keyid,\n")
	           .append("           s.opll_date,\n")
	           .append("           s.opll_teacher,\n")
	           .append("           s.opll_student,\n")
	           .append("           s.opll_mtrx_keyid AS matrix,\n")
	           .append("           mm.mtrx_code::text AS mtrx_code\n")
	           .append("    FROM opl_tl_mst m\n")
	           .append("    JOIN opl_tl_student s    ON m.oplm_keyid = s.opll_oplm_keyid\n")
	           .append("    JOIN opl_tl_matrixmst mm ON mm.mtrx_keyid = s.opll_mtrx_keyid\n")
	           .append("  ) s\n")
	           .append("  GROUP BY s.opll_teacher, s.opll_student, s.oplm_keyid, s.oplmkeyid\n")
	           .append("),\n")
	           .append("base_a AS (\n")
	           .append("  SELECT a.oplmkeyid,\n")
	           .append("         to_char(a.opll_date,'DD-Mon-YYYY') AS opll_date,\n")
	           .append("         COALESCE(a.opll_teacher, u.usrm_ccno) AS opllteacher,\n")
	           .append("         e.empm_name || '-' || e.empm_code AS studentname,\n")
	           .append("         e.empm_keyid AS studentid,\n")
	           .append("         a.mtx1, a.keyid1, ''::text AS chk1,\n")
	           .append("         a.mtx2, a.keyid2, ''::text AS chk2,\n")
	           .append("         a.mtx3, a.keyid3, ''::text AS chk3,\n")
	           .append("         ''::text AS dtevaldte3, ''::text AS chkval3,\n")
	           .append("         a.mtx4, a.keyid4, ''::text AS chk4,\n")
	           .append("         ''::text AS dtevaldte4, ''::text AS chkval4,\n")
	           .append("         e.empm_name,\n")
	           .append("         e.empm_keyid AS empm_keyid\n")
	           .append("  FROM gen_tl_employeemst e\n")
	           .append("  JOIN frt              ON e.empm_keyid = frt.FRT_EMPM_KEYID\n")
	           .append("  LEFT JOIN adm_tl_usermst u ON e.empm_keyid = u.usrm_ccno\n")
	           .append("  LEFT JOIN agg a       ON a.opll_student = e.empm_keyid").append(joinOplFilterA).append("\n")
	           .append("  WHERE e.EMPM_ACTIVE='Y'\n")
	           .append("),\n")
	           .append("base_b AS (\n")
	           .append("  SELECT b.oplmkeyid,\n")
	           .append("         to_char(b.opll_date,'DD-Mon-YYYY') AS opll_date,\n")
	           .append("         COALESCE(b.opll_teacher, u2.usrm_ccno) AS opllteacher,\n")
	           .append("         e2.empm_name || '-' || e2.empm_code AS studentname,\n")
	           .append("         e2.empm_keyid AS studentid,\n")
	           .append("         b.mtx1, b.keyid1, ''::text AS chk1,\n")
	           .append("         b.mtx2, b.keyid2, ''::text AS chk2,\n")
	           .append("         b.mtx3, b.keyid3, ''::text AS chk3,\n")
	           .append("         ''::text AS dtevaldte3, ''::text AS chkval3,\n")
	           .append("         b.mtx4, b.keyid4, ''::text AS chk4,\n")
	           .append("         ''::text AS dtevaldte4, ''::text AS chkval4,\n")
	           .append("         e2.empm_name,\n")
	           .append("         e2.empm_keyid AS empm_keyid\n")
	           .append("  FROM gen_tl_employeemst e2\n")
	           .append("  JOIN frt fr2           ON e2.empm_keyid = fr2.FRT_EMPM_KEYID\n")
	           .append("  LEFT JOIN adm_tl_usermst u2 ON e2.empm_keyid = u2.usrm_ccno\n")
	           .append("  LEFT JOIN agg b        ON b.opll_student = e2.empm_keyid").append(joinOplFilterB).append("\n")
	           .append("  WHERE e2.EMPM_ACTIVE='Y'\n")
	           .append(")\n")
	           .append("SELECT\n")
	           .append("  '' AS col1,\n")
	           .append("  '' AS col2,\n")
	           .append("  '' AS col3,\n")
	           .append("  t.opll_date,\n")
	           .append("  CASE WHEN ").append(decodeExpr).append(" THEN t.empm_name ELSE '' END AS decode_like,\n")
	           .append("  t.studentname,\n")
	           .append("  t.studentid,\n")
	           .append("  t.mtx1, t.keyid1, t.chk1,\n")
	           .append("  t.mtx2, t.keyid2, t.chk2,\n")
	           .append("  t.mtx3, t.keyid3, t.chk3,\n")
	           .append("  t.dtevaldte3, t.chkval3,\n")
	           .append("  t.mtx4, t.keyid4, t.chk4,\n")
	           .append("  t.dtevaldte4, t.chkval4,\n")
	           .append("  t.opllteacher,\n")
	           .append("  t.opll_date\n")
	           .append("FROM (\n")
	           .append("  SELECT * FROM base_a\n")
	           .append("  UNION ALL\n")
	           .append("  SELECT * FROM base_b\n")
	           .append(") t\n")
	           .append("ORDER BY t.oplmkeyid NULLS LAST;");

	        String sql1 = sql.toString();
	        CommonMessage.debugMsg("\n---[PostgreSQL getStudents SQL]---\n" + sql1);

	        // ---- 4) Execute & return ----
	        gridData = dbActionTemplate.getDataList(sql1);

	        CommonMessage.debugMsg("Rows: " + (gridData == null ? 0 : gridData.size()));
	        if (gridData != null) {
	            for (int i = 0; i < Math.min(5, gridData.size()); i++) {
	                String[] row = gridData.get(i);
	                StringBuilder sb = new StringBuilder();
	                for (int c = 0; c < row.length; c++) {
	                    sb.append(c == 0 ? "[" : ", ").append(row[c]);
	                }
	                sb.append("]");
	                CommonMessage.debugMsg(sb.toString());
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	    return gridData;
	}


//	
//	@Override
//	public List<String[]> getdatevalidate(String keyId, String rowId) throws Exception {
//		// TODO Auto-generated method stub
//		//CommonMessage.debugMsg(" x::::::::::: "+keyId);
//		String sql="select round(sysdate-OPLL_DATE),"+rowId+" from dual,OPL_TL_STUDENT where  OPLL_KEYID='"+keyId+"'";
//		CommonMessage.debugMsg("+++--999sql..."+sql);
//        List<String []> gridData = dbActionTemplate.getDataList(sql);
//		return gridData ;
//	}
//	--------Created by  Vignesh 11Sep2025 --------//
	@Override
	public List<String[]> getdatevalidate(String keyId, String rowId) throws Exception {
	    // NOTE: rowId must be numeric (e.g., "2"). This mirrors your original Oracle usage.
	    String sql =
	        "SELECT " +
	        "  ROUND(EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - OPLL_DATE::timestamp)) / 86400.0)::int AS days, " +
	        "  " + rowId + " AS rowid " +
	        "FROM OPL_TL_STUDENT " +
	        "WHERE OPLL_KEYID = '" + keyId + "'";

	 //   CommonMessage.debugMsg("+++--999sql..." + sql);
	    List<String[]> gridData = dbActionTemplate.getDataList(sql);
	    return gridData;
	}
	
//	--------Created by  Vignesh 11Sep2025 -------//

	@Override
	public List<String[]> getdatevalidating(String keyId, String rowId) throws Exception {
	    // Postgres-compatible: no DUAL; compute Oracle-like rounded day difference
	    final String sql =
	        "SELECT " +
	        "  ROUND(EXTRACT(EPOCH FROM (CURRENT_TIMESTAMP - OPLL_DATE::timestamp)) / 86400.0)::int AS days, " +
	        "  CAST(? AS int) AS rowid " +
	        "FROM OPL_TL_STUDENT " +
	        "WHERE OPLL_KEYID = ?";

	    // Bind rowId first (used in SELECT), then keyId (used in WHERE)
	    Object[] args = new Object[] { rowId, keyId };

	    return dbActionTemplate.getDataList(sql, args);
	}

	
//	@Override
//	public List<String[]> getdatevalidating(String keyId, String rowId)
//			throws Exception {
//		// TODO Auto-generated method stub
//		//CommonMessage.debugMsg(" x::::::::::: "+keyId);
//		String sql="select round(sysdate-OPLL_DATE),"+rowId+" from dual,OPL_TL_STUDENT where  OPLL_KEYID='"+keyId+"'";
//		//CommonMessage.debugMsg("sql..."+sql);
//        List<String []> gridData = dbActionTemplate.getDataList(sql);
//		return gridData ;
//	}
	
	@Override
	public List<String[]> getdatevalidation(String keyId)
			throws Exception {
		// TODO Auto-generated method stub
		//CommonMessage.debugMsg(" x::::::::::: "+keyId);
		String sql="SELECT a.cnfm_settingvalue, b.cnfm_settingvalue FROM adm_tl_configurationmst a, adm_tl_configurationmst b WHERE a.cnfm_keyid = 'RGS101' AND b.cnfm_keyid = 'RGS102'";
		
		//CommonMessage.debugMsg("sql..."+sql);
        List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData ;
	}
	
	private void setPillarLinkSqls(List<String>sqls, OplTlMst oplTlMst )
	{
		List <OplTlPillarlink> pillarLinkList = oplTlMst.getPillarLink();
		if(pillarLinkList != null && pillarLinkList.size()>0)
		{
			for(OplTlPillarlink pillarLnk:pillarLinkList)
			{
				pillarLnk.setOpplOplid(oplTlMst.getOplmKeyid());
				if( pillarLnk.getDbMode().equals("INSERT"))
					sqls.add(OplTlPillarlinkSql.getInsertSql(oplTlPillarlinkSql.getOpplDbFields(),pillarLnk.getSaveArray()));
				else if( pillarLnk.getDbMode().equals("UPDATE"))
					sqls.add(OplTlPillarlinkSql.getUpdateSql(oplTlPillarlinkSql.getOpplDbFields(),pillarLnk.getSaveArray()));
				else if( pillarLnk.getDbMode().equals("DELETE"))
					sqls.add(OplTlPillarlinkSql.getDeleteSql(oplTlPillarlinkSql.getOpplDbFields(),pillarLnk.getSaveArray()));
			}
		}

	}
	

	@Override
	public List<String[]> getImprvCategory(List<String> pillarId) throws Exception {
		// TODO Auto-generated method stub
		List<String[]> ImprvCategoryList=null;
		try
		{
			String sql = OplTlMstSql.getOplImprvCategorySql();
			//CommonMessage.debugMsg("String sql="+sql);
			List<String> params=pillarId;
			ImprvCategoryList = dbActionTemplate.getDataList(sql,params);
		}
	
		catch(Exception e)
		{
			//CommonMessage.debugMsg("Exception in getStudent dao impl"+e.getMessage());
		}
		return ImprvCategoryList;
	}

	public List<String []> getOplReportDao(CommonFilter commonFilter, String emppillar) throws Exception
	{
		try
		{
			
			String modifymode =commonFilter.getMainGroup();
			CommonMessage.debugMsg(" Inside 1234 :: modifymode "+modifymode);

			String oplmatrix=commonFilter.getType();
			String EmpillarOPLMP=commonFilter.getMPWorthy();
			String EmpillarOPLUtilize=commonFilter.getUtiliseFuture();
			String prepardby=commonFilter.getRescheduleTo();
			
			
			//CommonMessage.debugMsg(" Inside 1234 :: emppillar "+emppillar);
			
			List<String> paramValues = new ArrayList<String>();
			
			String condParms =FilterCondSql.getOPLRelatedCondSql(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			if(UIUtils.isValidKeyId(commonFilter.getEmpch()))
				condParms += "EMPLOYEEKEYID="+commonFilter.getEmpch()+";";
			
			if(UIUtils.isValidKeyId(modifymode)){
			    condParms +="MODIFYMODE="+modifymode+";";
			}
			
			if(UIUtils.isValidKeyId(oplmatrix))
			   condParms +="OPLMATRIX="+oplmatrix+";";
			
			
			if(UIUtils.isValidKeyId(EmpillarOPLMP)){
		        condParms +="MPWORTHY="+EmpillarOPLMP+";";
			}
			
			if(UIUtils.isValidKeyId(emppillar)){
		        condParms +="EMPPILLAR="+emppillar+";";
			}
			
            if(UIUtils.isValidKeyId(EmpillarOPLUtilize)){
		        condParms +="UTILISEFUTURE="+EmpillarOPLUtilize+";";
			}
            
            if(UIUtils.isValidKeyId(prepardby)){
		        condParms +="PREPARADBY="+prepardby+";";
			}
            CommonMessage.debugMsg("The condParms"+condParms);
            paramValues.add(condParms);
			paramValues.add(commonParams);
			
			
		//	List<String[]> oplModify= dbActionTemplate.processFunctionCalls("OPL_FN_OPLREPORT", paramValues);
			List<String[]> oplModify =  fnCallApi.callFunction("OPL_FN_OPLREPORT_SB", paramValues,3,true);
			
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return oplModify;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	

	
	@Override
	public List<String[]> getAllOplEmpPillarMainGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		try
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			List<String[]> oplModify= dbActionTemplate.processFunctionCalls("OPL_FN_OPLREPORT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return oplModify;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}

	@Override
	public List<String[]> getFourQuadrantmatrixDataWise(
			CommonFilter commonFilter, String flid) throws Exception {
		// TODO Auto-generated method stub
		try
		{
		List<String> paramValues = new ArrayList<String>();		
		String condParms =FilterCondSql.getOPLRelatedCondSql(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
		
		if(!UIUtils.isValidKeyId(flid))
			flid="";
		
		//CommonMessage.debugMsg(" In DaoImpl Servlet :: "+commonFilter.getType());
		
		if(UIUtils.isValidKeyId(commonFilter.getType()))
			condParms=condParms+"TYPEVAL="+commonFilter.getType()+";";
		
		condParms=condParms+"FLID="+flid+";";
		paramValues.add(condParms);
		paramValues.add(commonParams); 
		
	//	List<String[]> dataList= dbActionTemplate.processFunctionCallsWithColHeaders("OPL_FN_OPLFOURQDANTMTRIXDTEWSE", paramValues);
		
		List<String[]> dataList= fnCallApi.callFunction("OPL_FN_OPLFOURQDANTMTRIXDTEWSE_SB", paramValues,3,true);
		
		if( commonFilter.getViewClick() == 'Y'){
			String totalCnt = paramValues.get(0); 
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

	
	
	
	
	@Override
	public List<String[]> getFourQuadrantmatrix(CommonFilter commonFilter, String flid, String cellid, String Empid)
	        throws Exception {
	    try {
	        List<String> paramValues = new ArrayList<>();

	        String condParms   = FilterCondSql.getOPLRelatedCondSql(commonFilter);
	        String commonParms = FilterCondSql.getGridCommonParams(commonFilter);

	        // Normalize IDs we might append
	        if (!UIUtils.isValidKeyId(flid))   flid = "";
	        if (!UIUtils.isValidKeyId(cellid)) cellid = "";
	        if (!UIUtils.isValidKeyId(Empid))  Empid = "";

	        // -------- sanitize vcondparam (param #3) --------
	        if (condParms == null) condParms = "";
	        // strip NULs (fixes: invalid byte sequence for encoding "UTF8": 0x00)
	        condParms = condParms.replace("\u0000", "");
	        // ensure single EMPID=...; (remove any existing EMPID=)
	        condParms = condParms.replaceAll("(?i)EMPID=[^;]*;?", "");
	        // ensure trailing ';'
	        if (!condParms.endsWith(";")) condParms += ";";
	        // re-append EMPID (even if empty it keeps the parser happy)
	        condParms += "EMPID=" + Empid + ";";

	        // If you also want to force FLID/CELLID here, uncomment:
	        // condParms = condParms.replaceAll("(?i)FLID=[^;]*;?", "");
	        // if (!flid.isEmpty())   condParms += "FLID=" + flid + ";";
	        // condParms = condParms.replaceAll("(?i)CELLID=[^;]*;?", "");
	        // if (!cellid.isEmpty()) condParms += "CELLID=" + cellid + ";";

	        // -------- sanitize vcommonparam (param #4) --------
	        if (commonParms == null) commonParms = "";
	        commonParms = commonParms.replace("\u0000", "");   // remove NULs

	        // Ensure required keys exist and are non-empty where needed
	        if (!commonParms.contains("GRIDFILTER="))
	            commonParms = "GRIDFILTER=;" + commonParms;

	        if (!commonParms.matches("(?s).*\\bFILTERCOND=.*"))
	            commonParms += (commonParms.endsWith(";") ? "" : ";") + "FILTERCOND=;";

	        // If ISTOTALCNT is missing or blank (e.g., "ISTOTALCNT="), default to N
	        if (!commonParms.matches("(?s).*\\bISTOTALCNT=([^;]+).*")) {
	            commonParms += "ISTOTALCNT=N;";
	        } else {
	            commonParms = commonParms.replaceAll("(?s)\\bISTOTALCNT=;?", "ISTOTALCNT=N;");
	        }

	        // Provide a bounded RN window if absent
	        if (!commonParms.matches("(?s).*\\bFROMTOROW=([^;]+).*"))
	            commonParms += "FROMTOROW=1 AND 1000;";

	        if (!commonParms.endsWith(";")) commonParms += ";";

	        // Bind order required by DbActionTemplate: #3=vcondparam, #4=vcommonparam
	        paramValues.add(condParms);
	        paramValues.add(commonParms);

	        List<String[]> dataList =
	        //    dbActionTemplate.processFunctionCallsWithColHeaders("OPL_FN_OPLFOURQUADRANTMATRIX", paramValues);
	        // vignesh matrix 
	       fnCallApi.callFunction("OPL_FN_OPLFOURQUADRANTMATRIX_SB", paramValues,3,true);

	        // DbActionTemplate inserts the function's return value at index 0
	        if (commonFilter.getViewClick() == 'Y') {
	            String totalCnt = paramValues.get(0);
	            if (totalCnt != null && totalCnt.matches("^\\d+$")) {
	                commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
	            }
	        }
	        return dataList;

	    } catch (Exception e) {
	        // Important: rethrow so the caller can show a proper error and your connection gets rolled back by DbActionTemplate.
	        throw new Exception(e.getMessage());
	    }
	}

	
	
	
	
	
//	@Override
//	public List<String[]> getFourQuadrantmatrix(CommonFilter commonFilter,String flid, String cellid,String Empid)
//			throws Exception {
//		// TODO Auto-generated method stub
//		try
//		{
//			//String Empid = commonFilter.getEmpch();
//			//CommonMessage.debugMsg(" Inside DaoImpl :: After For Employee id :: "+Empid);
//			List<String> paramValues = new ArrayList<String>();		
//			String condParms =FilterCondSql.getOPLRelatedCondSql(commonFilter);
//			String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
//			if(!UIUtils.isValidKeyId(flid))
//				flid="";
//			if(!UIUtils.isValidKeyId(cellid))
//				cellid="";
//			if(!UIUtils.isValidKeyId(Empid))
//				Empid="";
//			//condParms=condParms+"FLID="+flid+";CELLID="+cellid+";EMPID="+Empid+";";
//			condParms=condParms+"EMPID="+Empid+";";
//			paramValues.add(condParms);
//			paramValues.add(commonParams); 
//			
//			List<String[]> dataList= dbActionTemplate.processFunctionCallsWithColHeaders("OPL_FN_OPLFOURQUADRANTMATRIX", paramValues);
//			
//			if( commonFilter.getViewClick() == 'Y'){
//				String totalCnt = paramValues.get(0); 
//				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
//				if(  isInteger ){
//					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
//				}
//			}
//			return dataList;
//		}
//		catch (Exception e)
//		{
//			throw new Exception(e.getMessage()); 
//		}
//		
//		/*
//		 * List<String> paramValues = new ArrayList<String>();		
//		String condParms =FilterCondSql.getAbnRelatedConditionStr(commonFilter);
//		String commonParams = FilterCondSql.getGridCommonParams(commonFilter);  
//		CommonMessage.debugMsg("test to............");
//		
//		paramValues.add(condParms);
//		paramValues.add(commonParams); 
//		
//		List<String[]> dataList =  dbActionTemplate.processFunctionCallsWithColHeaders("TEST_PC_TEST.ENT_FN_TASK_MAPPINGKSA", paramValues);
//		if( commonFilter.getViewClick() == 'Y')
//		{
//			String totalCnt = paramValues.get(0); 
//			CommonMessage.debugMsg("totalCnt..."+totalCnt);
//			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
//			if(  isInteger )
//			{
//				commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
//			}
//		}
//		return dataList; 
//		 */
//	}

	@Override
	public List<String[]> getAllFourQudrantReport(CommonFilter commonFilter)throws Exception {
		
		try
		{
			List<String> paramValues = getFilterParamValues(commonFilter);
			List<String[]> oplModify= dbActionTemplate.processFunctionCalls("OPL_FN_OPLREPORT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return oplModify;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	

	public List<String[]> getPillarNameCodes(String oplId) throws Exception
	{
		try
		{
			List<String> paramValues = new ArrayList<String>();
			
			paramValues.add(oplId);
			paramValues.add("Y");
		//	return dbActionTemplate.processFunctionCalls("OPL_FN_GETOPLTGTPILLARDTL", paramValues);
		//	vignesh 
			return fnCallApi.callFunction("OPL_FN_GETOPLTGTPILLARDTL", paramValues, 0, false);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	
	
	@Override
	public List<String[]> selectLesson(String oplloplId, String studId) {
		// TODO Auto-generated method stub
		List<String[]> studentList=null;
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(oplloplId);
			paramValues.add(studId);
			CommonMessage.debugMsg("opllOplid --- checkingdoaimpl" +  oplloplId);
			CommonMessage.debugMsg("PASSINGSTUDENTID  --- checkingdoaimpl" +  studId);
			String sql = OplTlMstSql.getOplStudentInfoSql();
			CommonMessage.debugMsg("String sql="+ sql);
			
			studentList = dbActionTemplate.getDataList(sql,paramValues);
			
		}
	
		catch(Exception e)
		{
			CommonMessage.debugMsg("Exception in getStudent dao impl"+e.getMessage());
		}
		return studentList;
	}
	/** FOR INSERTING IN OPL_TL_LESSON **/
	@Override
	public OplTlLesson insertIntoLesson(OplTlLesson oplTlLesson) throws Exception 
	{
		OplTlLessonSql oplTlLessonSql =new OplTlLessonSql();
 		List<String> sqls = new ArrayList<String>(); 
		try
		{
			sqls.add(OplTlLessonSql.getInsertIntoLessonSql(oplTlLessonSql.getOpllDbFields(), oplTlLesson.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return oplTlLesson;
	}
	/**---- FOR IMAGE---- **/
	
	// ----------------------------------Vignesh 15Sep2025----------------------------------------------

	
//	@Override
//	public List<GenTlAllmoduleimgfile> saveOplImg15(OplTlMst oplTlMst, String keyid, String imagetypepre, String imagetypeaft) throws Exception {
//	    try {
//	        List<String> sqls = new ArrayList<>();
//	        List<Object[]> valueList  = new ArrayList<>();
//	        List<int[]> dataTypes  = new ArrayList<>();
//	        List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
//
//	        // --- Pre-delete (your same logic), but execute immediately and then clear the list ---
//	        for (GenTlAllmoduleimgfile genTlAllmoduleimgfile : newGenTlAllmoduleimgfile) {
//
//	            if (UIUtils.isValidKeyId(imagetypeaft) && UIUtils.isValidKeyId(imagetypepre)) {
//	                sqls.add("DELETE FROM " + GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE + " WHERE IMFL_REFKEYID='" + keyid + "'");
//	            } else if (UIUtils.isValidKeyId(imagetypepre) && !UIUtils.isValidKeyId(imagetypeaft)) {
//	                sqls.add("DELETE FROM " + GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE + " WHERE IMFL_REFKEYID='" + keyid + "' AND IMFL_IMAGETYPE='PRE'");
//	            } else if (UIUtils.isValidKeyId(imagetypeaft) && !UIUtils.isValidKeyId(imagetypepre)) {
//	                sqls.add("DELETE FROM " + GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE + " WHERE IMFL_REFKEYID='" + keyid + "' AND IMFL_IMAGETYPE='AFT'");
//	            }
//
//	            if (!sqls.isEmpty()) {
//	                // execute raw deletes right away
//	                dbActionTemplate.executeStatements(sqls);
//	                sqls.clear();
//	            }
//	        }
//
//	        // --- Build parameterized DELETE + INSERT for each incoming image ---
//	        for (GenTlAllmoduleimgfile genTlAllmoduleimgfile : newGenTlAllmoduleimgfile) {
//
//	            // Skip entries with no file selected (your existing guard)
//	            if (!UIUtils.isValidKeyId(genTlAllmoduleimgfile.getImflFilename())) {
//	                continue;
//	            }
//
//	            // 1) Parameterized DELETE
//	            sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());
//	            Object[] delValue = {
//	                genTlAllmoduleimgfile.getImflRefkeyid(),
//	                genTlAllmoduleimgfile.getImflRefdoctype(),
//	                genTlAllmoduleimgfile.getImflImagetype()
//	            };
//	            int[] delTypes = { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };
//	            valueList.add(delValue);
//	            dataTypes.add(delTypes);
//
//	            // 2) Robust TIMESTAMP (never null)
//	            java.sql.Timestamp timeStamp = null;
//	            String rawModifiedOn = genTlAllmoduleimgfile.getImflModifiedon();
//	            if (UIUtils.isValidKeyId(rawModifiedOn)) {
//	                // Try to parse with your existing utility; it may return null if parsing fails
//	                timeStamp = CommonFunctions.pg_convertoSqlTimeStamp(rawModifiedOn);
//	            }
//	            if (timeStamp == null) {
//	                // Fallback to current time (TIMESTAMP WITHOUT TIME ZONE is fine with java.sql.Timestamp)
//	                timeStamp = new java.sql.Timestamp(System.currentTimeMillis());
//	                // Keep model consistent for any later reads that expect a string
//	                String tsStr = CommonFunctions.convertSqlTimeStampToString(timeStamp); // formats "dd-MMM-yyyy HH:mm:ss"
//	                genTlAllmoduleimgfile.setImflModifiedon(tsStr);
//	            }
//
//	            // 3) Parameterized INSERT
//	            sqls.add(GenTlAllmoduleimgfileSql.getInsertSql());
//	            Object[] insValues = {
//	                genTlAllmoduleimgfile.getImflRefkeyid(),
//	                genTlAllmoduleimgfile.getImflRefdoctype(),
//	                genTlAllmoduleimgfile.getImflImagetype(),
//	                genTlAllmoduleimgfile.getImflBlobimage(),
//	                genTlAllmoduleimgfile.getImflBloblength(),
//	                genTlAllmoduleimgfile.getImflFilename(),
//	                genTlAllmoduleimgfile.getImflTempfield1(),
//	                genTlAllmoduleimgfile.getImflTempfield2(),
//	                timeStamp
//	            };
//	            int[] insDataType = {
//	                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
//	                Types.BLOB,    Types.INTEGER, Types.VARCHAR,
//	                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
//	            };
//	            valueList.add(insValues);
//	            dataTypes.add(insDataType);
//	        }
//
//	        // Execute the batched parameterized DELETEs/INSERTs (if any)
//	        if (!sqls.isEmpty()) {
//	            dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
//	        }
//
//	    } catch (Exception e) {
//	        // Keep your existing exception behavior
//	        throw new Exception(e.getMessage());
//	    }
//
//	    return null;
//	}
//	
	public List<GenTlAllmoduleimgfile> saveOplImg(OplTlMst oplTlMst, String keyid, String imagetypepre, String imagetypeaft)throws Exception 
	{
		try
		{
			List<String> sqls = new ArrayList<String>();
			List<Object[]> valueList  = new ArrayList<Object[]>();
			List<int[]> dataTypes  = new ArrayList<int[]>();
			List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
			//CommonMessage.debugMsg("oplTlMst.getAllmoduleimgfile()"+oplTlMst.getAllmoduleimgfile());
			
			for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:newGenTlAllmoduleimgfile)
			{
				//CommonMessage.debugMsg("FILE NAME:"+genTlAllmoduleimgfile.getImflFilename());
				//CommonMessage.debugMsg("BLOB LENGTH:"+genTlAllmoduleimgfile.getImflBloblength());
				
				if(UIUtils.isValidKeyId(imagetypeaft) && UIUtils.isValidKeyId(imagetypepre)){
					sqls.add("Delete from " +GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE+" where imfl_refkeyid='"+keyid+"'");
					dbActionTemplate.executeStatements(sqls);
					sqls.clear(); 
				}
				else if(UIUtils.isValidKeyId(imagetypepre) && !(UIUtils.isValidKeyId(imagetypeaft))){
					sqls.add("Delete from " +GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE+" where imfl_refkeyid='"+keyid+"' and imfl_imagetype='PRE'");
					dbActionTemplate.executeStatements(sqls);
					sqls.clear(); 
				}
				else if(UIUtils.isValidKeyId(imagetypeaft) && !(UIUtils.isValidKeyId(imagetypepre))){
					sqls.add("Delete from " +GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE+" where imfl_refkeyid='"+keyid+"' and imfl_imagetype='AFT'");
					dbActionTemplate.executeStatements(sqls);
					sqls.clear(); 
				}
				
				
				
				if(!(UIUtils.isValidKeyId(genTlAllmoduleimgfile.getImflFilename())))
					continue;
				
				sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());
				sqls.add(GenTlAllmoduleimgfileSql.getInsertSql());
				
				Object [] delValue	= { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype() };
				int [] delTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				java.sql.Timestamp  timeStamp = CommonFunctions.convertoSqlTimeStampfromPgtimestamp(genTlAllmoduleimgfile.getImflModifiedon()); 
				Object [] insValues = { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype(),
						genTlAllmoduleimgfile.getImflBlobimage(),genTlAllmoduleimgfile.getImflBloblength(),
						genTlAllmoduleimgfile.getImflFilename(),genTlAllmoduleimgfile.getImflTempfield1(),genTlAllmoduleimgfile.getImflTempfield2(),timeStamp};
				int [] insDataType = { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR, Types.BLOB,Types.INTEGER ,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP};
			
				valueList.add(delValue);
				valueList.add(insValues);
		
				dataTypes.add(delTypes);
				dataTypes.add(insDataType);
				}
			
			
			dbActionTemplate.saveByteFile(sqls, valueList, dataTypes);
			
		}
		
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return null;
	}

	

//	@Override
//	public List<GenTlAllmoduleimgfile> saveOplImg(
//	        OplTlMst oplTlMst, String keyid, String imagetypepre, String imagetypeaft) throws Exception {
//	    try {
//	        List<String> sqls = new ArrayList<>();
//	        List<Object[]> valueList = new ArrayList<>();
//	        List<int[]> dataTypes = new ArrayList<>();
//
//	        List<GenTlAllmoduleimgfile> list = oplTlMst.getAllmoduleimgfile();
//	        CommonMessage.debugMsg("oplTlMst.getAllmoduleimgfile()" + list);
//	        if (list == null || list.isEmpty()) return null;
//
//	        // --- your pre-delete logic (execute immediately, then clear) ---
//	        for (GenTlAllmoduleimgfile g : list) {
//	            CommonMessage.debugMsg("FILE NAME:" + g.getImflFilename());
//	            CommonMessage.debugMsg("BLOB LENGTH:" + g.getImflBloblength());
//
//	            if (UIUtils.isValidKeyId(imagetypeaft) && UIUtils.isValidKeyId(imagetypepre)) {
//	                sqls.add("Delete from " + GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE
//	                        + " where imfl_refkeyid='" + keyid + "'");
//	                dbActionTemplate.executeStatements(sqls);
//	                sqls.clear();
//	            } else if (UIUtils.isValidKeyId(imagetypepre) && !UIUtils.isValidKeyId(imagetypeaft)) {
//	                sqls.add("Delete from " + GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE
//	                        + " where imfl_refkeyid='" + keyid + "' and imfl_imagetype='PRE'");
//	                dbActionTemplate.executeStatements(sqls);
//	                sqls.clear();
//	            } else if (UIUtils.isValidKeyId(imagetypeaft) && !UIUtils.isValidKeyId(imagetypepre)) {
//	                sqls.add("Delete from " + GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE
//	                        + " where imfl_refkeyid='" + keyid + "' and imfl_imagetype='AFT'");
//	                dbActionTemplate.executeStatements(sqls);
//	                sqls.clear();
//	            }
//	        }
//
//	        // --- build batched DELETE + INSERT for each image ---
//	        for (GenTlAllmoduleimgfile g : list) {
//	            if (!UIUtils.isValidKeyId(g.getImflFilename())) continue;
//
//	            // normalize inputs
//	            String baseDir = g.getImflBlobimage();
//	            if (baseDir == null) baseDir = "";
//	            baseDir = baseDir.trim().replace('\\', '/');
//
//	            String rawFile = g.getImflFilename();
//	            if (rawFile == null) rawFile = "";
//	            rawFile = rawFile.trim().replace('\\', '/');
//
//	            // derive basename from imflFilename
//	            String baseName;
//	            int idx = rawFile.lastIndexOf('/');
//	            baseName = (idx >= 0) ? rawFile.substring(idx + 1) : rawFile;
//
//	            // If imflFilename is absolute, use it; else join with baseDir
//	            boolean rawIsAbsolute = rawFile.startsWith("/")
//	                    || (rawFile.length() >= 3 && Character.isLetter(rawFile.charAt(0))
//	                        && rawFile.charAt(1) == ':' && rawFile.charAt(2) == '/');
//
//	            // if baseDir mistakenly contains a filename, strip it (fixes the double-folder bug)
//	            int cut = baseDir.lastIndexOf('/');
//	            if (cut >= 0) {
//	                String tail = baseDir.substring(cut + 1);
//	                if (tail.contains(".")) { // looks like a file
//	                    baseDir = baseDir.substring(0, cut + 1);
//	                }
//	            }
//	            if (baseDir.length() > 0 && !baseDir.endsWith("/")) baseDir += "/";
//
//	            String fullPath;
//	            if (rawIsAbsolute) {
//	                fullPath = rawFile;
//	            } else {
//	                fullPath = baseDir + baseName;
//	            }
//
//	            java.io.File f = new java.io.File(fullPath);
//	            if (!f.isFile()) {
//	                // also try joining baseDir + rawFile if rawFile was a relative "dir/name" form
//	                String alt = baseDir + (rawFile.startsWith("/") ? rawFile.substring(1) : rawFile);
//	                java.io.File fAlt = new java.io.File(alt);
//	                if (fAlt.isFile()) {
//	                    fullPath = fAlt.getAbsolutePath();
//	                } else {
//	                    throw new Exception(baseName + " (The system cannot find the file). Tried: "
//	                            + fullPath + " and " + alt);
//	                }
//	            } else {
//	                fullPath = f.getAbsolutePath();
//	            }
//
//	            // compute blob length; keep it as String for DBActionTemplate
//	            int blobLenInt;
//	            long fl = new java.io.File(fullPath).length();
//	            if (fl > 0 && fl <= Integer.MAX_VALUE) {
//	                blobLenInt = (int) fl;
//	            } else {
//	                String s = g.getImflBloblength();
//	                try {
//	                    if (s != null) {
//	                        s = s.trim();
//	                        if (s.indexOf('.') >= 0) s = s.substring(0, s.indexOf('.'));
//	                        blobLenInt = Integer.parseInt(s);
//	                    } else {
//	                        blobLenInt = 0;
//	                    }
//	                } catch (Exception ignore) { blobLenInt = 0; }
//	            }
//	            String blobLenStr = String.valueOf(blobLenInt); // <<<<<< IMPORTANT: pass String, not Integer
//
//	            // timestamp
//	            java.sql.Timestamp timeStamp = null;
//	            String rawModifiedOn = g.getImflModifiedon();
//	            if (UIUtils.isValidKeyId(rawModifiedOn)) {
//	                timeStamp = CommonFunctions.pg_convertoSqlTimeStamp(rawModifiedOn);
//	            }
//	            if (timeStamp == null) {
//	                timeStamp = new java.sql.Timestamp(System.currentTimeMillis());
//	            }
//
//	            // NOT NULL temp fields
//	            String t1 = g.getImflTempfield1();
//	            String t2 = g.getImflTempfield2();
//	            if (!UIUtils.isValidKeyId(t1)) t1 = "{}";
//	            if (!UIUtils.isValidKeyId(t2)) t2 = "{}";
//
//	            // DELETE
//	            sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());
//	            valueList.add(new Object[]{
//	                    g.getImflRefkeyid(), g.getImflRefdoctype(), g.getImflImagetype()
//	            });
//	            dataTypes.add(new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});
//
//	            // INSERT (values align with your 9 placeholders)
//	            sqls.add(GenTlAllmoduleimgfileSql.getInsertSql());
//	            valueList.add(new Object[]{
//	                    g.getImflRefkeyid(),               // IMFL_REFKEYID
//	                    g.getImflRefdoctype(),             // IMFL_REFDOCTYPE
//	                    g.getImflImagetype(),              // IMFL_IMAGETYPE
//	                    fullPath,                          // IMFL_BLOBIMAGE  (absolute path for DBActionTemplate)
//	                    blobLenStr,                        // IMFL_BLOBLENGTH (STRING so template can cast)
//	                    baseName,                          // IMFL_FILENAME
//	                    t1,                                // IMFL_TEMPFIELD1
//	                    t2,                                // IMFL_TEMPFIELD2
//	                    timeStamp                          // IMFL_MODIFIEDON
//	            });
//	            dataTypes.add(new int[]{
//	                    Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
//	                    Types.BLOB,    Types.INTEGER,     Types.VARCHAR,
//	                    Types.VARCHAR, Types.VARCHAR,     Types.TIMESTAMP
//	            });
//
//	            CommonMessage.debugMsg("Prepared image insert for " + g.getImflRefkeyid()
//	                    + " " + g.getImflImagetype() + " -> " + fullPath + " (" + blobLenStr + " bytes)");
//	        }
//
//	        if (!sqls.isEmpty()) {
//	            dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
//	        }
//	    } catch (Exception e) {
//	        throw new Exception(e.getMessage());
//	    }
//	    return null;
//	}

   
	/** parse numeric length safely from the model's String */
	private int parseLenSafe(String s) {
	    try {
	        if (s == null) return 0;
	        s = s.trim();
	        if (s.isEmpty()) return 0;
	        // many of your logs have plain integers; if decimal slips in, take floor
	        if (s.indexOf('.') >= 0) {
	            s = s.substring(0, s.indexOf('.'));
	        }
	        return Integer.parseInt(s);
	    } catch (Exception ex) {
	        return 0;
	    }
	}



	
//	public List<GenTlAllmoduleimgfile> saveOplImg11(OplTlMst oplTlMst, String keyid, String imagetypepre, String imagetypeaft)throws Exception 
//	{
//		try
//		{
//			List<String> sqls = new ArrayList<String>();
//			List<Object[]> valueList  = new ArrayList<Object[]>();
//			List<int[]> dataTypes  = new ArrayList<int[]>();
//			List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
//			CommonMessage.debugMsg("oplTlMst.getAllmoduleimgfile()"+oplTlMst.getAllmoduleimgfile());
//			
//			for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:newGenTlAllmoduleimgfile)
//			{
//				CommonMessage.debugMsg("FILE NAME:"+genTlAllmoduleimgfile.getImflFilename());
//				CommonMessage.debugMsg("BLOB LENGTH:"+genTlAllmoduleimgfile.getImflBloblength());
//				
//				if(UIUtils.isValidKeyId(imagetypeaft) && UIUtils.isValidKeyId(imagetypepre)){
//					sqls.add("Delete from " +GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE+" where imfl_refkeyid='"+keyid+"'");
//					dbActionTemplate.executeStatements(sqls);
//				}
//				else if(UIUtils.isValidKeyId(imagetypepre) && !(UIUtils.isValidKeyId(imagetypeaft))){
//					sqls.add("Delete from " +GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE+" where imfl_refkeyid='"+keyid+"' and imfl_imagetype='PRE'");
//					dbActionTemplate.executeStatements(sqls);
//				}
//				else if(UIUtils.isValidKeyId(imagetypeaft) && !(UIUtils.isValidKeyId(imagetypepre))){
//					sqls.add("Delete from " +GenTlAllmoduleimgfileSql.TBL_GEN_TL_ALLMODULEIMGFILE+" where imfl_refkeyid='"+keyid+"' and imfl_imagetype='AFT'");
//					dbActionTemplate.executeStatements(sqls);
//				}
//				
//				
//				
//				if(!(UIUtils.isValidKeyId(genTlAllmoduleimgfile.getImflFilename())))
//					continue;
//				
//				sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());
//				sqls.add(GenTlAllmoduleimgfileSql.getInsertSql());
//				
//				Object [] delValue	= { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype() };
//				int [] delTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
//				java.sql.Timestamp  timeStamp = CommonFunctions.pg_convertoSqlTimeStamp(genTlAllmoduleimgfile.getImflModifiedon()); 
//				Object [] insValues = { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype(),
//						genTlAllmoduleimgfile.getImflBlobimage(),genTlAllmoduleimgfile.getImflBloblength(),
//						genTlAllmoduleimgfile.getImflFilename(),genTlAllmoduleimgfile.getImflTempfield1(),genTlAllmoduleimgfile.getImflTempfield2(),timeStamp};
//				int [] insDataType = { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR, Types.BLOB,Types.INTEGER ,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP};
//			
//				valueList.add(delValue);
//				valueList.add(insValues);
//		
//				dataTypes.add(delTypes);
//				dataTypes.add(insDataType);
//				}
//			
//			
//			dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
//			
//		}
//		
//		catch(Exception e)
//		{
//			throw new Exception(e.getMessage());
//		}
//		
//		return null;
//	}
	
	// ------------------------------------------------------------------------------------------------------

  	
	
	

	/** ---------------------------- **/
	
	@Override // sIMPLEST
	public List<GenTlAllmoduleimgfile> getOplImage(List<GenTlAllmoduleimgfile> oplImgList)
	        throws NoDataFoundException, Exception {

	    CommonMessage.debugMsg("Inside Dao impl Image — simplified");

	    List<GenTlAllmoduleimgfile> result = new ArrayList<>();
	    if (oplImgList == null || oplImgList.isEmpty()) return result;

	    for (GenTlAllmoduleimgfile rec : oplImgList) {
	        if (rec == null) continue;

	        // Build the same condition format expected by restoreFile (which prefixes "WHERE 1 = 1")
	        String condCore  = " IMFL_REFDOCTYPE = '" + rec.getImflRefdoctype()
	                         + "' AND IMFL_IMAGETYPE = '" + rec.getImflImagetype() + "'";
	        String condWhere = " AND IMFL_REFKEYID = '" + rec.getImflRefkeyid() + "' AND " + condCore;

	        // Get stored filename and derive basename
	        String storedPath = dbActionTemplate.getSingleValue(
	                TableNames.TBL_GEN_TL_ALLMODULEIMGFILE,
	                "IMFL_FILENAME", "IMFL_REFKEYID",
	                rec.getImflRefkeyid(), condCore);

	        if (storedPath == null || storedPath.trim().isEmpty()) {
	            continue; // nothing saved for this imagetype
	        }

	        String norm     = storedPath.replace('\\', '/');
	        String baseName = norm.substring(norm.lastIndexOf('/') + 1);

	        // Build target absolute write path (disk) under your temp images folder
	        String webBase = rec.getImflBlobimage();                 // e.g., UIUtils.getImagePath(request)
	        if (webBase == null) webBase = "";
	        webBase = webBase.replace('\\', '/');
	        if (!webBase.endsWith("/")) webBase += "/";
	        String writeTo = webBase + baseName;

	        // Ensure parent folder exists (restoreFile requires existing parent path)
	        int cut = writeTo.lastIndexOf('/');
	        if (cut > 0) {
	            new java.io.File(writeTo.substring(0, cut)).mkdirs();
	        }
	        CommonMessage.debugMsg("Before entering Restorefile1 in DbAction");
	        // Restore bytes from DB to disk
	        dbActionTemplate.restoreFile1(
	                TableNames.TBL_GEN_TL_ALLMODULEIMGFILE,
	                "IMFL_BLOBIMAGE", condWhere, writeTo);

	        // Set the filename that the page will use (prefix passed from service, e.g., UIUtils.TPM_TEMPIMG_DIR)
	        String uiPrefix = rec.getImflFilename();
	        if (uiPrefix == null) uiPrefix = "";
	        uiPrefix = uiPrefix.replace('\\', '/');
	        if (!uiPrefix.endsWith("/")) uiPrefix += "/";
	        rec.setImflFilename(uiPrefix + baseName);

	        result.add(rec);
	    }

	    return result;
	}


//	@Override // WORKING SIMPLE
//	public List<GenTlAllmoduleimgfile> getOplImage(List<GenTlAllmoduleimgfile> oplImgList)
//	        throws NoDataFoundException, Exception {
//
//	    CommonMessage.debugMsg("Inside Dao impl Image — simplified");
//
//	    List<GenTlAllmoduleimgfile> out = new ArrayList<>();
//	    if (oplImgList == null || oplImgList.isEmpty()) return out;
//
//	    for (GenTlAllmoduleimgfile rec : oplImgList) {
//	        if (rec == null) continue;
//
//	        // Build conditions (same as your existing usage with restoreFile's "WHERE 1=1")
//	        String cond     = " IMFL_REFDOCTYPE = '" + rec.getImflRefdoctype()
//	                        + "' AND IMFL_IMAGETYPE = '" + rec.getImflImagetype() + "'";
//	        String condWhere = " AND IMFL_REFKEYID = '" + rec.getImflRefkeyid() + "' AND " + cond;
//
//	        // 1) Get stored filename and extract basename
//	        String storedPath = dbActionTemplate.getSingleValue(
//	                TableNames.TBL_GEN_TL_ALLMODULEIMGFILE,
//	                "IMFL_FILENAME", "IMFL_REFKEYID",
//	                rec.getImflRefkeyid(), cond);
//
//	        if (storedPath == null || storedPath.trim().isEmpty()) {
//	            continue;
//	        }
//	        String norm     = storedPath.replace('\\', '/');
//	        String baseName = norm.substring(norm.lastIndexOf('/') + 1);
//
//	        // 2) Build target write path under the web temp image folder you pass from service
//	        String webBase = rec.getImflBlobimage();       // e.g., UIUtils.getImagePath(request)
//	        if (webBase == null) webBase = "";
//	        webBase = webBase.replace('\\', '/');
//	        if (!webBase.endsWith("/")) webBase += "/";
//	        String writeTo = webBase + baseName;
//
//	        // Ensure folder exists
//	        int slash = writeTo.lastIndexOf('/');
//	        if (slash > 0) {
//	            new java.io.File(writeTo.substring(0, slash)).mkdirs();
//	        }
//
//	        // 3) Only restore if DB has bytes (no disk fallback)
//	        String lenStr = dbActionTemplate.getSingleValue(
//	                TableNames.TBL_GEN_TL_ALLMODULEIMGFILE,
//	                "COALESCE(IMFL_BLOBLENGTH,0)", "IMFL_REFKEYID",
//	                rec.getImflRefkeyid(), cond);
//
//	        int blobLen = 0;
//	        try { blobLen = Integer.parseInt(lenStr == null ? "0" : lenStr.trim()); } catch (NumberFormatException ignore) {}
//
//	        if (blobLen > 0) {
//	            CommonMessage.debugMsg("RESTORE -> DB to: " + writeTo);
//	            dbActionTemplate.restoreFile1(
//	                    TableNames.TBL_GEN_TL_ALLMODULEIMGFILE,
//	                    "IMFL_BLOBIMAGE", condWhere, writeTo);
//	        } else {
//	            CommonMessage.debugMsg("RESTORE -> skipped, no blob for " + rec.getImflImagetype());
//	            continue;
//	        }
//
//	        // 4) Set filename for UI: service already sets rec.imflFilename to the prefix (TPM_TEMPIMG_DIR)
//	        String uiPrefix = rec.getImflFilename();       // e.g., "tmp/images"
//	        if (uiPrefix == null) uiPrefix = "";
//	        uiPrefix = uiPrefix.replace('\\', '/');
//	        if (!uiPrefix.endsWith("/")) uiPrefix += "/";
//	        rec.setImflFilename(uiPrefix + baseName);
//
//	        out.add(rec);
//	    }
//
//	    return out;
//	}

	
	
	
	
	
//	@Override // WORKING FIRST
//	public List<GenTlAllmoduleimgfile> getOplImage(List<GenTlAllmoduleimgfile> oplImgList)
//	        throws NoDataFoundException, Exception {
//	    CommonMessage.debugMsg("Inside Dao impl Image ----- FOR RETURNING IMAGE");
//
//	    List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<>();
//
//	    for (GenTlAllmoduleimgfile rec : oplImgList) {
//	        String condSql  = " IMFL_REFDOCTYPE = '" + rec.getImflRefdoctype() + "' AND IMFL_IMAGETYPE = '" + rec.getImflImagetype() + "'";
//	        String condSql1 = " AND IMFL_REFKEYID = '" + rec.getImflRefkeyid() + "' AND " + condSql;
//
//	        // 1) Get the stored filename (absolute path saved during upload)
//	        String storedPath = dbActionTemplate.getSingleValue(
//	                TableNames.TBL_GEN_TL_ALLMODULEIMGFILE,
//	                "IMFL_FILENAME", "IMFL_REFKEYID",
//	                rec.getImflRefkeyid(), condSql);
//
//	        if (storedPath == null || storedPath.trim().isEmpty()) {
//	            continue; // nothing to do for this imagetype
//	        }
//
//	        // Normalize and split to get just the base file name
//	        String storedNorm = storedPath.replace('\\', '/');
//	        String baseName   = storedNorm.substring(storedNorm.lastIndexOf('/') + 1);
//
//	        // Where the page expects to read from (your temp images folder)
//	        String webBase = rec.getImflBlobimage(); // you already pass this in service as UIUtils.getImagePath(...)
//	        if (webBase == null) webBase = "";
//	        webBase = webBase.replace('\\', '/');
//	        if (!webBase.endsWith("/")) webBase = webBase + "/";
//	        String writeTo = webBase + baseName;
//
//	        // Ensure the target dir exists (restoreFile requires parent path to exist)
//	        try {
//	            int cut = writeTo.lastIndexOf('/');
//	            if (cut > 0) {
//	                new java.io.File(writeTo.substring(0, cut)).mkdirs();
//	            }
//	        } catch (Exception ignore) {}
//
//	        // 2) Check if blob exists (length > 0). If yes, use restoreFile to write bytes.
//	        //    If not, fall back to copying from the stored disk path (if that file still exists).
//	        String blobLenStr = dbActionTemplate.getSingleValue(
//	                TableNames.TBL_GEN_TL_ALLMODULEIMGFILE,
//	                "COALESCE(IMFL_BLOBLENGTH,0)", "IMFL_REFKEYID",
//	                rec.getImflRefkeyid(), condSql);
//
//	        int blobLen = 0;
//	        try { blobLen = Integer.parseInt(blobLenStr == null ? "0" : blobLenStr.trim()); } catch (NumberFormatException ignore) {}
//
//	        if (blobLen > 0) {
//	            // Use DB blob
//	            CommonMessage.debugMsg(" RESTORE -> write from DB blob: " + writeTo);
//	            dbActionTemplate.restoreFile1(
//	                    TableNames.TBL_GEN_TL_ALLMODULEIMGFILE,
//	                    "IMFL_BLOBIMAGE", condSql1, writeTo);
//	        } else {
//	            // Fallback: copy from the original stored disk path, if present
//	            java.io.File src = new java.io.File(storedNorm);
//	            if (src.exists() && src.isFile()) {
//	                CommonMessage.debugMsg(" RESTORE -> fallback copy: " + storedNorm + " -> " + writeTo);
//	                try (java.io.InputStream in = new java.io.FileInputStream(src);
//	                     java.io.OutputStream out = new java.io.FileOutputStream(writeTo)) {
//	                    byte[] buf = new byte[8192];
//	                    int n;
//	                    while ((n = in.read(buf)) > 0) out.write(buf, 0, n);
//	                    out.flush();
//	                }
//	            } else {
//	                CommonMessage.debugMsg(" RESTORE -> no blob and source file missing, skip: " + storedNorm);
//	                continue; // nothing to render
//	            }
//	        }
//
//	        // 3) Set the filename that the page will use (prefix from service + basename)
//	        String uiPrefix = rec.getImflFilename(); // you pass UIUtils.TPM_TEMPIMG_DIR from service
//	        if (uiPrefix == null) uiPrefix = "";
//	        uiPrefix = uiPrefix.replace('\\', '/');
//	        if (!uiPrefix.endsWith("/")) uiPrefix = uiPrefix + "/";
//	        rec.setImflFilename(uiPrefix + baseName);
//
//	        genTlAllmoduleimgList.add(rec);
//	    }
//
//	    return genTlAllmoduleimgList;
//	}

	
	
	
	
// ORACLE
//	public List<GenTlAllmoduleimgfile> getOplImage(List<GenTlAllmoduleimgfile> oplImgList) throws NoDataFoundException, Exception
//	{
//		CommonMessage.debugMsg("Inside Dao impl Image ----- FOR RETURNING IMAGE");
//	//	List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = oplTlMst.getAllmoduleimgfile();
//		List<GenTlAllmoduleimgfile> genTlAllmoduleimgList = new ArrayList<GenTlAllmoduleimgfile>();
//		for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:oplImgList)
//		{
//			String condSql = " IMFL_REFDOCTYPE = '" + genTlAllmoduleimgfile.getImflRefdoctype()+ "' AND IMFL_IMAGETYPE = '" + genTlAllmoduleimgfile.getImflImagetype()+"'";
//			String condSql1 = " AND IMFL_REFKEYID = '" + genTlAllmoduleimgfile.getImflRefkeyid() + "' AND " + condSql ;
//			
//			String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_FILENAME", "IMFL_REFKEYID", genTlAllmoduleimgfile.getImflRefkeyid(),condSql);
//			if( fileName != null )
//			{
//				if( fileName.lastIndexOf("/") > -1 )
//				fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
//			
//				String fileNamePath = genTlAllmoduleimgfile.getImflBlobimage()+  fileName; 
//			
//				String imgFileName = genTlAllmoduleimgfile.getImflFilename()+fileName;
//				genTlAllmoduleimgfile.setImflFilename(imgFileName);
//			
//				CommonMessage.debugMsg(" RESTORE -> disk: fileName " + fileNamePath);
//				
//				 // Changing -- fileNamePath ---> to fileName
//				dbActionTemplate.restoreFile1(TableNames.TBL_GEN_TL_ALLMODULEIMGFILE, "IMFL_BLOBIMAGE", condSql1, fileNamePath);
//				
//				genTlAllmoduleimgList.add(genTlAllmoduleimgfile);
//			}
//		}
//		
//		return genTlAllmoduleimgList;
//	}

	@Override
	public Workbook oplRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj,String reportType) throws Exception 
	{
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try
		   {  
			   rs=getOplResultSet(commonFilter);
			   ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			   // changing from row to 3 from 2 Vignesh 
			   return excelUtils.writeToExcel(rs,reportType,  3, 0,0 );
		   }
		   
		   finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());   
		   }
		
	}
	
	@Override
	public Workbook getEmPillarOPLReportExcel(JSONObject colmodel,
			String format, CommonFilter commonFilter,String emppillar) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		   try{
			
			rs =   getEMPillarOPLResultSet(commonFilter,emppillar);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
			
			return excelUtils.writeToExcel(rs,format, 2,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }
	}
	
	private ResultSet getEMPillarOPLResultSet(CommonFilter commonFilter, String emppillar) throws Exception {
		// TODO Auto-generated method stub

		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
		
		if(UIUtils.isValidKeyId(emppillar)){
	        condParms +="EMPPILLAR="+emppillar+";";
		}
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return dbActionTemplate.NewdbFunctionCall2("OPL_FN_OPLREPORT", paramValues);
	}

	////
	@Override
	public Workbook getoplfourquadrantmatrix(JSONObject colmodel,String format, CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null; 
		try{
		rs =   getOplFourQudrantMatrixReport(commonFilter);
		//CommonMessage.debugMsg("rs value::::::::"+ rs.getConcurrency());
		ExcelUtils excelUtils = new ExcelUtils(colmodel);
		return excelUtils.writeToExcel(rs,format,2,0,0 );
		
	   }finally{
		   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
	   }
	}

private ResultSet getOplFourQudrantMatrixReport(CommonFilter commonFilter) throws Exception{
	// TODO Auto-generated method stub
	List<String> paramValues = new ArrayList<String>();
	String condParms =FilterCondSql.getOPLRelatedCondSql(commonFilter);
	String commonParams = FilterCondSql.getGridCommonParams(commonFilter);
	paramValues.add(condParms);
	paramValues.add(commonParams);//TEST_PC_TEST1.OPL_FN_OPLFOURQUADRANTMATRIX
	return dbActionTemplate.NewdbFunctionCall2("OPL_FN_OPLFOURQUADRANTMATRIX", paramValues);
	//return null;
}
	private List<String> getFilterParamValues(CommonFilter commonFilter)
	{
	   String condParms = FilterCondSql.getOPLRelatedCondSql(commonFilter);
	   com.akranta.tpm.utils.CommonMessage.debugMsg("condParms:"+condParms);
	   String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
	   List<String> paramValues  = new ArrayList<String>();	
	   paramValues.add(condParms);
	   paramValues.add(commonParams);
	   
	   return paramValues;
		
	}
	
	private ResultSet getOplResultSet(CommonFilter commonFilter) throws Exception
	{
		//List<String> paramValues = getFilterParamValues(commonFilter);
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter); // OPLRelatedCondSql(commonFilter)			
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		
		String modifymode =commonFilter.getMainGroup();
		String prepardby=commonFilter.getRescheduleTo();
		
		if(UIUtils.isValidKeyId(modifymode)){
		  condParms +="MODIFYMODE="+modifymode+";";
		}
		
		if(UIUtils.isValidKeyId(prepardby)){
			condParms +="PREPARADBY="+prepardby+";";
		}
		
		paramValues.add(condParms);
		paramValues.add(commonParams);
		// --vignesh 11Feb2026
		return  dbActionTemplate.NewdbFunctionCall2("OPL_FN_OPLREPORT_SB", paramValues);
		 	
	}

	public OplTlMst updateDocUpdates(OplTlMst oplTlMst, String docId,String yyId)throws Exception {
		
			List<String> sqls = new ArrayList<String>();
		try
		{
			sqls.add(OplTlMstSql.getoplDocUpdatesSql(oplTlMst.getOplmKeyid(),docId));
			BdmTlYycountermeasurelink bdmTlYycountermeasurelink = new BdmTlYycountermeasurelink();
			bdmTlYycountermeasurelink.setYycmKeyid(dbActionTemplate.getSequenceNumber(BdmTlYycountermeasurelinkSql.TBL_BDM_TL_YYCOUNTERMEASURELINK ,11, "YCM", "YYMM", "Y"));
			
			sqls.add(OplTlMstSql.insertYYCounterMeasureSql(bdmTlYycountermeasurelink.getYycmKeyid(),yyId,oplTlMst));
			
			dbActionTemplate.executeStatements(sqls);	
		}
		catch(Exception e)
		{
			//CommonMessage.debugMsg("Exception updateDocUpdate Dao impl "+e.getMessage());
		}
		return null;
	}

	
//	@Override
//	public OplTlMst updateApprovedStatusLevel(String status,String keyid, String nextLevel, String type, String value,String mpValue) throws Exception {
//		
//		OplTlMst oplTlMst=new OplTlMst();
//		String sql=null;
//	
//		String sqll = "select count(*) from GEN_TL_WORKFLOW_INFO where WRIN_REF_ID='"+keyid+"' ";
//		String apprvCnt = dbActionTemplate.getSingleValue(sqll);
//		CommonMessage.debugMsg("apprvCnt" + apprvCnt );
//		
//		
//		if(mpValue!=null)
//		{
//			if(mpValue.equals("Y"))
//			{
//				String  sql1= " UPDATE  OPL_TL_MST  SET OPLM_MPWORTHY ='Y' WHERE OPLM_KEYID ='"+keyid+"' ";
//				dbActionTemplate.executeStatement(sql1);
//			}
//		}
//		
//		if(value!=null)
//		{
//			if(value.equals("Y"))
//			{
//				String  sql1= " UPDATE  OPL_TL_MST  SET OPLM_UTILISEFORFUTURE ='Y' WHERE OPLM_KEYID ='"+keyid+"' ";
//				dbActionTemplate.executeStatement(sql1);
//			}
//		}
//		
//		
//		//CommonMessage.debugMsg("apprvCnt"+apprvCnt+"statuswrin"+statuswrin);
//				//return Integer.parseInt(apprvCnt);
//		//if (apprvCnt == 0)
//		//if(!apprvCnt.)
//		if(Integer.parseInt(apprvCnt)!=0){
//			 // -- HARDCODED WRIN_WRKD_KEYID IN BY VIGNESH 14Oct2025 -- To be Removed
//			if(Integer.parseInt(apprvCnt)==1){
//				String sql2 = "select wrin_status from GEN_TL_WORKFLOW_INFO where WRIN_REF_ID='"+keyid+"' and WRIN_WRKD_KEYID IN ('QTM0000141')";
//				String statuswrin = dbActionTemplate.getSingleValue(sql2);
//				CommonMessage.debugMsg("statuswrin" + statuswrin);
//				if(statuswrin.equals("A"))
//				{
//		       sql= " UPDATE  OPL_TL_MST  SET OPLM_STATUS ='"+status+"',OPLM_APROV_LEVEL ='DMT LEADER' WHERE OPLM_KEYID ='"+keyid+"' ";
//		       dbActionTemplate.executeStatement(sql);
//				}
//			else{
//				
//				 sql= " UPDATE  OPL_TL_MST  SET OPLM_STATUS ='"+statuswrin+"', OPLM_APROV_LEVEL ='-' WHERE OPLM_KEYID ='"+keyid+"' ";
//				 dbActionTemplate.executeStatement(sql);
//			}
//			}
//		if(Integer.parseInt(apprvCnt)==2){
//			String sql2 = "select wrin_status from GEN_TL_WORKFLOW_INFO where WRIN_REF_ID='"+keyid+"' and WRIN_WRKD_KEYID IN ('QTM0000142')";
//			String statuswrin = dbActionTemplate.getSingleValue(sql2);
//			if(statuswrin.equals("A"))
//			{
//			       sql= " UPDATE  OPL_TL_MST  SET OPLM_UTILISEFORFUTURE ='"+value+"', OPLM_STATUS ='C',OPLM_APROV_LEVEL ='-' WHERE OPLM_KEYID ='"+keyid+"' ";
//			       dbActionTemplate.executeStatement(sql);
//			}
//		else{
//			 sql= " UPDATE  OPL_TL_MST  SET OPLM_STATUS ='"+statuswrin+"',OPLM_APROV_LEVEL ='-' WHERE OPLM_KEYID ='"+keyid+"' ";
//			 dbActionTemplate.executeStatement(sql);
//		}
//		}
//			
//		}
//		CommonMessage.debugMsg("sql"+sql);
//		//if(UIUtils.isValidKeyId(type))
//		//   sql= " UPDATE  OPL_TL_MST  SET OPLM_UTILISEFORFUTURE ='"+value+"' WHERE OPLM_KEYID ='"+keyid+"' ";
//		//else
//		 //  sql= " UPDATE  OPL_TL_MST  SET OPLM_STATUS ='"+status+"', OPLM_APROV_LEVEL='"+nextLevel+"' WHERE OPLM_KEYID ='"+keyid+"' ";
//		//CommonMessage.debugMsg("sqlll==="+sql);
//		
//		return oplTlMst;
//
//	}
	
public OplTlMst updateApprovedStatusLevel(String status,String keyid, String nextLevel, String type, String value,String mpValue) throws Exception {
		
		OplTlMst oplTlMst=new OplTlMst();
		String sql=null;
	
		String sqll = "select count(*) from GEN_TL_WORKFLOW_INFO where WRIN_REF_ID='"+keyid+"' ";
		String apprvCnt = dbActionTemplate.getSingleValue(sqll);
		CommonMessage.debugMsg("apprvCnt"+ apprvCnt );
		
		
		if(mpValue!=null)
		{
			if(mpValue.equals("Y"))
			{
				String  sql1= " UPDATE  OPL_TL_MST  SET OPLM_MPWORTHY ='Y' WHERE OPLM_KEYID ='"+keyid+"' ";
				dbActionTemplate.executeStatement(sql1);
			}
		}
		
		if(value!=null)
		{
			if(value.equals("Y"))
			{
				String  sql1= " UPDATE  OPL_TL_MST  SET OPLM_UTILISEFORFUTURE ='Y' WHERE OPLM_KEYID ='"+keyid+"' ";
				dbActionTemplate.executeStatement(sql1);
			}
			
		} else {
			value="N";
		}
		
		
		//CommonMessage.debugMsg("apprvCnt"+apprvCnt+"statuswrin"+statuswrin);
				//return Integer.parseInt(apprvCnt);
		//if (apprvCnt == 0)
		//if(!apprvCnt.)
		if(Integer.parseInt(apprvCnt)!=0){
			
			if(Integer.parseInt(apprvCnt)==1){
				String sql2 = "select wrin_status from GEN_TL_WORKFLOW_INFO where WRIN_REF_ID='"+keyid+"' and WRIN_ROLE_ID IN (SELECT ROLE_KEYID FROM ADM_TL_ROLEMST	WHERE ROLE_CODE='JH LEADER')";				
				String statuswrin = dbActionTemplate.getSingleValue(sql2);
				CommonMessage.debugMsg("statuswrin"+statuswrin);
				if(statuswrin.equals("A"))
				{
		       sql= " UPDATE  OPL_TL_MST  SET OPLM_STATUS ='"+status+"',OPLM_APROV_LEVEL ='DMT LEADER' WHERE OPLM_KEYID ='"+keyid+"' ";
		       dbActionTemplate.executeStatement(sql);
				}
			else{
				
				 sql= " UPDATE  OPL_TL_MST  SET OPLM_STATUS ='"+statuswrin+"', OPLM_APROV_LEVEL ='-' WHERE OPLM_KEYID ='"+keyid+"' ";
				 dbActionTemplate.executeStatement(sql);
			}
			}
		if(Integer.parseInt(apprvCnt)==2){
			String sql2 = "select wrin_status from GEN_TL_WORKFLOW_INFO where WRIN_REF_ID='"+keyid+"' and WRIN_ROLE_ID IN (SELECT ROLE_KEYID FROM ADM_TL_ROLEMST	WHERE ROLE_CODE='DMT LEADER')";
			String statuswrin = dbActionTemplate.getSingleValue(sql2);
			if(statuswrin.equals("A"))
			{
				CommonMessage.debugMsg("value for sql" + value);
				
			       sql= " UPDATE  OPL_TL_MST  SET OPLM_UTILISEFORFUTURE ='"+value+"', OPLM_STATUS ='C',OPLM_APROV_LEVEL ='-' WHERE OPLM_KEYID ='"+keyid+"' ";
			       CommonMessage.debugMsg("printing whole sql"  +sql);
			       dbActionTemplate.executeStatement(sql);
			}
		else{
			 sql= " UPDATE  OPL_TL_MST  SET OPLM_STATUS ='"+statuswrin+"',OPLM_APROV_LEVEL ='-' WHERE OPLM_KEYID ='"+keyid+"' ";
			 dbActionTemplate.executeStatement(sql);
		}
		}
			
		}
		CommonMessage.debugMsg("sql"+sql);
		//if(UIUtils.isValidKeyId(type))
		//   sql= " UPDATE  OPL_TL_MST  SET OPLM_UTILISEFORFUTURE ='"+value+"' WHERE OPLM_KEYID ='"+keyid+"' ";
		//else
		 //  sql= " UPDATE  OPL_TL_MST  SET OPLM_STATUS ='"+status+"', OPLM_APROV_LEVEL='"+nextLevel+"' WHERE OPLM_KEYID ='"+keyid+"' ";
		//CommonMessage.debugMsg("sqlll==="+sql);
		
		return oplTlMst;

	}
	
	
	@Override
	public void deleteimage(String keyid, String imagetype)
			throws Exception {
		// TODO Auto-generated method stub
		List<String > sqls = new ArrayList<String>();
		
		sqls.add( OplTlMstSql.Deleteimageclear(keyid,imagetype));
  
		dbActionTemplate.executeStatements(sqls);
	}
	
	@Override
	public Workbook getFourQuarExcelDatewise(JSONObject colmodel,
			String format, CommonFilter commonFilter, String imagepath)
			throws Exception {
		// TODO Auto-generated method stub

		ResultSet rs = null;
		try {
			rs = getFourQuarExcelDateWise(commonFilter);
			ExcelUtils excelUtils = new ExcelUtils(colmodel);
            //CommonMessage.debugMsg(" Checking for 12 ");
			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			XLConditionalFormats statusBooked = new XLConditionalFormats();
			statusBooked.setFontName(XLConditionalFormats.FONT_DEFAULT);
			statusBooked.setSymbolStr("-");
			statusBooked.setFromCol(5);
			statusBooked.setToCol(5);
			statusBooked.setOperator(ComparisonOperator.EQUAL);
			statusBooked.setCondValue("0");
			statusBooked.setIdentfier("Quadrant0");
			statusBooked.setImgPathName(imagepath + "images/green0.jpg");
			com.akranta.tpm.utils.CommonMessage.debugMsg(statusBooked.getImgPathName()
					+ " image nmae");
			condFormats.add(statusBooked);

			XLConditionalFormats statusBooked2 = new XLConditionalFormats();
			statusBooked2.setFontName(XLConditionalFormats.FONT_DEFAULT);
			statusBooked2.setFromCol(5);
			statusBooked2.setToCol(5);
			//CommonMessage.debugMsg(" Checking for 34 ");
			statusBooked2.setSymbolStr("-");
			statusBooked2.setOperator(ComparisonOperator.EQUAL);
			statusBooked2.setCondValue("1");
			statusBooked2.setIdentfier("Quadrant1");
			statusBooked2.setImgPathName(imagepath + "images/Green1.jpg");
			condFormats.add(statusBooked2);

			XLConditionalFormats statusBooked3 = new XLConditionalFormats();
			statusBooked3.setFontName(XLConditionalFormats.FONT_DEFAULT);
			statusBooked3.setFromCol(5);
			statusBooked3.setToCol(5);
			//CommonMessage.debugMsg(" Checking for 56 ");
			statusBooked3.setSymbolStr("-");
			statusBooked3.setOperator(ComparisonOperator.EQUAL);
			statusBooked3.setCondValue("2");
			statusBooked3.setIdentfier("Quadrant2");
			statusBooked3.setImgPathName(imagepath + "images/green2.jpg");
			condFormats.add(statusBooked3);

			XLConditionalFormats statusBooked4 = new XLConditionalFormats();
			statusBooked4.setFontName(XLConditionalFormats.FONT_DEFAULT);
			statusBooked4.setFromCol(5);
			statusBooked4.setToCol(5);
			//CommonMessage.debugMsg(" Checking for 78 ");
			statusBooked4.setSymbolStr("-");
			statusBooked4.setOperator(ComparisonOperator.EQUAL);
			statusBooked4.setCondValue("3");
			statusBooked4.setIdentfier("Quadrant3");
			statusBooked4.setImgPathName(imagepath + "images/green-3.jpg");
			condFormats.add(statusBooked4);

			XLConditionalFormats statusBooked5 = new XLConditionalFormats();
			statusBooked5.setFontName(XLConditionalFormats.FONT_DEFAULT);
			statusBooked5.setFromCol(5);
			statusBooked5.setToCol(5);
			//CommonMessage.debugMsg(" Checking for 90 ");
			statusBooked5.setOperator(ComparisonOperator.EQUAL);
			statusBooked5.setCondValue("4");
			statusBooked5.setSymbolStr("-");
			statusBooked5.setIdentfier("Quadrant4");
			statusBooked5.setImgPathName(imagepath + "images/green4.jpg");
			condFormats.add(statusBooked5);
			excelUtils.setCondFormats(condFormats);
                   // changing column start to 1
			return excelUtils.writeToExcel(rs, format, 2, 1, 0);

		} finally {

			DBActionTemplate.closeConnection(rs, null, null, null, rs
					.getStatement().getConnection());
		}
	}

	@Override
	public Workbook getFourQuarExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter, String imagepath) throws Exception {
		ResultSet rs = null;
		try {
			rs = getFourQuarExcel(commonFilter);
			//CommonMessage.debugMsg("This OPL Export Excel DAOIMPL");
			ExcelUtils excelUtils = new ExcelUtils(colmodel);

			List<XLConditionalFormats> condFormats = new ArrayList<XLConditionalFormats>();
			XLConditionalFormats statusBooked = new XLConditionalFormats();
			statusBooked.setFontName(XLConditionalFormats.FONT_DEFAULT);
			// statusBooked.setFontHeightPoint((short)8);
			// statusBooked.setFontBoldWeight((short)20);
			statusBooked.setSymbolStr("-");
			statusBooked.setFromCol(2);
			statusBooked.setToCol(-1);
			statusBooked.setOperator(ComparisonOperator.EQUAL);
			statusBooked.setCondValue("0");
			statusBooked.setIdentfier("Quadrant0");
			statusBooked.setImgPathName(imagepath + "images/green0.jpg");
			// statusBooked.setBgColor(new RGB(67, 197, 221));
			com.akranta.tpm.utils.CommonMessage.debugMsg(statusBooked.getImgPathName()
					+ " image nmae");
			condFormats.add(statusBooked);

			XLConditionalFormats statusBooked2 = new XLConditionalFormats();
			statusBooked2.setFontName(XLConditionalFormats.FONT_DEFAULT);
			// statusBooked2.setFontHeightPoint((short)8);
			// /statusBooked2.setFontBoldWeight((short)20);
			// statusBooked2.setDbChkColIndx(25);
			statusBooked2.setFromCol(2);
			statusBooked2.setToCol(-1);
			statusBooked2.setSymbolStr("-");
			statusBooked2.setOperator(ComparisonOperator.EQUAL);
			statusBooked2.setCondValue("1");
			statusBooked2.setIdentfier("Quadrant1");
			statusBooked2.setImgPathName(imagepath + "images/Green1.jpg");
			// statusBooked2.setBgColor(new RGB(224, 195, 195));
			condFormats.add(statusBooked2);

			XLConditionalFormats statusBooked3 = new XLConditionalFormats();
			statusBooked3.setFontName(XLConditionalFormats.FONT_DEFAULT);
			// /statusBooked3.setFontHeightPoint((short)8);
			// statusBooked3.setFontBoldWeight((short)20);
			// statusBooked3.setDbChkColIndx(23);
			statusBooked3.setFromCol(2);
			statusBooked3.setToCol(-1);
			statusBooked3.setSymbolStr("-");
			statusBooked3.setOperator(ComparisonOperator.EQUAL);
			statusBooked3.setCondValue("2");
			statusBooked3.setIdentfier("Quadrant2");
			statusBooked3.setImgPathName(imagepath + "images/green2.jpg");
			// statusBooked3.setBgColor(new RGB(175, 214, 254));
			condFormats.add(statusBooked3);

			XLConditionalFormats statusBooked4 = new XLConditionalFormats();
			statusBooked4.setFontName(XLConditionalFormats.FONT_DEFAULT);
			// statusBooked4.setFontHeightPoint((short)8);
			// statusBooked4.setFontBoldWeight((short)20);
			// statusBooked4.setDbChkColIndx(22);
			statusBooked4.setFromCol(2);
			statusBooked4.setToCol(-1);
			statusBooked4.setSymbolStr("-");
			statusBooked4.setOperator(ComparisonOperator.EQUAL);
			statusBooked4.setCondValue("3");
			statusBooked4.setIdentfier("Quadrant3");
			statusBooked4.setImgPathName(imagepath + "images/green-3.jpg");
			// statusBooked4.setBgColor(new RGB(239, 182, 239));
			condFormats.add(statusBooked4);

			XLConditionalFormats statusBooked5 = new XLConditionalFormats();
			statusBooked5.setFontName(XLConditionalFormats.FONT_DEFAULT);
			// statusBooked5.setFontHeightPoint((short)8);
			// statusBooked5.setFontBoldWeight((short)20);
			// statusBooked5.setDbChkColIndx(22);
			statusBooked5.setFromCol(2);
			statusBooked5.setToCol(-1);
			statusBooked5.setOperator(ComparisonOperator.EQUAL);
			statusBooked5.setCondValue("4");
			statusBooked5.setSymbolStr("-");
			statusBooked5.setIdentfier("Quadrant4");
			statusBooked5.setImgPathName(imagepath + "images/green4.jpg");
			// statusBooked5.setBgColor(new RGB(239, 182, 239));
			condFormats.add(statusBooked5);
			excelUtils.setCondFormats(condFormats);
  // changing column start to 1
			return excelUtils.writeToExcel(rs, format, 2, 1, 0);

		} finally {

			DBActionTemplate.closeConnection(rs, null, null, null, rs
					.getStatement().getConnection());
		}
	}

	private ResultSet getFourQuarExcel(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValue(commonFilter);
		return dbActionTemplate.NewdbFunctionCall2("OPL_FN_OPLFOURQUADRANTMATRIX", paramValues);
	}

	private ResultSet getFourQuarExcelDateWise(CommonFilter commonFilter) throws Exception {
		List<String> paramValues = getFilterParamValue(commonFilter);
		return dbActionTemplate.NewdbFunctionCall2("OPL_FN_OPLFOURQDANTMTRIXDTEWSE", paramValues);
	}
	
	private List<String> getFilterParamValue(CommonFilter commonFilter) {
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getAbnRelatedConditionStr(commonFilter);
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); // "ISTOTALCNT="+commonFilter.getViewClick() +";FROMTOROW="+commonFilter.getFromRow() +" AND " + commonFilter.getToRow() +";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return paramValues;
	   }

	@Override
	public List<String[]> FillEmployeeDatainGrid(String keyid) throws Exception {
		// TODO Auto-generated method stub
		String sql = OplTlMstSql.fillEmployeeDatainGrid(keyid);
		//CommonMessage.debugMsg("sql  "+sql);
		List<String []> gridData = dbActionTemplate.getDataList(sql);
		return gridData;
	}

	public OplTlMst createOPlUpload(OplTlMst oplTlMst) 	throws BusinessApplicationExceptions,Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		String sqlss = "" ;
		try
		{   

			String elementId = oplTlMst.getOplmElementid();
			CommonMessage.debugMsg(" Inside Dao Impl elementId "+elementId);

			String location = null;
		 	String seqIdentfi = com.akranta.tpm.dao.impl.CommonFunctions.getSeqnoLocationIdentifier(elementId,OplTlMstSql.TBL_OPL_TL_MST);
		 	
		 	oplTlMst.setOplmKeyid(dbActionTemplate.getSequenceNumber(seqIdentfi,12,"OP","YY","Y"));
			
			sqls.add(OplTlMstSql.getInsertSql(oplTlMstSql.getOplmDbFields(), oplTlMst.getSaveArray())); // add insert sql for master table
				
			//CommonMessage.debugMsg(" Inside :: If create 10");
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			//CommonMessage.debugMsg(" Inside :: If create 11");
		}
		
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return oplTlMst;
	}
	
	public  OplTlMst updateOPlUpload(OplTlMst oplTlMst) throws BusinessApplicationExceptions,Exception{ 
		//CommonMessage.debugMsg(" Inside :: Else update 9");
		List<String> sqls = new ArrayList<String>();
		String sqlss = "" ;

		try 
		{
			CommonMessage.debugMsg(" Inside the Update");
			sqls.add(OplTlMstSql.getUpdateSql(oplTlMstSql.getOplmDbFields(), oplTlMst.getSaveArray()));
		//	setPillarLinkSqls(sqls,oplTlMst);
			dbActionTemplate.executeStatements(sqls);
					}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return oplTlMst;
	}
	
	public List<String []> getIndividualOplReport(CommonFilter commonFilter, String emppillar) throws Exception
	{
		try
		{
			
			String modifymode =commonFilter.getMainGroup();
			CommonMessage.debugMsg("The modifymode:::::"+modifymode);
			String oplmatrix=commonFilter.getType();
			String EmpillarOPLMP=commonFilter.getMPWorthy();
			String EmpillarOPLUtilize=commonFilter.getUtiliseFuture();
			String prepardby=commonFilter.getRescheduleTo();
			String Empid=commonFilter.getAbnDetectBy();
            CommonMessage.debugMsg("The EmpId::::"+Empid);						
			List<String> paramValues = new ArrayList<String>();
			
			String condParms =FilterCondSql.getOPLRelatedCondSql(commonFilter);
			String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 
			
			
			if(UIUtils.isValidKeyId(commonFilter.getAbnDetectBy()))
				condParms+="EMPID="+commonFilter.getAbnDetectBy()+";";
			
			if(UIUtils.isValidKeyId(commonFilter.getEmpch()))
				condParms += "EMPLOYEEKEYID="+commonFilter.getEmpch()+";";
			
			if(UIUtils.isValidKeyId(modifymode)){
			    condParms +="MODIFYMODE="+modifymode+";";
			}
			
			if(UIUtils.isValidKeyId(oplmatrix))
			   condParms +="OPLMATRIX="+oplmatrix+";";
			
			
			if(UIUtils.isValidKeyId(EmpillarOPLMP)){
		        condParms +="MPWORTHY="+EmpillarOPLMP+";";
			}
			
			if(UIUtils.isValidKeyId(emppillar)){
		        condParms +="EMPPILLAR="+emppillar+";";
			}
			
            if(UIUtils.isValidKeyId(EmpillarOPLUtilize)){
		        condParms +="UTILISEFUTURE="+EmpillarOPLUtilize+";";
			}
            
            if(UIUtils.isValidKeyId(prepardby)){
		        condParms +="PREPARADBY="+prepardby+";";
			}
            CommonMessage.debugMsg("The condParms"+condParms);
            paramValues.add(condParms);
			paramValues.add(commonParams);
			
			
			List<String[]> oplModify= dbActionTemplate.processFunctionCalls("OPL_FN_OPLINDIVIDUALREPORT", paramValues);
			
			if( commonFilter.getViewClick() == 'Y'){
				String totalCnt = paramValues.get(0); 
				boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
				if(  isInteger ){
					commonFilter.setTotalRecordCnt(Long.parseLong(totalCnt));
				}
			}
			return oplModify;
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	@Override
	public Workbook IndividualoplRptExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj,String reportType) throws Exception 
	{
		  ResultSet rs=null;
		   try
		   {  
			   rs=getOplIndividualResultSet(commonFilter);
			   ExcelUtils excelUtils = new ExcelUtils(tblJSONObj);
			   return excelUtils.writeToExcel(rs,reportType,2, 0,0);
		   }
		   
		   finally{
			   DBActionTemplate.closeConnection(rs,null,null,null,rs.getStatement().getConnection());   
		   }
	}
	private ResultSet getOplIndividualResultSet(CommonFilter commonFilter) throws Exception
	{
		List<String> paramValues = new ArrayList<String>();
		String condParms = FilterCondSql.getJHCLITRelatedCondStr(commonFilter); 
		String commonParams = FilterCondSql.getGridCommonParams(commonFilter); 		
		if(UIUtils.isValidKeyId(commonFilter.getAbnDetectBy()))
			condParms+="EMPID="+commonFilter.getAbnDetectBy()+";";
		paramValues.add(condParms);
		paramValues.add(commonParams);
		return  dbActionTemplate.NewdbFunctionCall2("OPL_FN_OPLINDIVIDUALREPORT", paramValues);	 	
	}
	
	@Override
    public List<String[]> getOplUpdatedRow(String keyId) throws Exception {
        String sql = OplTlMstSql.getUpdatedRowOpl(keyId); // your SQL class name
        List<String[]> updatedRow = dbActionTemplate.getDataList(sql);
        CommonMessage.debugMsg("OPL DAO updatedRow keyId: " + keyId);
        return updatedRow;
    }
	
	}
