package com.akranta.tpm.dao.impl;

import java.util.List;

import com.akranta.tpm.dao.KnowWhyDao;
import com.akranta.tpm.utils.CommonMessage;
public class KnowWhyDaoImpl implements KnowWhyDao {
	private DBActionTemplate dbActionTemplate;
	
	public KnowWhyDaoImpl(DBActionTemplate dbActionTemplate){
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	@Override
	public List<String[]> getAllKnow() throws Exception {
		StringBuffer sql= new StringBuffer();

	 sql.append("select 'Possible Causes','Know Why','Solution','Normal Condition' from dual"
		+" union all"
		+" select 'DM Delivery PO4 high (>0.01ppm)','DM Water phosphates enter de-aerator>> Enters into feed water and spray water>> Enters secondary superheater>> Enters main steam','DM Delivery phosphates to be checked daily once','DM Delivery phosphate <0.01ppm' from dual union all"
		+" select 'Cyclone separators fixing loose','Gap is created between cyclones and waterbox>> Water escapes to steam space>> Water will carryover to to the scrubber>> Drum water will enter drum steam>> PO4 in drum water increases main steam PO4 content','Cyclone separators to be inspected for proper fixing once a  year','Cyclones are fixed rigidly and there are no water marks on the drum steam side surface' from dual union all"
		+" select 'Primary scrubbers gap between plates is high','High gap leads to escape of drum water>> Water escpaes to steam space>> PO4 in water evaporates along with steam>> Steam with high PO4 passes to drum steam>> Steam passes to the secondary superheater and main steam','Primary scrubber plate gaps to be inspected at leat once ayear','Scrubber plates gap to be <1mm' from dual union all"
		+" select 'High amount of TSP addition in HP dosing tank','High TSP increases phosphate content in chemical solution>> High PO4 will be dosed through HP dosing pump>> Drum water phosphate content increases>> High PO4 content causes PO4 to escape to drum steam>> PO4 content increases in main steam','SOP to be made and followed for the HP dosing chemical solution preparation  and mixing','Normal Condition 4' from dual");


		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		CommonMessage.debugMsg("Grid value"+ gridData);
		return gridData;
	}
	@Override
	public List<String[]> getAllKnowWhy() throws Exception {
		StringBuffer sql = new StringBuffer();

		sql.append("Select 'Pillar','Developed By' ,'Approved By','Defect Description'  from dual"
		+" union all"
		+" select 'KOBETSU KAIZEN-KK','ATPL-AT','EMPLOYEE1-1001','Laboratory analysis of phosphates in Main steam is greater than 0.03ppm' from dual");

		CommonMessage.debugMsg("sql....."+sql);
		List<String[]> gridData = dbActionTemplate.getDataList(sql.toString());
		CommonMessage.debugMsg("Grid value"+ gridData);
		return gridData;
	}

}
