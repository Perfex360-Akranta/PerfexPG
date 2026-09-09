package com.akranta.tpm.dao.impl;




import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.KpiTlIndicatorDao;
import com.akranta.tpm.dao.sql.KpiTlIndicatorKkSql;
import com.akranta.tpm.dao.sql.KpiTlIndicatorSql;
import com.akranta.tpm.dao.sql.TableNames;
import com.akranta.tpm.model.KpiTlIndicator;
import com.akranta.tpm.model.KpiTlIndicatorKk;
import com.akranta.tpm.service.api.FunctionCallApi;
import com.akranta.tpm.service.api.KpiTlActualandIndicatorServiceApi;
import com.akranta.tpm.utils.CommonFunctions;import com.akranta.tpm.utils.CommonMessage ;

/* dao implementation */
public class KpiTlIndicatorDaoImpl implements KpiTlIndicatorDao {


	private KpiTlActualandIndicatorServiceApi kpiTlActualandIndicatorServiceApi;
	FunctionCallApi fnCallApi;
	
	private DBActionTemplate dbActionTemplate; 

	public KpiTlIndicatorDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
     public void KpiTlIndicatorDaoImplJwt(String jwtToken) {
		
		try{
			kpiTlActualandIndicatorServiceApi = new KpiTlActualandIndicatorServiceApi(jwtToken);
		fnCallApi = new FunctionCallApi(jwtToken);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public KpiTlIndicator create(KpiTlIndicator kpiTlIndicator) 	throws Exception {

		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		KpiTlIndicatorSql kpiTlIndicatorSql = new KpiTlIndicatorSql();
		try{
			/*
			kpiTlIndicator.setKinkIndicatorname(kpiTlIndicator.getKinkDescription());
			kpiTlIndicator.setKinkKeyid(dbActionTemplate.getSequenceNumber(KpiTlIndicatorSql.TBL_KPI_TL_INDICATOR)); // set the sequnce number
			if(!UIUtils.isValidKeyId(kpiTlIndicator.getKinkParentid()) || "1".equals(kpiTlIndicator.getKinkParentid()))
				kpiTlIndicator.setKinkParentid(kpiTlIndicator.getKinkKeyid());
			
			sqls.add(KpiTlIndicatorSql.getInsertSql(kpiTlIndicatorSql.getKinkDbFields(), kpiTlIndicator.getSaveArray())); // add insert sql for master table			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			*/
			kpiTlIndicator.setKinkIndicatorname(kpiTlIndicator.getKinkDescription());
			kpiTlIndicator.setKinkKeyid(dbActionTemplate.getSequenceNumber(TableNames.TBL_KPI_TL_INDICATOR, 10, "KIN", null, null));
			CommonMessage.debugMsg("create daoimpl"+kpiTlIndicator.getKinkKeyid());
			
			if(!UIUtils.isValidKeyId(kpiTlIndicator.getKinkParentid()) || "1".equals(kpiTlIndicator.getKinkParentid())){
				kpiTlIndicator.setKinkParentid(kpiTlIndicator.getKinkKeyid());				
			}
			else{
				sqls.add("update " + TableNames.TBL_KPI_TL_INDICATOR + " set KINK_ISCHILD='N' where KINK_KEYID='" + kpiTlIndicator.getKinkParentid() +  "' " );
				if (kpiTlIndicator.getKinkTargetneed().equals("Y")){
					List<String[]>  searchList = getSearchNode("",kpiTlIndicator.getKinkParentid());
					
					if( searchList.size()>0 ){
						String parentId=searchList.get(0)[0];
						//CommonMessage.debugMsg("parentId:"+parentId);
						parentId=parentId.substring(1, parentId.length());
						//CommonMessage.debugMsg("parentId:"+parentId);
						String[] searchNode = parentId.split("/");
						//CommonMessage.debugMsg("searchNode.length:"+searchNode.length);
						String Sql="";
						for(int i=0;i<searchNode.length;i++){
							String keyid = searchNode[i];
							//CommonMessage.debugMsg("keyid:"+keyid);
							Sql="update " + TableNames.TBL_KPI_TL_INDICATOR + " set KINK_TARGETNEED='Y' where KINK_KEYID='" + keyid +  "'";
							//CommonMessage.debugMsg("update Sql:"+Sql);
							sqls.add(Sql);
						}
					}					
				}								
			}	
			kpiTlIndicator=getLevelSortNo(kpiTlIndicator);
			sqls.add(kpiTlIndicatorSql.getInsertSql(kpiTlIndicatorSql.getKinkDbFields(), kpiTlIndicator.getSaveArray()));
			dbActionTemplate.executeStatements(sqls); 	
			
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return kpiTlIndicator;
	}
	private KpiTlIndicator getLevelSortNo(KpiTlIndicator kpiTlIndicator) throws Exception {
		String SortNo="";
		try{
			if(kpiTlIndicator.getKinkKeyid().equals(kpiTlIndicator.getKinkParentid())){
				if (!"1".equals(kpiTlIndicator.getKinkLevelno())){
					kpiTlIndicator.setKinkLevelno("1");
				}
				if (kpiTlIndicator.getKinkSortno().indexOf(".")>0){
					SortNo=getSortNo(kpiTlIndicator);
					kpiTlIndicator.setKinkSortno(SortNo);			
				}
			}
			else{	
				//CommonMessage.debugMsg("ELSE");
				SortNo=getParentSortNo(kpiTlIndicator);
				if ("1".equals(kpiTlIndicator.getKinkLevelno())){
					kpiTlIndicator.setKinkLevelno(String.valueOf(Integer.parseInt(kpiTlIndicator.getKinkLevelno())+1));
				}
				if (kpiTlIndicator.getKinkSortno().indexOf(SortNo+".")<=0){
					SortNo=getSortNo(kpiTlIndicator);
					kpiTlIndicator.setKinkSortno(SortNo);
				}
			}
			CommonMessage.debugMsg("LevelNo:"+kpiTlIndicator.getKinkLevelno());
			CommonMessage.debugMsg("SortNo:"+kpiTlIndicator.getKinkSortno());
			return kpiTlIndicator;
		}catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
	}
	
	private  List<String[]> getSearchNode(String searchNode,String originalId) throws Exception
	{
		try
		{		
			String sql = null;
			sql=KpiTlIndicatorKkSql.getSearchNodeSql(searchNode,originalId);
			CommonMessage.debugMsg("Search SQL : "+sql);
			return dbActionTemplate.getDataList(sql);
		}
		catch (Exception e)
		{
			throw new Exception(e.getMessage()); 
		}
	}	
	
	public KpiTlIndicator update(KpiTlIndicator kpiTlIndicator)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		KpiTlIndicatorSql kpiTlIndicatorSql = new KpiTlIndicatorSql();
		String SortNo="";
		try {
			/*kpiTlIndicator.setKinkIndicatorname(kpiTlIndicator.getKinkDescription());
			sqls.add(KpiTlIndicatorSql.getUpdateSql(kpiTlIndicatorSql.getKinkDbFields(), kpiTlIndicator.getSaveArray()));
			CommonMessage.debugMsg("SQL ......"+sqls.toString());
			dbActionTemplate.executeStatements(sqls);*/
			if (kpiTlIndicator.getKinkTargetneed().equals("Y")){
				List<String[]>  searchList = getSearchNode("",kpiTlIndicator.getKinkParentid());
				
				if( searchList.size()>0 ){
					String parentId=searchList.get(0)[0];
					//CommonMessage.debugMsg("parentId:"+parentId);
					parentId=parentId.substring(1, parentId.length());
					//CommonMessage.debugMsg("parentId:"+parentId);
					String[] searchNode = parentId.split("/");
					//CommonMessage.debugMsg("searchNode.length:"+searchNode.length);
					String Sql="";
					for(int i=0;i<searchNode.length;i++){
						String keyid = searchNode[i];
						//CommonMessage.debugMsg("keyid:"+keyid);
						Sql="update " + TableNames.TBL_KPI_TL_INDICATOR + " set KINK_TARGETNEED='Y' where KINK_KEYID='" + keyid +  "'";
						//CommonMessage.debugMsg("update Sql:"+Sql);
						sqls.add(Sql);
					}
				}					
			}	
			kpiTlIndicator=getLevelSortNo(kpiTlIndicator);
			kpiTlIndicator.setKinkIndicatorname(kpiTlIndicator.getKinkDescription());
			sqls.add(kpiTlIndicatorSql.getUpdateSql(kpiTlIndicatorSql.getKinkDbFields(), kpiTlIndicator.getSaveArray()));			
			dbActionTemplate.executeStatements(sqls);		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return kpiTlIndicator;
	}
	
	public KpiTlIndicator delete(KpiTlIndicator kpiTlIndicator)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		KpiTlIndicatorSql kpiTlIndicatorSql = new KpiTlIndicatorSql();
		try {
			
			sqls.add(kpiTlIndicatorSql.getDeleteSql(kpiTlIndicatorSql.getKinkDbFields(), kpiTlIndicator.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return kpiTlIndicator;
	}

	@Override
	public String getSortNo(KpiTlIndicator kpiTlIndicatorKk) throws Exception {
		// TODO Auto-generated method stub
		KpiTlIndicatorSql kpiTlIndicatorSql = new KpiTlIndicatorSql();
		String sql = kpiTlIndicatorSql.getSortNo(kpiTlIndicatorSql.getKinkDbFields(), kpiTlIndicatorKk.getSaveArray());				
		CommonMessage.debugMsg(" sql: " + sql);
		
		String sortNo = dbActionTemplate.getSingleValue(sql);	
		CommonMessage.debugMsg(" SortNo: " + sortNo);
		return sortNo;
	}
	
	private String getParentSortNo(KpiTlIndicator kpiTlIndicatorKk) throws Exception {
		// TODO Auto-generated method stub
		KpiTlIndicatorSql kpiTlIndicatorSql = new KpiTlIndicatorSql();
		String sql = kpiTlIndicatorSql.getParentSortNo(kpiTlIndicatorSql.getKinkDbFields(), kpiTlIndicatorKk.getSaveArray());				
		CommonMessage.debugMsg(" sql: " + sql);
		
		String sortNo = dbActionTemplate.getSingleValue(sql);	
		CommonMessage.debugMsg(" SortNo: " + sortNo);
		return sortNo;
	}

	@Override
	public String getLocation(String flId) throws Exception {
		// TODO Auto-generated method stub
		KpiTlIndicatorSql kpiTlIndicatorSql = new KpiTlIndicatorSql();
		String sql = kpiTlIndicatorSql.getLocation(flId);				
		CommonMessage.debugMsg(" sql: " + sql);
		
		String location = dbActionTemplate.getSingleValue(sql);	
		CommonMessage.debugMsg(" SortNo: " + location);
		return location;
	}
	
}

