package routes;

import chat.SocketMsg;
import express.Express;
import io.javalin.websocket.WsContext;

import java.util.ArrayList;
import java.util.List;

public class ChatRoutes {

    Express app;
    List<WsContext> clients = new ArrayList<>();

    public ChatRoutes(Express app){
        app.ws("/websockets", ws -> {
            ws.onConnect(ctx -> {
                System.out.println("Connected");
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