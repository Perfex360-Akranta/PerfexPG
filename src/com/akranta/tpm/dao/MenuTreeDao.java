package com.akranta.tpm.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MenuTree;

public interface MenuTreeDao {
	
	public  List<MenuTree> getMenuTreeValues(String userId) throws Exception;
	public  List<MenuTree> getAllMneus(MenuTree menuTree,String userid) throws Exception;
	public  List<MenuTree> getUserRoleMenus(MenuTree menuTree,String userid) throws Exception;
	public List<String[]> getChildPath(String menuCaption, String userId) throws NoDataFoundException, Exception;
	public List<String[]> getAllQlinkList(MenuTree menuTree, String usrm_keyid)throws Exception;
	public List<String[]> getMenuChildPathByMenuNumber(String menuNumber, String userId) throws NoDataFoundException, Exception;
	public List<String[]> addmenuview1(CommonFilter commonFilter,GridParams gridparam)throws Exception;
	public Workbook getmenuRightsExcel(CommonFilter commonFilter,JSONObject colModel,String rptFormat) throws IOException, SQLException, Exception;
    public List<String[]> addmenureport(CommonFilter commonfilter,GridParams gridParam)throws Exception;
    public Workbook getmenurightreportExcel(CommonFilter commonfilter,JSONObject jsonobj,String formats)throws Exception;
}
