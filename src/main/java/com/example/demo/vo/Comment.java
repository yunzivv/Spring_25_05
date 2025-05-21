package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

	private int id;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private int memberId;
	private int relId;
	private String body;
	
	private String extra_memberNickName;
	
	private boolean userCanModify;
	private boolean userCanDelete;
	private int userReaction;
	
}
