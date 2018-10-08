package com.linkinstar.order.task;

import com.linkinstar.order.bean.OrderMq;
import com.linkinstar.order.dao.OrderMqDao;
import com.linkinstar.order.mq.OrderSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.util.List;

/**
 * 订单定时任务
 * @author LinkinStar
 */
@Configuration
@EnableScheduling
public class OrderTask {
    
    @Autowired
    private OrderMqDao orderMqDao;

    @Autowired
    private OrderSender orderSender;

    /**
     * 定时去查询发送失败的消息，让这些消息重新进行发送到mq
     */
    @Scheduled(cron = "0/10 * * * * ?")
    public void orderMqResend() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis() - 60000);
        List<OrderMq> orderMqList = orderMqDao.queryOrderMqByStatus(timestamp);
        orderMqList.forEach(orderMq -> {
            orderSender.sendOrder(orderMq);
            System.out.println("重新发送消息：" + orderMq.getMessageId());
        });
    }
}
