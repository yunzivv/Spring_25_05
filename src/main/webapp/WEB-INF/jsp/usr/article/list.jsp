<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.example.demo.vo.ResultData"%>
<%@page import="com.example.demo.vo.Article"%>
<%
ResultData rd = (ResultData) request.getAttribute("rd");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
body {
 text-align: center;
}
</style>
<title>Article List</title>
</head>
<body>
	<h2>article list</h2>
	<%List<Article> articles = (List<Article>)rd.getData1(); %>
	<div class="w-8/12 mx-auto my-4 border-solid border bg-red-300">
		<table class="w-full text-sm text-center rtl:text-right text-neutral-800 text-neutral-400">
				<thead class="text-base text-neutral-200 bg-neutral-800">
					<tr>
						<th scope="col" class="px-5 py-3">NO</th>
						<th scope="col" class="px-5 py-3 w-3/5">TITLE</th>
						<th scope="col" class="px-5 py-3">WRITER</th>
						<th scope="col" class="px-5 py-3">REGDATE</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Article article : articles) {
					%>
					<tr class="border-b bg-neutral-100 border-neutral-300 text-neutral-500">
						<td class="px-5 py-3"><%=article.getId()%></td>
						<td><a class="block text-left pl-6" href="detail?id=<%=article.getId()%>"><%=article.getTitle()%></td>
						<td class="px-5 py-3"><%=article.getWriterId()%></td>
						<td class="px-5 py-3"><%=article.getRegDate().toString().substring(0, 10)%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
	</div>
<script src="https://cdn.tailwindcss.com"></script>
</body>
</html>