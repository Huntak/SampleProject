package com.sample.qna.model;

@SuppressWarnings("rawtypes")
public class BoardVO extends PageVO{
	private int id;
	private int level;
	private int refid;
	private String title;
	private String contents;
	private int writer;
	private String writeDate;
	private String modifiedDate;
	private int status;
	
	private String writerName;
	private String startDate;
	private String endDate;
	private String dat_flag;
	private String delList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getRefid() {
		return refid;
	}
	public void setRefid(int refid) {
		this.refid = refid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDat_flag() {
		return dat_flag;
	}
	public void setDat_flag(String dat_flag) {
		this.dat_flag = dat_flag;
	}
	public String getDelList() {
		return delList;
	}
	public void setDelList(String delList) {
		this.delList = delList;
	}
	
	@Override
	public String toString() {
		return "BoardVO [id=" + id + ", level=" + level + ", refid=" + refid
				+ ", title=" + title + ", contents=" + contents + ", writer="
				+ writer + ", writerName=" + writerName + ", writeDate="
				+ writeDate + ", modifiedDate=" + modifiedDate + ", startDate="
				+ startDate + ", endDate=" + endDate + ", status=" + status
				+ ", dat_flag=" + dat_flag + ", delList=" + delList
				+ super.toString();
	}
}
