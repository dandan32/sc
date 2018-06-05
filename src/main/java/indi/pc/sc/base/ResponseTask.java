package indi.pc.sc.base;

import lombok.Data;
import org.apache.http.HttpStatus;
import org.apache.http.HttpHeaders;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.http.HttpMethod;
import org.jsoup.Jsoup;
import us.codecraft.xsoup.Xsoup;

import java.util.Map;

@Data
public class ResponseTask {
    private String id;
    private HttpStatus status;
    private HttpMethod method;
    private String url;
    private Map<String, Object> headers;

    private String encoding; // 编码
    private String content;
    private Document doc; //jsoup
    private Object save; // object from request.

    public void setContent(String content) {
        this.content = content;
        this.doc = Jsoup.parse(content);
    }

    public Elements xpath(String xpathQuery) {
        // TODO 学习下 Xsoup 的实现。
        return Xsoup.compile(xpathQuery).evaluate(this.doc).getElements();
    }

    public Elements select(String cssQuery) {
        return this.doc.select(cssQuery);
    }

}
