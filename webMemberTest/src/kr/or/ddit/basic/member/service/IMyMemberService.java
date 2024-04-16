package kr.or.ddit.basic.member.service;

import java.util.List;

import kr.or.ddit.vo.MyMemberVO;

public interface IMyMemberService {
	public List<MyMemberVO> getMemberList();

	public int insertMember(MyMemberVO vo);

	public int duplicateCheck(String id);
}