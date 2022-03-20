package com.example.week03.t1.client;

import com.alibaba.fastjson.JSONObject;
import com.example.week03.t1.gateway.dto.RequestDto;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * @author ltx
 * @date 2022/3/20 3:48 下午
 */
public class Client {

    public static void main(String[] args) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost getRequest = new HttpPost(new URI("http://localhost:8080"));
        RequestDto dto = new RequestDto();
        dto.setData("hello, netty");
        dto.setKey("key");

        HttpEntity entity = new StringEntity(JSONObject.toJSONString(dto), "utf-8");
        getRequest.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(getRequest);
        System.out.println(response);


//        CloseableHttpResponse execute = httpClient.execute(request);

//        BufferedInputStream stream = new BufferedInputStream(execute.getEntity().getContent());
//        byte[] res = new byte[1];
//        int offset = 0;
//        while ((offset = stream.read(res)) != -1) {
//            String str = new String(res, 0, offset, StandardCharsets.UTF_8);
//            System.out.print(str);
//        }
    }
}
