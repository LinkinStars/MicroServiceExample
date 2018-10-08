package com.linkinstar.pay.mq;

import com.alibaba.fastjson.JSONObject;
import com.linkinstar.pay.bean.ConsumerMq;
import com.linkinstar.pay.service.OrderService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * 消费订单消息
 * @author LinkinStar
 */
@Component
public class OrderReceiver {
    
    @Autowired
    private OrderService orderService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue", durable = "true"),
            exchange = @Exchange(name = "order-exchange", type = ExchangeTypes.TOPIC),
            key = "order.*"
    )
    )
    @RabbitHandler
    public void receive(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        JSONObject jsonObject = JSONObject.parseObject(message);
        ConsumerMq consumerMq = jsonObject.toJavaObject(ConsumerMq.class);
        orderService.consumer(consumerMq);
        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
