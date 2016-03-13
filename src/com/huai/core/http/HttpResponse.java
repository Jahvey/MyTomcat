package com.huai.core.http;

import com.huai.utils.Constants;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Locale;

/**
 * Created by liangyh on 3/12/16.
 */
public class HttpResponse implements HttpServletResponse {
    private static final int BUFFER_SIZE = 1024;
    HttpRequest request;
    OutputStream outputStream;
    PrintWriter writer;

    /**
     * @param outputStream
     *  传进来的outputStream是与{@link java.net.Socket}相关连的。
     */
    public HttpResponse(OutputStream outputStream){
        this.outputStream = outputStream;
    }

    public void setRequest(HttpRequest request){
        this.request = request;
    }

    /**
     * 发送静态文件
     * @throws IOException
     */
    public void sendStaticResource() throws IOException {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fileInputStream = null;

        try{
            File file = new File(Constants.WEB_ROOT, request.getUri());
            if(file.exists()){
                fileInputStream = new FileInputStream(file);
                int ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
                while(ch != -1){
                    outputStream.write(bytes, 0, ch);
                    ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
                }
            }else{
                //file not found
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n"+
                        "Content-Type:text/html\r\n"+"Content-Length:23\r\n"+
                        "\r\n"+
                        "<h1>File Not Found</h1>";
                System.out.println(errorMessage);
                outputStream.write(errorMessage.getBytes());

            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.toString());
        }finally{
            if(fileInputStream != null)
                fileInputStream.close();
        }
    }


    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        //auto flush is true, println()will flush.
        //but print()will not.
        writer = new PrintWriter(outputStream, true);
        return writer;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentLengthLong(long l) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }


    @Override
    public void addCookie(Cookie cookie) {

    }

    @Override
    public boolean containsHeader(String s) {
        return false;
    }

    @Override
    public String encodeURL(String s) {
        return null;
    }

    @Override
    public String encodeRedirectURL(String s) {
        return null;
    }

    @Override
    public String encodeUrl(String s) {
        return null;
    }

    @Override
    public String encodeRedirectUrl(String s) {
        return null;
    }

    @Override
    public void sendError(int i, String s) throws IOException {

    }

    @Override
    public void sendError(int i) throws IOException {

    }

    @Override
    public void sendRedirect(String s) throws IOException {

    }

    @Override
    public void setDateHeader(String s, long l) {

    }

    @Override
    public void addDateHeader(String s, long l) {

    }

    @Override
    public void setHeader(String s, String s1) {

    }

    @Override
    public void addHeader(String s, String s1) {

    }

    @Override
    public void setIntHeader(String s, int i) {

    }

    @Override
    public void addIntHeader(String s, int i) {

    }

    @Override
    public void setStatus(int i) {

    }

    @Override
    public void setStatus(int i, String s) {

    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getHeader(String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaders(String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return null;
    }
}
