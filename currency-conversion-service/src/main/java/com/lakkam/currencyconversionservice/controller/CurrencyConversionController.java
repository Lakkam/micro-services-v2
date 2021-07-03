package com.lakkam.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.lakkam.currencyconversionservice.bean.CurrencyConversion;
import com.lakkam.currencyconversionservice.proxy.CurrencyExchangeProxy;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	CurrencyExchangeProxy currencyExchangeServiceProxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getConversionValue(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		String currenyExchangeURL = "http://localhost:8000//currency-exchange/from/{from}/to/{to}";
		Map<String, String> uriVariables = new HashMap<String, String>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);
		ResponseEntity<CurrencyConversion> conversionEntity = new RestTemplate().getForEntity(currenyExchangeURL,
				CurrencyConversion.class, uriVariables);

		CurrencyConversion currencyConversion = conversionEntity.getBody();
		BigDecimal totalCalculatedAmount = quantity.multiply(currencyConversion.getConversionMultiple());
		return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getConversionMultiple(),
				quantity, totalCalculatedAmount, currencyConversion.getEnvironment());

	}

	@GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getConversionValueFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

  		
		CurrencyConversion currencyConversion = currencyExchangeServiceProxy.retrieveExchangeValue(from, to);

		BigDecimal totalCalculatedAmount = quantity.multiply(currencyConversion.getConversionMultiple());
		return new CurrencyConversion(currencyConversion.getId(), from, to, currencyConversion.getConversionMultiple(),
				quantity, totalCalculatedAmount, currencyConversion.getEnvironment());

	}

}
