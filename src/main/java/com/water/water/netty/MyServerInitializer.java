package com.water.water.netty;

import com.water.water.service.DetailService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    //DetailService detailService;
    @Autowired
    private MyServerHandler myServerHandler;
//    public MyServerInitializer(DetailService detailService){
//        this.detailService = detailService;
//    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(new Myfix(3,40));
        //pipeline.addLast(new MyServerFixedLengthFrameDecoder(3,40));
        //pipeline.addLast(new MyServerHandler(detailService));
        pipeline.addLast(myServerHandler);
    }
}
