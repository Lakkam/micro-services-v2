package com.lakkam.currencyexchangeservice.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<EmployeeResponseError> handleCurrencyExchangeException(CurrencyExchangeNotFoundException exe) {
		EmployeeResponseError employeeRes = new EmployeeResponseError();
		employeeRes.setStatus(HttpStatus.NOT_FOUND.value());
		employeeRes.setMessage(exe.getMessage());
		employeeRes.setTimestamp(System.currentTimeMillis());

		return new ResponseEntity<EmployeeResponseError>(employeeRes, HttpStatus.NOT_FOUND);

	}

}
