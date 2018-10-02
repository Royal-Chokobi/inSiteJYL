package com.insitejyl.kr.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping(value = "/drm/", method = RequestMethod.POST)
    public String drmtest(Locale locale, Model model, HttpServletRequest request){

        System.out.println("=============================================================================");
        System.out.println("=========inSite Spring by Jae Yoon Lee - POST DRM TEST=======================");
        System.out.println(request.getParameter("kind"));
        System.out.println(request.getParameter("client_user_id"));
        System.out.println(request.getParameter("player_id"));
        System.out.println(request.getParameter("device_name"));
        System.out.println(request.getParameter("media_content_key"));
        System.out.println(request.getParameter("uservalues"));
        System.out.println("================Home Page Requested, locale = " + locale + "=================");

        JSONObject jsonObject = new JSONObject();
        JSONObject returnObject = new JSONObject();
       // JSONArray personArray = new JSONArray();
        jsonObject.put("expiration_date", 1546128000);
        jsonObject.put("expiration_count", 10);
        jsonObject.put("result", 1);
        returnObject.put("data", jsonObject);

        System.out.println(returnObject.toString());
        /*JSONObject jsonObject = new JSONObject();

        JSONArray personArray = new JSONArray();
        personArray.put(request.getParameter("items"));

        jsonObject.put("dt", request.getParameter("items"));


        JSONObject obj = new JSONObject(request.getParameter("items"));
        String realplay = obj.getJSONObject("content_info").getString("real_playtime");
        //System.out.println(obj);
        // System.out.println(pageName);


        model.addAttribute("data", request.getParameter("data"));
        model.addAttribute("id", request.getParameter("id"));
        model.addAttribute("pt", request.getParameter("pt"));
*/
        /*lms_test =  request.getParameter("data");
        pitches.add(lms_test);
        real_play.add(realplay);
        //id.add(request.getParameter("id"));
        pt.add("ID : "+request.getParameter("id")+" 시청시간(초) : "+request.getParameter("pt")+"초 시청"+"실 재생시간 : "+realplay+" 초");


        System.out.println("ID : "+request.getParameter("id")+" 시청시간(초) : "+request.getParameter("pt")+"초 시청"+"실 재생시간 : "+realplay+" 초");*/

        model.addAttribute(returnObject);

        return "test";
    }

}
