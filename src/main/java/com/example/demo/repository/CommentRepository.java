package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Comment;
import com.example.demo.vo.Like;

@Mapper
public interface CommentRepository {

	List<Comment> getComments(int id);

	

}