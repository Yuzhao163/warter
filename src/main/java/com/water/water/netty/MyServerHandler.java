package com.water.water.netty;

import com.water.water.pojo.CommRec;
import com.water.water.service.DetailService;
import com.water.water.pojo.Rec_Detail;
import com.water.water.util.Int2Binary;
import com.water.water.util.connector;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;
import com.water.water.util.ByteStringToInt;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@ChannelHandler.Sharable
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf>{

    com.water.water.util.connector connector = new connector();

    public Connection conn = connector.ConnectToMySql();

    public ByteStringToInt byteStringToInt = new ByteStringToInt();

    //public ByteStringToInt bToI = byteStringToInt.bytestringtoint(String
    // resp);

    Int2Binary int2Binary = new Int2Binary();
    //DetailService detailService;

    public static Map<String,ChannelHandlerContext> ctxId = new HashMap<String,ChannelHandlerContext>();

//    public MyServerHandler(DetailService detailService){
//        this.detailService = detailService;
//    }
    //接受数据，解析数据，合并数据，连接数据库并写入数据库
    //1.接收数据
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
    //将数据解析
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        System.out.println("Client IP is " + ctx.channel().remoteAddress());
        System.out.println("ctxId " + ctxId);
        System.out.println("msg类型"+msg.getClass().toString());
        System.out.println("msg.toString();"+msg.toString());
        System.out.println("msg的长度为"+msg.toString().length());
        System.out.println();
        if(msg.readableBytes() == 40){
            System.out.println(ctx);
            ctx.channel().localAddress();
            ctxId.put(ctx.channel().remoteAddress().toString(),ctx);
            //msg.toString();
            //根据通信协议制定数据解析策略
            String Ip = ctx.channel().remoteAddress().toString();
            int[] communication = new int[]{2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 4};
            //解析后的数据放入数据准备插入数据库
            String[] stringbuffer = new String[31];
            Rec_Detail recDetail = new Rec_Detail();
            //readableBytes方法，将msg内容放入buffer的二进制数组中
            byte[] buffer = new byte[msg.readableBytes()];
            //放入
            msg.readBytes(buffer);
            //遍历初始量
            System.out.println("buffer"+buffer);
            for(int z = 0;z < buffer.length;z++){
                System.out.println("buffer"+z+":::"+buffer[z]);
            }
            int i = 0;
            int k = 0;
            //工具类，整数转换为二进制
            try {
                //遍历buffer二进制数组，将数据提取出来
                while (i < buffer.length && k < 31) {
                    //
                    String buffer_num = "";
                    String buffer_nums = "";
                    //提取出来的数据放入实体类
                    //根据通信协议遍历二进制数组，将多字节数据合并并提取
                    for (int j = 0; j < communication[k]; j++) {
                        //int buffer_temp1 = buffer[i] - 48;
                        int buffer_temp1 = buffer[i];
                        //System.out.print(buffer_temp1);
                        //System.out.println
                        // ("buffer_temp1[]"+"第"+i+"个"+buffer_temp1);
                        buffer_num = int2Binary.int2binary(buffer_temp1 - 48);
                        //buffer_num = int2Binary.int2binary(buffer_temp1 - 48);
                        buffer_nums = buffer_nums + buffer_num;
                        i++;
                    }
                    int result = byteStringToInt.bytestringtoint(buffer_nums);
//                if(buffer_nums.charAt(buffer_nums.length()-1) == '1'){
//                    int result = Integer.parseInt(buffer_nums, 2);
//                }else{
//                    buffer_nums
//                    int result = Integer.parseInt(buffer_nums, 2);
//                }
                    //int result = Integer.parseInt("11111100", 2);
                    //System.out.println("第"+i+"次结果是：=" + result);
                    stringbuffer[k] = Integer.toString(result);
                    System.out.println(stringbuffer);
                    k++;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
//        Date date = new Date();
//        //设置要获取到什么样的时间
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//        //获取String类型的时间
//        String createdate = sdf.format(date);
//        recDetail.setPackageID(Long.parseLong(createdate));
            recDetail.setTmnID(stringbuffer[0]);
            recDetail.setD_ID(stringbuffer[1]);
            recDetail.setV_status(Short.parseShort(stringbuffer[6]));
            recDetail.setV_per(Short.parseShort(stringbuffer[7]));
            recDetail.setW_line(Short.parseShort(stringbuffer[8]));
            recDetail.setB_status(Short.parseShort(stringbuffer[9]));
            recDetail.setO_temp(Short.parseShort(stringbuffer[10]));
            recDetail.setE_temp(Short.parseShort(stringbuffer[11]));
            recDetail.setD_doorsta(Short.parseShort(stringbuffer[12]));
            recDetail.setW_work(Short.parseShort(stringbuffer[17]));
            recDetail.setF_Volume(Short.parseShort(stringbuffer[18]));
            recDetail.setOV_period(Short.parseShort(stringbuffer[19]));
            recDetail.setOV_waterline(Short.parseShort(stringbuffer[20]));
            recDetail.setOV_keeptime(Short.parseShort(stringbuffer[21]));
            recDetail.setCV_waterline(Short.parseShort(stringbuffer[22]));
            recDetail.setV_actiontime(Short.parseShort(stringbuffer[23]));
            //PLC上传数据周期
            recDetail.setUD_period(Short.parseShort(stringbuffer[24]));
            recDetail.setC_times(Short.parseShort(stringbuffer[25]));
            recDetail.setSend_error(Short.parseShort(stringbuffer[26]));
            recDetail.setRece_error(Short.parseShort(stringbuffer[27]));
            UpdateRec(conn,recDetail,Ip);
        }
        else{
            //readableBytes方法，将msg内容放入buffer的二进制数组中
            byte[] buffer = new byte[msg.readableBytes()];
            //放入
            msg.readBytes(buffer);
            //遍历初始量
            for(int z = 0;z < buffer.length;z++){
                System.out.println("bufferresp"+z+":::"+buffer[z]);
            }
        }
    }

    public Boolean SelectAllTest(Connection conn,String sql)
    {
        try {
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql);

            while(rs.next())
            {
                //System.out.println("UserName:"+rs.getString("TmnID"));
                return true;
            }
            rs.close();
            st.close();

            //conn.close();
        }catch (Exception e)
        {
            //return null;
        }
        return false;
    }

        public void InsertTest(Connection conn,Long PackageID,
                               String PackagePP,String TmnID,String IP)
    {
        try {
            String sqlStr="INSERT INTO td_pack_list(PackageID,PackagePP," +
                    "TmnID,PeerAddress) VALUES(?,?,?,?)";
            PreparedStatement st=conn.prepareStatement(sqlStr);
            st.setLong(1,PackageID);
            st.setString(2,PackagePP);
            st.setString(3,TmnID);
            st.setString(4,IP);
            st.executeUpdate();
            //rs.close();
            st.close();
            //conn.close();
        }catch (Exception e)
        {
            System.err.println(e);
        }
    }

    public void UpdateRec(Connection conn,Rec_Detail rec_detail,String Ip){
        try {
            Date date = new Date();
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            //获取String类型的时间
            String createdate = sdf.format(date);
            rec_detail.setPackageID(Long.parseLong(createdate));
            Long PackageID = rec_detail.getPackageID();
            String TmnID = rec_detail.getTmnID();
            String D_ID = rec_detail.getD_ID();
            Short V_Status = rec_detail.getV_status();
            Short V_per = rec_detail.getV_per();
            Short W_line = rec_detail.getV_per();
            Short B_status = rec_detail.getV_per();
            Short O_temp = rec_detail.getV_per();
            Short E_temp = rec_detail.getV_per();
            Short D_doorsta = rec_detail.getV_per();
            Short W_work = rec_detail.getV_per();
            Short F_Volume = rec_detail.getV_per();
            Short OV_period = rec_detail.getV_per();
            Short OV_waterline = rec_detail.getV_per();
            Short OV_keeptime = rec_detail.getV_per();
            Short CV_waterline = rec_detail.getV_per();
            Short V_actiontime = rec_detail.getV_per();
            Short UD_period = rec_detail.getV_per();
            Short C_times = rec_detail.getV_per();
            Short Send_error = rec_detail.getV_per();
            Short Rece_error = rec_detail.getV_per();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String Create_time = df.format(date);
            String Update_time = df.format(date);
            String update_time = "'" + df.format(date) + "'";
            String updateSqlStr = "INSERT INTO td_rec_detail" +
                    "        (PackageID,TmnID,D_ID,V_Status,V_per,W_line," +
                    "B_status,O_temp,E_temp,D_doorsta,W_work,F_Volume," +
                    "OV_period,OV_waterline,OV_keeptime,CV_waterline," +
                    "V_actiontime,UD_period,C_times,Send_error,Rece_error," +
                    "Create_time,Update_time)" +
                    "        VALUES ('"+PackageID+"','" + TmnID + "','" + D_ID +
                    "','" + V_Status + "','" + V_per + "','" + W_line + "','" + B_status +
                    "','" + O_temp + "','" + E_temp + "','"+D_doorsta+"','"+W_work+"','"+
                    F_Volume+"','"+OV_period+"','" + OV_waterline + "','" + OV_keeptime + "','" +
                    CV_waterline + "','" + V_actiontime + "','" + UD_period + "','" + C_times + "','" +
                    Send_error + "','" + Rece_error + "','" + Create_time +
                    "','" + Update_time + "') ON DUPLICATE KEY UPDATE " +
                    "PackageID =" + PackageID + ", D_ID = " + D_ID + ", V_Status " +
                    "= " + V_Status + ", V_per = " + V_per + ", W_line = " + W_line +
                    ", B_status = " + B_status + ", O_temp = " + O_temp + ", E_temp = " + E_temp + ", D_doorsta = " + D_doorsta +
                    ", W_work = " + W_work + ", F_Volume = " + F_Volume + ", OV_period = " + OV_period + ", OV_waterline = " + OV_waterline +
                    ", OV_keeptime = " + OV_keeptime + ", CV_waterline = " + CV_waterline + ", V_actiontime = " + V_actiontime+
                    ", UD_period = " + UD_period + ", C_times = " + C_times + ", Send_error = " + Send_error + ", Rece_error = " + Rece_error +
                    ",Update_time = " + update_time;
            PreparedStatement preparedStatement = conn.prepareStatement(updateSqlStr);
            String selecttmn = "SELECT * FROM td_pack_list WHERE TmnID = " + TmnID + " AND PackagePP = 0;";
            if(SelectAllTest(conn,selecttmn)){
                String updatetoIp = "UPDATE td_pack_list set PeerAddress = " + "'" + Ip + "'" + " where TmnID = " + TmnID + ";";
//                update td_areas set AreaLeader=#{AreaLeader} where AreaID = #{AreaID};
                PreparedStatement preupdatetoIp = conn.prepareStatement(updatetoIp);
                preupdatetoIp.executeUpdate();
            }else{
                InsertTest(conn,PackageID,"0",TmnID,Ip);
//                String inserttoIp = "INSERT INTO td_pack_list (PackageID," +
//                        "PackagePP,TmnID,PeerAddress) VALUES (" +  PackageID + "0" + TmnID + Ip;
            }
//            preparedStatement.setString(1,newPassword);
//            preparedStatement.setString(2,username);
            preparedStatement.executeUpdate();
//            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String toString(Integer value){
        if(value != null || value != 0){
            Integer values = value;
            return  (values.toString());
        }else{
            return "0";
        }
    }


    public void close() throws Exception{
        for (String IP : ctxId.keySet()) {
            ChannelHandlerContext ctx = ctxId.get(IP);
            System.out.println("Value = " + ctx);
            System.out.println("一键关阀进行中");
            //CommRec commRec = commRecDao.SendOrder();
//            String TmnID = commrec.getTmnID();
//            String D_ID = commrec.getD_ID();
//            //Integer TmnId = Integer.valueOf(TmnID);
//            //Integer d_id = Integer.valueOf(D_ID);
//            Integer W_work = commrec.getW_work();
//            //Integer v_pre = commrec.getV_pre();
//            Integer Ov_period = commrec.getOV_period();
//            Integer OV_waterline= commrec.getOV_waterline();
//            Integer OV_keeptime = commrec.getOV_keeptime();
//            Integer CV_waterline = commrec.getCV_waterline();
//            Integer V_actiontime = commrec.getV_actiontime();
            //byte[] tmnid = intToByte.intToBytes(TmnId);
            //byte[] d_iD = intToByte.intToBytes(d_id);
            byte[] buff = new byte[22];
            //buff[b] = TmnID;
//            if(Integer.parseInt(TmnID) > 256){
//                buff[0] = (byte)(Integer.parseInt(TmnID) >> 8);
//                buff[1] = (byte)(Integer.parseInt(TmnID));
//            }else{
//                buff[0] = (byte)0;
//                buff[1] = (byte)Integer.parseInt(TmnID);
//            }
//            buff[2] = (byte)Integer.parseInt(D_ID);
//            buff[3] = (byte)(int)(W_work);
//            buff[4] = (byte)0;
//            if(Ov_period > 256){
//                buff[5] = (byte)(Ov_period >> 8);
//                buff[6] = (byte)(int)(Ov_period);
//            }else{
//                buff[5] = (byte)0;
//                buff[6] = (byte)(int)(Ov_period);
//            }
//            if(OV_waterline > 256){
//                buff[7] = (byte)(OV_waterline >> 8);
//                buff[8] = (byte)(int)(OV_waterline);
//            }else{
//                buff[7] = (byte)0;
//                buff[8] = (byte)(int)(OV_waterline);
//            }
//            if(OV_keeptime > 256){
//                buff[9] = (byte)(OV_keeptime >> 8);
//                buff[10] =  (byte)(int)(OV_keeptime);
//            }else{
//                buff[9] = (byte)0;
//                buff[10] = (byte)(int)(OV_keeptime);
//            }
//            if(CV_waterline > 256){
//                buff[11] = (byte)(CV_waterline >> 8);
//                buff[12] = (byte)(int)(CV_waterline);
//            }else{
//                buff[11] = (byte)0;
//                buff[12] = (byte)(int)(CV_waterline);
//            }
            //buff[13] = (byte)(int)(V_actiontime);
            buff[0] = (byte)0;
            buff[1] = (byte)0;
            buff[2] = (byte)0;
            buff[3] = (byte)0;
            buff[4] = (byte)0;
            buff[5] = (byte)0;
            buff[6] = (byte)0;
            buff[7] = (byte)0;
            buff[8] = (byte)0;
            buff[9] = (byte)0;
            buff[10] = (byte)0;
            buff[11] = (byte)0;
            buff[12] = (byte)0;
            buff[13] = (byte)0;
            buff[14] = (byte)0;
            buff[15] = (byte)0;
            buff[16] = (byte)0;
            buff[17] = (byte)0;
            buff[18] = (byte)0;
            buff[19] = (byte)0;
            buff[20] = (byte)0;
            buff[21] = (byte)0;
            ByteBuf buffer = Unpooled.buffer();
            buffer.writeBytes(buff);
            ctx.writeAndFlush(buffer);
        }
    }

    public void sendall(Integer openorclose) throws Exception{
        for (String IP : ctxId.keySet()) {
            ChannelHandlerContext ctx = ctxId.get(IP);
            System.out.println("Value = " + ctx);
            Integer V_pre = 0;
            //0为关
            V_pre = openorclose;
            System.out.println("一键开关阀进行中");
            //CommRec commRec = commRecDao.SendOrder();
//            String TmnID = commrec.getTmnID();
//            String D_ID = commrec.getD_ID();
//            //Integer TmnId = Integer.valueOf(TmnID);
//            //Integer d_id = Integer.valueOf(D_ID);
//            Integer W_work = commrec.getW_work();
//            //Integer v_pre = commrec.getV_pre();
//            Integer Ov_period = commrec.getOV_period();
//            Integer OV_waterline= commrec.getOV_waterline();
//            Integer OV_keeptime = commrec.getOV_keeptime();
//            Integer CV_waterline = commrec.getCV_waterline();
//            Integer V_actiontime = commrec.getV_actiontime();
            //byte[] tmnid = intToByte.intToBytes(TmnId);
            //byte[] d_iD = intToByte.intToBytes(d_id);
            byte[] buff = new byte[22];
            //buff[b] = TmnID;
//            if(Integer.parseInt(TmnID) > 256){
//                buff[0] = (byte)(Integer.parseInt(TmnID) >> 8);
//                buff[1] = (byte)(Integer.parseInt(TmnID));
//            }else{
//                buff[0] = (byte)0;
//                buff[1] = (byte)Integer.parseInt(TmnID);
//            }
//            buff[2] = (byte)Integer.parseInt(D_ID);
//            buff[3] = (byte)(int)(W_work);
//            buff[4] = (byte)0;
//            if(Ov_period > 256){
//                buff[5] = (byte)(Ov_period >> 8);
//                buff[6] = (byte)(int)(Ov_period);
//            }else{
//                buff[5] = (byte)0;
//                buff[6] = (byte)(int)(Ov_period);
//            }
//            if(OV_waterline > 256){
//                buff[7] = (byte)(OV_waterline >> 8);
//                buff[8] = (byte)(int)(OV_waterline);
//            }else{
//                buff[7] = (byte)0;
//                buff[8] = (byte)(int)(OV_waterline);
//            }
//            if(OV_keeptime > 256){
//                buff[9] = (byte)(OV_keeptime >> 8);
//                buff[10] =  (byte)(int)(OV_keeptime);
//            }else{
//                buff[9] = (byte)0;
//                buff[10] = (byte)(int)(OV_keeptime);
//            }
//            if(CV_waterline > 256){
//                buff[11] = (byte)(CV_waterline >> 8);
//                buff[12] = (byte)(int)(CV_waterline);
//            }else{
//                buff[11] = (byte)0;
//                buff[12] = (byte)(int)(CV_waterline);
//            }
            //buff[13] = (byte)(int)(V_actiontime);
            buff[0] = (byte)0;
            buff[1] = (byte)0;
            buff[2] = (byte)0;
            buff[3] = (byte)0;
            buff[4] = (byte)(int)V_pre;
            buff[5] = (byte)0;
            buff[6] = (byte)0;
            buff[7] = (byte)0;
            buff[8] = (byte)0;
            buff[9] = (byte)0;
            buff[10] = (byte)0;
            buff[11] = (byte)0;
            buff[12] = (byte)0;
            buff[13] = (byte)0;
            buff[14] = (byte)0;
            buff[15] = (byte)0;
            buff[16] = (byte)0;
            buff[17] = (byte)0;
            buff[18] = (byte)0;
            buff[19] = (byte)0;
            buff[20] = (byte)0;
            buff[21] = (byte)0;
            ByteBuf buffer = Unpooled.buffer();
            buffer.writeBytes(buff);
            ctx.writeAndFlush(buffer);
        }
    }

    public void channelActive(CommRec commrec,String IP) throws Exception {
        ChannelHandlerContext ctx = ctxId.get(IP);
        //使用客户端发送10条数据 hello,server 编号
        System.out.println("一键开阀进行中");
        //CommRec commRec = commRecDao.SendOrder();
        String TmnID = commrec.getTmnID();
        String D_ID = commrec.getD_ID();
        //Integer TmnId = Integer.valueOf(TmnID);
        //Integer d_id = Integer.valueOf(D_ID);
        Integer W_work = commrec.getW_work();
        //Integer v_pre = commrec.getV_pre();
        Integer Ov_period = commrec.getOV_period();
        Integer OV_waterline= commrec.getOV_waterline();
        Integer OV_keeptime = commrec.getOV_keeptime();
        Integer CV_waterline = commrec.getCV_waterline();
        Integer V_actiontime = commrec.getV_actiontime();
        //byte[] tmnid = intToByte.intToBytes(TmnId);
        //byte[] d_iD = intToByte.intToBytes(d_id);
        byte[] buff = new byte[22];
            //buff[b] = TmnID;
        if(Integer.parseInt(TmnID) > 256){
            buff[0] = (byte)(Integer.parseInt(TmnID) >> 8);
            buff[1] = (byte)(Integer.parseInt(TmnID));
        }else{
            buff[0] = (byte)0;
            buff[1] = (byte)Integer.parseInt(TmnID);
        }
        buff[2] = (byte)Integer.parseInt(D_ID);
        buff[3] = (byte)(int)(W_work);
        buff[4] = (byte)0;
        if(Ov_period > 256){
            buff[5] = (byte)(Ov_period >> 8);
            buff[6] = (byte)(int)(Ov_period);
        }else{
            buff[5] = (byte)0;
            buff[6] = (byte)(int)(Ov_period);
        }
        if(OV_waterline > 256){
            buff[7] = (byte)(OV_waterline >> 8);
            buff[8] = (byte)(int)(OV_waterline);
        }else{
            buff[7] = (byte)0;
            buff[8] = (byte)(int)(OV_waterline);
        }
        if(OV_keeptime > 256){
            buff[9] = (byte)(OV_keeptime >> 8);
            buff[10] =  (byte)(int)(OV_keeptime);
        }else{
            buff[9] = (byte)0;
            buff[10] = (byte)(int)(OV_keeptime);
        }
        if(CV_waterline > 256){
            buff[11] = (byte)(CV_waterline >> 8);
            buff[12] = (byte)(int)(CV_waterline);
        }else{
            buff[11] = (byte)0;
            buff[12] = (byte)(int)(CV_waterline);
        }
        buff[13] = (byte)(int)(V_actiontime);
        buff[14] = (byte)0;
        buff[15] = (byte)0;
        buff[16] = (byte)0;
        buff[17] = (byte)0;
        buff[18] = (byte)0;
        buff[19] = (byte)0;
        buff[20] = (byte)0;
        buff[21] = (byte)0;
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(buff);
        ctx.writeAndFlush(buffer);
    }


//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg)
//            throws Exception {
//        String response = (String) msg;
//        System.out.println("Client: " + response);
//    }
}