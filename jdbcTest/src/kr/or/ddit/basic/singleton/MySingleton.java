package kr.or.ddit.basic.singleton;

// singleton패턴 ==> 객체가 1개만 만들어지게 하는 방법(외부에서 new 명령을 사용하지 못하게한다)
// - 사용이유
// 1) 메모리 낭비 방지
// 2) 데이터의 공유가 쉽다

// - singleton 클래스를 만드는 방법(필수 구성요소)
// 1) 자신 class의 참조값이 저장될 변수를 private static으로 선언한다
// 2) 모든 생성자의 접근제한자를 private으로 변경한다 - new 명령 사용 불가능
// 3) 자신 class의 인스턴스르 생성하고 반환하는 메서드를 public static으로 작성한다. (이 메서드의 이름은 보통 'getInstance'로 한다)
public class MySingleton {
	// 1번. class의 참조값이 저장될 변수선언
	private static MySingleton single;

	// 2번. 모든 생성자의 접근제한자 private으로 변경
	private MySingleton() { System.out.println("싱글톤 클래스의 생성자입니다."); }

	// 3번. 자신 class의 인스턴스르 생성하고 반환하는 메서드
	public static MySingleton getInstance() {
		// 1번의 변수가 null이면 객체를 생성하여 1번변수에 저장한다
		if (single == null) single = new MySingleton();

		// 1번 변수값을 반환한다
		return single;
	}

	// 기타.. 이 클래스가 처리할 내용들을 작성한다
	public void displayTest() { System.out.println("싱글톤 클래스에서 출력하는 내용입니다."); }

}
