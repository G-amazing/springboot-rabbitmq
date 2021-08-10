package com.peanut.mq.listener;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Slf4j
@Component
public class DeadLetterQueueConsumer {
    @RabbitListener(queues = "QD")
    public void receiveD(final String jsonMsg) {
        String msg = new Gson().fromJson(jsonMsg, String.class);
        log.info("当前时间：{},收到死信队列信息{}", LocalDateTime.now().toString(), msg);
    }
}
