package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	@RequestMapping("/usr/home/main")
	public String showMain() {
		return "/usr/home/main";
	}
	
	@RequestMapping("/")
	public String connectMain() {
		return "redirect:/usr/home/main";
	}
}
