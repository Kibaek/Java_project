<%@ page import="java63.servlets.test05.domain.Product"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%-- JSP 주석
=> 클라이언트로 출력되지 않는다.

include를 JSP 액션 태그(기본으로 제공하는 태그)를 사용해서 처리하기
 --%>
<jsp:include page="/common/header"/>  
<% //위의 태그는 다음과 같은 자바 코드를 생성한다.
//RequestDispatcher rd = request.getRequestDispatcher("/common/header");
//rd.include(request, response);
%>
</head>
<body>
<div class='container'>
<h1>제품 목록(by JSP)</h1>
<p><a href='product-form.html' class='btn btn-primary'>새제품</a></p>
<table class='table table-hover'>
<tr>
  <th>#</th><th>제품</th><th>수량</th><th>제조사</th>
</tr>

<%
List<Product> products = 
  (List<Product>)request.getAttribute("products");
for (Product product : products) {
%>
<tr>
  <td><%=product.getNo()%></td>
  <td><a href='view?no=<%=product.getNo()%>'><%=product.getName()%></a></td>
  <td><%=product.getQuantity()%></td>
  <td><%=product.getMakerNo()%></td>
</tr>
<%} %>

</table>
</div>
<script src='../../js/jquery-1.11.1.js'></script>
<jsp:include page="/common/footer"/> 
</body>
</html>
















