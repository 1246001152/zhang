package com.lr.zhang.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

// 有个很重要的点 ExpBudgetExcelDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class ExpBudgetExcelDataListener extends AnalysisEventListener<ExpBudgetData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpBudgetExcelDataListener.class);
    private static AtomicInteger rowIndex = new AtomicInteger(0);
    private static List<HeaderData> headerList = new ArrayList<>();
    private static List<ExpBudgetData> dataList = new ArrayList<>();
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;

    // 获取头信息
    public static List<HeaderData> getHeaders(){
        return headerList;
    }

    // 获取头信息
    public static List<ExpBudgetData> getDataList(){
        return dataList;
    }
    /**
     * 这个每一条数据解析都会来调用
     * @param data
     * @param context
     */
    @Override
    public void invoke(ExpBudgetData data, AnalysisContext context) {
//        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
          dataList.add(data);
    }
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        String[] headerCodeArr = {"proCode","proType","proName","expStartBgt"};
            if(rowIndex.get()==0){
                getHeader(headerCodeArr, headMap);
            }else{
                for (int i = 0; i < 4; i++) {
                    String head = headMap.get(i);
                    HeaderData headerData = headerList.get(i);
                    setChildren(head, headerCodeArr,i,headerData);
                }
            }
        rowIndex.getAndIncrement();
    }
    private void setChildren(String head, String[] headerCodeArr,int i, HeaderData headerData) {
        if(headerData.getLabel() !=null ){
            if(head!=null){
                HeaderData header =  new HeaderData();
                header.setId(UUID.randomUUID().toString());
                header.setLabel(head);
                header.setProp(headerCodeArr[i]);
                headerData.setProp(null);
                List<HeaderData> children = headerData.getChildren();
                if(children==null){
                    children= new ArrayList<>();
                }
                children.add(header);
                headerData.setChildren(children);
                return;
            }
        }else{
            int temp = i - 1;
            if(temp>0){
                headerData = headerList.get(temp);
                setChildren(head, headerCodeArr,i,headerData);
            }
        }
    }

    private void getHeader(String[] headerCodeArr,Map<Integer, String> headMap) {
        for (int i = 0; i < 4; i++) {
            if(headMap.get(i) !=null){
                HeaderData header =  new HeaderData();
                header.setId(UUID.randomUUID().toString());
                header.setLabel(headMap.get(i) );
                header.setProp(headerCodeArr[i]);
                headerList.add(header);
            }else{
                headerList.add(new HeaderData());
            }
        }
    }


    /**
     * 所有数据解析完成了 都会来调用
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

}