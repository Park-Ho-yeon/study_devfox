package com.study.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.study.board.dto.BoardDto;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Autowired
	private SqlSession sql;
	
	private static String namespace = "com.study.board.BoardMapper";
	
	@Override
	public List<BoardDto> getList() throws Exception {
		return sql.selectList(namespace+".getList");
	}

	@Override
	public BoardDto getView(String b_no) throws Exception {
		return sql.selectOne(namespace+".getView",b_no);
	}

	@Override
	public String getAutoNo() throws Exception {
		return sql.selectOne(namespace+".getAutoNo");
	}

	@Override
	public int boardInsert(BoardDto dto) throws Exception {
		return sql.insert(namespace+".boardInsert",dto);
	}

	@Override
	public void setHitcount(String b_no) throws Exception {
		sql.update(namespace + ".hitcount",b_no);
		
	}

	@Override
	public int boardUpdate(BoardDto dto) throws Exception {
		return sql.insert(namespace+".boardUpdate",dto);
	}

	@Override
	public int boardDelete(String b_no) throws Exception {
		return sql.delete(namespace+".boardDelete",b_no);
	}

}
