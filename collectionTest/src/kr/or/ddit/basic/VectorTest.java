package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {
	public static void main(String[] args) {
		// 객체 생성
		Vector v1 = new Vector();
		System.out.println("처음 크기: " + v1.size());

		// 데이터 추가1: add(추가할 데이터)
		// 반환값: 추가성공(true), 추가실패(false)
		// 객체만 저장할 수 있음
		v1.add("aaaa");
		v1.add(new Integer(111));
		v1.add(123); // auto boxing
		v1.add('a');
		v1.add(true);
		boolean r = v1.add(3.14);

		System.out.println("v1 크기: " + v1.size());
		System.out.println("반환값: " + r);
		System.out.println("v1_1: " + v1);

		// 데이터 추가2: addElement(추가할 데이터)
		// 이전 버전의 프로그램과의 호환성을 위해서 남아있는 메서드
		v1.addElement("AAAA");
		System.out.println("v1_2: " + v1);

		// 데이터 추가3 : add(index, data)
		// 'index'번째에 'data'를 끼워 넣는다
		// index는 0부터 시작한다, 반환값 x
		v1.add(1, "SSSS");
		System.out.println("v1_3: " + v1);

		// 데이터 가져오기: get(index)
		// index번째의 데이터를 꺼내서 반환한다
		System.out.println("0번째 데이터:" + v1.get(0));
		int iTemp = (int) v1.get(2);
		System.out.println(iTemp);

		// 데이터 수정: set(index, data)
		// index번째의 데이터를 변경한다
		// 반환값: 변경되기 전의 원래의 데이터
		String sTemp = (String) v1.set(0, "zzzz");
		System.out.println("v1_4: " + v1);
		System.out.println("변경전 첫번째 데이터: " + sTemp);

		// 데이터 삭제 1: remove(index)
		// index번째 데이터 삭제
		// 반환값: 삭제된 데이터
		System.out.println(v1.remove(0) + "가 삭제되었습니다");
		System.out.println("v1_5: " + v1);

		sTemp = (String) v1.remove(0);
		System.out.println("v1_6: " + v1);
		System.out.println("삭제된 데이터: " + sTemp);

		// 데이터 삭제 2: remove(data)
		// 삭제할 데이터를 찾아서 삭제한다
		// 삭제할 데이터가 여러개이면 이들 중에 제일 첫번째로 찾아진 자료를 삭제한다
		// 반환값: true(삭제 성공), false(삭제 실패)
		// 삭제할 데이터가 int형이거나 char형 일경우에는 반드시 객체로 변환해서 사용한다

		v1.remove("SSSS");
		System.out.println("(SSSS 삭제 후 ) v1_7: " + v1);

		// v1.remove(new Integer(123)); // 자바 1.9이상에서는 사용 불가
		v1.remove(Integer.valueOf(123));
		System.out.println("(123삭제 후) v1_8: " + v1);

		v1.remove(Character.valueOf('a'));
		System.out.println("('a'삭제 후) v1_9: " + v1);

		v1.remove(true);
		System.out.println("(true삭제 후) v1_10: " + v1);

		v1.remove(3.14);
		System.out.println("(3.14 삭제 후) v1_11: " + v1);

		// 전체 데이터 삭제하기 : clear()
		v1.clear();
		System.out.println("(clear 후) v1_12: " + v1);

		// 제네릭 타입(Generic Type): 클래스 내부에서 사용할 데이터의 타입을 객체를 생성할때 외부에서 지정해 주는 기법으로
		// 객체를 선언 할 때 괄호('< >') 안에 그 객체의 내부에서 사용할 데이터의 타입을 정해주는 것을 말한다
		// 이런식으로 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터들을 저장할 수 없다
		// 이 때 제네릭으로 선언 될 수 있는 데이터 타입은 '클래스형'이어야 한다
		// 그래서 int는 Integer, boolean은 Boolean, char는 Character등으로 대체해서 사용한다
		// 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 별도의 형변환이 필요없다

		Vector<Integer> v2 = new Vector<Integer>(); // int형 자료만 저장할 수 있는 Vector
		Vector<String> v3 = new Vector<String>(); // String형 자료만 저장할 수 있는 Vector

		v3.add("안녕하세요");
		// v3.add(100); // 오류: 지정한 제네릭 타입과 다른 종류의 데이터를 저장할 수 없다

		String sTemp2 = v3.get(0); // 형변환 없이 사용할 수 있다

		Vector<Vector> vv = new Vector();
		Vector<Vector<Vector>> vvv = new Vector();
		Vector<Vector<Vector<Vector<Vector<Vector<Vector<Vector<Vector<Vector>>>>>>>>> vvvvvvvvvv = new Vector();
		
		v3.clear();
		System.out.println("v3의 size: "+v3.size());
		
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		v3.add("FFF");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBB");
		v4.add("EEE");
		
		System.out.println("v3: " + v3);
		System.out.println("v4: " + v4);
		
		// 데이터 삭제하기3: removeAll(Collection 객체)
		// 벡터의 데이터들 중에서 'Collection객체'가 가지고 있는 모든 데이터 들을 삭제한다
		// 반환값: true(성공), false(실패)
		v3.removeAll(v4); // v3의 데이터들 중에서 v4가 가지고있는 데이터를 모두 삭제
		
		System.out.println("(v4값 삭제 후)v3: " + v3);
		
		v3.clear();
		v3.add("AAA");
		v3.add("BBB");
		v3.add("CCC");
		v3.add("DDD");
		v3.add("EEE");
		v3.add("FFF");
		
		for (int i = 0; i < v3.size(); i++) {
			System.out.println(i+"번째 데이터: "+v3.get(i));
		}
		System.out.println("----------------------------------------");
		
		for (String str : v3) {
			System.out.println(str);
		}

		

	}

}
