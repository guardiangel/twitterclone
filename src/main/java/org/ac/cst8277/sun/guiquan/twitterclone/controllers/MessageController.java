package org.ac.cst8277.sun.guiquan.twitterclone.controllers;

import jakarta.annotation.Resource;
import org.ac.cst8277.sun.guiquan.twitterclone.entities.MessageEntity;
import org.ac.cst8277.sun.guiquan.twitterclone.services.MessageService;
import org.ac.cst8277.sun.guiquan.twitterclone.utils.JSONResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MessageController {
    @Resource(name = "jsonResult")
    private JSONResult jsonResult;
    @Resource(name = "messageService")
    private MessageService messageService;

    @GetMapping("/getMessagesByProducerId")
    public JSONResult<Object> getMessagesByProducerId(@RequestHeader("token") String token,
                                                      @RequestParam("userId") String userId) {
        boolean flag = messageService.verifyToken(token);
        List<MessageEntity> entityList;
        if (flag) {
            entityList = messageService.getMessagesByProducerId(userId);
            return jsonResult.success(HttpStatus.OK.value(), entityList);
        } else {
            return jsonResult.error(HttpStatus.UNAUTHORIZED.value(),
                    "The current token is not valid.");
        }
    }

    @GetMapping("/getAllMessages")
    public JSONResult<Object> getAllMessages(@RequestHeader("token") String token) {
        boolean flag = messageService.verifyToken(token);
        List<MessageEntity> entityList;
        if (flag) {
            entityList = messageService.getAllMessages();
            return jsonResult.success(HttpStatus.OK.value(), entityList);
        } else {
            return jsonResult.error(HttpStatus.UNAUTHORIZED.value(),
                    "The current token is not valid.");
        }
    }

    @GetMapping("/getMessagesBySubscriberId")
    public JSONResult<Object> getMessagesBySubscriberId(@RequestHeader("token") String token,
                                                      @RequestParam("userId") String userId) {
        boolean flag = messageService.verifyToken(token);
        List<MessageEntity> entityList;
        if (flag) {
            entityList = messageService.getMessagesBySubscriberId(userId);
            return jsonResult.success(HttpStatus.OK.value(), entityList);
        } else {
            return jsonResult.error(HttpStatus.UNAUTHORIZED.value(),
                    "The current token is not valid.");
        }
    }
}
