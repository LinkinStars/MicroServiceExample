package com.linkinstar.order.bean;

/**
 * 订单消息实体
 * @author LinkinStar
 */
public class OrderMq {
    private String messageId;
    private String messageContent;
    /** 消息状态 1：消息发送中； 2：消息发送成功； 3：消息发送失败 **/
    private int messageStatus;
    

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    @Override
    public String toString() {
        return "OrderMq{" +
                "messageId='" + messageId + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageStatus=" + messageStatus +
                '}';
    }
}
