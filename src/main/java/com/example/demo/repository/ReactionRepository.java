package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.Like;

@Mapper
public interface ReactionRepository {

	int getUserReaction(int loginedMemberId, int id);

	int getIsReactioned(int loginedMemberId, int id);

	int doGoodReaction(int loginedMemberId, int id);

	int doBadReaction(int loginedMemberId, int id);

	int doChangeReaction(int loginedMemberId, int id, int point);

}