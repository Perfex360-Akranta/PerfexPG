package com.akranta.tpm.dao.sql;
import com.akranta.tpm.utils.CommonMessage;
public class AddMachineSql {
	private static final String TBL_GEN_TL_MACHINEAREAMST = "GEN_TL_MACHINEAREAMST ";
	private static final String TBL_GEN_TL_MCHMACHINEAREALINK = "GEN_TL_MCHMACHINEAREALINK";
	
	public static String getAddMachine(String machineID, String getEquipmentId) {
		// TODO Auto-generated method stub
		return " SELECT DISTINCT  '0'  as Tick, MCAM_KEYID,MCAM_NAME ,MCAM_CODE FROM  "+
		TBL_GEN_TL_MACHINEAREAMST +" where MCAM_ACTIVE ='Y'  AND MCAM_KEYID NOT IN "+
		"(SELECT MMAL_MACHINEAREAID FROM  "+ TBL_GEN_TL_MCHMACHINEAREALINK +
		" where MMAL_MACHINEID = '"+machineID+"' )  AND MCAM_EQPGROUPID IN ('"+ getEquipmentId +"','{}')";
	}

	public static String insertintoMachineArea(String addMachGridStr,String addMachId) {
		// TODO Auto-generated method stub
		CommonMessage.debugMsg("inside sql of add machine");
		return "insert into "+TBL_GEN_TL_MCHMACHINEAREALINK +" values ('"+addMachId+"'"+",'"+addMachGridStr+"')" ;
		
		
	}

}
