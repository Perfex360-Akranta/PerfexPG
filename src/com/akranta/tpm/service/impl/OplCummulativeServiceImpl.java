
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


//import com.akranta.tpm.dao.JhnStandardDao;
import com.akranta.tpm.dao.OplCummulativeDao;
import com.akranta.tpm.dao.impl.OplCummulativeDaoImpl;
import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.OplCummulativeService;

public class OplCummulativeServiceImpl implements OplCummulativeService{

	private OplCummulativeDao oplCummulativeDao;
	
	
	public OplCummulativeServiceImpl(DBActionTemplate dbActionTemplate)
	{
		oplCummulativeDao = new OplCummulativeDaoImpl(dbActionTemplate);
	
	}
	public void OplCummulativeServiceImplJwt(String JwtToken){
	    try{
	    	oplCummulativeDao.OplCummulativeDaoImplJwt(JwtToken);   // dao side
	        // (Optional) if you want service-level direct access
	       //  oplServiceApi = new OplTlMstServiceApi(JwtToken); 
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	

	@Override
	public List<String[]> getAlloplcumm(CommonFilter commonFilter)	throws Exception {
		return this.oplCummulativeDao.getAlloplcumm(commonFilter);
	}


	@Override
	public Workbook getAlloplcummExl(CommonFilter commonFilter,	JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.oplCummulativeDao.getAlloplcummExl(commonFilter,colmodel,rptFormat);
	}
	public List<String[]> getOplCountData(CommonFilter commonFilter)
			throws Exception {
		return this.oplCummulativeDao.getOplCountData(commonFilter);
	}
public Workbook getOplCountExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return oplCummulativeDao.getOplCountExcel(commonFilter,tblJSONObj,format);
	}
}
