package org.mom.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final List<String> users;

    public ChatController() {
        users = new ArrayList<>();
    }

    @MessageMapping("/chat")
    @SendTo("/topic/response")
    public String chat(String message) throws Exception {
        return message;
    }

    @MessageMapping("/connect")
    @SendTo("/topic/connections")
    public String connect(String name) throws Exception {
        users.add(name);
        return String.format("%s is now Connected!", name);
    }
}
