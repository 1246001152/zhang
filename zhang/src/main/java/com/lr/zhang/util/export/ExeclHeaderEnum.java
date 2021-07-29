package com.lr.zhang.util.export;

import java.util.ArrayList;
import java.util.List;

public abstract class ExeclHeaderEnum {

    ExeclHeaderEnum(String code, String... text) {
        this.code = code;
        this.text = new ArrayList<>();
        for (String s : text) {
            this.text.add(s);
        }
    }

    private String code;
    private List<String> text;

    public String getCode() {
        return code;
    }

    public List<String> getText() {
        return text;
    }
}
