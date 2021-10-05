package routes;

import application.ChatLogic;
import application.Repositories;
import dtos.ChatMessageDTO;
import express.Express;

public class ChatRoutes {

    ChatLogic chatLogic;
    Repositories repositories;

    public ChatRoutes() {

    }

    public ChatRoutes(Express app, Repositories repositories) {
        this.repositories = repositories;
        chatLogic = new ChatLogic(repositories);


        app.ws("/websockets/:id", ws -> {
            ws.onConnect(ctx -> {
                chatLogic.connectToServer(ctx);
            });

            ws.onMessage(ctx -> {
                chatLogic.onMessage(ctx, ctx.message(ChatMessageDTO.class));
            });

            ws.onClose(ctx -> {
                chatLogic.onClose(ctx);
            });

            ws.onError(ctx -> {
                chatLogic.onError(ctx);
            });
        });
    }
}