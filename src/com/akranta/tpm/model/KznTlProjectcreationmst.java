package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KznTlProjectcreationmst {

	private  Object [] saveArray = null;  
	private String kkeyid;
	private String mode;
	private String elementType;
	private List<KznTlProjectResourceLink> kznTlProjectResourceLinkList=null;
	private GenTlWorkflowInfo genTlWorkflowInfo;
	private String elementid;
	public enum   tableFldConstants
	{
		keyid, flid, startdate, enddate, projectname, area, projectchamp
		, projectno, benefits, savings, projectmetrics, problemstatement
		, businesscase, goalobj, scopeconst, definestage, measurestage
		, analysestage, controlstage, improvestage, closurestage,definetargetdate, measuretargetdate
		, analysetargetdate, improvetargetdate, controltargetdate, closuretargetdate,definecompleteddate, measurecompleteddate
		, analysecompleteddate,improvecompleteddate, controlcompleteddate,  closurecompleteddate, imprcategory
		, istangible, isintangible, verifiedamnt,finalamnt,amtverifyremark,wave 
		,oldresponsibility  ,belt  ,tempfield4 , active, createdby, createdon
		, modifiedon
	}

	public KznTlProjectcreationmst()
	{
		saveArray = new  Object [ 47 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object [] saveArray) {		
		 this.saveArray = saveArray;
	}
	public List<KznTlProjectResourceLink> getProjectResourceList() {
		return kznTlProjectResourceLinkList;
	}

	public void setProjectResourceList(List<KznTlProjectResourceLink> kznTlProjectResourceLinkList) {
		this.kznTlProjectResourceLinkList = kznTlProjectResourceLinkList;
	}
	public String getKzpmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKzpmKeyid(String kzpmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kzpmKeyid;
	}

	public String getKzpmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setKzpmFlid(String kzpmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = kzpmFlid;
	}

	public String getKzpmStartdate() {
		return (String) saveArray[ tableFldConstants.startdate.ordinal() ];
	}

	public void setKzpmStartdate(String kzpmStartdate) {
		saveArray[ tableFldConstants.startdate.ordinal() ] = kzpmStartdate;
	}

	public String getKzpmEnddate() {
		return (String) saveArray[ tableFldConstants.enddate.ordinal() ];
	}

	public void setKzpmEnddate(String kzpmEnddate) {
		saveArray[ tableFldConstants.enddate.ordinal() ] = kzpmEnddate;
	}

	public String getKzpmProjectname() {
		return (String) saveArray[ tableFldConstants.projectname.ordinal() ];
	}

	public void setKzpmProjectname(String kzpmProjectname) {
		saveArray[ tableFldConstants.projectname.ordinal() ] = kzpmProjectname;
	}

	public String getKzpmArea() {
		return (String) saveArray[ tableFldConstants.area.ordinal() ];
	}

	public void setKzpmArea(String kzpmArea) {
		saveArray[ tableFldConstants.area.ordinal() ] = kzpmArea;
	}

	public String getKzpmProjectchamp() {
		return (String) saveArray[ tableFldConstants.projectchamp.ordinal() ];
	}

	public void setKzpmProjectchamp(String kzpmProjectchamp) {
		saveArray[ tableFldConstants.projectchamp.ordinal() ] = kzpmProjectchamp;
	}

	public String getKzpmProjectno() {
		return (String) saveArray[ tableFldConstants.projectno.ordinal() ];
	}

	public void setKzpmProjectno(String kzpmProjectno) {
		saveArray[ tableFldConstants.projectno.ordinal() ] = kzpmProjectno;
	}
	
	public String getKzpmBenefits() {
		return (String) saveArray[ tableFldConstants.benefits.ordinal() ];
	}

	public void setKzpmBenefits(String kzpmBenefits) {
		saveArray[ tableFldConstants.benefits.ordinal() ] = kzpmBenefits;
	}

	public String getKzpmSavings() {
		return (String) saveArray[ tableFldConstants.savings.ordinal() ];
	}

	public void setKzpmSavings(String kzpmSavings) {
		saveArray[ tableFldConstants.savings.ordinal() ] = kzpmSavings;
	}

	public String getKzpmProjectmetrics() {
		return (String) saveArray[ tableFldConstants.projectmetrics.ordinal() ];
	}

	public void setKzpmProjectmetrics(String kzpmProjectmetrics) {
		saveArray[ tableFldConstants.projectmetrics.ordinal() ] = kzpmProjectmetrics;
	}

	public String getKzpmProblemstatement() {
		return (String) saveArray[ tableFldConstants.problemstatement.ordinal() ];
	}

	public void setKzpmProblemstatement(String kzpmProblemstatement) {
		saveArray[ tableFldConstants.problemstatement.ordinal() ] = kzpmProblemstatement;
	}

	public String getKzpmBusinesscase() {
		return (String) saveArray[ tableFldConstants.businesscase.ordinal() ];
	}

	public void setKzpmBusinesscase(String kzpmBusinesscase) {
		saveArray[ tableFldConstants.businesscase.ordinal() ] = kzpmBusinesscase;
	}

	public String getKzpmGoalobj() {
		return (String) saveArray[ tableFldConstants.goalobj.ordinal() ];
	}

	public void setKzpmGoalobj(String kzpmGoalobj) {
		saveArray[ tableFldConstants.goalobj.ordinal() ] = kzpmGoalobj;
	}

	public String getKzpmScopeconst() {
		return (String) saveArray[ tableFldConstants.scopeconst.ordinal() ];
	}

	public void setKzpmScopeconst(String kzpmScopeconst) {
		saveArray[ tableFldConstants.scopeconst.ordinal() ] = kzpmScopeconst;
	}

	public String getKzpmDefinestage() {
		return (String) saveArray[ tableFldConstants.definestage.ordinal() ];
	}

	public void setKzpmDefinestage(String kzpmDefinestage) {
		saveArray[ tableFldConstants.definestage.ordinal() ] = kzpmDefinestage;
	}

	public String getKzpmMeasurestage() {
		return (String) saveArray[ tableFldConstants.measurestage.ordinal() ];
	}

	public void setKzpmMeasurestage(String kzpmMeasurestage) {
		saveArray[ tableFldConstants.measurestage.ordinal() ] = kzpmMeasurestage;
	}

	public String getKzpmAnalysestage() {
		return (String) saveArray[ tableFldConstants.analysestage.ordinal() ];
	}

	public void setKzpmAnalysestage(String kzpmAnalysestage) {
		saveArray[ tableFldConstants.analysestage.ordinal() ] = kzpmAnalysestage;
	}

	public String getKzpmControlstage() {
		return (String) saveArray[ tableFldConstants.controlstage.ordinal() ];
	}

	public void setKzpmControlstage(String kzpmControlstage) {
		saveArray[ tableFldConstants.controlstage.ordinal() ] = kzpmControlstage;
	}

	public String getKzpmImprovestage() {
		return (String) saveArray[ tableFldConstants.improvestage.ordinal() ];
	}

	public void setKzpmImprovestage(String kzpmImprovestage) {
		saveArray[ tableFldConstants.improvestage.ordinal() ] = kzpmImprovestage;
	}

	public String getKzpmClosurestage() {
		return (String) saveArray[ tableFldConstants.closurestage.ordinal() ];
	}

	public void setKzpmClosurestage(String kzpmclosurestage) {
		saveArray[ tableFldConstants.closurestage.ordinal() ] = kzpmclosurestage;
	}
	
	public String getKzpmDefinetargetdate() {
		return (String) saveArray[ tableFldConstants.definetargetdate.ordinal() ];
	}

	public void setKzpmDefinetargetdate(String kzpmDefinetargetdate) {
		saveArray[ tableFldConstants.definetargetdate.ordinal() ] = kzpmDefinetargetdate;
	}

	public String getKzpmMeasuretargetdate() {
		return (String) saveArray[ tableFldConstants.measuretargetdate.ordinal() ];
	}

	public void setKzpmMeasuretargetdate(String kzpmMeasuretargetdate) {
		saveArray[ tableFldConstants.measuretargetdate.ordinal() ] = kzpmMeasuretargetdate;
	}

	public String getKzpmAnalysetargetdate() {
		return (String) saveArray[ tableFldConstants.analysetargetdate.ordinal() ];
	}

	public void setKzpmAnalysetargetdate(String kzpmAnalysetargetdate) {
		saveArray[ tableFldConstants.analysetargetdate.ordinal() ] = kzpmAnalysetargetdate;
	}

	public String getKzpmControltargetdate() {
		return (String) saveArray[ tableFldConstants.controltargetdate.ordinal() ];
	}

	public void setKzpmControltargetdate(String kzpmControltargetdate) {
		saveArray[ tableFldConstants.controltargetdate.ordinal() ] = kzpmControltargetdate;
	}

	public String getKzpmImprovetargetdate() {
		return (String) saveArray[ tableFldConstants.improvetargetdate.ordinal() ];
	}

	public void setKzpmImprovetargetdate(String kzpmImprovetargetdate) {
		saveArray[ tableFldConstants.improvetargetdate.ordinal() ] = kzpmImprovetargetdate;
	}

	public String getKzpmClosuretargetdate() {
		return (String) saveArray[ tableFldConstants.closuretargetdate.ordinal() ];
	}

	public void setKzpmClosuretargetdate(String kzpmclosuretargetdate) {
		saveArray[ tableFldConstants.closuretargetdate.ordinal() ] = kzpmclosuretargetdate;
	}
	
	public String getKzpmDefinecompleteddate() {
		return (String) saveArray[ tableFldConstants.definecompleteddate.ordinal() ];
	}

	public void setKzpmDefinecompleteddate(String kzpmDefinecompleteddate) {
		saveArray[ tableFldConstants.definecompleteddate.ordinal() ] = kzpmDefinecompleteddate;
	}

	public String getKzpmMeasurecompleteddate() {
		return (String) saveArray[ tableFldConstants.measurecompleteddate.ordinal() ];
	}

	public void setKzpmMeasurecompleteddate(String kzpmMeasurecompleteddate) {
		saveArray[ tableFldConstants.measurecompleteddate.ordinal() ] = kzpmMeasurecompleteddate;
	}

	public String getKzpmAnalysecompleteddate() {
		return (String) saveArray[ tableFldConstants.analysecompleteddate.ordinal() ];
	}

	public void setKzpmAnalysecompleteddate(String kzpmAnalysecompleteddate) {
		saveArray[ tableFldConstants.analysecompleteddate.ordinal() ] = kzpmAnalysecompleteddate;
	}

	public String getKzpmControlcompleteddate() {
		return (String) saveArray[ tableFldConstants.controlcompleteddate.ordinal() ];
	}

	public void setKzpmControlcompleteddate(String kzpmControlcompleteddate) {
		saveArray[ tableFldConstants.controlcompleteddate.ordinal() ] = kzpmControlcompleteddate;
	}

	public String getKzpmImprovecompleteddate() {
		return (String) saveArray[ tableFldConstants.improvecompleteddate.ordinal() ];
	}

	public void setKzpmImprovecompleteddate(String kzpmImprovecompleteddate) {
		saveArray[ tableFldConstants.improvecompleteddate.ordinal() ] = kzpmImprovecompleteddate;
	}

	public String getKzpmClosurecompleteddate() {
		return (String) saveArray[ tableFldConstants.closurecompleteddate.ordinal() ];
	}

	public void setKzpmClosurecompleteddate(String kzpmclosurecompleteddate) {
		saveArray[ tableFldConstants.closurecompleteddate.ordinal() ] = kzpmclosurecompleteddate;
	}

	public String getKzpmImprCategory() {
		return (String) saveArray[ tableFldConstants.imprcategory.ordinal() ];
	}

	public void setKzpmImprCategory(String kzpmImprCategory) {
		saveArray[ tableFldConstants.imprcategory.ordinal() ] = kzpmImprCategory;
	}

	public String getKzpmIstangible() {
		return (String) saveArray[ tableFldConstants.istangible.ordinal() ];
	}

	public void setKzpmIstangible(String kzpmTempfield3) {
		saveArray[ tableFldConstants.istangible.ordinal() ] = kzpmTempfield3;
	}

	public String getKzpmIsintangible() {
		return (String) saveArray[ tableFldConstants.isintangible.ordinal() ];
	}

	public void setKzpmIsintangible(String isintangible) {
		saveArray[ tableFldConstants.isintangible.ordinal() ] = isintangible;
	}

	public String getKzpmVerifiedamnt() {
		
		String verAmount =  (String) saveArray[ tableFldConstants.verifiedamnt.ordinal() ];
		return  verAmount != null && "-1".equals(verAmount) ? "":verAmount;
	}

	public void setKzpmVerifiedamnt(String verifiedamnt) {
		saveArray[ tableFldConstants.verifiedamnt.ordinal() ] = verifiedamnt;
	}
	
    public String getKzpmFinalamnt() {
		
		String finAmount =  (String) saveArray[ tableFldConstants.finalamnt.ordinal() ];
		return  finAmount != null && "-1".equals(finAmount) ? "":finAmount;
	}

	public void setKzpmFinalamnt(String finalamnt) {
		saveArray[ tableFldConstants.finalamnt.ordinal() ] = finalamnt;
	}
	
	public String getKzpmAmtverifyremarks() {
		return (String) saveArray[ tableFldConstants.amtverifyremark.ordinal() ];
	}

	public void setKzpmAmtverifyremarks(String kzpmAmtverifyremarks) {
		saveArray[ tableFldConstants.amtverifyremark.ordinal() ] = kzpmAmtverifyremarks;
	}
	
	/*public String getKzpmTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKzpmTempfield1(String kzpmTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kzpmTempfield1;
	}*/
	
	public String getKzpmWave() {
		return (String) saveArray[ tableFldConstants.wave.ordinal() ];
	}

	public void setKzpmWave(String kzpmWave) {
		saveArray[ tableFldConstants.wave.ordinal() ] = kzpmWave;
	}

	public String getKzpmoldresponsibility() {
		return (String) saveArray[ tableFldConstants.oldresponsibility.ordinal() ];
	}

	public void setKzpmoldresponsibility(String Kzpmoldresponsibility) {
		saveArray[ tableFldConstants.oldresponsibility.ordinal() ] = Kzpmoldresponsibility;
	}

	public String getKzpmBelt() {
		return (String) saveArray[ tableFldConstants.belt.ordinal() ];
	}

	public void setKzpmBelt(String kzpmBelt) {
		saveArray[ tableFldConstants.belt.ordinal() ] = kzpmBelt;
	}

	
	public String getKzpmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setKzpmTempfield4(String kzpmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = kzpmTempfield4;
	}

		
	public String getKzpmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKzpmActive(String kzpmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kzpmActive;
	}

	public String getKzpmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKzpmCreatedby(String kzpmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kzpmCreatedby;
	}

	public String getKzpmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKzpmCreatedon(String kzpmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kzpmCreatedon;
	}

	public String getKzpmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKzpmModifiedon(String kzpmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kzpmModifiedon;
	}
	
	public void setKkeyid(String kkeyid) {
		this.kkeyid = kkeyid;
	}

	public String getKkeyid() {
		return kkeyid;
	}

	public void setWorkFlowApp(GenTlWorkflowInfo genTlWorkflowInfo) {
		// TODO Auto-generated method stub
		this.genTlWorkflowInfo= genTlWorkflowInfo;
	}
	public GenTlWorkflowInfo getWorkFlowApp() {
		// TODO Auto-generated method stub
		return this.genTlWorkflowInfo;		
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getMode() {
		return mode;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementid(String elementid) {
		this.elementid = elementid;
	}

	public String getElementid() {
		return elementid;
	}
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	
	
	public String toJsonManual() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");

	    boolean first = true;
	    for (tableFldConstants field : tableFldConstants.values()) {
	        int index = field.ordinal();
	        if (index < saveArray.length) {
	            if (!first) sb.append(",");
	            sb.append("\"").append(field.name()).append("\":");
	            Object val = saveArray[index];
	            if(field.name() == "keyid" && val == null) {
	            	sb.append("null");
	            }else if (val == null) {
	                sb.append("\"{}\"");
	            } else {
	                sb.append("\"").append(val.toString()).append("\"");
	            }
	            first = false;
	        }
	    }

	    sb.append("}");
	    return sb.toString();
	}
  
 
  
  
  public static KznTlProjectcreationmst fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  KznTlProjectcreationmst pcm = new KznTlProjectcreationmst();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
	                val = "";
	            }
	        pcm.setValue(field,  val);
	    	
	    }
	    return pcm;
	}
  
  

}

