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
	public List<BoardDto> getList(int start, int end) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		
		return dao.getList(map);
	}

	//게시글 상세보기
	@Override
	public BoardDto getView(String b_no, String vm) throws Exception {
		BoardDto dto = dao.getView(b_no);

		//상세조회용이면 <p>태그안에 들어가기에 줄바꿈 코드 <br>로 변환
		if(vm.equals("view")) dto.setContent(dto.getContent().replace("\r\n", "<br>"));			
		
		return dto;
	}

	//게시글 조회수 증가
	@Override
	public void setHitcount(String b_no) throws Exception {
		dao.setHitcount(b_no);
	}
	
	//게시글 등록
	@Override
	public int boardInsert(BoardDto dto) throws Exception {
		//22.12.29 댓글 번호생성과 같이쓰기위해 파라미터 수정
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("i","B"); //B001
		map.put("tbl","study_tbl_board"); //테이블 명
		map.put("col","b_no"); //컬럼명
		String autoNo = dao.getAutoNo(map); //자동생성된 번호
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

	//페이징 처리
	@Override
	public int totalCount() throws Exception {
		return dao.totalCount();
	}

	//댓글 등록
	@Override
	public int commentInsert(BoardDto dto) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("i","C"); 
		map.put("tbl","study_tbl_comment");
		map.put("col","c_no");
		String autoNo = dao.getAutoNo(map); //자동생성된 번호
		
		dto.setC_no(autoNo); 
		return dao.commentInsert(dto);
	}
	
	//댓글 목록
	@Override
	public List<BoardDto> getCommentList(String b_no) throws Exception {
		List<BoardDto> c_list = dao.getCommentList(b_no);
		return c_list;
	}

	//댓글 삭제
	@Override
	public int commentDelete(String c_no) throws Exception {
		return dao.commentDelete(c_no);
	}

	

}
