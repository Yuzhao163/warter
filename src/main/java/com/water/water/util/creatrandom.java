package com.water.water.util;

public class creatrandom {
    Int2Binary int2Binary = new Int2Binary();
    public int onetworan(){
        int rand_num = (int)((Math.random() + 1) * 2);
        return rand_num;
    }
    //生成tmnid的随机数
    public int ran(int num){
        int rand_num = (int)((Math.random()+1) * num);
        return rand_num;
    }
    //当他是1111111111111111111的时候无法进行判断
    public String code(){
        String tmnid = int2Binary.int2binary(ran(65535));
        String did = int2Binary.int2binary(ran(200));;
        String V_status = int2Binary.int2binary(ran(2) * 10);
        String V_per = int2Binary.int2binary(ran(100));
        String W_line = int2Binary.int2binary(ran(100));
        String B_status = int2Binary.int2binary(ran(2) * 10);
        String O_temp = int2Binary.int2binary(ran(2) * 10);
        String E_temp = int2Binary.int2binary(ran(256));
        String D_doorsta = int2Binary.int2binary(ran(2) * 10);
        String W_work = int2Binary.int2binary(ran(4));
        if(W_work.equals(1)){
            W_work = int2Binary.int2binary(11);
        }else if(W_work.equals(2)){
            W_work = int2Binary.int2binary(21);
        }else if(W_work.equals(3)){
            W_work = int2Binary.int2binary(22);
        }else{
            W_work = int2Binary.int2binary(23);
        }
        String F_Volume = int2Binary.int2binary(ran(100));
        String OV_period = int2Binary.int2binary(ran(200));
        String OV_waterline = int2Binary.int2binary(ran(200));
        String OV_keeptime = int2Binary.int2binary(ran(500));
        String CV_waterline = int2Binary.int2binary(ran(100));
        String V_actiontime = int2Binary.int2binary(ran(500));
        String UD_period = int2Binary.int2binary(ran(500));
        String C_times = int2Binary.int2binary(ran(500));
        String Send_error = int2Binary.int2binary(ran(500));
        String Rece_error = int2Binary.int2binary(ran(500));
        String res =
                tmnid + did + "00000000000000000000000000000000" + V_status + V_per + W_line + B_status + O_temp + E_temp + D_doorsta + "00000000000000000000000000000000" + W_work + F_Volume + OV_period + OV_waterline + OV_keeptime+CV_waterline+V_actiontime+UD_period+C_times+Send_error+Rece_error+"0000000000000000000000000000000000000000000000000000000";
        return res;
    }

//    public static void main(String args){
//        System.out.println(onetworan());
//    }
}
