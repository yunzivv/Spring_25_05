package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Like;

@Mapper
public interface LikeRepository {

	int getLikes(int id);

	void insertLike(int loginedMemberId, int id);

	void deleteLike(int loginedMemberId, int id);



}