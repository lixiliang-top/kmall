package com.kgc.kmall.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 李锡良
 * @create 2021-01-04 16:48
 */
@Controller
public class SearchController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/list")
    public String list(){
        return "list";
    }

}
