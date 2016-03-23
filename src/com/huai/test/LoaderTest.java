package com.huai.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by liangyh on 3/12/16.
 */
public class LoaderTest {

    public static void main(String[] args) {
        URLClassLoader loader = null;

        URL[] urls = new URL[1];
        URLStreamHandler streamHandler = null;
        File classFile = new File(System.getProperty("user.dir"));

        String servletName = "com.huai.test.TestMain";

        try {
            String repository = (new URL("file", null, classFile.getCanonicalPath()+File.separator)).toString();

            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        TestMain testMain = null;

        try {
            testMain = (TestMain)myClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
