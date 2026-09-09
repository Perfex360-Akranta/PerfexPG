package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.dao.JhaTlVisualsopdtlDao;
import com.akranta.tpm.dao.sql.GenTlAllmoduleimgfileSql;
import com.akranta.tpm.dao.sql.JhaTlVisualsopdtlSql;
import com.akranta.tpm.dao.sql.JhaTlVisualsopmstSql;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.JhaTlVisualsopdtl;
import com.akranta.tpm.model.JhaTlVisualsopmst;
import com.akranta.tpm.model.OplTlMst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class JhaTlVisualsopdtlDaoImpl implements JhaTlVisualsopdtlDao {


	private DBActionTemplate dbActionTemplate; 

	public JhaTlVisualsopdtlDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public JhaTlVisualsopdtl create(JhaTlVisualsopdtl jhaTlVisualsopdtl) 	throws Exception,BusinessApplicationExceptions {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		JhaTlVisualsopdtlSql jhaTlVisualsopdtlSql = new JhaTlVisualsopdtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			String sqlcheck="SELECT COUNT(*) FROM "+JhaTlVisualsopdtlSql.TBL_JHA_TL_VISUALSOPDTL+" WHERE VSOD_INSTRUCTION='"+jhaTlVisualsopdtl.getVsodInstruction()+"' AND VSOD_VSOM_KEYID='"+jhaTlVisualsopdtl.getVsodVsomKeyid()+"' AND VSOD_KEYPOINT='"+jhaTlVisualsopdtl.getVsodKeypoint()+"'";
			String count=dbActionTemplate.getSingleValue(sqlcheck);
			CommonMessage.debugMsg("count  "+count);
				if(Integer.parseInt(count)>0)
					throw new BusinessApplicationExceptions("duplication,");
				else{
					jhaTlVisualsopdtl.setVsodKeyid(dbActionTemplate.getSequenceNumber(JhaTlVisualsopdtlSql.TBL_JHA_TL_VISUALSOPDTL,10,"VSDT","","Y")); // set the sequnce number 
					sqls.add(JhaTlVisualsopdtlSql.getInsertSql(jhaTlVisualsopdtlSql.getVsodDbFields(), jhaTlVisualsopdtl.getSaveArray())); // add insert sql for master table
					dbActionTemplate.executeStatements(sqls); // execute the block of sqls
				}
			
		}catch(BusinessApplicationExceptions e)
		{
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return jhaTlVisualsopdtl;
	}
	
	public Map<Integer, List<String[]>> vsopExcelReport(String vsopid,String format, CommonFilter commonFilter) throws Exception {
		try
		{
			 CommonMessage.debugMsg(" Inside dao Impl report (VSOP) :::::::: ");
			CommonMessage.debugMsg("Inside export daoimpl Excel");
			List<String> paramValues = new ArrayList<String>();				
		
			paramValues.add(vsopid);
			
			CommonMessage.debugMsg("param Values :-" +paramValues.get(0));
		
			Map<Integer, List<String[]>> vsopExportReport = dbActionTemplate.processDbFunCallMultCursor("VSOPEXCELREPORT", paramValues,2);
			
			
			//return ImproprojshtReport;
			return vsopExportReport;
			
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
			
		}
	}
	
	@Override
	public List<String[]> getVisualid(String vsopid) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		CommonMessage.debugMsg(" Inside Dao Impl :: "+vsopid);
//		sql.append(" SELECT 'Visual SOP #' || TO_CHAR(TO_NUMBER(SUBSTR('"+vsopid+"',5))) FROM dual ");
		
		//sriram 31-OCT-2025
		sql.append("SELECT 'Visual SOP #' || TO_CHAR(CAST(SUBSTRING('" + vsopid + "' FROM 5) AS NUMERIC), 'FM999999999');");

		
		CommonMessage.debugMsg(" Inside CommonFunctions :: 5678 "+sql);
	    return dbActionTemplate.getDataList(sql.toString());
	}

	
	public JhaTlVisualsopdtl update(JhaTlVisualsopdtl jhaTlVisualsopdtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		JhaTlVisualsopdtlSql jhaTlVisualsopdtlSql = new JhaTlVisualsopdtlSql();
		try {
			

			sqls.add(JhaTlVisualsopdtlSql.getUpdateSql(jhaTlVisualsopdtlSql.getVsodDbFields(), jhaTlVisualsopdtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return jhaTlVisualsopdtl;
	}
	
	public JhaTlVisualsopdtl delete(JhaTlVisualsopdtl jhaTlVisualsopdtl)
			throws Exception {
		List<String> sqls = new ArrayList<String>();
		JhaTlVisualsopdtlSql jhaTlVisualsopdtlSql = new JhaTlVisualsopdtlSql();
		try {
			
			if(jhaTlVisualsopdtl.getVsodImgtoolused()!=null)
			{
				sqls.add(GenTlAllmoduleimgfileSql.getSqlDelete(jhaTlVisualsopdtl.getVsodKeyid()));	
			}
			
			sqls.add(jhaTlVisualsopdtlSql.getDeleteSql(jhaTlVisualsopdtlSql.getVsodDbFields(), jhaTlVisualsopdtl.getSaveArray()));
			CommonMessage.debugMsg("GenTlAllmoduleimgfile sql::::"+sqls.get(1));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return jhaTlVisualsopdtl;
	}
	
	public JhaTlVisualsopdtl getAllFillControlDtl(String keyId)throws Exception {
		JhaTlVisualsopdtl jhaTlVisualsopdtl  = new JhaTlVisualsopdtl();
		String sql = JhaTlVisualsopdtlSql.getSingledata();
		CommonMessage.debugMsg("sql in impl"+sql);
		Object args[] = new Object[] {keyId};
		jhaTlVisualsopdtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));
		return jhaTlVisualsopdtl;
}

	@Override
	public GenTlAllmoduleimgfile deleteForImg(String keyId) throws Exception {
		GenTlAllmoduleimgfile genTlAllmoduleimgfile  = new GenTlAllmoduleimgfile();
		String sql = JhaTlVisualsopdtlSql.getDeleteImage(keyId);
		CommonMessage.debugMsg("sql in impl"+sql);
		Object args[] = new Object[] {keyId};
		dbActionTemplate.executeStatement(sql);
		return genTlAllmoduleimgfile;
	}

	@Override
	public JhaTlVisualsopmst updateApprovedStatusLevel(String status,
			String keyid, String nextLevel) throws Exception {
		
		JhaTlVisualsopmst jhaTlVisualsopmst =new JhaTlVisualsopmst();
		String sql= " UPDATE  jha_tl_visualsopmst  SET VSOM_STATUS ='"+status+"', VSOM_NEXTLEVEL='"+nextLevel+"' WHERE VSOM_KEYID ='"+keyid+"' ";
		CommonMessage.debugMsg("sql for approval ==="+sql);
		dbActionTemplate.executeStatement(sql);
		return jhaTlVisualsopmst;

	}

	@Override
	public List<String[]> getVsopApprovalList(String keyid) throws Exception {
		// TODO Auto-generated method stub
       // String sql = "Select empm_name , decode(wrin_status,'A' ,TO_CHAR(WRIN_DATE,'DD-MON-YYYY'),'E','REWORK','R','REJECTED',TO_CHAR(WRIN_DATE,'DD-MON-YYYY'))  from gen_tl_workflow_info,gen_tl_employeemst where WRIN_REF_ID ='"+keyid+"' AND empm_keyid = WRIN_EMPLOYEE_ID ORDER BY EMPM_NAME DESC ";
		
		//sriram 31-oct-2025
		String sql = "SELECT empm_name, " +
	             "CASE " +
	             " WHEN wrin_status = 'A' THEN TO_CHAR(wrin_date, 'DD-MON-YYYY') " +
	             " WHEN wrin_status = 'E' THEN 'REWORK' " +
	             " WHEN wrin_status = 'R' THEN 'REJECTED' " +
	             " ELSE TO_CHAR(wrin_date, 'DD-MON-YYYY') " +
	             "END AS wrin_display_date " +
	             "FROM gen_tl_workflow_info " +
	             "JOIN gen_tl_employeemst ON empm_keyid = wrin_employee_id " +
	             "WHERE wrin_ref_id = '" + keyid + "' " +
	             "ORDER BY empm_name DESC";

		
		return dbActionTemplate.getDataList(sql);
	}
	
}

