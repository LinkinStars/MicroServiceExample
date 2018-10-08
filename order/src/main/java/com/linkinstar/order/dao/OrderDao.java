package com.linkinstar.order.dao;

import com.linkinstar.order.bean.OrderDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * @author LinkinStar
 */
@Mapper
public interface OrderDao {

    /**
     * 新增订单
     * @param orderDO 订单信息
     * @return true 新增成功； false 新增失败
     */
    boolean addOrder(OrderDO orderDO);
}
