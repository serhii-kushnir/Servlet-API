package org.example.servlet;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.servlet.utility.Thymeleaf;
import org.example.servlet.utility.Constant;
import org.example.servlet.utility.Respaonce;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebFilter("/time")
public final class TimezoneValidateFilter implements Filter {
    private static final String HTML_TEMPLATE = "InvalidTime";
    private TemplateEngine thymeleaf;

    @Override
    public void init(final FilterConfig filterConfig) {
        thymeleaf = new TemplateEngine();
        Thymeleaf.init(thymeleaf);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws ServletException, IOException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            String queryString = httpRequest.getQueryString();

            if (Constant.VALID_TIMEZONES.contains(queryString) || queryString == null) {
                chain.doFilter(request, response);
            } else {
                httpResponse.setContentType("text/html");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                Context context = Thymeleaf.getContext();
                Respaonce.writer(thymeleaf, HTML_TEMPLATE, context, httpResponse);
            }
    }
}

