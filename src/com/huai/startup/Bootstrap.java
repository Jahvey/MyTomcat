package com.huai.startup;

import com.huai.connector.http.HttpConnector;

/**
 * Created by liangyh on 3/13/16.
 */
public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
