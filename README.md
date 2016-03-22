# MyTomcat

This is Web Server container like Apache Tomcat!

How to run it?
    the main class is in com.huai.startup.Bootstrap.java


Now It can do:

1. Sent back HTML page to you while you pass a request in you browser, like:"http://localhost:8080/index.html"

2. Handle Servlet who implemented Servlet interface.
    If you want to test this function, you should put your servlet class(byte) file in "webroot" directory. And the url you
    put in you browser should be "http://localhost:8080/servlet/your.servlet.package.ServletName". You can infer the
    com.huai.test.PrimitiveServlet.java file.
