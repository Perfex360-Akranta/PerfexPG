package com.akranta.tpm.dao.impl;

import java.util.ArrayList;
import com.akranta.tpm.utils.CommonMessage;
import java.util.List;

import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.NoDataFoundException;
import com.akranta.tpm.bean.GridParams;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.AdmTlEmployeeDelPrvlDao;
import com.akranta.tpm.dao.sql.AdmTlEmployeeDelPrvlSql;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.model.AdmTlEmployeeDelPrvl;
import com.akranta.tpm.model.CommonFilter;

public class AdmTlEmployeeDelPrvlDaoImpl implements AdmTlEmployeeDelPrvlDao{

	private DBActionTemplate dbActionTemplate;
	//private CommonFilterDao commonFilterdao;
    private AdmTlEmployeeDelPrvlSql admTlEmployeeDelPrvlSql=null;
	public AdmTlEmployeeDelPrvlDaoImpl(DBActionTemplate dbActionTemplate) {
		//commonFilterdao = new CommonFilterDaoImpl(dbActionTemplate);
		admTlEmployeeDelPrvlSql=new AdmTlEmployeeDelPrvlSql();
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	public AdmTlEmployeeDelPrvl EmployeeCreate(AdmTlEmployeeDelPrvl newadmTlEmployeeDelPrvl) throws Exception{
		List<String> sqls = new ArrayList<String>(); 
		newadmTlEmployeeDelPrvl.setAedpKeyid(dbActionTemplate.getSequenceNumber(AdmTlEmployeeDelPrvlSql.TBL_ADM_TL_EMPLOYEEDELPRVL,15,"AED",null,null));
		sqls.add(AdmTlEmployeeDelPrvlSql.getInsertSql(admTlEmployeeDelPrvlSql.getadm_DbFields(),newadmTlEmployeeDelPrvl.getSaveArray()));
		dbActionTemplate.executeStatements(sqls);
	  //  CommonMessage.debugMsg("After Save Data Query"+sqls);
	    return newadmTlEmployeeDelPrvl;
	}

	public List<String[]> EmployeeData(CommonFilter commonFilter) throws Exception{
		  StringBuilder sb=new StringBuilder();		  
		  sb.append(" SELECT GMLL_MENUNUMBER AS MENUNO, GMLL_PARENRNUMBER AS PARENTNO, ");
		  sb.append(" MNUM_MASTINTEGSQL AS MENUNAME, GMLL_MENUPARENTNAME ");
		  sb.append(" FROM ADM_TL_MENUMST,GEN_TL_MENULISTLINK WHERE GMLL_PARENRNUMBER=MNUM_MENUNUMBER ");
          CommonMessage.debugMsg("The Final Data"+sb.toString());
		  List<String[]> dataList = dbActionTemplate.getDataListWithColHeader(sb.toString(),null);
		  return dataList;
	}
	 public String ChkEmployeeList(CommonFilter commonFilter) throws Exception{
			
		 String menuno=commonFilter.getAbnAllch();
		 String sql="SELECT COUNT(*) FROM ADM_TL_EMPLOYEEDElPRVL WHERE AEDP_MENUNUMBER='"+menuno+"' AND AEDP_EMPKEYID='"+commonFilter.getKey()+"'";
		 String count=dbActionTemplate.getSingleValue(sql);
		// CommonMessage.debugMsg("The CountIs:::"+count);
		/// CommonMessage.debugMsg("The sql for delete:::"+sql);
		// CommonMessage.debugMsg("The sql for count:::"+count);
		 return count;
	 }
	 public List<String[]> roleEmpList(CommonFilter commonFilter,GridParams gridparam)throws Exception{
		 
		 String functional=commonFilter.getFlid();
		 String roleid=commonFilter.getActionKeyId();
		 String innerSql=AdmTlEmployeeDelPrvlSql.getRoleNameDetail(functional,roleid);
		 String countSql=CommonFilterSqls.countSql(innerSql,gridparam.getGridFilters());
		 String viewinfo=dbActionTemplate.getSingleValue(countSql);
		    long counts=Long.parseLong(viewinfo);
		    if( counts >0){
		    	String sql = CommonFilterSqls.addPaginationParams(innerSql,gridparam); 
		    	gridparam.setTotalRecordCnt(counts);
		    	List<String[]>datacon=dbActionTemplate.getDataList(sql);
			  	return datacon;
		    }
		    throw new NoDataFoundException("No Data Found"); 
	 }
	    public List<String[]> EmployeeList(CommonFilter commonFilter) throws Exception{
	    	String empname=commonFilter.getKey();
	    	StringBuffer sql=new StringBuffer();
	    	sql.append("SELECT AEDP_MENUNUMBER ");
	    	sql.append(" FROM ADM_TL_EMPLOYEEDELPRVL,GEN_TL_MENULISTLINK ");
            sql.append(" WHERE AEDP_MENUNUMBER=GMLL_MENUNUMBER ");
            sql.append(" AND AEDP_EMPKEYID='"+empname+"'");
           // CommonMessage.debugMsg("The Sql Data:::"+sql.toString());
            List<String[]> dataList=dbActionTemplate.getDataList(sql.toString());
            return dataList;
	    }
		 public List<String[]> addEmpList(CommonFilter commonFilter,GridParams gridparam)throws Exception{
			    StringBuilder sql=new StringBuilder();
			   if(UIUtils.isValidKeyId(commonFilter.getMainkeyid())){
				   
			    	   sql.append(" SELECT DISTINCT AEDP_EMPKEYID AS empkeyid, ");
					   sql.append(" EMPM_CODE AS EMPM_CODE,EMPM_NAME AS EMPM_NAME ");
					   sql.append(" FROM ADM_TL_EMPLOYEEDELPRVL,GEN_TL_EMPLOYEEMST ");
					   sql.append(" WHERE AEDP_EMPKEYID=EMPM_KEYID ");
					   sql.append(" AND  AEDP_MENUNUMBER  IN("+commonFilter.getMainkeyid().replaceAll("\"", "'")+")");
					   sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
					  // CommonMessage.debugMsg("The Sql List:With MenuNo:"+sql.toString());
			    }
			    else{
			    	   sql.append(" SELECT DISTINCT AEDP_EMPKEYID as empkeyid,");
					   sql.append(" EMPM_CODE AS EMPM_CODE,EMPM_NAME AS EMPM_NAME ");
					   sql.append(" FROM ADM_TL_EMPLOYEEDELPRVL,GEN_TL_EMPLOYEEMST ");
					   sql.append(" WHERE AEDP_EMPKEYID=EMPM_KEYID ");
					   sql.append(FilterCondSql.makeGridFilterCond(commonFilter.getGridFilter()));
					 //  CommonMessage.debugMsg("The Sql List::"+sql.toString());
			    }
			   List<String[]> datalist=dbActionTemplate.getDataList(sql.toString());
			   return datalist;
              }
	     public List<AdmTlEmployeeDelPrvl> AddEmployee(List<AdmTlEmployeeDelPrvl> EmployeeLink) throws Exception {

	    		AdmTlEmployeeDelPrvlSql admTlEmployeeDelPrvlSql = new AdmTlEmployeeDelPrvlSql(); // contains dbtable,field names, Field types and related sqls  of master table
	    		try
	    		{
	    			     List<String> sqls = new ArrayList<String>();
	    			     List <AdmTlEmployeeDelPrvl> methodslist = EmployeeLink;
	    			     GenSequenceNumber sequenceNumber = new GenSequenceNumber(this.dbActionTemplate.getDataSource().getConnection(),AdmTlEmployeeDelPrvlSql.TBL_ADM_TL_EMPLOYEEDELPRVL,15,"AED", null,null);
	    				 for(AdmTlEmployeeDelPrvl empLink:methodslist)
	    				 {
	    					 String seqNo=sequenceNumber.getSequnceNumber();
	    					 empLink.setAedpKeyid(seqNo); 
	    					 empLink.setAedpEmpmKeyid(empLink.getAedpEmpmKeyid());
	    					 empLink.setAedpEmpName(empLink.getAedpEmpName());
	    					 empLink.setAedpMenunumber(empLink.getAedpMenunumber());
	    					 empLink.setAedpParentnumber(empLink.getAedpParentnumber());
	    					 sqls.add(AdmTlEmployeeDelPrvlSql.getInsertSql(admTlEmployeeDelPrvlSql.getadm_DbFields(), empLink.getSaveArray()));
	    				 }
	    				 dbActionTemplate.executeStatements(sqls);
	    		}
	    		catch(BusinessApplicationExceptions e)
	    		{
	    			throw new BusinessApplicationExceptions(e.getMessage()); 
	    		}
	    		return null;
	    	}
	     public List<String> EmployeeDeletelist(String Empid) throws Exception{
	    	 
	    	 List<String> sql=new ArrayList<String>();
	    	 StringBuffer sb=new StringBuffer();
	    	 String modifiedId=Empid.replaceAll("\"","'");
	    	 if(UIUtils.isValidKeyId(Empid)){
	    	      sb.append(" DELETE FROM ADM_TL_EMPLOYEEDELPRVL ");
	    	      sb.append(" WHERE AEDP_EMPKEYID IN(" + modifiedId + ") ");
	    	      sql.add(sb.toString());
	    	      dbActionTemplate.executeStatements(sql);
	    	 }
	    	 return sql;
	     } 
}
