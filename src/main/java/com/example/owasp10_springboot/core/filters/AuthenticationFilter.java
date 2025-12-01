package com.example.owasp10_springboot.core.filters;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet Filter for authentication
 */
public class AuthenticationFilter implements Filter {

    /**
     * Intercept unauthenticated requests for specific URLs and redirect to login page.
     *
     * @see jakarta.servlet.Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String target = request.getRequestURI();
        
        if (target.startsWith("/admins") || "/serverinfo".equals(target)) {
            /* Login (authentication) is needed to access admin pages (under /admins). */
            
            String loginType = request.getParameter("logintype");
            String queryString = request.getQueryString();
            if (queryString == null) {
                queryString = "";
            } else {
                /* Remove "logintype" parameter from query string. (* "logintype" specifies a login servlet) */
                queryString = queryString.replace("logintype=" + loginType + "&", "");
                queryString = queryString.replace("&logintype=" + loginType, "");
                queryString = queryString.replace("logintype=" + loginType, "");
                if (!queryString.isEmpty()) {
                    queryString = "?" + queryString;
                }
            }
            HttpSession session = request.getSession(false);
            String authNMsg = (session == null) ? null : (String) session.getAttribute("authNMsg");
			if (!"authenticated".equals(authNMsg)) {
                /* Not authenticated yet */
                session = request.getSession(true);
                session.setAttribute("target", target + queryString);
                if (loginType == null) {
                    response.sendRedirect("/login" + queryString);
                } else if ("sessionfixation".equals(loginType)) {
                    response.sendRedirect(response.encodeRedirectURL("/" + loginType + "/login" + queryString));
                } else {
                    response.sendRedirect("/" + loginType + "/login" + queryString);
                }
                return;
            }
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
