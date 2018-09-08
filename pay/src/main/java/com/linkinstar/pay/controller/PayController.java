package com.linkinstar.pay.controller;

import com.linkinstar.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单
 * @author LinkinStar
 */
@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping("/pay")
    public Boolean pay(){
        return payService.pay();
    }
}
