package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;
import net.sf.json.util.DynaBeanToBeanMorpher;

import com.akranta.tpm.bean.EquipmentBean;
import com.akranta.tpm.dao.GenTlMachinemstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.GenTlMachinemstSql;
import com.akranta.tpm.dao.sql.GenTlMachineskillmstSql;
import com.akranta.tpm.dao.sql.GenTlMchcirclelinkSql;
import com.akranta.tpm.dao.sql.GenTlMchemplinkSql;
import com.akranta.tpm.dao.sql.GenTlMchmaintteamlinkSql;
import com.akranta.tpm.dao.sql.GenTlMchparameterlinkSql;
import com.akranta.tpm.dao.sql.GenTlMchsubmchlinkSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlMachinemst;
import com.akranta.tpm.model.GenTlMachineskillmst;
import com.akranta.tpm.model.GenTlMchcirclelink;
import com.akranta.tpm.model.GenTlMchemplink;
import com.akranta.tpm.model.GenTlMchmaintteamlink;
import com.akranta.tpm.model.GenTlMchparameterlink;
import com.akranta.tpm.model.GenTlMchsubmchlink;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.businessvalidations.FunctionalLocValidations;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.service.api.MachineMasterServiceApi;
import com.akranta.tpm.service.api.FunctionCallApi;


/* dao implementation */
public class GenTlMachinemstDaoImpl implements GenTlMachinemstDao {

	private GenTlMchemplinkSql genTlMchemplinkSql = null;
	private DBActionTemplate dbActionTemplate; 
	private GenTlMchmaintteamlinkSql genTlMchmaintteamlinkSql = null;
	private GenTlMachineskillmstSql genTlMachineskillmstSql = null;
	private GenTlMchparameterlinkSql genTlMchparameterlinkSql = null;
	private GenTlMchsubmchlinkSql genTlMchsubmchlinkSql = null;
	private FunctionalLocValidations functionalLocValidations = null;
	private MachineMasterServiceApi machinemasterserviceapi;
	FunctionCallApi fnCallApi;
	
	public GenTlMachinemstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		genTlMchemplinkSql =new GenTlMchemplinkSql();
		genTlMchmaintteamlinkSql = new GenTlMchmaintteamlinkSql();
		genTlMachineskillmstSql = new GenTlMachineskillmstSql();
		genTlMchparameterlinkSql = new GenTlMchparameterlinkSql();
		genTlMchsubmchlinkSql = new GenTlMchsubmchlinkSql();
		functionalLocValidations = new FunctionalLocValidations(dbActionTemplate);
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
	public void GenTlMachinemstDaoImplJwt(String JwtToken) 
  	{
  		try{
  			machinemasterserviceapi = new MachineMasterServiceApi(JwtToken);
  		fnCallApi = new FunctionCallApi(JwtToken);
  		}
  		catch(Exception e)
  		{
  			e.printStackTrace();
  		}
  	}

public String createCircle(List<GenTlMchcirclelink> newGenTlMchcirclelinksList)throws Exception {
		
	List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
	GenTlMchcirclelinkSql genTlMchcirclelinkSql = new GenTlMchcirclelinkSql();
	List<GenTlMchcirclelink> newGenTlMchcirclelinks =newGenTlMchcirclelinksList;
	
	//GenTlMchcirclelink Mchcirclelink = (GenTlMchcirclelink) newGenTlMchcirclelinks;
	
	try{
		//sqls.add(genTlMchcirclelinkSql.getCircleDeleteSql(Mchcirclelink.getMclkMachineid()));
		boolean delFlag = true;
		
		for( GenTlMchcirclelink genTlMchcirclelink : newGenTlMchcirclelinks)
		{	
			if(delFlag)
				sqls.add(genTlMchcirclelinkSql.getCircleDeleteSql(genTlMchcirclelink.getMclkMachineid()));
			genTlMchcirclelink.setMclkKeyid(dbActionTemplate.getSequenceNumber(GenTlMchcirclelinkSql.TBL_GEN_TL_MCHCIRCLELINK,9,"MCL",null,null)); // set the sequnce number

			sqls.add(GenTlMchcirclelinkSql.getInsertSql(genTlMchcirclelinkSql.getMclkDbFields(), genTlMchcirclelink.getSaveArray()));
			delFlag=false;
		}
			CommonMessage.debugMsg("sqls....."+sqls);
		dbActionTemplate.executeStatements(sqls);
		CommonMessage.debugMsg("test11");
		return "Success";
		
	}
		catch(Exception e)
		{
			CommonMessage.debugMsg("Err Occ");
			e.printStackTrace();
			return e.getMessage();
		}
		
	
		
	}
	public GenTlMachinemst create(GenTlMachinemst genTlMachinemst) 	throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlMachinemstSql genTlMachinemstSql = new GenTlMachinemstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
	//	try		{
		
			//genTlMachinemst.setMchmKeyid(dbActionTemplate.getSequenceNumber(GenTlMachinemstSql.TBL_GEN_TL_MACHINEMST)); // set the sequnce number
		
			genTlMachinemst.setMchmKeyid(dbActionTemplate.getSequenceNumber(GenTlMachinemstSql.TBL_GEN_TL_MACHINEMST,10,"MCH",null,null)); // set the sequnce number
			
			sqls.add(GenTlMachinemstSql.getInsertSql(genTlMachinemstSql.getMchmDbFields(), genTlMachinemst.getSaveArray())); // add insert sql for master table

			GenTlFunctionallocn newGenTlFunctionallocn = genTlMachinemst.getGenTlFunctionallocn();
			
			newGenTlFunctionallocn.setFnlnOriginalid(genTlMachinemst.getMchmKeyid());
			newGenTlFunctionallocn.setFnlnElementid(newGenTlFunctionallocn.getFnlnElementid() + "-" + genTlMachinemst.getMchmKeyid() );
			
			newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN,12,"FNLN",null,null)); 
			sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			if(genTlMachinemst.getOperatorgrid() != null && genTlMachinemst.getOperatorgrid().size() > 0)
			{
				insertOperator(genTlMachinemst,sqls);
			}
			if(genTlMachinemst.getMaintainceGrid() !=null && genTlMachinemst.getMaintainceGrid().size() >0 )
			{
				insertMaintaince(genTlMachinemst,sqls);
			}
			if(genTlMachinemst.getOperatorSkillGrid() !=null && genTlMachinemst.getOperatorSkillGrid().size() > 0)
			{
				insertOperatorSkill(genTlMachinemst,sqls);
			}
			if(genTlMachinemst.getMaintainceSkillGrid() != null && genTlMachinemst.getMaintainceSkillGrid().size() > 0)
			{
				insertMaintainceSkill(genTlMachinemst,sqls);
			}
			if(genTlMachinemst.getEquipmentParameterGrid() != null && genTlMachinemst.getEquipmentParameterGrid().size()>0)
			{
				insertEquipmentParameter(genTlMachinemst,sqls);
			}
			if(genTlMachinemst.getSubEquipmentGrid() != null && genTlMachinemst.getSubEquipmentGrid().size()>0)
			{
				insertSubEquipment(genTlMachinemst,sqls);
			}
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		return genTlMachinemst;
	}
	
	
	
	private List<String> insertSubEquipment(GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception{
	
			
			if(genTlMachinemst.getSubEquipmentGrid()!= null && genTlMachinemst.getSubEquipmentGrid().size()>0) // check for detail table data
			{
				sqls.add(GenTlMchsubmchlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
				
		    	for(int i =0;i<genTlMachinemst.getSubEquipmentGrid().size();i++)
				{	
		    		GenTlMchsubmchlink subEquipment = (GenTlMchsubmchlink)genTlMachinemst.getSubEquipmentGrid().get(i); // get detail info from list in empployee object
		    		subEquipment.setScmlCellid(genTlMachinemst.getMchmCellid());	
		    		subEquipment.setScmlParentmchid(genTlMachinemst.getMchmKeyid());
		    		CommonMessage.debugMsg(genTlMachinemst.getMchmKeyid());
		    		
		    		//genTlMchemplink.setMcemMachineid(dbActionTemplate.getSequenceNumber(GenTlMchemplinkSql.TBL_GEN_TL_MCHEMPLINK));
					sqls.add(GenTlMchsubmchlinkSql.getInsertSql(genTlMchsubmchlinkSql.getScmlDbFields(), subEquipment.getSaveArray()));// add insert sql for detail table
				}
			}
			
			CommonMessage.debugMsg("Sql Equipment Parameter Table:"+sqls);
	
			return sqls;		
		
	}

	private List<String> insertEquipmentParameter(GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception{
		
			
			if(genTlMachinemst.getEquipmentParameterGrid()!= null && genTlMachinemst.getEquipmentParameterGrid().size()>0) // check for detail table data
			{
				sqls.add(GenTlMchparameterlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));

				
		    	for(int i =0;i<genTlMachinemst.getEquipmentParameterGrid().size();i++)
				{	
		    		GenTlMchparameterlink equipmentParameter = (GenTlMchparameterlink)genTlMachinemst.getEquipmentParameterGrid().get(i); // get detail info from list in empployee object
		    		equipmentParameter.setMplkKeyid(genTlMachinemst.getMchmKeyid());				

		    		sqls.add(GenTlMchparameterlinkSql.getInsertSql(genTlMchparameterlinkSql.getMplkDbFields(), equipmentParameter.getSaveArray()));// add insert sql for detail table
					CommonMessage.debugMsg("Sql:-"+sqls);
				}
			}
			
			return sqls;
	}

	private List<String> insertMaintainceSkill(GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception {
		
		if(genTlMachinemst.getMaintainceSkillGrid()!= null && genTlMachinemst.getMaintainceSkillGrid().size()>0) // check for detail table data
		{
			sqls.add(GenTlMachineskillmstSql.getMaintainceDeleteSql(genTlMachinemst.getMchmKeyid()));
			
	    	for(int i =0;i<genTlMachinemst.getMaintainceSkillGrid().size();i++)
			{	
	    		GenTlMachineskillmst maintaince = (GenTlMachineskillmst)genTlMachinemst.getMaintainceSkillGrid().get(i); // get detail info from list in empployee object
	    		maintaince.setMskmMachineid(genTlMachinemst.getMchmKeyid());				
	    		CommonMessage.debugMsg(genTlMachinemst.getMchmKeyid());
	    		
				sqls.add(GenTlMachineskillmstSql.getInsertSql(genTlMachineskillmstSql.getMskmDbFields(), maintaince.getSaveArray()));// add insert sql for detail table
			}
		}
		
		CommonMessage.debugMsg("Sql Data Maintaince Table:"+sqls);
		return sqls;
	}

	private List<String> insertOperatorSkill(GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception {
		
		CommonMessage.debugMsg("inside the DaoImpl OperatorSkill Table ");
		CommonMessage.debugMsg("size"+genTlMachinemst.getOperatorSkillGrid().size());
		
		if(genTlMachinemst.getOperatorSkillGrid()!= null && genTlMachinemst.getOperatorSkillGrid().size()>0) // check for detail table data
		{
			sqls.add(GenTlMachineskillmstSql.getOperatorDeleteSql(genTlMachinemst.getMchmKeyid()));
			
	    	for(int i =0;i<genTlMachinemst.getOperatorSkillGrid().size();i++)
			{	
	    		GenTlMachineskillmst genTlMachineskillmst = (GenTlMachineskillmst)genTlMachinemst.getOperatorSkillGrid().get(i); // get detail info from list in empployee object
	    		genTlMachineskillmst.setMskmMachineid(genTlMachinemst.getMchmKeyid());				
				sqls.add(GenTlMachineskillmstSql.getInsertSql(genTlMachineskillmstSql.getMskmDbFields(), genTlMachineskillmst.getSaveArray()));// add insert sql for detail table
			}
		}
		
		CommonMessage.debugMsg("Dql Data Operator Table:"+sqls);
		return sqls;
	}
	
	private List<String> insertMaintaince(GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception {
		
		CommonMessage.debugMsg("inside the DaoImpl Maintaince Table ");
		CommonMessage.debugMsg("size"+genTlMachinemst.getMaintainceGrid().size());
		
		if(genTlMachinemst.getMaintainceGrid()!= null && genTlMachinemst.getMaintainceGrid().size()>0) // check for detail table data
		{
			sqls.add(GenTlMchmaintteamlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
			
	    	for(int i =0;i<genTlMachinemst.getMaintainceGrid().size();i++)
			{	
	    		GenTlMchmaintteamlink genTlMchmaintteamlink = (GenTlMchmaintteamlink)genTlMachinemst.getMaintainceGrid().get(i); // get detail info from list in empployee object
	    		genTlMchmaintteamlink.setMcmtMachineid(genTlMachinemst.getMchmKeyid());				
	    		CommonMessage.debugMsg(genTlMachinemst.getMchmKeyid());
	    		//genTlMchemplink.setMcemMachineid(dbActionTemplate.getSequenceNumber(GenTlMchemplinkSql.TBL_GEN_TL_MCHEMPLINK));
				sqls.add(GenTlMchmaintteamlinkSql.getInsertSql(genTlMchmaintteamlinkSql.getMcmtDbFields(), genTlMchmaintteamlink.getSaveArray()));// add insert sql for detail table
			}
		}
		CommonMessage.debugMsg("Dql Data Operator Table:"+sqls);
		return sqls;
		
	}
	private List<String> insertOperator(GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception {
		
		CommonMessage.debugMsg("inside the DaoImpl Operator Table ");
		CommonMessage.debugMsg("size"+genTlMachinemst.getOperatorgrid().size());
		if(genTlMachinemst.getOperatorgrid()!= null && genTlMachinemst.getOperatorgrid().size()>0) // check for detail table data
		{
			sqls.add(GenTlMchemplinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
			
	    	for(int i =0;i<genTlMachinemst.getOperatorgrid().size();i++)
			{	
	    		GenTlMchemplink genTlMchemplink = (GenTlMchemplink)genTlMachinemst.getOperatorgrid().get(i); // get detail info from list in empployee object
	    		genTlMchemplink.setMcemMachineid(genTlMachinemst.getMchmKeyid());				
	    		CommonMessage.debugMsg(genTlMachinemst.getMchmKeyid());
	    		//genTlMchemplink.setMcemMachineid(dbActionTemplate.getSequenceNumber(GenTlMchemplinkSql.TBL_GEN_TL_MCHEMPLINK));
				sqls.add(GenTlMchemplinkSql.getInsertSql(genTlMchemplinkSql.getMcemDbFields(), genTlMchemplink.getSaveArray()));// add insert sql for detail table
			}
		}
		CommonMessage.debugMsg("Dql Data Operator Table:"+sqls);
		return sqls;
	}


	public GenTlMachinemst update(GenTlMachinemst genTlMachinemst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		GenTlMachinemstSql genTlMachinemstSql = new GenTlMachinemstSql();
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql();
		try {

			sqls.add(GenTlMachinemstSql.getUpdateSql(genTlMachinemstSql.getMchmDbFields(), genTlMachinemst.getSaveArray()));
			
			GenTlFunctionallocn newGenTlFunctionallocn = genTlMachinemst.getGenTlFunctionallocn();
			
			newGenTlFunctionallocn.setFnlnElementid(newGenTlFunctionallocn.getFnlnElementid() + "-" + genTlMachinemst.getMchmKeyid() );
			
			sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			if(genTlMachinemst.getOperatorgrid() != null && genTlMachinemst.getOperatorgrid().size() > 0)
			{
				insertOperator(genTlMachinemst,sqls);
			}
			if(genTlMachinemst.getMaintainceGrid() !=null && genTlMachinemst.getMaintainceGrid().size() >0 )
			{
				insertMaintaince(genTlMachinemst,sqls);
			}
			if(genTlMachinemst.getOperatorSkillGrid() !=null && genTlMachinemst.getOperatorSkillGrid().size() > 0)
			{
				insertOperatorSkill(genTlMachinemst,sqls);
			}
			if(genTlMachinemst.getMaintainceSkillGrid() != null && genTlMachinemst.getMaintainceSkillGrid().size() > 0)
			{
				insertMaintainceSkill(genTlMachinemst,sqls);
			}
			if(genTlMachinemst.getEquipmentParameterGrid() != null && genTlMachinemst.getEquipmentParameterGrid().size()>0)
			{
				insertEquipmentParameter(genTlMachinemst,sqls);
			}
			if(genTlMachinemst.getSubEquipmentGrid() != null && genTlMachinemst.getSubEquipmentGrid().size()>0)
			{
				insertSubEquipment(genTlMachinemst,sqls);
			}
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
		return genTlMachinemst;
	}
	
	public GenTlMachinemst delete(String delMode, GenTlMachinemst genTlMachinemst)
			throws Exception {
CommonMessage.debugMsg("inside dao impl "+delMode);
		List<String> sqls = new ArrayList<String>();
		GenTlMachinemstSql genTlMachinemstSql = new GenTlMachinemstSql();
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql();
		GenTlFunctionallocn newGenTlFunctionallocn = genTlMachinemst.getGenTlFunctionallocn();
		String originalId = genTlMachinemst.getMchmKeyid();
		
		if (!delMode.equals("I"))
		{
			
			functionalLocValidations.checkOriginalIdExistsinFunctionalLoc(originalId);
		
			sqls.add(GenTlMchemplinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
			sqls.add(GenTlMchmaintteamlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
			sqls.add(GenTlMachineskillmstSql.getOperatorDeleteSql(genTlMachinemst.getMchmKeyid()));
			sqls.add(GenTlMachineskillmstSql.getMaintainceDeleteSql(genTlMachinemst.getMchmKeyid()));
			sqls.add(GenTlMchparameterlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
			sqls.add(GenTlMchsubmchlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
		}
		
			CommonMessage.debugMsg("inside process");
			sqls.add(GenTlMachinemstSql.getDeleteSql(delMode, genTlMachinemstSql.getMchmDbFields(), genTlMachinemst.getSaveArray()));
			//sqls.add(GenTlFunctionallocnSql.getDeleteFunLocSql(delMode,genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		
		return genTlMachinemst;
	}

	@Override
	public GenTlMachinemst getselect(String MchmKeyid) throws Exception {
		List<String> sqls = new ArrayList<String>();
		GenTlMachinemst genTlMachinemst = new GenTlMachinemst();		
		CommonMessage.debugMsg("Before fetch the data");
		CommonMessage.debugMsg(MchmKeyid);
		String sql = GenTlMachinemstSql.getEquipmentMstSql();
		//sql += GenTlMachinemstSql.getgridData();
		//gridData(MchmKeyid,sqls);
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] {MchmKeyid};
		genTlMachinemst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		CommonMessage.debugMsg("Dao Impl"+genTlMachinemst);	
				
		return genTlMachinemst;
	}

	private GenTlMachinemst gridData(String MchmKeyid,List<String> sqls) throws Exception{
		
		CommonMessage.debugMsg("Grid Datas");
		GenTlMachinemst genTlMachinemst = new GenTlMachinemst();
		String sql = GenTlMachinemstSql.getgridData();
		Object args [] = new Object [] {MchmKeyid};
		CommonMessage.debugMsg("test data:"+sql);
		genTlMachinemst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		CommonMessage.debugMsg("grid data test:"+genTlMachinemst);
		return genTlMachinemst;
	}

	@Override
	public List<String[]> getOperator(String eqpId) throws Exception {
		
		return null;
	}

	@Override
	public List<String[]> getOperatorData(String factId ) throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			String sql = GenTlMachinemstSql.getOperatorData(factId);
			List<String[]> operator = dbActionTemplate.getDataList(sql);
			
			return operator;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getSkillData() throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			String sql = GenTlMachinemstSql.OperatorSkillData();
			List<String[]> operator = dbActionTemplate.getDataList(sql);
			
			return operator;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getMaintainceData() throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			String sql = GenTlMachinemstSql.MachineData();
			List<String[]> operator = dbActionTemplate.getDataList(sql);
			
			return operator;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getMaintSkillData() throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			String sql = GenTlMachinemstSql.MaintSkillData();
			List<String[]> operator = dbActionTemplate.getDataList(sql);
			
			return operator;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}


	@Override
	public List<String[]> getEquipParmData() throws Exception {
		return null;
	}

	@Override
	public List<String[]> getSubEquipData(String section) throws Exception {
		try
		{
			/*CommonMessage.debugMsg("inside dao Impl");
			List<String> params = new ArrayList<String>();
			params.add(section);
			String sql = GenTlMachinemstSql.subEquipmentData();
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;*/
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getEquipmentParm(String machineId) throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			//mano
			CommonMessage.debugMsg("MachineId: " + machineId); 
			List<String> params = new ArrayList();
			params.add(machineId);
			String sql = GenTlMachinemstSql.equipmentData();
			//mano
			CommonMessage.debugMsg("SQL Query: " + sql);
			CommonMessage.debugMsg("After");
			List<String[]> EquipmentPqrqmeter = dbActionTemplate.getDataList(sql, params);
			
			return EquipmentPqrqmeter;
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getOperatorRecall(String oprRecall) throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			List<String> params = new ArrayList<String>();
			params.add(oprRecall);
			String sql = GenTlMachinemstSql.RecallOperatorData();
			CommonMessage.debugMsg("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getOperatorSkillRecall(String recall)
			throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			List<String> params = new ArrayList<String>();
			params.add(recall);
			String sql = GenTlMachinemstSql.RecallOperatorSkillData();
			CommonMessage.debugMsg("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getMaintainceRecall(String recall) throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			List<String> params = new ArrayList<String>();
			params.add(recall);
			String sql = GenTlMachinemstSql.RecallMaintainceData();
			CommonMessage.debugMsg("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getMaintainceSkillRecall(String recall)
			throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			List<String> params = new ArrayList<String>();
			params.add(recall);
			String sql = GenTlMachinemstSql.RecallMaintainceSkillData();
			CommonMessage.debugMsg("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	/*
	 * @Override public List<String[]> getMainGrid(CommonFilter commonFilter) throws
	 * Exception {
	 * 
	 * try { List<String> params = new ArrayList<String>(); // params.add();
	 * if(commonFilter.getActive() == 'S'){ String sql =
	 * GenTlMachinemstSql.getAll(commonFilter);
	 * 
	 * List<String[]> operator = dbActionTemplate.getDataList(sql, params); return
	 * operator; } else{ String sql = GenTlMachinemstSql.masterGrid(commonFilter);
	 * 
	 * List<String[]> operator = dbActionTemplate.getDataList(sql, params);
	 * 
	 * return operator; } } catch (Exception e) {
	 * CommonMessage.debugMsg("Exception:"+e.getMessage()); e.getMessage(); } return
	 * null;
	 * 
	 * }
	 */
	
	@Override
	public List<String[]> getMainGrid(CommonFilter commonFilter) throws Exception {
		
		try {
			List<String> paramValues = new ArrayList<String>();
			String condParams = "";
			String commonParams = "";
			
			// Build grid filter parameter
			if (commonFilter.getGridFilter() != null) {
				commonParams += "GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) + ";";
			}
			
			// Build pagination parameter
			commonParams += "FROMTOROW=" + commonFilter.getFromRow() + " AND " + commonFilter.getToRow() + ";";
			
			// Build ACTIVE parameter
			if (commonFilter.getActive() != null) {
				commonParams += "ACTIVE=" + commonFilter.getActive() + ";";
			}
			
			// Build FLID parameter (if applicable)
			if (UIUtils.isValidKeyId(commonFilter.getFlid())) {
				commonParams += "FLID=" + commonFilter.getFlid() + ";";
			}
			
			// Build LOCATIONID parameter (if applicable)
			if (commonFilter.getLocation() != null && commonFilter.getLocation().getId() != null) {
				commonParams += "LOCATIONID=" + commonFilter.getLocation().getId() + ";";
			}
			
			// Add parameters to list
			paramValues.add(condParams);
			paramValues.add(commonParams);
			
			// Call the function
			List<String[]> result = fnCallApi.callFunction("gen_fn_machinemaingrid_sb", paramValues, 1,true);
			
			// Extract and set total count
			String totalCnt = paramValues.get(0);
			CommonMessage.debugMsg("totalCnt..." + totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			
			if (isInteger) {
				long counts = Long.parseLong(totalCnt);
				commonFilter.setTotalRecordCnt(counts);
			}
			
			return result;
			
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception in getMainGrid: " + e.getMessage());
			e.printStackTrace();
			throw e; // Re-throw to let caller handle
		}
	}

	@Override
	public List<String[]> getEquipmentParameterRecall(String recall) throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			List<String> params = new ArrayList<String>();
			CommonMessage.debugMsg("recall"+recall);
			params.add(recall);
			String sql = GenTlMachinemstSql.RecallEquipmentParameterData();
			CommonMessage.debugMsg("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			CommonMessage.debugMsg("Query:"+operator);
			return operator;
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getSubEquipmentRecall(String recall) throws Exception {
		try
		{
			/*CommonMessage.debugMsg("inside dao Impl");
			List<String> params = new ArrayList<String>();
			CommonMessage.debugMsg("recall"+recall);
			params.add(recall);
			//String sql = GenTlMachinemstSql.recallSubEquipmentData();
			//String sql = GenTlMachinemstSql.subEquipmentData();
			CommonMessage.debugMsg("sql:"+sql);
			List<String[]> subEquipment = dbActionTemplate.getDataList(sql, params);
			CommonMessage.debugMsg("Query:"+subEquipment);
			return subEquipment;*/
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getAllSubEquipDataForEqp(String sectionId,
			String eqpId) throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			/*List<String> params = new ArrayList<String>();
			CommonMessage.debugMsg("recall"+sectionId);
			params.add(sectionId);
			params.add(eqpId);
			params.add(eqpId);*/
			//String sql = GenTlMachinemstSql.recallSubEquipmentData();
			String sql = GenTlMachinemstSql.subEquipmentData(sectionId,eqpId);
			CommonMessage.debugMsg("sql:"+sql);
			//List<String[]> subEquipment = dbActionTemplate.getDataList(sql, params);
			List<String[]> subEquipment = dbActionTemplate.getDataList(sql);
			CommonMessage.debugMsg("Query:"+subEquipment);
			return subEquipment;
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}
	
	public void deleteOperatorSkill(String machineId, String skillName) throws Exception{
		String sql = GenTlMachinemstSql.getDeleteOperatorSkillSql();
		Object [] values = {machineId,skillName };
		int [] dataTypes = { Types.VARCHAR, Types.VARCHAR }; 
		
		dbActionTemplate.executeStatement(sql, values, dataTypes);
	}

	public void deleteMainTeamSkill(String machineId, String skillName) throws Exception{
		String sql = GenTlMachinemstSql.getDeleteMaintTeamSkillSql();
		Object [] values = {machineId,skillName };
		int [] dataTypes = { Types.VARCHAR, Types.VARCHAR }; 
		
		dbActionTemplate.executeStatement(sql, values, dataTypes);
	}
	
	public void deleteOperatorMachineLink(String machineId, String empId) throws Exception{
		String sql = GenTlMachinemstSql.getDeleteOperatorMachineLinkSql();
		Object [] values = {machineId,empId };
		int [] dataTypes = { Types.VARCHAR, Types.VARCHAR }; 
		
		dbActionTemplate.executeStatement(sql, values, dataTypes);
	}

	public void deleteMaintTeamMachineLink(String machineId, String maintTeamId) throws Exception{
		String sql = GenTlMachinemstSql.getDeleteMaintTeamMachineLinkSql();
		Object [] values = {machineId,maintTeamId };
		int [] dataTypes = { Types.VARCHAR, Types.VARCHAR }; 
		
		dbActionTemplate.executeStatement(sql, values, dataTypes);
	}

	@Override
	public Workbook EquipmentFormExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception {

		 ResultSet rs = null;
		   try{
			
			rs =   getEquipmentResultSet(commonFilter);
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
			return excelUtils.writeToExcel(rs,rptFormat, 0,0,0 );
			
		   }finally{
			   DBActionTemplate.closeConnection(rs, null, null, null, rs.getStatement().getConnection());
		   }  
	}
	private ResultSet getEquipmentResultSet(CommonFilter commonFilter) throws Exception
	{
		try
		{
			CommonMessage.debugMsg("inside dao Impl");		
			
			if(commonFilter.getActive() == 'S'){
				String sql = GenTlMachinemstSql.getAll(commonFilter);
				CommonMessage.debugMsg("sql:"+sql);
				return  dbActionTemplate.getData(sql);	
			}
			else{
				CommonMessage.debugMsg("Before the Function..."+commonFilter.getFromRow());
				String sql = GenTlMachinemstSql.masterGrid(commonFilter);
				CommonMessage.debugMsg("sql the Function..."+sql);
				return  dbActionTemplate.getData(sql);				
			}
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public int selectCount(CommonFilter commonFilter) throws Exception {
		
		/*
		 * CommonMessage.debugMsg("Before fetch the data");
		 * 
		 * //String sql = GenTlMachinemstSql.getEquipmentMstSqlCount(commonFilter);
		 * String sql = GenTlMachinemstSql.getCountAll(commonFilter);
		 * CommonMessage.debugMsg("Count sql....."+sql); String returnData =
		 * dbActionTemplate.getSingleValue(sql); int retData =
		 * Integer.parseInt(returnData); CommonMessage.debugMsg(retData); return
		 * retData;
		 */
		
		try {
			List<String> paramValues = new ArrayList<String>();
			String condParams = "";
			String commonParams = "";
			
			// Build grid filter parameter
			if (commonFilter.getGridFilter() != null) {
				commonParams += "GRIDFILTER=" + FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()) + ";";
			}
			
			// Build pagination parameter
			commonParams += "FROMTOROW=" + commonFilter.getFromRow() + " AND " + commonFilter.getToRow() + ";";
			
			// Build ACTIVE parameter
			if (commonFilter.getActive() != null) {
				condParams += "ACTIVE=" + commonFilter.getActive() + ";";
			}
			
			// Build FLID parameter (if applicable)
			if (UIUtils.isValidKeyId(commonFilter.getFlid())) {
				condParams += "FLID=" + commonFilter.getFlid() + ";";
			}
			
			// Build LOCATIONID parameter (if applicable)
			if (commonFilter.getLocation() != null && commonFilter.getLocation().getId() != null) {
				commonParams += "LOCATIONID=" + commonFilter.getLocation().getId() + ";";
			}
			
			// Add parameters to list
			paramValues.add(condParams);
			paramValues.add(commonParams);
			
			// Call the function
			List<String[]> result = fnCallApi.callFunction("gen_fn_machinemstcount_sb", paramValues, 1,true);
			
			// Extract and set total count
			String totalCnt = paramValues.get(0);
			CommonMessage.debugMsg("totalCnt..." + totalCnt);
			boolean isInteger = Pattern.matches("^\\d*$", totalCnt);
			
			if (isInteger) {
				long counts = Long.parseLong(totalCnt);
				commonFilter.setTotalRecordCnt(counts);
			}
			
			return Integer.parseInt(totalCnt);
			
		} catch (Exception e) {
			CommonMessage.debugMsg("Exception in getMainGrid: " + e.getMessage());
			e.printStackTrace();
			throw e; // Re-throw to let caller handle
		}
		
	}
	
	
	

	@Override
	public List<String[]> getInactive(String keyIds) throws Exception {
		
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			List<String> sqls = new ArrayList<String>();
			
			 String[] temp;
				String delimiter = ",";					
				  temp = ((String) keyIds).split(delimiter);
				  for(int i =0; i < temp.length ; i++)
				  {
					   String data = temp[i];
					   sqls.add(GenTlMachinemstSql.getActive(data));
					   sqls.add(GenTlMachinemstSql.getFuncLocActive(data));
				  }	
				  dbActionTemplate.executeStatements(sqls);
			
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getAll(CommonFilter commonFilter) throws Exception {
		
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			List<String> params = new ArrayList<String>();
		//	params.add();
			String sql = GenTlMachinemstSql.getAll(commonFilter);
			CommonMessage.debugMsg("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
		
	}

	@Override
	public List<String[]> getAllCircle(String mchId) throws Exception {
		try
		{
			CommonMessage.debugMsg("Catagory");
			List<String> params = new ArrayList<String>();		
			String sql = GenTlMachinemstSql.getAllCircle(mchId);			
			CommonMessage.debugMsg("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getFormCircle(String mchId) throws Exception {
		try
		{
			CommonMessage.debugMsg("Catagory");
			List<String> params = new ArrayList<String>();		
			String sql = GenTlMachinemstSql.getFormCircle(mchId);
			CommonMessage.debugMsg("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public String deleteCircle(GenTlMchcirclelink newGenTlMchcirclelink)throws Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlMchcirclelinkSql genTlMchcirclelinkSql = new GenTlMchcirclelinkSql();
		sqls.add(genTlMchcirclelinkSql.getCircleDeleteSql(newGenTlMchcirclelink.getMclkMachineid()));
		dbActionTemplate.executeStatements(sqls);
		CommonMessage.debugMsg("test11");
		return "DeleteSuccess";
	}

	@Override
	public List<String[]> getAllPM() throws Exception {
		// TODO Auto-generated method stub
		String sql="  select'','Equipment1','50','10','60','40','80','67','Remarks1' FROM DUAL";
	       sql+=" UNION ALL select '','Equipment2','56','12','68','60','107','88','Remarks2' FROM DUAL";
	       sql+=" UNION ALL select '','Equipment3','62','52','114','50','81','44','Remarks3' FROM DUAL";
	       sql+=" UNION ALL select '','Equipment4','35','30','65','15','43','23','Remarks4'FROM DUAL";
	       sql+=" UNION ALL select '','Equipment5','18','10','28','28','156','100','Remarks5' FROM DUAL";
	       sql+=" UNION ALL select '','Equipment6','85','52','137','100','118','73','Remarks6' FROM DUAL";
	       sql+=" UNION ALL select '','Equipment7','75','0','75','50','67','67','Remarks7' FROM DUAL";
	       CommonMessage.debugMsg("sql "+sql);
			List<String[]> dataList =  dbActionTemplate.getDataList(sql);
			return dataList;
	}

	@Override
	public List<String[]> getAllPMgrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String sql="  " + "select '','',' ','','','','','','','','','','','' FROM DUAL";
		sql+=" UNION ALL  select'Keyid','Equipment','July','July','August','August','September','September','October','October','November','November','December','December' FROM DUAL";
		 sql+=" UNION ALL select'Keyid','Equipment',' PM Adherence %','Cumulative','PM Adherence %','Cumulative','PM Adherence %','Cumulative','PM Adherence %','Cumulative','PM Adherence %','Cumulative','PM Adherence %','Cumulative' FROM DUAL";
		 sql+=" UNION ALL select'','Equipment1','50','10','60','40','80','67','87','34','65','21','94','41' FROM DUAL";
		 sql+=" UNION ALL select '','Equipment2','56','12','68','60','107','88','56','78','85','39','43','52' FROM DUAL";
		CommonMessage.debugMsg("sql "+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql);
		return dataList;
	}

	@Override
	public List<String[]> getEquipOtherDetails(String machineId)throws Exception {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");
			List<String> params = new ArrayList();
			params.add(machineId);
			String sql =GenTlMachinemstSql.equipmentOtherDetails();
			CommonMessage.debugMsg("After");
			List<String[]> EquipmentOtherDetails = dbActionTemplate.getDataList(sql, params);
			
			return EquipmentOtherDetails;
		
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public String getElementByMachineId(String machineId) throws Exception 
	{
		
		
		String tableName = "GEN_TL_FUNCTIONALLOCN";
		String returnField = "FNLN_ELEMENTID";
		String checkField = "FNLN_ORIGINALID";
		String elementId =  dbActionTemplate.getSingleValue(tableName, returnField, checkField, machineId);
		CommonMessage.debugMsg("ELEMENT ID NEW"+elementId);
		return elementId;
	}


	
	
}

