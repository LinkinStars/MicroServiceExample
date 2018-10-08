package com.linkinstar.pay.service;

import com.linkinstar.pay.bean.ConsumerMq;
import com.linkinstar.pay.dao.ConsumerMqDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消费订单消息
 * @author LinkinStar
 */
@Service
public class OrderService {
    
    @Autowired
    private ConsumerMqDao consumerMqDao;

    @Autowired
    private PayService payService;

    public boolean consumer(ConsumerMq consumerMq) {
        //1. 新增消息记录
        consumerMqDao.addConsumerMq(consumerMq);
        
        //2. 尝试处理消息
        payService.pay();
        
        //3. 修改消息记录处理状态
        consumerMqDao.updateConsumerMqStatus(consumerMq.getMessageId(), 2);
        return true;
    }
}
