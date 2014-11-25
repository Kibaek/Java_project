<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Scriptlet Element</h1>
<p>출력문 중간에 자바 코드를 넣고 싶으면 다음과 같이
스크립트릿 엘리먼트를 작성한다.</p>

<% 
int i = 20; 
int j = 30;
%>

<p>스크립트릿 안의 내용은 _jspService() 메서드 안에 순서대로
복사된다.</p>

<%
out.println("<p>i = " + i + "</p>");
out.println("<p>j = " + j + "</p>");
%>

<p>위와 같이 스크립트릿 안에서도 out 객체를 사용하여 
출력할 수 있다.</p>
</body>
</html>
















