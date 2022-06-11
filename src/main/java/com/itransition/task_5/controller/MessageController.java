package com.itransition.task_5.controller;

import com.itransition.task_5.entity.Message;
import com.itransition.task_5.payload.UserMessagesDto;
import com.itransition.task_5.repository.MessageRepository;
import com.itransition.task_5.service.MessageService;
import com.itransition.task_5.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;
    private final MessageRepository messageRepository;

    @GetMapping("/")
    public String mainPage(HttpSession session, Model model){
        if(session.getAttribute("username") == null){
            return "login";
        }

        String username = (String)session.getAttribute("username");
        List<UserMessagesDto> messages =  messageService.getMessages(username);
        model.addAttribute("messages", messages);
        model.addAttribute("username", username);
        return "main-page";
    }

    @PostMapping("messages/new-message")
    public String newMessage(
        @RequestParam String usernames,
        @RequestParam String title,
        @RequestParam String content,
        HttpSession session
    ){
        String currentUserName = (String)session.getAttribute("username");
        messageService.sendMessages(usernames,title,content,currentUserName);
        return "redirect:/";
    }

    @GetMapping("message/{id}")
    public String getMessage(
            @PathVariable Long id,
            Model model
        ){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()){
            Message message = optionalMessage.get();
            model.addAttribute("message",
                    new UserMessagesDto(
                            id,
                            message.getSender().getUsername(),
                            message.getTitle(),
                            message.getContent(),
                            message.getSentTime()
                            ));
        }

        return "message";
    }


}
