package com.wangbei.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


/**
 * @author yuyidi 2017-07-21 13:45:51
 * @class com.wangbei.util.HttpClientUtil
 * @description 请使用已有的dao层rest http接口
 */
@Deprecated
public class HttpClientUtil {

    public static void main(String[] args) {
    }

    public static String sendPost(String url, String context) {
        CloseableHttpClient client = new DefaultHttpClient();
        CloseableHttpResponse response = null;
        try {
            HttpPost httpPost = new HttpPost(url);
            BasicHeader contentType = new BasicHeader("Content-Type", "application/json; charset=UTF-8");
            httpPost.setHeader(contentType);
            HttpEntity httpEntity = new StringEntity(context, "UTF-8");
            httpPost.setEntity(httpEntity);
            response = client.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            return EntityUtils.toString(responseEntity);
        } catch (Exception e) {
            throw new RuntimeException("do request exception!");
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                }
            }
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
