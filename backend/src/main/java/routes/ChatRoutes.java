package routes;

import application.Repositories;
import chat.SocketMsg;
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
    private static Map<WsContext, String> userUsernameMap = new ConcurrentHashMap<>();

    public ChatRoutes() {

    }

    public ChatRoutes(Express app, Repositories repositories){
//        ListingRoutes listingRoutes = new ListingRoutes(app, repositories);
//        System.out.println("Current-User: " + listingRoutes.getCurrentUser());

        app.ws("/websockets", ws -> {
            ws.onConnect(ctx -> {
                ctx.matchedPath();
                System.out.println("Connected " + ctx.queryParam("userID"));
                clients.add(ctx);
            });

            ws.onMessage(ctx -> {
                System.out.println(ctx.message(SocketMsg.class));
                SocketMsg msg = ctx.message(SocketMsg.class); // convert from json
                System.out.println(ctx);
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