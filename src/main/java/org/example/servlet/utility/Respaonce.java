package org.example.servlet.utility;

import jakarta.servlet.http.HttpServletResponse;
import org.example.servlet.HtmlRenderingException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

public final class Respaonce {

    private Respaonce() {
    }

    public static void writer(final TemplateEngine thymeleaf, final String template, final Context context, final HttpServletResponse resp) {
        try {
            thymeleaf.process(template, context, resp.getWriter());
        } catch (IOException e) {
            throw new HtmlRenderingException("Error rendering HTML content");
        }
    }
}
