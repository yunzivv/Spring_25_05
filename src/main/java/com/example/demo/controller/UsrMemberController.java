package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DemoApplication;
import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {
	
	@Autowired
	private MemberService memberService;
	private final DemoApplication demoApplication;
	
	UsrMemberController(DemoApplication demoApplication) { // ?
		this.demoApplication = demoApplication;
	}

	// 액션메서드
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickName, String cellPhone, String email) {
		
		int id = memberService.doJoin(loginId, loginPw, name, nickName, cellPhone, email);
		
		return id + "번 회원가입 완료";
	}
	
//	@RequestMapping("/usr/member/doLogin")
//	@ResponseBody
//	public List<Article> getArticles() {
//		return articleService.getArticles();
//	}
//	
//	@RequestMapping("/usr/member/doLogout")
//	@ResponseBody
//	public Article doAdd(String title, String body) {
//		return articleService.writeArticle(title, body);
//	}

	
}