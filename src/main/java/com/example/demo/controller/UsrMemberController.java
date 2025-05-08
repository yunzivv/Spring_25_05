package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;

import util.Ut;

@Controller
public class UsrMemberController {
	
	@Autowired
	private MemberService memberService;


	// 액션메서드
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickName, String cellPhone, String email) {
		
		if(Ut.isIncorrectParams(loginId)) return "올바르지 않은 아이디";
		if(Ut.isIncorrectParams(loginPw)) return "올바르지 않은 비번";
		if(Ut.isIncorrectParams(name)) return "올바르지 않은 이름";
		if(Ut.isIncorrectParams(nickName)) return "올바르지 않은 닉네임";
		if(Ut.isIncorrectParams(cellPhone)) return "올바르지 않은 전화번호";
		if(Ut.isIncorrectParams(email) || !email.contains("@")) return "올바르지 않은 이메일";
		
		if(memberService.isExistsNameNEmail(name, email)) return "너 이미 가입햇잔아";
		
		if(memberService.isJoinableLogInId(loginId) != 0) {
			return "이미 사용중인 아이디입니다.";
		}
		
		int id = memberService.doJoin(loginId, loginPw, name, nickName, cellPhone, email);
		Member member = memberService.getMemberById(id);
		int joinId = member.getId();
		
		return id + "번 회원 : " + member;
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