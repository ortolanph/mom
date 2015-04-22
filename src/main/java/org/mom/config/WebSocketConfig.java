package org.mom.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        System.out.println("Entering registerStompEndpoints");
        registry.addEndpoint("/chat").withSockJS();
        System.out.println("Exiting registerStompEndpoints");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        System.out.println("Entering configureMessageBroker");
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
        System.out.println("Exiting configureMessageBroker");
    }
}
