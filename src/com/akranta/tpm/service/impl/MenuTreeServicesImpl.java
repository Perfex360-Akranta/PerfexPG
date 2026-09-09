package com.akranta.tpm.service.impl;
import com.akranta.tpm.bean.CommonParams;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.dao.impl.DBActionTemplate;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.dao.AdmTlRoleMenuLinkDao;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.MenuTreeDao;
import com.akranta.tpm.dao.impl.AdmTlRoleMenuLinkDaoImpl;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.MenuTreeDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.AdmTlRoleMenuLink;
import com.akranta.tpm.model.BdmTlDtl;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.MenuTree;
import com.akranta.tpm.service.MenuTreeServices;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

public class MenuTreeServicesImpl implements MenuTreeServices{

	private MenuTreeDao menuTreeDao; 
	private AdmTlRoleMenuLinkDao admTlRoleMenuLinkDao;
	private CommonFilterDao commonFilterDao;
	private Validations validations ;
	
	public MenuTreeServicesImpl(DBActionTemplate  dbActionTemplate) 
	{
		menuTreeDao = new MenuTreeDaoImpl(dbActionTemplate);
		admTlRoleMenuLinkDao = new AdmTlRoleMenuLinkDaoImpl(dbActionTemplate);
		commonFilterDao = new CommonFilterDaoImpl(dbActionTemplate);
		validations = new Validations();
	}
	@Override
	
	
	public List<MenuTree> getMenuTreeValues(String userId) throws Exception {

		return this.menuTreeDao.getMenuTreeValues(userId);
		
		
	}

	public List<MenuTree> getAllMneus(MenuTree menuTree,String userid) throws Exception {

		return this.menuTreeDao.getAllMneus(menuTree,userid);
	}
	public  List<MenuTree> getUserRoleMenus(MenuTree menuTree,String userid) throws Exception
	{
		
		return this.menuTreeDao.getUserRoleMenus(menuTree,userid);
	}
	public List<String[]> getChildPath(String menuCaption, String userId) throws NoDataFoundException, Exception{
		return this.menuTreeDao.getChildPath(menuCaption, userId);
	}
	public List<String[]> getMenuChildPathByMenuNumber(String menuNumber, String userId) throws NoDataFoundException, Exception{
		return this.menuTreeDao.getMenuChildPathByMenuNumber(menuNumber, userId);
	}
	public List<ComboBox> getComboRole(String condSql) throws Exception
	{
		ComboFilter comboFilter = new ComboFilter();
		comboFilter.setCodeField("ROLE_NAME");
		comboFilter.setIdField("ROLE_KEYID");	
		comboFilter.setTableName(TableNames.TBL_ADM_TL_ROLEMST);
		return commonFilterDao.fillComboValues(comboFilter);
	}
	public AdmTlRoleMenuLink create(AdmTlRoleMenuLink newAdmTlRoleMenuLink,AdmTlRoleMenuLink oldAdmTlRoleMenuLink) throws ValidationExceptions,Exception
	{
		try {
			 String validationsFor;
			 validationsFor = "create";
			 List<AdmTlRoleMenuLink> admTlRoleMenuLink = newAdmTlRoleMenuLink.getAdmRoleMenuLink();
			 for(AdmTlRoleMenuLink roleMenuLink : admTlRoleMenuLink )
			 {
					validations.validate(roleMenuLink,"RoleMenuLink",validationsFor);
			 }
		
			 return admTlRoleMenuLinkDao.create(newAdmTlRoleMenuLink);		
		
		}catch (ValidationExceptions e){			
			CommonMessage.debugMsg("e.getMessage():"+e.getMessage());
			throw new ValidationExceptions(e.getMessage());
		}		
	}
	public String checkMenuRoleExist(String roleId,String menuId) throws Exception
	{
		 return admTlRoleMenuLinkDao.checkMenuRoleExist(roleId, menuId);
	}
	@Override
	public List<String[]> getAllQlinkList(MenuTree menuTree, String usrm_keyid)
			throws Exception {
		// TODO Auto-generated method stub
		return this.menuTreeDao.getAllQlinkList(menuTree,usrm_keyid);
	}
	public List<String[]> addmenuview1(CommonFilter commonFilter,GridParams gridparam)throws Exception{
		
		return menuTreeDao.addmenuview1(commonFilter,gridparam);
				}
	public Workbook getmenuRightsExcel(CommonFilter commonFilter, JSONObject colModel, String rptFormat)throws Exception {		
return menuTreeDao.getmenuRightsExcel(commonFilter,colModel, rptFormat);
	}
	public List<String[]> addmenureport(CommonFilter commonfilter,GridParams gridParam)throws Exception{
	
		return menuTreeDao.addmenureport(commonfilter,gridParam);
	}
	public Workbook getmenurightreportExcel(CommonFilter commonfilter,JSONObject jsonobj,String formats)throws Exception{
		 
  return menuTreeDao.getmenurightreportExcel(commonfilter, jsonobj, formats);
	}	
  public List<ComboBox> getmenu(ComboFilter combofilter)throws Exception{
	    combofilter.setCodeField("MENUPATH");
		combofilter.setIdField("MNUM_MENUNUMBER"); 
        combofilter.setTableName("ADM_VW_MENUPATH");
		return commonFilterDao.fillComboValues(combofilter);
  }
}
