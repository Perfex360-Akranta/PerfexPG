package com.akranta.tpm.dao.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlTrainingareaDao;
import com.akranta.tpm.dao.sql.EntTlSkillmstSql;
import com.akranta.tpm.dao.sql.EntTlTrainingareaSql;
import com.akranta.tpm.dao.sql.EntTlTrainingareaSql;
import com.akranta.tpm.dao.sql.EntTlTrainingareaSql;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.EntTlProgCalendar;
import com.akranta.tpm.model.EntTlSkillmst;
import com.akranta.tpm.model.EntTlTrainingarea;
import com.akranta.tpm.model.EntTlTrainingarea;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlTrainingareaDaoImpl implements EntTlTrainingareaDao {


	private DBActionTemplate dbActionTemplate; 
	public EntTlTrainingareaDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public List<EntTlTrainingarea> getEntTlTrainingareaValues(EntTlTrainingarea EntTlTrainingarea) throws Exception
	{				
		StringBuffer sql= new StringBuffer();
		//String sql = "select  fnln_originalid,fnln_elementid,fnln_parentid,fnln_elementtype,displaycode";
		sql.append(" SELECT  TRAR_KEYID,TRAR_NAME,AREA.TRAR_PARENTID,TRAR_LEVELNO,TRAR_ELEMENTTYPE, ");
		sql.append(" AREA.TRAR_REFID,AREA.TRAR_REFTYPE,TRAR_LOCATIONID,TRAR_REMARKS,CHILDPATH " );		
		sql.append(" FROM " + TableNames.TBL_ENT_TL_TRAININGAREA + "  AREA,ent_vw_trainingAreachildpath");
		sql.append(" WHERE 1=1 AND KEYID=TRAR_KEYID");
		sql.append(" AND TRAR_ACTIVE='Y' ");		
		sql.append(" AND TRAR_KEYID=AREA.TRAR_PARENTID ");
		
		if (CommonFunctions.isValidKeyId(EntTlTrainingarea.getTrarParentid())){
			sql.append(" AND AREA.TRAR_PARENTID='" + EntTlTrainingarea.getTrarParentid() + "'");
		}
		if (CommonFunctions.isValidKeyId(EntTlTrainingarea.getTrarElementtype())){
			sql.append(" AND TRAR_ELEMENTTYPE='" + EntTlTrainingarea.getTrarElementtype() + "'");
		}
		sql.append(" ORDER BY TRAR_KEYID " );	
		CommonMessage.debugMsg(" sql " + sql.toString());
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillTrAreamstList(resultList);		
	}

	public List<EntTlTrainingarea> getAllTrArea(EntTlTrainingarea EntTlTrainingarea) throws Exception
	{		
		StringBuffer sql= new StringBuffer();
		//String sql = "select  fnln_originalid,fnln_elementid,fnln_parentid,fnln_elementtype,displaycode";
		sql.append(" SELECT  TRAR_KEYID,TRAR_NAME,AREA.TRAR_PARENTID,TRAR_LEVELNO,TRAR_ELEMENTTYPE, ");
		sql.append(" AREA.TRAR_REFID,AREA.TRAR_REFTYPE,TRAR_LOCATIONID,TRAR_REMARKS,CHILDPATH " );		
		sql.append(" FROM " + TableNames.TBL_ENT_TL_TRAININGAREA + "  AREA,ent_vw_trainingAreachildpath");
		sql.append(" WHERE 1=1 AND KEYID=TRAR_KEYID");
		sql.append(" AND TRAR_ACTIVE='Y' ");	
		sql.append(" AND TRAR_KEYID<>AREA.TRAR_PARENTID ");
		
		if (CommonFunctions.isValidKeyId(EntTlTrainingarea.getTrarParentid())){
			sql.append(" AND AREA.TRAR_PARENTID='" + EntTlTrainingarea.getTrarParentid() + "'");
		}
		if (CommonFunctions.isValidKeyId(EntTlTrainingarea.getTrarElementtype())){
			sql.append(" AND TRAR_ELEMENTTYPE='" + EntTlTrainingarea.getTrarElementtype() + "'");
		}
		sql.append(" ORDER BY TRAR_KEYID " );
		
		CommonMessage.debugMsg(" sql " + sql.toString());
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillTrAreamstList(resultList);		
	}
	
	public EntTlTrainingarea create(EntTlTrainingarea entTlTrainingarea) 	throws BusinessApplicationExceptions, Exception {
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTrainingareaSql EntTlTrainingareaSql = new EntTlTrainingareaSql(); 		
		try{		
			entTlTrainingarea.setTrarKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_TRAININGAREA, 10, "TRA", null, null)); // set the sequnce number			
			if(!CommonFunctions.isValidKeyId(entTlTrainingarea.getTrarParentid()))			
				entTlTrainingarea.setTrarParentid(entTlTrainingarea.getTrarKeyid());					
			
			sqls.add(EntTlTrainingareaSql.getInsertSql(EntTlTrainingareaSql.getTrarDbFields(), entTlTrainingarea.getSaveArray())); // add insert sql for master table
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls			
		}		
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlTrainingarea;
	}
	public List<EntTlTrainingarea> createList(List<EntTlTrainingarea> entTlTrainingarea)throws ValidationExceptions,BusinessApplicationExceptions, Exception{
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlTrainingareaSql EntTlTrainingareaSql = new EntTlTrainingareaSql(); 		
		try{		
			if (entTlTrainingarea!= null && entTlTrainingarea.size() > 0) 
			{	
				if (!entTlTrainingarea.get(0).getTrarReftype().equals("TOP")){
					for( EntTlTrainingarea newEntTlTrainingarea : entTlTrainingarea)
					{	
						if (!newEntTlTrainingarea.getTrarReftype().equals("TOP")){
							newEntTlTrainingarea.setTrarKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_ENT_TL_TRAININGAREA, 10, "TRA", null, null));
							if(!CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarParentid()))			
								newEntTlTrainingarea.setTrarParentid(newEntTlTrainingarea.getTrarKeyid());	
							sqls.add(EntTlTrainingareaSql.getInsertSql(EntTlTrainingareaSql.getTrarDbFields(), newEntTlTrainingarea.getSaveArray())); // add insert sql for master table}
						}
					}
				dbActionTemplate.executeStatements(sqls); // execute the block of sqls
				}
				else{
					String keyId="";
					String trarKeyId="";
					String userKeyId="";
					trarKeyId=entTlTrainingarea.get(0).getTrarParentid();
					userKeyId=entTlTrainingarea.get(0).getTrarCreatedby();
					for( EntTlTrainingarea newEntTlTrainingarea : entTlTrainingarea)
					{
						if(CommonFunctions.isValidKeyId(newEntTlTrainingarea.getTrarRefid()))
							keyId=keyId+ newEntTlTrainingarea.getTrarRefid() + ",";
					}
					CommonMessage.debugMsg(" trarKeyId:" + trarKeyId);
					CommonMessage.debugMsg(" keyId:" + keyId);
					List<String > paramValues = new ArrayList<String>();			
					paramValues.add(keyId);
					paramValues.add(trarKeyId);
					paramValues.add(userKeyId);
					List<String[]> resultList;	
					resultList = dbActionTemplate.processFunctionCalls("ENT_PC_EDUANDTRAINING.ENT_FN_INSERTTOPIC", paramValues);
					CommonMessage.debugMsg(" resultList Topic size " + resultList.size());
					//dbActionTemplate.processPLSQLProcedures("ENT_INSERTTOPIC", paramValues, null);
				}
			}	
		}		
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlTrainingarea;
		/*
		 EntTlTrainingarea createEntTlTrainingarea = new EntTlTrainingarea();
		for(int i=0;i<=newEntTlTrainingarea.size()-1;i++)
		{	
			//CommonMessage.debugMsg(" newEntTlTrainingareaNAME()" +  newEntTlTrainingarea.get(i).getTrarName());
			CommonMessage.debugMsg(" newEntTlTrainingareaRefId()" +  newEntTlTrainingarea.get(i).getTrarRefid());								
			createEntTlTrainingarea = newEntTlTrainingarea.get(i);
			createEntTlTrainingareaList.add(createEntTlTrainingarea);
			List<String[]> topicList=null;
			topicList=entTlTrainingareaService.getSearchTopicLevelSql(createEntTlTrainingarea.getTrarRefid());
		}
		 */
	}
	
	public EntTlTrainingarea update(EntTlTrainingarea entTlTrainingarea)	throws BusinessApplicationExceptions,Exception { 		
		List<String> sqls = new ArrayList<String>();
		EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql();
		try {
			sqls.add(entTlTrainingareaSql.getUpdateSql(entTlTrainingareaSql.getTrarDbFields(), entTlTrainingarea.getSaveArray()));			
			dbActionTemplate.executeStatements(sqls);	
			return entTlTrainingarea;
		} 
		catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
	}
	
	private List<EntTlTrainingarea> fillTrAreamstList(List<String []> resultList) throws SQLException
	{
	   List<EntTlTrainingarea> menus = new ArrayList<EntTlTrainingarea>();
	   for( String [] row : resultList )
	   {			  
		   EntTlTrainingarea EntTlTrainingarea = new EntTlTrainingarea();
		   EntTlTrainingarea.setTrarKeyid(row[0]);
		   EntTlTrainingarea.setTrarName(row[1]);	
		   EntTlTrainingarea.setTrarParentid(row[2]);
		   EntTlTrainingarea.setTrarLevelno(row[3]);
		   EntTlTrainingarea.setTrarElementtype(row[4]);
		   EntTlTrainingarea.setTrarRefid(row[5]);
		   EntTlTrainingarea.setTrarReftype(row[6]);
		   EntTlTrainingarea.setTrarLocationid(row[7]);
		   EntTlTrainingarea.setTrarRemarks(row[8]);	
		   EntTlTrainingarea.setTrarTempfield1(row[9]);	
		   menus.add(EntTlTrainingarea);			   
	   }
	   return menus;
	 }
	
	public EntTlTrainingarea delete(EntTlTrainingarea entTlTrainingarea)throws BusinessApplicationExceptions,Exception {
		List<String> sqls = new ArrayList<String>();
		String countSql;
		EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql();
		countSql = entTlTrainingareaSql.getCount(entTlTrainingarea);
		CommonMessage.debugMsg("countSql...."+countSql);
		String count = dbActionTemplate.getSingleValue(countSql);
		int cnt = Integer.parseInt(count);
		CommonMessage.debugMsg(count);
		try {
			if(cnt==0)
			{
				sqls.add(entTlTrainingareaSql.getDeleteSql(entTlTrainingareaSql.getTrarDbFields(), entTlTrainingarea.getSaveArray()));
				dbActionTemplate.executeStatements(sqls);				
				return entTlTrainingarea;
			}
			else
				return null;
			
		}catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		
	}

	@Override
	public EntTlTrainingarea select(EntTlTrainingarea entTlTrainingarea) throws Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql();
		String sql = entTlTrainingareaSql.getSelectSql(entTlTrainingareaSql.getTrarDbFields(), entTlTrainingarea.getSaveArray());				
		CommonMessage.debugMsg("DAO SQL : "+sql);
		//String Qaud_keyid=qtmTlDockaudit.getQaudkeyid();
		Object [] args =  new Object [] {};
		entTlTrainingarea.setSaveArray(dbActionTemplate.getDataArr(sql,args));
		CommonMessage.debugMsg("DAO Query:"+entTlTrainingarea.getTrarKeyid());
		return entTlTrainingarea;
	}
	
	@Override
	public List<EntTlTrainingarea> selectList(EntTlTrainingarea entTlTrainingarea)throws Exception {
		CommonMessage.debugMsg("Inside the dao impl");
		EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql();
		String sql = entTlTrainingareaSql.getSelectSql(entTlTrainingareaSql.getTrarDbFields(), entTlTrainingarea.getSaveArray());			
		CommonMessage.debugMsg("DAO SQL : "+sql);
		//String Qaud_keyid=qtmTlDockaudit.getQaudkeyid();
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());	
		CommonMessage.debugMsg(" resultList size " + resultList.size());
		return fillTrAreamstList(resultList);		
	}
	
	@Override
	public  List<String[]> getSearchNode(String searchNode,String originalId) throws Exception
	{
		try
		{		
			EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql();
			String sql = entTlTrainingareaSql.getSearchNodeSql(searchNode,originalId);
			CommonMessage.debugMsg("Search SQL : "+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}
	public List<String []> getParentElem(String elemId) throws Exception
	{
		String[] elemArr = elemId.split("-");
		String layout = elemArr[elemArr.length-1].substring(0, 3);
		String sql = null;		
		if(layout.equals("MCH") || layout.equals("ASM"))
			sql = GenTlFunctionallocnSql.getParentElemSql(elemId);
		else
		{
			//layout = getField(layout);
			sql = GenTlFunctionallocnSql.getAddedElemSql(layout);
		}		
		return dbActionTemplate.getDataList(sql);
	}
	
	public List<String []> getChildElem(EntTlTrainingarea entTlTrainingarea,String start,String end) throws Exception
	{
		String sql = null;
		EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql();
		sql = entTlTrainingareaSql.getChildSql(entTlTrainingareaSql.getTrarDbFields(), entTlTrainingarea.getSaveArray(),start,end);		
		com.akranta.tpm.utils.CommonMessage.debugMsg("All Chlid Sql "+sql);		
		return dbActionTemplate.getDataList(sql);
		
	}
	
	public String getTotalCount(EntTlTrainingarea entTlTrainingarea) throws Exception
	{
		String sql = null;
		EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql();
		
		sql = entTlTrainingareaSql.getAllChildSqlTotal(entTlTrainingareaSql.getTrarDbFields(), entTlTrainingarea.getSaveArray());		
		com.akranta.tpm.utils.CommonMessage.debugMsg("All Chlid Sql "+sql);
		return dbActionTemplate.getSingleValue(sql);
		
	}
	
	public int getTrAreaLevel(EntTlTrainingarea entTlTrainingarea)throws Exception
	{		
		String sql;
		int menuLevel = 0;
		EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql(); 	
		sql=entTlTrainingareaSql.getTrAreaLevelSql(entTlTrainingareaSql.getTrarDbFields(), entTlTrainingarea.getSaveArray());
		CommonMessage.debugMsg(" sql " + sql);
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
		 for( String [] row : resultList )
	    {			  
			 menuLevel= Integer.parseInt( row[0]);			   		   
	    }
		return menuLevel;
	}
	
	public int getTopicLevel(EntTlTrainingarea entTlTrainingarea)throws Exception
	{		
		String sql;
		
		EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql(); 	
		sql=entTlTrainingareaSql.getTopicLevelSql(entTlTrainingarea.getTrarRefid());
		CommonMessage.debugMsg(" sql " + sql);
		String menuLevel = dbActionTemplate.getSingleValue(sql.toString());
		
		return Integer.parseInt(menuLevel);
	}
	
	private List<String[]> getTopicParentSql(String keyId)throws Exception
	{		
		String sql;		
		EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql(); 	
		sql=entTlTrainingareaSql.getSearchTopicLevelSql(keyId);
		CommonMessage.debugMsg(" sql " + sql);
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
		return resultList;		
	}
	
	
	
	public int getConfigTrAreaLevel()throws Exception
	{		
		String sql;
		int menuLevel = 0;
		EntTlTrainingareaSql entTlTrainingareaSql = new EntTlTrainingareaSql(); 
		sql=entTlTrainingareaSql.getConfigTrAreaLevel();
		CommonMessage.debugMsg(" sql " + sql);
		List<String []> resultList = dbActionTemplate.getDataList(sql.toString());
		 for( String [] row : resultList )
	    {			  
			 menuLevel=Integer.parseInt( row[0]);			   		   
	    }
		return menuLevel;
	}

	@Override
	public String deleteTopic(String topicid) throws Exception {
		// TODO Auto-generated method stub
		//String sql = "select count(*) from ENT_TL_TRAININGAREA where TRAR_REFID ='"+topicid+"'";
		String topicCount =  dbActionTemplate.getSingleValue("ENT_TL_TRAININGAREA", "count(TRAR_KEYID)", "TRAR_REFID", topicid);
		int countofTopic = Integer.parseInt(topicCount); 
		String retMsg = null;
		if(countofTopic <=0)
		{
			String delSql = "delete from ENT_TL_TOPICMST WHERE TOPI_KEYID ='"+topicid+"'";
			dbActionTemplate.executeStatement(delSql);
			retMsg = "Data Deleted Successfully";
		}	
		else
			retMsg = "Refference Exist Data Cannot be Deleted";  
		return retMsg;
	}		
}

