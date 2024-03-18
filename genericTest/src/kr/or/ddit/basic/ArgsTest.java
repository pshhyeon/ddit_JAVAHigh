package kr.or.ddit.basic;

import java.util.Arrays;

public class ArgsTest {
	// 매개변수로 받은 정수들의 합계를 구하는 메서드 만들기
	// (이 정수의 개수는 상황에 따라 다를 수 있다)

	// 배열을 이용한 메서드
	public int sumArr(int[] data) {
		int sum = 0;
		for (int i : data) {
			sum += i;
		}
		return sum;
	}

	// 가변인자 => 메서드의 인자의 개수가 실행될 때마다 다를 때 사용한다
	// - 가변인자는 메서드 안에서는 배열로 처리된다
	// - 가변인자는 메서드에서는 한 개만 사용할 수 있다

	// 가변인자를 이용한 메서드
	public int sumArg(int... data) { // 정수형 데이터를 가변인자로 받는다 => int...
		int sum = 0;
		for (int i : data) {
			sum += i;
		}
		return sum;
	}

	// 가변 인자와 일반적인 매개변수를 같이 사용할 경우
	// 가변인자를 제일 뒤쪽에 배치해야 한다
	public String sumArg2(String name, int... data) {
		int sum = 0;
		for (int i : data) {
			sum += i;
		}
		return name + ": " + sum;
	}

	public static void main(String[] args) {
		ArgsTest test = new ArgsTest();

		int[] numArr = { 100, 200, 300 };
		System.out.println(test.sumArr(numArr));

		// 1~9까지 합계 메서드
		System.out.println(test.sumArr(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }));
		System.out.println(test.sumArg(new int[] { 100, 200, 300 }));
		
		System.out.println(test.sumArg2("홍길동", new int[] { 10, 20, 30, 40 }));
		
		
		
	}
}
