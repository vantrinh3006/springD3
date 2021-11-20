<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News-List</title>
</head>
<body>
	<div>
<%-- 		<h3 style="color:red; text-align:center">${msg}</h3> --%>
	<c:if test="${not empty msg }">
		<h3 style="color:red; text-align:center">${msg}</h3>
	</c:if>
	</div>
	<h1>List</h1>
	<%-- 		<c:if test="${datas ne null }"> 	<!-- ne : not or != --> --%>
	<!-- 			<ul> -->
	<%-- 				<c:forEach items="${datas}" var="news"> --%>
	<!-- 					<li> -->
	<%-- 						<a href="${pageContext.request.contextPath}/news/detail?id=${news.id}"> --%>
	<%-- 							${news.title} - ${news.author} --%>
	<!-- 						 </a> -->
	<!-- 					 </li> -->
	<%-- 				</c:forEach> --%>
	<!-- 			</ul> -->
	<%-- 		</c:if> --%>

	<a href="${pageContext.request.contextPath}/news/add" >Add News</a>
	<c:choose>
		<c:when test="${not empty datas}">	<!-- không check .size vì datas = null thì bị lỗi cú pháp , null không thể check size -->
			<ul>
				<c:forEach items="${datas}" var="news">
					<li>
						<img width=100px height=100px src="${pageContext.request.contextPath}/resource/${news.picDes}" />	
						<a href="${pageContext.request.contextPath}/news/detail/${news.id}/${news.author}">
							${news.title}- ${news.author}</a>
					</li>
				</c:forEach>
			</ul>
		</c:when>
		<c:otherwise>
			<div>
				<p>Data empty</p>
			</div>
		</c:otherwise>
	</c:choose>

</body>
</html>