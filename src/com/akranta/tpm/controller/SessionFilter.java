package com.akranta.tpm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class SessionFilter implements Filter {
 
    private ArrayList<String> urlList;
    private ArrayList<String> folderList;
    private ArrayList<String> ntsptList;
    private static final String[] DEFAULT_BROWSERS =
    		    { "Firefox"};
    private static final String browserNotSupprtURL = "ntspt";
    private static final String notsuportted_cookieName = "ntspt";
    public void init(FilterConfig config) throws ServletException {
        String urls = config.getInitParameter("avoid-urls");
        StringTokenizer token = new StringTokenizer(urls, ",");
 
        urlList = new ArrayList<String>();
        while (token.hasMoreTokens()) {
        	
            urlList.add(token.nextToken());
 
        }
        urls = config.getInitParameter("avoid-url-ntspt");
        token = new StringTokenizer(urls, ",");
        ntsptList = new ArrayList<String>(); 
        while (token.hasMoreTokens()) {
        	
            ntsptList.add(token.nextToken());
 
        }
        
        String folders = config.getInitParameter("avoid-folder");
        token = new StringTokenizer(folders, ",");
        folderList = new ArrayList<String>();
        while (token.hasMoreTokens()) {
        	folderList.add(token.nextToken());
        }
 
        
    }
    
    public void destroy() {
    }
 
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String isForGetDate = ((HttpServletRequest) req).getHeader("x-getServerDate");
        if( ! "Y".equals(isForGetDate)){
        	
	        String url = request.getServletPath();
	        
	        
	        boolean allowedRequest = false, isAllowedFolder = false;
	        boolean isBrowserSupport = false, isNotSptUrl = false;;
	        //CommonMessage.debugMsg(" urlList " + urlList + " - " + url);
	        if(urlList.contains(url)) {
	            allowedRequest = true;
	        }
	        
	        if( ntsptList.contains(url)){
	        	isNotSptUrl = true;
	        }
	        for(String allowedFolder : folderList ){
	        	if( url != null && url.startsWith(allowedFolder)) {
	        		isAllowedFolder = true;
	                break;
	        	}     
	        }
	        
	        
	 //       String ntsptCookie = UIUtils.getCookieValue(request, notsuportted_cookieName);
	        
	        	
	        String userAgent = ((HttpServletRequest) req).getHeader("User-Agent");
	        //CommonMessage.debugMsg("userAgent "  +userAgent);
	        
	        for (String browser_id : DEFAULT_BROWSERS)
	        {
		        if (userAgent.contains(browser_id))
		        {
		        	isBrowserSupport = true;
		        	break;
		        }
	        }
	        isBrowserSupport = true;
	        //System.out.print("!!isBrowserSupport " + isBrowserSupport + " URL " + url + " isAllowedFolder" + isAllowedFolder + " allowedRequest " + allowedRequest + " isNotSptUrl " + isNotSptUrl);
	        if( ! isBrowserSupport && ! isAllowedFolder && ! isNotSptUrl)
	        	//((HttpServletResponse) response).sendRedirect(this.browserNotSupprtURL);
	        	response.sendRedirect("perfex");
	        else if( isBrowserSupport && isNotSptUrl ){
	        	 response.sendRedirect("perfex");
	        }
	        else if (!allowedRequest && ! isAllowedFolder && ! isNotSptUrl) {
	        /*	Enumeration<String> head = request.getHeaderNames();
	        	String s =null;
	        	while( head.hasMoreElements())
	        	{
	        		s = head.nextElement();
	        		CommonMessage.debugMsg(" header Name " + s + " value - " + request.getHeader(s) );
	        	}
	        */	
	        	
	        //	UIUtils.displayRequestParamsValue(request);
	            HttpSession session = request.getSession(false);
	
	            
	        	
	            //CommonMessage.debugMsg(" inac " + " -- s  " + session );
	            if (session == null || ! UIUtils.checkUserSession(request, response)) {
	            	String requestType = request.getHeader("x-requested-with");
	          //  	CommonMessage.debugMsg("requestType " + requestType);
		
	            	if( requestType == null || (requestType != null && ! requestType.equals("XMLHttpRequest")) ){
	            		response.sendRedirect("perfex");
	            	}	
	            	else if( requestType.equals("XMLHttpRequest") ){
	            		response.setContentType("text/html");
	            		JSONObject sExpires = new JSONObject();
	            		sExpires.put("sExpires", "true");
	            		response.getWriter().print(sExpires);
	            		response.getWriter().close();
	            		throw new ServletException();
	            	}
	            		
	            }
	            else{
	            	response.setCharacterEncoding("UTF-8");
	            	response.setContentType("text/html");
	            	//response.getWriter().write("<script>var timeServer = new Date('" + Calendar.getInstance().getTime() + "');</script>");
	            	//chain.doFilter(req, res);
	            	chain.doFilter(new RequestWrapper( (HttpServletRequest)request ), response);
	            	
	            }	
	            
	        }
	        else{
	        	//chain.doFilter(new RequestWrapper( (HttpServletRequest)request ), response);
	        	chain.doFilter(req, res);
	        }
        }    
    }
}
