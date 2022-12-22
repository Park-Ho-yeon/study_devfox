package com.study.board;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
		List<BoardDto> list = service.getList();
		request.setAttribute("list", list);
		return "board_list";
	}
	
	//게시글 상세보기
	@RequestMapping("/view")
	public String view(String b_no, HttpServletRequest request) throws Exception  {
		request.setAttribute("dto", service.getView(b_no,"view")); /*조회용인지 수정용인지 구분짓기위해  "view"도 같이 넘겨준다*/
		return "board_view";
	}
	
	//게시글 작성 폼으로
	@RequestMapping("/write")
	public String write() {
		return "board_write";
	}
	
	//게시글 DB등록
	@RequestMapping("/DBinsert")
	public String insert(BoardDto dto, RedirectAttributes redirect) throws Exception {
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

}
