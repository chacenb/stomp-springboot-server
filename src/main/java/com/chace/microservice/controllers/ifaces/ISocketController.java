package com.chace.microservice.controllers.ifaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public interface ISocketController {

    @MessageMapping("/chat")   // client sends to /app/chat
    @SendTo("/topic/messages") // server broadcasts to /topic/messages
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
