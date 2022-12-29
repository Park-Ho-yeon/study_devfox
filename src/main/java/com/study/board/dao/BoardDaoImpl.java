package com.study.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.board.dto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSession sql;
	
	//로그인
	@Override
	public int userLogin(HashMap<String, Object> map) throws Exception {
		return sql.selectOne("login",map);
	}
	
	//아이디 중복 체크
	@Override
	public String checkid(String id) throws Exception {
		return sql.selectOne("checkid",id);
	}

	//회원등록
	@Override
	public int userReg(HashMap<String, Object> map) throws Exception {
		return sql.insert("regist",map);
	}
	
	//게시글 목록
	@Override
	public List<BoardDto> getList(HashMap<String, Object> map) throws Exception {
		return sql.selectList("getList",map);
	}

	//게시글 상세조회
	@Override
	public BoardDto getView(String b_no) throws Exception {
		return sql.selectOne("getView",b_no);
	}
	
	//게시글 조회수 증가
	@Override
	public void setHitcount(String b_no) throws Exception {
		sql.update("hitcount",b_no);
		
	}

	//게시글 자동번호 생성
	@Override
	public String getAutoNo(HashMap<String, Object> map) throws Exception {
		return sql.selectOne("getAutoNo",map);
	}

	//게시글 등록
	@Override
	public int boardInsert(BoardDto dto) throws Exception {
		return sql.insert("boardInsert",dto);
	}

	//게시글 수정
	@Override
	public int boardUpdate(BoardDto dto) throws Exception {
		return sql.insert("boardUpdate",dto);
	}

	//게시글 삭제
	@Override
	public int boardDelete(String b_no) throws Exception {
		return sql.delete("boardDelete",b_no);
	}

	//페이징 처리
	@Override
	public int totalCount() throws Exception {
		return sql.selectOne("totalcount");
	}

	//댓글 등록
	@Override
	public int commentInsert(BoardDto dto) throws Exception {
		return sql.insert("commentInsert",dto);
	}

	//댓글 목록
	@Override
	public List<BoardDto> getCommentList(String b_no) {
		return sql.selectList("commentList",b_no);
	}

	//댓글 삭제
	@Override
	public int commentDelete(String c_no) throws Exception {
		return sql.delete("commentDelete",c_no);
	}

}
