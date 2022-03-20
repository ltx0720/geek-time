package com.example.week03.t1.gateway;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import com.example.week03.t1.gateway.handler.*;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


/**
 * @author ltx
 * @date 2022/3/20 3:48 下午
 */
public class NettyGateWay {

    private final static int SERVER_PORT = 8080;

    private final static int CLIENT_PORT = 8081;

    public static void main(String[] args) throws InterruptedException {

        // server
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(5);

        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast("http_decoder", new HttpRequestDecoder());
                        ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65535));
                        ch.pipeline().addLast("http_encoder", new HttpResponseEncoder());
                        ch.pipeline().addLast(new DecoderHandler());
                        ch.pipeline().addLast(new AuthHandler());
                        ch.pipeline().addLast(new RequestHandler());
                        ch.pipeline().addLast(new HttpResponseEncoder());

//                        ch.pipeline().addLast("my_decoder", new DecoderHandler());
//                        ch.pipeline().addLast("auth_handler", new AuthHandler());
//                        ch.pipeline().addLast("request_handler", new RequestHandler());
                    }
                });
        Channel ch = serverBootstrap.bind(SERVER_PORT).sync().channel();
    }

}
