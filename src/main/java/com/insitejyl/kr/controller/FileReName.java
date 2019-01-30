package com.insitejyl.kr.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class FileReName {

    public static void main(String[] args) throws Exception {

        HashMap<String,String> acc = new HashMap<>();
        HashMap<String,String> tr = new HashMap<>();

        acc.put("4514", "Woong Math(supermentor011)");
        acc.put("4512", "엠앤에스(supermentor010)");
        acc.put("4510", "kichan(supermentor009)");
        acc.put("4508", "쑴아카데미(supermentor008)");
        acc.put("4506", "배상원슈퍼티쳐(supermentor007)");
        acc.put("4012", "키워드수학(supermentor006)");
        acc.put("4010", "석형철+박현구 논술(supermentor005)");
        acc.put("4008", "호바스교육연구소(supermentor004)");
        acc.put("4006", "BBong 수학연구소(supermentor003)");
        acc.put("4004", "(주)기신영(supermentor002)");
        acc.put("4002", "Contentsfarm(supermentor001)");
        acc.put("2956", "슈퍼멘토(supermentor)");

        tr.put("PC1(16:9) High","고화질1");
        tr.put("MOBILE1(16:9) Normal","모바일");
        tr.put("PC1(16:9) HD","고화질");
        tr.put("TABLET2(4:3) High","테블릿");




        File fileps = new File("C:\\Users\\JYL\\Desktop\\백업\\tt1.txt");
        FileReader filereader = new FileReader(fileps);
        BufferedReader bufReader = new BufferedReader(filereader);
        String line11 = "";
        int nn = 0;
        while((line11 = bufReader.readLine()) != null){
            String file_path = "D:\\supermentor\\";
            String file_path1 = "D:\\supermentor\\";
            nn++;
            String tt[] = line11.split(";");
            String num = tt[0];
            String dirforder = tt[1];
            String re_file_nm = tt[2];
            String file_nm = "dec_"+tt[3];
            String tr_cd = tt[4];
            file_path+= acc.get(num)+"\\"+dirforder+"\\("+tr.get(tr_cd)+")"+re_file_nm;
           // System.out.println(file_path);

            File destdir = new File(file_path1+acc.get(num)+"\\"+dirforder+"\\"); //디렉토리 가져오기

            if(!destdir.exists()){
                destdir.mkdirs(); //디렉토리가 존재하지 않는다면 생성
            }

            File file = new File( "D:\\file\\"+file_nm );
            File fileNew = new File( file_path );


            if (!file.renameTo(fileNew)) {
                System.err.println("이름 변경 에러 : " + file);
            }else{
                System.err.println("이름 변경 완료 : " + fileNew);
            }

        }

        System.out.println(nn);
/*

        File file = new File( "D:\\라.mp4" );
        File fileNew = new File( "D:\\test\\test123\\크1.mp4" );
        File destdir = new File(file_path); //디렉토리 가져오기



        if(!destdir.exists()){
            destdir.mkdirs(); //디렉토리가 존재하지 않는다면 생성
        }

        if (!file.renameTo(fileNew)) {
            System.err.println("이름 변경 에러 : " + file);
        }else{
            System.err.println("이름 변경 완료 : " + fileNew);
        }
*/

    }

}
