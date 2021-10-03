package routes;

import application.Repositories;
import chat.SocketMsg;
import entityDO.CurrentChat;
import entityDO.User;
import express.Express;
import io.javalin.websocket.WsContext;
import mapper.UserService;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoutes {

    Express app;
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
        admin = repositories.getUserRep().findUserById(91);

        app.ws("/websockets/:id", ws -> {
            ws.onConnect(ctx -> {
                userID = Integer.parseInt(ctx.pathParam("id"));
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
                            if (userNameMap.get(c) == 91) {
                                chatRoomMap.get(roomID).add(c);
                                System.out.println(chatRoomMap.get(roomID));
                            }
                        }
                    }
                }

                clients.add(ctx);

                user = repositories.getUserRepository().findUserById(userID);
                String userFirstName = user.getFirstName();
                SocketMsg msg = new SocketMsg(userFirstName + " has joint the chat", userID);
                if (userID != 91) {
                    chatRoomMap.get(roomID).forEach(client -> client.send(msg));
                } else {
                    clients.forEach(client -> client.send(msg));
                }
            });

            ws.onMessage(ctx -> {
                SocketMsg msg = ctx.message(SocketMsg.class);
                userID = userNameMap.get(ctx);

                if (userID != 91) {
                    for (Integer rid : chatRoomMap.keySet()
                    ) {
                        if (chatRoomMap.get(rid).contains(ctx)) {
                            roomID = rid;
                        }
                    }
                    System.out.println(roomID);
                    System.out.println(chatRoomMap);
                    chatRoomMap.get(roomID);
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
                    chatRoomMap.get(roomID);
                    chatRoomMap.get(roomID).forEach(client -> client.send(msg));
                }
            });

            ws.onClose(ctx -> {
                System.out.println("before dc: ");
                System.out.println(chatRoomMap);
                userID = userNameMap.get(ctx);
                String userFirstName = repositories.getUserRepository().findUserById(userID).getFirstName();
                SocketMsg msg = new SocketMsg(userFirstName + " has left the chat", userID);

                if (userID != 91) {
                    savedChatRoom.setClosed(true);
                    repositories.getCurrentChatRepository().updateCurrentChat(savedChatRoom);
                    chatRoomMap.get(roomID).forEach(client -> client.send(msg));
                    chatRoomMap.remove(roomID);
                    System.out.println("after dc: ");
                    System.out.println(chatRoomMap);
                } else {
                    clients.forEach(client -> client.send(msg));
                }
                clients.remove(ctx);
            });

            ws.onError(ctx -> {
                System.out.println(chatRoomMap);
                userID = userNameMap.get(ctx);
                String userFirstName = repositories.getUserRepository().findUserById(userID).getFirstName();
                SocketMsg msg = new SocketMsg(userFirstName + " has left the chat", userID);

                if (userID != 91) {
                    savedChatRoom.setClosed(true);
                    repositories.getCurrentChatRepository().updateCurrentChat(savedChatRoom);
                    chatRoomMap.get(roomID).forEach(client -> client.send(msg));
                    chatRoomMap.remove(roomID);
                    System.out.println(chatRoomMap);
                } else {
                    clients.forEach(client -> client.send(msg));
                }
                clients.remove(ctx);
            });
        });
    }
}