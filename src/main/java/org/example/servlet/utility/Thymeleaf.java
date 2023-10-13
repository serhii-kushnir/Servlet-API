package org.example.servlet.utility;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.FileTemplateResolver;

public final class Thymeleaf {

    private Thymeleaf() {
    }

    public static void init(final TemplateEngine thymeleaf) {
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix("src/main/webapp/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode("HTML5");
        resolver.setOrder(thymeleaf.getTemplateResolvers().size());
        resolver.setCacheable(false);

        thymeleaf.addTemplateResolver(resolver);
    }

    public static Context getContext(final String timeZone) {
        Context context = new Context();
        context.setVariable("currentTime", timeZone);

        return context;
    }

    public static Context getContext() {
        Context context = new Context();
        context.setVariable("invalid", "Invalid timezone");

        return context;
    }
}
