package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.bean.EmployeeBean;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.GenTlEmpfunclocnlinkDao;
import com.akranta.tpm.dao.JhaTlFiveSAuditareamstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.GenTlEmpfunclocnlinkDaoImpl;
import com.akranta.tpm.dao.impl.JhaTlFiveSAuditareamstDaoImpl;
import com.akranta.tpm.model.EntTlBatchEmployeeLink;
import com.akranta.tpm.model.GenTlEmpfunclocnlink;
import com.akranta.tpm.service.GenTlEmpfunclocnlinkService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;


//GenTlEmpfunclocnlinkDao
public class GenTlEmpfunclocnlinkServiceImpl implements GenTlEmpfunclocnlinkService {

	private CommonFilterDao commonFilterDao;
	private GenTlEmpfunclocnlinkDao gentlempfunloclinkdao;
	private Validations validations ;
	
	public GenTlEmpfunclocnlinkServiceImpl(DBActionTemplate dbActionTemplate) throws Exception
	{
		commonFilterDao =  new CommonFilterDaoImpl(dbActionTemplate);
		gentlempfunloclinkdao = new GenTlEmpfunclocnlinkDaoImpl(dbActionTemplate);
		
	}
	@Override
	public List<GenTlEmpfunclocnlink> create(GenTlEmpfunclocnlink newGenTlEmpfunclocnlink,GenTlEmpfunclocnlink existGenTlEmpfunclocnlink,EmployeeBean employeeBean,List<GenTlEmpfunclocnlink> FactorylinkList,String Factoryvar) throws Exception {
		                        //com.akranta.validations.tpm.validations.kaizencreation.xml - defined rules for server side validations
		
		List<GenTlEmpfunclocnlink> newgentlempfunclocnlinkList = new ArrayList<GenTlEmpfunclocnlink>();
		  fillvalues(newgentlempfunclocnlinkList,existGenTlEmpfunclocnlink,employeeBean,FactorylinkList, Factoryvar);
		  //CommonMessage.debugMsg("GenTlEmpfunclocnlinkServiceImpl");
		  //CommonMessage.debugMsg("newGenTlEmpfunclocnlink"+newGenTlEmpfunclocnlink);
		  return gentlempfunloclinkdao.create(newgentlempfunclocnlinkList,employeeBean);
          
		
	}

	private List<GenTlEmpfunclocnlink> fillvalues(List<GenTlEmpfunclocnlink> newgentlempfunclocnlinkList,GenTlEmpfunclocnlink existGenTlEmpfunclocnlink,EmployeeBean employeeBean,List<GenTlEmpfunclocnlink> FactorylinkList,String Factoryvar) {
		
		List<GenTlEmpfunclocnlink> newgentlempfunclocnlink =FactorylinkList;
		
		
	
		
		int index=0;
		if(FactorylinkList!=null)
		//CommonMessage.debugMsg("FactorylinkList"+FactorylinkList);
		for( GenTlEmpfunclocnlink newGenTlEmpfunclocnlinks : newgentlempfunclocnlink)
		{	
			
			//CommonMessage.debugMsg("newgentlempfunclocnlink"+newgentlempfunclocnlink.getEfllfunclocn());
			String dateTime = CommonFunctions.dateTimeNow();
			CommonMessage.debugMsg("employeeBean.fillv  "+employeeBean.getEmpIdForFuncLoc());
			GenTlEmpfunclocnlink genTlEmpfunclocnLink = newgentlempfunclocnlink.get(index);
			CommonMessage.debugMsg("genTlEmpfunclocnLink1"+genTlEmpfunclocnLink.getEfllfunclocn());
			index++;
			 if(newGenTlEmpfunclocnlinks.getEfllemployeeid()== null)  
		     	newGenTlEmpfunclocnlinks.setEfllemployeeid(employeeBean.getEmpIdForFuncLoc());
			 if(newGenTlEmpfunclocnlinks.getEfllfunclocn()== null)
				 newGenTlEmpfunclocnlinks.setEfllfunclocn(genTlEmpfunclocnLink.getEfllfunclocn());
			 CommonMessage.debugMsg("getEfllemployeeid"+newGenTlEmpfunclocnlinks.getEfllemployeeid());
			 if(newGenTlEmpfunclocnlinks.getEfllfunclocntype()== null)
				newGenTlEmpfunclocnlinks.setEfllfunclocntype(employeeBean.getFuncloctypeForFuncLoc());
			 if(newGenTlEmpfunclocnlinks.getEfllactive()== null)
				 newGenTlEmpfunclocnlinks.setEfllactive("Y");
		     if(newGenTlEmpfunclocnlinks.getEfllcreatedby()== null)
		    	 newGenTlEmpfunclocnlinks.setEfllcreatedby("{}");
			 if(newGenTlEmpfunclocnlinks.getEfllcreatedon()== null)
				 newGenTlEmpfunclocnlinks.setEfllcreatedon(dateTime);
		     if(newGenTlEmpfunclocnlinks.getEfllmodifiedon()== null)
		    	 newGenTlEmpfunclocnlinks.setEfllmodifiedon(dateTime);
		     
		     newgentlempfunclocnlinkList.add(newGenTlEmpfunclocnlinks);
		 
		}
		
		return newgentlempfunclocnlinkList;
		
	}
	/*@Override
	public List<GenTlEmpfunclocnlink> create(
			List<GenTlEmpfunclocnlink> newGenTlEmpfunclocnlink,
			List<GenTlEmpfunclocnlink> existGenTlEmpfunclocnlink,
			EmployeeBean employeeBean,
			List<GenTlEmpfunclocnlink> FactorylinkList, String Factoryvar)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}*/
	
}
		
	


