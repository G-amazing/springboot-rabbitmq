package com.peanut.mq.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@Slf4j
@RestController
public class SendMsgController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{msg}")
    public void sendMsgController(@PathVariable String msg) {
        log.info("当前时间:{}, 发送一条消息给两个TTL队列:{}", LocalDateTime.now().toString(), msg);
        rabbitTemplate.convertAndSend("X","XA",new Gson().toJson("发给10s的队列" + msg));
        rabbitTemplate.convertAndSend("X","XB",new Gson().toJson("发给40s的队列" + msg));
    }

    @GetMapping("/sendTimeMsg/{msg}/{time}")
    public void sendMsgController(@PathVariable String msg, @PathVariable String time) {
        log.info("当前时间:{}, 发送一条时长{}毫秒的消息给TTL队列:{}", LocalDateTime.now().toString(), time, msg);

        rabbitTemplate.convertAndSend("X","XC",new Gson().toJson(msg), correlationData -> {
            correlationData.getMessageProperties().setExpiration(time);
            return correlationData;
        });
    }
}
