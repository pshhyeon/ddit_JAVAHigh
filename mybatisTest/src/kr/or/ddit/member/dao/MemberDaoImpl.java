package kr.or.ddit.member.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	private static MemberDaoImpl instance = null;
	
	private MemberDaoImpl () { } 
	
	public static MemberDaoImpl getInstance() {
		return instance = instance == null ? new MemberDaoImpl() : instance;
	}
	
	@Override
	public int insertMember(MemberVO memVo) {
		// mybatis-config.xml 환경설정할 객체 생성
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.insert("member.insertMember", memVo);
			if (cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.delete("member.deleteMember", memId);
			if (cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("member.updateMember", memVo);
			if (cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		SqlSession session = null;
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
			session = MybatisUtil.getSqlSession();
			list = session.selectList("member.getAllMember");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return list;
	}

	@Override
	public int getMemberIdCount(String memId) {
		// ID중복확인
		SqlSession session = null;
		int cnt = 0; 
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.selectOne("member.getMemberIdCount", memId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return cnt;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("member.updateMember2", paramMap);
			if (cnt > 0) session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
		return cnt;
	}

}
