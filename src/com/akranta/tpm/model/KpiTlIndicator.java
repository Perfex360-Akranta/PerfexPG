package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.KpiTlIndicatorKk.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KpiTlIndicator {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, indicatorname, indicatorcode, description, parentid, levelno
		, sortno, ischild, inputtype, inputentry, identifier, manualcalctype
		, uomid, frequency, excelname, dept_keyid, costarea, targetneed
		, pillarid, type, impactarea, goals, sourceofkpi, kpireason, annualtarget
		, startdate, enddate,location, active, createdby, createdon, modifiedon
	}

	public KpiTlIndicator()
	{
		saveArray = new  Object [ 32 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getKinkKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKinkKeyid(String kinkKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kinkKeyid;
	}

	public String getKinkIndicatorname() {
		return (String) saveArray[ tableFldConstants.indicatorname.ordinal() ];
	}

	public void setKinkIndicatorname(String kinkIndicatorname) {
		saveArray[ tableFldConstants.indicatorname.ordinal() ] = kinkIndicatorname;
	}

	public String getKinkIndicatorcode() {
		return (String) saveArray[ tableFldConstants.indicatorcode.ordinal() ];
	}

	public void setKinkIndicatorcode(String kinkIndicatorcode) {
		saveArray[ tableFldConstants.indicatorcode.ordinal() ] = kinkIndicatorcode;
	}

	public String getKinkDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setKinkDescription(String kinkDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = kinkDescription;
	}

	public String getKinkParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setKinkParentid(String kinkParentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = kinkParentid;
	}

	public String getKinkLevelno() {
		return (String) saveArray[ tableFldConstants.levelno.ordinal() ];
	}

	public void setKinkLevelno(String kinkLevelno) {
		saveArray[ tableFldConstants.levelno.ordinal() ] = kinkLevelno;
	}

	public String getKinkSortno() {
		return (String) saveArray[ tableFldConstants.sortno.ordinal() ];
	}

	public void setKinkSortno(String kinkSortno) {
		saveArray[ tableFldConstants.sortno.ordinal() ] = kinkSortno;
	}

	public String getKinkIschild() {
		return (String) saveArray[ tableFldConstants.ischild.ordinal() ];
	}

	public void setKinkIschild(String kinkIschild) {
		saveArray[ tableFldConstants.ischild.ordinal() ] = kinkIschild;
	}

	public String getKinkInputtype() {
		return (String) saveArray[ tableFldConstants.inputtype.ordinal() ];
	}

	public void setKinkInputtype(String kinkInputtype) {
		saveArray[ tableFldConstants.inputtype.ordinal() ] = kinkInputtype;
	}

	public String getKinkInputentry() {
		return (String) saveArray[ tableFldConstants.inputentry.ordinal() ];
	}

	public void setKinkInputentry(String kinkInputentry) {
		saveArray[ tableFldConstants.inputentry.ordinal() ] = kinkInputentry;
	}

	public String getKinkIdentifier() {
		return (String) saveArray[ tableFldConstants.identifier.ordinal() ];
	}

	public void setKinkIdentifier(String kinkIdentifier) {
		saveArray[ tableFldConstants.identifier.ordinal() ] = kinkIdentifier;
	}

	public String getKinkManualcalctype() {
		return (String) saveArray[ tableFldConstants.manualcalctype.ordinal() ];
	}

	public void setKinkManualcalctype(String kinkManualcalctype) {
		saveArray[ tableFldConstants.manualcalctype.ordinal() ] = kinkManualcalctype;
	}

	public String getKinkUomid() {
		return (String) saveArray[ tableFldConstants.uomid.ordinal() ];
	}

	public void setKinkUomid(String kinkUomid) {
		saveArray[ tableFldConstants.uomid.ordinal() ] = kinkUomid;
	}

	public String getKinkFrequency() {
		return (String) saveArray[ tableFldConstants.frequency.ordinal() ];
	}

	public void setKinkFrequency(String kinkFrequency) {
		saveArray[ tableFldConstants.frequency.ordinal() ] = kinkFrequency;
	}

	public String getKinkExcelname() {
		return (String) saveArray[ tableFldConstants.excelname.ordinal() ];
	}

	public void setKinkExcelname(String kinkExcelname) {
		saveArray[ tableFldConstants.excelname.ordinal() ] = kinkExcelname;
	}

	public String getKinkDeptKeyid() {
		return (String) saveArray[ tableFldConstants.dept_keyid.ordinal() ];
	}

	public void setKinkDeptKeyid(String kinkDeptKeyid) {
		saveArray[ tableFldConstants.dept_keyid.ordinal() ] = kinkDeptKeyid;
	}

	public String getKinkCostarea() {
		return (String) saveArray[ tableFldConstants.costarea.ordinal() ];
	}

	public void setKinkCostarea(String kinkCostarea) {
		saveArray[ tableFldConstants.costarea.ordinal() ] = kinkCostarea;
	}

	public String getKinkTargetneed() {
		return (String) saveArray[ tableFldConstants.targetneed.ordinal() ];
	}

	public void setKinkTargetneed(String kinkTargetneed) {
		saveArray[ tableFldConstants.targetneed.ordinal() ] = kinkTargetneed;
	}

	public String getKinkPillarid() {
		return (String) saveArray[ tableFldConstants.pillarid.ordinal() ];
	}

	public void setKinkPillarid(String kinkPillarid) {
		saveArray[ tableFldConstants.pillarid.ordinal() ] = kinkPillarid;
	}

	public String getKinkType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setKinkType(String kinkType) {
		saveArray[ tableFldConstants.type.ordinal() ] = kinkType;
	}

	public String getKinkImpactarea() {
		return (String) saveArray[ tableFldConstants.impactarea.ordinal() ];
	}

	public void setKinkImpactarea(String kinkImpactarea) {
		saveArray[ tableFldConstants.impactarea.ordinal() ] = kinkImpactarea;
	}

	public String getKinkGoals() {
		return (String) saveArray[ tableFldConstants.goals.ordinal() ];
	}

	public void setKinkGoals(String kinkGoals) {
		saveArray[ tableFldConstants.goals.ordinal() ] = kinkGoals;
	}

	public String getKinkSourceofkpi() {
		return (String) saveArray[ tableFldConstants.sourceofkpi.ordinal() ];
	}

	public void setKinkSourceofkpi(String kinkSourceofkpi) {
		saveArray[ tableFldConstants.sourceofkpi.ordinal() ] = kinkSourceofkpi;
	}

	public String getKinkKpireason() {
		return (String) saveArray[ tableFldConstants.kpireason.ordinal() ];
	}

	public void setKinkKpireason(String kinkKpireason) {
		saveArray[ tableFldConstants.kpireason.ordinal() ] = kinkKpireason;
	}

	public String getKinkAnnualtarget() {
		return (String) saveArray[ tableFldConstants.annualtarget.ordinal() ];
	}

	public void setKinkAnnualtarget(String kinkAnnualtarget) {
		saveArray[ tableFldConstants.annualtarget.ordinal() ] = kinkAnnualtarget;
	}

	public String getKinkStartdate() {
		return (String) saveArray[ tableFldConstants.startdate.ordinal() ];
	}

	public void setKinkStartdate(String kinkStartdate) {
		saveArray[ tableFldConstants.startdate.ordinal() ] = kinkStartdate;
	}

	public String getKinkEnddate() {
		return (String) saveArray[ tableFldConstants.enddate.ordinal() ];
	}

	public void setKinkEnddate(String kinkEnddate) {
		saveArray[ tableFldConstants.enddate.ordinal() ] = kinkEnddate;
	}
	public String getKinkLocation() {
		return (String) saveArray[ tableFldConstants.location.ordinal() ];
	}

	public void setKinkLocation(String kinkLocation) {
		saveArray[ tableFldConstants.location.ordinal() ] = kinkLocation;
	}
	public String getKinkActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKinkActive(String kinkActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kinkActive;
	}

	public String getKinkCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKinkCreatedby(String kinkCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kinkCreatedby;
	}

	public String getKinkCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKinkCreatedon(String kinkCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kinkCreatedon;
	}

	public String getKinkModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKinkModifiedon(String kinkModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kinkModifiedon;
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
                if (field.name().equals("keyid") && val == null) {
                    sb.append("null");
                } else if (val == null) {
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
    
    
//	    public static KpiTlIndicator fromJson(String json) {
//	        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");
//
//	        JSONObject obj = JSONObject.fromObject(json);
//
//	        KpiTlIndicator audit = new KpiTlIndicator();
//	        CommonMessage.debugMsg("RAW JSON Response: " + json);
//
//	        for (tableFldConstants field : tableFldConstants.values()) {
//	            String key = field.name();
//	            
//	            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
//	            String val = obj.optString(field.name(), null);
//	            audit.setValue(field, val != null && val.equals("null") ? null : val);
//	        }
//	        
//	        return audit;
//	    }

    
    public static KpiTlIndicator fromJson(String json) {
  	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

  	  JSONObject obj = JSONObject.fromObject(json);

  	KpiTlIndicator pcm = new KpiTlIndicator();
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

