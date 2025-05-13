package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import util.Ut;

@Controller
public class UsrMemberController {
	
	@Autowired
	private MemberService memberService;

	public UsrMemberController(HttpSession session) {
		
	}
	
	@RequestMapping("/usr/member/join")
	public String join() {
		
		return "/usr/member/join";
	}
	
	// 액션메서드
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginId, String loginPw, String checkLoginPw, String name, String nickName, String cellPhone, String email) {
		
		if(Ut.isEmpty(loginId)) return Ut.jsHistoryBack("F-1", "아이디를 쓰시오");
		if(Ut.isEmpty(loginPw)) return Ut.jsHistoryBack("F-2", "비밀번호를 쓰시오");
		if(Ut.isEmpty(name)) return Ut.jsHistoryBack("F-3", "이름을 쓰시오");
		if(Ut.isEmpty(nickName)) return Ut.jsHistoryBack("F-4", "닉네임을 쓰시오");
		if(Ut.isEmpty(cellPhone)) return Ut.jsHistoryBack("F-5", "전화번호 좀 쓰시오");
		if(Ut.isEmpty(email) || !email.contains("@")) return Ut.jsHistoryBack("F-6", "이메일 정확히 쓰시오");
		if(!loginPw.equals(checkLoginPw)) return Ut.jsHistoryBack("F-7", "비밀번호가 일치하지 않소");

		int id = memberService.doJoin(loginId, loginPw, name, nickName, cellPhone, email);
		
		if(id == -1) return Ut.jsHistoryBack("F-8", Ut.f("%s는 이미 사용 중인 아이디입니다.", loginId));
		if(id == -2) return Ut.jsHistoryBack("F-9", Ut.f("이름 %s과 이메일 %s은(는) 이미 사용 중입니다.", loginId, email));
		
		Member member = memberService.getMemberById(id);
		
		return Ut.jsReplace("S-1", Ut.f("%s 님 회원가입을 축하", nickName), "/");
	}
	
	@RequestMapping("/usr/member/login")
	public String login() {
		
		return "/usr/member/login";
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String loginId, String loginPw) {
		
		Rq rq = (Rq) req.getAttribute("rq");
		
		if(Ut.isEmpty(loginId)) return Ut.jsHistoryBack("F-1", "아이디 입력해주세요");
		if(Ut.isEmpty(loginPw)) return Ut.jsHistoryBack("F-2", "비밀번호 입력햇주세요");
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if(member == null) return Ut.jsHistoryBack("F-3", "존재하지 않는 아이디에요");
		if(!member.getLoginPw().equals(loginPw)) return Ut.jsHistoryBack("F-A", "올바르지 않은 비밀번호에요");
	
		rq.login(member);

		return Ut.jsReplace("S-1", Ut.f("%s님 환영합니다", member.getNickName()), "/");
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		rq.logout();
		
		return Ut.jsReplace("S-1", "로그아웃 되었습니다", "/");
		
	}

	
}