package com.akranta.tpm.filter;

import java.util.regex.Pattern;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class XSSRequestWrapper extends HttpServletRequestWrapper {
	

    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    
    private static final Pattern[] XSS_PATTERNS = {
    	    Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
    	    Pattern.compile("src[\\r\\n]*=[\\r\\n]*\\'(.*?)\\'", Pattern.CASE_INSENSITIVE),
    	    Pattern.compile("</?(script|embed|object|iframe|frame|frameset|form|meta|link|style|img)[^>]*>", Pattern.CASE_INSENSITIVE),
    	    Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE),
    	    Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
    	    Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
    	    Pattern.compile("on(load|error|click|mouse|key|focus|blur|submit)\\s*=", Pattern.CASE_INSENSITIVE),
    	    Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE),
    	  };

    @Override
    public String getHeader(String name) {
        return sanitize(super.getHeader(name));
    }
    
    @Override
    public String getParameter(String param) {
        String value = super.getParameter(param);
        return sanitize(value);
    }

    @Override
    public String[] getParameterValues(String param) {
        String[] values = super.getParameterValues(param);
        if (values == null) return null;

        for (int i = 0; i < values.length; i++) {
            values[i] = sanitize(values[i]);
        }
        return values;
    }

    private String sanitize(String value) {
    	 if (value == null) return null;
    	 value = value.replaceAll("[\r\n\t]", " ");
    	    value = value.replaceAll("\0", ""); // null byte (already have this, keep it)

 	    value = value.replaceAll("\0", "");
 	    for (Pattern p : XSS_PATTERNS)
 	      value = p.matcher(value).replaceAll(" ");
        return value
        		.replace("&", "&amp;").replace("<", "&lt;")
	    	      .replace(">", "&gt;")//.replace("\"", "&quot;")
	    	      .replace("'", "&#x27;");//.replace("/", "&#x2F;"); 
    }
}