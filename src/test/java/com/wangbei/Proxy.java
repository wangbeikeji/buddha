package com.wangbei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Proxy {

    public static void main(String[] args) {
        Map<String, Integer> ips = new HashMap<>();
        ips.put("192.129.200.202",9001);
//        ips.put("118.117.136.83",9000);
//        ips.put("58.251.249.243",8118);
//        ips.put("222.208.78.31",9000);
//        ips.put("117.25.190.192",808);
//        ips.put("121.31.149.198",8123);
//        ips.put("121.232.147.173",9000);
//        ips.put("113.248.14.123",8998);
//        ips.put("60.178.139.70",8081);
//        ips.put("121.31.151.205",8123);
//        ips.put("122.96.59.106",83);
//        ips.put("58.251.249.122",8118);
//        ips.put("121.232.147.159",9000);
//        ips.put("171.39.40.55",8123);
//        ips.put("110.73.3.60",8123);
//        ips.put("122.96.59.106",80);
//        ips.put("117.90.5.233",9000);
//        ips.put("121.232.145.156",9000);
//        ips.put("139.196.36.156",3128);
//        ips.put("123.169.84.251",808);
//        ips.put("121.232.145.10",9000);
//        ips.put("117.90.7.34",9000);
//        ips.put("117.64.237.14",3128);
//        ips.put("115.217.8.210",80);
//        ips.put("120.236.178.117",8118);
//        ips.put("121.232.144.147",9000);
//        ips.put("125.117.131.167",9000);
//        ips.put("117.90.0.125",9000);
//        ips.put("60.178.130.111",8081);
//        ips.put("125.67.72.66",9000);
        for (Map.Entry<String, Integer> entry : ips.entrySet()) {
            // 如果不设置，只要代理IP和代理端口正确,此项不设置也可以
            System.getProperties().setProperty("http.proxyHost", entry.getKey());
            System.getProperties().setProperty("http.proxyPort", String.valueOf(entry.getValue()));
            // 判断代理是否设置成功
            // 发送 GET 请求
            System.out.println(sendGet("http://t66y.com/?yi06302514",null));
        }

        // 发送 POST 请求
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
//            connection.setConnectTimeout(3*1000);
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }
}