package com.akranta.tpm.dao;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.GenTlToolsimg;
import com.akranta.tpm.model.JhaTlVisualsopmst;

public interface JhaTlVisualsopmstDao {

	public abstract JhaTlVisualsopmst create(JhaTlVisualsopmst jhaTlVisualsopmst) throws Exception;
	public abstract JhaTlVisualsopmst update(JhaTlVisualsopmst jhaTlVisualsopmst) throws Exception;
	public abstract JhaTlVisualsopmst delete(JhaTlVisualsopmst jhaTlVisualsopmst) throws Exception;
	public List<String[]> getAllVisualSopDetail(String keyId,String fileName,CommonFilter commonFilter) throws Exception;
	public JhaTlVisualsopmst getAllFillControl(String keyId)throws Exception;
	public List<String[]> getAllVisualSopDetailReport(CommonFilter commonFilter) throws Exception;
	public Workbook visualSOPExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public Workbook visualSOPDetailExportExcel (CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public List<String[]> getPPEDetail(String fileName) throws Exception;
	public List<GenTlAllmoduleimgfile> getvsopImage(List<GenTlAllmoduleimgfile> oplImgList,String Vsopid)throws NoDataFoundException, Exception;
	public List<GenTlToolsimg> getToolImage(List<GenTlToolsimg> oplImgList,String Vsopid)throws NoDataFoundException, Exception;
	
	public abstract void JhaTlVisualsopmstDaoImplJwt(String jwtToken);


}

