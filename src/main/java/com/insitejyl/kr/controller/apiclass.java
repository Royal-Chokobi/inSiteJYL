package com.insitejyl.kr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/call")
public class apiclass {


    public static void main(String[] args) throws Exception {

        String url="http://api.kr.kollus.com/0/media/subtitle/upload?access_token=pdlzqdm5yx2ycxsq";
        String result="";

        File file1 = new File("C:\\Users\\JYL\\Desktop\\강남인강\\자막\\list_11.txt");
        FileReader filereader = new FileReader(file1);
        //입력 버퍼 생성
        BufferedReader bufReader = new BufferedReader(filereader);
        String line11 = "";
        int cknum = 0;
        int cknum1 = 0;
        while((line11 = bufReader.readLine()) != null){
            String tt[] = line11.split(",");
            String m_key = tt[0];
            String nm = tt[1];
            String file_nm = tt[2];
            //System.out.println(tt[0]);
            //System.out.println(tt[1]);
            //System.out.println(tt[2]);
          //  System.out.println(line);
            cknum++;

            try {
                URL object=new URL(url);

                HttpURLConnection con = (HttpURLConnection) object.openConnection();

                con.setDoOutput(true);
                con.setDoInput(true);
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
                con.setRequestMethod("POST");

                String txt = "";
                File file = new File("C:\\Users\\JYL\\Desktop\\강남인강\\자막\\srt\\"+file_nm);
                Scanner scan = new Scanner(file);
                while(scan.hasNextLine()){
                    txt+=scan.nextLine()+"\n";
                    //  System.out.println(scan.nextLine());
                }


                //  FileReader filereader = new FileReader(file);
                //  FileInputStream fin = new FileInputStream(file);

                String media_content_key =m_key;
                String name = nm;
                String language_id = "2";
                String subtitle_body= txt;

                String body = String.format("media_content_key=%s&name=%s&language_id=%s&subtitle_body=%s",
                        media_content_key, name, language_id, subtitle_body);

                OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
                //System.out.println(body);
                wr.write(body);
                wr.flush();

                //display what returns the POST request

                int HttpResult =con.getResponseCode();
                if(HttpResult ==HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
                    String line = null;
                    line = br.readLine();

                    while ((line = br.readLine())!= null) {
                        result = result+line + "\n";
                        System.out.println(line+"\n");
                    }
                    br.close();
                    cknum1++;
                }else{
                    System.out.println(con.getResponseMessage());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            Thread.sleep(500);

        }

        System.out.println(cknum);
        System.out.println(cknum1);
        //.readLine()은 끝에 개행문자를 읽지 않는다.
        bufReader.close();



        /*try {
            URL object=new URL(url);

            HttpURLConnection con = (HttpURLConnection) object.openConnection();

            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
            con.setRequestMethod("POST");

            String txt = "";
            File file = new File("C:\\Users\\JYL\\Desktop\\강남인강\\자막\\srt\\(LUKAS)2018 생활과 윤리 The Final 1강_600k(c).srt");
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                txt+=scan.nextLine()+"\n";
              //  System.out.println(scan.nextLine());
            }


          //  FileReader filereader = new FileReader(file);
          //  FileInputStream fin = new FileInputStream(file);

            JSONObject json = new JSONObject();
            String media_content_key ="nhUlu4vF";
            String name = "진짜 마지막.srt";
            String language_id = "2";
            String subtitle_body= txt;

            String body = String.format("media_content_key=%s&name=%s&language_id=%s&subtitle_body=%s",
                    media_content_key, name, language_id, subtitle_body);

            OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
            System.out.println(body);
            wr.write(body);
            wr.flush();

            //display what returns the POST request

            int HttpResult =con.getResponseCode();
            if(HttpResult ==HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
                String line = null;
                line = br.readLine();

                while ((line = br.readLine())!= null) {
                    result = result+line + "\n";
                    System.out.println(line+"\n");
                }
                br.close();
            }else{
                System.out.println(con.getResponseMessage());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/

        /*
        String txt = "1 " +
                "00:00:09,909 --> 00:00:13,282" +
                "반갑습니다." +
                "루카스 진민 선생님입니다.";
        JSONObject json = new JSONObject();
        String media_content_key ="nhUlu4vF";
        String name = "test9999999";
        String language_id = "2";
        String subtitle_body= txt;

        String body = String.format("media_content_key=%s&name=%s&language_id=%s&subtitle_body=%s",
                media_content_key, name, language_id, subtitle_body);
        String api = "http://api.kr.kollus.com/0/media/subtitle/upload";
        String _url = api + "?access_token=j4toxsy2xkhsrbv3";
      //  String body = json.toString();
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        String method = "POST";
        URL url = new URL(_url);
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30 * 1000);
        } catch (Exception e) {
            Thread.sleep(5000L);
            connection = (HttpURLConnection) url.openConnection();
        }
        connection.setRequestMethod(method);
        if (headers != null) {
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
        }
        if (body != null && body.trim() != "") {
            if ("POST".equals(method) || "PUT".equals(method)) {
                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                byte[] bodybytes = body.getBytes("UTF-8");

               *//* System.out.println(json.toString());
                OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());*//*

                wr.write(bodybytes);
                wr.flush();
            }
        }
        int responseCode = 0;
        int retry = 0;
        try {
            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            System.out.println("Raise ERR getResponseCode()");
            while (retry < 5 && responseCode == 0) {
                Thread.sleep(2000L);
                try {
                    responseCode = connection.getResponseCode();
                } catch (Exception sub) {
                    System.out.println("Raise Sub ERR getResponseCode()");
                }
                retry += 1;
            }
        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
        String inputLine;
        StringBuffer response = new StringBuffer();
        int inIdx = 0;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            inIdx++;
        }
        in.close();
    }*/}
    }

    /*

    public String send() throws Exception {
        String api = "http://api.kr.kollus.com/0/media/subtitle/upload";
        String _url = api + "?access_token=j4toxsy2xkhsrbv3";
        String body = "";
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        String method="POST";
        URL url = new URL(_url);
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(30 * 1000);
        } catch (Exception e) {
            Thread.sleep(5000L);
            connection = (HttpURLConnection) url.openConnection();
        }
        connection.setRequestMethod(method);
        if (headers != null) {
            for (String key : headers.keySet()) {
                connection.setRequestProperty(key, headers.get(key));
            }
        }
        if (body != null && body.trim() != "") {
            if ("POST".equals(method) || "PUT".equals(method)) {
                connection.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
                byte[] bodybytes = body.getBytes("UTF-8");
                wr.write(bodybytes);
                wr.flush();
            }
        }
        int responseCode = 0;
        int retry = 0;
        try {
            responseCode = connection.getResponseCode();
        } catch (Exception e) {
            System.out.println("Raise ERR getResponseCode()");
            while (retry < 5 && responseCode == 0) {
                Thread.sleep(2000L);
                try {
                    responseCode = connection.getResponseCode();
                } catch (Exception sub) {
                    System.out.println("Raise Sub ERR getResponseCode()");
                }
                retry += 1;
            }
        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
        String inputLine;
        StringBuffer response = new StringBuffer();
        int inIdx = 0;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            inIdx++;
        }
        in.close();
        return response.toString();
    }

}

*/








/*

 try{
         File file = new File("C:\\in_workspace\\encipherment\\txt_file\\encode.txt");
         FileReader filereader = new FileReader(file);
         int ch_txt = 0;
         String txt = "";
         HashMap<Integer, String> txtMap = new HashMap<>();
        ArrayList<String> txtArray = new ArrayList<>();
        ArrayList<String> copy_txtArray = new ArrayList<>();
        int lineNum = 0;

        while((ch_txt = filereader.read()) != -1){
        //System.out.println(ch_txt);
        if(((char)ch_txt == '\r') || ((char)ch_txt == '\n')){
        //System.out.print("=====================");
        // System.out.print("\n");
        }else{
        //System.out.print((char)singleCh);
        // System.out.println(Integer.toString((char)singleCh, 16));
        String char_txt_16= Integer.toString((char)ch_txt, 16);

        if(char_txt_16.equals("2e")){
        txtArray.add(char_txt_16);
        txt += char_txt_16;
        txtMap.put(lineNum, txt);
        txtArray.clear();
        txt="";
        lineNum++;

        }else{
        txt += char_txt_16+" ";
        txtArray.add(char_txt_16);
        }
        //txt += char_txt_16+" ";
        }


        // System.out.print((char)singleCh);
        //System.out.println(Integer.toString((char)singleCh, 16));  //returns c548
        }

        //System.out.println(txt);
        // Encoder encoder = Base64.getEncoder();
        // Decoder decoder = Base64.getDecoder();

        //	 byte[] encodeByte = encoder.encode(line.getBytes());
        //  byte[] decodeByte = decoder.decode(encodeByte);
        //	System.out.println(new String(decodeByte));

        Base64.Encoder Bs64_encoder = Base64.getEncoder();

        for(int i = 0; i < txtMap.size(); i++){
        // Integer.decode(txtMap.get(i));

        //txtMap.get(i);
        //  byte[] encodeByte = encoder.encode(txtMap.get(i).getBytes());
        //   System.out.println(txtMap.get(i));

        String target = txtMap.get(i);
        byte[] encodeBytes = target.getBytes("UTF-8");
        String encodedString = Bs64_encoder.encodeToString(encodeBytes);

        System.out.println(encodedString);

        // System.out.println(encoder.encode(txtMap.get(i)));
        }
        // System.out.println(txtArray.toString());
        filereader.close();
        }catch (FileNotFoundException e) {
        // TODO: handle exception
        }catch(IOException e){
        System.out.println(e);
        }

*/

