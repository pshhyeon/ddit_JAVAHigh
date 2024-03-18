package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest03 {
	// 문제2) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중에서 별명의 길이가 제일 긴 별명을 출력하는 프로그램을 작성
	// (단, 입력할 때 각 별명의 길이를 다르게 입력한다)
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("q");
		list.add("qw");
		list.add("qwe");
		list.add("qwer");
		list.add("qwert");

		String s = (String) list.get(0);
		for (Object o : list.toArray()) {
			s = s.length() < ((String) o).length() ? (String) o : s;
		}
		System.out.println(s);

	}
}
