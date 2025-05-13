<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Article List"></c:set>
<%@ include file="../common/head.jspf"%>

<div class="container mx-auto w-5/6">
	<div class="flex justify-between text-neutral-800 text-4xl font-bold mx-2 mt-20 mb-6">
		<span>
		Article List
		</span>
			
		<div class="search-bar flex justify-between px-2 text-sm mt-auto mb-0">
			<form class="search-box flex h-8" action="list" method="POST">
<!-- 				<div class="border rounded-md border-neutral-400 overflow-hidden mx-2 w-80 flex bg-neutral-800"> -->
<%-- 					<input type="text" name="keyword" placeholder="search keyword" class="block flex-grow p-4" value="${not empty keyword ? keyword : ''}"/> --%>
<!-- 					<button type="submit"> -->
<!-- 						<i class="fa-solid fa-magnifying-glass text-neutral-200 text-xl px-2"></i> -->
<!-- 					</button> -->
<!-- 				</div> -->

				<label class="input border border-solid border-neutral-500 mx-2 overflow-hidden rounded-md">
				  <svg class="h-[1em] opacity-70" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
				    <g stroke-linejoin="round" stroke-linecap="round" stroke-width="2.5" fill="none" stroke="currentColor">
				      <circle cx="11" cy="11" r="8"></circle>
				      <path d="m21 21-4.3-4.3"></path>
				    </g>
				  </svg>
				  <input type="text" name="keyword" placeholder="Search"  value="${not empty keyword ? keyword : ''}"/>
				</label>
			</form>
			<button class="btn">
				<a class="block w-28 h-full text-base flex items-center justify-center font-large bg-neutral-800 text-neutral-200 rounded-md hover:bg-neutral-700" href="write">새 글 작성</a>
			</button>
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
					<tr class="border-b bg-neutral-200 border-neutral-300">
						<td class="px-5 py-3">${article.id }</td>
						<td><a class="block text-left pl-6 hover:underline" href="detail?id=${article.id }">${article.title }</td>
						<td class="px-5 py-3">${article.extra_writer }</td>
						<td class="px-5 py-3">${article.regDate.toString().substring(0, 10) }</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
	</div>
</div>

<%@ include file="../common/foot.jspf"%>