package com.bitcamp.myapp.board;

public class pagingVO {
	
	private int totalRecord;//�ѷ��ڵ��
	private int nowPage=1;//���纸���ִ�������
	private int totalPage;//����������
	private int onePageRecord=5; //���������緹�ڵ��
	
	//�˻���
	
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		
		//���������� = �ѷ��ڵ�� /�����̴緹�ڵ�� '�ø�...'
		totalPage = (int)Math.ceil(totalRecord/(double)onePageRecord); //�Ǽ��� ������ �Ǽ��� ����,�ø��ؼ��������������ϰ� �ٽ� ����ȭ
		
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
