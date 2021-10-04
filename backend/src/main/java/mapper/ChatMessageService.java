package mapper;

import dtos.ChatMessageDTO;
import entityDO.ChatMessage;
import entityDO.CurrentChat;
import entityDO.User;

import java.util.ArrayList;
import java.util.List;

public class ChatMessageService {

    public ChatMessage convertDTOToMessage(ChatMessageDTO dto, User sender, User receiver, CurrentChat chatRoom){
        return new ChatMessage(chatRoom, sender, receiver,dto.getMessage());
    }

    public List<ChatMessageDTO> convertUserMessageToDTOList(List<ChatMessage> chatMessages){
        List<ChatMessageDTO> cmDTOs = new ArrayList<>();
        for ( ChatMessage cm: chatMessages
             ) {
            ChatMessageDTO cmDTO = new ChatMessageDTO(cm.getMessage(), cm.getSender().getId());
            cmDTOs.add(cmDTO);
        }
        return cmDTOs;
    }
}
