package com.akranta.tpm.dao.impl;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.Exceptions.BusinessApplicationExceptions;
import com.akranta.tpm.Exceptions.ValidationExceptions;
import com.akranta.tpm.bean.BAL_PcsEntryBean;
import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.dao.BAL_PcsTlProductiondataDao;
import com.akranta.tpm.dao.sql.BAL_PcsTlDtlSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlHtDetailsSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlMstSql;
import com.akranta.tpm.dao.sql.BAL_PcsTlProductiondataSql;
import com.akranta.tpm.model.BAL_PcsTlDtl;
import com.akranta.tpm.model.BAL_PcsTlHtDetails;
import com.akranta.tpm.model.BAL_PcsTlHtProductionHistory;
import com.akranta.tpm.model.BAL_PcsTlMst;
import com.akranta.tpm.model.BAL_PcsTlMst;
import com.akranta.tpm.model.BAL_PcsTlProductiondata;
import com.akranta.tpm.service.api.BAL_PcsEntryServiceApi;
import com.akranta.tpm.utils.CommonFunctions;
import com.akranta.tpm.dao.sql.BAL_PcsTlHtHistrorySql;

/* dao implementation */
public class BAL_PcsTlProductiondataDaoImpl implements BAL_PcsTlProductiondataDao {


	private static final BAL_PcsTlProductiondata PcsTlProductiondata = null;
	private DBActionTemplate dbActionTemplate;
	private BAL_PcsTlMstSql pcsTlMstSql = null;
	private BAL_PcsTlDtlSql pcsTlDtlSql = null;
	private BAL_PcsTlHtDetailsSql pcsTlHtDtlSql = null;
	private BAL_PcsTlHtHistrorySql pcsTlHtHistrorySql = null;
	private BAL_PcsEntryServiceApi pcsenrtyserviceApi;

	public BAL_PcsTlProductiondataDaoImpl(DBActionTemplate dbActionTemplate)
	{
		this.dbActionTemplate = dbActionTemplate;
		pcsTlMstSql = new BAL_PcsTlMstSql();
		pcsTlDtlSql = new BAL_PcsTlDtlSql();
		
	}

	public void setDbActionTemplate(DBActionTemplate dbActionTemplate) {
		this.dbActionTemplate = dbActionTemplate;
	}
	
	public void BAL_PcsTlProductiondataDaoImplJwt(String jwtToken) { 
		 try { 
			 pcsenrtyserviceApi = new BAL_PcsEntryServiceApi(jwtToken); 
		 } 
		 catch (Exception e) { 
		 e.printStackTrace(); 
		 } 
		 } 

	public BAL_PcsTlProductiondata create(BAL_PcsTlProductiondata pcsTlProductiondata) 	throws ValidationExceptions, Exception {

		CommonFunctions.debugMsg("In Production Data Dao");
		List<String> sqls = new ArrayList<String>(); /* sqls for execution */ 
		BAL_PcsTlProductiondataSql pcsTlProductiondataSql = new BAL_PcsTlProductiondataSql(); // contains dbtable,field names, Field types and related sqls  of master table
		
		try{
			if(pcsTlProductiondata.getPprdShiftnoplan() == null || pcsTlProductiondata.getPprdShiftnoplan() == "N")
			{
				pcsTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlProductiondataSql.TBL_BAL_PCS_TL_PRODUCTIONDATA,  15, "PRL", "YY","Y")); // set the sequnce number 
				System.out.println(" Pprd KeyId"+pcsTlProductiondata.getPprdKeyid());
				sqls.add(BAL_PcsTlProductiondataSql.getInsertSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray())); // add insert sql for master table
			}
			else 
			{
				for(int i=1; i<=8; i++)
				{
										
					pcsTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlProductiondataSql.TBL_BAL_PCS_TL_PRODUCTIONDATA,  15, "PRL", "YY","Y")); // set the sequnce number 
					pcsTlProductiondata.setPprdHour(String.valueOf(i));
					pcsTlProductiondata.setPprdShiftnoplan("Y");
					sqls.add(BAL_PcsTlProductiondataSql.getInsertSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray())); // add insert sql for master table
					
				}
				
			}
			
			
			dbActionTemplate.executeStatements(sqls); // execute the block of sqls
			
		}catch(ValidationExceptions m)
		{
			throw new ValidationExceptions(m.getMessage());
		}
		catch(Exception e)
		{
			throw new Exception(e.getMessage());
		}
		return pcsTlProductiondata;
	}
	
	public BAL_PcsTlProductiondata update(BAL_PcsTlProductiondata pcsTlProductiondata)	throws Exception { 
		
		List<String> sqls = new ArrayList<String>();
		BAL_PcsTlProductiondataSql pcsTlProductiondataSql = new BAL_PcsTlProductiondataSql();
		try {

			sqls.add(BAL_PcsTlProductiondataSql.getUpdateSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray()));
			
			dbActionTemplate.executeStatements(sqls);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(e.getMessage());
		}
		
		return pcsTlProductiondata;
	}
	
	public BAL_PcsTlProductiondata delete(BAL_PcsTlProductiondata pcsTlProductiondata)
			throws Exception {

		List<String> sqls = new ArrayList<String>();
		BAL_PcsTlProductiondataSql pcsTlProductiondataSql = new BAL_PcsTlProductiondataSql();
		try {
			
			sqls.add(BAL_PcsTlProductiondataSql.getDeleteSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray()));

			dbActionTemplate.executeStatements(sqls);
			
		}catch( Exception e){
			throw new Exception(e.getMessage());
		}
		return pcsTlProductiondata;
	}
	
	public List<String[]> getPcsProductionCaptureGrid(String flid, String Fromdate,String Todate, String shiftid) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer Sql  = new StringBuffer();
		Sql.append("Select PPRD_KEYID as keyId,PPRD_HOUR as 'Hour', PPRD_PRODUCTID as 'Product',PPRD_PRODUCTMODEL as 'Product Model',PPRD_PRODUCEDQTY as 'Prod. Qty', ");
		Sql.append("PPRD_REWORKQTY as 'Rework Qty',PPRD_REJECTEDQTY as 'Rej Qty',PPRD_MANPOWER as 'Manpower' ,");
		Sql.append("PPRD_CYCLETIME as 'RNT' , PPRD_REMARKS as 'Remarks' from BAL_PCS_TL_PRODUCTIONDATA ");
		//,"Hour","Product","Product Model","Prod. Qty","Rework Qty","Rej Qty","Manpower","RNT","Remarks"
		
		CommonFunctions.debugMsg("ProductionSql  :"+Sql.toString());
		List<String[]> getproductiondata = dbActionTemplate.getDataList(Sql.toString());
		return getproductiondata;
	}
	
		public BAL_PcsTlProductiondata deleteProductiondata(String keyid)
					throws BusinessApplicationExceptions, Exception {
			
			BAL_PcsTlProductiondata pcsTlProductiondata = new BAL_PcsTlProductiondata();
				List<String> sqls = new ArrayList<String>();
				sqls.add(BAL_PcsTlProductiondataSql.deleteRejectiondata(keyid));
				sqls.add(BAL_PcsTlProductiondataSql.deleteProductiondata(keyid));
				dbActionTemplate.executeStatements(sqls);

				return pcsTlProductiondata;
	}


	@Override
	public BAL_PcsTlProductiondata removeShiftNoplan(String mstkeyId,String dtlkeyId,
				String cellId, String entrydate)throws BusinessApplicationExceptions, Exception {
			
		BAL_PcsTlProductiondata pcsTlProductiondata = new BAL_PcsTlProductiondata();

		String detailTableName =getDetailTableName(cellId, entrydate);
		CommonFunctions.debugMsg("detailTableName"+detailTableName);

		List<String> sqls = new ArrayList<String>();
		
		
		if(dtlkeyId.startsWith("PPRD"))
		{
			sqls.add(BAL_PcsTlProductiondataSql.deleteRejectiondata(dtlkeyId));
			sqls.add(BAL_PcsTlProductiondataSql.deleteProductiondata(dtlkeyId));
			
			StringBuffer sb= new StringBuffer(" DELETE FROM ");
			sb.append(detailTableName);
			sb.append(" where ");
			sb.append("PLDETAILSID = '"+mstkeyId+"'");
			sqls.add(sb.toString());
		}
			
		if(mstkeyId.startsWith("PRL"))
		{
			sqls.add(BAL_PcsTlProductiondataSql.deleteProductiondata(mstkeyId));
			
			StringBuffer sb= new StringBuffer(" DELETE FROM ");
			sb.append(detailTableName);
			sb.append(" where ");
			sb.append("PLMASTERID = '"+mstkeyId+"'");
			sqls.add(sb.toString());
			
			StringBuilder sb1 = new StringBuilder( "DELETE from " );
			sb1.append("BAL_PCS_TL_MST ");
			sb1.append( " where " );
			sb1.append("PRLM_KEYID ='"+mstkeyId+"'");
			sqls.add(sb1.toString());
			
		}
		
		CommonFunctions.debugMsg("removeshiftnoplan "+sqls);
		
		
		dbActionTemplate.executeStatements(sqls);	
		return pcsTlProductiondata;
	}
		

		@Override
		public BAL_PcsTlProductiondata createNew(BAL_PcsTlProductiondata pcsTlProductiondata,BAL_PcsTlMst pcsTlMst, BAL_PcsTlDtl pcsTlDtl, BAL_PcsEntryBean pcsEntryBean)
				throws Exception {
			try{
			List<String> sqls = new ArrayList<String>();
			String CycleTime = "0";
			BAL_PcsTlProductiondataSql pcsTlProductiondataSql = new BAL_PcsTlProductiondataSql();
			String detailTableName =getDetailTableName(pcsTlMst.getPrlmCellid(), pcsTlMst.getPrlmEntrydate());
			
			//String Checksql = " select count(*) from BAL_PCS_TL_MST where TRUNC(PRLM_ENTRYDATE) = TRUNC(TO_DATE('"+ pcsTlMst.getPrlmEntrydate() +"','DD-MON-YYYY HH24:MI'))  and PRLM_SHIFTID = '"+ pcsTlMst.getPrlmShiftid() +"'";
			//Checksql = Checksql + " and PRLM_SECTIONID = '"+ pcsTlMst.getPrlmSectionid() + "'  and PRLM_CELLID = '"  +  pcsTlMst.getPrlmCellid() + "'  ";
			String Checksql = " select count(*) from BAL_PCS_TL_MST where PRLM_ENTRYDATE::date = TO_DATE('"+ pcsTlMst.getPrlmEntrydate() +"','DD-MON-YYYY')::date"
			        + " and PRLM_SHIFTID = '"+ pcsTlMst.getPrlmShiftid() +"'";
			Checksql = Checksql + " and PRLM_SECTIONID = '"+ pcsTlMst.getPrlmSectionid() + "'  and PRLM_CELLID = '"  +  pcsTlMst.getPrlmCellid() + "'  ";
//			Checksql = Checksql + " and PRLM_SUBGROUPID = '" + pcsTlMst.getPrlmSubgroupid() + "'  and PRLM_MACHINEID = '" + pcsTlMst.getPrlmMachineid() + "' " ;
			
			CommonFunctions.debugMsg("Checksql " +  Checksql );
			String checkcount = dbActionTemplate.getSingleValue(Checksql);
			
			CommonFunctions.debugMsg("checkcount" + checkcount);
			if (Integer.parseInt(checkcount) > 0)
			{   
				/*
				 * String Checksqlkeyid =
				 * " select PRLM_KEYID from BAL_PCS_TL_MST where TRUNC(PRLM_ENTRYDATE) = TRUNC(TO_DATE('"
				 * + pcsTlMst.getPrlmEntrydate()
				 * +"','DD-MON-YYYY HH24:MI'))  and PRLM_SHIFTID = '"+ pcsTlMst.getPrlmShiftid()
				 * +"'"; Checksqlkeyid = Checksqlkeyid + " and PRLM_SECTIONID = '"+
				 * pcsTlMst.getPrlmSectionid() + "'  and PRLM_CELLID = '" +
				 * pcsTlMst.getPrlmCellid() + "' ";
				 */
				
				String Checksqlkeyid = " select PRLM_KEYID from BAL_PCS_TL_MST where PRLM_ENTRYDATE::date = TO_TIMESTAMP('"+ pcsTlMst.getPrlmEntrydate() +"','DD-MON-YYYY HH24:MI')::date  and PRLM_SHIFTID = '"+ pcsTlMst.getPrlmShiftid() +"'";
				Checksqlkeyid = Checksqlkeyid + " and PRLM_SECTIONID = '"+ pcsTlMst.getPrlmSectionid() + "'  and PRLM_CELLID = '"  +  pcsTlMst.getPrlmCellid() + "' ";
				CommonFunctions.debugMsg("Checksql inside checkcount>0 " +  Checksqlkeyid );
				String id = dbActionTemplate.getSingleValue(Checksqlkeyid);
				pcsTlMst.setPrlmKeyid(id);
				sqls.add(BAL_PcsTlMstSql.getUpdateSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add insert sql for master table
				pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
				CommonFunctions.debugMsg("machineId."+pcsTlDtl.getMachineid());
				CommonFunctions.debugMsg("productid"+pcsTlDtl.getProductid());
				CommonFunctions.debugMsg("Masterid"+pcsTlDtl.getPlmasterid());
				
				String dtlId = dbActionTemplate.getSingleValue("SELECT PLDETAILSID FROM "+detailTableName+" WHERE MACHINEID='"+pcsTlDtl.getMachineid()+"' AND PRODUCTID='"+pcsTlDtl.getProductid()+"' AND PLMASTERID='"+pcsTlDtl.getPlmasterid()+"' AND ACTUALCYCLETIME = '"+pcsTlDtl.getActualcycletime()+"' ");
				
			
			  	if(!UIUtils.isValidKeyId(dtlId) && UIUtils.isValidKeyId(pcsTlProductiondata.getPprdProductid()) && !pcsTlProductiondata.getPprdNoplanduration().equals("0"))
				{
					dtlId = dbActionTemplate.getSingleValue("SELECT PLDETAILSID FROM "+detailTableName+" WHERE MACHINEID='"+pcsTlDtl.getMachineid()+"' AND PLMASTERID='"+pcsTlDtl.getPlmasterid()+"'");
					CommonFunctions.debugMsg("In hourplandtlId1"+dtlId);
				}
			
				if(!UIUtils.isValidKeyId(dtlId) && pcsTlProductiondata.getPprdHourlynoplan().equals("Y"))
				{
					dtlId = dbActionTemplate.getSingleValue("SELECT PLDETAILSID FROM "+detailTableName+" WHERE MACHINEID='"+pcsTlDtl.getMachineid()+"' AND PLMASTERID='"+pcsTlDtl.getPlmasterid()+"'");
					CommonFunctions.debugMsg("In hourplandtlId 2 "+dtlId);
				}
				if(!UIUtils.isValidKeyId(dtlId) && !pcsTlProductiondata.getPprdHourlynoplan().equals("Y")  && pcsTlProductiondata.getPprdNoplanduration().equals("0"))
				{
					CommonFunctions.debugMsg("dtlId for lossentry1"+dtlId+"   "+detailTableName);
					CommonFunctions.debugMsg("dtlId for lossentry1"+pcsTlDtl.getMachineid());
					CommonFunctions.debugMsg("dtlId for lossentry1"+pcsTlDtl.getPlmasterid());
					 
					String dtlproductid = dbActionTemplate.getSingleValue("SELECT PRODUCTID FROM "+detailTableName+" WHERE MACHINEID='"+pcsTlDtl.getMachineid()+"' AND PLMASTERID='"+pcsTlDtl.getPlmasterid()+"'");
					CommonFunctions.debugMsg("dtlproductid in side the Productiond dao"+dtlproductid);
					
					if( !UIUtils.isValidKeyId(dtlproductid))
					{
					dtlId = dbActionTemplate.getSingleValue("SELECT PLDETAILSID FROM "+detailTableName+" WHERE MACHINEID='"+pcsTlDtl.getMachineid()+"' AND PLMASTERID='"+pcsTlDtl.getPlmasterid()+"'");
					CommonFunctions.debugMsg("dtlId"+dtlId);
						if( UIUtils.isValidKeyId(dtlId))
						{
							CommonFunctions.debugMsg("dtlId"+dtlId);
							pcsTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlProductiondataSql.TBL_BAL_PCS_TL_PRODUCTIONDATA,  15, "PRL", "YY","Y")); // set the sequnce number 
							pcsTlProductiondata.setPprdHourlynoplan("N");
							pcsTlProductiondata.setPprdTempfield1(dtlId);
							pcsTlProductiondata.setPprdTempfield2(pcsTlMst.getPrlmKeyid());
							sqls.add(BAL_PcsTlProductiondataSql.getInsertSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray())); // add insert sql for master table
						}
					}
				}
				CommonFunctions.debugMsg("dtlId"+dtlId);
				if(!UIUtils.isValidKeyId(dtlId) && pcsTlProductiondata.getPprdShiftnoplan().equals("Y"))
					{
		
					
						CommonFunctions.debugMsg("pcsTlProductiondata.getPprdShiftnoplan()   ");
						CommonFunctions.debugMsg("shift duration"+pcsEntryBean.getSftDuration());
						
						double fullhour = Integer.parseInt(pcsEntryBean.getSftDuration());
						double hourlength=Math.ceil(fullhour/60); 
						CommonFunctions.debugMsg("hourlength"+hourlength);
						
						for(int i=1; i<=hourlength; i++)
						{
							pcsTlDtl.setCalendartime("60");
							pcsTlDtl.setUnaccountedtime("0");
							pcsTlDtl.setProductiontime("0");
							pcsTlDtl.setNoplaninmins(pcsTlDtl.getCalendartime());
							pcsTlDtl.setProductionavltime(pcsTlDtl.getCalendartime());
							pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
							
							
							if( i == hourlength)
							{
								int avltime=(int)(fullhour%60);
								if(avltime == 0)
								{
									pcsTlDtl.setCalendartime("60");
									pcsTlDtl.setUnaccountedtime("0");
									pcsTlDtl.setProductiontime("0");
									pcsTlDtl.setNoplaninmins(pcsTlDtl.getCalendartime());
									pcsTlDtl.setProductionavltime(pcsTlDtl.getCalendartime());
									pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
								}
								else
								{
									pcsTlDtl.setCalendartime(String.valueOf(avltime));
									pcsTlDtl.setUnaccountedtime("0");
									pcsTlDtl.setProductiontime("0");
									pcsTlDtl.setNoplaninmins(pcsTlDtl.getCalendartime());
									pcsTlDtl.setProductionavltime(pcsTlDtl.getCalendartime());
									pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
								}
							}	
							
							pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
							pcsTlDtl.setMachineid(String.valueOf(i));
							
							pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(BAL_PcsTlDtlSql.TBL_BAL_PCS_TL_DTL,  15, "PDE", "YY","Y"));
							sqls.add(BAL_PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql for detail table
							
							pcsTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlProductiondataSql.TBL_BAL_PCS_TL_PRODUCTIONDATA,  15, "PRL", "YY","Y")); // set the sequnce number 
							pcsTlProductiondata.setPprdHour(String.valueOf(i));
							pcsTlProductiondata.setPprdShiftnoplan("Y");
							pcsTlProductiondata.setPprdTempfield1(pcsTlDtl.getPldetailsid());
							pcsTlProductiondata.setPprdTempfield2(pcsTlMst.getPrlmKeyid());
							sqls.add(BAL_PcsTlProductiondataSql.getInsertSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray())); // add insert sql for master table
							
						}
						
					}
				
				else if(!UIUtils.isValidKeyId(dtlId) && pcsTlProductiondata.getPprdHourlynoplan().equals("Y"))
				{
					CommonFunctions.debugMsg("pcsTlProductiondata.getPprdHourlynoplan()   "+pcsTlDtl.getCalendartime());
					int loadingtime= Integer.parseInt(pcsEntryBean.getAvailtimehourly()) - Integer.parseInt(pcsTlProductiondata.getPprdNoplanduration());
					CommonFunctions.debugMsg("loadingtime  "+loadingtime);
					pcsTlDtl.setLoadingtime(String.valueOf(loadingtime));
					pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
					pcsTlDtl.setMchavailabletime(pcsEntryBean.getAvailtimehourly());
					pcsTlDtl.setProductionavltime(String.valueOf(loadingtime));
					pcsTlDtl.setUnaccountedtime(pcsEntryBean.getUnreptimehourly());				
					pcsTlDtl.setNoplaninmins(pcsTlProductiondata.getPprdNoplanduration());
					pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
					pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(BAL_PcsTlDtlSql.TBL_BAL_PCS_TL_DTL,  15, "PDE", "YY","Y"));
					sqls.add(BAL_PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql for detail table
					
					pcsTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlProductiondataSql.TBL_BAL_PCS_TL_PRODUCTIONDATA,  15, "PRL", "YY","Y")); // set the sequnce number 
					pcsTlProductiondata.setPprdHourlynoplan("Y");
					pcsTlProductiondata.setPprdTempfield1(pcsTlDtl.getPldetailsid());
					pcsTlProductiondata.setPprdTempfield2(pcsTlMst.getPrlmKeyid());
					sqls.add(BAL_PcsTlProductiondataSql.getInsertSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray())); // add insert sql for master table
	
				}
				
				else if(!UIUtils.isValidKeyId(dtlId)  && pcsTlProductiondata.getPprdHourlynoplan().equals("N") && pcsTlProductiondata.getPprdShiftnoplan().equals("N")){
							CommonFunctions.debugMsg("detailidininsert"+dtlId);
							CommonFunctions.debugMsg("detailidininsert"+pcsTlDtl.getPldetailsid());
							pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(pcsTlDtlSql.TBL_BAL_PCS_TL_DTL,  15, "PDE", "YY","Y"));
							pcsTlDtl.setUnaccountedtime(pcsEntryBean.getUnreptimehourly());
							pcsTlDtl.setProductiontime(pcsEntryBean.getProdtimehourly());
						//	pcsTlDtl.setNoplaninmins("0");
							pcsTlDtl.setProductionavltime(pcsEntryBean.getAvailtimehourly());
							pcsTlDtl.setMchavailabletime(pcsEntryBean.getAvailtimehourly());
							pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
							
							pcsTlProductiondata.setPprdTempfield2(pcsTlMst.getPrlmKeyid());
						
							sqls.add(BAL_PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql for detail table
							pcsTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlProductiondataSql.TBL_BAL_PCS_TL_PRODUCTIONDATA,  15, "PRL", "YY","Y")); // set the sequnce number 
							CommonFunctions.debugMsg("detailidininsert"+pcsTlDtl.getPldetailsid());
							pcsTlProductiondata.setPprdTempfield1(pcsTlDtl.getPldetailsid());
							sqls.add(BAL_PcsTlProductiondataSql.getInsertSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray())); // add insert sql for master table
			//			}	
					} 
					else
					{
						
						CommonFunctions.debugMsg("getavailtimehourlyindao"+pcsEntryBean.getAvailtimehourly());
						
						pcsTlDtl.setUnaccountedtime(pcsEntryBean.getUnreptimehourly());
						pcsTlDtl.setProductiontime(pcsEntryBean.getProdtimehourly());
						
						pcsTlDtl.setProductionavltime(pcsEntryBean.getAvailtimehourly());
						pcsTlDtl.setMchavailabletime(pcsEntryBean.getAvailtimehourly());
						pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
						pcsTlDtl.setPldetailsid(dtlId);

						pcsTlProductiondata.setPprdTempfield1(pcsTlDtl.getPldetailsid());
						pcsTlProductiondata.setPprdTempfield2(pcsTlMst.getPrlmKeyid());
				//		sqls.add(PcsTlDtlSql.getUpdateSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql for detail table
						CommonFunctions.debugMsg("pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray())" + pcsTlDtlSql.getPcs_DbFields().length+"  "+ pcsTlDtl.getSaveArray().length);
						sqls.add(BAL_PcsTlDtlSql.getUpdatePcsProductionSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql for detail table
						sqls.add(BAL_PcsTlProductiondataSql.getUpdateSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray()));
					
					}
						CommonFunctions.debugMsg("sqls for Mst Dtl  " +  sqls.toString() );
			}
			else
			{
				
				if(pcsTlProductiondata.getPprdHourlynoplan().equals("Y"))
				{
					CommonFunctions.debugMsg("pcsTlProductiondata.getPprdHourlynoplan()   "+pcsTlProductiondata.getPprdHourlynoplan());
					pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlMstSql.TBL_BAL_PCS_TL_MST,  15, "	PRLM", "YY","Y")); // set the sequnce number
					sqls.add(BAL_PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add insert sql for master table
					CommonFunctions.debugMsg("pcsTlProductiondata.getPprdHourlynoplan()   "+pcsTlDtl.getCalendartime());
					int loadingtime= Integer.parseInt(pcsEntryBean.getAvailtimehourly()) - Integer.parseInt(pcsTlProductiondata.getPprdNoplanduration());
					CommonFunctions.debugMsg("loadingtime  "+loadingtime);
					pcsTlDtl.setLoadingtime(String.valueOf(loadingtime));
					pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
					pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
					pcsTlDtl.setMchavailabletime(pcsEntryBean.getAvailtimehourly());
			//		pcsTlDtl.setProductionavltime(pcsEntryBean.getUnreptimehourly());
					pcsTlDtl.setUnaccountedtime(pcsEntryBean.getUnreptimehourly());
					pcsTlDtl.setProductionavltime(String.valueOf(loadingtime));
			
					pcsTlDtl.setNoplaninmins(pcsTlProductiondata.getPprdNoplanduration());
					pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
					
					pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(BAL_PcsTlDtlSql.TBL_BAL_PCS_TL_DTL,  15, "PDE", "YY","Y"));
					sqls.add(BAL_PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql for detail table
					
					pcsTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlProductiondataSql.TBL_BAL_PCS_TL_PRODUCTIONDATA,  15, "PRL", "YY","Y")); // set the sequnce number 
	
					pcsTlProductiondata.setPprdHourlynoplan("Y");
					pcsTlProductiondata.setPprdTempfield1(pcsTlDtl.getPldetailsid());
					pcsTlProductiondata.setPprdTempfield2(pcsTlMst.getPrlmKeyid());
					sqls.add(BAL_PcsTlProductiondataSql.getInsertSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray())); // add insert sql for master table
	
				}
				else if(pcsTlProductiondata.getPprdShiftnoplan().equals("Y"))
				{
					//List<String> sqlstr = new ArrayList<String>();
					CommonFunctions.debugMsg("pcsTlProductiondata.getPprdShiftnoplan()  1 ");
					//pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlMstSql.TBL_BAL_PCS_TL_MST)); // set the sequnce number
					pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlMstSql.TBL_BAL_PCS_TL_MST,  15, "	PRLM", "YY","Y")); // set the sequnce number
					String  plmasterkeyid = pcsTlMst.getPrlmKeyid(); 
					/*	sqls.add(PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add insert sql for master table
					*/
					CommonFunctions.debugMsg("availTime"+pcsEntryBean.getSftDuration());
					
					double fullhour = Integer.parseInt(pcsEntryBean.getSftDuration());
					double hourlength=Math.ceil(fullhour/60); 
					CommonFunctions.debugMsg("hourlength"+hourlength);
					
					for(int i=1; i<=hourlength; i++)
					{
						pcsTlDtl.setCalendartime("60");
						pcsTlDtl.setUnaccountedtime("0");
						pcsTlDtl.setProductiontime("0");
						pcsTlDtl.setNoplaninmins(pcsTlDtl.getCalendartime());
						pcsTlDtl.setProductionavltime(pcsTlDtl.getCalendartime());
						pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
						
						
						if( i == hourlength)
						{
							int avltime=(int)(fullhour%60);
							if(avltime == 0)
							{
								pcsTlDtl.setCalendartime("60");
								pcsTlDtl.setUnaccountedtime("0");
								pcsTlDtl.setProductiontime("0");
								pcsTlDtl.setNoplaninmins(pcsTlDtl.getCalendartime());
								pcsTlDtl.setProductionavltime(pcsTlDtl.getCalendartime());
								pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
								
								
							}else
							{
								pcsTlDtl.setCalendartime(String.valueOf(avltime));
								pcsTlDtl.setUnaccountedtime("0");
								pcsTlDtl.setProductiontime("0");
								pcsTlDtl.setNoplaninmins(pcsTlDtl.getCalendartime());
								pcsTlDtl.setProductionavltime(pcsTlDtl.getCalendartime());
								pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
								
							}
							
						}
						
						if( i == 1)
						{
							CommonFunctions.debugMsg("valueofhourinif"+i);
							sqls.add(BAL_PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add insert sql for master table
								
						}
						else
						{
							CommonFunctions.debugMsg("valueofhourinelse"+i);
							sqls.add(BAL_PcsTlMstSql.getUpdateSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray()));
						}
						
						
						
						pcsTlDtl.setPlmasterid(plmasterkeyid);
						pcsTlDtl.setMachineid(String.valueOf(i));
						pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(pcsTlDtlSql.TBL_BAL_PCS_TL_DTL,  15, "PDE", "YY","Y"));
						sqls.add(BAL_PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql for detail table
						//pcsTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlProductiondataSql.TBL_BAL_PCS_TL_PRODUCTIONDATA)); // set the sequnce number
						pcsTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlProductiondataSql.TBL_BAL_PCS_TL_PRODUCTIONDATA,  15, "PPRD", "YY","Y")); 
						pcsTlProductiondata.setPprdHour(String.valueOf(i));
						pcsTlProductiondata.setPprdShiftnoplan("Y");
						pcsTlProductiondata.setPprdTempfield1(pcsTlDtl.getPldetailsid());
						pcsTlProductiondata.setPprdTempfield2(plmasterkeyid);
						sqls.add(BAL_PcsTlProductiondataSql.getInsertSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray())); // add insert sql for master table
						
						runBulkSqls(sqls,detailTableName,pcsTlDtl.getPldetailsid(),pcsTlDtl.getCalendartime(),pcsTlMst.getPrlmFlid());
						sqls.clear();
					}
					

				}
			/*	else if(pcsEntryBean.getAddrowtoGrid() != null )
				{
					CommonFunctions.debugMsg("pcsTlProductiondata.getPprdShiftnoplan()  1 ");
					pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(PcsTlMstSql.TBL_PCS_TL_MST)); // set the sequnce number
					sqls.add(PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add insert sql for master table
					
					CommonFunctions.debugMsg("availTime"+pcsEntryBean.getSftDuration());
					
					double fullhour = Integer.parseInt(pcsEntryBean.getSftDuration());
					double hourlength=Math.ceil(fullhour/60); 
					CommonFunctions.debugMsg("hourlength"+hourlength);
					
					for(int i=1; i<=hourlength; i++)
					{
						pcsTlDtl.setCalendartime("60");
						pcsTlDtl.setUnaccountedtime(pcsTlDtl.getCalendartime());
						pcsTlDtl.setProductiontime("0");
						pcsTlDtl.setNoplaninmins("0");
						pcsTlDtl.setProductionavltime(pcsTlDtl.getCalendartime());
						pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
						
						
						if( i == hourlength)
						{
							int avltime=(int)(fullhour%60);
							if(avltime == 0)
							{
								pcsTlDtl.setCalendartime("60");
								pcsTlDtl.setUnaccountedtime("60");
								pcsTlDtl.setProductiontime("0");
								pcsTlDtl.setNoplaninmins("0");
								pcsTlDtl.setProductionavltime("0");
								pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
								
								
							}else
							{
								pcsTlDtl.setCalendartime(String.valueOf(avltime));
								pcsTlDtl.setUnaccountedtime(String.valueOf(avltime));
								pcsTlDtl.setProductiontime("0");
								pcsTlDtl.setNoplaninmins("0");
								pcsTlDtl.setProductionavltime(pcsTlDtl.getCalendartime());
								pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
								
							}
						}	
						
						pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
						pcsTlDtl.setMachineid(String.valueOf(i));
						
						pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(pcsTlDtlSql.TBL_BAL_PCS_TL_DTL,  15, "PDE", "YY","Y"));
						sqls.add(PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql for detail table
						
						pcsTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(PcsTlProductiondataSql.TBL_PCS_TL_PRODUCTIONDATA)); // set the sequnce number 
						pcsTlProductiondata.setPprdHour(String.valueOf(i));
					
						pcsTlProductiondata.setPprdTempfield1(pcsTlDtl.getPldetailsid());
						pcsTlProductiondata.setPprdTempfield2(pcsTlMst.getPrlmKeyid());
						sqls.add(PcsTlProductiondataSql.getInsertSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray())); // add insert sql for master table
						
					//	runBulkSqls(sqls,detailTableName,pcsTlDtl.getPldetailsid(),pcsTlDtl.getCalendartime(),pcsTlMst.getPrlmFlid());
	
					}
					

				}
				*/
				else if(pcsTlProductiondata.getPprdShiftnoplan().equals("N") && pcsTlProductiondata.getPprdHourlynoplan().equals("N") && pcsEntryBean.getAddrowtoGrid() == null)
				{
					CommonFunctions.debugMsg("In Last else if ");
					pcsTlMst.setPrlmKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlMstSql.TBL_BAL_PCS_TL_MST,  15, "	PRLM", "YY","Y")); // set the sequnce number
					sqls.add(BAL_PcsTlMstSql.getInsertSql(pcsTlMstSql.getPrlmDbFields(), pcsTlMst.getSaveArray())); // add insert sql for master table
					
					pcsTlDtl.setCalendartime(pcsEntryBean.getAvailtimehourly());
					pcsTlDtl.setPlmasterid(pcsTlMst.getPrlmKeyid());
					
					CommonFunctions.debugMsg("pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray())" + pcsTlDtlSql.getPcs_DbFields().length+"  "+ pcsTlDtl.getSaveArray().length);

					pcsTlDtl.setPldetailsid(dbActionTemplate.getSequenceNumber(pcsTlDtlSql.TBL_BAL_PCS_TL_DTL,  15, "PDE", "YY","Y"));
					sqls.add(BAL_PcsTlDtlSql.getInsertSql(detailTableName, pcsTlDtlSql.getPcs_DbFields(), pcsTlDtl.getSaveArray()));// add insert sql for detail table
					pcsTlProductiondata.setPprdTempfield2(pcsTlMst.getPrlmKeyid());
					pcsTlProductiondata.setPprdTempfield1(pcsTlDtl.getPldetailsid());
					pcsTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlProductiondataSql.TBL_BAL_PCS_TL_PRODUCTIONDATA,  15, "PRL", "YY","Y")); // set the sequnce number 
					sqls.add(BAL_PcsTlProductiondataSql.getInsertSql(pcsTlProductiondataSql.getPprdDbFields(), pcsTlProductiondata.getSaveArray())); // add insert sql for master table
				}
					
					CommonFunctions.debugMsg("sqls for Mst Dtl  " +  sqls.toString() );
			}
			
			
			
			CommonFunctions.debugMsg("sqls for Mst Dtl  " +  sqls);
			CommonFunctions.debugMsg("sqls for Mst Dtl  " + detailTableName );
			CommonFunctions.debugMsg("sqls for Mst Dtl  " + pcsTlDtl.getPldetailsid() );
			CommonFunctions.debugMsg("sqls for Mst Dtl  " + pcsTlDtl.getCalendartime() );
			CommonFunctions.debugMsg("sqls for Mst Dtl  " +  pcsTlMst.getPrlmFlid() );
			
	//		dbActionTemplate.executeStatements(sqls);
			if( !pcsTlProductiondata.getPprdShiftnoplan().equals("Y"))
			runBulkSqls(sqls,detailTableName,pcsTlDtl.getPldetailsid(),pcsTlDtl.getCalendartime(),pcsTlMst.getPrlmFlid());
			
			
		 }
		catch(Exception e){
			e.printStackTrace();
		}	
		return pcsTlProductiondata;
		}
		
		
	
		
		
		public void runBulkSqls(List<String> sqls, String detailTableName,String pldetailsId,String CycleTime,String flid) throws Exception { 
			try {
				List<String> bulk_sqls = new ArrayList<String>();
				List<Object[]> valueList  = new ArrayList<Object[]>();
				List<int[]> dataTypes  = new ArrayList<int[]>();
				List<String> charList  = new ArrayList<String>();
				if(CycleTime == null)
					CycleTime="0";
					//int [] dataTypesBD =  { Types.TIMESTAMP,Types.VARCHAR,Types.VARCHAR};
				for (int i=0; i<sqls.size(); i++) {
					String insertPCSSql = sqls.get(i);	
					Object [] insertPCSDatas = {};
					int [] insertPCSTypes =  {};
					bulk_sqls.add(insertPCSSql);
					valueList.add(insertPCSDatas);
					dataTypes.add(insertPCSTypes);
					charList.add("Q");			
				}
				//String insertPCSSql = "PCS_PC_PRODUCTIONCALC.PCS_FN_UPDATEPLDTL";
				String insertPCSSql = "bal_pcs_fn_updatepldtl"; //"PCS_FN_UPDATEPLDTL";
				Object [] insertPCSDatas = {detailTableName,pldetailsId,CycleTime,"Y","N",flid};
				System.out.println("detailTableName : "+detailTableName+" pldetailsId "+ pldetailsId+"  Cycletime"+CycleTime);
				int [] insertPCSTypes =  {Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
				System.out.println(" PCS_FN_UPDATEPLDTL 012  "+insertPCSSql);
				
				bulk_sqls.add(insertPCSSql);
				valueList.add(insertPCSDatas);
				dataTypes.add(insertPCSTypes);
				charList.add("P");				
				
				
				char [] sqlType = new char[charList.size()];
				for(int c=0;c<sqlType.length;c++)
				{
					sqlType[c] = charList.get(c).charAt(0);
				}
				
				System.out.println(" bulk_sqlsbulk_sqlsbulk_sqlsbulk_sqls "+bulk_sqls);
				dbActionTemplate.executeStatement1(bulk_sqls, valueList, dataTypes, sqlType);

			}
			catch(Exception e)
			
			{	
				
				System.out.println("Exception in updatng pcs impl "+e.getMessage());
				
				e.printStackTrace();
				
					
				if (e.getMessage().equals("-2")) {
					String tolrance = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE", "CNFM_CODE", "MAXROP");
					int tolval = Integer.parseInt(tolrance) + 100;
					String tolmsg = "ROP is greater than " + tolval + "%" ;
					throw new Exception( tolmsg.toString());
				}
				else if (e.getMessage().equals("-3")) {
					String unrepTime = dbActionTemplate.getSingleValue("ADM_TL_CONFIGURATIONMST", "CNFM_SETTINGVALUE", "CNFM_CODE", "MAXUNREPORTED");
					
					if(UIUtils.isValidKeyId(unrepTime) && UIUtils.isValidKeyId(CycleTime))
					{
						if(Double.parseDouble(CycleTime) > Integer.parseInt(unrepTime))
							unrepTime = CycleTime;
					}
					//String unrepmsg = "UNREPORTED TIME EXCEEDS  " + unrepTime + " Mins" ;
					String unrepmsg = "Unreported time exceeds " + unrepTime + " Mins" ;
					e.printStackTrace();
					throw new Exception( unrepmsg.toString());
				}
				else
					
					System.out.println(" e.getMessage() "+e.getMessage());
				throw new Exception( e.getMessage());
			}
		}
		
		/*
		 * public String getDetailTableName(String cellId, String entryDate) throws
		 * Exception {
		 * 
		 * CommonFunctions.debugMsg("Inside getDetailTableName.........."); try { String
		 * sql = "";
		 * 
		 * //String detailTableName = "PCS_TL_" +
		 * dbActionTemplate.getSingleValue("GEN_TL_SECTIONMST", "SECT_CODE",
		 * "SECT_KEYID", sectId); String detailTableName = "PCS_TL_" +
		 * dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_CODE",
		 * "CELL_KEYID",cellId ); detailTableName =
		 * detailTableName.trim().replaceAll("-", "#"); detailTableName =
		 * detailTableName.replaceAll(" ", "");
		 * CommonFunctions.debugMsg("detailTableNamee.........."+detailTableName);
		 * detailTableName = detailTableName.replaceAll("&", ""); detailTableName =
		 * detailTableName.replaceAll(",", ""); detailTableName =
		 * detailTableName.replaceAll("/", ""); //detailTableName =
		 * detailTableName.replaceAll("(", "");
		 * CommonFunctions.debugMsg("detailTableNamee.........."+detailTableName);
		 * //String avialTable = dbActionTemplate.getSingleValue("TAB", "TNAME",
		 * "TNAME", detailTableName); String avialTable =
		 * dbActionTemplate.getSingleValue(
		 * "SELECT tablename FROM pg_tables WHERE tablename = '" +
		 * detailTableName.toLowerCase() + "'");
		 * CommonFunctions.debugMsg("avialTable.........."+avialTable); if (!
		 * UIUtils.isValidKeyId(avialTable)) avialTable =" ";
		 * 
		 * if (avialTable.equals(detailTableName)) {
		 * CommonFunctions.debugMsg("Table already exists "); } else {
		 * CommonFunctions.debugMsg("Create table for section"); sql = " CREATE TABLE "
		 * + detailTableName + " AS  SELECT * FROM BAL_PCS_TL_DTL ";
		 * CommonFunctions.debugMsg("sqlsqlsql"+sql);
		 * dbActionTemplate.executeStatement(sql);
		 * 
		 * String pkConst = detailTableName.replace("PCS_TL_", ""); sql =
		 * " ALTER TABLE " + detailTableName +
		 * " ADD CONSTRAINT PK_BAL_PCS_TL_DTL_"+pkConst+" PRIMARY KEY (PLDETAILSID) ";
		 * dbActionTemplate.executeStatement(sql);
		 * 
		 * sql = " ALTER TABLE " + detailTableName +
		 * " ADD  CONSTRAINT FK_PLMASTERID_"+pkConst+" "; sql = sql +
		 * " FOREIGN KEY (PLMASTERID) REFERENCES BAL_PCS_TL_MST (PRLM_KEYID) ";
		 * dbActionTemplate.executeStatement(sql);
		 * 
		 * } CommonFunctions.debugMsg("avialTable.........."+avialTable); String
		 * sql1="SELECT to_date('" + entryDate +
		 * "') - TO_date(CNFM_FROMDATE)  FROM ADM_TL_CONFIGURATIONMST WHERE CNFM_CODE = 'TPMSTARTDATE'"
		 * ; CommonFunctions.debugMsg("sql1111"+sql1);
		 * 
		 * String diff = dbActionTemplate.getSingleValue(sql1);
		 * 
		 * CommonFunctions.debugMsg("diff"+diff);
		 * 
		 * if ( Integer.parseInt(diff) <0 ) detailTableName="BAL_PCS_TL_DTL";
		 * //detailTableName=detailTableName; //else
		 * 
		 * 
		 * return detailTableName; }
		 * 
		 * catch(Exception e) {
		 * CommonFunctions.debugMsg("Exception in getDetailTableName dao impl"+e.
		 * getMessage()); } return null; }
		 */

		
		//sriram 
		public String getDetailTableName(String cellId, String entryDate) throws Exception {

		    CommonFunctions.debugMsg("Inside getDetailTableName..........");
		    try
		    {
		        String sql = "";

		        String detailTableName = "PCS_TL_" + dbActionTemplate.getSingleValue("GEN_TL_CELLMST", "CELL_CODE", "CELL_KEYID", cellId);
		        detailTableName = detailTableName.trim().replaceAll("-", "_");
		        detailTableName = detailTableName.replaceAll(" ", "");
		        CommonFunctions.debugMsg("detailTableNamee.........." + detailTableName);
		        detailTableName = detailTableName.replaceAll("&", "");
		        detailTableName = detailTableName.replaceAll(",", "");
		        detailTableName = detailTableName.replaceAll("/", "");
		        CommonFunctions.debugMsg("detailTableNamee.........." + detailTableName);

		        String avialTable = dbActionTemplate.getSingleValue(
		                "SELECT tablename FROM pg_tables WHERE tablename = '" + detailTableName.toLowerCase() + "'");
		        CommonFunctions.debugMsg("avialTable.........." + avialTable);
		        if (!UIUtils.isValidKeyId(avialTable))
		            avialTable = " ";

		        // CHANGE 1: equals -> equalsIgnoreCase (pg_tables returns lowercase)
		        if (avialTable.equalsIgnoreCase(detailTableName))
		        {
		            CommonFunctions.debugMsg("Table already exists ");
		        }
		        else
		        {
		            CommonFunctions.debugMsg("Create table for section");
		            sql = " CREATE TABLE " + detailTableName + " AS  SELECT * FROM BAL_PCS_TL_DTL ";
		            CommonFunctions.debugMsg("sqlsqlsql" + sql);
		            dbActionTemplate.executeStatement(sql);

		            String pkConst = detailTableName.replace("BAL_PCS_TL_", "");
		            sql = " ALTER TABLE " + detailTableName + " ADD CONSTRAINT PK_BAL_PCS_TL_DTL_" + pkConst + " PRIMARY KEY (PLDETAILSID) ";
		            dbActionTemplate.executeStatement(sql);

		            sql = " ALTER TABLE " + detailTableName + " ADD  CONSTRAINT FK_PLMASTERID_" + pkConst + " ";
		            sql = sql + " FOREIGN KEY (PLMASTERID) REFERENCES BAL_PCS_TL_MST (PRLM_KEYID) ";
		            dbActionTemplate.executeStatement(sql);
		        }
		        CommonFunctions.debugMsg("avialTable.........." + avialTable);

		        // CHANGE 2: PostgreSQL to_date needs a format mask; CNFM_FROMDATE used directly (assumes date column)
		        //String sql1 = "SELECT to_date('" + entryDate + "', 'DD-Mon-YYYY') - CNFM_FROMDATE FROM ADM_TL_CONFIGURATIONMST WHERE CNFM_CODE = 'TPMSTARTDATE'";
		        String sql1 = "SELECT (to_date('" + entryDate + "', 'DD-Mon-YYYY') - CNFM_FROMDATE::date) FROM ADM_TL_CONFIGURATIONMST WHERE CNFM_CODE = 'TPMSTARTDATE'";

		        CommonFunctions.debugMsg("sql1111" + sql1);

		        String diff = dbActionTemplate.getSingleValue(sql1);
		        CommonFunctions.debugMsg("diff" + diff);

		        if (Integer.parseInt(diff) < 0)
		            detailTableName = "BAL_PCS_TL_DTL";

		        return detailTableName;
		    }
		    catch (Exception e)
		    {
		        CommonFunctions.debugMsg("Exception in getDetailTableName dao impl" + e.getMessage());
		    }
		    return null;
		}
		
	public BAL_PcsTlHtDetails createNew(BAL_PcsTlHtProductionHistory newPcsTlHtProductionHistory,BAL_PcsTlHtDetails newPcsTlHtDetails,BAL_PcsTlHtDetails existPcsTlHtDetails)
			throws Exception {
		// TODO Auto-generated method stub		
		
	try{
	List<String> sqls = new ArrayList<String>();
	String CycleTime = "0";
	BAL_PcsTlProductiondataSql pcsTlProductiondataSql = new BAL_PcsTlProductiondataSql();
	CommonFunctions.debugMsg("newPcsTlHtProductionHistory.getCellid()  " +newPcsTlHtProductionHistory.getCellid());
	String detailTableName =getDetailTableName(newPcsTlHtProductionHistory.getCellid(), newPcsTlHtProductionHistory.getDate());
	
	String Checksql = " select count(*) from PCS_TL_HTPRODSPECMSTHISTORY where TRUNC(HPSH_FROMDATE) = TRUNC(TO_DATE('"+ newPcsTlHtProductionHistory.getDate() +"','DD-MON-YYYY HH24:MI'))  and HPSH_SUBGROUPID = '"+ newPcsTlHtProductionHistory.getShiftid() +"'";
	Checksql = Checksql + " AND  HPSH_CELLID = '"  +  newPcsTlHtProductionHistory.getCellid() + "'  ";
//	Checksql = Checksql + " and PRLM_SUBGROUPID = '" + pcsTlMst.getPrlmSubgroupid() + "'  and PRLM_MACHINEID = '" + pcsTlMst.getPrlmMachineid() + "' " ;
	
	CommonFunctions.debugMsg("Checksql " +  Checksql );
	String checkcount = dbActionTemplate.getSingleValue(Checksql);
	
	CommonFunctions.debugMsg("checkcount       " + checkcount);
	if (Integer.parseInt(checkcount) == 0)
	{   
		String Checksqlkeyid = " select HPSH_KEYID from PCS_TL_HTPRODSPECMSTHISTORY where TRUNC(HPSH_FROMDATE) = TRUNC(TO_DATE('"+ newPcsTlHtProductionHistory.getToDate() +"','DD-MON-YYYY HH24:MI'))  and HPSH_SUBGROUPID = '"+ newPcsTlHtProductionHistory.getShiftid() +"'";
		Checksqlkeyid = Checksqlkeyid + " and HPSH_CELLID = '"+ newPcsTlHtProductionHistory.getCellid() + "' ";
		
		String id = dbActionTemplate.getSingleValue(Checksqlkeyid);
		newPcsTlHtProductionHistory.setKeyid(id);
	/*	CommonFunctions.debugMsg("pcsTlHtHistrorySql.getPcs_DbFields()  "+ pcsTlHtHistrorySql.getPcs_DbFields());
		sqls.add(PcsTlMstSql.getUpdateSql(pcsTlHtHistrorySql.getPcs_DbFields(), newPcsTlHtProductionHistory.getSaveArray())); // add insert sql for master table
	*/	newPcsTlHtDetails.setMasterid(newPcsTlHtProductionHistory.getKeyid());
		
		
		String dtlId = dbActionTemplate.getSingleValue("SELECT PLDETAILSID FROM "+detailTableName+" WHERE  PRODUCTID='"+newPcsTlHtDetails.getProductid()+"' AND PLMASTERID='"+newPcsTlHtDetails.getMasterid()+"' AND ACTUALCYCLETIME = '"+newPcsTlHtDetails.getActualCycleTime()+"' ");
		
	
	  	if(!UIUtils.isValidKeyId(dtlId) && UIUtils.isValidKeyId(newPcsTlHtDetails.getProductid()) && !newPcsTlHtDetails.getNoplanMints().equals("0"))
		{
			dtlId = dbActionTemplate.getSingleValue("SELECT PLDETAILSID FROM "+detailTableName+" WHERE  PLMASTERID='"+newPcsTlHtDetails.getMasterid()+"'");
			CommonFunctions.debugMsg("In hourplandtlId1"+dtlId);
		}	
		/*
		if(!UIUtils.isValidKeyId(dtlId) && !newPcsTlHtDetails.get().equals("Y")  && newPscTlProductiondata.getPprdNoplanduration().equals("0"))
		{		 
			String dtlproductid = dbActionTemplate.getSingleValue("SELECT PRODUCTID FROM "+detailTableName+" WHERE  PLMASTERID='"+newPcsTlHtDetails.getMasterid()+"'");
			CommonFunctions.debugMsg("dtlproductid"+dtlproductid);
			
			if( !UIUtils.isValidKeyId(dtlproductid))
			{
			dtlId = dbActionTemplate.getSingleValue("SELECT PLDETAILSID FROM "+detailTableName+" WHERE MACHINEID  PLMASTERID='"+newPcsTlHtDetails.getMasterid()+"'");
			CommonFunctions.debugMsg("dtlId"+dtlId);
				if( UIUtils.isValidKeyId(dtlId))
				{
					CommonFunctions.debugMsg("dtlId"+dtlId);
					newPscTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(PcsTlProductiondataSql.TBL_PCS_TL_PRODUCTIONDATA)); // set the sequnce number 
					newPscTlProductiondata.setPprdHourlynoplan("N");
					newPscTlProductiondata.setPprdTempfield1(dtlId);
					newPscTlProductiondata.setPprdTempfield2(newPcsTlHtProductionHistory.getKeyid());
					sqls.add(PcsTlProductiondataSql.getInsertSql(pcsTlProductiondataSql.getPprdDbFields(), newPscTlProductiondata.getSaveArray())); // add insert sql for master table
				}
			}
		}
		
		CommonFunctions.debugMsg("dtlId"+dtlId);
		if(!UIUtils.isValidKeyId(dtlId) && newPscTlProductiondata.getPprdShiftnoplan().equals("Y"))
			{
				CommonFunctions.debugMsg("pcsTlProductiondata.getPprdShiftnoplan()   ");
				//CommonFunctions.debugMsg("shift duration"+pcsEntryBean.getSftDuration());
				
				//double fullhour = Integer.parseInt(pcsEntryBean.getSftDuration());
			//	double hourlength=Math.ceil(fullhour/60); 
			//	CommonFunctions.debugMsg("hourlength"+hourlength);
			/*	
				for(int i=1; i<=hourlength; i++)
				{
					pcsTlDtl.setCalendartime("60");
					pcsTlDtl.setUnaccountedtime("0");
					pcsTlDtl.setProductiontime("0");
					pcsTlDtl.setNoplaninmins(pcsTlDtl.getCalendartime());
					pcsTlDtl.setProductionavltime(pcsTlDtl.getCalendartime());
					pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
					
					
					if( i == hourlength)
					{
						int avltime=(int)(fullhour%60);
						if(avltime == 0)
						{
							pcsTlDtl.setCalendartime("60");
							pcsTlDtl.setUnaccountedtime("0");
							pcsTlDtl.setProductiontime("0");
							pcsTlDtl.setNoplaninmins(pcsTlDtl.getCalendartime());
							pcsTlDtl.setProductionavltime(pcsTlDtl.getCalendartime());
							pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
						}
						else
						{
							pcsTlDtl.setCalendartime(String.valueOf(avltime));
							pcsTlDtl.setUnaccountedtime("0");
							pcsTlDtl.setProductiontime("0");
							pcsTlDtl.setNoplaninmins(pcsTlDtl.getCalendartime());
							pcsTlDtl.setProductionavltime(pcsTlDtl.getCalendartime());
							pcsTlDtl.setMchavailabletime(pcsTlDtl.getCalendartime());
						}
					}	
					*/
					newPcsTlHtDetails.setMasterid(newPcsTlHtProductionHistory.getKeyid());
					//newPcsTlHtDetails.setMachineid(String.valueOf(i));
					CommonFunctions.debugMsg("in side production data  "+ detailTableName +"  "+ pcsTlHtDtlSql.getPcsDbFields()+"   "+ newPcsTlHtDetails.getSaveArray());
					newPcsTlHtDetails.setKeyid(dbActionTemplate.getSequenceNumber(BAL_PcsTlHtDetailsSql.TBL_PCS_TL_HTPLDETAILS,  15, "HTD", "YY","Y"));
					sqls.add(BAL_PcsTlDtlSql.getInsertSql(detailTableName, pcsTlHtDtlSql.getPcsDbFields(), newPcsTlHtDetails.getSaveArray()));// add insert sql for detail table
					/*///??
					newPscTlProductiondata.setPprdKeyid(dbActionTemplate.getSequenceNumber(PcsTlProductiondataSql.TBL_PCS_TL_PRODUCTIONDATA)); // set the sequnce number 
					//newPscTlProductiondata.setPprdHour(String.valueOf(i));
					newPscTlProductiondata.setPprdShiftnoplan("Y");
					newPscTlProductiondata.setPprdTempfield1(newPcsTlHtDetails.getKeyid());
					newPscTlProductiondata.setPprdTempfield2(newPcsTlHtDetails.getKeyid());*/
					sqls.add(BAL_PcsTlHtDetailsSql.getInsertSql(pcsTlHtDtlSql.getPcsDbFields(), newPcsTlHtDetails.getSaveArray())); // add insert sql for master table
					
				
				CommonFunctions.debugMsg("newPscTlProductiondata    00"+newPcsTlHtDetails);
			}
		return null;
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return newPcsTlHtDetails;
	
	}

	/*
	 * @Override public BAL_PcsTlProductiondata createNew(BAL_PcsTlProductiondata
	 * newPscTlProductiondata,BAL_PcsTlHtProductionHistory
	 * newPcsTlHtProductionHistory, BAL_PcsTlHtDetails newPcsTlHtDetails)throws
	 * Exception { // TODO Auto-generated method stub return null; }
	 */

	/*
	 * @Override public com.akranta.tpm.model.PcsTlProductiondata createNew(
	 * com.akranta.tpm.model.PcsTlProductiondata newPscTlProductiondata,
	 * PcsTlHtProductionHistory newPcsTlHtProductionHistory, PcsTlHtDetails
	 * newPcsTlHtDetails) throws Exception { // TODO Auto-generated method stub
	 * return null; }
	 */
	
}

