package com.chace.microservice.controllers.ifaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import static com.chace.microservice.utilities.FATUtils.*;

@Controller
public interface ISocketController {

    @MessageMapping(SOCKET_INPUT_CHAT_CHANNEL)   // client sends to /app/chat
    @SendTo(SOCKET_OUTPUT_PREFIX + SOCKET_OUTPUT_MESSAGE_TOPIC) // server broadcasts to this topic
    public OutputMessage processMessage(InputMessage input);


    @Getter @Setter @ToString @AllArgsConstructor
    class InputMessage {
        private String user;
        private String message;
    }

    @Getter @Setter @ToString @AllArgsConstructor
    class OutputMessage {
        private String content;
    }
}
