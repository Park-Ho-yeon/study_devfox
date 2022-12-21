package com.study.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.board.dao.BoardDao;
import com.study.board.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;
	
	//게시판 목록
	@Override
	public List<BoardDto> getList() throws Exception {
		return dao.getList();
	}

	//게시글 상세보기
	@Override
	public BoardDto getView(String b_no) throws Exception {
		dao.setHitcount(b_no);
		BoardDto dto = dao.getView(b_no);
		dto.setContent(dto.getContent().replace("\r\n", "<br>"));
		return dto;
	}

	//게시글 등록
	@Override
	public int boardInsert(BoardDto dto) throws Exception {
		String autoNo = dao.getAutoNo(); //자동생성된 번호
		dto.setB_no(autoNo); 
		return dao.boardInsert(dto);
	}

	@Override
	public BoardDto getViewModify(String b_no) throws Exception {
		return dao.getView(b_no);
	}

	@Override
	public int boardUpdate(BoardDto dto) throws Exception {
		return dao.boardUpdate(dto);
	}

	@Override
	public int boardDelete(String b_no) throws Exception {
		return dao.boardDelete(b_no);
	}

}
