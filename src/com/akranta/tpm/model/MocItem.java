package com.akranta.tpm.model;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class MocItem {

    private Object[] saveArray = null;

    public enum tableFldConstants {
        keyid, item, sorton, section, flid,
        tempfield1, tempfield2, tempfield3, tempfield4,
        createdby, active, createdon, modifiedon
    }

    public Object getValue(tableFldConstants field) {
        return saveArray[field.ordinal()];
    }

    public void setValue(tableFldConstants field, Object value) {
        saveArray[field.ordinal()] = value;
    }

    public MocItem() {
        saveArray = new Object[13];
    }

    public Object[] getSaveArray() {
        return saveArray;
    }

    public void setSaveArray(Object[] saveArray) {
        this.saveArray = saveArray;
    }

    // --- Getters & Setters ---

    public String getMocItmKeyid() {
        return (String) saveArray[tableFldConstants.keyid.ordinal()];
    }

    public void setMocItmKeyid(String keyid) {
        saveArray[tableFldConstants.keyid.ordinal()] = keyid;
    }

    public String getMocItmItem() {
        return (String) saveArray[tableFldConstants.item.ordinal()];
    }

    public void setMocItmItem(String item) {
        saveArray[tableFldConstants.item.ordinal()] = item;
    }

    public String getMocItmSorton() {
        return (String) saveArray[tableFldConstants.sorton.ordinal()];
    }

    public void setMocItmSorton(String sorton) {
        saveArray[tableFldConstants.sorton.ordinal()] = sorton;
    }

    public String getMocItmSection() {
        return (String) saveArray[tableFldConstants.section.ordinal()];
    }

    public void setMocItmSection(String section) {
        saveArray[tableFldConstants.section.ordinal()] = section;
    }

    public String getMocItmFlid() {
        return (String) saveArray[tableFldConstants.flid.ordinal()];
    }

    public void setMocItmFlid(String flid) {
        saveArray[tableFldConstants.flid.ordinal()] = flid;
    }

    public String getMocItmTempfield1() {
        return (String) saveArray[tableFldConstants.tempfield1.ordinal()];
    }

    public void setMocItmTempfield1(String tempfield1) {
        saveArray[tableFldConstants.tempfield1.ordinal()] = tempfield1;
    }

    public String getMocItmTempfield2() {
        return (String) saveArray[tableFldConstants.tempfield2.ordinal()];
    }

    public void setMocItmTempfield2(String tempfield2) {
        saveArray[tableFldConstants.tempfield2.ordinal()] = tempfield2;
    }

    public String getMocItmTempfield3() {
        return (String) saveArray[tableFldConstants.tempfield3.ordinal()];
    }

    public void setMocItmTempfield3(String tempfield3) {
        saveArray[tableFldConstants.tempfield3.ordinal()] = tempfield3;
    }

    public String getMocItmTempfield4() {
        return (String) saveArray[tableFldConstants.tempfield4.ordinal()];
    }

    public void setMocItmTempfield4(String tempfield4) {
        saveArray[tableFldConstants.tempfield4.ordinal()] = tempfield4;
    }

    public String getMocItmCreatedby() {
        return (String) saveArray[tableFldConstants.createdby.ordinal()];
    }

    public void setMocItmCreatedby(String createdby) {
        saveArray[tableFldConstants.createdby.ordinal()] = createdby;
    }

    public String getMocItmActive() {
        return (String) saveArray[tableFldConstants.active.ordinal()];
    }

    public void setMocItmActive(String active) {
        saveArray[tableFldConstants.active.ordinal()] = active;
    }

    public String getMocItmCreatedon() {
        return (String) saveArray[tableFldConstants.createdon.ordinal()];
    }

    public void setMocItmCreatedon(String createdon) {
        saveArray[tableFldConstants.createdon.ordinal()] = createdon;
    }

    public String getMocItmModifiedon() {
        return (String) saveArray[tableFldConstants.modifiedon.ordinal()];
    }

    public void setMocItmModifiedon(String modifiedon) {
        saveArray[tableFldConstants.modifiedon.ordinal()] = modifiedon;
    }

    // --- JSON serialization ---

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
                if (val == null) {
                    sb.append("\"\"");   // ✅ empty string instead of {}
                } else {
                    sb.append("\"").append(val.toString()).append("\"");
                }
                first = false;
            }
        }

        sb.append("}");
        return sb.toString();
    }

    public static MocItem fromJson(String json) {
        CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

        JSONObject obj = JSONObject.fromObject(json);
        MocItem mst = new MocItem();

        for (tableFldConstants field : tableFldConstants.values()) {
            String key = field.name();
            CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
            Object valueObj = obj.opt(key);
            String val = (valueObj == null || "null".equals(valueObj.toString())) 
                         ? "" : valueObj.toString();
            // ✅ treat {}, null, "null" all as empty string
            if ("null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
            mst.setValue(field, val);
        }
        return mst;
    }

    public static String toJsonManualList(List<MocItem> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) sb.append(",");
            sb.append(list.get(i).toJsonManual());
        }

        sb.append("]");
        return sb.toString();
    }

    public static List<MocItem> fromJsonList(String jsonResponse) {
        CommonMessage.debugMsg("RAW JSON ARRAY Response: " + jsonResponse);

        JSONArray jsonArray = JSONArray.fromObject(jsonResponse);
        List<MocItem> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            MocItem mst = new MocItem();

            for (tableFldConstants field : tableFldConstants.values()) {
                String key = field.name();
                Object valueObj = obj.opt(key);
                String val = (valueObj == null || "null".equals(valueObj.toString()))
                        ? null
                        : valueObj.toString();
                mst.setValue(field, val);
            }

            list.add(mst);
        }

        return list;
    }
}