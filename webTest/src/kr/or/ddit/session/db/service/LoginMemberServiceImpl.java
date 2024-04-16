package kr.or.ddit.session.db.service;

import kr.or.ddit.session.db.dao.ILoginMemeberDao;
import kr.or.ddit.session.db.dao.LoginMemberDaoImpl;
import kr.or.ddit.vo.MemberVO;

public class LoginMemberServiceImpl implements ILoginMemberService {
	private ILoginMemeberDao dao;
	private static LoginMemberServiceImpl service;

	private LoginMemberServiceImpl() { 
		dao = LoginMemberDaoImpl.getInstance();
	}

	public static LoginMemberServiceImpl getInstance() {
		return service = service == null ? new LoginMemberServiceImpl() : service;
	}
	@Override
	public MemberVO getLoginMember(MemberVO memVO) {
		return dao.getLoginMember(memVO);
	}

}
