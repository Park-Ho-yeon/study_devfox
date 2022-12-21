<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Spring 게시판 만들기</title>
    <script type="text/javascript">
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
        <h2>게시판<span>수정</span></h2>
        <form name="write" action="DBupdate" method="post" onsubmit="return checkForm()">
        <div id="white_box" class="write_form">
            <div>
                <p>제목</p>
                <input type="text" name="title" value="${dto.getTitle()}">
                <input type="hidden" name="b_no" value="${dto.getB_no()}">
            </div>
            <div>
                <p>내용</p>
                <textarea name="content">${dto.getContent()}</textarea>
            </div>
        </div>
        <div class="btn_wrap">
            <button type="submit" class="btn btn_save">수정하기</button>
            <button type="button" class="btn btn_save" onclick="history.back()">취소</button>
        </div>
        </form>
    </div>
</body>
</html>