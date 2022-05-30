package common;

public class Message {
    private MessageType messageType;
    private String msg;

    public Message(MessageType messageType, String msg) {
        this.messageType = messageType;
        this.msg = msg;
    }

    public Message() {
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getMsg() {
        return msg;
    }
}
