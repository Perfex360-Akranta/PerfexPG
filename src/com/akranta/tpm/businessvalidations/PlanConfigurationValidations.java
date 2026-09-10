package com.akranta.tpm.businessvalidations;

import java.sql.SQLException;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.PlanConfigExceptions;
import com.akranta.tpm.controller.UIUtils;
//import com.akranta.tpm.dao.PlmTlPlanconfigurationDao;
import com.akranta.tpm.dao.impl.DBActionTemplate;
//import com.akranta.tpm.dao.sql.PlmTlPlanconfigurationSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.CommonMessage;

public class PlanConfigurationValidations {
	
	private static DBActionTemplate dbActionTemplate = null;  
	public PlanConfigurationValidations(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate =dbActionTemplate;
	}
	public  void checkMachineExists(String string, String indentifir, String userConfirmation) throws PlanConfigExceptions  {
		// TODO Auto-generated method stub
		String  sql = null ;
		try {
				CommonMessage.debugMsg("inside business validations of planconfig"+userConfirmation);
			
			if(indentifir.equals("M") ){
				 sql = "SELECT DISTINCT MCHM_MACHINENO ,MCHM_KEYID  FROM " +TableNames.TBL_BAL_PLM_TL_PLANCONFIGURATION + 
			      "," + TableNames.TBL_GEN_TL_MACHINEMST +" WHERE PPLC_MACHINEID = MCHM_KEYID " +
						" AND PPLC_LEVEL='M' AND PPLC_ASSEMBLYID ='{}' " +
						" AND PPLC_MACHINEID IN ('" + string + "') ";
			}
			else if(indentifir.equals("A") ){
				sql = "SELECT distinct MCHM_MACHINENO ,MCHM_KEYID    FROM " +
				TableNames.TBL_BAL_PLM_TL_PLANCONFIGURATION + "," + TableNames.TBL_GEN_TL_MACHINEMST +
				 " WHERE PPLC_MACHINEID =MCHM_KEYID " +
				 " AND PPLC_LEVEL ='A' and PPLC_ASSEMBLYID <> '{}' AND PPLC_MACHINEID IN ('"  + string + "') ";
			}
			 CommonMessage.debugMsg("machineData..  :"+sql);
			 String machNumber = null;
			 List<String[]> mchDataList= null;
			 mchDataList = dbActionTemplate.getDataList(sql);
			 if(mchDataList != null){
				 for(int i = 0; i<mchDataList.size();i++){
					 machNumber =mchDataList.get(0)[0];
				 }
				 CommonMessage.debugMsg("machinNo:  :"+machNumber);
			 if( mchDataList.size()>0)
				throw new PlanConfigExceptions("Plan exist in Equipment level for [ "+machNumber+" ], Do you want to delete and reconfigure?");
			}
			
			
		
	  } catch (Exception e) {
				 throw new PlanConfigExceptions(e.getMessage());
			}
			
		}
	/*public  void checkWorkOrderRespExists(String mchId) throws PlanConfigExceptions,SQLException {
		try{
			String sql= "SELECT DISTINCT PWRM_MACHINEID FROM " + TableNames.TBL_PLM_TL_WORESPMST +" WHERE PWRM_MACHINEID IN ('" + mchId + "')";
			CommonMessage.debugMsg("chkWORESP Exists   "+sql);
			List<String[]> woRespData= null;
			woRespData = dbActionTemplate.getDataList(sql);
			if( woRespData.size()>0)
					throw new PlanConfigExceptions("No Work Order Responsibility entry for the Equipment, Do you want to open?");
	   }
	catch(Exception e) {
		 throw new PlanConfigExceptions(e.getMessage());
	}
   }*/
}
	
