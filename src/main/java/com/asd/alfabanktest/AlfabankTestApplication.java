package com.asd.alfabanktest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/* первый раз работаю с Feign, делаю по наитию (документации) */
@SpringBootApplication
@EnableFeignClients
public class AlfabankTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlfabankTestApplication.class, args);
	}

}
