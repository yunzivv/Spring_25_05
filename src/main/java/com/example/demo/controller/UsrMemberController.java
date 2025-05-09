package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;
import util.Ut;

@Controller
public class UsrMemberController {
	
	@Autowired
	private MemberService memberService;

	public UsrMemberController(HttpSession session) {
		
	}
	// 액션메서드
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickName, String cellPhone, String email) {
		
		if(Ut.isEmpty(loginId)) return ResultData.from("F-1", "아이디를 입력하세요");
		if(Ut.isEmpty(loginPw)) return ResultData.from("F-2", "비밀번호를 입력하세요");
		if(Ut.isEmpty(name)) return ResultData.from("F-3", "이름을 입력하세요");
		if(Ut.isEmpty(nickName)) return ResultData.from("F-4", "닉네임을 입력하세요");
		if(Ut.isEmpty(cellPhone)) return ResultData.from("F-5", "전화번호를 입력하세요");
		if(Ut.isEmpty(email) || !email.contains("@")) return ResultData.from("F-6", "이메일을 입력하세요");

		int id = memberService.doJoin(loginId, loginPw, name, nickName, cellPhone, email);
		
		if(id == -1) return ResultData.from("F-7", Ut.f("%s는 이미 사용중인 아이디입니다.", loginId));
		if(id == -2) return ResultData.from("F-8",  Ut.f("이미 사용 중인 이름, 이메일입니다."));
		
		Member member = memberService.getMemberById(id);
		int joinId = member.getId();
		
		return ResultData.from("S-1",  Ut.f("%d번 회원 가입 완료.", id), member);
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(HttpSession session, String loginId, String loginPw) {

		if((Member)session.getAttribute("loginedMember") != null) return ResultData.from("F-10",  "이미 로그인 되어있음");
		
		if(Ut.isEmpty(loginId)) return ResultData.from("F-1", "아이디를 입력하세요.");
		if(Ut.isEmpty(loginPw)) return ResultData.from("F-1", "비밀번호를 입력하세요.");
		
		Member member = memberService.doLogin(loginId);
		if(member == null) return ResultData.from("F-9", "존재하지 않는 아이디입니다.");
		if(!member.getLoginPw().equals(loginPw)) return ResultData.from("F-5", "올바르지 않은 비밀번호 입니다.");
	
		session.setAttribute("loginedMember", member);
		session.setAttribute("loginedMemberId", member.getId());
		
		return ResultData.from("S-2",  Ut.f("%s님 로그인 되었습니다.", member.getName()), member);
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession session) {

		if((Member)session.getAttribute("loginedMember") == null) return ResultData.from("F-10",  "이미 로그아웃 되어있음");
		
		session.removeAttribute("loginedMember");
		session.removeAttribute("loginedMemberId");
		
		return ResultData.from("S-3", "로그아웃 되었습니다.");
		
	}

	
}