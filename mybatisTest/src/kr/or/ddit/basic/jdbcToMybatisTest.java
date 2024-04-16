package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.or.ddit.util.MybatisUtil;
import kr.or.ddit.vo.LprodVO;

// 'jdbcTest' 프로젝트에 있는 JdbcTest05클래스의 처리를 mybatis로 처리하시오

// 문제)
//LPROD테이블에 새로운 데이터 추가하기
//lprod_gu와 lprod_nm은 직접 입력 받아서 처리하고, 
//lprod_id는 현재의 lprod_id중에서 제일 큰 값보다 1 크게 한다.
//입력 받은 lprod_gu가 이미 등록되어있으면 다시 입력 받아서 처리한다

public class jdbcToMybatisTest {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
/*
		// mybatis의 환경설정파일(mybatis-config.xml)을 읽어와서 처리하기
		InputStream in = null;
		SqlSessionFactory sqlSessionFactory = null;
		try {
			// 환경 설정 파일을 읽어올 스트림 객체 생성
			in = Resources.getResourceAsStream("kr/or/ddit/mybatis/config/mybatis-config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
		} catch (Exception e) {
			System.out.println("MyBatis initialization failed");
		} finally {
			// 스트림 닫기
			if (in != null) try { in.close(); } catch (IOException e) { e.printStackTrace(); }
		} // /finally
		// /mybatis-config.xml 환경설정
*/
		SqlSession session = null;
		System.out.println("Start insert...");

		String gu;
		while (true) {
			System.out.print("Input lprod_gu >> ");
			gu = scan.next();
			try {
				// SqlSessionFactory객체의 openSession()메서드를 이용하여 SQL문을 호출해서 실행할 수 있는 SqlSession객체를
				// 생성
				session = MybatisUtil.getSqlSession(); // mybatis-config.xml 환경설정
				int guChk = session.selectOne("jdbc.getLprodCount", gu);
				if (guChk > 0) {
					System.out.println("Duplicated GU value");
				} else break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.print("Input lprod_nm >> ");
		String nm = scan.next();
		
		// 입력 받은 자료를 VO객체에 저장하기
		LprodVO lvo = new LprodVO();
		lvo.setLprod_gu(gu);
		lvo.setLprod_nm(nm);

		try {
			if (session.insert("jdbc.insert_lprod", lvo) > 0) {
				session.commit();
				System.out.println("Insert completed");
			} else System.out.println("Insert failed");
		} catch (Exception e) {
			e.printStackTrace();
		} finally { if (session != null) session.close(); }
		// /insert
		
	}
}
