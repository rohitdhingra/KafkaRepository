package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class KafkaPublisherApplication {
	
	@Autowired
	private KafkaTemplate<String,Object> template;
	
	private String topic = "kafka-topic";
	
	@GetMapping("/publish/{name}")
	public String publishMessage(@PathVariable String name)
	{
		template.send(topic, "Hi"+name+"  Welcome to Kafka Learning");
	
		return "Data published";
	}

	@GetMapping("/publishJson")
	public String publishMessage()
	{
		User user = new User(123,"User",new String[] {"Banglore","BTM","house90"}); 
		template.send(topic, user);
	
		return "Json Data published";
	}

	
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaPublisherApplication.class, args);
	}

}
