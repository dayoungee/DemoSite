package com.web.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        // stomp 접속 주소 url => /ws-stomp
        registry.addEndpoint("/ws-stomp") // 연결된 엔드포인트
                .withSockJS(); // socketJS 를 연결
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        // 메시지를 구독하는 요청 url
        registry.enableSimpleBroker("/sub");
        // 메시지를 방행하는 요청 url
        registry.setApplicationDestinationPrefixes("/pub");
    }
}
