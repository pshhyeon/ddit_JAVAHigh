package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImple;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImple implements IBoardService {

	private static BoardServiceImple service;
	private static BoardDaoImple dao;

	private BoardServiceImple() {
		dao = BoardDaoImple.getInstance();
	}

	public static BoardServiceImple getInstance() {
		return service = service == null ? new BoardServiceImple() : service;
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public BoardVO detailBoard(int boardNo) {
		return dao.detailBoard(boardNo);
	}

	@Override
	public List<BoardVO> printBoard(String boardTitle) {
		return dao.printBoard(boardTitle);
	}

	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}

}
