package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {
	public static void main(String[] args) {
		// FileInputStream객체를 이용한 파일 내용 읽기

		try {
			// 읽어올 파일 정보를 갖는 파일 입력용 스트림 객체를 생성한다

			// 방법1) 읽어올 파일 정보를 문자열로 지정하는 방법
//			FileInputStream fin = new FileInputStream("d:/d_other/test.txt");

			// 방법2) 읽어올 파일 정보가 저장된 File객체를 지정하는 방법
			File file = new File("d:/d_other/test.txt");
			FileInputStream fin = new FileInputStream(file);

			int c; // 읽어온 데이터가 저장될 변수 선언
			while ((c = fin.read()) != -1) {
				// 읽어온 데이터를 화면에 출력하기
				System.out.print((char) c);
			}
			fin.close(); // 작업 완료 후 스트림 닫기
		} catch (IOException e) {
			System.out.println("입출력 오류입니다...");
		}
	}
}
