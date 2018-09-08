package com.linkinstar.order.service;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * 商品
 * @author LinkinStar
 */
@Service
public class GoodsService {

    private Logger log = Logger.getLogger(GoodsService.class.getName());

    /**
     * 减少库存
     * @return 成功返回 true 失败返回 false
     */
    public boolean reduceAmount() {
        //模拟数据库操作
        log.info("减少库存成功");
        return true;
    }
}
