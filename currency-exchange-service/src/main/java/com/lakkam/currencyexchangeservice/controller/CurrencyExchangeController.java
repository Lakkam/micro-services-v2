 package com.lakkam.currencyexchangeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lakkam.currencyexchangeservice.controller.bean.CurrencyExchange;
import com.lakkam.currencyexchangeservice.controller.exception.CurrencyExchangeNotFoundException;
import com.lakkam.currencyexchangeservice.jpa.repository.CurrencyExchangeRepository;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	Environment environment;
	
	@Autowired
	CurrencyExchangeRepository repository;
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retriveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		String environmentPort = environment.getProperty("local.server.port");
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
		
		if(currencyExchange==null) {
			throw new CurrencyExchangeNotFoundException("Currency Conversion not found for"+from+"to"+to);
		}
		currencyExchange.setEnvironment(environmentPort);
		return currencyExchange;
		//return new CurrencyExchange(10001, from, to, BigDecimal.valueOf(50), environmentPort);
		
	}

}
