package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.AbnormalityBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.GenTlFnlnrolemapDao;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.impl.GenTlFnlnrolemapDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.FunctionalLocn;
import com.akranta.tpm.model.GenTlFnlnrolemap;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlFnlnroleteam;
import com.akranta.tpm.model.GenTlTeamtradelink;
import com.akranta.tpm.service.GenTlFnlnrolemapService;
import com.akranta.tpm.service.api.PillarRoleLinkServiceApi;
import com.akranta.tpm.service.api.TradeRoleLinkServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

/**
 * Servlet implementation class AbnormalityFormServiceImpl
 */
public class GenTlFnlnrolemapServiceImpl  implements GenTlFnlnrolemapService {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private CommonFilterDao commonFilterDao;
	private GenTlFnlnrolemapDao GenTlFnlnrolemapDao;
	private Validations validations ;
	
	private TradeRoleLinkServiceApi serviceApi;
	private PillarRoleLinkServiceApi pillarServiceApi;
	
    public GenTlFnlnrolemapServiceImpl(DBActionTemplate dbActionTemplate){
    	try{
        commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
        GenTlFnlnrolemapDao = new GenTlFnlnrolemapDaoImpl(dbActionTemplate);
        validations = new Validations();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
    
    public void GenTlFnlnrolemapServiceImplJwt(String JwtToken){
    	try{
    		GenTlFnlnrolemapDao.GenTlFnlnrolemapDaoImplJwt(JwtToken);
    		
    		serviceApi = new TradeRoleLinkServiceApi(JwtToken);
    		
    		pillarServiceApi = new PillarRoleLinkServiceApi(JwtToken); // added
    	}
    	
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	@Override
	public GenTlFnlnrolemap create(GenTlFnlnrolemap genTlFnlnrolemap)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.create(genTlFnlnrolemap);
	}
	@Override
	public GenTlFnlnrolemap update(GenTlFnlnrolemap genTlFnlnrolemap)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.update(genTlFnlnrolemap);
	}
	@Override
	public GenTlFnlnrolemap delete(GenTlFnlnrolemap genTlFnlnrolemap)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.delete(genTlFnlnrolemap);
	}
	@Override
	public List<String[]> getRoleMappingGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getRoleMappingGrid(commonFilter);
	}
	@Override
	public List<FunctionalLocn> getAllfnLocation(FunctionalLocn functionalLocn)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getAllfnLocation(functionalLocn);
	}
	@Override
	public List<GenTlFnlnrolemap> create(
			List<GenTlFnlnrolemap> genTlFnlnrolemapList) throws Exception {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("Inside Create in serviceimpl");
			String validationsFor = "create";
			String xml = "GenTlFnlnrolemap";
			//validations.validate(genTlFnlnrolemapList, xml, validationsFor);			
			genTlFnlnrolemapList=fillValuesGenTlFnlnrolemap(genTlFnlnrolemapList);
			return GenTlFnlnrolemapDao.create(genTlFnlnrolemapList);
		} catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}
	}
	private List<GenTlFnlnrolemap> fillValuesGenTlFnlnrolemap(List<GenTlFnlnrolemap> genTlFnlnrolemapList) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("start Of  fillValues genTlFnlnrolemapList");
		for(int i=0 ;i<=genTlFnlnrolemapList.size()-1;i++){		
			genTlFnlnrolemapList.get(i).setFrlTimestamp(dateTime);
			genTlFnlnrolemapList.get(i).setFrlModtimestamp(dateTime);
			genTlFnlnrolemapList.get(i).setFrlActive("Y");
			if (genTlFnlnrolemapList.get(i).getFrlLevel() == null)
				genTlFnlnrolemapList.get(i).setFrlLevel("0");
			
			if (genTlFnlnrolemapList.get(i).getFrlFnlnKeyid() == null)
				genTlFnlnrolemapList.get(i).setFrlFnlnKeyid("{}");

			if (genTlFnlnrolemapList.get(i).getFrlRoleKeyid() == null)
				genTlFnlnrolemapList.get(i).setFrlRoleKeyid("{}");
			
			if (genTlFnlnrolemapList.get(i).getFrlTempfield1() == null)
				genTlFnlnrolemapList.get(i).setFrlTempfield1("-");

			if (genTlFnlnrolemapList.get(i).getFrlTempfield2() == null)
				genTlFnlnrolemapList.get(i).setFrlTempfield2("-");
			
			if (genTlFnlnrolemapList.get(i).getFrlTempfield3() == null)
				genTlFnlnrolemapList.get(i).setFrlTempfield3("-");
			
			if (genTlFnlnrolemapList.get(i).getFrlTempfield4() == null)
				genTlFnlnrolemapList.get(i).setFrlTempfield4("-");
			
			if (genTlFnlnrolemapList.get(i).getFrlTempfield5() == null)				
				genTlFnlnrolemapList.get(i).setFrlTempfield5("-");
			
			if (genTlFnlnrolemapList.get(i).getFrlNoofpersons() == null)
				genTlFnlnrolemapList.get(i).setFrlNoofpersons("M");
			
			genTlFnlnrolemapList.get(i).setFrlNoofpersons(genTlFnlnrolemapList.get(i).getFrlNoofpersons().substring(0, 1));
		}
		CommonMessage.debugMsg("End Of  fillValues genTlFnlnrolemapList");
		return genTlFnlnrolemapList;
	}
	
	private List<GenTlFnlnroleteam> fillValuesGenTlFnlnroleteam(List<GenTlFnlnroleteam> genTlFnlnroleteamList) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.dateTimeNow();
		CommonMessage.debugMsg("start Of  fillValues genTlFnlnrolemapList");
		for(int i=0 ;i<=genTlFnlnroleteamList.size()-1;i++){		
			genTlFnlnroleteamList.get(i).setFrtCreatedon(dateTime);
			genTlFnlnroleteamList.get(i).setFrtModifiedon(dateTime);
			genTlFnlnroleteamList.get(i).setFrtActive("Y");

			if (genTlFnlnroleteamList.get(i).getFrtFnlnKeyid() == null)
				genTlFnlnroleteamList.get(i).setFrtFnlnKeyid("{}");
			
			if (genTlFnlnroleteamList.get(i).getFrtFrlKeyid() == null)
				genTlFnlnroleteamList.get(i).setFrtFrlKeyid("{}");

			if (genTlFnlnroleteamList.get(i).getFrtRoleKeyid() == null)
				genTlFnlnroleteamList.get(i).setFrtRoleKeyid("{}");
			
			if (genTlFnlnroleteamList.get(i).getFrtTempfield1() == null)
				genTlFnlnroleteamList.get(i).setFrtTempfield1("-");

			if (genTlFnlnroleteamList.get(i).getFrtTempfield2() == null)
				genTlFnlnroleteamList.get(i).setFrtTempfield2("-");
			
			if (genTlFnlnroleteamList.get(i).getFrtTempfield3() == null)
				genTlFnlnroleteamList.get(i).setFrtTempfield3("-");
			
			if (genTlFnlnroleteamList.get(i).getFrtTempfield4() == null)
				genTlFnlnroleteamList.get(i).setFrtTempfield4("-");
			
			if (genTlFnlnroleteamList.get(i).getFrtTempfield5() == null)				
				genTlFnlnroleteamList.get(i).setFrtTempfield5("-");
			
			if (genTlFnlnroleteamList.get(i).getFrtEmpmKeyid() == null)
				genTlFnlnroleteamList.get(i).setFrtEmpmKeyid("{}");
			
			genTlFnlnroleteamList.get(i).setTeamtradelink(fillValuesGenTlTeamTradeLink(genTlFnlnroleteamList.get(i)));
		}
		CommonMessage.debugMsg("End Of  fillValues genTlFnlnrolemapList");
		return genTlFnlnroleteamList;
	}
	
	private List<GenTlTeamtradelink> fillValuesGenTlTeamTradeLink(GenTlFnlnroleteam genTlFnlnroleteam) {
		// TODO Auto-generated method stub
		String dateTime = CommonFunctions.dateTimeNow();
		List<GenTlTeamtradelink> genTlTeamtradelinkList=genTlFnlnroleteam.getTeamtradelink();
		//CommonMessage.debugMsg("start Of  fillValues genTlFnlnrolemapList:"+ genTlTeamtradelinkList.size());
		if (genTlFnlnroleteam.getTeamtradelink()!=null){
			for(int i=0 ;i<=genTlTeamtradelinkList.size()-1;i++){		
				genTlTeamtradelinkList.get(i).setFrpCreatedon(dateTime);
				genTlTeamtradelinkList.get(i).setFrpCreatedby(genTlFnlnroleteam.getFrtCreatedby());
				genTlTeamtradelinkList.get(i).setFrpModifiedon(dateTime);
				genTlTeamtradelinkList.get(i).setFrpActive("Y");
	
				if (genTlTeamtradelinkList.get(i).getFrpProcessid() == null)
					genTlTeamtradelinkList.get(i).setFrpProcessid("{}");
	
				if (genTlTeamtradelinkList.get(i).getFrpSubprocessid() == null)
					genTlTeamtradelinkList.get(i).setFrpSubprocessid("{}");
				
				if (genTlTeamtradelinkList.get(i).getFrpSubsubprocessid() == null)
					genTlTeamtradelinkList.get(i).setFrpSubsubprocessid("{}");
				
				if (genTlTeamtradelinkList.get(i).getFrpTempfield1() == null)
					genTlTeamtradelinkList.get(i).setFrpTempfield1("-");
	
				if (genTlTeamtradelinkList.get(i).getFrpTempfield2() == null)
					genTlTeamtradelinkList.get(i).setFrpTempfield2("-");
				
				if (genTlTeamtradelinkList.get(i).getFrpTempfield3() == null)
					genTlTeamtradelinkList.get(i).setFrpTempfield3("-");
				
				if (genTlTeamtradelinkList.get(i).getFrpTempfield4() == null)
					genTlTeamtradelinkList.get(i).setFrpTempfield4("-");
				
				if (genTlTeamtradelinkList.get(i).getFrpTempfield5() == null)				
					genTlTeamtradelinkList.get(i).setFrpTempfield5("-");
			}
		}
		CommonMessage.debugMsg("End Of  fillValues genTlTeamtradelinkList");
		return genTlTeamtradelinkList;
	}

	
	@Override
	public List<String[]> getRoleTeamCntrlGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getRoleTeamCntrlGrid(commonFilter);
	}
	@Override
	public List<GenTlFnlnrolemap> delete(
			List<GenTlFnlnrolemap> genTlFnlnrolemapList) throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.delete(genTlFnlnrolemapList);
	}
	@Override
	public List<ComboBox> getRoleComboList(ComboFilter roleFilterComboFilter) throws Exception {
		// TODO Auto-generated method stub
	
		//roleFilterComboFilter.setCodeField("ROLE_KEYID");
		roleFilterComboFilter.setNameField("ROLE_NAME");
		roleFilterComboFilter.setIdField("ROLE_KEYID");
		roleFilterComboFilter.setTableName(TableNames.TBL_ADM_TL_ROLEMST);
		return commonFilterDao.fillComboValues(roleFilterComboFilter);
	}
	@Override
	public List<ComboBox> getTradeComboList(ComboFilter filterComboFilter) throws Exception {
		// TODO Auto-generated method stub	
		filterComboFilter.setNameField("TRDM_NAME");
		filterComboFilter.setIdField("TRDM_KEYID");
		filterComboFilter.setTableName(TableNames.TBL_GEN_TL_TRADEMST);
		return commonFilterDao.fillComboValues(filterComboFilter);
	}
	@Override
	public List<ComboBox> getProcessComboList(ComboFilter filterComboFilter) throws Exception {
		// TODO Auto-generated method stub	
		filterComboFilter.setNameField("QPOM_NAME");
		filterComboFilter.setIdField("QPOM_KEYID");
		filterComboFilter.setTableName(TableNames.TBL_QTM_TL_PROCESSMST);
		return commonFilterDao.fillComboValues(filterComboFilter);
	}
	@Override
	public List<ComboBox> getSubProcessComboList(ComboFilter filterComboFilter) throws Exception {
		// TODO Auto-generated method stub	
		filterComboFilter.setNameField("SUBP_NAME");
		filterComboFilter.setIdField("SUBP_KEYID");
		filterComboFilter.setTableName(TableNames.TBL_QTM_TL_SUBPROCESSMST);
		return commonFilterDao.fillComboValues(filterComboFilter);
	}
	@Override
	public List<ComboBox> getSubSubProcComboList(ComboFilter filterComboFilter) throws Exception {
		// TODO Auto-generated method stub	
		filterComboFilter.setNameField("SBSP_NAME");
		filterComboFilter.setIdField("SBSP_KEYID");
		filterComboFilter.setTableName(TableNames.TBL_QTM_TL_SUBSUBPROCESSMST);
		return commonFilterDao.fillComboValues(filterComboFilter);
	}
	@Override
	public GenTlFnlnroleteam deleteTeam(GenTlFnlnroleteam genTlFnlnroleteam)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.deleteTeam(genTlFnlnroleteam);
	}
	@Override
	public List<String[]> getRoleTeamGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getRoleTeamGrid(commonFilter);
	}
	@Override
	public List<GenTlFnlnroleteam> createTeam(
			List<GenTlFnlnroleteam> genTlFnlnroleteamList) throws Exception {
		// TODO Auto-generated method stub
		try {
			CommonMessage.debugMsg("Inside Create");
			String validationsFor = "create";
			String xml = "GenTlFnlnroleteam";
			// --------Vignesh 24Oct2025
			//validations.validate(genTlFnlnroleteamList, xml, validationsFor);			
			genTlFnlnroleteamList=fillValuesGenTlFnlnroleteam(genTlFnlnroleteamList);
			return GenTlFnlnrolemapDao.createTeam(genTlFnlnroleteamList);
		} catch (ValidationExceptions e) {
			throw new ValidationExceptions(e.getMessage());
		}
	}
	@Override
	public List<GenTlFnlnroleteam> deleteTeam(
			List<GenTlFnlnroleteam> genTlFnlnroleteamList) throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.deleteTeam(genTlFnlnroleteamList);
	}
	@Override
	public List<String[]> getRoleTeamEmpGrid(CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getRoleTeamEmpGrid(commonFilter);
	}
	@Override
	public List<String[]> getRoleMappingMainGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getRoleMappingMainGrid(commonFilter);
	}
	
	@Override
	public List<String[]> getRoleTeamMainGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getRoleTeamMainGrid(commonFilter);
	}
	@Override
	public String SelectOriginalId(String flId) throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.SelectOriginalId(flId);
	}
	@Override
	public String SelectFnlnName(String flId) throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.SelectFnlnName(flId);
	}
	@Override
	public String getTradeType(String trade) throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getTradeType(trade);
	}
	@Override
	public String getSubProcessId(String subSubProcessId) throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getSubProcessId(subSubProcessId);
	}
	@Override
	public String getProcessId(String subProcessId) throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getProcessId(subProcessId);
	}
	@Override
	public List<String[]> getEmpAllList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getEmpAllList(commonFilter);
	}
	@Override
	public List<String[]> getEmpTeamList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getEmpTeamList(commonFilter);
	}
	@Override
	public List<String[]> getRoleAllList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getRoleAllList(commonFilter);
	}
	@Override
	public List<String[]> getRoleTeamList(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getRoleTeamList(commonFilter);
	}
	@Override
	public List<String[]> getRoleTeamAllMainGrid(CommonFilter commonFilter)
			throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getRoleTeamAllMainGrid(commonFilter);
	}
	@Override
	public Workbook getRoleTeamAllExportExcel(CommonFilter commonFilter,
			JSONObject tblJSONObj, String format) throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getRoleTeamAllExportExcel(commonFilter,tblJSONObj,format);
	}
	@Override
	public String SelectLocation(String flId) throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.SelectLocation(flId);
	}
	@Override
	public List<String[]> getlevelrole(String flid) throws Exception {
		return GenTlFnlnrolemapDao.getlevelrole(flid);
	}
	/**********************************new*****************************/
	
	@Override
	public Workbook getEmployeeRoleLocationExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)
			throws Exception {
		return GenTlFnlnrolemapDao.getEmployeeRoleLocationExportExcel(commonFilter,tblJSONObj,format);
	}
	
	/********************************************/
	@Override
	public List<String[]> getEmployeeGridData(CommonFilter commonFilter)
			throws Exception {
		
		return GenTlFnlnrolemapDao.getEmployeeGridData(commonFilter);
	}
	@Override
	public List<String[]> getTransactionSummaryGridData(CommonFilter commonFilter) throws Exception{
		return GenTlFnlnrolemapDao.getTransactionSummaryGridData(commonFilter);
	}
	@Override
	public Workbook getTransactionSummaryGridDataExportExcel(
			CommonFilter commonFilter, JSONObject tblJSONObj, String format)
			throws Exception {
		
		return GenTlFnlnrolemapDao.getTransactionSummaryGridDataExportExcel(commonFilter, tblJSONObj, format);
	}
	@Override
	public List<String[]> getlocation(String flid) throws Exception {
		// TODO Auto-generated method stub
		return GenTlFnlnrolemapDao.getlocation(flid);
	}
	
	
	
	


@Override
	public List<ComboBox> getTradeComboListforrole(ComboFilter filterComboFilter) throws Exception {
	    filterComboFilter.setNameField("TRDM_NAME");
	    filterComboFilter.setIdField("TRDM_KEYID");
	    filterComboFilter.setTableName(TableNames.TBL_GEN_TL_TRADEMST);
	    return commonFilterDao.fillComboValues(filterComboFilter);
	}

@Override
	public List<ComboBox> getRoleComboListfortrade(ComboFilter comboFilter) throws Exception {
	    comboFilter.setNameField("ROLE_NAME");
	    comboFilter.setIdField("ROLE_KEYID");
	    //comboFilter.setTableName(TableNames.TBL_GEN_TL_ROLEMST);
	    comboFilter.setTableName(TableNames.TBL_ADM_TL_ROLEMST); // CHANGED HERE 
	    return commonFilterDao.fillComboValues(comboFilter);
	}	

	
@Override
	public List<ComboBox> getPillarComboListforrole(ComboFilter filterComboFilter) throws Exception {
	    filterComboFilter.setNameField("TPMP_NAME");
	    filterComboFilter.setIdField("TPMP_KEYID");
	    filterComboFilter.setTableName(TableNames.TBL_GEN_TL_TPMPILLARMST);
	    return commonFilterDao.fillComboValues(filterComboFilter);
	}
	
@Override
	public List<String[]> getTradeRoleLinkGrid(CommonFilter commonFilter) throws Exception {
	    return GenTlFnlnrolemapDao.getTradeRoleLinkGrid(commonFilter);
	}	

@Override
	public List<String[]> getPillarRoleLinkGridData(CommonFilter commonFilter, String pillarId, String roleId) throws Exception {
	    return GenTlFnlnrolemapDao.getPillarRoleLinkList(commonFilter, pillarId, roleId);
	}
	
@Override
	public void saveTradeRoleLink(String tradeId, String roleId, String createdBy) throws Exception {
	    //GenTlFnlnrolemapDao.saveTradeRoleLink(tradeId, roleId, createdBy);
	    //serviceApi.saveTradeRoleLinkApi(tradeId, roleId, createdBy);
		
		String response = serviceApi.saveTradeRoleLinkApi(tradeId, roleId, createdBy);
		
		if(response == "Trade-Role conflict occurred") 
		{
			throw new Exception("This Trade is already linked to a Role. A Trade can be linked to only one Role.");
		}

	    
	}

	@Override
	public void deleteTradeRoleLink(String tradeId, String roleId) throws Exception {
	    //GenTlFnlnrolemapDao.deleteTradeRoleLink(tradeId, roleId);
		String response = serviceApi.deleteTradeRoleLinkApi(tradeId, roleId);
	    
	    if(response == "Trade-Role conflict occurred") 
		{
			throw new Exception("This Trade-Role link does not exist.");
		}
	}	

@Override
	public void savePillarRoleLink(String pillarId, String roleId, String createdBy) throws Exception {
	    //GenTlFnlnrolemapDao.savePillarRoleLink(pillarId, roleId, createdBy);
		String response = pillarServiceApi.savePillarRoleLinkApi(pillarId, roleId, createdBy);
	    
	    if ("Pillar-Role conflict occurred".equals(response))
	    {
	        throw new Exception("This Pillar-Role combination already exists.");
	    }
	}
	
	@Override
	public void deletePillarRoleLink(String pillarId, String roleId) throws Exception {
	    //GenTlFnlnrolemapDao.deletePillarRoleLink(pillarId, roleId);
		String response = pillarServiceApi.deletePillarRoleLinkApi(pillarId, roleId);
	    
	    if ("Pillar-Role conflict occurred".equals(response))
	    {
	        throw new Exception("This Pillar-Role link does not exist.");
	    }
	}
	
	
	
	
}
