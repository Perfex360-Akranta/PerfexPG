package com.akranta.tpm.businessvalidations;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.sql.GenTlFunctionallocnSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class FunctionalLocValidations {
	
	private DBActionTemplate dbActionTemplate = null;  
	public FunctionalLocValidations(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void setDBActionTemplate(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public boolean checkForParentvalue(String parentValue ) throws NoDataFoundException, Exception{
		
		String sql = GenTlFunctionallocnSql.getChildRecordCountSql();
		Object [] args =  new Object [] { TableNames.TBL_GEN_TL_FUNCTIONALLOCN ,"FNLN_PARENTID",parentValue};
		
		List<String[]> data = dbActionTemplate.getDataList(sql, args);
		 
		if( data != null ){
			int count =Integer.parseInt(((String[] )data.get(0))[0]);
			if( count > 0)
				return true;
			
		}
		return false;
	}
	
	public void elementExistsinFunctionalLoc(String elementID) throws SQLException, BusinessApplicationExceptions{
		
		String orginalID = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_FUNCTIONALLOCN, "FNLN_ORIGINALID", "FNLN_ELEMENTID", elementID);
		
		if( CommonFunctions.isValidKeyId(orginalID) )
			throw new BusinessApplicationExceptions("elementID-exist");
		
	}
	
public void checkOriginalIdExistsinFunctionalLoc(String originalId) throws SQLException, BusinessApplicationExceptions{
		
		String orginalID = dbActionTemplate.getSingleValue(TableNames.TBL_GEN_TL_FUNCTIONALLOCN, "FNLN_ORIGINALID", "FNLN_ORIGINALID", originalId);		
		if( CommonFunctions.isValidKeyId(orginalID) )
			throw new BusinessApplicationExceptions("ORIGINAL-exist");		
	}
	
	

}
