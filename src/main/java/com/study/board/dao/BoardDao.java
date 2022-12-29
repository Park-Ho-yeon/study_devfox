package com.study.board.dao;

import java.util.HashMap;
import java.util.List;

import com.study.board.dto.BoardDto;

public interface BoardDao {

	//로그인
	public int userLogin(HashMap<String, Object> map) throws Exception;
	
	//아이디중복체크
	public String checkid(String id) throws Exception;

	//회원등록
	public int userReg(HashMap<String, Object> map) throws Exception;
	
	//게시글 목록
	public List<BoardDto> getList(HashMap<String, Object> map) throws Exception;

	//게시글 번호 자동생성
	public String getAutoNo(HashMap<String, Object> map) throws Exception;

	//게시글 등록
	public int boardInsert(BoardDto dto) throws Exception;

	//게시글 상세보기
	public BoardDto getView(String b_no) throws Exception;

	//게시글 조회수 증가
	public void setHitcount(String b_no) throws Exception;

	//게시글 수정
	public int boardUpdate(BoardDto dto) throws Exception;
	
	//게시글 삭제
	public int boardDelete(String b_no) throws Exception;

	//페이징 처리
	public int totalCount() throws Exception;

	//댓글 등록
	public int commentInsert(BoardDto dto) throws Exception;

	//댓글 목록
	public List<BoardDto> getCommentList(String b_no) throws Exception;

	//댓글 삭제
	public int commentDelete(String c_no) throws Exception;

}
