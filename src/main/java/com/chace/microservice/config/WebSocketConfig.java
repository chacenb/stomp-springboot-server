package com.chace.microservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import static com.chace.microservice.utilities.FATUtils.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /* Clients connect here: ws://localhost:8081/socket */
        // registry.addEndpoint(SOCKET_ENDPOINT).setAllowedOrigins("*").withSockJS();    // This works too but raises error .....
        registry.addEndpoint(SOCKET_ENDPOINT).setAllowedOriginPatterns("*").withSockJS(); // ✅ allows any origin with credentials .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Prefix used for messages that go from client → server.
        registry.setApplicationDestinationPrefixes(SOCKET_INPUT_PREFIX);

        // Prefix used for messages that go from server → client.
        registry.enableSimpleBroker(SOCKET_OUTPUT_PREFIX);
    }
}
