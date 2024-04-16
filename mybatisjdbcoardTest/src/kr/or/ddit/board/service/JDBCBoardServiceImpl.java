package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.JDBCBoardDaoImpl;
import kr.or.ddit.vo.BoardVO;

public class JDBCBoardServiceImpl implements IJDBCBoardService {

	private static JDBCBoardServiceImpl service;
	private static JDBCBoardDaoImpl dao;

	private JDBCBoardServiceImpl() {
		dao = JDBCBoardDaoImpl.getInstance();
	}

	public static JDBCBoardServiceImpl getInstance() {
		return service = service == null ? new JDBCBoardServiceImpl() : service;
	}

	@Override
	public int insertBoard(BoardVO boardVO) {
		return dao.insertBoard(boardVO);
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		return dao.updateBoard(boardVO);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public List<BoardVO> getAllBoard() {
		return dao.getAllBoard();
	}

	@Override
	public BoardVO getBoard(int boardNo) {
		return dao.getBoard(boardNo);
	}

	@Override
	public List<BoardVO> getSearchBoard(String title) {
		return dao.getSearchBoard(title);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
