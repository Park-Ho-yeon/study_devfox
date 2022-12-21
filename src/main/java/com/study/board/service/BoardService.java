package com.study.board.service;

import java.util.List;

import com.study.board.dto.BoardDto;

public interface BoardService {
	
	//게시글 목록
	public List<BoardDto> getList() throws Exception;

	//게시글 상세보기
	public BoardDto getView(String b_no) throws Exception;

	//게시글 등록
	public int boardInsert(BoardDto dto) throws Exception;

	//게시글 수정화면
	public BoardDto getViewModify(String b_no) throws Exception;

	//게시글 수정
	public int boardUpdate(BoardDto dto) throws Exception;
	
	//게시글 삭제
	public int boardDelete(String b_no) throws Exception;

}
