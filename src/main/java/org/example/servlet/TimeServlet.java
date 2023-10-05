package org.example.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        TimeZone timeZone = TimeZone.getTimeZone("UTC");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(timeZone);

        String currentTime = dateFormat.format(new Date());

        try (PrintWriter out = resp.getWriter()){
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<title>Current Time</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Current Time</h1>");
            out.println("<p>" + currentTime + "</p>");
            out.println("</body>");
            out.println("</html>");
        } catch (IOException e) {
            throw new HtmlRenderingException("Error rendering HTML content", e);
        }

    }
}
