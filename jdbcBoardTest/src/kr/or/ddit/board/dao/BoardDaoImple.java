package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.DBUtil3;

public class BoardDaoImple implements IBoardDao {

	private static BoardDaoImple dao;

	private BoardDaoImple() { }

	public static BoardDaoImple getInstance() {
		return dao = dao == null ? new BoardDaoImple() : dao;
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();

			String sql = " insert into JDBC_BOARD (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE, BOARD_CNT, BOARD_CONTENT) " + 
					" values (board_seq.NEXTVAL, ?, ?, sysdate, 0, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();

			String sql = "update JDBC_BOARD set BOARD_TITLE = ? , BOARD_CONTENT = ? where BOARD_NO = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_no());

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();

			String sql = "delete from JDBC_BOARD where board_no = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return cnt;
	}

	@Override
	public BoardVO detailBoard(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO boardVo = null;
		try {
			conn = DBUtil3.getConnection();

			String sql = " select * from JDBC_BOARD where BOARD_NO = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				boardVo = new BoardVO();
				boardVo.setBoard_no(rs.getInt(1));;
				boardVo.setBoard_title(rs.getString(2));
				boardVo.setBoard_writer(rs.getString(3));
				boardVo.setBoard_date(rs.getString(4));
				boardVo.setBoard_cnt(rs.getInt(5));
				boardVo.setBoard_content(rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return boardVo;
	}

	@Override
	public List<BoardVO> printBoard(String boardTitle) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			conn = DBUtil3.getConnection();

			String sql = " select * from JDBC_BOARD ";
			if (!(boardTitle == null || boardTitle.isEmpty())) {
				sql += " where BOARD_TITLE like '%" + boardTitle + "%' "; 
			}
			sql += " order by 1 desc ";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO boardVo = new BoardVO();
				boardVo.setBoard_no(rs.getInt(1));;
				boardVo.setBoard_title(rs.getString(2));
				boardVo.setBoard_writer(rs.getString(3));
				boardVo.setBoard_date(rs.getString(4));
				boardVo.setBoard_cnt(rs.getInt(5));
				boardVo.setBoard_content(rs.getString(6));
				list.add(boardVo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return list;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;

		try {
			conn = DBUtil3.getConnection();

			String sql = " update JDBC_BOARD set BOARD_CNT = ( "
					+ " select BOARD_CNT from JDBC_BOARD where BOARD_NO = ?) + 1 where BOARD_NO = ? ";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, boardNo);

			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
		return cnt;
	}
	
	

}
