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
    
    <!-- 메세지가 있으면 alert실행 -->
    <c:if test="${not empty msg}">
	    <script>
	    	alert('${msg}');
	    </script>
    </c:if>
    
    <script type="text/javascript">
    	//유효성 검사
    	function checkForm(){
    		if(write.title.value==''){
    			alert('제목을 입력해주세요.');
    			write.title.focus();
    			return false;
    		}
    		if(write.content.value==''){
    			alert('내용을 입력해주세요.');
    			write.content.focus();
    			return false;
    		}
    		return true;
    	}
    </script>
</head>
<body>
    <div id="wrap">
        <h2>게시판<span>글쓰기</span></h2>
        <form name="write" action="DBinsert" method="post" onsubmit="return checkForm()">
	        <div id="white_box" class="write_form">
	            <div>
	                <p>제목</p>
	                <input type="text" name="title">
         	        <input type="hidden" name="reg_id" value="${sessionId}">
	            </div>
	            <div>
	                <p>내용</p>
	                <textarea name="content"></textarea>
	            </div>
	        </div>
	        <div class="btn_wrap">
	            <button type="submit" class="btn btn_save">등록하기</button>
	            <button type="button" class="btn btn_save" onclick="history.back()">취소</button>
	        </div>
        </form>
    </div>
</body>
</html>