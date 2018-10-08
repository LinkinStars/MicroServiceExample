package com.linkinstar.order.dao;

import com.linkinstar.order.bean.OrderMq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * 订单消息
 * @author LinkinStar
 */
@Mapper
public interface OrderMqDao {

    /**
     * 新增订单消息
     * @param orderMq 订单消息
     * @return true 新增成功； false 新增失败
     */
    boolean addOrderMq(OrderMq orderMq);

    /**
     * 修改订单消息状态
     * @param messageId 消息id
     * @param messageStatus 消息状态
     * @return true 修改成功； false 修改失败
     */
    boolean updateOrderMqStatus(@Param("messageId") String messageId, @Param("messageStatus")int messageStatus);

    /**
     * 查询消息状态为1且已经经过60秒未成功的消息
     * @param curTime 当前时间 + 60秒
     * @return 消息列表
     */
    List<OrderMq> queryOrderMqByStatus(@Param("curTime") Timestamp curTime);
}
