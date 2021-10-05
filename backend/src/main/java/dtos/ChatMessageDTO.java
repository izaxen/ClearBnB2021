package dtos;

public class ChatMessageDTO {
    private String message;
    private int senderID;
    private int receiverID;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(String message, int senderID) {
        this.message = message;
        this.senderID = senderID;
    }

    public ChatMessageDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getSenderID() {
        return senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }
}
