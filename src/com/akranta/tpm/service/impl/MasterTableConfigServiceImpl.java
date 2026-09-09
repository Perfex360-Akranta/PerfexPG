package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.MasterTableConfigDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.MasterTableConfigDaoImpl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlMmcmst;
import com.akranta.tpm.model.MastTblConfigColMeta;
import com.akranta.tpm.model.MastTblConfigTableMeta;
import com.akranta.tpm.service.MasterTableConfigService;
import com.akranta.tpm.upload.UploadException;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.PrjConstants;
import com.akranta.tpm.utils.CommonMessage;
public class MasterTableConfigServiceImpl implements MasterTableConfigService {
	
	
	MasterTableConfigDao masterTableConfigDao = null; 
	CommonFilterDao commonFilterDao = null;
	public MasterTableConfigServiceImpl(DBActionTemplate dbActionTemplate)
	{
		masterTableConfigDao = new MasterTableConfigDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
	}
	
	public GenTlMmcmst  getMasterTableConfigDetails(String menuId) throws NoDataFoundException, SQLException, Exception{
		
		return masterTableConfigDao.getMasterTableConfigDetails(menuId);
	}

	public List<String[]>  getMasterTableData(GenTlMmcmst genTlMmcmst,GridParams  gridParams) throws NoDataFoundException, SQLException, Exception{
		
		return masterTableConfigDao.getMasterTableData(genTlMmcmst,gridParams );
	}
	public int getMasterTableCount(GenTlMmcmst genTlMmcmst,GridParams gridParams) throws Exception{
		return masterTableConfigDao.getMasterTableCount(genTlMmcmst, gridParams);
	}
	
	public List<ComboBox> getMasterTblComboList(ComboFilter mastTblCombo) throws Exception 
	{
		return commonFilterDao.fillComboValues(mastTblCombo);
	}
	
	public MastTblConfigTableMeta getMasterTableMeta(String tableName) throws NoDataFoundException, Exception{
		return masterTableConfigDao.getMasterTableMeta(tableName) ;
	}
	public MastTblConfigTableMeta populateMasterTableData(GenTlMmcmst genTlMmcmst,MastTblConfigTableMeta mastTblConfigTableMeta, String keyid) throws Exception{
		return masterTableConfigDao.populateMasterTableData(genTlMmcmst,mastTblConfigTableMeta,keyid) ;
	}
	
	public MastTblConfigTableMeta saveMasterTableData(MastTblConfigTableMeta mastTblConfigTableMeta) throws ValidationExceptions, Exception{
		validateMasterTblData(mastTblConfigTableMeta);
		fillValues(mastTblConfigTableMeta);
		
 CommonMessage.debugMsg("mastTblConfigTableMeta::::"+mastTblConfigTableMeta);
		if( mastTblConfigTableMeta.isInsert())
			return masterTableConfigDao.insertMasterTableData(mastTblConfigTableMeta) ;
		
		return masterTableConfigDao.updateMasterTableData(mastTblConfigTableMeta);
	}
	
	public MastTblConfigTableMeta deleteMasterTableData(MastTblConfigTableMeta mastTblConfigTableMeta) throws Exception{
		
		checkRecordSelected(mastTblConfigTableMeta);
		return masterTableConfigDao.deleteMasterTableData( mastTblConfigTableMeta);
	}
	
	public Workbook getMasrerTblMasterExcel(GenTlMmcmst genTlMmcmst,JSONObject colModel,String format,GridParams gridParams) throws Exception{
		return masterTableConfigDao.getMasrerTblMasterExcel(genTlMmcmst,colModel,format, gridParams) ;
	}
	public void uploadmasterexcel(String excelFileName,String tableName,String userid)throws UploadException, BusinessApplicationExceptions,Exception {

	     masterTableConfigDao.uploadmasterexcel(excelFileName,tableName,userid);		
}	
  // ------- Altered By vignesh ----------------------//
	private void checkRecordSelected(MastTblConfigTableMeta mastTblConfigTableMeta) throws ValidationExceptions{
		List<MastTblConfigColMeta> mastTblConfigColMetaList =   mastTblConfigTableMeta.getMastTblConfigCols();
		boolean isDelete =false;
		for(MastTblConfigColMeta mastTblConfigColMeta:mastTblConfigColMetaList )
		{
			CommonMessage.debugMsg("inside if " + mastTblConfigColMeta.isPrimaryKey() + " and "  + mastTblConfigColMeta.getNewValue() + " and " + mastTblConfigColMeta.isMandatory() );
			// ------- Altered by Vignesh 06Nov2025------- //
			if( mastTblConfigColMeta.isPrimaryKey() && ! CommonFunctions.isValidKeyId(mastTblConfigColMeta.getNewValue()))
			
			{
				throw new ValidationExceptions("Select Record");
			}
			else if( mastTblConfigColMeta.isPrimaryKey() &&  CommonFunctions.isValidKeyId(mastTblConfigColMeta.getNewValue() )){
				isDelete = true;
				break;
			}
			
		}
		if( ! isDelete )
			throw new ValidationExceptions("Select Record");
			
		
	}
//	private void checkRecordSelected(MastTblConfigTableMeta mastTblConfigTableMeta) throws ValidationExceptions{
//		List<MastTblConfigColMeta> mastTblConfigColMetaList =   mastTblConfigTableMeta.getMastTblConfigCols();
//		boolean isDelete =false;
//		for(MastTblConfigColMeta mastTblConfigColMeta:mastTblConfigColMetaList )
//		{			
//			if( mastTblConfigColMeta.isPrimaryKey() && ! CommonFunctions.isValidKeyId(mastTblConfigColMeta.getNewValue())){
//				throw new ValidationExceptions("Select Record");
//			}
//			else if( mastTblConfigColMeta.isMandatory() &&  CommonFunctions.isValidKeyId(mastTblConfigColMeta.getNewValue() )){
//				isDelete = true;
//				break;
//			}
//			
//		}
//		if( ! isDelete )
//			throw new ValidationExceptions("Select Record");
//			
//		
//	}
	
	public void validateMasterTblData(MastTblConfigTableMeta mastTblConfigTableMeta) throws ValidationExceptions{
		List<MastTblConfigColMeta> mastTblConfigCols = mastTblConfigTableMeta.getMastTblConfigCols();
		
		StringBuffer validationString = new StringBuffer();
		String newValue =null,validFor = null;
		for( MastTblConfigColMeta mastTblConfigCol:mastTblConfigCols ){
			newValue = mastTblConfigCol.getNewValue();
			validFor = null;
			
			if(  mastTblConfigCol.isMandatory() && ! mastTblConfigCol.isPrimaryKey() && ! CommonFunctions.isValidKeyId(newValue) ){
				validFor = "required";
			}
			else if( ( mastTblConfigCol.getType() != Types.DATE && mastTblConfigCol.getType() != Types.TIMESTAMP ) &&
					newValue != null && CommonFunctions.isValidKeyId(newValue)  && newValue.length() > mastTblConfigCol.getSize() )
				validFor = "max-length";
			
			if( validFor != null){
				String fieldId = mastTblConfigCol.getFieldId() ;
				
				String msg=null;
				if( validFor.equals("required") ){
					if( fieldId.startsWith("cmb") )
						msg = "Select ";
					else
						msg = "Enter ";
					
					msg += mastTblConfigCol.getDisplayName();
				}
				else if( validFor.equals("max-length") ){
					msg = mastTblConfigCol.getDisplayName() + " Exceeds Max Length ";
				}
				validationString.append("[\"");
				validationString.append(mastTblConfigCol.getFieldId() );
				validationString.append("\",\"");
				validationString.append(msg);
				validationString.append("\"],");
			}	
		}
		if( validationString.length() > 0 ){
			validationString.deleteCharAt(validationString.lastIndexOf(","));
			 validationString.append("]");
			 
			throw new ValidationExceptions("["+validationString.toString());
		}
	}
	
//	public void fillValues(MastTblConfigTableMeta mastTblConfigTableMeta){
//		
//		String dateTime = CommonFunctions.dateTimeNow();
//		
//		boolean insert = true;
//		String newValue = null;
//		
//		for( int i = 0; i < mastTblConfigTableMeta.getMastTblConfigCols().size(); i++ ){
//			newValue = mastTblConfigTableMeta.getMastTblConfigCols().get(i).getNewValue() ;
//			if( mastTblConfigTableMeta.getMastTblConfigCols().get(i).isPrimaryKey())
//			{
//				CommonMessage.debugMsg(" Master Data Primary Key "+mastTblConfigTableMeta.getMastTblConfigCols().get(i).isPrimaryKey());
//				if( CommonFunctions.isValidKeyId(newValue) )
//					insert = false;
//			}
//			else if( !  mastTblConfigTableMeta.getMastTblConfigCols().get(i).isMandatory() && ! CommonFunctions.isValidKeyId(newValue) ) {
//				int type = mastTblConfigTableMeta.getMastTblConfigCols().get(i).getType();
//				CommonMessage.debugMsg(" Master Data Primary Key "+type);
//
//				String defaultVal = mastTblConfigTableMeta.getMastTblConfigCols().get(i).getDefaultValue();
//				CommonMessage.debugMsg(" defaultVal " + defaultVal + " newValue " + newValue);
//				if( defaultVal != null && defaultVal.startsWith("EMP") )
//					mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(mastTblConfigTableMeta.getCreatedBy());
//				else if( defaultVal != null && ! CommonFunctions.isValidKeyId(newValue) && ( type == Types.DATE || type == Types.TIMESTAMP)){
//					if( mastTblConfigTableMeta.getMastTblConfigCols().get(i).getColName().toUpperCase().endsWith("CREATEDON") )
//					{				
//						
//						if( insert)
//							mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(dateTime);
//						else{
//							String oldValue = mastTblConfigTableMeta.getMastTblConfigCols().get(i).getValue();
//							mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(oldValue);
//						}	
//					}else if( mastTblConfigTableMeta.getMastTblConfigCols().get(i).getColName().toUpperCase().endsWith("MODIFIEDON") )
//						mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(dateTime);
//					else
//						mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(Constants.pgPassNullDate);
//					
//				}
//				else if( defaultVal != null && ! CommonFunctions.isValidKeyId(newValue) ){
//					String oldValue = mastTblConfigTableMeta.getMastTblConfigCols().get(i).getDefaultValue();
//					
//					mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(oldValue);
//				}
//					
//			}
//					
//		}
//		
//		mastTblConfigTableMeta.setInsert(insert);
//	}
	
	
	public void fillValues(MastTblConfigTableMeta mastTblConfigTableMeta){
		boolean insert = true;

		try {
		String dateTime = CommonFunctions.pg_PG_dateTimeNow();
;//CommonFunctions.getDate();
		
		String newValue = null;
		
		for( int i = 0; i < mastTblConfigTableMeta.getMastTblConfigCols().size(); i++ ){
			newValue = mastTblConfigTableMeta.getMastTblConfigCols().get(i).getNewValue() ;
			//CommonMessage.debugMsg();
			if( mastTblConfigTableMeta.getMastTblConfigCols().get(i).isPrimaryKey())
			{
				CommonMessage.debugMsg(" Master Data Primary Key "+mastTblConfigTableMeta.getMastTblConfigCols().get(i).isPrimaryKey());
				if( CommonFunctions.isValidKeyId(newValue) )
					insert = false;
			}
			else if( !  mastTblConfigTableMeta.getMastTblConfigCols().get(i).isMandatory() && ! CommonFunctions.isValidKeyId(newValue) ) {
				int type = mastTblConfigTableMeta.getMastTblConfigCols().get(i).getType();
				CommonMessage.debugMsg(" Master Data Primary Key "+type);
				

				String defaultVal = mastTblConfigTableMeta.getMastTblConfigCols().get(i).getDefaultValue();
				CommonMessage.debugMsg(" defaultVal " + defaultVal + " newValue " + newValue);
				if( defaultVal != null && defaultVal.startsWith("EMP") )
					mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(mastTblConfigTableMeta.getCreatedBy());
				else if( defaultVal != null && ! CommonFunctions.isValidKeyId(newValue) && ( type == Types.DATE || type == Types.TIMESTAMP)){
					if( mastTblConfigTableMeta.getMastTblConfigCols().get(i).getColName().toUpperCase().endsWith("CREATEDON") )
					{				
						
						if( insert) {
							mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(dateTime);
						}
						else{
						String oldValue = mastTblConfigTableMeta.getMastTblConfigCols().get(i).getValue();
						CommonMessage.debugMsg(oldValue +"    oldValue ");
						// oldValue=CommonFunctions.pg_getDateTimeFromPGTimeStamp(oldValue);
						String oldValue2=dateChange(oldValue);
						CommonMessage.debugMsg(oldValue +" After   oldValue ");

							mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(oldValue2);
						}	
					}
					else if( mastTblConfigTableMeta.getMastTblConfigCols().get(i).getColName().toUpperCase().endsWith("MODIFIEDON") )
						mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(dateTime);
					else
						mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(Constants.pgPassNullDate);
					
				}
				else if( defaultVal != null && ! CommonFunctions.isValidKeyId(newValue) ){
					String oldValue = mastTblConfigTableMeta.getMastTblConfigCols().get(i).getDefaultValue();
					
					CommonMessage.debugMsg(oldValue +"  oldValue");
					mastTblConfigTableMeta.getMastTblConfigCols().get(i).setNewValue(oldValue);
				}
					
			}
					
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		mastTblConfigTableMeta.setInsert(insert);
	}
	
	public String dateChange(String inputDate) {
        DateTimeFormatter inputFormatter =
                DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

        DateTimeFormatter outputFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dateTime =
                LocalDateTime.parse(inputDate, inputFormatter);

        String formattedDate = dateTime.format(outputFormatter);
 return formattedDate;
    }

	@Override
	public String locnflid(String loginLocnId) throws SQLException {
		// TODO Auto-generated method stub
		return this.masterTableConfigDao.locnflid(loginLocnId);
	}

	
}
