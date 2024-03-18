package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 문제1) 5명의 사람 이름을 입력 받아 ArrayList에 저장한 후에 ArrayList에 저장된 데이터들 중에서 '김'씨 성의 이름을 모두 출력하시오
// (단, 입력은 Scanner 객체를 이용한다.)

// 문제2) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중에서 별명의 길이가 제일 긴 별명을 출력하는 프로그램을 작성
// (단, 입력할 때 각 별명의 길이를 다르게 입력한다)
// (작성 클래스명: ArrayListTest03)

// 문제3) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중에서 별명의 길이가 제일 긴 별명을 출력하는 프로그램을 작성
// (단, 입력할 때 각 별명의 길이가 같을 수 있다)
// (작성 클래스명: ArrayListTest04)

public class ArrayListTest02 {
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		ArrayListTest02 obj = new ArrayListTest02();
		obj.method1();

	}

	public void method1() {
		ArrayList<String> list = new ArrayList();
		for (int i = 0; i < 5; i++) {
			System.out.println("입력: ");
			String s = sc.nextLine();
			list.add(s);
		}

		for (String s : list) {
			if (String.valueOf((s.charAt(0))).equals("김")) {
				System.out.println(s);
			}
		}
	}

}
