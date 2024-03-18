package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileIOTest06 {

	public static void main(String[] args) {
		// scanner로 출려한 단을 입력 받아 입력받은 단의 구구단을 'D:/d_other'폴더에 'gugudan.txt'파일로 출력하는
		// 프로그램을 작성하시오
		Scanner sc = new Scanner(System.in);
		System.out.println("단을 입력하세요");
		int dan = sc.nextInt();
		try {
			// 파일 출력용 문자 기반 스트림 객체 생성
			FileWriter fw = new FileWriter("d:/d_other/gugudan.txt");
			for (int i = 1; i <= 9; i++) {
				String s = dan + " * " + i + " = " + (dan * i) + "\n";
				fw.write(s);
			}

			System.out.println("끝");
			fw.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}
}
