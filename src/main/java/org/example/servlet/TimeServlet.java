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

        if (req.getQueryString() != null && req.getQueryString().startsWith("timezone=")) {
            String timeZoneParameter = req.getQueryString().substring(12);

            try (PrintWriter out = resp.getWriter()){
                out.println(getHtmlPage(getDataTime(timeZoneParameter)));
            } catch (IOException e) {
                throw new HtmlRenderingException("Error rendering HTML content", e);
            }
        } else {
            try (PrintWriter out = resp.getWriter()){
                out.println(getHtmlPage(getDataTime()));
            } catch (IOException e) {
                throw new HtmlRenderingException("Error rendering HTML content", e);
            }
        }
    }

    private String getDataTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return dateFormat.format(new Date());
    }

    private String getDataTime(String timeZoneParameter) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z" + timeZoneParameter);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return dateFormat.format(new Date());
    }

    private String getHtmlPage(String currentTime) {
        return """
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <title>Current Time</title>
                    </head>
                    <body>
                        <h1>Current Time</h1>
                        <p>%s</p>
                    </body>
                </html>
                """.formatted(currentTime);
    }
}
