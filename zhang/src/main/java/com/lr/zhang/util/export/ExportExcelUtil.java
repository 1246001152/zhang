package com.lr.zhang.util.export;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ExportExcelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportExcelUtil.class);

        private ExportExcelUtil() {
        }

        private static String outFileSrc(String fileName) {
//            try {
//                String fileSrc = FilePathUtil.createNewTempFile(fileName+".xls","excel");
//                return FilePathUtil.realPath(fileSrc);
//            } catch (Exception e) {
//                LOGGER.getLog(ExportExcelUtil.class).error("",e);
//            }
            return null;
        }

        private static List<List<Object>> getDataList(List<?> list, List<String> fieldList) {
            List<String>  headList = fieldList.stream().filter(string -> !string.trim().isEmpty()).collect(Collectors.toList());
            return list.stream().map(e -> {
                Class<?> cls = e.getClass().getSuperclass();
                List<Object> valueList = new ArrayList<>();
                for (String name : headList) {
                    try {
                        Field field = cls.getDeclaredField(name);
                        field.setAccessible(true);
                        String text = null;
                        if(field.get(e)!=null) {
                            text = field.get(e).toString();
                        }
                        valueList.add(text);
                    } catch (NoSuchFieldException | IllegalAccessException noSuchFieldException) {
                        noSuchFieldException.printStackTrace();
                    }
                }
                return valueList;
            }).collect(Collectors.toList());
        }

        /**
         * 支出预算
         * @param filePath  指定文件地址
         * @param fieldList 表头对应的类字段
         * @param fieldList 内容数据
         * @return
         */
        public static String  exportExcel(String filePath ,List<String> fieldList, List<?> list ,HeadTextImpl headImpl) {
            String fileName= filePath.substring(filePath.indexOf("\\")==-1?filePath.indexOf("/"):filePath.indexOf("\\"));
            List<List<String>> headList = new ArrayList<List<String>>();
            for (int i = 0; i < fieldList.size(); i++) {
                List<String> head = headImpl.getHeadText(fieldList.get(i));
                if(head!=null){
                    headList.add(head);
                }
            }
            // 内容数据
            List<List<Object>> data = getDataList(list, fieldList);
            EasyExcel.write(filePath).head(headList)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet(fileName).doWrite(data);
            return filePath;
        }
}
