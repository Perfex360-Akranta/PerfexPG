package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

public class BAL_PcsTlDtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		pldetailsid, plmasterid, cellid, machineid, operators, calendartime
		, noplaninmins, noofproducts, productid, plannedqty, rawmaterialtype, weight
		, wno, operationno, operationdescription, theoriticalcycletime
		, actualcycletime, batchno, cavityavailable, cavityused, mandrelavailable, mandrelused,producedqty, defectsandreworkloss_ml, rejectedqty, reworkqty, defecttime_sl
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
		, loss91, loss92, loss93, loss94, loss95, loss96, loss97,loss98,loss99,loss100
		, loss101, POKAYOKELINESTOP_ML, loss102,loss103,loss104,loss105,loss106,loss107,loss108,loss109,loss110,loss111,loss112,
		loss114,loss115,loss116,loss117,loss118,loss119,loss120,loss121,loss122,
		JIGFIXTURELOSS_ML,trimmingqty, expansionqty
		, modelchangepart, reprocessing, INSPECTEDQTY
		, QAACCEPTEDQTY, backlogqty, active, createdby, createdon, modifiedon/* ,equipId */
	}

	public BAL_PcsTlDtl()
	{
		saveArray = new  Object [ 190];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPldetailsid() {  
		return (String) saveArray[ tableFldConstants.pldetailsid.ordinal() ];
	}

	public void setPldetailsid(String pldetailsid) {
		saveArray[ tableFldConstants.pldetailsid.ordinal() ] = pldetailsid;
	}

	public String getPlmasterid() {
		return (String) saveArray[ tableFldConstants.plmasterid.ordinal() ];
	}

	public void setPlmasterid(String plmasterid) {
		saveArray[ tableFldConstants.plmasterid.ordinal() ] = plmasterid;
	}

	public String getCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setCellid(String cellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = cellid;
	}

	public String getMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setMachineid(String machineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = machineid;
	}

	public String getOperators() {
		return (String) saveArray[ tableFldConstants.operators.ordinal() ];
	}

	public void setOperators(String operators) {
		saveArray[ tableFldConstants.operators.ordinal() ] = operators;
	}

	public String getCalendartime() {
		return (String) saveArray[ tableFldConstants.calendartime.ordinal() ];
	}

	public void setCalendartime(String calendartime) {
		saveArray[ tableFldConstants.calendartime.ordinal() ] = calendartime;
	}

	public String getNoplaninmins() {
		return (String) saveArray[ tableFldConstants.noplaninmins.ordinal() ];
	}

	public void setNoplaninmins(String noplaninmins) {
		saveArray[ tableFldConstants.noplaninmins.ordinal() ] = noplaninmins;
	}

	public String getNoofproducts() {
		return (String) saveArray[ tableFldConstants.noofproducts.ordinal() ];
	}

	public void setNoofproducts(String noofproducts) {
		saveArray[ tableFldConstants.noofproducts.ordinal() ] = noofproducts;
	}

	public String getProductid() {
		return (String) saveArray[ tableFldConstants.productid.ordinal() ];
	}

	public void setProductid(String productid) {
		saveArray[ tableFldConstants.productid.ordinal() ] = productid;
	}

	public String getPlannedqty() {
		return (String) saveArray[ tableFldConstants.plannedqty.ordinal() ];
	}

	public void setPlannedqty(String plannedqty) {
		saveArray[ tableFldConstants.plannedqty.ordinal() ] = plannedqty;
	}

	public String getRawmaterialtype() {
		return (String) saveArray[ tableFldConstants.rawmaterialtype.ordinal() ];
	}

	public void setRawmaterialtype(String rawmaterialtype) {
		saveArray[ tableFldConstants.rawmaterialtype.ordinal() ] = rawmaterialtype;
	}

	public String getWeight() {
		return (String) saveArray[ tableFldConstants.weight.ordinal() ];
	}

	public void setWeight(String weight) {
		saveArray[ tableFldConstants.weight.ordinal() ] = weight;
	}

	public String getWno() {
		return (String) saveArray[ tableFldConstants.wno.ordinal() ];
	}

	public void setWno(String wno) {
		saveArray[ tableFldConstants.wno.ordinal() ] = wno;
	}
	
	public String getOperationno() {
		return (String) saveArray[ tableFldConstants.operationno.ordinal() ];
	}

	public void setOperationno(String operationno) {
		saveArray[ tableFldConstants.operationno.ordinal() ] = operationno;
	}

	public String getOperationdescription() {
		return (String) saveArray[ tableFldConstants.operationdescription.ordinal() ];
	}

	public void setOperationdescription(String operationdescription) {
		saveArray[ tableFldConstants.operationdescription.ordinal() ] = operationdescription;
	}

	public String getTheoriticalcycletime() {
		return (String) saveArray[ tableFldConstants.theoriticalcycletime.ordinal() ];
	}

	public void setTheoriticalcycletime(String theoriticalcycletime) {
		saveArray[ tableFldConstants.theoriticalcycletime.ordinal() ] = theoriticalcycletime;
	}

	public String getActualcycletime() {
		return (String) saveArray[ tableFldConstants.actualcycletime.ordinal() ];
	}

	public void setActualcycletime(String actualcycletime) {
		saveArray[ tableFldConstants.actualcycletime.ordinal() ] = actualcycletime;
	}

	public String getBatchno() {
		return (String) saveArray[ tableFldConstants.batchno.ordinal() ];
	}

	public void setBatchno(String batchno) {
		saveArray[ tableFldConstants.batchno.ordinal() ] = batchno;
	}
	
	public String getCavityavailable() {
		return (String) saveArray[ tableFldConstants.cavityavailable.ordinal() ];
	}

	public void setCavityavailable(String cavityavailable) {
		saveArray[ tableFldConstants.cavityavailable.ordinal() ] = cavityavailable;
	}

	public String getCavityused() {
		return (String) saveArray[ tableFldConstants.cavityused.ordinal() ];
	}

	public void setCavityused(String cavityused) {
		saveArray[ tableFldConstants.cavityused.ordinal() ] = cavityused;
	}

	public String getMandrelavailable() {
		return (String) saveArray[ tableFldConstants.mandrelavailable.ordinal() ];
	}

	public void setMandrelavailable(String mandrelavailable) {
		saveArray[ tableFldConstants.mandrelavailable.ordinal() ] = mandrelavailable;
	}
		
	public String getMandrelused() {
		return (String) saveArray[ tableFldConstants.mandrelused.ordinal() ];
	}

	public void setMandrelused(String mandrelused) {
		saveArray[ tableFldConstants.mandrelused.ordinal() ] = mandrelused;
	}
	
	public String getProducedqty() {
		return (String) saveArray[ tableFldConstants.producedqty.ordinal() ];
	}

	public void setProducedqty(String producedqty) {
		saveArray[ tableFldConstants.producedqty.ordinal() ] = producedqty;
	}

	public String getDefectsandreworklossMl() {
		return (String) saveArray[ tableFldConstants.defectsandreworkloss_ml.ordinal() ];
	}

	public void setDefectsandreworklossMl(String defectsandreworklossMl) {
		saveArray[ tableFldConstants.defectsandreworkloss_ml.ordinal() ] = defectsandreworklossMl;
	}

	public String getRejectedqty() {
		return (String) saveArray[ tableFldConstants.rejectedqty.ordinal() ];
	}

	public void setRejectedqty(String rejectedqty) {
		saveArray[ tableFldConstants.rejectedqty.ordinal() ] = rejectedqty;
	}

	public String getReworkqty() {
		return (String) saveArray[ tableFldConstants.reworkqty.ordinal() ];
	}

	public void setReworkqty(String reworkqty) {
		saveArray[ tableFldConstants.reworkqty.ordinal() ] = reworkqty;
	}

	public String getDefecttimeSl() {
		return (String) saveArray[ tableFldConstants.defecttime_sl.ordinal() ];
	}

	public void setDefecttimeSl(String defecttimeSl) {
		saveArray[ tableFldConstants.defecttime_sl.ordinal() ] = defecttimeSl;
	}

	public String getProductionlosses() {
		return (String) saveArray[ tableFldConstants.productionlosses.ordinal() ];
	}

	public void setProductionlosses(String productionlosses) {
		saveArray[ tableFldConstants.productionlosses.ordinal() ] = productionlosses;
	}

	public String getEquipmentfailureMl() {
		return (String) saveArray[ tableFldConstants.equipmentfailure_ml.ordinal() ];
	}

	public void setEquipmentfailureMl(String equipmentfailureMl) {
		saveArray[ tableFldConstants.equipmentfailure_ml.ordinal() ] = equipmentfailureMl;
	}

	public String getSetupandadjustmentMl() {
		return (String) saveArray[ tableFldConstants.setupandadjustment_ml.ordinal() ];
	}

	public void setSetupandadjustmentMl(String setupandadjustmentMl) {
		saveArray[ tableFldConstants.setupandadjustment_ml.ordinal() ] = setupandadjustmentMl;
	}

	public String getToolchangelossMl() {
		return (String) saveArray[ tableFldConstants.toolchangeloss_ml.ordinal() ];
	}

	public void setToolchangelossMl(String toolchangelossMl) {
		saveArray[ tableFldConstants.toolchangeloss_ml.ordinal() ] = toolchangelossMl;
	}

	public String getStartuplossMl() {
		return (String) saveArray[ tableFldConstants.startuploss_ml.ordinal() ];
	}

	public void setStartuplossMl(String startuplossMl) {
		saveArray[ tableFldConstants.startuploss_ml.ordinal() ] = startuplossMl;
	}

	public String getMinorstoppagelossMl() {
		return (String) saveArray[ tableFldConstants.minorstoppageloss_ml.ordinal() ];
	}

	public void setMinorstoppagelossMl(String minorstoppagelossMl) {
		saveArray[ tableFldConstants.minorstoppageloss_ml.ordinal() ] = minorstoppagelossMl;
	}

	public String getSpeedlossMl() {
		return (String) saveArray[ tableFldConstants.speedloss_ml.ordinal() ];
	}

	public void setSpeedlossMl(String speedlossMl) {
		saveArray[ tableFldConstants.speedloss_ml.ordinal() ] = speedlossMl;
	}

	public String getShutdownlossMl() {
		return (String) saveArray[ tableFldConstants.shutdownloss_ml.ordinal() ];
	}

	public void setShutdownlossMl(String shutdownlossMl) {
		saveArray[ tableFldConstants.shutdownloss_ml.ordinal() ] = shutdownlossMl;
	}

	public String getManagementlossMl() {
		return (String) saveArray[ tableFldConstants.managementloss_ml.ordinal() ];
	}

	public void setManagementlossMl(String managementlossMl) {
		saveArray[ tableFldConstants.managementloss_ml.ordinal() ] = managementlossMl;
	}

	public String getCommonutilitylossSl() {
		return (String) saveArray[ tableFldConstants.commonutilityloss_sl.ordinal() ];
	}

	public void setCommonutilitylossSl(String commonutilitylossSl) {
		saveArray[ tableFldConstants.commonutilityloss_sl.ordinal() ] = commonutilitylossSl;
	}

	public String getOperatingmotionlossMl() {
		return (String) saveArray[ tableFldConstants.operatingmotionloss_ml.ordinal() ];
	}

	public void setOperatingmotionlossMl(String operatingmotionlossMl) {
		saveArray[ tableFldConstants.operatingmotionloss_ml.ordinal() ] = operatingmotionlossMl;
	}

	public String getLineorganisationlossMl() {
		return (String) saveArray[ tableFldConstants.lineorganisationloss_ml.ordinal() ];
	}

	public void setLineorganisationlossMl(String lineorganisationlossMl) {
		saveArray[ tableFldConstants.lineorganisationloss_ml.ordinal() ] = lineorganisationlossMl;
	}

	public String getLogisticslossMl() {
		return (String) saveArray[ tableFldConstants.logisticsloss_ml.ordinal() ];
	}

	public void setLogisticslossMl(String logisticslossMl) {
		saveArray[ tableFldConstants.logisticsloss_ml.ordinal() ] = logisticslossMl;
	}

	public String getMeasuringandadjlossMl() {
		return (String) saveArray[ tableFldConstants.measuringandadjloss_ml.ordinal() ];
	}

	public void setMeasuringandadjlossMl(String measuringandadjlossMl) {
		saveArray[ tableFldConstants.measuringandadjloss_ml.ordinal() ] = measuringandadjlossMl;
	}

	public String getDietoolandjiglossMl() {
		return (String) saveArray[ tableFldConstants.dietoolandjigloss_ml.ordinal() ];
	}

	public void setDietoolandjiglossMl(String dietoolandjiglossMl) {
		saveArray[ tableFldConstants.dietoolandjigloss_ml.ordinal() ] = dietoolandjiglossMl;
	}

	public String getEnergylossMl() {
		return (String) saveArray[ tableFldConstants.energyloss_ml.ordinal() ];
	}

	public void setEnergylossMl(String energylossMl) {
		saveArray[ tableFldConstants.energyloss_ml.ordinal() ] = energylossMl;
	}

	public String getYieldlossMl() {
		return (String) saveArray[ tableFldConstants.yieldloss_ml.ordinal() ];
	}

	public void setYieldlossMl(String yieldlossMl) {
		saveArray[ tableFldConstants.yieldloss_ml.ordinal() ] = yieldlossMl;
	}

	public String getUnaccountedtime() {
		return (String) saveArray[ tableFldConstants.unaccountedtime.ordinal() ];
	}

	public void setUnaccountedtime(String unaccountedtime) {
		saveArray[ tableFldConstants.unaccountedtime.ordinal() ] = unaccountedtime;
	}

	public String getLoadingtime() {
		return (String) saveArray[ tableFldConstants.loadingtime.ordinal() ];
	}

	public void setLoadingtime(String loadingtime) {
		saveArray[ tableFldConstants.loadingtime.ordinal() ] = loadingtime;
	}

	public String getEffectiveprodmins() {
		return (String) saveArray[ tableFldConstants.effectiveprodmins.ordinal() ];
	}

	public void setEffectiveprodmins(String effectiveprodmins) {
		saveArray[ tableFldConstants.effectiveprodmins.ordinal() ] = effectiveprodmins;
	}

	public String getMchavailabletime() {
		return (String) saveArray[ tableFldConstants.mchavailabletime.ordinal() ];
	}

	public void setMchavailabletime(String mchavailabletime) {
		saveArray[ tableFldConstants.mchavailabletime.ordinal() ] = mchavailabletime;
	}

	public String getProductionavltime() {
		return (String) saveArray[ tableFldConstants.productionavltime.ordinal() ];
	}

	public void setProductionavltime(String productionavltime) {
		saveArray[ tableFldConstants.productionavltime.ordinal() ] = productionavltime;
	}

	public String getProductiontime() {
		return (String) saveArray[ tableFldConstants.productiontime.ordinal() ];
	}

	public void setProductiontime(String productiontime) {
		saveArray[ tableFldConstants.productiontime.ordinal() ] = productiontime;
	}

	public String getRoa() {
		return (String) saveArray[ tableFldConstants.roa.ordinal() ];
	}

	public void setRoa(String roa) {
		saveArray[ tableFldConstants.roa.ordinal() ] = roa;
	}

	public String getRop() {
		return (String) saveArray[ tableFldConstants.rop.ordinal() ];
	}

	public void setRop(String rop) {
		saveArray[ tableFldConstants.rop.ordinal() ] = rop;
	}

	public String getRoq() {
		return (String) saveArray[ tableFldConstants.roq.ordinal() ];
	}

	public void setRoq(String roq) {
		saveArray[ tableFldConstants.roq.ordinal() ] = roq;
	}

	public String getOee() {
		return (String) saveArray[ tableFldConstants.oee.ordinal() ];
	}

	public void setOee(String oee) {
		saveArray[ tableFldConstants.oee.ordinal() ] = oee;
	}

	public String getCompletedflag() {
		return (String) saveArray[ tableFldConstants.completedflag.ordinal() ];
	}

	public void setCompletedflag(String completedflag) {
		saveArray[ tableFldConstants.completedflag.ordinal() ] = completedflag;
	}

	public String getRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setRemarks(String remarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = remarks;
	}

	public String getLoss01() {
		return (String) saveArray[ tableFldConstants.loss01.ordinal() ];
	}

	public void setLoss01(String loss01) {
		saveArray[ tableFldConstants.loss01.ordinal() ] = loss01;
	}

	public String getLoss02() {
		return (String) saveArray[ tableFldConstants.loss02.ordinal() ];
	}

	public void setLoss02(String loss02) {
		saveArray[ tableFldConstants.loss02.ordinal() ] = loss02;
	}

	public String getLoss03() {
		return (String) saveArray[ tableFldConstants.loss03.ordinal() ];
	}

	public void setLoss03(String loss03) {
		saveArray[ tableFldConstants.loss03.ordinal() ] = loss03;
	}

	public String getLoss04() {
		return (String) saveArray[ tableFldConstants.loss04.ordinal() ];
	}

	public void setLoss04(String loss04) {
		saveArray[ tableFldConstants.loss04.ordinal() ] = loss04;
	}

	public String getLoss05() {
		return (String) saveArray[ tableFldConstants.loss05.ordinal() ];
	}

	public void setLoss05(String loss05) {
		saveArray[ tableFldConstants.loss05.ordinal() ] = loss05;
	}

	public String getLoss06() {
		return (String) saveArray[ tableFldConstants.loss06.ordinal() ];
	}

	public void setLoss06(String loss06) {
		saveArray[ tableFldConstants.loss06.ordinal() ] = loss06;
	}

	public String getLoss07() {
		return (String) saveArray[ tableFldConstants.loss07.ordinal() ];
	}

	public void setLoss07(String loss07) {
		saveArray[ tableFldConstants.loss07.ordinal() ] = loss07;
	}

	public String getLoss08() {
		return (String) saveArray[ tableFldConstants.loss08.ordinal() ];
	}

	public void setLoss08(String loss08) {
		saveArray[ tableFldConstants.loss08.ordinal() ] = loss08;
	}

	public String getLoss09() {
		return (String) saveArray[ tableFldConstants.loss09.ordinal() ];
	}

	public void setLoss09(String loss09) {
		saveArray[ tableFldConstants.loss09.ordinal() ] = loss09;
	}

	public String getLoss10() {
		return (String) saveArray[ tableFldConstants.loss10.ordinal() ];
	}

	public void setLoss10(String loss10) {
		saveArray[ tableFldConstants.loss10.ordinal() ] = loss10;
	}

	public String getLoss11() {
		return (String) saveArray[ tableFldConstants.loss11.ordinal() ];
	}

	public void setLoss11(String loss11) {
		saveArray[ tableFldConstants.loss11.ordinal() ] = loss11;
	}

	public String getLoss12() {
		return (String) saveArray[ tableFldConstants.loss12.ordinal() ];
	}

	public void setLoss12(String loss12) {
		saveArray[ tableFldConstants.loss12.ordinal() ] = loss12;
	}

	public String getLoss13() {
		return (String) saveArray[ tableFldConstants.loss13.ordinal() ];
	}

	public void setLoss13(String loss13) {
		saveArray[ tableFldConstants.loss13.ordinal() ] = loss13;
	}

	public String getLoss14() {
		return (String) saveArray[ tableFldConstants.loss14.ordinal() ];
	}

	public void setLoss14(String loss14) {
		saveArray[ tableFldConstants.loss14.ordinal() ] = loss14;
	}

	public String getLoss15() {
		return (String) saveArray[ tableFldConstants.loss15.ordinal() ];
	}

	public void setLoss15(String loss15) {
		saveArray[ tableFldConstants.loss15.ordinal() ] = loss15;
	}

	public String getLoss16() {
		return (String) saveArray[ tableFldConstants.loss16.ordinal() ];
	}

	public void setLoss16(String loss16) {
		saveArray[ tableFldConstants.loss16.ordinal() ] = loss16;
	}

	public String getLoss17() {
		return (String) saveArray[ tableFldConstants.loss17.ordinal() ];
	}

	public void setLoss17(String loss17) {
		saveArray[ tableFldConstants.loss17.ordinal() ] = loss17;
	}

	public String getLoss18() {
		return (String) saveArray[ tableFldConstants.loss18.ordinal() ];
	}

	public void setLoss18(String loss18) {
		saveArray[ tableFldConstants.loss18.ordinal() ] = loss18;
	}

	public String getLoss19() {
		return (String) saveArray[ tableFldConstants.loss19.ordinal() ];
	}

	public void setLoss19(String loss19) {
		saveArray[ tableFldConstants.loss19.ordinal() ] = loss19;
	}

	public String getLoss20() {
		return (String) saveArray[ tableFldConstants.loss20.ordinal() ];
	}

	public void setLoss20(String loss20) {
		saveArray[ tableFldConstants.loss20.ordinal() ] = loss20;
	}

	public String getLoss21() {
		return (String) saveArray[ tableFldConstants.loss21.ordinal() ];
	}

	public void setLoss21(String loss21) {
		saveArray[ tableFldConstants.loss21.ordinal() ] = loss21;
	}

	public String getLoss22() {
		return (String) saveArray[ tableFldConstants.loss22.ordinal() ];
	}

	public void setLoss22(String loss22) {
		saveArray[ tableFldConstants.loss22.ordinal() ] = loss22;
	}

	public String getLoss23() {
		return (String) saveArray[ tableFldConstants.loss23.ordinal() ];
	}

	public void setLoss23(String loss23) {
		saveArray[ tableFldConstants.loss23.ordinal() ] = loss23;
	}

	public String getLoss24() {
		return (String) saveArray[ tableFldConstants.loss24.ordinal() ];
	}

	public void setLoss24(String loss24) {
		saveArray[ tableFldConstants.loss24.ordinal() ] = loss24;
	}

	public String getLoss25() {
		return (String) saveArray[ tableFldConstants.loss25.ordinal() ];
	}

	public void setLoss25(String loss25) {
		saveArray[ tableFldConstants.loss25.ordinal() ] = loss25;
	}

	public String getLoss26() {
		return (String) saveArray[ tableFldConstants.loss26.ordinal() ];
	}

	public void setLoss26(String loss26) {
		saveArray[ tableFldConstants.loss26.ordinal() ] = loss26;
	}

	public String getLoss27() {
		return (String) saveArray[ tableFldConstants.loss27.ordinal() ];
	}

	public void setLoss27(String loss27) {
		saveArray[ tableFldConstants.loss27.ordinal() ] = loss27;
	}

	public String getLoss28() {
		return (String) saveArray[ tableFldConstants.loss28.ordinal() ];
	}

	public void setLoss28(String loss28) {
		saveArray[ tableFldConstants.loss28.ordinal() ] = loss28;
	}

	public String getLoss29() {
		return (String) saveArray[ tableFldConstants.loss29.ordinal() ];
	}

	public void setLoss29(String loss29) {
		saveArray[ tableFldConstants.loss29.ordinal() ] = loss29;
	}

	public String getLoss30() {
		return (String) saveArray[ tableFldConstants.loss30.ordinal() ];
	}

	public void setLoss30(String loss30) {
		saveArray[ tableFldConstants.loss30.ordinal() ] = loss30;
	}

	public String getLoss31() {
		return (String) saveArray[ tableFldConstants.loss31.ordinal() ];
	}

	public void setLoss31(String loss31) {
		saveArray[ tableFldConstants.loss31.ordinal() ] = loss31;
	}

	public String getLoss32() {
		return (String) saveArray[ tableFldConstants.loss32.ordinal() ];
	}

	public void setLoss32(String loss32) {
		saveArray[ tableFldConstants.loss32.ordinal() ] = loss32;
	}

	public String getLoss33() {
		return (String) saveArray[ tableFldConstants.loss33.ordinal() ];
	}

	public void setLoss33(String loss33) {
		saveArray[ tableFldConstants.loss33.ordinal() ] = loss33;
	}

	public String getLoss34() {
		return (String) saveArray[ tableFldConstants.loss34.ordinal() ];
	}

	public void setLoss34(String loss34) {
		saveArray[ tableFldConstants.loss34.ordinal() ] = loss34;
	}

	public String getLoss35() {
		return (String) saveArray[ tableFldConstants.loss35.ordinal() ];
	}

	public void setLoss35(String loss35) {
		saveArray[ tableFldConstants.loss35.ordinal() ] = loss35;
	}

	public String getLoss36() {
		return (String) saveArray[ tableFldConstants.loss36.ordinal() ];
	}

	public void setLoss36(String loss36) {
		saveArray[ tableFldConstants.loss36.ordinal() ] = loss36;
	}

	public String getLoss37() {
		return (String) saveArray[ tableFldConstants.loss37.ordinal() ];
	}

	public void setLoss37(String loss37) {
		saveArray[ tableFldConstants.loss37.ordinal() ] = loss37;
	}

	public String getLoss38() {
		return (String) saveArray[ tableFldConstants.loss38.ordinal() ];
	}

	public void setLoss38(String loss38) {
		saveArray[ tableFldConstants.loss38.ordinal() ] = loss38;
	}

	public String getLoss39() {
		return (String) saveArray[ tableFldConstants.loss39.ordinal() ];
	}

	public void setLoss39(String loss39) {
		saveArray[ tableFldConstants.loss39.ordinal() ] = loss39;
	}

	public String getLoss40() {
		return (String) saveArray[ tableFldConstants.loss40.ordinal() ];
	}

	public void setLoss40(String loss40) {
		saveArray[ tableFldConstants.loss40.ordinal() ] = loss40;
	}

	public String getLoss41() {
		return (String) saveArray[ tableFldConstants.loss41.ordinal() ];
	}

	public void setLoss41(String loss41) {
		saveArray[ tableFldConstants.loss41.ordinal() ] = loss41;
	}

	public String getLoss42() {
		return (String) saveArray[ tableFldConstants.loss42.ordinal() ];
	}

	public void setLoss42(String loss42) {
		saveArray[ tableFldConstants.loss42.ordinal() ] = loss42;
	}

	public String getLoss43() {
		return (String) saveArray[ tableFldConstants.loss43.ordinal() ];
	}

	public void setLoss43(String loss43) {
		saveArray[ tableFldConstants.loss43.ordinal() ] = loss43;
	}

	public String getLoss44() {
		return (String) saveArray[ tableFldConstants.loss44.ordinal() ];
	}

	public void setLoss44(String loss44) {
		saveArray[ tableFldConstants.loss44.ordinal() ] = loss44;
	}

	public String getLoss45() {
		return (String) saveArray[ tableFldConstants.loss45.ordinal() ];
	}

	public void setLoss45(String loss45) {
		saveArray[ tableFldConstants.loss45.ordinal() ] = loss45;
	}

	public String getLoss46() {
		return (String) saveArray[ tableFldConstants.loss46.ordinal() ];
	}

	public void setLoss46(String loss46) {
		saveArray[ tableFldConstants.loss46.ordinal() ] = loss46;
	}

	public String getLoss47() {
		return (String) saveArray[ tableFldConstants.loss47.ordinal() ];
	}

	public void setLoss47(String loss47) {
		saveArray[ tableFldConstants.loss47.ordinal() ] = loss47;
	}

	public String getLoss48() {
		return (String) saveArray[ tableFldConstants.loss48.ordinal() ];
	}

	public void setLoss48(String loss48) {
		saveArray[ tableFldConstants.loss48.ordinal() ] = loss48;
	}

	public String getLoss49() {
		return (String) saveArray[ tableFldConstants.loss49.ordinal() ];
	}

	public void setLoss49(String loss49) {
		saveArray[ tableFldConstants.loss49.ordinal() ] = loss49;
	}

	public String getLoss50() {
		return (String) saveArray[ tableFldConstants.loss50.ordinal() ];
	}

	public void setLoss50(String loss50) {
		saveArray[ tableFldConstants.loss50.ordinal() ] = loss50;
	}

	public String getLoss51() {
		return (String) saveArray[ tableFldConstants.loss51.ordinal() ];
	}

	public void setLoss51(String loss51) {
		saveArray[ tableFldConstants.loss51.ordinal() ] = loss51;
	}

	public String getLoss52() {
		return (String) saveArray[ tableFldConstants.loss52.ordinal() ];
	}

	public void setLoss52(String loss52) {
		saveArray[ tableFldConstants.loss52.ordinal() ] = loss52;
	}

	public String getLoss53() {
		return (String) saveArray[ tableFldConstants.loss53.ordinal() ];
	}

	public void setLoss53(String loss53) {
		saveArray[ tableFldConstants.loss53.ordinal() ] = loss53;
	}

	public String getLoss54() {
		return (String) saveArray[ tableFldConstants.loss54.ordinal() ];
	}

	public void setLoss54(String loss54) {
		saveArray[ tableFldConstants.loss54.ordinal() ] = loss54;
	}

	public String getLoss55() {
		return (String) saveArray[ tableFldConstants.loss55.ordinal() ];
	}

	public void setLoss55(String loss55) {
		saveArray[ tableFldConstants.loss55.ordinal() ] = loss55;
	}
	public String getLoss56() {
		return (String) saveArray[ tableFldConstants.loss56.ordinal() ];
	}
 
	public void setLoss56(String loss56) {
		saveArray[ tableFldConstants.loss56.ordinal() ] = loss56;
	}
 
	public String getLoss57() {
		return (String) saveArray[ tableFldConstants.loss57.ordinal() ];
	}
 
	public void setLoss57(String loss57) {
		saveArray[ tableFldConstants.loss57.ordinal() ] = loss57;
	}
 
	public String getLoss58() {
		return (String) saveArray[ tableFldConstants.loss58.ordinal() ];
	}
 
	public void setLoss58(String loss58) {
		saveArray[ tableFldConstants.loss58.ordinal() ] = loss58;
	}
 
	public String getLoss59() {
		return (String) saveArray[ tableFldConstants.loss59.ordinal() ];
	}
 
	public void setLoss59(String loss59) {
		saveArray[ tableFldConstants.loss59.ordinal() ] = loss59;
	}
 
	public String getLoss60() {
		return (String) saveArray[ tableFldConstants.loss60.ordinal() ];
	}
 
	public void setLoss60(String loss60) {
		saveArray[ tableFldConstants.loss60.ordinal() ] = loss60;
	}
 
	public String getLoss61() {
		return (String) saveArray[ tableFldConstants.loss61.ordinal() ];
	}
 
	public void setLoss61(String loss61) {
		saveArray[ tableFldConstants.loss61.ordinal() ] = loss61;
	}
 
	public String getLoss62() {
		return (String) saveArray[ tableFldConstants.loss62.ordinal() ];
	}
 
	public void setLoss62(String loss62) {
		saveArray[ tableFldConstants.loss62.ordinal() ] = loss62;
	}
 
	public String getLoss63() {
		return (String) saveArray[ tableFldConstants.loss63.ordinal() ];
	}
 
	public void setLoss63(String loss63) {
		saveArray[ tableFldConstants.loss63.ordinal() ] = loss63;
	}
 
	public String getLoss64() {
		return (String) saveArray[ tableFldConstants.loss64.ordinal() ];
	}
 
	public void setLoss64(String loss64) {
		saveArray[ tableFldConstants.loss64.ordinal() ] = loss64;
	}
 
	public String getLoss65() {
		return (String) saveArray[ tableFldConstants.loss65.ordinal() ];
	}
 
	public void setLoss65(String loss65) {
		saveArray[ tableFldConstants.loss65.ordinal() ] = loss65;
	}
 
	public String getLoss66() {
		return (String) saveArray[ tableFldConstants.loss66.ordinal() ];
	}
 
	public void setLoss66(String loss66) {
		saveArray[ tableFldConstants.loss66.ordinal() ] = loss66;
	}
 
	public String getLoss67() {
		return (String) saveArray[ tableFldConstants.loss67.ordinal() ];
	}
 
	public void setLoss67(String loss67) {
		saveArray[ tableFldConstants.loss67.ordinal() ] = loss67;
	}
 
	public String getLoss68() {
		return (String) saveArray[ tableFldConstants.loss68.ordinal() ];
	}
 
	public void setLoss68(String loss68) {
		saveArray[ tableFldConstants.loss68.ordinal() ] = loss68;
	}
 
	public String getLoss69() {
		return (String) saveArray[ tableFldConstants.loss69.ordinal() ];
	}
 
	public void setLoss69(String loss69) {
		saveArray[ tableFldConstants.loss69.ordinal() ] = loss69;
	}
 
	public String getLoss70() {
		return (String) saveArray[ tableFldConstants.loss70.ordinal() ];
	}
 
	public void setLoss70(String loss70) {
		saveArray[ tableFldConstants.loss70.ordinal() ] = loss70;
	}
 
	public String getLoss71() {
		return (String) saveArray[ tableFldConstants.loss71.ordinal() ];
	}
 
	public void setLoss71(String loss71) {
		saveArray[ tableFldConstants.loss71.ordinal() ] = loss71;
	}
 
	public String getLoss72() {
		return (String) saveArray[ tableFldConstants.loss72.ordinal() ];
	}
 
	public void setLoss72(String loss72) {
		saveArray[ tableFldConstants.loss72.ordinal() ] = loss72;
	}
 
	public String getLoss73() {
		return (String) saveArray[ tableFldConstants.loss73.ordinal() ];
	}
 
	public void setLoss73(String loss73) {
		saveArray[ tableFldConstants.loss73.ordinal() ] = loss73;
	}
 
	public String getLoss74() {
		return (String) saveArray[ tableFldConstants.loss74.ordinal() ];
	}
 
	public void setLoss74(String loss74) {
		saveArray[ tableFldConstants.loss74.ordinal() ] = loss74;
	}
 
	public String getLoss75() {
		return (String) saveArray[ tableFldConstants.loss75.ordinal() ];
	}
 
	public void setLoss75(String loss75) {
		saveArray[ tableFldConstants.loss75.ordinal() ] = loss75;
	}
 
	public String getLoss76() {
		return (String) saveArray[ tableFldConstants.loss76.ordinal() ];
	}
 
	public void setLoss76(String loss76) {
		saveArray[ tableFldConstants.loss76.ordinal() ] = loss76;
	}
 
	public String getLoss77() {
		return (String) saveArray[ tableFldConstants.loss77.ordinal() ];
	}
 
	public void setLoss77(String loss77) {
		saveArray[ tableFldConstants.loss77.ordinal() ] = loss77;
	}
 
	public String getLoss78() {
		return (String) saveArray[ tableFldConstants.loss78.ordinal() ];
	}
 
	public void setLoss78(String loss78) {
		saveArray[ tableFldConstants.loss78.ordinal() ] = loss78;
	}
 
	public String getLoss79() {
		return (String) saveArray[ tableFldConstants.loss79.ordinal() ];
	}
 
	public void setLoss79(String loss79) {
		saveArray[ tableFldConstants.loss79.ordinal() ] = loss79;
	}
 
	public String getLoss80() {
		return (String) saveArray[ tableFldConstants.loss80.ordinal() ];
	}
 
	public void setLoss80(String loss80) {
		saveArray[ tableFldConstants.loss80.ordinal() ] = loss80;
	}
 
	public String getLoss81() {
		return (String) saveArray[ tableFldConstants.loss81.ordinal() ];
	}
 
	public void setLoss81(String loss81) {
		saveArray[ tableFldConstants.loss81.ordinal() ] = loss81;
	}
 
	public String getLoss82() {
		return (String) saveArray[ tableFldConstants.loss82.ordinal() ];
	}
 
	public void setLoss82(String loss82) {
		saveArray[ tableFldConstants.loss82.ordinal() ] = loss82;
	}
 
	public String getLoss83() {
		return (String) saveArray[ tableFldConstants.loss83.ordinal() ];
	}
 
	public void setLoss83(String loss83) {
		saveArray[ tableFldConstants.loss83.ordinal() ] = loss83;
	}
 
	public String getLoss84() {
		return (String) saveArray[ tableFldConstants.loss84.ordinal() ];
	}
 
	public void setLoss84(String loss84) {
		saveArray[ tableFldConstants.loss84.ordinal() ] = loss84;
	}
 
	public String getLoss85() {
		return (String) saveArray[ tableFldConstants.loss85.ordinal() ];
	}
 
	public void setLoss85(String loss85) {
		saveArray[ tableFldConstants.loss85.ordinal() ] = loss85;
	}
 
	public String getLoss86() {
		return (String) saveArray[ tableFldConstants.loss86.ordinal() ];
	}
 
	public void setLoss86(String loss86) {
		saveArray[ tableFldConstants.loss86.ordinal() ] = loss86;
	}
 
	public String getLoss87() {
		return (String) saveArray[ tableFldConstants.loss87.ordinal() ];
	}
 
	public void setLoss87(String loss87) {
		saveArray[ tableFldConstants.loss87.ordinal() ] = loss87;
	}
 
	public String getLoss88() {
		return (String) saveArray[ tableFldConstants.loss88.ordinal() ];
	}
 
	public void setLoss88(String loss88) {
		saveArray[ tableFldConstants.loss88.ordinal() ] = loss88;
	}
 
	public String getLoss89() {
		return (String) saveArray[ tableFldConstants.loss89.ordinal() ];
	}
 
	public void setLoss89(String loss89) {
		saveArray[ tableFldConstants.loss89.ordinal() ] = loss89;
	}
 
	public String getLoss90() {
		return (String) saveArray[ tableFldConstants.loss90.ordinal() ];
	}
 
	public void setLoss90(String loss90) {
		saveArray[ tableFldConstants.loss90.ordinal() ] = loss90;
	}
 
	public String getLoss91() {
		return (String) saveArray[ tableFldConstants.loss91.ordinal() ];
	}
 
	public void setLoss91(String loss91) {
		saveArray[ tableFldConstants.loss91.ordinal() ] = loss91;
	}
 
	public String getLoss92() {
		return (String) saveArray[ tableFldConstants.loss92.ordinal() ];
	}
 
	public void setLoss92(String loss92) {
		saveArray[ tableFldConstants.loss92.ordinal() ] = loss92;
	}
 
	public String getLoss93() {
		return (String) saveArray[ tableFldConstants.loss93.ordinal() ];
	}
 
	public void setLoss93(String loss93) {
		saveArray[ tableFldConstants.loss93.ordinal() ] = loss93;
	}
 
	public String getLoss94() {
		return (String) saveArray[ tableFldConstants.loss94.ordinal() ];
	}
 
	public void setLoss94(String loss94) {
		saveArray[ tableFldConstants.loss94.ordinal() ] = loss94;
	}
 
	public String getLoss95() {
		return (String) saveArray[ tableFldConstants.loss95.ordinal() ];
	}
 
	public void setLoss95(String loss95) {
		saveArray[ tableFldConstants.loss95.ordinal() ] = loss95;
	}
 
	public String getLoss96() {
		return (String) saveArray[ tableFldConstants.loss96.ordinal() ];
	}
 
	public void setLoss96(String loss96) {
		saveArray[ tableFldConstants.loss96.ordinal() ] = loss96;
	}
 
	public String getLoss97() {
		return (String) saveArray[ tableFldConstants.loss97.ordinal() ];
	}
 
	public void setLoss97(String loss97) {
		saveArray[ tableFldConstants.loss97.ordinal() ] = loss97;
	}
	public String getLoss98() {
		return (String) saveArray[ tableFldConstants.loss98.ordinal() ];
	}

	public void setLoss98(String loss98) {
		saveArray[ tableFldConstants.loss98.ordinal() ] = loss98;
	}
	
	public String getLoss99() {
		return (String) saveArray[ tableFldConstants.loss99.ordinal() ];
	}

	public void setLoss99(String loss99) {
		saveArray[ tableFldConstants.loss99.ordinal() ] = loss99;
	}
	
	public String getLoss100() {
		return (String) saveArray[ tableFldConstants.loss100.ordinal() ];
	}

	public void setLoss100(String loss100) {
		saveArray[ tableFldConstants.loss100.ordinal() ] = loss100;
	}
	public String getLoss101() {
		return (String) saveArray[ tableFldConstants.loss101.ordinal() ];
	}

	public void setLoss101(String loss101) {
		saveArray[ tableFldConstants.loss101.ordinal() ] = loss101;
	}
	
	public String getLoss102() {
		return (String) saveArray[ tableFldConstants.loss102.ordinal() ];
	}

	public void setLoss102(String loss102) {
		saveArray[ tableFldConstants.loss102.ordinal() ] = loss102;
	}
	
	public String getLoss103() {
		return (String) saveArray[ tableFldConstants.loss103.ordinal() ];
	}

	public void setLoss103(String loss103) {
		saveArray[ tableFldConstants.loss103.ordinal() ] = loss103;
	}
		public String getLoss104() {
		return (String) saveArray[ tableFldConstants.loss104.ordinal() ];
	}

	public void setLoss104(String loss104) {
		saveArray[ tableFldConstants.loss104.ordinal() ] = loss104;
	}
	public String getLoss105() {
		return (String) saveArray[ tableFldConstants.loss105.ordinal() ];
	}

	public void setLoss105(String loss105) {
		saveArray[ tableFldConstants.loss105.ordinal() ] = loss105;
	}
	public String getLoss106() {
		return (String) saveArray[ tableFldConstants.loss106.ordinal() ];
	}

	public void setLoss106(String loss106) {
		saveArray[ tableFldConstants.loss106.ordinal() ] = loss106;
	}
		public String getLoss107() {
		return (String) saveArray[ tableFldConstants.loss107.ordinal() ];
	}

	public void setLoss107(String loss107) {
		saveArray[ tableFldConstants.loss107.ordinal() ] = loss107;
	}
	
	public String getLoss108() {
		return (String) saveArray[ tableFldConstants.loss108.ordinal() ];
	}

	public void setLoss108(String loss108) {
		saveArray[ tableFldConstants.loss108.ordinal() ] = loss108;
	}
	public String getLoss109() {
		return (String) saveArray[ tableFldConstants.loss109.ordinal() ];
	}

	public void setLoss109(String loss109) {
		saveArray[ tableFldConstants.loss109.ordinal() ] = loss109;
	}
	public String getLoss110() {
		return (String) saveArray[ tableFldConstants.loss110.ordinal() ];
	}

	public void setLoss110(String loss110) {
		saveArray[ tableFldConstants.loss110.ordinal() ] = loss110;
	}
	public String getLoss111() {
		return (String) saveArray[ tableFldConstants.loss111.ordinal() ];
	}

	public void setLoss111(String loss111) {
		saveArray[ tableFldConstants.loss111.ordinal() ] = loss111;
	}
	public String getLoss112() {
		return (String) saveArray[ tableFldConstants.loss112.ordinal() ];
	}
	public void setLoss112(String loss112) {
		 saveArray[ tableFldConstants.loss112.ordinal() ] = loss112;
		 
	}
	
	public String getLoss114() {
		return (String) saveArray[ tableFldConstants.loss114.ordinal() ];
	}
	public void setLoss114(String loss114) {
		 saveArray[ tableFldConstants.loss114.ordinal() ] = loss114;
		 
	}public String getLoss115() {
		return (String) saveArray[ tableFldConstants.loss115.ordinal() ];
	}
	public void setLoss115(String loss115) {
		 saveArray[ tableFldConstants.loss115.ordinal() ] = loss115;
		 
	}public String getLoss116() {
		return (String) saveArray[ tableFldConstants.loss116.ordinal() ];
	}
	public void setLoss116(String loss116) {
		 saveArray[ tableFldConstants.loss116.ordinal() ] = loss116;
		 
	}public String getLoss117() {
		return (String) saveArray[ tableFldConstants.loss117.ordinal() ];
	}
	public void setLoss117(String loss117) {
		 saveArray[ tableFldConstants.loss117.ordinal() ] = loss117;
		 
	}public String getLoss118() {
		return (String) saveArray[ tableFldConstants.loss118.ordinal() ];
	}
	public void setLoss118(String loss118) {
		 saveArray[ tableFldConstants.loss118.ordinal() ] = loss118;
		 
	}public String getLoss119() {
		return (String) saveArray[ tableFldConstants.loss119.ordinal() ];
	}
	public void setLoss119(String loss119) {
		 saveArray[ tableFldConstants.loss119.ordinal() ] = loss119;
		 
	}public String getLoss120() {
		return (String) saveArray[ tableFldConstants.loss120.ordinal() ];
	}
	public void setLoss120(String loss120) {
		 saveArray[ tableFldConstants.loss120.ordinal() ] = loss120;
		 
	}public String getLoss121() {
		return (String) saveArray[ tableFldConstants.loss121.ordinal() ];
	}
	public void setLoss121(String loss121) {
		 saveArray[ tableFldConstants.loss121.ordinal() ] = loss121;
		 
	
	}public String getLoss122() {
	return (String) saveArray[ tableFldConstants.loss122.ordinal() ];
	}
	public void setLoss122(String loss122) {
	 saveArray[ tableFldConstants.loss122.ordinal() ] = loss122;
	}
	public String getPokaYokeMl() {
		return (String) saveArray[ tableFldConstants.POKAYOKELINESTOP_ML.ordinal() ];
	}

	public void setPokaYokeMl(String PokaYokeMl) {
		saveArray[ tableFldConstants.POKAYOKELINESTOP_ML.ordinal() ] = PokaYokeMl;
	}
	
	public String getJigfixtureMl() {
		return (String) saveArray[ tableFldConstants.JIGFIXTURELOSS_ML.ordinal() ];
	}

	public void setJigfixtureMl(String JigfixtureMl) {
		saveArray[ tableFldConstants.JIGFIXTURELOSS_ML.ordinal() ] = JigfixtureMl;
	}
	
	
	public String getTrimmingqty() {
		return (String) saveArray[ tableFldConstants.trimmingqty.ordinal() ];
	}

	public void setTrimmingqty(String trimmingqty) {
		saveArray[ tableFldConstants.trimmingqty.ordinal() ] = trimmingqty;
	}
	
	public String getExpansionqty() {
		return (String) saveArray[ tableFldConstants.expansionqty.ordinal() ];
	}

	public void setExpansionqty(String expansionqty) {
		saveArray[ tableFldConstants.expansionqty.ordinal() ] = expansionqty;
	}

	public String getModelchangepart() {
		return (String) saveArray[ tableFldConstants.modelchangepart.ordinal() ];
	}

	public void setModelchangepart(String modelchangepart) {
		saveArray[ tableFldConstants.modelchangepart.ordinal() ] = modelchangepart;
	}

	public String getReprocessing() {
		return (String) saveArray[ tableFldConstants.reprocessing.ordinal() ];
	}

	public void setReprocessing(String reprocessing) {
		saveArray[ tableFldConstants.reprocessing.ordinal() ] = reprocessing;
	}

	public String getINSPECTEDQTY() {
		return (String) saveArray[ tableFldConstants.INSPECTEDQTY.ordinal() ];
	}

	public void setINSPECTEDQTY(String INSPECTEDQTY) {
		saveArray[ tableFldConstants.INSPECTEDQTY.ordinal() ] = INSPECTEDQTY;
	}

	public String getQAACCEPTEDQTY() {
		return (String) saveArray[ tableFldConstants.QAACCEPTEDQTY.ordinal() ];
	}

	public void setQAACCEPTEDQTY(String QAACCEPTEDQTY) {
		saveArray[ tableFldConstants.QAACCEPTEDQTY.ordinal() ] = QAACCEPTEDQTY;
	}

	public String getBacklogqty() {
		return (String) saveArray[ tableFldConstants.backlogqty.ordinal() ];
	}
	public void setBacklogqty(String backlogqty) {
		saveArray[ tableFldConstants.backlogqty.ordinal() ] = backlogqty;
	}

	public String getActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setActive(String active) {
		saveArray[ tableFldConstants.active.ordinal() ] = active;
	}

	public String getCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setCreatedby(String createdby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = createdby;
	}

	public String getCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setCreatedon(String createdon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = createdon;
	}

	public String getModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setModifiedon(String modifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = modifiedon;
	}
	
	/*
	 * public String getEquipId() { return (String) saveArray[
	 * tableFldConstants.equipId.ordinal() ]; }
	 * 
	 * public void setEquipId(String equipId) { saveArray[
	 * tableFldConstants.equipId.ordinal() ] = equipId; }
	 */

	//tojson
	public String toJsonManual() {
	    StringBuilder sb = new StringBuilder("{");
	    boolean first = true;
	    for (tableFldConstants field : tableFldConstants.values()) {
	        int index = field.ordinal();
	        if (index >= saveArray.length) continue;

	        if (!first) sb.append(",");
	        first = false;

	        // lowercase key so Jackson matches the entity property
	        sb.append("\"").append(field.name().toLowerCase()).append("\":");

	        Object val = saveArray[index];
	        if (val == null) {
	            sb.append("null");                       // real JSON null, not "{}"
	        } else {
	            sb.append("\"").append(escape(val.toString())).append("\"");
	        }
	    }
	    sb.append("}");
	    return sb.toString();
	}

	private static String escape(String s) {
	    return s.replace("\\", "\\\\").replace("\"", "\\\"")
	            .replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
	}
	
	/*
	 * public String toJsonManual() { StringBuilder sb = new StringBuilder();
	 * sb.append("{");
	 * 
	 * boolean first = true; for (tableFldConstants field :
	 * tableFldConstants.values()) { int index = field.ordinal(); if (index <
	 * saveArray.length) { if (!first) sb.append(",");
	 * sb.append("\"").append(field.name()).append("\":"); Object val =
	 * saveArray[index]; if(field.name() == "keyid" && val == null) {
	 * sb.append("null"); }else if (val == null) { sb.append("\"{}\""); } else {
	 * sb.append("\"").append(val.toString()).append("\""); } first = false; } }
	 * 
	 * sb.append("}"); return sb.toString(); }
	 * 
	 * public static String toJsonManualList(List<AbnTlAbnormality> list) {
	 * StringBuilder sb = new StringBuilder(); sb.append("[");
	 * 
	 * for (int i = 0; i < list.size(); i++) { if (i > 0) sb.append(",");
	 * sb.append(list.get(i).toJsonManual()); }
	 * 
	 * sb.append("]"); return sb.toString(); }
	 */
}


