package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest04 {
	// 문제3) 5명의 별명을 입력받아 ArrayList에 저장하고 이들 중에서 별명의 길이가 제일 긴 별명을 출력하는 프로그램을 작성
	// (단, 입력할 때 각 별명의 길이가 같을 수 있다)
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("qw");
		list.add("wq");
		list.add("reqw");
		list.add("qwer");
		list.add("qew");

		// 길이
		int i = ((String) list.get(0)).length();
		for (Object o : list.toArray()) {
			i = i < ((String) o).length() ? ((String) o).length() : i;
		}
		
		// 출력
		for (Object o : list.toArray()) {
			if(((String) o).length() == i) {
				System.out.println(o);
			}
		}

	}
}
