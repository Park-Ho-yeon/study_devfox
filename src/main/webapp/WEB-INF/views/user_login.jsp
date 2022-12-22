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
    <c:if test="${not empty msg}">
	    <script>
	    	alert('${msg}');
	    </script>
    </c:if>
    <script type="text/javascript">
    	//유효성 체크
    	function checkForm(){
    		if(login.id.value==''){
    			alert('아이디를 입력해주세요.');
    			login.id.focus();
    			return false;
    		}
    		if(login.pw.value==''){
    			alert('비밀번호를 입력해주세요.');
    			login.pw.focus();
    			return false;
    		}
    		return true;
    	}
    </script>
</head>
<body>
    <div id="wrap">
        <h2>로그인</h2>
        <form name="login" action="loginCheck" method="post" onsubmit="return checkForm()">
	        <div id="white_box" class="login_box">
	            <div class="input_wrap">
	                <p>
	                    <span>아이디</span>
	                    <input type="text" name="id">
	                </p>
	                <p>
	                    <span>비밀번호</span>
	                    <input type="password" name="pw">
	                </p>
	            </div>
	            <button type="submit">로그인</button>
	        </div>
        </form>
    </div>
</body>
</html>