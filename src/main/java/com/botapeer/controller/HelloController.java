package com.botapeer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api")
public class HelloController {

	@RequestMapping("/hello")
	private String init() {
		return "hello";
	}
}
