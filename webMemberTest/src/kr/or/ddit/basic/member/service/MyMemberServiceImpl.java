package kr.or.ddit.basic.member.service;

import java.util.List;

import kr.or.ddit.basic.member.dao.IMyMemberDao;
import kr.or.ddit.basic.member.dao.MyMemberDaoImpl;
import kr.or.ddit.vo.MyMemberVO;

public class MyMemberServiceImpl implements IMyMemberService {
	private static IMyMemberService service;
	private IMyMemberDao dao;
	
	private MyMemberServiceImpl() {
		dao = MyMemberDaoImpl.getInstance();
	}
	
	public static IMyMemberService getIntance() {
		return service = service == null ? new MyMemberServiceImpl() : service;
	}
	
	@Override
	public List<MyMemberVO> getMemberList() {
		return dao.getMemberList();
	}

	@Override
	public int insertMember(MyMemberVO vo) {
		return dao.insertMember(vo);
	}

	@Override
	public int duplicateCheck(String id) {
		return dao.duplicateCheck(id);
	}
	

}
