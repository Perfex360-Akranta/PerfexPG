package com.akranta.tpm.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlDmtnotebookdetail;
import com.akranta.tpm.model.GenTlDmtnotebookmaster;
import com.akranta.tpm.model.KznTlDmtnotebookmst;

public interface KznTlDmtnotebookmstDao {

	//public abstract KznTlDmtnotebookmst create(KznTlDmtnotebookmst kznTlDmtnotebookmst) throws Exception;
	//public abstract KznTlDmtnotebookmst update(KznTlDmtnotebookmst kznTlDmtnotebookmst) throws Exception;
	//public abstract KznTlDmtnotebookmst delete(KznTlDmtnotebookmst kznTlDmtnotebookmst) throws Exception;
	public abstract List<String[]> getDmtNotGrid(CommonFilter commonFilter)throws Exception ;
	public abstract GenTlDmtnotebookmaster select(String keyid) throws NoDataFoundException, SQLException, Exception;
	public abstract Workbook notebookRpt(JSONObject colmodel,String format, CommonFilter commonFilter) 
	throws Exception, SQLException;
	public abstract List<String[]> getDmtfillgriddata()throws Exception;
	public abstract GenTlDmtnotebookmaster create(GenTlDmtnotebookmaster genTlDmtnotebookmaster)throws Exception;
	public abstract GenTlDmtnotebookmaster update(GenTlDmtnotebookmaster genTlDmtnotebookmaster);
	public abstract List<String[]> getfillgriddata(CommonFilter commonFilter)throws Exception;
	public abstract List<String[]> getfillDetailgriddata(String masterkeyid)throws Exception;
	public abstract void Deletedmtdetails(String detailKeyid)throws Exception;
	public abstract GenTlDmtnotebookmaster deleteDmtmaster(GenTlDmtnotebookmaster newGenTlDmtnotebookmaster)throws Exception;
	//public abstract  Deletedmtdetails(GenTlDmtnotebookdetail newGenTlDmtnotebookdetail)throws Exception;
	public abstract GenTlDmtnotebookdetail Deletedmtdetails(GenTlDmtnotebookdetail newGenTlDmtnotebookdetail)throws Exception;
	//public abstract Workbook DmtNoteViewExportExcel(String dmtmasterid,String dmtdetailKeyid, CommonFilter commonFilter, String format,String path);
	public abstract Map<Integer, List<String[]>> DmtNoteViewExportExcel(String dmtmasterid, String format, CommonFilter commonFilter,String path)throws Exception;

}

