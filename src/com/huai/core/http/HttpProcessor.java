package com.huai.core.http;

import com.huai.doc.ServletInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by liangyh on 3/13/16.
 */
public class HttpProcessor {

    public HttpProcessor(){}


    public void process(Socket socket) throws IOException {
        Logger logger = Logger.getLogger("HttpProcessor");

        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()){

            HttpRequest request = new HttpRequest(inputStream);
            //parse the request
            request.parse();

            HttpResponse response = new HttpResponse(outputStream);
            response.setRequest(request);
            response.setHeader("Server", "LiangYiHuai's Servlet Container");

            logger.log(Level.INFO, "parse the request");

            String uri = request.getUri();
            if("/".equals(uri)){
                // ?
            }else {
                if (uri.endsWith("/"))
                    uri = uri.substring(0, uri.length() - 1);
                if (uri.startsWith("/"))
                    uri = uri.substring(1);
            }
            String servletPackage = ServletInfo.getServletPackage(uri);

            if(servletPackage != null){
                request.setUri(uri);
                logger.log(Level.INFO, "process servlet class");
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            }else {//if it is a static source, included HTML page
                logger.log(Level.INFO, "process static resource");
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
