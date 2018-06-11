package indi.pc.sc.fetcher;

import indi.pc.sc.base.RequestTask;
import indi.pc.sc.base.ResponseTask;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Request;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.BufferingResponseListener;
import org.eclipse.jetty.client.util.MultiPartContentProvider;

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
//        request.param();
//        request.param();
        params(); // 参数
//        request.file();
//        MultiPartContentProvider multiPart = new MultiPartContentProvider();
//        multiPart.addFieldPart("field", new StringContentProvider("foo"), null);
//        multiPart.addFilePart("icon", "img.png", new PathContentProvider(Paths.get("/tmp/img.png")), null);
//        multiPart.close();
//        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
//                .method(HttpMethod.POST)
//                .content(multiPart)
//                .send();
        request.getParams();
        request.listener(requestListener);
        request.send(responseListener);
        //

    }

    private void params() {

    }

    private void multipart() {

    }

    class DefaultRequestListener extends Request.Listener.Adapter {

    }

    class DefaultResponseListener extends BufferingResponseListener {

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
