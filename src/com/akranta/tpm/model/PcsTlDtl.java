package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PcsTlDtl {

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
		, loss91, loss92, loss93, loss94, loss95, loss96, loss97, trimmingqty, expansionqty
		, modelchangepart, reprocessing, INSPECTEDQTY
		, QAACCEPTEDQTY, backlogqty, active, createdby, createdon, modifiedon
	}

	public PcsTlDtl()
	{
		saveArray = new  Object [ 164 ];
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

}


