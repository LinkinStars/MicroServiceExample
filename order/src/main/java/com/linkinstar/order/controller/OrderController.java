package com.linkinstar.order.controller;

import com.linkinstar.order.client.IPayService;
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

    @Autowired
    private IPayService payService;

    @RequestMapping("/buy")
    public String buy(){
        //通过服务创建订单
        orderService.createOrder();

        //进行扣款支付
        payService.pay();
        return "完成";
    }
}
