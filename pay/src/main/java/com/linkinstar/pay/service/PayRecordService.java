package com.linkinstar.pay.service;

import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * 支付记录
 * @author LinkinStar
 */
@Service
public class PayRecordService {

    private Logger log = Logger.getLogger(PayRecordService.class.getName());

    /**
     * 创建支付记录
     * @return 成功返回 true 失败返回 false
     */
    public boolean createPayRecord() {
        //模拟数据库操作
        log.info("创建支付记录成功");
        return true;
    }
}
