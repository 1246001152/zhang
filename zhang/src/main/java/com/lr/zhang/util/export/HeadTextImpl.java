package com.lr.zhang.util.export;

import java.util.List;

public interface HeadTextImpl {
    /**
     * #==================Enum=========================
     * public enum HeaderEnum {
     * YEAR("year","支出预算数","财政年度");
     * HeaderEnum(String code, String... text) {
     * this.code = code;
     * this.text = new ArrayList<>();
     * for (String s:text) {
     * this.text.add(s);
     * }
     * }
     * <p>
     * private String code;
     * private List<String> text;
     * <p>
     * public String getCode() {
     * return code;
     * }
     * public List<String> getText() {
     * return text;
     * }
     * }
     * #===================impl========================
     * impl{
     * HeaderEnum[] values =  HeaderEnum.values();
     * for (HeaderEnum value:values) {
     * if(code.equals(value.getCode())){
     * return value.getText();
     * }
     * }
     * return null;
     * }
     *
     * @param code
     * @return
     */
    List<String> getHeadText(String code);

}
