package dtos;

public class ChatMessageDTO {
    private String message;
    private int senderID;
    private int receiverID;

    public ChatMessageDTO() {
    }

    public ChatMessageDTO(int senderID, int receiverID, String message) {
        this.message = message;
        this.senderID = senderID;
        this.receiverID = receiverID;
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

    public void setMessage(String message) {
        this.message = message;
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
}
