package com.akranta.tpm.model;

import com.akranta.tpm.model.BAL_PcsTlHtProductionHistory.tableFldConstants;

public class BAL_PcsTlHtDetails {
	

	private  Object [] saveArray = null; 
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

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

		public BAL_PcsTlHtDetails()
		{
			saveArray = new  Object [ 113 ];
		}
		public Object[] getSaveArray() {
			return saveArray;
		}
		public String getKeyid() {
			return (String) saveArray[ tableFldConstants.htpdetailsid.ordinal() ];
		}

		public void setKeyid(String keyid) {
			saveArray[ tableFldConstants.htpdetailsid.ordinal() ] = keyid;
		}
		public String getMasterid() {
			return (String) saveArray[ tableFldConstants.plmasterid.ordinal() ];
		}

		public void setMasterid(String masterkeyid) {
			saveArray[ tableFldConstants.plmasterid.ordinal() ] = masterkeyid;
		}
		public String getArea() {
			return (String) saveArray[ tableFldConstants.area.ordinal() ];
		}

		public void setArea(String area) {
			saveArray[ tableFldConstants.area.ordinal() ] = area;
		}
		public String getManpower() {
			return (String) saveArray[ tableFldConstants.manpower.ordinal() ];
		}

		public void setManpower(String manpower) {
			saveArray[ tableFldConstants.manpower.ordinal() ] = manpower;
		}public String getCalendarTime() {
			return (String) saveArray[ tableFldConstants.calendartime.ordinal() ];
		}

		public void setCalendarTime(String calendarTime) {
			saveArray[ tableFldConstants.calendartime.ordinal() ] = calendarTime;
		}public String getPlanedQty() {
			return (String) saveArray[ tableFldConstants.planedQty.ordinal() ];
		}

		public void setPlanedQty(String plnQty) {
			saveArray[ tableFldConstants.planedQty.ordinal() ] = plnQty;
		}
		public String getNoofProduct() {
			return (String) saveArray[ tableFldConstants.noOfProduct.ordinal() ];
		}

		public void setNoofProduct(String noOfProduct) {
			saveArray[ tableFldConstants.noOfProduct.ordinal() ] = noOfProduct;
		}
		public String getProductid() {
			return (String) saveArray[ tableFldConstants.productid.ordinal() ];
		}

		public void setProductid(String productid) {
			saveArray[ tableFldConstants.productid.ordinal() ] = productid;
		}
		public String getNoplanMints() {
			return (String) saveArray[ tableFldConstants.noPlanMins.ordinal() ];
		}

		public void setNoplanMints(String noPlanMin) {
			saveArray[ tableFldConstants.noPlanMins.ordinal() ] = noPlanMin;
		}
		public String getRnt() {
		
			return (String) saveArray[ tableFldConstants.rnt.ordinal() ];
		}

		public void setRnt(String rnt) {
			saveArray[ tableFldConstants.rnt.ordinal() ] = rnt;
		}
		public String getCellmaning() {
			return (String) saveArray[ tableFldConstants.cellmaning.ordinal() ];
		}

		public void setCellmaning(String cellmng) {
			saveArray[ tableFldConstants.cellmaning.ordinal() ] = cellmng;
		}
		public String getProduceQty() {
			return (String) saveArray[ tableFldConstants.prodecdQty.ordinal() ];
		}

		public void setProduceQty(String prodecdQty) {
			saveArray[ tableFldConstants.prodecdQty.ordinal() ] = prodecdQty;
		}
		public String getProcessStopage() {
			return (String) saveArray[ tableFldConstants.processStopage.ordinal() ];
		}

		public void setProcessStopage(String processStopage) {
			saveArray[ tableFldConstants.processStopage.ordinal() ] = processStopage;
		}
		public String getActualCharges() {
			return (String) saveArray[ tableFldConstants.actualCharges.ordinal() ];
		}

		public void setActualCharges(String actualCharges) {
			saveArray[ tableFldConstants.actualCharges.ordinal() ] = actualCharges;
		}
		public String getCalculatedCharges() {
			return (String) saveArray[ tableFldConstants.calculatedCharges.ordinal() ];
		}

		public void setCalculatedCharges(String calculatedCharges) {
			saveArray[ tableFldConstants.calculatedCharges.ordinal() ] = calculatedCharges;
		}
		public String getDefectStdLoss() {
			return (String) saveArray[ tableFldConstants.defectstandardworkLoss.ordinal() ];
		}

		public void setDefectStdLoss(String dfctStdLoss) {
			saveArray[ tableFldConstants.defectstandardworkLoss.ordinal() ] = dfctStdLoss;
		}
		public String getRejectedQty() {
			return (String) saveArray[ tableFldConstants.rejectedQty.ordinal() ];
		}

		public void setRejectedQty(String rejectedQty) {
			saveArray[ tableFldConstants.rejectedQty.ordinal() ] = rejectedQty;
		}
		public String getReworkQty() {
			return (String) saveArray[ tableFldConstants.reworkQty.ordinal() ];
		}

		public void setReworkQty(String reworkQty) {
			saveArray[ tableFldConstants.reworkQty.ordinal() ] = reworkQty;
		}
		public String getReprocessQty() {
			return (String) saveArray[ tableFldConstants.reprocessQty.ordinal() ];
		}

		public void setReprocessQty(String reProcessQty) {
			saveArray[ tableFldConstants.reprocessQty.ordinal() ] = reProcessQty;
		}
		public String getProductionLoss() {
			return (String) saveArray[ tableFldConstants.productionLoss.ordinal() ];
		}

		public void setProductionLoss(String prdLoss) {
			saveArray[ tableFldConstants.productionLoss.ordinal() ] = prdLoss;
		}
		public String setEquipmentFailuare() {
			return (String) saveArray[ tableFldConstants.equipmentFailuare_ml.ordinal() ];
		}
		public void setEquipmentFailuare(String eqflmin) {
			saveArray[ tableFldConstants.equipmentFailuare_ml.ordinal() ] = eqflmin;
		}
		public String getEqMechanical() {
			return (String) saveArray[ tableFldConstants.Eq_mechanical.ordinal() ];
		}

		public void setEqMechanical(String eqMech) {
			saveArray[ tableFldConstants.Eq_mechanical.ordinal() ] = eqMech;
		}
		public String getEqElectrical() {
			return (String) saveArray[ tableFldConstants.Eq_electrical.ordinal() ];
		}

		public void setEqElectrical(String eqEle) {
			saveArray[ tableFldConstants.Eq_electrical.ordinal() ] = eqEle;
		}
		public String getEqElectronics() {
			return (String) saveArray[ tableFldConstants.Eq_electronics.ordinal() ];
		}

		public void setEqElectronics(String eqElecs) {
			saveArray[ tableFldConstants.Eq_electronics.ordinal() ] = eqElecs;
		}
		public String getEqJigfixture() {
			return (String) saveArray[ tableFldConstants.Eq_jigfixture.ordinal() ];
		}

		public void setEqJigfixture(String eqEqJig) {
			saveArray[ tableFldConstants.Eq_jigfixture.ordinal() ] = eqEqJig;
		}
		public String getStartupLoss() {
			return (String) saveArray[ tableFldConstants.startupLoss.ordinal() ];
		}

		public void setStartupLoss(String strtUp) {
			saveArray[ tableFldConstants.startupLoss.ordinal() ] = strtUp;
		}
		public String getJhTime() {
			return (String) saveArray[ tableFldConstants.jhTime.ordinal() ];
		}

		public void setJhTime(String jhTime) {
			saveArray[ tableFldConstants.jhTime.ordinal() ] = jhTime;
		}
		public String getMeating() {
			return (String) saveArray[ tableFldConstants.meating.ordinal() ];
		}

		public void setMeating(String meatingTime) {
			saveArray[ tableFldConstants.meating.ordinal() ] = meatingTime;
		}
		public String getMinorstopage() {
			return (String) saveArray[ tableFldConstants.minorstopage.ordinal() ];
		}

		public void setMinorstopage(String keyid) {
			saveArray[ tableFldConstants.minorstopage.ordinal() ] = keyid;
		}
		public String getSpeedLoss() {
			return (String) saveArray[ tableFldConstants.speedLoss.ordinal() ];
		}

		public void setSpeedLoss(String speedLossTm) {
			saveArray[ tableFldConstants.speedLoss.ordinal() ] = speedLossTm;
		}
		public String getShutDownLoss() {
			return (String) saveArray[ tableFldConstants.shutDownloss.ordinal() ];
		}

		public void setShutDownLoss(String shtdwnloss) {
			saveArray[ tableFldConstants.shutDownloss.ordinal() ] = shtdwnloss;
		}
		public String getPmTime() {
			return (String) saveArray[ tableFldConstants.pmtime.ordinal() ];
		}

		public void setPmTime(String pmTime) {
			saveArray[ tableFldConstants.pmtime.ordinal() ] = pmTime;
		}
		public String getTrainig() {
			return (String) saveArray[ tableFldConstants.trainig.ordinal() ];
		}

		public void setTrainig(String trngTime) {
			saveArray[ tableFldConstants.trainig.ordinal() ] = trngTime;
		}
		public String getBreaks() {
			return (String) saveArray[ tableFldConstants.breaks.ordinal() ];
		}

		public void setBreaks(String breaktime) {
			saveArray[ tableFldConstants.breaks.ordinal() ] = breaktime;
		}
		public String getNewProdDev() {
			return (String) saveArray[ tableFldConstants.newProdDev.ordinal() ];
		}

		public void setNewProdDev(String newPrdDev) {
			saveArray[ tableFldConstants.newProdDev.ordinal() ] = newPrdDev;
		}
		public String getManagementLoss() {
			return (String) saveArray[ tableFldConstants.mgtLoss.ordinal() ];
		}

		public void setManagementLoss(String mgtlosstm) {
			saveArray[ tableFldConstants.mgtLoss.ordinal() ] = mgtlosstm;
		}
		public String getNoMaterialFactory() {
			return (String) saveArray[ tableFldConstants.noMaterialFactory.ordinal() ];
		}

		public void getNoMaterialFactory(String nomtrfact) {
			saveArray[ tableFldConstants.noMaterialFactory.ordinal() ] = nomtrfact;
		}
		public String getNoMaterialPpc() {
			return (String) saveArray[ tableFldConstants.noMaterialPpc.ordinal() ];
		}

		public void setNoMaterialPpc(String nomtrppc) {
			saveArray[ tableFldConstants.noMaterialPpc.ordinal() ] = nomtrppc;
		}
		public String getNoPlanFrnng() {
			return (String) saveArray[ tableFldConstants.NoPlanFurning.ordinal() ];
		}

		public void setNoPlanFrnng(String noplnfrng) {
			saveArray[ tableFldConstants.NoPlanFurning.ordinal() ] = noplnfrng;
		}
		public String getNoTool() {
			return (String) saveArray[ tableFldConstants.notool.ordinal() ];
		}

		public void setNoTool(String notool) {
			saveArray[ tableFldConstants.notool.ordinal() ] = notool;
		}
		public String getNoManPower() {
			return (String) saveArray[ tableFldConstants.nomanpower.ordinal() ];
		}

		public void setNoManPower(String noman) {
			saveArray[ tableFldConstants.nomanpower.ordinal() ] = noman;
		}
		public String getLineChangOvr() {
			return (String) saveArray[ tableFldConstants.linechangeover.ordinal() ];
		}

		public void setLineChangOvr(String lnchgovr) {
			saveArray[ tableFldConstants.linechangeover.ordinal() ] = lnchgovr;
		}
		public String getCommonUtilities() {
			return (String) saveArray[ tableFldConstants.commonutilitiesloss.ordinal() ];
		}

		public void setCommonUtilities(String cmnutility) {
			saveArray[ tableFldConstants.commonutilitiesloss.ordinal() ] = cmnutility;
		}
		public String getNoPower() {
			return (String) saveArray[ tableFldConstants.nopower.ordinal() ];
		}

		public void setNoPower(String power) {
			saveArray[ tableFldConstants.nopower.ordinal() ] = power;
		}
		
		public String getNoAir() {
			return (String) saveArray[ tableFldConstants.noAir.ordinal() ];
		}

		public void setNoAir(String noAir) {
			saveArray[ tableFldConstants.noAir.ordinal() ] = noAir;
		}
		public String getNoGas() {
			return (String) saveArray[ tableFldConstants.noGas.ordinal() ];
		}

		public void setNoGas(String nogas) {
			saveArray[ tableFldConstants.noGas.ordinal() ] = nogas;
		}
		
		public String getNoCoolent() {
			return (String) saveArray[ tableFldConstants.noCoolent.ordinal() ];
		}

		public void setNoCoolent(String nocoolent) {
			saveArray[ tableFldConstants.noCoolent.ordinal() ] = nocoolent;
		}
		public String getLogisticLoss() {
			return (String) saveArray[ tableFldConstants.logisticLoss.ordinal() ];
		}

		public void setLogisticLoss(String logisticloss) {
			saveArray[ tableFldConstants.logisticLoss.ordinal() ] = logisticloss;
		}
		public String getTheoroticalCycletime() {
			return (String) saveArray[ tableFldConstants.theoroticalCycletime.ordinal() ];
		}

		public void setTheoroticalCycletime(String thrcycltm) {
			saveArray[ tableFldConstants.theoroticalCycletime.ordinal() ] = thrcycltm;
		}
		public String getActualCycleTime() {
			return (String) saveArray[ tableFldConstants.actualCycletime.ordinal() ];
		}

		public void setActualCycleTime(String actCycltime) {
			saveArray[ tableFldConstants.actualCycletime.ordinal() ] = actCycltime;
		}
		public String getMchAvailabletime() {
			return (String) saveArray[ tableFldConstants.mchAvailabletime.ordinal() ];
		}

		public void setMchAvailabletime(String mchAvbltime) {
			saveArray[ tableFldConstants.mchAvailabletime.ordinal() ] = mchAvbltime;
		}
		public String getProductAvailabetime() {
			return (String) saveArray[ tableFldConstants.productavailabetime.ordinal() ];
		}
		public void setProductAvailabetime(String prdAvltime) {
			saveArray[ tableFldConstants.noGas.ordinal() ] = prdAvltime;
		}
		public String getProductionTime() {
			return (String) saveArray[ tableFldConstants.productiontime.ordinal() ];
		}
		
		public void getProductionTime(String prdtime) {
			saveArray[ tableFldConstants.productiontime.ordinal() ] = prdtime;
		}
		public String getCycleTimeVariation() {
			return (String) saveArray[ tableFldConstants.cycletimevariation.ordinal() ];
		}

		public void setCycleTimeVariation(String cyclTmVrn) {
			saveArray[ tableFldConstants.cycletimevariation.ordinal() ] = cyclTmVrn;
		}
		
		public String getLoadingTime() {
			return (String) saveArray[ tableFldConstants.loadingtime.ordinal() ];
		}

		public void setLoadingTime(String loadingTm) {
			saveArray[ tableFldConstants.cycletimevariation.ordinal() ] = loadingTm;
		}
		public String getEffProdmins() {
			return (String) saveArray[ tableFldConstants.effprodmins.ordinal() ];
		}

		public void setEffProdmins(String effprdmin) {
			saveArray[ tableFldConstants.effprodmins.ordinal() ] = effprdmin;
		}
		public String getRoa() {
			return (String) saveArray[ tableFldConstants.ROA.ordinal() ];
		}

		public void setRoa(String roa) {
			saveArray[ tableFldConstants.ROA.ordinal() ] = roa;
		}
		
		public String getRop() {
			return (String) saveArray[ tableFldConstants.ROP.ordinal() ];
		}

		public void setRop(String rop) {
			saveArray[ tableFldConstants.ROP.ordinal() ] = rop;
		}
		public String getRoq() {
			return (String) saveArray[ tableFldConstants.ROQ.ordinal() ];
		}

		public void setRoq(String roq) {
			saveArray[ tableFldConstants.ROQ.ordinal() ] = roq;
		}
		public String getOee() {
			return (String) saveArray[ tableFldConstants.OEE.ordinal() ];
		}

		public void setOee(String oee) {
			saveArray[ tableFldConstants.OEE.ordinal() ] = oee;
		}
		public String getRemarks() {
			return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
		}

		public void setRemarks(String remarks) {
			saveArray[ tableFldConstants.remarks.ordinal() ] = remarks;
		}
		
		public String getProductionTimeLoss() {
			return (String) saveArray[ tableFldConstants.productiontimeloss.ordinal() ];
		}

		public void setProductionTimeLoss(String prdtimeloss) {
			saveArray[ tableFldConstants.productiontimeloss.ordinal() ] = prdtimeloss;
		}
		public String getTempField1() {
			return (String) saveArray[ tableFldConstants.temp1.ordinal() ];
		}

		public void setTempField1(String temp1) {
			saveArray[ tableFldConstants.temp1.ordinal() ] = temp1;
		}
		public String getTempField2() {
			return (String) saveArray[ tableFldConstants.temp2.ordinal() ];
		}

		public void setTempField2(String temp2) {
			saveArray[ tableFldConstants.temp2.ordinal() ] = temp2;
		}
		
		public String getTempField3() {
			return (String) saveArray[ tableFldConstants.temp3.ordinal() ];
		}

		public void setTempField3(String temp3) {
			saveArray[ tableFldConstants.temp2.ordinal() ] = temp3;
		}
		
		public void setActive(String active) {
			saveArray[ tableFldConstants.active.ordinal() ] = active;
		}
		public String getCreatedon(){
			return (String )saveArray [tableFldConstants.createdon.ordinal() ];
		}
		public void setCreatedon(String createdon) {
			saveArray[ tableFldConstants.createdon.ordinal() ] = createdon;
		}
		
		public String getCreatedby(){
			return (String )saveArray [tableFldConstants.createdby.ordinal() ];
		}
		public void setCreatedby(String createdby) {
			saveArray[ tableFldConstants.createdon.ordinal() ] = createdby;
		}
		public String getModifiedon(){
			return (String )saveArray [tableFldConstants.modifiedon.ordinal() ];
		}
		public void setModifiedon(String modifiedon) {
			saveArray[ tableFldConstants.modifiedon.ordinal() ] = modifiedon;
		}
		
		
		public String getCompletedFlag(){
			return (String )saveArray [tableFldConstants.completedFlag.ordinal() ];
		}
		public void setCompletedFlag(String complFlag) {
			saveArray[ tableFldConstants.modifiedon.ordinal() ] = complFlag;
		}
		
		
		
		
		
}
