package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {
	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<Member>();
		memList.add(new Member(1, "홍길동", "010-1111-2222"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(9, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));

		System.out.println("정렬 전...");
		for (Member mem : memList) {
			System.out.println(mem);
		}

		System.out.println("-----------------------------");
		Collections.sort(memList);

		System.out.println("name 기준 오름차순 정렬 후...");
		for (Member mem : memList) {
			System.out.println(mem);
		}

		System.out.println("-----------------------------");
		Collections.sort(memList, new SorNumDesc());

		System.out.println("num기준 내림차순 정렬 후...");
		for (Member mem : memList) {
			System.out.println(mem);
		}

	}
}

class SorNumDesc implements Comparator<Member> {
	@Override
	public int compare(Member o1, Member o2) {
		// Wrapper클래스를 이용하는 방법 1
		// return new Integer(o1.getNum()).compareTo(o2.getNum()) * -1;

		// Wrapper클래스를 이용하는 방법 2
		return Integer.compare(o1.getNum(), o2.getNum()) * -1;
	}

}

// Member클래스의 '회원 이름'을 기준으로 오름차순이 되도록 내부 정렬 기준을 추가하기
// => Comparable인터페이스를 구현한다
class Member implements Comparable<Member> {
	private int num;
	private String name;
	private String tel;

	// 생성자
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	// getter / setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 회원 이름의 오름차순 정렬 기준
	@Override
	public int compareTo(Member mem) {
		return this.getName().compareTo(mem.getName());
	}
}