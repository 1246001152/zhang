package com.zhang.craw;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

public class JsoupUtils {

    public static String jsoupElement(String content) {
        Document doc = Jsoup.parse(content);
        Element body = doc.body();
        Elements aHref = body.select("a");
        Elements jsScript = body.select("script");
        Elements form = body.select("form");
        Elements link = body.select("link");
        Elements ifrom = body.select("iframe ");
        Elements http = body.select("http");
        if (jsScript.size() != 0 || aHref.size() != 0 || form.size() != 0 || link.size() != 0 || ifrom.size() != 0 || http.size() != 0) {
            return "0";
        }
        return "";
    }

    /**
     * String content
     *
     * @return
     */
    public static String jsoupElementByURL() {
        String url = "http://10.120.93.131:7001/pdm/prodetail/query.page?mouldid=E7F5F6AA70734D6B982AFAA6E79D47C1&vchtypeid=C17515B55628A3B4C067DD54D893E9FF&mainmenu=CCF75F693DE759F437440BABF59395CE&submenu=BCBD008EDC634AF64F7E9AD0AC629CCD&appid=pdm&settingyear=2021&menuName=%E7%89%B9%E5%AE%9A%E7%9B%AE%E6%A0%87%E7%B1%BB%E4%B8%89%E7%BA%A7%E9%A1%B9%E7%9B%AE%E9%A2%84%E7%AE%97%E7%BC%96%E5%88%B6%E6%9F%A5%E8%AF%A2&menuId=BCBD008EDC634AF64F7E9AD0AC629CCD&year=2021&tokenid=3C547F4BB740AF1C91035697F7C42DC9e5ec7036";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Element body = doc.body();
        Elements aHref = body.select("a");
        Elements es = body.select("a");
        for (Iterator it = es.iterator(); it.hasNext(); ) {
            Element e = (Element) it.next();
            System.out.println(e.text() + " " + e.attr("href"));
        }
        Elements jsScript = body.select("script");
        Elements form = body.select("form");
        Elements link = body.select("link");
        Elements ifrom = body.select("iframe ");
        Elements http = body.select("http");
        if (jsScript.size() != 0 || aHref.size() != 0 || form.size() != 0 || link.size() != 0 || ifrom.size() != 0 || http.size() != 0) {
            return "0";
        }
        return "";
    }

//    public static void main(String[] args) {
//        jsoupElementByURL();
//    }
}