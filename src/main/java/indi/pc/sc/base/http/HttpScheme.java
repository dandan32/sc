package indi.pc.sc.base.http;

public enum HttpScheme {
    HTTP("http"),
    HTTPS("https"),
    WS("ws"),
    WSS("wss");

    private final String value;

    private HttpScheme(String s) {
        this.value = s;
    }


    public boolean is(String s) {
        return s != null && this.value.equalsIgnoreCase(s);
    }

    public String value() {
        return this.value;
    }

    public String toString() {
        return this.value;
    }
}
