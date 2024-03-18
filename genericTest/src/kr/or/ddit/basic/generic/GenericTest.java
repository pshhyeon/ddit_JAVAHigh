package kr.or.ddit.basic.generic;

class NonGerneric {
	private Object value;

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return this.value;
	}
}

/*
 * 제네릭 클래스 만드는 방법 
 * 
 * 형식) 
 * 
 * class 클래스명<제네릭타입글자>{
 * 	public 제네릭 타입 글자 변수명: 		// 변수 선언에 제네릭을 사용한 경우
 * 	...
 *  ...
 *  // 메서드의 반환 값 타입을 ㅗ제네릭을 사욯한 경우
 *  public 제네릭 타입 글자 메서드 명(메개변수들...){
 *  	...
 *  	return 반환값;
 *  }
 *  
 *  // 매개변수의 타입으로 제네릭을 사용한 경우
 *  public 한환값 타입 메서드명(제네릭 타입 글자 변수명, ...){
 *  	...
 *  	...
 *  }
 * }
 * 
 * - 제네릭 타입 글자로 많이 사용되는 것들
 *  T ==> Type
 *  K ==> key
 *  V ==> Value
 *  E ==> Element
 * 
 */

// Generic을 적용하는 class 만들기
class MyGeneric<T> {
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
}

public class GenericTest {

	public static void main(String[] args) {
		NonGerneric non1 = new NonGerneric();
		non1.setValue("안녕하세요");

		NonGerneric non2 = new NonGerneric();
		non2.setValue(123);

		String str1 = (String) non1.getValue();
		System.out.println("문자열 반환값: " + str1);

		int temp1 = (int) non2.getValue();
		System.out.println("정수형 반환값: " + temp1);

		// int temp2 = (int)non1.getValue(); // cast 오류

		System.out.println("--------------------------");

		MyGeneric<String> my1 = new MyGeneric<String>();
		my1.setValue("우리나라");
		// my1.setValue(123); // 제네릭과 다른 종류의데이터를 저장할 수 없다

		MyGeneric<Integer> my2 = new MyGeneric();
		my2.setValue(123);

		String str2 = my1.getValue(); // 데이터를 꺼내올 때 캐스팅 없이 사용할 수 있다
		int temp2 = my2.getValue();

		System.out.println("Generic 문자열 반환 값: " + str2);
		System.out.println("Generic 정수형 반환 값: " + temp2);
	}

}
