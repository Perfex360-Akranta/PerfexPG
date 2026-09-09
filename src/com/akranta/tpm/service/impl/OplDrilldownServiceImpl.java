
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.dao.OplDrilldownDao;
import com.akranta.tpm.dao.impl.OplDrilldownDaoImpl;
import com.akranta.tpm.model.CommonFilter;

import com.akranta.tpm.service.OplDrilldownService;

public class OplDrilldownServiceImpl implements OplDrilldownService{

	private OplDrilldownDao oplDrilldownDao;
	
	
	public OplDrilldownServiceImpl(DBActionTemplate dbActionTemplate)
	{
		oplDrilldownDao = new OplDrilldownDaoImpl(dbActionTemplate);
	
	}
	


	@Override
	public List<String[]> getAllopldrill(CommonFilter commonFilter)	throws Exception {
		// TODO Auto-generated method stub
		return this.oplDrilldownDao.getAllopldrill( commonFilter);
	}



	@Override
	public Workbook getAllopldrillExl(CommonFilter commonFilter,JSONObject colmodel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return this.oplDrilldownDao.getAllopldrillExl( commonFilter,colmodel, rptFormat);
	}
	
}
