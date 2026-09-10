package com.akranta.tpm.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.dao.BAL_CompanyDao;
import com.akranta.tpm.dao.sql.CommonFilterSqls;
import com.akranta.tpm.dao.sql.BAL_CompanySql;
import com.akranta.tpm.model.ComboBox;
import com.akranta.tpm.model.ComboFilter;
import com.akranta.tpm.model.BAL_Company;
import com.akranta.tpm.utils.CommonFunctions;


public class BAL_CompanyDaoImpl implements BAL_CompanyDao {
	
	private static String TBL_COMPANYMST = BAL_CompanySql.TBL_GEN_TL_COMPANYMST;
	
	private DBActionTemplate dbActionTemplate; 
	
	public BAL_CompanyDaoImpl(DBActionTemplate dbActionTemplate) 
	{
		this.dbActionTemplate = dbActionTemplate;
	}
	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	
	public BAL_Company create(BAL_Company company) throws Exception
	{
		try{
			String sql = BAL_CompanySql.getInsertSqlForCOMP();
			
			int [] dataTypes = BAL_CompanySql.comp_datatypes; 
			System.out.println(" dataTypes " + dataTypes[ 0 ]);
			
			//company.setKeyid(dbActionTemplate.getSequenceNumber(CompanySql.TBL_GEN_TL_COMPANYMST));
			Object [] values = new Object [] { company.getKeyid(), company.getName(),
						company.getCode(),company.getAddress(),  company.getActive(),  company.getCreatedby(),
						company.getCreatedon(), company.getModifiedon() };
			
			dbActionTemplate.executeStatement(sql, values, dataTypes);
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return company;
	}
	
	public BAL_Company update(BAL_Company company) throws Exception
	{
			
		try{
			String sql = BAL_CompanySql.getUpdateSqlForCOMP();
		
			//System.out.println(" date  " + CommonFunctions.now());	
			//company.setCreatedon(createdon)
			int [] dataTypes =  new int[ BAL_CompanySql.comp_datatypes.length ];
			int i = 0;
			for(i =1 ; i < BAL_CompanySql.comp_datatypes.length; i++)
			{
				dataTypes[ i - 1 ] = BAL_CompanySql.comp_datatypes[ i ];
			}
			
			dataTypes[ i - 1 ] =  BAL_CompanySql.comp_datatypes[ 0 ];
			Object  values [] = new Object [] { company.getName(),company.getCode(),company.getAddress(), company.getActive(),  company.getCreatedby(),company.getCreatedon(), company.getModifiedon(),company.getKeyid() };
			dbActionTemplate.executeStatement(sql, values, dataTypes);
			
		}catch(Exception e)
		{
			System.out.println(" err.u " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		
		return company;
	}
			
	public BAL_Company delete(BAL_Company company) throws Exception
	{
		try{
			String sql = BAL_CompanySql.getDeleteSqlForCOMP();
			
			int [] dataTypes =  new int[ 1 ];
			dataTypes[ 0 ] = BAL_CompanySql.comp_datatypes[ 0 ];

			Object [] values = new Object [] { company.getKeyid() };
			
			dbActionTemplate.executeStatement(sql, values, dataTypes);
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		
		return company;

	}
	
	public List<ComboBox> getCompanyComboList(ComboFilter comboFilter) throws Exception
	{
		ResultSet rs =null;
		Connection connection = null;
		try{
			String sql = CommonFilterSqls.getCompanyComboSql();
			String param = "%"+ (comboFilter.getCode() != null?comboFilter.getCode():"") + "-" + (comboFilter.getName()!=null?comboFilter.getName():"") +"%" ;
			
			Object args [] = new Object [] { param };
	
			rs = dbActionTemplate.getData(sql, args) ;
			connection = rs.getStatement().getConnection();
			List<ComboBox> companyList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));
				
				companyList.add(compComb);
			}
			
			DBActionTemplate.closeConnection(rs,null,null,null,connection);
			return companyList;
		}catch(Exception e)
		{
			
			DBActionTemplate.closeConnection(rs,null,null,null,connection);
			throw new Exception(e.getMessage());
		}finally{
			
			DBActionTemplate.closeConnection(rs,null,null,null,connection);
		}
	}
	public BAL_Company getComapany(String keyId) throws Exception
	{
		ResultSet rs =null;
		Connection connection =null;
		try{
			String sql = BAL_CompanySql.getSelectSqlForComp();
			
			Object args [] = new Object [] { keyId };
			
			rs = dbActionTemplate.getData(sql, args) ;
			connection = rs.getStatement().getConnection();
			if( rs != null)
			{	
				rs.next();
				BAL_Company company = new BAL_Company();
				
				company.setKeyid(rs.getString("comp_keyid"));
				company.setName(rs.getString("comp_name"));
				company.setCode(rs.getString("comp_code"));
				company.setAddress(rs.getString("comp_address"));
				company.setActive(rs.getString("comp_active"));
				company.setCreatedby(rs.getString("comp_createdby"));
				company.setCreatedon(rs.getDate("comp_createdon"));
				company.setModifiedon(rs.getDate("comp_modifiedon"));
				

				return company;
			}
			throw new Exception(" No data found ");
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}finally{
			DBActionTemplate.closeConnection(rs,null,null,null,connection);
		}
	}
	
	
/*	public Company create(Company company) throws Exception
	{
		StringBuffer sql = new StringBuffer();
	
		try{
		
			DBExecute dbExecute = new DBExecute();
			dbExecute.openConn();
			company.setKeyid(dbExecute.getSequenceNumber(TBL_COMPANYMST));
			
			sql.append("insert into " + TBL_COMPANYMST + " (comp_keyid,comp_name, comp_code,");
			sql.append("comp_address,comp_active,comp_createdby,comp_createdon,comp_modifiedon) values( '");
			
			sql.append(company.getKeyid());
			sql.append("','" + company.getName() );
			sql.append("','" + company.getCode() );
			sql.append("','" + company.getAddress() );
			sql.append("','" + company.getActive() );
			sql.append("','" + company.getCreatedby());
			sql.append("',sysdate");
			sql.append(",sysdate)");
			
			System.out.println(" sql "+ sql);
			dbExecute.executeStatement(sql.toString());
			
			dbExecute.closeConn();
			
			return company;
			
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}

	public Company update(Company company) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		
		try{
		
			sql.append("update " + TBL_COMPANYMST  + " set ");
			sql.append(" comp_name = '" + company.getName() +"',");
			sql.append(" comp_code = '" + company.getCode() +"',");
			sql.append(" comp_address = '" + company.getAddress() +"',");
			sql.append(" comp_active = '" + company.getActive() +"',");
			sql.append(" comp_modifiedon = '" + company.getModifiedon() +"'");

			DBExecute dbExecute = new DBExecute();
			dbExecute.openConn();

			dbExecute.executeStatement(sql.toString());
			
			dbExecute.closeConn();
			
			return company;
			
		}catch(Exception e){
			throw new Exception(e.getMessage());
		}
	}
	
	public Company delete(Company company) throws Exception
	{
		
		try{
			StringBuffer sql = new StringBuffer();

			sql.append("delete from " + TBL_COMPANYMST + " where ");
			sql.append(" comp_keyid = '" + company.getKeyid() +"'");

			DBExecute dbExecute = new DBExecute();
			dbExecute.openConn();

			dbExecute.executeStatement(sql.toString());
			
			dbExecute.closeConn();
			
			return company;
			
		}catch(Exception e){
			
			throw new Exception(e.getMessage());
		}
	}
	
	public Company getComapany(String keyId) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		DBExecute dbExecute = new DBExecute();
		try{
			sql.append(" select * from " + TBL_COMPANYMST);
			sql.append(" where comp_keyid = '" + keyId + "'");
			
			System.out.println("sql " + sql);
			dbExecute.openConn();
			ResultSet rs = dbExecute.getData(sql.toString());
			
			if( rs != null)
			{	
				rs.next();
				Company company = new Company();
				
				company.setKeyid(rs.getString("comp_keyid"));
				company.setName(rs.getString("comp_name"));
				company.setCode(rs.getString("comp_code"));
				company.setAddress(rs.getString("comp_address"));
				company.setActive(rs.getString("comp_active"));
				company.setCreatedby(rs.getString("comp_createdby"));
				company.setCreatedon(rs.getDate("comp_createdon"));
				company.setModifiedon(rs.getDate("comp_modifiedon"));
				
				dbExecute.closeConn();
				return company;
			}
			dbExecute.closeConn();
			throw new Exception(" No data found ");
			
		}catch(Exception e)
		{
			dbExecute.closeConn();
			throw new Exception(e.getMessage());
		}
	}
	
	
*/	
/*	public List<ComboBox> getCompanyComboList(ComboFilter comboFilter) throws Exception
	{
		StringBuffer sql = new StringBuffer();
		DBExecute dbExecute = new DBExecute();
		
		
		try{
			String relation = null;
			sql.append(" select comp_keyid id,comp_code ||' - '|| comp_name as text from " + TBL_COMPANYMST + " where 1= 1 ");
			
			if( comboFilter.getCode() != null )
			{
				
				sql.append(" and ( comp_code like '%" + comboFilter.getCode() + "%' ");
				relation = " or ";
			}
			
			if( comboFilter.getName() != null )
			{	
				relation = relation != null ? relation:" and ( ";
				sql.append( relation + " comp_name like '%" + comboFilter.getName() + "%' ");
			}
			if( relation != null ) 
				sql.append(")");
			System.out.println(" sql " + sql);
			dbExecute.openConn();
			ResultSet rs = dbExecute.getData(sql.toString());
			
		/*	HashMap<String, String> companyList = new HashMap<String, String>();
			while(rs.next())
			{	
				companyList.put(rs.getString("id"), rs.getString("text"));
			}
		*/
	/*		List<ComboBox> companyList = new ArrayList<ComboBox>();
			while(rs.next())
			{	
				ComboBox compComb = new ComboBox();
				compComb.setId(rs.getString("id"));
				compComb.setText(rs.getString("text"));
				
				companyList.add(compComb);
			}
			
			dbExecute.closeConn();
			return companyList;
			
		}catch(Exception e)
		{
			dbExecute.closeConn();
			throw new Exception(e.getMessage());
		}
		
	}
	*/
}
