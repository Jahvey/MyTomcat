package com.huai.test;

import java.io.*;
import java.net.Socket;

/**
 * Created by liangyh on 3/11/16.
 */
public class SocketTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("111.13.101.208", 80);

        boolean autoFlush = true;
        OutputStream os = socket.getOutputStream();

        PrintWriter out = new PrintWriter(os, autoFlush);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("GET / HTTP/1.1");
        out.println("Host:111.13.101.208:80");

        out.println("Connection: Close");
        out.println();

        boolean loop = true;
        StringBuffer sb = new StringBuffer(8096);
        while(loop){
            if(in.ready()){
                int i = 0;
                while(i  != -1){
                    i = in.read();
                    sb.append((char)i);
                }
                loop = false;
            }
            Thread.currentThread().sleep(50);
        }
        System.out.println(sb.toString());
        socket.close();
    }
}
