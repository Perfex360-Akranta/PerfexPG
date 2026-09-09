package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.QtmTlComplaintgalleryBean;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlComplaintgallery;

public interface CompliantGalleryService {
	
	public List<String[]> getAllCompliant(CommonFilter commonFilter) throws Exception;

	public QtmTlComplaintgallery create(
			QtmTlComplaintgallery newQtmTlComplaintgallery,
			QtmTlComplaintgallery existQtmTlComplaintgallery,
			QtmTlComplaintgalleryBean newQtmTlComplaintgalleryBean)throws ValidationExceptions, Exception;
	
	public QtmTlComplaintgallery update(
			QtmTlComplaintgallery newQtmTlComplaintgallery,
			QtmTlComplaintgallery existQtmTlComplaintgallery,
			QtmTlComplaintgalleryBean newQtmTlComplaintgalleryBean) throws ValidationExceptions, Exception;

	public QtmTlComplaintgallery getvalues(String keyid);

	public  QtmTlComplaintgallery delete(QtmTlComplaintgallery newQtmTlComplaintgallery)throws Exception;

	public String[] getImgName(String keyid,String fileDir,String imagepath);

	public void deleteImage(String cmgaKeyid, String string, String string2) throws BusinessApplicationExceptions, Exception;

	public Workbook getCompGalExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception;
	
	public List<String[]> getCustCompliantCount(CommonFilter commonFilter) throws Exception;
	public Workbook getCustComplintsCountExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception;
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception;
	public void CompliantGalleryServiceImplJwt(String JwtToken);
	
	public List<ComboBox> getFillcombobox(CommonFilter commonFilter,ComboFilter comboFilter)throws Exception;
	public List<ComboBox> getGradeSpecComboList(CommonFilter commonFilter, ComboFilter comboFilter) throws Exception;

}
