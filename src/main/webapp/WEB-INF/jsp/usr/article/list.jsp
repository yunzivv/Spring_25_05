<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Article List"></c:set>
<%@ include file="../common/head.jspf"%>

<div class="container mx-auto w-5/6">
	<div class="flex justify-between text-neutral-800 text-4xl font-bold mx-2 mt-20 mb-6">
		<span>
		Article List
		</span>
		
<%-- 		<c:if test="${keyword.length() != 0 }"> --%>
<%-- 			<input type="text" name="keyword" placeholder="search keyword" class="block flex-grow p-4" value=${keyword }/> --%>
<%-- 		</c:if> --%>
			
		<div class="search-bar px-2 text-sm mt-auto mb-0">
			<form class="search-box flex text-neutral-400 h-8" action="list" method="POST">
<!-- 			<div class="mx-2 border rounded-md border-neutral-400 overflow-hidden"> -->
<!-- 				<input type="date" name="start" class="block h-full p-4"/>  -->
<!-- 			</div> -->
<!-- 			<div class="mx-2  border rounded-md border-neutral-400 overflow-hidden"> -->
<!-- 				<input type="date"  name="end" class="block h-full p-4"/>  -->
<!-- 			</div> -->
<!-- 			<div class="flex-grow"></div> -->
				<div class="border rounded-md border-neutral-400 overflow-hidden mx-2 w-80 flex bg-neutral-800">
					<input type="text" name="keyword" placeholder="search keyword" class="block flex-grow p-4" value="${not empty keyword ? keyword : ''}"/>
					<button type="submit">
						<i class="fa-solid fa-magnifying-glass text-neutral-200 text-xl px-2"></i>
					</button>
				</div>
				<div class="px-2">
					<a class="block w-28 h-8 text-base flex items-center justify-center font-large bg-neutral-800 text-neutral-200 rounded-md hover:bg-neutral-700" href="write">새 글 작성</a>
				</div>
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