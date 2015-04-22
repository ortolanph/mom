package org.mom.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    
    @MessageMapping("/chat")
    @SendTo("/topic/response")
    public String chat(String message) throws Exception {
        return message;
    }
}
