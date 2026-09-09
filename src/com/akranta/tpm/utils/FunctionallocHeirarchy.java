package com.akranta.tpm.utils;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.model.FunctionallocFilter;

public class FunctionallocHeirarchy {
	
	List<FunctionallocFilter> functionallocHeirarchy = null;
	DBActionTemplate  dbActionTemplate ;

	public FunctionallocHeirarchy(DBActionTemplate dbActionTemplate ){
		this.dbActionTemplate = dbActionTemplate;
		functionallocHeirarchy = new ArrayList<FunctionallocFilter>();
	}

	public List<FunctionallocFilter> fillFunctionallocHeirarchy(){
		StringBuilder sql = new StringBuilder();
		sql.append(" Select FNLN_KEYID,FNLN_ORIGINALID,FUNCTIONALLOC,FNLN_ELEMENTID,DISPLAYCODE FROM ");
		sql.append(" GEN_VW_FNLN where DISPLAYCODE in ('COMP','LOCN','SBU','PBU','SECT','CELL' ) ");
		try{
			List<String[]> funLoc = dbActionTemplate.getDataList(sql.toString()) ;
			if(funLoc != null && funLoc.size() > 0 ){
				for(String [] row : funLoc ){
					functionallocHeirarchy.add( new FunctionallocFilter(row[0],row[2],row[3],row[4]));
				}
			}
			return functionallocHeirarchy;
		}catch(Exception e){
			
		}
		return null;
	}
}
