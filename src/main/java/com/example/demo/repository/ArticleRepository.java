package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.vo.Article;

@Component
public class ArticleRepository {
	
	int lastId;
	private List<Article> articles;
	
	public ArticleRepository() {
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
	
	
	public Article doWrite(String title, String body) {
		
		lastId++;
		Article article = new Article(lastId, title, body);
		articles.add(article);
		
		return article;
		
	}
	
	public List<Article> getArticles() {
		
		return articles;
	}
	
	public Article doModify(int id, String title, String body) {
		
		Article article = getArticleById(id);
		if(article == null) return null;
		
		article.setTitle(title);
		article.setBody(body);
		return article;
		
	}


	public int doDelete(int id) {
		
		Article article = getArticleById(id);
		if(article == null) return -1;
		articles.remove(article);
		return id;
	}


}
