package com.linkinstar.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 创建订单
     * @return 成功返回 true 失败返回 false
     */
    public boolean createOrder() {
        //1. 减少商品库存
        goodsService.reduceAmount();

        //2. 创建商品订单
        //模拟数据库操作
        log.info("创建订单成功");
        return true;
    }
}
