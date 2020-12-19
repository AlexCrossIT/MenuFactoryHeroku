package com.factory.menufactory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebsiteController {

	@GetMapping("/")
	public String openMainPage() {
		
		return "main-page";
		
	}
	
	@GetMapping("/support")
	public String openSupportPage() {
		
		return "support";
	}	
		
}
