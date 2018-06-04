package com.baomidou.framework.common.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author Blse
 * @date 2018/5/2
 * @description     ID工具类
 */
public class IDUtil {

    /**
     *  默认生成的ID的位数
     */
    static final int NUM = 15;

    /**
     * 生成一个32位的UUID
     * @return
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /**
     * 生成一个13位数的不重复的ID
     * @return
     */
    public static Long getID() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上随机数
        Random random = new Random();
        int end2 = random.nextInt(999999999);
        //如果不足指定位数则前面补0
        Long id = millis +  Long.valueOf(String.format("%02d", end2));
        return id;
    }

}
