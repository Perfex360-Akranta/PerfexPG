package com.akranta.tpm.dao.sql;

import com.akranta.tpm.dao.sql.BAL_PcsTlMstSql.tableFldConstants;

public class BAL_PcsTlHtDetailsSql {
	/*
	 ------------------------------ -------- --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
HTPLDETAILSID                  NOT NULL VARCHAR2(15)                                                                                                                                                                                  
PLMASTERID                     NOT NULL VARCHAR2(15)                                                                                                                                                                                  
AREA                           NOT NULL VARCHAR2(15)                                                                                                                                                                                  
OPERATORS                      NOT NULL NUMBER                                                                                                                                                                                        
CALENDARTIME            NUMBER                                                                                                                                                                                        
PLANNEDQTY                              NUMBER                                                                                                                                                                                        
UNACCOUNTEDTIME                         NUMBER                                                                                                                                                                                        
NOOFPRODUCTS                            NUMBER                                                                                                                                                                                        
PRODUCTID                      NOT NULL VARCHAR2(15)                                                                                                                                                                                  
NOPLANINMINS                            NUMBER                                                                                                                                                                                        
RNT                                     NUMBER                                                                                                                                                                                        
CELLMANNING                             VARCHAR2(10)                                                                                                                                                                                  
PRODUCEDQTY                             NUMBER                                                                                                                                                                                        
PROCESSSTOPPAGE                         NUMBER                                                                                                                                                                                        
ACTUALCHARGES                           NUMBER                                                                                                                                                                                        
CALCULATEDCHARGE                        NUMBER                                                                                                                                                                                        
DEFECTSANDREWORKLOSS_ML                 NUMBER                                                                                                                                                                                        
REJECTEDQTY                             NUMBER                                                                                                                                                                                        
REWORKQTY                               NUMBER                                                                                                                                                                                        
REPROCESSQTY                            NUMBER                                                                                                                                                                                        
PRODUCTIONLOSSES                        NUMBER                                                                                                                                                                                        
EQUIPMENTFAILURE_ML                     NUMBER                                                                                                                                                                                        
EF_MECHANICAL                           NUMBER                                                                                                                                                                                        
EF_ELECTRICAL                           NUMBER                                                                                                                                                                                        
EF_ELECTRONICS                          NUMBER                                                                                                                                                                                        
EF_JIGFIXTURELOSS                       NUMBER                                                                                                                                                                                        
STARTUPLOSS_ML                          NUMBER                                                                                                                                                                                        
MINORSTOPPAGELOSS_ML                    NUMBER                                                                                                                                                                                        
SPEEDLOSS_ML                            NUMBER                                                                                                                                                                                        
SHUTDOWNLOSS_ML                         NUMBER                                                                                                                                                                                        
PMTIME                                  NUMBER                                                                                                                                                                                        
JHTIME                                  NUMBER                                                                                                                                                                                        
MEETINGS                                NUMBER                                                                                                                                                                                        
TRAINING                                NUMBER                                                                                                                                                                                        
BREAKS                                  NUMBER                                                                                                                                                                                        
NEWPRODDEV                              NUMBER                                                                                                                                                                                        
MANAGEMENTLOSS_ML                       NUMBER                                                                                                                                                                                        
NOMATERIALPPC                           NUMBER                                                                                                                                                                                        
NOMATERIALFACTORY                       VARCHAR2(10)                                                                                                                                                                                  
NOPLANFURRUNNIG        NUMBER                                                                                                                                                                                        
NOTOOL                 NUMBER                                                                                                                                                                                        
NOMANPOWER             NUMBER                                                                                                                                                                                        
LINECHANGEOVER          NUMBER                                                                                                                                                                                        
COMMONUTILITYLOSS_ML    NUMBER                                                                                                                                                                                        
NOPOWER                 NUMBER                                                                                                                                                                                        
NOAIR                   NUMBER                                                                                                                                                                                        
NOWATER                 NUMBER                                                                                                                                                                                        
NOGAS                   NUMBER                                                                                                                                                                                        
NOCOOLANT               NUMBER                                                                                                                                                                                        
LOGISTICSLOSS_ML        NUMBER                                                                                                                                                                                        
THEORITICALCYCLETIME    NUMBER                                                                                                                                                                                        
ACTUALCYCLETIME         NUMBER                                                                                                                                                                                        
MCHAVAILABLETIME        NUMBER                                                                                                                                                                                        
PRODUCTIONAVLTIME       NUMBER                                                                                                                                                                                        
PRODUCTIONTIME          NUMBER                                                                                                                                                                                        
CYCLETIMEVARIATION      NUMBER                                                                                          NUMBER                                                        
LOADINGTIME             NUMBER                                                                                                                                                                                        
EFFPRODMINS             NUMBER                                                                                                                                                                                        
ROA                     NUMBER                                                                                                                                                                                        
ROP                     NUMBER                                                                                                                                                                                        
ROQ                     NUMBER                                                                                                                                                                                        
OEE                     NUMBER                                                                                                                                                                                        
REMARKS                        NOT NULL VARCHAR2(1000)                                                                                                                                                                                
PRODUCTIONTIMELOSS      NUMBER                                                                                                                                                                                        
TEMPFIELD1                              VARCHAR2(500)                                                                                                                                                                                 
TEMPFIELD2                              VARCHAR2(500)                                                                                                                                                                                 
TEMPFIELD3                              VARCHAR2(500)                                                                                                                                                                                 
TEMPFIELD4                              VARCHAR2(500)                                                                                                                                                                                 
COMPLETEDFLAG                           CHAR(1)                                                                                                                                                                                       
ACTIVE                                  CHAR(1)                                                                                                                                                                                       
CREATEDBY                               VARCHAR2(10)                                                                                                                                                                                  
CREATEDON                      NOT NULL DATE                                                                                                                                                                                          
MODIFIEDON                     NOT NULL DATE 
	 */
	
	
	public static final String TBL_PCS_TL_HTPLDETAILS = "PCS_TL_HTPLDETAILS";

	TableFieldType [] plosDbFields = null;	

	public enum   tableFldConstants
	{
		htpdetailsid, plmasterid, area, manpower,  calendartime, planedQty,uncountedTime,noOfProduct,productid,noPlanMins,
		rnt, cellmaning, prodecdQty,processStopage,actualCharges,calculatedCharges,defectstandardworkLoss,rejectedQty,reworkQty,
		reprocessQty,productionLoss,equipmentFailuare_ml,Eq_mechanical,Eq_electrical,Eq_electronics,Eq_jigfixture,startupLoss,jhTime,meating,
		minorstopage,speedLoss,shutDownloss,pmtime,trainig,breaks,newProdDev,mgtLoss,noMaterialPpc,noMaterialFactory,NoPlanFurning,
		notool,nomanpower,linechangeover,commonutilitiesloss,nopower,noAir,noGas,noCoolent,logisticLoss,theoroticalCycletime,actualCycletime,
		mchAvailabletime,productavailabetime,productiontime,cycletimevariation,loadingtime,effprodmins,ROA,ROP,ROQ,OEE,remarks,productiontimeloss,
		temp1,temp2,temp3,temp4,active, createdby, createdon, modifiedon,completedFlag,nowater
	}
	
	public TableFieldType[] getPcsDbFields() {
		return plosDbFields;
	}
	public BAL_PcsTlHtDetailsSql()
	{
		plosDbFields = new TableFieldType[ 113 ];
		for(int i = 0;i < 113; i++)
		{	
			plosDbFields[ i ] = new TableFieldType();
			
    }
		plosDbFields[ tableFldConstants.htpdetailsid.ordinal() ].fieldName = "HTPLDETAILSID";
		plosDbFields[ tableFldConstants.htpdetailsid.ordinal() ].fieldType = 'V';
		
		plosDbFields[ tableFldConstants.plmasterid.ordinal() ].fieldName = "PLMASTERID";
		plosDbFields[ tableFldConstants.plmasterid.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.area.ordinal() ].fieldName = "AREA";
		plosDbFields[ tableFldConstants.htpdetailsid.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.manpower.ordinal() ].fieldName = "OPERATORS";
		plosDbFields[ tableFldConstants.manpower.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.calendartime.ordinal() ].fieldName = "CALENDARTIME";
		plosDbFields[ tableFldConstants.calendartime.ordinal() ].fieldType = 'N';
		
		plosDbFields[ tableFldConstants.planedQty.ordinal() ].fieldName = "PLANNEDQTY";
		plosDbFields[ tableFldConstants.planedQty.ordinal() ].fieldType = 'N';
		
		plosDbFields[ tableFldConstants.uncountedTime.ordinal() ].fieldName = "UNACCOUNTEDTIME";
		plosDbFields[ tableFldConstants.uncountedTime.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.noOfProduct.ordinal() ].fieldName = "NOOFPRODUCTS";
		plosDbFields[ tableFldConstants.noOfProduct.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.productid.ordinal() ].fieldName = "PRODUCTID";
		plosDbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.noPlanMins.ordinal() ].fieldName = "NOPLANINMINS";
		plosDbFields[ tableFldConstants.noPlanMins.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.rnt.ordinal() ].fieldName = "RNT";
		plosDbFields[ tableFldConstants.rnt.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.cellmaning.ordinal() ].fieldName = "CELLMANNING";
		plosDbFields[ tableFldConstants.cellmaning.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.prodecdQty.ordinal() ].fieldName = "PRODUCEDQTY";
		plosDbFields[ tableFldConstants.prodecdQty.ordinal() ].fieldType = 'N';
		
		plosDbFields[ tableFldConstants.processStopage.ordinal() ].fieldName = "PROCESSSTOPPAGE";
		plosDbFields[ tableFldConstants.processStopage.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.actualCharges.ordinal() ].fieldName = "ACTUALCHARGES";
		plosDbFields[ tableFldConstants.actualCharges.ordinal() ].fieldType = 'N';
		
		plosDbFields[ tableFldConstants.calculatedCharges.ordinal() ].fieldName = "CALCULATEDCHARGE";
		plosDbFields[ tableFldConstants.calculatedCharges.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.defectstandardworkLoss.ordinal() ].fieldName = "DEFECTSANDREWORKLOSS_ML";
		plosDbFields[ tableFldConstants.defectstandardworkLoss.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.rejectedQty.ordinal() ].fieldName = "REJECTEDQTY";
		plosDbFields[ tableFldConstants.rejectedQty.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.reworkQty.ordinal() ].fieldName = "REWORKQTY";
		plosDbFields[ tableFldConstants.reworkQty.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.reprocessQty.ordinal() ].fieldName = "REPROCESSQTY";
		plosDbFields[ tableFldConstants.reprocessQty.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.productionLoss.ordinal() ].fieldName = "PRODUCTIONLOSSES";
		plosDbFields[ tableFldConstants.productionLoss.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.equipmentFailuare_ml.ordinal() ].fieldName = "EQUIPMENTFAILURE_ML";
		plosDbFields[ tableFldConstants.equipmentFailuare_ml.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.Eq_mechanical.ordinal() ].fieldName = "EF_MECHANICAL";
		plosDbFields[ tableFldConstants.Eq_mechanical.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.Eq_electrical.ordinal() ].fieldName = "EF_ELECTRICAL";
		plosDbFields[ tableFldConstants.Eq_electrical.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.Eq_electronics.ordinal() ].fieldName = "EF_ELECTRONICS";
		plosDbFields[ tableFldConstants.Eq_electronics.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.Eq_jigfixture.ordinal() ].fieldName = "EF_JIGFIXTURELOSS";
		plosDbFields[ tableFldConstants.Eq_jigfixture.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.startupLoss.ordinal() ].fieldName = "STARTUPLOSS_ML";
		plosDbFields[ tableFldConstants.startupLoss.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.minorstopage.ordinal() ].fieldName = "MINORSTOPPAGELOSS_ML";
		plosDbFields[ tableFldConstants.minorstopage.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.speedLoss.ordinal() ].fieldName = "SPEEDLOSS_ML";
		plosDbFields[ tableFldConstants.speedLoss.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.shutDownloss.ordinal() ].fieldName = "SHUTDOWNLOSS_ML";
		plosDbFields[ tableFldConstants.shutDownloss.ordinal() ].fieldType = 'N';
		
		plosDbFields[ tableFldConstants.pmtime.ordinal() ].fieldName = "PMTIME";
		plosDbFields[ tableFldConstants.pmtime.ordinal() ].fieldType = 'N';		
		plosDbFields[ tableFldConstants.actualCycletime.ordinal() ].fieldName = "ACTUALCYCLETIME";
		plosDbFields[ tableFldConstants.actualCycletime.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.mchAvailabletime.ordinal() ].fieldName = "MCHAVAILABLETIME";
		plosDbFields[ tableFldConstants.mchAvailabletime.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.productavailabetime.ordinal() ].fieldName = "PRODUCTIONAVLTIME";
		plosDbFields[ tableFldConstants.productavailabetime.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.productiontime.ordinal() ].fieldName = "PRODUCTIONTIME";
		plosDbFields[ tableFldConstants.productiontime.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.cycletimevariation.ordinal() ].fieldName = "CYCLETIMEVARIATION";
		plosDbFields[ tableFldConstants.cycletimevariation.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.loadingtime.ordinal() ].fieldName = "LOADINGTIME";
		plosDbFields[ tableFldConstants.loadingtime.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.effprodmins.ordinal() ].fieldName = "EFFPRODMINS";
		plosDbFields[ tableFldConstants.effprodmins.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.ROA.ordinal() ].fieldName = "ROA";
		plosDbFields[ tableFldConstants.ROA.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.ROP.ordinal() ].fieldName = "ROP";
		plosDbFields[ tableFldConstants.ROP.ordinal() ].fieldType = 'N';		
		plosDbFields[ tableFldConstants.OEE.ordinal() ].fieldName = "OEE";
		plosDbFields[ tableFldConstants.OEE.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "REMARKS";
		plosDbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.productiontimeloss.ordinal() ].fieldName = "PRODUCTIONTIMELOSS";
		plosDbFields[ tableFldConstants.productiontimeloss.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.temp1.ordinal() ].fieldName = "TEMPFIELD1";
		plosDbFields[ tableFldConstants.temp1.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.temp2.ordinal() ].fieldName = "TEMPFIELD2";
		plosDbFields[ tableFldConstants.temp2.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.temp3.ordinal() ].fieldName = "TEMPFIELD3";
		plosDbFields[ tableFldConstants.temp3.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.temp4.ordinal() ].fieldName = "TEMPFIELD4";
		plosDbFields[ tableFldConstants.temp4.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.completedFlag.ordinal() ].fieldName = "COMPLETEDFLAG";
		plosDbFields[ tableFldConstants.completedFlag.ordinal() ].fieldType = 'C';
		
		plosDbFields[ tableFldConstants.active.ordinal() ].fieldName = "ACTIVE";
		plosDbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';
		plosDbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CREATEDBY";
		plosDbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';
		plosDbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CREATEDON";
		plosDbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';
		plosDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MODIFIEDON";
		plosDbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';
		
		plosDbFields[ tableFldConstants.jhTime.ordinal() ].fieldName = "JHTIME";
		plosDbFields[ tableFldConstants.jhTime.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.meating.ordinal() ].fieldName = "MEETINGS";
		plosDbFields[ tableFldConstants.meating.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.trainig.ordinal() ].fieldName = "TRAINING";
		plosDbFields[ tableFldConstants.trainig.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.breaks.ordinal() ].fieldName = "BREAKS";
		plosDbFields[ tableFldConstants.breaks.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.newProdDev.ordinal() ].fieldName = "NEWPRODDEV";
		plosDbFields[ tableFldConstants.newProdDev.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.mgtLoss.ordinal() ].fieldName = "MANAGEMENTLOSS_ML";
		plosDbFields[ tableFldConstants.mgtLoss.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.noMaterialPpc.ordinal() ].fieldName = "NOMATERIALPPC";
		plosDbFields[ tableFldConstants.noMaterialPpc.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.noMaterialFactory.ordinal() ].fieldName = "NOMATERIALFACTORY";
		plosDbFields[ tableFldConstants.noMaterialFactory.ordinal() ].fieldType = 'V';
		
		plosDbFields[ tableFldConstants.NoPlanFurning.ordinal() ].fieldName = "NOPLANFURRUNNIG";
		plosDbFields[ tableFldConstants.NoPlanFurning.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.notool.ordinal() ].fieldName = "NOTOOL";
		plosDbFields[ tableFldConstants.notool.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.nomanpower.ordinal() ].fieldName = "NOMANPOWER";
		plosDbFields[ tableFldConstants.nomanpower.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.linechangeover.ordinal() ].fieldName = "LINECHANGEOVER";
		plosDbFields[ tableFldConstants.linechangeover.ordinal() ].fieldType = 'N';
		
		plosDbFields[ tableFldConstants.commonutilitiesloss.ordinal() ].fieldName = "COMMONUTILITYLOSS_ML";
		plosDbFields[ tableFldConstants.commonutilitiesloss.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.nopower.ordinal() ].fieldName = "NOPOWER";
		plosDbFields[ tableFldConstants.nopower.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.noAir.ordinal() ].fieldName = "NOAIR";
		plosDbFields[ tableFldConstants.noAir.ordinal() ].fieldType = 'N';
			
		plosDbFields[ tableFldConstants.nowater.ordinal() ].fieldName = "NOWATER";
		plosDbFields[ tableFldConstants.nowater.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.noGas.ordinal() ].fieldName = "NOGAS";
		plosDbFields[ tableFldConstants.noGas.ordinal() ].fieldType = 'N';
		plosDbFields[ tableFldConstants.noCoolent.ordinal() ].fieldName = "NOCOOLANT";
		plosDbFields[ tableFldConstants.noCoolent.ordinal() ].fieldType = 'N';
				
		
	}
	public static String getInsertSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		return SqlUtils.getInsertSql(TBL_PCS_TL_HTPLDETAILS, fieldTypeArr, dataArray);
	}

	public static String getUpdateSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_HTPLDETAILS, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.htpdetailsid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.htpdetailsid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_HTPLDETAILS ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.htpdetailsid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.htpdetailsid.ordinal()] + "'";
		return sql;
	}
}
