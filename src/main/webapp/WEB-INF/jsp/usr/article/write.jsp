<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MAIN PAGE"></c:set>
<%@ include file="../common/head.jspf"%>

<button onclick="history.back()" class="block text-4xl pl-10 pt-6 cursor-pointer">
		<i class="fa-solid fa-angle-left"></i>
	</button>
		
	<div class="container mx-auto mt-8 w-3/5">


		<div class="text-4xl text-neutral-800 px-1 py-6">Article Write</div>


		<form action="doWrite" method="POST" class="w-full">
			<div style="display: flex; flex-direction: column; justify-content: center;">
				<section>
					<input class="w-full p-2 border border-neutral-500 border-solid rounded-md " type="text" name="title" placeholder="제목을 입력하세요" />
				</section>
				<br>
				<section>
					<textarea class="resize-none w-full h-96 p-2 border border-neutral-500 border-solid rounded-md" name="body"
						placeholder="내용을 입력하세요"></textarea>
				</section>
				<br>
			</div>
			
			<div class="text-right px-2 pb-2">
			<button type="submit" class="py-2.5 px-5 w-32 text-base font-large bg-neutral-800 text-neutral-200 rounded-lg hover:bg-neutral-700">Write</button>
			</div>

		</form>
	</div>

<%@ include file="../common/foot.jspf"%>