//package indi.pc.sc.fetcher;
//
//
//import indi.pc.sc.base.RequestData;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.DnsResolver;
//import org.apache.http.conn.ssl.DefaultHostnameVerifier;
//import org.apache.http.impl.conn.SystemDefaultDnsResolver;
//import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
//import org.apache.http.impl.nio.client.HttpAsyncClients;
//import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
//import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
//import org.apache.http.impl.nio.reactor.IOReactorConfig;
//import org.apache.http.nio.conn.NHttpConnectionFactory;
//import org.apache.http.nio.conn.NoopIOSessionStrategy;
//import org.apache.http.nio.conn.SchemeIOSessionStrategy;
//import org.apache.http.nio.conn.ssl.SSLIOSessionStrategy;
//import org.apache.http.nio.reactor.ConnectingIOReactor;
//import org.apache.http.nio.reactor.IOReactorException;
//import org.apache.http.ssl.SSLContexts;
//import org.apache.http.util.EntityUtils;
//
//import org.springframework.stereotype.Component;
//import org.springframework.context.annotation.Scope;
//import org.springframework.http.HttpMethod;
//
//
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.Future;
//
//import indi.pc.sc.base.RequestTask;
//import indi.pc.sc.base.ResponseTask;
//import indi.pc.sc.base.TaskQueue;
//
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLContext;
//
///**
// *
// */
//@Component
//@Scope("prototype")
//public class HttpFetcher implements Runnable {
//    public CloseableHttpAsyncClient httpclient;
//
//    public void init() throws IOReactorException {
//        // TODO 默认配置
//        DnsResolver dnsResolver = new SystemDefaultDnsResolver() {
//            @Override
//            public InetAddress[] resolve(final String host) throws UnknownHostException {
//                if ("localhost".equalsIgnoreCase(host)) {
//                    return new InetAddress[] { InetAddress.getByAddress(new byte[] {127, 0, 0, 1}) };
//                } else {
//                    return super.resolve(host);
//                }
//            }
//        };
//
//        SSLContext sslcontext = SSLContexts.createSystemDefault();
//        HostnameVerifier hostnameVerifier = new DefaultHostnameVerifier();
//
//        // 访问策略
//        Registry<SchemeIOSessionStrategy> sessionStrategyRegistry = RegistryBuilder.<SchemeIOSessionStrategy>create()
//                .register("http", NoopIOSessionStrategy.INSTANCE)
//                .register("https", new SSLIOSessionStrategy(sslcontext, hostnameVerifier))
//                .build();
//        // IOReactor配置（连接池）
//        IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
//                .setIoThreadCount(Runtime.getRuntime().availableProcessors())
//                .setConnectTimeout(30000)
//                .setSoTimeout(30000)
//                .build();
//        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor(ioReactorConfig);
//        // 连接池配置（连接管理器）
//        PoolingNHttpClientConnectionManager connManager = new PoolingNHttpClientConnectionManager(ioReactor
//                , (NHttpConnectionFactory)null
//                , sessionStrategyRegistry
//                , dnsResolver);
//        connManager.setMaxTotal(100);
//        connManager.setDefaultMaxPerRoute(100);
//        // 默认请求配置
//        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectTimeout(50000)
//                .setSocketTimeout(50000)
//                .setConnectionRequestTimeout(1000)
//                .build();
//        httpclient = HttpAsyncClients.custom()
//                .setConnectionManager(connManager)
//                .setDefaultRequestConfig(requestConfig)
//                .build();
//    }
//
//    /**
//     *
//     * @throws Exception
//     */
//    @Override
//    public void run() {
//
//        try {
//            init();
//            try {
//                httpclient.start();
//                RequestTask requestTask = null;
//                while ((requestTask = TaskQueue.FetchQueue.take()) != null) {
//                    ResponseTask responseTask = doFetch(requestTask);
//                    TaskQueue.ProcessQueue.put(responseTask);
//                }
//            } catch (InterruptedException ex) {
//                ex.printStackTrace();
//            } finally {
//                httpclient.close();
//            }
//        } catch (IOReactorException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    /**
//     *
//     * @param requestTask
//     * @return
//     * @throws Exception
//     */
//    public ResponseTask fetchonce(RequestTask requestTask) {
//        try {
//            init();
//            try {
//                httpclient.start();
//                ResponseTask responseTask = doFetch(requestTask);
//                return responseTask;
//            } finally {
//                httpclient.close();
//            }
//        } catch (IOReactorException ex) {
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     *
//     * @param requestTask
//     * @return
//     * @throws Exception
//     */
//    private ResponseTask doFetch(RequestTask requestTask) {
//        HttpRequestBase trueRequest = null;
//        if (requestTask.getMethod() == HttpMethod.GET) {
//            trueRequest = buildGetRequest(requestTask);
//        } else if (requestTask.getMethod() == HttpMethod.POST) {
//            trueRequest = buildPostRequest(requestTask);
//        } else {
//            return null;
//        }
//        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
//                .setSocketTimeout(60000)
//                .setConnectTimeout(60000)
//                .build();
//        trueRequest.setConfig(requestConfig);
//        Future<HttpResponse> future = httpclient.execute(trueRequest, null);
//        try {
//            HttpResponse trueResponse = future.get();
//            System.out.println("ResponseTask: " + trueResponse.getStatusLine());
//            String content = EntityUtils.toString(trueResponse.getEntity());
//            ResponseTask responseTask = new ResponseTask();
//            responseTask.setContent(content);
//            return responseTask;
//        } catch (ExecutionException ex) {
//            ex.printStackTrace();
//        } catch (InterruptedException ex){
//            ex.printStackTrace();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//
//    public HttpRequestBase buildGetRequest(RequestTask requestTask) {
//        HttpGet trueRequest = null;
//        if (requestTask.getData().getType() == RequestData.JSON) {
//            // TODO 转换为JSON
//        } else if (requestTask.getData().getType() == RequestData.KV) {
//            // TODO 转换为键值对
//            //EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
//        } else if (requestTask.getData().getType() == RequestData.FILE) {
//            // 抛出错误
//        } else {
//            trueRequest = new HttpGet(requestTask.getUrl());
//        }
//        // trueRequest.setHeaders();
//        // trueRequest.setConfig();
//        return trueRequest;
//    }
//
//    public HttpRequestBase buildPostRequest(RequestTask requestTask) {
//        HttpPost trueRequest = new HttpPost(requestTask.getUrl());
//        if (requestTask.getData().getType() == RequestData.JSON) {
//            // TODO 转换为JSON
//        } else if (requestTask.getData().getType() == RequestData.KV) {
//            // TODO 转换为键值对
//            //EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
//        } else if (requestTask.getData().getType() == RequestData.FILE) {
//            // TODO
//        }
//        // trueRequest.setHeaders();
//        // trueRequest.setConfig();
//        return trueRequest;
//    }
//}
