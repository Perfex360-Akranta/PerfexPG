package com.akranta.tpm.dao.sql;

public class BAL_ToolChangeTlDetailsSql {
	
	public static final String TBL_TLM_TL_TOOLCHANGE = "TLM_TL_TOOLCHANGE";  

	TableFieldType [] tool_DbFields = null;
	public enum   tableFldConstants
	{
		keyid,toolkeyid,toolserialno,changeddate,changeType,sectionid,cellid,machineid,stdcotime,actcotime,estsharp,sharpno,
		lfwrtsharp,lastchangedate,prdtillchdate,nextchangedate,changeLinkId,factoryid,remark,lifeExtended,lifeEarly,trialTool,tempfield6,active, createdby, createdon, modifiedon,
		usedremark,stdLife,extendedLife,changeReason,whywhy,flid,tempfiled8,tempfiled9,tempfiled10
	}
        
	public TableFieldType[] getToolDbFields() {
		return tool_DbFields;
	}

	public BAL_ToolChangeTlDetailsSql()
	{
		tool_DbFields = new TableFieldType[ 36];
		for(int i = 0;i < 36; i++)
		{	
			tool_DbFields[ i ] = new TableFieldType();
		}
		
		
		tool_DbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "TLTC_KEYID";
		tool_DbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';
		
		tool_DbFields[ tableFldConstants.toolkeyid.ordinal() ].fieldName = "TLTC_TOOLKEYID";
		tool_DbFields[ tableFldConstants.toolkeyid.ordinal() ].fieldType = 'V';
		
		tool_DbFields[ tableFldConstants.toolserialno.ordinal() ].fieldName = "TLTC_TOOLSERIALNO";
		tool_DbFields[ tableFldConstants.toolserialno.ordinal() ].fieldType = 'V';
				
		tool_DbFields[ tableFldConstants.changeddate.ordinal() ].fieldName = "TLTC_DATECHANGED";
		tool_DbFields[ tableFldConstants.changeddate.ordinal() ].fieldType = 'D';
		
		tool_DbFields[ tableFldConstants.changeType.ordinal() ].fieldName = "TLTC_CHANGETYPE";
		tool_DbFields[ tableFldConstants.changeType.ordinal() ].fieldType = 'C';
		
		tool_DbFields[ tableFldConstants.sectionid.ordinal() ].fieldName = "TLTC_SECTIONID";
		tool_DbFields[ tableFldConstants.sectionid.ordinal() ].fieldType = 'V';
		
		tool_DbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "TLTC_CELLID";
		tool_DbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';
		
		tool_DbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "TLTC_MACHINEID";
		tool_DbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';
		
		tool_DbFields[ tableFldConstants.stdcotime.ordinal() ].fieldName = "TLTC_STDCOTIME";
		tool_DbFields[ tableFldConstants.stdcotime.ordinal() ].fieldType = 'N';
		
		tool_DbFields[ tableFldConstants.actcotime.ordinal() ].fieldName = "TLTC_ACTUALCOTIME";
		tool_DbFields[ tableFldConstants.actcotime.ordinal() ].fieldType = 'N';
		
		tool_DbFields[ tableFldConstants.estsharp.ordinal() ].fieldName = "TLTC_ESTISHARPENINGS";
		tool_DbFields[ tableFldConstants.estsharp.ordinal() ].fieldType = 'N';
		
		tool_DbFields[ tableFldConstants.sharpno.ordinal() ].fieldName = "TLTC_TOOLSHARPENINGNO";
		tool_DbFields[ tableFldConstants.sharpno.ordinal() ].fieldType = 'V';
		
		tool_DbFields[ tableFldConstants.lfwrtsharp.ordinal() ].fieldName = "TLTC_LIFEWRTSHARPENINGNO";
		tool_DbFields[ tableFldConstants.lfwrtsharp.ordinal() ].fieldType = 'N';
		
		tool_DbFields[ tableFldConstants.lastchangedate.ordinal() ].fieldName = "TLTC_LASTDATECHANGED";
		tool_DbFields[ tableFldConstants.lastchangedate.ordinal() ].fieldType = 'D';
		
		tool_DbFields[ tableFldConstants.prdtillchdate.ordinal() ].fieldName = "TLTC_PRDTILLLASTCHANGE";
		tool_DbFields[ tableFldConstants.prdtillchdate.ordinal() ].fieldType = 'N';
	
		tool_DbFields[ tableFldConstants.nextchangedate.ordinal() ].fieldName = "TLTC_NEXTCHANGEDATE";
		tool_DbFields[ tableFldConstants.nextchangedate.ordinal() ].fieldType = 'D';
	
		tool_DbFields[ tableFldConstants.changeLinkId.ordinal() ].fieldName = "TLTC_CHANGELINKID";
		tool_DbFields[ tableFldConstants.changeLinkId.ordinal() ].fieldType = 'V';
	
		tool_DbFields[ tableFldConstants.factoryid.ordinal() ].fieldName = "TLTC_FACTORYID";
		tool_DbFields[ tableFldConstants.factoryid.ordinal() ].fieldType = 'V';
		
		tool_DbFields[ tableFldConstants.remark.ordinal() ].fieldName = "TLTC_REMARK";
		tool_DbFields[ tableFldConstants.remark.ordinal() ].fieldType = 'V';
		
		tool_DbFields[ tableFldConstants.lifeExtended.ordinal() ].fieldName = "TLTC_ISLFEXTENDED";
		tool_DbFields[ tableFldConstants.lifeExtended.ordinal() ].fieldType = 'C';
		
		tool_DbFields[ tableFldConstants.lifeEarly.ordinal() ].fieldName = "TLTC_ISEARLYLIFE";		
		tool_DbFields[ tableFldConstants.lifeEarly.ordinal() ].fieldType = 'C';
	
		tool_DbFields[ tableFldConstants.trialTool.ordinal() ].fieldName = "TLTC_ISTRAILTOOL";
		tool_DbFields[ tableFldConstants.trialTool.ordinal() ].fieldType = 'C';
		
		tool_DbFields[ tableFldConstants.tempfield6.ordinal() ].fieldName = "TLTC_TEMPFIELD6";
		tool_DbFields[ tableFldConstants.tempfield6.ordinal() ].fieldType = 'C';
		
		tool_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "TLTC_ACTIVE";
		tool_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';
	
		tool_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "TLTC_CREATEDBY";
		tool_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'C';
	
		tool_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "TLTC_CREATEDON";
		tool_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';
	
		tool_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "TLTC_MODIFIEDON";
		tool_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		tool_DbFields[ tableFldConstants.usedremark.ordinal() ].fieldName = "TLTC_REMARKUSED";
		tool_DbFields[ tableFldConstants.usedremark.ordinal() ].fieldType = 'V';
		
		tool_DbFields[ tableFldConstants.stdLife.ordinal() ].fieldName = "TLTC_STDLIFE";
		tool_DbFields[ tableFldConstants.stdLife.ordinal() ].fieldType = 'N';
		
		tool_DbFields[ tableFldConstants.extendedLife.ordinal() ].fieldName = "TLTC_EXTENDEDLIFE";
		tool_DbFields[ tableFldConstants.extendedLife.ordinal() ].fieldType = 'N';
		
		tool_DbFields[ tableFldConstants.changeReason.ordinal() ].fieldName = "TLTC_CHANGEREASON";
		tool_DbFields[ tableFldConstants.changeReason.ordinal() ].fieldType = 'V'; 
		
		tool_DbFields[ tableFldConstants.whywhy.ordinal() ].fieldName = "TLTC_WHYWHYID";
		tool_DbFields[ tableFldConstants.whywhy.ordinal() ].fieldType = 'V';
		
		tool_DbFields[ tableFldConstants.flid.ordinal() ].fieldName = "TLTC_FLID";
		tool_DbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';
		
		tool_DbFields[ tableFldConstants.tempfiled8.ordinal() ].fieldName = "TLTC_TEMPFIELD8";
		tool_DbFields[ tableFldConstants.tempfiled8.ordinal() ].fieldType = 'C';
		
		tool_DbFields[ tableFldConstants.tempfiled9.ordinal() ].fieldName = "TLTC_TEMPFIELD9";
		tool_DbFields[ tableFldConstants.tempfiled9.ordinal() ].fieldType = 'C';
		
		tool_DbFields[ tableFldConstants.tempfiled10.ordinal() ].fieldName = "TLTC_TEMPFIELD10";
		tool_DbFields[ tableFldConstants.tempfiled10.ordinal() ].fieldType = 'C';
		
	}
	
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_TLM_TL_TOOLCHANGE, fieldTypeArr, dataArray);
	}
	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
	
	String sql = SqlUtils.getUpdateSql(TBL_TLM_TL_TOOLCHANGE, fieldTypeArr, dataArray);
	
	sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
	return sql;
	
	}

	public static String getDeleteSql(TableFieldType[] toolDbFields,
			Object[] saveArray) {
		String sql=null;
		
		 sql = "DELETE from " + TBL_TLM_TL_TOOLCHANGE ;
		
		 sql += " where " + toolDbFields[tableFldConstants.keyid.ordinal()].fieldName  +
		  " = '" +  (String)saveArray[ tableFldConstants.keyid.ordinal() ] + "'";
		System.out.println(sql+"  in Delete ");
		
			  return sql;
	}

	public static String getSelect() {
		String sql=" SELECT * from " + TBL_TLM_TL_TOOLCHANGE + " where TLTC_KEYID = ?  ";
		System.out.println(sql+"  in SELECT ");
		return sql;
	}

	public static String updateYYSql(String WhyKeyid, String wwmsRefdocno) {
		// TODO Auto-generated method stub
		String sql=" Update TLM_TL_TOOLCHANGE SET TLTC_WHYWHYID='"+WhyKeyid+"' where TLTC_KEYID ='"+wwmsRefdocno+"'";
		return sql;
	}
}
