package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaConsumerApplication {
	
	List<String> messages = new ArrayList<>();
	User userFromTopic = null;
	
	@GetMapping("/consumeStringMessage")
	public List<String> consumeMsg()
	{
		
		return messages;
	}
	
	@GetMapping("/consumeJSONMessage")
	public User consumeJSONMsg()
	{		
		return userFromTopic;
	}

	@KafkaListener(groupId = "kafka-topic-1",topics="kafka-topic",containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMsgFromTopic(String data)
	{
		messages.add(data);
		return messages;
	}
	
	@KafkaListener(groupId = "kafka-topic-2",topics="kafka-topic",containerFactory = "userKafkaListenerContainerFactory")
	public User getJSONMsgFromTopic(User user)
	{
		userFromTopic = user;
//		messages.add(data);
		return userFromTopic;
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}

}
