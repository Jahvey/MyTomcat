# MyTomcat


This is the first version that I write the web server just like Apache Tomcat Container.


Now It can do:

1. Sent back HTML page to you while you pass a request in you browser, like:"http://localhost:8080/index.html"

2. Handle Servlet who implemented Servlet interface.
    If you want to test this function, you should put your servlet class file in "webroot" directory. And the url you
    put in you browser should be "http://localhost:8080/servlet/your.servlet.package.ServletName". You can infer the
    com.huai.test.PrimitiveServlet.java file.
