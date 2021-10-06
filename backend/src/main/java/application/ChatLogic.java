package application;

import dtos.ChatMessageDTO;
import entityDO.CurrentChat;
import entityDO.User;
import io.javalin.websocket.WsContext;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ChatLogic {
    Repositories repositories;
    List<WsContext> clients = new ArrayList<>();
    CurrentChat savedChatRoom;
    LogicHandler logicHandler;
    User admin;
    int userID;
    int roomID;
    private static Map<Integer, List<WsContext>> chatRoomMap = new ConcurrentHashMap<>();
    private static Map<WsContext, Integer> userNameMap = new ConcurrentHashMap<>();

    public ChatLogic(Repositories repositories, LogicHandler logicHandler) {
        this.repositories = repositories;
        this.logicHandler = logicHandler;
        admin = repositories.getUserRepository().findUserById(91);
    }

    public void connectToServer(WsContext ctx) {

        registerAndDeployCTXToRoom(ctx);
        historyAndOnlineUsers(ctx, getUserFirstName());

    }

    public void onMessage(WsContext ctx, ChatMessageDTO msg) {
        if (msg.getReceiverID() == 0) {
            msg.setReceiverID(91);
        }
        userID = userNameMap.get(ctx);
        // UserID 91 is hard coded admin
        if (userID != 91) {
            userMessage(ctx, msg);
        }

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
            userNameMap.remove(ctx);
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
        // Check if user is admin ( hard coded userID 91)
        if (userID == 91) {
            registerAdminToRoom(ctx);
        }

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
        // If user is not admin (hardcoded admin userID 91)
        if (userID != 91) {
            getAndPrintChatHistory(ctx, msg);
        }
        else {
            getAndPrintOnlineUsers(ctx, msg);
        }
    }

    public void getAndPrintChatHistory(WsContext ctx, ChatMessageDTO msg) {
        chatRoomMap.get(roomID).forEach(client -> client.send(msg));
        List<ChatMessageDTO> cmDTOs = logicHandler.getChatMessageLogic().getChatMessageDTOOfUser(userID, 91);
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
        logicHandler.getChatMessageLogic().createNewMessage(msg, roomID);
        chatRoomMap.get(roomID).forEach(client -> client.send(msg));
    }

    public void adminMessage(ChatMessageDTO msg){
        int receiverID = msg.getReceiverID();
        WsContext tempC = null;
        for (WsContext c : userNameMap.keySet()
        ) {
            if (userNameMap.get(c) == receiverID) {
                tempC = c;
            }
        }

        for (Integer rid : chatRoomMap.keySet()
        ) {
            if (chatRoomMap.get(rid).contains(tempC)) {
                roomID = rid;
            }
        }
        logicHandler.getChatMessageLogic().createNewMessage(msg, roomID);
        chatRoomMap.get(roomID).forEach(client -> client.send(msg));
    }

    public void removeRoomAndSetCloseInDB(ChatMessageDTO msg, WsContext ctx){
        roomID = findUserRoomID(ctx);
        repositories.getCurrentChatRepository().updateCurrentChat(savedChatRoom);
        chatRoomMap.get(roomID).forEach(client -> client.send(msg));
        savedChatRoom.setClosed(true);
        chatRoomMap.remove(roomID);
        userNameMap.remove(ctx);
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
