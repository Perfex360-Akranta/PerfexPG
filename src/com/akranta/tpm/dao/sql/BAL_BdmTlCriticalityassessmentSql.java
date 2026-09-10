package com.akranta.tpm.dao.sql;

import com.akranta.tpm.controller.UIUtils;

public class BAL_BdmTlCriticalityassessmentSql {

	public static final String TBL_BDM_TL_CRITICALITYASSESSMENT = "BDM_TL_CRITICALITYASSESSMENT";  

	TableFieldType [] casmDbFields = null;

	public enum   tableFldConstants
	{
		keyid, elementid, flid, equipmentid, criteriaid, scores, date
		, doneby, remarks, tempfield6, tempfield7, tempfield8, tempfield9
		, tempfield10, tradeid , tempfield2, tempfield3, tempfield4
		, tempfield5, createdby, active, createdon, modifiedon
	}

	public TableFieldType[] getCasmDbFields() {
		return casmDbFields;
	}

	public BAL_BdmTlCriticalityassessmentSql()
	{
		casmDbFields = new TableFieldType[ 23 ];
		for(int i = 0;i < 23; i++)
		{	
			casmDbFields[ i ] = new TableFieldType();
		}
		casmDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "CASM_KEYID";
		casmDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "CASM_ELEMENTID";
		casmDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "CASM_FLID";
		casmDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.equipmentid.ordinal() ].fieldName = "CASM_EQUIPMENTID";
		casmDbFields[ tableFldConstants.equipmentid.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.criteriaid.ordinal() ].fieldName = "CASM_CRITERIAID";
		casmDbFields[ tableFldConstants.criteriaid.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.scores.ordinal() ].fieldName = "CASM_SCORES";
		casmDbFields[ tableFldConstants.scores.ordinal() ].fieldType = 'N';

		casmDbFields[ tableFldConstants.date.ordinal() ].fieldName = "CASM_DATE";
		casmDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		casmDbFields[ tableFldConstants.doneby.ordinal() ].fieldName = "CASM_DONEBY";
		casmDbFields[ tableFldConstants.doneby.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "CASM_REMARKS";
		casmDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "CASM_TEMPFIELD6";
		casmDbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldName = "CASM_TEMPFIELD7";
		casmDbFields[ tableFldConstants.tempfield7.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldName = "CASM_TEMPFIELD8";
		casmDbFields[ tableFldConstants.tempfield8.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldName = "CASM_TEMPFIELD9";
		casmDbFields[ tableFldConstants.tempfield9.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldName = "CASM_TEMPFIELD10";
		casmDbFields[ tableFldConstants.tempfield10.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldName = "CASM_TRADEID";
		casmDbFields[ tableFldConstants.tradeid.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "CASM_TEMPFIELD2";
		casmDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "CASM_TEMPFIELD3";
		casmDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "CASM_TEMPFIELD4";
		casmDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "CASM_TEMPFIELD5";
		casmDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CASM_CREATEDBY";
		casmDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		casmDbFields[ tableFldConstants.active.ordinal() ].fieldName = "CASM_ACTIVE";
		casmDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		casmDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CASM_CREATEDON";
		casmDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		casmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "CASM_MODIFIEDON";
		casmDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_BDM_TL_CRITICALITYASSESSMENT, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_BDM_TL_CRITICALITYASSESSMENT, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_BDM_TL_CRITICALITYASSESSMENT ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}
	public static StringBuffer getmachinerank(String tot)
	{
		System.out.println("Inside Sql file"+tot);
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select  mrsk_code from (select minrating,maxrating, mrsk_code,mrsk_keyid from GEN_TL_MCHRANKSKILLMST," );
		sql.append("(select min( nvl(a.MRSK_MAXIMUMPOINTS+1,0)) minrating  , min(b.MRSK_MAXIMUMPOINTS) maxrating, a.MRSK_CODE r ");
		sql.append("from GEN_TL_MCHRANKSKILLMST a ,GEN_TL_MCHRANKSKILLMST b  where a.MRSK_MAXIMUMPOINTS(+) < b.MRSK_MAXIMUMPOINTS group by a.MRSK_CODE " ); 
		sql.append(" order by minrating ) where maxrating = MRSK_MAXIMUMPOINTS ) where  "+tot+" >= minrating and "+tot+" <= maxrating ");
		
		return sql;
	
	}
	public static StringBuffer getCriteria(String flId,String tot , String equbmentid, String trade)
	{ 
		/*System.out.println("Inside Sql file"+tot);		
		StringBuffer sql = new StringBuffer();		
		sql.append("select CRIA_KEYID FROM PLM_TL_CRITERIAMST WHERE CRIA_FLID='"+flId+"' ");
		sql.append("AND "+tot+" BETWEEN CRIA_MINIMUMPOINTS AND CRIA_MAXIMUMPOINTS " );	*/	
		StringBuffer sql = new StringBuffer();		
		
		sql.append(" SELECT MAX( CASE WHEN "+ tot +" <= CRIA_MAXIMUMPOINTS AND "+ tot +"  >= CRIA_MINIMUMPOINTS THEN CRIA_KEYID  END) CRITERIAID , ");
		sql.append(" MAX(CRIA_MINIMUMPOINTS) CRIA_MINIMUMPOINTS, ");
		sql.append(" MAX(CRIA_MAXIMUMPOINTS) CRIA_MAXIMUMPOINTS,   MAX(A.\"LEVEL\") AS LV ");
		//sql.append(" MAX(CRIA_MAXIMUMPOINTS) CRIA_MAXIMUMPOINTS ");
		sql.append("  , CRIA_NAME  ");		
		sql.append("  FROM PLM_TL_CRITERIAMST, ");
		sql.append("  GEN_MV_FLIDHIERARCHY A,   ");     
		sql.append(" GEN_MV_FLIDHIERARCHY B ");
		sql.append(" WHERE CRIA_FLID      = A.FLID ");
		sql.append(" AND B.FNLN_ORIGINALID = '" + equbmentid + "'");
		sql.append("  and "+ tot +" between CRIA_MINIMUMPOINTS and CRIA_MAXIMUMPOINTS  "); 
		sql.append("  AND INSTR(B.PARENTFLIDS ");
		sql.append("   ||B.FLID,A.FLID) > 0 ");
		if(UIUtils.isValidKeyId(trade))
		{
			sql.append(" AND CRIA_TRADEID ='"+trade+"'"); 
		}
		
		
		sql.append(" GROUP BY CRIA_NAME ");
        
		return sql;
	
	}
}

