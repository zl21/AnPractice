package com.myapplication01.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by zhoul on 2019/1/2.
 */
public class HttpUtils {
    private final static String HOST = "http://120.77.242.43:8000/";
    public static String doPost(String method) {
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
}
