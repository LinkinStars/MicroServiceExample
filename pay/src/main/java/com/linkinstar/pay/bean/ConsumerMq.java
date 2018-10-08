package com.linkinstar.pay.bean;

/**
 * 消费消息实体
 * @author LinkinStar
 */
public class ConsumerMq {
    private String messageId;
    private String messageContent;
    /** 消息状态 1：消息收到还未处理； 2：消息处理成功； 3：消息处理失败 **/
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
        return "ConsumerMq{" +
                "messageId='" + messageId + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageStatus=" + messageStatus +
                '}';
    }
}
