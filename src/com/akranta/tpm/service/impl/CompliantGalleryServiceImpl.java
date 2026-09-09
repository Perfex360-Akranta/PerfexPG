package com.akranta.tpm.service.impl;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.QtmTlComplaintgalleryBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.ComplaintGalleryDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.QtmTlComplaintgallery;
import com.akranta.tpm.service.CompliantGalleryService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.ComplaintGalleryDao;
import com.akranta.tpm.service.api.ComplaintGalleryServiceApi;
//import com.akranta.tpm.service.api.WhywhyServiceApi;
import com.akranta.tpm.service.api.WhywhyServiceApi;


public class CompliantGalleryServiceImpl implements CompliantGalleryService{
    private ComplaintGalleryDao complaintGalleryDao;
    private Validations validations;
    private ComplaintGalleryServiceApi complaintgalleryserviceapi;
    private CommonFilterDao commonFilterDao;
	
	public CompliantGalleryServiceImpl(DBActionTemplate dbActionTemplate){
		complaintGalleryDao = new ComplaintGalleryDaoImpl( dbActionTemplate);
		commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	
	public void CompliantGalleryServiceImplJwt(String JwtToken){
    	try{
    		complaintGalleryDao.ComplaintGalleryDaoImplJwt(JwtToken);
    		complaintgalleryserviceapi = new ComplaintGalleryServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
	
	@Override
	public List<String[]> getAllCompliant(CommonFilter commonFilter) throws Exception {
		return this.complaintGalleryDao.getAllCompliant(commonFilter);
	}
	
	@Override
	public QtmTlComplaintgallery create(QtmTlComplaintgallery newQtmTlComplaintgallery,QtmTlComplaintgallery oldQtmTlComplaintgallery, 
			QtmTlComplaintgalleryBean  abnBean)throws ValidationExceptions,BusinessApplicationExceptions, Exception 
	{
		try
		{
			String validationsFor="create";
			validations.validate(newQtmTlComplaintgallery, "ComplaintGalleryValidations",validationsFor);
			fillValues(newQtmTlComplaintgallery,oldQtmTlComplaintgallery);
			//return complaintGalleryDao.create(newQtmTlComplaintgallery);
			return complaintgalleryserviceapi.saveComplaintGallery(newQtmTlComplaintgallery);
		}
	    catch (ValidationExceptions e){
	    	e.printStackTrace();
	    	throw new ValidationExceptions(e.getMessage());
	   }
    }
	
	@Override
	public QtmTlComplaintgallery update(QtmTlComplaintgallery newQtmTlComplaintgallery,QtmTlComplaintgallery existQtmTlComplaintgallery,QtmTlComplaintgalleryBean cmpglBean)
			throws ValidationExceptions, Exception {
		try
		{
			String validationsFor="update";
			validations.validate(newQtmTlComplaintgallery, "ComplaintGalleryValidations",validationsFor);
			fillValues(newQtmTlComplaintgallery,existQtmTlComplaintgallery);		 
			//return complaintGalleryDao.update(newQtmTlComplaintgallery);
			return complaintgalleryserviceapi.saveComplaintGallery(newQtmTlComplaintgallery);
		}
	    catch (ValidationExceptions e){
	    	e.printStackTrace();
	    	throw new ValidationExceptions(e.getMessage());
	    }
	}
	
	private QtmTlComplaintgallery fillValues(QtmTlComplaintgallery newQtmTlComplaintgallery,QtmTlComplaintgallery oldQtmTlComplaintgallery) throws Exception
	{
		newQtmTlComplaintgallery.setCmgaActive("Y");
		String dateTime = CommonFunctions.pg_dateTimeNow();	
		
		String date = newQtmTlComplaintgallery.getCmgaComplaintdate();
		newQtmTlComplaintgallery.setCmgaComplaintdate(CommonFunctions.pg_getDateTimeFromDate(date));
		
		String date1 = newQtmTlComplaintgallery.getCmgaManufacturedate();
		newQtmTlComplaintgallery.setCmgaManufacturedate(CommonFunctions.pg_getDateTimeFromDate(date1));
		
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaCustomerid()))
			newQtmTlComplaintgallery.setCmgaCustomerid("{}");
	
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaGradeproduct()) )
			newQtmTlComplaintgallery.setCmgaGradeproduct("{}");
		
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaCorrectiveaction()))
			newQtmTlComplaintgallery.setCmgaCorrectiveaction("{}");
		
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaComplaintdate()))
			newQtmTlComplaintgallery.setCmgaComplaintdate(Constants.pgPassNullDate);
		
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaManufacturedate()))
			newQtmTlComplaintgallery.setCmgaManufacturedate(Constants.pgPassNullDate);
		
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaGradespecification()))
			newQtmTlComplaintgallery.setCmgaGradespecification("{}");
		
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaPreventiveaction()))
			newQtmTlComplaintgallery.setCmgaPreventiveaction("{}");
		
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaComplaintdescription()))
			newQtmTlComplaintgallery.setCmgaComplaintdescription("{}");
		
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaFlid()))
			newQtmTlComplaintgallery.setCmgaFlid("{}");
		else
			newQtmTlComplaintgallery.setCmgaElementid(complaintGalleryDao.getElementId(newQtmTlComplaintgallery.getCmgaFlid()));
		
		if(!UIUtils.isValidKeyId( newQtmTlComplaintgallery.getCmgaElementid()))
			newQtmTlComplaintgallery.setCmgaElementid("{}");
		
		
		if(!UIUtils.isValidKeyId( newQtmTlComplaintgallery.getCmgaSource()) )
			newQtmTlComplaintgallery.setCmgaSource("-");

		if(!UIUtils.isValidKeyId( newQtmTlComplaintgallery.getCmgaDefectid()) )
			newQtmTlComplaintgallery.setCmgaDefectid("-");

		if(!UIUtils.isValidKeyId( newQtmTlComplaintgallery.getCmgaDefectqty()) )
			newQtmTlComplaintgallery.setCmgaDefectqty("0");		
		
		if(!UIUtils.isValidKeyId( newQtmTlComplaintgallery.getCmgaTempfield1()) )
			newQtmTlComplaintgallery.setCmgaTempfield1("-");

		
		if(!UIUtils.isValidKeyId( newQtmTlComplaintgallery.getCmgaTempfield2()) )
			newQtmTlComplaintgallery.setCmgaTempfield2("-");
		
		if(!UIUtils.isValidKeyId( newQtmTlComplaintgallery.getCmgaTempfield3()))
			newQtmTlComplaintgallery.setCmgaTempfield3("-");
		
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaCreatedby() ))
			newQtmTlComplaintgallery.setCmgaCreatedby("-");
		
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaCreatedon()))
			newQtmTlComplaintgallery.setCmgaCreatedon(dateTime);
		
		if(!UIUtils.isValidKeyId(newQtmTlComplaintgallery.getCmgaModifiedon()))
			newQtmTlComplaintgallery.setCmgaModifiedon(dateTime);
		
		return newQtmTlComplaintgallery; 
	}
	
	@Override
	public QtmTlComplaintgallery getvalues(String keyid) {
		//return complaintGalleryDao.getValues(keyid);
		return complaintgalleryserviceapi.getComplaintGalleryData(keyid);
	}
	
	@Override
	public QtmTlComplaintgallery delete(QtmTlComplaintgallery newQtmTlComplaintgallery)throws Exception {
		//return complaintGalleryDao.delete(newQtmTlComplaintgallery);
		return complaintgalleryserviceapi.deleteComplaintGallery(newQtmTlComplaintgallery);
	}

	@Override
	public String[] getImgName(String keyid,String fileDir,String imagepath) {
		return complaintGalleryDao.getImgName(keyid, fileDir, imagepath);
	}

	@Override
	public void deleteImage(String cmgaKeyid, String imgType, String refDoc) throws BusinessApplicationExceptions, Exception {
		complaintGalleryDao.deleteImage( cmgaKeyid,  imgType,  refDoc); 
		
	}

	@Override
	public Workbook getCompGalExcel(JSONObject colmodel, String format,
			CommonFilter commonFilter) throws Exception {
		return complaintGalleryDao. getCompGalExcel( colmodel,  format, commonFilter);
	}
	public List<String[]> getCustCompliantCount(CommonFilter commonFilter) throws Exception{
		return complaintGalleryDao.getCustCompliantCount(commonFilter);
	}
	public Workbook getCustComplintsCountExcel(CommonFilter commonFilter,JSONObject colmodel, String format) throws Exception{
		return complaintGalleryDao.getCustComplintsCountExcel(commonFilter, colmodel, format);
	}
	@Override
	public List<String[]> getElementId(String loginflid, String loginlevel, String loginElementid, String empId) throws Exception {
	    return complaintGalleryDao.getElementId(loginflid, loginlevel, loginElementid, empId);
	}
	
	@Override
	public List<ComboBox> getFillcombobox(CommonFilter commonFilter, ComboFilter comboFilter)
	        throws Exception {

	    CommonMessage.debugMsg(" Inside Service Impl 11 :: ");

	    comboFilter.setNameField("QPHM_NAME");
	    comboFilter.setIdField("QPHM_KEYID");
	    comboFilter.setTableName(TableNames.TBL_QTM_TL_PHENOMENAMST);

	    // parse defectMode and sectionId from condSql marker
	    String defectMode   = "";
	    String sectionId    = "";
	    String existingCond = comboFilter.getCondSql();

	    if (existingCond != null && existingCond.startsWith("##")) {
	        String[] parts = existingCond.split("##");
	        defectMode = parts.length > 1 ? parts[1] : "";
	        sectionId  = parts.length > 2 ? parts[2] : "";
	        // reset condSql so it doesn't pollute the actual query
	        comboFilter.setCondSql("");
	    }

	    CommonFunctions.debugMsg("=== getFillcombobox (Upstream Defect) ===");
	    CommonFunctions.debugMsg("defectMode : " + defectMode);
	    CommonFunctions.debugMsg("flid       : " + commonFilter.getFlid());
	    CommonFunctions.debugMsg("sectionId  : " + sectionId);

	    if ("OTHERS".equals(defectMode)) {
	        comboFilter.setCondSql(
	            " AND QPHM_KEYID NOT IN ( " +
	            "   SELECT PHNM_QPHM_KEYID " +
	            "   FROM QTM_TL_PHENOMENA_MAPPING " +
	            "   WHERE PHNM_ACTIVE = 'Y' " +
	            "   AND PHNM_SECT_FLID = '" + sectionId + "' " +
	            " ) "
	        );
	    } else {
	        comboFilter.setCondSql(
	            " AND QPHM_KEYID IN ( " +
	            "   SELECT PHNM_QPHM_KEYID " +
	            "   FROM QTM_TL_PHENOMENA_MAPPING " +
	            "   WHERE PHNM_ACTIVE = 'Y' " +
	            "   AND PHNM_SECT_FLID = '" + sectionId + "' " +
	            " ) "
	        );
	    }

	    return commonFilterDao.fillComboValuesWithoutCondition(comboFilter);
	}
	
	@Override
	public List<ComboBox> getGradeSpecComboList(CommonFilter commonFilter, ComboFilter comboFilter)
	        throws Exception {

	    comboFilter.setCodeField("GSPC_CODE");
	    comboFilter.setNameField("GSPC_NAME");
	    comboFilter.setIdField("GSPC_KEYID");
	    comboFilter.setTableName("PCS_TL_GRADESPECMST");

	    // Parse gradeMode from ## piggyback marker
	    String gradeMode    = "";
	    String existingCond = comboFilter.getCondSql();
	    if (existingCond != null && existingCond.startsWith("##")) {
	        String[] parts = existingCond.split("##");
	        gradeMode = parts.length > 1 ? parts[1] : "";
	        comboFilter.setCondSql(""); // reset so it doesn't pollute the query
	    }

	    CommonFunctions.debugMsg("=== getGradeSpecComboList (CompliantGallery) ===");
	    CommonFunctions.debugMsg("gradeMode : " + gradeMode);
	    CommonFunctions.debugMsg("flid      : " + commonFilter.getFlid());

	    if (UIUtils.isValidKeyId(commonFilter.getFlid())) {
	        if ("OTHERS".equals(gradeMode)) {
	            // Load grade specs from parent/related locations (broad search)
	            StringBuffer sb = new StringBuffer();
	            sb.append(" AND GSPC_FLID IN (SELECT FNLN_KEYID FROM GEN_TL_FUNCTIONALLOCN WHERE ");
	            sb.append(" FNLN_ORIGINALID IN (SELECT SUBSTR(FNLN_ELEMENTID,12,10) ");
	            sb.append(" FROM GEN_VW_FNLN WHERE FNLN_KEYID = '" + commonFilter.getFlid() + "') ) ");
	            comboFilter.setCondSql(sb.toString());
	        } else {
	            comboFilter.setCondSql(" AND GSPC_FLID = '" + commonFilter.getFlid() + "' ");
	        }
	    } else {
	        comboFilter.setCondSql(" AND 1 = 0 ");
	    }

	    return commonFilterDao.fillComboValuesWithoutCondition(comboFilter);
	}
}
