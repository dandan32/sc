package indi.pc.sc.fetcher;

import indi.pc.sc.base.RequestTask;
import indi.pc.sc.base.ResponseTask;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.BufferingResponseListener;

public class JettyHttpFetcher {

    private HttpClient httpClient;

    public JettyHttpFetcher(HttpClient httpClient) {
        this.httpClient = httpClient;
    }



    public ResponseTask syncFetch(RequestTask requestTask) {
        //doFetch(requestTask);
        return null;
    }

    public void asyncFetch(RequestTask requestTask) {

    }

    public void doFetch(RequestTask requestTask,
                        Request.Listener.Adapter requestListener,
                        Response.Listener.Adapter responseListener) {
        // URL
        Request request = this.httpClient.newRequest(requestTask.getUrl());
        // method
        request.method(request.getMethod());
        // header
        // request.header();
        // User-Agent
        // request.agent();
        request.send(responseListener);


    }

    class CustomBufferingResponseListener extends BufferingResponseListener {

        @Override
        public void onComplete(Result result)
        {
            if (!result.isFailed())
            {
                result.getResponse().getHeaders();
                byte[] responseContent = getContent();
                // Your logic here
            }
        }
    }

}
