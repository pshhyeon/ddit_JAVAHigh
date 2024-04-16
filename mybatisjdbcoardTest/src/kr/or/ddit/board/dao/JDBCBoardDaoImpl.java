package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.BoardVO;

public class JDBCBoardDaoImpl implements IJDBCBoardDao {

	private static JDBCBoardDaoImpl dao;

	private JDBCBoardDaoImpl() { }

	public static JDBCBoardDaoImpl getInstance() {
		return dao = dao == null ? new JDBCBoardDaoImpl() : dao;
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.insert("board.isnertBoard", boardVO);
			if (cnt > 0) { session.commit(); }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) { session.close(); }
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("board.updateBoard", boardVO);
			if (cnt > 0) { session.commit(); }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) { session.close(); }
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.delete("board.deleteBoard", boardNo);
			if (cnt > 0) { session.commit(); }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) { session.close(); }
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		SqlSession session = null;
		List<BoardVO> boardList = null;
		try {
			session = MybatisUtil.getSqlSession();
			boardList = session.selectList("board.getAllBoard");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) { session.close(); }
		}
		return boardList;
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		SqlSession session = null;
		BoardVO boardVO = null;
		try {
			session = MybatisUtil.getSqlSession();
			boardVO = session.selectOne("board.getBoard", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) { session.close(); }
		}
		return boardVO;
	}

	@Override
	public List<BoardVO> getSearchBoard(String title) {
		SqlSession session = null;
		List<BoardVO> boardList = null;
		try {
			session = MybatisUtil.getSqlSession();
			boardList = session.selectList("board.getSearchBoard");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) { session.close(); }
		}
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		SqlSession session = null;
		int cnt = 0;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("board.setCountIncrement", boardNo);
			if (cnt > 0) { session.commit(); }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) { session.close(); }
		}
		return cnt;
	}

}
