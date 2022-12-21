package com.study.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.board.dto.BoardDto;
import com.study.board.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService service;
	
	//게시글 목록
	@RequestMapping("/list")
	public String list(HttpServletRequest request) throws Exception {
		List<BoardDto> list = service.getList();
		request.setAttribute("list", list);
		return "board_list";
	}
	
	//게시글 상세보기
	@RequestMapping("/view")
	public String view(String b_no, HttpServletRequest request) throws Exception  {
		request.setAttribute("dto", service.getView(b_no));
		return "board_view";
	}
	
	//게시글 작성 폼으로
	@RequestMapping("/write")
	public String write() {
		return "board_write";
	}
	
	//게시글 DB등록
	@RequestMapping("/DBinsert")
	public String insert(BoardDto dto) throws Exception {
		int result = service.boardInsert(dto);
		if(result>0) return "redirect:list";
		else return "redirect:write";
	}
	
	//게시글 수정 폼으로
	@RequestMapping("/modify")
	public String modify(String b_no, HttpServletRequest request) throws Exception {
		request.setAttribute("dto", service.getViewModify(b_no));
		return "board_modify";
	}
	
	//게시글 DB수정
	@RequestMapping("/DBupdate")
	public String update(BoardDto dto) throws Exception {
		int result = service.boardUpdate(dto);
		if(result>0) return "redirect:list";
		else return "redirect:view?b_no="+dto.getB_no();
	}
	
	//게시글 삭제
	@RequestMapping("/DBdelete")
	public String delete(String b_no) throws Exception {
		int result = service.boardDelete(b_no);
		if(result>0) return "redirect:list";
		else return "redirect:view?b_no="+b_no;
	}
}
