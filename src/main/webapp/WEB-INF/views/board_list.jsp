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
     	
     //글쓰기 - 로그인 상태 체크
    	function loginCheck(b_no){
    		var id = "${sessionId}";
    		if(id==""){
    			alert('로그인 후 가능합니다.');
    			location.href="login";
    		}else{
    			location.href="write";
    		}
    	}
    </script>
    
</head>
<body>
    <div id="wrap">
    	<div id="header">
    		<!-- 로그인 상태 여부로 달라지는 상단 메뉴 -->
    		<c:if test="${empty sessionId}">
    			<a href="login">로그인</a>&nbsp;&nbsp;|&nbsp;
	    		<a href="signup">회원가입</a>
    		</c:if>
    		<c:if test="${not empty sessionId}">
	    		<span>${sessionId}님&nbsp;&nbsp;|&nbsp;</span>
	    		<a href="logout">로그아웃</a>
    		</c:if>
    	</div>
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
                <c:set var="listNo" value="${t_order}"/>
                <c:forEach items="${list}" var="dto">
		            <tr>
		                <td>${seq}<c:set var="seq" value="${seq-1}"/></td>
		                <td class="td_left">
		                	<a href="view?b_no=${dto.getB_no()}">${dto.getTitle()}</a>
		                	<!-- 댓글 개수 표시 / 없으면 표시안함 -->
		                	<c:if test="${dto.getComment_count() ne '0'}">
			                	<span>[${dto.getComment_count()}]</span>
		                	</c:if>
		                </td>
		                <td>${dto.getHit()}</td>
		                <td>${dto.getReg_id()}</td>
		                <td>${dto.getReg_date()}</td>
		            </tr>
	            </c:forEach>
            </table>
        </div>
        <!-- 페이징 버튼 -->
        <div class="paging_wrap">
        <c:forEach var="i" begin="1" end="${totalPage}">
            <a href="list?page=${i}" <c:if test="${i eq pageNum}">class="on"</c:if>>${i}</a>
        </c:forEach>
        </div>
        <button class="btn btn_write" onclick="loginCheck()">글쓰기</button>
    </div>
</body>
</html>