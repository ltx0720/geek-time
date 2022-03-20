package com.example.week03.t1.gateway.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.week03.t1.gateway.dto.RequestDto;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

import java.nio.charset.StandardCharsets;

/**
 * @author ltx
 * @date 2022/3/20 4:51 下午
 */
public class RequestHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RequestDto dto = (RequestDto) msg;
        ByteBuf byteBuf = Unpooled.copiedBuffer(JSONObject.toJSONString(dto).getBytes(StandardCharsets.UTF_8));
        FullHttpResponse resp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
        resp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8")
        .set(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

        ctx.writeAndFlush(resp).addListener(ChannelFutureListener.CLOSE);
    }
}
