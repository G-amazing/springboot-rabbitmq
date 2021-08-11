package com.peanut.mq.controller;

import com.google.gson.Gson;
import com.peanut.mq.config.MyCallBack;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Slf4j
@RestController
public class SendConfirmMsgController {
    private static final String CONFIRM_EXCHANGE_NAME = "confirm.exchange";
    private static final String CONFIRM_ROUTING_KEY = "key1";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/confirmMsg/{msg}")
    public void sendMsgController(@PathVariable String msg) {
        CorrelationData correlationData = new CorrelationData("1");
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME, CONFIRM_ROUTING_KEY, new Gson().toJson(msg), correlationData);
        log.info("当前时间:{}, 成功发送一条消息:{}", LocalDateTime.now().toString(), msg);

        CorrelationData correlationData2 = new CorrelationData("2");
        rabbitTemplate.convertAndSend(CONFIRM_EXCHANGE_NAME, CONFIRM_ROUTING_KEY + "2", new Gson().toJson(msg), correlationData2);
        log.info("当前时间:{}, 成功发送一条消息:{}", LocalDateTime.now().toString(), msg);
    }
}
