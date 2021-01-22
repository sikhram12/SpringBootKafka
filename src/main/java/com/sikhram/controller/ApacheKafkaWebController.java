package com.sikhram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sikhram.service.KafkaSender;

@RestController
@RequestMapping(value = "/javainuse-kafka/")
public class ApacheKafkaWebController {

	@Autowired
	KafkaSender kafkaSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("message") String message) {
		kafkaSender.send(message);

		return "Message sent to the Kafka Topic java_in_use_topic Successfully";
	}
	
	@KafkaListener(topics = "test")
    public void consume(String message) {
        System.out.println(String.format("$$ -> Consumed Message -> %s", message));
    }
	
	@KafkaListener(topics = "java_in_use_topic")
    public void consume1(String message) {
        System.out.println(String.format("$$ -> Consumed Message -> %s", message));
    }

}

