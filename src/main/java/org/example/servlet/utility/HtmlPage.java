package org.example.servlet.utility;

public final class HtmlPage {

    private HtmlPage() {
    }

    public static String getHtmlPage(final String massage) {
        return """
                <!DOCTYPE html>
                <html lang="en">
                    <head>
                        <title>Current Time</title>
                    </head>
                    <body>
                        <center>
                            <h1>Current Time</h1>
                            <p>%s</p>
                        </center>
                    </body>
                </html>
                """.formatted(massage);
    }
}
