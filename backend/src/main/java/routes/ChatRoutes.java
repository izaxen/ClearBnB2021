package routes;

import application.ChatMessageLogic;
import application.Repositories;
import dtos.ChatMessageDTO;
import entityDO.CurrentChat;
import entityDO.User;
import express.Express;
import io.javalin.websocket.WsContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoutes {

    Express app;
    ChatMessageLogic chatMessageLogic;
    List<WsContext> clients = new ArrayList<>();
    Repositories repositories;
    CurrentChat savedChatRoom;

    // chatroom ID as key, list<ctx> as value
    private static Map<Integer, List<WsContext>> chatRoomMap = new ConcurrentHashMap<>();
    // ctx as key, userID as value
    private static Map<WsContext, Integer> userNameMap = new ConcurrentHashMap<>();

    User user;
    User admin;
    int userID;
    int roomID;


    public ChatRoutes() {

    }

    public ChatRoutes(Express app, Repositories repositories) {
        this.repositories = repositories;
        chatMessageLogic = new ChatMessageLogic(repositories);
        admin = repositories.getUserRep().findUserById(91);

        app.ws("/websockets/:id", ws -> {
            ws.onConnect(ctx -> {
                userID = Integer.parseInt(ctx.pathParam("id"));
                // Check if user is admin (91)
                // If yes, add admin to all chatRooms
                if (userID == 91) {
                    userNameMap.put(ctx, userID);
                    for (Integer rid : chatRoomMap.keySet()
                    ) {
                        if (!chatRoomMap.get(rid).contains(ctx)) {
                            chatRoomMap.get(rid).add(ctx);
                        }
                    }
                } else {
                    CurrentChat newChatRoom = new CurrentChat();
                    savedChatRoom = repositories.getCurrentChatRepository().addCurrentChat(newChatRoom).get();
                    roomID = savedChatRoom.getId();

                    List<WsContext> userCtxInRoom = new ArrayList<>();
                    userCtxInRoom.add(ctx);
                    userNameMap.put(ctx, userID);
                    chatRoomMap.put(roomID, userCtxInRoom);

                    // if user logged in after admin, admin won't be in same room, thus when they log in we need to run a check
                    if (userNameMap.containsValue(91)) {
                        for (WsContext c : userNameMap.keySet()
                        ) {
                            if (userNameMap.get(c) == 91 && !chatRoomMap.get(roomID).contains(c)) {
                                chatRoomMap.get(roomID).add(c);
                                System.out.println(chatRoomMap.get(roomID));
                            }
                        }
                    }
                }

                clients.add(ctx);

                user = repositories.getUserRepository().findUserById(userID);
                String userFirstName = user.getFirstName();
                ChatMessageDTO msg = new ChatMessageDTO(userFirstName + " has joint the chat", userID);
                if (userID != 91) {
                    chatRoomMap.get(roomID).forEach(client -> client.send(msg));
                    // print out all history in DB is not admin
                    List<ChatMessageDTO> cmDTOs = chatMessageLogic.getChatMessageDTOOfUser(userID, 91);
//                    Collections.reverse(cmDTOs)
                    if(cmDTOs.size()>0){
                        ChatMessageDTO historyStart = new ChatMessageDTO("===Chat History Start===");
                        ctx.send(historyStart);
                        for ( ChatMessageDTO cmDTO: cmDTOs)
                        {
                            ctx.send(cmDTO);
                        }
                        ChatMessageDTO historyEnd = new ChatMessageDTO("===Chat History End===");
                        ctx.send(historyEnd);
                    }
                } else {
                    clients.forEach(client -> client.send(msg));
                    if(clients.size()>1){
                        List<Integer> uids = new ArrayList<>();
                        for (WsContext c: clients) {
                            if(userNameMap.get(c)!=userID){
                                uids.add(userNameMap.get(c));
                            }
                        }
                        Set<Integer> set = new HashSet<>(uids);
                        uids.clear();
                        uids.addAll(set);
                        ChatMessageDTO onlineUidStart = new ChatMessageDTO("===Online Users===");
                        ctx.send(onlineUidStart);
                        for ( Integer uid: uids) {
                            ChatMessageDTO tempMess = new ChatMessageDTO(""+uid);
                            ctx.send(tempMess);
                        }
                        ChatMessageDTO onlineUidEnd = new ChatMessageDTO("================");
                        ctx.send(onlineUidEnd);
                    }
                }
            });

            ws.onMessage(ctx -> {
                ChatMessageDTO msg = ctx.message(ChatMessageDTO.class);

                // if message is not from admin, set receiverID to admin
                if(msg.getReceiverID() == 0) {
                    msg.setReceiverID(91);
                }

                userID = userNameMap.get(ctx);

                if (userID != 91) {
                    for (Integer rid : chatRoomMap.keySet()
                    ) {
                        if (chatRoomMap.get(rid).contains(ctx)) {
                            roomID = rid;
                        }
                    }

                    // set room id for this message
                    chatMessageLogic.createNewMessage(msg, roomID);
                    chatRoomMap.get(roomID).forEach(client -> client.send(msg));
                } else {
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
                    chatMessageLogic.createNewMessage(msg, roomID);
                    chatRoomMap.get(roomID).forEach(client -> client.send(msg));
                }
            });

            ws.onClose(ctx -> {
                userID = userNameMap.get(ctx);
                String userFirstName = repositories.getUserRepository().findUserById(userID).getFirstName();
                ChatMessageDTO msg = new ChatMessageDTO(userFirstName + " has left the chat", userID);

                if (userID != 91) {
                    savedChatRoom.setClosed(true);
                    repositories.getCurrentChatRepository().updateCurrentChat(savedChatRoom);
                    chatRoomMap.get(roomID).forEach(client -> client.send(msg));
                    chatRoomMap.remove(roomID);
                } else {
                    clients.forEach(client -> client.send(msg));
                }
                clients.remove(ctx);
            });

            ws.onError(ctx -> {
                System.out.println(chatRoomMap);
                userID = userNameMap.get(ctx);
                String userFirstName = repositories.getUserRepository().findUserById(userID).getFirstName();
                ChatMessageDTO msg = new ChatMessageDTO(userFirstName + " has left the chat", userID);

                if (userID != 91) {
                    savedChatRoom.setClosed(true);
                    repositories.getCurrentChatRepository().updateCurrentChat(savedChatRoom);
                    chatRoomMap.get(roomID).forEach(client -> client.send(msg));
                    chatRoomMap.remove(roomID);
                } else {
                    clients.forEach(client -> client.send(msg));
                }
                clients.remove(ctx);
            });
        });
    }
}