package routes;


import application.LogicHandler;
import dtos.ChatMessageDTO;
import express.Express;

public class ChatRoutes {

    public ChatRoutes() {

    }

    public ChatRoutes(Express app, LogicHandler logicHandler) {


        app.ws("/websockets/:id", ws -> {
            ws.onConnect(ctx -> {
                logicHandler.getChatLogic().connectToServer(ctx);
            });

            ws.onMessage(ctx -> {
                logicHandler.getChatLogic().onMessage(ctx, ctx.message(ChatMessageDTO.class));
            });

            ws.onClose(ctx -> {
                logicHandler.getChatLogic().onClose(ctx);
            });

            ws.onError(ctx -> {
                logicHandler.getChatLogic().onError(ctx);
            });
        });
    }
}