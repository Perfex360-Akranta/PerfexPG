package com.akranta.tpm.dao.sql;

import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.sql.KpiTlIndicatorKkSql.tableFldConstants;
import com.akranta.tpm.model.KpiTlIndicatorDeptLink;

public class KpiTlIndicatorDeptLinkSql {

	public static final String TBL_KPI_TL_INDICATOR_DEPT_LINK = "KPI_TL_INDICATOR_DEPT_LINK";  

	TableFieldType [] kidlDbFields = null;

	public enum   tableFldConstants
	{
		keyid, indicatorid, deptid, depttype, pillarid, effectivedate
		, inactivedate, tempfield1, tempfield2, tempfield3, tempfield4
		, tempfield5, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getKidlDbFields() {
		return kidlDbFields;
	}

	public KpiTlIndicatorDeptLinkSql()
	{
		kidlDbFields = new TableFieldType[ 16 ];
		for(int i = 0;i < 16; i++)
		{	
			kidlDbFields[ i ] = new TableFieldType();
		}
		kidlDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KIDL_KEYID";
		kidlDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kidlDbFields[ tableFldConstants.indicatorid.ordinal() ].fieldName = "KIDL_INDICATORID";
		kidlDbFields[ tableFldConstants.indicatorid.ordinal() ].fieldType = 'V';

		kidlDbFields[ tableFldConstants.deptid.ordinal() ].fieldName = "KIDL_DEPTID";
		kidlDbFields[ tableFldConstants.deptid.ordinal() ].fieldType = 'V';

		kidlDbFields[ tableFldConstants.depttype.ordinal() ].fieldName = "KIDL_DEPTTYPE";
		kidlDbFields[ tableFldConstants.depttype.ordinal() ].fieldType = 'V';

		kidlDbFields[ tableFldConstants.pillarid.ordinal() ].fieldName = "KIDL_PILLARID";
		kidlDbFields[ tableFldConstants.pillarid.ordinal() ].fieldType = 'V';

		kidlDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldName = "KIDL_EFFECTIVEDATE";
		kidlDbFields[ tableFldConstants.effectivedate.ordinal() ].fieldType = 'D';

		kidlDbFields[ tableFldConstants.inactivedate.ordinal() ].fieldName = "KIDL_INACTIVEDATE";
		kidlDbFields[ tableFldConstants.inactivedate.ordinal() ].fieldType = 'D';

		kidlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldName = "KIDL_TEMPFIELD1";
		kidlDbFields[ tableFldConstants.tempfield1.ordinal() ].fieldType = 'C';

		kidlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldName = "KIDL_TEMPFIELD2";
		kidlDbFields[ tableFldConstants.tempfield2.ordinal() ].fieldType = 'C';

		kidlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldName = "KIDL_TEMPFIELD3";
		kidlDbFields[ tableFldConstants.tempfield3.ordinal() ].fieldType = 'C';

		kidlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldName = "KIDL_TEMPFIELD4";
		kidlDbFields[ tableFldConstants.tempfield4.ordinal() ].fieldType = 'C';

		kidlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldName = "KIDL_TEMPFIELD5";
		kidlDbFields[ tableFldConstants.tempfield5.ordinal() ].fieldType = 'C';

		kidlDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KIDL_ACTIVE";
		kidlDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		kidlDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KIDL_CREATEDBY";
		kidlDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kidlDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KIDL_CREATEDON";
		kidlDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kidlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KIDL_MODIFIEDON";
		kidlDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KPI_TL_INDICATOR_DEPT_LINK, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KPI_TL_INDICATOR_DEPT_LINK, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr,String pillCode, String indicatorId, String deptId, String drillLevel)
	{
		String sql = "DELETE from " + TBL_KPI_TL_INDICATOR_DEPT_LINK ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.pillarid.ordinal()].fieldName  +
			  " ='"+pillCode+"' AND "+fieldTypeArr[tableFldConstants.depttype.ordinal()].fieldName +"='"+drillLevel+"' ";
		if(UIUtils.isValidKeyId(indicatorId))
			sql += " and "+fieldTypeArr[tableFldConstants.indicatorid.ordinal()].fieldName+"='"+indicatorId+"'";
		 if(UIUtils.isValidKeyId(deptId))
			sql += " and "+fieldTypeArr[tableFldConstants.deptid.ordinal()].fieldName+"='"+deptId+"'";
		
		return sql;
	}
	public static String getdeleteDeptLinkSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//String sql = "UPDATE " + TableNames.TBL_KPI_TL_INDICATOR_DEPT_LINK ;
		
		
        StringBuffer sql= new StringBuffer();
		
		sql.append(" UPDATE " + TableNames.TBL_KPI_TL_INDICATOR_DEPT_LINK+" set KIDL_ACTIVE='N',");	
		
                sql.append("KIDL_INACTIVEDATE=sysdate");
		sql.append( " WHERE "  + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
				  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] +  "' and "+fieldTypeArr[tableFldConstants.deptid.ordinal() ].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.deptid.ordinal()  ] + "' " );		
		return sql.toString();
	/*	String sql =  "UPDATE KPI_TL_INDICATOR_DEPT_LINK  SET KIDL_ACTIVE='N', KIDL_INACTIVEDATE=sysdate";
		   sql +=" WHERE KIDL_KEYID= '' and KIDL_DEPTID= ?";
	return sql;*/
	}
	public static String getPillarID(String pillCode)
	{
		
		return "Select TPMP_KEYID from gen_tl_tpmpillarmst where TPMP_CODE='"+pillCode+"'";
	}
	
	public static String getdeptidSql(String kpid)
	{
		
		return "select  KIDL_KEYID FROM KPI_TL_INDICATOR_DEPT_LINK WHERE KIDL_INDICATORID = 'kpid' and KIDL_DEPTID='deptid'";
	}
	public static String getIndicatorCnt(String pillId)
	{
		return "select count(*) from KPI_TL_INDICATOR where KINK_PILLARID = '"+pillId+"'";
	}
	public static String getInsert()
	{
		return "INSERT INTO "+TBL_KPI_TL_INDICATOR_DEPT_LINK+"(KIDL_KEYID,KIDL_INDICATORID,KIDL_DEPTID,KIDL_DEPTTYPE,KIDL_PILLARID,KIDL_EFFECTIVEDATE,KIDL_INACTIVEDATE"+
		        ",KIDL_TEMPFIELD1,KIDL_TEMPFIELD2,KIDL_TEMPFIELD3,KIDL_TEMPFIELD4,KIDL_TEMPFIELD5,KIDL_ACTIVE ,KIDL_CREATEDBY,KIDL_CREATEDON,KIDL_MODIFIEDON) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	}
	
	public static String kdlkeyidvalue(String kpid, String flid)
	{
		return "Select KIDL_KEYID from kpi_tl_indicator_dept_link where KIDL_INDICATORID="+kpid+" and KIDL_DEPTID="+flid;
	}

	public String getLinkCount( Object [] dataArray) {
		// TODO Auto-generated method stub
		String sql = "Select Count(*) from KPI_TL_ACTUAL where 1=1 ";
		
		if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.indicatorid.ordinal() ])){
			sql += " and (KAUK_INDICATORID='"+(String)dataArray[ tableFldConstants.indicatorid.ordinal() ]+"'";
			sql += " OR KAUK_INDICATORID IN (SELECT KINK_KEYID FROM KPI_TL_INDICATOR WHERE KINK_PARENTID='"+(String)dataArray[ tableFldConstants.indicatorid.ordinal()] +"' ))";
		}
		if(UIUtils.isValidKeyId((String)dataArray[ tableFldConstants.deptid.ordinal() ]))
			sql += " and KAUK_DEPTID='"+(String)dataArray[ tableFldConstants.deptid.ordinal() ]+"'";
		return sql;
	}

}

