package kr.or.ddit.session.db.dao;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class LoginMemberDaoImpl implements ILoginMemeberDao {

	private static LoginMemberDaoImpl dao;

	private LoginMemberDaoImpl() { }

	public static LoginMemberDaoImpl getInstance() {
		return dao = dao == null ? new LoginMemberDaoImpl() : dao;
	}

	@Override
	public MemberVO getLoginMember(MemberVO memVO) {
		MemberVO loginMemberVO = null; // 반환값이 저장될 변수
		SqlSession session = null;

		try {
			session = MybatisUtil.getSqlSession();
			loginMemberVO = session.selectOne("member.getLoginMember", memVO);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) { session.close(); }
		}
		
		return loginMemberVO;
	}

}
