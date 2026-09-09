package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.AbnTlAbnormality.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class OplTlMst {

	private  Object [] saveArray = null;  
	
	private List <OplTlPillarlink> PillarLink;
	private List <OplTlStudent> StudentLink;
	private List<GenTlAllmoduleimgfile> allmoduleimgfile;

	public enum   tableFldConstants
	{
		keyid, date, factoryid, tpmpillarid, sectionid, cellid, machineid
		, theme, themecategoryid, classification, classdescription, benefit
		, type, tradeid, presentcondition, presentimage, aftercondition
		, afterimage, lesson, preparedid, prepareddate, approvedid, approveddate
		, status, refdoctype, refdocno, remarks, relatedto, departmentmanager
		, sectionmanager, groupleader, requestflag, related, mouldid
		, isok, ispresent, elementid, flid, process,isupload, utiliseforfuture
		, mpworthy, aprovLevel, general, oplupload, tempfield4, tempfield5
		, active, createdby, createdon, modifiedon
	}
	
	public OplTlMst()
	{
	//	saveArray = new  Object [ 51 ];
		saveArray = new  Object [ 52 ];
		setPillarLink(new ArrayList<OplTlPillarlink>());
		setStudentLink(new ArrayList<OplTlStudent>());
		setAllmoduleimgfile(new ArrayList<GenTlAllmoduleimgfile>());
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	// --vignesh -- //
	
	public Object getValue(tableFldConstants field) {
	    return saveArray[field.ordinal()];
	}

	public void setValue(tableFldConstants field, Object value) {
	    saveArray[field.ordinal()] = value;
	}
	
	public String getOplmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setOplmKeyid(String oplmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = oplmKeyid;
	}

	public String getOplmDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setOplmDate(String oplmDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = oplmDate;
	}

	public String getOplmFactoryid() {
		return (String) saveArray[ tableFldConstants.factoryid.ordinal() ];
	}

	public void setOplmFactoryid(String oplmFactoryid) {
		saveArray[ tableFldConstants.factoryid.ordinal() ] = oplmFactoryid;
	}

	public String getOplmTpmpillarid() {
		return (String) saveArray[ tableFldConstants.tpmpillarid.ordinal() ];
	}

	public void setOplmTpmpillarid(String oplmTpmpillarid) {
		saveArray[ tableFldConstants.tpmpillarid.ordinal() ] = oplmTpmpillarid;
	}

	public String getOplmSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setOplmSectionid(String oplmSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = oplmSectionid;
	}

	public String getOplmCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setOplmCellid(String oplmCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = oplmCellid;
	}

	public String getOplmMachineid() {
		return (String) saveArray[ tableFldConstants.machineid.ordinal() ];
	}

	public void setOplmMachineid(String oplmMachineid) {
		saveArray[ tableFldConstants.machineid.ordinal() ] = oplmMachineid;
	}

	public String getOplmTheme() {
		return (String) saveArray[ tableFldConstants.theme.ordinal() ];
	}

	public void setOplmTheme(String oplmTheme) {
		saveArray[ tableFldConstants.theme.ordinal() ] = oplmTheme;
	}

	public String getOplmThemecategoryid() {
		return (String) saveArray[ tableFldConstants.themecategoryid.ordinal() ];
	}

	public void setOplmThemecategoryid(String oplmThemecategoryid) {
		saveArray[ tableFldConstants.themecategoryid.ordinal() ] = oplmThemecategoryid;
	}

	public String getOplmClassification() {
		return (String) saveArray[ tableFldConstants.classification.ordinal() ];
	}

	public void setOplmClassification(String oplmClassification) {
		saveArray[ tableFldConstants.classification.ordinal() ] = oplmClassification;
	}

	public String getOplmClassdescription() {
		return (String) saveArray[ tableFldConstants.classdescription.ordinal() ];
	}

	public void setOplmClassdescription(String oplmClassdescription) {
		saveArray[ tableFldConstants.classdescription.ordinal() ] = oplmClassdescription;
	}

	public String getOplmBenefit() {
		return (String) saveArray[ tableFldConstants.benefit.ordinal() ];
	}

	public void setOplmBenefit(String oplmBenefit) {
		saveArray[ tableFldConstants.benefit.ordinal() ] = oplmBenefit;
	}

	public String getOplmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setOplmType(String oplmType) {
		saveArray[ tableFldConstants.type.ordinal() ] = oplmType;
	}

	public String getOplmTradeid() {
		return (String) saveArray[ tableFldConstants.tradeid.ordinal() ];
	}

	public void setOplmTradeid(String oplmTradeid) {
		saveArray[ tableFldConstants.tradeid.ordinal() ] = oplmTradeid;
	}

	public String getOplmPresentcondition() {
		return (String) saveArray[ tableFldConstants.presentcondition.ordinal() ];
	}

	public void setOplmPresentcondition(String oplmPresentcondition) {
		saveArray[ tableFldConstants.presentcondition.ordinal() ] = oplmPresentcondition;
	}

	public String getOplmPresentimage() {
		return (String) saveArray[ tableFldConstants.presentimage.ordinal() ];
	}

	public void setOplmPresentimage(String oplmPresentimage) {
		saveArray[ tableFldConstants.presentimage.ordinal() ] = oplmPresentimage;
	}

	public String getOplmAftercondition() {
		return (String) saveArray[ tableFldConstants.aftercondition.ordinal() ];
	}

	public void setOplmAftercondition(String oplmAftercondition) {
		saveArray[ tableFldConstants.aftercondition.ordinal() ] = oplmAftercondition;
	}

	public String getOplmAfterimage() {
		return (String) saveArray[ tableFldConstants.afterimage.ordinal() ];
	}

	public void setOplmAfterimage(String oplmAfterimage) {
		saveArray[ tableFldConstants.afterimage.ordinal() ] = oplmAfterimage;
	}

	public String getOplmLesson() {
		return (String) saveArray[ tableFldConstants.lesson.ordinal() ];
	}

	public void setOplmLesson(String oplmLesson) {
		saveArray[ tableFldConstants.lesson.ordinal() ] = oplmLesson;
	}

	public String getOplmPreparedid() {
		return (String) saveArray[ tableFldConstants.preparedid.ordinal() ];
	}

	public void setOplmPreparedid(String oplmPreparedid) {
		saveArray[ tableFldConstants.preparedid.ordinal() ] = oplmPreparedid;
	}

	public String getOplmPrepareddate() {
		return (String) saveArray[ tableFldConstants.prepareddate.ordinal() ];
	}

	public void setOplmPrepareddate(String oplmPrepareddate) {
		saveArray[ tableFldConstants.prepareddate.ordinal() ] = oplmPrepareddate;
	}

	public String getOplmApprovedid() {
		return (String) saveArray[ tableFldConstants.approvedid.ordinal() ];
	}

	public void setOplmApprovedid(String oplmApprovedid) {
		saveArray[ tableFldConstants.approvedid.ordinal() ] = oplmApprovedid;
	}

	public String getOplmApproveddate() {
		return (String) saveArray[ tableFldConstants.approveddate.ordinal() ];
	}

	public void setOplmApproveddate(String oplmApproveddate) {
		saveArray[ tableFldConstants.approveddate.ordinal() ] = oplmApproveddate;
	}

	public String getOplmStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setOplmStatus(String oplmStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = oplmStatus;
	}

	public String getOplmRefdoctype() {
		return (String) saveArray[ tableFldConstants.refdoctype.ordinal() ];
	}

	public void setOplmRefdoctype(String oplmRefdoctype) {
		saveArray[ tableFldConstants.refdoctype.ordinal() ] = oplmRefdoctype;
	}

	public String getOplmRefdocno() {
		return (String) saveArray[ tableFldConstants.refdocno.ordinal() ];
	}

	public void setOplmRefdocno(String oplmRefdocno) {
		saveArray[ tableFldConstants.refdocno.ordinal() ] = oplmRefdocno;
	}

	public String getOplmRemarks() {
		return (String) saveArray[ tableFldConstants.remarks.ordinal() ];
	}

	public void setOplmRemarks(String oplmRemarks) {
		saveArray[ tableFldConstants.remarks.ordinal() ] = oplmRemarks;
	}

	public String getOplmRelatedto() {
		return (String) saveArray[ tableFldConstants.relatedto.ordinal() ];
	}

	public void setOplmRelatedto(String oplmRelatedto) {
		saveArray[ tableFldConstants.relatedto.ordinal() ] = oplmRelatedto;
	}

	public String getOplmDepartmentmanager() {
		return (String) saveArray[ tableFldConstants.departmentmanager.ordinal() ];
	}

	public void setOplmDepartmentmanager(String oplmDepartmentmanager) {
		saveArray[ tableFldConstants.departmentmanager.ordinal() ] = oplmDepartmentmanager;
	}

	public String getOplmSectionmanager() {
		return (String) saveArray[ tableFldConstants.sectionmanager.ordinal() ];
	}

	public void setOplmSectionmanager(String oplmSectionmanager) {
		saveArray[ tableFldConstants.sectionmanager.ordinal() ] = oplmSectionmanager;
	}

	public String getOplmGroupleader() {
		return (String) saveArray[ tableFldConstants.groupleader.ordinal() ];
	}

	public void setOplmGroupleader(String oplmGroupleader) {
		saveArray[ tableFldConstants.groupleader.ordinal() ] = oplmGroupleader;
	}

	public String getOplmRequestflag() {
		return (String) saveArray[ tableFldConstants.requestflag.ordinal() ];
	}

	public void setOplmRequestflag(String oplmRequestflag) {
		saveArray[ tableFldConstants.requestflag.ordinal() ] = oplmRequestflag;
	}

	public String getOplmRelated() {
		return (String) saveArray[ tableFldConstants.related.ordinal() ];
	}

	public void setOplmRelated(String oplmRelated) {
		saveArray[ tableFldConstants.related.ordinal() ] = oplmRelated;
	}

	public String getOplmMouldid() {
		return (String) saveArray[ tableFldConstants.mouldid.ordinal() ];
	}

	public void setOplmMouldid(String oplmMouldid) {
		saveArray[ tableFldConstants.mouldid.ordinal() ] = oplmMouldid;
	}

	public String getOplmIsok() {
		return (String) saveArray[ tableFldConstants.isok.ordinal() ];
	}

	public void setOplmIsok(String oplmIsok) {
		saveArray[ tableFldConstants.isok.ordinal() ] = oplmIsok;
	}

	public String getOplmIspresent() {
		return (String) saveArray[ tableFldConstants.ispresent.ordinal() ];
	}

	public void setOplmIspresent(String oplmIspresent) {
		saveArray[ tableFldConstants.ispresent.ordinal() ] = oplmIspresent;
	}

	public String getOplmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setOplmElementid(String oplmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = oplmElementid;
	}

	public String getOplmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setOplmFlid(String oplmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = oplmFlid;
	}

	public String getOplmProcess() {
		return (String) saveArray[ tableFldConstants.process.ordinal() ];
	}

	public void setOplmProcess(String oplmProcess) {
		saveArray[ tableFldConstants.process.ordinal() ] = oplmProcess;
	}
	
	
	public String getOplmIsUpload() {
		return (String) saveArray[ tableFldConstants.isupload.ordinal() ];
	}

	public void setOplmIsUpload(String oplmIsUpload) {
		saveArray[ tableFldConstants.isupload.ordinal() ] = oplmIsUpload;
	}

	
	public String getOplmUtiliseforfuture() {
		return (String) saveArray[ tableFldConstants.utiliseforfuture.ordinal() ];
	}

	public void setOplmUtiliseforfuture(String oplmUtiliseforfuture) {
		saveArray[ tableFldConstants.utiliseforfuture.ordinal() ] = oplmUtiliseforfuture;
	}

	public String getOplmMpworthy() {
		return (String) saveArray[ tableFldConstants.mpworthy.ordinal() ];
	}

	public void setOplmMpworthy(String oplmMpworthy) {
		saveArray[ tableFldConstants.mpworthy.ordinal() ] = oplmMpworthy;
	}

	public String getOplmAprovLevel() {
		return (String) saveArray[ tableFldConstants.aprovLevel.ordinal() ];
	}

	public void setOplmAprovLevel(String oplmAprovLevel) {
		saveArray[ tableFldConstants.aprovLevel.ordinal() ] = oplmAprovLevel;
	}

	public String getOplmIsgeneral() {
		return (String) saveArray[ tableFldConstants.general.ordinal() ];
	}

	public void setOplmIsgeneral(String oplmGeneral) {
		saveArray[ tableFldConstants.general.ordinal() ] = oplmGeneral;
	}

	public String getOplmOplupload() {
		return (String) saveArray[ tableFldConstants.oplupload.ordinal() ];
	}

	public void setOplmOplupload(String oplmOplupload) {
		saveArray[ tableFldConstants.oplupload.ordinal() ] = oplmOplupload;
	}

	public String getOplmTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setOplmTempfield4(String oplmTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = oplmTempfield4;
	}

	public String getOplmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setOplmTempfield5(String oplmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = oplmTempfield5;
	}

	public String getOplmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setOplmActive(String oplmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = oplmActive;
	}

	public String getOplmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setOplmCreatedby(String oplmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = oplmCreatedby;
	}

	public String getOplmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setOplmCreatedon(String oplmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = oplmCreatedon;
	}

	public String getOplmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setOplmModifiedon(String oplmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = oplmModifiedon;
	}
/**
	 * @param pillarLink the pillarLink to set
	 */
	public void setPillarLink(List <OplTlPillarlink> pillarLink) {
		PillarLink = pillarLink;
	}
	
	public void setStudentLink(List<OplTlStudent> newOplTlStudent) {
		// TODO Auto-generated method stub
		StudentLink = newOplTlStudent;
		
	}
	public List<OplTlStudent> getStudentLink() {
		return StudentLink;
	}
	/**
	 * @return the pillarLink
	 */
	public List <OplTlPillarlink> getPillarLink() {
		return PillarLink;
	}

	/**
	 * @param allmoduleimgfile the allmoduleimgfile to set
	 */
	public void setAllmoduleimgfile(List<GenTlAllmoduleimgfile> allmoduleimgfile) {
		this.allmoduleimgfile = allmoduleimgfile;
	}

	/**
	 * @return the allmoduleimgfile
	 */
	public List<GenTlAllmoduleimgfile> getAllmoduleimgfile() {
		return allmoduleimgfile;
	}
	
	private static String jsonKey(tableFldConstants field) {
	    // ✅ Spring Boot JSON uses "isgeneral", but your enum is "general"
	    if (field == tableFldConstants.general) return "isgeneral";
	    return field.name();
	}

	private static String escapeJson(String s) {
	    if (s == null) return null;
	    return s.replace("\\", "\\\\")
	            .replace("\"", "\\\"")
	            .replace("\r", "\\r")
	            .replace("\n", "\\n")
	            .replace("\t", "\\t");
	}

	// ✅ Needed for fromJson()
	public void setValue(tableFldConstants field, String value) {
	    if (value != null && "null".equalsIgnoreCase(value)) value = null;
	    saveArray[field.ordinal()] = value;
	}
	public String toJsonManual() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("{");

	    boolean first = true;
	    for (tableFldConstants field : tableFldConstants.values()) {
	        int index = field.ordinal();
	        if (index >= saveArray.length) continue;

	        if (!first) sb.append(",");
	        String key = jsonKey(field);

	        sb.append("\"").append(key).append("\":");

	        Object val = saveArray[index];

	        // ✅ keyid must be null on insert (API generates it)
	        if (field == tableFldConstants.keyid && val == null) {
	            sb.append("null");
	        }
	        // ✅ keep approval fields null until approval
	        else if ((field == tableFldConstants.approvedid || field == tableFldConstants.approveddate) && val == null) {
	            sb.append("null");
	        }
	        // ✅ for everything else, if null send "{}" (your existing convention)
	        else if (val == null) {
	            sb.append("\"{}\"");
	          
	        } else {
	            sb.append("\"").append(escapeJson(val.toString())).append("\"");
	        }

	        first = false;
	    }

	    sb.append("}");
	    return sb.toString();
	}
	  
	public static OplTlMst fromJson(String json) {
	    CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	    JSONObject obj = JSONObject.fromObject(json);

	    OplTlMst o = new OplTlMst();
	    for (tableFldConstants field : tableFldConstants.values()) {
	        String key = jsonKey(field);

	        // optString returns "" if key exists but empty, null if missing
	        String val = obj.optString(key, null);
	        
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }

	        o.setValue(field, (val != null && "null".equalsIgnoreCase(val)) ? null : val);
	    }
	    return o;
	}

	
}

