package com.chace.microservice.controllers;

import com.chace.microservice.controllers.ifaces.ISocketController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Slf4j @RestController @RequiredArgsConstructor @CrossOrigin("*")
public class SocketController implements ISocketController {

    @Override
    public OutputMessage processMessage(InputMessage input) {
        log.info("Client {} sent message : {}", input.getUser(), input.getMessage());

        // Returns a value from a controller method annotated with "@MessageMapping" and "@SendTo"
        // used in response to a client sending a message to a websocket endpoint
        // Spring automatically takes the return value and sends it to the destination specified in "@SendTo"
        return new OutputMessage("User " + input.getUser() + " says: " + input.getMessage());
    }
}
