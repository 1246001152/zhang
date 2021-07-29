package com.lr.zhang.util;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {
    @ExcelProperty({"aaa", "字符串标题"})
    private String str;
    @ExcelProperty({"aaa", "bbbb", "日期标题"})
    private String date;
    @ExcelProperty({"aaa", "bbbb", "数字标题"})
    private String doubleData;
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    private String ignore;
}