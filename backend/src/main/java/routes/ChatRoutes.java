package routes;

import application.Repositories;
import chat.SocketMsg;
import entityDO.User;
import express.Express;
import io.javalin.websocket.WsContext;
import mapper.UserService;
import org.eclipse.jetty.util.ajax.JSON;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoutes {

    Express app;
    List<WsContext> clients = new ArrayList<>();
    Repositories repositories;
    UserService us;
    private static Map<WsContext, User> userUsernameMap = new ConcurrentHashMap<>();

//    User user;

    public ChatRoutes() {

    }

    public ChatRoutes(Express app, Repositories repositories){
        this.repositories = new Repositories();

        app.ws("/websockets/:id", ws -> {
            ws.onConnect(ctx -> {

                int userID = Integer.parseInt(ctx.pathParam("id"));
                User user = repositories.getUserRep().findUserById(userID);
                userUsernameMap.put(ctx,user);

                clients.add(ctx);

                String userFirstName = userUsernameMap.get(ctx).getFirstName();

                System.out.println(userID + " - " + userFirstName + " connected");
                clients.forEach(client -> client.send(userFirstName + " has joint the chat"));
            });

            ws.onMessage(ctx -> {
                String userFirstName = userUsernameMap.get(ctx).getFirstName();
                SocketMsg msg = ctx.message(SocketMsg.class); // convert from json

                clients.forEach(client -> client.send(userFirstName + ": " + msg.getMsg())); // convert to json and send back to ALL connected clients
//        ctx.send(msg); // convert to json and send back (ONLY to the sender)
            });

            ws.onClose(ctx -> {
                clients.remove(ctx);
                String userFirstName = userUsernameMap.get(ctx).getFirstName();
                int userID = Integer.parseInt(ctx.pathParam("id"));
                System.out.println(userID + " - " + userFirstName + " disconnected");
                clients.forEach(client -> client.send(userFirstName + " has left the chat"));
            });

            ws.onError(ctx -> {
                System.out.println("Error");
                clients.remove(ctx);
            });
        });
        
    }


}