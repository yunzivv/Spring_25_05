package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DemoApplication;
import com.example.demo.service.ArticleService;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import util.Ut;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;

	// 액션메서드
	@RequestMapping("/usr/article/detail")
	public String getArticle(Model model, HttpSession session, int id) {

		boolean isLogined = false;
		int loginedMemberId = 0;
		
		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		}
		
		Article article = articleService.getArticleForPrint(id, loginedMemberId);
		model.addAttribute("article", article); // article 필드에 article 정보, 접근 권한까지 포함되어있음
		
		return "/usr/article/detail";
	}

	@RequestMapping("/usr/article/list")
	public String getArticles(Model model) {

//		return ResultData.from("S-2", Ut.f("게시글 목록"), articles);
//		ResultData rd = ResultData.from("S-2", Ut.f("게시글 목록"), articles);
	
		List<Article> articles = articleService.getArticles();

		model.addAttribute("articles", articles);
		
		return "/usr/article/list";
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(HttpSession session, String title, String body) {

		if ((Member) session.getAttribute("loginedMember") == null) {
			return ResultData.from("F-3", "로그인 후 가능", Ut.f("logined : %b", session.getAttribute("isLogined")));
		}

		Member member = (Member) session.getAttribute("loginedMember");

		if (Ut.isEmpty(title))
			return ResultData.from("F-2", "제목 써");
		if (Ut.isEmpty(body))
			return ResultData.from("F-2", "내용 써");

		Article article = articleService.writeArticle(title, body, member.getId());
		return ResultData.from("S-3", Ut.f("게시글 %d 번 작성 완료", articleService.getLastInsertId()), article);
	}
	
	@RequestMapping("/usr/article/modify")
	public Object modify(Model model, HttpSession session, int id, String title, String body) {
		Member member = (Member) session.getAttribute("loginedMember");

		if (member == null) {
			return ResultData.from("F-3", "로그인 후 가능");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없거던", id));
		}

		if (article.getWriterId() != member.getId())
			return ResultData.from("F-A", "권한 없음");

		model.addAttribute("article", article);
		
		return "/usr/article/modify";
	}

	// 로그인 체크 -> 유무 체크 -> 권한 체크
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {

		articleService.modifyArticle(id, title, body);

		return ResultData.from("S-4", Ut.f("게시글 %d번 수정 완료", id),articleService.getArticleById(id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpSession session, int id) {

		Member member = (Member) session.getAttribute("loginedMember");

		if (member == null) {
			return ResultData.from("F-3", "로그인 후 가능");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 게시글은 없거던", id));
		}

		if (article.getWriterId() != member.getId())
			return ResultData.from("F-A", "권한 없음");

		articleService.deleteArticle(id);

		return ResultData.from("S-5", Ut.f("게시글 %d번 삭제 완료", id));
	}
}