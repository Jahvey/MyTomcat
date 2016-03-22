package com.huai.core.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by liangyh on 3/13/16.
 */
public class HttpProcessor {
    private String requestLine;

    public HttpProcessor(){}


    public void process(Socket socket) throws IOException {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()){

            HttpRequest request = new HttpRequest(inputStream);

            HttpResponse response = new HttpResponse(outputStream);
            response.setRequest(request);
            response.setHeader("Server", "LiangYiHuai's Servlet Container");

            //parse the request
            request.parse();

            if (request.getUri().startsWith("/servlet/")) {//if it is a JSP
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {//if it is a static source, included HTML page
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            // Close the socket
            socket.close();
            // no shutdown for this application
        }
    }



}
