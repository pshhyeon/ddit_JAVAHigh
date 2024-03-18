package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopyTest {
// 문제) 'd:/d_other'폴더에 있는 '펭귄.jpg'파일을 'd:/d_other/연습용' 폴더에 '펭귄_복사본.jpg'파일로 복사하는 프로그램을 작성하시오.
	public static void main(String[] args) {
		// 풀이
		File file = new File("d:/d_other/펭귄.jpg");
		if (!file.exists()) {
			System.out.println(file.getAbsolutePath() + " 파일이 없습니다");
			System.out.println("복사작업을 중지합니다...");
			return;
		}
		FileInputStream fin = null;
		FileOutputStream fout = null;
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;

		try {
			// 원본 파일을 읽어올 스트림 객체 생성
			fin = new FileInputStream(file);
			bin = new BufferedInputStream(fin);

			// 대상 파일에 출력할 스트림 객체 생성
			fout = new FileOutputStream("d:/d_other/연습용/펭귄_복사본.jpg");
			bout = new BufferedOutputStream(fout);

			System.out.println("복사 작업 시작...");
			int data; // 읽어온 자료가 저장될 변수
			while ((data = fin.read()) != -1) {
				bout.write(data);
			}
			bout.flush();
			System.out.println("복사 작업 완료...");

		} catch (IOException e) {
			// TODO: handle exception
		} finally {
//			if (fin != null) {
//				try {
//					fin.close();
//				} catch (IOException e2) {
//					// TODO: handle exception
//				}
//			}
//			if (fout != null) {
//				try {
//					fout.close();
//				} catch (IOException e2) {
//					// TODO: handle exception
//				}
//			}
			if (bin != null) {
				try {
					fin.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
			}
			if (bout != null) {
				try {
					fout.close();
				} catch (IOException e2) {
					// TODO: handle exception
				}
			}

		}

		// 나의 풀이
//		try {
//			FileInputStream infile = new FileInputStream("d:/d_other/펭귄.jpg");
//			FileOutputStream outfile = new FileOutputStream("d:/d_other/연습용/펭귄_복사본.jpg");
//			int i;
//			while ((i = infile.read()) != -1) {
//				outfile.write(i);
//			}
//			System.out.println("finish");
//			infile.close();
//			outfile.close();
//		} catch (IOException e) {
//		}

	}
}
