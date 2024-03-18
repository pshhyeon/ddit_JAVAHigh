package kr.or.ddit.basic;

import java.util.Stack;

public class StackTest {
	public static void main(String[] args) {
		Browser b = new Browser();
		b.history();
		System.out.println("사이트 방문하기...");
		b.goURL("1. 네이버");
		b.history();

		b.goURL("2.야후");
		b.history();

		b.goURL("3.구글");
		b.goURL("4.다음");
		b.history();

		System.out.println("뒤로 가기 후...");
		b.goBack();
		b.history();

		System.out.println("뒤로 가기 후...");
		b.goBack();
		b.history();

		System.out.println("앞으로 가기 후...");
		b.goForward();
		b.history();

		System.out.println("새로운 사이트 접속하기...");
		b.goURL("5. 네이트");
		b.history();

	}
}

// 웹 브라우저의 앞으로 가기, 뒤로가기 기능 구현하기(스택이용)

class Browser {
	private Stack<String> back;
	private Stack<String> forward;
	private String currentURL;

	// 생성자
	public Browser() {
		back = new Stack<String>();
		forward = new Stack<String>();
		currentURL = "";
	}

	// 사이트 이동
	public void goURL(String url) {
		System.out.println(url + "사이트에 접속합니다...");

		// 현재페이지가 존재하면 back Stack에 추가
		if (currentURL != null && !currentURL.equals("")) {
			back.push(currentURL);
		}

		currentURL = url; // 현재페이지를 '방문할 URL'로 변경
	}

	// 뒤로 가기
	public void goBack() {
		// isEmpty() => List, Stack등이 비어 있으면 true, 비어있지 않으면 false를 반환
		if (!back.isEmpty()) {
			forward.push(currentURL); // 현재 페이지를 forward스택에 추가
			currentURL = back.pop(); // back스택에서 1개의 요소를 꺼내와 현재 페이지로 지정한다.
		}
	}

	// 앞으로 가기
	public void goForward() {
		if (!forward.isEmpty()) {
			back.push(currentURL); // 현재 페ㅐ이지를 back스택에 추가
			currentURL = forward.pop(); // forward스택에서 1개의 요소를 꺼내와 현재 페이지로 한다.
		}
	}

	// 방문 기록 확인하기
	public void history() {
		System.out.println("\n---------------\n 방 문 기 록 \n---------------\n");
		System.out.println("back => " + back);
		System.out.println("currentURL => " + currentURL);
		System.out.println("forward => " + forward);
		System.out.println("----------------------------\n");
	}

}