package indi.pc.sc.fetcher;

import indi.pc.sc.base.Response;
import indi.pc.sc.base.TaskQueue;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpMethod;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import indi.pc.sc.base.Request;

/**
 * Hello world!
 *
 */
public class HttpFetcher implements Runnable {
    CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();

    /**
     *
     * @throws Exception
     */
    public void run() {
        try {
            try {
                httpclient.start();
                Request request = null;
                while ((request = TaskQueue.FetchQueue.take()) != null) {
                    Response response = doFetch(request);
                    TaskQueue.ProcessQueue.put(response);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (ExecutionException ex) {
                ex.printStackTrace();
            }finally {
                httpclient.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    public Response fetchonce(Request request) throws Exception {
        try {
            httpclient.start();
            Response response = doFetch(request);
            return response;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return null;
        } catch (ExecutionException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            httpclient.close();
        }

    }

    /**
     *
     * @param request
     * @return
     * @throws Exception
     */
    private Response doFetch(Request request) throws Exception{
        HttpRequestBase trueRequest = null;
        if (request.getMethod() == HttpMethod.GET) {
            trueRequest = buildGetRequest(request);
        } else if (request.getMethod() == HttpMethod.POST) {
            trueRequest = buildPostRequest(request);
        } else {
            throw new Exception(String.format("NOT ALLOW METHOD %s", request.getMethod()));
        }

        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(60000)
                .setConnectTimeout(60000)
                .build();
        trueRequest.setConfig(requestConfig);
        Future<HttpResponse> future = httpclient.execute(trueRequest, null);
        HttpResponse trueResponse = future.get();
        System.out.println("Response: " + trueResponse.getStatusLine());
        String content = EntityUtils.toString(trueResponse.getEntity());
        Response response = new Response();
        response.setContent(content);
        return response;
    }

    public HttpRequestBase buildGetRequest(Request request) {
        HttpGet trueRequest = new HttpGet(request.getUrl());
        return trueRequest;
    }

    public HttpRequestBase buildPostRequest(Request request) {
        HttpPost trueRequest = new HttpPost(request.getUrl());
        return trueRequest;

    }

}
