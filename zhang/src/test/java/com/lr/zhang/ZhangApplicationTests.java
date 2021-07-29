package com.lr.zhang;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.Map;

@SpringBootTest
class ZhangApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("hello");
    }

    public static void main(String[] args) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("abc", "a1");
        map.put("book", "a2");
        map.put("city", "a3");
        map.put("dis", "a4");

        System.out.println(map);


    }

}
