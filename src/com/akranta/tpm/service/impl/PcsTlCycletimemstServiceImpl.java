/*Created By : Siddharth.A*/
package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.bean.PcsCycletimemstBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.PcsTlCycletimemstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.PcsTlCycletimemstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.PcsTlCycletimemst;
import com.akranta.tpm.service.PcsTlCycletimemstService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class PcsTlCycletimemstServiceImpl implements PcsTlCycletimemstService {
	
	private PcsTlCycletimemstDao pcsTlCycletimemstDao ;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	
	
	public PcsTlCycletimemstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pcsTlCycletimemstDao = new PcsTlCycletimemstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
 	}
	
	public void setPcsTlCycletimemstDao(PcsTlCycletimemstDao pcsTlCycletimemstDao)
	{
		this.pcsTlCycletimemstDao = pcsTlCycletimemstDao;
	}

	public List<PcsTlCycletimemst> save(List<PcsTlCycletimemst> newPcsTlCycletimemst,List<PcsTlCycletimemst> oldPcsTlCycletimemst,  PcsCycletimemstBean pcsCycletimemstBean)  throws ValidationExceptions, Exception 
	{
		
	/*	for( PcsTlCycletimemst pcsTlCycletimemst:newPcsTlCycletimemst)
		{
			CommonMessage.debugMsg("newPlmTlSparedtl in save func"+newPcsTlCycletimemst.size());
			validations.validate(pcsTlCycletimemst,"SparesPickupCreation","create");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
		}
	*/	
		fillValues(newPcsTlCycletimemst,oldPcsTlCycletimemst,pcsCycletimemstBean);
		return  pcsTlCycletimemstDao.save( newPcsTlCycletimemst);
	}
	
	@Override
	public PcsTlCycletimemst delete(PcsTlCycletimemst newPcsTlCycletimemst)	throws Exception {
		// TODO Auto-generated method stub
		
		return  pcsTlCycletimemstDao.delete(newPcsTlCycletimemst);
	}
	
	@Override
	public List<ComboBox> getSubGroupCombo(String conSql) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setIdField("MODELID");
		comboFilter.setNameField("MODELNAME");
		
		if(UIUtils.isValidKeyId(conSql))
			comboFilter.setCondSql("AND PRODUCTID = '"+conSql+"'");
		
		comboFilter.setTableName(TableNames.TBL_PCS_VW_PRODUCTMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	@Override
	public List<ComboBox> getProductCombo(String condSql) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("PRDM_CODE");
		comboFilter.setIdField("PRDM_KEYID");
		comboFilter.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	

	@Override
	public List<String[]> getPcsData() throws Exception {
		// TODO Auto-generated method stub
		return  pcsTlCycletimemstDao.getPcsData();
	}
	
	@Override
	public Workbook cellMngExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return  pcsTlCycletimemstDao.cellMngExportExcel(commonFilter,tblJSONObj,format);
	}


	@Override
	public List<String[]> getPcsEntryData(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		return  pcsTlCycletimemstDao.getPcsEntryData(cellId, machineId, productId, prodGroupId,gridParams);
	}
	public int getTotalCountSql(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams)
	{
		return  pcsTlCycletimemstDao.getTotalCountSql(cellId, machineId, productId, prodGroupId,gridParams);
	}

	private List<PcsTlCycletimemst> fillValues(List<PcsTlCycletimemst> newPcsTlCycletimemst,List<PcsTlCycletimemst> oldPcsTlCycletimemsts,	PcsCycletimemstBean pcsCycletimemstBean)
	{
		CommonMessage.debugMsg("Inside Service impl");
		
		String dateTime = CommonFunctions.dateTimeNow();
		PcsTlCycletimemst oldPcsTlCycletimemst = null;
		
		List<PcsTlCycletimemst> pcsTlCycletimemstList =new ArrayList<PcsTlCycletimemst>();
		/*	if( oldPcsTlCycletimemsts != null && oldPcsTlCycletimemsts.size() > 0 )
			oldPcsTlCycletimemst =  (PcsTlCycletimemst)oldPcsTlCycletimemsts.get(0);
		
	*/	
		
		for( PcsTlCycletimemst pcsTlCycletimemst : newPcsTlCycletimemst)
		{	
			if(pcsTlCycletimemst.getCytmKeyid()==null)			
			{	
				pcsTlCycletimemst.setCytmCreatedon(dateTime);
				
				CommonMessage.debugMsg("created on if keyid null"+pcsTlCycletimemst.getCytmCreatedon());
			}	
			else
			{
			//	CommonMessage.debugMsg("Inside Else created on"+oldPcsTlCycletimemst.getCytmCreatedon());
				pcsTlCycletimemst.setCytmCreatedon(dateTime);
			}
			pcsTlCycletimemst.setCytmModifiedon(dateTime);
			pcsTlCycletimemst.setCytmCreatedby(pcsCycletimemstBean.getCreatedBy());
			
			if(pcsTlCycletimemst.getCytmProdgroupid()==null)	
				pcsTlCycletimemst.setCytmProdgroupid("{}");
			
			//if(pcsTlCycletimemst.getCytmTempfield1()==null)
				pcsTlCycletimemst.setCytmTempfield1("-");
			
			//if(pcsTlCycletimemst.getCytmTempfield2()==null)
				pcsTlCycletimemst.setCytmTempfield2("-");

			//if(pcsTlCycletimemst.getCytmTempfield3()==null)
				pcsTlCycletimemst.setCytmTempfield3("-");
			
			//if(pcsTlCycletimemst.getCytmTilldate()==null)
				pcsTlCycletimemst.setCytmTilldate(Constants.futureNullDate);
			/*pcsTlCycletimemst.setCytmCavity("1");
			pcsTlCycletimemst.setCytmMandrels("1");
			pcsTlCycletimemst.setCytmManpower("1");
			*/
			pcsTlCycletimemst.setCytmActive("Y");
			
			
			pcsTlCycletimemstList.add(pcsTlCycletimemst);
		}
	
		return pcsTlCycletimemstList;
		
	}

	@Override
	public void getcycleTimeUpdate(String product, String cycleTime)throws Exception {
		  pcsTlCycletimemstDao.getcycleTimeUpdate(product, cycleTime);
		
	}

	@Override
	public List<String[]> getPcsPrdData(String cellId, String machineId,String productId, String prodGroupId, GridParams gridParams)throws Exception {

		return  pcsTlCycletimemstDao.getPcsPrdData(cellId, machineId, productId, prodGroupId,gridParams);
	}

	
	
	
}
