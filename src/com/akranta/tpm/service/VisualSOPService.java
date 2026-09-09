package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;


import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.JhaTlVisualsopdtl;
import com.akranta.tpm.model.JhaTlVisualsopmst;

public interface VisualSOPService {
	
	public abstract JhaTlVisualsopmst create(JhaTlVisualsopmst newjhaTlVisualsopmst,JhaTlVisualsopmst existjhaTlVisualsopmst) throws Exception;
	public abstract JhaTlVisualsopmst update(JhaTlVisualsopmst newjhaTlVisualsopmst,JhaTlVisualsopmst existjhaTlVisualsopmst) throws Exception;
	public abstract JhaTlVisualsopmst delete(JhaTlVisualsopmst oldjhaTlVisualsopmst) throws Exception;
	public List<String[]> getAllVisualSopDetail(String keyId,String fileName,CommonFilter commonFilter) throws Exception;
	public List<String[]> getAllVisualSopDetailReport(CommonFilter commonFilter) throws Exception;
	public JhaTlVisualsopmst getAllFillControl(String keyId)throws Exception;
	

	public abstract JhaTlVisualsopdtl create(JhaTlVisualsopdtl newjhaTlVisualsopdtl,JhaTlVisualsopdtl existjhaTlVisualsopdtl) throws Exception,BusinessApplicationExceptions;
	public abstract JhaTlVisualsopdtl update(JhaTlVisualsopdtl newjhaTlVisualsopdtl,JhaTlVisualsopdtl existjhaTlVisualsopdtl) throws Exception;
	public abstract JhaTlVisualsopdtl delete(JhaTlVisualsopdtl oldjhaTlVisualsopdtl) throws Exception;
	public JhaTlVisualsopdtl getAllFillControlDtl(String keyId)throws Exception;
	public Workbook visualSOPExportExcel(CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	public Workbook visualSOPDetailExportExcel (CommonFilter commonFilter,JSONObject colModel, String rptFormat) throws Exception;
	
	public GenTlAllmoduleimgfile deleteForImg(String keyId) throws Exception ;
	public List<String[]> getAllPPEDetail(String fileName) throws Exception;
	public Workbook VsopExportExcel(String vsopid, String format, String path,
			String imagePath, CommonFilter commonFilter) throws Exception;
	public abstract JhaTlVisualsopmst updateApprovedStatusLevel(String status,
			String keyid, String nextLevel) throws Exception;
	public abstract List<ComboBox> gettradecombo(ComboFilter currentFilter) throws Exception;
	
	public void VisualSOPServiceImplJwt(String JwtToken);
}
