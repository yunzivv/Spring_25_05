<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="Article List"></c:set>
<%@ include file="../common/head.jspf"%>

<div class="container mx-auto">

	<div class="title text-neutral-800 text-4xl font-bold mx-2 my-6">
		<span>
		Article List
		</span>
	</div>
	<div class="w-8/12 mx-auto my-4 border-solid border bg-red-300">
		<table class="w-full text-sm text-center rtl:text-right text-neutral-800">
				<thead class="text-base text-neutral-200 bg-neutral-800">
					<tr>
						<th scope="col" class="px-5 py-3">NO</th>
						<th scope="col" class="px-5 py-3 w-3/5">TITLE</th>
						<th scope="col" class="px-5 py-3">WRITER</th>
						<th scope="col" class="px-5 py-3">REGISTATION DATE</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="article" items="${articles }">
					<tr class="border-b bg-neutral-100 border-neutral-300">
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