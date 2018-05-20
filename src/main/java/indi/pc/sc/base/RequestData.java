package indi.pc.sc.base;

import lombok.Data;

import java.util.Map;

@Data
public class RequestData {
    public static int JSON = 1;
    public static int KV = 2;
    public static int FILE = 3;
    private int type;
    private Map<String, String> data;
}
