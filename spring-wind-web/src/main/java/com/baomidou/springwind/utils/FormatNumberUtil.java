package com.baomidou.springwind.utils;

import java.text.DecimalFormat;

/**
 * 描述：
 *
 * @author XieYongJie
 * @since 2018/6/1
 */
public class FormatNumberUtil {
    public static double getTwoDecimal(String number){
        return getTwoDecimal(Double.parseDouble(number));
    }

    public static  double getTwoDecimal(double number){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String temp = decimalFormat.format(number);
        return Double.valueOf(temp);
    }
}
