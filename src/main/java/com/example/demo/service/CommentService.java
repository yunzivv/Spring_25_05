package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.LikeRepository;
import com.example.demo.vo.Comment;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public List<Comment> getComments(int relId) {
		return commentRepository.getComments(relId);
	}

	public int doCommentWrtie(String relTypeCode, int relId, int loginedMemberId, String body) {
		return commentRepository.doCommentWrtie(relTypeCode, relId, loginedMemberId, body);
	}


}
