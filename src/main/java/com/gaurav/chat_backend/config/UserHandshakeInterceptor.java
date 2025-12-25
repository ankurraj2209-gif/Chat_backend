package com.gaurav.chat_backend.config;

import com.gaurav.chat_backend.service.AuthenticateUser;
import org.jspecify.annotations.Nullable;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class UserHandshakeInterceptor implements HandshakeInterceptor {
    private AuthenticateUser authenticateUser;
    public UserHandshakeInterceptor(AuthenticateUser authenticateUser) {
        this.authenticateUser = authenticateUser;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        String userId = UriComponentsBuilder
                .fromUri(request.getURI())
                .build()
                .getQueryParams()
                .getFirst("userId");

        if (userId == null || userId.isBlank()) {
            return false;
        }
        Long userid = Long.parseLong(userId);
        boolean isvalid=authenticateUser.authenticate(userid);


        attributes.put("userId", userId);
        return true;
    }



    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, @Nullable Exception exception) {

    }
}