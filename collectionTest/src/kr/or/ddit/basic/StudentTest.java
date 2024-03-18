package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import sun.font.CreatedFontTracker;

// 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다

// 이 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서 초기화 처리를 한다

// 이 Student객체는 List에 저장하여 관리한다

// List에 저장된 데이터들은 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현한다. 
// 그리고 총점의 역순(내림차순)으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬하는 외부 정렬 기준 클래스를 작성하여 정렬된 경과를 출력하시오
// (등수는 구하지 않아도 됨)

public class StudentTest {

	public static void main(String[] args) {
		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student(5, "aaa", 10, 44, 5));
		list.add(new Student(3, "eee", 20, 22, 7));
		list.add(new Student(4, "ddd", 30, 11, 8));
		list.add(new Student(2, "ccc", 50, 55, 3));
		list.add(new Student(1, "bbb", 40, 33, 4));
		createRank(list);
		Collections.sort(list);
		Collections.sort(list, new StdDesc());

		for (Student std : list) {
			System.out.println(std);
		}
	}

	// rank method
	public static void createRank(List<Student> stdList) {
		for (Student std1 : stdList) {
			int rank = 1;
			for (Student std2 : stdList) {
				if (std1.getSum() < std2.getSum()) {
					rank++;
				}
			}
			std1.setRank(rank);
		}
	}

}

class StdDesc implements Comparator<Student> {
	@Override
	public int compare(Student std1, Student std2) {
		if (std1.getSum() - std2.getSum() > 0) {
			return -1;
		} else if (std1.getSum() - std2.getSum() < 0) {
			return 1;
		}
		// 이름 정렬
		else {
			return std1.getName().compareTo(std2.getName());
		}

	}

}

class Student implements Comparable<Student> {
	int stdNum;
	String name;
	int kor;
	int math;
	int eng;
	int sum;
	int avg;
	int rank;

	public Student(int stdNum, String name, int kor, int math, int eng) {
		this.stdNum = stdNum;
		this.name = name;
		this.kor = kor;
		this.math = math;
		this.eng = eng;
		this.sum = kor + math + eng;
	}

	public int getStdNum() {
		return stdNum;
	}

	public void setStdNum(int stdNum) {
		this.stdNum = stdNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getAvg() {
		return avg;
	}

	public void setAvg(int avg) {
		this.avg = avg;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [stdNum=" + stdNum + ", name=" + name + ", kor=" + kor + ", math=" + math + ", eng=" + eng
				+ ", sum=" + sum + ", avg=" + avg + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student std) {
		return this.getStdNum() - std.getStdNum();
	}

}