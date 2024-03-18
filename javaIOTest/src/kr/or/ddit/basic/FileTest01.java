package kr.or.ddit.basic;

import java.io.File;

public class FileTest01 {

	public static void main(String[] args) {
		// File객체 만들기 연습

		// 1. new File(String 파일 또는 경로)
		// ==> 디렉토리와 디렉토리 사이 또는 디렉토리와 파일명 사이의 구분 문자는 역슬래쉬('\')를 사용하거나 슬래쉬('/')를 사용할 수 있다

		// File file1 = new File("d:/D_Other/test.txt"); // 구분문자를 '/'로 사용
		File file1 = new File("d:\\D_Other\\test.txt"); // 구분문자를 '\'로 사용

		System.out.println("1\n파일명: " + file1.getName());
		System.out.println("파일일까?: " + file1.isFile());
		System.out.println("디렉토리일까?: " + file1.isDirectory());

		File file2 = new File("D:/D_Other");
		System.out.println("\n2\n파일명: " + file2.getName());
		System.out.println("파일일까?: " + file2.isFile());
		System.out.println("디렉토리일까?: " + file2.isDirectory());

		// 2. new File(File parent, String child)
		// ==> 'parent'디렉토리 안에있는 'child' 파일을 갖는다

		File file3 = new File(file2, "test.txt");
		System.out.println("\n3\n파일명: " + file3.getName());
		System.out.println("파일일까?: " + file3.isFile());
		System.out.println("디렉토리일까?: " + file3.isDirectory());

		// 3. new File(String parent, String child)
		// ==> 'parent'디렉토리 안에 있는 'child' 파일을 갖는다
		File file4 = new File("d:/D_Other", "test.txt");
		System.out.println("\n4\n파일명: " + file3.getName());
		System.out.println("파일일까?: " + file4.isFile());
		System.out.println("디렉토리일까?: " + file4.isDirectory() + "\n");

		// --------------------------------------------------------------------------

		// 디렉토리(폴더) 생성하기
		// - mkdir()
		// ==> File객체의 경로 중 마지막 위치에 디렉토리를 만든다
		// ==> 반환값: 만들기 성공(true), 실패(flase)
		// ==> 중간의 경로가 모두 이미 만들어져 있어야 마지막 위치의 경로를 만들 수 있다
		// - mkdirs()
		// 

		File file5 = new File("d:/d_other/연습용");
		System.out.println(file5.getName() + "의 존재 여부: " + file5.exists());
		if (!file5.exists()) { // 존재하면 실행 하지 않는다
			if (file5.mkdir()) { // 만들기 성공하면...
				System.out.println(file5.getName() + "만들기 성공~~~");
			} else {
				System.out.println(file5.getName() + "만들기 실패~~~");
			}
		}
		
		File file6 = new File("d:/d_other/test/java/src");
		if (file6.mkdirs()) { // 만들기 성공하면...
			System.out.println(file6.getName() + "만들기 성공~~~");
		} else {
			System.out.println(file6.getName() + "만들기 실패~~~");
		}
	}
}
