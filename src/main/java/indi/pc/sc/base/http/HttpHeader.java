package indi.pc.sc.base.http;

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

public enum HttpHeader {
    CONNECTION("Connection"),
    CACHE_CONTROL("Cache-Control"),
    DATE("Date"),
    PRAGMA("Pragma"),
    PROXY_CONNECTION("Proxy-Connection"),
    TRAILER("Trailer"),
    TRANSFER_ENCODING("Transfer-Encoding"),
    UPGRADE("Upgrade"),
    VIA("Via"),
    WARNING("Warning"),
    NEGOTIATE("Negotiate"),
    ALLOW("Allow"),
    CONTENT_ENCODING("Content-Encoding"),
    CONTENT_LANGUAGE("Content-Language"),
    CONTENT_LENGTH("Content-Length"),
    CONTENT_LOCATION("Content-Location"),
    CONTENT_MD5("Content-MD5"),
    CONTENT_RANGE("Content-Range"),
    CONTENT_TYPE("Content-Type"),
    EXPIRES("Expires"),
    LAST_MODIFIED("Last-Modified"),
    ACCEPT("Accept"),
    ACCEPT_CHARSET("Accept-Charset"),
    ACCEPT_ENCODING("Accept-Encoding"),
    ACCEPT_LANGUAGE("Accept-Language"),
    AUTHORIZATION("Authorization"),
    EXPECT("Expect"),
    FORWARDED("Forwarded"),
    FROM("From"),
    HOST("Host"),
    IF_MATCH("If-Match"),
    IF_MODIFIED_SINCE("If-Modified-Since"),
    IF_NONE_MATCH("If-None-Match"),
    IF_RANGE("If-Range"),
    IF_UNMODIFIED_SINCE("If-Unmodified-Since"),
    KEEP_ALIVE("Keep-Alive"),
    MAX_FORWARDS("Max-Forwards"),
    PROXY_AUTHORIZATION("Proxy-Authorization"),
    RANGE("Range"),
    REQUEST_RANGE("Request-Range"),
    REFERER("Referer"),
    TE("TE"),
    USER_AGENT("User-Agent"),
    X_FORWARDED_FOR("X-Forwarded-For"),
    X_FORWARDED_PROTO("X-Forwarded-Proto"),
    X_FORWARDED_SERVER("X-Forwarded-Server"),
    X_FORWARDED_HOST("X-Forwarded-Host"),
    ACCEPT_RANGES("Accept-Ranges"),
    AGE("Age"),
    ETAG("ETag"),
    LOCATION("Location"),
    PROXY_AUTHENTICATE("Proxy-Authenticate"),
    RETRY_AFTER("Retry-After"),
    SERVER("Server"),
    SERVLET_ENGINE("Servlet-Engine"),
    VARY("Vary"),
    WWW_AUTHENTICATE("WWW-Authenticate"),
    ORIGIN("Origin"),
    SEC_WEBSOCKET_KEY("Sec-WebSocket-Key"),
    SEC_WEBSOCKET_VERSION("Sec-WebSocket-Version"),
    SEC_WEBSOCKET_EXTENSIONS("Sec-WebSocket-Extensions"),
    SEC_WEBSOCKET_SUBPROTOCOL("Sec-WebSocket-Protocol"),
    SEC_WEBSOCKET_ACCEPT("Sec-WebSocket-Accept"),
    COOKIE("Cookie"),
    SET_COOKIE("Set-Cookie"),
    SET_COOKIE2("Set-Cookie2"),
    MIME_VERSION("MIME-Version"),
    IDENTITY("identity"),
    X_POWERED_BY("X-Powered-By"),
    HTTP2_SETTINGS("HTTP2-Settings"),
    STRICT_TRANSPORT_SECURITY("Strict-Transport-Security"),
    C_METHOD(":method"),
    C_SCHEME(":scheme"),
    C_AUTHORITY(":authority"),
    C_PATH(":path"),
    C_STATUS(":status"),
    UNKNOWN("::UNKNOWN::");

    private static final Map<String, HttpHeader> mappings = new HashMap(76);


    private HttpHeader(String s) {
    }

    public boolean is(String s) {
        return this.toString().equalsIgnoreCase(s);
    }

    @Nullable
    public static HttpHeader resolve(@Nullable String header) {
        return header != null ? (HttpHeader) mappings.get(header.toUpperCase()) : null;
    }

    public boolean matches(String method) {
        return this == resolve(method);
    }

    static {
        HttpHeader[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            HttpHeader httpHeader = var0[var2];
            mappings.put(httpHeader.name(), httpHeader);
        }
    }
}
