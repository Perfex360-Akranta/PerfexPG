package com.akranta.tpm.service;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.model.AdmTlRoleMenuLink;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MenuTree;

public interface MenuTreeServices {
	public  List<MenuTree> getMenuTreeValues(String userId) throws Exception;
	public  List<MenuTree> getAllMneus(MenuTree menuTree,String userid) throws Exception;
	public  List<MenuTree> getUserRoleMenus(MenuTree menuTree,String userid) throws Exception;
	public List<String[]> getChildPath(String menuCaption, String userId) throws NoDataFoundException, Exception;
	public List<ComboBox> getComboRole(String searchTxt) throws Exception;
	public AdmTlRoleMenuLink create(AdmTlRoleMenuLink newAdmTlRoleMenuLink,AdmTlRoleMenuLink oldAdmTlRoleMenuLink) throws ValidationExceptions,Exception;
	public String checkMenuRoleExist(String roleId,String menuId) throws Exception;
	public List<String[]> getAllQlinkList(MenuTree menuTree, String usrm_keyid) throws Exception;
	public List<String[]> getMenuChildPathByMenuNumber(String menuNumber, String userId) throws NoDataFoundException, Exception;
	public List<String[]> addmenuview1(CommonFilter commonFilter,GridParams gridparam)throws Exception;
	public Workbook getmenuRightsExcel(CommonFilter commonFilter,JSONObject tblJSONObj, String format)throws Exception;
	public List<String[]> addmenureport(CommonFilter commonfilter,GridParams gridParam)throws Exception;
    public Workbook getmenurightreportExcel(CommonFilter commonfilter,JSONObject jsonobj,String formats)throws Exception;
    public List<ComboBox> getmenu(ComboFilter combofilter)throws Exception; 
}
