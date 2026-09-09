package com.akranta.tpm.dao.impl;
/*
 * @Author:Prasanth 
 * 
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.CommonFilterDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.FilterCondSql;
import com.akranta.tpm.dao.sql.GenTlAllmoduleimgfileSql;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.CommonFilter;
import com.akranta.tpm.model.GenTlAllmoduleimgfile;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

public class CommonFilterDaoImpl implements CommonFilterDao {

	private DBActionTemplate dbActionTemplate; 
	
	private final int COND_CHECK_CONST = (1)|(1<<1)|(1<<2)|(1<<3)|(1<<4)|(1<<5)|(1<<6)|(1<<7)|(1<<7)|(1<<8)|(1<<9);  
	private final int COMPANY = 1;
	private final int LOCATION = 1 << 1;
	private final int SBU = 1 << 2;
	private final int PBU = 1 << 3;
	private final int FACTORY = 1<<4;
	private final int SECTION = 1<<5;
	private final int CELL = 1<<6;
	private final int MACHINE = 1<<7;
	private final int COSTCENTER = 1<<8;
	private String comboFetchCount = "100";
	
	public CommonFilterDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
		comboFetchCount = UIUtils.getPropertyValue("com.akranta.tpm.resources.ApplicationConfig", "COMBO_FETCH_RECORDS");
		if( comboFetchCount == null || comboFetchCount.isEmpty() || ! UIUtils.isInteger(comboFetchCount)){
			comboFetchCount = "100";
		}
	}
	public DBActionTemplate  getDBActionTemplate()
	{
		return this.dbActionTemplate ;
	}
	/*public CommonFilterDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	*/
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}

	public List<ComboBox> getComboList(ComboFilter comboFilter) throws Exception
	{
		try{
			String sql = getComboFilterSql(comboFilter);
			
						
			return getComboValues( sql, null);
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	private String getComboFilterSql(ComboFilter comboFilter)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("Select ");
		sql.append(comboFilter.getIdField() +" id,");
		
		if( comboFilter.getNameField() != null )
			 sql.append(comboFilter.getNameField());
		if( comboFilter.getCodeField() != null )
		{	
			if( comboFilter.getNameField() != null) 
				sql.append("||'-'||");
			
			sql.append(comboFilter.getNameField());
		}	
		sql.append( " text from " + comboFilter.getTableName());
		
		sql.append( " where 1 = 1 ");
		if( comboFilter.getCondSql() != null )
			sql.append( comboFilter.getCondSql());
		if( comboFilter.getOrderByField() != null )
			sql.append( comboFilter.getOrderByField() );
		
		return sql.toString();
	}
	
	public List<ComboBox> getCompanyComboList(CommonFilter commonFilter) throws Exception
	{
		try{
			StringBuilder sql = new StringBuilder( CommonFilterSqls.getCompanyComboSql());
			
			Object condArgs [] = new Object [] { getCurrentSelection(commonFilter.getCompany()) };
			
			int add = 0;
			sql.append(getConditionSql(commonFilter, add));

			sql.append((commonFilter.getCompany().getCondSql()) != null?(commonFilter.getCompany().getCondSql()):""); 
			sql .append(" order by text ");
			//return getComboValues( sql, condArgs);
			return exceuteComboQuery( sql.toString(),condArgs,commonFilter.getCompany()) ;
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}

	
	public List<ComboBox> getFactoryComboList(CommonFilter commonFilter) throws Exception
	{
		try{
			String sql = CommonFilterSqls.getFactoryComboSql();
			
			Object condArgs [] = new Object [] { getCurrentSelection(commonFilter.getFactory()) };
			
			int add =  COMPANY|LOCATION;
			sql += getConditionSql(commonFilter, add);
			
			sql += (commonFilter.getFactory().getCondSql()) != null?(commonFilter.getFactory().getCondSql()):""; 
			sql += " order by text ";
			//if(UIUtils.isValidKeyId( commonFilter.getCode()))
				//sql += "and EMPM_NAME  ||'-' || EMPM_CODE  like upper('%"+commonFilter.getCode()+"%')";
			//return getComboValues( sql, condArgs);
			return exceuteComboQuery( sql,condArgs,commonFilter.getFactory()) ;
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}		
	}
	
	
	public List<ComboBox> getFlidComboList(CommonFilter commonFilter) throws Exception
	{
		try{
			String sql = CommonFilterSqls.getFlidComboSql();
			
			Object condArgs [] = new Object [] { getCurrentSelection(commonFilter.getSection()) };
			CommonMessage.debugMsg(" condArgs " + condArgs);
			/*int add =COMPANY | LOCATION | SBU |PBU| FACTORY;
			sql += getConditionSql(commonFilter, add);
			
			
			if(UIUtils.isValidKeyId(commonFilter.getSection().getCode()))
				sql += " and SECT_CODE ||' - ' || SECT_NAME  like upper('%"+commonFilter.getSection().getCode()+"%')";
			
			sql += (commonFilter.getSection().getCondSql()) != null?(commonFilter.getSection().getCondSql()):""; 
		
			sql += " order by text ";
			//return getComboValues( sql, condArgs);
*/			return exceuteComboQuery( sql,condArgs,commonFilter.getSection()) ;
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}		
	}
	
	
	public List<ComboBox> getSectionComboList(CommonFilter commonFilter) throws Exception
	{
		try{
			
			String sql = null;
		////CommonMessage.debugMsg("122");
			//	//CommonMessage.debugMsg("commonFilter.getAp()>>"+commonFilter.getAp());
				if((commonFilter.getAp())!=null)
				{
					if((commonFilter.getAp()).equals("frmFilter"))
							{
			               sql = CommonFilterSqls.getSectionComboSqlFilter();
							}
					else{
						sql = CommonFilterSqls.getSectionComboSql();
					}
				}
				else{
					sql = CommonFilterSqls.getSectionComboSql();
				}
			
			Object condArgs [] = new Object [] { getCurrentSelection(commonFilter.getSection()) };
			CommonMessage.debugMsg(" condArgs " + condArgs);
			int add =COMPANY | LOCATION | SBU |PBU| FACTORY;
			sql += getConditionSql(commonFilter, add);
			
			
			if(UIUtils.isValidKeyId(commonFilter.getSection().getCode()))
				sql += " and  upper(SECT_CODE ||' - ' || SECT_NAME)  like upper('%"+commonFilter.getSection().getCode()+"%')";
			
			sql += (commonFilter.getSection().getCondSql()) != null?(commonFilter.getSection().getCondSql()):""; 
		
			sql += " order by text ";
			//return getComboValues( sql, condArgs);
			//CommonMessage.debugMsg("sql.toString()"+sql.toString());
			return exceuteComboQuery( sql,condArgs,commonFilter.getSection()) ;
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}		
	}
	
	public List<ComboBox> getCellComboList(CommonFilter commonFilter) throws Exception
	{
		int add =COMPANY |LOCATION|SBU | PBU | FACTORY |  SECTION;
		String sql;

	   //  sql = CommonFilterSqls.getCellComboSql();
		try{
			//CommonMessage.debugMsg("commonFilter.getAp()>>"+commonFilter.getAp());
			if((commonFilter.getAp())!=null)
			{
				if((commonFilter.getAp()).equals("frmFilter") || (commonFilter.getAp()).equals("frmuniqueposition"))
						{
					sql = CommonFilterSqls.getCellComboSqlFilter();
						}
				else{
					sql = CommonFilterSqls.getCellComboSql();
				}
			}
			else{
				sql = CommonFilterSqls.getCellComboSql();
			}
			
			
			Object condArgs [] = new Object [] { getCurrentSelection(commonFilter.getCell()) };
			 

			if( (commonFilter.getCostCenter() != null && commonFilter.getCostCenter().getId() != null) )
				sql += " AND CELL_COSTCENTREID = '" + commonFilter.getCostCenter().getId() + "'";
			
			if (commonFilter.getPcsEnabled() != null )
				if (commonFilter.getPcsEnabled().equals("Y")) {
				sql += " AND CELL_KEYID IN ( SELECT PELC_CELLID FROM    PCS_TL_ENABLELOSSCAPTURE " ; 
				sql += " WHERE  PELC_ISPCSENABLED = 'Y') " ;	
			}
			if(UIUtils.isValidKeyId(commonFilter.getCell().getId())){
			
				if(UIUtils.isValidKeyId(commonFilter.getCell().getCode()))
					sql += "and upper(CELL_CODE ||' - ' || CELL_NAME)  like upper('%"+commonFilter.getCell().getCode()+"%')";
			
			}
		
		sql += getConditionSql(commonFilter, add);
		
		if(UIUtils.isValidKeyId(commonFilter.getCell().getCode()))
			sql += "and upper(CELL_CODE ||' - ' || CELL_NAME)  like upper('%"+commonFilter.getCell().getCode()+"%')";
			//sql += getConditionSql(commonFilter, add);
			
			sql += (commonFilter.getCell().getCondSql()) != null?(commonFilter.getCell().getCondSql()):"";
			
			sql += " order by text ";
			//CommonMessage.debugMsg("inside dao impl"+sql);
			//CommonMessage.debugMsg("sql.toString()"+sql.toString());
			//return getComboValues( sql, condArgs);
			return exceuteComboQuery( sql,condArgs,commonFilter.getCell()) ;
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}		
	}
//	public List<ComboBox> getMachineComboList(CommonFilter commonFilter) throws Exception
//	{
//		try{
//			
//			ComboFilter machine = commonFilter.getMachine();
//			StringBuilder mSql = new StringBuilder();
//			
//			StringBuilder sql = new StringBuilder( CommonFilterSqls.getMachineComboSql());
//			
//			Object condArgs [] = new Object [] { getCurrentSelection(machine) };
//			
//			////CommonMessage.debugMsg(" commonFilter.getMachine().getId() " + commonFilter.getMachine().getId());
//			int add =COMPANY | LOCATION |SBU | PBU | FACTORY |  SECTION | CELL;
//			
//			//CommonMessage.debugMsg(" Inside Daoimpl Machine combo "+commonFilter.getMachine().getCode());
//			
//			if(UIUtils.isValidKeyId(machine.getId())){
//				if(UIUtils.isValidKeyId(machine.getCode()))
//					sql.append(" and   upper(MCHM_MACHINENAME || MCHM_MACHINENO )  like upper('%"+machine.getCode()+"%')");
//				//else
//				//	sql += getConditionSql(commonFilter, add);
//			}
//			sql.append( getConditionSql(commonFilter, add));
//			
//			String flid = commonFilter.getFlid();
//			//CommonMessage.debugMsg("flid======"+flid);
//			if(UIUtils.isValidKeyId(flid)){
////				sql.append(" and MCHM_FLID IN (SELECT FLID FROM gen_mv_flidhierarchy WHERE INSTR(PARENTFLIDS ||FLID,'"+flid+"')>0 ) ");
//				sql.append(" and MCHM_FLID IN (SELECT FLID FROM gen_mv_flidhierarchy WHERE   POSITION('"+flid+"' IN (PARENTFLIDS || FLID)) > 0 )");
//			}
//
//			if(UIUtils.isValidKeyId(machine.getCode()))
//				sql.append(" and upper(MCHM_MACHINENAME || MCHM_MACHINENO )  like upper('%"+machine.getCode()+"%')");
//			//CommonMessage.debugMsg("conditionalsql   : "+sql);
//			/*sql.append((commonFilter.getMachine().getCondSql()) != null?(commonFilter.getMachine().getCondSql()):""); 
//			
//			sql.append( " order by text ");
// 
//			StringBuilder s = new StringBuilder(); 
//			if("grid".equals(commonFilter.getMachine().getMode()) && UIUtils.isValidKeyId(commonFilter.getMachine().getNewSelectQuery()))
//			{
//				
//				s.append(commonFilter.getMachine().getNewSelectQuery());
//			}
//			else{
//				s = sql;
//				//s.append(" select * from ( " + sql + ") where rownum <= " + comboFetchCount );
//			}
//			*/
//			
//			if((! "grid".equals(machine.getMode())) && UIUtils.isValidKeyId(machine.getId())&& !UIUtils.isValidKeyId(machine.getName()) )
//            {
//				/*if(employee.getId().indexOf(",")>0)
//				
//					sql += " and EMPM_KEYID in('"+employee.getId().replaceAll(",", "','")+"')";
//				else
//					sql += " and EMPM_KEYID ='"+employee.getId()+"'";*/
//				StringBuilder s = new StringBuilder(  sql);
//				//s.append(" UNION " );
//				//CommonMessage.debugMsg("  s ------ " + s);
//				String cSql ="";
//				if(machine.getId().indexOf(",")>0){
//					s.append(" and  MCHM_KEYID  in('"+machine.getId().replaceAll(",", "','")+"')");
//					cSql = " and  MCHM_KEYID not in ('"+machine.getId().replaceAll(",", "','")+"')";
//				}	
//				else{
//					s.append(" and  MCHM_KEYID = '" +machine.getId()+"'");//'"+employee.getId()+"'"
//					cSql =  " and  MCHM_KEYID <> '" +machine.getId()+"'";
//				}	
//				
//				mSql.append(  s + " UNION " + sql.toString().replace("1 as r", "2 as r ") + cSql );
//				mSql.append( " order by r,text ");
//            }
//			else{
//				mSql.append( sql.toString().replace(",1 as r", ""));
//			}
//			
//			 //CommonMessage.debugMsg("machineSql  "+sql+"   "+commonFilter.getMachine().getMode());
//			//return getComboValues( sql, condArgs);
//			return exceuteComboQuery( mSql.toString(),condArgs,commonFilter.getMachine()) ;
// 
// 			
//		}catch(Exception e)
//		{
//			throw e ;//new Exception(e.getMessage());
//		}		
//	}
	
	public List<ComboBox> getMachineComboList(CommonFilter commonFilter) throws Exception
	{
		try{
			
			ComboFilter machine = commonFilter.getMachine();
			StringBuilder mSql = new StringBuilder();
			
			StringBuilder sql = new StringBuilder( CommonFilterSqls.getMachineComboSql());
			
			Object condArgs [] = new Object [] { getCurrentSelection(machine) };
			
			////CommonMessage.debugMsg(" commonFilter.getMachine().getId() " + commonFilter.getMachine().getId());
			int add =COMPANY | LOCATION |SBU | PBU | FACTORY |  SECTION | CELL;
			
			//CommonMessage.debugMsg(" Inside Daoimpl Machine combo "+commonFilter.getMachine().getCode());
			
			if(UIUtils.isValidKeyId(machine.getId())){
				if(UIUtils.isValidKeyId(machine.getCode()))
					sql.append(" and   upper(MCHM_MACHINENAME || MCHM_MACHINENO )  like upper('%"+machine.getCode()+"%')");
				//else
				//	sql += getConditionSql(commonFilter, add);
			}
			sql.append( getConditionSql(commonFilter, add));
			
			String flid = commonFilter.getFlid();
			//CommonMessage.debugMsg("flid======"+flid);
			
			//Swetha modified
			if (UIUtils.isValidKeyId(flid)) {
			    sql.append(" and MCHM_FLID IN (SELECT FLID FROM gen_mv_flidhierarchy " +
			               "WHERE POSITION('" + flid + "' IN (PARENTFLIDS || FLID)) > 0) ");
			}

			if(UIUtils.isValidKeyId(machine.getCode()))
				sql.append(" and upper(MCHM_MACHINENAME || MCHM_MACHINENO )  like upper('%"+machine.getCode()+"%')");
			//CommonMessage.debugMsg("conditionalsql   : "+sql);
			/*sql.append((commonFilter.getMachine().getCondSql()) != null?(commonFilter.getMachine().getCondSql()):""); 
			
			sql.append( " order by text ");
 
			StringBuilder s = new StringBuilder(); 
			if("grid".equals(commonFilter.getMachine().getMode()) && UIUtils.isValidKeyId(commonFilter.getMachine().getNewSelectQuery()))
			{
				
				s.append(commonFilter.getMachine().getNewSelectQuery());
			}
			else{
				s = sql;
				//s.append(" select * from ( " + sql + ") where rownum <= " + comboFetchCount );
			}
			*/
			
			if((! "grid".equals(machine.getMode())) && UIUtils.isValidKeyId(machine.getId())&& !UIUtils.isValidKeyId(machine.getName()) )
            {
				/*if(employee.getId().indexOf(",")>0)
				
					sql += " and EMPM_KEYID in('"+employee.getId().replaceAll(",", "','")+"')";
				else
					sql += " and EMPM_KEYID ='"+employee.getId()+"'";*/
				StringBuilder s = new StringBuilder(  sql);
				//s.append(" UNION " );
				//CommonMessage.debugMsg("  s ------ " + s);
				String cSql ="";
				if(machine.getId().indexOf(",")>0){
					s.append(" and  MCHM_KEYID  in('"+machine.getId().replaceAll(",", "','")+"')");
					cSql = " and  MCHM_KEYID not in ('"+machine.getId().replaceAll(",", "','")+"')";
				}	
				else{
					s.append(" and  MCHM_KEYID = '" +machine.getId()+"'");//'"+employee.getId()+"'"
					cSql =  " and  MCHM_KEYID <> '" +machine.getId()+"'";
				}	
				
				mSql.append(  s + " UNION " + sql.toString().replace("1 as r", "2 as r ") + cSql );
				mSql.append( " order by r,text ");
            }
			else{
				mSql.append( sql.toString().replace(",1 as r", ""));
			}
			
			 //CommonMessage.debugMsg("machineSql  "+sql+"   "+commonFilter.getMachine().getMode());
			//return getComboValues( sql, condArgs);
			return exceuteComboQuery( mSql.toString(),condArgs,commonFilter.getMachine()) ;
 
 			
		}catch(Exception e)
		{
			throw e ;//new Exception(e.getMessage());
		}		
	}
	
	@Override
	public String resolveSectId(String fnlnKeyid) throws Exception {
	    String sql =
	        "SELECT CASE " +
	        "  WHEN src.fnln_elementtype = 'L' THEN src.fnln_originalid " +
	        "  WHEN src.fnln_elementtype = 'C' THEN ( " +
	        "    SELECT anc.fnln_originalid " +
	        "    FROM gen_tl_functionallocn anc " +
	        "    WHERE anc.fnln_elementtype = 'L' " +
	        "      AND POSITION(anc.fnln_originalid IN src.fnln_elementid) > 0 " +
	        "    ORDER BY LENGTH(anc.fnln_elementid) DESC " +
	        "    LIMIT 1 " +
	        "  ) " +
	        "  ELSE NULL " +
	        "END AS sect_originalid " +
	        "FROM gen_tl_functionallocn src " +
	        "WHERE src.fnln_keyid = '" + fnlnKeyid + "'";

	    CommonMessage.debugMsg("resolveSectId sql: " + sql);
	    return dbActionTemplate.getSingleValue(sql); // same as getElementID
	}
	
	public List<ComboBox> getEmployeeComboList(ComboFilter employee) throws Exception
	{
		try{
			 
			StringBuilder mSql = new StringBuilder();
			StringBuilder sql = new StringBuilder( CommonFilterSqls.getEmployeeComboSql());
			
	//		Object condArgs [] = new Object [] { getCurrentSelection(commonFilter.getMachine()) };
			
	//		int add =COMPANY | FACTORY | SECTION | CELL;
	//		sql += getConditionSql(commonFilter, add);
			
			//CommonMessage.debugMsg("employee CONDSQL ..."+employee.getCondSql());
			//sql.append( (employee.getCondSql()) != null?(employee.getCondSql()):""); 
			
			if(UIUtils.isValidKeyId(employee.getId())||!UIUtils.isValidKeyId(employee.getId())|| employee.getId()==null){
				CommonMessage.debugMsg(" Inside If In Daoimpl "+employee.getId());
			if(UIUtils.isValidKeyId( employee.getCode())){
				sql.append( " and upper(EMPM_NAME  ||'-' || EMPM_CODE)  like upper('%");
				sql.append(employee.getCode());
				sql.append("%')");
			}	
           // if  (UIUtils.isValidKeyId( employee.getId()))
			
			if((! "grid".equals(employee.getMode())) && UIUtils.isValidKeyId(employee.getId() != null?employee.getId().trim():"")&& !UIUtils.isValidKeyId(employee.getName()) )
            {
				/*if(employee.getId().indexOf(",")>0)
				
					sql += " and EMPM_KEYID in('"+employee.getId().replaceAll(",", "','")+"')";
				else
					sql += " and EMPM_KEYID ='"+employee.getId()+"'";*/
				StringBuilder s = new StringBuilder(  sql);
				//s.append(" UNION " );
				//CommonMessage.debugMsg("  s ------ " + s);
				StringBuilder cSql = new StringBuilder();
				if(employee.getId().trim().indexOf(",")>0){
					s.append(" and  EMPM_KEYID  in('");
					s.append(employee.getId().trim().replaceAll(",", "','"));
					s.append("')");
					cSql.append(" and  EMPM_KEYID not in ('").append(employee.getId().trim().replaceAll(",", "','")).append("')");
				}	
				else{
					s.append(" and  EMPM_KEYID = '").append(employee.getId().trim()).append('\'');//'"+employee.getId()+"'"
					cSql.append(" and  EMPM_KEYID <> '").append(employee.getId().trim()).append('\'');
				}	
				
				mSql.append(  s );
				mSql.append(" UNION ");
				mSql.append(sql.toString().replace("1 as r", "2 as r ") );
				mSql.append( cSql );
				mSql.append( (employee.getCondSql()) != null?(employee.getCondSql()):"");
				mSql.append( " order by r,text ");
            }
			else{
				mSql.append( sql.toString().replace(",1 as r", ""));
				mSql.append( (employee.getCondSql()) != null?(employee.getCondSql()):"");
			}
				//sqls.append(employee.getId() + " id ");
			
			
			/* if( employee.getNameField() != null )
				 sql +=" and "+ employee.getNameField() + " like upper('%" + (employee.getName()!=null? employee.getName():"") + "%')";
		       else if( employee.getCodeField() != null )
		    	   sql +=" and "+  employee.getCodeField() + " like upper('%" + (employee.getCode()!=null? employee.getCode():"") + "%')";
			 */
			//CommonMessage.debugMsg("conditionalsql   : "+mSql);
			}
			 
			////CommonMessage.debugMsg(employee.getCode()+" sqlEmployee  " + sql);
			
			return exceuteComboQuery( mSql.toString(),null,employee) ;

		}catch(Exception e)
		{
			throw e;
		}		
	}
	
	
	/*public List<ComboBox> getEmployeeComboList(ComboFilter employee) throws Exception
	{
		try{
			 
			String sql = CommonFilterSqls.getEmployeeComboSql();
			
	//		Object condArgs [] = new Object [] { getCurrentSelection(commonFilter.getMachine()) };
			
	//		int add =COMPANY | FACTORY | SECTION | CELL;
	//		sql += getConditionSql(commonFilter, add);
			
			sql += (employee.getCondSql()) != null?(employee.getCondSql()):""; 
			
			if(UIUtils.isValidKeyId(employee.getId())||!UIUtils.isValidKeyId(employee.getId())|| employee.getId()==null){CommonMessage.debugMsg(" Inside If In Daoimpl "+employee.getId());
			if(UIUtils.isValidKeyId( employee.getCode()))
				sql += " and EMPM_NAME  ||'-' || EMPM_CODE  like upper('%"+employee.getCode()+"%')";
           // if  (UIUtils.isValidKeyId( employee.getId()))
			
			if((! "grid".equals(employee.getMode())) && UIUtils.isValidKeyId(employee.getId())&& !UIUtils.isValidKeyId(employee.getName()) )
            {
				/*if(employee.getId().indexOf(",")>0)
				
					sql += " and EMPM_KEYID in('"+employee.getId().replaceAll(",", "','")+"')";
				else
					sql += " and EMPM_KEYID ='"+employee.getId()+"'";*/
				
		/*		StringBuilder s = new StringBuilder(  sql);
				if(employee.getId().indexOf(",")>0)
					s.append(" and  EMPM_KEYID  in('"+employee.getId().replaceAll(",", "','")+"')");
				else
					s.append(" and  EMPM_KEYID = '" +employee.getId()+"'");//'"+employee.getId()+"'"
				
				sql = s + " UNION " + sql;
            }
				//sqls.append(employee.getId() + " id ");
			
			
			/* if( employee.getNameField() != null )
				 sql +=" and "+ employee.getNameField() + " like upper('%" + (employee.getName()!=null? employee.getName():"") + "%')";
		       else if( employee.getCodeField() != null )
		    	   sql +=" and "+  employee.getCodeField() + " like upper('%" + (employee.getCode()!=null? employee.getCode():"") + "%')";
			 */
			/*CommonMessage.debugMsg("conditionalsql   : "+sql);
			}
			 
			//CommonMessage.debugMsg(employee.getCode()+" sqlEmployee  " + sql);
			
			return exceuteComboQuery( sql,null,employee) ;

		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}		
	}*/
	private List<ComboBox> getComboGriddata(String exectueSql, Object[] condArgs, String mode) throws Exception {
		// TODO Auto-generated method stub
		//CommonMessage.debugMsg("getComboGriddata");
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null; 
		try{
			//if( condArgs != null )
			//	rs = dbActionTemplate.getData(sql, condArgs) ;
			//else
			
			
			List<ComboBox> comboList = new ArrayList<ComboBox>();

			if("grid".equals(mode)){
				List<String []>   combolistArray = dbActionTemplate.getDataListWithColHeader(exectueSql,null) ;
				for(int i=0;i<combolistArray.size();i++){
					 
					 ComboBox combobox = new ComboBox();	 
					// combobox.setId(combolistArray.get(i)[0]);
					 //combobox.setText(combolistArray.get(i)[1]);
					 combobox.setColumns(combolistArray.get(i));
					 
					 comboList.add(combobox );	 
				 }
			}	
			else{
				connection = dbActionTemplate.getNewConnection();
				statement = connection.createStatement();
			    rs = statement.executeQuery(exectueSql);
//				rs = dbActionTemplate.getData(exectueSql) ;
//				statement = rs.getStatement();
//				connection = rs.getStatement().getConnection();
				while(rs.next())
				{	
					ComboBox compComb = new ComboBox();
					compComb.setId(rs.getString("id"));
					compComb.setText(rs.getString("text"));
					comboList.add(compComb);
				}
			}	
			 
			 
			return comboList;
		}finally{
			if( rs != null)
				rs.close();
			rs = null;
			DBActionTemplate.closeConnection(rs,statement, null, null, connection);
		}
	}
	private List<ComboBox> getComboValues(String sql, Object[]condArgs  ) throws Exception
	{
		
		ResultSet rs = null;
		Connection connection = null;
		Statement statement = null; 
		try{
			//if( condArgs != null )
			//	rs = dbActionTemplate.getData(sql, condArgs) ;
			//else
			rs = dbActionTemplate.getData(sql) ;
			statement = rs.getStatement();
			connection = rs.getStatement().getConnection();
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));
				comboList.add(compComb);
			}
			
			
			return comboList;
		}finally{
			if( rs != null)
				rs.close();
			rs = null;
			DBActionTemplate.closeConnection(rs,statement, null, null, connection);
		}
	}

	private String getCurrentSelection(ComboFilter comboFilter )
	{
		return "%"+ (comboFilter.getCode() != null?comboFilter.getCode().trim():"") + 
		         "-" + (comboFilter.getName()!=null?comboFilter.getName().trim():"") +"%" ;
	}

	private String getConditionSql(CommonFilter commonFilter, int add  )
	{

		StringBuilder condSql = new StringBuilder();
		
		try{
			
			if(  (commonFilter.getCompany() != null && commonFilter.getCompany().getId() != null) && commonFilter.getCompany().getId().trim().length() > 0 && (! "grid".equals(commonFilter.getCompany().getMode())) &&   (  (add &  COMPANY) == COMPANY) )
				condSql.append(" AND COMP_KEYID = '").append( commonFilter.getCompany().getId()).append('\'');
			
			if( (commonFilter.getLocation() != null && commonFilter.getLocation().getId() != null) && commonFilter.getLocation().getId().trim().length() > 0 && (! "grid".equals(commonFilter.getLocation().getMode())) && (  (add &  LOCATION) == LOCATION) )
				condSql.append(" AND LOCN_KEYID = '").append(  commonFilter.getLocation().getId()).append('\'');
			if( (commonFilter.getSbu() != null && commonFilter.getSbu().getId() != null) && commonFilter.getSbu().getId().trim().length() > 0 && (! "grid".equals(commonFilter.getSbu().getMode())) && (  (add &  SBU) == SBU) )
				condSql.append( " AND SBUT_KEYID = '" ).append( commonFilter.getSbu().getId()).append('\'');
			if( (commonFilter.getPbu() != null && commonFilter.getPbu().getId() != null) && commonFilter.getPbu().getId().trim().length() > 0 && (! "grid".equals(commonFilter.getPbu().getMode())) && (  (add &  PBU) == PBU) )
				condSql.append( " AND PBUT_KEYID = '").append(  commonFilter.getPbu().getId() ).append('\'');
		/*	if( commonFilter.getFactory() != null && commonFilter.getFactory().getId() != null && ((FACTORY & add) == FACTORY) )
				condSql += " AND FACT_KEYID = '" + commonFilter.getFactory().getId() + "'";
		*/	
			if( commonFilter.getSection() != null && commonFilter.getSection().getId() != null && commonFilter.getSection().getId().trim().length() > 0 && (! "grid".equals(commonFilter.getSection().getMode())) && ((SECTION & add) == SECTION) )
			{	
				condSql.append(  " AND SECT_KEYID = '").append(  commonFilter.getSection().getId().trim() ).append('\'');
			}	
			if( commonFilter.getCell() != null && commonFilter.getCell().getId() != null && commonFilter.getCell().getId().trim().length() > 0 && (! "grid".equals(commonFilter.getCell().getMode())) && ((CELL & add) == CELL) )
				condSql.append(  " AND CELL_KEYID = '" ).append(  commonFilter.getCell().getId()).append('\'');
			if( commonFilter.getMachine() != null && commonFilter.getMachine().getId() != null && commonFilter.getMachine().getId().trim().length() > 0  && (! "grid".equals(commonFilter.getMachine().getMode())) && ((MACHINE & add) == MACHINE) )
				condSql.append(  " AND MCHM_KEYID = '" ).append(  commonFilter.getMachine().getId()).append('\'');
			
		}catch( Exception e)
		{
			
		}
		////CommonMessage.debugMsg(" condSql " + condSql);
		return (condSql.length() != 0 ? condSql.toString():"");
	}	
	
	/*public List<ComboBox> fillComboValues(ComboFilter comboFilter) throws Exception
	{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT DISTINCT ");
		String selectSql = null;
		String likeSql = null ;
		if( comboFilter.getIdField()!= null )//comboFilter.getIdField()
			sql.append(comboFilter.getIdField() + " id ");
		
		if( comboFilter.getCodeField() != null && comboFilter.getNameField() != null )
		{
			selectSql = "," + comboFilter.getNameField() + "||'-'||" +comboFilter.getCodeField() ;
			if( comboFilter.getCode() != null && comboFilter.getName() != null)
				likeSql = " ( upper(" + comboFilter.getCodeField() + ") like upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%') or "  
						 + " upper( "+ comboFilter.getNameField() + ") like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')  ) ";
		}
		else if( comboFilter.getNameField() != null )
		{
			selectSql = "," + comboFilter.getNameField();
			if(  comboFilter.getName() != null )
				likeSql = " Upper(" + comboFilter.getNameField() + ") like Upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		}
		else if( comboFilter.getCodeField() != null )
		{
			selectSql = "," + comboFilter.getCodeField(); 
			if( comboFilter.getCode() != null )
				likeSql = " Upper( "+ comboFilter.getCodeField() + ") like Upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%')"; 
		}
		/*else if( comboFilter.getCode() != null )
		{
			//selectSql = "," + comboFilter.getCodeField();  
		 	likeSql = comboFilter.getCodeField() + " like '%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%'"; 
		}*/
		
		//
		
		/*if(UIUtils.isValidKeyId( comboFilter.getCode())){ //PRDM_NAME,PRDM_CODE
			likeSql += "and PRDM_NAME  ||'-' || PRDM_CODE  like upper('%"+comboFilter.getCode()+"%')";
	        CommonMessage.debugMsg(" Product Id : "+comboFilter.getCode());
		}*/
		//CommonMessage.debugMsg("getID   :"+comboFilter.getId());
	 /* if(UIUtils.isValidKeyId(comboFilter.getName()) && !UIUtils.isValidKeyId(comboFilter.getId()))
	  {	  
			//selectSql = "," + comboFilter.getNameField();
		if( comboFilter.getCodeField() != null && comboFilter.getNameField() != null )
			likeSql  =" and "+ comboFilter.getNameField() +"||'-'||"+comboFilter.getCodeField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		else if( comboFilter.getNameField() != null )
			likeSql  =" and "+ comboFilter.getNameField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		else if( comboFilter.getCodeField() != null )
			likeSql  =" and "+  comboFilter.getCodeField() + " like upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%')";
			CommonMessage.debugMsg(UIUtils.isValidKeyId(likeSql)+" condsqlll  "+likeSql);
		}
	   else{
			likeSql  =" and "+ comboFilter.getNameField() +"||'-'||"+comboFilter.getCodeField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		}
	*/
		/*
		sql.append( selectSql + " text ");
		if( comboFilter.getOrderByField() != null && comboFilter.getOrderByField().trim().length() >  0)
			sql.append("," + comboFilter.getOrderByField().replace("desc", "").replace("asc", ""));
		
		sql.append(" from ");
		sql.append(comboFilter.getTableName());
		sql.append(" where 1 = 1  " ) ;// + likeSql);and
		//sql.append(" and  " ) ;
		if( (! "grid".equals(comboFilter.getMode())) && UIUtils.isValidKeyId(comboFilter.getId())  && ! UIUtils.isValidKeyId(comboFilter.getName()) )
		{	
			/*if(comboFilter.getId().indexOf(",")>0)
				sql.append(" and  "+comboFilter.getIdField() + "  in('"+comboFilter.getId().replaceAll(",", "','")+"')");
			else
				sql.append(" and  "+comboFilter.getIdField() + " = '" +comboFilter.getId()+"'");//'"+employee.getId()+"'"*/
			/*StringBuilder s = new StringBuilder(  sql);
			//s.append(" UNION " );
			CommonMessage.debugMsg("  s ------ " + s);
			if(comboFilter.getId().indexOf(",")>0)
				s.append(" and  "+comboFilter.getIdField() + "  in('"+comboFilter.getId().replaceAll(",", "','")+"')");
			else
				s.append(" and  "+comboFilter.getIdField() + " = '" +comboFilter.getId()+"'");//'"+employee.getId()+"'"
			
			sql.insert(0, s + " UNION ");
		}
		
		if(UIUtils.isValidKeyId(likeSql))
			sql.append( " and " + likeSql);
		if(comboFilter.getCondSql() != null )
			sql.append(comboFilter.getCondSql());
		
		if( comboFilter.getOrderByField() != null && comboFilter.getOrderByField().trim().length() > 0 )
			sql.append(" order by " + comboFilter.getOrderByField() );
		else
			sql.append(" order by text ");
		
		//ResultSet rs = null; 
		//Connection connection = null;
		
		//CommonMessage.debugMsg(" sql111 " + sql);
		try{
			/*rs = dbActionTemplate.getData(sql.toString()) ;
			connection = rs.getStatement().getConnection();
			
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));
				
				comboList.add(compComb);
			}*/
			//return comboList;
			
			
			/*return exceuteComboQuery(sql.toString(), null, comboFilter);
		}finally{
			
			//DBActionTemplate.closeConnection(rs,null,null,null,connection);
			
		}
	}*/
	
	public List<ComboBox> fillComboValues(ComboFilter comboFilter) throws Exception
	{
		
		StringBuffer sql = new StringBuffer();
		StringBuffer tbl = new StringBuffer(" from "+ comboFilter.getTableName() + " where 1 = 1  ");
		//sql.append(" SELECT DISTINCT ");
		StringBuilder selectSql = new StringBuilder(" SELECT DISTINCT ");
		String likeSql = null ;
		if( comboFilter.getIdField()!= null )//comboFilter.getIdField()
			selectSql.append(comboFilter.getIdField() + " id ");
		
		if( comboFilter.getCodeField() != null && comboFilter.getNameField() != null )
		{
			selectSql.append("," + comboFilter.getNameField() + "||'-'||" +comboFilter.getCodeField() );
			if( comboFilter.getCode() != null && comboFilter.getName() != null)
				likeSql = " ( upper(" + comboFilter.getCodeField() + ") like upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%') or "  
						 + " upper( "+ comboFilter.getNameField() + ") like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')  ) ";
		}
		else if( comboFilter.getNameField() != null )
		{
			selectSql.append("," + comboFilter.getNameField());
			if(  comboFilter.getName() != null )
				likeSql = " Upper(" + comboFilter.getNameField() + ") like Upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		}
		else if( comboFilter.getCodeField() != null )
		{
			selectSql.append("," + comboFilter.getCodeField()); 
			if( comboFilter.getCode() != null )
				likeSql = " Upper( "+ comboFilter.getCodeField() + ") like Upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%')"; 
		}
		/*else if( comboFilter.getCode() != null )
		{
			//selectSql = "," + comboFilter.getCodeField();  
		 	likeSql = comboFilter.getCodeField() + " like '%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%'"; 
		}*/
		
		//
		
		/*if(UIUtils.isValidKeyId( comboFilter.getCode())){ //PRDM_NAME,PRDM_CODE
			likeSql += "and PRDM_NAME  ||'-' || PRDM_CODE  like upper('%"+comboFilter.getCode()+"%')";
	        CommonMessage.debugMsg(" Product Id : "+comboFilter.getCode());
		}*/
		CommonMessage.debugMsg("getID   :"+comboFilter.getId());
	 /* if(UIUtils.isValidKeyId(comboFilter.getName()) && !UIUtils.isValidKeyId(comboFilter.getId()))
	  {	  
			//selectSql = "," + comboFilter.getNameField();
		if( comboFilter.getCodeField() != null && comboFilter.getNameField() != null )
			likeSql  =" and "+ comboFilter.getNameField() +"||'-'||"+comboFilter.getCodeField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		else if( comboFilter.getNameField() != null )
			likeSql  =" and "+ comboFilter.getNameField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		else if( comboFilter.getCodeField() != null )
			likeSql  =" and "+  comboFilter.getCodeField() + " like upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%')";
			CommonMessage.debugMsg(UIUtils.isValidKeyId(likeSql)+" condsqlll  "+likeSql);
		}
	   else{
			likeSql  =" and "+ comboFilter.getNameField() +"||'-'||"+comboFilter.getCodeField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		}
	*/
		
		//sql.append( selectSql + " text ");
		selectSql.append(" text " );
		if( comboFilter.getOrderByField() != null && comboFilter.getOrderByField().trim().length() >  0)
			selectSql.append("," + comboFilter.getOrderByField().replace("desc", "").replace("asc", "") );//sql.append("," + comboFilter.getOrderByField().replace("desc", "").replace("asc", ""));
		String cSql ="";
		//tbl.append(" from ");
		//tbl.append(comboFilter.getTableName());
		//sql.append(" where 1 = 1  " ) ;// + likeSql);and
		//sql.append(" and  " ) ;
		boolean isCombo = false;
		if( (! "grid".equals(comboFilter.getMode())) && UIUtils.isValidKeyId(comboFilter.getId())  && ! UIUtils.isValidKeyId(comboFilter.getName()) )
		{	
			isCombo =true;
			StringBuilder s = new StringBuilder(  selectSql +", 1 r ");
			
			selectSql.append(",2  r " );
			s.append(tbl);
			
			
			//s.append(" UNION " );
			//CommonMessage.debugMsg("  s ------ " + s);
			
			if(comboFilter.getId().indexOf(",")>0){
				s.append(" and  "+comboFilter.getIdField() + "  in('"+comboFilter.getId().replaceAll(",", "','")+"')");
				cSql = " and  "+comboFilter.getIdField() + " not in ('"+comboFilter.getId().replaceAll(",", "','")+"')";
			}	
			else{
				s.append(" and  "+comboFilter.getIdField() + " = '" +comboFilter.getId()+"'");//'"+employee.getId()+"'"
				cSql = " and  "+comboFilter.getIdField() + " <> '" +comboFilter.getId() + "'";//'"+employee.getId()+"'"
			}	
			
			if(comboFilter.getCondSql() != null )
				s.append(comboFilter.getCondSql());
			
			//sql.insert(0, s + " UNION ");
			//sql.append(" UNION " + s + " ) where 1 = 1 ");
			//sql.insert(0, " SELECT * from ( ");
			//CommonMessage.debugMsg("  sql ------ " + sql );
			s.append(" UNION ");
			sql.append(s);
			
			
		}
		selectSql.append( tbl);
		sql.append( selectSql );
		
		if(UIUtils.isValidKeyId(likeSql))
			sql.append( " and " + likeSql);
		if(comboFilter.getCondSql() != null )
			sql.append(comboFilter.getCondSql());
		
		if( isCombo )
			sql.append(cSql);		
		sql.append(" order by ");
		
		if( isCombo )
			sql.append(" r, ");
		
		if( comboFilter.getOrderByField() != null && comboFilter.getOrderByField().trim().length() > 0 )
			sql.append( comboFilter.getOrderByField() );
		else
			sql.append(" text ");
		
		//ResultSet rs = null; 
		//Connection connection = null;
		
		//CommonMessage.debugMsg(" sql111 " + sql);
		
		CommonMessage.debugMsg("Like Sql:"+likeSql);
		
		try{
			/*rs = dbActionTemplate.getData(sql.toString()) ;
			connection = rs.getStatement().getConnection();
			
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));
				
				comboList.add(compComb);
			}*/
			//return comboList;
			
			//CommonMessage.debugMsg("SQL Combo Data"+sql.toString());
			return exceuteComboQuery(sql.toString(), null, comboFilter);
		}finally{
			
			
			//DBActionTemplate.closeConnection(rs,null,null,null,connection);
			
		}
	}
	
	
	public List<ComboBox> fillComboValuesWithoutDistinct(ComboFilter comboFilter) throws Exception
	{
		
		StringBuffer sql = new StringBuffer();
		StringBuffer tbl = new StringBuffer(" from "+ comboFilter.getTableName() + " where 1 = 1  ");
		//sql.append(" SELECT DISTINCT ");
		StringBuilder selectSql = new StringBuilder(" SELECT  ");
		String likeSql = null ;
		if( comboFilter.getIdField()!= null )//comboFilter.getIdField()
			selectSql.append(comboFilter.getIdField() + " id ");
		
		if( comboFilter.getCodeField() != null && comboFilter.getNameField() != null )
		{
			selectSql.append("," + comboFilter.getNameField() + "||'-'||" +comboFilter.getCodeField() );
			if( comboFilter.getCode() != null && comboFilter.getName() != null)
				likeSql = " ( upper(" + comboFilter.getCodeField() + ") like upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%') or "  
						 + " upper( "+ comboFilter.getNameField() + ") like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')  ) ";
		}
		else if( comboFilter.getNameField() != null )
		{
			selectSql.append("," + comboFilter.getNameField());
			if(  comboFilter.getName() != null )
				likeSql = " Upper(" + comboFilter.getNameField() + ") like Upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		}
		else if( comboFilter.getCodeField() != null )
		{
			selectSql.append("," + comboFilter.getCodeField()); 
			if( comboFilter.getCode() != null )
				likeSql = " Upper( "+ comboFilter.getCodeField() + ") like Upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%')"; 
		}
		/*else if( comboFilter.getCode() != null )
		{
			//selectSql = "," + comboFilter.getCodeField();  
		 	likeSql = comboFilter.getCodeField() + " like '%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%'"; 
		}*/
		
		//
		
		/*if(UIUtils.isValidKeyId( comboFilter.getCode())){ //PRDM_NAME,PRDM_CODE
			likeSql += "and PRDM_NAME  ||'-' || PRDM_CODE  like upper('%"+comboFilter.getCode()+"%')";
	        CommonMessage.debugMsg(" Product Id : "+comboFilter.getCode());
		}*/
		CommonMessage.debugMsg("getID   :"+comboFilter.getId());
	 /* if(UIUtils.isValidKeyId(comboFilter.getName()) && !UIUtils.isValidKeyId(comboFilter.getId()))
	  {	  
			//selectSql = "," + comboFilter.getNameField();
		if( comboFilter.getCodeField() != null && comboFilter.getNameField() != null )
			likeSql  =" and "+ comboFilter.getNameField() +"||'-'||"+comboFilter.getCodeField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		else if( comboFilter.getNameField() != null )
			likeSql  =" and "+ comboFilter.getNameField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		else if( comboFilter.getCodeField() != null )
			likeSql  =" and "+  comboFilter.getCodeField() + " like upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%')";
			CommonMessage.debugMsg(UIUtils.isValidKeyId(likeSql)+" condsqlll  "+likeSql);
		}
	   else{
			likeSql  =" and "+ comboFilter.getNameField() +"||'-'||"+comboFilter.getCodeField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		}
	*/
		
		//sql.append( selectSql + " text ");
		selectSql.append(" text " );
		if( comboFilter.getOrderByField() != null && comboFilter.getOrderByField().trim().length() >  0)
			selectSql.append("," + comboFilter.getOrderByField().replace("desc", "").replace("asc", "") );//sql.append("," + comboFilter.getOrderByField().replace("desc", "").replace("asc", ""));
		String cSql ="";
		//tbl.append(" from ");
		//tbl.append(comboFilter.getTableName());
		//sql.append(" where 1 = 1  " ) ;// + likeSql);and
		//sql.append(" and  " ) ;
		boolean isCombo = false;
		if( (! "grid".equals(comboFilter.getMode())) && UIUtils.isValidKeyId(comboFilter.getId())  && ! UIUtils.isValidKeyId(comboFilter.getName()) )
		{	
			isCombo =true;
			StringBuilder s = new StringBuilder(  selectSql +", 1 r ");
			
			selectSql.append(",2  r " );
			s.append(tbl);
			
			
			//s.append(" UNION " );
			//CommonMessage.debugMsg("  s ------ " + s);
			
			if(comboFilter.getId().indexOf(",")>0){
				s.append(" and  "+comboFilter.getIdField() + "  in('"+comboFilter.getId().replaceAll(",", "','")+"')");
				cSql = " and  "+comboFilter.getIdField() + " not in ('"+comboFilter.getId().replaceAll(",", "','")+"')";
			}	
			else{
				s.append(" and  "+comboFilter.getIdField() + " = '" +comboFilter.getId()+"'");//'"+employee.getId()+"'"
				cSql = " and  "+comboFilter.getIdField() + " <> '" +comboFilter.getId() + "'";//'"+employee.getId()+"'"
			}	
			
			if(comboFilter.getCondSql() != null )
				s.append(comboFilter.getCondSql());
			
			//sql.insert(0, s + " UNION ");
			//sql.append(" UNION " + s + " ) where 1 = 1 ");
			//sql.insert(0, " SELECT * from ( ");
			//CommonMessage.debugMsg("  sql ------ " + sql );
			s.append(" UNION ");
			sql.append(s);
			
			
		}
		selectSql.append( tbl);
		sql.append( selectSql );
		
		if(UIUtils.isValidKeyId(likeSql))
			sql.append( " and " + likeSql);
		if(comboFilter.getCondSql() != null )
			sql.append(comboFilter.getCondSql());
		
		if( isCombo )
			sql.append(cSql);		
		sql.append(" order by ");
		
		if( isCombo )
			sql.append(" r, ");
		
		if( comboFilter.getOrderByField() != null && comboFilter.getOrderByField().trim().length() > 0 )
			sql.append( comboFilter.getOrderByField() );
		else
			sql.append(" text ");
		
		//ResultSet rs = null; 
		//Connection connection = null;
		
		//CommonMessage.debugMsg(" sql111 " + sql);
		
		CommonMessage.debugMsg("Like Sql:"+likeSql);
		
		try{
			/*rs = dbActionTemplate.getData(sql.toString()) ;
			connection = rs.getStatement().getConnection();
			
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));
				
				comboList.add(compComb);
			}*/
			//return comboList;
			
			//CommonMessage.debugMsg("SQL Combo Data"+sql.toString());
			return exceuteComboQuery(sql.toString(), null, comboFilter);
		}finally{
			
			
			//DBActionTemplate.closeConnection(rs,null,null,null,connection);
			
		}
	}
	
	public List<ComboBox> fillComboValuesWithoutCondition(ComboFilter comboFilter) throws Exception
	{
		
		StringBuffer sql = new StringBuffer();
		StringBuffer tbl = new StringBuffer(" from "+ comboFilter.getTableName() + " where 1 = 1  ");
		//sql.append(" SELECT DISTINCT ");
		StringBuilder selectSql = new StringBuilder(" SELECT DISTINCT ");
		String likeSql = null ;
		if( comboFilter.getIdField()!= null )//comboFilter.getIdField()
			selectSql.append(comboFilter.getIdField() + " id ");
		
		if( comboFilter.getCodeField() != null && comboFilter.getNameField() != null )
		{
			selectSql.append("," + comboFilter.getNameField() + "||'-'||" +comboFilter.getCodeField() );
			if( comboFilter.getCode() != null && comboFilter.getName() != null)
				likeSql = " ( upper(" + comboFilter.getCodeField() + ") like upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%') or "  
						 + " upper( "+ comboFilter.getNameField() + ") like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')  ) ";
		}
		else if( comboFilter.getNameField() != null )
		{
			selectSql.append("," + comboFilter.getNameField());
			if(  comboFilter.getName() != null )
				likeSql = " Upper(" + comboFilter.getNameField() + ") like Upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		}
		else if( comboFilter.getCodeField() != null )
		{
			selectSql.append("," + comboFilter.getCodeField()); 
			if( comboFilter.getCode() != null )
				likeSql = " Upper( "+ comboFilter.getCodeField() + ") like Upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%')"; 
		}
		/*else if( comboFilter.getCode() != null )
		{
			//selectSql = "," + comboFilter.getCodeField();  
		 	likeSql = comboFilter.getCodeField() + " like '%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%'"; 
		}*/
		
		//
		
		/*if(UIUtils.isValidKeyId( comboFilter.getCode())){ //PRDM_NAME,PRDM_CODE
			likeSql += "and PRDM_NAME  ||'-' || PRDM_CODE  like upper('%"+comboFilter.getCode()+"%')";
	        CommonMessage.debugMsg(" Product Id : "+comboFilter.getCode());
		}*/
		CommonMessage.debugMsg("getID   :"+comboFilter.getId());
	 /* if(UIUtils.isValidKeyId(comboFilter.getName()) && !UIUtils.isValidKeyId(comboFilter.getId()))
	  {	  
			//selectSql = "," + comboFilter.getNameField();
		if( comboFilter.getCodeField() != null && comboFilter.getNameField() != null )
			likeSql  =" and "+ comboFilter.getNameField() +"||'-'||"+comboFilter.getCodeField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		else if( comboFilter.getNameField() != null )
			likeSql  =" and "+ comboFilter.getNameField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		else if( comboFilter.getCodeField() != null )
			likeSql  =" and "+  comboFilter.getCodeField() + " like upper('%" + (comboFilter.getCode()!=null? comboFilter.getCode():"") + "%')";
			CommonMessage.debugMsg(UIUtils.isValidKeyId(likeSql)+" condsqlll  "+likeSql);
		}
	   else{
			likeSql  =" and "+ comboFilter.getNameField() +"||'-'||"+comboFilter.getCodeField() + " like upper('%" + (comboFilter.getName()!=null? comboFilter.getName():"") + "%')";
		}
	*/
		
		//sql.append( selectSql + " text ");
		selectSql.append(" text " );
		if( comboFilter.getOrderByField() != null && comboFilter.getOrderByField().trim().length() >  0)
			selectSql.append("," + comboFilter.getOrderByField().replace("desc", "").replace("asc", "") );//sql.append("," + comboFilter.getOrderByField().replace("desc", "").replace("asc", ""));
		String cSql ="";
		//tbl.append(" from ");
		//tbl.append(comboFilter.getTableName());
		//sql.append(" where 1 = 1  " ) ;// + likeSql);and
		//sql.append(" and  " ) ;
		boolean isCombo = false;
		if( (! "grid".equals(comboFilter.getMode())) && UIUtils.isValidKeyId(comboFilter.getId())  && ! UIUtils.isValidKeyId(comboFilter.getName()) )
		{	
			isCombo =true;
			StringBuilder s = new StringBuilder(  selectSql +", 1 r ");
			
			selectSql.append(",2  r " );
			s.append(tbl);
			
			
			//s.append(" UNION " );
			//CommonMessage.debugMsg("  s ------ " + s);
			
			if(comboFilter.getId().indexOf(",")>0){
				s.append(" and  "+comboFilter.getIdField() + "  in('"+comboFilter.getId().replaceAll(",", "','")+"')");
				cSql = " and  "+comboFilter.getIdField() + " not in ('"+comboFilter.getId().replaceAll(",", "','")+"')";
			}	
			else{
				s.append(" and  "+comboFilter.getIdField() + " = '" +comboFilter.getId()+"'");//'"+employee.getId()+"'"
				cSql = " and  "+comboFilter.getIdField() + " <> '" +comboFilter.getId() + "'";//'"+employee.getId()+"'"
			}	
			
			//if(comboFilter.getCondSql() != null )
			//	s.append(comboFilter.getCondSql());
			
			//sql.insert(0, s + " UNION ");
			//sql.append(" UNION " + s + " ) where 1 = 1 ");
			//sql.insert(0, " SELECT * from ( ");
			//CommonMessage.debugMsg("  sql ------ " + sql );
			s.append(" UNION ");
			sql.append(s);
			
			
		}
		selectSql.append( tbl);
		sql.append( selectSql );
		
		if(UIUtils.isValidKeyId(likeSql))
			sql.append( " and " + likeSql);
		if(comboFilter.getCondSql() != null )
			sql.append(comboFilter.getCondSql());
		
		if( isCombo )
			sql.append(cSql);		
		sql.append(" order by ");
		
		if( isCombo )
			sql.append(" r, ");
		
		if( comboFilter.getOrderByField() != null && comboFilter.getOrderByField().trim().length() > 0 )
			sql.append( comboFilter.getOrderByField() );
		else
			sql.append(" text ");
		
		//ResultSet rs = null; 
		//Connection connection = null;
		
		//CommonMessage.debugMsg(" sql111 " + sql);
		
		CommonMessage.debugMsg("Like Sql:"+likeSql);
		
		try{
			/*rs = dbActionTemplate.getData(sql.toString()) ;
			connection = rs.getStatement().getConnection();
			
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));
				
				comboList.add(compComb);
			}*/
			//return comboList;
			
			//CommonMessage.debugMsg("SQL Combo Data"+sql.toString());
			return exceuteComboQuery(sql.toString(), null, comboFilter);
		}finally{
			
			
			//DBActionTemplate.closeConnection(rs,null,null,null,connection);
			
		}
	}
	
	public List<String[]> getMachineHierarchy(String eqpID) {
		// TODO Auto-generated method stub
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(eqpID);
			
			String sql = CommonFilterSqls.getMachineHirerachysql();
			//String sql = CommonFilterSqls.getHierarchysql();
			List<String []> equipmentrefill = dbActionTemplate.processFunctionCalls(sql,paramValues);
			return equipmentrefill;
			//commonFilter.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
			//return  commonFilter;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return null;
	}
	
	
	public List<String[]> getCostCenterRelCell(String costCenterId) {
		// TODO Auto-generated method stub
		try
		{
			String sql = " SELECT CELL_FACTORYID, CELL_SECTIONID, CELL_KEYID FROM GEN_TL_CELLMST WHERE CELL_COSTCENTREID = '" + costCenterId + "' "; 
			List<String []> costcenterrefill = dbActionTemplate.getDataList(sql);
			return costcenterrefill;
			//commonFilter.setSaveArray( dbActionTemplate.getDataArr(sql,args ) );
			//return  commonFilter;
		}
		catch(Exception e)	{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<String[]> getCellHierarchy(String cellID) {
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(cellID);			
			String sql = CommonFilterSqls.getCellHirerachysql();
			//String sql = CommonFilterSqls.getHierarchysql();
			//CommonMessage.debugMsg("sql " + sql + " cell  " + cellID );
			List<String []> cellrefill = dbActionTemplate.processFunctionCalls(sql,paramValues);	
			return cellrefill;			
		}catch(Exception e)	{
			e.printStackTrace();			
		}
		return null;
	}

	@Override
	public List<String[]> getSectionHierarchy(String SectionId) {
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(SectionId);			
			String sql = CommonFilterSqls.getSectionHirerachysql();
			//String sql = CommonFilterSqls.getHierarchysql();
			//CommonMessage.debugMsg(" sec sql " + sql + " " + SectionId);
			List<String []> cellrefill = dbActionTemplate.getDataList(sql, paramValues);			
			return cellrefill;			
		}catch(Exception e)	{
			e.printStackTrace();			
		}
		return null;
	}
	public List<String[]> getSubUnitHierarchy(String subUnitId)
	{
		try
		{
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(subUnitId);			
			String sql = CommonFilterSqls.getSubUnitHirerachysql();		
			//String sql = CommonFilterSqls.getHierarchysql();
			//CommonMessage.debugMsg(" sec sql " + sql + " " + subUnitId);
			List<String []> cellrefill = dbActionTemplate.getDataList(sql, paramValues);			
			return cellrefill;			
		}catch(Exception e)	{
			e.printStackTrace();			
		}
		return null;
	}
	public List<String[]> getfactoryHierarchy(String fctId) {
		try
		{	
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(fctId);			
			String sql = CommonFilterSqls.getfactoryHierarchysql();
			//String sql = CommonFilterSqls.getHierarchysql();
			List<String []> fctrefill = dbActionTemplate.getDataList(sql,paramValues);	
			return fctrefill;			
		}catch(Exception e)	{
			e.printStackTrace();			
		}
		return null;
	}
	public List<String[]> getSbuHierarchy(String sbuId) {
		try
		{	
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(sbuId);			
			String sql = CommonFilterSqls.getfactoryHierarchysql();
			//String sql = CommonFilterSqls.getHierarchysql();
			List<String []> fctrefill = dbActionTemplate.getDataList(sql,paramValues);	
			return fctrefill;			
		}catch(Exception e)	{
			e.printStackTrace();			
		}
		return null;
	}
	public List<String[]> getlocationHierarchy(String lcnId) {
		try
		{	
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(lcnId);			
			String sql = CommonFilterSqls.getlocationHierarchysql();
		//	String sql = CommonFilterSqls.getHierarchysql();
			List<String []> lcnrefill = dbActionTemplate.getDataList(sql,paramValues);	
			return lcnrefill;			
		}catch(Exception e)	{
			e.printStackTrace();			
		}
		return null;
	}

	@Override
	public List<String[]> getCityHierarchy(String cityId) {
		
		try
		{	
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(cityId);			
			String sql = CommonFilterSqls.getCityHierarchySql();
			List<String []> lcnrefill = dbActionTemplate.getDataList(sql,paramValues);	
			return lcnrefill;			
		}catch(Exception e)	{
			e.printStackTrace();			
		}
		return null;
		
		
	}

	@Override
	public String getDesigId(String empId) throws Exception {
		// TODO Auto-generated method stubCommonFilterSqls
		String sqls =CommonFilterSqls.getDesgIDsql(empId);
		return dbActionTemplate.getSingleValue(sqls);
	}

	@Override
	public String getspokeid(String progkeyId) throws Exception {
		// TODO Auto-generated method stub
		String sqls =CommonFilterSqls.getspokeIDsql(progkeyId);
		return dbActionTemplate.getSingleValue(sqls);
	}


	public List<ComboBox> getTeamComboList(String funLocCndSql, String empCndSql) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		
		CommonMessage.debugMsg("funLocCndSql : "+funLocCndSql);
		
		sql.append(" SELECT DISTINCT ID, TEXT FROM (");
		
		sql.append(" SELECT '' AS ID, '' AS CODE, '' AS TEXT FROM DUAL WHERE 1 = 2 ");
		
		if (UIUtils.isValidKeyId(funLocCndSql)) {
			sql.append(" UNION ALL " );
			sql.append(" SELECT TMHI_KEYID, TMHI_CODE, TMHI_NAME " );
			sql.append(" FROM GEN_TL_TEAM_FUNCLOC_MAP, GEN_TL_TEAM_HIERARCHY " );
			sql.append(" WHERE 1 = 1 " );		
				//sql.append(funLocCndSql);		
			sql.append(" AND TMFM_TMHI_KEYID = TMHI_KEYID " );
		}
		
		if (UIUtils.isValidKeyId(empCndSql)) {		
			sql.append(" UNION ALL " );
			sql.append(" SELECT DISTINCT TMHI_KEYID, TMHI_CODE, TMHI_NAME " ); 
			sql.append(" FROM GEN_TL_TEAM_EMP_LINK , GEN_TL_TEAM_HIERARCHY " );
			sql.append(" WHERE 1 =1 " );			
			//sql.append(empCndSql);
			sql.append(" AND TMEL_TMHI_KEYID = TMHI_KEYID  " );
		}
		
		
		sql.append(" ) " );
				
		ResultSet rs = null; 
		Connection connection = null;
		
		//CommonMessage.debugMsg(" sql111 " + sql);
		try{
			rs = dbActionTemplate.getData(sql.toString()) ;
			connection = rs.getStatement().getConnection();
			
			List<ComboBox> comboList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));				
				comboList.add(compComb);
			}
			return comboList;
		}finally{			
			DBActionTemplate.closeConnection(rs,null,null,null,connection);			
		}					
	}
	@Override
	public String getFuncLocnHierarchy(String keyid) throws Exception {
		try
		{	
			List<String > paramValues = new ArrayList<String>();
			paramValues.add(keyid);			
			//String sql = CommonFilterSqls.getlocationHierarchysql();
			String sql = CommonFilterSqls.getHierarchysql(keyid);
			return dbActionTemplate.getSingleValue(sql);
					
		}catch(Exception e)	{
			e.printStackTrace();			
		}
		return null;
	}
	@Override
	public String getElementID(String originalId) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select fnln_elementid from gen_tl_functionallocn where fnln_keyid = '"+originalId+"'";
		CommonMessage.debugMsg(sql);
		String getElementID = dbActionTemplate.getSingleValue(sql);
	
		return getElementID;
	}
	@Override
	public List<String[]> getempEqpData(String userID) throws Exception {
		// TODO Auto-generated method stub
		try
		{
			//CommonMessage.debugMsg("userID  :"+userID);
			String sql = "select MCHM_KEYID,'','',MCHM_MACHINENO ||'-'||  MCHM_MACHINENAME from gen_tl_machinemst,GEN_TL_EMPFUNCLOCNLINK  "+
                         " where EFLL_FUNCLOCN = MCHM_KEYID AND EFLL_FUNCLOCNTYPE = 'MCHM'  AND  EFLL_EMPLOYEEID  = '"+userID+"'";
			CommonMessage.debugMsg(sql);
			List<String[]> empEquipmentList = dbActionTemplate.getDataList(sql);
			
			return empEquipmentList;
		
		}
		catch (Exception e)
		{
			e.getMessage(); 
		}
		return null;
	}
/*Added By Manikandan For New ComboBox*/
	private List<ComboBox> exceuteComboQuery(String sql, Object[]condArgs,ComboFilter comboFilter  ) throws Exception
	{
		String mode = comboFilter.getMode();
		StringBuilder eSql = new StringBuilder();
		if("grid".equals(mode)){
			StringBuilder exectueSql = new StringBuilder();
			exectueSql.append("SELECT * FROM (");
			exectueSql.append(sql);
			exectueSql.append(") WHERE 1=1 ").append(FilterCondSql.makeGridFilterCond(comboFilter.getGridparam().getGridFilters()));
			StringBuilder sqlCount = new StringBuilder("select count(*) from (");
			sqlCount.append( exectueSql );
			sqlCount.append(" )");
			String totalRecords = "";
			totalRecords = dbActionTemplate.getSingleValue(sqlCount.toString());
			comboFilter.getGridparam().setTotalRecordCnt(Integer.parseInt(totalRecords));

			eSql.append("SELECT * FROM (SELECT ROWNUM AS SLNO,A.* FROM (");
			eSql.append( exectueSql);
			eSql.append(" )A )WHERE SLNO BETWEEN  ");
			eSql.append(comboFilter.getGridparam().getFromRow());
			eSql.append(" AND ");
			eSql.append(comboFilter.getGridparam().getToRow());
			//CommonMessage.debugMsg("ifExectueCombo");
	     }
		 else{
			 eSql.append( " select * from ( ");
			 eSql.append( sql );
			 eSql.append(") ");
			 //where rownum <= ").append(comboFetchCount) ;
			
			
		 }
			CommonMessage.debugMsg("exectueSql    :: "+eSql );
			//if( UIUtils.isValidKeyId(comboFilter.getNewSelectQuery()))
			
			 return getComboGriddata(eSql.toString(),condArgs,mode);
		//	 commnented now		return null;
			/*else
				return getComboValues(exectueSql,condArgs);*/
		 
	}
	@Override
	public List<String[]> getEmpData(String userKeyid) throws Exception {
		// TODO Auto-generated method stub
		/*String sql ="select empm_name ,empm_code,EMPM_TEMPFIELD4 as pillar from gen_tl_employeemst where empm_keyid = '"+userKeyid+"'";*/
		String sql ="select empm_name ,empm_code,'' as pillar from gen_tl_employeemst where empm_keyid = '"+userKeyid+"'";
		List<String[]> getEmployeeData = dbActionTemplate.getDataList(sql);
		return getEmployeeData;
	}
	public List<String[]> getMenu(String pillarid,String userKeyid) throws Exception {
		// TODO Auto-generated method stub
		String IsLeaf = "\"IsLeaf\"";
		String menuSql ="select actm_menurl,actm_menuname ,actm_menuno from ADM_TL_ACTIONTABLEMENU where actm_pillar ='"+ pillarid+"'";
				//="select  MNUM_LOADFORMARGUMENT,MNUM_SIMILARCOLUMN,MNUM_TABLENAME,MNUM_MENUCAPTION,MNUM_ISPARENT,MNUM_MENUNAME from adm_vw_menutreesearch where usrt_userid ='"+userKeyid+"' and  "+IsLeaf+"='1'";
				//"SELECT MNUM_LOADFORMARGUMENT,MNUM_SIMILARCOLUMN,MNUM_TABLENAME,MNUM_MENUCAPTION,MNUM_ISPARENT,MNUM_MENUNAME FROM ADM_TL_MENUMST WHERE MNUM_Active = 'Y'";
				//"SELECT MNUM_LOADFORMARGUMENT,MNUM_SIMILARCOLUMN,MNUM_TABLENAME,MNUM_MENUCAPTION,MNUM_ISPARENT,MNUM_MENUNAME FROM ADM_TL_MENUMST WHERE MNUM_PILLAR = '"+pillarid+"'";
		CommonMessage.debugMsg("menuSql  :"+menuSql);
		List<String[]> getPillarRelMenu = dbActionTemplate.getDataList(menuSql);
		return getPillarRelMenu;
	}
	@Override
	public List<String[]> getPcsLoss(String string) throws Exception {
			// TODO Auto-generated method stub
			StringBuffer menuSql  = new StringBuffer();
			menuSql.append(" SELECT * FROM ( ");
			menuSql.append(" SELECT 'From','To','Mins','Loss Ref.No','Loss Reason','Loss Description','Trade','Prodn.Impact(Y/N)','Prodn.Impacted Qty(Other then Downtime)','Why-Why','Why-Why Ref No','Kaizen Ideas','Kaizen Ref No', 0 AS DATAORDER FROM DUAL ");
			menuSql.append(" UNION SELECT '02:00','03:00','01:00','','','Description1','','Y','','','BDM13100013, BDM13100015','','KZ1300000023, KZ1300000025', 1 AS DATAORDER FROM DUAL ");
			menuSql.append(" UNION SELECT '05:00','08:00','03:00','','','Description2','','N','','','BDM13100014','','KZ1300000022, KZ1300000026', 2 AS DATAORDER FROM DUAL ");
			menuSql.append(" UNION SELECT '12:00','18:00','06:00','','','Description3','','N','','','BDM13100016, BDM13100018','','KZ1300000024, KZ1300000028, KZ1300000032', 3 AS DATAORDER FROM DUAL ");
			menuSql.append(" UNION SELECT '15:00','20:00','05:00','','','Description4','','Y','','','BDM13100017','','KZ1300000027, KZ1300000029', 4 AS DATAORDER FROM DUAL ");
			menuSql.append(" UNION SELECT '04:00','06:00','02:00','','','Description5','','N','','','BDM13100018, BDM13100020','','KZ1300000025, KZ1300000027', 5 AS DATAORDER FROM DUAL ");
			menuSql.append(" ) ORDER BY DATAORDER ");
			CommonMessage.debugMsg("LossSql  :"+menuSql.toString());
			List<String[]> getPillarRelMenu = dbActionTemplate.getDataList(menuSql.toString());
			return getPillarRelMenu;
		}
	
	@Override
	public List<GenTlAllmoduleimgfile> saveImg(List<GenTlAllmoduleimgfile> allmoduleimgfiles) throws Exception {
		try
		{
			List<String> sqls = new ArrayList<String>();
			List<Object[]> valueList  = new ArrayList<Object[]>();
			List<int[]> dataTypes  = new ArrayList<int[]>();
			//List<GenTlAllmoduleimgfile> newGenTlAllmoduleimgfile = allmoduleimgfiles.getAllmoduleimgfile();
			CommonMessage.debugMsg(allmoduleimgfiles.get(0).getImflBlobimage() + " daoimpl");
			for(GenTlAllmoduleimgfile genTlAllmoduleimgfile:allmoduleimgfiles)
			{
				
				sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());
				Object [] delValue	= { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype() };
				int [] delTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				valueList.add(delValue);
				dataTypes.add(delTypes);
				CommonMessage.debugMsg("Object....:"+genTlAllmoduleimgfile.getImflFilename());
				if(!(UIUtils.isValidKeyId(genTlAllmoduleimgfile.getImflFilename())))
					continue;
				CommonMessage.debugMsg("Object.. After..:"+genTlAllmoduleimgfile.getImflFilename());
				//sqls.add(GenTlAllmoduleimgfileSql.getDeleteSql());
				sqls.add(GenTlAllmoduleimgfileSql.getInsertSql());
				
				/*Object [] delValue	= { genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),genTlAllmoduleimgfile.getImflImagetype() };
				
				int [] delTypes =  { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				*/
				java.sql.Timestamp  timeStamp = com.akranta.tpm.dao.impl.CommonFunctions.convertoSqlTimeStamp(genTlAllmoduleimgfile.getImflModifiedon()); 
				
				Object [] insValues = {
						genTlAllmoduleimgfile.getImflRefkeyid(),genTlAllmoduleimgfile.getImflRefdoctype(),
						genTlAllmoduleimgfile.getImflImagetype(),genTlAllmoduleimgfile.getImflBlobimage(),
						genTlAllmoduleimgfile.getImflBloblength(),genTlAllmoduleimgfile.getImflFilename(),
						genTlAllmoduleimgfile.getImflTempfield1(),genTlAllmoduleimgfile.getImflTempfield2(),timeStamp};
				//CommonMessage.debugMsg("Object....:"+insValues.length);
				int [] insDataType = { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR, Types.BLOB,Types.INTEGER ,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.TIMESTAMP};
				//valueList.add(delValue);
				valueList.add(insValues);
				//dataTypes.add(delTypes);
				dataTypes.add(insDataType);
			}
			dbActionTemplate.saveByteFile(sqls, valueList, dataTypes);
		}
		
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return null;
	}
	@Override
	public List<String[]> getPilarWiseEmployee(String pillar) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer empSql  = new StringBuffer();
		empSql.append("SELECT  DISTINCT EMPM_KEYID,'' as checkbox,'' as hdncheck,EMPM_NAME  ");
		empSql.append("  FROM gen_tl_employeemst,GEN_TL_EMPFUNCLOCNLINK,GEN_TL_MACHINEMST,GEN_TL_CELLMST ");
		empSql.append("  WHERE  EFLL_EMPLOYEEID = EMPM_KEYID AND MCHM_CELLID = CELL_KEYID(+) AND EFLL_FUNCLOCN = MCHM_KEYID  AND CELL_KEYID = 'CEL0000062'");
		
		CommonMessage.debugMsg("employeeSql  :"+empSql.toString());
		List<String[]> getPillarEmployee = dbActionTemplate.getDataList(empSql.toString());
		return getPillarEmployee;
	}
	public String getEmployeeLocation(String userKeyid)throws Exception{
		String LocationId=dbActionTemplate.getSingleValue("GEN_TL_EMPLOYEEMST", "EMPM_LOCATION", "EMPM_KEYID",userKeyid);
        CommonMessage.debugMsg("The LocationId is:::"+LocationId);
        return LocationId;
	}
}	
 
	
