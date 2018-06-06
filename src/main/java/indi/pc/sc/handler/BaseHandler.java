package indi.pc.sc.handler;

import java.util.Map;

public abstract class BaseHandler {

    public static int timeout = 60000;
    public static String proxy;
    public static Map<String, Object> headers;

    public abstract void init();

    public void crawl(String url) {

    }
}
