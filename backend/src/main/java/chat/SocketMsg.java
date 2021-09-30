package chat;

import java.util.Date;

public class SocketMsg {
    private String msg;
    private int time_sent;
    int userID;
    private String userFirstName;

    public SocketMsg() {
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    @Override
    public String toString() {
        return "SocketMsg{ " +
                "msg='" + msg + '\'' +
                ", time_sent=" + new Date(time_sent).toString() +
                " }";
    }
}