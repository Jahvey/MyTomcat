package com.huai.doc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class store the information of servlets which from the web.xml config file by parse it.
 * Created by liangyh on 3/23/16.
 */
public class ServletInfo {

    /**
     * store Servlets information in web.xml config document.
     */
    public static List<Map<String,String>> servlets = new ArrayList<>();

    /**
     * the key is servlet name or alias name, the value is servlet class package name.
     */
    public static Map<String, String> servletsPackage = new HashMap<>();

    public ServletInfo(){

    }

    /**
     * Search the class path by servlet name.
     * <p>All the information is stored in the "servlets" field in this class</p>
     * @param servletName
     * @return
     */
    public static String getServletPackage(String servletName){
        return servletsPackage.get(servletName);
    }

}
