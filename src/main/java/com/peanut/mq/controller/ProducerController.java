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
public class ProducerController {
    private static final String CONFIRM_EXCHANGE_NAME = "confirm.exchange";
    private static final String CONFIRM_ROUTING_KEY = "key1";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/confirmMsg/{msg}")
    public void sendMsgController(@PathVariable String msg) {
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME,CONFIRM_ROUTING_KEY,new Gson().toJson(msg));
        log.info("当前时间:{}, 成功发送一条消息:{}", LocalDateTime.now().toString(), msg);
    }
}
