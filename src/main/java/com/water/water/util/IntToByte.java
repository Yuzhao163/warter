package com.water.water.util;

/**
 * @author zhaoyu
 * @version 1.0
 * @date 2021/5/3 21:27
 */
public class IntToByte {
    public static byte[] intToBytes( int value )
    {
        byte[] src = new byte[2];
        src[1] =  (byte) (value & 0xFF);
        src[0] =  (byte) ((value>>8) & 0xFF);
        return src;
    }
}
