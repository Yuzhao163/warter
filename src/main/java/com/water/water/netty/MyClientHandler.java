package com.water.water.netty;

import com.water.water.dao.CommRecDao;
import com.water.water.pojo.CommRec;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import com.water.water.util.IntToByte;


import java.nio.charset.Charset;


public class MyClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private CommRec commrec;

    public MyClientHandler(CommRec commRec){
        commrec = commRec;
    }
    @Autowired
    private CommRecDao commRecDao;

    private int count;

    private IntToByte intToByte;

//    public void scheduled(){
//        channelActive();
//    }

    public String toString(Integer value){
        if(value != null){
            return value.toString();
        }else{
            return "0";
        }
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //使用客户端发送10条数据 hello,server 编号
        System.out.println("sss");
        //CommRec commRec = commRecDao.SendOrder();
        String TmnID = commrec.getTmnID();
        String D_ID = commrec.getD_ID();
        //Integer TmnId = Integer.valueOf(TmnID);
        //Integer d_id = Integer.valueOf(D_ID);
        Integer W_work = commrec.getW_work();
        Integer v_pre = commrec.getV_pre();
        Integer Ov_period = commrec.getOV_period();
        Integer OV_waterline= commrec.getOV_waterline();
        Integer OV_keeptime = commrec.getOV_keeptime();
        Integer CV_waterline = commrec.getCV_waterline();
        Integer V_actiontime = commrec.getV_actiontime();
        //byte[] tmnid = intToByte.intToBytes(TmnId);
        //byte[] d_iD = intToByte.intToBytes(d_id);
        String buf =
                TmnID + D_ID + toString(W_work) + toString(v_pre) + toString(Ov_period) + toString(OV_waterline) + toString(OV_keeptime) + toString(CV_waterline) + toString(V_actiontime);
        ByteBuf buffer = Unpooled.copiedBuffer(buf, Charset.forName("utf-8"));
        //ctx.writeAndFlush(tmnid);
        ctx.writeAndFlush(buffer);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);

        String message = new String(buffer, Charset.forName("utf-8"));
        System.out.println("客户端接收到消息=" + message);
        System.out.println("客户端接收消息数量=" + (++this.count));

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
