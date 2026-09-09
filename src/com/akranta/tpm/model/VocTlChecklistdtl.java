package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class VocTlChecklistdtl {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, vchm_keyid, criteria_order, criteria, check_order, check_point
		, target_score, effective_from, effective_till, tempfield1, tempfield2
		, tempfield3, active, createdby, createdon, modifiedon
	}

	public VocTlChecklistdtl()
	{
		saveArray = new  Object [ 16 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getVchdKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setVchdKeyid(String vchdKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = vchdKeyid;
	}

	public String getVchdVchmKeyid() {
		return (String) saveArray[ tableFldConstants.vchm_keyid.ordinal() ];
	}

	public void setVchdVchmKeyid(String vchdVchmKeyid) {
		saveArray[ tableFldConstants.vchm_keyid.ordinal() ] = vchdVchmKeyid;
	}

	public String getVchdCriteriaOrder() {
		return (String) saveArray[ tableFldConstants.criteria_order.ordinal() ];
	}

	public void setVchdCriteriaOrder(String vchdCriteriaOrder) {
		saveArray[ tableFldConstants.criteria_order.ordinal() ] = vchdCriteriaOrder;
	}

	public String getVchdCriteria() {
		return (String) saveArray[ tableFldConstants.criteria.ordinal() ];
	}

	public void setVchdCriteria(String vchdCriteria) {
		saveArray[ tableFldConstants.criteria.ordinal() ] = vchdCriteria;
	}

	public String getVchdCheckOrder() {
		return (String) saveArray[ tableFldConstants.check_order.ordinal() ];
	}

	public void setVchdCheckOrder(String vchdCheckOrder) {
		saveArray[ tableFldConstants.check_order.ordinal() ] = vchdCheckOrder;
	}

	public String getVchdCheckPoint() {
		return (String) saveArray[ tableFldConstants.check_point.ordinal() ];
	}

	public void setVchdCheckPoint(String vchdCheckPoint) {
		saveArray[ tableFldConstants.check_point.ordinal() ] = vchdCheckPoint;
	}

	public String getVchdTargetScore() {
		return (String) saveArray[ tableFldConstants.target_score.ordinal() ];
	}

	public void setVchdTargetScore(String vchdTargetScore) {
		saveArray[ tableFldConstants.target_score.ordinal() ] = vchdTargetScore;
	}

	public String getVchdEffectiveFrom() {
		return (String) saveArray[ tableFldConstants.effective_from.ordinal() ];
	}

	public void setVchdEffectiveFrom(String vchdEffectiveFrom) {
		saveArray[ tableFldConstants.effective_from.ordinal() ] = vchdEffectiveFrom;
	}

	public String getVchdEffectiveTill() {
		return (String) saveArray[ tableFldConstants.effective_till.ordinal() ];
	}

	public void setVchdEffectiveTill(String vchdEffectiveTill) {
		saveArray[ tableFldConstants.effective_till.ordinal() ] = vchdEffectiveTill;
	}

	public String getVchdTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setVchdTempfield1(String vchdTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = vchdTempfield1;
	}

	public String getVchdTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setVchdTempfield2(String vchdTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = vchdTempfield2;
	}

	public String getVchdTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setVchdTempfield3(String vchdTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = vchdTempfield3;
	}

	public String getVchdActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setVchdActive(String vchdActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = vchdActive;
	}

	public String getVchdCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setVchdCreatedby(String vchdCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = vchdCreatedby;
	}

	public String getVchdCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setVchdCreatedon(String vchdCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = vchdCreatedon;
	}

	public String getVchdModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setVchdModifiedon(String vchdModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = vchdModifiedon;
	}

}

