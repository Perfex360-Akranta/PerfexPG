package com.akranta.tpm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.JhaTlVisualsopdtlDao;
import com.akranta.tpm.dao.JhaTlVisualsopmstDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.JhaTlVisualsopdtlDaoImpl;
import com.akranta.tpm.dao.impl.JhaTlVisualsopmstDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.exportreport.OPLITCExcelTemplate;
import com.akranta.tpm.exportreport.VSOPExcelTemplate;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.JhaTlVisualsopdtl;
import com.akranta.tpm.model.JhaTlVisualsopmst;
import com.akranta.tpm.service.VisualSOPService;
import com.akranta.tpm.service.api.VisualsopServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class VisualSOPServiceImpl implements VisualSOPService {
	DBActionTemplate  dbActionTemplate ; 
	private VisualsopServiceApi visualsopServiceApi;
	private JhaTlVisualsopmstDao jhaTlVisualsopmstDao; 
	private JhaTlVisualsopdtlDao jhaTlVisualsopdtlDao; 
	private Validations validations ;
	private CommonFilterDao commonFilterDao;
	
	public VisualSOPServiceImpl(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate  =dbActionTemplate ;
		jhaTlVisualsopmstDao=new JhaTlVisualsopmstDaoImpl(dbActionTemplate);
		jhaTlVisualsopdtlDao=new JhaTlVisualsopdtlDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations=new Validations();
	}
	
	//jwt token
	public void VisualSOPServiceImplJwt(String JwtToken){
    	try{
    		jhaTlVisualsopmstDao.JhaTlVisualsopmstDaoImplJwt(JwtToken);
    		visualsopServiceApi = new VisualsopServiceApi(JwtToken);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    }


	@Override
	public List<String[]> getAllVisualSopDetail(String keyId,String fileName,CommonFilter commonFilter)
			throws Exception {
		
		return this.jhaTlVisualsopmstDao.getAllVisualSopDetail(keyId,fileName,commonFilter);
		//return this.visualsopServiceApi.getdetailbyKeyid(keyId,fileName,commonFilter);
	}
	@Override
	public List<String[]> getAllPPEDetail(String fileName)
			throws Exception {
		
		return this.jhaTlVisualsopmstDao.getPPEDetail(fileName);
	}
	@Override
	public List<String[]> getAllVisualSopDetailReport(CommonFilter commonFilter)
			throws Exception {
		return this.jhaTlVisualsopmstDao.getAllVisualSopDetailReport(commonFilter);
	}

	@Override
	public JhaTlVisualsopmst create(JhaTlVisualsopmst newjhaTlVisualsopmst,
			JhaTlVisualsopmst existjhaTlVisualsopmst) throws Exception {
		String validationsFor = "create";
		String xml="VisualSopCreation";
		
		validations.validate(newjhaTlVisualsopmst,xml,validationsFor);
		fillvaluesVisualsopmst(newjhaTlVisualsopmst,existjhaTlVisualsopmst);
		//return this.jhaTlVisualsopmstDao.create(newjhaTlVisualsopmst);
		return this.visualsopServiceApi.insertRecord(newjhaTlVisualsopmst);
	}

	@Override
	public JhaTlVisualsopmst update(JhaTlVisualsopmst newjhaTlVisualsopmst,
			JhaTlVisualsopmst existjhaTlVisualsopmst) throws Exception {
		String validationsFor = "update";
		String xml="VisualSopCreation";
		validations.validate(newjhaTlVisualsopmst,xml,validationsFor);
		fillvaluesVisualsopmst(newjhaTlVisualsopmst,existjhaTlVisualsopmst);
		//return this.jhaTlVisualsopmstDao.update(newjhaTlVisualsopmst);
		return this.visualsopServiceApi.insertRecord(newjhaTlVisualsopmst);
	}

	@Override
	public JhaTlVisualsopmst delete(JhaTlVisualsopmst oldjhaTlVisualsopmst)
			throws Exception {
		// TODO Auto-generated method stub
		//return this.jhaTlVisualsopmstDao.delete(oldjhaTlVisualsopmst);
		return this.visualsopServiceApi.delete(oldjhaTlVisualsopmst);
	}
	private void fillvaluesVisualsopmst(JhaTlVisualsopmst newjhaTlVisualsopmst,JhaTlVisualsopmst existjhaTlVisualsopmst)throws Exception  {
		
	String dateTime = CommonFunctions.pg_dateTimeNow();
		newjhaTlVisualsopmst.setVsomCreatedon(dateTime);
		newjhaTlVisualsopmst.setVsomModifiedon(dateTime);
		if(newjhaTlVisualsopmst.getVsomActive()==null)
		newjhaTlVisualsopmst.setVsomActive("N");
		if(newjhaTlVisualsopmst.getVsomApprovedby()==null)
			newjhaTlVisualsopmst.setVsomApprovedby("{}");
		if(newjhaTlVisualsopmst.getVsomCreatedby()==null)
			newjhaTlVisualsopmst.setVsomCreatedby("{}");
		if(newjhaTlVisualsopmst.getVsomEffectofnoncompliance()==null)
			newjhaTlVisualsopmst.setVsomEffectofnoncompliance("{}");
		if(newjhaTlVisualsopmst.getVsomEquipmentid()==null)
			newjhaTlVisualsopmst.setVsomEquipmentid("{}");
		if(newjhaTlVisualsopmst.getVsomFlnid()==null)
			newjhaTlVisualsopmst.setVsomFlnid("{}");
		if(newjhaTlVisualsopmst.getVsomIssuedby()==null)
			newjhaTlVisualsopmst.setVsomIssuedby("{}");
		if(newjhaTlVisualsopmst.getVsomOperation()==null)
			newjhaTlVisualsopmst.setVsomOperation("{}");
		if(newjhaTlVisualsopmst.getVsomPreparedby()==null)
			newjhaTlVisualsopmst.setVsomPreparedby("{}");
		if(newjhaTlVisualsopmst.getVsomStatus()== null)
			newjhaTlVisualsopmst.setVsomStatus("-");
		if(newjhaTlVisualsopmst.getVsomNextlevel()==null)
			newjhaTlVisualsopmst.setVsomNextlevel("-");
		if(newjhaTlVisualsopmst.getVsomMaintsection()==null)
			newjhaTlVisualsopmst.setVsomMaintsection("-");
		if(newjhaTlVisualsopmst.getVsomTempfield4()==null)
			newjhaTlVisualsopmst.setVsomTempfield4("-");
		if(newjhaTlVisualsopmst.getVsomTempfield5()==null)
			newjhaTlVisualsopmst.setVsomTempfield5("-");
		if(newjhaTlVisualsopmst.getVsomProductid()==null)
			newjhaTlVisualsopmst.setVsomProductid("{}");
		if(newjhaTlVisualsopmst.getVsomSafetyinstruction()==null)
			newjhaTlVisualsopmst.setVsomSafetyinstruction("{}");
			CommonMessage.debugMsg("End Of  Fill values");
			
			
	}
	@Override
	public Workbook VsopExportExcel(String vsopid, String format,String path, String imagePath,CommonFilter commonFilter) throws Exception {
		
		
		Map<Integer, List<String[]>> oplData = jhaTlVisualsopdtlDao.vsopExcelReport(vsopid, format, commonFilter) ;
		List<String[]>  getVisualid = jhaTlVisualsopdtlDao.getVisualid(vsopid);
		Workbook wb=(new VSOPExcelTemplate(dbActionTemplate)).fillValues(oplData, getVisualid,format, path, vsopid, imagePath);
		
		return wb;
		
	}
	public List<GenTlToolsimg>  getVsopToolImage( String fileName, String filePath,String vsopDId)throws NoDataFoundException, Exception
	{
		// TODO Auto-generated method stub
		
		List<GenTlToolsimg> GenTlToolsimgToolList = new ArrayList<GenTlToolsimg>();
		
		if(UIUtils.isValidKeyId(vsopDId))
		{
			
			GenTlToolsimg GenTlToolsimgBeforeImage = new GenTlToolsimg();
				
			
			GenTlToolsimgBeforeImage.setToimFilename(fileName);
			GenTlToolsimgBeforeImage.setToimBlobimage(filePath);
			GenTlToolsimgToolList.add(GenTlToolsimgBeforeImage);
			
			GenTlToolsimg GenTlToolsimgAfterImage = new GenTlToolsimg();	
			
			GenTlToolsimgAfterImage.setToimBlobimage(filePath);	
			GenTlToolsimgAfterImage.setToimFilename(fileName);			
			GenTlToolsimgToolList.add(GenTlToolsimgAfterImage);

		}	
	
				
		return jhaTlVisualsopmstDao.getToolImage(GenTlToolsimgToolList,vsopDId);
	}
	
	public List<GenTlAllmoduleimgfile>  getVsopImage( String fileName, String filePath,String vsopId)throws NoDataFoundException, Exception
	{
		// TODO Auto-generated method stub
		
		List<GenTlAllmoduleimgfile> vsopImgList = new ArrayList<GenTlAllmoduleimgfile>();
		
		if(UIUtils.isValidKeyId(vsopId))
		{
			
			GenTlAllmoduleimgfile vsopBeforeImage = new GenTlAllmoduleimgfile();
				
			vsopBeforeImage.setImflBlobimage(filePath);
			 
			vsopBeforeImage.setImflFilename(fileName);
			
			vsopBeforeImage.setImflRefkeyid(vsopId);
			vsopBeforeImage.setImflRefdoctype("VSP");
			vsopBeforeImage.setImflImagetype("TOL");
			vsopImgList.add(vsopBeforeImage);
			
			GenTlAllmoduleimgfile vsopAfterImage = new GenTlAllmoduleimgfile();
			
			vsopAfterImage.setImflBlobimage(filePath);
			vsopAfterImage.setImflFilename(fileName);
			vsopAfterImage.setImflRefkeyid(vsopId);
			vsopAfterImage.setImflRefdoctype("VSP");
			vsopAfterImage.setImflImagetype("TOL1");
			vsopImgList.add(vsopAfterImage);

		}	
	
				
		return jhaTlVisualsopmstDao.getvsopImage(vsopImgList,vsopId);
	}
	@Override
	public JhaTlVisualsopmst getAllFillControl(String keyId) throws Exception {
		
		//return jhaTlVisualsopmstDao.getAllFillControl(keyId);
		return visualsopServiceApi.getByMstKeyid(keyId);
	}

	@Override
	public JhaTlVisualsopdtl create(JhaTlVisualsopdtl newjhaTlVisualsopdtl,
			JhaTlVisualsopdtl existjhaTlVisualsopdtl) throws Exception,BusinessApplicationExceptions {
		try{	
			String validationsFor = "create";
			String xml="VisualSopDetailCreation";
			validations.validate(newjhaTlVisualsopdtl,xml,validationsFor);
			fillvaluesVisualsopdtl(newjhaTlVisualsopdtl,existjhaTlVisualsopdtl);
			//return this.jhaTlVisualsopdtlDao.create(newjhaTlVisualsopdtl);
			return this.visualsopServiceApi.insertdetailRecord(newjhaTlVisualsopdtl);
		}catch (BusinessApplicationExceptions e){
			CommonMessage.debugMsg("e.getMessage()"+e.getMessage());
			throw new BusinessApplicationExceptions(e.getMessage());
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public JhaTlVisualsopdtl update(JhaTlVisualsopdtl newjhaTlVisualsopdtl,
			JhaTlVisualsopdtl existjhaTlVisualsopdtl) throws Exception {
		String validationsFor = "update";
		String xml="VisualSopDetailCreation";
		validations.validate(newjhaTlVisualsopdtl,xml,validationsFor);
		fillvaluesVisualsopdtl(newjhaTlVisualsopdtl,existjhaTlVisualsopdtl);
		//return this.jhaTlVisualsopdtlDao.update(newjhaTlVisualsopdtl);
		return this.visualsopServiceApi.insertdetailRecord(newjhaTlVisualsopdtl);
	}

	@Override
	public JhaTlVisualsopdtl delete(JhaTlVisualsopdtl oldjhaTlVisualsopdtl)
			throws Exception {
		//return this.jhaTlVisualsopdtlDao.delete(oldjhaTlVisualsopdtl);
		return this.visualsopServiceApi.deletetById(oldjhaTlVisualsopdtl);
	}
	//fill for detail
	private void fillvaluesVisualsopdtl(JhaTlVisualsopdtl newjhaTlVisualsopdtl,JhaTlVisualsopdtl existjhaTlVisualsopdtl)throws Exception  {
		
		String dateTime = CommonFunctions.pg_dateTimeNow();
		newjhaTlVisualsopdtl.setVsodCreatedon(dateTime);
		newjhaTlVisualsopdtl.setVsodModifiedon(dateTime);
			if(newjhaTlVisualsopdtl.getVsodActive()==null)
				newjhaTlVisualsopdtl.setVsodActive("N");
			if(newjhaTlVisualsopdtl.getVsodImgppe()==null)
				newjhaTlVisualsopdtl.setVsodImgppe("{}");
			
			if(newjhaTlVisualsopdtl.getVsodImgtoolused()==null||newjhaTlVisualsopdtl.getVsodImgtoolused().length()<=0)
			{
				newjhaTlVisualsopdtl.setVsodImgtoolused("{}");
			}
			if(newjhaTlVisualsopdtl.getVsodToolused()==null)
				newjhaTlVisualsopdtl.setVsodToolused("{}");
			if(newjhaTlVisualsopdtl.getVsodImportanceofkeypoint()==null)
				newjhaTlVisualsopdtl.setVsodImportanceofkeypoint("{}");
			if(newjhaTlVisualsopdtl.getVsodInstruction()==null)
				newjhaTlVisualsopdtl.setVsodInstruction("{}");
			if(newjhaTlVisualsopdtl.getVsodKeypoint()==null)
				newjhaTlVisualsopdtl.setVsodKeypoint("{}");
			if(newjhaTlVisualsopdtl.getVsodTempfield1()==null)
				newjhaTlVisualsopdtl.setVsodTempfield1("-");
			if(newjhaTlVisualsopdtl.getVsodTempfield2()==null)
				newjhaTlVisualsopdtl.setVsodTempfield2("-");
			if(newjhaTlVisualsopdtl.getVsodTempfield3()==null)
				newjhaTlVisualsopdtl.setVsodTempfield3("-");
			if(newjhaTlVisualsopdtl.getVsodTempfield4()==null)
				newjhaTlVisualsopdtl.setVsodTempfield4("-");
			if(newjhaTlVisualsopdtl.getVsodTempfield5()==null)
				newjhaTlVisualsopdtl.setVsodTempfield5("-");
				CommonMessage.debugMsg("End Of  Fill values");
				
				
		}
	public JhaTlVisualsopdtl getAllFillControlDtl(String keyId)throws Exception{
		
		//return this.jhaTlVisualsopdtlDao.getAllFillControlDtl(keyId);
		return this.visualsopServiceApi.getById(keyId);
	}

	@Override
	public Workbook visualSOPExportExcel(CommonFilter commonFilter,
			JSONObject colModel, String rptFormat) throws Exception {
		// TODO Auto-generated method stub
		return jhaTlVisualsopmstDao.visualSOPExportExcel(commonFilter, colModel, rptFormat);
	}

	@Override
	public Workbook visualSOPDetailExportExcel(CommonFilter commonFilter,
			JSONObject colModel, String rptFormat) throws Exception {
		//CommonMessage.debugMsg("Key Id...... service ::"+commonFilter.getVisualKeyId());
		return jhaTlVisualsopmstDao.visualSOPDetailExportExcel(commonFilter, colModel, rptFormat);
	}

	@Override
	public GenTlAllmoduleimgfile deleteForImg(String keyId) throws Exception {
		// TODO Auto-generated method stub
		return jhaTlVisualsopdtlDao.deleteForImg(keyId);
	}
	public List<ComboBox> gettradecombo(ComboFilter comboFilter) throws Exception {
		//ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("TRDM_CODE");
		comboFilter.setNameField("TRDM_NAME");
		comboFilter.setIdField("TRDM_KEYID");
		comboFilter.setTableName("GEN_TL_TRADEMST");
		return commonFilterDao.fillComboValues(comboFilter);
	}
	@Override
	public JhaTlVisualsopmst updateApprovedStatusLevel(String status,
			String keyid, String nextLevel) throws Exception {
		// TODO Auto-generated method stub
		return jhaTlVisualsopdtlDao.updateApprovedStatusLevel(status,keyid,nextLevel);
	}

	public List<String[]> getVsopApprovalList(String Keyid)throws Exception  {
		// TODO Auto-generated method stub
		return jhaTlVisualsopdtlDao.getVsopApprovalList(Keyid);
	}

}
