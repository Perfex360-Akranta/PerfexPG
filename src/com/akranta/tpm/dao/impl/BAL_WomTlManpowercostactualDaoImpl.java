package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_WomTlManpowercostactualDao;
import com.akranta.tpm.dao.sql.BAL_WomTlManpowercostactualSql;
import com.akranta.tpm.model.BAL_WomTlManpowercostactual;

/* dao implementation */
public class BAL_WomTlManpowercostactualDaoImpl implements BAL_WomTlManpowercostactualDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_WomTlManpowercostactualDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_WomTlManpowercostactual create(BAL_WomTlManpowercostactual womTlManpowercostactual) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_WomTlManpowercostactualSql womTlManpowercostactualSql = new BAL_WomTlManpowercostactualSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		//try{		
			//womTlManpowercostactual.setMpcsMaintwoid(dbActionTemplate.getSequenceNumber(WomTlManpowercostactualSql.TBL_WOM_TL_MANPOWERCOSTACTUAL)); // set the sequnce number 
			sqls.add(BAL_WomTlManpowercostactualSql.getInsertSql(womTlManpowercostactualSql.getMpcsDbFields(), womTlManpowercostactual.getSaveArray())); // add insert sql for master table			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
	//	}catch(Exception e)
		//{
		//	throw new Exception(e.getMessage());
		//}
		return womTlManpowercostactual;
	}
	
	public BAL_WomTlManpowercostactual update(BAL_WomTlManpowercostactual womTlManpowercostactual)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_WomTlManpowercostactualSql womTlManpowercostactualSql = new BAL_WomTlManpowercostactualSql();

		sqls.add(BAL_WomTlManpowercostactualSql.getUpdateSql(womTlManpowercostactualSql.getMpcsDbFields(), womTlManpowercostactual.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);		
		
		return womTlManpowercostactual;
	}
	
	public BAL_WomTlManpowercostactual delete(BAL_WomTlManpowercostactual womTlManpowercostactual)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_WomTlManpowercostactualSql womTlManpowercostactualSql = new BAL_WomTlManpowercostactualSql();
		try {			
			sqls.add(BAL_WomTlManpowercostactualSql.getDeleteSql(womTlManpowercostactualSql.getMpcsDbFields(), womTlManpowercostactual.getSaveArray()));
			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return womTlManpowercostactual;
	}

	public String getCheckManpowerExists(String formType, String woId ) throws Exception
	{
		try
		{	String sql = "";
			System.out.println("formType dao imp"+ formType);
			if (formType.equals("Actual")) {				
				sql = " select COUNT(*) AS CNT from BAL_WOM_TL_MANPOWERCOSTACTUAL " ; 
				sql+= " WHERE MPCS_MAINTWOID = '" + woId + "'  ";
			}
			else if (formType.equals("Estimate")) {
				sql = " select COUNT(*) AS CNT from WOM_TL_MANPOWERCOSTPLAN " ; 
				sql+= " WHERE MPCP_WOID = '" + woId + "'  ";				
			}				
			return dbActionTemplate.getSingleValue(sql);
		}
		catch(Exception e) { e.printStackTrace(); }		
		return null;		
	}
	
	public List<String[]> getGridManPowerQry(String formName, String formType, String woId ) throws Exception {			
		try
		{
			
			String sql = "";
			StringBuffer buffer = new StringBuffer();
			
			System.out.println("formType in dao impl"+formType);
			
			if (formType.equals("Actual")) {
				if (formName.equals("empCost")) {
					buffer.append (" SELECT MPCS_SKILLID, GRDM_NAME, MPCS_MANPOWERID, EMPM_EMPLOYEENUMBER, EMPM_NAME, "); 
					buffer.append (" ROUND(MPCS_NORMALWT,2), ROUND(MPCS_HOLIDAYWT,2), ROUND(MPCS_OTHERWT,2), ROUND(MPCS_NORMALRATE,2), ROUND(MPCS_HOLIDAYRATE,2), ");
					buffer.append (" ROUND(MPCS_OTHERRATE,2), ROUND(MPCS_TOTALVALUE,2), REPLACE(REPLACE(REPLACE(MPCS_REMARKS,'<*',''),'*>',''),'{}','') ");
					buffer.append (" FROM BAL_WOM_TL_MANPOWERCOSTACTUAL  , GEN_TL_EMPLOYEEMST,  GEN_TL_EMPGRADEMST ");
					buffer.append (" WHERE MPCS_MAINTWOID = '" + woId + "'  AND MPCS_SKILLFLAG = 'E'  "); 
					buffer.append (" AND MPCS_MANPOWERID = EMPM_KEYID AND MPCS_SKILLID = GRDM_KEYID ");
					buffer.append (" ORDER BY MPCS_CREATEDON ");
				}	
				else if (formName.equals("contractorCost")) {
					buffer.append (" SELECT AMVM_KEYID, AMVM_NAME, MPCS_MANPOWERID, UNGM_CODE, UNGM_NAME, "); 
					buffer.append (" ROUND(MPCS_NORMALWT,2), ROUND(MPCS_HOLIDAYWT,2), ROUND(MPCS_OTHERWT,2), ROUND(MPCS_NORMALRATE,2), ROUND(MPCS_HOLIDAYRATE,2), ");
					buffer.append (" ROUND(MPCS_OTHERRATE,2), ROUND(MPCS_TOTALVALUE,2), REPLACE(REPLACE(REPLACE(MPCS_REMARKS,'<*',''),'*>',''),'{}','') ");
					buffer.append (" FROM BAL_WOM_TL_MANPOWERCOSTACTUAL  , PLM_TL_UNSKILLEDGRADEMST_I,  GEN_TL_AMCVENDORMST ");
					buffer.append (" WHERE MPCS_MAINTWOID = '" + woId + "'  AND MPCS_SKILLFLAG = 'H'  "); 
					buffer.append (" AND MPCS_MANPOWERID = UNGM_KEYID AND MPCS_SKILLID = AMVM_KEYID ");
					buffer.append (" ORDER BY MPCS_CREATEDON ");
				}	
			}
			else if (formType.equals("Estimate")) {
				if (formName.equals("empCost")) {
					buffer.append (" SELECT MPCP_SKILLID, GRDM_NAME, MPCP_MANPOWERID, EMPM_EMPLOYEENUMBER, EMPM_NAME, "); 
					buffer.append (" ROUND(MPCP_NORMALMINS,2), '' AS HOLIDAYWT, '' AS OTHERWT, ROUND(MPCP_NORMALCOST,2), ");
					buffer.append (" '' AS HOLIDAYRATE, '' AS OTHERRATE, ROUND(MPCP_TOTALVALUE,2),REPLACE(REPLACE(REPLACE(MPCP_REMARKS,'<*',''),'*>',''),'{}','') ");
					buffer.append (" FROM WOM_TL_MANPOWERCOSTPLAN  , GEN_TL_EMPLOYEEMST,  GEN_TL_EMPGRADEMST ");
					buffer.append (" WHERE MPCP_WOID = '" + woId + "'  AND MPCP_SKILLFLAG = 'E'  "); 
					buffer.append (" AND MPCP_MANPOWERID = EMPM_KEYID AND MPCP_SKILLID = GRDM_KEYID ");
					buffer.append (" ORDER BY MPCP_CREATEDON ");
				}	
				else if (formName.equals("contractorCost")) {
					buffer.append (" SELECT AMVM_KEYID, AMVM_NAME, MPCP_MANPOWERID, UNGM_CODE, UNGM_NAME, "); 
					buffer.append (" ROUND(MPCP_NORMALMINS,2), '' AS HOLIDAYWT, '' AS OTHERWT, ROUND(MPCP_NORMALCOST,2), ");
					buffer.append (" '' AS HOLIDAYRATE, '' AS OTHERRATE, ROUND(MPCP_TOTALVALUE,2),REPLACE(REPLACE(REPLACE(MPCP_REMARKS,'<*',''),'*>',''),'{}','') ");
					buffer.append (" FROM WOM_TL_MANPOWERCOSTPLAN  , PLM_TL_UNSKILLEDGRADEMST_I,  GEN_TL_AMCVENDORMST ");
					buffer.append (" WHERE MPCP_WOID = '" + woId + "'  AND MPCP_SKILLFLAG = 'H'  "); 
					buffer.append (" AND MPCP_MANPOWERID = UNGM_KEYID AND MPCP_SKILLID = AMVM_KEYID ");
					buffer.append (" ORDER BY MPCP_CREATEDON ");
				}	
			}
		
		System.out.println("woId : "+woId);
		sql = buffer.toString();
		return dbActionTemplate.getDataList(sql);
		}		
		catch(Exception e) { e.printStackTrace(); }		
		return null;
	}
}

