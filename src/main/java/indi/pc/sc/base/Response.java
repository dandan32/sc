package indi.pc.sc.base;

import lombok.Data;
import org.apache.http.HttpStatus;
import org.apache.http.HttpHeaders;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.jsoup.select.Elements;
import org.springframework.http.HttpMethod;
import org.jsoup.Jsoup;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.Map;

@Data
public class Response {
    private HttpStatus status;
    private HttpMethod method;
    private String url;
    private Map<String, Object> headers;

    private String encoding; // 编码
    private String content;
    private org.jsoup.nodes.Document doc; //jsoup
    private org.w3c.dom.Document dom; //xpath dom
    private XPath xPathInstance; // xpath instance
    private Object save; // object from request.

    public void setContent(String content) {
        this.content = content;
        org.jsoup.nodes.Document doc = Jsoup.parse(content);
        try {
            HtmlCleaner hc = new HtmlCleaner();
            TagNode tn = hc.clean(content);
            dom = new DomSerializer(new CleanerProperties()).createDOM(tn);
            xPathInstance = XPathFactory.newInstance().newXPath();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    public Object xpath(String xpathQuery) {
        try {
            Object result = xPathInstance.evaluate(xpathQuery, this.dom, XPathConstants.NODESET);
            return result;
        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Elements doc(String cssQuery) {
        return this.doc.select(cssQuery);
    }

}
