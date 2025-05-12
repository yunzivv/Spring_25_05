package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
	public Article(String title, String body, int writerId) {
		this.title = title;
		this.body = body;
		this.writerId = writerId;
	}

	private int id;
	private LocalDateTime regDate;
	private LocalDateTime updateDate;
	private String title;
	private String body;
	private int writerId;
	
	private String extra_writer;
	private boolean userCanModify;

}