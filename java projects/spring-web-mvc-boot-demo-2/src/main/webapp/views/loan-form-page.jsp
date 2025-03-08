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
	<jsp:include page="/header" />
	<section>
		<form  method="POST">
			<div>
				<label>Principal:
					<input type="decimal" name="principal" />
				</label>
			</div>
			<div>
				<label>Rate of Interest:
					<input type="decimal" name="interestRate" />
				</label>
			</div>
			<div>
				<label>time Period:
					<input type="decimal" name="time" />
				</label>
			</div>
			<button>OK</button>
		
		</form>
		<p>${msg }</p>
	</section>
</body>
</html>
