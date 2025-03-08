<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/header" />
	<section>
		<table>
			<tr><td>Principal: </td><td>${loan.principal }</td></tr>
			<tr><td>Rate of Interest: </td><td>${loan.interestRate }</td></tr>
			<tr><td>Time Period(yrs): </td><td>${loan.time }</td></tr>
			<tr><td>Simple Interest: </td><td>${loan.simpleInterest }</td></tr>
			<tr><td>Payable Amount: </td><td>${loan.payableAmount }</td></tr>
		</table>
	</section>
</body>
</html>