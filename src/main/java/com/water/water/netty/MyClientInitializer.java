package com.water.water.netty;

import com.water.water.pojo.CommRec;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    private CommRec commrec;

    public MyClientInitializer(CommRec commRec){
        commrec = commRec;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new MyClientHandler(commrec));
    }
}
