package com.zhang.craw;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class HttpClientUtil {

    // 采用HttpClient进行post
    public static String post(String api_url, Map<String, String> postParameters) {
        try {
            @SuppressWarnings("resource")
            HttpClient client = new DefaultHttpClient();// 开启一个客户端 HTTP 请求
            HttpPost httpPost = new HttpPost(api_url);// 创建 HTTP POST 请求
//      List<NameValuePair> list = new ArrayList<NameValuePair>();
//      Iterator<Entry<String, String>> iterator = postParameters.entrySet().iterator();
//      while (iterator.hasNext()) {
//        Entry<String, String> elem = (Entry<String, String>) iterator.next();
//        list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
//      }
//      if (list.size() > 0) {
//        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, "utf-8");
//        httpPost.setEntity(entity);
//      }
            HttpResponse response = client.execute(httpPost);// 发起请求 并返回请求的响应
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static void main(String[] args) {
        String API_URL = "http://10.120.93.131:7001/pdm/prodetail/detailinfo/webservice.rcp?1626251442302&tokenid=3C547F4BB740AF1C91035697F7C42DC98fe91ee4&year=2021";
        //post的参数需要urlencode，由于httpclient组件里使用了UrlEncodedFormEntity组织参数，所以此处不能编码
        Map<String, String> parameters = new HashMap<>();
        parameters.put("tokenid", "app_id");
        parameters.put("year", "2021");
        String strResult = HttpClientUtil.post(API_URL, parameters);
        System.out.println(strResult);
    }
}
