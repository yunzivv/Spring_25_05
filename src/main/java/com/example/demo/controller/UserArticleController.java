package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.vo.Article;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class UserArticleController {

	private int id;
	private List<Article> articles;

	public UserArticleController() {
		id = 0;
		articles = new ArrayList<>();
	};
	
	public Article getArticleById(int id) {
		
		for(Article article : articles) {
			if(article.getId() == id) return article;
		}
		return null;
	}

	@RequestMapping("user/article/doAdd")
	@ResponseBody
	public Article getArticle(String title, String body) {

		id++;
		Article article = new Article(id, title, body);
		articles.add(article);

		return article;
	}

	@RequestMapping("user/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {

		return articles;
	}

	@RequestMapping("user/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		String response = id + "번 글은 없음 메롱";
		
		Article article = getArticleById(id);
		if(article != null) {
			articles.remove(article);
			response =  id + "번 글이 삭제 되었습니다.";
		}
		
		return response;
	}
	
	@RequestMapping("user/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {
		String response = id + "번 글은 없음 메롱";
	
		Article article = getArticleById(id);
		if(article == null) {
			return response;
		}
		
		article.setTitle(title);
		article.setBody(body);
		
		response = id + "번 글이 수정되었습니다." + article;
		
		return response;
	}
}