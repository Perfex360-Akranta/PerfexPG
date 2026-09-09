package com.akranta.tpm.service.api;

import java.util.HashMap;
import java.util.Map;

public class AbnormalityFunctionHeaders {
	private static final Map<String, String[]> HEADERS_MAP = new HashMap<>();

    static {
       
        HEADERS_MAP.put("ABN_FN_ABNORMALITYVIEW", new String[]{
                "attachment", "tagno", "detecteddate", "item", "machinemname",
                "detectedby", "tagclass", "funloc", "abnormalitytype",
                "abnormalitycategory", "abnormalityimpact", "countermeasure",
                "remarks", "maintenancesection", "responsibility", "targetdate",
                "status", "workdoneby", "elapseddays", "afeem", "orderdate", "rowno"
            });

    
        HEADERS_MAP.put("JHN_FN_GetSqlForFillSpread", new String[]{
    	    "slno","selectv", "attachment", "tagno", "detecteddate", "item", "machinemname",
    	    "detectedby", "responsiblityby", "mwno", "tagclass", "funloc", "assembly",
    	    "occureddate", "abnormalitytype", "whyabnormality", "whatcause",
    	    "abnormalitycategory", "abnormalityimpact", "countermeasure", "remarks",
    	    "targetdate", "status", "workstartdate", "workenddate", "completeddate",
    	    "workdoneby", "elapseddays", "actnplnsts", "st", "refdoc", "receivedateddate",
    	    "downtime", "manpower", "orderdate"
    	});
    }

    public static String[] getHeaders(String functionName) {
        return HEADERS_MAP.getOrDefault(functionName, new String[0]);
    }
}
