package com.study.board.dao;

import java.util.List;

import com.study.board.dto.BoardDto;

public interface BoardDao {

	//게시글 목록
	public List<BoardDto> getList() throws Exception;

	//게시글 상세보기
	public BoardDto getView(String b_no) throws Exception;

	//게시글 번호 자동생성
	public String getAutoNo() throws Exception;

	//게시글 등록
	public int boardInsert(BoardDto dto) throws Exception;

	//게시글 조회수 증가
	public void setHitcount(String b_no) throws Exception;

	//게시글 수정
	public int boardUpdate(BoardDto dto) throws Exception;
	
	//게시글 삭제
	public int boardDelete(String b_no) throws Exception;

}
