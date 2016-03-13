package com.huai.connector.http;

import com.huai.core.http.HttpProcessor;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liangyh on 3/13/16.
 */
public class HttpConnector implements Runnable{
    boolean stopped;
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

            while(!stopped){
                Socket socket = null;
                try{
                    socket = serverSocket.accept();

                    HttpProcessor processor = new HttpProcessor();
                    processor.process(socket);
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }


    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }
}
