package com.example.week03.t1.gateway.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.week03.t1.gateway.dto.RequestDto;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author ltx
 * @date 2022/3/20 4:52 下午
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RequestDto requestDto = (RequestDto) msg;
        if (!requestDto.checkKey()) {
            ctx.close();
        } else {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, requestDto);
        }
    }
}

