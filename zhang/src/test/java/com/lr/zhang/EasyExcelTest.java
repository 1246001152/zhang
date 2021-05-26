package com.lr.zhang;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.lr.zhang.util.ExpBudgetData;
import com.lr.zhang.util.ExpBudgetExcelDataListener;
import com.lr.zhang.util.HeaderData;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class EasyExcelTest {

    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = "C:\\Users\\12460\\Documents\\WeChat Files\\wxid_2aelxl8m3f1w21\\FileStorage\\File\\2021-05\\2019决算总表（正式上报表）.xls";
//         这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, ExpBudgetData.class, new ExpBudgetExcelDataListener()).sheet().headRowNumber(2).doRead();
        List<HeaderData> headers = ExpBudgetExcelDataListener.getHeaders();
        List<ExpBudgetData> dataList = ExpBudgetExcelDataListener.getDataList();
//
        System.out.println(JSONArray.parseArray(JSON.toJSONString(headers)));
//        System.out.println(JSONArray.parseArray(JSON.toJSONString(dataList)));
//        System.out.println(headers);
        System.out.println(dataList);


        // 写法2：
//        fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//        excelReader.read(readSheet);
//        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//        excelReader.finish();
//        Workbook wb = createWorkbook(fileName);
//        Sheet sheet = wb.getSheetAt(0);
//        Row row = sheet.getRow(0);
//        int num = row.getPhysicalNumberOfCells();
//        System.out.println(num);
//        FormulaEvaluator formulaEvaluator =new HSSFFormulaEvaluator((HSSFWorkbook) wb);
//
//        Cell cell = row.getCell(0);
//        String cellFormula = cell.getCellFormula();



    }

    public static XSSFWorkbook createWorkbook(String filePath){
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        try (InputStream is = new FileInputStream(filePath)){
            System.out.println("filePath====>>>>>"+filePath);
            if(".xls".equals(extString)){
                return new XSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return new XSSFWorkbook(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
