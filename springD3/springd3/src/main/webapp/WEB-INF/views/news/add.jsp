<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>News - Add</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/news/add" method="post" enctype="multipart/form-data">		<!-- Note: multipart -->
		<label>Title:</label> <input type="text" name="title" /> <br /> <br /> 
		<label>Author:</label> <input type="text" name="author" /> <br /> <br /> 
		<label>Detail:</label> <input type="text" name="detail" /> <br /> <br /> 
		<label>Status:</label> <input type="text" name="status" /> <br /> <br /> 
		<label>Upload file:</label> <input type="file" name="pic" multiple/> <br /> <br /> 
		<input type="submit" value="Submit" />
	</form>
</body>
</html>