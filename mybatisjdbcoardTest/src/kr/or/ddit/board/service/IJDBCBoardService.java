package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface IJDBCBoardService {
	// 출력, 작성, 수정, 삭제, 검색
	public int insertBoard(BoardVO boardVO);

	public int updateBoard(BoardVO boardVO);

	public int deleteBoard(int boardNo);

	public List<BoardVO> getAllBoard();

	public BoardVO getBoard(int boardNo);

	public List<BoardVO> getSearchBoard(String title);

	public int setCountIncrement(int boardNo);
}