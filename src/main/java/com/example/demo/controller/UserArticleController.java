package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;

import lombok.AllArgsConstructor;
import lombok.Data;

@Controller
public class UserArticleController {

	@Autowired
	private ArticleService articleService;
	
	
	@RequestMapping("user/article/doAdd")
	@ResponseBody
	public Article getArticle(String title, String body) {

		Article article = articleService.doWrite(title, body);

		return article;
	}

	@RequestMapping("user/article/getArticles")
	@ResponseBody
	public List<Article> getArticles() {

		return articleService.getArticles();
	}

	@RequestMapping("user/article/doDelete")
	@ResponseBody
	public String doDelete(int id) {
		
		if(articleService.doDelete(id) != -1) {
			return  id + "번 글이 삭제 되었습니다.";
		}
		
		return  id + "번 글은 없음 메롱";
	}
	
	@RequestMapping("user/article/doModify")
	@ResponseBody
	 // return 타입을 Object 로 설정하면 String / article 선택해서서 반환 가능
	public String doModify(int id, String title, String body) { 
		
		Article article = articleService.doModify(id, title, body);
		if(article != null) {
			return  id + "번 글이 수정 되었습니다." + article;
		}
		
		return  id + "번 글은 없음 메롱";
	}
}