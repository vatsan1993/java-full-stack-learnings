<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDateTime"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Simple jsp page</title>
</head>
<body>
	<h2>Simple JSP page</h2>
	<!-- class level declaration -->
	<%! LocalDateTime dt = LocalDateTime.now(); %>
	
	<!-- local level declaration -->
	<% LocalDateTime dt2 = LocalDateTime.now(); %>
	<%=dt %><br>
	<%=dt2 %>
	
	<p>
		<% for(int i = 0; i< 1000; i++){ %>
			<span><%=i %></span>
		<% } %>
	</p>
</body>
</html>