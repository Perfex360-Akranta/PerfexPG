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
import com.akranta.tpm.dao.BAL_PcsTlCycletimemstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.BAL_PcsTlCycletimemstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PcsTlCycletimemst;
import com.akranta.tpm.service.BAL_PcsTlCycletimemstService;
import com.akranta.tpm.service.api.BAL_PcsTlCycletimeServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.utils.Validations;

public class BAL_PcsTlCycletimemstServiceImpl implements BAL_PcsTlCycletimemstService {
	
	private BAL_PcsTlCycletimeServiceApi balPcsTlCycletimeServiceApi;
	
	private BAL_PcsTlCycletimemstDao pcsTlCycletimemstDao ;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	
	//jwt
	@Override 
	 public void BAL_PcsTlCycletimemstServiceImplJwt(String jwtToken) { 
	        try { 
	        	balPcsTlCycletimeServiceApi = new BAL_PcsTlCycletimeServiceApi(jwtToken); 
	        	pcsTlCycletimemstDao.BAL_PcsTlCycletimemstDaoImplJwt(jwtToken);
	        	
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	}
	
	
	public BAL_PcsTlCycletimemstServiceImpl(DBActionTemplate dbActionTemplate)
	{
		pcsTlCycletimemstDao = new BAL_PcsTlCycletimemstDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
 	}
	
	
	
	public void setPcsTlCycletimemstDao(BAL_PcsTlCycletimemstDao pcsTlCycletimemstDao)
	{
		this.pcsTlCycletimemstDao = pcsTlCycletimemstDao;
	}

	public List<BAL_PcsTlCycletimemst> save(List<BAL_PcsTlCycletimemst> newPcsTlCycletimemst,List<BAL_PcsTlCycletimemst> oldPcsTlCycletimemst,  PcsCycletimemstBean pcsCycletimemstBean)  throws ValidationExceptions, Exception 
	{
		
	/*	for( PcsTlCycletimemst pcsTlCycletimemst:newPcsTlCycletimemst)
		{
			System.out.println("newPlmTlSparedtl in save func"+newPcsTlCycletimemst.size());
			validations.validate(pcsTlCycletimemst,"SparesPickupCreation","create");//com.akranta.validations.tpm.validations.oplcreation.xml - defined rules for server side validations
		}
	*/	
		fillValues(newPcsTlCycletimemst,oldPcsTlCycletimemst,pcsCycletimemstBean);
		//return  pcsTlCycletimemstDao.save( newPcsTlCycletimemst);
		
		  List<BAL_PcsTlCycletimemst> savedList = new ArrayList<>();
		  
		  for (BAL_PcsTlCycletimemst balPcsTlCycletimemst : newPcsTlCycletimemst) {
		  BAL_PcsTlCycletimemst saved =
		  balPcsTlCycletimeServiceApi.createorupdate(balPcsTlCycletimemst);
		  savedList.add(saved); }
		 

	    return savedList;
	}
	
	@Override
	public BAL_PcsTlCycletimemst delete(BAL_PcsTlCycletimemst newPcsTlCycletimemst)	throws Exception {
		// TODO Auto-generated method stub
		
		//return  pcsTlCycletimemstDao.delete(newPcsTlCycletimemst);
		return  balPcsTlCycletimeServiceApi.delete(newPcsTlCycletimemst);
	}
	
	@Override
	public List<ComboBox> getSubGroupCombo(String conSql) throws Exception {
		// TODO Auto-generated method stub
		ComboFilter comboFilter = new ComboFilter();
		//comboFilter.setIdField("MODELID");
		//comboFilter.setNameField("MODELNAME");
		comboFilter.setIdField("PRDM_KEYID");
		comboFilter.setNameField("PRDM_NAME");
		
		if(UIUtils.isValidKeyId(conSql))
			comboFilter.setCondSql("AND PRODUCTID = '"+conSql+"'");
		
		//comboFilter.setTableName(TableNames.TBL_PCS_VW_PRODUCTMST);
		comboFilter.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	
	@Override
	public List<ComboBox> getProductCombo(ComboFilter currentFilter , String factId, String cellId) throws Exception {
		// TODO Auto-generated method stub
		
		ComboFilter comboFilter = currentFilter ;
		
		comboFilter.setCodeField("PRDM_CODE");
		comboFilter.setIdField("PRDM_KEYID");
		comboFilter.setNameField("PRDM_NAME");
		
		String condSql ="";	
/*	
		if (UIUtils.isValidKeyId(prdModelId))  
			condSql += " AND PRDM_MODEL ='"+prdModelId+"'";*/
		
		if (UIUtils.isValidKeyId(cellId)) { 
			condSql += " AND PRDM_KEYID IN ( SELECT CYTM_PRODUCTID  FROM BAL_PCS_TL_CYCLETIMEMST " +
					" WHERE CYTM_CELLID='" + cellId + "'  " ;
				condSql += " AND CYTM_ACTIVE='Y' "; 
		}
		//if (UIUtils.isValidKeyId(factId) )  
		//		condSql += " AND CYTM_FACTORYID ='"+factId+"'  ";

		if( UIUtils.isValidKeyId(cellId) )
				condSql += " ) ";

		if (UIUtils.isValidKeyId(factId) ) {  
			CommonFunctions.debugMsg("factId "+ factId);
			condSql += " AND PRDM_FACTORY='"+ factId + "'";
	
		}
	
		
		//condSql += " AND PRDM_ACTIVE = 'Y' ";
		CommonFunctions.debugMsg("Conditionsssql "+condSql);
		comboFilter.setCondSql(condSql);
		
		comboFilter.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);	
		//comboFilter.setTableName(TableNames.BAL_PCS_TL_PRODUCTMST);
		return commonFilterDao.fillComboValues(comboFilter);	
		/*ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("PRDM_CODE");
		comboFilter.setIdField("PRDM_KEYID");
		comboFilter.setNameField("PRDM_NAME");
		comboFilter.setTableName(TableNames.TBL_PCS_TL_PRODUCTMST);
		return commonFilterDao.fillComboValues(comboFilter);*/
	}
public List<ComboBox> getCellCombo(ComboFilter Combofilter)throws Exception{
	
	CommonFunctions.debugMsg("Inside the getCellCombo");
	Combofilter.setNameField("CELL_NAME");
	Combofilter.setIdField("CELL_KEYID");
	Combofilter.setTableName("GEN_TL_CELLMST");
	return commonFilterDao.fillComboValues(Combofilter);
}
//cell
	@Override
	public List<String[]> getPcsData(String cellId) throws Exception {
		// TODO Auto-generated method stub
		return  pcsTlCycletimemstDao.getPcsData(cellId);
	}
	
	public List<String[]> getCellCycletimeData(String cellId, String machineId,
	        String productId, GridParams gridParams) throws Exception {
	    return pcsTlCycletimemstDao.getCellCycletimeData(cellId, machineId, productId, gridParams);
	}
	
	@Override
	public Workbook cellMngExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return  pcsTlCycletimemstDao.cellMngExportExcel(commonFilter,tblJSONObj,format);
	}


	@Override
/*	public List<String[]> getPcsEntryData(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		return  pcsTlCycletimemstDao.getPcsEntryData(cellId, machineId, productId, prodGroupId,gridParams);
	}
	public int getTotalCountSql(String cellId, String machineId, String productId, String prodGroupId,GridParams gridParams)
	{
		return  pcsTlCycletimemstDao.getTotalCountSql(cellId, machineId, productId, prodGroupId,gridParams);
	} */
	
	public List<String[]> getPcsEntryData(String cellId,String productId,GridParams gridParams) throws Exception {
		// TODO Auto-generated method stub
		return  pcsTlCycletimemstDao.getPcsEntryData(cellId, productId,gridParams);
	}
	public int getTotalCountSql(String cellId, String productId,GridParams gridParams)
	{
		return  pcsTlCycletimemstDao.getTotalCountSql(cellId, productId,gridParams);
	}	
	
	
	

	private List<BAL_PcsTlCycletimemst> fillValues(List<BAL_PcsTlCycletimemst> newPcsTlCycletimemst,List<BAL_PcsTlCycletimemst> oldPcsTlCycletimemsts,	PcsCycletimemstBean pcsCycletimemstBean)
	{
		System.out.println("Inside Service impl");
		
		
		//String dateTime = CommonFunctions.dateTimeNow();
		String dateTime = CommonFunctions.pg_dateTimeNow();
		BAL_PcsTlCycletimemst oldPcsTlCycletimemst = null;
		
		List<BAL_PcsTlCycletimemst> pcsTlCycletimemstList =new ArrayList<BAL_PcsTlCycletimemst>();
		/*	if( oldPcsTlCycletimemsts != null && oldPcsTlCycletimemsts.size() > 0 )
			oldPcsTlCycletimemst =  (PcsTlCycletimemst)oldPcsTlCycletimemsts.get(0);
		
	*/	
		for( BAL_PcsTlCycletimemst pcsTlCycletimemst : newPcsTlCycletimemst)
		{	
			if(pcsTlCycletimemst.getCytmKeyid()==null)			
			{	
				pcsTlCycletimemst.setCytmCreatedon(dateTime);
				
				System.out.println("created on if keyid null"+pcsTlCycletimemst.getCytmCreatedon());
			}	
			else
			{
			//	System.out.println("Inside Else created on"+oldPcsTlCycletimemst.getCytmCreatedon());
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
				 String fromDate = pcsTlCycletimemst.getCytmFromdate();
				   if (UIUtils.isValidKeyId(fromDate)) {
				       pcsTlCycletimemst.setCytmFromdate(CommonFunctions.pg_getDateTimeFromDate(fromDate));
				   }
				
				//String CytmTilldate = pcsTlCycletimemst.getCytmTilldate();
				//pcsTlCycletimemst.setCytmTilldate(CommonFunctions.pg_getDateTimeFromDate(CytmTilldate));
				
				//pcsTlCycletimemst.setCytmTilldate(Constants.futureNullDate);	
				   
				   pcsTlCycletimemst.setCytmTilldate(CommonFunctions.pg_getDateTimeFromDate(Constants.futureNullDate));

				
				System.out.println("Till date : " + pcsTlCycletimemst.getCytmTilldate());
				System.out.println("From date : " + pcsTlCycletimemst.getCytmFromdate());
				//elumalai
			   pcsTlCycletimemst.setCytmCavity("0");
			   pcsTlCycletimemst.setCytmMandrels("0");
			  //  pcsTlCycletimemst.setCytmManpower("1");
				if(pcsTlCycletimemst.getCytmMachineid()==null){
                pcsTlCycletimemst.setCytmMachineid("-");
				}
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
/*	public List<String[]> getPcsPrdData(String cellId, String machineId,String productId, String prodGroupId, GridParams gridParams)throws Exception {

		return  pcsTlCycletimemstDao.getPcsPrdData(cellId, machineId, productId, prodGroupId,gridParams);
	}

	*/
	
	public List<String[]> getPcsPrdData(String cellId,String productId,GridParams gridParams)throws Exception {

		return  pcsTlCycletimemstDao.getPcsPrdData(cellId, productId,gridParams);
	}

	
	
}
