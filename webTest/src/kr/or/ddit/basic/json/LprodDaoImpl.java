package kr.or.ddit.basic.json;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

public class LprodDaoImpl implements ILprodDao {
	private static LprodDaoImpl dao;
	
	private LprodDaoImpl() { }
	
	public static LprodDaoImpl getInstance() {
		return dao = dao == null ? new LprodDaoImpl() : dao;
	}
	
	@Override
	public List<LprodVO> getAllLprod() {
		// DB에서 lprodList가져오기
		SqlSession session = null;
		List<LprodVO> lprodList = null; // lprodVO객체 리스트 생성
		try {
			session = MybatisUtil.getSqlSession();
			lprodList = session.selectList("lprod.getLprodList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) { session.close();}
		}
		return lprodList;
	}

}
