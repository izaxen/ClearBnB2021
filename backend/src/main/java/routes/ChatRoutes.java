package routes;

import application.Repositories;
import chat.SocketMsg;
import entityDO.User;
import express.Express;
import io.javalin.websocket.WsContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoutes {

    Express app;
    List<WsContext> clients = new ArrayList<>();
    Repositories repositories;
    private static Map<WsContext, User> userUsernameMap = new ConcurrentHashMap<>();
    User user;

    public ChatRoutes() {

    }

    public ChatRoutes(Express app, Repositories repositories){
        this.repositories = new Repositories();

        app.ws("/websockets/:id", ws -> {
            ws.onConnect(ctx -> {
                System.out.println("Connected");

                int userID = Integer.parseInt(ctx.pathParam("id"));
                user = repositories.getUserRepository().findUserById(userID);
                userUsernameMap.put(ctx,user);
                clients.add(ctx);
            });

            ws.onMessage(ctx -> {
                SocketMsg msg = ctx.message(SocketMsg.class); // convert from json

                user = repositories.getUserRepository().findUserById(msg.getUserID());
                System.out.println(user);

                clients.forEach(client -> client.send(msg)); // convert to json and send back to ALL connected clients
//        ctx.send(msg); // convert to json and send back (ONLY to the sender)
            });

            ws.onClose(ctx -> {
                System.out.println("Closed");
                clients.remove(ctx);
            });

            ws.onError(ctx -> {
                System.out.println("Error");
                clients.remove(ctx);
            });
        });
    }
}