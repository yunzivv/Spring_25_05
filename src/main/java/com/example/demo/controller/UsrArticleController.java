package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DemoApplication;
import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import util.Ut;

@Controller
public class UsrArticleController {
	
	@Autowired
	private ArticleService articleService;

	// 액션메서드
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		
		Article article = articleService.getArticleById(id);
		
		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없거던", id));
		}
		
		return ResultData.from("S-1", Ut.f("%d번 게시글", id), article);
	}
	
	@RequestMapping("/usr/article/getArticles")
	@ResponseBody
	public ResultData getArticles() {
		
		List<Article> articles = articleService.getArticles();
		
		return ResultData.from("S-2", Ut.f("게시글 목록"), articles);
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		Article article = articleService.writeArticle(title, body);
		return ResultData.from("S-3", Ut.f("게시글 %d 번 작성 완료", articleService.getLastInsertId()), article);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없거던", id));
		}

		articleService.modifyArticle(id, title, body);

		return ResultData.from("S-4", Ut.f("게시글 %d번 수정 완료", id), article);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없거던", id));
		}

		articleService.deleteArticle(id);

		return ResultData.from("S-5", Ut.f("게시글 %d번 삭제 완료", id));
	}
}