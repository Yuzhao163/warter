package com.water.water.util;

public class Int2Binary {
    public String int2binary(int num){
        int intlen = 8;
        String string = "";
        for (int i=0;i<intlen;i++){
            if (num%2 != 0){
                string = "1" + string;
            }
            else {
                string = "0" + string;
            }
            num = num/2;
        }
        return string;
    }
}
