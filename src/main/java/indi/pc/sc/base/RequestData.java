package indi.pc.sc.base;

import lombok.Data;

import java.util.Map;

@Data
public class RequestData {

    public enum DataType {
        JSON("JSON", 1), KV("http params",2), FILE("FILE", 3);

        private String name;
        private int index;

        DataType(String name, int index) {
            this.name = name;
            this.index = index;
        }
    }

    private DataType dataType;
    private Map<String, String> data;
}
