package kr.or.ddit.basic;

// enum(열거형) ==> 서로 관련있는 상수들의 집합을 나타낸다 / 클래스처럼 보이게 하는 상수

// - 작성 방법
// 1) 클래스처럼 독립된 java파일에 만드는 방법
// 2) 하나의 java파일에 클래스와 같이 만드는 방법
// 3) 클래스의 내부에 내부 클래스처럼 만드는 방법

// - 열겨형의 속성 및 메서드
// 1) 열겨형변수.name() ==> 열거형 상수의 이름을 문자열로 반환한다
// 2) 열거형변수.ordinal() ==> 열거형 상수가 정의된 순서값(index값)을 반환한다. (0부터 시작)
// 3) 열겨형이름.valueOf("열거형 상수명") ==> 인수값에 지정한 '열거형 상수명'과 일치하는 열거형 상수르 반환한다.
// 4) 열거형이름.상수명 ==> 열거형이름.valueOf("상수명")와 같다
// 5) 열거형이름.values() ==> 모든 상수들을 배열로 가져온다

// -------------------------------------------------------

// - 열거형 선언하기
// 방법1)
// enum 열거형이름 { 상수명1, 상수명2, 상수명3, ... }

// 방법2)
// enum 열거형이름{
// 	상수명1(값들1, ...),
//  상수명2(값들2, ...),
//  ...
//  상수명n(값들n, ...);
//
// '값들'이 저장될 변수를 선언
//  private 자료형이름 변수명1;
//  private 자료형이름 변수명2;
//  ....
//
//  열거형의 상성자를 작성한다
//  ==> 열거형의 생성자는 '열거형상수'에 '값들'을 셋팅하는 역할을 수행한다
//  ==> 열거형의 상성자의 접근 제한자는 묵시적으로 'private'이다
//
//  생성자의 매개변수는 '값들'과 갯수가 같고, 자료형이 맞아야 한다
//  private 열거형이름(자료형 이름 변수명 1, ...){
//  	위에 선언된 변수명들을 초기화 한다
//      ...
//  }
//  
//  위에 선언한 변수들을 외부에서 불러올 수 있는 getter메서드를 작성한다
//  ...
//
// }

public class EnumTest {
	public enum Color {
		RED, GREEN, BLUE
	}

	public enum Count {
		ONE, TWO, THREE
	}

	public enum Season {
		봄("3월부터 5월까지", 13), // 상수명(값들, ...) 형식의 선언
		여름("6월부터 8월까지", 29), 가을("9월부터 11월까지", 15), 겨울("12월부터 2월까지", 3);

		// 변수 필드
		private String span;
		private int temp;

		Season(String months, int temp) { // private 생략
			span = months;
			this.temp = temp;
		}

		public String getSpan() {
			return this.span;
		}

		public int getTemp() {
			return this.temp;
		}

	}

	public static void main(String[] args) {
//		System.out.println("RED: " + ConstTest.RED);
//		System.out.println("THREE: " + ConstTest.THREE);
//
//		if (ConstTest.ONE == ConstTest.RED) {
//			System.out.println("같다...");
//		} else {
//			System.out.println("같지 않다");
//		}

		Color mycol = Color.valueOf("GREEN"); // == Color.GREEN
		Count mycnt = Count.ONE; // == Count.valueOf("ONE")

		System.out.println("mycol의 name: " + mycol.name());
		System.out.println("mycnt의 name: " + mycnt.name());

		System.out.println("mycol의 ordinal: " + mycol.ordinal());
		System.out.println("mycnt의 ordinal: " + mycnt.ordinal());
		/*
		 * if (Color.RED = Count.ONE)) { // 다른 종류의 열거형 끼리는 비교 불가능
		 * 
		 * }
		 */

		if (mycol == Color.BLUE) { // 같은 종류의 열거형 끼리만 비교 가능
			System.out.println("같다...");
		} else {
			System.out.println("같지 않다...");
		}

		System.out.println("--------------------------");

		Season ss = Season.봄;
		System.out.println("name: " + ss.name() + "\nname: " + ss.ordinal());
		System.out.println("span: " + ss.getSpan() + "\ntemp: " + ss.getTemp());

		for (Season time : Season.values()) {
			System.out.println(time.name() + " == " + time + " ==> " + time.getSpan() + ", " + time.getTemp());
		}

		// 열거형을 switch문에서 비교하기
		// case문 옆에는 '상수명'만 기술해야 한다
		switch (mycol) {
		// case Color.RED : break; // case 열거형이름.상수명: => 사용불가
		case RED:
			System.out.println("RED 입니다...");
			break;
		case GREEN:
			System.out.println("GREEN 입니다...");
			break;
		case BLUE:
			System.out.println("BLUE 입니다...");
			break;
		default:
			break;
		}

	}
}
