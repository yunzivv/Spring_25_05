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
		
		if(Ut.isIncorrectParams(loginId)) return Ut.f("%s는 올바르지 않은 아이디입니다.", loginId);
		if(Ut.isIncorrectParams(loginPw)) return Ut.f("%s는 올바르지 않은 비밀번호입니다.", loginPw);
		if(Ut.isIncorrectParams(name)) return Ut.f("%s는 올바르지 않은 이름입니다.", name);
		if(Ut.isIncorrectParams(nickName)) return Ut.f("%s는 올바르지 않은 닉네임입니다.", nickName);
		if(Ut.isIncorrectParams(cellPhone)) return Ut.f("%s는 올바르지 않은 전화번호입니다.", cellPhone);
		if(Ut.isIncorrectParams(email) || !email.contains("@")) return Ut.f("%s는 올바르지 않은 이메일입니다.", email);

		int id = memberService.doJoin(loginId, loginPw, name, nickName, cellPhone, email);
		
		if(id == -1) return Ut.f("%s는 이미 사용중인 아이디입니다.", loginId);
		if(id == -2) return Ut.f("이미 사용 중인 이름, 이메일입니다.");
		
		Member member = memberService.getMemberById(id);
		int joinId = member.getId();
		
		return id + "번 회원 : " + member + " 가입 완료";
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