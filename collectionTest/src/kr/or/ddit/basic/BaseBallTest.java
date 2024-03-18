package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

// 예제) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오
// 컴퓨터의 숫자는 난수를 이용하여 구한다
// (스트라이크는 S, 볼은 B로 출력한다)

// 컴퓨터의 난수 => 9 5 7

// 실행예시)
// 숫자 입력 >> 3 5 6
// 3 5 6 => 1S 0B
// 숫자 입력 >> 7 8 9
// 7 8 9 => 0S 2B
// 숫자 입력 >> 9 7 5
// 9 7 5 => 1S 2B
// 숫자 입력 >> 9 5 7
// 9 5 7 => 3S 0B

// 축하합니다 당신은 4번째 만에 맞췄습니다.

public class BaseBallTest {


	// 풀이
	private ArrayList<Integer> numList; // 난수 List
	private ArrayList<Integer> userList = new ArrayList<Integer>(); // userList

	Scanner sc = new Scanner(System.in);

	private int strike;
	private int ball;

	public static void main(String[] args) {
		new BaseBallTest().gameStart();

	}

	public void gameStart() {
		// 난수를 만드는 메서드 호출
		createNum();

		// 확인용 출력
//		System.out.println(" 컴퓨터의 난수값 == > " + numList);

		int cnt = 0; // 시도 횟수
		do {
			cnt++;
			inputNum();
			ballCount();
		} while (strike != 3);
		System.out.println("축하합니다. 당신은 " + cnt + "번째만에 맞췄습니다.");
	}

	private void createNum() {
		Set<Integer> numSet = new HashSet<Integer>();

		// 난수 3개 생성
		while (numSet.size() < 3) {
			numSet.add((int) (Math.random() * 9 + 1));
		}

		// 난수 리스트에 저장
		numList = new ArrayList<Integer>(numSet);
		Collections.shuffle(numList);
	}

	// 사용자로 부터 3개의 정수를 입력 받아서 List에 저장하는 메서드
	// 입력한 정수들은 중복되지 않게 한다.
	private void inputNum() {
		int n1, n2, n3; // 입력한 값이 저장될 변수 선언

		do {
			System.out.println("숫자입력 >> ");
			n1 = sc.nextInt();
			n2 = sc.nextInt();
			n3 = sc.nextInt();

			if (n1 == n2 || n1 == n3 || n2 == n3) {
				System.out.println("중복되는 값은 입력할 수 없습니다. 다시 입력하세요...");
			}

		} while (n1 == n2 || n1 == n3 || n2 == n3);

		// 입력 받은 값들을 List에 추가한다
		userList.clear();
		userList.add(n1);
		userList.add(n2);
		userList.add(n3);
	}

	// 스트라이크와 볼을 판정하고 결과를 출력하는 메서드
	private void ballCount() {
		strike = 0;
		ball = 0; // 초기화

		for (int i = 0; i < numList.size(); i++) {
			for (int j = 0; j < userList.size(); j++) {
				if (numList.get(i) == userList.get(j)) {
					if (i == j) {
						strike++;
					} else {
						ball++;
					}
					break;
				}
			}
		}

		// 볼카운트 결과 출력하기
		System.out.printf("%d %d %d ==> %dS %dB\n", userList.get(0), userList.get(1), userList.get(2), strike, ball);
	}

	/*
 	// 내 풀이
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> com = new ArrayList<Integer>();
		HashSet<Integer> h = new HashSet<Integer>();
		while (com.size() < 3) {
			int i = (int) (Math.random() * 9) + 1;
			if (h.add(i))
				com.add(i);
		}
		Collections.shuffle(com);
//		System.out.println(com);
		int cnt = 0;
		while (true) {
			cnt++;
			ArrayList<Integer> user = new ArrayList<Integer>();
			int strike = 0;
			int ball = 0;
			for (int i = 0; i < 3; i++) {
				System.out.println(i + 1 + "번호입력: ");
				user.add(sc.nextInt());
			}

			for (int i = 0; i < user.size(); i++) {
				if (user.get(i) == com.get(i)) {
					strike++;
				} else {
					for (int j = 0; j < com.size(); j++) {
						if (user.get(i) == com.get(j)) {
							ball++;
						}
					}
				}
			}

			System.out.println(strike + "S " + ball + "B");
			if (strike == 3) {
				System.out.println(cnt + "번 시도");
				break;
			}
		}

	}*/
	
	
}