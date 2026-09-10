package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_BdmTlDtlDao;
import com.akranta.tpm.dao.sql.BAL_BdmTlDtlSql;
import com.akranta.tpm.dao.sql.BAL_BdmTlMstSql;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
//import com.akranta.tpm.dao.sql.SapTlSparesreplacedSql;
import com.akranta.tpm.model.BAL_BdmTlDtl;
import com.akranta.tpm.model.BAL_BdmTlMst;
import com.akranta.tpm.model.BAL_BdmTlWhywhymst;
import com.akranta.tpm.model.SapTlSparesreplaced;
import com.akranta.tpm.utils.CommonFunctions;

/* dao implementation */
public class BAL_BdmTlDtlDaoImpl implements BAL_BdmTlDtlDao {


	private DBActionTemplate dbActionTemplate; 

	public BAL_BdmTlDtlDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public BAL_BdmTlDtl create(BAL_BdmTlDtl bdmTlDtl) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_BdmTlDtlSql bdmTlDtlSql = new BAL_BdmTlDtlSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
		
			bdmTlDtl.setBdanKeyid(dbActionTemplate.getSequenceNumber(BAL_BdmTlDtlSql.TBL_BAL_BDM_TL_DTL)); // set the sequnce number 
			sqls.add(BAL_BdmTlDtlSql.getInsertSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray())); // add insert sql for master table

			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return bdmTlDtl;
	}
	
	public BAL_BdmTlDtl update(BAL_BdmTlDtl bdmTlDtl)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlDtlSql bdmTlDtlSql = new BAL_BdmTlDtlSql();
		try {

			sqls.add(BAL_BdmTlDtlSql.getUpdateSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return bdmTlDtl;
	}
	
	public BAL_BdmTlDtl delete(BAL_BdmTlDtl bdmTlDtl)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_BdmTlDtlSql bdmTlDtlSql = new BAL_BdmTlDtlSql();
		try {
			
			sqls.add(BAL_BdmTlDtlSql.getDeleteSql(bdmTlDtlSql.getBdanDbFields(), bdmTlDtl.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return bdmTlDtl;
	}

	@Override
	public BAL_BdmTlDtl selectBd(String keyid) throws Exception {
		// TODO Auto-generated method stub
		
		    BAL_BdmTlDtl bdmTlDtl = new BAL_BdmTlDtl();
			String sql = BAL_BdmTlDtlSql.select();
			System.out.println(sql);
			Object args [] = new Object [] { keyid };
			bdmTlDtl.setSaveArray(dbActionTemplate.getDataArr(sql, args));
			return bdmTlDtl;
		}
	
	@Override
	public BAL_BdmTlWhywhymst selectWhyWhy(String keyid) throws Exception {
		// TODO Auto-generated method stub
		
			BAL_BdmTlWhywhymst bdmTlWhywhymst = new BAL_BdmTlWhywhymst();
			String sql = BAL_BdmTlDtlSql.selectWhyWhyMst();
   
			Object args [] = new Object [] { keyid };
			bdmTlWhywhymst.setSaveArray(dbActionTemplate.getDataArr(sql, args));
			return bdmTlWhywhymst;
		}

	@Override
	public BAL_BdmTlDtl getUpdateErpStatus(BAL_BdmTlDtl newBdmTlDtl )
			throws Exception {
			//BdmTlDtl newBdmTlDtl=new BdmTlDtl();
			List<String> sqls = new ArrayList<String>(); /* sqls for execution */

			
			try {
			
				CommonFunctions.debugMsg("KEYID===="+newBdmTlDtl.getBdanErpnumber());
				CommonFunctions.debugMsg("stauts===="+newBdmTlDtl.getBdanErppoststatus());
			//	sapTlSparesreplaced.setSspmKeyId(dbActionTemplate.getSequenceNumber(SapTlSparesreplacedSql.TBL_SAP_TL_SPARESREPLACED, 12, "SSPM", "MMYY", "Y"));//getSequenceNumber(WomTlWomstSql.TBL_WOM_TL_WOMST)); // set the sequnce number
				StringBuffer sql = new StringBuffer();
				sql.append(" UPDATE "+BAL_BdmTlDtlSql.TBL_BAL_BDM_TL_DTL+" ");
				sql.append(" SET BDAN_ERPNUMBER = '" + newBdmTlDtl.getBdanErpnumber() +"' ");
				sql.append(" , BDAN_ERPPOSTSTATUS = '" + newBdmTlDtl.getBdanErppoststatus() +"' "); 
				sql.append(" Where BDAN_BDMS_KEYID = '" + newBdmTlDtl.getBdanErpnumber() +"' ");
				System.out.println(sql);
				sqls.add(sql.toString());			
			
			dbActionTemplate.executeStatements(sqls);
			//return "sucess";
			}catch(Exception e){
				e.printStackTrace();
			}
			//return "sucess";
			return newBdmTlDtl;
	}

/*	@Override
	public BdmTlDtl getUpdateErpStatus(String success, String tranId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	*/
	
}

