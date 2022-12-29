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
    	

    	//댓글 등록
    	function checkComment(){
   			var id = "${sessionId}";
    		if(id==""){
    			//로그아웃 상태일때
    			if(confirm('로그인 후 가능합니다. 로그인하시겠습니까?')){				
	    			location.href="login";
    			}
    			return false;
    			
    		}else{
    			//로그인 상태일때
    			if(form.comments.value==''){
    				alert('댓글을 입력해주세요.');
    				form.comments.focus();
    				return false;
    			}
    			
    			return true;
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
        <!-- 댓글 부분  -->
        <div class="comment_wrap view_form">
            <div class="title">
                <h3>Comments</h3>
            </div>
            <form name="form" action="insertComment?b_no=${dto.getB_no()}" method="post" onsubmit="return checkComment()">
            <div class="input_box">
                <input type="text" name="comments" <c:if test="${empty sessionId}">placeholder="로그인 후 작성 가능합니다." disabled</c:if>>
                <button type="submit">댓글 등록</button>
            </div>
            </form>
            <div>
                <ul>
                <c:forEach var="dto" items="${c_list}">
                    <li>
                        <p>${dto.getComments()}</p>
                        <p>${dto.getReg_id()}<span>|</span>${dto.getReg_time()}</p>
                    </li>
                </c:forEach>
                <c:if test="${empty c_list}">
                	<li class="noComments">
                		작성된 댓글이 없습니다.
                	</li>
                </c:if>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>