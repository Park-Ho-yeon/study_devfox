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
    	function deleteCheck(b_no){
    		if(confirm('삭제하시겠습니까?')){
    			location.href="DBdelete?b_no="+b_no;
    		}
    	}
    </script>
</head>
<body>
    <div id="wrap">
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
            <button class="btn btn_save" onclick="location.href='modify?b_no=${dto.getB_no()}'">수정</button>
            <button class="btn btn_save" onclick="deleteCheck('${dto.getB_no()}')">삭제</button>
            <button class="btn btn_save" onclick="location.href='list'">목록</button>
        </div>
    </div>
</body>
</html>