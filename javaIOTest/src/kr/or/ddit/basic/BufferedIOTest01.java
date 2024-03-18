package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {
	public static void main(String[] args) {
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");

			BufferedOutputStream bout = new BufferedOutputStream(fout, 5);

			for (char ch = '1'; ch <= '9'; ch++) {
				bout.write(ch);
			}
			// 출력작업을 마치면 버퍼에 남아있는 데이터를 모두 출력 시켜야 한다
			bout.flush();
			
			System.out.println("작업 끝...");
			// 보조 스트림을 닫으면 보조 스트림이 사용한 기반 스트림도 같이 닫힌다
			bout.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
}
