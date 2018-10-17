# MQ保证消息一致性

## 简介  
使用rabbitMq来简单实现分布式事务的最终一致性
版本如下：  
SpringCloud ： Finchley.SR1  
SpringBoot ： 2.0.4.RELEASE  
Gradle ： 4.8  

## 项目结构
| 目录 | 名称 | 访问地址 |
| --- | --- | --- |
| eureka | 注册中心 | http://127.0.0.1:8761 |
| gateway | 网关+路由 | http://127.0.0.1:8769/order/buy <br> http://127.0.0.1:8769/pay/pay |
| order | 订单服务 | http://127.0.0.1:8763/buy |
| pay | 支付服务 | http://127.0.0.1:8762/pay |


## 启动顺序
rabbitMq -> 注册中心 -> 网关 -> 订单 -> 支付  

## 整体流程
生产者的逻辑  
1、订单入库  
2、消息记录入库  
3、发送消息（采用确认模式）  
4、mq收到消息之后给生产端一个确认消息  
5、生产端监听这个确认消息  
6、根据监听结果操作消息表的状态  
7、定时任务定时去操作消息状态为1未发送的记录，就是那些没有监听到结果的记录进行重新发送    

消费者的逻辑  
1、将收到消息的消息入库  
2、处理消息失败消息记录的状态就为未处理  
3、处理消息成功修改消息记录的状态为处理成功  
4、收到相同的消息id的消息直接丢弃  
5、定时任务去操作那些未处理，并且已经经过一段时间的消息  
6、针对那些一直处理失败的，且很长一段时间都没办法处理成功的消息交由人工或者其他途径处理  

## 数据库设计
数据库只是为了满足测试要求，不做实际用途
````sql
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for consumer_mq_tab
-- ----------------------------
DROP TABLE IF EXISTS `consumer_mq_tab`;
CREATE TABLE `consumer_mq_tab` (
  `messageId` varchar(255) NOT NULL,
  `messageContent` varchar(255) NOT NULL,
  `messageStatus` int(11) NOT NULL,
  `addTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_mq_tab
-- ----------------------------
DROP TABLE IF EXISTS `order_mq_tab`;
CREATE TABLE `order_mq_tab` (
  `messageId` varchar(255) NOT NULL,
  `messageContent` varchar(255) NOT NULL,
  `messageStatus` int(11) NOT NULL,
  `addTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`messageId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order_tab
-- ----------------------------
DROP TABLE IF EXISTS `order_tab`;
CREATE TABLE `order_tab` (
  `orderId` varchar(255) NOT NULL,
  `messageId` varchar(255) NOT NULL,
  `orderContent` varchar(255) NOT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
````

## 具体完整项目说明讲解
https://www.cnblogs.com/linkstar/p/9784243.html








