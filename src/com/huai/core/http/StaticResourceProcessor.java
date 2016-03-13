package com.huai.core.http;

import com.huai.core.http.HttpRequest;
import com.huai.core.http.HttpResponse;

import java.io.IOException;

/**
 * Created by liangyh on 3/12/16.
 */
public class StaticResourceProcessor {

    public void process(HttpRequest request, HttpResponse response){
        try{
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
