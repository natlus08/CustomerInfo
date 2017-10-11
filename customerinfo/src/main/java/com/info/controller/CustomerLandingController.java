package com.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 
 * @author 387090
 *
 */
@Controller
public class CustomerLandingController {
	@GetMapping("/")
	String getIndexPage() {
		return "index";
	}
}
