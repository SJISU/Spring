package com.bitcamp.myapp.register;

import java.util.List;

public interface RegisterDAOImpl {
	
	//ȸ������
	public int insertRecord(RegisterVO vo);
	
	//ȸ������ ������ ���� ���� �����ϴ� �޼ҵ�
	//public RegisterVO selectRecord(String userid); //1�������������ϴ°Ŵϱ� vo�� ������ �ְ�
	public void selectRecord(RegisterVO vo); 
	
	//ȸ������ ����
	public int updateRecord(RegisterVO vo);
	
	//�α���
	public void loginSelect(RegisterVO vo);
	
	//���̵��ߺ��˻�
	public int idDoubleCheck(String userid);

	//���θ��� �̿��� �����ȣ,�ּ� �˻� �޼ҵ�
	public List<ZipCodeVO> zipSearchRecord(String doro); 
}
