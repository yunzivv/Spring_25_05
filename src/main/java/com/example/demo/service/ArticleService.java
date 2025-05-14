package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository; 
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

import util.Ut;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	
	public int getLastInsertId() {
		return articleRepository.getLastInsertId();
	}

	public Article writeArticle(String title, String body, int writerId, int boardId) {
		articleRepository.writeArticle(title, body, writerId, boardId);
		return new Article(title, body, writerId);
	}

	public void deleteArticle(int id) {
		articleRepository.deleteArticle(id);
	}

	public void modifyArticle(int id, String title, String body) {
		articleRepository.modifyArticle(id, title, body);
	}

	public Article getArticleById(int id) {
		return articleRepository.getArticleById(id);
	}

	public List<Article> getArticles(String keyword, int boardId, int searchItem, int limitFrom, int itemsInAPage) {
		return articleRepository.getArticles(keyword, boardId, searchItem, limitFrom, itemsInAPage);
	}

	public Article getArticleForPrint(int id, int loginedMemberId) {
		
		Article article = articleRepository.getArticleForPrint(id);

		updateForPrintData(loginedMemberId, article);
	    
		return article;
	}
	
	private void updateForPrintData(int loginedMemberId, Article article) {
		if (article == null) {
			return;
		}

		ResultData userCanModifyRd = userCanModify(loginedMemberId, article);
		article.setUserCanModify(userCanModifyRd.isSuccess());
		
		ResultData userCanDeleteRd = userCanDelete(loginedMemberId, article);
		article.setUserCanDelete(userCanModifyRd.isSuccess());
	}


	public ResultData userCanModify(int loginedMemberId, Article article) {

		if (article.getWriterId() != loginedMemberId) {
			return ResultData.from("F-A", Ut.f("%d번 게시글 수정 권한 없음", article.getId()));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글 수정 권한 있음", article.getId()));
	}
	
	private ResultData userCanDelete(int loginedMemberId, Article article) {
		
		if (article.getWriterId() != loginedMemberId) {
			return ResultData.from("F-A", Ut.f("%d번 게시글 삭제 권한 없음", article.getId()));
		}

		return ResultData.from("S-1", Ut.f("%d번 게시글 삭제 권한 있음", article.getId()));
	}


	public int getArticleCnt() {
		
		return articleRepository.getArticleCnt();
	}


	public int getArticlesCnt(String keyword, int boardId, int searchItem) {
		return articleRepository.getArticlesCnt(keyword, boardId, searchItem);
	}

}