package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import util.Ut;

@Controller
public class UsrMemberController {
	
	@Autowired
	private MemberService memberService;


	// 액션메서드
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickName, String cellPhone, String email) {
		
		if(Ut.isEmpty(loginId)) return ResultData.from("F-1", Ut.f("%s는 올바르지 않은 아이디입니다.", loginId));
		if(Ut.isEmpty(loginPw)) return ResultData.from("F-1", Ut.f("%s는 올바르지 않은 비밀번호입니다.", loginPw));
		if(Ut.isEmpty(name)) return ResultData.from("F-1", Ut.f("%s는 올바르지 않은 이름입니다.", name));
		if(Ut.isEmpty(nickName)) return ResultData.from("F-1", Ut.f("%s는 올바르지 않은 닉네임입니다.", nickName));
		if(Ut.isEmpty(cellPhone)) return ResultData.from("F-1", Ut.f("%s는 올바르지 않은 전화번호입니다.", cellPhone));
		if(Ut.isEmpty(email) || !email.contains("@")) return ResultData.from("F-1", Ut.f("%s는 올바르지 않은 이메일입니다.", email));

		int id = memberService.doJoin(loginId, loginPw, name, nickName, cellPhone, email);
		
		if(id == -1) return ResultData.from("f-2", Ut.f("%s는 이미 사용중인 아이디입니다.", loginId));
		if(id == -2) return ResultData.from("f-2",  Ut.f("이미 사용 중인 이름, 이메일입니다."));
		
		Member member = memberService.getMemberById(id);
		int joinId = member.getId();
		
		return ResultData.from("S-1",  Ut.f("%d번 회원 가입 완료.", id), member);
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(String loginId, String loginPw) {
		
		if(Ut.isEmpty(loginId)) return ResultData.from("F-1", "아이디를 입력하세요");
		if(Ut.isEmpty(loginPw)) return ResultData.from("F-1", "비밀번호를 입력하세요");
		
		String name = memberService.doLogin(loginId, loginPw);
		
		return ResultData.from("S-2",  Ut.f("%s님 로그인 되었습니다.", name));
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public void doLogout() {
		
	}

	
}