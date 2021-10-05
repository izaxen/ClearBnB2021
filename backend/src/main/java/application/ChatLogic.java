package application;

import dtos.ChatMessageDTO;
import entityDO.CurrentChat;
import entityDO.User;
import io.javalin.websocket.WsContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ChatLogic {
    Repositories repositories;
    ChatMessageLogic chatMessageLogic;
    List<WsContext> clients = new ArrayList<>();
    CurrentChat savedChatRoom;
    User admin;
    int userID;
    int roomID;
    // chatroom ID as key, list<ctx> as value
    private static Map<Integer, List<WsContext>> chatRoomMap = new ConcurrentHashMap<>();
    // ctx as key, userID as value
    private static Map<WsContext, Integer> userNameMap = new ConcurrentHashMap<>();

    public ChatLogic(Repositories repositories) {
        this.repositories = repositories;
        chatMessageLogic = new ChatMessageLogic(repositories);
        admin = repositories.getUserRepository().findUserById(91);
    }

    public void connectToServer(WsContext ctx) {

        registerAndDeployCTXToRoom(ctx);
        historyAndOnlineUsers(ctx, getUserFirstName());

    }

    public void onMessage(WsContext ctx, ChatMessageDTO msg) {
        // if message is not from admin, set receiverID to admin
        if (msg.getReceiverID() == 0) {
            msg.setReceiverID(91);
        }
        userID = userNameMap.get(ctx);

        // if user is not admin, loop through every chat room to find the room i am in.
        // ( to update roomID, else roomID would be the last connected user's roomID )
        // save message to DB, send back to client
        if (userID != 91) {
            userMessage(ctx, msg);
        }

        // if admin, find the receiver's chat room, and send message to that room
        else {
            adminMessage(msg);
        }
    }

    public void onClose(WsContext ctx) {
        userID = userNameMap.get(ctx);
        String userFirstName = repositories.getUserRepository().findUserById(userID).getFirstName();
        ChatMessageDTO msg = new ChatMessageDTO(userFirstName + " has left the chat", userID);

        if (userID != 91) {
            removeRoomAndSetCloseInDB(msg, ctx);
        } else {
            clients.forEach(client -> client.send(msg));
        }
        clients.remove(ctx);
    }

    public void onError(WsContext ctx) {
        System.out.println("Error");
        onClose(ctx);
    }

    public void registerAndDeployCTXToRoom(WsContext ctx) {
        userID = Integer.parseInt(ctx.pathParam("id"));
        userNameMap.put(ctx, userID);
        // Check if user is admin (91)
        // If yes, register ctx together with this userID
        // Add admin to all existing
        if (userID == 91) {
            registerAdminToRoom(ctx);
        }

        // If not, create new room.
        // Register this ctx to this userID
        // and put user in new chat room
        else {
            registerUserToRoom(ctx);
        }

        clients.add(ctx);
    }

    public void registerAdminToRoom(WsContext ctx) {
        for (Integer rid : chatRoomMap.keySet()
        ) {
            if (!chatRoomMap.get(rid).contains(ctx)) {
                chatRoomMap.get(rid).add(ctx);
            }
        }
    }

    public void registerUserToRoom(WsContext ctx) {
        roomID = createNewRoomAndRegister(ctx);
        // If user logged in after admin log in, admin won't be in same room, thus when they log in we need to run a check
        if (userNameMap.containsValue(91)) {
            for (WsContext c : userNameMap.keySet()
            ) {
                if (userNameMap.get(c) == 91 && !chatRoomMap.get(roomID).contains(c)) {
                    chatRoomMap.get(roomID).add(c);
                }
            }
        }
    }

    public Integer createNewRoomAndRegister(WsContext ctx) {
        CurrentChat newChatRoom = new CurrentChat();
        savedChatRoom = repositories.getCurrentChatRepository().addCurrentChat(newChatRoom).get();
        roomID = savedChatRoom.getId();
        List<WsContext> userCtxInRoom = new ArrayList<>();
        userCtxInRoom.add(ctx);
        userNameMap.put(ctx, userID);
        chatRoomMap.put(roomID, userCtxInRoom);
        return roomID;
    }

    public String getUserFirstName() {
        return repositories.getUserRepository().findUserById(userID).getFirstName();
    }

    public void historyAndOnlineUsers(WsContext ctx, String userFirstName) {
        ChatMessageDTO msg = new ChatMessageDTO(userFirstName + " has joint the chat", userID);
        // If user is not admin
        // Broadcast to everyone IN MY ROOM i'm online
        // Get all chat history between me and admin in our DB and send chat history
        if (userID != 91) {
            getAndPrintChatHistory(ctx, msg);
        }
        // If admin, tell everyone IN THE SERVER that i am connected
        // loop to get all userID currently connected to server
        // Delete duplicate and admin userID
        // Send current users online to admin
        else {
            getAndPrintOnlineUsers(ctx, msg);
        }
    }

    public void getAndPrintChatHistory(WsContext ctx, ChatMessageDTO msg) {
        chatRoomMap.get(roomID).forEach(client -> client.send(msg));
        List<ChatMessageDTO> cmDTOs = chatMessageLogic.getChatMessageDTOOfUser(userID, 91);
        if (cmDTOs.size() > 0) {
            ChatMessageDTO historyStart = new ChatMessageDTO("===Chat History Start===");
            ctx.send(historyStart);
            for (ChatMessageDTO cmDTO : cmDTOs) {
                ctx.send(cmDTO);
            }
            ChatMessageDTO historyEnd = new ChatMessageDTO("===Chat History End===");
            ctx.send(historyEnd);
        }
    }

    public void getAndPrintOnlineUsers(WsContext ctx, ChatMessageDTO msg) {
        clients.forEach(client -> client.send(msg));
        if (clients.size() > 1) {
            List<Integer> uids = new ArrayList<>();
            for (WsContext c : clients) {
                if (userNameMap.get(c) != userID) {
                    uids.add(userNameMap.get(c));
                }
            }
            Set<Integer> set = new HashSet<>(uids);
            uids.clear();
            uids.addAll(set);
            ChatMessageDTO onlineUidStart = new ChatMessageDTO("===Online Users===");
            ctx.send(onlineUidStart);
            for (Integer uid : uids) {
                ChatMessageDTO tempMess = new ChatMessageDTO("" + uid);
                ctx.send(tempMess);
            }
            ChatMessageDTO onlineUidEnd = new ChatMessageDTO("================");
            ctx.send(onlineUidEnd);
        }
    }

    public void userMessage(WsContext ctx, ChatMessageDTO msg){
        for (Integer rid : chatRoomMap.keySet()
        ) {
            if (chatRoomMap.get(rid).contains(ctx)) {
                roomID = rid;
            }
        }
        chatMessageLogic.createNewMessage(msg, roomID);
        chatRoomMap.get(roomID).forEach(client -> client.send(msg));
    }

    public void adminMessage(ChatMessageDTO msg){
        int receiverID = msg.getReceiverID();
        System.out.println("ReceiverID: " + receiverID);
        WsContext tempC = null;
        // Run in two separate for loop to avoid messing in my head
        // Find every ctx that belongs to receiver's ID
        for (WsContext c : userNameMap.keySet()
        ) {
            if (userNameMap.get(c) == receiverID) {
                tempC = c;
                System.out.println("tempc: " + tempC);
            }
        }

        // Find chatRoom belongs to that ID
        for (Integer rid : chatRoomMap.keySet()
        ) {
            if (chatRoomMap.get(rid).contains(tempC)) {
                roomID = rid;
                System.out.println("roomID: " + roomID);
            }
        }
        chatMessageLogic.createNewMessage(msg, roomID);
        chatRoomMap.get(roomID).forEach(client -> client.send(msg));
    }

    public void removeRoomAndSetCloseInDB(ChatMessageDTO msg, WsContext ctx){
        roomID = findUserRoomID(ctx);
        savedChatRoom.setClosed(true);
        repositories.getCurrentChatRepository().updateCurrentChat(savedChatRoom);
        chatRoomMap.get(roomID).forEach(client -> client.send(msg));
        chatRoomMap.remove(roomID);
    }

    public Integer findUserRoomID(WsContext ctx){
        Integer room = 0;
        for ( Integer rid: chatRoomMap.keySet()) {
            if(chatRoomMap.get(rid).contains(ctx)){
                room = rid;
            }
        }
        return room;
    }
}
