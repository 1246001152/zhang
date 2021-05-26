package com.lr.zhang.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping({"/","index","index.html"})
    public String index(){
        return "index";
    }
}
