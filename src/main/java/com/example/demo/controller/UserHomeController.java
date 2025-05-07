package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserHomeController {
	
	@RequestMapping("user/home/main")
	@ResponseBody
	public String showMain() {
		return "keroro";
	}
	
	@RequestMapping("user/home/main2")
	@ResponseBody
	public String showMain2() {
		return "kululu";
	}
	
	@RequestMapping("user/home/main3")
	@ResponseBody
	public int showMain3() {
		return 1 + 2;
	}
	
	
	private int a;
	
	public UserHomeController() {
		a = 0;
	}
	
	@RequestMapping("user/home/main4")
	@ResponseBody
	public int showMain4() {
		return a++;
	}
	
	@RequestMapping("user/home/setCount_0")
	@ResponseBody
	public int setCount_0() {
		a = 0; // 0으로 초기화
		return 0;
	}
	
	@RequestMapping("user/home/setCount_re")
	@ResponseBody
	public int setCount_re(int value) { // setCount_re?value=35432 형식으로 value 값을 넘겨줄 수 있다.
		this.a = value;
		return a;
	}
}
