package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LikeRepository;

@Service
public class LikeService {
	
	@Autowired
	private LikeRepository likeRepository;

	public LikeService(LikeRepository likeRepository) {
		this.likeRepository = likeRepository;
	}

	public int getLikes(int id) {
		return likeRepository.getLikes(id);
	}

	public void insertLike(int loginedMemberId, int id) {
		likeRepository.insertLike(loginedMemberId, id);
	}

	public void deleteLike(int loginedMemberId, int id) {
		likeRepository.deleteLike(loginedMemberId, id);
		
	}

}
