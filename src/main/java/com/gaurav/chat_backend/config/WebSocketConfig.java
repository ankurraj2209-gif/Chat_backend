package com.gaurav.chat_backend.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private UserHandshakeInterceptor interceptor;
    public WebSocketConfig(UserHandshakeInterceptor interceptor) {
        this.interceptor = interceptor;
    }
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       registry.addEndpoint("/ws").setAllowedOrigins("*")
               //.addInterceptors(new UserHandshakeInterceptor())
               //.setHandshakeHandler(new CustomHandshakeHandler())
               .setAllowedOrigins("*");


    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");

    }
}

