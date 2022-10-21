<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    
<div class="header bg-info d-flex justify-content-between">
	<%-- logo를 y축으로 .header의 가운데에 위치 --%>
	<div class="logo d-flex align-items-center">
		<h1 class="text-white ml-3"><a href="/timeline/timeline_view" class="text-white">Marondalgram</a></h1>
	</div>
	

	<!-- 로그인 정보 -->
	<div>
	<!-- 로그인이 되었을 때만 정보 노출 -->
		<c:if test="${not empty userName}">
			<span>${userName}님 안녕하세요</span>
			<a href="/user/sign_out" class="font-weight-bold ml-3">로그아웃</a>
		</c:if>
	</div>
</div>