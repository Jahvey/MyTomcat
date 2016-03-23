# MyTomcat

This is Web Server container like Apache Tomcat!


----You can see different version in different Branch! -----


一、How to run it?

    the main class is in com.huai.startup.Bootstrap.java


二、Now It can do:

1. Sent back HTML page to you while you pass a request in you browser, like:"http://localhost:8080/index.html"

2. Handle Servlet who implemented Servlet interface.
    If you want to test this function, you can access the URL:http://localhost:8080/PrimitiveServlet. (You may find the
    byte class file in the webroot/com/huai/test/PrimitiveServlet.class). If you want to test your own servlet file,
    just put the byte class file in the webroot, and config the web.xml file. And the url you
    put in you browser should be "http://localhost:8080/your_Servlet_Name". You can infer the
    com.huai.test.PrimitiveServlet.java file.
