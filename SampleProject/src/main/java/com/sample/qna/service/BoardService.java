package com.sample.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.qna.dao.BoardDAO;
import com.sample.qna.model.BoardVO;
import com.sample.qna.model.PageVO;

@Service
public class BoardService {
	@Autowired
	BoardDAO dao;
	
	public BoardVO doListMst(int id){
		BoardVO param = new BoardVO();
		param.setId(id);
		List<BoardVO> list = dao.doListMst(param);
		
		return list.isEmpty() ? null : list.get(0);
	}
	
	public PageVO<BoardVO> doListPagingMst(BoardVO param){
		PageVO<BoardVO> rtnVO = new PageVO<BoardVO>();
		rtnVO.setBoardCount(dao.doCountMst(param));
		rtnVO.setPage(param.getPage());
		rtnVO.setBlock(param.getBlock());
		param.setPage(rtnVO.getPage());
		List<BoardVO> list = dao.doListMst(param);
		rtnVO.setList(list);
		
		return rtnVO;
	}
	
	public int doProcessMst(BoardVO param){
		int result = 0;
		if(param.getDat_flag().equals("I")){
			result = dao.doInsertMst(param);
		}else if(param.getDat_flag().equals("U")){
			result = dao.doUpdateMst(param);
		}else if(param.getDat_flag().equals("D")){
			String[] delListArr = param.getDelList().substring(2, param.getDelList().length()-2).split("\",\"");
			for(int i = 0; i < delListArr.length; i++){
				param.setId(Integer.parseInt(delListArr[i]));
				result = dao.doDeleteMst(param);
			}
		}
		return result;
	}
}
