package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.DemoApplication;
import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.vo.Article;
import com.example.demo.vo.Board;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import util.Ut;

@Controller
public class UsrArticleController {

	@Autowired
	private Rq rq;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private BoardService boardService;

	// 액션메서드
	@RequestMapping("/usr/article/detail")
	public String getArticle(Model model, HttpServletRequest req, int id) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getArticleForPrint(id, rq.getLoginedMemberId());
		model.addAttribute("article", article); // article 필드에 article 정보, 접근 권한까지 포함되어있음
		
		return "/usr/article/detail";
	}

	@RequestMapping("/usr/article/list")
	public String getArticles(Model model, String keyword, @RequestParam(defaultValue = "0") int boardId) {

		Board board = boardService.getBoardById(boardId);
		List<Article> articles = articleService.getArticles(keyword, boardId);

		model.addAttribute("articles", articles);
		model.addAttribute("keyword", keyword);
		model.addAttribute("boardId", boardId);
		
		return "/usr/article/list";
	}
	
	@RequestMapping("/usr/article/write")
	public String write() {
		return "/usr/article/write";
	}

	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, String title, String body) {

		Rq rq = (Rq) req.getAttribute("rq");

		if (Ut.isEmpty(title))
			return Ut.jsHistoryBack("F-2", "제목 좀 써");
		if (Ut.isEmpty(body))
			return Ut.jsHistoryBack("F-2", "내용 좀 써");

		Article article = articleService.writeArticle(title, body, rq.getLoginedMemberId());
		int id = articleService.getLastInsertId();
		
		return Ut.jsReplace("S-1", Ut.f("게시글 %d 번 작성 완료", id), Ut.f("../article/detail?id=%d", id));
	}
	
	@RequestMapping("/usr/article/modify")
	public String modify(Model model, HttpServletRequest req, int id) { //, String title, String body

		Rq rq =(Rq) req.getAttribute("rq");

		Article article = articleService.getArticleById(id);

		// 권한이 없다면 이전 페이지로 돌아가야하고,
		if (article == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d 번 게시물은 없으시오", id));
		}

		if (article.getWriterId() != rq.getLoginedMemberId()) {
			return Ut.jsHistoryBack("F-A", "권한 없음");
		}
		
		model.addAttribute("article", article);
		
		// 권한이 있다면 수정페이지로 돌아가야한다.
		return "/usr/article/modify";
	} 

	// 로그인 체크 -> 유무 체크 -> 권한 체크
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public String doModify(int id, String title, String body) {

		articleService.modifyArticle(id, title, body);

		return Ut.jsReplace("S-1",Ut.f("%d 번 게시물 수정 완료", id), Ut.f("../article/detail?id=%d", id));
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		if (rq.isLogined() == false) {
			return Ut.jsReplace("F-3", "로그인 후 이용", "../member/login");
		}

		Article article = articleService.getArticleById(id);

		if (article == null) {
			ResultData.from("F-1", Ut.f("%d번 게시글은 없거던", id));
			return Ut.jsHistoryBack("F-1", Ut.f("%d 번 게시물은 없으시오", id));
		}

		if (article.getWriterId() != rq.getLoginedMemberId()) {
			ResultData.from("F-A", "권한 없음");
			return Ut.jsHistoryBack("F-A", Ut.f("%d번 게시물에 대한 권한이 업습", id));
		}

		articleService.deleteArticle(id);

		return  Ut.jsReplace("S-1", Ut.f("%d번 게시물 삭제 완료", id), "../article/list");
	}
}