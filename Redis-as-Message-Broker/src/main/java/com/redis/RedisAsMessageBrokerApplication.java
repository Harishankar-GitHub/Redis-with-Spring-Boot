package com.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisAsMessageBrokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisAsMessageBrokerApplication.class, args);
	}

}