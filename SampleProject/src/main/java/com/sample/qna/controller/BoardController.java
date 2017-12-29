package com.sample.qna.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sample.qna.model.BoardVO;
import com.sample.qna.model.conf.SESSION_CONF;
import com.sample.qna.service.BoardService;

@RequestMapping("/board")
@org.springframework.stereotype.Controller
public class BoardController {
	@Autowired
	BoardService service;
	
	/**
	 * @param mv
	 * @param boardVO
	 * @return
	 */
	@RequestMapping(value = "/boardList")
	public ModelAndView boardList(ModelAndView mv, BoardVO boardVO){
		mv.addObject(SESSION_CONF.BOARD_MST_LIST, boardVO);
		mv.addObject(SESSION_CONF.BOARD_MST_PAGING_LIST, service.doListPagingMst(boardVO));
		mv.setViewName("board/boardList");
		
		return mv;
	}
	
	/**
	 * @param mv
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/boardDetail/{id}")
	public ModelAndView boardDetail(ModelAndView mv, @PathVariable int id) {
		BoardVO boardVO = service.doListMst(id);
		
		mv.addObject(SESSION_CONF.BOARD_MST_LIST, boardVO);
		mv.setViewName("board/boardDetail");
		
		return mv;
	}
	
	/**
	 * @param boardVO
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doProcessMst")
	public List<Integer> doProcessMst(ArrayList<Integer> result, BoardVO boardVO, HttpServletRequest request) throws IllegalStateException, IOException {
		int boardResult = service.doProcessMst(boardVO);
		result.add(boardResult);
		
		return result;
	}
}
