package com.sindhu.springboot.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//Spring should manage this bean and create an instance of this
@Service
public class WelcomeService{
	
	@Value("${welcome.message}")
	private String welcomeMessage;
	
	
	public String retrieveWelcomeMessage() {
		return welcomeMessage;
		//return "GoodLuck for Future";
	}
}