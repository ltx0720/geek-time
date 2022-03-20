package com.example.week03.t1.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.apache.http.HttpRequest;

/**
 * @author ltx
 * @date 2022/3/20 11:39 下午
 */
public class HeaderHttpRequestFilter {

    public void filter(HttpRequest httpRequest) {
        httpRequest.setHeader("mao", "soul");
    }
}
