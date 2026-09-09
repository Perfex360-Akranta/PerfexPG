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
import com.akranta.tpm.dao.WwblaRptDao;
import com.akranta.tpm.dao.impl.FishBoneDaoImpl;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.impl.CommonFilterDaoImpl;
import com.akranta.tpm.dao.impl.Constants;
import com.akranta.tpm.dao.impl.DBActionTemplate;
import com.akranta.tpm.dao.impl.WwblaRptDaoImpl;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.BdmTlWwblamst;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.Fishbone;
import com.akranta.tpm.model.GenTlFishbonedtl;
import com.akranta.tpm.model.GenTlFishbonemst;
import com.akranta.tpm.service.FishBoneService;
import com.akranta.tpm.service.WwblaRptService;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;
import com.akranta.tpm.utils.Validations;

/**
 * Servlet implementation class AbnormalityFormServiceImpl
 */
public class WwblaRptServiceImpl  implements WwblaRptService {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private CommonFilterDao commonFilterDao;
	private WwblaRptDao  wwblaRptDao;
	private Validations validations ;
    public WwblaRptServiceImpl(DBActionTemplate dbActionTemplate){
    	try{
        commonFilterDao =new CommonFilterDaoImpl(dbActionTemplate);
        wwblaRptDao = new WwblaRptDaoImpl(dbActionTemplate);
        validations = new Validations();
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
        // TODO Auto-generated constructor stub
    }
    public List<String[]> getAllWwblaGrid(CommonFilter commonFilter) throws Exception {
		return wwblaRptDao.getAllWwblaGrid(commonFilter);  
	}
    public Workbook getWwblaExcel(JSONObject colmodel, String format,CommonFilter commonFilter) throws Exception {
		// TODO Auto-generated method stub
		return wwblaRptDao.getWwblaExcel(colmodel,format,commonFilter);
	}
   /* public BdmTlWwblamst getFillControl(String wwblKeyid)
	throws Exception {
// TODO Auto-generated method stub

      return wwblaRptDao.getFillControl(wwblKeyid);

}*/
    public List<String[]> getWwblaDetail(String keyid) throws Exception {
		return wwblaRptDao.getWwblaDetail(keyid);
	}
    @Override
	public Workbook getWwblaDetailForExcel(String keyid,JSONObject tblJSONObj,String format) throws Exception {
		return wwblaRptDao.getWwblaDetailForExcel(keyid,tblJSONObj,format);
	}

}
