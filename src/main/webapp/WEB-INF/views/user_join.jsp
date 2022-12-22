<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <script  src="http://code.jquery.com/jquery-latest.min.js"></script>
    <title>Spring 게시판 만들기</title>
    <script type="text/javascript">
    
    	//유효성 체크
    	function checkForm(){
    		if(signup.id.value==''){
    			alert('아이디를 입력해주세요.');
    			signup.id.focus();
    			return false;
    		}
    		if(signup.pw.value==''){
    			alert('비밀번호를 입력해주세요.');
    			signup.pw.focus();
    			return false;
    		}
    		if(signup.pw_confirm.value==''){
    			alert('비밀번호를 입력해주세요.');
    			signup.pw_confirm.focus();
    			return false;
    		}
    		
    		//비밀번호와 비밀번호확인이 일치하지않을때 
    		if(signup.pw.value!=signup.pw_confirm.value){
    			alert('비밀번호가 정확하지않습니다.');
    			signup.pw.focus();
    			return false;
    		}
    		
    		//중복체크 하지않았거나 중복된 아이디일때
    		if(signup.idck.value!='ok'){
    			alert('아이디 중복체크를 해주세요.');
    			signup.id.focus();
    			return false;
    		}
    		return true;
    	}
    	
    	//아이디중복체크
    	function checkId(){
    		var id = signup.id.value;

    		if(id==''){
    			alert('아이디를 입력해주세요.');
    			signup.id.focus();
    			return;
    		}
    		
    		$.ajax({
    			type: "POST",
    			url : "checkId",
    			data: id,
    			dataType : "json",
                contentType: "application/json; charset=UTF-8",
    			error : function(){
    				alert('통신실패!!');
    			},
    			success : function(data){
    				if(data == 0){
    					alert('사용가능한 아이디입니다.');
    					signup.idck.value='ok';
    				} else {
    					alert('중복된 아이디입니다.');
    					signup.id.value='';
    					signup.id.focus();
    				}
    			}
    		});
    	}
    	
    </script>
</head>
<body>
    <div id="wrap">
        <h2>회원가입</h2>
        <form name="signup" action="userReg" method="post" onsubmit="return checkForm()">
	        <div id="white_box" class="join_box">
	            <div class="input_wrap">
	                <p>
	                    <span>아이디</span>
	                    <input type="text" name="id" class="id_input">
	                    <button type="button" onclick="checkId()">중복확인</button>
	                    <input type="hidden" name="idck" value="no"> <!-- 중복체크 결과값 -->
	                </p>
	                <p>
	                    <span>비밀번호</span>
	                    <input type="password" name="pw">
	                </p>
	                <p>
	                    <span>비밀번호 확인</span>
	                    <input type="password" name="pw_confirm">
	                </p>
	            </div>
	            <button type="submit">회원가입</button>
	        </div>
        </form>
    </div>
</body>
</html>