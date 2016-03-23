package com.huai.startup;

import com.huai.connector.http.Connector;

import java.util.logging.Logger;

/**
 * Created by liangyh on 3/13/16.
 */
public class Bootstrap {
    public static void main(String[] args) {
        Connector connector = new Connector();
        Logger.getLogger("com.huai.startup.Bootstrap").info("the server is starting");
        connector.start();
    }
}
