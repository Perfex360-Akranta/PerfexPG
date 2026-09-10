package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.BAL_PlmTlCbmstdcadtl;
import com.akranta.tpm.model.BAL_PlmTlMethodtasklist;
import com.akranta.tpm.model.BAL_PlmTlShutdowncal;
import com.akranta.tpm.model.BAL_PlmTlStandards;

public interface BAL_PlmTlStandardsDao {

	public abstract BAL_PlmTlStandards create(BAL_PlmTlStandards plmTlStandards, String dKeyId) throws Exception;
	public abstract BAL_PlmTlStandards update(BAL_PlmTlStandards plmTlStandards, String dKeyId) throws Exception;
	public abstract BAL_PlmTlStandards delete(BAL_PlmTlStandards plmTlStandards) throws Exception;
	public abstract List<String[]> getAllsubtype();
	public abstract List<String[]> getAllgridData(CommonFilter commonFilter) throws Exception;
	public abstract BAL_PlmTlStandards getFillValue(String pmstdKeyid) throws NoDataFoundException, SQLException, Exception;
	public abstract List<String[]> getAllsprGriddata(String pmsdkeyid);
	public abstract List<String[]> getSprPickup(String standardId);
	/*public abstract void generateCalendar(String pmsdEffectivedate,
			String pmsdFactoryid, String pmsdSectionid, String pmsdCellid,
			String pmsdMachineid, String pmsdAssemblyid,
			String pmsdFrequencyunit, String pmsdMouldid, String pmsdFlid) throws Exception;
	*/
	public void generateCalendar(String pmsdEffectivedate,
			String pmsdFactoryid, String pmsdSectionid, String pmsdCellid,
			String pmsdMachineid, String pmsdAssemblyid,
			String pmsdFrequencyunit, String pmsdMouldid, String monthMMM) throws Exception; 
	public abstract List<String[]> getactSubValue(String replActSub) throws Exception;
	public abstract List<String[]> getAlljhclittools(String toolpmsdid);
	public abstract List<String[]> getchkplnexists(String machorasswise,
			String machineId);
	public abstract List<String[]> getAllgridassmData(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> getassmgridData(CommonFilter commonFilter) throws Exception;
	public abstract String delSpares(String pmstdId)throws Exception;
	public abstract String delTool(String pmstdId)throws Exception;
	public abstract List<String[]> getCBM(String pmStandardId)throws Exception;
	public abstract BAL_PlmTlCbmstdcadtl createCBM(
			BAL_PlmTlCbmstdcadtl newPlmTlCbmstdcadtl, String dKeyId)throws Exception;
	public abstract BAL_PlmTlCbmstdcadtl updateCBM(
			BAL_PlmTlCbmstdcadtl newPlmTlCbmstdcadtl, String dKeyId)throws Exception;
	public abstract BAL_PlmTlCbmstdcadtl getFillValueCMB(String pmsdId)throws Exception;
	public abstract List<String[]> getPermitLinkData(String pmStandardId)throws Exception;
	public abstract List<String[]> getfillJobType()throws Exception;
	public abstract List<String[]> getfillActivity()throws Exception;
	public abstract List<String[]> getCbmGrid(CommonFilter commonFilter)throws Exception;
	
	//PM Report
	
	public List<String[]> getPMReport(CommonFilter commonFilter) throws Exception;	
	public List<String[]> getMethodTaskList(CommonFilter commonFilter)throws Exception;
	public abstract BAL_PlmTlMethodtasklist createMethodTask( BAL_PlmTlMethodtasklist newPlmTlMethodtasklist)throws Exception;
	
	// TO COPY STATNDARDS
	public abstract List<String[]> getEquipmentList(CommonFilter commonFilter) throws Exception;
	public abstract String getEquipflid(CommonFilter commonFilter) throws Exception;
	public abstract List<String[]> copyStandards(List<String> eqlist,List<String> actlst, String elementid, String tradeid)throws Exception;
	public abstract Workbook stdExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format)throws Exception;
	public abstract String delteStandards(String detailKeyid, String cellId,String machineId)throws Exception;
	  public abstract void BAL_PlmTlStandardsDaoImplJwt(String jwtToken);
	  public abstract  List<String[]> getMultiplePmsdList(CommonFilter commonFilter) throws Exception;
		
}

