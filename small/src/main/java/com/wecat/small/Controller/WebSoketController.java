package com.wecat.small.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.wecat.small.common.ChatResp;

@Controller
public class WebSoketController {

	
	@Autowired
    SimpMessagingTemplate messagingTemplate;
	
	@MessageMapping("/ws/chat")
    //在springmvc 中可以直接获得principal,principal 中包含当前用户的信息
    public void handleChat(Principal principal, String msg) {
		    String destUser = msg.substring(msg.lastIndexOf(";") + 1, msg.length());
	        String message = msg.substring(0, msg.lastIndexOf(";"));
	        messagingTemplate.convertAndSendToUser(destUser, "/queue/chat", new ChatResp(message, principal.getName()));
	}
	
	@MessageMapping("/ws/nf")
    @SendTo("/topic/nf")
    public String handleNF() {
        return "系统消息";
    }
	
}
