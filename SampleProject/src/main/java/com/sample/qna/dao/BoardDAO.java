package com.sample.qna.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sample.qna.model.BoardVO;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	private String NAME_SPACE = "board.";
	
	public List<BoardVO> doListMst(BoardVO param){
		return sqlSession.selectList(NAME_SPACE + "doListMst", param);
	}
	
	public int doCountMst(BoardVO param){
		return sqlSession.selectOne(NAME_SPACE + "doCountMst", param);
	}
	
	public int doInsertMst(BoardVO param){
		return sqlSession.update(NAME_SPACE + "doInsertMst", param);
	}
	
	public int doUpdateMst(BoardVO param){
		return sqlSession.insert(NAME_SPACE + "doUpdateMst", param);
	}
	
	public int doDeleteMst(BoardVO param){
		return sqlSession.update(NAME_SPACE + "doDeleteMst", param);
	}
}
