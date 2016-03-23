package com.huai.doc;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by liangyh on 3/23/16.
 */
public class MyXMLParser {

    public static final String CONFIG_FILE = "webroot/WEB-INF/web.xml";

    private final String DOC_ROOT_TAG = "web-app";

    private final String SERVLET_TAG = "servlet";

    private final String SERVLET_NAME_TAG = "servlet-name";

    private final String SERVLET_CLASS_TAG = "servlet-class";

    private final String SERVLET_MAPPING_TAG = "servlet-mapping";

    private final String URL_PATTERN_TAG = "url-pattern";

    private Document document;

    /**
     * singleton pattern
     */
    private MyXMLParser(){
        init();
    }

    public static MyXMLParser instance(){
        return Nested.myXMLParse;
    }

    public static class Nested{
        public static MyXMLParser myXMLParse = new MyXMLParser();
    }

    private void init(){
        Logger.getLogger("XmlDocumentImpl").log(Level.INFO, "initing the web.xml config file");
        try {
//            String path = MyXMLParser.class.getClassLoader().getResource(CONFIG_FILE).getPath();
            File file = new File(CONFIG_FILE);
            String path = file.getCanonicalPath();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(path);

            parserXml();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void parserXml() {
        //
        List<Map<String, String>> servlets = ServletInfo.servlets;
        Map<String, String> servletsPackage = ServletInfo.servletsPackage;

        Element root = document.getDocumentElement();
        if(root == null) return ;

        NodeList servletList = root.getElementsByTagName(SERVLET_TAG);

        for (int i = 0; i < servletList.getLength(); i++) {

            Element servletElement = (Element)servletList.item(i);

            NodeList childList = servletElement.getElementsByTagName(SERVLET_NAME_TAG);
            if(childList == null || childList.getLength() < 1)
                continue;
            String nameValue = childList.item(0).getTextContent();

            NodeList classNodes = servletElement.getElementsByTagName(SERVLET_CLASS_TAG);
            if(classNodes == null || classNodes.getLength() < 1)
                continue;
            String classContent = classNodes.item(0).getTextContent();

            Map<String, String> servletMap = new HashMap<>();
            servletMap.put(SERVLET_NAME_TAG, nameValue);
            servletMap.put(SERVLET_CLASS_TAG, classContent);
            servlets.add(servletMap);

            servletsPackage.put(nameValue, classContent);
        }
        Logger.getLogger("XmlDocumentImpl").log(Level.INFO, "parse completedly");
    }

}
