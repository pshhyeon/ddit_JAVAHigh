package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao {
	
	private static final Logger logger = Logger.getLogger(MemberDaoImpl.class);
	
	// 1. 
	private static MemberDaoImpl dao;
	
	// 2. 
	private MemberDaoImpl() { }
	
	// 3. 
	public static MemberDaoImpl getInstance() {
		if (dao == null) { dao = new MemberDaoImpl(); }
		return dao;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성 완료...");
			String sql = "insert into mymember(mem_id, mem_pass, mem_name, mem_tel, mem_addr) "
					+ " values (?, ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_pass());
			pstmt.setString(3, memVo.getMem_name());
			pstmt.setString(4, memVo.getMem_tel());
			pstmt.setString(5, memVo.getMem_addr());
			
			logger.debug("PrepareStatement객체 생성 완료...");
			logger.debug("실행 SQL문장: " + sql);
			logger.debug("사용 데이터: " + memVo.getMem_id() + ", " 
									  + memVo.getMem_pass() + ", "
									  + memVo.getMem_name() + ", " 
									  + memVo.getMem_tel() + ", " 
									  + memVo.getMem_addr());
			

			cnt = pstmt.executeUpdate();
			logger.info("INSERT 성공...");
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.error("INSERT 실패...", e);
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
			logger.debug("자원 반납 성공...");
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성 완료...");

			String sql = " delete from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			logger.debug("PrepareStatement객체 생성 완료...");
			logger.debug("실행 SQL문장: " + sql);
			logger.debug("사용 데이터: " + memId);
			cnt = pstmt.executeUpdate();
			logger.info("DELETE 성공...");

		} catch (SQLException e) {
//			e.printStackTrace();
			logger.error("DELETE 실패...", e);
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
			logger.debug("자원 반납 성공...");
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성 완료...");

			String sql = " update mymember set mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ? "
					+ " where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memVo.getMem_pass());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			pstmt.setString(5, memVo.getMem_id());
			logger.debug("PrepareStatement객체 생성 완료...");
			logger.debug("실행 SQL문장: " + sql);
			logger.debug("사용 데이터: " + memVo.getMem_pass() + ", "
									  + memVo.getMem_name() + ", " 
									  + memVo.getMem_tel() + ", " 
									  + memVo.getMem_addr() + ", "
									  + memVo.getMem_id());
			
			cnt = pstmt.executeUpdate();
			logger.info("UPDATE 성공...");

		} catch (SQLException e) {
//			e.printStackTrace();
			logger.error("UPDATE 실패...", e);
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
			logger.debug("자원 반납 성공...");
		}
		return cnt;
	}
	
	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수
		
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성 완료...");

			String sql = " update mymember set " + paramMap.get("field") + " = ? where mem_id = ? ";
			// key값 정보 ==> 회원ID(memID), 컬럼명(field), 데이터값(data)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  paramMap.get("data"));
			pstmt.setString(2,  paramMap.get("memID"));
			logger.debug("PrepareStatement객체 생성 완료...");
			logger.debug("실행 SQL문장: " + sql);
			logger.debug("사용 데이터: " + paramMap.get("data") + ", "
									  + paramMap.get("memID"));
			
			cnt = pstmt.executeUpdate();	
			logger.info("UPDATE 성공...");

		} catch (SQLException e) {
//			e.printStackTrace();
			logger.error("UPDATE 실패...", e);
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
			logger.debug("자원 반납 성공...");
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MemberVO> memList = null;
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성 완료...");
			String sql = " select * from mymember ";
			
			pstmt = conn.prepareStatement(sql);
			logger.debug("PrepareStatement객체 생성 완료...");
			logger.debug("실행 SQL문장: " + sql);
			
			rs = pstmt.executeQuery();
			logger.info("SELECT 성공...");

			memList = new ArrayList<MemberVO>();
			while (rs.next()) {
				// 1개의 레코드가 저장될 VO객체 생성
				MemberVO memVo = new MemberVO();
				
				// VO객체에 select한 데이터를 저장
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_pass(rs.getString("mem_pass"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));
				
				// List에 memVo객체 추가
				memList.add(memVo);
			}
		} catch (SQLException e) {
			logger.error("SELECT 실패...", e);
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
			logger.debug("자원 반납 성공...");
		}
		
		return memList;
	}

	@Override
	public int getMemberIdCount(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0; // 반환값이 저장될 변수
		try {
			conn = DBUtil3.getConnection();
			logger.info("Connection객체 생성 완료...");

			String sql = " select count(*) from mymember where mem_id = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			logger.debug("PrepareStatement객체 생성 완료...");
			logger.debug("실행 SQL문장: " + sql);
			logger.debug("사용 데이터: " + memId);
			
			rs = pstmt.executeQuery();
			logger.info("SELECT 성공...");
			
			cnt = rs.next() ? rs.getInt(1) : cnt;
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.error("SELECT 실패...", e);
		} finally {
			if (pstmt != null) { try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (conn != null) { try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (rs != null) { try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
			logger.debug("자원 반납 성공...");
		}
		return cnt;
	}
	

}
