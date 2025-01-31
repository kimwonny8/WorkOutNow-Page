<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="kjw59_project.model.won.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>payment Progress</title>
<style>
.bottom {
	background-image: url("<%=imgDir%>/cart.jpg");
}

</style>
<link rel="stylesheet" href="<%=cssDir%>/mypage.css">
<link rel="stylesheet" href="<%=cssDir%>/chargeCoin.css">
</head>
<body>
	<%@ include file="../module/header.jsp"%>
	<%
	ArrayList<CartVO> classList;
	classList = (ArrayList<CartVO>) session.getAttribute("classList");
	String m_birth=""; 
	String m_gender=""; 
	String m_phone=""; 
	int mp_tall=0; 
	int mp_weight=0;
	String mp_detail="";
	
	if(classList.size() != 0) {
		CartVO cart = classList.get(0);
		m_name = cart.getM_name();
		m_birth = cart.getM_birth();
		m_gender = cart.getM_gender();
		m_phone = cart.getM_phone();
		mp_tall = cart.getMp_tall();
		mp_weight = cart.getMp_weight();
		mp_detail = cart.getMp_detail();
	} else {
		response.sendRedirect("/kjw59_project/com/yju/2wda/team1/view/etc/error.jsp");
	}
	
	%>
	<div class="bottom">
		<p class="menuTitle">신청내역관리</p>
	</div>
	
	
	<div class="paymentForm">
		<p class="onTheFormP">신청자 추가 정보</p>
		<div class="paymentFormInner">
		<div class="formInputLineH">
				<p>이름</p>
				<input type="text" disabled value="<%=m_name%>"
					class="inputBox">
			</div>
			<div class="formInputLineH">
				<p>생년월일</p>
				<input type="text" disabled value="<%=m_birth%>"
					class="inputBox">
			</div>
			<div class="formInputLineH">
				<p>성별</p>
				<input type="text" disabled value="<%=m_gender%>"
					class="inputBox">
			</div>
			<div class="formInputLineH">
				<p>휴대폰번호</p>
				<input type="text" disabled value="<%=m_phone%>"
					class="inputBox">
			</div>
			
			<div class="formInputLineH">
				<p>키</p>
				<input type="text" disabled value="<%=mp_tall%>"
					class="inputBox">
			</div>
			<div class="formInputLineH">
				<p>체중</p>
				<input type="text" disabled value="<%=mp_weight %>"
					class="inputBox">
			</div>
			<div class="formInputLineH">
				<p>상세정보</p>
				<input type="text" disabled value="<%=mp_detail %>"
					class="inputBox">
			</div>
		</div>
	</div>
	</div>
	<%@ include file="../module/footer.jsp"%>

</body>
</html>