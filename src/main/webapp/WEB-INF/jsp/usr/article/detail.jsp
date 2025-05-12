<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Article Detail"></c:set>
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
			
			<div class="articleInfo flex justify-between my-2">
				<div class="writeInfo text-neutral-800 m-1">
					<span> 작성 일자 : ${article.regDate.toString().substring(0, 10)} </span>
					<span> 작성자 : ${article.extra_writerName } </span>
					${memberId }
					${article.writerId }
				</div>

				<c:if test="${memberId eq article.writerId}">
					<div class="articleBtn">
						<button class="mx-1">
							<a href="doModify?id=${article.id}">modify</a>
						</button>
						<button class="mx-1">
							<a onclick="return confirm('정말 삭제할거야? ㅠㅠ😢?');" 
								href="doDelete?id=${article.id}">delete</a>
						</button>
					</div>
				</c:if>
				
			</div>
		</div>
		
		<hr class="border-neutral-400"/>

		<div class="content p-4">
			${article.body}
		</div>
	</div>
</div>


<%@ include file="../common/foot.jspf"%>