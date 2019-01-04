package com.insitejyl.kr.controller;

import com.google.gson.Gson;
import com.insitejyl.kr.jwt;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.www.http.HttpClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/api")
public class TestController {

    List<HashMap<String, Object>> bookmark_position = new ArrayList<HashMap<String, Object>>();
    private String lms_test = "";
    private ArrayList id = new ArrayList();
    private ArrayList pt = new ArrayList();
    private ArrayList pitches = new ArrayList();
    private ArrayList real_play = new ArrayList();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(Locale locale, Model model){
        System.out.println("===============inSite Spring by Jae Yoon Lee - Get Test=======================");

        return "test";
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/jamac", method = RequestMethod.GET)
    public String jamac(Locale locale, Model model){
        System.out.println("===============inSite Spring by Jae Yoon Lee - Get Test=======================");

        return "ajax_test_jamac";
    }

    @RequestMapping(value = "/jamac1", method = RequestMethod.GET)
    public String jamac1(Locale locale, Model model) throws Exception{
        System.out.println("===============jjjjj=======================");
        try{
            //파일 객체 생성
            File file = new File("C:\\in_workspace\\encipherment\\txt_file\\encode.txt");
            //입력 스트림 생성
            FileReader filereader = new FileReader(file);
            int singleCh = 0;
            while((singleCh = filereader.read()) != -1){
                System.out.print((char)singleCh);
            }
            filereader.close();
        }catch (FileNotFoundException e) {
            // TODO: handle exception
        }catch(IOException e){
            System.out.println(e);
        }

        StringBuffer response = new StringBuffer();

        try {
            String url = "http://api.kr.kollus.com/0/media/subtitle/upload?access_token=j4toxsy2xkhsrbv3";
            URL obj = new URL(url);
            URLConnection conn = obj.openConnection();

            String urlParameters = "media_content_key=" + "nhUlu4vF" +"&name=" + "test123"+"&language_id="+"2"+"&subtitle_body="+"";

            // POST 값 전송일 경우 true
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            // 파라미터를 wr에 넣어주고 flush
            wr.write(urlParameters);
            wr.flush();

            // in에 readLine이 null이 아닐때까지 StringBuffer에 append
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            wr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return "ajax_test_jamac";
    }


    @RequestMapping(value = "/mo", method = RequestMethod.GET)
    public String mobile_test(Locale locale, Model model){
        System.out.println("===============inSite Spring by Jae Yoon Lee - Get Test=======================");

        return "mobile_test";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String apitest(Locale locale, Model model){

        System.out.println("===============inSite Spring by Jae Yoon Lee - Get Test=======================");

        return "api_test";
    }

    @RequestMapping(value = "/playtest", method = RequestMethod.GET)
    public String playtest(Locale locale, Model model){

        System.out.println("===============inSite Spring by Jae Yoon Lee - play_test=======================");

        return "play_test";
    }

    @RequestMapping(value = "/bookmarkget", method = RequestMethod.GET)
    public void bookmarkGet(Locale locale, Model model,HttpServletRequest request, HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,IOException{

        System.out.println("===============inSite Spring by Jae Yoon Lee - bookmarkget=======================");

        List<String> bookmark = new ArrayList<String>();
        bookmark.add("Bookmark");
        bookmark.add("Index");

        HashMap<String, Object> bookmark_map = new HashMap<String, Object>();
        bookmark_map.put("bookmark_labels", bookmark);
        bookmark_map.put("bookmark_positions", bookmark_position);

        List<HashMap<String, Object>> resultPayload = new ArrayList<HashMap<String, Object>>();
        resultPayload.add(bookmark_map);

        HashMap<String, Object> payload = new HashMap<String, Object>();
        payload.put("error", 0);
        payload.put("result", bookmark_map);


        response.setStatus(200);
        //response.setContentType("plain/text; charset=utf-8");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setHeader("Content-Type", "application/json");
        // PrintWriter out = response.getWriter();
        response.getWriter().write(new Gson().toJson(payload));
        response.getWriter().close();

    }

    @RequestMapping(value = "/bookmarkpost", method = RequestMethod.POST)
    public void bookmarkPost(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws InvalidKeyException, NoSuchAlgorithmException,IOException{

        System.out.println("===============inSite Spring by Jae Yoon Lee - bookmarkpost=======================");

        String requestBody = request.getParameter("bookmarks");

        List<String> bookmark = new ArrayList<String>();
        bookmark.add("Bookmark");
        bookmark.add("Index");
        HashMap<String, Object> bookmarkpositions = new HashMap<String, Object>();

        if(request.getParameter("bookmarks") != null){
            List<HashMap<String, Object>> jsonDt = JsonPath.read(requestBody.replace("bookmarks=", ""), "$[*]");
            for(int i= 0; i < jsonDt.size(); i++){
                System.out.println(jsonDt.get(i).get("action"));
                if( jsonDt.get(i).get("action").equals("remove")){
                    int j = 0;
                    for (HashMap<String, Object> list: bookmark_position) {
                        if(list.get("position").equals(jsonDt.get(i).get("position"))){
                            bookmark_position.remove(j);
                            break;
                        }
                        j++;
                    }
                }else{
                    bookmarkpositions.put("position",jsonDt.get(i).get("position"));
                    bookmarkpositions.put("value",jsonDt.get(i).get("value"));
                    bookmarkpositions.put("kind",0);
                    bookmarkpositions.put("label","");
                    bookmarkpositions.put("localtime",1417568260);
                    bookmark_position.add(bookmarkpositions);
                }
            }
        }else{
            bookmarkpositions.put("position",request.getParameter("position"));
            bookmarkpositions.put("value",request.getParameter("value"));
            bookmarkpositions.put("kind",0);
            bookmarkpositions.put("label","");
            bookmarkpositions.put("localtime",1417568260);
            bookmark_position.add(bookmarkpositions);
        }

        HashMap<String, Object> bookmark_map = new HashMap<String, Object>();
        bookmark_map.put("bookmark_labels", bookmark);
        bookmark_map.put("bookmark_positions", bookmark_position);

        List<HashMap<String, Object>> resultPayload = new ArrayList<HashMap<String, Object>>();
        resultPayload.add(bookmark_map);

        HashMap<String, Object> payload = new HashMap<String, Object>();
        payload.put("error", 0);
        payload.put("result", bookmark_map);

        response.setStatus(200);
        //response.setContentType("plain/text; charset=utf-8");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

        response.setHeader("Access-Control-Allow-Origin", "*");

        response.setHeader("Content-Type", "application/json");

        response.getWriter().write(new Gson().toJson(payload));
        response.getWriter().close();

    }
    @RequestMapping(value = "/drm/", method = RequestMethod.POST)
    public @ResponseBody void drmtest(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)  throws InvalidKeyException, NoSuchAlgorithmException,IOException {

        System.out.println("=========inSite Spring by Jae Yoon Lee - POST DRM TEST=======================");

        JSONObject jsonObject = new JSONObject();

        JSONArray personArray = new JSONArray();
        personArray.put(request.getParameter("items"));

        jsonObject.put("dt", request.getParameter("items"));

        HashMap<String, Object> map12 = new HashMap<String, Object>();
        map12.put("items",  request.getParameter("items"));

        System.out.println("request : ======================== "+map12.get("items").toString());
        String requestBody = request.getParameter("items");
        List<HashMap<String, Object>> test33 = JsonPath.read(requestBody.replace("items=", ""), "$[*]");
        test33.get(0).get("client_user_id");

        int kind = Integer.parseInt(test33.get(0).get("kind").toString());

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("kind", Integer.parseInt(test33.get(0).get("kind").toString()));
        map.put("media_content_key", test33.get(0).get("media_content_key").toString());
        map.put("result", 1);
      //  map.put("expiration_date", Integer.parseInt("1542090511"));
      //  map.put("expiration_count", Integer.parseInt("100"));

        if (kind == 1) {

            map.put("expiration_playtime", Integer.parseInt("1200"));
            map.put("expiration_playtime_type", Integer.parseInt("1"));
            map.put("message", "죄송합니다. 등록할 수 있는 기기는 최대 3개까지 입니다. PC버전 강의실 하단의 기기등록 정보에서 사용하지 않는 기기를 제거하고 다시 실행해주세요.");

          /*  map.put("expiration_refresh_popup", Integer.parseInt("0"));
            map.put("expiration_playtime", Integer.parseInt("120"));
            map.put("expiration_playtime_type", Integer.parseInt("1"));
            map.put("vmcheck", Integer.parseInt("0"));
            map.put("check_abuse", Integer.parseInt("0"));
            map.put("message", "안녕하세요.");*/

        } else if (kind == 2) {
            map.put("content_delete", Integer.parseInt("0"));
            map.put("message", "kind2");
        } else if (kind == 3) {
            if( test33.get(0).get("session_key").toString() != null){
                map.put("session_key", test33.get(0).get("session_key").toString());
            }
            map.put("start_at", Integer.parseInt(test33.get(0).get("start_at").toString()));
            map.put("content_expired", 0);
            map.put("content_delete", 0);
            map.put("content_expire_reset", 0);

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
        response.setHeader("X-Kollus-UserKey", "5f7cc1338860b4dc667ce5df77d3c19dedc9f9519eb167197cffe328ba944605");
        response.setHeader("Content-Type", "application/json");
        response.getWriter().write(responseValue);

        response.getWriter().close();

       System.out.println("response : ============ "+ payload);

    }

    @RequestMapping(value = "/lms", method = RequestMethod.POST)
    public String lms(Locale locale, HttpServletRequest request, Model model) throws ServletException, IOException {
        JSONObject jsonObject = new JSONObject();

        JSONArray personArray = new JSONArray();
        personArray.put(request.getParameter("data"));

        jsonObject.put("dt", request.getParameter("data"));

        System.out.println(personArray);

        JSONObject obj = new JSONObject(request.getParameter("data"));
        System.out.println(obj.getJSONObject("content_info"));
        String realplay = obj.getJSONObject("content_info").get("real_playtime").toString();
        //System.out.println(obj);
        // System.out.println(pageName);


        model.addAttribute("data", request.getParameter("data"));
        model.addAttribute("id", request.getParameter("id"));
        model.addAttribute("pt", request.getParameter("pt"));

        lms_test =  request.getParameter("data");
        pitches.add(lms_test);
        real_play.add(realplay);
        //id.add(request.getParameter("id"));
        pt.add("ID : "+request.getParameter("id")+" 시청시간(초) : "+request.getParameter("pt")+"초 시청"+"실 재생시간 : "+realplay+" 초");


        System.out.println("ID : "+request.getParameter("id")+" 시청시간(초) : "+request.getParameter("pt")+"초 시청"+"실 재생시간 : "+realplay+" 초");

        return "lmsData";
    }

    @RequestMapping(value = "/play", method = RequestMethod.POST)
    public @ResponseBody void play(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)  throws InvalidKeyException, NoSuchAlgorithmException,IOException {

        System.out.println("----------------------------"+request.getParameter("kind"));
        System.out.println("----------------------------"+request.getParameter("client_user_id"));
        System.out.println("----------------------------"+request.getParameter("player_id"));
        System.out.println("----------------------------"+request.getParameter("device_name"));
        Enumeration params = request.getParameterNames();
        System.out.println("----------------------------");
        while (params.hasMoreElements()){
            String name = (String)params.nextElement();
            System.out.println(name + " : " +request.getParameter(name));
        }
        System.out.println("----------------------------");


        JSONObject jsonObject = new JSONObject();

        JSONArray personArray = new JSONArray();
        personArray.put(request.getParameter("items"));

        jsonObject.put("dt", request.getParameter("items"));

        HashMap<String, Object> map12 = new HashMap<String, Object>();
        map12.put("items",  request.getParameter("items"));

        System.out.println("request : ======================== "+map12.get("items").toString());
        String requestBody = request.getParameter("items");
        List<HashMap<String, Object>> test33 = JsonPath.read(requestBody.replace("items=", ""), "$[*]");
        test33.get(0).get("client_user_id");

        int kind = Integer.parseInt(test33.get(0).get("kind").toString());

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("kind", request.getParameter("kind"));
        map.put("media_content_key", test33.get(0).get("media_content_key").toString());
        map.put("result", 1);
        //  map.put("expiration_date", Integer.parseInt("1542090511"));
        //  map.put("expiration_count", Integer.parseInt("100"));

        if (request.getParameter("kind") == "1") {

            map.put("expiration_playtime", Integer.parseInt("1200"));
            map.put("expiration_playtime_type", Integer.parseInt("1"));
            map.put("message", "죄송합니다. 등록할 수 있는 기기는 최대 3개까지 입니다. PC버전 강의실 하단의 기기등록 정보에서 사용하지 않는 기기를 제거하고 다시 실행해주세요.");

          /*  map.put("expiration_refresh_popup", Integer.parseInt("0"));
            map.put("expiration_playtime", Integer.parseInt("120"));
            map.put("expiration_playtime_type", Integer.parseInt("1"));
            map.put("vmcheck", Integer.parseInt("0"));
            map.put("check_abuse", Integer.parseInt("0"));
            map.put("message", "안녕하세요.");*/

        }  else if (request.getParameter("kind") == "3") {

            map.put("start_at", Integer.parseInt(test33.get(0).get("start_at").toString()));
            map.put("content_expired", 0);
            map.put("content_delete", 0);
            map.put("content_expire_reset", 0);

            map.put("message", "kind3");
            map.put("check_abuse", 1);

        }


        List<HashMap<String, Object>> resultPayload = new ArrayList<HashMap<String, Object>>();
        resultPayload.add(map);
        HashMap<String, Object> payload = new HashMap<String, Object>();
        payload.put("data", resultPayload);

        jwt jwt = new jwt();
        String responseValue = jwt.jwt_encode(new Gson().toJson(payload), "jaeyoonlee");
        // String responseValue = jwt.jwt_encode(new Gson().toJson(payload), "hdyang2");
        response.setStatus(200);
        //response.setContentType("plain/text; charset=utf-8");
        response.setHeader("X-Kollus-UserKey", "5f7cc1338860b4dc667ce5df77d3c19dedc9f9519eb167197cffe328ba944605");
        // response.setHeader("X-Kollus-UserKey", "cd285ed87f3c581f4739e0e303c4bfc19e9b1caefeb2337223d285b3b9b7eca9");
        response.setHeader("Content-Type", "application/json");
        response.getWriter().write(responseValue);

        response.getWriter().close();


        System.out.println("=========inSite Spring by Jae Yoon Lee - play=======================");

    }
    @RequestMapping(value = "/play", method = RequestMethod.GET)
    public void play_get(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response)  throws InvalidKeyException, NoSuchAlgorithmException,IOException {



        System.out.println("=========inSite Spring by Jae Yoon Lee - play_get=======================");

    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public String goapi(Locale locale, Model model){
        System.out.println("===============inSite Spring by Jae Yoon Lee - Get Test=======================");

        return "ajax_test";
    }

}
