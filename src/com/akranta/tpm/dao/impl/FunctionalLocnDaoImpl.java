package com.akranta.tpm.dao.impl;

import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GenTlPbumstBean;
import com.akranta.tpm.bean.GenTlSbumstBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.FunctionalLocnDao;
import com.akranta.tpm.dao.sql.GenTlEmployeemstSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.GenTlLayoutfieldimgSql;
import com.akranta.tpm.dao.sql.GenTlPbumstSql;
import com.akranta.tpm.dao.sql.GenTlSbumstSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.FactoryLayout;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.FuntLocnElementDispModel;
import com.akranta.tpm.model.GenTlFunctionallocn;
import com.akranta.tpm.model.GenTlLayoutfieldimg;
import com.akranta.tpm.model.GenTlPbumst;
import com.akranta.tpm.model.GenTlSbumst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;




public class FunctionalLocnDaoImpl implements FunctionalLocnDao {
	/*Created By Suresh.K on Oct 10*/
	private DBActionTemplate dbActionTemplate; 
	private GenTlFunctionallocnSql genTlFunctionallocnSql = null;
	
	public FunctionalLocnDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		genTlFunctionallocnSql = new GenTlFunctionallocnSql();
		//genTlLayoutfieldimgSql = new GenTlLayoutfieldimgSql();
	}
	public  List<FunctionalLocn> getFunctionalLocnValues(String userId) throws Exception
	{
		
		
			ReportProcedures reportProcedures = new ReportProcedures();
			
			reportProcedures.setFunctionName("ADM_FN_MENUMST");
			
			List<String []> resultList = reportProcedures.execute();
			
			//return fillMenuTree(rs);
			return fillLocation(resultList);
	
	}

	public  List<FunctionalLocn> getAllLocation(FunctionalLocn functionalLocn) throws Exception
	{
		try
		{
			String sql = "select "
				   		+ "    a.fnln_originalid, "
				   		+ "    a.fnln_elementid,"
				   		+ "    a.fnln_parentid, "
				   		+ "    a.fnln_elementtype,"
				   		+ "    b.displaycode    "
				   		+ " FROM "
				   		+ "    gen_tl_functionallocn a "
				   		+ "    JOIN  "
				   		+ "    ftl_vw_layoutdisplaycode b "
				   		+ " ON "
				   		+ "    a.fnln_originalid = b.originalid ";
			
			if( functionalLocn.getElementId().equals("1"))
				sql +=	"and fnln_elementtype ='CMP' ";
			else
				sql +=	"and fnln_parentid ='"+functionalLocn.getElementId() +"' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
				
			sql +=" and fnln_active='Y' order by DISPLAYCODE ";
			
			List resultList = dbActionTemplate.getDataList(sql);
			
			return fillLocation(resultList);
		}
		catch (Exception e)
		{			
			throw new Exception(e.getMessage()); 			
		}
		
	}
	
	public  List<FunctionalLocn> getAllfnLocation(FunctionalLocn functionalLocn) throws Exception
	{
		try
		{	
		String sql = "select "
		   		+ "    a.fnln_originalid, "
		   		+ "    a.fnln_elementid,"
		   		+ "    a.fnln_parentid, "
		   		+ "    a.fnln_elementtype,"
		   		+ "    b.displaycode    "
		   		+ " FROM "
		   		+ "    gen_tl_functionallocn a "
		   		+ "   LEFT JOIN  "
		   		+ "    ftl_vw_layoutdisplaycode b "
		   		+ " ON "
		   		+ "    a.fnln_originalid = b.originalid; ";     
		    
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
		    if( functionalLocn.getElementType().equals("F"))
		    {
		    	sql +=	"and fnln_elementtype = 'SBU'";
		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentId() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
		    }
		    if( functionalLocn.getElementType().equals("SBU"))
		    {
		    	sql +=	"and fnln_elementtype = 'PBU'";
		    	sql +=	"and fnln_parentid like '"+functionalLocn.getParentId() +"%' and FNLN_ELEMENTTYPE != '" + functionalLocn.getElementType() +"'";
		    }
		    if( functionalLocn.getElementType().equals("PBU"))
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
		com.akranta.tpm.utils.CommonMessage.debugMsg("elemId ::::"+elemId);
		String[] elemArr = elemId.split("-");
		com.akranta.tpm.utils.CommonMessage.debugMsg("elemARR ::::"+elemArr[elemArr.length-1]);
		String layout = elemArr[elemArr.length-1].substring(0, 3);
		String sql = null;		
		if(layout.equals("MCH") || layout.equals("ASM"))
			sql = GenTlFunctionallocnSql.getParentElemSql(elemId);
		else
		{
			layout = getField(layout);
			sql = GenTlFunctionallocnSql.getAddedElemSql(layout);
		}	
		com.akranta.tpm.utils.CommonMessage.debugMsg("get parent elemtn  "+sql);
		return dbActionTemplate.getDataList(sql);
	}
	
	public List<String []> getChildElem(List<String> childElem,String formField,String start,String end,String key,GridParams gridParams) throws Exception
	{
		String sql = null;
		if(childElem.size() > 0 )
		{
			sql = GenTlFunctionallocnSql.getChildSql(childElem.size(),childElem.get(0).substring(0, 3),start,end,key,gridParams);
		}
		else
		{
			sql = GenTlFunctionallocnSql.getAllChildSql(formField,start,end,key,gridParams);		
			com.akranta.tpm.utils.CommonMessage.debugMsg("All Chlid Sql "+sql);		
			return dbActionTemplate.getDataList(sql);
		}
		
		//List<String[]> dataList =  dbActionTemplate.getDataList(sql);
		//com.akranta.tpm.utils.CommonMessage.debugMsg("Size Of datalist : "+dataList.size());
		return dbActionTemplate.getDataList(sql, childElem);
	}
	
	public String getTotalCount(List<String> childElem,String formfield) throws Exception
	{
		String sql = null;
		com.akranta.tpm.utils.CommonMessage.debugMsg("childElem.size() :"+childElem.size());
		if(childElem.size() > 0 )
		{
			sql = GenTlFunctionallocnSql.getTotalChildSql(childElem.size(),childElem.get(0).substring(0, 3));
		}
		else
		{
			sql = GenTlFunctionallocnSql.getAllChildSqlTotal(formfield);		
			com.akranta.tpm.utils.CommonMessage.debugMsg("All totalCount Sql "+sql);
			//List<String[]> dList =  dbActionTemplate.getDataList(sql);
			//com.akranta.tpm.utils.CommonMessage.debugMsg("Size Of dList : "+dList.size());
			return dbActionTemplate.getSingleValue(sql);
		}
		List<String[]> dataList =  dbActionTemplate.getDataList(sql,childElem);
		com.akranta.tpm.utils.CommonMessage.debugMsg("Size Of datalist : "+dataList.size());
		String returnCount = null;
		if(dataList.size() >0)
			returnCount = dataList.get(0)[0];
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
		for(int i =0;i<locnValues.size();i++)
		{
		    sql = "";
			String[] funcnVal = locnValues.get(i).split(",");
			String funcnval=locnValues.get(0);
			String funcKeyId = dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12, "FNLN", "MMYY", "Y");
			if(funcnval.substring(0, 3).equals("FCT")||funcnval.substring(0, 3).equals("LIN"))
				parentField = functionalLocn.getParentId().substring(7);
			
			/*sql += "INSERT INTO GEN_TL_FUNCTIONALLOCN VALUES('"+funcnVal[0]+"','";
			sql += parentField+"-"+funcnVal[0]+"','"+functionalLocn.getParentId()+"','";
			sql += funcnVal[2]+"-"+ funcnVal[1]+"','"+funcnVal[2]+"-"+ funcnVal[1]+"','";
			sql += getElemType(funcnVal[0].substring(0, 3))+"','Y','"+funcKeyId+"')";
			sqls.add(sql);*/
		}
		dbActionTemplate.executeStatements(sqls);
		return functionalLocn;
	}
	
	@Override
	public GenTlSbumst sbucreate(GenTlSbumst newGenTlSbumst,
			GenTlSbumst exitGenTlSbumst, GenTlSbumstBean genTlSbumstBean)
			throws Exception {
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlSbumstSql genTlSbumstSql = new GenTlSbumstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql();
		
		try{
			                                                     //GenTlCellmstSql.TBL_GEN_TL_CELLMST, 10, "CEL", "MMYY", "Y"
			newGenTlSbumst.setSbutKeyid(dbActionTemplate.getSequenceNumber(GenTlSbumstSql.TBL_GEN_TL_SBUMST, 10, "SBU", "", "Y")); // set the sequnce number 
			
			
			GenTlFunctionallocn newGenTlFunctionallocn = newGenTlSbumst.getGenTlFunctionallocn();
			
			
			newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(genTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12, "FNLN", "", " ")); // set the sequnce number
			
			StringBuilder Sql=new StringBuilder();
			
		    Sql.append( "   SELECT FNLN_KEYID  FROM  GEN_TL_FUNCTIONALLOCN  where FNLN_ORIGINALID=  '").append(newGenTlSbumst.getSbutLocationid()).append("'");		
			String fiId =dbActionTemplate.getSingleValue(Sql.toString());
			//newGenTlSbumst.setSbutFlid(newGenTlFunctionallocn.getFnlnKeyid());
			 String sbuname=newGenTlSbumst.getSbutName();
			 String sbucode=newGenTlSbumst.getSbutCode();
			 ////CommonMessage.debugMsg("SBUNAME:"+sbuname+"SBUCODE:"+sbucode);
			 String sbunameupp=sbuname.toUpperCase();
			 String sbucodeupp=sbucode.toUpperCase();
			 ////CommonMessage.debugMsg("SBUNAME:"+sbunameupp+"SBUCODE:"+sbucodeupp);
			 newGenTlSbumst.setSbutCode(sbucodeupp);
			 newGenTlSbumst.setSbutName(sbunameupp);
			newGenTlFunctionallocn.setFnlnActive("Y");
			newGenTlFunctionallocn.setFnlnDescription(sbunameupp);
			newGenTlFunctionallocn.setFnlnDisplaycode(sbucodeupp);
	
			
			newGenTlFunctionallocn.setFnlnElementid(newGenTlSbumst.getSbutCompanyid()+"-"+newGenTlSbumst.getSbutLocationid()+"-"+newGenTlSbumst.getSbutKeyid());
			
			newGenTlFunctionallocn.setFnlnOriginalid(newGenTlSbumst.getSbutKeyid());
			
			newGenTlFunctionallocn.setFnlnParentid(newGenTlSbumst.getSbutCompanyid()+"-"+newGenTlSbumst.getSbutLocationid());
			
			newGenTlFunctionallocn.setFnlnElementtype("SBU");
			
			newGenTlSbumst.setSbutFlid(fiId);
			
			
            sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			sqls.add(GenTlSbumstSql.getInsertSql(genTlSbumstSql.getSbutDbFields(), newGenTlSbumst.getSaveArray())); // add insert sql for master table
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			////CommonMessage.debugMsg("BEFORE CALLING PROCEDURE");
			List<String> paramValues = new ArrayList<String>();
					Object[] outParam    = null;
			dbActionTemplate.processPLSQLProcedures("REFRESH_MV",paramValues,outParam);
					////CommonMessage.debugMsg("procedure returned");
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		}
		return newGenTlSbumst;
	}
	
	@Override
	public GenTlSbumst sbuupdate(GenTlSbumst newGenTlSbumst,
			GenTlSbumst exitGenTlSbumst, GenTlSbumstBean genTlSbumstBean)
			throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		GenTlSbumstSql genTlSbumstSql = new GenTlSbumstSql();
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
		try {
 
			 String sbuname=newGenTlSbumst.getSbutName();
			 String sbucode=newGenTlSbumst.getSbutCode();
			 String sbuflidvalue=newGenTlSbumst.getSbutFlid();
			 ////CommonMessage.debugMsg("newGenTlSbumst.getSbutFlid():::"+sbuflidvalue);
			 ////CommonMessage.debugMsg("SBUNAME:"+sbuname+"SBUCODE:"+sbucode);
			 String sbunameupp=sbuname.toUpperCase();
			 String sbucodeupp=sbucode.toUpperCase();
			 ////CommonMessage.debugMsg("SBUNAME:"+sbunameupp+"SBUCODE:"+sbucodeupp);
			 newGenTlSbumst.setSbutCode(sbucodeupp);
			 newGenTlSbumst.setSbutName(sbunameupp);
			sqls.add(GenTlSbumstSql.getUpdateSql(genTlSbumstSql.getSbutDbFields(), newGenTlSbumst.getSaveArray()));
			GenTlFunctionallocn newGenTlFunctionallocn = newGenTlSbumst.getGenTlFunctionallocn();
			if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnKeyid()))
			{   CommonMessage.debugMsg("Sbu12");
				
				newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
				
			}
			else
				CommonMessage.debugMsg("Sbu1");	
		   // if(UIUtils.isValidKeyId(newGenTlSbumst.getSbutFlid()))
			//newGenTlFunctionallocn.setFnlnKeyid(newGenTlSbumst.getSbutFlid() );
			newGenTlFunctionallocn.setFnlnDescription(sbunameupp);
			newGenTlFunctionallocn.setFnlnDisplaycode(sbucodeupp);
			//////CommonMessage.debugMsg("exitGenTlSbumst.getSbutFlid()::"+exitGenTlSbumst.getSbutFlid());
			////CommonMessage.debugMsg("newGenTlSbumst.getSbutFlid()::"+newGenTlSbumst.getSbutFlid());
			newGenTlFunctionallocn.setFnlnKeyid(sbuflidvalue);
			sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
		////CommonMessage.debugMsg("BEFORE CALLING PROCEDURE");
			List<String> paramValues = new ArrayList<String>();
					Object[] outParam    = null;
			dbActionTemplate.processPLSQLProcedures("REFRESH_MV",paramValues,outParam);
					////CommonMessage.debugMsg("procedure returned");
			
			/*
			 * sqls.add(GenTlCellmstSql.getUpdateSql(genTlCellmstSql.getCellDbFields(), genTlCellmst.getSaveArray()));
			GenTlFunctionallocn newGenTlFunctionallocn = genTlCellmst.getGenTlFunctionallocn();
			////CommonMessage.debugMsg("inside dao impl update for function " +newGenTlFunctionallocn.getFnlnOriginalid());
			//functionalLocValidations.elementExistsinFunctionalLoc(newGenTlFunctionallocn.getFnlnOriginalid());
			
			sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			 * 
			 */
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
			//e.printStackTrace();
		}
		
		return newGenTlSbumst;
	}
	@Override
	public GenTlSbumst sbudelete(GenTlSbumst newGenTlSbumst) throws Exception,BusinessApplicationExceptions {
		try{
			List<String> sqls = new ArrayList<String>();
			GenTlSbumstSql genTlSbumstSql = new GenTlSbumstSql();
			
			String SbuFlid=newGenTlSbumst.getSbutKeyid();
			sqls.add(GenTlSbumstSql.getDeleteSql(genTlSbumstSql.getSbutDbFields(), newGenTlSbumst.getSaveArray()));
			sqls.add(" delete  from GEN_TL_FUNCTIONALLOCN where FNLN_ORIGINALID= '"+SbuFlid+"' ");
			GenTlFunctionallocn newGenTlFunctionallocn = newGenTlSbumst.getGenTlFunctionallocn();
			dbActionTemplate.executeStatements(sqls);
			
		}catch(BusinessApplicationExceptions e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return newGenTlSbumst;
	}
	
	@Override
	public GenTlPbumst pbudelete(GenTlPbumst newGenTlPbumst) throws Exception {
		// TODO Auto-generated method stub
		
		try{
		List<String> sqls = new ArrayList<String>();
		GenTlPbumstSql genTlPbumstSql = new GenTlPbumstSql();
		
		String PbuFlid=newGenTlPbumst.getPbutFlid();
		sqls.add(GenTlPbumstSql.getDeleteSql(genTlPbumstSql.getPbutDbFields(), newGenTlPbumst.getSaveArray()));
		sqls.add(" delete  from GEN_TL_FUNCTIONALLOCN where FNLN_ORIGINALID= '"+PbuFlid+"'  ");
		//dbActionTemplate.executeStatement(GenTlSbumstSql.getDeleteSql(genTlPbumstSql.getPbutDbFields(), newGenTlPbumst.getSaveArray()));
		
		GenTlFunctionallocn newGenTlFunctionallocn = newGenTlPbumst.getGenTlFunctionallocn();
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
	public GenTlPbumst pbucreate(GenTlPbumst newGenTlPbumst,GenTlPbumst exitGenTlPbumst, GenTlPbumstBean genTlPbumstBean)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		GenTlPbumstSql genTlPbumstSql = new GenTlPbumstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql();
		
		try{
			  
			//GenTlCellmstSql.TBL_GEN_TL_CELLMST, 10, "CEL", "MMYY", "Y"
			newGenTlPbumst.setPbutKeyid(dbActionTemplate.getSequenceNumber(GenTlPbumstSql.TBL_GEN_TL_PBUMST, 10, "PBU", "", "Y")); // set the sequnce number 
			
			
			//GenTlFunctionallocn newGenTlFunctionallocn = newGenTlSbumst.getGenTlFunctionallocn();
			
			GenTlFunctionallocn newGenTlFunctionallocn = newGenTlPbumst.getGenTlFunctionallocn();
			
			newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12, "FNLN", "", " ")); // set the sequnce number
			////CommonMessage.debugMsg("newGenTlFunctionallocn.getFnlnKeyid():::"+newGenTlFunctionallocn.getFnlnKeyid());
			newGenTlPbumst.setPbutFlid(newGenTlFunctionallocn.getFnlnKeyid());
			
			StringBuilder Sql=new StringBuilder();
			Sql.append( "   SELECT FNLN_KEYID  FROM  GEN_TL_FUNCTIONALLOCN  where FNLN_ORIGINALID=  '").append(newGenTlPbumst.getLocation()).append("'");		
			CommonMessage.debugMsg("FFFFFFFF     "+Sql );
			String elementId =dbActionTemplate.getSingleValue(Sql.toString());
			
			newGenTlFunctionallocn.setFnlnActive("Y");
			 String pbuname=newGenTlPbumst.getPbutName();
			 String pbucode=newGenTlPbumst.getPbutCode();
			 ////CommonMessage.debugMsg("SBUNAME:"+pbuname+"SBUCODE:"+pbucode);
			 String pbunameupp=pbuname.toUpperCase();
			 String pbucodeupp=pbucode.toUpperCase();
			 ////CommonMessage.debugMsg("SBUNAME:"+pbunameupp+"SBUCODE:"+pbucodeupp);
			 newGenTlPbumst.setPbutCode(pbucodeupp);
			 newGenTlPbumst.setPbutName(pbunameupp);
			
			
			
			newGenTlFunctionallocn.setFnlnDescription(pbunameupp);
			newGenTlFunctionallocn.setFnlnDisplaycode(pbucodeupp);
			
			newGenTlFunctionallocn.setFnlnElementid(newGenTlPbumst.getCompany()+"-"+newGenTlPbumst.getLocation()+"-"+newGenTlPbumst.getPbutSbuid()+"-"+newGenTlPbumst.getPbutKeyid());
			
			newGenTlFunctionallocn.setFnlnOriginalid(newGenTlPbumst.getPbutKeyid());
			
			newGenTlFunctionallocn.setFnlnParentid(newGenTlPbumst.getCompany()+"-"+newGenTlPbumst.getLocation()+"-"+newGenTlPbumst.getPbutSbuid());//+"-"+newGenTlPbumst.getPbutSbuid()
			
			newGenTlFunctionallocn.setFnlnElementtype("PBU");
			
			//newGenTlPbumst.setPbutFlid(elementId);   //tttttt
			
			
            sqls.add(GenTlFunctionallocnSql.getInsertSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
			sqls.add(GenTlPbumstSql.getInsertSql(genTlPbumstSql.getPbutDbFields(), newGenTlPbumst.getSaveArray())); // add insert sql for master table
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			////CommonMessage.debugMsg("BEFORE CALLING PROCEDURE");
			List<String> paramValues = new ArrayList<String>();
					Object[] outParam    = null;
			dbActionTemplate.processPLSQLProcedures("REFRESH_MV",paramValues,outParam);
					////CommonMessage.debugMsg("procedure returned");
			
		}catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception(e.getMessage());
			
		}
		return newGenTlPbumst;
	
	}
	@Override
	public GenTlPbumst pbuupdate(GenTlPbumst newGenTlPbumst,GenTlPbumst exitGenTlPbumst, GenTlPbumstBean genTlPbumstBean)
			throws Exception {
		// TODO Auto-generated method stub
		
		List<String> sqls = new ArrayList<String>();
		GenTlPbumstSql genTlPbumstSql = new GenTlPbumstSql(); 
		GenTlFunctionallocnSql genTlFunctionallocnSql =new GenTlFunctionallocnSql(); 
		try {
			 String pbuname=newGenTlPbumst.getPbutName();
			 String pbucode=newGenTlPbumst.getPbutCode();
			 String pbuflidvalue=newGenTlPbumst.getPbutFlid();
			 ////CommonMessage.debugMsg("pbuflidvalue:::"+pbuflidvalue);
			 ////CommonMessage.debugMsg("SBUNAME:"+pbuname+"SBUCODE:"+pbucode);
			 String pbunameupp=pbuname.toUpperCase();
			 String pbucodeupp=pbucode.toUpperCase();
			 ////CommonMessage.debugMsg("SBUNAME:"+pbunameupp+"SBUCODE:"+pbucodeupp);
			 newGenTlPbumst.setPbutCode(pbucodeupp);
			 newGenTlPbumst.setPbutName(pbunameupp);
			
            
			sqls.add(GenTlPbumstSql.getUpdateSql(genTlPbumstSql.getPbutDbFields(), newGenTlPbumst.getSaveArray()));
			GenTlFunctionallocn newGenTlFunctionallocn = newGenTlPbumst.getGenTlFunctionallocn();
		 if(! UIUtils.isValidKeyId(newGenTlFunctionallocn.getFnlnKeyid()))
			{
			 CommonMessage.debugMsg("Sbu12");
				newGenTlFunctionallocn.setFnlnKeyid(dbActionTemplate.getSequenceNumber(GenTlFunctionallocnSql.TBL_GEN_TL_FUNCTIONALLOCN, 12,"FNL","",""));
				
			}else
				 CommonMessage.debugMsg("Sbu12");
			/*if(UIUtils.isValidKeyId(newGenTlPbumst.getPbutFlid()))
			newGenTlFunctionallocn.setFnlnKeyid(newGenTlPbumst.getPbutFlid() );*/
		 newGenTlFunctionallocn.setFnlnDescription(pbunameupp);
			newGenTlFunctionallocn.setFnlnDisplaycode(pbucodeupp);
			
			////CommonMessage.debugMsg("newGenTlPbumst.setPbutFlid()::"+newGenTlPbumst.getPbutFlid());
			//////CommonMessage.debugMsg("exitGenTlPbumst.getPbutFlid()::"+exitGenTlPbumst.getPbutFlid());
			newGenTlFunctionallocn.setFnlnKeyid(pbuflidvalue);
		  sqls.add(GenTlFunctionallocnSql.getUpdateSql(genTlFunctionallocnSql.getFnlnDbFields(), newGenTlFunctionallocn.getSaveArray()));
			
		   dbActionTemplate.executeStatements(sqls);
		   ////CommonMessage.debugMsg("BEFORE CALLING PROCEDURE");
			List<String> paramValues = new ArrayList<String>();
					Object[] outParam    = null;
			dbActionTemplate.processPLSQLProcedures("REFRESH_MV",paramValues,outParam);
					////CommonMessage.debugMsg("procedure returned");
		
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
				List<String []> updateMchRelated = dbActionTemplate.processFunctionCalls("GEN_FN_INACTIVATEFORMCH",paramVal);
			}
			else
			{
				updateNode(functionalLocn,elementId,"",sqls);
				sql = GenTlFunctionallocnSql.deleteNodeSql(elementId);
				sqls.add(sql);
				dbActionTemplate.executeStatements(sqls);				
			}
		}
		else
		{
			updateNode(functionalLocn,elementId,"",sqls);
			sql = GenTlFunctionallocnSql.deleteNodeSql(elementId);
			sqls.add(sql);
			dbActionTemplate.executeStatements(sqls);
		}
		return functionalLocn;
	}
	public FunctionalLocn deletMachine(FunctionalLocn functionalLocn)throws Exception {
		List<String> sqls = new ArrayList<String>();
		String sql = "";
		String elementId = functionalLocn.getElementId();
		if(com.akranta.tpm.utils.CommonFunctions.isValidKeyId(functionalLocn.getElementType()))
		{
			if(functionalLocn.getElementType().equals("M"))
			{
				List<String> paramVal = new ArrayList<String>();			
				paramVal.add(functionalLocn.getOriginalId());	
			
				List<String []> checkMchExist = dbActionTemplate.processFunctionCalls("GEN_PC_COMMONFUNCTIONS.GEN_FN_CHECKENTRYEXISTSFORMCH",paramVal);
				
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
		updateNode(functionalLocn,elementId,"",sqls);
		sql = GenTlFunctionallocnSql.deleteNodeSql(elementId);
		sqls.add(sql);
		//dbActionTemplate.executeStatements(sqls);
		return functionalLocn;
	}
	public List<String[]> cutValidEqp(String nodeId)throws Exception
	{
		String sql = GenTlFunctionallocnSql.getBdForEqp(nodeId);
		List<String[]> bdmVal =  dbActionTemplate.getDataList(sql);		
		
		String pmsSql = GenTlFunctionallocnSql.CheckPmStnds(nodeId);
		List<String[]> pmsVal =  dbActionTemplate.getDataList(pmsSql);		
			
		if(bdmVal.size()>0 && pmsVal.size()>0)
			return bdmVal;		
		return bdmVal;
	}
	public List<String[]> getBdforEqp(String nodeId)throws Exception
	{
		String sql = GenTlFunctionallocnSql.getBdSql(nodeId);
		List<String[]> bdVal =  dbActionTemplate.getDataList(sql);		
		return bdVal;
	}
	
	/*public FunctionalLocn cutEqp(FunctionalLocn functionalLocn,List<String> parentValues)	throws Exception {
		
		String eqpElemId = functionalLocn.getElementId();
		String subUnit = parentValues.get(0);
		String cell = parentValues.get(1);
		String parentId = dbActionTemplate.getSingleValue("gen_tl_functionallocn", "fnln_ELEMENTID", "fnln_originalid", subUnit);
		com.akranta.tpm.utils.CommonMessage.debugMsg("eqpElemId......................."+eqpElemId);
		String[] elemIdArr = eqpElemId.split("-");
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		String timeFormat = "dd-MMM-yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		String compId =  "{}";
		String locnId =  "{}";
		String factId = "{}";
		String lineId = "{}";
		String cellId = "{}";
		if(elemIdArr.length>3)
		{
			compId = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_FACTORYMST, "FACT_COMPANYID", "FACT_KEYID", elemIdArr[0]);
			locnId = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_FACTORYMST, "FACT_LOCATIONID", "FACT_KEYID", elemIdArr[0]);
			factId = elemIdArr[0];
			lineId = elemIdArr[1];
			cellId = elemIdArr[2];
		}
			
		
		sqls.add(GenTlFunctionallocnSql.updateMchSql());
		sqls.add(GenTlFunctionallocnSql.updateMchChildSql());
		sqls.add(GenTlFunctionallocnSql.updatePhenCauseLinkSql());
		sqls.add(GenTlFunctionallocnSql.updateCellInMchSql());
		sqls.add(GenTlFunctionallocnSql.updateABNSql());
		sqls.add(GenTlFunctionallocnSql.updatePLMSql());
		sqls.add(GenTlFunctionallocnSql.updatePLCalSql());
		sqls.add(GenTlFunctionallocnSql.updateKaizenSql());
		sqls.add(GenTlFunctionallocnSql.updateKaizenHdSql());
		sqls.add(GenTlFunctionallocnSql.updateOplSql());
		sqls.add(GenTlFunctionallocnSql.updateCycletimeMstSql());
		sqls.add(GenTlFunctionallocnSql.updateBDStatusSql());
		sqls.add(GenTlFunctionallocnSql.insertMchHistorySql());
		
		Object [] updateMch	= { parentId+"-"+cell,parentId+"-"+cell+"-"+parentValues.get(2),eqpElemId};
		int [] flTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		Object [] updateMchChild	= { eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),parentValues.get(2)};
		int [] childTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		Object [] updatePhnCseLink	= { eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),parentValues.get(2)};
		int [] phenCseTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		Object [] updateCell	= { cell,parentValues.get(2)};
		int [] cellTypes =  { Types.VARCHAR,Types.VARCHAR};
		
		Object [] updateAbn	= { subUnit,cell,parentValues.get(2)};
		int [] AbnTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		Object [] updateMst	= { parentId.split("-")[0],subUnit,cell,parentValues.get(2)};
		int [] mstTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		Object [] bdStatus	= { parentValues.get(2)};
		int [] statusTypes =  { Types.VARCHAR};
		
		Object [] insertMchHist	= {compId,locnId,factId,lineId,cellId,parentValues.get(2),new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime()),new java.sql.Timestamp( sdf.parse(Constants.futureNullDate +" 00:00").getTime()),"Y",parentValues.get(4),new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime()),new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime())};
		int [] insertMchHistTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP};
		
		valueList.add(updateMch);
		valueList.add(updateMchChild);
		valueList.add(updatePhnCseLink);
		valueList.add(updateCell);
		valueList.add(updateAbn);
		valueList.add(updateMst);
		valueList.add(updateMst);
		valueList.add(updateMst);
		valueList.add(updateMst);
		valueList.add(updateMst);
		valueList.add(updateMst);
		valueList.add(bdStatus);
		valueList.add(insertMchHist);
		
		dataTypes.add(flTypes);
		dataTypes.add(childTypes);
		dataTypes.add(phenCseTypes);
		dataTypes.add(cellTypes);
		dataTypes.add(AbnTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(statusTypes);
		dataTypes.add(insertMchHistTypes);
		
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
		return functionalLocn;
		
	}*/
public FunctionalLocn cutEqp(FunctionalLocn functionalLocn,List<String> parentValues)	throws Exception {
		
		String eqpElemId = functionalLocn.getElementId();
		String subUnit = parentValues.get(0);//DMT
		String cell = parentValues.get(1);//JH
		String parentId = dbActionTemplate.getSingleValue("gen_tl_functionallocn", "fnln_ELEMENTID", "fnln_originalid", subUnit);
		com.akranta.tpm.utils.CommonMessage.debugMsg("eqpElemId......................."+eqpElemId);
		String[] elemIdArr = eqpElemId.split("-");
		List<String> sqls = new ArrayList<String>();
		List<Object[]> valueList  = new ArrayList<Object[]>();
		List<int[]> dataTypes  = new ArrayList<int[]>();
		String timeFormat = "dd-MMM-yyyy HH:mm";
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat,Locale.ENGLISH);
		String compId =  "{}";
		String locnId =  "{}";
		String sbuId = "{}";
		String pbuId = "{}";

		String lineId = "{}";
		String cellId = "{}";
		if(elemIdArr.length>5)
		{
			//compId = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_FACTORYMST, "FACT_COMPANYID", "FACT_KEYID", elemIdArr[0]);
			//locnId = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_FACTORYMST, "FACT_LOCATIONID", "FACT_KEYID", elemIdArr[0]);
			compId = elemIdArr[0];
			locnId = elemIdArr[1];
			sbuId = elemIdArr[2];
			pbuId = elemIdArr[3];
			lineId = elemIdArr[4];
			cellId = elemIdArr[5];
		}


		sqls.add(GenTlFunctionallocnSql.updateMchSql());
		sqls.add(GenTlFunctionallocnSql.updateMchChildSql());
		sqls.add(GenTlFunctionallocnSql.updatePhenCauseLinkSql());
		sqls.add(GenTlFunctionallocnSql.updateCellInMchSql());
		sqls.add(GenTlFunctionallocnSql.updateABNSql());
		sqls.add(GenTlFunctionallocnSql.updatePLMSql());
		sqls.add(GenTlFunctionallocnSql.updatePLCalSql());
		sqls.add(GenTlFunctionallocnSql.updateKaizenSql());
		sqls.add(GenTlFunctionallocnSql.updateKaizenHdSql());
		sqls.add(GenTlFunctionallocnSql.updateOplSql());
		sqls.add(GenTlFunctionallocnSql.updateCycletimeMstSql());
		sqls.add(GenTlFunctionallocnSql.updateBDStatusSql());
		sqls.add(GenTlFunctionallocnSql.updateWorkordersql());
		//sqls.add(GenTlFunctionallocnSql.insertMchHistorySql());
		CommonMessage.debugMsg("1: " +parentId+"-"+cell);
		CommonMessage.debugMsg("2: " +parentId+"-"+cell+"-"+parentValues.get(2));
		CommonMessage.debugMsg("3: " +eqpElemId);


		Object [] updateMch	= { parentId+"-"+cell,parentId+"-"+cell+"-"+parentValues.get(2),eqpElemId};
		int [] flTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		CommonMessage.debugMsg("21: " +eqpElemId);
		CommonMessage.debugMsg("22: " +parentId+"-"+cell+"-"+parentValues.get(2));
		CommonMessage.debugMsg("23: " +eqpElemId);
		CommonMessage.debugMsg("24: " +parentId+"-"+cell+"-"+parentValues.get(2));
		CommonMessage.debugMsg("25: " +parentValues.get(2));

		
		Object [] updateMchChild	= { eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),parentValues.get(2)};
		int [] childTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		Object [] updatePhnCseLink	= { eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),eqpElemId,parentId+"-"+cell+"-"+parentValues.get(2),parentValues.get(2)};
		int [] phenCseTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		Object [] updateCell	= { cell,parentValues.get(2)};
		int [] cellTypes =  { Types.VARCHAR,Types.VARCHAR};
		
		Object [] updateAbn	= { subUnit,cell,parentValues.get(2)};
		int [] AbnTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		Object [] updateMst	= { parentId.split("-")[0],subUnit,cell,parentValues.get(2)};
		int [] mstTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
		
		Object [] bdStatus	= { parentValues.get(2)};
		int [] statusTypes =  { Types.VARCHAR};
		
		Object[] Workorder = { cell,parentValues.get(2)};
		int[] Ordertype = { Types.VARCHAR,Types.VARCHAR};
		/*
		Object [] insertMchHist	= {compId,locnId,sbuId,pbuId,lineId,cellId,parentValues.get(2),new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime()),new java.sql.Timestamp( sdf.parse(Constants.futureNullDate +" 00:00").getTime()),"Y",parentValues.get(4),new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime()),new java.sql.Timestamp( sdf.parse(com.akranta.tpm.utils.CommonFunctions.dateTimeNow()).getTime())};
		int [] insertMchHistTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP,Types.TIMESTAMP};
		*/

		valueList.add(updateMch);
		valueList.add(updateMchChild);
		valueList.add(updatePhnCseLink);
		valueList.add(updateCell);
		valueList.add(updateAbn);
		valueList.add(updateMst);
		valueList.add(updateMst);
		valueList.add(updateMst);
		valueList.add(updateMst);
		valueList.add(updateMst);
		valueList.add(updateMst);
		valueList.add(bdStatus);
		valueList.add(Workorder);
	//	valueList.add(insertMchHist);
		
		dataTypes.add(flTypes);
		dataTypes.add(childTypes);
		dataTypes.add(phenCseTypes);
		dataTypes.add(cellTypes);
		dataTypes.add(AbnTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(mstTypes);
		dataTypes.add(statusTypes);
		dataTypes.add(Ordertype);
		//dataTypes.add(insertMchHistTypes);
		
		dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
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
	sql =  GenTlFunctionallocnSql.deleteNodeSql(eqpElemId);
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
		String sql = GenTlFunctionallocnSql.getOriginalidFromParent(elemID);
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
						 sql += GenTlFunctionallocnSql.delNodeSql(child[i]);
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
		else if(elemType.equals("LIN"))
			elemField = "L";
		else if(elemType.equals("ASM"))
			elemField = "A";
		else if(elemType.equals("SPR"))
			elemField = elemType;
		else if(elemType.equals("FCT"))
			elemField = "F";
		else 
			elemField = elemType;
		return elemField;
	}
	private String getField(String elemType) {
		
		String layout = null;
		if(elemType.equals("CMP"))
			layout = "LCN";
		else if(elemType.equals("LCN"))
			layout = "SBU";
		else if(elemType.equals("SBU"))
			layout = "PBU";
		else if(elemType.equals("PBU"))
			layout = "LIN";
		else if(elemType.equals("LCN"))	
			layout = "FCT";
		else if(elemType.equals("FCT"))
			layout = "LIN";
		else if(elemType.equals("LIN"))
			layout = "CEL";
		else if(elemType.equals("CEL"))
			layout = "MCH";
		else if(elemType.equals("TEM"))
			layout = "MCH";
		return layout;
		
	}
	
	public  List<String[]> getSearchNode(String searchNode) throws Exception
	{
		try
		{
			
			String originalId = null;
			if(searchNode.indexOf("||")>0)
			{
				originalId = searchNode.substring(searchNode.indexOf("||")+2);			
				searchNode = searchNode.substring(0, searchNode.indexOf("||"));
			}		
			
			String sql = GenTlFunctionallocnSql.getSearchNodeSql(searchNode,originalId);
			return dbActionTemplate.getDataList(sql);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
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
			//////CommonMessage.debugMsg("file Name : "+genTlLayoutfieldimg.getLyfiFilename());
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
			dbActionTemplate.saveByteFile(sqls, valueList, dataTypes);
			//dbActionTemplate.executeStatement(sqls, valueList, dataTypes);
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
		////CommonMessage.debugMsg("flid Flidddd444=="+flid+"ROLE ID"+roleId+"employeeId"+employeeId);
		String sql = GenTlEmployeemstSql.getEmployeeFunctionalLocationSql(roleId, flid);
		////CommonMessage.debugMsg("THE RESULT SQL IS"+sql.toString());
		//String sql1 = GenTlEmployeemstSql.getEmployeeFunctionalLocationSqls(roleId, flid,employeeId);
		
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
		
		////CommonMessage.debugMsg(" sql )))))))))))))))))))) " + sql + " employeeId " + employeeId);
		List<String[]> data = dbActionTemplate.getDataList(sql, arg);
		////CommonMessage.debugMsg("DATA"+data);
		
		
		FactoryLayout factoryLayout = null;
		if( data.size() > 0 ){
			String [] row = data.get(0); 
			factoryLayout  = new FactoryLayout();
			FuntLocnElementDispModel company = new FuntLocnElementDispModel();
			company.setKeyid(row[0]);
			company.setCode(row[1]);
			company.setName(row[2]);
			factoryLayout.setCompany(company);
			
			FuntLocnElementDispModel location = new FuntLocnElementDispModel();
			location.setKeyid(row[3]);
			location.setCode(row[4]);
			location.setName(row[5]);
			factoryLayout.setLocation(location);
			
			FuntLocnElementDispModel sbu = new FuntLocnElementDispModel();
			sbu.setKeyid(row[6]);
			sbu.setCode(row[7]);
			sbu.setName(row[8]);
			factoryLayout.setSbu(sbu);
			
			FuntLocnElementDispModel pbu = new FuntLocnElementDispModel();
			pbu.setKeyid(row[9]);
			pbu.setCode(row[10]);
			pbu.setName(row[11]);
			factoryLayout.setPbu(pbu);
			
			
			
			
			//fact//
			/* FuntLocnElementDispModel factory = new FuntLocnElementDispModel();
			factory.setKeyid(row[6]);
			factory.setCode(row[7]);
			factory.setName(row[8]);
			factoryLayout.setFactory(factory);
			*/
			
			FuntLocnElementDispModel section = new FuntLocnElementDispModel();
			section.setKeyid(row[12]);
			section.setCode(row[13]);
			section.setName(row[14]);
			factoryLayout.setSection(section);
			
			FuntLocnElementDispModel cell = new FuntLocnElementDispModel();
			cell.setKeyid(row[15]);
			cell.setCode(row[16]);
			cell.setName(row[17]);
			factoryLayout.setCell(cell);
			
			FuntLocnElementDispModel machine = new FuntLocnElementDispModel();
			machine.setKeyid(row[18]);
			machine.setCode(row[19]);
			machine.setName(row[20]);
			factoryLayout.setMachine(machine);
			
			FuntLocnElementDispModel flidd = new FuntLocnElementDispModel();
			flidd.setKeyid(row[21]);
			flidd.setCode(row[24]+"##"+ row[22]);
			flidd.setName(row[23]);
			
			factoryLayout.setFlid(flidd);
			
			//factoryLayout.setMachine(null);
			//getFuncLocnId(factoryLayout);
		}
		
		return factoryLayout;
	}
	public FactoryLayout getFactoryLayoutElements(FactoryLayout factoryLayout) throws Exception{
		////CommonMessage.debugMsg("INSIDE THE FUNCLOCDAOIMPL"+factoryLayout);
		String sql=  GenTlFunctionallocnSql.getFactoryLayoutElementsSql(factoryLayout);
		////CommonMessage.debugMsg("SQL IN GETFATORYLAYOUT ELEMNETS"+sql);
		
		
		if( sql != "" && sql.length() > 0){
			List<String[]> data = dbActionTemplate.getDataList(sql); 
			////CommonMessage.debugMsg("DATE IN DA IMPL"+data);
			if(data.size() >0){
				int dataLength =data.get(0).length ;
				String [] row = data.get(0); 
				int index = 0, slab =2;
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
				/*
				if( dataLength >=index+2 ){
					factoryLayout.getFactory().setKeyid(row[index++]);
					factoryLayout.getFactory().setCode(row[index++]);
					factoryLayout.getFactory().setName(row[index++]);
				}
				*/
				if( dataLength >= index + 2 ){	
					////CommonMessage.debugMsg("INSIDE THE SBU");
					factoryLayout.getSbu().setKeyid(row[index++]);
					factoryLayout.getSbu().setCode(row[index++]);
					factoryLayout.getSbu().setName(row[index++]);
				}
				if( dataLength >= index + 2 ){
					////CommonMessage.debugMsg("INSIDE THE PBU");
					factoryLayout.getPbu().setKeyid(row[index++]);
					factoryLayout.getPbu().setCode(row[index++]);
					factoryLayout.getPbu().setName(row[index++]);
				}
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
					////CommonMessage.debugMsg("INSIDE THE FLID");
					factoryLayout.getFlid().setKeyid(row[index++]);
					factoryLayout.getFlid().setCode(row[index+2]+"##"+ row[index++]);
					factoryLayout.getFlid().setName(row[index++]);
				}
				//	getFuncLocnId(factoryLayout);
				////CommonMessage.debugMsg("return fatcory layout"+factoryLayout);
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
		
		com.akranta.tpm.utils.CommonMessage.debugMsg("factoryLayout.getCompany().getKeyid()"+factoryLayout.getCompany().getKeyid());
		
		if(UIUtils.isValidKeyId(factoryLayout.getLocation().getKeyid()))
			elementId += "-"+factoryLayout.getLocation().getKeyid();		
		//fact// if(UIUtils.isValidKeyId(factoryLayout.getFactory().getKeyid()))
		//fact// 	elementId += "-"+factoryLayout.getFactory().getKeyid();				
		if(factoryLayout.getSbu() != null && UIUtils.isValidKeyId(factoryLayout.getSbu().getKeyid()))
			elementId += "-"+factoryLayout.getSbu().getKeyid();
		if(factoryLayout.getPbu() != null && UIUtils.isValidKeyId(factoryLayout.getPbu().getKeyid()))
			elementId += "-"+factoryLayout.getPbu().getKeyid();		
		if(factoryLayout.getSection() != null && UIUtils.isValidKeyId(factoryLayout.getSection().getKeyid()))
			elementId += "-"+factoryLayout.getSection().getKeyid();
		if(factoryLayout.getCell() != null && UIUtils.isValidKeyId(factoryLayout.getCell().getKeyid()))
			elementId += "-"+factoryLayout.getCell().getKeyid();
		if(factoryLayout.getMachine() != null && factoryLayout.getMachine() != null)
		{if(UIUtils.isValidKeyId(factoryLayout.getMachine().getKeyid()))
			elementId += "-"+factoryLayout.getMachine().getKeyid();
		}
		com.akranta.tpm.utils.CommonMessage.debugMsg("elementId==="+elementId);
		return elementId;
	}
	public String getNameForId(String currentSearchId)throws Exception
	{
		
		String retName = null;
		if(currentSearchId.indexOf(":")>0)
		{
			if(currentSearchId.substring(0, 3).equals("MCH")){
				retName = dbActionTemplate.getSingleValue(GenTlFunctionallocnSql.getMachineToSearchSql(currentSearchId.split("::")[1]));
			}
			else if(currentSearchId.substring(0, 3).equals("SPR")){
				retName = dbActionTemplate.getSingleValue(GenTlFunctionallocnSql.getSpareToSearchSql(currentSearchId.split("::")[1]));
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
	public GenTlSbumst fillcontrol(String Sbukeyid) throws Exception {
		// TODO Auto-generated method stub
		//GenTlCellmst genTlCellmst = new GenTlCellmst();
		GenTlSbumst genTlSbumst=new GenTlSbumst();
		String sql = GenTlSbumstSql.getGenTlSbumstSql();
		Object args [] = new Object [] {Sbukeyid};
		genTlSbumst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlSbumst;
	}
	@Override
	public GenTlPbumst fillpbucontrol(String Pbukeyid) throws Exception {
		// TODO Auto-generated method stub
		GenTlPbumst genTlPbumst=new GenTlPbumst();
		String sql = GenTlPbumstSql.getGenTlPbumstSql();
		Object args [] = new Object [] {Pbukeyid};
		genTlPbumst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return genTlPbumst;
	}
/*@Override
	public String procedurecall() throws Exception {
		// TODO Auto-generated method stub
		////CommonMessage.debugMsg("BEFORE CALLING PROCEDURE");
		List<String> paramValues = new ArrayList<String>();
				Object[] outParam    = null;
		dbActionTemplate.processPLSQLProcedures("REFRESH_MV",paramValues,outParam);
				////CommonMessage.debugMsg("procedure returned");
				String result="procedure returned";
				return result;
	}
*/
	/*public String getfunctionalid(String elemId) throws Exception {
		// TODO Auto-generated method stub
		com.akranta.tpm.utils.CommonMessage.debugMsg("elemId ::::"+elemId);
		////CommonMessage.debugMsg("INSIDE THE DAO IMPL");
		//String[] elemArr = elemId.split("-");
	//	com.akranta.tpm.utils.CommonMessage.debugMsg("elemARR ::::"+elemArr[elemArr.length-1]);
		//String layout = elemArr[elemArr.length-1].substring(0, 3);
		
		String sql = null;	
		
		String elementid=elemId.substring(0,3);
		if(elementid.equals("SEC"))
		{
			sql = GenTlCellmstSql.getParentElemSql(elemId);
		}
		else if(elementid.equals("C"))
		{
			
			sql = GenTlCellmstSql.getParentElemSql(elemId);
		}	
		com.akranta.tpm.utils.CommonMessage.debugMsg("get parent elemtn  "+sql);
		String result= dbActionTemplate.getSingleValue("GEN_MV_FLIDHIERARCHY","FLID", "FNLN_ORIGINALID", elemId);
		//dbActionTemplate.executeStatements(sql);
		return result;
	}*/

	
	
	
	
	
}
