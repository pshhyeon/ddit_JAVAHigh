package kr.or.ddit.basic.member.dao;

import java.util.List;

import kr.or.ddit.vo.MyMemberVO;

public interface IMyMemberDao {
	public List<MyMemberVO> getMemberList();

	public int insertMember(MyMemberVO vo);

	public int duplicateCheck(String id);
}
