package com.study.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.board.dto.BoardDto;
import com.study.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	//로그인 화면
	@RequestMapping("login")
	public String login() {
		return "user_login";
	}
	
	//로그인
	@RequestMapping("/loginCheck")
	public String logincheck(String id, String pw, HttpServletRequest request, RedirectAttributes redirect) throws Exception {
		//두 가지 이상의 값을 map에 담을 수 있다. key, value로 사용
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id",id);
		map.put("pw",pw);
		
		int result = service.userLogin(map);
		
		if(result!=1) {
			redirect.addFlashAttribute("msg", "아이디 또는 비밀번호가 정확하지않습니다.");
			return "redirect:login";			
		}else {
			//세션 생성
			HttpSession session = request.getSession();
			//id 변수 선언
			session.setAttribute("sessionId", id);
			//세션 유지 시간(작업을 안하고있을 때)
			session.setMaxInactiveInterval(60*10);
			
			return "redirect:list";			
		}
	}
	
	//회원가입 화면
	@RequestMapping("/signup")
	public String signup() {
		return "user_join";
	}
	
	//아이디중복체크
	@ResponseBody /*json객체로 데이터를 받아온다*/
	@RequestMapping("/checkId")
	public String checkid(@RequestBody String id) throws Exception {
		String result = service.checkid(id);
		return result;
	}
	
	//DB에 회원등록
	@RequestMapping("/userReg")
	public String userreg(String id, String pw, RedirectAttributes redirect) throws Exception{
		
		/*Map에 담아 service로 보냄. Map(key,value)*/
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("id",id);
		map.put("pw",pw);
		
		int result = service.userReg(map);
		
		if(result!=0) {
			redirect.addFlashAttribute("msg","회원가입되었습니다.");
			return "redirect:login";			
		}else {
			return "redirect:signup";
		}
	}
	
	//로그아웃
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, RedirectAttributes redirect) {
		
		HttpSession session = request.getSession();
		
		//세선정보 지우기
		session.invalidate();
		
		redirect.addFlashAttribute("msg", "로그아웃되었습니다");
		return "redirect:list";	
	}
	
	//게시판 목록
	@RequestMapping("/list")
	public String list(HttpServletRequest request) throws Exception {
		//페이징 처리
		int listCount = 5; //한 페이지당 보여줄 글 갯수
		int totalCount = 0; //총 게시글 수
		int totalPage = 1; //페이지 수 초기값
		int pageNum = 1; //현재 페이지 초기값
		
		String pageNumS = request.getParameter("page"); //페이지 번호 클릭했을때
		if(pageNumS!=null) pageNum = Integer.parseInt(pageNumS);
		
		totalCount = service.totalCount();
		totalPage = totalCount/listCount;
		if(totalCount % listCount != 0) {
			totalPage++; //나머지 페이지가 있을 경우 페이지 수 +1
		} 
		
		int start = (pageNum -1) * listCount + 1; //시작번호
		int end   = pageNum * listCount; //끝번호
		
		int seq = totalCount-(start-1); //게시판 표시용 번호
		
		List<BoardDto> list = service.getList(start,end);
		
		request.setAttribute("list", list);
		request.setAttribute("totalPage", totalPage); //총 페이지 수
		request.setAttribute("pageNum", pageNum); //현재 페이지
		request.setAttribute("seq", seq);
		
		return "board_list";
	}
	
	//게시글 상세보기
	@RequestMapping("/view")
	public String view(String b_no, HttpServletRequest request, HttpServletResponse response) throws Exception  {
		/* -- 조회수 중복방지 --*/
		Cookie oldCookie = null; //oldCookie 객체 선언 후 null로 초기화
		Cookie[] cookies = request.getCookies(); // Cookie타입을 요소로 가지는 리스트
		
		//기존 생성된 쿠키가 있고 이름이 boardView인지 확인
		if(cookies!=null) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("boardView")) {
					oldCookie = cookie;
				}
			}
		}

		if(oldCookie!=null) {
			//기존 생성된 쿠키가 있을때
			//기존 쿠키에 현재 게시글 번호가 없다면
			if(!oldCookie.getValue().contains(b_no)) { 
				service.setHitcount(b_no); //조회수 +1
				oldCookie.setValue(oldCookie.getValue()+"_"+b_no); //기존 쿠키에 현재 게시글 번호 추가
				oldCookie.setPath("/"); //모든URL범위에서 쿠키 전송가능
				oldCookie.setMaxAge(60*60*24); //쿠키 유지시간 60s*60m*24 = 24h
				response.addCookie(oldCookie); //쿠키 전송
			}
		}else{
			//기존 생성된 쿠키가 없을때 새로운 쿠키 생성
			service.setHitcount(b_no);
			Cookie newCookie = new Cookie("boardView",b_no);
			newCookie.setPath("/");
			newCookie.setMaxAge(60*60*24);
			response.addCookie(newCookie);
		}
		
		request.setAttribute("dto", service.getView(b_no,"view")); //조회용인지 수정용인지 구분짓기위해  "view"도 같이 넘겨준다
		request.setAttribute("c_list", service.getCommentList(b_no)); //댓글 목록
		
		return "board_view";
	}
	
	//게시글 작성 폼으로
	@RequestMapping("/write")
	public String write() {
		return "board_write";
	}
	
	//게시글 DB등록
	@RequestMapping("/DBinsert")
	public String insert(BoardDto dto, HttpServletRequest request, RedirectAttributes redirect) throws Exception {
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("sessionId");
		dto.setReg_id(sessionId);//작성자 아이디 dto에 set
		
		int result = service.boardInsert(dto);
		if(result!=0) {
			/*등록 성공하면 목록으로*/
			redirect.addFlashAttribute("msg","등록되었습니다.");
			return "redirect:list";
		}else { 
			/*등록 실패하면 다시 작성폼으로*/
			redirect.addFlashAttribute("msg","등록에 오류가 발생하였습니다.");
			return "redirect:write";
		}
	}
	
	//게시글 수정 폼으로
	@RequestMapping("/modify")
	public String modify(String b_no, HttpServletRequest request) throws Exception {
		request.setAttribute("dto", service.getView(b_no,"modify"));
		return "board_modify";
	}
	
	//게시글 DB수정
	@RequestMapping("/DBupdate")
	public String update(BoardDto dto, RedirectAttributes redirect) throws Exception {
		int result = service.boardUpdate(dto);
		if(result!=0) {
			/*수정되면 해당 글 상세보기화면으로 redirect*/
			redirect.addFlashAttribute("msg","수정되었습니다.");
			return "redirect:view?b_no="+dto.getB_no();
		}else{
			/*수정 실패하면 목록으로 redirect*/
			redirect.addFlashAttribute("msg","수정에 오류가 발생하였습니다.");			
			return "redirect:list";
		}
	}
	
	//게시글 삭제
	@RequestMapping("/DBdelete")
	public String delete(String b_no, RedirectAttributes redirect) throws Exception {
		int result = service.boardDelete(b_no);
		if(result!=0){
			/*삭제되면 목록으로*/
			redirect.addFlashAttribute("msg","삭제되었습니다.");
			return "redirect:list";
		}else{
			/*삭제 실패하면 다시 상세보기화면으로*/
			redirect.addFlashAttribute("msg","삭제에 오류가 발생하였습니다.");			
			return "redirect:view?b_no="+b_no;			
		}
	}

	//댓글 DB등록
	@RequestMapping("insertComment")
	public String insertComment(BoardDto dto, HttpServletRequest request, RedirectAttributes redirect) throws Exception{
		HttpSession session = request.getSession();
		String sessionId = (String)session.getAttribute("sessionId");
		dto.setReg_id(sessionId);//작성자 아이디 dto에 set
		
		int result = service.commentInsert(dto);
		if(result!=0) {
			redirect.addFlashAttribute("msg","댓글이 등록되었습니다.");
		}else{
			redirect.addFlashAttribute("msg","댓글 등록에 오류가 발생하였습니다.");			
		}
		return "redirect:view?b_no="+dto.getB_no();
	}
	
	//댓글 삭제
	@RequestMapping("deleteComment")
	public String deleteComment(String c_no, String b_no) throws Exception{
		int result = service.commentDelete(c_no);
		if(result!=1) System.out.println("댓글 삭제 오류");
		
		return "redirect:view?b_no="+b_no;
	}
	
}
