<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--  <link rel="stylesheet" href="<%= request.getContextPath() %>/css/site.css" />-->
<link rel="stylesheet" href="/css/site.css">

</head>
<body>
	<jsp:include page="/header"/>
	<section>
		<form action="greet">
			<label>Enter Your Name:
				<input type="text" name="unm" />
			</label>
			<button>OK</button>
		</form>
		<p>${msg }</p>
	</section>
</body>
</html>
