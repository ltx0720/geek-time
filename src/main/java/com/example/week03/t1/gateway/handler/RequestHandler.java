package com.example.week03.t1.gateway.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.week03.t1.gateway.dto.RequestDto;
import com.example.week03.t1.gateway.filter.HeaderHttpRequestFilter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * @author ltx
 * @date 2022/3/20 4:51 下午
 */
public class RequestHandler extends ChannelInboundHandlerAdapter {

    private final HeaderHttpRequestFilter filter = new HeaderHttpRequestFilter();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RequestDto dto = (RequestDto) msg;
        ByteBuf byteBuf = Unpooled.copiedBuffer(JSONObject.toJSONString(send(dto)).getBytes(StandardCharsets.UTF_8));
        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8")
        .set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());
        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
    }

    private String send(RequestDto dto) throws URISyntaxException, IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost(new URI("http://localhost:8001"));

        filter.filter(request);

        CloseableHttpResponse response = httpClient.execute(request);
        InputStream inputStream = response.getEntity().getContent();
        byte[] res = new byte[1024];
        inputStream.read(res);
        return new String(res, "utf-8");
    }
}
