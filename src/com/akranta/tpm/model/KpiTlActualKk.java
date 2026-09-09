package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class KpiTlActualKk {

	private  Object [] saveArray = null;  
	

	public enum   tableFldConstants
	{
		keyid, indicatorid, deptid, depttype, pillarid, calendaryear
		, monthyear, excellencevalue, benchmarkvalue, value, isactual
		, freqtype, status, tempfield1, tempfield2, tempfield3, active
		, createdby, createdon, modifiedon
	}/** factoryid,sectionid, cellid is removed and deptid, depttype is added**/

	public KpiTlActualKk()
	{
		saveArray = new  Object [ 20 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}
	
	public String getKaukKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKaukKeyid(String kaukKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = kaukKeyid;
	}

	public String getKaukIndicatorid() {
		return (String) saveArray[ tableFldConstants.indicatorid.ordinal() ];
	}

	public void setKaukIndicatorid(String kaukIndicatorid) {
		saveArray[ tableFldConstants.indicatorid.ordinal() ] = kaukIndicatorid;
	}

	public String getKaukDeptid() {
		return (String) saveArray[ tableFldConstants.deptid.ordinal() ];
	}

	public void setKaukDeptid(String kaukDeptid) {
		saveArray[ tableFldConstants.deptid.ordinal() ] = kaukDeptid;
	}

	public String getKaukDepttype() {
		return (String) saveArray[ tableFldConstants.depttype.ordinal() ];
	}

	public void setKaukDepttype(String kaukDepttype) {
		saveArray[ tableFldConstants.depttype.ordinal() ] = kaukDepttype;
	}

	public String getKaukPillarid() {
		return (String) saveArray[ tableFldConstants.pillarid.ordinal() ];
	}

	public void setKaukPillarid(String kaukPillarid) {
		saveArray[ tableFldConstants.pillarid.ordinal() ] = kaukPillarid;
	}

	public String getKaukCalendaryear() {
		return (String) saveArray[ tableFldConstants.calendaryear.ordinal() ];
	}

	public void setKaukCalendaryear(String kaukCalendaryear) {
		saveArray[ tableFldConstants.calendaryear.ordinal() ] = kaukCalendaryear;
	}

	public String getKaukMonthyear() {
		return (String) saveArray[ tableFldConstants.monthyear.ordinal() ];
	}

	public void setKaukMonthyear(String kaukMonthyear) {
		saveArray[ tableFldConstants.monthyear.ordinal() ] = kaukMonthyear;
	}

	public String getKaukExcellencevalue() {
		return (String) saveArray[ tableFldConstants.excellencevalue.ordinal() ];
	}

	public void setKaukExcellencevalue(String kaukExcellencevalue) {
		saveArray[ tableFldConstants.excellencevalue.ordinal() ] = kaukExcellencevalue;
	}

	public String getKaukBenchmarkvalue() {
		return (String) saveArray[ tableFldConstants.benchmarkvalue.ordinal() ];
	}

	public void setKaukBenchmarkvalue(String kaukBenchmarkvalue) {
		saveArray[ tableFldConstants.benchmarkvalue.ordinal() ] = kaukBenchmarkvalue;
	}

	public String getKaukValue() {
		return (String) saveArray[ tableFldConstants.value.ordinal() ];
	}

	public void setKaukValue(String kaukValue) {
		saveArray[ tableFldConstants.value.ordinal() ] = kaukValue;
	}

	public String getKaukIsactual() {
		return (String) saveArray[ tableFldConstants.isactual.ordinal() ];
	}

	public void setKaukIsactual(String kaukIsactual) {
		saveArray[ tableFldConstants.isactual.ordinal() ] = kaukIsactual;
	}

	public String getKaukFreqtype() {
		return (String) saveArray[ tableFldConstants.freqtype.ordinal() ];
	}

	public void setKaukFreqtype(String kaukFreqtype) {
		saveArray[ tableFldConstants.freqtype.ordinal() ] = kaukFreqtype;
	}

	public String getKaukStatus() {
		return (String) saveArray[ tableFldConstants.status.ordinal() ];
	}

	public void setKaukStatus(String kaukStatus) {
		saveArray[ tableFldConstants.status.ordinal() ] = kaukStatus;
	}

	public String getKaukTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setKaukTempfield1(String kaukTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = kaukTempfield1;
	}

	public String getKaukTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setKaukTempfield2(String kaukTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = kaukTempfield2;
	}

	public String getKaukTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setKaukTempfield3(String kaukTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = kaukTempfield3;
	}

	public String getKaukActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKaukActive(String kaukActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = kaukActive;
	}

	public String getKaukCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKaukCreatedby(String kaukCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = kaukCreatedby;
	}

	public String getKaukCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKaukCreatedon(String kaukCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = kaukCreatedon;
	}

	public String getKaukModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKaukModifiedon(String kaukModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = kaukModifiedon;
	}
	
	public Object getValue(tableFldConstants field) {
        return saveArray[field.ordinal()];
    }

    public void setValue(tableFldConstants field, Object value) {
        saveArray[field.ordinal()] = value;
    }
    
    // to json
    
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
                    sb.append("\"\"");
                } else {
                    sb.append("\"").append(val.toString().replace("\"", "\\\"")).append("\"");
                }
                first = false;
            }
        }

        sb.append("}");
        return sb.toString();
    }
    
    // from json
    
    public static KpiTlActualKk fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response (Master): [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        KpiTlActualKk mst = new KpiTlActualKk();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.opt(key));
            String val = obj.optString(field.name(), null);
            mst.setValue(field, val != null && val.equals("null") ? null : val);
        }

        return mst;
    }



			//from json list
			
			public static List<KpiTlActualKk> fromJsonList(String json) {
			
			CommonMessage.debugMsg("RAW JSON ARRAY Response: " + json);
			
			JSONArray jsonArray = JSONArray.fromObject(json);
			List<KpiTlActualKk> list = new ArrayList<>();
			
			for (int i = 0; i < jsonArray.length(); i++) {
			
			    JSONObject obj = jsonArray.getJSONObject(i);
			    KpiTlActualKk abn = new KpiTlActualKk();
			
			    for (tableFldConstants field : tableFldConstants.values()) {
			        String key = field.name();
			
			        Object valueObj = obj.opt(key);
			        String val = (valueObj == null || "null".equals(valueObj.toString()))
			                     ? null
			                     : valueObj.toString();
			
			        abn.setValue(field, val);
			    }
			
			    list.add(abn);
			}
			
			return list;
			}
			
			//to json list
			
			public static String toJsonManualList(List<KpiTlActualKk> list) {
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			
			for (int i = 0; i < list.size(); i++) {
			    if (i > 0) sb.append(",");
			    sb.append(list.get(i).toJsonManual());
			}
			
			sb.append("]");
			return sb.toString();
			}

}

