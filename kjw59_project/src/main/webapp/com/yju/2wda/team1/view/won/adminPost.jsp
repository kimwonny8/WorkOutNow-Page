<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="kjw59_project.model.won.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Page</title>
<link rel="stylesheet" href="<%=cssDir%>/cart.css">
<script
  src="http://code.jquery.com/jquery-3.5.1.js"
  integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
  crossorigin="anonymous"></script>
<style>
.bottom {
	background-image: url("<%=imgDir%>/mypage.jpg");
}
</style>
</head>
<body>
<%@ include file="../module/header.jsp"%>
<%
ArrayList<PtDTO> postList;
postList = (ArrayList<PtDTO>) session.getAttribute("postList");
PtDTO pt;
%>
<div class="bottom">
	<p class="menuTitle">게시물 관리</p>
</div>

<div class="cartForm">
<form method="post" action="./adminPost.won">
		<select name="search">
			<option value="전체" selected>전체</option>
			<option value="t_id">아이디</option>
			<option value="pt_title">제목</option>
		</select>
		<input type="text" class="inputBox" name="searchContent">
		<button class="smallBtn">검색</button>
		<%
		if (postList.size() == 0) {
		%>
		<div class="basicForm">

			<div class="basicFormInner">
				<p>게시물이 없습니다.</p>
			</div>
		</div>

		<%
		} else {
		%>
		<table class="tableForm">
		
			<thead>
				<tr>
					<th>게시물번호</th>
					<th>작성자</th>
					<th>제목</th>
					<th>상담 가격</th>
					<th>회당 가격</th>
					<th>내용</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < postList.size(); i++) {
					pt = postList.get(i);
				%>
				<tr>
					<td><%=pt.getPt_code() %></td>
					<td><%=pt.getT_id() %></td>
					<td><%=pt.getPt_title() %></td>
					<td><%=pt.getPt_con_c() %></td>
					<td><%=pt.getPt_one_c() %></td>
					<td><a href="./selectPt.won?pt_code=<%=pt.getPt_code() %>">글 보러가기</a></td>
					<td><a href="./expulsionPost.won?pt_code=<%=pt.getPt_code() %>" onclick="return chk()">삭제</a></td>
				<tr>	
			</tbody>
				<% } }%>
		</table>

<%@ include file="../module/footer.jsp"%>
<script>
function chk() {
	var tmp = confirm("정말 삭제하시겠습니까?");
	return tmp;
}
</script>
</body>
</html>