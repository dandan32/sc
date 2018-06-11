package indi.pc.sc;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.ssl.SslContextFactory;


public class test {

    public static void main(String[] args) {
        try {
            SslContextFactory sslContextFactory = new SslContextFactory();
            HttpClient httpClient = new HttpClient(sslContextFactory);
            httpClient.setFollowRedirects(false);

            httpClient.start();
            ContentResponse response = httpClient.GET("http://www.google.com");
            String content = response.getContentAsString();
            System.out.print(content);
            httpClient.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
