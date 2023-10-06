package org.example.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.servlet.utility.DataTimeZone;
import org.example.servlet.utility.HtmlPage;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/time")
public final class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try (PrintWriter out = resp.getWriter()) {
            out.println(
                    HtmlPage.getHtmlPage(
                            DataTimeZone.getDataTimeZone()));
        } catch (IOException e) {
            throw new HtmlRenderingException("Error rendering HTML content");
        }
    }
}
