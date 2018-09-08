package com.linkinstar.pay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * 支付服务
 * @author LinkinStar
 */
@Service
public class PayService {

    private Logger log = Logger.getLogger(PayService.class.getName());

    @Autowired
    private PayRecordService payRecordService;

    /**
     * 支付
     * @return 成功返回 true 失败返回 false
     */
    public boolean pay() {
        //通过支付
        //模拟数据库操作
        log.info("支付成功");

        //创建支付记录
        payRecordService.createPayRecord();
        return true;
    }
}
