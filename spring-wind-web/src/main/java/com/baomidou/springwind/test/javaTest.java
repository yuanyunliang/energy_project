package com.baomidou.springwind.test;

import com.baomidou.framework.common.util.IDUtil;

/**
 * @author Blse
 * @date 2018/5/4
 * @description
 */
public class javaTest {

    public static void main(String[] args) {
        System.out.println(IDUtil.getUUID());
    }

    static int oppositeNum(int num) {
        int val = 0;
        int realNum = num;
        while(num != 0) {
            val = val * 10 + num % 10;
            num /= 10;
        }
        return val + realNum;
    }
}
