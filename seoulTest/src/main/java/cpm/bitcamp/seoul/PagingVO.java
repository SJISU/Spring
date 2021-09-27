package cpm.bitcamp.seoul;

public class PagingVO {
	private int totalRecord;//총레코드수
	   private int nowPage=1;//현재페이지
	   private int totalPage;//총페이지수
	   private int onePageRecord=5;//한페이지당 레코드 수
	   
	   //페이지
	   private int onePageNumCount=5;
	   private int startPage=1;
		
	   public int getTotalRecord() {
			return totalRecord;
		}
		public void setTotalRecord(int totalRecord) {
			this.totalRecord = totalRecord;
		}
		public int getNowPage() {
			return nowPage;
		}
		public void setNowPage(int nowPage) {
			this.nowPage = nowPage;
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
		public int getOnePageNumCount() {
			return onePageNumCount;
		}
		public void setOnePageNumCount(int onePageNumCount) {
			this.onePageNumCount = onePageNumCount;
		}
		public int getStartPage() {
			return startPage;
		}
		public void setStartPage(int startPage) {
			this.startPage = startPage;
		}
		   
		   
}
