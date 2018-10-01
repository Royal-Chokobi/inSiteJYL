package com.insitejyl.kr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
@RequestMapping("/api")
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(Locale locale, Model model){

        System.out.println("=============================================================================");
        System.out.println("===============inSite Spring by Jae Yoon Lee - Get Test=======================");
        System.out.println("=============================================================================");
        System.out.println("================Home Page Requested, locale = " + locale + "=================");

        return "test";
    }

}
