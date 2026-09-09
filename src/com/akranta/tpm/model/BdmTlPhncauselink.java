package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class BdmTlPhncauselink {

	private  Object [] saveArray = null;  
	

	private List<BdmTlPhenomenamst> bdmTlPhenomenamst ;
	private List<BdmTlCausemst> bdmTlCausemst ;
	
	public enum   tableFldConstants
	{
		originalid, elementid, parentid, displaycode, elementtype, active
	}

	public BdmTlPhncauselink()
	{
		setBdmTlPhenomenamst(new ArrayList<BdmTlPhenomenamst> ());
		setBdmTlCausemst(new ArrayList<BdmTlCausemst> ());
		saveArray = new  Object [ 6 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public List<BdmTlPhenomenamst> getBdmTlPhenomenamst() {
		return bdmTlPhenomenamst;
	}

	public void setBdmTlPhenomenamst(List<BdmTlPhenomenamst> bdmTlPhenomenamst) {
		this.bdmTlPhenomenamst = bdmTlPhenomenamst;
	}

	public List<BdmTlCausemst> getBdmTlCausemst() {
		return bdmTlCausemst;
	}

	public void setBdmTlCausemst(List<BdmTlCausemst> bdmTlCausemst) {
		this.bdmTlCausemst = bdmTlCausemst;
	}

	public String getBpclOriginalid() {
		return (String) saveArray[ tableFldConstants.originalid.ordinal() ];
	}

	public void setBpclOriginalid(String bpcl_originalid) {
		saveArray[ tableFldConstants.originalid.ordinal() ] = bpcl_originalid;
	}

	public String getBpclElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setBpclElementid(String bpcl_elementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = bpcl_elementid;
	}

	public String getBpclParentid() {
		return (String) saveArray[ tableFldConstants.parentid.ordinal() ];
	}

	public void setBpclParentid(String bpcl_parentid) {
		saveArray[ tableFldConstants.parentid.ordinal() ] = bpcl_parentid;
	}

	public String getBpclDisplaycode() {
		return (String) saveArray[ tableFldConstants.displaycode.ordinal() ];
	}

	public void setBpclDisplaycode(String bpcl_displaycode) {
		saveArray[ tableFldConstants.displaycode.ordinal() ] = bpcl_displaycode;
	}

	public String getBpclElementtype() {
		return (String) saveArray[ tableFldConstants.elementtype.ordinal() ];
	}

	public void setBpclElementtype(String bpcl_elementtype) {
		saveArray[ tableFldConstants.elementtype.ordinal() ] = bpcl_elementtype;
	}

	public String getBpclActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setBpclActive(String bpcl_active) {
		saveArray[ tableFldConstants.active.ordinal() ] = bpcl_active;
	}

}

