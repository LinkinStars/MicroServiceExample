package com.linkinstar.pay.dao;


import com.linkinstar.pay.bean.ConsumerMq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * 消费消息
 * @author LinkinStar
 */
@Mapper
public interface ConsumerMqDao {

    /**
     * 新增消费消息
     * @param consumerMq 消费消息
     * @return true 新增成功； false 新增失败
     */
    boolean addConsumerMq(ConsumerMq consumerMq);

    /**
     * 修改消费消息状态
     * @param messageId 消息id
     * @param messageStatus 消息状态
     * @return true 修改成功； false 修改失败
     */
    boolean updateConsumerMqStatus(@Param("messageId") String messageId, @Param("messageStatus") int messageStatus);

    /**
     * 查询消息状态为1且已经经过60秒未成功的消息
     * @param curTime 当前时间 + 60秒
     * @return 消息列表
     */
    List<ConsumerMq> queryConsumerMqByStatus(@Param("curTime") Timestamp curTime);
}
