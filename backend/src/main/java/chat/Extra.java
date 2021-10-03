package chat;

import application.Repositories;
import express.Express;
import io.javalin.websocket.WsContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Extra {



    class SocketMsg {
        public String msg;
        public int time_sent;
        public SocketMsg(){}
        public String sender;
        public String receiver;

        public SocketMsg(String msg, int time_sent, String sender, String receiver) {
            this.msg = msg;
            this.time_sent = time_sent;
            this.sender = sender;
            this.receiver = receiver;
        }

        @Override
        public String toString() {
            return "SocketMsg{" +
                    "msg='" + msg + '\'' +
                    ", time_sent=" + time_sent +
                    ", sender='" + sender + '\'' +
                    ", receiver='" + receiver + '\'' +
                    '}';
        }
    }


        private List<io.javalin.websocket.WsContext> clients;
        public static void main(String[] args) {
            Express app = new Express();

            app.get("/rest/hello", (req, res) -> {
                res.json(Map.of("message", "Hello from express"));
            });

            // roomID
            Map<String, List<io.javalin.websocket.WsContext>> chatrooms = new HashMap<>();
            app.ws("/websocket/:id", ws -> {
                ws.onConnect(ctx -> {
                    String user = (ctx.pathParam("id"));
                    if(user.equals("admin")){
                        chatrooms.get("1").add(ctx);
                        chatrooms.get("2").add(ctx);
                    }
                    else{
                        List<io.javalin.websocket.WsContext> clients = new ArrayList<>();
                        clients.add(ctx);
                        chatrooms.put(user,clients);}

                    System.out.println("  ");
                });

                ws.onMessage(ctx -> {
                    SocketMsg msg = ctx.message(SocketMsg.class); // convert from json
                    String chat = msg.sender;
                    if(msg.sender.equals("admin")){
                        chat = msg.receiver;
                    }

                    chatrooms.get(chat).forEach(client -> client.send(msg)); // convert to json and send back to ALL connected clients
//        ctx.send(msg); // convert to json and send back (ONLY to the sender)
                });

                ws.onClose(ctx -> {
                    System.out.println("Closed");
                    String chat ="";
                    chatrooms.get(chat).remove(ctx);
                });

                ws.onError(ctx -> {
                    String chat ="";
                    System.out.println("Errored");
                    chatrooms.get(chat).remove(ctx);
                });
            });

        }
    }
