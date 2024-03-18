package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LottoTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		LottoTest obj = new LottoTest();

		System.out.println("Lotto 프로그램");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		int sel = sc.nextInt();
		if (sel == 2) {
			System.out.println("감사합니다");
		} else if (sel == 1) {
			System.out.println("금액입력: ");
			int money = sc.nextInt();
			int lottoNum = money / 1000;
			int change = money % 1000;
			if (money < 1000) {
				System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
			}
			if (lottoNum > 100) {
				System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
			} else {
				ArrayList<ArrayList> allLotto = new ArrayList<ArrayList>();
				for (int i = 0; i < lottoNum; i++) {
					HashSet h = new HashSet();
					ArrayList<Integer> l = new ArrayList<Integer>();
					for (int j = 0; j < 6; j++) {
						int num = (int) (Math.random() * 45) + 1;
						if (!h.add(num)) {
							j--;
							continue;
						}
						l.add(num);
					}
					allLotto.add(l);
				}
				for (int i = 0; i < allLotto.size(); i++) {
					String s = "로또번호" + (i + 1) + ": ";
					for (int j = 0; j < 6; j++) {
						s += allLotto.get(i).get(j) + ", ";
					}
					System.out.println(s.substring(0, s.length() - 2));
				}
			}
			System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + change + "원입니다.");
		}

	}
}
