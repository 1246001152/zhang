package com.lr.zhang.export;

import com.lr.zhang.util.export.ExportExcelUtil;
import com.lr.zhang.util.export.HeadTextImpl;

import java.util.List;

public class ExportTest {

    public static void main(String[] args) {
        String filePath = null;
        List<String> fieldList = null;
        List<?> list = null;
        HeadTextImpl headImpl = null;
        String fileUrl = ExportExcelUtil.exportExcel(filePath, fieldList, list, headImpl);
    }
}
