package com.akranta.tpm.service.impl;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.MasterModuleGroupDao;
import com.akranta.tpm.dao.impl.MasterModuleGroupDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.AuditmasterModel;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MasterModuleGroup;
import com.akranta.tpm.service.MasterModuleGroupService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
//import com.sun.corba.se.impl.orbutil.closure.Constant;

public class MasterModuleGroupServiceImpl implements MasterModuleGroupService{

	private MasterModuleGroupDao masterModuleGroupDao;
    private CommonFilterDao commonfilterao;
	
	public MasterModuleGroupServiceImpl(DBActionTemplate dbActionTemplate)
	{
		masterModuleGroupDao = new MasterModuleGroupDaoImpl(dbActionTemplate);
		commonfilterao=new CommonFilterDaoImpl(dbActionTemplate);
	}
	
	public List<MasterModuleGroup> getAllMasterModuleGroup(String userId,String pillar) throws Exception
	{
		return this.masterModuleGroupDao.getAllMasterModuleGroup(userId,pillar);
	}
	
	public List<String []> getRelatedMst(String menuCaption,String menuName,String activeRecordFlag,GridParams gridParams) throws Exception
	{
		return this.masterModuleGroupDao.getRelatedMst(menuCaption, menuName, activeRecordFlag, gridParams);
		
	}
	
	public String getMstDatasCount(String menuName,String activeRecordFlag,GridParams gridParams) throws Exception
	{
		return this.masterModuleGroupDao.getMstDatasCount(menuName, activeRecordFlag,gridParams);
	}

	public Workbook generalMstFormExportExcel(String menuCaption,String menuName, String activeRecordFlag,JSONObject colModel,String format, GridParams gridParams) throws Exception {
		return this.masterModuleGroupDao.generalMstFormExportExcel(menuCaption,menuName,activeRecordFlag,colModel,format,gridParams) ;
	}

	@Override
	public void getMakeactive(String menuCaption, String menuName,List<String> paramValues) throws Exception {
		masterModuleGroupDao.getMakeactive(menuCaption,menuName,paramValues);
	}
	
    public List<AuditmasterModel> getauditmastermodule(String userid,String module)throws Exception{
       
       return this.masterModuleGroupDao.getauditmastermodule(userid, module);
    }
    public List<AuditmasterModel> save(List<AuditmasterModel> saveeaudit)throws Exception{
    	
    	 
    	String validationFor;
    	validationFor="save";
    	fillValues(saveeaudit);
    	return this.masterModuleGroupDao.save(saveeaudit);
    }
  
    public List<ComboBox> getauditcombo(ComboFilter comboFilter)throws Exception{
    	CommonMessage.debugMsg("Service Impl getauditcombo");
    	comboFilter.setNameField("MMGR_MENUCAPTION");
		comboFilter.setIdField("ADT_TABLE_NAME");
		comboFilter.setTableName("GEN_TL_MASTERMODULEGROUP"+","+"ADT_TL_TABLES");
		comboFilter.setCondSql("AND ADT_TABLE_NAME=MMGR_TABLENAME");
    	return commonfilterao.fillComboValues(comboFilter);
    }   
  public List<String[]> getauditdata(CommonFilter commonFilter,GridParams gridparam)throws Exception{
	   
	 return masterModuleGroupDao.getauditdata(commonFilter, gridparam);
  } 
  public Workbook getauditdataexcel(CommonFilter commonFilter,JSONObject jsonobj,String format)throws Exception{
    
	  return masterModuleGroupDao.getauditdataexcel(commonFilter, jsonobj, format);
   }

  public List<AuditmasterModel> fillValues(List<AuditmasterModel> auditmastermodellist){
	         String datetime=CommonFunctions.dateTimeNow();
	  	    for(int i=0;i<auditmastermodellist.size();i++){
	    	if(auditmastermodellist.get(i).getAtdactive()==null)
	    		auditmastermodellist.get(i).setAtdactive("N");
	    	 if(auditmastermodellist.get(i).getAtdtriggerenable()==null)  
	    	    auditmastermodellist.get(i).setAtdtriggerenable("N");
	    	 if(auditmastermodellist.get(i).getAtdcreatedon()==null)
	    	    auditmastermodellist.get(i).setAtdcreatedon(datetime);
	    	 if(auditmastermodellist.get(i).getAtdmodifyon()==null)
	    		auditmastermodellist.get(i).setAtdmodifyon(datetime); 
	    	  if(auditmastermodellist.get(i).getAtdcreatedby()==null)
	    		 auditmastermodellist.get(i).setAtdcreatedby("EMP00001");
             }
	    return auditmastermodellist;
      } 
 
  public List<String[]> auditreportdata(CommonFilter commonFilter)throws Exception{
	  
	return masterModuleGroupDao.auditreportdata(commonFilter);
  }
//@iyyappan
public List<ComboBox> getTableCombo(CommonFilter commonfilter)
		throws Exception {
	
		ComboFilter breakdown = commonfilter.getPillarid();
		String titleName=commonfilter.gettitle();
		breakdown.setIdField("A.MMGR_TABLENAME");
		breakdown.setNameField("B.ADT_TABLE_NAME");
		breakdown.setTableName("GEN_TL_MASTERMODULEGROUP A"+","+"ADT_TL_TABLES B");
		breakdown.setCondSql(" AND A.MMGR_MODULE='"+titleName+"' AND B.ADT_TABLE_NAME=A.MMGR_TABLENAME");
		
		
		return ((CommonFilterDaoImpl) commonfilterao).getComboList(breakdown);
	
	
}
 }
