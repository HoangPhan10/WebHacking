package com.example.owasp10_springboot.core.filters;

import java.io.IOException;


import jakarta.servlet.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.context.MessageSource;

/**
 * Servlet Filter for security
 */
public class SecurityFilter implements Filter {

    private MessageSource msg;

    public SecurityFilter(MessageSource msg) {
        this.msg = msg;
    }

    /**
     * The maximum size permitted for the complete request.
     */
    private static final int REQUEST_SIZE_MAX = 1024 * 1024 * 50;

    /**
     * The maximum size permitted for a single uploaded file.
     */
    private static final int FILE_SIZE_MAX = 1024 * 1024 * 10;

    /**
     * Prevent several security vulnerabilities.
     * 
     * @see jakarta.servlet.Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String target = request.getRequestURI();
        
         /* Prevent clickjacking if target is not /admins/clickjacking ... */
        if (!target.startsWith("/admins/clickjacking")) {
            response.addHeader("Content-Security-Policy", "frame-ancestors 'self'");
        }
        /* Prevent Content-Type sniffing */
        response.addHeader("X-Content-Type-Options", "nosniff");

        /* Prevent XSS */
        if (!target.startsWith("/xss") && !target.startsWith("/admins/seshj")) {
            response.addHeader("X-XSS-Protection", "1; mode=block");
        }
        
        /* Prevent to upload large files if target start w/ /ureupload and /xee and /xxe */
        if ((target.startsWith("/ureupload") || target.startsWith("/xee") || target.startsWith("/xxe"))
                && request.getMethod().equalsIgnoreCase("POST")) {
            ServletFileUpload upload = new ServletFileUpload();
            upload.setFileItemFactory(new DiskFileItemFactory());
            upload.setFileSizeMax(FILE_SIZE_MAX); // 10MB
            upload.setSizeMax(REQUEST_SIZE_MAX); // 50MB
//            try {
//                upload.parseRequest(
//                        new ServletRequestContext(request));
//            } catch (FileUploadException e) {
//                req.setAttribute("errorMessage", msg.getMessage("msg.max.file.size.exceed", null, request.getLocale()));
//            }
        }

       chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // Do nothing
    }

    @Override
    public void init(FilterConfig arg0) {
        // Do nothing
    }
}
