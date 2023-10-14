package org.example.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.servlet.utility.Thymeleaf;
import org.example.servlet.utility.Constant;
import org.example.servlet.utility.DataTimeZone;
import org.example.servlet.utility.Respaonce;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@WebServlet("/time")
public final class TimeServlet extends HttpServlet {
    private static final String HTML_TEMPLATE = "CurrentTime";
    private transient TemplateEngine thymeleaf;

    @Override
    public void init(final ServletConfig config) {
        thymeleaf = new TemplateEngine();
        Thymeleaf.init(thymeleaf);
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        String dataTimeZone = DataTimeZone.getDataTime();
        String queryString = req.getQueryString();
        Cookie[] cookies = req.getCookies();

        if (Constant.VALID_TIMEZONES.contains(queryString)) {
            String parseTimeZone = DataTimeZone.parseTimeZone(queryString);
            dataTimeZone = DataTimeZone.getDataTimeZone(parseTimeZone);

            resp.addCookie(new Cookie("lastTimeZone", parseTimeZone));
        } else {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("lastTimeZone".equals(cookie.getName())) {
                        String lastTimeZone = cookie.getValue();
                        dataTimeZone = DataTimeZone.getDataTimeZone(lastTimeZone);
                        break;
                    }
                }
            }
        }

        Context context = Thymeleaf.getContext(dataTimeZone);
        Respaonce.writer(thymeleaf, HTML_TEMPLATE, context, resp);
    }
}

