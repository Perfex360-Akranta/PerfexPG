package com.akranta.tpm.dao.sql;

public class PcsTlDtlSql {

	public static final String TBL_PCS_TL_DTL = "PCS_TL_DTL";  

	TableFieldType [] pcs_DbFields = null;

	public enum   tableFldConstants
	{
		pldetailsid, plmasterid, cellid, machineid, operators, calendartime
		, noplaninmins, noofproducts, productid, plannedqty, rawmaterialtype, weight
		, wno, operationno, operationdescription, theoriticalcycletime
		, actualcycletime, batchno, cavityavailable, cavityused, mandrelavailable, mandrelused, producedqty, defectsandreworkloss_ml, rejectedqty, reworkqty, defecttime_sl
		, productionlosses, equipmentfailure_ml, setupandadjustment_ml, toolchangeloss_ml, startuploss_ml
		, minorstoppageloss_ml, speedloss_ml, shutdownloss_ml, managementloss_ml, commonutilityloss_sl, operatingmotionloss_ml
		, lineorganisationloss_ml, logisticsloss_ml, measuringandadjloss_ml, dietoolandjigloss_ml, energyloss_ml, yieldloss_ml
		, unaccountedtime, loadingtime, effectiveprodmins
		, mchavailabletime, productionavltime, productiontime, roa, rop
		, roq, oee, completedflag, remarks, loss01, loss02, loss03, loss04
		, loss05, loss06, loss07, loss08, loss09, loss10, loss11, loss12
		, loss13, loss14, loss15, loss16, loss17, loss18, loss19, loss20
		, loss21, loss22, loss23, loss24, loss25, loss26, loss27, loss28
		, loss29, loss30, loss31, loss32, loss33, loss34, loss35, loss36
		, loss37, loss38, loss39, loss40, loss41, loss42, loss43, loss44
		, loss45, loss46, loss47, loss48, loss49, loss50, loss51, loss52
		, loss53, loss54, loss55, loss56, loss57, loss58, loss59, loss60
		, loss61, loss62, loss63, loss64, loss65, loss66, loss67, loss68, loss69, loss70
		, loss71, loss72, loss73, loss74, loss75, loss76, loss77, loss78, loss79, loss80
		, loss81, loss82, loss83, loss84, loss85, loss86, loss87, loss88, loss89, loss90
		, loss91, loss92, loss93, loss94, loss95, loss96, loss97, trimmingqty, expansionqty		
		, modelchangepart, reprocessing, INSPECTEDQTY
		, QAACCEPTEDQTY, backlogqty, active, createdby, createdon, modifiedon
	}

	public TableFieldType[] getPcs_DbFields() {
		return pcs_DbFields;
	}

	public PcsTlDtlSql()
	{
		pcs_DbFields = new TableFieldType[ 164 ];
		for(int i = 0;i < 164; i++)
		{	
			pcs_DbFields[ i ] = new TableFieldType();
		}
		pcs_DbFields[ tableFldConstants.pldetailsid.ordinal() ].fieldName = "PLDETAILSID";
		pcs_DbFields[ tableFldConstants.pldetailsid.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.plmasterid.ordinal() ].fieldName = "PLMASTERID";
		pcs_DbFields[ tableFldConstants.plmasterid.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.cellid.ordinal() ].fieldName = "CELLID";
		pcs_DbFields[ tableFldConstants.cellid.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.machineid.ordinal() ].fieldName = "MACHINEID";
		pcs_DbFields[ tableFldConstants.machineid.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.operators.ordinal() ].fieldName = "OPERATORS";
		pcs_DbFields[ tableFldConstants.operators.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.calendartime.ordinal() ].fieldName = "CALENDARTIME";
		pcs_DbFields[ tableFldConstants.calendartime.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.noplaninmins.ordinal() ].fieldName = "NOPLANINMINS";
		pcs_DbFields[ tableFldConstants.noplaninmins.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.noofproducts.ordinal() ].fieldName = "NOOFPRODUCTS";
		pcs_DbFields[ tableFldConstants.noofproducts.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.productid.ordinal() ].fieldName = "PRODUCTID";
		pcs_DbFields[ tableFldConstants.productid.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.plannedqty.ordinal() ].fieldName = "PLANNEDQTY";
		pcs_DbFields[ tableFldConstants.plannedqty.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.rawmaterialtype.ordinal() ].fieldName = "RAWMATERIALTYPE";
		pcs_DbFields[ tableFldConstants.rawmaterialtype.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.weight.ordinal() ].fieldName = "WEIGHT";
		pcs_DbFields[ tableFldConstants.weight.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.wno.ordinal() ].fieldName = "WNO";
		pcs_DbFields[ tableFldConstants.wno.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.operationno.ordinal() ].fieldName = "OPERATIONNO";
		pcs_DbFields[ tableFldConstants.operationno.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.operationdescription.ordinal() ].fieldName = "OPERATIONDESCRIPTION";
		pcs_DbFields[ tableFldConstants.operationdescription.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.theoriticalcycletime.ordinal() ].fieldName = "THEORITICALCYCLETIME";
		pcs_DbFields[ tableFldConstants.theoriticalcycletime.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.actualcycletime.ordinal() ].fieldName = "ACTUALCYCLETIME";
		pcs_DbFields[ tableFldConstants.actualcycletime.ordinal() ].fieldType = 'N';


		pcs_DbFields[ tableFldConstants.batchno.ordinal() ].fieldName = "BATCHNO";
		pcs_DbFields[ tableFldConstants.batchno.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.cavityavailable.ordinal() ].fieldName = "CAVITYAVAILABLE";
		pcs_DbFields[ tableFldConstants.cavityavailable.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.cavityused.ordinal() ].fieldName = "CAVITYUSED";
		pcs_DbFields[ tableFldConstants.cavityused.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.mandrelavailable.ordinal() ].fieldName = "MANDRELAVAILABLE";
		pcs_DbFields[ tableFldConstants.mandrelavailable.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.mandrelused.ordinal() ].fieldName = "MANDRELUSED";
		pcs_DbFields[ tableFldConstants.mandrelused.ordinal() ].fieldType = 'N';
	
		
		pcs_DbFields[ tableFldConstants.producedqty.ordinal() ].fieldName = "PRODUCEDQTY";
		pcs_DbFields[ tableFldConstants.producedqty.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.defectsandreworkloss_ml.ordinal() ].fieldName = "DEFECTSANDREWORKLOSS_ML";
		pcs_DbFields[ tableFldConstants.defectsandreworkloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.rejectedqty.ordinal() ].fieldName = "REJECTEDQTY";
		pcs_DbFields[ tableFldConstants.rejectedqty.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.reworkqty.ordinal() ].fieldName = "REWORKQTY";
		pcs_DbFields[ tableFldConstants.reworkqty.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.defecttime_sl.ordinal() ].fieldName = "DEFECTTIME_SL";
		pcs_DbFields[ tableFldConstants.defecttime_sl.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.productionlosses.ordinal() ].fieldName = "PRODUCTIONLOSSES";
		pcs_DbFields[ tableFldConstants.productionlosses.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.equipmentfailure_ml.ordinal() ].fieldName = "EQUIPMENTFAILURE_ML";
		pcs_DbFields[ tableFldConstants.equipmentfailure_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.setupandadjustment_ml.ordinal() ].fieldName = "SETUPANDADJUSTMENT_ML";
		pcs_DbFields[ tableFldConstants.setupandadjustment_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.toolchangeloss_ml.ordinal() ].fieldName = "TOOLCHANGELOSS_ML";
		pcs_DbFields[ tableFldConstants.toolchangeloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.startuploss_ml.ordinal() ].fieldName = "STARTUPLOSS_ML";
		pcs_DbFields[ tableFldConstants.startuploss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.minorstoppageloss_ml.ordinal() ].fieldName = "MINORSTOPPAGELOSS_ML";
		pcs_DbFields[ tableFldConstants.minorstoppageloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.speedloss_ml.ordinal() ].fieldName = "SPEEDLOSS_ML";
		pcs_DbFields[ tableFldConstants.speedloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.shutdownloss_ml.ordinal() ].fieldName = "SHUTDOWNLOSS_ML";
		pcs_DbFields[ tableFldConstants.shutdownloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.managementloss_ml.ordinal() ].fieldName = "MANAGEMENTLOSS_ML";
		pcs_DbFields[ tableFldConstants.managementloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.commonutilityloss_sl.ordinal() ].fieldName = "COMMONUTILITYLOSS_SL";
		pcs_DbFields[ tableFldConstants.commonutilityloss_sl.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.operatingmotionloss_ml.ordinal() ].fieldName = "OPERATINGMOTIONLOSS_ML";
		pcs_DbFields[ tableFldConstants.operatingmotionloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.lineorganisationloss_ml.ordinal() ].fieldName = "LINEORGANISATIONLOSS_ML";
		pcs_DbFields[ tableFldConstants.lineorganisationloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.logisticsloss_ml.ordinal() ].fieldName = "LOGISTICSLOSS_ML";
		pcs_DbFields[ tableFldConstants.logisticsloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.measuringandadjloss_ml.ordinal() ].fieldName = "MEASURINGANDADJLOSS_ML";
		pcs_DbFields[ tableFldConstants.measuringandadjloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.dietoolandjigloss_ml.ordinal() ].fieldName = "DIETOOLANDJIGLOSS_ML";
		pcs_DbFields[ tableFldConstants.dietoolandjigloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.energyloss_ml.ordinal() ].fieldName = "ENERGYLOSS_ML";
		pcs_DbFields[ tableFldConstants.energyloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.yieldloss_ml.ordinal() ].fieldName = "YIELDLOSS_ML";
		pcs_DbFields[ tableFldConstants.yieldloss_ml.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.unaccountedtime.ordinal() ].fieldName = "UNACCOUNTEDTIME";
		pcs_DbFields[ tableFldConstants.unaccountedtime.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loadingtime.ordinal() ].fieldName = "LOADINGTIME";
		pcs_DbFields[ tableFldConstants.loadingtime.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.effectiveprodmins.ordinal() ].fieldName = "EFFECTIVEPRODMINS";
		pcs_DbFields[ tableFldConstants.effectiveprodmins.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.mchavailabletime.ordinal() ].fieldName = "MCHAVAILABLETIME";
		pcs_DbFields[ tableFldConstants.mchavailabletime.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.productionavltime.ordinal() ].fieldName = "PRODUCTIONAVLTIME";
		pcs_DbFields[ tableFldConstants.productionavltime.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.productiontime.ordinal() ].fieldName = "PRODUCTIONTIME";
		pcs_DbFields[ tableFldConstants.productiontime.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.roa.ordinal() ].fieldName = "ROA";
		pcs_DbFields[ tableFldConstants.roa.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.rop.ordinal() ].fieldName = "ROP";
		pcs_DbFields[ tableFldConstants.rop.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.roq.ordinal() ].fieldName = "ROQ";
		pcs_DbFields[ tableFldConstants.roq.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.oee.ordinal() ].fieldName = "OEE";
		pcs_DbFields[ tableFldConstants.oee.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.completedflag.ordinal() ].fieldName = "COMPLETEDFLAG";
		pcs_DbFields[ tableFldConstants.completedflag.ordinal() ].fieldType = 'C';

		pcs_DbFields[ tableFldConstants.remarks.ordinal() ].fieldName = "REMARKS";
		pcs_DbFields[ tableFldConstants.remarks.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.loss01.ordinal() ].fieldName = "LOSS01";
		pcs_DbFields[ tableFldConstants.loss01.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss02.ordinal() ].fieldName = "LOSS02";
		pcs_DbFields[ tableFldConstants.loss02.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss03.ordinal() ].fieldName = "LOSS03";
		pcs_DbFields[ tableFldConstants.loss03.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss04.ordinal() ].fieldName = "LOSS04";
		pcs_DbFields[ tableFldConstants.loss04.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss05.ordinal() ].fieldName = "LOSS05";
		pcs_DbFields[ tableFldConstants.loss05.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss06.ordinal() ].fieldName = "LOSS06";
		pcs_DbFields[ tableFldConstants.loss06.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss07.ordinal() ].fieldName = "LOSS07";
		pcs_DbFields[ tableFldConstants.loss07.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss08.ordinal() ].fieldName = "LOSS08";
		pcs_DbFields[ tableFldConstants.loss08.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss09.ordinal() ].fieldName = "LOSS09";
		pcs_DbFields[ tableFldConstants.loss09.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss10.ordinal() ].fieldName = "LOSS10";
		pcs_DbFields[ tableFldConstants.loss10.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss11.ordinal() ].fieldName = "LOSS11";
		pcs_DbFields[ tableFldConstants.loss11.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss12.ordinal() ].fieldName = "LOSS12";
		pcs_DbFields[ tableFldConstants.loss12.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss13.ordinal() ].fieldName = "LOSS13";
		pcs_DbFields[ tableFldConstants.loss13.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss14.ordinal() ].fieldName = "LOSS14";
		pcs_DbFields[ tableFldConstants.loss14.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss15.ordinal() ].fieldName = "LOSS15";
		pcs_DbFields[ tableFldConstants.loss15.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss16.ordinal() ].fieldName = "LOSS16";
		pcs_DbFields[ tableFldConstants.loss16.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss17.ordinal() ].fieldName = "LOSS17";
		pcs_DbFields[ tableFldConstants.loss17.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss18.ordinal() ].fieldName = "LOSS18";
		pcs_DbFields[ tableFldConstants.loss18.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss19.ordinal() ].fieldName = "LOSS19";
		pcs_DbFields[ tableFldConstants.loss19.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss20.ordinal() ].fieldName = "LOSS20";
		pcs_DbFields[ tableFldConstants.loss20.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss21.ordinal() ].fieldName = "LOSS21";
		pcs_DbFields[ tableFldConstants.loss21.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss22.ordinal() ].fieldName = "LOSS22";
		pcs_DbFields[ tableFldConstants.loss22.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss23.ordinal() ].fieldName = "LOSS23";
		pcs_DbFields[ tableFldConstants.loss23.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss24.ordinal() ].fieldName = "LOSS24";
		pcs_DbFields[ tableFldConstants.loss24.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss25.ordinal() ].fieldName = "LOSS25";
		pcs_DbFields[ tableFldConstants.loss25.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss26.ordinal() ].fieldName = "LOSS26";
		pcs_DbFields[ tableFldConstants.loss26.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss27.ordinal() ].fieldName = "LOSS27";
		pcs_DbFields[ tableFldConstants.loss27.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss28.ordinal() ].fieldName = "LOSS28";
		pcs_DbFields[ tableFldConstants.loss28.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss29.ordinal() ].fieldName = "LOSS29";
		pcs_DbFields[ tableFldConstants.loss29.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss30.ordinal() ].fieldName = "LOSS30";
		pcs_DbFields[ tableFldConstants.loss30.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss31.ordinal() ].fieldName = "LOSS31";
		pcs_DbFields[ tableFldConstants.loss31.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss32.ordinal() ].fieldName = "LOSS32";
		pcs_DbFields[ tableFldConstants.loss32.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss33.ordinal() ].fieldName = "LOSS33";
		pcs_DbFields[ tableFldConstants.loss33.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss34.ordinal() ].fieldName = "LOSS34";
		pcs_DbFields[ tableFldConstants.loss34.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss35.ordinal() ].fieldName = "LOSS35";
		pcs_DbFields[ tableFldConstants.loss35.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss36.ordinal() ].fieldName = "LOSS36";
		pcs_DbFields[ tableFldConstants.loss36.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss37.ordinal() ].fieldName = "LOSS37";
		pcs_DbFields[ tableFldConstants.loss37.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss38.ordinal() ].fieldName = "LOSS38";
		pcs_DbFields[ tableFldConstants.loss38.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss39.ordinal() ].fieldName = "LOSS39";
		pcs_DbFields[ tableFldConstants.loss39.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss40.ordinal() ].fieldName = "LOSS40";
		pcs_DbFields[ tableFldConstants.loss40.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss41.ordinal() ].fieldName = "LOSS41";
		pcs_DbFields[ tableFldConstants.loss41.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss42.ordinal() ].fieldName = "LOSS42";
		pcs_DbFields[ tableFldConstants.loss42.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss43.ordinal() ].fieldName = "LOSS43";
		pcs_DbFields[ tableFldConstants.loss43.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss44.ordinal() ].fieldName = "LOSS44";
		pcs_DbFields[ tableFldConstants.loss44.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss45.ordinal() ].fieldName = "LOSS45";
		pcs_DbFields[ tableFldConstants.loss45.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss46.ordinal() ].fieldName = "LOSS46";
		pcs_DbFields[ tableFldConstants.loss46.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss47.ordinal() ].fieldName = "LOSS47";
		pcs_DbFields[ tableFldConstants.loss47.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss48.ordinal() ].fieldName = "LOSS48";
		pcs_DbFields[ tableFldConstants.loss48.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss49.ordinal() ].fieldName = "LOSS49";
		pcs_DbFields[ tableFldConstants.loss49.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss50.ordinal() ].fieldName = "LOSS50";
		pcs_DbFields[ tableFldConstants.loss50.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss51.ordinal() ].fieldName = "LOSS51";
		pcs_DbFields[ tableFldConstants.loss51.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss52.ordinal() ].fieldName = "LOSS52";
		pcs_DbFields[ tableFldConstants.loss52.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss53.ordinal() ].fieldName = "LOSS53";
		pcs_DbFields[ tableFldConstants.loss53.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss54.ordinal() ].fieldName = "LOSS54";
		pcs_DbFields[ tableFldConstants.loss54.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss55.ordinal() ].fieldName = "LOSS55";
		pcs_DbFields[ tableFldConstants.loss55.ordinal() ].fieldType = 'N';

		
		pcs_DbFields[ tableFldConstants.loss56.ordinal() ].fieldName = "LOSS56";
		pcs_DbFields[ tableFldConstants.loss56.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss57.ordinal() ].fieldName = "LOSS57";
		pcs_DbFields[ tableFldConstants.loss57.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss58.ordinal() ].fieldName = "LOSS58";
		pcs_DbFields[ tableFldConstants.loss58.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss59.ordinal() ].fieldName = "LOSS59";
		pcs_DbFields[ tableFldConstants.loss59.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss60.ordinal() ].fieldName = "LOSS60";
		pcs_DbFields[ tableFldConstants.loss60.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss61.ordinal() ].fieldName = "LOSS61";
		pcs_DbFields[ tableFldConstants.loss61.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss62.ordinal() ].fieldName = "LOSS62";
		pcs_DbFields[ tableFldConstants.loss62.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss63.ordinal() ].fieldName = "LOSS63";
		pcs_DbFields[ tableFldConstants.loss63.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss64.ordinal() ].fieldName = "LOSS64";
		pcs_DbFields[ tableFldConstants.loss64.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss65.ordinal() ].fieldName = "LOSS65";
		pcs_DbFields[ tableFldConstants.loss65.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss66.ordinal() ].fieldName = "LOSS66";
		pcs_DbFields[ tableFldConstants.loss66.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss67.ordinal() ].fieldName = "LOSS67";
		pcs_DbFields[ tableFldConstants.loss67.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss68.ordinal() ].fieldName = "LOSS68";
		pcs_DbFields[ tableFldConstants.loss68.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss69.ordinal() ].fieldName = "LOSS69";
		pcs_DbFields[ tableFldConstants.loss69.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss70.ordinal() ].fieldName = "LOSS70";
		pcs_DbFields[ tableFldConstants.loss70.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss71.ordinal() ].fieldName = "LOSS71";
		pcs_DbFields[ tableFldConstants.loss71.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss72.ordinal() ].fieldName = "LOSS72";
		pcs_DbFields[ tableFldConstants.loss72.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss73.ordinal() ].fieldName = "LOSS73";
		pcs_DbFields[ tableFldConstants.loss73.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss74.ordinal() ].fieldName = "LOSS74";
		pcs_DbFields[ tableFldConstants.loss74.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss75.ordinal() ].fieldName = "LOSS75";
		pcs_DbFields[ tableFldConstants.loss75.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss76.ordinal() ].fieldName = "LOSS76";
		pcs_DbFields[ tableFldConstants.loss76.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss77.ordinal() ].fieldName = "LOSS77";
		pcs_DbFields[ tableFldConstants.loss77.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss78.ordinal() ].fieldName = "LOSS78";
		pcs_DbFields[ tableFldConstants.loss78.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss79.ordinal() ].fieldName = "LOSS79";
		pcs_DbFields[ tableFldConstants.loss79.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss80.ordinal() ].fieldName = "LOSS80";
		pcs_DbFields[ tableFldConstants.loss80.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss81.ordinal() ].fieldName = "LOSS81";
		pcs_DbFields[ tableFldConstants.loss81.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss82.ordinal() ].fieldName = "LOSS82";
		pcs_DbFields[ tableFldConstants.loss82.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss83.ordinal() ].fieldName = "LOSS83";
		pcs_DbFields[ tableFldConstants.loss83.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss84.ordinal() ].fieldName = "LOSS84";
		pcs_DbFields[ tableFldConstants.loss84.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss85.ordinal() ].fieldName = "LOSS85";
		pcs_DbFields[ tableFldConstants.loss85.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss86.ordinal() ].fieldName = "LOSS86";
		pcs_DbFields[ tableFldConstants.loss86.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss87.ordinal() ].fieldName = "LOSS87";
		pcs_DbFields[ tableFldConstants.loss87.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss88.ordinal() ].fieldName = "LOSS88";
		pcs_DbFields[ tableFldConstants.loss88.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss89.ordinal() ].fieldName = "LOSS89";
		pcs_DbFields[ tableFldConstants.loss89.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss90.ordinal() ].fieldName = "LOSS90";
		pcs_DbFields[ tableFldConstants.loss90.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss91.ordinal() ].fieldName = "LOSS91";
		pcs_DbFields[ tableFldConstants.loss91.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss92.ordinal() ].fieldName = "LOSS92";
		pcs_DbFields[ tableFldConstants.loss92.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss93.ordinal() ].fieldName = "LOSS93";
		pcs_DbFields[ tableFldConstants.loss93.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss94.ordinal() ].fieldName = "LOSS94";
		pcs_DbFields[ tableFldConstants.loss94.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss95.ordinal() ].fieldName = "LOSS95";
		pcs_DbFields[ tableFldConstants.loss95.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss96.ordinal() ].fieldName = "LOSS96";
		pcs_DbFields[ tableFldConstants.loss96.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.loss97.ordinal() ].fieldName = "LOSS97";
		pcs_DbFields[ tableFldConstants.loss97.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.trimmingqty.ordinal() ].fieldName = "TRIMMINGQTY";
		pcs_DbFields[ tableFldConstants.trimmingqty.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.expansionqty.ordinal() ].fieldName = "EXPANSIONQTY";
		pcs_DbFields[ tableFldConstants.expansionqty.ordinal() ].fieldType = 'N';

		
		pcs_DbFields[ tableFldConstants.modelchangepart.ordinal() ].fieldName = "MODELCHANGEPART";
		pcs_DbFields[ tableFldConstants.modelchangepart.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.reprocessing.ordinal() ].fieldName = "REPROCESSING";
		pcs_DbFields[ tableFldConstants.reprocessing.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.INSPECTEDQTY.ordinal() ].fieldName = "INSPECTEDQTY";
		pcs_DbFields[ tableFldConstants.INSPECTEDQTY.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.QAACCEPTEDQTY.ordinal() ].fieldName = "QAACCEPTEDQTY";
		pcs_DbFields[ tableFldConstants.QAACCEPTEDQTY.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.backlogqty.ordinal() ].fieldName = "BACKLOGQTY";
		pcs_DbFields[ tableFldConstants.backlogqty.ordinal() ].fieldType = 'N';

		pcs_DbFields[ tableFldConstants.active.ordinal() ].fieldName = "ACTIVE";
		pcs_DbFields[ tableFldConstants.active.ordinal() ].fieldType = 'C';

		pcs_DbFields[ tableFldConstants.createdby.ordinal() ].fieldName = "CREATEDBY";
		pcs_DbFields[ tableFldConstants.createdby.ordinal() ].fieldType = 'V';

		pcs_DbFields[ tableFldConstants.createdon.ordinal() ].fieldName = "CREATEDON";
		pcs_DbFields[ tableFldConstants.createdon.ordinal() ].fieldType = 'D';

		pcs_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldName = "MODIFIEDON";
		pcs_DbFields[ tableFldConstants.modifiedon.ordinal() ].fieldType = 'D';

	}

	public static String getInsertSql(String detailTableName, TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//return SqlUtils.getInsertSql(TBL_PCS_TL_DTL, fieldTypeArr, dataArray);
		return SqlUtils.getInsertSql(detailTableName, fieldTypeArr, dataArray);
	}

	
	public static String getSingleUpdtSql(String detailTableName,String Pldetailsid,  TableFieldType [] fieldTypeArr, Object [] dataArray, String lossId, String lossMapId, String lossColName, String lossValue)
	{
		//return SqlUtils.getInsertSql(TBL_PCS_TL_DTL, fieldTypeArr, dataArray);
		
		String sql = SqlUtils.getSingleUpdt(detailTableName, Pldetailsid, fieldTypeArr, lossId,lossMapId,lossColName,lossValue);
		sql += " where " + fieldTypeArr[tableFldConstants.pldetailsid.ordinal()].fieldName  +
		  " = '" +  Pldetailsid  + "'";
		return sql;
		
		
		
	}	
	public static String getUpdateSql(String detailTableName, TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		//String sql = SqlUtils.getUpdateSql(TBL_PCS_TL_DTL, fieldTypeArr, dataArray);
		String sql = SqlUtils.getUpdatePcsSql(detailTableName, fieldTypeArr, dataArray);
		
		sql += " where " + fieldTypeArr[tableFldConstants.pldetailsid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.pldetailsid.ordinal() ] + "'";
		return sql;
	}

	public static String getDeleteSql(TableFieldType [] fieldTypeArr, Object [] dataArray)
	{
		String sql = "DELETE from " + TBL_PCS_TL_DTL ;
		
		sql += " where " + fieldTypeArr[tableFldConstants.pldetailsid.ordinal()].fieldName  +
			  " = '" +  (String)dataArray[ tableFldConstants.pldetailsid.ordinal()] + "'";
		return sql;
	}

}

