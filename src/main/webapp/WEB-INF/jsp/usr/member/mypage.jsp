<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="MY PAGE"></c:set>
<%@ include file="../common/head.jspf"%>

<button onclick="history.back()" class="block text-4xl pl-10 cursor-pointer">
	<i class="fa-solid fa-angle-left"></i>
</button>

<div class="container w-11/12 mx-auto mt-16 p-4">
		<div class="avatar">
		  <div class="w-24 rounded">
		    <img src="https://img.daisyui.com/images/profile/demo/batperson@192.webp" />
		  </div>
		</div>
</div>

<%@ include file="../common/foot.jspf"%>