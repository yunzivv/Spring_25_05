package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.vo.Article;

public class ArticleService {
	
	private ArticleRepository articleRepository;
	
	public ArticleService(ArticleRepository articleRepository) {

		articleRepository = new ArticleRepository();
		
	}
	
	public Article doWrite(String title, String body) {
		
		return articleRepository.doWrite(title, body);
	}

	
	public Article doModify(int id, String title, String body) {
		
		return articleRepository.doModify(id, title, body);
	}

	public int doDelete(int id) {
		
		return articleRepository.doDelete(id);
	}

	public List<Article> getArticles() {
		
		return articleRepository.getArticles();
	}

}
