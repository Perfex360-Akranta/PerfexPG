package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import com.akranta.tpm.utils.CommonMessage;
import java.util.List;

import com.akranta.tpm.dao.AddMachineDao;
import com.akranta.tpm.dao.sql.AddMachineSql;
//import com.akranta.tpm.dao.sql.CliTlStandardsSql;

public class AddMachineDaoImpl implements AddMachineDao {
	private DBActionTemplate dbActionTemplate; 
	public AddMachineDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	@Override
	public List<String[]> getAddMachine(String machineID, String getEquipmentId) {
		// TODO Auto-generated method stub
		try
		{
			CommonMessage.debugMsg("inside dao Impl getAddMachine");
			String sql = AddMachineSql.getAddMachine(machineID,getEquipmentId);
			List<String[]> addmachineList = dbActionTemplate.getDataList(sql);
			
			return addmachineList;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}

	@Override
	public String geteqpGroup(String machineID) {
		// TODO Auto-generated method stub
		try
		{
			CommonMessage.debugMsg("inside dao Impl geteqpGroup "+machineID);
			//String sql = CliTlStandardsSql.getEqpGrp(machineID);
			//String geteqpgroup = dbActionTemplate.getSingleValue(tbl_gen_tl_machinemst, sql, machineID, "");
			//select MCHM_EQUIPMENTGROUP from gen_tl_machinemst where MCHM_KEYID
			String geteqpgroup = dbActionTemplate.getSingleValue("gen_tl_machinemst", "MCHM_EQUIPMENTGROUP", "MCHM_KEYID", machineID);
			 CommonMessage.debugMsg("geteqpgroup   :"+geteqpgroup.toString());
			return geteqpgroup;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}
	@Override
	public void CreateMachineArea(String addMachGridStr, String addMachId) {
		// TODO Auto-generated method stub
		try
		{
			List<String> sqls = new ArrayList<String>();
			String sql = null;
			CommonMessage.debugMsg("inside dao Impl getAddMachine");
			String[] MachArea=addMachGridStr.split(",");
			for(int i=0;i<MachArea.length;i++){
				sql = AddMachineSql.insertintoMachineArea(MachArea[i],addMachId);
				sqls.add(sql);	
			}
			
			 
			
			dbActionTemplate.executeStatements(sqls);
			
			
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		
	}

}
