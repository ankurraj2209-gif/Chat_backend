# Chat_backend

Tech Stack-:
Java 17+
Spring Boot
Spring WebSocket (STOMP)
Spring Data JPA
MySQL 
Hibernate

prerequiste :-
Create User (REST)
Users must be created before connecting to WebSocket.
End point:  POST /users
Requestbody{
  "username": "Gaurav"
}

WebSocket Connection Flow :-

1.The initial connection is made using an HTTP request, which is then upgraded to a WebSocket connection during the         handshake phase.
http://localhost:8080/ws-test.html?userId=1 (html page need to be created so test.html is proxy)

2.HandshakeInterceptor (Authentication)
Runs before WebSocket session creation.
Responsibilities:
Read userId from query param.
Validate user exists in DB.
Reject connection if user doesnot exist.
Store userId in attributes.

3.CustomHandshakeHandler (Principal Creation)
Reads userId from attributes.
Converts it into Principal.
Binds identity to WebSocket session.
User-specific routing (/user/**).

4.Client Subscription (ONCE)
After connection, client subscribes.
SUBSCRIBE /user/queue/messages

5.One-to-One Chat Messaging
Client sends:
SEND /app/chat.send
{
  "to": "2",
  "content": "Hello"
}**
6.Backend Processing
ChatController receives the message
@MessageMapping("/chat.send")
public void send(ChatMessage message, Principal principal)
Sender is resolved from Principal

7.Message persistence (Database)
Before delivery:Message is saved in DB
Stores:
sender object
receiver object
message
timestamp

 Database is the source of truth.

8.Real-time delivery using user routing
messagingTemplate.convertAndSendToUser(
receiverId,
"/queue/messages",
payload
);
Spring internally routes this to:
/user/2/queue/messages
Only the intended receiver gets the message.

Bidirectional Chat :-
Both users subscribe once
Same endpoint handles:
user1 → user2
user2 → user1

9.Online / Offline User Status

Online status is determined by active WebSocket sessions.

SessionConnectEvent → user is ONLINE
SessionDisconnectEvent → user is OFFLINE

Check User Status (REST)
GET /users/{userId}/status

10.Chat History (REST API)
Endpoint
GET /chat/history?user1=1&user2=2


    ....... Design Decisions.........
   Authentication handled at handshake.
   WebSocket used for real-time only.
   Database is source of truth. 
   Controller → Service → Repository separation.


