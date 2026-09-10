package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.BAL_GenTlPbumstBean;
import com.akranta.tpm.bean.BAL_GenTlSbumstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_FunctionalLocnDao;
import com.akranta.tpm.dao.sql.GenTlCellmstSql;
import com.akranta.tpm.dao.sql.GenTlEmployeemstSql;
import com.akranta.tpm.dao.sql.BAL_GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.GenTlLayoutfieldimgSql;
import com.akranta.tpm.dao.sql.GenTlMachinemstSql;
import com.akranta.tpm.dao.sql.GenTlPbumstSql;
import com.akranta.tpm.dao.sql.GenTlSbumstSql;
import com.akranta.tpm.dao.sql.JhaTlFiveSAuditareamstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.AdmTlUsermst;
import com.akranta.tpm.model.FactoryLayout;
import com.akranta.tpm.model.GenTlMachinemst;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.FuntLocnElementDispModel;
import com.akranta.tpm.model.GenTlCellmst;
import com.akranta.tpm.model.BAL_GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlLayoutfieldimg;
import com.akranta.tpm.model.BAL_GenTlPbumst;
import com.akranta.tpm.model.BAL_GenTlSbumst;
import com.akranta.tpm.utils.CommonFunctions;




public class BAL_FunctionalLocnDaoImpl implements BAL_FunctionalLocnDao {
	/*Created By Suresh.K on Oct 10*/
	private DBActionTemplate dbActionTemplate; 
	private BAL_GenTlFunctionallocnSql genTlFunctionallocnSql = null;
	
	public BAL_FunctionalLocnDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		genTlFunctionallocnSql = new BAL_GenTlFunctionallocnSql();
		//genTlLayoutfieldimgSql = new GenTlLayoutfieldimgSql();
	}
	public  List<FunctionalLocn> getFunctionalLocnValues(String userId) throws Exception
	{
		
		
			ReportProcedures reportProcedures = new ReportProcedures();
			
			reportProcedures.setFunctionName("ADM_PC_ADMINISTRATION.ADM_FN_MENUMST");
			
			List<String []> resultList = reportProcedures.execute();
			
			//return fillMenuTree(rs);
			return fillLocation(resultList);
	
	}

	public  List<FunctionalLocn> getAllLocation(FunctionalLocn functionalLocn) throws Exception
	{
		StringBuilder sql = new StringBuilder("select  fnln_originalid,fnln_elementid,fnln_parentid,fnln_elementtype,nvl(displaycode,FNLN_DISPLAYCODE) displaycode");
		sql.append(" from GEN_TL_FUNCTIONALLOCN,FTL_VW_LAYOUTDISPLAYCODE where fnln_originalid = originalid(+) ");
		
		if( functionalLocn.getElementId().equals("1"))
			sql.append(" and fnln_elementtype ='CMP' ");
		else{
			if( functionalLocn.getElementId() != null && functionalLocn.getElementId().equals(functionalLocn.getParentId()) )
				sql.append(" and fnln_elementid ='").append( functionalLocn.getElementId() ) .append( "'");
			else{
				sql.append(" and fnln_parentid ='").append( functionalLocn.getElementId() ) .append( "'");
				
				if(CommonFunctions.isValidKeyId(functionalLocn.getElementType()) )
					sql.append(" and FNLN_ELEMENTTYPE != '" ).append( functionalLocn.getElementType()).append("'");
			}
		}	
		sql.append(" and fnln_active='Y' order by DISPLAYCODE ");
		
		CommonFunctions.debugMsg(sql);
		List<String[]> resultList = dbActionTemplate.getDataList(sql.toString());
		
		return fillLocation(resultList);
	
		
	}
	
	public  List<FunctionalLocn> getAllfnLocation(FunctionalLocn functionalLocn) throws Exception
	{
		try
		{	String sql = "";
			sql = "select  fnln_originalid,fnln_elementid,fnln_parentid,fnln_elementtype,displaycode";
		    sql += " from GEN_TL_FUNCTIONALLOCN,FTL_VW_LAYOUTDISPLAYCODE where fnln_originalid = originalid(+) ";
		        
		    
		    if( functionalLocn.getElementType().equals("FL"))
		    {				
		    	sql +=	"and fnln_elementtype = 'CMP'";
		    }
		    if( functionalLocn.getElementType().equals("CMP"))
		    {
		    	sql +=	"and fnln_elementtype ='LCN'";
		    	sql +=	"and fnln_parentid like '"+ functionalLocn.getParentId() +"' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
		    }
		    if( functionalLocn.getElementType().equals("LCN"))
		    {
		    	//sql +=	"and fnln_elementtype = 'F'";
		    	sql +=	"and fnln_parentid like '"+ functionalLocn.getParentNumber()+"' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
		    }
		    /*if( functionalLocn.getElementType().equals("F"))
		    {
		    	sql +=	"and fnln_elementtype = 'SBU'";
		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentId() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
		    }
		    if( functionalLocn.getElementType().equals("SBU"))
		    {
		    	sql +=	"and fnln_elementtype = 'PBU'";
		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentId() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
		    }*/
		    if( functionalLocn.getElementType().equals("FCT") || functionalLocn.getElementType().equals("F"))
		    {
		    	sql +=	"and fnln_elementtype = 'L'";
		    	sql +=	"and fnln_parentid like'"+functionalLocn.getParentId() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
		    }
		   
		    if( functionalLocn.getElementType().equals("L"))
		    {
		    	sql +=	"and fnln_elementtype = 'C'";
		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentId() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
		    }	
		    
		    if( functionalLocn.getElementType().equals("C"))
		    {
		    	sql +=	"and fnln_elementtype = 'M'";
		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentId() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
		    }	
		    if( functionalLocn.getElementType().equals("M"))
		    {
		    	sql +=	"and fnln_elementtype = 'A'";
		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentId() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
		    }	
			
				sql +=" and fnln_active='Y'";
				List resultList = dbActionTemplate.getDataList(sql);
			
			return fillLocation(resultList);
			
		}
		catch (Exception e)
		{			
			throw new Exception(e.getMessage()); 			
		}
		
	}
	
	public List<String []> getParentElem(String elemId) throws Exception
	{
		String[] elemArr = elemId.split("-");
		com.akranta.tpm.utils.CommonFunctions.debugMsg("elemARR ::::"+elemArr[elemArr.length-1]);
		String layout = elemArr[elemArr.length-1].substring(0, 3);
		String sql = null;		
		if(layout.equals("MCH") || layout.equals("ASM"))
			sql = BAL_GenTlFunctionallocnSql.getParentElemSql(elemId);
		else
		{
			layout = getField(layout);
			sql = BAL_GenTlFunctionallocnSql.getAddedElemSql(layout);
		}	
		com.akranta.tpm.utils.CommonFunctions.debugMsg("get parent elemtn  "+sql);
		return dbActionTemplate.getDataList(sql);
	}
	
	public List<String []> getChildElem(List<String> childElem,String formField,String start,String end,String key,GridParams gridParams,String parentId) throws Exception
	{
		String sql = null;
		CommonFunctions.debugMsg("ParentID"+parentId);
		if(childElem != null && childElem.size() > 0 )
		{
			sql = BAL_GenTlFunctionallocnSql.getChildSql(childElem.size(),formField,start,end,key,gridParams,parentId);
		}
		else
		{
			sql = BAL_GenTlFunctionallocnSql.getChildSql(0,formField,start,end,key,gridParams,parentId);
			//sql = GenTlFunctionallocnSql.getAllChildSql(formField,start,end,key,gridParams);		
			com.akranta.tpm.utils.CommonFunctions.debugMsg("All Chlid Sql "+sql);		
			return dbActionTemplate.getDataList(sql);
		}
		
		//List<String[]> dataList =  dbActionTemplate.getDataList(sql);
		com.akranta.tpm.utils.CommonFunctions.debugMsg(" : "+sql);
		return dbActionTemplate.getDataList(sql);
		
	}
	
	public String getTotalCount(List<String> childElem,String elementType,String parentId) throws Exception
	{
		String sql = null;
		//com.akranta.tpm.utils.CommonFunctions.debugMsg("childElem.size() :"+childElem.size());
		if(childElem != null && childElem.size() > 0 )
		{
		//	sql = GenTlFunctionallocnSql.getTotalChildSql(childElem.size(),childElem.get(0).substring(0, 3));
			sql = BAL_GenTlFunctionallocnSql.getTotalChildSql(0,elementType,parentId);
		}
		else
		{
			sql = BAL_GenTlFunctionallocnSql.getTotalChildSql(0,elementType,parentId);
			//sql = GenTlFunctionallocnSql.getAllChildSqlTotal(elementType);		
			CommonFunctions.debugMsg(" 0101010 All totalCount Sql "+sql);
			//List<String[]> dList =  dbActionTemplate.getDataList(sql);
			//CommonFunctions.debugMsg("Size Of dList : ");
			return dbActionTemplate.getSingleValue(sql);
		}
	//	CommonFunctions.debugMsg("All total       Count Sql 1"+sql);
		List<String[]> dataList =  dbActionTemplate.getDataList(sql);//,childElem);
	//	com.akranta.tpm.utils.CommonFunctions.debugMsg("Size Of datalist : "+dataList.size());
		String returnCount = "0" ;
		if(dataList.size() >0)
			returnCount = dataList.get(0)[0];
	//	CommonFunctions.debugMsg("  returnCount " + returnCount);
		return returnCount;
	}
	

	private List<FunctionalLocn> fillLocation(List<String []> resultList) throws SQLException
	{
	
		   List<FunctionalLocn> menus = new ArrayList<FunctionalLocn>();
		   for( String [] row : resultList )
		   {
			   FunctionalLocn fl = new FunctionalLocn();
			   fl.setOriginalId(row[0]);
			   fl.setElementId(row[1]);
			   fl.setParentId(row[2]);
			   fl.setElementType(row[3]);
			   fl.setDisplayCode(row[4]);			
			   menus.add(fl);			   
		   }
  		  return menus;
	 }
	@Override
	public FunctionalLocn create(FunctionalLocn functionalLocn,List<String> locnValues)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>(); 		
		String sql = "";
		String parentField = functionalLocn.getParentId();	
		CommonFunctions.debugMsg("ParentField"+parentField);
		for(int i =0;i<locnValues.size();i++)
		{
		    sql = "";
			String[] funcnVal = locnValues.get(i).split(",");
			
			//CommonFunctions.debugMsg("inside the locn values"+ i +"  "+locnValues.get(1).split(","));
			//String funcnval=locnValues.get(0);
			String funcKeyId = dbActionTemplate.getSequenceNumber(BAL_GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12, "FNLN", "MMYY", "Y");
			//if(funcnval.substring(0, 3).equals("FCT")|| funcnval.substring(0, 3).equals("LIN") || funcnval.substring(0, 3).equals("SEC"))
				//parentField = functionalLocn.getParentId().substring(7);
			CommonFunctions.debugMsg(funcnVal.length+"FuncKeyId  "+funcKeyId +"funcnValfuncnVal"+funcnVal.toString());
			sql += "INSERT INTO GEN_TL_FUNCTIONALLOCN VALUES('"+funcnVal[0]+"','";
			sql += parentField+"-"+funcnVal[0]+"','"+parentField+"','";
			sql += funcnVal[2]+"-"+ funcnVal[1]+"','"+funcnVal[2]+"-"+ funcnVal[1]+"','";
			sql += getElemType(funcnVal[0].substring(0, 3))+"','Y','"+funcKeyId+"')";
			sqls.add(sql);
		}
		dbActionTemplate.executeStatements(sqls);
		return functionalLocn;
	}
	
	public FunctionalLocn update(FunctionalLocn functionalLocn,
			List<String> locnValues) throws Exception {
		
	try{
		List<String> sqls = new ArrayList<String>();
		
		String funcnVal = locnValues.get(0).split(",")[0];
		String parentId = functionalLocn.getParentId();	
				/*sqls.add(GenTlFunctionallocnSql.getEqpUpdateSql(elementId,parentId,originalId));
		
		sqls.add(GenTlFunctionallocnSql.getAssemblyEqpUpdateSql(elementid,originalId));
		dbActionTemplate.executeStatements(sqls);
	*/	
	/*	System.out.println("newGenTlFunctionallocn in dao"+machineno);
		sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
		
		String elementid=newGenTlFunctionallocn.getFnlnElementid();
		System.out.println("Element Id"+elementid);
		
		sqls.add(GenTlFunctionallocnSql.getAssemblyEqpUpdateSql(elementid,machineno));
		dbActionTemplate.executeStatements(sqls);*/
	} catch (Exception e) {
		
		throw new Exception(e.getMessage());
	}
	
		return functionalLocn;
	}

	
	
	
	
	@Override
	public BAL_GenTlSbumst sbucreate(BAL_GenTlSbumst newGenTlSbumst,
			BAL_GenTlSbumst exitGenTlSbumst, BAL_GenTlSbumstBean genTlSbumstBean)
			throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlSbumstSql genTlSbumstSql = new GenTlSbumstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql =new BAL_GenTlFunctionallocnSql();
		
		try{
			                                                     //GenTlCellmstSql.TBL_GEN_TL_CELLMST, 10, "CEL", "MMYY", "Y"
			newGenTlSbumst.setSbutKeyid(dbActionTemplate.getSequenceNumber(GenTlSbumstSql.TBL_GEN_TL_SBUMST, 10, "SBU", "", "Y")); // set the sequnce number 
			
			
			BAL_GenTlFunctionallocn newGenTlFunctionallocn = newGenTlSbumst.getGenTlFunctionallocn();
			
			
			newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(genTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12, "FNLN", "", " ")); // set the sequnce number
			
			StringBuilder Sql=new StringBuilder();
			
		    Sql.append( "   SELECT FNLN_KEYID  FROM  GEN_TL_FUNCTIONALLOCN  where FNLN_ORIGINALID=  '").append(newGenTlSbumst.getSbutLocationid()).append("'");		
			String fiId =dbActionTemplate.getSingleValue(Sql.toString());
			//newGenTlSbumst.setSbutFlid(newGenTlFunctionallocn.getFnlnKeyid());
			
			
			newGenTlFunctionallocn.setFnlnActive("Y");
			newGenTlFunctionallocn.setFnlnDescription(newGenTlSbumst.getSbutName());
			newGenTlFunctionallocn.setFnlnDisplaycode(newGenTlSbumst.getSbutCode());
			
			newGenTlFunctionallocn.setFnlnElementid(newGenTlSbumst.getSbutCompanyid()+"-"+newGenTlSbumst.getSbutLocationid()+"-"+newGenTlSbumst.getSbutKeyid());
			
			newGenTlFunctionallocn.setFnlnOriginalid(newGenTlSbumst.getSbutKeyid());
			
			newGenTlFunctionallocn.setFnlnParentid(newGenTlSbumst.getSbutCompanyid()+"-"+newGenTlSbumst.getSbutLocationid());
			
			newGenTlFunctionallocn.setFnlnElementtype("SBU");
			
			newGenTlSbumst.setSbutFlid(fiId);
			
			
            sqls.add(BAL_GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			sqls.add(GenTlSbumstSql.getInsertSql(genTlSbumstSql.getSbutDbFields(), newGenTlSbumst.getSaveArray())); // add insert sql for master table
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		}
		return newGenTlSbumst;
	}
	
	@Override
	public BAL_GenTlSbumst sbuupdate(BAL_GenTlSbumst newGenTlSbumst,
			BAL_GenTlSbumst exitGenTlSbumst, BAL_GenTlSbumstBean genTlSbumstBean)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		GenTlSbumstSql genTlSbumstSql = new GenTlSbumstSql();
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql =new BAL_GenTlFunctionallocnSql(); 
		try {

			sqls.add(GenTlSbumstSql.getUpdateSql(genTlSbumstSql.getSbutDbFields(), newGenTlSbumst.getSaveArray()));
			BAL_GenTlFunctionallocn newGenTlFunctionallocn = newGenTlSbumst.getGenTlFunctionallocn();
			if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnKeyid()))
			{   CommonFunctions.debugMsg("Sbu12");
				
				newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
				
			}
			else
				CommonFunctions.debugMsg("Sbu1");	
		   // if(UIUtils.isValidKeyId(newGenTlSbumst.getSbutFlid()))
			//newGenTlFunctionallocn.setFnlnKeyid(newGenTlSbumst.getSbutFlid() );
			
			sqls.add(BAL_GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
			/*
			 * sqls.add(GenTlCellmstSql.getUpdateSql(genTlCellmstSql.getCellDbFields(), genTlCellmst.getSaveArray()));
			GenTlFunctionallocn newGenTlFunctionallocn = genTlCellmst.getGenTlFunctionallocn();
			System.out.println("inside dao impl update for function " +newGenTlFunctionallocn.getFnlnOriginalid());
			//functionalLocValidations.elementExistsinFunctionalLoc(newGenTlFunctionallocn.getFnlnOriginalid());
			
			sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			 * 
			 */
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return newGenTlSbumst;
	}
	@Override
	public BAL_GenTlSbumst sbudelete(BAL_GenTlSbumst newGenTlSbumst) throws Exception,BusinessApplicationExceptions {
		try{
			List<String> sqls = new ArrayList<String>();
			GenTlSbumstSql genTlSbumstSql = new GenTlSbumstSql();
			
			String SbuFlid=newGenTlSbumst.getSbutKeyid();
			sqls.add(GenTlSbumstSql.getDeleteSql(genTlSbumstSql.getSbutDbFields(), newGenTlSbumst.getSaveArray()));
			sqls.add(" delete  from GEN_TL_FUNCTIONALLOCN where FNLN_ORIGINALID= '"+SbuFlid+"' ");
			BAL_GenTlFunctionallocn newGenTlFunctionallocn = newGenTlSbumst.getGenTlFunctionallocn();
			dbActionTemplate.executeStatements(sqls);
			
		}catch(BusinessApplicationExceptions e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return newGenTlSbumst;
	}
	
	@Override
	public BAL_GenTlPbumst pbudelete(BAL_GenTlPbumst newGenTlPbumst) throws Exception {
		// TODO Auto-generated method stub
		
		try{
		List<String> sqls = new ArrayList<String>();
		GenTlPbumstSql genTlPbumstSql = new GenTlPbumstSql();
		
		String PbuFlid=newGenTlPbumst.getPbutFlid();
		sqls.add(GenTlPbumstSql.getDeleteSql(genTlPbumstSql.getPbutDbFields(), newGenTlPbumst.getSaveArray()));
		sqls.add(" delete  from GEN_TL_FUNCTIONALLOCN where FNLN_ORIGINALID= '"+PbuFlid+"'  ");
		//dbActionTemplate.executeStatement(GenTlSbumstSql.getDeleteSql(genTlPbumstSql.getPbutDbFields(), newGenTlPbumst.getSaveArray()));
		
		BAL_GenTlFunctionallocn newGenTlFunctionallocn = newGenTlPbumst.getGenTlFunctionallocn();
		dbActionTemplate.executeStatements(sqls);
		
		//dbActionTemplate.getSingleValue(" delete  from GEN_TL_FUNCTIONALLOCN where FNLN_ORIGINALID= '"+PbuFlid+"' ");
		}catch (BusinessApplicationExceptions e){
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return newGenTlPbumst;
	}
	
	
	@Override
	public BAL_GenTlPbumst pbucreate(BAL_GenTlPbumst newGenTlPbumst,BAL_GenTlPbumst exitGenTlPbumst, BAL_GenTlPbumstBean genTlPbumstBean)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlPbumstSql genTlPbumstSql = new GenTlPbumstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql =new BAL_GenTlFunctionallocnSql();
		
		try{
			                                                       //GenTlCellmstSql.TBL_GEN_TL_CELLMST, 10, "CEL", "MMYY", "Y"
			newGenTlPbumst.setPbutKeyid(dbActionTemplate.getSequenceNumber(GenTlPbumstSql.TBL_GEN_TL_PBUMST, 10, "PBU", "", "Y")); // set the sequnce number 
			
			
			//GenTlFunctionallocn newGenTlFunctionallocn = newGenTlSbumst.getGenTlFunctionallocn();
			
			BAL_GenTlFunctionallocn newGenTlFunctionallocn = newGenTlPbumst.getGenTlFunctionallocn();
			
			newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12, "FNLN", "", " ")); // set the sequnce number
			newGenTlPbumst.setPbutFlid(newGenTlFunctionallocn.getFnlnKeyid());
			
			StringBuilder Sql=new StringBuilder();
			Sql.append( "   SELECT FNLN_KEYID  FROM  GEN_TL_FUNCTIONALLOCN  where FNLN_ORIGINALID=  '").append(newGenTlPbumst.getLocation()).append("'");		
			CommonFunctions.debugMsg("FFFFFFFF     "+Sql );
			String elementId =dbActionTemplate.getSingleValue(Sql.toString());
			
			newGenTlFunctionallocn.setFnlnActive("Y");
			newGenTlFunctionallocn.setFnlnDescription(newGenTlPbumst.getPbutName());
			newGenTlFunctionallocn.setFnlnDisplaycode(newGenTlPbumst.getPbutCode());
			
			newGenTlFunctionallocn.setFnlnElementid(newGenTlPbumst.getCompany()+"-"+newGenTlPbumst.getLocation()+"-"+newGenTlPbumst.getPbutSbuid()+"-"+newGenTlPbumst.getPbutKeyid());
			
			newGenTlFunctionallocn.setFnlnOriginalid(newGenTlPbumst.getPbutKeyid());
			
			newGenTlFunctionallocn.setFnlnParentid(newGenTlPbumst.getCompany()+"-"+newGenTlPbumst.getLocation()+"-"+newGenTlPbumst.getPbutSbuid());//+"-"+newGenTlPbumst.getPbutSbuid()
			
			newGenTlFunctionallocn.setFnlnElementtype("PBU");
			
			newGenTlPbumst.setPbutFlid(elementId);   //tttttt
			
			
            sqls.add(BAL_GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			sqls.add(GenTlPbumstSql.getInsertSql(genTlPbumstSql.getPbutDbFields(), newGenTlPbumst.getSaveArray())); // add insert sql for master table
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		}
		return newGenTlPbumst;
	
	}
	@Override
	public BAL_GenTlPbumst pbuupdate(BAL_GenTlPbumst newGenTlPbumst,BAL_GenTlPbumst exitGenTlPbumst, BAL_GenTlPbumstBean genTlPbumstBean)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>();
		GenTlPbumstSql genTlPbumstSql = new GenTlPbumstSql(); 
		BAL_GenTlFunctionallocnSql genTlFunctionallocnSql =new BAL_GenTlFunctionallocnSql(); 
		try {

			sqls.add(GenTlPbumstSql.getUpdateSql(genTlPbumstSql.getPbutDbFields(), newGenTlPbumst.getSaveArray()));
			BAL_GenTlFunctionallocn newGenTlFunctionallocn = newGenTlPbumst.getGenTlFunctionallocn();
		 if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnKeyid()))
			{
			 CommonFunctions.debugMsg("Sbu12");
				newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(BAL_GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
				
			}else
				 CommonFunctions.debugMsg("Sbu12");
			/*if(UIUtils.isValidKeyId(newGenTlPbumst.getPbutFlid()))
			newGenTlFunctionallocn.setFnlnKeyid(newGenTlPbumst.getPbutFlid() );*/
		  sqls.add(BAL_GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
		   dbActionTemplate.executeStatements(sqls);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		return newGenTlPbumst;
}
	public FunctionalLocn deleteNode(FunctionalLocn functionalLocn)throws Exception {
		List<String> sqls = new ArrayList<String>();
		String sql = "";
		String elementId = functionalLocn.getElementId();
		if(com.akranta.tpm.utils.CommonFunctions.isValidKeyId(functionalLocn.getElementType()))
		{
			if(functionalLocn.getElementType().equals("M"))
			{
				List<String> paramVal = new ArrayList<String>();			
				paramVal.add(functionalLocn.getOriginalId());
				paramVal.add(functionalLocn.getRelatedFilter());				
				List<String []> updateMchRelated = dbActionTemplate.processFunctionCalls("GEN_PC_COMMONFUNCTIONS.GEN_FN_INACTIVATEFORMCH",paramVal);
			}
			else
			{
				updateNode(functionalLocn,elementId,"",sqls);
				sql = BAL_GenTlFunctionallocnSql.deleteNodeSql(elementId);
				sqls.add(sql);
				dbActionTemplate.executeStatements(sqls);				
			}
		}
		else
		{
			updateNode(functionalLocn,elementId,"",sqls);
			sql = BAL_GenTlFunctionallocnSql.deleteNodeSql(elementId);
			sqls.add(sql);
			dbActionTemplate.executeStatements(sqls);
		}
		return functionalLocn;
	}
	public FunctionalLocn deletMachine(FunctionalLocn functionalLocn)throws Exception {
		List<String> sqls = new ArrayList<String>();
		String sql = "";
		String elementId = functionalLocn.getElementId();
		CommonFunctions.debugMsg("Element Id :"+elementId);
		if(com.akranta.tpm.utils.CommonFunctions.isValidKeyId(functionalLocn.getElementType()))
		{
			if(functionalLocn.getElementType().equals("M"))
			{
				List<String> paramVal = new ArrayList<String>();
				CommonFunctions.debugMsg("In If Statement");
				paramVal.add(functionalLocn.getOriginalId());	
				CommonFunctions.debugMsg("Original Id :"+functionalLocn.getOriginalId());
				List<String []> checkMchExist = dbActionTemplate.processFunctionCalls("GEN_PC_COMMONFUNCTIONS.GEN_FN_CHECKENTRYEXISTSFORMCH",paramVal);
				CommonFunctions.debugMsg("Original Id :"+checkMchExist.size());
				if(checkMchExist.size()>0)
				{
					
					if(UIUtils.isValidKeyId(checkMchExist.get(0)[0]))
					{
						//if(!checkMchExist.get(0)[0].equals("1"))
							throw new BusinessApplicationExceptions(checkMchExist.get(0)[0]);
					}
				}
			}
		}	
		CommonFunctions.debugMsg("Sql :"+sqls);
		updateNode(functionalLocn,elementId,"",sqls);
		CommonFunctions.debugMsg("Sql1 :"+updateNode(functionalLocn,elementId,"",sqls));
		sql = BAL_GenTlFunctionallocnSql.deleteNodeSql(elementId);
		CommonFunctions.debugMsg("Sql2 :"+sqls);
		sqls.add(sql);
	//	dbActionTemplate.executeStatements(sqls);
		return functionalLocn;
	}/*
	public FunctionalLocn inActivsteMachine(FunctionalLocn functionalLocn)
	throws Exception {
		
		// System.out.println("inside dao impl "+delMode);
			List<String> sqls = new ArrayList<String>();
			//GenTlMachinemstSql genTlMachinemstSql = new GenTlMachinemstSql();
			String factoryId=functionalLocn.getFactId();
	    	String originalId = functionalLocn.getOriginalId();
		
			System.out.println("Factory Id :"+factoryId);
			System.out.println("Original Id"+originalId);
		//	GenTlFunctionallocn newGenTlFunctionallocn = genTlMachinemst.getGenTlFunctionallocn();
			String sql = GenTlFunctionallocnSql.getUnalloactedSectionId(factoryId);
			String parentId = dbActionTemplate.getSingleValue(sql);
			String elementId=parentId+"-"+originalId;
			System.out.println("Sql :"+sql);
			System.out.println("PArentId"+parentId);
			System.out.println("Element Id"+elementId);
			System.out.println("SQL 1 :"+sqls);
		
		
		CommonFunctions.debugMsg("In Activate machine :");
		System.out.println("inside process");
		sqls.add(GenTlMachinemstSql.getDeleteSql(delMode, genTlMachinemstSql.getMchmDbFields(), genTlMachinemst.getSaveArray()));
	//	sqls.add(GenTlMachinemstSql.getDeleteSql(peArr, dataArray))
		sqls.add(GenTlFunctionallocnSql.getEqpUpdateSql(elementId,parentId,originalId));
		sqls.add(GenTlFunctionallocnSql.getAssemblyEqpUpdateSql(elementId,originalId));

		//sqls.add(GenTlFunctionallocnSql.getDeleteFunLocSql(delMode,genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
     return functionalLocn;
   }*/
	public List<String[]> cutValidEqp(String nodeId)throws Exception
	{
		String sql = BAL_GenTlFunctionallocnSql.getBdForEqp(nodeId);
		List<String[]> bdmVal =  dbActionTemplate.getDataList(sql);		
		
		String pmsSql = BAL_GenTlFunctionallocnSql.CheckPmStnds(nodeId);
		List<String[]> pmsVal =  dbActionTemplate.getDataList(pmsSql);		
			
		if(bdmVal.size()>0 && pmsVal.size()>0)
			return bdmVal;		
		return bdmVal;
	}
	public List<String[]> getBdforEqp(String nodeId)throws Exception
	{
		String sql = BAL_GenTlFunctionallocnSql.getBdSql(nodeId);
		List<String[]> bdVal =  dbActionTemplate.getDataList(sql);		
		return bdVal;
	}
	
	public FunctionalLocn cutEqp(FunctionalLocn functionalLocn,List<String> parentValues)	throws Exception {
		
		String eqpElemId = functionalLocn.getElementId();
		GenTlMachinemst genTlMachinemst= new GenTlMachinemst();
	//	com.akranta.tpm.utils.CommonFunctions.debugMsg("eqpElemId......................."+eqpElemId);
		String Unit = parentValues.get(0);
		String subUnit  = parentValues.get(1);
		String cell = parentValues.get(2);
		String createdBy = parentValues.get(3);
		CommonFunctions.debugMsg(Unit);
		CommonFunctions.debugMsg(subUnit);
		CommonFunctions.debugMsg(cell);
		String originalId=functionalLocn.getOriginalId();
		String parentId = dbActionTemplate.getSingleValue("gen_tl_functionallocn", "fnln_ELEMENTID", "fnln_originalid", subUnit);
		String cellElementId=parentId+"-"+cell;
		String sql = BAL_GenTlFunctionallocnSql.getCellflId(cellElementId);
		String flId = dbActionTemplate.getSingleValue(sql);
		//String flid= dbActionTemplate.getSingleValue("gen_tl_functionallocn", "fnln_ELEMENTID", "fnln_originalid", subUnit);
		CommonFunctions.debugMsg(parentId);
		
		CommonFunctions.debugMsg("originalId) "+originalId);
		CommonFunctions.debugMsg("Cell ElementId " +cellElementId);
		CommonFunctions.debugMsg("Cell ElementId "+flId);
	//	String eqpElemId=parentId+"-"+originalId;
		com.akranta.tpm.utils.CommonFunctions.debugMsg("eqpElemId......................1236."+parentValues.toString());
		
		String[] elemIdArr = eqpElemId.split("-");
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		String timeFormat = "dd-MMM-yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		String compId =  "{}";
		String locnId =  "{}";
		String factId = "{}";
		String sectionId = "{}";
		String cellId = "{}";
		if(elemIdArr.length>3)
		{
			compId = elemIdArr[0] ; //dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_FACTORYMST, "FACT_COMPANYID", "FACT_KEYID", elemIdArr[0]);
			locnId = elemIdArr[1] ; //dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_FACTORYMST, "FACT_LOCATIONID", "FACT_KEYID", elemIdArr[0]);
			factId = elemIdArr[2];
			sectionId = elemIdArr[3];
			cellId = elemIdArr[4];
		}
		String costcentreId =  dbActionTemplate.getSingleValue("Select CELL_COSTCENTREID FROM GEN_TL_CELLMST WHERE CELL_KEYID ='"+cell+"'");
		String mchFlid = dbActionTemplate.getSingleValue("Select FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE FNLN_ORIGINALID ='"+originalId+"'");
		CommonFunctions.debugMsg("costcentreId  "+costcentreId);
		CommonFunctions.debugMsg("mchFlid  "+mchFlid);
		CommonFunctions.debugMsg("eLEMENTiD  "+parentId+"-"+cell+"-"+originalId);
		String status="U";		
		//String cretedby=genTlMachinemst.getMchmCreatedby();
			
		sqls.add(BAL_GenTlFunctionallocnSql.updateMchFlidSql());
		sqls.add(BAL_GenTlFunctionallocnSql.updateMchSql());
		sqls.add(BAL_GenTlFunctionallocnSql.updateMchChildSql());
		sqls.add(BAL_GenTlFunctionallocnSql.updatePhenCauseLinkSql());
		sqls.add(BAL_GenTlFunctionallocnSql.updateCellInMchSql());
		sqls.add(BAL_GenTlFunctionallocnSql.updateABNSql());
		sqls.add(BAL_GenTlFunctionallocnSql.updatePLMSql());    //  fl el 
		sqls.add(BAL_GenTlFunctionallocnSql.updatePLCalSql());  //  fl el
		sqls.add(BAL_GenTlFunctionallocnSql.updateKaizenSql()); //  fl el
		sqls.add(BAL_GenTlFunctionallocnSql.updateKaizenHdSql()); // old 
		sqls.add(BAL_GenTlFunctionallocnSql.updateOplSql()); // fl el
		sqls.add(BAL_GenTlFunctionallocnSql.updateCycletimeMstSql());  //fl el
		sqls.add(BAL_GenTlFunctionallocnSql.updateBDStatusSql());
		sqls.add(BAL_GenTlFunctionallocnSql.insertMchHistorySql());
	//	sqls.add(GenTlFunctionallocnSql.insertMchUdSql());

		Object [] updateMchFlidSql	= { flId,originalId};
		int [] MchFlid =  { Types.VARCHAR,Types.VARCHAR};
		
	//	Object [] updateMch	= { parentId+"-"+cell,parentId+"-"+cell+"-"+parentValues.get(2),eqpElemId};
		Object [] updateMch	= { parentId+"-"+cell,parentId+"-"+cell+"-"+originalId,eqpElemId};
		int [] flTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
	//	Object [] updateMchChild	= { eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),parentValues.get(2)};
		Object [] updateMchChild	= { eqpElemId,parentId+"-"+cell+"-"+originalId,eqpElemId,parentId+"-"+cell+"-"+originalId,originalId};
		int [] childTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
	//	Object [] updatePhnCseLink	= { eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),parentValues.get(2)};
		Object [] updatePhnCseLink	= { eqpElemId,parentId+"-"+cell+"-"+originalId,eqpElemId,parentId+"-"+cell+"-"+originalId,originalId};
		int [] phenCseTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
//		Object [] updateCell	= { cell,parentValues.get(2)};
		Object [] updateCell	= { cell,costcentreId,originalId};
		int [] cellTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
//		Object [] updateAbn	= { subUnit,cell,parentValues.get(2)};
		Object [] updateAbn	= { subUnit,cell,originalId};
		int [] AbnTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		
		Object [] updateFLELMst	= { factId,sectionId,cell,mchFlid,parentId+"-"+cell+"-"+originalId,originalId};
		int [] mstFLELTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};

		//Object [] updateMst	= { parentId.split("-")[0],subUnit,cell,parentValues.get(2)};
		Object [] updateMst	= { factId,sectionId,cell,originalId};
		int [] mstTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
	//	Object [] bdStatus	= { parentValues.get(2)};
		Object [] bdStatus	= {originalId};
		int [] statusTypes =  { Types.VARCHAR};
		
	//	Object [] insertMchHist	= {compId,locnId,factId,sectionId,cellId,parentValues.get(2),new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime()),new java.sql.Timestamp( sdf.parse(Constants.futureNullDate +" 00:00").getTime()),"Y",parentValues.get(4),new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime()),new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime())};
		Object [] insertMchHist	= {compId,locnId,factId,sectionId,cellId,originalId,new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime()),new java.sql.Timestamp( sdf.parse(Constants.futureNullDate +" 00:00").getTime()),"Y",parentValues.get(4),new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime()),new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime())};
		int [] insertMchHistTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP};
		System.out.println(insertMchHistTypes);
		
		Object [] insertMchUdSql	= {originalId,cellId,sectionId,factId,eqpElemId,status,createdBy,new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime())};
		int [] insertMchUdTypes =     { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP};
		System.out.println(insertMchUdTypes);
		
		valueList.add(updateMchFlidSql);
		valueList.add(updateMch);
		valueList.add(updateMchChild);
		valueList.add(updatePhnCseLink);
		valueList.add(updateCell);
		valueList.add(updateAbn);
		valueList.add(updateFLELMst);
		valueList.add(updateFLELMst);
		valueList.add(updateFLELMst);
		valueList.add(updateMst);
		valueList.add(updateFLELMst);
		valueList.add(updateFLELMst);
		valueList.add(bdStatus);
		valueList.add(insertMchHist);
		valueList.add(insertMchUdSql);
		
		dataTypes.add(MchFlid);
		dataTypes.add(flTypes);
		dataTypes.add(childTypes);
		dataTypes.add(phenCseTypes);
		dataTypes.add(cellTypes);
		dataTypes.add(AbnTypes);
		dataTypes.add(mstFLELTypes);
		dataTypes.add(mstFLELTypes);
		dataTypes.add(mstFLELTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(mstFLELTypes);
		dataTypes.add(mstFLELTypes);
		dataTypes.add(statusTypes);
		dataTypes.add(insertMchHistTypes);
		dataTypes.add(insertMchUdTypes);
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		//List<String> paramValues = new ArrayList<String>();	
		dbActionTemplate.processPLSQLProcedures("REFRESH_MV", null, null);
		return functionalLocn;
		
	}
	public FunctionalLocn cutEqps(FunctionalLocn functionalLocn,List<String> parentValues)	throws Exception {
// TODO Auto-generated method stub
	List<String> sqls = new ArrayList<String>();
	String sql = "";
	String eqpElemId = functionalLocn.getElementId();	
	
	String subUnit = parentValues.get(0);
	String cell = parentValues.get(1);
	/* Retrieving Parent Id to insert */
	String parentId = dbActionTemplate.getSingleValue("gen_tl_functionallocn", "fnln_ELEMENTID", "fnln_originalid", subUnit);
	String[] funcnVal = eqpElemId.split("-");
	/* UPDATE CHILD VALUES */
	updateNode(functionalLocn,eqpElemId,parentId+"-"+cell,sqls);
	/* Cut the Equipment */
	sql =  BAL_GenTlFunctionallocnSql.deleteNodeSql(eqpElemId);
	sqls.add(sql);
	
	sql = "";
	/* Insert Functional Locn */
	insertFunLocn(functionalLocn,funcnVal[3],parentId+"-"+cell,getElemType(funcnVal[3].substring(0, 3)),sqls);
	/*sql += "INSERT INTO GEN_TL_FUNCTIONALLOCN VALUES('"+funcnVal[3]+"','";
	sql += parentId+"-"+cell+"-"+funcnVal[3]+"','"+parentId+"-"+cell+"','";
	sql += functionalLocn.getDisplayCode()+"','"+functionalLocn.getDisplayCode()+"','";
	sql += "MCH','Y')";*/

	sqls.add(sql);
	dbActionTemplate.executeStatements(sqls);
	return functionalLocn;
}
	public FunctionalLocn copyNode(FunctionalLocn functionalLocn,String eqpId)	throws Exception {
		List<String> sqls = new ArrayList<String>();	
		String copiedElemId = functionalLocn.getElementId();
		String[] funcnVal = copiedElemId.split("-");		
		//String parentId = dbActionTemplate.getSingleValue("gen_tl_functionallocn", "fnln_ELEMENTID", "fnln_originalid", eqpId);
		updateNode(functionalLocn,copiedElemId,eqpId,sqls);
		insertFunLocn(functionalLocn,funcnVal[funcnVal.length-1],eqpId,getElemType(funcnVal[funcnVal.length-1].substring(0, 3)),sqls);
		dbActionTemplate.executeStatements(sqls);
		return functionalLocn;
	}
	
	private List<String> updateNode(FunctionalLocn functionalLocn,String elemID,String parentId,List<String> sqls) throws Exception {
		String sql = BAL_GenTlFunctionallocnSql.getOriginalidFromParent(elemID);
		String[] funcnVal = elemID.split("-");		
		List <String[]> childValues = dbActionTemplate.getDataList(sql);
		if(childValues.size()>0)
		{
			for(String[] child: childValues)
			{
				 for( int i = 0 ;i < child.length ; i++)
		         {
					if(functionalLocn.getTempParentId()== null)
					{
						 sql = "";
						 sql += BAL_GenTlFunctionallocnSql.delNodeSql(child[i]);
						 sqls.add(sql);
					}
					
					if(parentId.length()>0)
					  insertFunLocn(functionalLocn,child[i],parentId+"-"+funcnVal[funcnVal.length-1],getElemType(child[i].substring(0, 3)),sqls);
		         }
			}
		}
		return sqls;
		
	}
	private List<String> insertFunLocn(FunctionalLocn functionalLocn,String originalID,String parentId,String elemType,List<String> sqls) throws Exception {
		String sql = "";
		sql += "INSERT INTO GEN_TL_FUNCTIONALLOCN VALUES('"+originalID+"','";
		sql += parentId+"-"+originalID+"','"+parentId+"','";
		sql += functionalLocn.getDisplayCode()+"','"+functionalLocn.getDisplayCode()+"','";
		sql += elemType+"','Y')";
		sqls.add(sql);
		return sqls;
	}

	private String getElemType(String elemType) {
		// TODO Auto-generated method stub
		String elemField = null;
		if(elemType.equals("LCN"))
			elemField = elemType;
		else if(elemType.equals("MCH"))
			elemField = "M";
		else if(elemType.equals("CEL"))
			elemField = "C";
		else if(elemType.equals("LIN") || elemType.equals("SEC"))
			elemField = "L";
		else if(elemType.equals("ASM"))
			elemField = "A";
		else if(elemType.equals("SPR"))
			elemField = elemType;
		else if(elemType.equals("FCT"))
			elemField = "FCT";
		else 
			elemField = elemType;
		return elemField;
	}
	private String getField(String elemType) {
		
		String layout = null;
		if(elemType.equals("CMP"))
			layout = "LCN";
	/*	else if(elemType.equals("LCN"))
			layout = "SBU";
		else if(elemType.equals("SBU"))
			layout = "PBU";
		else if(elemType.equals("PBU"))
			layout = "SEC";*/
		else if(elemType.equals("LCN"))	
			layout = "FCT";
		else if(elemType.equals("FCT"))
			layout = "SEC";
		else if(elemType.equals("LIN") || elemType.equals("SEC"))
			layout = "CEL";
		else if(elemType.equals("CEL"))
			layout = "MCH";
		else if(elemType.equals("TEM"))
			layout = "MCH";
		return layout;
		
	}
	
	public  List<String[]> getSearchNode(String searchNode) throws Exception
	{
		String originalId = null;
		if(searchNode.indexOf("||")>0)
		{
			originalId = searchNode.substring(searchNode.indexOf("||")+2);			
			searchNode = searchNode.substring(0, searchNode.indexOf("||"));
		}		
		
		String sql = BAL_GenTlFunctionallocnSql.getSearchNodeSql(searchNode,originalId);
		CommonFunctions.debugMsg(" sql " + sql);
		return dbActionTemplate.getDataList(sql);
		
	}
	public GenTlLayoutfieldimg saveBlobImage(GenTlLayoutfieldimg genTlLayoutfieldimg)throws Exception
	{
		try{
			List<String> sqls = new ArrayList<String>();
			List<Object[]> valueList  = new ArrayList<Object[]>();
			List<int[]> dataTypes  = new ArrayList<int[]>();
			
			sqls.add(GenTlLayoutfieldimgSql.getDeleteBlobSql());
			sqls.add(GenTlLayoutfieldimgSql.getInsertSqlForImg());
			
			Object [] delValue	= { genTlLayoutfieldimg.getLyfiKeyid()};
			int [] delTypes =  { Types.VARCHAR};
			//System.out.println("file Name : "+genTlLayoutfieldimg.getLyfiFilename());
			java.sql.Timestamp  timeStamp = com.akranta.tpm.dao.impl.CommonFunctions.convertoSqlTimeStamp(genTlLayoutfieldimg.getLyfiModifiedon()); 
			java.sql.Timestamp  timeStamp2 = com.akranta.tpm.dao.impl.CommonFunctions.convertoSqlTimeStamp(genTlLayoutfieldimg.getLyfiCreatedon()); 
			Object [] insValues = { genTlLayoutfieldimg.getLyfiKeyid(),genTlLayoutfieldimg.getLyfiBloblength(),genTlLayoutfieldimg.getLyfiBlobimage(),
									genTlLayoutfieldimg.getLyfiFilename(),genTlLayoutfieldimg.getLyfiTempfield1(),genTlLayoutfieldimg.getLyfiTempfield2(),
									genTlLayoutfieldimg.getLyfiTempfield3(),genTlLayoutfieldimg.getLyfiActive(),genTlLayoutfieldimg.getLyfiCreatedby(),
									timeStamp2,timeStamp};
			int [] insDataType = { Types.VARCHAR,Types.INTEGER , Types.BLOB,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.CHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP};
			
			valueList.add(delValue);
			valueList.add(insValues);
			
			dataTypes.add(delTypes);
			dataTypes.add(insDataType);
			
			dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return genTlLayoutfieldimg;
		
	}
	
	public GenTlLayoutfieldimg select(String locnId) throws Exception
	{
		try
		{
			GenTlLayoutfieldimg genTlLayoutfieldimg = new GenTlLayoutfieldimg();
			String sql = null;
			
			sql = GenTlLayoutfieldimgSql.selectSql();			
			Object [] args =  new Object [] { locnId };
			genTlLayoutfieldimg.setSaveArray(dbActionTemplate.getDataArr(sql,args ) );
			return  genTlLayoutfieldimg;
		}
		catch(Exception e)	{
			throw new Exception(e.getMessage());
		}
	}
	
	public GenTlLayoutfieldimg getLayoutImg(GenTlLayoutfieldimg genTlLayoutfieldimg) throws Exception
	{
		String fileName = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_LAYOUTFIELDIMG, "LYFI_FILENAME", "LYFI_KEYID", genTlLayoutfieldimg.getLyfiKeyid());
		if( fileName != null ){
			if( fileName.lastIndexOf("/") > -1 )
				fileName = fileName.substring(0,fileName.lastIndexOf("/")+1);
			
			String fileNamePath = genTlLayoutfieldimg.getLyfiBlobimage()+  fileName; 
			String condSql = " AND LYFI_KEYID = '" + genTlLayoutfieldimg.getLyfiKeyid() + "'";
			
			String imgFileName = genTlLayoutfieldimg.getLyfiFilename()+fileName;
			genTlLayoutfieldimg.setLyfiFilename(imgFileName);			
			
		//	if(CommonFunctions.isFileExists(fileNamePath)){
				
		//	}else{
			
				dbActionTemplate.restoreFile(TableNames.TBL_GEN_TL_LAYOUTFIELDIMG, "LYFI_BLOBIMAGE", condSql, fileNamePath);
		//	}	
			return 	genTlLayoutfieldimg;
		}
		return null;
		
	}
	public FunctionalLocn deleteImage(String nodeId)throws Exception
	{
		String sql = GenTlLayoutfieldimgSql.getDeleteBlobSql();
		
		Object [] delValue	= { nodeId};
		int [] delTypes =  { Types.VARCHAR};
		dbActionTemplate.executeStatement(sql, delValue, delTypes);
		return null;
	
	}

	public FactoryLayout getEmployeeFunctionalLocation(String employeeId, String roleId, String flid) throws NoDataFoundException, Exception{
		//System.out.println("flid Flidddd444=="+flid);
		String sql = GenTlEmployeemstSql.getEmployeeFunctionalLocationSql(roleId, flid);
		Object [] arg = null ;
		if( ! UIUtils.isValidKeyId(roleId ) ){
			arg = new Object[1];
			arg[0] =  employeeId;
			
		}	
		else{
			arg = new Object[2];
			arg[0] =  employeeId;
			arg[1] =  roleId ;
		}	
		
		//System.out.println(" sql )))))))))))))))))))) " + sql + " employeeId " + employeeId);
		List<String[]> data = dbActionTemplate.getDataList(sql, arg);
		
		
		FactoryLayout factoryLayout = null;
		if( data.size() > 0 ){
			String [] row = data.get(0); 
			factoryLayout  = new FactoryLayout();
			int index = 0;
			FuntLocnElementDispModel company = new FuntLocnElementDispModel();
			
			company.setKeyid(row[index++]);
			company.setCode(row[index++]);
			company.setName(row[index++]);
			factoryLayout.setCompany(company);
			
			FuntLocnElementDispModel location = new FuntLocnElementDispModel();
			location.setKeyid(row[index++]);
			location.setCode(row[index++]);
			location.setName(row[index++]);
			factoryLayout.setLocation(location);
			
		/*	FuntLocnElementDispModel sbu = new FuntLocnElementDispModel();
			sbu.setKeyid(row[index++]);
			sbu.setCode(row[index++]);
			sbu.setName(row[index++]);
			factoryLayout.setSbu(sbu);
			
			FuntLocnElementDispModel pbu = new FuntLocnElementDispModel();
			pbu.setKeyid(row[index++]);
			pbu.setCode(row[index++]);
			pbu.setName(row[index++]);
			factoryLayout.setPbu(pbu);
			
			
			*/
			
			
		    FuntLocnElementDispModel factory = new FuntLocnElementDispModel();
			factory.setKeyid(row[index++]);
			factory.setCode(row[index++]);
			factory.setName(row[index++]);
			factoryLayout.setFactory(factory);
			
			
			FuntLocnElementDispModel section = new FuntLocnElementDispModel();
			section.setKeyid(row[index++]);
			section.setCode(row[index++]);
			section.setName(row[index++]);
			factoryLayout.setSection(section);
			
			FuntLocnElementDispModel cell = new FuntLocnElementDispModel();
			cell.setKeyid(row[index++]);
			cell.setCode(row[index++]);
			cell.setName(row[index++]);
			factoryLayout.setCell(cell);
			
			FuntLocnElementDispModel machine = new FuntLocnElementDispModel();
			machine.setKeyid(row[index++]);
			machine.setCode(row[index++]);
			machine.setName(row[index++]);
			factoryLayout.setMachine(machine);
			
			FuntLocnElementDispModel flidd = new FuntLocnElementDispModel();
			flidd.setKeyid(row[index++]);
			flidd.setName(row[index++]);
			flidd.setCode(row[index++]+"##"+ row[index++]);
			
			
			factoryLayout.setFlid(flidd);
			
			//factoryLayout.setMachine(null);
			//getFuncLocnId(factoryLayout);
		}
		
		return factoryLayout;
	}
	public FactoryLayout getFactoryLayoutElements(FactoryLayout factoryLayout) throws Exception{
		
		String sql=  BAL_GenTlFunctionallocnSql.getFactoryLayoutElementsSql(factoryLayout);
		
		
		if( sql != "" && sql.length() > 0){
			List<String[]> data = dbActionTemplate.getDataList(sql); 
			if(data.size() >0){
				int dataLength =data.get(0).length ;
				String [] row = data.get(0); 
				int index = 0 ;
				if( dataLength >= index + 2 ){
					
					factoryLayout.getCompany().setKeyid(row[index++]);
					factoryLayout.getCompany().setCode(row[index++]);
					factoryLayout.getCompany().setName(row[index++]);
				}
				if( dataLength >=index + 2 ){
					factoryLayout.getLocation().setKeyid(row[index++]);
					factoryLayout.getLocation().setCode(row[index++]);
					factoryLayout.getLocation().setName(row[index++]);
				}
				//fact//
				
				if( dataLength >=index+2 ){
					factoryLayout.getFactory().setKeyid(row[index++]);
					factoryLayout.getFactory().setCode(row[index++]);
					factoryLayout.getFactory().setName(row[index++]);
				}
				
				/*if( dataLength >= index + 2 ){	
					factoryLayout.getSbu().setKeyid(row[index++]);
					factoryLayout.getSbu().setCode(row[index++]);
					factoryLayout.getSbu().setName(row[index++]);
				}
				if( dataLength >= index + 2 ){
					factoryLayout.getPbu().setKeyid(row[index++]);
					factoryLayout.getPbu().setCode(row[index++]);
					factoryLayout.getPbu().setName(row[index++]);
				}*/
				if( dataLength >= index + 2 ){	
					factoryLayout.getSection().setKeyid(row[index++]);
					factoryLayout.getSection().setCode(row[index++]);
					factoryLayout.getSection().setName(row[index++]);
				}
				if( dataLength >= index + 2 ){
					factoryLayout.getCell().setKeyid(row[index++]);
					factoryLayout.getCell().setCode(row[index++]);
					factoryLayout.getCell().setName(row[index++]);
				}
				if( dataLength >= index + 2 ){
					factoryLayout.getMachine().setKeyid(row[index++]);
					factoryLayout.getMachine().setCode(row[index++]);
					factoryLayout.getMachine().setName(row[index++]);
				}
				if( dataLength >= index + 2 ){
					factoryLayout.getFlid().setKeyid(row[index++]);
					factoryLayout.getFlid().setCode(row[index+2]+"##"+ row[index++]);
					factoryLayout.getFlid().setName(row[index++]);
				}
				//	getFuncLocnId(factoryLayout);
				return factoryLayout;
				
			}
		}	
		return null;
	}
	
	private void getFuncLocnId(FactoryLayout factoryLayout) {
		try {
			
		String elementId = getElementId(factoryLayout);	
		if(UIUtils.isValidKeyId(elementId)){
		String sql=  genTlFunctionallocnSql.getFuncLocnId(elementId);
		
			String flid = dbActionTemplate.getSingleValue(sql);
			
			FuntLocnElementDispModel flidData = new FuntLocnElementDispModel();			
			flidData.setKeyid(flid);
			flidData.setCode("{}");
			flidData.setName("{}");
			factoryLayout.setFlid(flidData);
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private String getElementId(FactoryLayout factoryLayout) {
		String elementId = "";
		if(UIUtils.isValidKeyId(factoryLayout.getCompany().getKeyid()))
			elementId += factoryLayout.getCompany().getKeyid();
		
		//com.akranta.tpm.utils.CommonFunctions.debugMsg("factoryLayout.getCompany().getKeyid()"+factoryLayout.getCompany().getKeyid());
		
		if(UIUtils.isValidKeyId(factoryLayout.getLocation().getKeyid()))
			elementId += "-"+factoryLayout.getLocation().getKeyid();		
		 if(UIUtils.isValidKeyId(factoryLayout.getFactory().getKeyid()))
		 	elementId += "-"+factoryLayout.getFactory().getKeyid();				
		//if(factoryLayout.getSbu() != null && UIUtils.isValidKeyId(factoryLayout.getSbu().getKeyid()))
		//	elementId += "-"+factoryLayout.getSbu().getKeyid();
		//if(factoryLayout.getPbu() != null && UIUtils.isValidKeyId(factoryLayout.getPbu().getKeyid()))
		//	elementId += "-"+factoryLayout.getPbu().getKeyid();		
		if(factoryLayout.getSection() != null && UIUtils.isValidKeyId(factoryLayout.getSection().getKeyid()))
			elementId += "-"+factoryLayout.getSection().getKeyid();
		if(factoryLayout.getCell() != null && UIUtils.isValidKeyId(factoryLayout.getCell().getKeyid()))
			elementId += "-"+factoryLayout.getCell().getKeyid();
		if(factoryLayout.getMachine() != null && factoryLayout.getMachine() != null)
		{if(UIUtils.isValidKeyId(factoryLayout.getMachine().getKeyid()))
			elementId += "-"+factoryLayout.getMachine().getKeyid();
		}
		//com.akranta.tpm.utils.CommonFunctions.debugMsg("elementId==="+elementId);
		return elementId;
	}
	public String getNameForId(String currentSearchId)throws Exception
	{
		
		String retName = null;
		if(currentSearchId.indexOf(":")>0)
		{
			if(currentSearchId.substring(0, 3).equals("MCH")){
				retName = dbActionTemplate.getSingleValue(BAL_GenTlFunctionallocnSql.getMachineToSearchSql(currentSearchId.split("::")[1]));
			}
			else if(currentSearchId.substring(0, 3).equals("SPR")){
				retName = dbActionTemplate.getSingleValue(BAL_GenTlFunctionallocnSql.getSpareToSearchSql(currentSearchId.split("::")[1]));
			}
				
		}
		return retName;
	}

	public List<String[]> getGridDetail() throws Exception {
		StringBuffer sql = new StringBuffer();
				
				sql.append("SELECT 'Pillar' , 'Code' , 'Head' , 'SubHead', 'Champion1', 'Champion2', 'Champion3' from dual union all "
						+ "SELECT TPMP_NAME, TPMP_CODE, MAX(H.EMPM_NAME) AS HEAD, MAX(SH.EMPM_NAME) AS SUB_HEAD, NULL, NULL, NULL "
		                + " FROM GEN_TL_TPMPILLARMST, GEN_TL_EMPLOYEEMST H, GEN_TL_EMPLOYEEMST SH "
		                + " WHERE SUBSTR(H.EMPM_KEYID,LENGTH(H.EMPM_KEYID)-2,3) = SUBSTR(TPMP_KEYID,LENGTH(TPMP_KEYID)-2,3)" 
		                + " AND SUBSTR(SH.EMPM_KEYID,LENGTH(SH.EMPM_KEYID)-2,3)= SUBSTR(TPMP_KEYID,LENGTH(TPMP_KEYID)-2,3) +1 "
		                + " group by TPMP_NAME, TPMP_CODE");
		
		
	
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		return gridData;	
	}
	@Override
	public BAL_GenTlSbumst fillcontrol(String Sbukeyid) throws Exception {
		// TODO Auto-generated method stub
		//GenTlCellmst genTlCellmst = new GenTlCellmst();
		BAL_GenTlSbumst genTlSbumst=new BAL_GenTlSbumst();
		String sql = GenTlSbumstSql.getGenTlSbumstSql();
		Object args [] = new Object [] {Sbukeyid};
		genTlSbumst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlSbumst;
	}
	@Override
	public BAL_GenTlPbumst fillpbucontrol(String Pbukeyid) throws Exception {
		// TODO Auto-generated method stub
		BAL_GenTlPbumst genTlPbumst=new BAL_GenTlPbumst();
		String sql = GenTlPbumstSql.getGenTlPbumstSql();
		Object args [] = new Object [] {Pbukeyid};
		genTlPbumst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlPbumst;
	}
	@Override
	public FunctionalLocn inActivsteMachine(FunctionalLocn functionalLocn)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
}
