package com.akranta.tpm.filter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        res.setHeader("Pragma", "no-cache");
        res.setDateHeader("Expires", 0);

        // Optional (More strict)
        res.setHeader("Cache-Control", "no-store");


        res.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        res.setHeader("Pragma", "no-cache");
        res.setDateHeader("Expires", 0);
        res.setHeader("Referrer-Policy", "strict-origin-when-cross-origin");


        
        // Click jacking 
        res.setHeader("X-Frame-Options", "DENY");

        // MIME Sniffing Protection
        //res.setHeader("X-Content-Type-Options", "nosniff");

        // XSS Protection (basic browsers)
        res.setHeader("X-XSS-Protection", "1; mode=block");

        // HSTS (ONLY if HTTPS)
        //res.setHeader("Strict-Transport-Security", "max-age=31536000; includeSubDomains");

		/*
		 * res.setHeader("Content-Security-Policy",
		 * "default-src 'self'; script-src 'self' 'unsafe-inline'; style-src 'self' 'unsafe-inline'; img-src 'self' data:;"
		 * );
		 */
        chain.doFilter(request, response);
    }
    }

