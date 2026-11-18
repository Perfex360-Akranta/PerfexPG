package com.akranta.tpm.bean;

import java.util.List;

import com.akranta.tpm.bean.NoPlanSaveBean.tableFldConstants;


public class BAL_PcsEntryBean {
	
		private String formActionMode;
		private String formMode;
		private String formHeader;
		private String formType;
		private String lossId;
		private String lossValue;		
		private String plmasterid;
		private String pldetailsid;
		private String ptwokeyid;
		private String plrkKeyid;
		private String plosKeyid;
		private String isOpenMsr;	
		private String oldTime;	
		private String availtime;
		private String prodtimehourly;
		private String availtimehourly;
		private String losstimehourly;
		private String unreptimehourly;
		private String sftDuration;
		private String shiftstarttime;
		private String shiftendtime;
		private String addrowtoGrid;
		private String losshrno;
		private String shiftWise;
		
		
		public String getAddrowtoGrid() {
			return addrowtoGrid;
		}


		public void setAddrowtoGrid(String addrowtoGrid) {
			this.addrowtoGrid = addrowtoGrid;
		}


		public String getShiftstarttime() {
			return shiftstarttime;
		}


		public void setShiftstarttime(String shiftstarttime) {
			this.shiftstarttime = shiftstarttime;
		}


		public String getShiftendtime() {
			return shiftendtime;
		}


		public void setShiftendtime(String shiftendtime) {
			this.shiftendtime = shiftendtime;
		}


		public String getSftDuration() {
			return sftDuration;
		}


		public void setSftDuration(String sftDuration) {
			this.sftDuration = sftDuration;
		}


		public String getFormType() {
			return formType;
		}


		public void setFormType(String formType) {
			this.formType = formType;
		}


		


	

		


		public BAL_PcsEntryBean()
		{
			this.formMode = "CREATE";
		}


		public String getFormActionMode() {
			return formActionMode;
		}


		public void setFormActionMode(String formActionMode) {
			this.formActionMode = formActionMode;
		}


		public String getFormMode() {
			return formMode;
		}


		public void setFormMode(String formMode) {
			this.formMode = formMode;
		}


		public String getFormHeader() {
			return formHeader;
		}


		public void setFormHeader(String formHeader) {
			this.formHeader = formHeader;
		}

		
		public String getLossId() {
			return lossId;
		}

		public void setLossId(String lossId) {
			this.lossId = lossId;
		}

		public String getLossValue() {
			return lossValue;
		}

		public void setLossValue(String lossValue) {
			this.lossValue = lossValue;
		}
		
		public String getPlmasterid() {
			return plmasterid;
		}

		public void setPlmasterid(String plmasterid) {
			this.plmasterid = plmasterid;
		}

		
		public String getPldetailsid() {
			return pldetailsid;
		}

		public void setPldetailsid(String pldetailsid) {
			this.pldetailsid = pldetailsid;
		}

		public String getPtwokeyid() {
			return ptwokeyid;
		}

		public void setPtwokeyid(String ptwokeyid) {
			this.ptwokeyid = ptwokeyid;
		}
		
		public String getPlrkKeyid() {
			return plrkKeyid;
		}

		public void setPlrkKeyid(String plrkKeyid) {
			this.plrkKeyid= plrkKeyid;
		}

		public String getIsOpenMsr() {
			return isOpenMsr;
		}

		public void setIsOpenMsr(String isOpenMsr) {
			this.isOpenMsr = isOpenMsr;
		}


		public String getOldTime() {
			return oldTime;
		}


		public void setOldTime(String oldTime) {
			this.oldTime = oldTime;
		}


		public String getPlosKeyid() {
			return plosKeyid;
		}


		public void setPlosKeyid(String plosKeyid) {
			this.plosKeyid = plosKeyid;
		}


		public String getAvailtime() {
			return availtime;
		}


		public void setAvailtime(String availtime) {
			this.availtime = availtime;
		}
		public String getProdtimehourly() {
			return prodtimehourly;
		}


		public void setProdtimehourly(String prodtimehourly) {
			this.prodtimehourly = prodtimehourly;
		}


		public String getAvailtimehourly() {
			return availtimehourly;
		}


		public void setAvailtimehourly(String availtimehourly) {
			this.availtimehourly = availtimehourly;
		}


		public String getLosstimehourly() {
			return losstimehourly;
		}


		public void setLosstimehourly(String losstimehourly) {
			this.losstimehourly = losstimehourly;
		}


		public String getUnreptimehourly() {
			return unreptimehourly;
		}


		public void setUnreptimehourly(String unreptimehourly) {
			this.unreptimehourly = unreptimehourly;
		}
		
		public String getLosshrno(){
			return losshrno;
		}
		public void setLosshrno(String losshrno){
			this.losshrno=losshrno;
		}
		public String getShiftWise(){
			return shiftWise;
		}
		public void setShiftWise(String shiftWise){
			this.shiftWise=shiftWise;
		}
		
		//elumalai
		public String toJsonManual() {
		    StringBuilder sb = new StringBuilder();
		    sb.append("{");
		    sb.append("\"sftDuration\":").append(jsonInt(getSftDuration()));
		    sb.append(",\"availtimehourly\":").append(jsonNum(getAvailtimehourly()));
		    sb.append(",\"unreptimehourly\":").append(jsonNum(getUnreptimehourly()));
		    sb.append(",\"prodtimehourly\":").append(jsonNum(getProdtimehourly()));
		    // Spring checks addrowtoGrid == null -> must be JSON null when empty
		    sb.append(",\"addrowtoGrid\":").append(jsonStrOrNull(getAddrowtoGrid()));
		    sb.append("}");
		    return sb.toString();
		}

		private static String jsonNum(String v) {
		    if (v == null) return "null";
		    String t = v.trim();
		    return t.matches("[-+]?\\d+(\\.\\d+)?") ? t : "null";
		}

		private static String jsonInt(String v) {
		    String n = jsonNum(v);
		    if ("null".equals(n)) return n;
		    try {
		        return String.valueOf(new java.math.BigDecimal(n).intValue());
		    } catch (Exception e) {
		        return "null";
		    }
		}

		private static String jsonStrOrNull(String v) {
		    if (v == null) return "null";
		    String t = v.trim();
		    if (t.length() == 0 || "null".equalsIgnoreCase(t) || "undefined".equalsIgnoreCase(t) || "{}".equals(t))
		        return "null";
		    return "\"" + t.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
		}
		
		/*
		 * public String toJsonManual() { StringBuilder sb = new StringBuilder();
		 * sb.append("{");
		 * 
		 * boolean first = true; for (tableFldConstants field :
		 * tableFldConstants.values()) { int index = field.ordinal(); if (index <
		 * saveArray.length) { if (!first) sb.append(",");
		 * sb.append("\"").append(field.name()).append("\":"); Object val =
		 * saveArray[index]; if(field.name() == "keyid" && val == null) {
		 * sb.append("null"); }else if (val == null) { sb.append("\"{}\""); } else {
		 * sb.append("\"").append(val.toString()).append("\""); } first = false; } }
		 * 
		 * sb.append("}"); return sb.toString(); }
		 * 
		 * public static String toJsonManualList(List<AbnTlAbnormality> list) {
		 * StringBuilder sb = new StringBuilder(); sb.append("[");
		 * 
		 * for (int i = 0; i < list.size(); i++) { if (i > 0) sb.append(",");
		 * sb.append(list.get(i).toJsonManual()); }
		 * 
		 * sb.append("]"); return sb.toString(); }
		 */
}
