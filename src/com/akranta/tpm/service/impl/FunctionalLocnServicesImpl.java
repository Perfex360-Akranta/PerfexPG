package com.akranta.tpm.service.impl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GenTlPbumstBean;
import com.akranta.tpm.bean.GenTlSbumstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.FunctionalLocnDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.FunctionalLocnDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.FactoryLayout;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlLayoutfieldimg;
import com.akranta.tpm.model.GenTlPbumst;
import com.akranta.tpm.model.GenTlSbumst;
import com.akranta.tpm.service.FunctionalLocnServices;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;



public class FunctionalLocnServicesImpl implements FunctionalLocnServices{

	private FunctionalLocnDao functionalLocnDao; 
	private CommonFilterDao commonFilterDao ; 
	private Validations validations ;
	public FunctionalLocnServicesImpl(DBActionTemplate dbActionTemplate) 
	{
		functionalLocnDao = new FunctionalLocnDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	@Override
	public List<FunctionalLocn> getFunctionalLocnValues(String userId) throws Exception {

		return this.functionalLocnDao.getFunctionalLocnValues(userId);
	}

	public List<FunctionalLocn> getAllLocation(FunctionalLocn functionalLocn) throws Exception {

		return this.functionalLocnDao.getAllLocation(functionalLocn);
	}

	public List<FunctionalLocn> getAllfnLocation(FunctionalLocn functionalLocn) throws Exception {

		return this.functionalLocnDao.getAllfnLocation(functionalLocn);
	}
	public List<String[]> getGridDetail()throws Exception {
		return functionalLocnDao.getGridDetail();
	}
	
	public FunctionalLocn create(FunctionalLocn functionalLocn,List<String> locnValues)throws Exception
	{
		return this.functionalLocnDao.create(functionalLocn,locnValues);
	}
	@Override
	public GenTlSbumst sbucreate(GenTlSbumst newGenTlSbumst,
			GenTlSbumst exitGenTlSbumst, GenTlSbumstBean genTlSbumstBean)
			throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" Inside Service Impl ::1");
		String validationsFor;
		
		validationsFor = "create";
		
		validations.validate(newGenTlSbumst,"GentlSbumst",validationsFor);//GentlPbumst.xml
		fillValues(newGenTlSbumst,exitGenTlSbumst,genTlSbumstBean);
		return this.functionalLocnDao.sbucreate(newGenTlSbumst,exitGenTlSbumst,genTlSbumstBean);
	}
	@Override
	public GenTlSbumst sbudelete(GenTlSbumst newGenTlSbumst) throws Exception {
		// TODO Auto-generated method stub
		return this.functionalLocnDao.sbudelete(newGenTlSbumst);
	}
	@Override
	public GenTlPbumst pbudelete(GenTlPbumst newGenTlPbumst) throws Exception {
		// TODO Auto-generated method stub
		return this.functionalLocnDao.pbudelete(newGenTlPbumst);
	}
	@Override
	public GenTlSbumst sbuupdate(GenTlSbumst newGenTlSbumst,
			GenTlSbumst exitGenTlSbumst, GenTlSbumstBean genTlSbumstBean)
			throws Exception {
		// TODO Auto-generated method stub

		CommonMessage.debugMsg("updatee" +newGenTlSbumst);
		//validations.validate(newGenTlSbumst,"cellCreation","update");//com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		fillValues(newGenTlSbumst,exitGenTlSbumst,genTlSbumstBean);
	
	return this.functionalLocnDao.sbuupdate(newGenTlSbumst,exitGenTlSbumst,genTlSbumstBean);
	}
	@Override
	public GenTlPbumst pbucreate(GenTlPbumst newGenTlPbumst,
			GenTlPbumst exitGenTlPbumst, GenTlPbumstBean genTlPbumstBean)
			throws Exception {
		// TODO Auto-generated method stub
        String validationsFor;
		
		validationsFor = "create";
		
		validations.validate(newGenTlPbumst,"GentlPbumst",validationsFor);//GentlPbumst.xml
		fillValues(newGenTlPbumst,exitGenTlPbumst,genTlPbumstBean);
		return this.functionalLocnDao.pbucreate(newGenTlPbumst,exitGenTlPbumst,genTlPbumstBean);
	}
	
	@Override
	public GenTlPbumst pbuupdate(GenTlPbumst newGenTlPbumst,
			GenTlPbumst exitGenTlPbumst, GenTlPbumstBean genTlPbumstBean)
			throws Exception {
		// TODO Auto-generated method stub
		fillValues(newGenTlPbumst,exitGenTlPbumst,genTlPbumstBean);
		return this.functionalLocnDao.pbuupdate(newGenTlPbumst,exitGenTlPbumst,genTlPbumstBean);
	}
	private GenTlPbumst fillValues(GenTlPbumst newGenTlPbumst,GenTlPbumst exitGenTlPbumst, GenTlPbumstBean genTlPbumstBean) {
		// TODO Auto-generated method stub
		
         String dateTime = CommonFunctions.dateTimeNow();
		
		 if(newGenTlPbumst.getPbutKeyid() == null )
			 newGenTlPbumst.setPbutCreatedon(dateTime);
		 else
			 newGenTlPbumst.setPbutCreatedon(dateTime);
		
		
		 newGenTlPbumst.setPbutModifiedon(dateTime);
		   
		   if( newGenTlPbumst.getPbutFlid() == null )
			   newGenTlPbumst.setPbutFlid("{}");
			
			if( newGenTlPbumst.getPbutSbuid() == null )
				newGenTlPbumst.setPbutSbuid("{}");
		
			if( newGenTlPbumst.getPbutFactoryid() == null )
				newGenTlPbumst.setPbutFactoryid("{}");
			
			/*if( newGenTlPbumst.getSbutFlid()== null )
				newGenTlPbumst.setSbutFlid("{}");
				*/
			
			if( newGenTlPbumst.getPbutName() == null )
				newGenTlPbumst.setPbutName("{}");
		
			if( newGenTlPbumst.getPbutCode() == null )
				newGenTlPbumst.setPbutCode("{}");
			
			if( newGenTlPbumst.getPbutDescription() == null )
				newGenTlPbumst.setPbutDescription("{}");
			
			if( newGenTlPbumst.getPbutTempfield1() == null )
				newGenTlPbumst.setPbutTempfield1("-");
			
			if( newGenTlPbumst.getPbutTempfield2() == null )
				newGenTlPbumst.setPbutTempfield2("-");
				
			if( newGenTlPbumst.getPbutTempfield3()== null )
				newGenTlPbumst.setPbutTempfield3("-");	
			
			if( newGenTlPbumst.getPbutTempfield4()== null )	
				newGenTlPbumst.setPbutTempfield4("-");
			
			if( newGenTlPbumst.getPbutActive()== null )
				newGenTlPbumst.setPbutActive("Y");
		
			fillFunctionLoc(newGenTlPbumst);
			
			return newGenTlPbumst;
		
	
		
	}
	private GenTlFunctionallocn fillFunctionLoc(GenTlPbumst newGenTlPbumst) {
		// TODO Auto-generated method stub
		
        GenTlFunctionallocn  newGenTlFunctionallocn = new GenTlFunctionallocn();
		
		newGenTlFunctionallocn.setFnlnOriginalid(newGenTlPbumst.getPbutKeyid());
		
		//newGenTlFunctionallocn.setFnlnElementid(newGenTlPbumst.getSbutCompanyid()+"-"+newGenTlPbumst.getSbutLocationid()+"-"+newGenTlPbumst.getPbutKeyid());
		
		newGenTlFunctionallocn.setFnlnElementid(newGenTlPbumst.getCompany()+"-"+newGenTlPbumst.getLocation()+"-"+newGenTlPbumst.getPbutSbuid()+"-"+newGenTlPbumst.getPbutKeyid());
		
        newGenTlFunctionallocn.setFnlnElementtype("PBU");
		
        CommonMessage.debugMsg("INSIDE THE SERVICE IMPL"+newGenTlPbumst.getLocation());
        //newGenTlFunctionallocn.setFnlnParentid(newGenTlPbumst.getSbutCompanyid()+"-"+newGenTlPbumst.getSbutLocationid());
       // getLocation  getCompany
        newGenTlFunctionallocn.setFnlnParentid(newGenTlPbumst.getCompany()+"-"+newGenTlPbumst.getLocation()+"-"+newGenTlPbumst.getPbutSbuid());//
		
        newGenTlFunctionallocn.setFnlnDescription(newGenTlPbumst.getPbutName());
		newGenTlFunctionallocn.setFnlnDisplaycode(newGenTlPbumst.getPbutCode());
		newGenTlFunctionallocn.setFnlnActive("Y");
		if(newGenTlFunctionallocn.getFnlnKeyid()==null)
		{
			if( newGenTlFunctionallocn.getFnlnKeyid() == null )
				newGenTlFunctionallocn.setFnlnKeyid("{}");
		}else
		{
			
		}
		newGenTlFunctionallocn.getFnlnKeyid();
		
		
		newGenTlPbumst.setGenTlFunctionallocn(newGenTlFunctionallocn);
		return newGenTlFunctionallocn;
		
	}
	private GenTlSbumst fillValues(GenTlSbumst newGenTlSbumst,GenTlSbumst exitGenTlSbumst, GenTlSbumstBean genTlSbumstBean) {
		// TODO Auto-generated method stub
		
		String dateTime = CommonFunctions.dateTimeNow();
		
		if(newGenTlSbumst.getSbutKeyid() == null )
			newGenTlSbumst.setSbutCreatedon(dateTime);
		else
			newGenTlSbumst.setSbutCreatedon(dateTime);
		
		
		   newGenTlSbumst.setSbutModifiedon(dateTime);
		   
		   if( newGenTlSbumst.getSbutCompanyid() == null )
			   newGenTlSbumst.setSbutCompanyid("{}");
			
			if( newGenTlSbumst.getSbutLocationid() == null )
				newGenTlSbumst.setSbutLocationid("{}");
		
			if( newGenTlSbumst.getSbutFactoryid() == null )
				newGenTlSbumst.setSbutFactoryid("{}");
			
			if( newGenTlSbumst.getSbutFlid()== null )
				newGenTlSbumst.setSbutFlid("{}");
			
			if( newGenTlSbumst.getSbutName() == null )
				newGenTlSbumst.setSbutName("{}");
		
			if( newGenTlSbumst.getSbutCode() == null )
				newGenTlSbumst.setSbutCode("{}");
			
			if( newGenTlSbumst.getSbutDescription() == null )
				newGenTlSbumst.setSbutDescription("{}");
			
			if( newGenTlSbumst.getSbutTempfield1() == null )
				newGenTlSbumst.setSbutTempfield1("-");
			
			if( newGenTlSbumst.getSbutTempfield2() == null )
				newGenTlSbumst.setSbutTempfield2("-");
				
			if( newGenTlSbumst.getSbutTempfield3()== null )
				newGenTlSbumst.setSbutTempfield3("-");	
			
			if( newGenTlSbumst.getSbutTempfield4()== null )	
				newGenTlSbumst.setSbutTempfield4("-");
			
			if( newGenTlSbumst.getSbutActive()== null )
				newGenTlSbumst.setSbutActive("Y");
		
			fillFunctionLoc(newGenTlSbumst);
			
			return newGenTlSbumst;
		
		
	}
	private GenTlFunctionallocn fillFunctionLoc(GenTlSbumst newGenTlSbumst) {
		// TODO Auto-generated method stub
		
		GenTlFunctionallocn  newGenTlFunctionallocn = new GenTlFunctionallocn();
		
		newGenTlFunctionallocn.setFnlnOriginalid(newGenTlSbumst.getSbutKeyid());
		newGenTlFunctionallocn.setFnlnElementid(newGenTlSbumst.getSbutCompanyid()+"-"+newGenTlSbumst.getSbutLocationid()+"-"+newGenTlSbumst.getSbutKeyid());
		
        newGenTlFunctionallocn.setFnlnElementtype("SBU");
		
        newGenTlFunctionallocn.setFnlnParentid(newGenTlSbumst.getSbutCompanyid()+"-"+newGenTlSbumst.getSbutLocationid());
		
        newGenTlFunctionallocn.setFnlnDescription(newGenTlSbumst.getSbutName());
		newGenTlFunctionallocn.setFnlnDisplaycode(newGenTlSbumst.getSbutCode());
		newGenTlFunctionallocn.setFnlnActive("Y");
		
		if(newGenTlFunctionallocn.getFnlnKeyid()==null)
		{
			if( newGenTlFunctionallocn.getFnlnKeyid() == null )
				newGenTlFunctionallocn.setFnlnKeyid("{}");
		}else
		{
			
		}
		newGenTlFunctionallocn.getFnlnKeyid();
		
		//newGenTlFunctionallocn.setFnlnKeyid(newGenTlSbumst.getSbutKeyid());
		
		newGenTlSbumst.setGenTlFunctionallocn(newGenTlFunctionallocn);
		return newGenTlFunctionallocn;
		
	}
	public GenTlLayoutfieldimg saveBlobImage(GenTlLayoutfieldimg genTlLayoutfieldimg)throws Exception
	{
		fillValues(genTlLayoutfieldimg);
		return this.functionalLocnDao.saveBlobImage(genTlLayoutfieldimg);
	}
	
	public List<String []> getParentElem(String elemId) throws Exception {

		return this.functionalLocnDao.getParentElem(elemId);
	}
	public List<String []> getChildElem(List<String> childElem,String formfield,String start,String end,String key,GridParams gridParams) throws Exception{
		return this.functionalLocnDao.getChildElem(childElem,formfield,start,end,key,gridParams);
	}
	public String getTotalCount(List<String> childElem,String formfield) throws Exception
	{
		return this.functionalLocnDao.getTotalCount(childElem,formfield);
	}
	public  List<String[]>  getSearchNode(String searchNode) throws Exception{
		return this.functionalLocnDao.getSearchNode(searchNode);
	}
	public String getNameForId(String currentSearchId)throws Exception
	{
		return this.functionalLocnDao.getNameForId(currentSearchId);
	}
	public FunctionalLocn cutEqp(FunctionalLocn functionalLocn,List<String> parentValues)	throws Exception
	{
		return this.functionalLocnDao.cutEqp(functionalLocn,parentValues);
	}
	public FunctionalLocn deleteNode(FunctionalLocn functionalLocn)throws Exception
	{
		return this.functionalLocnDao.deleteNode(functionalLocn);
	}
	public FunctionalLocn copyNode(FunctionalLocn functionalLocn,String eqpId)	throws Exception
	{
		return this.functionalLocnDao.copyNode(functionalLocn,eqpId);
	}
	public GenTlLayoutfieldimg select(String locnId) throws Exception
	{
		return this.functionalLocnDao.select(locnId);
	}
	public GenTlLayoutfieldimg getLayoutImg(GenTlLayoutfieldimg genTlLayoutfieldimg) throws Exception
	{
		return this.functionalLocnDao.getLayoutImg(genTlLayoutfieldimg);
	}
	public FunctionalLocn deleteImage(String nodeId)throws Exception
	{
		return this.functionalLocnDao.deleteImage(nodeId);
	}
	public List<String[]>  cutValidEqp(String nodeId)throws Exception
	{
		return this.functionalLocnDao.cutValidEqp(nodeId);
	}
	public List<String[]>  getBdforEqp(String nodeId)throws Exception
	{
		return this.functionalLocnDao.getBdforEqp(nodeId);
	}
	public FunctionalLocn deletMachine(FunctionalLocn functionalLocn)throws Exception
	{
		return this.functionalLocnDao.deletMachine(functionalLocn);
	}
	private GenTlLayoutfieldimg fillValues(GenTlLayoutfieldimg genTlLayoutfieldimg)
	{
		genTlLayoutfieldimg.setLyfiActive("Y");
		String dateTime = CommonFunctions.dateTimeNow();
		genTlLayoutfieldimg.setLyfiCreatedon(dateTime);
		genTlLayoutfieldimg.setLyfiModifiedon(dateTime);
		genTlLayoutfieldimg.setLyfiTempfield1("{}");
		genTlLayoutfieldimg.setLyfiTempfield2("{}");
		genTlLayoutfieldimg.setLyfiTempfield3("{}");
		long length =0;
		String fileName = genTlLayoutfieldimg.getLyfiFilename();
		if( CommonFunctions.isValidKeyId(fileName)){
			fileName =  fileName.substring(fileName.lastIndexOf("/")+1);
			genTlLayoutfieldimg.setLyfiFilename(fileName);
			fileName =genTlLayoutfieldimg.getLyfiBlobimage() + fileName;
			CommonMessage.debugMsg(" fileName " + fileName);
			genTlLayoutfieldimg.setLyfiBlobimage(fileName);
			
			if( CommonFunctions.isFileExists(fileName ) )
				length = new File(fileName).length();
			
			genTlLayoutfieldimg.setLyfiBloblength(Long.toString(length) );
			return genTlLayoutfieldimg;
		}
		return genTlLayoutfieldimg;
		
	}
	
	
	public List<ComboBox> getCompanyComboList(CommonFilter commonFilter)throws Exception {

		CommonMessage.debugMsg( "service impl");
		return commonFilterDao.getCompanyComboList(commonFilter);
	}


		
	public List<ComboBox> getAssemblyComboList(CommonFilter commonFilter)throws Exception {
		ComboFilter assembly = commonFilter.getAssembly();
		assembly.setIdField("ASSEMBLYID");
		assembly.setNameField("ASSEMBLYNAME");
		String machineId = (commonFilter.getMachine() != null ? commonFilter.getMachine().getId() : null);
		CommonMessage.debugMsg(" machine id " + machineId);
		if( UIUtils.isValidKeyId(machineId)  ){
			assembly.setCondSql(" AND MACHINEID = '" + machineId + "'");
		}
		assembly.setTableName(TableNames.TBL_GEN_VW_MCHASMLINK);
		assembly.setCondSql("AND ASSEMBLYID IN(SELECT FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN)");
//		StringBuffer condSql = new StringBuffer();
		
		return commonFilterDao.fillComboValues(assembly);
		
	}
	
	public List<ComboBox> getSubassemblyComboList(CommonFilter commonFilter)	throws Exception {

		ComboFilter subAssembly = commonFilter.getSubassembly();
		subAssembly.setIdField("SBAM_KEYID");
		subAssembly.setCodeField("SBAM_CODE");
		subAssembly.setNameField("SBAM_NAME");
		subAssembly.setTableName(TableNames.TBL_GEN_TL_SUBASSEMBLYMST);
		subAssembly.setCondSql("AND SBAM_KEYID IN(SELECT FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN)");
		
		//StringBuffer condSql = new StringBuffer();
		
		return commonFilterDao.fillComboValues(subAssembly);
	}
	
	public List<ComboBox> getSpareComboList(CommonFilter commonFilter)	throws Exception {

		ComboFilter spare = commonFilter.getSpare();
		spare.setIdField("SPRM_KEYID");
		spare.setCodeField("SPRM_PARTNO");
		spare.setNameField("SPRM_PARTNAME");
		spare.setTableName(TableNames.TBL_GEN_TL_SPARESMST);
		spare.setCondSql("AND SPRM_KEYID IN(SELECT FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN)");
		
		//StringBuffer condSql = new StringBuffer();
		
		return commonFilterDao.fillComboValues(spare);
	}		
		
	public List<ComboBox> getLocationComboList(CommonFilter commonFilter)throws Exception {
		

		ComboFilter locn = commonFilter.getLocation();
		locn.setIdField("LOCN_KEYID");
		locn.setCodeField("LOCN_CODE");
		locn.setNameField("LOCN_NAME");
		locn.setTableName(TableNames.TBL_GEN_TL_LOCATIONMST);
		
		StringBuffer condSql = new StringBuffer();
		locn.setCondSql("AND LOCN_KEYID IN(SELECT FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN)");
		
		return commonFilterDao.fillComboValues(locn);
	}
	public List<ComboBox> getFindComboList(ComboFilter comboFilter,List<String> childElem,String formfield,String type,String code)throws Exception
	{
		String condSql = "";
		List<String> datas = getRelatedNames(type,code);
		comboFilter.setIdField(datas.get(0));
		comboFilter.setNameField(datas.get(1));
		CommonMessage.debugMsg(datas.get(2));
		comboFilter.setTableName(datas.get(2));
		if(childElem.size()>0)
		{
			condSql = "AND "+datas.get(0)+" NOT IN(";
			for( int i =0 ; i < childElem.size(); i++)
			{
				condSql += "'"+childElem.get(i)+"'";
				if(i != childElem.size()-1)
					condSql += ",";
			}
			condSql += ")";
			
		}
		if(datas.size()>3)
			condSql += datas.get(3);
		comboFilter.setCondSql(condSql); 
			
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public List<ComboBox> getSubCellComboList(CommonFilter commonFilter)throws Exception {
		

		ComboFilter sbcl = commonFilter.getLocation();
		sbcl.setIdField("SBCL_KEYID");
		sbcl.setCodeField("SBCL_CODE");
		sbcl.setNameField("SBCL_NAME");
		sbcl.setTableName(TableNames.TBL_GEN_TL_SUBCELLMST);
		
		StringBuffer condSql = new StringBuffer();
		sbcl.setCondSql("AND SBCL_KEYID IN(SELECT FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN)");
		
		return commonFilterDao.fillComboValues(sbcl);
	}
	
	public List<ComboBox> getMachineComboList(CommonFilter commonFilter)throws Exception {		
		
		StringBuffer condSql = new StringBuffer();
		condSql.append("AND SBCL_KEYID IN(SELECT FNLN_ORIGINALID FROM GEN_TL_FUNCTIONALLOCN)");
		
		return commonFilterDao.getMachineComboList(commonFilter);
	}
	
	public FactoryLayout getFactoryLayoutElements(FactoryLayout factoryLayout) throws Exception{
		return this.functionalLocnDao.getFactoryLayoutElements(factoryLayout);
	}
	
	public FactoryLayout getEmployeeFunctionalLocation(String employeeId, String roleId, String flid) throws NoDataFoundException, Exception{
		return this.functionalLocnDao.getEmployeeFunctionalLocation(employeeId,roleId,flid);
		
	}
	
	private List<String> getRelatedNames(String type,String code)
	{
		List<String> datas = new ArrayList<String>();
		if(type.equals("F"))
		{
			datas.add("Sect_Keyid");
			if(UIUtils.isValidKeyId(code))
				datas.add("Sect_Code");
			else
				datas.add("Sect_Name");
			
			datas.add(TableNames.TBL_GEN_TL_SECTIONMST);
			datas.add(" AND Sect_Active = 'Y'");
		}
		else if(type.equals("L"))
		{
			datas.add("Cell_Keyid");
			if(UIUtils.isValidKeyId(code))
				datas.add("Cell_Code");
			else
				datas.add("Cell_Name");
			
			datas.add(TableNames.TBL_GEN_TL_CELLMST);
			datas.add(" AND Cell_Active = 'Y'");
		}
		else if(type.equals("SEC"))
		{
			datas.add("Cell_Keyid");
			if(UIUtils.isValidKeyId(code))
				datas.add("Cell_Code");
			else
				datas.add("Cell_Name");
			
			datas.add(TableNames.TBL_GEN_TL_CELLMST);
			datas.add(" AND Cell_Active = 'Y'");
		}
		else if(type.equals("E"))
		{
			datas.add("Mchm_Keyid");
			if(UIUtils.isValidKeyId(code))
				datas.add("Mchm_Machineno");
			else
				datas.add("Mchm_Machinename");			
			datas.add(TableNames.TBL_GEN_TL_MACHINEMST);
			datas.add(" AND Mchm_Active = 'Y'");
		}
		else if(type.equals("A"))
		{
			datas.add("ASSm_Keyid");
			if(UIUtils.isValidKeyId(code))
				datas.add("ASSM_CODE");
			else
				datas.add("ASSM_NAME");			
			datas.add(TableNames.TBL_GEN_TL_ASSEMBLYMST);
			datas.add(" AND ASSm_Active = 'Y'");
		}
		else if(type.equals("S"))
		{
			datas.add("SPRM_Keyid");
			if(UIUtils.isValidKeyId(code))
				datas.add("SPRM_PARTNO");
			else
				datas.add("SPRM_PARTNAME");			
			datas.add(TableNames.TBL_GEN_TL_SPARESMST);
			datas.add(" AND SPRM_Active = 'Y'");
		}
		
		return datas;
		
	}
/*	@Override
	public List<String[]> getGridDetail() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
*/
	@Override
	public GenTlSbumst fillcontrol(String Sbukeyid) throws Exception {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg(" Inside Service Impl :: "+Sbukeyid);
		return this.functionalLocnDao.fillcontrol(Sbukeyid);
	}
	/*@Override
	public GenTlCellmst select(String keyid) throws Exception {
		// TODO Auto-generated method stub
		return this.genTlCellmstDao.select(keyid);
	}*/
	@Override
	public GenTlPbumst fillpbucontrol(String Pbukeyid) throws Exception {
		// TODO Auto-generated method stub
		return this.functionalLocnDao.fillpbucontrol(Pbukeyid);
	}

/*	public String getfunctionalid(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.functionalLocnDao.getfunctionalid(id);
	}
	*/
	
}
