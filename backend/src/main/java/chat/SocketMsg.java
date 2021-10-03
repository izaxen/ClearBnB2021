package chat;


public class SocketMsg {
    private String msg;
    private int time_sent;
    private int senderID;
    private int receiverID;
    private int chatRoomID;

    public SocketMsg() {
    }

    public SocketMsg(String msg, int time_sent, int chatRoomID) {
        this.msg = msg;
        this.time_sent = time_sent;
        this.chatRoomID = chatRoomID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTime_sent() {
        return time_sent;
    }

    public void setTime_sent(int time_sent) {
        this.time_sent = time_sent;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public int getChatRoomID() {
        return chatRoomID;
    }

    public void setChatRoomID(int chatRoomID) {
        this.chatRoomID = chatRoomID;
    }
}