package com.akranta.tpm.dao.impl;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.SkillCheckListBean;
import com.akranta.tpm.dao.EntTlSkillChecklistDao;
import com.akranta.tpm.dao.sql.EntTlChecklistdtlSql;
import com.akranta.tpm.dao.sql.EntTlChecklistmstSql;
import com.akranta.tpm.dao.sql.EntTlSkillChecklistSql;
import com.akranta.tpm.dao.sql.GenTlEmployeedtlSql;
import com.akranta.tpm.dao.sql.GenTlEmployeemstSql;
//import com.akranta.tpm.dao.sql.GenTlHolidaymstSql;
import com.akranta.tpm.dao.sql.GenTlMachinemstSql;
import com.akranta.tpm.exportreport.ComparisonOperator;
import com.akranta.tpm.exportreport.RGB;
import com.akranta.tpm.exportreport.XLConditionalFormats;
import com.akranta.tpm.model.EntTlChecklistdtl;
import com.akranta.tpm.model.EntTlChecklistmst;
import com.akranta.tpm.model.EntTlSkillChecklist;
import com.akranta.tpm.model.GenTlEmployeedtl;
import com.akranta.tpm.model.GenTlEmployeemst;
//import com.akranta.tpm.model.GenTlHolidaymst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.ExcelUtils;

/* dao implementation */
public class EntTlSkillChecklistDaoImpl implements EntTlSkillChecklistDao {


	private DBActionTemplate dbActionTemplate; 
	private EntTlChecklistmstSql entTlChecklistmstSql = null;
	private EntTlChecklistdtlSql entTlChecklistdtlSql = null;
	
	public EntTlSkillChecklistDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		entTlChecklistmstSql = new EntTlChecklistmstSql();
		entTlChecklistdtlSql = new EntTlChecklistdtlSql();
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	@Override
	public EntTlSkillChecklist create(EntTlSkillChecklist entTlSkillChecklist)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntTlSkillChecklist update(EntTlSkillChecklist entTlSkillChecklist)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntTlSkillChecklist delete(EntTlSkillChecklist entTlSkillChecklist)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntTlChecklistmst selectAll(String keyId,String dtlId) throws Exception {
			
		EntTlChecklistmst entTlChecklistmst = new EntTlChecklistmst();
		EntTlChecklistdtl entTlChecklistdtl = new EntTlChecklistdtl();
	
		String sql = EntTlChecklistmstSql.getSkillCheckListSql();
		
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] {keyId};
		entTlChecklistmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
		sql = EntTlChecklistdtlSql.getCheckListDetailSql();		
		CommonMessage.debugMsg("KeyId..............."+entTlChecklistdtl.getChkdKeyid());
		CommonMessage.debugMsg(sql);
		try{
			Object arg [] = new Object [] {keyId,dtlId};
			entTlChecklistdtl.setSaveArray(dbActionTemplate.getDataArr(sql, arg));			
			entTlChecklistmst.getCheckListDetail().add(entTlChecklistdtl);	
			
		}catch(NoDataFoundException e)
		{
			CommonMessage.debugMsg("test123...."+e.getMessage());
		}		
		return entTlChecklistmst;
		
	}

	@Override
	public EntTlChecklistmst create(EntTlChecklistmst newEntTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception {
		
		/*List<String> sqls = new ArrayList<String>(); 
		EntTlChecklistmstSql entTlChecklistmstSql = new EntTlChecklistmstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		CommonMessage.debugMsg("Inside Save in DaoImpl");
		newEntTlChecklistmst.setChkmKeyid(dbActionTemplate.getSequenceNumber(EntTlChecklistmstSql.TBL_ENT_TL_CHECKLISTMST)); // set the sequnce number
		
		sqls.add(EntTlChecklistmstSql.getInsertSql(entTlChecklistmstSql.getChkmDbFields(), newEntTlChecklistmst.getSaveArray())); // add insert sql for master table
		CommonMessage.debugMsg(sqls);
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls			
		
		return newEntTlChecklistmst;	*/
		
		CommonMessage.debugMsg("Inside Save in DaoImpl");
		
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 		
		newEntTlChecklistmst.setChkmKeyid(dbActionTemplate.getSequenceNumber(EntTlChecklistmstSql.TBL_ENT_TL_CHECKLISTMST));//(EntTlChecklistmstSql.TBL_ENT_TL_CHECKLISTMST));//(EntTlChecklistmstSql.TBL_ENT_TL_CHECKLISTMST, 10, "CHK", "YYMM", "Y"));
		
		sqls.add(EntTlChecklistmstSql.getInsertSql(entTlChecklistmstSql.getChkmDbFields(), newEntTlChecklistmst.getSaveArray())); // add insert sql for master table
		if(newEntTlChecklistmst.getCheckListDetail()!= null && newEntTlChecklistmst.getCheckListDetail().size()>0) // check for detail table data
		{	
		
			EntTlChecklistdtl entTlChecklistdtl = (EntTlChecklistdtl)newEntTlChecklistmst.getCheckListDetail().get(0); // get detail info from list in empployee object
			entTlChecklistdtl.setChkdChkmKeyid(newEntTlChecklistmst.getChkmKeyid());
			entTlChecklistdtl.setChkdKeyid(dbActionTemplate.getSequenceNumber(EntTlChecklistdtlSql.TBL_ENT_TL_CHECKLISTDTL, 10, "CHD", "", "Y"));//(EntTlChecklistdtlSql.TBL_ENT_TL_CHECKLISTDTL));
			
			sqls.add(EntTlChecklistdtlSql.getInsertSql(entTlChecklistdtlSql.getChkdDbFields(), entTlChecklistdtl.getSaveArray()));// add insert sql for detail table
		}		
		dbActionTemplate.executeStatements(sqls); // execute the block of sqls	
		
		return newEntTlChecklistmst;
	}
	
	public List<String[]> selectCheckList(String keyId) throws Exception {
		String sql = EntTlChecklistmstSql.getCheckListBasedOnSkill();
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] { keyId };
		List<String[]> Sqls = dbActionTemplate.getDataList(sql, args);
		CommonMessage.debugMsg(Sqls);
		return Sqls;
	}


	public List<String[]> getAllCheckList() throws Exception {
		
		String sql = EntTlChecklistmstSql.getAllSkillCheckListSql();
		CommonMessage.debugMsg(sql);
		List<String[]> Sqls = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg(Sqls);
		return Sqls;
	}

	@Override
	public EntTlChecklistmst update(EntTlChecklistmst entTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception {
		
		/*List<String> sqls = new ArrayList<String>();
		EntTlChecklistmstSql entTlChecklistmstSql = new EntTlChecklistmstSql();		
		try {
			
			sqls.add(EntTlChecklistmstSql.getUpdateSql(entTlChecklistmstSql.getChkmDbFields(), entTlChecklistmst.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch (Exception e) {	
			
			throw new Exception(e.getMessage());
		}		
		return entTlChecklistmst;		*/
		
		
		List<String> sqls = new ArrayList<String>();
		try {			
			sqls.add(EntTlChecklistmstSql.getUpdateSql(entTlChecklistmstSql.getChkmDbFields(), entTlChecklistmst.getSaveArray()));
			List<EntTlChecklistdtl> entTlChecklistdtls = entTlChecklistmst.getCheckListDetail();
			
			CommonMessage.debugMsg("SQL ---------"+sqls.get(0));
			if( entTlChecklistdtls != null && entTlChecklistdtls.size()> 0 )
			{					
				for( EntTlChecklistdtl entTlChecklistdtl : entTlChecklistdtls ){
					entTlChecklistdtl.setChkdChkmKeyid(entTlChecklistmst.getChkmKeyid());
					if(CommonFunctions.isValidKeyId( entTlChecklistdtl.getChkdKeyid()) )
					{	
						sqls.add(EntTlChecklistdtlSql.getUpdateSql(entTlChecklistdtlSql.getChkdDbFields(), entTlChecklistdtl.getSaveArray()));
					}	
					else{
						entTlChecklistdtl.setChkdKeyid(dbActionTemplate.getSequenceNumber(EntTlChecklistdtlSql.TBL_ENT_TL_CHECKLISTDTL));
						sqls.add(EntTlChecklistdtlSql.getInsertSql(entTlChecklistdtlSql.getChkdDbFields(), entTlChecklistdtl.getSaveArray()));
					}	
				}
				entTlChecklistmst.setCheckListDetail(entTlChecklistdtls);
			}
			
			dbActionTemplate.executeStatements(sqls);			
		} catch (Exception e) {
			
			throw new Exception(e.getMessage());
		}		
		return entTlChecklistmst;
		
		
	}

	@Override
	public EntTlChecklistmst delete(EntTlChecklistmst entTlChecklistmst,SkillCheckListBean skillCheckListBean) throws Exception {
		
		List<String> sqls = new ArrayList<String>();
		EntTlChecklistmstSql entTlChecklistmstSql = new EntTlChecklistmstSql();
		EntTlChecklistdtlSql entTlChecklistdtlSql = new EntTlChecklistdtlSql();
		
		try {				
			List<EntTlChecklistdtl> entTlChecklistdtls = entTlChecklistmst.getCheckListDetail();
			
			
			CommonMessage.debugMsg("length............."+entTlChecklistdtls.size());
			if( entTlChecklistdtls != null && entTlChecklistdtls.size()> 0 )
			{					
				for( EntTlChecklistdtl entTlChecklistdtl : entTlChecklistdtls ){
					entTlChecklistdtl.setChkdChkmKeyid(entTlChecklistmst.getChkmKeyid());
					sqls.add(EntTlChecklistdtlSql.getDeleteSql(entTlChecklistdtlSql.getChkdDbFields(),entTlChecklistdtl.getSaveArray()));
				}
			
				sqls.add(EntTlChecklistmstSql.getDeleteSql(entTlChecklistmstSql.getChkmDbFields(),entTlChecklistmst.getSaveArray()));			
			dbActionTemplate.executeStatements(sqls);
				
			}
			}catch( Exception e){				
				throw new Exception(e.getMessage());
			}
			
		return entTlChecklistmst;	
		
		
		
		
		
		
	}

	private String selectCountVal(EntTlChecklistmst entTlChecklistmst) throws Exception {
		
		String sql = EntTlChecklistdtlSql.getAllDetailCount(entTlChecklistmst);
		CommonMessage.debugMsg(sql);
		String Sqls = dbActionTemplate.getSingleValue(sql);
		CommonMessage.debugMsg(Sqls);
		return Sqls;
	}

	@Override
	public List<String[]> selectCheckListMainGrid() throws Exception {
		
		String sql = EntTlChecklistmstSql.selectCheckListMainGrid();
		CommonMessage.debugMsg(sql);
		List<String[]> Sqls = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg(Sqls);
		return Sqls;
		
	}

	@Override
	public Workbook SkillExportExcel(JSONObject colModel, String rptFormat)	throws Exception {
		ResultSet rs = null;
		   try{
			
			rs =   getSkillResultSet();
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

	private ResultSet getSkillResultSet() {
		try
		{
			CommonMessage.debugMsg("inside dao Impl");					
			
			CommonMessage.debugMsg("Before the Function...");
			String sql = EntTlChecklistmstSql.selectCheckListMainGrid();
			CommonMessage.debugMsg("sql the Function..."+sql);
			return  dbActionTemplate.getData(sql);				
			
		}
		catch (Exception e)
		{
			CommonMessage.debugMsg("Exception:"+e.getMessage());
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public EntTlChecklistmst selectRank(String topicId, String rattingId) throws Exception {
		EntTlChecklistmst entTlChecklistmst = new EntTlChecklistmst();
		EntTlChecklistdtl entTlChecklistdtl = new EntTlChecklistdtl();
				
		String sql = EntTlChecklistmstSql.getselectRank();
		
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] {topicId, rattingId};
		CommonMessage.debugMsg("topicId::::::"+topicId);
		
		CommonMessage.debugMsg("rattingId::::::"+rattingId);
		entTlChecklistmst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
		/*sql = EntTlChecklistdtlSql.getCheckListDetailSql();		
		CommonMessage.debugMsg(sql);
		try{
			entTlChecklistdtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));			
			entTlChecklistmst.getCheckListDetail().add(entTlChecklistdtl);	
			
		}catch(NoDataFoundException e)
		{
			CommonMessage.debugMsg("test123...."+e.getMessage());
		}		*/
		return entTlChecklistmst;
	}

	@Override
	public EntTlChecklistdtl selectDtl(String keyId) throws Exception {
	
		
		EntTlChecklistmst entTlChecklistmst = new EntTlChecklistmst();
		EntTlChecklistdtl entTlChecklistdtl = new EntTlChecklistdtl();
	
		String sql = EntTlChecklistmstSql.selectDtl();
		
		CommonMessage.debugMsg(sql);
		Object args [] = new Object [] {keyId};
		entTlChecklistdtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		
		
		return entTlChecklistdtl;
		
	}

	@Override
	public EntTlChecklistdtl deleteDetail(EntTlChecklistdtl entTlChecklistdtl) throws Exception {
		
		EntTlChecklistmst entTlChecklistmst = new EntTlChecklistmst();
		
		String sql = EntTlChecklistmstSql.deleteDetail(entTlChecklistdtl);
		
		CommonMessage.debugMsg(sql);	
		
		 dbActionTemplate.executeStatement(sql);
		return null;
		
		
		
		
	}

	@Override
	public List<String[]> selectCheckListRating(String topicId, String rattingId)throws Exception {
		
		String sql = EntTlChecklistmstSql.getCheckListBasedTopicRankSql(topicId,rattingId);
		CommonMessage.debugMsg(sql);
		List<String[]> Sqls = dbActionTemplate.getDataList(sql);
		CommonMessage.debugMsg(Sqls);
		return Sqls;
		
	}

	
	
}

