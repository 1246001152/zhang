package com.lr.zhang;

public enum HeaderEnum {

    Headerz("zzz","总头"),
    HeaderString("aaa","字符串"),
    HeaderDate("bbb","日期"),
    HeaderEnum("ccc","数字");
    HeaderEnum(String code, String text) {
        this.code = code;
        this.text = text;
    }

    private String code;
    private String text;

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
