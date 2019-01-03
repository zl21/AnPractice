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
    private final static String HOST = "http://120.77.242.43:8000/";
    public static String doPost(String method,HashMap<String,Object> params) {
        try {
//            ��ȡURL����
            URL url = new URL(HOST + method);
//            ��ȡ���Ӷ���HttpURLConnection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            ��������ʽ
            httpURLConnection.setRequestMethod("POST");
//            �������ӺͶ�ȡ��ʱ
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
//            �ж��Ƿ���Ҫ������
            if (params != null && params.keySet().size() > 0) {
//                ��ȡ�����
                OutputStream outputStream = httpURLConnection.getOutputStream();
//                account=123 & password=123
            }
//            ��ȡ��Ӧ��
            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                ��Ӧ�ɹ�����ȡ����������
                InputStream inputStream = httpURLConnection.getInputStream();
//                ����һ���������ڴ洢ÿ�ζ�ȡ�ĳ���
                int len;
//                ����һ��array���ڽ���ÿ�ζ�ȡ�����ֽ���
                byte[] b = new byte[1024];
                StringBuffer sb = new StringBuffer();
//                ��ʼ��ȡ
                while ((len = inputStream.read(b))!= -1) {
//                    ����ȡ��������׷�ӵ�StringBuffer��
                    sb.append(new String(b,0,len,"utf-8"));
                }
//               �ر������ͷŶ��󣨽�Լ��Դ��
                inputStream.close();
                httpURLConnection.disconnect();
                return sb.toString();
            }
            else {
                return "����ʧ�ܣ�";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "����URL��";
        }
    }
    /*
    * ��������String��
    * �����б�
    * */
//    ƴ�Ӳ�����ʽ�ķ���
    private String param(HashMap<String,Object> params) {
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
