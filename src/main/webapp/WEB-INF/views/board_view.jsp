<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Spring 게시판 만들기</title>
    <script type="text/javascript">
    	//삭제 확인
    	function deleteCheck(b_no){
    		if(confirm('삭제하시겠습니까?')){
    			location.href="DBdelete?b_no="+b_no;
    		}
    	}
    </script>
 
    <!-- 메세지가 있으면 alert실행 -->
    <c:if test="${not empty msg}">
	    <script>
	    	alert('${msg}');
	    </script>
    </c:if>
    
</head>
<body>
    <div id="wrap">
    	<div id="header">
    		<c:if test="${empty sessionId}">
    			<a href="login">로그인</a>&nbsp;&nbsp;|&nbsp;
	    		<a href="signup">회원가입</a>
    		</c:if>
    		<c:if test="${not empty sessionId}">
	    		<span>${sessionId}님&nbsp;&nbsp;|&nbsp;</span>
	    		<a href="logout">로그아웃</a>
    		</c:if>
    	</div>
        <h2>게시판</h2>
        <div id="white_box" class="view_form">
            <div class="title">
                <h3>${dto.getTitle()}</h3>
            </div>
            <div class="info">
                <p>${dto.getReg_id()} <span>|</span> ${dto.getReg_date()} <span>|</span> 조회수 ${dto.getHit()}</p>
            </div>
            <div class="content">
                <p>${dto.getContent()}</p>
            </div>
        </div>
        <div class="btn_wrap">
        <!-- 현재 로그인된 ID와 작성자 ID가 같으면 수정,삭제 버튼 표시-->
        <c:if test="${sessionId eq dto.getReg_id()}">
            <button class="btn btn_save" onclick="location.href='modify?b_no=${dto.getB_no()}'">수정</button>
            <button class="btn btn_save" onclick="deleteCheck('${dto.getB_no()}')">삭제</button>
        </c:if>
            <button class="btn btn_save" onclick="location.href='list'">목록</button>
        </div>
    </div>
</body>
</html>