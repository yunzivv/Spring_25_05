package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository; 
import com.example.demo.vo.Member;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	public int getMemberById(int id) {
		
		return 0;
	}


	public int doJoin(String loginId, String loginPw, String name, String nickName, String cellPhone, String email) {
		memberRepository.doJoin(loginId, loginPw, name, nickName, cellPhone, email);
		Member member = new Member(loginId, loginPw, name, nickName, cellPhone, email);
		return member.getId();
	}


}