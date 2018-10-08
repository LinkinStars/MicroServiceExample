package com.linkinstar.order.service;

import com.linkinstar.order.bean.OrderDO;
import com.linkinstar.order.bean.OrderMq;
import com.linkinstar.order.dao.OrderDao;
import com.linkinstar.order.dao.OrderMqDao;
import com.linkinstar.order.mq.OrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;


/**
 * 订单
 * @author LinkinStar
 */
@Service
public class OrderService {

    private Logger log = Logger.getLogger(OrderService.class.getName());

    @Autowired
    private GoodsService goodsService;
    
    @Autowired
    private OrderDao orderDao;
    
    @Autowired
    private OrderMqDao orderMqDao;

    @Autowired
    private OrderSender orderSender;
    
    /**
     * 创建订单
     * @return 成功返回 true 失败返回 false
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrder(OrderDO orderDO) {
        //1. 减少商品库存
        goodsService.reduceAmount();

        //2. 创建商品订单
        orderDao.addOrder(orderDO);

        OrderMq orderMq = new OrderMq();
        orderMq.setMessageId(orderDO.getMessageId());
        orderMq.setMessageContent(orderDO.getOrderContent());
        orderMq.setMessageStatus(1);
        
        //3. 创建订单mq消息
        orderMqDao.addOrderMq(orderMq);

        //4. 发送消息到mq
        orderSender.sendOrder(orderMq);
        return true;
    }
}
