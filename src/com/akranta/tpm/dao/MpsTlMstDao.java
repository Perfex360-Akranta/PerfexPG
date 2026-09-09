package com.akranta.tpm.dao;

import java.util.List;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Workbook;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.MpSheetBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.MpsTlMst;

public interface MpsTlMstDao {

	public abstract MpsTlMst create(MpsTlMst mpsTlMst) throws Exception;
	public abstract MpsTlMst update(MpsTlMst mpsTlMst) throws Exception;
	public abstract MpsTlMst delete(MpsTlMst mpsTlMst) throws Exception;
	public abstract MpsTlMst select(String keyid)throws Exception;
	public List<String[]> getModifyFormDao(CommonFilter commonFilter)throws Exception;
	public Workbook mpsExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public List<GenTlAllmoduleimgfile> saveMpsImg(MpsTlMst existMpsTlMst,MpSheetBean mpSheetBean)throws Exception;
	public List<GenTlAllmoduleimgfile> getMpsImage(List<GenTlAllmoduleimgfile> mpsImgList) throws NoDataFoundException, Exception;
	public List<String[]> getTargetImpData(String mpsKeyid)throws Exception;
	public List<String[]> getMpsCompletedDtls(CommonFilter commonFilter)throws Exception;
	public List<String[]> getMpsHDdata(String mpsKeyid)throws Exception;
	public List<String[]> getResultsCreationData(String mpsKeyid)throws Exception;
	public MpsTlMst updateMpsCompletion(MpsTlMst mpsTlMst) throws Exception;
	public abstract Workbook completeExportExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;

}

