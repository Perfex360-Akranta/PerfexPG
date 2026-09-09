package com.akranta.tpm.dao.sql;

public class KaizenReportSql {

	public static String getKaizenReportModificationSql()
	{
		return " select * from " + TableNames.TBL_KK_VW_KAIZENMODIFICATION + " order by KZN_KAIZENNO desc "; 
	}
}
