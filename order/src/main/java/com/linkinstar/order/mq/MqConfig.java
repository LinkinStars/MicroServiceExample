package com.linkinstar.order.mq;

/**
 * 消息队列相关配置
 * @author LinkinStar
 */
public class MqConfig {
    
    /** 订单消息交换机 **/
    public static final String EXCHANGE_ORDER = "order-exchange";
    
    /** 订单消息路由 **/
    public static final String QUEUE_ORDER = "order.pay";
}
