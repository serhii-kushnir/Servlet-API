package org.example.servlet;

import javax.servlet.Filter;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.servlet.utility.DataTimeZone;
import org.example.servlet.utility.HtmlPage;

import java.io.IOException;
import java.io.PrintWriter;


@WebFilter("/time")
public final class TimezoneValidateFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            String queryString = httpRequest.getQueryString();

            if (queryString != null
                    && (queryString.startsWith("timezone=UTC+") || queryString.startsWith("timezone=UTC-"))) {
                String timeZoneParameter = queryString.substring(12, 14);

                try (PrintWriter out = response.getWriter()) {
                    out.println(
                            HtmlPage.getHtmlPage(
                                    DataTimeZone.getDataTimeZone(
                                            timeZoneParameter)));
                } catch (IOException e) {
                    throw new HtmlRenderingException("Error rendering HTML content");
                }
            } else {
                httpResponse.setContentType("text/html");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);

                try (PrintWriter out = response.getWriter()){
                    out.println(
                            HtmlPage.getHtmlPage(
                                    "Invalid timezone"));
                } catch (IOException e) {
                    throw new HtmlRenderingException("Error rendering HTML content");
                }
            }

            chain.doFilter(request, response);
        }
    }
}
