package com.example.week03.t1.gateway.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.week03.t1.gateway.dto.RequestDto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.FullHttpMessage;

import java.util.List;

/**
 * @author ltx
 * @date 2022/3/20 6:34 下午
 */
public class DecoderHandler extends MessageToMessageDecoder<FullHttpMessage> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, FullHttpMessage fullHttpMessage, List<Object> list) throws Exception {
        ByteBuf content = fullHttpMessage.content();
        byte[] res = new byte[content.readableBytes()];
        content.readBytes(res);
        RequestDto requestDto = JSONObject.parseObject(new String(res), RequestDto.class);
        list.add(requestDto);
    }
}
