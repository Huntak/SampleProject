package com.sample.qna.model;

import java.util.ArrayList;
import java.util.List;

public class PageVO<T> {
	private int page = 1;				// 현재 페이지
	private int block = 1;				// 현재 블럭
	private int perPage = 10;			// 한 page에 보여질 수
	private int perBlock = 5;			// 한 block의 수
	private int boardCount;				// 총 게시물 수
	private int pageCount;				// 총 페이지 수
	private int blockCount;				// 총 block 수
	private int pageStart = 1;			// select 해올 rownum의 값
	private int pageEnd = perPage;		// select 해올 rownum의 값
	private int blockStart = 1;			// 페이징에 보여지는 값
	private int blockEnd = perBlock;	// 페이징에 보여지는 값
	private List<T> list = new ArrayList<T>();	// 게시판 리스트
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
		this.pageStart = page * this.perPage - this.perPage + 1;
		this.pageEnd = page * this.perPage;
		
		setBlock(Math.round(page / this.perBlock) + (page % this.perBlock > 0 ? 1 : 0));
	}
	public int getBlock() {
		return block;
	}
	public void setBlock(int block) {
		this.block = (block <= 0 ? 1 : block);
		this.blockStart = this.block * this.perBlock - this.perBlock + 1;
		this.blockEnd = this.block * this.perBlock;
		
		if(this.blockStart > this.pageCount){
			this.blockStart = this.blockCount * this.perBlock - this.perBlock + 1;
			this.blockEnd = this.blockCount * this.perBlock;
		}
		
		if(this.blockEnd > this.pageCount){
			this.blockEnd = this.pageCount;
		}
		
		if(this.page < this.blockStart){
			this.page = this.blockStart;
		}
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}
	public int getPerBlock() {
		return perBlock;
	}
	public void setPerBlock(int perBlock) {
		this.perBlock = perBlock;
	}
	public int getBoardCount() {
		return boardCount;
	}
	public void setBoardCount(int boardCount) {
		this.boardCount = boardCount;
		this.pageCount = (boardCount / perPage) + (boardCount % perPage > 0 ? 1 : 0);
		this.blockCount = (pageCount / perBlock) + (pageCount % perBlock > 0 ? 1 : 0);
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getBlockCount() {
		return blockCount;
	}
	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}
	public int getPageStart() {
		return pageStart;
	}
	public void setPageStart(int pageStart) {
		this.pageStart = pageStart;
	}
	public int getPageEnd() {
		return pageEnd;
	}
	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}
	public int getBlockStart() {
		return blockStart;
	}
	public void setBlockStart(int blockStart) {
		this.blockStart = blockStart;
	}
	public int getBlockEnd() {
		return blockEnd;
	}
	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "PageVO [page=" + page + ", block=" + block + ", perPage="
				+ perPage + ", perBlock=" + perBlock + ", boardCount="
				+ boardCount + ", pageCount=" + pageCount + ", blockCount="
				+ blockCount + ", pageStart=" + pageStart + ", pageEnd="
				+ pageEnd + ", blockStart=" + blockStart + ", blockEnd="
				+ blockEnd + ", list=" + list + "]";
	}
}
