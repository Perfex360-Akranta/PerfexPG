package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class EntTlFeedbackRatingmst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, code, description, order, tempfield1, tempfield2, tempfield3
		, active, createdby, createdon, modifiedon
	}

	public EntTlFeedbackRatingmst()
	{
		saveArray = new  Object [ 11 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getFermKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setFermKeyid(String fermKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = fermKeyid;
	}

	public String getFermCode() {
		return (String) saveArray[ tableFldConstants.code.ordinal() ];
	}

	public void setFermCode(String fermCode) {
		saveArray[ tableFldConstants.code.ordinal() ] = fermCode;
	}

	public String getFermDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setFermDescription(String fermDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = fermDescription;
	}

	public String getFermOrder() {
		return (String) saveArray[ tableFldConstants.order.ordinal() ];
	}

	public void setFermOrder(String fermOrder) {
		saveArray[ tableFldConstants.order.ordinal() ] = fermOrder;
	}

	public String getFermTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setFermTempfield1(String fermTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = fermTempfield1;
	}

	public String getFermTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setFermTempfield2(String fermTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = fermTempfield2;
	}

	public String getFermTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setFermTempfield3(String fermTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = fermTempfield3;
	}

	public String getFermActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setFermActive(String fermActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = fermActive;
	}

	public String getFermCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setFermCreatedby(String fermCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = fermCreatedby;
	}

	public String getFermCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setFermCreatedon(String fermCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = fermCreatedon;
	}

	public String getFermModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setFermModifiedon(String fermModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = fermModifiedon;
	}

}

