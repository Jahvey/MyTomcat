# MyTomcat

这个是本人实现的Tomcat服务器。现在的功能有：
1、能返回用户请求的html文件。
2、可以处理Servlet类的service(HttpServletRequest request, HttpServletResponse response)方法。
目前的功能比较简单：如果要运行Servlet类的class文件，需要该Servlet类无package，并且它的class文件需要放在项目的webroot目录下面。然后在浏览器中输入http://localhost:8080/servlet/**Servlet
如果要访问index.html文件，只需要在浏览器中输入：http://localhsot:8080/index.html,需要注意的是index.html文件需要实存放在webroot文件夹下面。
