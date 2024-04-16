package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

// LPROD테이블에 새로운 데이터 추가하기
// lprod_gu와 lprod_nm은 직접 입력 받아서 처리하고, 
// lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1 크게 한다.
// 입력 받은 lprod_gu가 이미 등록되어있으면 다시 입력 받아서 처리한다
public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PSH", "java");

			// DBUtil로 연결
			conn = DBUtil.getConnection();

			// lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1 크게 한다.
			String sql = "select max(lprod_id) as maxId from lprod";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			int maxId = 0;
			// ResultSet에 저장된 결과 데이터가 1개의 레코드일 경우 while문 대신 if문으로 처리해도 된다
			if (rs.next()) {
				maxId = rs.getInt("maxId"); // 컬럼의 alias명을 이용하여 값 구하기
				maxId++;
			}
			// /maxId값 구하기----

			// 입력 받은 lprod_gu가 이미 등록되어있으면 다시 입력 받아서 처리한다
			String gu; // 상품 분류 코드(LPROD_GU)가 저장될 변수 선언
			while (true) {
				System.out.print("lprod_gu(상품 분류 코드) 입력 >> ");
				gu = scan.next();
				sql = "select count(*) cnt from lprod where lprod_gu = '" + gu + "'";
				// SQL문의 물음표(?) 자리에 데이터 셋팅하기
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next()) {
					if (rs.getInt("cnt") == 0) { // 검색한 상품 분류 코드가 없으면 break;
						break;
					}
				}
				System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다. \n다시 입력하세요\n");
			}
			// prepareStatement를 많이 사용하는 이유 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// prepareStatement는 이미 conn.prepareStatement(sql);에서 해석이 끝나기 때문에
			// 반복문 안에서 set으로 데이터만 바꿔서 실행하는게 훨씬 좋은 효율을 보여준다

			System.out.print("lprod_nm(상품 분류명) 입력 >> ");
			String nm = scan.next();

			sql = "insert into lprod(lprod_id, lprod_gu, lprod_nm) values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, maxId);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);

			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("등록 성공");
			} else {
				System.out.println("등록 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} 
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (pstmt != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (stmt != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (conn != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
		}

	}
}