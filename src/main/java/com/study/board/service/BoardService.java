package com.study.board.service;

import java.util.HashMap;
import java.util.List;

import com.study.board.dto.BoardDto;

public interface BoardService {
	
	//로그인
	public int userLogin(HashMap<String, Object> map) throws Exception;
	
	//아이디중복체크
	public String checkid(String id) throws Exception;

	//회원등록
	public int userReg(HashMap<String, Object> map) throws Exception;
	
	//게시글 목록
	public List<BoardDto> getList() throws Exception;

	//게시글 상세보기
	public BoardDto getView(String b_no, String vm) throws Exception;

	//게시글 등록
	public int boardInsert(BoardDto dto) throws Exception;

	//게시글 수정
	public int boardUpdate(BoardDto dto) throws Exception;
	
	//게시글 삭제
	public int boardDelete(String b_no) throws Exception;

	
}
