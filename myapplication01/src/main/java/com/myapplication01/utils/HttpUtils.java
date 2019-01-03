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
//            获取URL对象
            URL url = new URL(HOST + method);
//            获取连接对象HttpURLConnection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            设置请求方式
            httpURLConnection.setRequestMethod("POST");
//            设置连接和读取超时
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
//            判断是否需要传参数
            if (params != null && params.keySet().size() > 0) {
//                打开输出流
                httpURLConnection.setDoOutput(true);
//                获取输出流
                OutputStream outputStream = httpURLConnection.getOutputStream();
//                account=123 & password=123
//                获取拼接的参数
                String paramStr = param(params);
//                写入数据
                outputStream.write(paramStr.getBytes("utf-8"));
//                强制刷新到服务器
                outputStream.flush();
//                关闭输出流
                outputStream.close();
            }
//            获取响应码
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//
//                响应成功，获取输入流对象
                InputStream inputStream = httpURLConnection.getInputStream();
//                定义一个变量用于存储每次读取的长度
                int len;
//                定义一个array用于接收每次读取到的字节数
                byte[] b = new byte[1024];
                StringBuffer sb = new StringBuffer();
//                开始读取
                while ((len = inputStream.read(b))!= -1) {
//                    将读取到的数据追加到StringBuffer中
                    sb.append(new String(b,0,len,"utf-8"));
                }
//               关闭流，释放对象（节约资源）
                inputStream.close();
                httpURLConnection.disconnect();
                return sb.toString();
            }
            else {
                return "请求失败！";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "请检查URL！";
        }
    }
    /*拼接参数格式的方法
    * 返回值类型String；
    * 参数列表
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
