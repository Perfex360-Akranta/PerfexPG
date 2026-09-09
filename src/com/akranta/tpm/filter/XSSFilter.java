package com.akranta.tpm.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;

public class XSSFilter implements Filter {

	

	  @Override
	  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
	      throws IOException, ServletException {
	    chain.doFilter(new XSSRequestWrapper((HttpServletRequest) req), res);
	  }

	  
	}
