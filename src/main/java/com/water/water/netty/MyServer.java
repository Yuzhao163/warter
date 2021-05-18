package com.water.water.netty;

import com.water.water.service.DetailService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("MyServer")
public class MyServer implements Runnable{
    //在bean初始化后启动服务器。
//    @Autowired
//    private DetailService detailService;
    @Autowired
    private MyServerInitializer myServerInitializer;

    @Override
    public void run(){
        System.out.println("QIDONG");
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(myServerInitializer); //自定义一个初始化类

            System.out.println("绑定了端口号");
            ChannelFuture channelFuture = serverBootstrap.bind(5678).sync();
            channelFuture.channel().closeFuture().sync();

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
    //定时任务的方式 @Scheduled(cron = "0/10 * * * * *")

