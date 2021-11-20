<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- fmt:format -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News-List</title>
</head>
<body>
	<h1>Details.jsp in file news</h1>
	<h2>${news.title}</h2>
	<p>
		<span>Create by: <fmt:formatDate value="${news.createdBy}"
				pattern="yyyy-MM-dd HH:mm" /></span>
	</p>
	<div>${news.detail}</div>
	<a href="javascript:window.history.back()">Back List</a>
</body>
</html>