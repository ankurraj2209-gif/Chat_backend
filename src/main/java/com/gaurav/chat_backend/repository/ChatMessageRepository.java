package com.gaurav.chat_backend.repository;

import com.gaurav.chat_backend.entity.ChatMessage;
import com.gaurav.chat_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    ChatMessage save(ChatMessage chatMessage);

    List<ChatMessage>
    findBySenderAndReceiverOrSenderAndReceiverOrderByTimestampAsc(
            User sender1, User receiver1,
            User sender2, User receiver2
    );
}
