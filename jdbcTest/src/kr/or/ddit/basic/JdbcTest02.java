package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) 사용자로부터 LPROD_ID값을 입력받아 입력한 값보다 LPROD_ID가 큰 자료들을 출력하기오
public class JdbcTest02 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PSH", "java");

			System.out.print("LPROD_ID 입력 >> ");
			String sql = "select * from lprod where lprod_id > " + sc.nextLine();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println("== 쿼리문 실행 결과 ==");
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
