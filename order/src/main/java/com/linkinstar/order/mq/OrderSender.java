package com.linkinstar.order.mq;

import com.google.gson.Gson;
import com.linkinstar.order.bean.OrderMq;
import com.linkinstar.order.dao.OrderMqDao;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.linkinstar.order.mq.MqConfig.EXCHANGE_ORDER;
import static com.linkinstar.order.mq.MqConfig.QUEUE_ORDER;

/**
 * 订单消息发送
 * @author LinkinStar
 */
@Component
public class OrderSender {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private OrderMqDao orderMqDao;

    /**
     * 发送订单消息到mq
     * @param orderMq 订单消息
     */
    public void sendOrder(OrderMq orderMq){
        //消息id
        CorrelationData correlationData = new CorrelationData(orderMq.getMessageId());
        
        //设置回调函数
        rabbitTemplate.setConfirmCallback(confirmCallback);
        
        //发送消息
        try {
            Gson gson = new Gson();
            rabbitTemplate.convertAndSend(EXCHANGE_ORDER, QUEUE_ORDER, gson.toJson(orderMq), correlationData);
        } catch (AmqpException e) {
            e.printStackTrace();
        }
    }


    /**
     * 订单消息发送回调
     * correlationData 消息id
     * ack 是否处理成功
     */
    private final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        //如果消息被确认
        if (ack) {
            orderMqDao.updateOrderMqStatus(correlationData.getId(), 2);
        } else {
            System.out.println("做相对应的异常处理：");
        }
    };
}
