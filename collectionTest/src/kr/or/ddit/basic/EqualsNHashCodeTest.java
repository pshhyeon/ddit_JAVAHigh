package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;

/*
 *  ▶ Mehtod 영역 (클래스 정보, static)
 *  
 *  ▶ Call Stack
 *  
 *  ▶ Heap
 * 
 */
public class EqualsNHashCodeTest {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setNum(1);
		p1.setName("홍길동");

		Person p2 = new Person();
//		p1.setNum(2);
//		p1.setName("일지매");

		p2.setNum(1);
		p2.setName("홍길동");

		Person p3 = p1;

		System.out.println(p1 == p2); // false
		System.out.println(p1 == p3); // true
		System.out.println("-------------------------------------");

		System.out.println(p1.equals(p2)); // false > Object객체의 .equals()함수를 재정의하여 true반환
		System.out.println(p1.equals(p3)); // true
		System.out.println("-------------------------------------");

		HashSet<Person> testSet = new HashSet<Person>();
		testSet.add(p1);
		testSet.add(p2);
		System.out.println("Set의 개수: " + testSet.size());
		System.out.println("p1 hash: " + p1.hashCode());
		System.out.println("p2 hash: " + p2.hashCode());
		System.out.println("p3 hash: " + p3.hashCode());
		System.out.println("p1: " + p1);
		System.out.println("p2: " + p2);
		System.out.println("p3: " + p3);
	}

}

class Person /* extends 부모클래스(1개) implements 인터페이스(여러개) */ {
	private int num;
	private String name;

	public Person() {

	}

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) { // 참조값 체크
			return true;
		}

		if (obj == null) { // null 체크
			return false;
		}
		if (this.getClass() != obj.getClass()) { // 같은 유형의 클래스인지 체크
			return false;
		}
		Person that = (Person) obj; // 매개변수의 값을 현재 객체 유형으로 형변환 한다.
		return this.num == that.num && Objects.equals(this.name, that.name);
		// Objects.equals(객체1, 객체2) a와 b의 참조 변수를 비교할때 사용
	}

	@Override
	public int hashCode() {
		return Objects.hash(num, name);
	}
}