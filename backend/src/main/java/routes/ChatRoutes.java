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
    //List<WsContext> clients = new ArrayList<>();
    Repositories repositories;
    UserService us;
    CurrentChat savedChatRoom;
    List<Integer> usersInSameRoomAsMe = new ArrayList<>();
    List<WsContext> usersInSameRoomAsMeCTX = new ArrayList<>();

    // userUsernameMap is to identify ctx with a user in our DB
    private static Map<Integer, WsContext> userNameMap = new ConcurrentHashMap<>();

    // chatRoomMap is to deploy USERID to different chat-room ID
    private static Map<Integer, Integer> chatRoomMap = new ConcurrentHashMap<>();

//    // usersInChatRoomMap is to connect chat-room with an arraylist of all users in that room
//    private static Map<Integer, List<WsContext>> usersInChatRoomMap = new ConcurrentHashMap<>();

    User user;
    User admin;
    int userID;


    public ChatRoutes() {

    }

    public ChatRoutes(Express app, Repositories repositories){
        this.repositories = repositories;
        admin = repositories.getUserRep().findUserById(91);

        app.ws("/websockets/:id", ws -> {
            ws.onConnect(ctx -> {
                userID = Integer.parseInt(ctx.pathParam("id"));

                user = repositories.getUserRep().findUserById(userID);
                // here user gets an identifier, system now knows this ctx belongs to this userID
                userNameMap.put(userID, ctx);

                // if not admin
                if (userID != 91){
                    // if ctx has no chat room
                    // (chatRoomMap.get(userID) should give a ChatRoom ID
                    // (!chatRoomMap.containsKey(userID) means if userID does not have a room in our map
                    if(!chatRoomMap.containsKey(userID)){
                        // create new chatroom for this ctx.
                        CurrentChat chatRoom = new CurrentChat();
                        // calls repo to save the new room in DB
                        savedChatRoom = repositories.getCurrentChatRepository().addCurrentChat(chatRoom).get();
                        int roomID = savedChatRoom.getId();
                        // put this ctx in a room

                        chatRoomMap.put(userID, roomID);

                        // add admin to the room
                        //chatRoomMap.put(91, roomID);
                    }

                    else{
                        // put user in old chat room if log on from other browser
                        chatRoomMap.put(userID, chatRoomMap.get(userID));
                        // check if admin is already in your room, if not, add admin
                        if(!Objects.equals(chatRoomMap.get(userID), chatRoomMap.get(91))){
                            chatRoomMap.put(91, chatRoomMap.get(userID));
                        }
                    }
                    // put user in user arraylist
      //             clients.add(ctx);
                }

                // testing
                System.out.println("UserID: " + userID);
                System.out.println("Is in chat room number: " + chatRoomMap.get(userID));
                System.out.println("All users in this room: ");

                System.out.println("userNamemap: " + chatRoomMap.entrySet());
                // take all userID out from userNameMap, this hashmap contains all users that has logged in
                for (int uid: chatRoomMap.keySet()
                ) {
                    // we get userID here, uid
                    // get Room id for this userID, and compare it
                    System.out.println("uid: " + uid);
                    if (Objects.equals(chatRoomMap.get(uid), chatRoomMap.get(userID))) {
                        // this will give me a list of all userID in my room.
                        usersInSameRoomAsMe.add(uid);
                        // if system finds userID with the same chatroom ID as me, find the paired ctx for that user and add it in a list of CTX
                        // ignore duplicate and invalid items
                        // this will be the list for every user in my room (me + admin)
                        if(userNameMap.get(uid) != null && !usersInSameRoomAsMeCTX.contains(userNameMap.get(uid))){
                            usersInSameRoomAsMeCTX.add(userNameMap.get(uid));
                            System.out.println("usersInSameRoom: " + usersInSameRoomAsMeCTX);
                        }
                    }
                }

                // got 2 ctx of admin
//                System.out.println(usersInSameRoomAsMeCTX);

                String userFirstName = user.getFirstName();

                System.out.println(userID + " - " + userFirstName + " connected");
                usersInSameRoomAsMeCTX.forEach(client -> client.send(userFirstName + " has joint the chat"));
            });

            ws.onMessage(ctx -> {
                // everytime we send a message, update user
                for ( Integer uid : userNameMap.keySet()
                     ) {
                    if(userNameMap.get(uid).equals(ctx)){
                        user = repositories.getUserRepository().findUserById(uid);
                    }
                }
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
     //           clients.remove(ctx);
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
      //          clients.remove(ctx);
                usersInSameRoomAsMeCTX.remove(ctx);
            });
        });
        
    }


}