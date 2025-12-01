package com.example.owasp10_springboot.core.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * Filter to enforce HTTPS communication
 */
public class HttpsEnforcementFilter implements Filter {

    @Value("${metrics.allowed.hosts}")
    protected String METRICS_ALLOWED_HOSTS;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        // Allow HTTP only for these paths
        if (uri.startsWith("/stat")) {
            chain.doFilter(request, response);
            return;
        } else if (uri.startsWith("/metrics") || uri.startsWith("/health")) {
            if (METRICS_ALLOWED_HOSTS.indexOf(req.getRemoteHost()) < 0) {
                res.sendRedirect("https://" + req.getServerName());
            }
            chain.doFilter(request, response);
            return;
        }
        // Check if accessing via HTTPS
        boolean secure = req.isSecure() || "https".equalsIgnoreCase(req.getScheme())
                || "https".equalsIgnoreCase(req.getHeader("X-Forwarded-Proto"));

        if (!secure) {
            // Redirect to HTTPS URL
            String redirectUrl = "https://" + req.getServerName() + req.getRequestURI();
            if (req.getQueryString() != null) {
                redirectUrl += "?" + req.getQueryString();
            }
            res.sendRedirect(redirectUrl);
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }

    @Override
    public void destroy() {
        // Do nothing
    }
}
