package com.baomidou.springwind.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * @author Blse
 * @date 2018/5/30
 * @description
 */
public class JsonHelper {

    private JsonHelper() {}

    /**
     * <p>
     * 转换为 bootstrap-table 需要的分页格式 JSON
     * </p>
     *
     * @param page
     *            分页对象
     * @return
     */
    public static String jsonPage(Page<?> page) {
        JSONObject jo = new JSONObject();
        jo.put("total", page.getTotal());
        jo.put("rows", page.getRecords());
        return toJson(jo);
    }

    /**
     *
     * 返回 JSON 格式对象
     *
     * @param object
     *            转换对象
     * @return
     */
    private static  String toJson(Object object) {
        return JSON.toJSONString(object, SerializerFeature.BrowserCompatible);
    }
}
