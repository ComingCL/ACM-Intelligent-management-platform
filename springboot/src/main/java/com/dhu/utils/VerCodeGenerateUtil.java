package com.dhu.utils;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

/**
 * Author: ComingLiu
 * Date: 2022/5/9 14:09
 */
public class VerCodeGenerateUtil {
    private static final String SYMBOLS = "0123456789";
    private static final Random RANDOM = new SecureRandom();
    public static String generateVerCode(){
        char[] nonceChars = new char[6];
        for(int i=0;i<nonceChars.length;i++){
            nonceChars[i] = SYMBOLS.charAt(RANDOM.nextInt(nonceChars.length));
        }
        return new String(nonceChars);
    }
    /**
     * 计算两个日期的分钟差
     */
    public static int getMinute(Date from, Date to){
        return (int)(to.getTime() - from.getTime()) / (60 * 1000);
    }
}
