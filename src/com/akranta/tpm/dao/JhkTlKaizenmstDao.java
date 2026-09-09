package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.JHKaizenBean;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.model.JhkTlKaizenmst;

public interface JhkTlKaizenmstDao {

	JhkTlKaizenmst create(JhkTlKaizenmst newJhkTlKaizenmst)throws Exception;

	List<String[]> getAllJHKaizenReport(CommonFilter commonFilter)throws Exception;

	JhkTlKaizenmst JhkTlKaizenmst(String kznKeyid)throws Exception;

	JhkTlKaizenmst update(JhkTlKaizenmst newJhkTlKaizenmst)throws Exception;

	JhkTlKaizenmst delete(JhkTlKaizenmst newJhKznMst)throws Exception;

	List<GenTlAllmoduleimgfile> saveJHKznImg(JhkTlKaizenmst existJhkTlKaizenmst, JHKaizenBean jhKaizenBean)throws Exception;

	List<GenTlAllmoduleimgfile> getJHKznImage(List<GenTlAllmoduleimgfile> kznImgList)throws Exception;

	GenTlAllmoduleimgfile getkznImage(String fileName, String filePath, String kznKeyid)throws Exception;

	List<String[]> getkznImageList(String fileName, String filePath,String kznKeyid)throws Exception;

	List<GenTlAllmoduleimgfile> getkznImageList(List<GenTlAllmoduleimgfile> oplImgList) throws SQLException, NoDataFoundException, Exception;

	Workbook jhKaizenExportExcel(CommonFilter commonFilter,	JSONObject colModel, String rptFormat)throws Exception;

	

}
