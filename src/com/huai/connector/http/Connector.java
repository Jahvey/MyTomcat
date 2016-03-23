package com.huai.connector.http;

import com.huai.core.http.HttpProcessor;
import com.huai.doc.MyXMLParser;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by liangyh on 3/23/16.
 */
public class Connector implements Runnable{
    boolean stoped = false;
    private String scheme = "http";
    private final static int PORT = 8080;
    private final static String IP = "127.0.0.1";

    public String getScheme(){
        return this.scheme;
    }


    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(PORT, 1, InetAddress.getByName(IP));
            while(!stoped){
                try {
                    Logger.getLogger("Connector").log(Level.INFO, "the server is waiting client to connect");
                    Socket socket = serverSocket.accept();
                    HttpProcessor processor = new HttpProcessor();
                    processor.process(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }catch (Exception e){
            Logger.getLogger("Connector").log(Level.WARNING, "the server start error");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void start(){

        loadConfigFile();

        Logger.getLogger("Connector").log(Level.INFO, "load Config file completedly");

        //threads pool
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(this);
    }

    /**
     * load and parse the web.xml config file.
     */
    public void loadConfigFile(){
        MyXMLParser.instance();
    }
}
