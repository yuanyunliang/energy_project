package com.baomidou.framework;

import com.baomidou.framework.common.RelativeDateFormat;
import org.junit.Test;

import java.util.Date;

/**
 * @author Blse
 * @date 2018/4/19
 * @description
 */
public class CommonTest {

    @Test
    public void testTime() {
        System.out.println(RelativeDateFormat.format(new Date()));
    }
}
