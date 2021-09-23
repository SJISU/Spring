package com.bitcamp.myapp.board;

public class pagingVO {
	
	private int totalRecord;//총레코드수
	private int nowPage=1;//현재보고있는페이지
	private int totalPage;//총페이지수
	private int onePageRecord=5; //한페이지당레코드수
	
	//검색어
	private String searchKey;
	private String searchWord;
	
	//페이지
	private int startPage=1;
	//한번에 출력할 페이짓
	private int onePageNumCount =5;
	
	
	
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getOnePageNumCount() {
		return onePageNumCount;
	}
	public void setOnePageNumCount(int onePageNumCount) {
		this.onePageNumCount = onePageNumCount;
	}
	
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
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
		
		if(nowPage!=0) {
			//			현재페이지      한번에 표시할 페이지수
			startPage = (nowPage-1)/onePageNumCount*onePageNumCount+1; //시작페이지....
		}
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
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
	
}
