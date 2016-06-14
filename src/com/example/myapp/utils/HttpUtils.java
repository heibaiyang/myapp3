package com.example.myapp.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by 李钊颖 on 2016/5/12.
 */
public class HttpUtils {
    public static String getString(String address) {
        StringBuffer resultStr=null;
        InputStream is=null;
        try{
            resultStr=new StringBuffer();
            URL myurl=new URL(address);
            HttpURLConnection connection=(HttpURLConnection) myurl.openConnection();
            is=connection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String readerStr="";
            while((readerStr=br.readLine())!=null)
            {
                resultStr.append(readerStr);
            }
            br.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(is!=null)
            {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultStr.toString();
    }

    public static String postDate(String url, List<NameValuePair> params){
        try{
            HttpEntity requstEntity=new UrlEncodedFormEntity(params);
            HttpPost httpPost=new HttpPost(url);
            httpPost.setEntity(requstEntity);
            HttpClient httpClient=new DefaultHttpClient();
            HttpResponse httpResponse=httpClient.execute(httpPost);
            if(httpResponse==null) return "";
            HttpEntity responseEntity=httpResponse.getEntity();
            InputStream inputStream=responseEntity.getContent();
            BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer buffer=new StringBuffer();
            String line="";
            while((line=reader.readLine())!=null)
            {
                buffer.append(line);
            }
            return buffer.toString();
        }
        catch (Exception e)
        {

        }
        return "";
    }
}
