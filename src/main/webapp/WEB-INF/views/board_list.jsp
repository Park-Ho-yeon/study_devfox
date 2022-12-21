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
</head>
<body>
    <div id="wrap">
        <h2>게시판<span>목록</span></h2>
        <div id="white_box">
            <table>
                <colgroup>
                    <col width="10%">
                    <col width="*">
                    <col width="10%">
                    <col width="15%">
                    <col width="15%">
                </colgroup>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>조회수</th>
                    <th>작성자</th>
                    <th>등록날짜</th>
                </tr>
                 <c:forEach items="${list}" var="dto">
		            <tr>
		                <td>${dto.getB_no()}</td>
		                <td class="td_left"><a href="/board/view?b_no=${dto.getB_no()}">${dto.getTitle()}</a></td>
		                <td>${dto.getHit()}</td>
		                <td>${dto.getReg_id()}</td>
		                <td>${dto.getReg_date()}</td>
		            </tr>
	            </c:forEach>
            </table>
        </div>
        <div class="paging_wrap">
            <a href="" class="on">1</a>
            <a href="">2</a>
            <a href="">3</a>
            <a href="">4</a>
            <a href="">5</a>
        </div>
        <button class="btn btn_write" onclick="location.href='write'">글쓰기</button>
    </div>
</body>
</html>