package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {
	public static void main(String[] args) {
		// FileOutputStream객체를 이용한 파일 내용 읽기
		try {
			// 출력할 파일 정보를 갖는 출력용 파일 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/out.txt");
			for (char ch = 'A'; ch <= 'Z'; ch++) {
				fout.write(ch); // ch변수의 값을 파일로 출력한다
			}
			System.out.println("출력 완료...");
			fout.close(); // 스트림 닫기
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
