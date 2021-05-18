package com.water.water.util;

/**
 * @author zhaoyu
 * @version 1.0
 * @date 2021/5/17 10:02
 */
public class ByteStringToInt {
    public Integer bytestringtoint(String resp){
        int res = 0;
        for(int i = resp.length()-1;i > 0;i--){
//            System.out.println(resp.charAt(i));
//            System.out.println(Math.abs(15-i));
//            System.out.println(Math.pow((resp.charAt(i)-48)*2,Math.abs(15-i)));
            if(resp.charAt(i)-48 != 0){
                int nowres = (int)Math.pow((resp.charAt(i)-48)*2,Math.abs(15-i));
                res = nowres + res;
            }else{
                res = 0 + res;
            }
        }
        return res;
    }

    public Integer bytestringtoint48(String resp){
        int res = 0;
        for(int i = resp.length()-1;i > 0;i--){
            int nowres = (int)Math.pow(resp.charAt(i)-48,i);
            res = nowres + res;
        }
        return res;
    }
}
