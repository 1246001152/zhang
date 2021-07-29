package com.zhang.craw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.UUID;

public class GenSQLTest {

    public static void main(String[] args) throws Exception {
        String path = "E:\\zhang\\demo\\Test.json";
        // 读取
        String s = readJsonFile(path);
        JSONArray JsonArr = JSON.parseArray(s);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < JsonArr.size(); i++) {
            JSONObject json = JsonArr.getJSONObject(i);
//            System.out.println(json);
            String sql = getInsertSQL(json);
            System.out.println(sql);
            sb.append(sql);
            sb.append("\r\n");
        }

        File file = new File("sql.txt");
        if (!file.exists())//判断文件是否存在，若不存在则新建
        {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);//实例化FileOutputStream
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");//将字符流转换为字节流
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);//创建字符缓冲输出流对象
        bufferedWriter.write(sb.toString());//将格式化的jsonarray字符串写入文件
        bufferedWriter.flush();//清空缓冲区，强制输出数据
        bufferedWriter.close();//关闭输出流
        System.out.println("读写完成");
//          JSONArray arr = null;
//        arr = json.getJSONArray("直达资金标识");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("资金申请结束年");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否重点项目监控");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("资金申请起始年");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("重点项目事前绩效评估结论");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("预算单位");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("上年部门绩效自评结果");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否附件");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否提前下达");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否科研项目");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("一级项目编码名称");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("股权管理");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("预算单位主管部门");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("二级项目主管部门");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("上年财政绩效评价结果");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("项目类型");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("二级项目编码名称");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("三级项目名称");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("三保项目分类");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否为政府购买服务项目");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("项目滚动管理分类");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否追踪");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否补贴到人到企业");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否绩效");//===================
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("分配状态");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否直达资金的地方对应安排");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否基建");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否扶贫监控项目");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("二级项目管理处室");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否三保");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("项目执行状态");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("是否补助企业（非预算单位）项目");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("扶贫标识");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("额度预算来源");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("部门事前绩效评估结论");
//        System.out.println(arr.getJSONArray(0).get(1));
//        arr = json.getJSONArray("项目期限");
//        System.out.println(arr.getJSONArray(0).get(1));
    }

    private static String getInsertSQL(JSONObject json) {
//        JSONObject json = jsonArr.getJSONObject(0);
//        System.out.println(json);
//        Set<Map.Entry<String, Object>> entries = json.entrySet();
//        for (Map.Entry<String, Object> entry:entries) {
//            String key = entry.getKey();
//            Object val = entry.getValue();
//            System.out.println(key+"==="+val);
//            System.out.println("arr = json.getJSONArray(""+key+"");");
//            System.out.println("System.out.println(arr.getJSONArray(0).get(1));");
//             System.out.println("getValue(json, "" + key + "");");
//        }
        Object direct_funds = getValue(json, "直达资金标识");
        Object APPLY_END_YEAR = getValue(json, "资金申请结束年");
        Object IS_KEY_MONITOR = getValue(json, "是否重点项目监控");
        Object APPLY_START_YEAR = getValue(json, "资金申请起始年");
//         Object  = getValue(json, "重点项目事前绩效评估结论");
        Object AGENCY_CODE = getValue(json, "预算单位");
//         Object  = getValue(json, "上年部门绩效自评结果");
        Object IS_ANNEX = getValue(json, "是否附件");
        Object IS_EARLY_RELEASE = getValue(json, "是否提前下达");
        Object IS_SCI_PRO = getValue(json, "是否科研项目");
        Object PRO_SORT_CODE = getValue(json, "一级项目编码名称");
        Object stock_right = getValue(json, "股权管理");
        Object AGENCY_DEPT_CODE = getValue(json, "预算单位主管部门");
        Object DEPT_CODE = getValue(json, "二级项目主管部门");
//         Object  = getValue(json, "上年财政绩效评价结果");
        Object PRO_CAT_CODE = getValue(json, "项目类型");
        Object PRO_CODE = getValue(json, "二级项目编码名称");
        Object PRO_NAME = getValue(json, "三级项目名称");
        Object ENSURE_CAT_CODE = getValue(json, "三保项目分类");
        Object IS_GOV_PUR_SERV = getValue(json, "是否为政府购买服务项目");
        Object pro_roll_cat_code = getValue(json, "项目滚动管理分类");
        Object IS_TRACK = getValue(json, "是否追踪");
        Object SUB_SIDY = getValue(json, "是否补贴到人到企业");
        Object IS_PERF_KPI = getValue(json, "是否绩效");
        Object audit_status = getValue(json, "分配状态");
        Object zdzj_jqpt = getValue(json, "是否直达资金的地方对应安排");
        Object IS_CAPTIAL_CONS_PRO = getValue(json, "是否基建");
        Object IS_POVERTY = getValue(json, "是否扶贫监控项目");
        Object mof_dep_code = getValue(json, "二级项目管理处室");
        Object IS_THREE_ENSURE = getValue(json, "是否三保");
        Object pro_exec_status = getValue(json, "项目执行状态");
        Object IS_SUB_ENT = getValue(json, "是否补助企业（非预算单位）项目");
        Object QKSF_BS = getValue(json, "扶贫标识");
        Object bgt_source_type = getValue(json, "额度预算来源");
//         Object  = getValue(json, "部门事前绩效评估结论");
        Object PRO_TERM = getValue(json, "项目期限");

        String sql = "insert into PM_PROJECTINFO (PRO_ID, direct_funds,APPLY_END_YEAR,IS_KEY_MONITOR,APPLY_START_YEAR," +
                "AGENCY_CODE,IS_ANNEX,IS_EARLY_RELEASE,IS_SCI_PRO,PRO_SORT_CODE,stock_right,AGENCY_DEPT_CODE," +
                "DEPT_CODE,PRO_CAT_CODE,PRO_CODE,PRO_NAME,ENSURE_CAT_CODE,IS_GOV_PUR_SERV,pro_roll_cat_code," +
                "IS_TRACK,SUB_SIDY,IS_PERF_KPI,audit_status,zdzj_jqpt,IS_CAPTIAL_CONS_PRO,IS_POVERTY,mof_dep_code," +
                "IS_THREE_ENSURE,pro_exec_status,IS_SUB_ENT,QKSF_BS,bgt_source_type,PRO_TERM ) values " +
                "('" + UUID.randomUUID().toString() + "','" + direct_funds + "','" + APPLY_END_YEAR + "','" + IS_KEY_MONITOR + "','" + APPLY_START_YEAR + "','" + AGENCY_CODE + "'," +
                "'" + IS_ANNEX + "','" + IS_EARLY_RELEASE + "','" + IS_SCI_PRO + "','" + PRO_SORT_CODE + "','" + stock_right + "','" + AGENCY_DEPT_CODE + "'," +
                "'" + DEPT_CODE + "','" + PRO_CAT_CODE + "','" + PRO_CODE + "','" + PRO_NAME + "','" + ENSURE_CAT_CODE + "','" + IS_GOV_PUR_SERV + "','" + pro_roll_cat_code + "'" +
                "'" + IS_TRACK + "','" + SUB_SIDY + "','" + IS_PERF_KPI + "','" + audit_status + "','" + zdzj_jqpt + "','" + IS_CAPTIAL_CONS_PRO + "','" + IS_POVERTY + "','" + mof_dep_code + "'" +
                "'" + IS_THREE_ENSURE + "','" + pro_exec_status + "','" + IS_SUB_ENT + "','" + QKSF_BS + "','" + bgt_source_type + "','" + PRO_TERM + "');";

        return sql;
    }

    public static Object getValue(JSONObject json, String key) {
        JSONArray arr = json.getJSONArray(key);
        if (arr.size() > 0) {
//            System.out.println(arr.getJSONArray(0).get(1));
            return arr.getJSONArray(0).get(1);
        }
        return null;
    }

    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
