package com.lr.zhang;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.lr.zhang.util.DemoData;
import org.joda.time.DateTime;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExprotDataTest {

    @Test
    public void simpleWrite() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "D:\\test\\car\\file\\text\\2021_06\\" + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
//        String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName).head(head()).registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()).sheet("模板").doWrite(data());

    }

    private List data() {
        List<DemoData> list = new ArrayList<>();
        DemoData demoData = new DemoData();
        for (int i = 0; i < 10; i++) {
            demoData.setDate(new DateTime().toString("yyyy-MM-dd"));
            demoData.setStr("str1" + i);
            demoData.setDoubleData("12" + i);
            list.add(demoData);
        }
        return list;
    }

    /**
     * 动态头，实时生成头写入
     * <p>
     * 思路是这样子的，先创建List<String>头格式的sheet仅仅写入头,然后通过table 不写入头的方式 去写入数据
     *
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 然后写入table即可
     */
    @Test
    public void dynamicHeadWrite() {
        String fileName = "dynamicHeadWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName)
                // 这里放入动态头
                .head(head()).sheet("模板")
                // 当然这里数据也可以用 List<List<String>> 去传入
                .doWrite(data());
    }

    private List<List<String>> head() {
        String[] arr = {"aaa", "bbb", "ccc"};
        String[] tarr = {"bbb", "ccc"};
        List<List<String>> list = new ArrayList<List<String>>();
        for (int i = 0; i < arr.length; i++) {
            List<String> head0 = new ArrayList<String>();
            if (Arrays.asList(tarr).contains(arr[i])) {
                setText(head0, "zzz");
            }
            setText(head0, arr[i]);
            list.add(head0);
        }
//        List<String> head0 = new ArrayList<String>();
//        setText(head0, "aaa");
//        List<String> head1 = new ArrayList<String>();
//        setText(head1, "aaa");
//        setText(head1, "bbb");
//        List<String> head2 = new ArrayList<String>();
//        setText(head2, "aaa");
//        setText(head2, "ccc");
//        list.add(head0);
//        list.add(head1);
//        list.add(head2);
        return list;
    }

    private void setText(List<String> head0, String code) {
        HeaderEnum[] values = HeaderEnum.values();
        for (HeaderEnum value : values) {
            if (code.equals(value.getCode())) {
                head0.add(value.getText());
            }
        }
    }
}
