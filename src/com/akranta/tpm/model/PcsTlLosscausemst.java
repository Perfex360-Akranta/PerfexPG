package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

public class PcsTlLosscausemst {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, phenomenaid, description, tempfield2, tempfield3
	}

	public PcsTlLosscausemst()
	{
		saveArray = new  Object [ 5 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}

	public String getPlcsKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setPlcsKeyid(String plcsKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = plcsKeyid;
	}

	public String getPlcsPhenomenaid() {
		return (String) saveArray[ tableFldConstants.phenomenaid.ordinal() ];
	}

	public void setPlcsPhenomenaid(String plcsPhenomenaid) {
		saveArray[ tableFldConstants.phenomenaid.ordinal() ] = plcsPhenomenaid;
	}

	public String getPlcsDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setPlcsDescription(String plcsDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = plcsDescription;
	}

	public String getPlcsTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setPlcsTempfield2(String plcsTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = plcsTempfield2;
	}

	public String getPlcsTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setPlcsTempfield3(String plcsTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = plcsTempfield3;
	}

}

