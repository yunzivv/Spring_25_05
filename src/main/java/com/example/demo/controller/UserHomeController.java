package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;
import lombok.Data;


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
	
	@RequestMapping("user/home/main4") // 접근할 때마다 1씩 증가하는 값을 반환
	@ResponseBody
	public int showMain4() {
		return a++;
	}
	
	@RequestMapping("user/home/setCount_0") // 값을 0으로 초기화
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
	
	@RequestMapping("user/home/getDouble") // double 반환
	@ResponseBody
	public double getDouble(double d) {
		return d;
	}
	
	@RequestMapping("user/home/getBoolean") // boolean 반환
	@ResponseBody
	public boolean getBoolean(String bool) {
		return Boolean.parseBoolean(bool); 
		// string으로 true나 숫자 1을 전달해도 안됐다.
		// 쿼리스트링 값을 Boolean.parseBoolean() 하여 가져온다.
	}
	
	@RequestMapping("user/home/getMap") // map 반환
	@ResponseBody
	public Map<Integer, String> getMap(int key, String value) {
		Map<Integer, String> my_map = new HashMap<>();
		my_map.put(key, value);
		return my_map;
	}
	
	@RequestMapping("user/home/getList") // List 반환
	@ResponseBody
	public List<String> getList(String value) {
		List<String> list = new ArrayList<>();
		list.add(value);
		return list;
	}
	
	@RequestMapping("user/home/getArticle") // article 반환
	@ResponseBody
	public Article getArticle(int id, String title, String body) {
		
		Article article = new Article(id, title, body);
		return article;
	}
}

@Data
@AllArgsConstructor
class Article {
	
	int id;
	String title;
	String body;
	
}
