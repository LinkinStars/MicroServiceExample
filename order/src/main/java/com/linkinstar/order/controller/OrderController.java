package com.linkinstar.order.controller;

import com.linkinstar.order.bean.OrderDO;
import com.linkinstar.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单
 * @author LinkinStar
 */
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/buy")
    public String buy(){
        OrderDO orderDO = new OrderDO();
        orderDO.setMessageId("message" + System.currentTimeMillis());
        orderDO.setOrderContent("order content");
        orderDO.setOrderId("order" + System.currentTimeMillis());

        //创建订单
        orderService.createOrder(orderDO);

        return "完成";
    }
}
