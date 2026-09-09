package com.akranta.tpm.dao.impl;





import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.EntTlEmpmanagermstDao;
import com.akranta.tpm.dao.sql.EntTlEmpmanagermstSql;
import com.akranta.tpm.dao.sql.EntTlProgTargetSkillsSql;
//import com.akranta.tpm.dao.sql.GenTlMouldmachinelinkSql;
import com.akranta.tpm.dao.sql.EntTlEmpmanagerdtlSql;
import com.akranta.tpm.model.EntTlEmpmanagerdtl;
import com.akranta.tpm.model.EntTlEmpmanagermstModel;
//import com.akranta.tpm.model.GenTlMouldmachinelink;
//import com.akranta.tpm.model.GenTlMouldmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class EntTlEmpmanagermstDaoImpl implements EntTlEmpmanagermstDao {


	private DBActionTemplate dbActionTemplate; 
	private EntTlEmpmanagerdtlSql entTlEmpmanagerdtlSql = null;
	public EntTlEmpmanagermstDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		entTlEmpmanagerdtlSql = new EntTlEmpmanagerdtlSql();
		
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public EntTlEmpmanagermstModel create(EntTlEmpmanagermstModel entTlEmpmanagermstModel) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		EntTlEmpmanagermstSql entTlEmpmanagermstSql = new EntTlEmpmanagermstSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			entTlEmpmanagermstModel.setEemmKeyid(dbActionTemplate.getSequenceNumber(EntTlEmpmanagermstSql.TBL_ENT_TL_EMPMANAGERMST, 6, "EMM", null, "N")); // set the sequnce number 
			sqls.add(EntTlEmpmanagermstSql.getInsertSql(entTlEmpmanagermstSql.getEemmDbFields(), entTlEmpmanagermstModel.getSaveArray())); // add insert sql for master table
			CommonMessage.debugMsg("Inside  details");
			//Detail(entTlEmpmanagermstModel,sqls);
//			DETAIL
			EntTlEmpmanagerdtl entTlEmpmanagerdtl = (EntTlEmpmanagerdtl)entTlEmpmanagermstModel.getEntTlEmpmanagerdtl(); // get detail info from list in empployee object
    		entTlEmpmanagerdtl.setEemdKeyid(dbActionTemplate.getSequenceNumber(EntTlEmpmanagerdtlSql.TBL_ENT_TL_EMPMANAGERDTL, 6, "EMD", null, "N")); // set the sequnce number
    		entTlEmpmanagerdtl.setEemdEemmKeyid(entTlEmpmanagermstModel.getEemmKeyid());
			sqls.add(EntTlEmpmanagerdtlSql.getInsertSql(entTlEmpmanagerdtlSql.getEemdDbFields(), entTlEmpmanagerdtl.getSaveArray()));// add insert sql for detail table
			String sqlUpCal = " update gen_tl_employeemst set  empm_email = '" + entTlEmpmanagermstModel.getEemmMailid() +"' where EMPM_KEYID = '" + entTlEmpmanagermstModel.getEemmManagerId() +"' ";
			sqls.add(sqlUpCal);
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return entTlEmpmanagermstModel;
	}
	private List<String> Detail(EntTlEmpmanagermstModel entTlEmpmanagermstModel,List<String> sqls) throws Exception
	{
		
		CommonMessage.debugMsg("SIZE OF GRID"+entTlEmpmanagermstModel.getmethodentTlEmpmanagerdtllist().size());
		if(entTlEmpmanagermstModel.getmethodentTlEmpmanagerdtllist()!= null && entTlEmpmanagermstModel.getmethodentTlEmpmanagerdtllist().size()>=0) // check for detail table data
		{
			CommonMessage.debugMsg("detail key id"+entTlEmpmanagermstModel.getEemmManagerId());
			
			
	    	//for(int i =0;i<entTlEmpmanagermstModel.getmethodentTlEmpmanagerdtllist().size();i++)
			//{	
	    		
	    		/// get detail info from list in empployee object
	    		
			//}
		}
		return sqls;
	}
	
	public EntTlEmpmanagermstModel update(EntTlEmpmanagermstModel entTlEmpmanagermst)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		EntTlEmpmanagermstSql entTlEmpmanagermstSql = new EntTlEmpmanagermstSql();
		
		try {

			sqls.add(EntTlEmpmanagermstSql.getUpdateSql(entTlEmpmanagermstSql.getEemmDbFields(), entTlEmpmanagermst.getSaveArray()));
			EntTlEmpmanagerdtl entTlEmpmanagerdtl = (EntTlEmpmanagerdtl)entTlEmpmanagermst.getEntTlEmpmanagerdtl(); // get detail info from list in empployee object
			CommonMessage.debugMsg("Inside Dao update  " +entTlEmpmanagerdtl.getEemdKeyid());
			if(! UIUtils.isValidKeyId( entTlEmpmanagerdtl.getEemdKeyid()))
			{
				
    		entTlEmpmanagerdtl.setEemdKeyid(dbActionTemplate.getSequenceNumber(EntTlEmpmanagerdtlSql.TBL_ENT_TL_EMPMANAGERDTL, 6, "EMD", null, "N")); // set the sequnce number
    		entTlEmpmanagerdtl.setEemdEemmKeyid(entTlEmpmanagermst.getEemmKeyid());
			sqls.add(EntTlEmpmanagerdtlSql.getInsertSql(entTlEmpmanagerdtlSql.getEemdDbFields(), entTlEmpmanagerdtl.getSaveArray()));// add insert sql for detail table
			}		
			else
			{
    		entTlEmpmanagerdtl.setEemdEemmKeyid(entTlEmpmanagermst.getEemmKeyid());
			
			sqls.add(EntTlEmpmanagerdtlSql.getUpdateSql(entTlEmpmanagerdtlSql.getEemdDbFields(), entTlEmpmanagerdtl.getSaveArray()));// add insert sql for detail table
			}
			String sqlUpCal = " update gen_tl_employeemst set  empm_email = '" + entTlEmpmanagermst.getEemmMailid() +"' where EMPM_KEYID = '" + entTlEmpmanagermst.getEemmManagerId() +"' ";
			sqls.add(sqlUpCal);
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return entTlEmpmanagermst;
	}
	
	public String delete(EntTlEmpmanagermstModel entTlEmpmanagermst)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		EntTlEmpmanagermstSql entTlEmpmanagermstSql = new EntTlEmpmanagermstSql();
		try {
			String sqlDelCal = " DELETE FROM ent_tl_empmanagerdtl WHERE EEMD_EEMM_KEYID = '" + entTlEmpmanagermst.getEemmKeyid() +"' ";
			sqls.add(sqlDelCal);
			sqls.add(EntTlEmpmanagermstSql.getDeleteSql(entTlEmpmanagermstSql.getEemmDbFields(), entTlEmpmanagermst.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			return "sucess";
			
		}catch( Exception e){
			
			return "fail";
		}
		
	}
	
	@Override
	public void DeleteEmployeeDet(String EmpDetailkeyId) throws Exception {
		// TODO Auto-generated method stub
		List<String> sqls = new ArrayList<String>();
		String sqlDelCal = " DELETE FROM ent_tl_empmanagerdtl  WHERE EEMD_KEYID = '" + EmpDetailkeyId +"' ";
		sqls.add(sqlDelCal);
		
		dbActionTemplate.executeStatements(sqls);
	}
}

