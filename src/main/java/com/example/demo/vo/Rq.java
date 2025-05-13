package com.example.demo.vo;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import util.Ut;

public class Rq {

	@Getter
	private boolean isLogined;
	@Getter
	private int loginedMemberId;
	
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private HttpSession session;
	
	public Rq(HttpServletRequest req, HttpServletResponse resp) {
		
		this.req = req;
		this.resp = resp;
		this.session = req.getSession();

		HttpSession httpSession = req.getSession();
		
		if(httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int)httpSession.getAttribute("loginedMemberId");
		}
	}

	public void printHistoryBack(String msg) throws IOException {
		
		resp.setContentType("text/html; charset=UTF-8");
		println("<script>");
		if (!Ut.isEmpty(msg)) {
			println("alert('" + msg + "');");
		}
		println("history.back();");
		println("</script>");

	}

	private void println(String str) throws IOException {
		print(str + "\n");

	}

	private void print(String str) throws IOException {
		resp.getWriter().append(str);
	}
	
	public void login(Member member) {
		
		session.setAttribute("loginedMember", member);
		session.setAttribute("loginedMemberId", member.getId());
		System.out.println(session.getAttribute("loginedMemberId"));
	}

	public void logout() {
		session.removeAttribute("loginedMemberId");
	}
}
