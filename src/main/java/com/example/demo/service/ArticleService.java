package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.vo.Article;

public class ArticleService {
	
	int lastId;
	private List<Article> articles;
	
	public ArticleService() {
		lastId = 0;
		articles = new ArrayList<>();
		
	}

	public Article getArticleById(int id) {

		for (Article article : articles) {
			if (article.getId() == id)
				return article;
		}
		return null;
	}

	
	public Article doModify(int id, String title, String body) {
		Article article = getArticleById(id);
		
		article.setTitle(title);
		article.setBody(body);
		
		return article;
		
	}

	public int doDelete(int id) {
		
		Article article = getArticleById(id);
		articles.remove(article);
		
		return id;
	}

	public Article doWrite(String title, String body) {
	
		lastId++;
		Article article = new Article(lastId, title, body);
		articles.add(article);
		
		return article;
	}

	public List<Article> getArticles() {
		return articles;
	}

}
