package com.linkinstar.order.bean;

/**
 * 订单实体
 * @author LinkinStar
 */
public class OrderDO {
    private String orderId;
    private String messageId;
    private String orderContent;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    @Override
    public String toString() {
        return "OrderDO{" +
                "orderId='" + orderId + '\'' +
                ", messageId='" + messageId + '\'' +
                ", orderContent='" + orderContent + '\'' +
                '}';
    }
}
