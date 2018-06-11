package indi.pc.sc.base;

import lombok.Data;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.springframework.http.HttpMethod;
import org.apache.http.HttpHeaders;

import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 */
@Data
public class RequestTask {
    private String id;
    private HttpMethod method;
    private String url;
    private Map<String, Object> headers;
    private int timeout;
    private Map<String, Object> data;
    private Map<String, Object> attributes;
    private Method callbackMethod;
}
