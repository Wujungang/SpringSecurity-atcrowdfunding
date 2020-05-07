<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="/WEB-INF/jsp/error/error.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		//int i = 1/0 ;
	%>

	<!-- http://localhost:8080/atcrowdfunding-main/index -->
	<jsp:forward page="/index"></jsp:forward>
	
	
	<!-- http://localhost:8080/atcrowdfunding-main/welcome.jsp
		 http://localhost:8080/atcrowdfunding-main/index
		 
		 http://localhost:8080/atcrowdfunding-main/xxx/welcome.jsp
		  http://localhost:8080/atcrowdfunding-main/xxx/index  相对于当前welcome.jsp的请求路径进行资源查找。
	 -->
	<%-- <jsp:forward page="index"></jsp:forward> --%>
	
	
	<%--
		http://localhost:8080/atcrowdfunding-main/welcome.jsp
		
		http://localhost:8080/atcrowdfunding-main/atcrowdfunding-main/index
	 --%>
	<%-- <jsp:forward page="${pageContext.request.contextPath}/index"></jsp:forward> --%><!-- 重复上下文路径 -->
	
	
	<%--
		绝对路径：固定不变的路径。
			c:\temp\1.jsp
			http://19.168.137.3:8080/xxx/yyy.jsp
			<jsp:forward page="/index"></jsp:forward>
		
		
		相对路径：与当前请求所访问的路径相关，是可变的路径。
				./1.jsp
				../1.jsp
				../../1.jsp
				
				<a href="./yyy.jsp">xxx</a>
				<a href="../yyy.jsp">xxx</a>
			
		
		前台路径：浏览器端发起的请求路径。
				
				
				不以斜杠开头表示相对路径。
					<link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
				
				以斜杠开头，表示从服务器的根(ROOT)进行资源查找。
					<link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
					
					
					
				以上下文路径开头，表示从当前应用程序的根（atcrowdfunding-main）进行资源查找。				
					<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
				
		
		后台路径：服务器端发起资源请求路径。
			<jsp:forward page="/index"></jsp:forward>
	
	
	 --%>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>