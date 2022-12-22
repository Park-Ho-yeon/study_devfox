package com.study.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.board.dao.BoardDao;
import com.study.board.dto.BoardDto;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;
	
	//로그인
	@Override
	public int userLogin(HashMap<String, Object> map) throws Exception {
		return dao.userLogin(map);
	}
	
	//아이디 중복 체크
	@Override
	public String checkid(String id) throws Exception {
		return dao.checkid(id);
	}

	//회원등록
	@Override
	public int userReg(HashMap<String, Object> map) throws Exception {
		return dao.userReg(map);
	}
	
	//게시판 목록
	@Override
	public List<BoardDto> getList() throws Exception {
		return dao.getList();
	}

	//게시글 상세보기
	@Override
	public BoardDto getView(String b_no, String vm) throws Exception {

		if(vm.equals("view")) dao.setHitcount(b_no); //상세조회용이면 조회수 1 증가
		
		BoardDto dto = dao.getView(b_no);
				
		if(vm.equals("view")) dto.setContent(dto.getContent().replace("\r\n", "<br>"));			
		//상세조회용이면 <p>태그안에 들어가기에 줄바꿈 코드 <br>로 변환
		
		return dto;
	}

	//게시글 등록
	@Override
	public int boardInsert(BoardDto dto) throws Exception {
		String autoNo = dao.getAutoNo(); //자동생성된 번호
		dto.setB_no(autoNo); 
		return dao.boardInsert(dto);
	}

	//게시글 수정
	@Override
	public int boardUpdate(BoardDto dto) throws Exception {
		return dao.boardUpdate(dto);
	}

	//게시글 삭제
	@Override
	public int boardDelete(String b_no) throws Exception {
		return dao.boardDelete(b_no);
	}

}
