package com.myapplication01.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhoul on 2019/1/2.
 */
public class HttpUtils {
    private final static String HOST = "http://192.168.1.102:8080/health/";
    public static String doPost(String method,HashMap<String,Object> params) {
        try {
//            ???URL????
            URL url = new URL(HOST + method);
//            ??????????HttpURLConnection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            ?????????
            httpURLConnection.setRequestMethod("POST");
//            ??????????????
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
//            ?§Ø?????????????
            if (params != null && params.keySet().size() > 0) {
//                ???????
                httpURLConnection.setDoOutput(true);
//                ????????
                OutputStream outputStream = httpURLConnection.getOutputStream();
//                account=123 & password=123
//                ??????????
                String paramStr = param(params);
//                §Õ??????
                outputStream.write(paramStr.getBytes("utf-8"));
//                ?????????????
                outputStream.flush();
//                ????????
                outputStream.close();
            }
//            ????????
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//
//                ?????????????????????
                InputStream inputStream = httpURLConnection.getInputStream();
//                ???????????????›¥??¦Æ???????
                int len;
//                ???????array?????????¦Æ???????????
                byte[] b = new byte[1024];
                StringBuffer sb = new StringBuffer();
//                ??????
                while ((len = inputStream.read(b))!= -1) {
//                    ?????????????????StringBuffer??
                    sb.append(new String(b,0,len,"utf-8"));
                }
//               ????????????????????
                inputStream.close();
                httpURLConnection.disconnect();
                return sb.toString();
            }
            else {
                return "????????";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "????URL??";
        }
    }
    /*??????????????
    * ?????????String??
    * ?????§Ò?
    * */
    private static String param(HashMap<String,Object> params) {
        if (params == null) return null;
        Set<Map.Entry<String,Object>> entries = params.entrySet ();
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String,Object> entry:entries) {
            sb.append("&" + entry.getKey() + "=" + entry.getValue());
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

}
