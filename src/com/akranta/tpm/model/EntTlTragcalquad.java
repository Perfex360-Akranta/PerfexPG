package com.akranta.tpm.model;
import java.util.ArrayList;
import java.util.List;

import com.akranta.tpm.model.JhaTlAuditmst.tableFldConstants;

import net.sf.json.JSONObject;
import com.akranta.tpm.utils.CommonMessage;

public class EntTlTragcalquad{

	private  Object [] saveArray = null;  
	
	private List<EntTlTragcalquad> entTlEmployeeRatting; 
	public enum   tableFldConstants
	{
		keyid, empm_keyid,empm_roleid,empm_topicid,flid, location,dmt, jh,currentlevel,
		currleveldate ,l1pass,l1date,l1trgcalid,l1remarks,l2pass,l2date,l2trgcalid,l2remarks,
		l3pass,l3date,l3updby,l3remarks,l4pass,l4date,l4updby,l4remarks,tempfield1, tempfield2,
		tempfield3, tempfield4,tempfield5, createdby,active,createdon, modifiedon
	}

	public EntTlTragcalquad()
	{
		saveArray = new  Object [ 35 ];
	}

	public Object[] getSaveArray() {
		return saveArray;
	}
	
	public void setSaveArray(Object[] saveArray) {
		this.saveArray = saveArray;
	}

	public String getEtcqKeyid() {
		return (String) saveArray[ tableFldConstants.keyid.ordinal() ];
	}

	public void setEtcqKeyid(String etcqKeyid) {
		saveArray[ tableFldConstants.keyid.ordinal() ] = etcqKeyid;
	}

	public String getEtcqEmpmKeyid() {
		return (String) saveArray[ tableFldConstants.empm_keyid.ordinal() ];
	}

	public void setEtcqEmpmKeyid(String etcqEmpmKeyid) {
		saveArray[ tableFldConstants.empm_keyid.ordinal() ] = etcqEmpmKeyid;
	}

	public String getEtcqEmpmRoleid() {
		return (String) saveArray[ tableFldConstants.empm_roleid.ordinal() ];
	}

	public void setEtcqEmpmRoleid(String etcqEmpmRoleid) {
		saveArray[ tableFldConstants.empm_roleid.ordinal() ] = etcqEmpmRoleid;
	}

	public String getEtcqTopicid() {
		return (String) saveArray[ tableFldConstants.empm_topicid.ordinal() ];
	}

	public void setEtcqTopicid(String etcqTopicid) {
		saveArray[ tableFldConstants.empm_topicid.ordinal() ] = etcqTopicid;
	}

	public String getEtcqFlid() {
		return (String) saveArray[ tableFldConstants.flid.ordinal() ];
	}

	public void setEtcqFlid(String etcqFlid) {
		saveArray[ tableFldConstants.flid.ordinal() ] = etcqFlid;
	}
	public String getEtcqLocation() {
		return (String) saveArray[ tableFldConstants.location.ordinal() ];
	}

	public void setEtcqLocation(String etcqLocation) {
		saveArray[ tableFldConstants.location.ordinal() ] = etcqLocation;
	}

	public String getEtcqDmt() {
		return (String) saveArray[ tableFldConstants.dmt.ordinal() ];
	}

	public void setEtcqDmt(String getEtcqDmt) {
		saveArray[ tableFldConstants.dmt.ordinal() ] = getEtcqDmt;
	}
	public String getEtcqJh() {
		return (String) saveArray[ tableFldConstants.jh.ordinal() ];
	}

	public void setEtcqJh(String getEtcqJh) {
		saveArray[ tableFldConstants.jh.ordinal() ] = getEtcqJh;
	}
	public String getEtcqCurrLevel() {
		return (String) saveArray[ tableFldConstants.currentlevel.ordinal() ];
	}
	public void setEtcqCurrLevel(String etcqCurrLevel) {
		saveArray[ tableFldConstants.currentlevel.ordinal() ] = etcqCurrLevel;
	}
	
	public String getEtcqCurrlevelDate() {
		return (String) saveArray[ tableFldConstants.currleveldate.ordinal() ];
	}

	public void setEtcqCurrlevelDate(String etcqCurrlevelDate) {
		saveArray[ tableFldConstants.currleveldate.ordinal() ] = etcqCurrlevelDate;
	}

		public String getEtcql1Pass() {
			return (String) saveArray[ tableFldConstants.l1pass.ordinal() ];
		}

		/*public void setEtcql1Pass(String etcql1Pass) {
			saveArray[ tableFldConstants.l1pass.ordinal() ] = etcql1Pass;
		}*/
		public String getEtcql1Date() {
			return (String) saveArray[ tableFldConstants.l1date.ordinal() ];
		}

		public void setEtcql1Date(String etcql1Date) {
			saveArray[ tableFldConstants.l1date.ordinal() ] = etcql1Date;
		}
		
		public String getEtcql1TrgCalId() {
			return (String) saveArray[ tableFldConstants.l1trgcalid.ordinal() ];
		}

		public void setEtcql1TrgCalId(String etcql1TrgCalId) {
			saveArray[ tableFldConstants.l1trgcalid.ordinal() ] = etcql1TrgCalId;
		}
		public String getEtcql1Remarks() {
			return (String) saveArray[ tableFldConstants.l1remarks.ordinal() ];
		}

		public void setEtcql1Remarks(String etcql1Remarks) {
			saveArray[ tableFldConstants.l1remarks.ordinal() ] = etcql1Remarks;
		}
		
		public void setEtcql1Pass(String etcql1Pass) {
			saveArray[ tableFldConstants.l1pass.ordinal() ] = etcql1Pass;
		}
		
		public void setEtcql2Pass(String etcql2Pass) {
			saveArray[ tableFldConstants.l2pass.ordinal() ] = etcql2Pass;
		}
		
		public void setEtcql3Pass(String etcql3Pass) {
			saveArray[ tableFldConstants.l3pass.ordinal() ] = etcql3Pass;
		}
		
		public void setEtcql4Pass(String etcql4Pass) {
			saveArray[ tableFldConstants.l4pass.ordinal() ] = etcql4Pass;
		}
		
		public String getEtcql2Pass() {
			return (String) saveArray[ tableFldConstants.l2pass.ordinal() ];
		}

		public void getEtcql2Pass(String etcql2Pass) {
			saveArray[ tableFldConstants.l2pass.ordinal() ] = etcql2Pass;
		}
		public String getEtcql2Date() {
			return (String) saveArray[ tableFldConstants.l2date.ordinal() ];
		}

		public void setEtcql2Date(String etcql2Date) {
			saveArray[ tableFldConstants.l2date.ordinal() ] = etcql2Date;
		}
		
		public String getEtcql2TrgCalId() {
			return (String) saveArray[ tableFldConstants.l2trgcalid.ordinal() ];
		}

		public void setEtcql2TrgCalId(String etcql2TrgCalId) {
			saveArray[ tableFldConstants.l2trgcalid.ordinal() ] = etcql2TrgCalId;
		}
		public String getEtcql2Remarks() {
			return (String) saveArray[ tableFldConstants.l2remarks.ordinal() ];
		}

		public void setEtcql2Remarks(String etcql2Remarks) {
			saveArray[ tableFldConstants.l2remarks.ordinal() ] = etcql2Remarks;
		}
		

		public String getEtcql3Pass() {
			return (String) saveArray[ tableFldConstants.l3pass.ordinal() ];
		}

		public void getEtcql3Pass(String etcql3Pass) {
			saveArray[ tableFldConstants.l3pass.ordinal() ] = etcql3Pass;
		}
		public String getEtcql3Date() {
			return (String) saveArray[ tableFldConstants.l3date.ordinal() ];
		}

		public void setEtcql3Date(String etcql3Date) {
			saveArray[ tableFldConstants.l3date.ordinal() ] = etcql3Date;
		}
		public String getEtcql3Udy() {
			return (String) saveArray[ tableFldConstants.l3updby.ordinal() ];
		}

		public void setEtcql3Udy(String etcql3Udy) {
			saveArray[ tableFldConstants.l3updby.ordinal() ] = etcql3Udy;
		}
		
		public String getEtcql3Remarks() {
			return (String) saveArray[ tableFldConstants.l3remarks.ordinal() ];
		}

		public void setEtcql3Remarks(String etcql3Remarks) {
			saveArray[ tableFldConstants.l3remarks.ordinal() ] = etcql3Remarks;
		}
		
		
		public String getEtcql4Pass() {
			return (String) saveArray[ tableFldConstants.l4pass.ordinal() ];
		}

		public void getEtcql4Pass(String etcql4Pass) {
			saveArray[ tableFldConstants.l3pass.ordinal() ] = etcql4Pass;
		}
		public String getEtcql4Date() {
			return (String) saveArray[ tableFldConstants.l4date.ordinal() ];
		}

		public void setEtcql4Date(String etcql4Date) {
			saveArray[ tableFldConstants.l4date.ordinal() ] = etcql4Date;
		}
		public String getEtcql4Udy() {
			return (String) saveArray[ tableFldConstants.l4updby.ordinal() ];
		}

		public void setEtcql4Udy(String etcql4Udy) {
			saveArray[ tableFldConstants.l4updby.ordinal() ] = etcql4Udy;
		}
		
		public String getEtcql4Remarks() {
			return (String) saveArray[ tableFldConstants.l4remarks.ordinal() ];
		}

		public void setEtcql4Remarks(String etcql4Remarks) {
			saveArray[ tableFldConstants.l4remarks.ordinal() ] = etcql4Remarks;
		}
		
	public String getEtcqTempfield1() {
		return (String) saveArray[ tableFldConstants.tempfield1.ordinal() ];
	}

	public void setEtcqTempfield1(String etcqTempfield1) {
		saveArray[ tableFldConstants.tempfield1.ordinal() ] = etcqTempfield1;
	}

	public String getEtcqTempfield2() {
		return (String) saveArray[ tableFldConstants.tempfield2.ordinal() ];
	}

	public void setEtcqTempfield2(String etcqTempfield2) {
		saveArray[ tableFldConstants.tempfield2.ordinal() ] = etcqTempfield2;
	}
	
	
	public String getEtcqTempfield3() {
		return (String) saveArray[ tableFldConstants.tempfield3.ordinal() ];
	}

	public void setEtcqTempfield3(String etcqTempfield3) {
		saveArray[ tableFldConstants.tempfield3.ordinal() ] = etcqTempfield3;
	}
	
	public String getEtcqTempfield4() {
		return (String) saveArray[ tableFldConstants.tempfield4.ordinal() ];
	}

	public void setEtcqTempfield4(String etcqTempfield4) {
		saveArray[ tableFldConstants.tempfield4.ordinal() ] = etcqTempfield4;
	}
	
	public String getEtcqTempfield5() {
		return (String) saveArray[ tableFldConstants.tempfield5.ordinal() ];
	}

	public void setEtcqTempfield5(String etcqTempfield5) {
		saveArray[ tableFldConstants.tempfield5.ordinal() ] = etcqTempfield5;
	}
	public String getEtcqCreatedby() {
		return (String) saveArray[ tableFldConstants.createdby.ordinal() ];
	}

	public void setEtcqCreatedby(String etcqCreatedby) {
		saveArray[ tableFldConstants.createdby.ordinal() ] = etcqCreatedby;
	}


	public String getEtcqActive() {
		return (String) saveArray[ tableFldConstants.active.ordinal() ];
	}

	public void setEtcfActive(String etcqActive) {
		saveArray[ tableFldConstants.active.ordinal() ] = etcqActive;
	}


	public String getEtcqCreatedon() {
		return (String) saveArray[ tableFldConstants.createdon.ordinal() ];
	}

	public void setEtcqCreatedon(String etcqCreatedon) {
		saveArray[ tableFldConstants.createdon.ordinal() ] = etcqCreatedon;
	}

	public String getEtcqModifiedon() {
		return (String) saveArray[ tableFldConstants.modifiedon.ordinal() ];
	}

	public void setEtcqModifiedon(String etcqModifiedon) {
		saveArray[ tableFldConstants.modifiedon.ordinal() ] = etcqModifiedon;
	}
	
	  


	  public List<EntTlTragcalquad> getEntTlEmployeeRatting() {
	          return entTlEmployeeRatting;
	      }

	      public void setEntTlEmployeeRatting(List<EntTlTragcalquad> entTlEmployeeRatting) {
	          this.entTlEmployeeRatting = entTlEmployeeRatting;
	      }
	      
	      
	      
	     	      

	  	public Object getValue(tableFldConstants field) {
	          return saveArray[field.ordinal()];
	      }

	      public void setValue(tableFldConstants field, Object value) {
	          saveArray[field.ordinal()] = value;
	      }
//
//	      /**
//	       * Convert object to JSON string manually
//	       */
//	      public String toJsonManual() {
//	          StringBuilder sb = new StringBuilder();
//	          sb.append("{");
//
//	          boolean first = true;
//	          for (tableFldConstants field : tableFldConstants.values()) {
//	              int index = field.ordinal();
//	              if (index < saveArray.length) {
//	                  if (!first) sb.append(",");
//	                  sb.append("\"").append(field.name()).append("\":");
//	                  Object val = saveArray[index];
//	                  if (field.name().equals("keyid") && val == null) {
//	                      sb.append("null");
//	                  } else if (val == null) {
//	                      sb.append("\"{}\"");
//	                  } else {
//	                      sb.append("\"").append(val.toString()).append("\"");
//	                  }
//	                  first = false;
//	              }
//	          }
//
//	          sb.append("}");
//	          return sb.toString();
//	      }
//
//	      /**
//	       * Parse JSON string to object
//	       */
//	      public static EntTlTragcalquad fromJson(String json) {
//	          CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");
//
//	          JSONObject obj = JSONObject.fromObject(json);
//
//	          EntTlTragcalquad quad = new EntTlTragcalquad();
//	          CommonMessage.debugMsg("RAW JSON Response: " + json);
//
//	          for (tableFldConstants field : tableFldConstants.values()) {
//	              String key = field.name();
//	              
//	              CommonMessage.debugMsg("JSON[" + field + "] :" + obj.get(key));
//	              String val = obj.optString(field.name(), null);
//	              quad.setValue(field, val != null && val.equals("null") ? null : val);
//	          }
//	          
//	          return quad;
//	      }
	      
	      
	 
	  	
	  	
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
	    
	  	
	    public static EntTlTragcalquad fromJson(String json) {
	  	  CommonMessage.debugMsg("RAW JSON Response: [" + json + "]");

	  	  JSONObject obj = JSONObject.fromObject(json);

	  	EntTlTragcalquad mst = new EntTlTragcalquad();
	  	  CommonMessage.debugMsg("RAW JSON Response: :"+json);

	  	    for (tableFldConstants field : tableFldConstants.values()) {
	  	    	String key = field.name();
	  	    	
	  	    	CommonMessage.debugMsg("JSON[" + field + "] :"+obj.get(key));
	  	        String val = obj.optString(field.name(), null);
	  	        mst.setValue(field, val != null && val.equals("null") ? null : val);
	  	    	
	  	    }
	  	    return mst;
	  	}
	    
	    


}

