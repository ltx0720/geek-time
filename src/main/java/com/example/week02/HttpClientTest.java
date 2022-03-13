package com.example.week02;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * @author ltx
 * @date 2022/3/13 9:05 下午
 */
public class HttpClientTest {

    public static void main(String[] args) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet getRequest = new HttpGet(new URI("http://localhost:8001"));
        CloseableHttpResponse response = httpClient.execute(getRequest);

        if (response.getStatusLine().getStatusCode() != 200) {
            System.out.println("fail");
        }

        BufferedInputStream bufferedInputStream = new BufferedInputStream(response.getEntity().getContent());
        byte[] res = new byte[1024];
        int offset = 0;

        while ((offset = bufferedInputStream.read(res)) != -1) {
            String str = new String(res, 0, offset, StandardCharsets.UTF_8);
            System.out.print(str);
        }
    }
}
