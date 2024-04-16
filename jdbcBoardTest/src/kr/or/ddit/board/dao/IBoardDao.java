package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	// 출력, 작성, 수정, 삭제, 검색
	public int insertBoard(BoardVO boardVo);

	public int updateBoard(BoardVO boardVo);

	public int deleteBoard(int boardNo);

	public BoardVO detailBoard(int boardNo);

	public List<BoardVO> printBoard(String boardTitle);

	public int setCountIncrement(int boardNo);
}