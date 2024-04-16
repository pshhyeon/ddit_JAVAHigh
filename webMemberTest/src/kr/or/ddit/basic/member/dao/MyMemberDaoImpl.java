package kr.or.ddit.basic.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MyMemberVO;

public class MyMemberDaoImpl implements IMyMemberDao{

	private static IMyMemberDao dao;
	
	private MyMemberDaoImpl() { }
	
	public static IMyMemberDao getInstance() {
		return dao = dao == null? new MyMemberDaoImpl() : dao;
	}
	
	@Override
	public List<MyMemberVO> getMemberList() {
		SqlSession session = null;
		List<MyMemberVO> list = null;
		try {
			session = MybatisUtil.getSqlSession();
			list = session.selectList("mymember.getMemberList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return list;
	}

	@Override
	public int insertMember(MyMemberVO vo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.insert("mymember.insertMember", vo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cnt > 0) { session.commit(); }
			if (session != null) { session.close(); }
		}
		return cnt;
	}

	@Override
	public int duplicateCheck(String id) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.selectOne("mymember.duplicateCheck", id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return cnt;
	}

}