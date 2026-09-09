package com.akranta.tpm.service;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.GenTlMmcmst;
import com.akranta.tpm.model.MastTblConfigTableMeta;
import com.akranta.tpm.model.MenuTree;
import com.akranta.tpm.upload.UploadException;

public interface MasterTableConfigService {

	public GenTlMmcmst  getMasterTableConfigDetails(String menuId) throws NoDataFoundException, SQLException, Exception;
	public List<String[]>  getMasterTableData(GenTlMmcmst genTlMmcmst, GridParams gridParams) throws NoDataFoundException, SQLException, Exception;
	public int getMasterTableCount(GenTlMmcmst genTlMmcmst,GridParams gridParams) throws Exception;
	public List<ComboBox> getMasterTblComboList(ComboFilter mastTblCombo ) throws Exception;
	public MastTblConfigTableMeta getMasterTableMeta(String tableName) throws NoDataFoundException, Exception;
	public MastTblConfigTableMeta populateMasterTableData(GenTlMmcmst genTlMmcmst,MastTblConfigTableMeta mastTblConfigTableMeta, String keyid) throws Exception;
	public MastTblConfigTableMeta saveMasterTableData(MastTblConfigTableMeta mastTblConfigTableMeta) throws Exception;
	public MastTblConfigTableMeta deleteMasterTableData(MastTblConfigTableMeta mastTblConfigTableMeta) throws Exception;
	public Workbook getMasrerTblMasterExcel(GenTlMmcmst genTlMmcmst,JSONObject colModel,String format,GridParams gridParams) throws Exception;
	public void uploadmasterexcel(String excelFileName,String tableName,String userid) throws UploadException,BusinessApplicationExceptions, Exception;
	public String locnflid(String loginLocnId) throws SQLException;	
}
