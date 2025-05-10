<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN PAGE"></c:set>
<%@ include file="../common/head.jspf"%>


<div>
	
	<div class="title text-neutral-800 text-4xl font-bold mx-4 my-8 px-10">
		<span>
		Article Details of ${article.id }
		</span>
	</div>
	
	<div class="container m-4"
		style="border: gray solid 1px; border-radius: 30px; padding: 20px 30px;">

		<div class="header" style="padding: 10px;">
			<div class="title" style="font-size: 1.7rem; font-weight: 500;">${article.title }</div>

			<div class="articleInfo"
				style="display: flex; justify-content: space-between;">
				
				<div class="writeInfo" style="color: gray;">
					<span> 작성 일자 : ${article.regDate.toString().substring(0, 10)}</span>
					<span> 작성자 : ${article.writerId }</span>
				</div>
				
			</div>
		</div>
		<hr style="color: gray;"/>

		<div class="content" style="padding: 10px;">
			${article.body}
		</div>

	</div>
</div>

<%@ include file="../common/foot.jspf"%>