package com.lakkam.mircroservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lakkam.mircroservices.limitsservice.bean.Limits;
import com.lakkam.mircroservices.limitsservice.config.Configuration;

@RestController
public class LimitsController {

	@Autowired
	Configuration config;

	@GetMapping("/limits")
	public Limits getLimits() {
		return new Limits(config.getMin(), config.getMax());

	}

}
