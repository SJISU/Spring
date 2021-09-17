package com.bitcamp.myapp.board;

public class pagingVO {
	
	private int totalRecord;//총레코드수
	private int nowPage=1;//현재보고있는페이지
	private int totalPage;//총페이지수
	private int onePageRecord=5; //한페이지당레코드수
	
	//검색어
	
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		
		//총페이지수 = 총레코드수 /한페이당레코드수 '올림...'
		totalPage = (int)Math.ceil(totalRecord/(double)onePageRecord); //실수로 나누면 실수가 나옴,올림해서페이지수를구하고 다시 정수화
		
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int noePage) {
		this.nowPage = noePage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getOnePageRecord() {
		return onePageRecord;
	}
	public void setOnePageRecord(int onePageRecord) {
		this.onePageRecord = onePageRecord;
	}
	
	
}
