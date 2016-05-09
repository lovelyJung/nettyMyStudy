package com.test.go;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class test {

	public static void main(String[] args) throws Exception {

        // TODO Auto-generated method stub

        

        // 한글의 경우 인코딩을 해야함.

        // 서버쪽에서는 따로 decode할 필욘 없음. 대신 new String(str.getBytes("8859_1"), "UTF-8");로 인코딩을 변경해야함

        String str = URLEncoder.encode("한글", "UTF-8");

      

        URL url = new URL("http://www.kma.go.kr/DFSROOT/POINT/DATA/top.json.txt");

        // open connection

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setDoInput(true);            // 입력스트림 사용여부

        conn.setDoOutput(true);            // 출력스트림 사용여부

        conn.setUseCaches(false);        // 캐시사용 여부

        conn.setReadTimeout(20000);        // 타임아웃 설정 ms단위

        conn.setRequestMethod("POST");  // or GET

 

        // Post로 Request하기

        OutputStream os = conn.getOutputStream();

        OutputStreamWriter writer = new OutputStreamWriter(os);

        writer.write("title="+str);

        writer.write("&subTitle="+str+"2");

        writer.close();

        os.close();

        

        // Response받기

       StringBuffer sb =  new StringBuffer();

        BufferedReader br = new BufferedReader( new InputStreamReader(conn.getInputStream()));

       

        for(;;){

        String line =  br.readLine();

        if(line == null) break;

        sb.append(line+"\n");

        }

   

        br.close();

        conn.disconnect();

       

        String getXml = sb.toString();

        System.out.println(sb.toString());        

    }

}
