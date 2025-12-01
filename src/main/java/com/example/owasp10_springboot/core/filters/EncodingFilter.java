package com.example.owasp10_springboot.core.filters;



import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;

import java.io.IOException;

/**
 * Servlet Filter for encoding
 */
public class EncodingFilter extends OrderedCharacterEncodingFilter {

    /**
     * Set the encoding to use for requests.
     * "Shift_JIS" is intentionally set to the request to /mojibake.
     *
     * @see jakarta.servlet.Filter#doFilter(ServletRequest, ServletResponse, FilterChain) (ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        super.setOrder(HIGHEST_PRECEDENCE);
        if ("/mojibake".equals(request.getRequestURI())) {
            super.setEncoding("Shift_JIS");
        } else {
            super.setEncoding("UTF-8");
        }
        super.setForceEncoding(true);
        super.doFilterInternal(request, response, filterChain);
    }
}
