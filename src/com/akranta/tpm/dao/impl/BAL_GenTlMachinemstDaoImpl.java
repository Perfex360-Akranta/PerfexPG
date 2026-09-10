package com.akranta.tpm.dao.impl;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;
import net.sf.json.util.DynaBeanToBeanMorpher;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.EquipmentBean;
import com.akranta.tpm.dao.BAL_GenTlMachinemstDao;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.BAL_GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.BAL_GenTlMachinemstSql;
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
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
import com.akranta.tpm.model.BAL_GenTlMachinemst;
import com.akranta.tpm.model.GenTlMachineskillmst;
import com.akranta.tpm.model.GenTlMchcirclelink;
import com.akranta.tpm.model.GenTlMchemplink;
import com.akranta.tpm.model.GenTlMchmaintteamlink;
import com.akranta.tpm.model.GenTlMchparameterlink;
import com.akranta.tpm.model.GenTlMchsubmchlink;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.ExcelUtils;
import com.akranta.tpm.businessvalidations.FunctionalLocValidations;

/* dao implementation */
public class BAL_GenTlMachinemstDaoImpl implements BAL_GenTlMachinemstDao {

	private GenTlMchemplinkSql genTlMchemplinkSql = null;
	private DBActionTemplate dbActionTemplate; 
	private GenTlMchmaintteamlinkSql genTlMchmaintteamlinkSql = null;
	private GenTlMachineskillmstSql genTlMachineskillmstSql = null;
	private GenTlMchparameterlinkSql genTlMchparameterlinkSql = null;
	private GenTlMchsubmchlinkSql genTlMchsubmchlinkSql = null;
	private FunctionalLocValidations functionalLocValidations = null;
	BAL_GenTlFunctionallocnSql genTlFunctionallocnSql =new BAL_GenTlFunctionallocnSql();
	
	public BAL_GenTlMachinemstDaoImpl(DBActionTemplate dbActionTemplate) 
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
			CommonFunctions.debugMsg("sqls....."+sqls);
		dbActionTemplate.executeStatements(sqls);
		CommonFunctions.debugMsg("test11");
		return "Success";
		
	}
		catch(Exception e)
		{
			CommonFunctions.debugMsg("Err Occ");
			e.printStackTrace();
			return e.getMessage();
		}
		
	
		
	}
	public BAL_GenTlMachinemst create(BAL_GenTlMachinemst genTlMachinemst) 	throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_GenTlMachinemstSql genTlMachinemstSql = new BAL_GenTlMachinemstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		//GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
	//	try		{
		
			//genTlMachinemst.setMchmKeyid(dbActionTemplate.getSequenceNumber(GenTlMachinemstSql.TBL_GEN_TL_MACHINEMST)); // set the sequnce number
	       CommonFunctions.debugMsg(" genTlMachinemst.genTlMachinemst by Kiran" +genTlMachinemst.getMchmMachineno());
	       if(genTlMachinemst.getMchmMachineno().equals("{}")  || genTlMachinemst.getMchmMachineno()==null) {
	    	   String mchmPrefix=getPrefix(genTlMachinemst.getMchmCellid());
	    	   genTlMachinemst.setMchmMachineno(dbActionTemplate.getSequenceNumber("GEN_TL_MACHINENO",10,mchmPrefix,"YYYY","Y"));
	    	  String str=  genTlMachinemst.getMchmMachineno();
	    	    str.substring(0,6).equals("0");
	    	    String MchNo=mchmPrefix+str.substring(5);
	    	    genTlMachinemst.setMchmMachineno(MchNo);
	    	   //genTlMachinemst.setMchmMachineno(getPrefix(genTlMachinemst.getMchmCellid()));
		       CommonFunctions.debugMsg(" genTlMachinemst.genTlMachinemst by Kiran2 "+MchNo+ " mchmPrefix" +genTlMachinemst.getMchmMachineno());

	       } //sectionid;
	        genTlMachinemst.setMchmTempfield4(getSection(genTlMachinemst.getMchmCellid())) ;
			genTlMachinemst.setMchmKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlMachinemstSql.TBL_GEN_TL_MACHINEMST,10,"MCH",null,null)); // set the sequnce number
			
			sqls.add(BAL_GenTlMachinemstSql.getInsertSql(genTlMachinemstSql.getMchmDbFields(), genTlMachinemst.getSaveArray())); // add insert sql for master table

			BAL_GenTlFunctionallocn newGenTlFunctionallocn = genTlMachinemst.getGenTlFunctionallocn();
			
			newGenTlFunctionallocn.setFnlnOriginalid(genTlMachinemst.getMchmKeyid());
			//if(genTlMachinemst.getMchmMachineno().equals("{}")  || genTlMachinemst.getMchmMachineno()==null) {
			newGenTlFunctionallocn.setFnlnDisplaycode(genTlMachinemst.getMchmMachineno());
			//}
			newGenTlFunctionallocn.setFnlnElementid(genTlMachinemst.getMchmElementid() + "-" + genTlMachinemst.getMchmKeyid() );
			newGenTlFunctionallocn.setFnlnParentid(genTlMachinemst.getMchmElementid());
			
			newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN,12,"FNL",null,null)); 
			sqls.add(BAL_GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
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
	
	private String getPrefix(String mchCellid) throws Exception{
		String sql="select fact_code||substr(Sect_code,-3) from gen_tl_cellmst,GEN_TL_SECTIONMST,GEN_TL_FACTORYMST where cell_sectionid=sect_keyid and cell_factoryid=fact_keyid and cell_keyid='"+mchCellid+"'";
		
		String prefix=dbActionTemplate.getSingleValue(sql);
		
		return prefix;
	}
	private String getSection(String mchCellid) throws Exception{
		String sql="select cell_sectionid from gen_tl_cellmst where  cell_keyid='"+mchCellid+"'";
		
		String sectionid=dbActionTemplate.getSingleValue(sql);
		
		return sectionid;
	}
	
	private List<String> insertSubEquipment(BAL_GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception{
	
			
			if(genTlMachinemst.getSubEquipmentGrid()!= null && genTlMachinemst.getSubEquipmentGrid().size()>0) // check for detail table data
			{
				sqls.add(GenTlMchsubmchlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
				
		    	for(int i =0;i<genTlMachinemst.getSubEquipmentGrid().size();i++)
				{	
		    		GenTlMchsubmchlink subEquipment = (GenTlMchsubmchlink)genTlMachinemst.getSubEquipmentGrid().get(i); // get detail info from list in empployee object
		    		subEquipment.setScmlCellid(genTlMachinemst.getMchmCellid());	
		    		subEquipment.setScmlParentmchid(genTlMachinemst.getMchmKeyid());
		    			    		
		    		//genTlMchemplink.setMcemMachineid(dbActionTemplate.getSequenceNumber(GenTlMchemplinkSql.TBL_GEN_TL_MCHEMPLINK));
					sqls.add(GenTlMchsubmchlinkSql.getInsertSql(genTlMchsubmchlinkSql.getScmlDbFields(), subEquipment.getSaveArray()));// add insert sql for detail table
				}
			}
			
			//CommonFunctions.debugMsg("Sql Equipment Parameter Table:"+sqls);
	
			return sqls;		
		
	}

	private List<String> insertEquipmentParameter(BAL_GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception{
		
			
			if(genTlMachinemst.getEquipmentParameterGrid()!= null && genTlMachinemst.getEquipmentParameterGrid().size()>0) // check for detail table data
			{
				sqls.add(GenTlMchparameterlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));

				
		    	for(int i =0;i<genTlMachinemst.getEquipmentParameterGrid().size();i++)
				{	
		    		GenTlMchparameterlink equipmentParameter = (GenTlMchparameterlink)genTlMachinemst.getEquipmentParameterGrid().get(i); // get detail info from list in empployee object
		    		equipmentParameter.setMplkKeyid(genTlMachinemst.getMchmKeyid());				

		    		sqls.add(GenTlMchparameterlinkSql.getInsertSql(genTlMchparameterlinkSql.getMplkDbFields(), equipmentParameter.getSaveArray()));// add insert sql for detail table
		    	//	CommonFunctions.debugMsg("Sql:-"+sqls);
				}
			}
			
			return sqls;
	}

	private List<String> insertMaintainceSkill(BAL_GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception {
		
		if(genTlMachinemst.getMaintainceSkillGrid()!= null && genTlMachinemst.getMaintainceSkillGrid().size()>0) // check for detail table data
		{
			sqls.add(GenTlMachineskillmstSql.getMaintainceDeleteSql(genTlMachinemst.getMchmKeyid()));
			
	    	for(int i =0;i<genTlMachinemst.getMaintainceSkillGrid().size();i++)
			{	
	    		GenTlMachineskillmst maintaince = (GenTlMachineskillmst)genTlMachinemst.getMaintainceSkillGrid().get(i); // get detail info from list in empployee object
	    		maintaince.setMskmMachineid(genTlMachinemst.getMchmKeyid());				
	    	
	    		
				sqls.add(GenTlMachineskillmstSql.getInsertSql(genTlMachineskillmstSql.getMskmDbFields(), maintaince.getSaveArray()));// add insert sql for detail table
			}
		}
		return sqls;
	}

	private List<String> insertOperatorSkill(BAL_GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception {
		
		
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
		
		//CommonFunctions.debugMsg("Dql Data Operator Table:"+sqls);
		return sqls;
	}
	
	private List<String> insertMaintaince(BAL_GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception {
		
	/*	System.out.println("inside the DaoImpl Maintaince Table ");
		System.out.println("size"+genTlMachinemst.getMaintainceGrid().size());
		
	*/	if(genTlMachinemst.getMaintainceGrid()!= null && genTlMachinemst.getMaintainceGrid().size()>0) // check for detail table data
		{
			sqls.add(GenTlMchmaintteamlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
			
	    	for(int i =0;i<genTlMachinemst.getMaintainceGrid().size();i++)
			{	
	    		GenTlMchmaintteamlink genTlMchmaintteamlink = (GenTlMchmaintteamlink)genTlMachinemst.getMaintainceGrid().get(i); // get detail info from list in empployee object
	    		genTlMchmaintteamlink.setMcmtMachineid(genTlMachinemst.getMchmKeyid());				
	    		System.out.println(genTlMachinemst.getMchmKeyid());
	    		//genTlMchemplink.setMcemMachineid(dbActionTemplate.getSequenceNumber(GenTlMchemplinkSql.TBL_GEN_TL_MCHEMPLINK));
				sqls.add(GenTlMchmaintteamlinkSql.getInsertSql(genTlMchmaintteamlinkSql.getMcmtDbFields(), genTlMchmaintteamlink.getSaveArray()));// add insert sql for detail table
			}
		}
    //	CommonFunctions.debugMsg("Dql Data Operator Table:"+sqls);
		return sqls;
		
	}
	private List<String> insertOperator(BAL_GenTlMachinemst genTlMachinemst,List<String> sqls) throws Exception {
		
		/*System.out.println("inside the DaoImpl Operator Table ");
		System.out.println("size"+genTlMachinemst.getOperatorgrid().size());
		*/
		if(genTlMachinemst.getOperatorgrid()!= null && genTlMachinemst.getOperatorgrid().size()>0) // check for detail table data
		{
			sqls.add(GenTlMchemplinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
			
	    	for(int i =0;i<genTlMachinemst.getOperatorgrid().size();i++)
			{	
	    		GenTlMchemplink genTlMchemplink = (GenTlMchemplink)genTlMachinemst.getOperatorgrid().get(i); // get detail info from list in empployee object
	    		genTlMchemplink.setMcemMachineid(genTlMachinemst.getMchmKeyid());				
	    		System.out.println(genTlMachinemst.getMchmKeyid());
	    		//genTlMchemplink.setMcemMachineid(dbActionTemplate.getSequenceNumber(GenTlMchemplinkSql.TBL_GEN_TL_MCHEMPLINK));
				sqls.add(GenTlMchemplinkSql.getInsertSql(genTlMchemplinkSql.getMcemDbFields(), genTlMchemplink.getSaveArray()));// add insert sql for detail table
			}
		}
//		CommonFunctions.debugMsg("Dql Data Operator Table:"+sqls);
		return sqls;
	}


	public BAL_GenTlMachinemst update(BAL_GenTlMachinemst genTlMachinemst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_GenTlMachinemstSql genTlMachinemstSql = new BAL_GenTlMachinemstSql();
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql =new BAL_GenTlFunctionallocnSql();
		String machineno=genTlMachinemst.getMchmKeyid();
		
		try {

			sqls.add(BAL_GenTlMachinemstSql.getUpdateSql(genTlMachinemstSql.getMchmDbFields(), genTlMachinemst.getSaveArray()));
			
			BAL_GenTlFunctionallocn newGenTlFunctionallocn = genTlMachinemst.getGenTlFunctionallocn();
		//	sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			String elementid=newGenTlFunctionallocn.getFnlnElementid();
			
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
			String parentId=genTlMachinemst.getMchmElementid();
			String[] prntId = parentId.split("-");
			String cellId=prntId[4];
			String sectionId=prntId[3];
			String factId=prntId[2];
			String elementId=parentId.concat("-").concat(genTlMachinemst.getMchmKeyid());
			String createdby=genTlMachinemst.getMchmCreatedby();
			String originalId=genTlMachinemst.getMchmKeyid();
			String status="U";			
			sqls.add(BAL_GenTlFunctionallocnSql.getEqpUpdateSql(elementid, parentId, machineno));
			sqls.add(BAL_GenTlFunctionallocnSql.getAssemblyEqpUpdateSql(elementid,machineno));
		//	sqls.add(GenTlMachinemstSql.getInsertUdSql(originalId,cellId,sectionId,factId,elementId,createdby,status));
			CommonFunctions.debugMsg(" sqls sqlssqlssqlssqls"+sqls.toString()); 
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}
		
		return genTlMachinemst;
	}
	
	public BAL_GenTlMachinemst delete(String delMode, BAL_GenTlMachinemst genTlMachinemst,EquipmentBean equipmentBean,FunctionalLocn functionalLocn)
			throws BusinessApplicationExceptions,Exception {
    	List<String> sqls = new ArrayList<String>();
		String factoryId=equipmentBean.getMchmfact();
    	String originalId = genTlMachinemst.getMchmKeyid();
    	if(originalId==null)
    	{
    		originalId=functionalLocn.getOriginalId();
    		genTlMachinemst.setMchmKeyid(originalId);
    	}
		
		if(factoryId.equals("FCT0000007")){
			factoryId="FCT0000006" ;
		}
		else{
			factoryId=factoryId;
		}
		String sql = BAL_GenTlFunctionallocnSql.getUnalloactedSectionId(factoryId);

		String parentId = dbActionTemplate.getSingleValue(sql);
		CommonFunctions.debugMsg(sql +" parentId inside the mchDaoImpl"+ parentId);
		sql = BAL_GenTlFunctionallocnSql.getCellflId(parentId);
		String flId = dbActionTemplate.getSingleValue(sql);
		String elementId=parentId+"-"+originalId;
		String[] prntId = parentId.split("-");
		String cellId=prntId[4];
		String sectionId=prntId[3];
		String factId=prntId[2];
		String cretedby=genTlMachinemst.getMchmCreatedby();
		CommonFunctions.debugMsg(" cretedby cretedby cretedby "+cretedby +" "+sectionId +" "+factId +" "+elementId);
		if (!delMode.equals("I"))
		{
			functionalLocValidations.checkOriginalIdExistsinFunctionalLoc(originalId);
			sqls.add(GenTlMchemplinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
			sqls.add(GenTlMchmaintteamlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
			sqls.add(GenTlMachineskillmstSql.getOperatorDeleteSql(genTlMachinemst.getMchmKeyid()));
			sqls.add(GenTlMachineskillmstSql.getMaintainceDeleteSql(genTlMachinemst.getMchmKeyid()));
			sqls.add(GenTlMchparameterlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
			sqls.add(GenTlMchsubmchlinkSql.getDeleteSql(genTlMachinemst.getMchmKeyid()));
		//	sqls.add(GenTlMachinemstSql.getInsertUdSql(originalId,cellId,sectionId,factId,elementId,cretedby,delMode));

		}
			sqls.add(BAL_GenTlMachinemstSql.getDeleteSql(originalId,flId,cellId,delMode));
			sqls.add(BAL_GenTlFunctionallocnSql.getEqpUpdateSql(elementId,parentId,originalId));
			sqls.add(BAL_GenTlFunctionallocnSql.getAssemblyEqpUpdateSql(elementId,originalId));
		//	sqls.add(GenTlMachinemstSql.getInsertUdSql(originalId,cellId,sectionId,factId,elementId,cretedby,delMode));

			//sqls.add(GenTlFunctionallocnSql.getDeleteFunLocSql(delMode,genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			CommonFunctions.debugMsg(" sqlssqlssqlssqlssqls "+sqls.toString());
			dbActionTemplate.executeStatements(sqls);
			
		
		return genTlMachinemst;
	}

	@Override
	public BAL_GenTlMachinemst getselect(String MchmKeyid) throws Exception {
		List<String> sqls = new ArrayList<String>();
		BAL_GenTlMachinemst genTlMachinemst = new BAL_GenTlMachinemst();		
		String sql = BAL_GenTlMachinemstSql.getEquipmentMstSql();
		Object args [] = new Object [] {MchmKeyid};
		genTlMachinemst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
				
		return genTlMachinemst;
	}

	private BAL_GenTlMachinemst gridData(String MchmKeyid,List<String> sqls) throws Exception{
		
		BAL_GenTlMachinemst genTlMachinemst = new BAL_GenTlMachinemst();
		String sql = BAL_GenTlMachinemstSql.getgridData();
		Object args [] = new Object [] {MchmKeyid};
		genTlMachinemst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
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
			String sql = BAL_GenTlMachinemstSql.getOperatorData(factId);
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
			String sql = BAL_GenTlMachinemstSql.OperatorSkillData();
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
			String sql = BAL_GenTlMachinemstSql.MachineData();
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
			String sql = BAL_GenTlMachinemstSql.MaintSkillData();
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
			/*System.out.println("inside dao Impl");
			List<String> params = new ArrayList<String>();
			params.add(section);
			String sql = GenTlMachinemstSql.subEquipmentData();
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;*/
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getEquipmentParm(String machineId) throws Exception {
		try
		{
			System.out.println("inside dao Impl");
			List<String> params = new ArrayList();
			params.add(machineId);
			String sql = BAL_GenTlMachinemstSql.equipmentData();
			System.out.println("After");
			List<String[]> EquipmentPqrqmeter = dbActionTemplate.getDataList(sql, params);
			
			return EquipmentPqrqmeter;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getOperatorRecall(String oprRecall) throws Exception {
		try
		{
			List<String> params = new ArrayList<String>();
			params.add(oprRecall);
			String sql = BAL_GenTlMachinemstSql.RecallOperatorData();
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getOperatorSkillRecall(String recall)
			throws Exception {
		try
		{
			List<String> params = new ArrayList<String>();
			params.add(recall);
			String sql = BAL_GenTlMachinemstSql.RecallOperatorSkillData();
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getMaintainceRecall(String recall) throws Exception {
		try
		{
			List<String> params = new ArrayList<String>();
			params.add(recall);
			String sql = BAL_GenTlMachinemstSql.RecallMaintainceData();
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getMaintainceSkillRecall(String recall)
			throws Exception {
		try
		{
			List<String> params = new ArrayList<String>();
			params.add(recall);
			String sql = BAL_GenTlMachinemstSql.RecallMaintainceSkillData();
			System.out.println("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getMainGrid(CommonFilter commonFilter) throws Exception {
	
		try
		{
			List<String> params = new ArrayList<String>();
		//	params.add();
			if(commonFilter.getActive() == 'S'){
				String sql = BAL_GenTlMachinemstSql.getAll(commonFilter);
				
				List<String[]> operator = dbActionTemplate.getDataList(sql, params);				
				return operator;
			}
			else{
				String sql = BAL_GenTlMachinemstSql.masterGrid(commonFilter);
				
				List<String[]> operator = dbActionTemplate.getDataList(sql, params);
		
			return operator;
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
		
	}

	@Override
	public List<String[]> getEquipmentParameterRecall(String recall) throws Exception {
		try
		{
	
			List<String> params = new ArrayList<String>();
			params.add(recall);
			String sql = BAL_GenTlMachinemstSql.RecallEquipmentParameterData();
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			return operator;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getSubEquipmentRecall(String recall) throws Exception {
		try
		{
			/*System.out.println("inside dao Impl");
			List<String> params = new ArrayList<String>();
			System.out.println("recall"+recall);
			params.add(recall);
			//String sql = GenTlMachinemstSql.recallSubEquipmentData();
			//String sql = GenTlMachinemstSql.subEquipmentData();
			System.out.println("sql:"+sql);
			List<String[]> subEquipment = dbActionTemplate.getDataList(sql, params);
			System.out.println("Query:"+subEquipment);
			return subEquipment;*/
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getAllSubEquipDataForEqp(String sectionId,
			String eqpId) throws Exception {
		try
		{
			System.out.println("inside dao Impl");
			/*List<String> params = new ArrayList<String>();
			System.out.println("recall"+sectionId);
			params.add(sectionId);
			params.add(eqpId);
			params.add(eqpId);*/
			//String sql = GenTlMachinemstSql.recallSubEquipmentData();
			String sql = BAL_GenTlMachinemstSql.subEquipmentData(sectionId,eqpId);
			System.out.println("sql:"+sql);
			//List<String[]> subEquipment = dbActionTemplate.getDataList(sql, params);
			List<String[]> subEquipment = dbActionTemplate.getDataList(sql);
			System.out.println("Query:"+subEquipment);
			return subEquipment;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}
	
	public void deleteOperatorSkill(String machineId, String skillName) throws Exception{
		String sql = BAL_GenTlMachinemstSql.getDeleteOperatorSkillSql();
		Object [] values = {machineId,skillName };
		int [] dataTypes = { Types.VARCHAR, Types.VARCHAR }; 
		
		dbActionTemplate.executeStatement(sql, values, dataTypes);
	}

	public void deleteMainTeamSkill(String machineId, String skillName) throws Exception{
		String sql = BAL_GenTlMachinemstSql.getDeleteMaintTeamSkillSql();
		Object [] values = {machineId,skillName };
		int [] dataTypes = { Types.VARCHAR, Types.VARCHAR }; 
		
		dbActionTemplate.executeStatement(sql, values, dataTypes);
	}
	
	public void deleteOperatorMachineLink(String machineId, String empId) throws Exception{
		String sql = BAL_GenTlMachinemstSql.getDeleteOperatorMachineLinkSql();
		Object [] values = {machineId,empId };
		int [] dataTypes = { Types.VARCHAR, Types.VARCHAR }; 
		
		dbActionTemplate.executeStatement(sql, values, dataTypes);
	}

	public void deleteMaintTeamMachineLink(String machineId, String maintTeamId) throws Exception{
		String sql = BAL_GenTlMachinemstSql.getDeleteMaintTeamMachineLinkSql();
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
			System.out.println("inside dao Impl");		
			
			if(commonFilter.getActive() == 'S'){
				String sql = BAL_GenTlMachinemstSql.getAll(commonFilter);
				System.out.println("sql:"+sql);
				return  dbActionTemplate.getData(sql);	
			}
			else{
				CommonFunctions.debugMsg("Before the Function..."+commonFilter.getFromRow());
				String sql = BAL_GenTlMachinemstSql.masterGrid(commonFilter);
				CommonFunctions.debugMsg("sql the Function..."+sql);
				return  dbActionTemplate.getData(sql);				
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public int selectCount(CommonFilter commonFilter) throws Exception {
		
		System.out.println("Before fetch the data");
	
		//String sql = GenTlMachinemstSql.getEquipmentMstSqlCount(commonFilter);
		String sql = BAL_GenTlMachinemstSql.getCountAll(commonFilter);
		CommonFunctions.debugMsg("Count sql....."+sql);
		String returnData = dbActionTemplate.getSingleValue(sql);
		int retData = Integer.parseInt(returnData);
		return  retData;
		
	}

	@Override
	public List<String[]> getInactive(String keyIds) throws Exception {
		
		try
		{
			System.out.println("inside dao Impl");
			List<String> sqls = new ArrayList<String>();
			
			 String[] temp;
				String delimiter = ",";					
				  temp = ((String) keyIds).split(delimiter);
				  for(int i =0; i < temp.length ; i++)
				  {
					   String data = temp[i];
					   sqls.add(BAL_GenTlMachinemstSql.getActive(data));
					   sqls.add(BAL_GenTlMachinemstSql.getFuncLocActive(data));
				  }	
				  dbActionTemplate.executeStatements(sqls);
			
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getAll(CommonFilter commonFilter) throws Exception {
		
		try
		{
			System.out.println("inside dao Impl");
			List<String> params = new ArrayList<String>();
		//	params.add();
			String sql = BAL_GenTlMachinemstSql.getAll(commonFilter);
			System.out.println("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
		
	}

	@Override
	public List<String[]> getAllCircle(String mchId) throws Exception {
		try
		{
			System.out.println("Catagory");
			List<String> params = new ArrayList<String>();		
			String sql = BAL_GenTlMachinemstSql.getAllCircle(mchId);			
			System.out.println("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public List<String[]> getFormCircle(String mchId) throws Exception {
		try
		{
			System.out.println("Catagory");
			List<String> params = new ArrayList<String>();		
			String sql = BAL_GenTlMachinemstSql.getFormCircle(mchId);
			System.out.println("sql:"+sql);
			List<String[]> operator = dbActionTemplate.getDataList(sql, params);
			
			return operator;
		
		}
		catch (Exception e)
		{
			System.out.println("Exception:"+e.getMessage());
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
		CommonFunctions.debugMsg("test11");
		return "DeleteSuccess";
	}

	@Override
	public List<String[]> getAllPM() throws Exception {
		// TODO Auto-generated method stub
		String sql="  select'','Equipment1','50','10','60','40','80','67','Remarks1' FROM DUAL";
	       sql+=" UNION select '','Equipment2','56','12','68','60','107','88','Remarks2' FROM DUAL";
	       sql+=" UNION select '','Equipment3','62','52','114','50','81','44','Remarks3' FROM DUAL";
	       sql+=" UNION select '','Equipment4','35','30','65','15','43','23','Remarks4'FROM DUAL";
	       sql+=" UNION select '','Equipment5','18','10','28','28','156','100','Remarks5' FROM DUAL";
	       sql+=" UNION select '','Equipment6','85','52','137','100','118','73','Remarks6' FROM DUAL";
	       sql+=" UNION select '','Equipment7','75','0','75','50','67','67','Remarks7' FROM DUAL";
	       System.out.println("sql "+sql);
			List<String[]> dataList =  dbActionTemplate.getDataList(sql);
			return dataList;
	}

	@Override
	public List<String[]> getAllPMgrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		String sql="  " + "select '','',' ','','','','','','','','','','','' FROM DUAL";
		sql+=" UNION  select'Keyid','Equipment','July','July','August','August','September','September','October','October','November','November','December','December' FROM DUAL";
		 sql+=" UNION select'Keyid','Equipment',' PM Adherence %','Cumulative','PM Adherence %','Cumulative','PM Adherence %','Cumulative','PM Adherence %','Cumulative','PM Adherence %','Cumulative','PM Adherence %','Cumulative' FROM DUAL";
		 sql+=" UNION select'','Equipment1','50','10','60','40','80','67','87','34','65','21','94','41' FROM DUAL";
		 sql+=" UNION select '','Equipment2','56','12','68','60','107','88','56','78','85','39','43','52' FROM DUAL";
		System.out.println("sql "+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql);
		return dataList;
	}



	public String getSapMchNumber(String mchineId) throws Exception{
		return dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_MACHINEMST, "MCHM_TECHNICALID", "MCHM_KEYID", mchineId);
	}
	
}

