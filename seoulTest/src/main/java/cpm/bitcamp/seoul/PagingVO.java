package cpm.bitcamp.seoul;

public class PagingVO {
	private int totalRecord;//�ѷ��ڵ��
	   private int nowPage=1;//����������
	   private int totalPage;//����������
	   private int onePageRecord=5;//���������� ���ڵ� ��
	   
	   //������
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
