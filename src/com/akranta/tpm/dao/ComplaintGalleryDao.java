package com.akranta.tpm.dao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import net.sf.json.JSONObject;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlComplaintgallery;

public interface ComplaintGalleryDao {
	public List<String[]> getAllCompliant(CommonFilter commonFilter) throws Exception ;

	public QtmTlComplaintgallery create(
			QtmTlComplaintgallery newQtmTlComplaintgallery)throws Exception ;

	public QtmTlComplaintgallery update(
			QtmTlComplaintgallery newQtmTlComplaintgallery)throws Exception ;

	public QtmTlComplaintgallery getValues(String keyid);

	public QtmTlComplaintgallery delete(
			QtmTlComplaintgallery newQtmTlComplaintgallery)throws Exception;

	public String[] getImgName(String keyid,String fileDir,String imagepath);

	public void deleteImage(String cmgaKeyid, String imgType, String refDoc) throws BusinessApplicationExceptions, Exception;

	public Workbook getCompGalExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;

	public String getElementId(String cmgaFlid) throws Exception;
	public List<String[]> getCustCompliantCount(CommonFilter commonFilter) throws Exception;
	public Workbook getCustComplintsCountExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception;
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception;
	public abstract void ComplaintGalleryDaoImplJwt(String jwtToken);

}
