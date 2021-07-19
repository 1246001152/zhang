package com.zhang.craw;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Crawing {
    public static void main(String[] args) throws Exception {
        // selenium
        WebDriver driver = getWebDriver();
        driver.get("http://10.120.93.131:7001");
        login(driver);
//        ma(driver);
        xm(driver);


    }

    private static void xm(WebDriver driver) throws Exception {
        WebElement ys = driver.findElement(new By.ByXPath("//*[@id=\"column_list\"]/li[4]"));
        ys.click();
        WebElement tdTagYs = driver.findElement(new By.ByXPath("//*[@id=\"column_list\"]/li[4]/ul/li[4]/a"));
        tdTagYs.click();
//        WebElement sanji = driver.findElement(new By.ByXPath("/html/body/div[1]/div[2]/div[3]/div/div/ul/li[4]/ul/li[4]/ul/li[4]/a/span"));
//        sanji.click();
        // 开启开发者模式，方便观察请求
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.F12).perform();

//        driver.navigate().refresh();
//        String currentUrl = driver.getCurrentUrl();
////        System.out.println(currentUrl);
//        String[] split = currentUrl.split("=");
//        String tokenid = split[1];
//        System.out.println(tokenid);
//        driver.get("http://10.120.93.131:7001/pdm/prodetail/query.page?mouldid=E7F5F6AA70734D6B982AFAA6E79D47C1&vchtypeid=C17515B55628A3B4C067DD54D893E9FF&mainmenu=CCF75F693DE759F437440BABF59395CE&submenu=BCBD008EDC634AF64F7E9AD0AC629CCD&appid=pdm&settingyear=2021&menuName=%E7%89%B9%E5%AE%9A%E7%9B%AE%E6%A0%87%E7%B1%BB%E4%B8%89%E7%BA%A7%E9%A1%B9%E7%9B%AE%E9%A2%84%E7%AE%97%E7%BC%96%E5%88%B6%E6%9F%A5%E8%AF%A2&menuId=BCBD008EDC634AF64F7E9AD0AC629CCD&year=2021&tokenid="+tokenid);
//        Thread.sleep(1000);
        // 切换新页面
//        Set<String> window = driver.getWindowHandles();
//        Iterator<String> it = window.iterator();
//        it.next();
//        driver.switchTo().window( it.next());
//        WebElement select = driver.findElement(new By.ByXPath("//*[@id=\"paginationtbl\"]/tbody/tr/td[3]/a/select"));
//        select.sendKeys("全部");


//        Thread.sleep(5000);
//      获取多选框
//        List<WebElement> boxList = driver.findElements(new By.ByCssSelector("input[type=checkbox]"));
//        System.out.println(boxList.size());
//        boxList.get(1).click();
//        WebElement btn = driver.findElement(new By.ByXPath("//*[@id=\"查看项目明细\"]"));
//        btn.click();
//        Thread.sleep(1000);

//
        String path = "C:\\Users\\Administrator\\Desktop\\guid.txt";
        // 读取
        String s = readJsonFile(path);
        JSONArray JsonArr = JSON.parseArray(s);
        JSONArray guids = JsonArr.getJSONObject(0).getJSONArray("guids");

        File file=new File("项目详情json.json");
        if(!file.exists())//判断文件是否存在，若不存在则新建
        {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream=new FileOutputStream(file);//实例化FileOutputStream
        OutputStreamWriter outputStreamWriter=new OutputStreamWriter(fileOutputStream,"utf-8");//将字符流转换为字节流
        BufferedWriter bufferedWriter= new BufferedWriter(outputStreamWriter);//创建字符缓冲输出流对象
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i< guids.size() ;i++){
           String guid = (String)guids.getJSONObject(0).get("guid");
           JSONObject json = getJson(driver, guid);
//           System.out.println(json);
           jsonArray.add(json);
            System.out.println("===================================================="+i);
        }
        String jsonString=jsonArray.toString();
        bufferedWriter.write(jsonString);//将格式化的jsonarray字符串写入文件
        bufferedWriter.flush();//清空缓冲区，强制输出数据
        bufferedWriter.close();//关闭输出流
        System.out.println("读写完成");
    }
    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
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

    public static JSONObject getJson(WebDriver driver,String guid) throws InterruptedException {
        driver.navigate().refresh();
        String currentUrl = driver.getCurrentUrl();
//        System.out.println(currentUrl); ***********
        String[] split = currentUrl.split("=");
        String tokenid = split[1];
//        System.out.println(tokenid);
        JavascriptExecutor jse = (JavascriptExecutor) driver ;
        String script2 = "const input1 = document.createElement('input');\n" +
                "input1.name='method'; input1.value='getProDetailData';\n" +
                "const input2 = document.createElement('input');\n" +
//                                                      [{"guid": "D504B1646682278331906474164BFF7A", "pagetype": "detailinfo", "vchtypeid": "C17515B55628A3B4C067DD54D893E9FF", "adjstatus": "0", "tableid": "editform", "submenu": "BCBD008EDC634AF64F7E9AD0AC629CCD"}]
                "input2.name='paramjson'; input2.value='[{\"guid\": "+guid+", \"pagetype\": \"detailinfo\", \"vchtypeid\": \"C17515B55628A3B4C067DD54D893E9FF\", \"adjstatus\": \"0\", \"tableid\": \"editform\", \"submenu\": \"BCBD008EDC634AF64F7E9AD0AC629CCD\"}]';\n" +
                "const form = document.createElement('form');\n" +
                "form.appendChild(input1);\n" +
                "form.appendChild(input2);\n" +
                "form.method = 'post';\n" +
                "form.action = '%s';\n" +
                "document.body.appendChild(form);\n" +
                "form.submit();";
        // post
        String url2="http://10.120.93.131:7001/pdm/prodetail/detailinfo/detailinfo_prodetailService.rcp?"+System.currentTimeMillis()+"&tokenid="+tokenid+"&year=2021";
        jse.executeScript(String.format(script2, url2));
//        Thread.sleep(1000); // ==========
        WebElement text2 = driver.findElement(new By.ByXPath("/html"));
//        System.out.println(text2.getText());

        JSONObject jsonObject2 = JSONObject.parseObject(text2.getText());
//            System.err.println(jsonObject2);
//==========================================
        String script1 = "const input1 = document.createElement('input');\n" +
                "input1.name='serverid'; input1.value='buscommon.ui.editform';\n" +
                "const input2 = document.createElement('input');\n" +
                "input2.name='method'; input2.value='getMapperValue';\n" +
                "const input3 = document.createElement('input');\n" +
                "input3.name='paramjson'; \n"+
//                "input3.value='[{\"tablecode\": \"PDM_T_PRODETAILINFOFLOW\", \"data\": [{\"qksfbs\": \"B861885433B291095E923249C4346CDF\", \"auditor\": \"5A1596A1E16D5BBF0427FB6025567335\", \"vcol19\": \"0.00000\", \"creater\": \"DC80B7D12F37A00FC3A7302DE874B6E7\", \"vcol18\": \"未评价\", \"vcol17\": \"362286E787F3A26DBB31BA936221AED6\", \"vchtypeid\": \"C17515B55628A3B4C067DD54D893E9FF\", \"vcol12\": \"2\", \"vcol15\": \"2\", \"vcol16\": \"0.00000\", \"vcol13\": \"2\", \"vcol14\": \"2\", \"province\": \"140423\", \"year\": 2021, \"dept\": \"E8A1C2FD394CE6F04A55DFF723D25206\", \"busiorgguid\": \"30F96B02BB8C99ED8D9B1EEE8A6DF5A6\", \"isperform\": null, \"fromguid\": null, \"vcol8\": \"3C48C831BB58187E02783BD090E902BF\", \"vcol9\": \"8022574BA2D57813D9248AA1A5D5E7E3\", \"projectkind\": \"F17DB8CE\", \"vcol6\": \"3C48C831BB58187E02783BD090E902BF\", \"orgguid\": \"324D0CE80C4172ADDF0DF049BB27584C\", \"vcol5\": \"2\", \"vcol2\": \"04E900BDF7B9721C0291AA249D71035D\", \"vcol3\": \"2\", \"sfsbxm\": \"CA31D8C5E11FBA376C9A7E6B9E9CED6F\", \"busidept\": \"E8A1C2FD394CE6F04A55DFF723D25206\", \"subsidy\": \"2A8E4BB9350BC6D6CF3D4FEA35D82312\", \"finintorgguid\": \"30F96B02BB8C99ED8D9B1EEE8A6DF5A6\", \"promaincode\": \"14042321TD21010900473\", \"vcol30\": null, \"promaininfo\": \"D26EAE5E3D39522659899B872672483C\", \"isattach\": \"1\", \"vcol35\": \"A4A12751403994044F6CD612C6F25D17\", \"vcol32\": \"D6CBAFA565127311DAD37308CDF189D1\", \"vcol31\": \"7B2769F10EC85BE5DCF152BD257B6500\", \"leadername\": \"路军\", \"vcol34\": \"0\", \"vcol33\": \"0\", \"fundstartyear\": \"2021\", \"isconstruction\": \"2\", \"fundendyear\": \"2023\", \"leaderphone\": \"7222575\", \"sbxmfl\": \"02114AoqV7rF2\", \"ncol2\": 0, \"ncol3\": 0, \"directfunds\": \"19B17B2EEE78D91166EF186F203630B4\", \"billcode\": \"14042321TD31010000509\", \"proname\": \"长期代课教师工资\", \"wfid\": \"C23C673B29D45AD869208E10B107B7D5\", \"vcol27\": \"本项目由于涉及人员均是以前历史遗留下来的相对固定人员，主管部门教育局内部由人事股专门负责管理所有人员的人事档案，并定期核查。学校是用人单位，负责实际管理和考核。如有人员变动情况，当月调整，各单位会计具体执行。\", \"vcol26\": \"为有效解决学校教师短缺问题，保障学校正常运转。根据襄联办发【2018】1号会议纪要文件，设立本项目。\", \"vcol25\": \"根据襄联办发【2018】1号会议纪要文件。\", \"vcol24\": \"5628BBCE1404761106C89A9A89F6B819\", \"vcol23\": \"8341E44D19D27727EF20318A6C0211FC\", \"vcol22\": null, \"vcol21\": \"未评价\", \"vcol20\": \"362286E787F3A26DBB31BA936221AED6\", \"guid\": \"324D0CE80C4172ADDF0DF049BB27584C\", \"zdzjjqpt\": \"9C36CC87EE5C76CFC932541BDF50CA1C\", \"lastupdatetime\": \"20210312171024681\", \"vcol28\": \"年初计财股向学校和局人事股统计各校实际人数，按照预算批复向财政申请拨付资金。各校严格考核人员到岗情况，按照实际工作在职情况按月发放工资。年底按照教师考核管理办法对教师进行考核。\", \"vcol29\": null, \"agencyguid\": \"9D3A40A7C656D9F12C7DBF61F03BBE99\", \"billno\": null, \"prodesc\": \"为有效解决学校教师短缺问题，保障学校正常运转。根据襄联办发【2018】1号会议纪要文件，设立本项目。项目受益时间：2021年1月1日至2021年12月31日<br>B.项目受益人群：全县所有学校\"}], \"submenu\": \"BCBD008EDC634AF64F7E9AD0AC629CCD\"}]'; \n"+
                "input3.value='[{\"tablecode\": \"PDM_T_PRODETAILINFOFLOW\", \"data\": ["+jsonObject2.get("editformdata")+"], \"submenu\": \"BCBD008EDC634AF64F7E9AD0AC629CCD\"}]'; \n"+
                "const form = document.createElement('form');\n" +
                "form.appendChild(input1);\n" +
                "form.appendChild(input2);\n" +
                "form.appendChild(input3);\n" +
                "form.method = 'post';\n" +
                "form.action = '%s';\n" +
                "document.body.appendChild(form);\n" +
                "form.submit();";
        // post
        String url1="http://10.120.93.131:7001/pdm/prodetail/detailinfo/webservice.rcp?"+System.currentTimeMillis()+"&tokenid="+tokenid+"&year=2021";
        jse.executeScript(String.format(script1, url1));
//        Thread.sleep(1000); // ======
        WebElement text1 = driver.findElement(new By.ByXPath("/html"));
//        System.out.println(text1.getText());

        JSONObject jsonObject1 = JSONObject.parseObject(text1.getText());
//            System.err.println(jsonObject1);
//        Thread.sleep(1000); // =====
        ///////////////////////////////////////

//        System.out.println("====================================================");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("一级项目编码名称",jsonObject1.get("projectkind"));
        jsonObject.put("二级项目编码名称",jsonObject1.get("promaincode"));
        jsonObject.put("二级项目管理处室",jsonObject1.get("busiorgguid"));
        jsonObject.put("二级项目主管部门",jsonObject1.get("dept"));
        jsonObject.put("预算单位主管部门",jsonObject1.get("busidept"));
        jsonObject.put("预算单位",jsonObject1.get("agencyguid"));
        jsonObject.put("三级项目名称",jsonObject1.get("promaininfo"));
        jsonObject.put("项目滚动管理分类",jsonObject1.get("vcol23"));
        jsonObject.put("资金申请起始年",jsonObject1.get("fundstartyear"));
        jsonObject.put("资金申请结束年",jsonObject1.get("fundendyear"));
        jsonObject.put("项目期限",jsonObject1.get("vcol9"));
        jsonObject.put("发改委基本建设项目代码.",jsonObject1.get(""));
        jsonObject.put("负责人姓名",jsonObject2.get("leadername"));
        jsonObject.put("负责人电话",jsonObject2.get("leaderphone"));
        jsonObject.put("分配状态",jsonObject1.get("vcol2"));
        jsonObject.put("是否补助企业（非预算单位）项目",jsonObject1.get("vcol12"));
        jsonObject.put("是否追踪",jsonObject1.get("vcol13"));
        jsonObject.put("是否基建",jsonObject1.get("isconstruction"));
        jsonObject.put("是否科研项目",jsonObject1.get("vcol14"));
        jsonObject.put("是否绩效",jsonObject1.get("isperform"));
        jsonObject.put("是否为政府购买服务项目",jsonObject1.get("vcol5"));
        jsonObject.put("是否扶贫监控项目",jsonObject1.get("vcol15"));
        jsonObject.put("是否重点项目监控",jsonObject1.get("vcol3"));
        jsonObject.put("项目执行状态",jsonObject1.get("vcol24"));
        jsonObject.put("是否附件",jsonObject1.get("isattach"));
        jsonObject.put("是否补贴到人到企业",jsonObject1.get("subsidy"));
        jsonObject.put("扶贫标识",jsonObject1.get("qksfbs"));
        jsonObject.put("是否直达资金的地方对应安排",jsonObject1.get("zdzjjqpt"));
        jsonObject.put("直达资金标识",jsonObject1.get("directfunds"));
        jsonObject.put("是否三保",jsonObject1.get("sfsbxm"));
        jsonObject.put("三保项目分类",jsonObject1.get("sbxmfl"));
        jsonObject.put("项目类型",jsonObject1.get("vcol31"));
        jsonObject.put("股权管理",jsonObject1.get("vcol32"));
        jsonObject.put("是否提前下达",jsonObject1.get("vcol33"));
        jsonObject.put("是否基数",jsonObject1.get("vcol134"));
        jsonObject.put("额度预算来源",jsonObject1.get("vcol35"));
        jsonObject.put("项目概况",jsonObject2.get("prodesc"));
        jsonObject.put("立项依据",jsonObject2.get("vcol25"));
        jsonObject.put("项目设立的必要性",jsonObject2.get("vcol26"));
        jsonObject.put("保证项目实施的制度和措施",jsonObject2.get("vcol27"));
        jsonObject.put("项目实施计划",jsonObject2.get("vcol28"));
        jsonObject.put("部门事前绩效评估分数",jsonObject2.get("ncol2"));
        jsonObject.put("部门事前绩效评估结论",jsonObject1.get("vcol6"));
        jsonObject.put("重点项目事前绩效评估分数",jsonObject2.get("ncol3"));
        jsonObject.put("重点项目事前绩效评估结论",jsonObject1.get("vcol8"));
        jsonObject.put("上年部门绩效自评分数",jsonObject2.get("vcol16"));
        jsonObject.put("上年部门绩效自评结果",jsonObject1.get("vcol17"));
        jsonObject.put("上年部门绩效自评结论",jsonObject2.get("vcol18"));
        jsonObject.put("上年财政绩效评价分数",jsonObject2.get("vcol19"));
        jsonObject.put("上年财政绩效评价结果",jsonObject1.get("vcol20"));
        jsonObject.put("上年财政绩效评价结论",jsonObject2.get("vcol21"));
        jsonObject.put("年度绩效目标",jsonObject1.get("vcol30"));
        jsonObject.put("中期绩效目标",jsonObject1.get("vcol29"));
        return jsonObject;
    }

    private static void ma(WebDriver driver) throws InterruptedException {
        WebElement ys = driver.findElement(new By.ByXPath("//*[@id=\"column_list\"]/li[5]"));
        ys.click();
        WebElement tdTagYs = driver.findElement(new By.ByXPath("//*[@id=\"column_list\"]/li[5]/ul/li[5]"));
        tdTagYs.click();
        WebElement aa = driver.findElement(new By.ByXPath("//*[@id=\"48C1F29CD4814FE1A57910FA5A783CEE\"]"));
        aa.click();
        driver.navigate().refresh();
        String currentUrl = driver.getCurrentUrl();
//        System.out.println(currentUrl);**************************
        String[] split = currentUrl.split("=");
        String tokenid = split[1];
        System.out.println(tokenid);
        driver.get("http://10.120.93.131:7001/bdg/bdgsub/query.page?mouldid=54033228A90F27B7E0530603A8C0F167&vchtypeid=83D9EDBF5D03CA14ABE67B01E6A7D807&mainmenu=BDG000&submenu=48C1F29CD4814FE1A57910FA5A783CEE&appid=bdg&settingyear=2021&menuName=%E6%8E%A5%E6%94%B6%E4%B8%8A%E7%BA%A7%E6%8C%87%E6%A0%87&menuId=48C1F29CD4814FE1A57910FA5A783CEE&year=2021&tokenid="+tokenid);
//        Thread.sleep(1000); // ====
        WebElement text = driver.findElement(new By.ByXPath("//*[@id=\"tablediv\"]/div[1]"));
        System.out.println(text.getText());
    }

    private static void login(WebDriver driver) {
        WebElement username = driver.findElement(new By.ByXPath("//*[@id=\"username\"]"));
        username.sendKeys("140423011");
//        username.sendKeys("140423033");
        WebElement password = driver.findElement(new By.ByXPath("//*[@id=\"password\"]"));
        password.sendKeys("wld321");
//        password.sendKeys("Jkwg1234");
        WebElement code = driver.findElement(new By.ByXPath("//*[@id=\"ehong-code\"]"));
        WebElement codeText = driver.findElement(new By.ByXPath("//*[@id=\"TxtIdCode\"]"));
        codeText.sendKeys(code.getText());
        WebElement login = driver.findElement(new By.ByXPath(" //*[@id=\"content_box\"]/div[4]/input[1]"));
        login.click();
    }

    private static WebDriver getWebDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        WebDriver driver =  new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}
