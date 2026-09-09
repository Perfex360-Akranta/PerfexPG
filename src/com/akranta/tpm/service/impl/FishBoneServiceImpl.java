package com.akranta.tpm.service.impl;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;
import javax.servlet.http.HttpServlet;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.FishBoneBean;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.FishBoneDao;
import com.akranta.tpm.dao.impl.FishBoneDaoImpl;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.Fishbone;
import com.akranta.tpm.model.GenTlFishbonedtl;
import com.akranta.tpm.model.GenTlFishbonemst;
import com.akranta.tpm.service.FishBoneService;
import com.akranta.tpm.service.api.FishBoneServiceApi;
import com.akranta.tpm.service.api.OplTlMstServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

/**
 * Servlet implementation class AbnormalityFormServiceImpl
 */
public class FishBoneServiceImpl  implements FishBoneService {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private CommonFilterDao commonFilterDao;
	private FishBoneDao  fishBoneDao;
	private Validations validations ;
	FishBoneServiceApi fishBoneServiceApi;
	
    public FishBoneServiceImpl(DBActionTemplate dbActionTemplate){
    	try{
        commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
        fishBoneDao = new FishBoneDaoImpl(dbActionTemplate);
        validations = new Validations();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
    
	public void FishBoneServiceImplJwt(String JwtToken){
	    try{
	   // 	oplTlMstDao.OplTlMstDaoImplJwt(JwtToken);   // dao side
	        // (Optional) if you want service-level direct access
	    	fishBoneDao.FishBoneDaoImplJwt(JwtToken);
	    	fishBoneServiceApi = new FishBoneServiceApi(JwtToken);
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	}
	
    public List<Fishbone> getAllFishBone(Fishbone fishbone) throws Exception{
    	return fishBoneDao.getAllFishBone(fishbone);
    }
    
    public List<Fishbone> getFishBoneValues(Fishbone fishbone) throws Exception{
    	return fishBoneDao.getFishBoneValues(fishbone);
    }
    @Override
	/*public List<Fishbone> getFishBoneValues(GenTlFishbonedtl genTlFishbonedtl)
			throws Exception {
		// TODO Auto-generated method stub
    	return fishBoneDao.getFishBoneValues(genTlFishbonedtl);
	}*/
    
    //@Override
	public List<GenTlFishbonedtl> getFishBoneValues(
			GenTlFishbonedtl genTlFishbonedtl,GenTlFishbonemst genTlFishbonemst,String id, String masterId) throws Exception {
		// TODO Auto-generated method stub
    	 
    	CommonMessage.debugMsg(" Inside Service Impl For Loadval ::");
    //	return fishBoneDao.getFishBoneValues(genTlFishbonedtl,genTlFishbonemst,id,masterId);
    	return fishBoneServiceApi.getFishBoneValues(genTlFishbonedtl,genTlFishbonemst,id,masterId);
	}
   /* @Override
	public List<GenTlFishbonemst> getFishBoneValues(
			GenTlFishbonemst genTlFishbonemst) throws Exception {
		// TODO Auto-generated method stub
    	return fishBoneDao.getFishBoneValues(genTlFishbonemst);
	}*/
    public List<String[]> getSearchNode(String searchNode,String originalId) throws Exception{
    	return fishBoneDao.getSearchNode(searchNode,originalId);    
    }

	@Override
	public List<String[]> getAllFishGrid(CommonFilter commonFilter) throws Exception {
		return fishBoneDao.getAllFishGrid(commonFilter);  
	}

	@Override
	public GenTlFishbonemst create(GenTlFishbonemst newGenTlFishbonemst,GenTlFishbonemst existGenTlFishbonemst,
			FishBoneBean fishBoneBean)throws Exception {
		// TODO Auto-generated method stub
		String validationsFor;
		validationsFor = "create";
		String xml = "FishBone";
		
		 validations.validate(newGenTlFishbonemst, xml, validationsFor);
		
		fillValuesFishBone(newGenTlFishbonemst, existGenTlFishbonemst,fishBoneBean);
	//	return fishBoneDao.create(newGenTlFishbonemst,existGenTlFishbonemst,fishBoneBean);
		return fishBoneServiceApi.create(newGenTlFishbonemst,existGenTlFishbonemst,fishBoneBean);
	
	

		
	}
	@Override
	public GenTlFishbonemst update(GenTlFishbonemst newGenTlFishbonemst,GenTlFishbonemst existGenTlFishbonemst, 
			FishBoneBean fishBoneBean)throws Exception {
		// TODO Auto-generated method stub
		
		String validationsFor;
		validationsFor = "create";
		String xml = "FishBone";
		validations.validate(newGenTlFishbonemst, xml, validationsFor);
		fillValuesFishBone(newGenTlFishbonemst, existGenTlFishbonemst,fishBoneBean);
//		return fishBoneDao.update(newGenTlFishbonemst,existGenTlFishbonemst,fishBoneBean);
		return fishBoneServiceApi.create(newGenTlFishbonemst,existGenTlFishbonemst,fishBoneBean);
		
	}
	
	@Override
	public GenTlFishbonedtl createChildEntry(GenTlFishbonedtl newGenTlFishbonedtl,GenTlFishbonedtl existGenTlFishbonedtl,
			FishBoneBean fishBoneBean, String editval)throws Exception {
		   // TODO Auto-generated method stub
		
		String validationsFor;
		validationsFor = "create";
		
		CommonMessage.debugMsg(" Inside Service Impl :: "+" editval :: "+editval);
		
		String xml = "FishBoneChildEntry";
		
		if("Editval".equals(editval)){
			
			CommonMessage.debugMsg("  Inside Service Impl  :: fishBoneBean.getFormModes() ");
		    validations.validate(newGenTlFishbonedtl, xml, "cause");

		}else{
			
			validations.validate(newGenTlFishbonedtl, xml, validationsFor);
		
		}
			
		fillValuesFishBone(newGenTlFishbonedtl, existGenTlFishbonedtl,fishBoneBean);
		//return fishBoneDao.createChildEntry(newGenTlFishbonedtl,existGenTlFishbonedtl,fishBoneBean,editval);
		return fishBoneServiceApi.createChildEntry(newGenTlFishbonedtl,existGenTlFishbonedtl,fishBoneBean,editval);
		
	}

	

	@Override
	public GenTlFishbonedtl updateChildEntry(GenTlFishbonedtl newGenTlFishbonedtl,
			GenTlFishbonedtl existGenTlFishbonedtl, FishBoneBean fishBoneBean)
			throws Exception {
		// TODO Auto-generated method stub
		
		String validationsFor;
		validationsFor = "update";
		String xml = "FishBoneChildEntry";
		validations.validate(newGenTlFishbonedtl, xml, validationsFor);
		fillValuesFishBone(newGenTlFishbonedtl, existGenTlFishbonedtl,fishBoneBean);
	//	return fishBoneDao.updateChildEntry(newGenTlFishbonedtl,existGenTlFishbonedtl,fishBoneBean);
		return fishBoneServiceApi.updateChildEntry(newGenTlFishbonedtl,existGenTlFishbonedtl,fishBoneBean);
		
	}
	@Override
	public GenTlFishbonedtl deleteFishBoneChildEntry(
			GenTlFishbonedtl newGenTlFishbonedtl) throws Exception {
		// TODO Auto-generated method stub
	//	return fishBoneDao.deleteFishBoneChildEntry(newGenTlFishbonedtl);
	 	return fishBoneServiceApi.deleteFishBoneChildEntry(newGenTlFishbonedtl);
	}

	@Override
	public GenTlFishbonemst deleteFishBoneMst(GenTlFishbonemst newGenTlFishbonemst) throws Exception {
		// TODO Auto-generated method stub
		return fishBoneDao.deleteFishBoneMst(newGenTlFishbonemst);
	}

	@Override
	public Workbook getFishBoneExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return fishBoneDao.getFishBoneExcel(colmodel,format,commonFilter);
	}
	
	@Override
	public GenTlFishbonemst getFillControl(String fishboneKeyId)
			throws Exception {
		// TODO Auto-generated method stub
		
	//	return fishBoneDao.getFillControl(fishboneKeyId);
		return fishBoneServiceApi.getFillControl(fishboneKeyId);
		
	}
	private void fillValuesFishBone(GenTlFishbonedtl newGenTlFishbonedtl,GenTlFishbonedtl existGenTlFishbonedtl,
			FishBoneBean fishBoneBean)throws Exception {
		// TODO Auto-generated method stub
		
		newGenTlFishbonedtl.setFisdActive("Y");

		String dateTime = CommonFunctions.dateTimeNow();

		newGenTlFishbonedtl.setFisdCreatedon(dateTime);

		    newGenTlFishbonedtl.setFisdModifiedon(dateTime);
		
		if (newGenTlFishbonedtl.getFisdFismKeyid() == null)
			newGenTlFishbonedtl.setFisdFismKeyid("{}");

		if (newGenTlFishbonedtl.getFisdCause() == null)
			newGenTlFishbonedtl.setFisdCause("{}");
		
		if (newGenTlFishbonedtl.getFisdParentid() == null)
			newGenTlFishbonedtl.setFisdParentid("{}");
		//else
			//newGenTlFishbonedtl.setFisdParentid(newGenTlFishbonedtl.getFisdKeyid());
		
		
		if (newGenTlFishbonedtl.getFisdOrderno() == null)
			newGenTlFishbonedtl.setFisdOrderno("1");
		
		if (newGenTlFishbonedtl.getFisdLevelno() == null)
			newGenTlFishbonedtl.setFisdLevelno("1");
		
	/*	if (newGenTlFishbonedtl.getFismProblem() == null)
			newGenTlFishbonedtl.setFismProblem("{}");
		
		if (newGenTlFishbonedtl.getFismRevisionno() == null)
			newGenTlFishbonedtl.setFismRevisionno("{}");
		
		if (newGenTlFishbonedtl.getFismPrepareddate() == null)
			newGenTlFishbonedtl.setFismPrepareddate(dateTime);
		
		if (newGenTlFishbonedtl.getFismPreparedby() == null)
			newGenTlFishbonedtl.setFismPreparedby("{}");
		
		if (newGenTlFishbonedtl.getFismApproveddate() == null)
			newGenTlFishbonedtl.setFismApproveddate(dateTime);
		
		if (newGenTlFishbonedtl.getFismApprovedby() == null)
			newGenTlFishbonedtl.setFismApprovedby("{}");
*/		
		if (newGenTlFishbonedtl.getFisdTempfield1() == null)
			newGenTlFishbonedtl.setFisdTempfield1("-");
		
		if (newGenTlFishbonedtl.getFisdTempfield2() == null)
			newGenTlFishbonedtl.setFisdTempfield2("-");
		
		if (newGenTlFishbonedtl.getFisdTempfield3() == null)
			newGenTlFishbonedtl.setFisdTempfield3("-");
		
		if (newGenTlFishbonedtl.getFisdTempfield4() == null)
			newGenTlFishbonedtl.setFisdTempfield4("-");
		
		if (newGenTlFishbonedtl.getFisdTempfield5() == null)
			newGenTlFishbonedtl.setFisdTempfield5("-");
		
		//if (newGenTlFishbonedtl.getFismStatus() == null)
		//	newGenTlFishbonedtl.setFismStatus("Y");

		
	}
	private void fillValuesFishBone(GenTlFishbonemst newGenTlFishbonemst,GenTlFishbonemst existGenTlFishbonemst,
			FishBoneBean fishBoneBean)throws Exception {
		
		newGenTlFishbonemst.setFismActive("Y");

		String dateTime = CommonFunctions.pg_dateTimeNow();

		newGenTlFishbonemst.setFismCreatedon(dateTime);

		newGenTlFishbonemst.setFismModifiedon(dateTime);
		if (newGenTlFishbonemst.getFismFlid() == null)
			newGenTlFishbonemst.setFismFlid("{}");

		if (newGenTlFishbonemst.getFismElementid() == null)
			newGenTlFishbonemst.setFismElementid("{}");
		
		if (newGenTlFishbonemst.getFismRefdocid() == null)
			newGenTlFishbonemst.setFismRefdocid("{}");
		
		if (!UIUtils.isValidKeyId(newGenTlFishbonemst.getFismRefdoctype()))
			newGenTlFishbonemst.setFismRefdoctype("{}");
		
		if (newGenTlFishbonemst.getFismTitle() == null)
			newGenTlFishbonemst.setFismTitle("{}");
		
		if (newGenTlFishbonemst.getFismProblem() == null)
			newGenTlFishbonemst.setFismProblem("{}");
		
		if (newGenTlFishbonemst.getFismRevisionno() == null)
			newGenTlFishbonemst.setFismRevisionno("{}");
		
		if (newGenTlFishbonemst.getFismPrepareddate() == null)
			newGenTlFishbonemst.setFismPrepareddate(dateTime);
		
		if (newGenTlFishbonemst.getFismPreparedby() == null)
			newGenTlFishbonemst.setFismPreparedby("{}");
		
		if (newGenTlFishbonemst.getFismApproveddate() == null)
			newGenTlFishbonemst.setFismApproveddate(dateTime);
		
		if (newGenTlFishbonemst.getFismApprovedby() == null)
			newGenTlFishbonemst.setFismApprovedby("{}");
		
		if (newGenTlFishbonemst.getFismDefect() == null)
			newGenTlFishbonemst.setFismDefect("{}");
		
		if (newGenTlFishbonemst.getFismTempfield2() == null)
			newGenTlFishbonemst.setFismTempfield2("-");
		
		if (newGenTlFishbonemst.getFismTempfield3() == null)
			newGenTlFishbonemst.setFismTempfield3("-");
		
		if (newGenTlFishbonemst.getFismTempfield4() == null)
			newGenTlFishbonemst.setFismTempfield4("-");
		
		if (newGenTlFishbonemst.getFismTempfield5() == null)
			newGenTlFishbonemst.setFismTempfield5("-");
		
		if (newGenTlFishbonemst.getFismStatus() == null)
			newGenTlFishbonemst.setFismStatus("Y");
		
	}

	@Override
	public List<String[]> getFBDetail(String keyid) throws Exception {
		return fishBoneServiceApi.getFBDetail(keyid);
		//return fishBoneDao.getFBDetail(keyid);
		
	}

	@Override
	public Workbook getFBDetailForExcel(String keyid,JSONObject tblJSONObj,String format) throws Exception {
		return fishBoneDao.getFBDetailForExcel(keyid,tblJSONObj,format);
	}
	public List<String[]> getKznSgnCount(CommonFilter commonFilter)throws Exception {		
    	return this.fishBoneDao.getKznSgnCount(commonFilter);
    	}
	// --------Vignesh 
	
	public  Workbook getExcelreport(CommonFilter commonFilter,
			JSONObject colModel, String format) throws Exception {
		// TODO Auto-generated method stub
	
		return fishBoneDao.getExcelreport(commonFilter,colModel,format);
	}
	//------------- Vignesh added -------------------------------//
	
	
	
}
