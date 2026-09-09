package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.EntTlAssessmentmstBean;
import com.akranta.tpm.bean.MultiBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.EntSkillAnalysisDao;
import com.akranta.tpm.dao.EntTlAssessmentmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.EntSkilAnalysisDaoImpl;
import com.akranta.tpm.dao.impl.EntTlAssessmentmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlAssessmentChecklist;
import com.akranta.tpm.model.EntTlAssessmentmst;
import com.akranta.tpm.model.EntTlAssessmentdtl;
import com.akranta.tpm.model.EntTlMultiskilldialymap;
import com.akranta.tpm.model.EntTlMultiskillempmap;
import com.akranta.tpm.model.EntTlTrainingneedmst;
import com.akranta.tpm.model.StdTlStdworksheetdtl;
import com.akranta.tpm.service.EntTlAssessmentmstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class EntTlAssessmentmstServiceImpl  implements EntTlAssessmentmstService {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private CommonFilterDao commonFilterDao;
	private EntTlAssessmentmstDao entTlAssessmentmstDao;
	private EntSkillAnalysisDao entSkillAnalysisDao;
	private Validations validations ;
    public EntTlAssessmentmstServiceImpl(DBActionTemplate dbActionTemplate){    	
    	commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
        entTlAssessmentmstDao = new EntTlAssessmentmstDaoImpl(dbActionTemplate);
        entSkillAnalysisDao = new EntSkilAnalysisDaoImpl(dbActionTemplate);
        validations = new Validations();
        // TODO Auto-generated constructor stub
    }

    public EntTlAssessmentmst create(EntTlAssessmentmst newEntTlAssessmentmst,EntTlAssessmentmst oldEntTlAssessmentmst, 
			EntTlAssessmentmstBean entTlAssessmentmstBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception 
	{
		try 
		{
			String validationsFor;
			validationsFor="create ";
			String maxDate=null;
	    	String minDate=null;
	    	CommonMessage.debugMsg("inside assesment service impl");
	    	entTlAssessmentmstDao.checkEvalDateEqCurrentDate(newEntTlAssessmentmst.getAsmmEvaluationType(),
	    			newEntTlAssessmentmst.getAsmmEvaluationDate(),newEntTlAssessmentmst.getAsmmEmpmKeyid());
	    	if(newEntTlAssessmentmst.getAsmmEvaluationType().equals("POS")){
//	    		validationsFor="pos";
	    		maxDate=entTlAssessmentmstDao.getMaxEvlDate(newEntTlAssessmentmst);
	    		entTlAssessmentmstBean.setPostEvaluationdate(maxDate);
	    		entTlAssessmentmstBean.setPreEvaluationdate(newEntTlAssessmentmst.getAsmmEvaluationDate());
	    		CommonMessage.debugMsg("Inside create pos");
	    	}
			else if(newEntTlAssessmentmst.getAsmmEvaluationType().equals("PRE")){
//				validationsFor="pre";
				minDate=entTlAssessmentmstDao.getMinEvlDate(newEntTlAssessmentmst);
				entTlAssessmentmstBean.setPreEvaluationdate(minDate);
				entTlAssessmentmstBean.setPostEvaluationdate(newEntTlAssessmentmst.getAsmmEvaluationDate());
				CommonMessage.debugMsg("Inside create pre" +newEntTlAssessmentmst.getAsmmEvaluationDate() +"--"+minDate);
				
			}
			validations.validate(newEntTlAssessmentmst,"EntTlAssessmentmstCreation",validationsFor);
			//if(UIUtils.isValidKeyId(minDate) || UIUtils.isValidKeyId(maxDate))
				validations.validate(entTlAssessmentmstBean,"EntTlAssessmentmstCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
			List<EntTlAssessmentdtl> newEntTlAssessmentdtl = newEntTlAssessmentmst.getEntTlAssessmentdtl();
			if (newEntTlAssessmentmst.getEntTlAssessmentdtl() != null
					&& newEntTlAssessmentmst.getEntTlAssessmentdtl().size() > 0) 
			{
				for( EntTlAssessmentdtl entTlAssessmentdtl :newEntTlAssessmentdtl)
				{	
					//CommonMessage.debugMsg("validate entTlAssessmentdtl");
					validations.validate(entTlAssessmentdtl,"EntTlAssessmentmstCreation",validationsFor);	
				}
			}
			fillValues(newEntTlAssessmentmst,oldEntTlAssessmentmst,entTlAssessmentmstBean);
			//CommonMessage.debugMsg("After Fill Values");
			return entTlAssessmentmstDao.create(newEntTlAssessmentmst);	
		}
		catch (ValidationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}	
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
	}

    public EntTlAssessmentmst update(EntTlAssessmentmst newEntTlAssessmentmst,EntTlAssessmentmst oldEntTlAssessmentmst, 
			EntTlAssessmentmstBean entTlAssessmentmstBean) throws ValidationExceptions,BusinessApplicationExceptions, Exception {
		CommonMessage.debugMsg("Inside the ServiceImpl update");
		String validationsFor="update";
		String maxDate=null;
    	String minDate=null;
    	CommonMessage.debugMsg("inside assesment service impl");
    	if(newEntTlAssessmentmst.getAsmmEvaluationType().equals("POS")){
//    		validationsFor="pos";
    		maxDate=entTlAssessmentmstDao.getMaxEvlDate(newEntTlAssessmentmst);
    		entTlAssessmentmstBean.setPostEvaluationdate(maxDate);
    		entTlAssessmentmstBean.setPreEvaluationdate(newEntTlAssessmentmst.getAsmmEvaluationDate());
    		CommonMessage.debugMsg("Inside update pos");
    	}
		else if(newEntTlAssessmentmst.getAsmmEvaluationType().equals("PRE")){
//			validationsFor="pre";
			minDate=entTlAssessmentmstDao.getMinEvlDate(newEntTlAssessmentmst);
			entTlAssessmentmstBean.setPreEvaluationdate(minDate);
			entTlAssessmentmstBean.setPostEvaluationdate(newEntTlAssessmentmst.getAsmmEvaluationDate());
			CommonMessage.debugMsg("Inside update pre");
		}
		
		
		validations.validate(newEntTlAssessmentmst,"EntTlAssessmentmstCreation",validationsFor);//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		validations.validate(entTlAssessmentmstBean,"EntTlAssessmentmstCreation",validationsFor);
		List<EntTlAssessmentdtl> newEntTlAssessmentdtl = newEntTlAssessmentmst.getEntTlAssessmentdtl();
		if (newEntTlAssessmentmst.getEntTlAssessmentdtl() != null
				&& newEntTlAssessmentmst.getEntTlAssessmentdtl().size() > 0) 
		{
			for( EntTlAssessmentdtl entTlAssessmentdtl :newEntTlAssessmentdtl)
			{	
				//CommonMessage.debugMsg("validate entTlAssessmentdtl");
				validations.validate(entTlAssessmentdtl,"EntTlAssessmentmstCreation",validationsFor);	
			}
		}
		fillValues(newEntTlAssessmentmst,oldEntTlAssessmentmst,entTlAssessmentmstBean);		
		//CommonMessage.debugMsg("After Fill Values");
		return entTlAssessmentmstDao.update(newEntTlAssessmentmst);	
    }	

	@Override
	public EntTlAssessmentmst delete(EntTlAssessmentmst newEntTlAssessmentmst)throws Exception 
	{
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.delete(newEntTlAssessmentmst);
	}
	
	@Override
	public EntTlAssessmentmst select(EntTlAssessmentmst EntTlAssessmentmst) throws Exception 
	{
		//CommonMessage.debugMsg("ServiceImpl:"+EntTlAssessmentmst.getAsmmkeyid());
		return this.entTlAssessmentmstDao.select(EntTlAssessmentmst);
	}

	@Override
	public List<EntTlAssessmentdtl> selectAssessmentList(EntTlAssessmentdtl entTlAssessmentdtl)throws Exception{
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.selectAssessmentList(entTlAssessmentdtl);
	}
	
	@Override
	public List<String[]> getAssessmentList(EntTlAssessmentdtl entTlAssessmentdtl) throws Exception
	{
		return this.entTlAssessmentmstDao.getAssessmentList(entTlAssessmentdtl);
	}
	
	public List<String[]> getMainGrid(CommonFilter commonFilter) throws Exception {
		return this.entTlAssessmentmstDao.getMainGrid(commonFilter);
	}
	
	public List<String[]> getAll(CommonFilter commonFilter) throws Exception {
		return this.entTlAssessmentmstDao.getAll(commonFilter);
	}

	public int selectCount(CommonFilter commonFilter) throws Exception {
		return this.entTlAssessmentmstDao.selectCount(commonFilter);
	}
	
	public List<String[]> getAssessmentGrid(CommonFilter commonFilter,String progId,String batchId,String evlType) throws Exception {
		return this.entTlAssessmentmstDao.getAssessmentGrid(commonFilter,progId,batchId,evlType);
	}
	
	public int selectAssessmentCount(CommonFilter commonFilter,String progId,String batchId) throws Exception {
		return this.entTlAssessmentmstDao.selectAssessmentCount(commonFilter,progId,batchId);
	}
	
	@Override
	public List<ComboBox> getProgramComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		String sql="";
		ComboFilter program = commonFilter.getProgm();	
//		String role=commonFilter.getType();
		String empId=commonFilter.getType();
		program.setIdField("PROG_KEYID");		
		program.setNameField("PROG_NAME");	
//		program.setCodeField("PROG_CODE");
		program.setTableName(TableNames.TBL_ENT_TL_PROGRAMMST);	
		if (CommonFunctions.isValidKeyId(empId)){
			sql=" AND PROG_KEYID IN (SELECT DISTINCT BACH_PROG_KEYID  FROM ENT_TL_BATCH_EMPLOYEE_LINK,ENT_TL_BATCHMST ";
			sql=sql+" WHERE BSTD_BACH_KEYID = BACH_KEYID AND BSTD_EMPM_KEYID ='"+empId+"') ";
			program.setCondSql(sql);
		}		
		return commonFilterDao.fillComboValues(program);
	}
	
	@Override
	public List<ComboBox> getBatchComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		String sql="";
		ComboFilter program = commonFilter.getBatch();
		String employee=commonFilter.getType();
		String progKey=commonFilter.getTC();
		program.setIdField("BACH_KEYID");		
		program.setNameField("BACH_NAME");	
//		program.setCodeField("BACH_CODE");		
		program.setTableName(TableNames.TBL_ENT_TL_BATCHMST);
		//CommonMessage.debugMsg("emp  " +employee);
		if (!CommonFunctions.isValidKeyId(employee) && CommonFunctions.isValidKeyId(progKey)){
			sql=" AND BACH_PROG_KEYID ='"+progKey+"'";
		}
		if (CommonFunctions.isValidKeyId(employee)){
			
				sql=" AND BACH_KEYID IN (SELECT DISTINCT BSTD_BACH_KEYID from ENT_TL_BATCH_EMPLOYEE_LINK ";
				sql=sql+" WHERE BSTD_EMPM_KEYID='"+employee+"') ";
			
			if(CommonFunctions.isValidKeyId(progKey)){
				sql=sql+" AND BACH_PROG_KEYID ='"+progKey+"'";
			}
			program.setCondSql(sql);
		}
		return commonFilterDao.fillComboValues(program);
	}
	@Override
	public List<ComboBox> getTopicComboList(CommonFilter commonFilter)throws Exception {
		// TODO Auto-generated method stub
		String condSql=null;
		String spoke=commonFilter.getType();
		ComboFilter program = commonFilter.getEmp();	
		program.setIdField("TOPI_KEYID");		
		program.setNameField("TOPI_NAME");	
//		program.setCodeField("TOPI_CODE");	
		if (CommonFunctions.isValidKeyId(spoke)){
			condSql=" AND TOPI_KEYID IN (SELECT TRAR_REFID FROM "+ TableNames.TBL_ENT_TL_TRAININGAREA ;
			condSql=condSql+" WHERE TRAR_PARENTID='"+ spoke+ "' ) ";
		}
		program.setCondSql(condSql);
		program.setTableName(TableNames.TBL_ENT_TL_TOPICMST);		
		return commonFilterDao.fillComboValues(program);
	}
	
	@Override
	public List<ComboBox> getTraingingAreaComboList(CommonFilter commonFilter)throws Exception {
		String condSql=null;
		// TODO Auto-generated method stub
		ComboFilter trArea = commonFilter.getTrainingtype();
		trArea.setIdField("TRAR_KEYID");		
		trArea.setNameField("TRAR_NAME");		
		trArea.setCondSql(" AND TRAR_REFTYPE<> 'SPK' AND TRAR_REFTYPE<>'TOP' ");			
		trArea.setTableName(TableNames.TBL_ENT_TL_TRAININGAREA);		
		return commonFilterDao.fillComboValues(trArea);		
	}
	
	@Override
	public String getEvaluationNo(EntTlAssessmentmst newEntTlAssessmentmst)throws Exception {
		return this.entTlAssessmentmstDao.getEvaluationNo(newEntTlAssessmentmst);
	}
	public EntTlAssessmentmst getEvaluationLatest(EntTlAssessmentmst newEntTlAssessmentmst)
		throws Exception {
		return this.entTlAssessmentmstDao.getEvaluationLatest(newEntTlAssessmentmst);
	}
	@Override
	public EntTlAssessmentdtl getAssessmentDetails(EntTlAssessmentdtl entTlAssessmentdtl)throws Exception {
		return this.entTlAssessmentmstDao.getAssessmentDetails(entTlAssessmentdtl);
	}
	@Override
	public List<String[]> getCheckList(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception{
		return this.entTlAssessmentmstDao.getCheckList(entTlAssessmentmst,entTlAssessmentdtl);
	}
	@Override
	public List<String[]> getAssessmentDetailsgridData(EntTlAssessmentmst EntTlAssessmentmst)throws Exception {
		// TODO Auto-generated method stub
		List<String[]> dateList=new ArrayList<String[]>();
		dateList=this.entTlAssessmentmstDao.getAssessmentDetailsgridData(EntTlAssessmentmst);	
		return dateList;
	}
	@Override
	public List<String[]> getTrnAreaRoleEmpView(EntTlAssessmentmst entTlAssessmentmst)throws Exception {
		// TODO Auto-generated method stub
		List<String[]> dateList=new ArrayList<String[]>();
		dateList=this.entTlAssessmentmstDao.getTrnAreaRoleEmpView(entTlAssessmentmst);	
		return dateList;
	}
	@Override
	public Workbook getTrnAreaRoleEmpexcel(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.getTrnAreaRoleEmpexcel(entTlAssessmentmst,commonFilter,colmodel,rptFormat);
	}
	@Override
	public Workbook getbatchProgexcel(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.getbatchProgexcel(commonFilter,colmodel,rptFormat);
	}
	@Override
	public Workbook getAssesmentListexcel(EntTlAssessmentmst entTlAssessmentmst,CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.getAssesmentListexcel(entTlAssessmentmst,commonFilter,colmodel,rptFormat);
	}
	@Override
	public String selectTrainingAreaPath(EntTlAssessmentmst entTlAssessmentmst)throws Exception{
		return this.entTlAssessmentmstDao.selectTrainingAreaPath(entTlAssessmentmst);
	}
	@Override
	public String getSkillRating(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception{
		return this.entTlAssessmentmstDao.getSkillRating(entTlAssessmentmst,entTlAssessmentdtl);
	}
	@Override
	public String getPreviousRating(EntTlAssessmentmst entTlAssessmentmst,EntTlAssessmentdtl entTlAssessmentdtl)throws Exception{
		return this.entTlAssessmentmstDao.getPreviousRating(entTlAssessmentmst,entTlAssessmentdtl);
	}	
	private EntTlAssessmentmst fillValues(EntTlAssessmentmst newEntTlAssessmentmst,EntTlAssessmentmst oldEntTlAssessmentmst,EntTlAssessmentmstBean entTlAssessmentmstBean) throws BusinessApplicationExceptions 
	{
			CommonMessage.debugMsg("Inside fill Values");
			newEntTlAssessmentmst.setAsmmActive("Y");
			String dateTime = CommonFunctions.dateTimeNow();		
			if(newEntTlAssessmentmst.getAsmmKeyid() == null ){	
				newEntTlAssessmentmst.setAsmmCreatedon(dateTime);
				if(newEntTlAssessmentmst.getAsmmEvaluationDate() == null)
					newEntTlAssessmentmst.setAsmmEvaluationDate(dateTime);
			}					
			else{	
				newEntTlAssessmentmst.setAsmmCreatedon(oldEntTlAssessmentmst.getAsmmCreatedon());
				if(newEntTlAssessmentmst.getAsmmEvaluationDate() == null)
					newEntTlAssessmentmst.setAsmmEvaluationDate(oldEntTlAssessmentmst.getAsmmEvaluationDate());
				CommonMessage.debugMsg("oldEntTlAssessmentmst.getAsmmIsLocked():"+oldEntTlAssessmentmst.getAsmmIsLocked());
				newEntTlAssessmentmst.setAsmmIsLocked(oldEntTlAssessmentmst.getAsmmIsLocked());		
			}
			newEntTlAssessmentmst.setAsmmModifiedon(dateTime);
			
			if(newEntTlAssessmentmst.getAsmmEvaluationDesc() == null)
				newEntTlAssessmentmst.setAsmmEvaluationDesc("{}");
			
			//if(newEntTlAssessmentmst.getAsmmEvaluationNo() == null)
				//newEntTlAssessmentmst.setAsmmEvaluationNo("0");
			
			//if(newEntTlAssessmentmst.getAsmmEvaluationType() == null)
			//newEntTlAssessmentmst.setAsmmEvaluationType("POS");
			
			//if(newEntTlAssessmentmst.getAsmmTrarKeyid() == null)
				//newEntTlAssessmentmst.setAsmmTrarKeyid("{}");
			
			//if(newEntTlAssessmentmst.getAsmmRoleKeyid() == null)
				//newEntTlAssessmentmst.setAsmmRoleKeyid("{}");
			
			//if(newEntTlAssessmentmst.getAsmmEmpmKeyid() == null)
				//newEntTlAssessmentmst.setAsmmEmpmKeyid("{}");
			
			if(newEntTlAssessmentmst.getAsmmIsLocked() == null)
				newEntTlAssessmentmst.setAsmmIsLocked("N");		
			
			if(newEntTlAssessmentmst.getAsmmFaculty() == null)
				newEntTlAssessmentmst.setAsmmFaculty("-");
			
			if(newEntTlAssessmentmst.getAsmmRemarks() == null)
				newEntTlAssessmentmst.setAsmmRemarks("-");
			
			if(newEntTlAssessmentmst.getAsmmFactId() == null)
				newEntTlAssessmentmst.setAsmmFactId("-");
			
			/*if(newEntTlAssessmentmst.getAsmm== null)
				newEntTlAssessmentmst.setAsmmTempfield4("-");
			*/
			//if(newEntTlAssessmentmst.getAsmmTempfield5() == null)
				//newEntTlAssessmentmst.setAsmmTempfield5("-");	
			
			if(newEntTlAssessmentmst.getAsmmTempfield6() == null)
				newEntTlAssessmentmst.setAsmmTempfield6("-");	
			
			if(newEntTlAssessmentmst.getAsmmTempfield7() == null)
				newEntTlAssessmentmst.setAsmmTempfield7("-");	
			
			if(newEntTlAssessmentmst.getAsmmTempfield8() == null)
				newEntTlAssessmentmst.setAsmmTempfield8("-");	
			
			if(newEntTlAssessmentmst.getAsmmTempfield9() == null)
				newEntTlAssessmentmst.setAsmmTempfield9("-");	
			
			if(newEntTlAssessmentmst.getAsmmTempfield10() == null)
				newEntTlAssessmentmst.setAsmmTempfield10("-");
			
			newEntTlAssessmentmst.setEntTlAssessmentdtl(detailFillValues(newEntTlAssessmentmst,oldEntTlAssessmentmst,entTlAssessmentmstBean));
			
			return newEntTlAssessmentmst;
	}
	
	private List<EntTlAssessmentdtl> detailFillValues(EntTlAssessmentmst newEntTlAssessmentmst,EntTlAssessmentmst oldEntTlAssessmentmst,
			EntTlAssessmentmstBean entTlAssessmentmstBean) 
	{
			// TODO Auto-generated method stub
			String dateTime = CommonFunctions.dateTimeNow();	
			List<EntTlAssessmentdtl> newEntTlAssessmentdtl = new ArrayList<EntTlAssessmentdtl>();
			newEntTlAssessmentdtl=newEntTlAssessmentmst.getEntTlAssessmentdtl();		
			List<EntTlAssessmentdtl> newEntTlAssessmentdtlList = new ArrayList<EntTlAssessmentdtl>();
			
			if (newEntTlAssessmentmst.getEntTlAssessmentdtl() != null
					&& newEntTlAssessmentmst.getEntTlAssessmentdtl().size() > 0) 
			{
				for( EntTlAssessmentdtl entTlAssessmentdtl :newEntTlAssessmentdtl)
				{	
					entTlAssessmentdtl.setAsmdCreatedby(newEntTlAssessmentmst.getAsmmCreatedby());
					entTlAssessmentdtl.setAsmdActive("Y");
					entTlAssessmentdtl.setAsmdModifiedon(dateTime);
					if(entTlAssessmentdtl.getAsmdKeyid() == null ){			
						entTlAssessmentdtl.setAsmdCreatedon(dateTime);
					}
					else{
						entTlAssessmentdtl.setAsmdCreatedon(newEntTlAssessmentmst.getAsmmCreatedon());
					}
					//if(entTlAssessmentdtl.getAsmdSpokKeyid()== null)
					//	entTlAssessmentdtl.setAsmdSpokKeyid("{}");
						
					//if(entTlAssessmentdtl.getAsmdTopiKeyid()== null)
					//	entTlAssessmentdtl.setAsmdTopiKeyid("{}");
					
					if(entTlAssessmentdtl.getAsmdCutoff()== null)
						entTlAssessmentdtl.setAsmdCutoff("0");
					
					if(entTlAssessmentdtl.getAsmdScore()== null)
						entTlAssessmentdtl.setAsmdScore("0");
					
					//if(entTlAssessmentdtl.getAsmdResult()== null)
						//entTlAssessmentdtl.setAsmdResult("F");
					
					if(entTlAssessmentdtl.getAsmdProgKeyid()== null)
					entTlAssessmentdtl.setAsmdProgKeyid("{}");
					
					if(entTlAssessmentdtl.getAsmdBachKeyid()== null)
						entTlAssessmentdtl.setAsmdBachKeyid("{}");
					
					if(entTlAssessmentdtl.getAsmdCurrentRate()== null)
						entTlAssessmentdtl.setAsmdCurrentRate("{}");
					
					if(entTlAssessmentdtl.getAsmdPreviousRate()== null)
						entTlAssessmentdtl.setAsmdPreviousRate("{}");
					
					if(entTlAssessmentdtl.getAsmdFaculty() == null)
						entTlAssessmentdtl.setAsmdFaculty("-");
					
					if(entTlAssessmentdtl.getAsmdType() == null)
						entTlAssessmentdtl.setAsmdType("-");
					
					if(entTlAssessmentdtl.getAsmdTempfield3() == null)
						entTlAssessmentdtl.setAsmdTempfield3("-");
					
					if(entTlAssessmentdtl.getAsmdTempfield4() == null)
						entTlAssessmentdtl.setAsmdTempfield4("-");
					
					if(entTlAssessmentdtl.getAsmdTempfield5() == null)
						entTlAssessmentdtl.setAsmdTempfield5("-");
					
					if(entTlAssessmentdtl.getAsmdTempfield6() == null)
						entTlAssessmentdtl.setAsmdTempfield6("-");
					
					if(entTlAssessmentdtl.getAsmdTempfield7() == null)
						entTlAssessmentdtl.setAsmdTempfield7("-");
					
					if(entTlAssessmentdtl.getAsmdTempfield8() == null)
						entTlAssessmentdtl.setAsmdTempfield8("-");
					
					if(entTlAssessmentdtl.getAsmdTempfield9() == null)
						entTlAssessmentdtl.setAsmdTempfield9("-");
					
					if(entTlAssessmentdtl.getAsmdTempfield10() == null)
						entTlAssessmentdtl.setAsmdTempfield10("-");
					
					entTlAssessmentdtl.setEntTlAssessmentChecklist(fillCheckListValues(entTlAssessmentdtl));
						
					newEntTlAssessmentdtlList.add(entTlAssessmentdtl);	
				}
			}
		return newEntTlAssessmentdtlList;
	}
	private List<EntTlAssessmentChecklist> fillCheckListValues(EntTlAssessmentdtl newEntTlAssessmentdtl)
	{
			// TODO Auto-generated method stub
			String dateTime = CommonFunctions.dateTimeNow();	
			List<EntTlAssessmentChecklist> newEntTlAssessmentChecklist = new ArrayList<EntTlAssessmentChecklist>();
			newEntTlAssessmentChecklist=newEntTlAssessmentdtl.getEntTlAssessmentChecklist();		
			List<EntTlAssessmentChecklist> newEntTlAssessmentChecklistList = new ArrayList<EntTlAssessmentChecklist>();
			
			if (newEntTlAssessmentdtl.getEntTlAssessmentChecklist() != null
					&& newEntTlAssessmentdtl.getEntTlAssessmentChecklist().size() > 0) 
			{
				for( EntTlAssessmentChecklist entTlAssessmentChecklist :newEntTlAssessmentChecklist)
				{	
					entTlAssessmentChecklist.setAsclCreatedby(newEntTlAssessmentdtl.getAsmdCreatedby());
					
					entTlAssessmentChecklist.setAsclModifiedon(dateTime);
					if(entTlAssessmentChecklist.getAsclKeyid() == null )			
						entTlAssessmentChecklist.setAsclCreatedon(dateTime);
					else
						entTlAssessmentChecklist.setAsclCreatedon(newEntTlAssessmentdtl.getAsmdCreatedon());
					
					if(entTlAssessmentChecklist.getAsclActive()== null)
						entTlAssessmentChecklist.setAsclActive("Y");
					
					if(entTlAssessmentChecklist.getAsclTopiKeyid()== null)
						entTlAssessmentChecklist.setAsclTopiKeyid("{}");
						
					if(entTlAssessmentChecklist.getAsclChekKeyid()== null)
						entTlAssessmentChecklist.setAsclChekKeyid("{}");
					
					if(entTlAssessmentChecklist.getAsclStatus()== null)
						entTlAssessmentChecklist.setAsclStatus("P");
					
					if(entTlAssessmentChecklist.getAsclTempfield1() == null)
						entTlAssessmentChecklist.setAsclTempfield1("-");
					
					if(entTlAssessmentChecklist.getAsclTempfield2() == null)
						entTlAssessmentChecklist.setAsclTempfield2("-");
					
					if(entTlAssessmentChecklist.getAsclTempfield3() == null)
						entTlAssessmentChecklist.setAsclTempfield3("-");
					
					if(entTlAssessmentChecklist.getAsclTempfield4() == null)
						entTlAssessmentChecklist.setAsclTempfield4("-");
					
					if(entTlAssessmentChecklist.getAsclTempfield5() == null)
						entTlAssessmentChecklist.setAsclTempfield5("-");
					
					
						
					newEntTlAssessmentChecklistList.add(entTlAssessmentChecklist);	
				}
			}
		return newEntTlAssessmentChecklistList;
	}

	@Override
	public String getMaxEvlDate(EntTlAssessmentmst entTlAssessmentmst) {
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.getMaxEvlDate(entTlAssessmentmst);
	}
	
	@Override
	public String getMinEvlDate(EntTlAssessmentmst entTlAssessmentmst) {
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.getMinEvlDate(entTlAssessmentmst);
	}

	@Override
	public Boolean preEvlExists(EntTlAssessmentmst entTlAssessmentmst) {
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.preEvlExists(entTlAssessmentmst);
	}

	@Override
	public String getCutOff(String assEmpKeyId, String topicId,
			String assRoleKeyId, String evalType,String mode, String trarkey, String result, String asmdKeyId) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.getCutOff( assEmpKeyId,topicId,assRoleKeyId,evalType,mode,trarkey,  result,  asmdKeyId);
	}

	@Override
	public String getpreExist(String assEmpKeyId, String topicId, String evalType)
			throws Exception {
		// TODO Auto-generated method stub
		return  this.entTlAssessmentmstDao.getpreExist( assEmpKeyId,topicId,evalType);
	}

	@Override
	public String getProgid(String batchId) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.getProgid( batchId);
	}

	@Override
	public List<String[]> getMultiSkillfillgriddata(String flid) throws Exception {
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.getMultiSkillfillgriddata(flid);
	}

	@Override
	public List<String[]> getMultiSkillAssessmentfillgriddata()
			throws Exception {
		// TODO Auto-generated method stub
		return this.entTlAssessmentmstDao.getMultiSkillAssessmentfillgriddata();
	}

	@Override
	public List<String[]> getEmployeerole(CommonFilter commonFilter)
			throws Exception {
		return this.entTlAssessmentmstDao.getEmployeerole(commonFilter);
	}

	@Override
	public EntTlMultiskillempmap create(List<EntTlMultiskillempmap> lstEntTlMultiskillempmap,
			EntTlMultiskillempmap existEntTlMultiskillempmap,
			MultiBean multiBean) throws Exception
	{		
	  
      //validations.validate( newEntTlMultiskillempmap,"StdWork","create");
      fillValues(lstEntTlMultiskillempmap,existEntTlMultiskillempmap,multiBean);//
		 //  lstEntTlMultiskillempmap=fillValues(lstEntTlMultiskillempmap,existEntTlMultiskillempmap,multiBean);		
	  return entTlAssessmentmstDao.create(lstEntTlMultiskillempmap) ;
	
}
		


	private List<EntTlMultiskillempmap> fillValues(List<EntTlMultiskillempmap> lstEntTlMultiskillempmap,EntTlMultiskillempmap existEntTlMultiskillempmap,
			MultiBean multiBean) {
	
		 List<EntTlMultiskillempmap> lstEntTlMultiskillempma= new ArrayList<EntTlMultiskillempmap>();
		for (EntTlMultiskillempmap Multiskillempmap : lstEntTlMultiskillempmap)
		{
			
			 String dateTime = CommonFunctions.dateTimeNow();
			 //CommonMessage.debugMsg(" fillvalues of skillemp111      "+Multiskillempmap.getMuseEmployeeid());
			 CommonMessage.debugMsg(" fillvalues of skillemp111      "+Multiskillempmap.getMuseEmployeeid());
			 CommonMessage.debugMsg(" fillvalues of skillemp222      "+Multiskillempmap.getMuseUnipositionid());
			Multiskillempmap.setMuseCreatedon(dateTime);
			Multiskillempmap.setMuseModifiedon(dateTime);

			Multiskillempmap.setMuseActive("Y");
			if (!UIUtils.isValidKeyId(Multiskillempmap.getMuseCreatedby()))
				Multiskillempmap.setMuseCreatedby("{}");
			else
				Multiskillempmap.setMuseCreatedby(Multiskillempmap.getMuseCreatedby());

			if (!UIUtils.isValidKeyId(Multiskillempmap.getMuseKeyid()))
				Multiskillempmap.setMuseKeyid("{}");
			
			if (!UIUtils.isValidKeyId(Multiskillempmap.getMuseEmployeeid()))
				Multiskillempmap.setMuseEmployeeid("{}");
			
			if (!UIUtils.isValidKeyId(Multiskillempmap.getMuseUnipositionid()))
				Multiskillempmap.setMuseUnipositionid("{}");
			
			if (!UIUtils.isValidKeyId(Multiskillempmap.getMuseFlid()))
				Multiskillempmap.setMuseFlid("{}");
			
		
			if (!UIUtils.isValidKeyId(Multiskillempmap.getMuseTempfield1()))
				Multiskillempmap.setMuseTempfield1("-");
		
			
			if (!UIUtils.isValidKeyId(Multiskillempmap.getMuseTempfield2()))
				Multiskillempmap.setMuseTempfield2("-");
			
			if (!UIUtils.isValidKeyId(Multiskillempmap.getMuseTempfield3()))
				Multiskillempmap.setMuseTempfield3("-");
			
			if (!UIUtils.isValidKeyId(Multiskillempmap.getMuseTempfield4()))
				Multiskillempmap.setMuseTempfield4("-");
			
			if (!UIUtils.isValidKeyId(Multiskillempmap.getMuseTempfield5()))
				Multiskillempmap.setMuseTempfield5("-");
			
			if (!UIUtils.isValidKeyId(Multiskillempmap.getMuseCreatedby()))
				Multiskillempmap.setMuseCreatedby("{}");
			
			
			lstEntTlMultiskillempma.add(Multiskillempmap);
		}
		return lstEntTlMultiskillempma;
		
				
	}

	@Override
	public EntTlMultiskillempmap update(
			List<EntTlMultiskillempmap> lstEntTlMultiskillempmap,
			EntTlMultiskillempmap existEntTlMultiskillempmap,
			MultiBean multiBean) throws Exception 
		{		
			   try
			{
		      //validations.validate( newEntTlMultiskillempmap,"StdWork","create");
		      fillValues(lstEntTlMultiskillempmap,existEntTlMultiskillempmap,multiBean);//
				 //  lstEntTlMultiskillempmap=fillValues(lstEntTlMultiskillempmap,existEntTlMultiskillempmap,multiBean);		
		       return   entTlAssessmentmstDao.create(lstEntTlMultiskillempmap) ;
			}
			catch (Exception e){
				CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
				throw new ValidationExceptions(e.getMessage());
			}
			
		}

	@Override
	public EntTlMultiskillempmap deleteMultiSkill(
			EntTlMultiskillempmap newEntTlMultiskillempmap,
			EntTlMultiskillempmap existEntTlMultiskillempmap,
			MultiBean multiBean) throws Exception {
		return entTlAssessmentmstDao.deleteMultiSkill(newEntTlMultiskillempmap);
	}

	@Override
	public List<String[]> getemployeeMaster(CommonFilter commonFilter)
			throws Exception {
		return this.entTlAssessmentmstDao.getemployeeMaster(commonFilter);
	}

	@Override
	public EntTlMultiskillempmap selectmaster(String flid) throws Exception {
		return this.entTlAssessmentmstDao.selectmaster(flid);
	}

	@Override
	public List<String[]> getemployeeDate(CommonFilter commonFilter )
			throws Exception {
		return this.entTlAssessmentmstDao.getemployeeDate(commonFilter);
	}

	@Override
	public Workbook getmultiSkillExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return entTlAssessmentmstDao.getmultiSkillExcel(colmodel,format,commonFilter);
	}

	@Override
	public EntTlMultiskilldialymap create(List<EntTlMultiskilldialymap> lstEntTlMultiskilldialymap,EntTlMultiskilldialymap existEntTlMultiskilldialymap,
			MultiBean multiBean) throws Exception {
		
		{		
			  
		      //validations.validate( lstEntTlMultiskilldialymap,"MultiSkillDate","create");
		      fillValues(lstEntTlMultiskilldialymap,existEntTlMultiskilldialymap,multiBean);//
				 //  lstEntTlMultiskillempmap=fillValues(lstEntTlMultiskillempmap,existEntTlMultiskillempmap,multiBean);		
			  return entSkillAnalysisDao.create(lstEntTlMultiskilldialymap) ;
			
		}
	}

	private List<EntTlMultiskilldialymap> fillValues(
			List<EntTlMultiskilldialymap> lstEntTlMultiskilldialymap,
			EntTlMultiskilldialymap existEntTlMultiskilldialymap,
			MultiBean multiBean) {
		
		
		   List<EntTlMultiskilldialymap> lstEntTlMultiskillDate= new ArrayList<EntTlMultiskilldialymap>();
			for (EntTlMultiskilldialymap MultiskillempDate : lstEntTlMultiskilldialymap)
			{
				
				 String dateTime = CommonFunctions.dateTimeNow();
				 //CommonMessage.debugMsg(" fillvalues of skillemp111      "+Multiskillempmap.getMuseEmployeeid());
				 CommonMessage.debugMsg(" fillvalues of skillemp111      "+MultiskillempDate.getMudmDate());
				 CommonMessage.debugMsg(" fillvalues of skillemp222      "+MultiskillempDate.getMudmUnipositionid());
				 MultiskillempDate.setMudmCreatedon(dateTime);
				 MultiskillempDate.setMudmModifiedon(dateTime);

				 MultiskillempDate.setMudmActive("Y");
				if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmCreatedby()))
					MultiskillempDate.setMudmCreatedby("{}");
				else
					MultiskillempDate.setMudmCreatedby(MultiskillempDate.getMudmCreatedby());

		
				/*if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmDate()))
					MultiskillempDate.setMudmDate(dateTime);*/
				
				
				if( MultiskillempDate.getMudmDate() == null )
					MultiskillempDate.setMudmDate(dateTime);
				
				
				if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmEmployeeid()))
					MultiskillempDate.setMudmEmployeeid("{}");
				
				if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmUnipositionid()))
					MultiskillempDate.setMudmUnipositionid("{}");
				
				if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmFlid()))
					MultiskillempDate.setMudmFlid("{}");
				
			
				if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmTempfield1()))
					MultiskillempDate.setMudmTempfield1("-");
			
				
				if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmTempfield2()))
					MultiskillempDate.setMudmTempfield2("-");
				
				if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmTempfield3()))
					MultiskillempDate.setMudmTempfield3("-");
				
				if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmTempfield4()))
					MultiskillempDate.setMudmTempfield4("-");
				
				if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmTempfield5()))
					MultiskillempDate.setMudmTempfield5("-");
				
				if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmCreatedby()))
					MultiskillempDate.setMudmCreatedby("{}");
				
				//if (!UIUtils.isValidKeyId(MultiskillempDate.getMudmModifiedon()))
					//MultiskillempDate.setMudmModifiedon("{}");
				
				
				lstEntTlMultiskillDate.add(MultiskillempDate);
			}
			return  lstEntTlMultiskillDate;
			
					
		}

	@Override
	public List<String[]> getemployees(CommonFilter commonFilter)
			throws Exception {
		return this.entTlAssessmentmstDao.getemployees(commonFilter);
	}

	@Override
	public EntTlMultiskilldialymap selectFlid(String flid,String date) throws Exception {
		
		return entTlAssessmentmstDao.selectFlid(flid,date);	
	}

	@Override
	public Workbook getmultiSkillDate(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return entTlAssessmentmstDao.getmultiSkillDate(colmodel,format,commonFilter);	
	}

	@Override
	public List<String[]> getemployeeReport(CommonFilter commonFilter)
			throws Exception {
		return this.entTlAssessmentmstDao.getemployeeReport(commonFilter);
	}

	@Override
	public Workbook getmultiSkillReport(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return entTlAssessmentmstDao.getmultiSkillReport(colmodel,format,commonFilter);	
		
	}
}

				


		