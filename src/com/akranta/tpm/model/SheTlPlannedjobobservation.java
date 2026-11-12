package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

public class SheTlPlannedjobobservation {

	private  Object [] saveArray = null;  
	private List<SheTlPlnjobemplink> sheTlPlnjobemplink;
	private String positioning="N";
	private String checklist="N";
	private String appPPE="N";
	private String checkWorker="N";
	public enum   tableFldConstants
	{
		keyid, flid, elementid, employeeid, date, location_description
		, task_observed, type, notification, reasonid, is_property_damage
		, is_efficient, is_comply, is_detrimental, corrections, deviations
		, is_complemented, is_follow_up, standard_procedure, manager
		, follow_up_action, follow_up_date, follow_up_done, practicecond
		,followuppjono,folowuptaskobserved, sopno, tempfield5, active, createdby
		, createdon, modifiedon,actionplan_id
	}

	public SheTlPlannedjobobservation()
	{
		saveArray = new  Object [ 33 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	public void setSaveArray(Object [] saveArray) {
		
		 this.saveArray = saveArray;
	}
	public String getPjobKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPjobKeyid(String pjobKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = pjobKeyid;
	}

	public String getPjobFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setPjobFlid(String pjobFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = pjobFlid;
	}

	public String getPjobElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setPjobElementid(String pjobElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = pjobElementid;
	}

	public String getPjobEmployeeid() {
		return (String) saveArray[ tableFldConstants.employeeid.ordinal() ];
	}

	public void setPjobEmployeeid(String pjobEmployeeid) {
		saveArray[ tableFldConstants.employeeid.ordinal() ] = pjobEmployeeid;
	}

	public String getPjobDate() {
		return (String) saveArray[ tableFldConstants.date.ordinal() ];
	}

	public void setPjobDate(String pjobDate) {
		saveArray[ tableFldConstants.date.ordinal() ] = pjobDate;
	}

	public String getPjobLocationDescription() {
		return (String) saveArray[ tableFldConstants.location_description.ordinal() ];
	}

	public void setPjobLocationDescription(String pjobLocationDescription) {
		saveArray[ tableFldConstants.location_description.ordinal() ] = pjobLocationDescription;
	}

	public String getPjobTaskObserved() {
		return (String) saveArray[ tableFldConstants.task_observed.ordinal() ];
	}

	public void setPjobTaskObserved(String pjobTaskObserved) {
		saveArray[ tableFldConstants.task_observed.ordinal() ] = pjobTaskObserved;
	}

	public String getPjobType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setPjobType(String pjobType) {
		saveArray[ tableFldConstants.type.ordinal() ] = pjobType;
	}

	public String getPjobNotification() {
		return (String) saveArray[ tableFldConstants.notification.ordinal() ];
	}

	public void setPjobNotification(String pjobNotification) {
		saveArray[ tableFldConstants.notification.ordinal() ] = pjobNotification;
	}

	public String getPjobReasonid() {
		return (String) saveArray[ tableFldConstants.reasonid.ordinal() ];
	}

	public void setPjobReasonid(String pjobReasonid) {
		saveArray[ tableFldConstants.reasonid.ordinal() ] = pjobReasonid;
	}

	public String getPjobIsPropertyDamage() {
		return (String) saveArray[ tableFldConstants.is_property_damage.ordinal() ];
	}

	public void setPjobIsPropertyDamage(String pjobIsPropertyDamage) {
		saveArray[ tableFldConstants.is_property_damage.ordinal() ] = pjobIsPropertyDamage;
	}

	public String getPjobIsEfficient() {
		return (String) saveArray[ tableFldConstants.is_efficient.ordinal() ];
	}

	public void setPjobIsEfficient(String pjobIsEfficient) {
		saveArray[ tableFldConstants.is_efficient.ordinal() ] = pjobIsEfficient;
	}

	public String getPjobIsComply() {
		return (String) saveArray[ tableFldConstants.is_comply.ordinal() ];
	}

	public void setPjobIsComply(String pjobIsComply) {
		saveArray[ tableFldConstants.is_comply.ordinal() ] = pjobIsComply;
	}

	public String getPjobIsDetrimental() {
		return (String) saveArray[ tableFldConstants.is_detrimental.ordinal() ];
	}

	public void setPjobIsDetrimental(String pjobIsDetrimental) {
		saveArray[ tableFldConstants.is_detrimental.ordinal() ] = pjobIsDetrimental;
	}

	public String getPjobCorrections() {
		return (String) saveArray[ tableFldConstants.corrections.ordinal() ];
	}

	public void setPjobCorrections(String pjobCorrections) {
		saveArray[ tableFldConstants.corrections.ordinal() ] = pjobCorrections;
	}

	public String getPjobDeviations() {
		return (String) saveArray[ tableFldConstants.deviations.ordinal() ];
	}

	public void setPjobDeviations(String pjobDeviations) {
		saveArray[ tableFldConstants.deviations.ordinal() ] = pjobDeviations;
	}

	public String getPjobIsComplemented() {
		return (String) saveArray[ tableFldConstants.is_complemented.ordinal() ];
	}

	public void setPjobIsComplemented(String pjobIsComplemented) {
		saveArray[ tableFldConstants.is_complemented.ordinal() ] = pjobIsComplemented;
	}

	public String getPjobIsFollowUp() {
		return (String) saveArray[ tableFldConstants.is_follow_up.ordinal() ];
	}

	public void setPjobIsFollowUp(String pjobIsFollowUp) {
		saveArray[ tableFldConstants.is_follow_up.ordinal() ] = pjobIsFollowUp;
	}

	public String getPjobStandardProcedure() {
		return (String) saveArray[ tableFldConstants.standard_procedure.ordinal() ];
	}

	public void setPjobStandardProcedure(String pjobStandardProcedure) {
		saveArray[ tableFldConstants.standard_procedure.ordinal() ] = pjobStandardProcedure;
	}

	public String getPjobManager() {
		return (String) saveArray[ tableFldConstants.manager.ordinal() ];
	}

	public void setPjobManager(String pjobManager) {
		saveArray[ tableFldConstants.manager.ordinal() ] = pjobManager;
	}

	public String getPjobFollowUpAction() {
		return (String) saveArray[ tableFldConstants.follow_up_action.ordinal() ];
	}

	public void setPjobFollowUpAction(String pjobFollowUpAction) {
		saveArray[ tableFldConstants.follow_up_action.ordinal() ] = pjobFollowUpAction;
	}

	public String getPjobFollowUpDate() {
		return (String) saveArray[ tableFldConstants.follow_up_date.ordinal() ];
	}

	public void setPjobFollowUpDate(String pjobFollowUpDate) {
		saveArray[ tableFldConstants.follow_up_date.ordinal() ] = pjobFollowUpDate;
	}

	public String getPjobFollowUpDone() {
		return (String) saveArray[ tableFldConstants.follow_up_done.ordinal() ];
	}

	public void setPjobFollowUpDone(String pjobFollowUpDone) {
		saveArray[ tableFldConstants.follow_up_done.ordinal() ] = pjobFollowUpDone;
	}

	public String getPjobPracticecond() {
		return (String) saveArray[ tableFldConstants.practicecond.ordinal() ];
	}

	public void setPjobPracticecond(String pjobPracticecond) {
		saveArray[ tableFldConstants.practicecond.ordinal() ] = pjobPracticecond;
	}

	public String getPjobFollowuppjono() {
		return (String) saveArray[ tableFldConstants.followuppjono.ordinal() ];
	}

	public void setPjobFollowuppjono(String pjobFollowuppjono) {
		saveArray[ tableFldConstants.followuppjono.ordinal() ]=pjobFollowuppjono;
	}

	public String getPjobFollowuptaskobserved() {
		return (String) saveArray[ tableFldConstants.folowuptaskobserved.ordinal() ];
	}

	public void setPjobFollowuptaskobserved(String pjobFollowuptaskobserved) {
		saveArray[ tableFldConstants.folowuptaskobserved.ordinal() ]=pjobFollowuptaskobserved;
	}

	public String getPjobSopNo() {
		return (String) saveArray[ tableFldConstants.sopno.ordinal() ];
	}

	public void setPjobSopNo(String pjobSopNo) {
		saveArray[ tableFldConstants.sopno.ordinal() ] = pjobSopNo;
	}

	public String getPjobTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setPjobTempfield5(String pjobTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = pjobTempfield5;
	}

	public String getPjobActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setPjobActive(String pjobActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = pjobActive;
	}

	public String getPjobCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setPjobCreatedby(String pjobCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = pjobCreatedby;
	}

	public String getPjobCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setPjobCreatedon(String pjobCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = pjobCreatedon;
	}

	public String getPjobModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setPjobModifiedon(String pjobModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = pjobModifiedon;
	}
	public String getPjobActionplanId() {
		return (String) saveArray[ tableFldConstants.actionplan_id.ordinal() ];
	}
	public void setPjobActionplanId(String pjobActionplanid) {
		saveArray[ tableFldConstants.actionplan_id.ordinal() ] = pjobActionplanid;
	}
	public String getPjobPositioning() {
		return positioning;
	}
	public void setPjobPositioning(String positioning) {
		this.positioning=positioning ;
	}
	
	public String getPjobChecklist() {
		return checklist;
	}
	public void setPjobChecklist(String checklist) {
		this.checklist=checklist ;
	}
	
	public String getPjobAppPPE() {
		return appPPE;
	}
	public void setPjobAppPPE(String appPPE) {
		this.appPPE=appPPE ;
	}
	
	public String getPjobCheckWorker() {
		return checkWorker;
	}
	public void setPjobCheckWorker(String checkWorker) {
		this.checkWorker=checkWorker ;
	}
	
	public void setSheTlPlnjobemplink(List<SheTlPlnjobemplink> sheTlPlnjobemplink) {
		this.sheTlPlnjobemplink = sheTlPlnjobemplink;
	}

	public List<SheTlPlnjobemplink> getSheTlPlnjobemplink() {
		return sheTlPlnjobemplink;
	}
	
	/**
     * Generic setValue method to set values by field enum
     */
    public void setValue(tableFldConstants field, String value) {
        if (field != null) {
            saveArray[field.ordinal()] = value;
        }
    }

    /**
     * Convert master object to JSON string
     */
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

    /**
     * Parse JSON string to SheTlPlannedjobobservation object
     */
    public static SheTlPlannedjobobservation fromJson(String json) {
        System.out.println("RAW JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        SheTlPlannedjobobservation mst = new SheTlPlannedjobobservation();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            System.out.println("JSON[" + field + "] :" + obj.get(key));
            String val = obj.optString(field.name(), null);
            if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
            	{
	                val = "";
	            }
            }
            mst.setValue(field, val != null && val.equals("null") ? null : val);
        }
        return mst;
    }

	
}