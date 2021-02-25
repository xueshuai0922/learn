package com.xs.activemq.bootmq.controller;

import com.xs.activemq.bootmq.producer.QueueProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * @author xueshuai
 * @date 2021/2/2 10:44
 * @description 生产者controller
 */
@RestController
public class ProducerController {
    @Resource
    private QueueProducer producer;

    @Resource
    private Queue bootQueue;

    @GetMapping("/produce")
    public void produce() {
        producer.send(bootQueue, "测试一条消息");
    }
}
