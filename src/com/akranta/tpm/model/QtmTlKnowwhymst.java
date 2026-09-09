package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.controller.UIUtils;
import com.akranta.tpm.model.KznTlBestmst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;
public class QtmTlKnowwhymst {

	private  Object [] saveArray = null;  
	
	private QtmTlKnowwhydtl qtmTlKnowwhydtl;
	List <GenTlAllmoduleimgfile> images =null;
	private String imagePath ;
	private String fileDir;
	private String isDtlTrue; 
	private String CheckPhenomena;
	
	public enum   tableFldConstants
	{
		keyid, flid, elementid, pillarid, type, developedby, approvedby
		, quality, description, image, phenomena, prepareddate, versionno
		, versiondate, tempfield5, createdby, active, createdon, modifiedon
	}

	public QtmTlKnowwhymst()
	{

		saveArray = new  Object [ 19 ];
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

	public String getKnwmKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setKnwmKeyid(String knwmKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = knwmKeyid;
	}

	public String getKnwmFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setKnwmFlid(String knwmFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = knwmFlid;
	}

	public String getKnwmElementid() {
		return (String) saveArray[ tableFldConstants.elementid.ordinal() ];
	}

	public void setKnwmElementid(String knwmElementid) {
		saveArray[ tableFldConstants.elementid.ordinal() ] = knwmElementid;
	}

	public String getKnwmPillarid() {
		return (String) saveArray[ tableFldConstants.pillarid.ordinal() ];
	}

	public void setKnwmPillarid(String knwmPillarid) {
		saveArray[ tableFldConstants.pillarid.ordinal() ] = knwmPillarid;
	}

	public String getKnwmType() {
		return (String) saveArray[ tableFldConstants.type.ordinal() ];
	}

	public void setKnwmType(String knwmType) {
		saveArray[ tableFldConstants.type.ordinal() ] = knwmType;
	}

	public String getKnwmDevelopedby() {
		return (String) saveArray[ tableFldConstants.developedby.ordinal() ];
	}

	public void setKnwmDevelopedby(String knwmDevelopedby) {
		saveArray[ tableFldConstants.developedby.ordinal() ] = knwmDevelopedby;
	}

	public String getKnwmApprovedby() {
		return (String) saveArray[ tableFldConstants.approvedby.ordinal() ];
	}

	public void setKnwmApprovedby(String knwmApprovedby) {
		saveArray[ tableFldConstants.approvedby.ordinal() ] = knwmApprovedby;
	}

	public String getKnwmQuality() {
		return (String) saveArray[ tableFldConstants.quality.ordinal() ];
	}

	public void setKnwmQuality(String knwmQuality) {
		saveArray[ tableFldConstants.quality.ordinal() ] = knwmQuality;
	}

	public String getKnwmDescription() {
		return (String) saveArray[ tableFldConstants.description.ordinal() ];
	}

	public void setKnwmDescription(String knwmDescription) {
		saveArray[ tableFldConstants.description.ordinal() ] = knwmDescription;
	}

	public String getKnwmImage() {
		return (String) saveArray[ tableFldConstants.image.ordinal() ];
	}

	public void setKnwmImage(String knwmImage) {
		saveArray[ tableFldConstants.image.ordinal() ] = knwmImage;
	}

	public String getKnwmPhenomena() {
		return (String) saveArray[ tableFldConstants.phenomena.ordinal() ];
	}

	public void setKnwmPhenomena(String knwmPhenomena) {
		saveArray[ tableFldConstants.phenomena.ordinal() ] = knwmPhenomena;
	}

	public String getKnwmPrepareddate() {
		return (String) saveArray[ tableFldConstants.prepareddate.ordinal() ];
	}

	public void setKnwmPrepareddate(String knwmPrepareddate) {
		saveArray[ tableFldConstants.prepareddate.ordinal() ] = knwmPrepareddate;
	}

	public String getKnwmVersionno() {
		return (String) saveArray[ tableFldConstants.versionno.ordinal() ];
	}

	public void setKnwmVersionno(String knwmVersionno) {
		saveArray[ tableFldConstants.versionno.ordinal() ] = knwmVersionno;
	}

	public String getKnwmVersiondate() {
		return (String) saveArray[ tableFldConstants.versiondate.ordinal() ];
	}

	public void setKnwmVersiondate(String knwmVersiondate) {
		saveArray[ tableFldConstants.versiondate.ordinal() ] = knwmVersiondate;
	}

	public String getKnwmTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setKnwmTempfield5(String knwmTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = knwmTempfield5;
	}

	public String getKnwmCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setKnwmCreatedby(String knwmCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = knwmCreatedby;
	}

	public String getKnwmActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setKnwmActive(String knwmActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = knwmActive;
	}

	public String getKnwmCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setKnwmCreatedon(String knwmCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = knwmCreatedon;
	}

	public String getKnwmModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setKnwmModifiedon(String knwmModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = knwmModifiedon;
	}

	public void setQtmTlKnowwhydtl(QtmTlKnowwhydtl qtmTlKnowwhydtl) {
		this.qtmTlKnowwhydtl = qtmTlKnowwhydtl;
	}

	public QtmTlKnowwhydtl getqtmTlKnowwhydtl() {
		return qtmTlKnowwhydtl;
	}
	public List<GenTlAllmoduleimgfile> getImages() {
		return images;
	}

	public void setImages(List<GenTlAllmoduleimgfile> images) {
		this.images = images;
	}

	public void setSaveArray(Object[] saveArray) {
		// TODO Auto-generated method stub
		this.saveArray = saveArray;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	public String getFileDir() {
		return fileDir;
	}

	public void setIsDtlTrue(String isDtlTrue) {
		this.isDtlTrue = isDtlTrue;
	}

	public String getIsDtlTrue() {
		return isDtlTrue;
	}

	public void setCheckPhenomena(String checkPhenomena) {
		CheckPhenomena = checkPhenomena;
	}

	public String getCheckPhenomena() {
		return CheckPhenomena;
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
	            if(field.name() == "keyid" && val == null) {
	            	sb.append("null");
	            }else if (val == null) {
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
  
  public static QtmTlKnowwhymst fromJson(String json) {
	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  JSONObject obj = JSONObject.fromObject(json);

	  QtmTlKnowwhymst mst = new QtmTlKnowwhymst();
	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	    for (tableFldConstants field : tableFldConstants.values()) {
	    	String key = field.name();
	    	
	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	        String val = obj.optString(field.name(), null);
	        if (val == null || "null".equalsIgnoreCase(val) || "{}".equals(val)) {
                val = "";
            }
	        mst.setValue(field, val);
	    	
	    }
	    return mst;
	}

	

}

