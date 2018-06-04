package com.baomidou.springwind.mapper;


import com.baomidou.springwind.entity.RecordUserPay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordUserPayMapper {

    /**
     * 保存用户充值信息
     * @param recordUserPay
     */
    void saveRecordUserPay(RecordUserPay recordUserPay);

    /**
     * 分页获取用户充值信息
     * @return
     */
    List<RecordUserPay> listRecordUserPay(@Param("page") Integer page, @Param("limit") Integer limit, @Param("payUserId") String userId);

    /**
     * 查询出用户充值记录数量
     * @param userId
     * @return
     */
    Integer countRecordUserPay(@Param("payUserId") String userId);

    /**
     * 查询出全部用户的充值记录，userId不为空则是查询某个用户的所有查询记录
     * @param userId
     * @return
     */
    List<RecordUserPay> queryAllRecordUserPay(@Param("payUserId") String userId);
}