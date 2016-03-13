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
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            HttpRequest request = new HttpRequest(inputStream);

            HttpResponse response = new HttpResponse(outputStream);
            response.setRequest(request);
            response.setHeader("Server", "Pyrmont Servlet Container");

            request.parse();

            if (request.getUri().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }
            // Close the socket
            socket.close();
            // no shutdown for this application
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
