package com.lakkam.currencyexchangeservice.controller;

import java.util.HashMap;

 import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class Resilience4jController {

	Logger logger = LoggerFactory.getLogger(Resilience4jController.class);
	
	@GetMapping("/sample-api")
	//@Retry(name="sample-api",fallbackMethod="stubResponse")
	@CircuitBreaker(name="default",fallbackMethod="stubResponse")
	public String testResilience() {
		logger.info("Sample API call received...");
		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-url", String.class);
		// return "SampleAPI";
		return forEntity.getBody();
	}
	
	public String stubResponse(Exception ex) {
		return "Fallback stubResponse";
	}

}
