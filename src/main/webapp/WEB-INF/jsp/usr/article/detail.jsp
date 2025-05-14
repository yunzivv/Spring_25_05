<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>


<button onclick="history.back()" class="block text-4xl pl-10 pt-6 cursor-pointer">
		<i class="fa-solid fa-angle-left"></i>
</button>
<div class="container m-auto">
	
	<div class="title text-neutral-800 text-4xl font-bold mx-2 my-6">
		<span>
		Article Details of ${article.id }
		</span>
	</div>
	
	<div class="border bg-neutral-100 border-neutral-400 rounded-3xl px-8 py-5">
	
		<div class="header p-3">
		
			<div class="title text-3xl font-medium">${article.title }</div>
			
			<div class="articleInfo flex my-2">
				<div class="writeInfo text-neutral-800 m-1">
					<span> 작성 일자 : ${article.regDate.toString().substring(0, 10)} &nbsp;&nbsp;&nbsp;</span>
					<span> 수정 일자 : ${article.updateDate.toString().substring(0, 10)} &nbsp;&nbsp;&nbsp;</span>
					<span> 작성자 : ${article.extra_writer } &nbsp;&nbsp;&nbsp;</span>
					<span> 게시판 : ${article.extra_boardCode } &nbsp;&nbsp;&nbsp;</span>
					<span> 조회수 : ${article.hits } </span>
				</div>

				<div class="flex-grow"></div>
				
				<div class="like_box flex items-center justify-center mx-4 text-xl cursor-pointer">
					<form action="detail" method="POST">
					<input type="hidden" name="id" value="${article.id}">
						<c:if test="${article.myLike}">
							<input name="like" type="submit" class="text-red-400 cursor-pointer" value="♥"/>
								<span>&nbsp;${likes }</span>
						</c:if>
						
						<c:if test="${!article.myLike}">
							<button type="submit">
								<input name="like" type="submit" class="text-red-400 cursor-pointer" value="♡"/>
								<span>&nbsp;${likes }</span>
							</button>
						</c:if>					
					</form>
				</div>
				
				<div class="btn-box">
					<c:if test="${article.userCanModify}">
						<button class="btn rounded-xl ml-4 mr-1 px-3 hover:bg-neutral-300">
							<a href="modify?id=${article.id}">Modify</a>
						</button>
					</c:if>
					<c:if test="${article.userCanDelete}">
						<button class="btn rounded-xl mx-1 px-3 hover:bg-neutral-300">
							<a onclick="return confirm('정말 삭제할거야? ㅠㅠ😢?');" 
								href="doDelete?id=${article.id}">Delete</a>
						</button>
					</c:if>
				</div>
				
			</div>
		</div>
		
		<hr class="border-neutral-400"/>

		<div class="content mt-2 p-4">
			${article.body}
		</div>
	</div>
</div>


<%@ include file="../common/foot.jspf"%>