package com.mab.merchantapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mab.merchantapi.http.WebSecurity;
import com.mab.merchantapi.service.ApplicationUserService;

@RestController
@RequestMapping(value = "/api", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApplicationUserController {

	Logger logger = LoggerFactory.getLogger(ApplicationUserController.class);

	@Autowired	
	ApplicationUserService applicationUserService;

	@Autowired
	WebSecurity webSecurity;

	
}
