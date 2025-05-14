<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>

<button onclick="history.back()" class="block text-4xl pl-10 cursor-pointer">
	<i class="fa-solid fa-angle-left"></i>
</button>

<div class="container mx-auto w-5/6">
	<div class="flex justify-between text-neutral-800 text-4xl font-bold mx-2 mt-20 mb-6">
		<span>
		Article List
		</span>
			
		<div class="search-bar flex justify-between items-center h-8 px-2 text-sm mt-auto mb-0">
			<form class="search-box flex" action="list" method="POST">				
				<label for="boardId" class="flex">
				  <select name="boardId" id="boardId" 
				    class="block flex justify-center items-center px-8 border border-solid border-neutral-500 rounded-lg overflow-hidden">
				    <option value="0" disabled ${boardId eq 0 ? 'selected' : ''} hidden class="text-neutral-400">게시판 선택</option>
				    <option value="0">전체 게시판</option>
				    <option value="1" ${boardId eq 1 ? 'selected' : ''}>공지사항</option>
				    <option value="2" ${boardId eq 2 ? 'selected' : ''}>자유 게시판</option>
				    <option value="3" ${boardId eq 3 ? 'selected' : ''}>질문과 답변</option>
				  </select>
				</label>

				<label class="input border border-solid border-neutral-500 mx-2 overflow-hidden rounded-md">
<!-- 				  <svg class="h-[1em] opacity-70" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"> -->
<!-- 				    <g stroke-linejoin="round" stroke-linecap="round" stroke-width="2.5" fill="none" stroke="currentColor"> -->
<%-- 				      <circle cx="11" cy="11" r="8"></circle> --%>
<!-- 				      <path d="m21 21-4.3-4.3"></path> -->
<!-- 				    </g> -->
<!-- 				  </svg> -->
				  <input type="text" name="keyword" placeholder="Search"  value="${not empty keyword ? keyword : ''}"/>
				</label>
				<button type="submit" class="btn block flex items-center justify-center p-4 bg-neutral-800 text-neutral-200 text-base font-large rounded-md hover:bg-neutral-700">
					검색
				</button>
				
				<a href="write" 
			class="block ml-6 px-5 whitespace-nowrap text-base flex items-center justify-center font-large rounded-md hover:bg-neutral-300">
			글 작성</a>
			</form>
			
			
			
		</div>
		
	</div>
	
	<div class="mx-auto my-4 border-solid border rounded-xl overflow-hidden">
		<table class="w-full text-sm text-center rtl:text-right text-neutral-800">
				<thead class="h-5 text-base text-neutral-200 bg-neutral-800">
					<tr>
						<th scope="col" class="px-5 py-4">NO</th>
						<th scope="col" class="px-5 py-4 w-3/5">TITLE</th>
						<th scope="col" class="px-5 py-4">WRITER</th>
						<th scope="col" class="px-5 py-4">REGISTATION DATE</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="article" items="${articles }">
					<tr class="border-b bg-neutral-200 border-neutral-300 hover:bg-neutral-300">
						<td class="px-5 py-3">${article.id }</td>
						<td><a class="block text-left pl-6" href="detail?id=${article.id }">${article.title }</a></td>
						<td class="px-5 py-3">${article.extra_writer }</td>
						<td class="px-5 py-3">${article.regDate.toString().substring(0, 10) }</td>
					</tr>
					</c:forEach>
					<c:if test="">
						<td>게시글이 없습니다.</td>
					</c:if>
				</tbody>
			</table>
	</div>
</div>

<%@ include file="../common/foot.jspf"%>