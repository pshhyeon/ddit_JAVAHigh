package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil3;

// 회원을 관리하는 프로그램을 작성하시오(MYMEMBER테이블 이용)

// 아래 메뉴의 기능을 몯두 구현하시오(CRUD기능 구현하기)
// 메뉴)
// == 작업선택 ==
// 1. 자료 추가 ==> insert (C)
// 2. 자료 삭제 ==> delete (D)
// 3. 자료 수정 ==> update (U)
// 4. 전체 자료 출력 ==> select (R)
// 0. 작업 끝

// 조건)
// 1) 자료 추가시 '회원ID'는 중복불가능. (중복시 재입력)
// 2) 자료 삭제시 '회원ID'를 입력받아서 처리
// 3) 자료 수정시 '회원ID'는 변경 불가능.

public class JdbcTest07 {
	Scanner sc = new Scanner(System.in);
	Connection conn = DBUtil3.getConnection();
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new JdbcTest07().start();
	}

	public void start() {
		while (true) {
			menu();
			int sel = sc.nextInt();
			switch (sel) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				print();
				break;
			case 5:
				update2();
				break;
			case 0:
				if (conn != null) {try { conn.close(); } catch (SQLException e) { e.printStackTrace() ;} }
				return;
			}
		}
	}

	public void menu() {
		System.out.println("==== 작업 ====");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 전체 수정");
		System.out.println("4. 전체 자료 출력");
		System.out.println("5. 자료 선택 수정");
		System.out.println("0. 작업 끝");
		System.out.print("작업 선택 >> ");
	}
	
	private void update2() {
		String sql = "select count(*) from mymember where mem_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			String id;
			while (true) {
				System.out.print("아이디 입력 >> ");
				id = sc.next();
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					if (rs.getInt(1) == 0) {
						System.out.println("존재하지 않는 아이디");
						continue;
					}
					break;
				}
			}
			
			System.out.println("1. 비밀번호\n2. 이름\n3. 전화번호\n4. 주소");
			System.out.print("수정할 정보 선택 >> ");
			int sel = sc.nextInt();
			sql = " update mymember set ";
			switch (sel) {
			case 1:
				System.out.print("비밀번호 >> ");
				sql += " mem_pass = ? ";
				break;
			case 2:
				System.out.print("이름 >> ");
				sql += " mem_name = ? ";
				break;
			case 3:
				System.out.print("전화번호 >> ");
				sql += " mem_tel = ? ";
				break;
			case 4:
				System.out.print("주소 >> ");
				sql += " mem_addr = ? ";
				break;
			}
			sql += " where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			String info = sc.next();
			pstmt.setString(1, info);
			pstmt.setString(2, id);
			if (pstmt.executeUpdate() > 0) {
				System.out.println(id + " 수정성공");
			} else {
				System.out.println("수정실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (rs != null) {try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
	}

	private void insert() {
		String sql = "select count(*) from mymember where mem_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			String id;
			while (true) {
				System.out.print("아이디 입력 >> ");
				id = sc.next();
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					if (rs.getInt(1) > 0) {
						System.out.println("중복된 아이디");
						continue;
					}
					break;
				}
			}

			sql = "insert into mymember(mem_id, mem_pass, mem_name, mem_tel, mem_addr) values (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			System.out.print("비밀번호 >> ");
			String pw = sc.next();
			System.out.print("이름 >> ");
			String name = sc.next();
			System.out.print("전화번호 >> ");
			String tel = sc.next();
			System.out.print("주소 >> ");
			String addr = sc.next();
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);
			if (pstmt.executeUpdate() > 0) {
				System.out.println(id + " 등록성공");
			} else {
				System.out.println("등록실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (rs != null) {try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
	}

	private void delete() {
		String sql = "select count(*) from mymember where mem_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			String id;
			while (true) {
				System.out.print("아이디 입력 >> ");
				id = sc.next();
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					if (rs.getInt(1) == 0) {
						System.out.println("존재하지 않는 아이디");
						continue;
					}
					break;
				}
			}

			sql = "delete from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			if (pstmt.executeUpdate() > 0) {
				System.out.println(id + " 삭제성공");
			} else {
				System.out.println("삭제실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (rs != null) {try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}

	}

	private void update() {
		String sql = "select count(*) from mymember where mem_id = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			String id;
			while (true) {
				System.out.print("아이디 입력 >> ");
				id = sc.next();
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					if (rs.getInt(1) == 0) {
						System.out.println("존재하지 않는 아이디");
						continue;
					}
					break;
				}
			}
			sql = " update mymember set mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ? where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			System.out.print("비밀번호 >> ");
			String pw = sc.next();
			System.out.print("이름 >> ");
			String name = sc.next();
			System.out.print("전화번호 >> ");
			String tel = sc.next();
			System.out.print("주소 >> ");
			String addr = sc.next();
			pstmt.setString(1, pw);
			pstmt.setString(2, name);
			pstmt.setString(3, tel);
			pstmt.setString(4, addr);
			pstmt.setString(5, id);
			if (pstmt.executeUpdate() > 0) {
				System.out.println(id + " 수정성공");
			} else {
				System.out.println("수정실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (rs != null) {try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}
	}

	private void print() {
		String sql = "select * from mymember";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			System.out.println("ID\tPW\tNAME\tTEL\t\tADDR");
			System.out.println("---------------------------------------------------");
			while (rs.next()) {
				String memId = rs.getString(1);
				String memPass = rs.getString(2);
				String memName = rs.getString(3);
				String memTel = rs.getString(4);
				String memAddr = rs.getString(5);
				System.out.println(memId + "\t" + memPass + "\t" + memName + "\t" + memTel + "\t" + memAddr);
			}
			System.out.println("---------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
			if (rs != null) {try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
		}

	}
}
