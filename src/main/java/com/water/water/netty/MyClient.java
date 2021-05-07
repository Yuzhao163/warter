package com.water.water.netty;

import com.water.water.pojo.CommRec;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MyClient{

//    @Scheduled(fixedRate=1000)
//    @Scheduled(cron = "0/10 * * * * *")
//    public void sche() throws Exception{
//        afterPropertiesSet();
//    }

//    @Scheduled(cron = "0/10 * * * * *")
    public void timer(CommRec commRec) throws Exception {
//        LocalDateTime localDateTime = LocalDateTime.now();
//        System.out.println("当前时间为："+localDateTime.format((DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//    }
        EventLoopGroup group = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new MyClientInitializer(commRec)); //自定义一个初始化类

            //ChannelFuture channelFuture = bootstrap.connect("219.224.48.126", 5678).sync();
            ChannelFuture channelFuture = bootstrap.connect("localhost", 8000).sync();

            channelFuture.channel().closeFuture().sync();

        } finally {
            group.shutdownGracefully();
        }
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("1");
//        EventLoopGroup group = new NioEventLoopGroup();
//
//        try {
//
//            Bootstrap bootstrap = new Bootstrap();
//            bootstrap.group(group).channel(NioSocketChannel.class)
//                    .handler(new MyClientInitializer()); //自定义一个初始化类
//
//            //ChannelFuture channelFuture = bootstrap.connect("219.224.48.126", 5678).sync();
//            ChannelFuture channelFuture =
//                    bootstrap.connect("localhost", 8111).sync();
//
//            channelFuture.channel().closeFuture().sync();
//
//        }finally {
//            group.shutdownGracefully();
//        }
//    }
    }
}
