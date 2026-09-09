package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GenTlMchsubmchlink {

	private  Object [] saveArray = null;  
	private List<GenTlMchsubmchlink> subEquipmentGrid;  

	public enum   tableFldConstants
	{
		parentmchid, sectionid, cellid, childmchid, createdon
	}

	public GenTlMchsubmchlink()
	{
		saveArray = new  Object [ 5 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public Object getValue(tableFldConstants field) {
        return saveArray[field.ordinal()];
    }

    public void setValue(tableFldConstants field, Object value) {
        saveArray[field.ordinal()] = value;
    }

	public String getScmlParentmchid() {
		return (String) saveArray[ tableFldConstants.parentmchid.ordinal() ];
	}

	public void setScmlParentmchid(String scmlParentmchid) {
		saveArray[ tableFldConstants.parentmchid.ordinal() ] = scmlParentmchid;
	}

	public String getScmlSectionid() {
		return (String) saveArray[ tableFldConstants.sectionid.ordinal() ];
	}

	public void setScmlSectionid(String scmlSectionid) {
		saveArray[ tableFldConstants.sectionid.ordinal() ] = scmlSectionid;
	}

	public String getScmlCellid() {
		return (String) saveArray[ tableFldConstants.cellid.ordinal() ];
	}

	public void setScmlCellid(String scmlCellid) {
		saveArray[ tableFldConstants.cellid.ordinal() ] = scmlCellid;
	}

	public String getScmlChildmchid() {
		return (String) saveArray[ tableFldConstants.childmchid.ordinal() ];
	}

	public void setScmlChildmchid(String scmlChildmchid) {
		saveArray[ tableFldConstants.childmchid.ordinal() ] = scmlChildmchid;
	}

	public String getScmlCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setScmlCreatedon(String scmlCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = scmlCreatedon;
	}

	public void setsubEquipmentGrid(List<GenTlMchsubmchlink> subEquipmentGrid) {
		this.setsubEquipmentGrid(subEquipmentGrid);
		
	}

	public void setSubEquipmentGrid(List<GenTlMchsubmchlink> subEquipmentGrid) {
		this.subEquipmentGrid = subEquipmentGrid;
	}

	public List<GenTlMchsubmchlink> getSubEquipmentGrid() {
		return subEquipmentGrid;
	}
	
	public String toJsonManual() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        boolean first = true;
        for (tableFldConstants field : tableFldConstants.values()) {
            if (!first) sb.append(",");
            sb.append("\"").append(field.name()).append("\":");
            
            Object val = saveArray[field.ordinal()];
            if (val == null) {
                sb.append("null");
            } else {
                sb.append("\"").append(val.toString()).append("\"");
            }
            first = false;
        }

        sb.append("}");
        return sb.toString();
    }

    public static GenTlMchsubmchlink fromJson(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        GenTlMchsubmchlink link = new GenTlMchsubmchlink();

        for (tableFldConstants field : tableFldConstants.values()) {
            Object valueObj = obj.opt(field.name());
            String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
            link.setValue(field, val);
        }

        return link;
    }

    public static List<GenTlMchsubmchlink> fromJsonList(String json) {
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<GenTlMchsubmchlink> list = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            GenTlMchsubmchlink link = new GenTlMchsubmchlink();

            for (tableFldConstants field : tableFldConstants.values()) {
                Object valueObj = obj.opt(field.name());
                String val = (valueObj == null || "null".equals(valueObj.toString())) ? null : valueObj.toString();
                link.setValue(field, val);
            }

            list.add(link);
        }

        return list;
    }

    public static String toJsonManualList(List<GenTlMchsubmchlink> list) {
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

