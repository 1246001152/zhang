package com.lr.zhang.util;

import java.util.List;

public class HeaderData {

    private String id ;
    // 表头
    private String label;
    // 字段名
    private String prop;

    private List<HeaderData> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public List<HeaderData> getChildren() {
        return children;
    }

    public void setChildren(List<HeaderData> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "HeaderData{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", prop='" + prop + '\'' +
                ", children=" + children +
                '}';
    }
}
