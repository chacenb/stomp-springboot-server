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
        log.info("Processing message from user {}: {}", input.getUser(), input.getMessage());
        return new OutputMessage("User " + input.getUser() + " says: " + input.getMessage());
    }
}
