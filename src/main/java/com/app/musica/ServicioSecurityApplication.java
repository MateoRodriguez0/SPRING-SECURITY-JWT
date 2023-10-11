package com.app.musica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ServicioSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioSecurityApplication.class, args);
	}

}
