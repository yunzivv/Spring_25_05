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
	
	public Member getMemberById(int id) {
		
		return memberRepository.getMemberById(id);
	}
	
	public int isJoinableLogInId(String loginId) {
		
		return memberRepository.isJoinableLogInId(loginId);
	}

	public int doJoin(String loginId, String loginPw, String name, String nickName, String cellPhone, String email) {
		
		memberRepository.doJoin(loginId, loginPw, name, nickName, cellPhone, email);
		return memberRepository.getLastInsertId(); // 방금 가입된 멤버의 id 반환
	}


}