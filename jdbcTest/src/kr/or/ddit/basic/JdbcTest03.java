package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

// 문제) LPROD_ID값을 2개 입력 받아서 두 값 중 작은 값부터 큰 값 사이의 자료들을 출력하시오
public class JdbcTest03 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PSH", "java");
			// Statement사용
			/*
			System.out.println("정수 2개 입력(작은 값부터 입력) >> ");
			String sql = "select * from lprod where lprod_id between " + sc.nextInt() + " and " + sc.nextInt();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			*/
			
			// PreparedStatement사용
			System.out.println("정수 2개 입력 >> ");
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			if (n1 > n2) {
				int temp = n1;
				n1 = n2;
				n2 = temp;
			}
			String sql = "select * from lprod where lprod_id between ? and ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, n1);
			pstmt.setInt(2, n2);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				System.out.println("LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("LPROD_GU : " + rs.getString("lprod_gu"));
				System.out.println("LPROD_NM : " + rs.getString(3));
				System.out.println("---------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch (SQLException e) {}
			if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
			if (conn != null) try { conn.close(); } catch (SQLException e) {}
		}
	}
}
