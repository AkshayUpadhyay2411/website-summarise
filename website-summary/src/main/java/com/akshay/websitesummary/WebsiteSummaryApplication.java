package com.akshay.websitesummary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WebsiteSummaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsiteSummaryApplication.class, args);
	}

}
