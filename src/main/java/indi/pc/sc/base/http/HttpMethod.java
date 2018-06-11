package indi.pc.sc.base.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.lang.Nullable;

/**
 * @see org.springframework.http.HttpMethod
 */
public enum HttpMethod {
    GET,
    POST,
    HEAD,
    PUT,
    OPTIONS,
    DELETE,
    TRACE,
    CONNECT,
    MOVE,
    PROXY,
    PRI;

    private static final Map<String, HttpMethod> mappings = new HashMap(11);

    private HttpMethod() {
    }

    public boolean is(String s) {
        return this.toString().equalsIgnoreCase(s);
    }

    @Nullable
    public static HttpMethod resolve(@Nullable String method) {
        return method != null ? (HttpMethod)mappings.get(method.toUpperCase()) : null;
    }

    public boolean matches(String method) {
        return this == resolve(method);
    }

    static {
        HttpMethod[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            HttpMethod httpMethod = var0[var2];
            mappings.put(httpMethod.name(), httpMethod);
        }

    }
}