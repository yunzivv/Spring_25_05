<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Article Modify"></c:set>
<%@ include file="../common/head.jspf"%>


<button onclick="history.back()" class="block text-4xl pl-10 pt-6 cursor-pointer">
		<i class="fa-solid fa-angle-left"></i>
</button>
<div class="container mx-auto">
<div class="title text-neutral-800 text-4xl font-bold mx-2 my-6">
	<span>
	Article ${article.id} Modify
	</span>
</div>
	<div class="border bg-neutral-100 border-neutral-400 rounded-3xl px-8 py-5">
		<form action="doModify" method="POST">
			<div style="display: flex; flex-direction: column; justify-content: center;">
				<input type="hidden" name="id" value="${article.id}">
				<section>
					<input type="text" name="title" value="${article.title}">
				</section>
				<br>
				<section>
					<textarea name="body">${article.body}</textarea>
				</section>
				<br>
				<div class="flex justify-end">
					<button class="border border-neutral-400 rounded-lg px-3 py-2 hover:bg-neutral-300" 
					type="submit">수정하기</button>
				</div>
			</div>
		</form>
	</div>
</div>
<%@ include file="../common/foot.jspf"%>