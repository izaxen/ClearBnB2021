package routes;

import application.Repositories;
import chat.SocketMsg;
import entityDO.CurrentChat;
import entityDO.User;
import express.Express;
import io.javalin.websocket.WsContext;
import mapper.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoutes {

    Express app;
    List<WsContext> clients = new ArrayList<>();
    Repositories repositories;
    UserService us;
    CurrentChat savedChatRoom;
    ArrayList<Integer> usersInRoomList = new ArrayList<>();

    // userUsernameMap is to identify ctx with a user in our DB
    // ctx as key, userID as value
    private static Map<WsContext, Integer> userNameMap = new ConcurrentHashMap<>();

    // UserID as key, roomID array as value
    // chatRoomMap is to deploy USERID to different chat-room ID
    // can not have userID and then chatRoomID because key must be unique
    private static Map<Integer, ArrayList<Integer>> chatRoomMap = new ConcurrentHashMap<>();

//    // usersInChatRoomMap is to connect chat-room with an arraylist of all users in that room
//    private static Map<Integer, List<WsContext>> usersInChatRoomMap = new ConcurrentHashMap<>();

    User user;
    User admin;
    int userID;
    int roomID;
    Boolean check = false;


    public ChatRoutes() {

    }

    public ChatRoutes(Express app, Repositories repositories){
        this.repositories = repositories;
        admin = repositories.getUserRep().findUserById(91);
        List<WsContext> usersInSameRoomAsMeCTX = new ArrayList<>();

        app.ws("/websockets/:id", ws -> {
            ws.onConnect(ctx -> {
                userID = Integer.parseInt(ctx.pathParam("id"));
                usersInSameRoomAsMeCTX.clear();
                usersInRoomList.clear();
                roomID = 0;

                user = repositories.getUserRep().findUserById(userID);
                userNameMap.put(ctx, userID);

                if (!chatRoomMap.isEmpty()){
                    for ( Map.Entry<Integer, ArrayList<Integer>> entry : chatRoomMap.entrySet()) {
                        check = entry.getValue().contains(userID);
//                        System.out.println(entry.getValue()); // why would this give me an empty array? i have values in it!!
//                        System.out.println(entry.getValue().contains(userID));
                    }
                }

                    if(!check && userID != 91){
                        CurrentChat chatRoom = new CurrentChat();
                        savedChatRoom = repositories.getCurrentChatRepository().addCurrentChat(chatRoom).get();
                        roomID = savedChatRoom.getId();
                        usersInRoomList.add(userID);
                        chatRoomMap.put(roomID, usersInRoomList);
//                        System.out.println(chatRoomMap);
                    }
                    if(userID == 91){
                        for (Map.Entry<Integer, ArrayList<Integer>> entry : chatRoomMap.entrySet()) {
                            System.out.println("value: " + entry.getValue());
                            System.out.println("Keys: " + entry.getKey()); // empty arraylist???
                            if(!entry.getValue().contains(91)){
                                usersInRoomList.add(91);
                                chatRoomMap.put(entry.getKey(), usersInRoomList);
                        }
                    }
                }



                // put user in user arraylist
                // like a broadcast
                clients.add(ctx);

                // testing
                System.out.println("UserID: " + userID);
                System.out.println("Is in chat room number: " + roomID);
                System.out.println("Users in my room: ");
//                for (Integer uid: chatRoomMap.get(roomID)
//                     ) {
//                    System.out.println("user: " + uid);
//                }







//                System.out.println("userNamemap: " + chatRoomMap.entrySet());
//                // take all userID out from userNameMap, this hashmap contains all users that has logged in
//                for (int uid: chatRoomMap.keySet()
//                ) {
//                    // we get userID here, uid
//                    // get Room id for this userID, and compare it
//                    System.out.println("uid: " + uid);
//                    System.out.println("chatRoom: " + chatRoomMap.get(uid));
//                    System.out.println("chatRoomEqual: " + chatRoomMap.get(userID));
//                    if (Objects.equals(chatRoomMap.get(uid), chatRoomMap.get(userID))) {
//                        // this will give me a list of all userID in my room.
//                        usersInSameRoomAsMe.add(uid);
//                        // if system finds userID with the same chatroom ID as me, find the paired ctx for that user and add it in a list of CTX
//                        // ignore duplicate and invalid items
//                        // this will be the list for every user in my room (me + admin)
//                        if((userNameMap.get(uid)) != null && (!usersInSameRoomAsMeCTX.contains(userNameMap.get(uid)))){
//                            usersInSameRoomAsMeCTX.add(userNameMap.get(uid));
//                            System.out.println("went in!");
//                            System.out.println("usersInSameRoom: " + usersInSameRoomAsMeCTX);
//                        }
//                    }
//                }

                // got 2 ctx of admin
//                System.out.println(usersInSameRoomAsMeCTX);

                String userFirstName = user.getFirstName();

                System.out.println(userID + " - " + userFirstName + " connected");
                usersInSameRoomAsMeCTX.forEach(client -> client.send(userFirstName + " has joint the chat"));
            });

            ws.onMessage(ctx -> {
                // everytime we send a message, update user
//                usersInSameRoomAsMeCTX.clear();
//                for ( Integer uid : userNameMap.keySet()
//                     ) {
//                    if(userNameMap.get(uid).equals(ctx)){
//                        userID = uid;
//                        user = repositories.getUserRepository().findUserById(uid);
//                    }
//                }
//                for (int uid: chatRoomMap.keySet()
//                ) {
//                    if(chatRoomMap.get(uid) == chatRoomMap.get(userID) && (userNameMap.get(uid)) != null && (!usersInSameRoomAsMeCTX.contains(userNameMap.get(uid)))){
//                        usersInSameRoomAsMeCTX.add(userNameMap.get(uid));
//                    }
//                }


                String userFirstName = user.getFirstName();
                SocketMsg msg = ctx.message(SocketMsg.class); // convert from json

                usersInSameRoomAsMeCTX.forEach(client -> client.send(userFirstName + ": " + msg.getMsg())); // convert to json and send back to ALL connected clients
//        ctx.send(msg);
// convert to json and send back (ONLY to the sender)
            });

            ws.onClose(ctx -> {
                if(userID!=91){
                    savedChatRoom.setClosed(true);
                    repositories.getCurrentChatRepository().updateCurrentChat(savedChatRoom);
                }
                clients.remove(ctx);
                usersInSameRoomAsMeCTX.remove(ctx);

                String userFirstName = user.getFirstName();
                userID = Integer.parseInt(ctx.pathParam("id"));
                System.out.println(userID + " - " + userFirstName + " disconnected");
                usersInSameRoomAsMeCTX.forEach(client -> client.send(userFirstName + " has left the chat"));
            });

            ws.onError(ctx -> {
                System.out.println("Error");
                if(userID!=91){
                    savedChatRoom.setClosed(true);
                    repositories.getCurrentChatRepository().updateCurrentChat(savedChatRoom);
                }
                clients.remove(ctx);
                usersInSameRoomAsMeCTX.remove(ctx);
            });
        });
        
    }


}