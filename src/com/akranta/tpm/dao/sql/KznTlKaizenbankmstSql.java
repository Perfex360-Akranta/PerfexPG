package com.akranta.tpm.dao.sql;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KznTlKaizenbankmst;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class KznTlKaizenbankmstSql {

	public static final String TBL_KZN_TL_KAIZENBANKMST = "KZN_TL_KAIZENBANKMST";  

	TableFieldType [] kzbnDbFields = null;

	public enum   tableFldConstants
	{
		keyid, flid, elementid, date, kaizen, benefit, targetdate, pqcdsme
		, suggestedby, responsibility, completedon, status, accrejremarks,acrejby,
		implementedby,verifyremarks,impremarks,compremarks,acceptrejon,implementedon,
		verifiedon,verifiedby,completedby,ehsrelated,ehsstatus,refdoctype,refdocno,others,
		implementcost,approvalflag,mocrequired,active,createdby 
		, createdon, modifiedon
	}
	public TableFieldType[] getKzbnDbFields() {
		return kzbnDbFields;
	}

	public KznTlKaizenbankmstSql()
	{
		kzbnDbFields = new TableFieldType[ 35 ];
		for(int i = 0;i < 35; i++)
		{	
			kzbnDbFields[ i ] = new TableFieldType();
		}
		kzbnDbFields[ tableFldConstants.keyid.ordinal() ].fieldName = "KZBN_KEYID";
		kzbnDbFields[ tableFldConstants.keyid.ordinal() ].fieldType = 'V';

		kzbnDbFields[ tableFldConstants.flid.ordinal() ].fieldName = "KZBN_FLID";
		kzbnDbFields[ tableFldConstants.flid.ordinal() ].fieldType = 'V';

		kzbnDbFields[ tableFldConstants.elementid.ordinal() ].fieldName = "KZBN_ELEMENTID";
		kzbnDbFields[ tableFldConstants.elementid.ordinal() ].fieldType = 'V';

		kzbnDbFields[ tableFldConstants.date.ordinal() ].fieldName = "KZBN_DATE";
		kzbnDbFields[ tableFldConstants.date.ordinal() ].fieldType = 'D';

		kzbnDbFields[ tableFldConstants.kaizen.ordinal() ].fieldName = "KZBN_KAIZEN";
		kzbnDbFields[ tableFldConstants.kaizen.ordinal() ].fieldType = 'V';

		kzbnDbFields[ tableFldConstants.benefit.ordinal() ].fieldName = "KZBN_BENEFIT";
		kzbnDbFields[ tableFldConstants.benefit.ordinal() ].fieldType = 'V';

		kzbnDbFields[ tableFldConstants.targetdate.ordinal() ].fieldName = "KZBN_TARGETDATE";
		kzbnDbFields[ tableFldConstants.targetdate.ordinal() ].fieldType = 'D';

		kzbnDbFields[ tableFldConstants.pqcdsme.ordinal() ].fieldName = "KZBN_PQCDSME";
		kzbnDbFields[ tableFldConstants.pqcdsme.ordinal() ].fieldType = 'C';

		kzbnDbFields[ tableFldConstants.suggestedby.ordinal() ].fieldName = "KZBN_SUGGESTEDBY";
		kzbnDbFields[ tableFldConstants.suggestedby.ordinal() ].fieldType = 'V';

		kzbnDbFields[ tableFldConstants.responsibility.ordinal() ].fieldName = "KZBN_RESPONSIBILITY";
		kzbnDbFields[ tableFldConstants.responsibility.ordinal() ].fieldType = 'V';

		kzbnDbFields[ tableFldConstants.completedon.ordinal() ].fieldName = "KZBN_COMPLETEDON";
		kzbnDbFields[ tableFldConstants.completedon.ordinal() ].fieldType = 'D';

		kzbnDbFields[ tableFldConstants.status.ordinal() ].fieldName = "KZBN_STATUS";
		kzbnDbFields[ tableFldConstants.status.ordinal() ].fieldType = 'C';

		kzbnDbFields[ tableFldConstants.accrejremarks.ordinal() ].fieldName = "KZBN_ACCREJREMARKS";
		kzbnDbFields[ tableFldConstants.accrejremarks.ordinal() ].fieldType = 'V';
		
		kzbnDbFields[ tableFldConstants.acrejby.ordinal() ].fieldName = "KZBN_ACREJBY";
		kzbnDbFields[ tableFldConstants.acrejby.ordinal() ].fieldType = 'V';
		
		kzbnDbFields[ tableFldConstants.implementedby.ordinal() ].fieldName = "KZBN_IMPLEMENTEDBY";
		kzbnDbFields[ tableFldConstants.implementedby.ordinal() ].fieldType = 'V';

		kzbnDbFields[ tableFldConstants.verifyremarks.ordinal() ].fieldName = "KZBN_VERIFYREMARKS";
		kzbnDbFields[ tableFldConstants.verifyremarks.ordinal() ].fieldType = 'V';

		kzbnDbFields[ tableFldConstants.impremarks.ordinal() ].fieldName = "KZBN_IMPREMARKS";
		kzbnDbFields[ tableFldConstants.impremarks.ordinal() ].fieldType = 'V';
		
		kzbnDbFields[ tableFldConstants.compremarks.ordinal() ].fieldName = "KZBN_COMPREMARKS";
		kzbnDbFields[ tableFldConstants.compremarks.ordinal() ].fieldType = 'V';
		
		kzbnDbFields[ tableFldConstants.acceptrejon.ordinal() ].fieldName = "KZBN_ACCEPTREJON";
		kzbnDbFields[ tableFldConstants.acceptrejon.ordinal() ].fieldType = 'D';
		
		kzbnDbFields[ tableFldConstants.implementedon.ordinal() ].fieldName = "KZBN_IMPLEMENTEDON";
		kzbnDbFields[ tableFldConstants.implementedon.ordinal() ].fieldType = 'D';
		
		kzbnDbFields[ tableFldConstants.verifiedon.ordinal() ].fieldName = "KZBN_VERIFIEDON";
		kzbnDbFields[ tableFldConstants.verifiedon.ordinal() ].fieldType = 'D';
		
		kzbnDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldName = "KZBN_VERIFIEDBY";
		kzbnDbFields[ tableFldConstants.verifiedby.ordinal() ].fieldType = 'V';
		
		kzbnDbFields[ tableFldConstants.completedby.ordinal() ].fieldName = "KZBN_COMPLETEDBY";
		kzbnDbFields[ tableFldConstants.completedby.ordinal() ].fieldType = 'V';
		
		kzbnDbFields[ tableFldConstants.ehsrelated.ordinal() ].fieldName = "KZBN_EHSRELATED";
		kzbnDbFields[ tableFldConstants.ehsrelated.ordinal() ].fieldType = 'C';
		
		kzbnDbFields[ tableFldConstants.ehsstatus.ordinal() ].fieldName = "KZBN_EHSSTATUS";
		kzbnDbFields[ tableFldConstants.ehsstatus.ordinal() ].fieldType = 'V';
		
		kzbnDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldName = "KZBN_REFDOCTYPE";
		kzbnDbFields[ tableFldConstants.refdoctype.ordinal() ].fieldType = 'V';
		
		kzbnDbFields[ tableFldConstants.refdocno.ordinal() ].fieldName = "KZBN_REFDOCNO";
		kzbnDbFields[ tableFldConstants.refdocno.ordinal() ].fieldType = 'V';
		
		kzbnDbFields[ tableFldConstants.others.ordinal() ].fieldName = "KZBN_OTHERS";
		kzbnDbFields[ tableFldConstants.others.ordinal() ].fieldType = 'C';
		
		kzbnDbFields[ tableFldConstants.implementcost.ordinal() ].fieldName = "KZBN_IMPLEMENTCOST";
		kzbnDbFields[ tableFldConstants.implementcost.ordinal() ].fieldType = 'N';
		
		kzbnDbFields[ tableFldConstants.approvalflag.ordinal() ].fieldName = "KZBN_APPROVALFLAG";
		kzbnDbFields[ tableFldConstants.approvalflag.ordinal() ].fieldType = 'C';
		
		kzbnDbFields[ tableFldConstants.mocrequired.ordinal() ].fieldName = "KZBN_MOCREQUIRED";
		kzbnDbFields[ tableFldConstants.mocrequired.ordinal() ].fieldType = 'V';
		
		kzbnDbFields[ tableFldConstants.active.ordinal() ].fieldName = "KZBN_ACTIVE";
		kzbnDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';
		
		kzbnDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "KZBN_CREATEDBY";
		kzbnDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		kzbnDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "KZBN_CREATEDON";
		kzbnDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		kzbnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "KZBN_MODIFIEDON";
		kzbnDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_KZN_TL_KAIZENBANKMST, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_KZN_TL_KAIZENBANKMST, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_KZN_TL_KAIZENBANKMST ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.keyid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.keyid.ordinal()] + "'";
		return sql;
	}

	public String getRecall() {
		String sql = "SELECT K.KZBN_KEYID, K.KZBN_FLID, K.KZBN_ELEMENTID,to_char(K.KZBN_DATE,'dd-Mon-YYYY'), K.KZBN_KAIZEN, K.KZBN_BENEFIT,"; 
				sql+= "to_char(K.KZBN_TARGETDATE,'dd-Mon-YYYY'), K.KZBN_PQCDSME, K.KZBN_SUGGESTEDBY,K.KZBN_RESPONSIBILITY,to_char(K.KZBN_COMPLETEDON,'dd-Mon-YYYY'), K.KZBN_STATUS,";
				sql+= "K.KZBN_REMARKS, K.KZBN_MOCITEM, K.KZBN_TEMPFIELD2, K.KZBN_TEMPFIELD3, K.KZBN_TEMPFIELD4, K.KZBN_IMPLEMENTCOST,K.KZBN_CREATEDBY, K.KZBN_ACTIVE, K.KZBN_CREATEDON,";
				sql+="K.KZBN_MODIFIEDON FROM KZN_TL_KAIZENBANKMST K where k.kzbn_keyid =?";
		CommonMessage.debugMsg("sql in check"+sql);
				
				return sql;
	}

	public static String selectmst(String keyid) {
		return "SELECT * FROM "+TBL_KZN_TL_KAIZENBANKMST+" WHERE KZBN_KEYID = ?";
	}
	public static String selectkznData(String keyid) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder();
		sql.append(" select kzbn_kaizen from kzn_tl_kaizenbankmst ");
		sql.append(" where kzbn_keyid='"+keyid+"' ");
		
		return sql.toString();
	}
	public static String getUpdateotherSql(KznTlKaizenbankmst kznTlKaizenbankmst, String type) {
		CommonMessage.debugMsg("type SQL   "+type);
		String sql = "	UPDATE  KZN_TL_KAIZENBANKMST SET KZBN_KEYID = '"+kznTlKaizenbankmst.getKzbnKeyid()+"',KZBN_STATUS = '"+kznTlKaizenbankmst.getKzbnStatus()+"' , " ;
		if("S".equals(type)){		
			sql+=" KZBN_BENEFIT = '{}',KZBN_TARGETDATE =  to_date( '"+kznTlKaizenbankmst.getKzbnTargetdate()+"','dd-Mon-yyyy hh24:mi:ss'), KZBN_PQCDSME = '{}',KZBN_RESPONSIBILITY = '"+kznTlKaizenbankmst.getKzbnResponsibility()+"',KZBN_ACCEPTREJON =  to_date( '"+kznTlKaizenbankmst.getKzbnAcceptrejon()+"','dd-Mon-yyyy hh24:mi:ss'),";
			sql+=" KZBN_ACREJBY = '"+kznTlKaizenbankmst.getKzbnAcrejby()+"', KZBN_ACCREJREMARKS = '"+kznTlKaizenbankmst.getKzbnAccrejremarks()+"' ";
		}else if("A".equals(type)){		
			sql+=" KZBN_BENEFIT = '{}',KZBN_TARGETDATE =  to_date( '"+kznTlKaizenbankmst.getKzbnTargetdate()+"','dd-Mon-yyyy hh24:mi:ss'), KZBN_PQCDSME = '{}',KZBN_RESPONSIBILITY = '"+kznTlKaizenbankmst.getKzbnResponsibility()+"',KZBN_ACCEPTREJON =  to_date( '"+kznTlKaizenbankmst.getKzbnAcceptrejon()+"','dd-Mon-yyyy hh24:mi:ss'),";
			sql+=" KZBN_ACREJBY = '"+kznTlKaizenbankmst.getKzbnAcrejby()+"', KZBN_ACCREJREMARKS = '"+kznTlKaizenbankmst.getKzbnAccrejremarks()+"' ";
		}else if("C".equals(type)){		
			sql+=" KZBN_COMPLETEDON =  to_date( '"+kznTlKaizenbankmst.getKzbnCompletedon()+"','dd-Mon-yyyy hh24:mi:ss'),KZBN_COMPLETEDBY = '"+kznTlKaizenbankmst.getKzbnCompletedby()+"',";
			sql+=" KZBN_COMPREMARKS = '"+kznTlKaizenbankmst.getKzbnCompremarks()+"' ";
		}else if("I".equals(type)){		
			sql+=" KZBN_IMPLEMENTEDON =  to_date( '"+kznTlKaizenbankmst.getKzbnImplementedon()+"','dd-Mon-yyyy hh24:mi:ss'),KZBN_IMPLEMENTEDBY = '"+kznTlKaizenbankmst.getKzbnImplementedby()+"',";
			sql+=" KZBN_IMPREMARKS = '"+kznTlKaizenbankmst.getKzbnImpremarks()+"' ";
		}else if("V".equals(type)){		
			sql+=" KZBN_VERIFIEDON =  to_date( '"+kznTlKaizenbankmst.getKzbnVerifiedon()+"','dd-Mon-yyyy hh24:mi:ss'),KZBN_VERIFIEDBY = '"+kznTlKaizenbankmst.getKzbnVerifiedby()+"',";
			sql+=" KZBN_VERIFYREMARKS = '"+kznTlKaizenbankmst.getKzbnVerifyremarks()+"' ";
		}
			sql+=" WHERE KZBN_KEYID = '"+kznTlKaizenbankmst.getKzbnKeyid()+"'";
			return sql;
			
	}

	public static String checkDuplication(KznTlKaizenbankmst kznTlKaizenbankmst) {
		String sql="SELECT COUNT(*) FROM "+KznTlKaizenbankmstSql.TBL_KZN_TL_KAIZENBANKMST+" WHERE  KZBN_FLID='"+kznTlKaizenbankmst.getKzbnFlid()+"' AND KZBN_KAIZEN='"+kznTlKaizenbankmst.getKzbnKaizen()+"'";
		return sql;
	}

	public static String updateKaizenBank(KznTlKaizenbankmst kznTlKaizenbankmst) {
		CommonMessage.debugMsg(" Status :: Remarks :: SQL Files :: "+kznTlKaizenbankmst.getKzbnAccrejremarks());
		String sql="UPDATE "+KznTlKaizenbankmstSql.TBL_KZN_TL_KAIZENBANKMST+" SET KZBN_EHSSTATUS='"+kznTlKaizenbankmst.getKzbnEhsstatus()+"',KZBN_ACCREJREMARKS='"+kznTlKaizenbankmst.getKzbnAccrejremarks()+"' WHERE  KZBN_KEYID='"+kznTlKaizenbankmst.getKzbnKeyid()+"'";
		CommonMessage.debugMsg(" sql :: Checking for Remarks ::  "+sql);
		return sql;
	}
	
	//******************Direct Kaizen changes Method*******************************//
	public static String selectMasterKeyid(String keyid) {
		return "SELECT * FROM "+TBL_KZN_TL_KAIZENBANKMST+" WHERE KZBN_KEYID = ?";
	}
	public static String selectCategory(String keyid) {
		// TODO Auto-generated method stub
		//KINK_KEYID,
		String sql= " select KZCT_KEYID,KZCT_CODE,KZCT_NAME ";
		sql+= " from KZN_TL_CATEGORYTHMMST " ;
		sql+= " where KZCT_KEYID='"+keyid+"'";
	    return sql;	
	}
}

