package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Application {

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        // Контекст веб-застосунку
        Context context = tomcat.addContext("", new File(".").getAbsolutePath());

        // Додавання сервлета
        Tomcat.addServlet(context, "hello", new HelloWorldServlet());
        context.addServletMappingDecoded("/hello-world", "hello");

        tomcat.start();
        tomcat.getServer().await();
    }
}