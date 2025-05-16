<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>


<script>
	const params = {};
	params.id = parseInt('${param.id}');
	
</script>

<script>
// 조회수 증가 함수
	function articleDetail_increaseHits() {
		$.get('../article/doIncHits',{
			id : params.id, <!-- 매개변수 설정 -->
			ajaxMode : 'Y' <!-- 매개변수 설정 -->
		}, function(data){ <!-- incHits의 return 값이 data에 저장된다. -->
			$('.article_detail_hit_count').html("조회수 : " + data.data1);
		}, 'json');
	}
	
// 	좋아요 활성화 함수
 	function articleDetail_like() {
	console.log("affected")
 		$.get('../article/doLike',{
			id : params.id, <!-- 매개변수 설정 -->
			ajaxMode : 'Y' <!-- 매개변수 설정 -->
		}, function(data){ <!-- incHits의 return 값이 data에 저장된다. -->
			$('.like_btn').html(data.data1);
		}, 'json');
	}
	
	$(function() {
		articleDetail_increaseHits();
// 		setTimeout(articleDetail_increaseHits, 2000);

	})
	
</script>

<button onclick="history.back()" class="block text-4xl pl-10 pt-6 cursor-pointer">
		<i class="fa-solid fa-angle-left"></i>
</button>
<div class="article container m-auto">
	
	<div class="title text-neutral-800 text-4xl font-bold mx-2 my-6">
		<span>
		Article Details of ${article.id }
		</span>
	</div>
	
	<div class="border bg-neutral-100 border-neutral-400 rounded-xl px-8 py-5">
	
		<div class="header p-3">
		
			<div class="title text-3xl font-medium">${article.title }</div>
			
			<div class="articleInfo flex my-2">
<!-- 				article info -->
				<div class="writeInfo text-neutral-800 m-1">
					<span> 작성 일자 : ${article.regDate.toString().substring(0, 10)} &nbsp;&nbsp;&nbsp;</span>
					<span> 수정 일자 : ${article.updateDate.toString().substring(0, 10)} &nbsp;&nbsp;&nbsp;</span>
					<span> 작성자 : ${article.extra_writer } &nbsp;&nbsp;&nbsp;</span>
					<span> 게시판 : ${article.extra_boardCode } &nbsp;&nbsp;&nbsp;</span>
					<span class="article_detail_hit_count"> 조회수 : ${article.hits } </span>
				</div>

				<div class="flex-grow"></div>
				
<!-- 				like -->
				<div class="like_box flex items-center justify-center mx-4 text-xl cursor-pointer">			
					<c:choose>
						<c:when test="${article.myLike}">
							<button class="like_btn" onClick="articleDetail_like()">♡</button>
						</c:when>
						<c:otherwise>
							<button class="like_btn text-red-400" onClick="articleDetail_like()">♥</button>
				   		</c:otherwise>
					</c:choose> &nbsp;${article.extra_goodReactionPoint } &nbsp;&nbsp;&nbsp;
					⍤⃝&nbsp; ${article.extra_badReactionPoint }
<!-- 					<form action="doLike" method="POST"> -->
<%-- 					<input type="hidden" name="id" value="${article.id}"> --%>
<%-- 						<c:if test="${article.myLike}"> --%>
<!-- 							<input name="like" type="submit" class="text-red-400 cursor-pointer" value="♥"/> -->
<%-- 						</c:if> --%>
						
<%-- 						<c:if test="${!article.myLike}"> --%>
<!-- 							<button type="submit"> -->
<!-- 								<input name="like" type="submit" class="text-red-400 cursor-pointer bg-red-500" value="♡"/> -->
<!-- 							</button> -->
<%-- 						</c:if>	 --%>
<%-- 						<span>&nbsp;${likes }</span>				 --%>
<!-- 					</form> -->
				</div>
					
<!-- 				modify, delete btn -->
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


<div class="comment-box container flex justify-center flex-col mx-auto mt-4 mb-20">
	<c:if test="${not empty comments }">
		<div class="comments-box my-2 rounded-xl border border-solid border-neutral-500 px-4 py-6">
			<c:forEach var="comment" items="${comments }">
				<hr class="border-neutral-500 my-4"/>
				<div class="flex items-center px-4">
					<div class="avatar">
					  <div class="w-12 rounded">
					    <img src="https://img.daisyui.com/images/profile/demo/batperson@192.webp" />
					  </div>
					</div>
					<strong class="ml-2 mr-6">${comment.extra_memberNickName }</strong>
					<div class="flex-grow">${comment.body }</div>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<div class="input-comment-box">
		<form action="doCommentWrite" class="text-center">
			<input type="hidden" name="id" value="${article.id}">
			<label class="flex items-center border border-neutral-500 mx-auto overflow-hidden rounded-3xl p-2 gap-2">
			  <input type="text" name="body" placeholder="나도 한마디 하기!"
			         class="flex-grow min-w-0 px-2 py-1 focus-within:outline-none" />
			  <input type="submit" value="게시" class="px-3 py-1 font-bold" />
			</label>
		</form>
	</div>
</div>



<%@ include file="../common/foot.jspf"%>