package com.insitejyl.kr.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import com.insitejyl.kr.jwt;
import com.jayway.jsonpath.JsonPath;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public @ResponseBody void drmtest(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)  throws InvalidKeyException, NoSuchAlgorithmException,IOException {

        System.out.println("=============================================================================");
        System.out.println("=========inSite Spring by Jae Yoon Lee - POST DRM TEST=======================");
       /* System.out.println(request.getParameter("items"));
        System.out.println(request.getParameter("kind"));
        System.out.println(request.getParameter("client_user_id"));
        System.out.println(request.getParameter("player_id"));
        System.out.println(request.getParameter("device_name"));
        System.out.println(request.getParameter("media_content_key"));
        System.out.println(request.getParameter("uservalues"));*/
        System.out.println("================Home Page Requested, locale = " + locale + "=================");

        /*JSONObject jsonObject = new JSONObject();
        JSONObject returnObject = new JSONObject();
       // JSONArray personArray = new JSONArray();
        jsonObject.put("expiration_date", 1893455999);
        jsonObject.put("expiration_count", 1000);
        jsonObject.put("result", 1);
        returnObject.put("data", jsonObject);*/
        String test111111 = request.getParameter("items");

        JSONObject jsonObject = new JSONObject();

        JSONArray personArray = new JSONArray();
        personArray.put(request.getParameter("items"));

        jsonObject.put("dt", request.getParameter("items"));


        //JSONObject obj = new JSONObject(request.getParameter("items"));
        //String realplay = jsonObject.getJSONObject("dt").getString("media_content_key");


        HashMap<String, Object> map12 = new HashMap<String, Object>();
        map12.put("items",  request.getParameter("items"));
      //  map12.put("items",  map12.get("items"));

        System.out.println("map12 : "+map12.toString());
        System.out.println("map12 : "+map12.get("items").toString());
        String requestBody = request.getParameter("items");
        List<HashMap<String, Object>> test33 = JsonPath.read(requestBody.replace("items=", ""), "$[*]");
        test33.get(0).get("client_user_id");



        System.out.println("aaaaaaaaaaaaaa ========= :  "+test33);
        System.out.println(" =====1==== :  "+test33.get(0).get("client_user_id"));

        System.out.println("----------------------------"+jsonObject);
       // System.out.println("----------------------------"+jsonObject.getString("client_user_id"));
        System.out.println("----------------------------"+personArray);
        System.out.println("----------------------------"+personArray.getString(0));
        Enumeration params = request.getParameterNames();
        System.out.println("----------------------------");
        while (params.hasMoreElements()){
            String name = (String)params.nextElement();
            System.out.println(name + " : " +request.getParameter(name));
        }
        System.out.println("----------------------------");

        int kind = Integer.parseInt(test33.get(0).get("kind").toString());

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("kind", Integer.parseInt(test33.get(0).get("kind").toString()));
        map.put("media_content_key", test33.get(0).get("media_content_key").toString());
        map.put("result", 1);
        //map.put("content_expired", 0);
        // map.put("media_content_key", request.getParameter("media_content_key"));
        //map.put("client_user_id", request.getParameter("client_user_id"));
        //map.put("device_name", request.getParameter("device_name"));
        //map.put("player_id", request.getParameter("player_id"));

       // map.put("expiration_date", Integer.parseInt("1893455999"));
        map.put("expiration_date", Integer.parseInt("1538265600"));
        map.put("expiration_count", Integer.parseInt("100"));
      //  map.put("expiration_playtime", Integer.parseInt("300"));

        if (kind == 1) {


            map.put("expiration_refresh_popup", Integer.parseInt("0"));
            map.put("vmcheck", Integer.parseInt("0"));
            map.put("check_abuse", Integer.parseInt("0"));
            map.put("message", "kind1");

        } else if (kind == 2) {
            map.put("content_delete", Integer.parseInt("0"));
            map.put("message", "kind2");
        } else if (kind == 3) {
            if( test33.get(0).get("session_key").toString() != null){
                map.put("session_key", test33.get(0).get("session_key").toString());
            }
            map.put("start_at", Integer.parseInt(test33.get(0).get("start_at").toString()));
            map.put("content_expired", 1);
            map.put("content_delete", 0);
            map.put("content_expire_reset", 1);

            map.put("message", "kind3");
            map.put("check_abuse", 1);
        }


        List<HashMap<String, Object>> resultPayload = new ArrayList<HashMap<String, Object>>();
        resultPayload.add(map);
        HashMap<String, Object> payload = new HashMap<String, Object>();
        payload.put("data", resultPayload);

        jwt jwt = new jwt();
        String responseValue = jwt.jwt_encode(new Gson().toJson(payload), "jaeyoonlee");
        response.setStatus(200);
        //response.setContentType("plain/text; charset=utf-8");
        response.setHeader("X-Kollus-UserKey", "5f7cc1338860b4dc667ce5df77d3c19dedc9f9519eb167197cffe328ba944605");
        response.setHeader("Content-Type", "application/json");
        // PrintWriter out = response.getWriter();
       response.getWriter().write(responseValue);
       // response.getWriter().write("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjpbeyJyZXN1bHQiOjEsImV4cGlyYXRpb25fcGxheXRpbWUiOjMwMCwidm1jaGVjayI6MCwiZXhwaXJhdGlvbl9jb3VudCI6MTAwLCJraW5kIjoxLCJjaGVja19hYnVzZSI6MCwiZXhwaXJhdGlvbl9yZWZyZXNoX3BvcHVwIjowLCJtZWRpYV9jb250ZW50X2tleSI6ImxqQXEydll3IiwiZXhwaXJhdGlvbl9kYXRlIjoxODkzNDU1OTk5LCJtZXNzYWdlIjoiYWFhIn1dfQ.-PTnkyPEpvB0kSIjLuqIU8ijwiDCQNtsPxcRxgjt3aQ");
       // response.getWriter().write("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjpbeyJyZXN1bHQiOjEsImV4cGlyYXRpb25fcGxheXRpbWUiOjAsInZtY2hlY2siOjAsImtpbmQiOjEsImNoZWNrX2FidXNlIjowLCJleHBpcmF0aW9uX3JlZnJlc2hfcG9wdXAiOjAsIm1lZGlhX2NvbnRlbnRfa2V5IjoibGpBcTJ2WXciLCJleHBpcmF0aW9uX2RhdGUiOjE4OTM0NTU5OTksIm1lc3NhZ2UiOiIifV19.-U7jm4RSTR4olNRjC60vLfJg_NHniEauDM5QrMaIYXo");
        //out.print(result1);
        response.getWriter().close();

        System.out.println(responseValue);
        System.out.println("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjpbeyJyZXN1bHQiOjEsImV4cGlyYXRpb25fcGxheXRpbWUiOjMwMCwidm1jaGVjayI6MCwiZXhwaXJhdGlvbl9jb3VudCI6MTAwLCJraW5kIjoxLCJjaGVja19hYnVzZSI6MCwiZXhwaXJhdGlvbl9yZWZyZXNoX3BvcHVwIjowLCJtZWRpYV9jb250ZW50X2tleSI6ImxqQXEydll3IiwiZXhwaXJhdGlvbl9kYXRlIjoxODkzNDU1OTk5LCJtZXNzYWdlIjoiYWFhIn1dfQ.-PTnkyPEpvB0kSIjLuqIU8ijwiDCQNtsPxcRxgjt3aQ");



       /* String result = null;
       // ObjectMapper aeta= new ObjectMapper();
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", map);
        //result = aeta.writeValueAsString(resultMap);


       *//* List<HashMap<String, Object>> mapList = null;
        mapList = new ArrayList<HashMap<String, Object>>();
        mapList.add(map);*//*

        String result1 = null;
        HashMap<String, Object> resultMapList = new HashMap<String, Object>();
        resultMapList.put("data", mapList);
        result1 = (new ObjectMapper()).writeValueAsString(resultMapList);


        System.out.println(result1);
        System.out.println(resultMap);
        System.out.println(map);

        jwt jwt = new jwt();
        String responseValue = jwt.jwt_encode(new Gson().toJson(resultMapList), "jaeyoonlee");
        System.out.println(responseValue);
        response.setStatus(200);
        response.setContentType("plain/text; charset=utf-8");
        response.setHeader("X-Kollus-UserKey", "cf791cc9453448775d9d58fe099689f81651f618c3dc12845447f4b6f7b1e6fe");
       // PrintWriter out = response.getWriter();
       // response.getWriter().write(responseValue);
        response.getWriter().write("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkYXRhIjpbeyJyZXN1bHQiOjEsImV4cGlyYXRpb25fcGxheXRpbWUiOjAsInZtY2hlY2siOjAsImtpbmQiOjEsImNoZWNrX2FidXNlIjowLCJleHBpcmF0aW9uX3JlZnJlc2hfcG9wdXAiOjAsIm1lZGlhX2NvbnRlbnRfa2V5IjoibGpBcTJ2WXciLCJleHBpcmF0aW9uX2RhdGUiOjE4OTM0NTU5OTksIm1lc3NhZ2UiOiIifV19.-U7jm4RSTR4olNRjC60vLfJg_NHniEauDM5QrMaIYXo");
        //out.print(result1);
        response.getWriter().close();*/
       // String test123 = "{data:[{result=1, device_name=iPhone10,5, player_id=8272B31730355AA3C8384FCD42AD9DD4CFF2FED2, expiration_count=10, client_user_id=jaeyoonlee, kind=1, media_content_key=ljAq2vYw, expiration_date=1546128000}]}";

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

        //model.addAttribute(returnObject);


       // model.addAttribute(result1);
        //model.addAttribute( new Gson().toJson(resultMapList));

     //  return  new Gson().toJson(resultMapList);
    }

}
