package com.huai.core.http;

import com.huai.doc.ServletInfo;
import com.huai.utils.Constants;


import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by liangyh on 3/12/16.
 */
public class ServletProcessor {

    public void process(HttpRequest request, HttpResponse response){
        //get servlet 's package name.
        String servletPackage = ServletInfo.getServletPackage(request.getUri());

        URLClassLoader loader = null;

        URL[] urls = new URL[1];
        URLStreamHandler streamHandler = null;
        File classFile = new File(Constants.WEB_ROOT);

        try {
            String repository = (new URL("file", null, classFile.getCanonicalPath()+File.separator)).toString();

            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletPackage);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Servlet servlet = null;

        try {
            servlet = (Servlet)myClass.newInstance();

            servlet.service(request, response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
