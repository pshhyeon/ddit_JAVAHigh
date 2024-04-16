package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	public int insertBoard(BoardVO boardVo);

	public int updateBoard(BoardVO boardVo);

	public int deleteBoard(int boardNo);

	public BoardVO detailBoard(int boardNo);

	public List<BoardVO> printBoard(String boardTitle);

	public int setCountIncrement(int boardNo);
}
