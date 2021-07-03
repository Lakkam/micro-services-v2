package com.lakkam.currencyexchangeservice.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lakkam.currencyexchangeservice.controller.bean.CurrencyExchange;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
	
	CurrencyExchange findByFromAndTo(String from, String to);

}
