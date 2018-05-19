package indi.pc.sc.fetcher;

import indi.pc.sc.base.ResponseTask;
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

import indi.pc.sc.base.RequestTask;

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
                RequestTask requestTask = null;
                while ((requestTask = TaskQueue.FetchQueue.take()) != null) {
                    ResponseTask responseTask = doFetch(requestTask);
                    TaskQueue.ProcessQueue.put(responseTask);
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
     * @param requestTask
     * @return
     * @throws Exception
     */
    public ResponseTask fetchonce(RequestTask requestTask) throws Exception {
        try {
            httpclient.start();
            ResponseTask responseTask = doFetch(requestTask);
            return responseTask;
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
     * @param requestTask
     * @return
     * @throws Exception
     */
    private ResponseTask doFetch(RequestTask requestTask) throws Exception{
        HttpRequestBase trueRequest = null;
        if (requestTask.getMethod() == HttpMethod.GET) {
            trueRequest = buildGetRequest(requestTask);
        } else if (requestTask.getMethod() == HttpMethod.POST) {
            trueRequest = buildPostRequest(requestTask);
        } else {
            throw new Exception(String.format("NOT ALLOW METHOD %s", requestTask.getMethod()));
        }

        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(60000)
                .setConnectTimeout(60000)
                .build();
        trueRequest.setConfig(requestConfig);
        Future<HttpResponse> future = httpclient.execute(trueRequest, null);
        HttpResponse trueResponse = future.get();
        System.out.println("ResponseTask: " + trueResponse.getStatusLine());
        String content = EntityUtils.toString(trueResponse.getEntity());
        ResponseTask responseTask = new ResponseTask();
        responseTask.setContent(content);
        return responseTask;
    }

    public HttpRequestBase buildGetRequest(RequestTask requestTask) {
        HttpGet trueRequest = new HttpGet(requestTask.getUrl());
        return trueRequest;
    }

    public HttpRequestBase buildPostRequest(RequestTask requestTask) {
        HttpPost trueRequest = new HttpPost(requestTask.getUrl());
        return trueRequest;

    }

}
