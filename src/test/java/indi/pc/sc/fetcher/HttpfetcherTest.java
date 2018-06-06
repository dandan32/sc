package indi.pc.sc.fetcher;

import indi.pc.sc.base.RequestTask;
import indi.pc.sc.base.ResponseTask;
import org.junit.Test;
import org.springframework.http.HttpMethod;

public class HttpfetcherTest {
    @Test
    public void fetchTest() throws Exception {
        RequestTask requestTask = new RequestTask();
        requestTask.setMethod(HttpMethod.GET);
        requestTask.setUrl("http://www.baidu.com");

        HttpFetcher fetcher = new HttpFetcher();
        ResponseTask responseTask = fetcher.fetchonce(requestTask);
        System.out.println(responseTask.getContent());
    }
}
