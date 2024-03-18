package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

// 문제) 이름, 전화번호, 주소를 멤버로 갖는 Phone클래스를 작성한다
// Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오

// (Map 구조 key값 '사람의 이름' value값 'Phone클래스의 인스턴스')
// HashMap<String, Phone> 변수명;

// 아래의 메뉴의 기능을 구현.(삭제, 검색 기능에서는 '이름'을 입력받아 처리한다.)

// - 추가 조건
// 1) '6.전화번호 저장' 메뉴를 추가하고 구현한다.
// (저장 파일명은 'phoneBookData.dat'로 한다.)
// 2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 셋팅한다.
// 3) 프로그램을 종료할 때 Map의 데이터가 변경되거나 추가 또는 삭제되면 저장한 후 종료되록 한다.

// 실행 예시)
// 1. 전화번호 등록
// 2. 전화번호 수정
// 3. 전화번호 삭제
// 4. 전화번호 검색
// 5. 전화번호 전체 출력
// 0. 종료
// --------------------

// 번호  입력 >> 1
// 새롭게 등록할 전화번호 정보를 입력하세요
// 이 름 >> 홍길동 (중복시 => '홍길동'씨는 이미 등록된 사람입니다 출력 => 메뉴)
// 전화번호 >> 010-1234-5678
// 주 소 >> 대전시 중구 오류동

// '홍길동'씨의 전화번호 등록이 완료 되었습니다..
// 메뉴 출력

// 번호 입력 >> 5
// ---------------------------------
// 번호 이름 전화번호 주소
// ---------------------------------
// 1 홍길동 010-1234-5678 대전시 중구 오류동
// ~~
// ---------------------------------
// 출력 완료
// 메뉴 0입력시 프로그램을 종료합니다 출력
public class PhoneBookTest {
	Scanner sc = new Scanner(System.in);
	HashMap<String, Phone> user = new HashMap<String, Phone>();
	public String fileName = "d:/d_other/phoneBook.dat";

	// 데이터가 변경되었는지 확인하는 변수
	//
	private boolean dataChange;

	public static void main(String[] args) {
		new PhoneBookTest().process();
	}

	public void process() {
		user = load();
		while (true) {
			menu();
			System.out.println("메뉴 선택 >>");
			switch (sc.nextInt()) {
			case 1:
				insert();
				break;
			case 2:
				update();
				break;
			case 3:
				del();
				break;
			case 4:
				search();
				break;
			case 5:
				printList();
				break;
			case 6:
				save();
				break;
			case 0:
				// 데이터가 변경되었는지 확인 후 저장하기
				if (dataChange)
					save();
				System.out.println("종료");
				return;
			default:
				System.out.println("잘못선택");
				break;
			}
		}
	}

	public void menu() {
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체출력");
		System.out.println("6. 전화번호 저장");
		System.out.println("0. 종료");
		System.out.println("--------------------");
	}

	public void printList() {
		System.out.println("------------------------------------------");
		System.out.println("번호\t이름\t전화번호\t\t주소");
		System.out.println("------------------------------------------");
		int cnt = 0;
		for (String key : user.keySet()) {
			System.out.println(++cnt + "" + (Phone) user.get(key));
		}
		System.out.println("------------------------------------------");
	}

	public void insert() {
		System.out.println("이름 >> ");
		String name;
		do {
			name = sc.next();
		} while (user.containsKey(name)); // 재입력

		System.out.println("전화번호 >> ");
		String tel = sc.next();

		System.out.println("주소 >> ");
		String addr = sc.next();

		user.put(name, new Phone(name, tel, addr));
		dataChange = true;
	}

	public void update() {
		System.out.println("이름 >>");
		String key = sc.next();
		if (user.containsKey(key)) {
			System.out.println("수정할 정보 1. 번호 2. 주소");
			switch (sc.nextInt()) {
			case 1:
				System.out.println("수정 할 번호 >>");
				String tel = sc.next();
				user.get(key).setTel(tel);
				break;
			case 2:
				System.out.println("수정 할 주소 >>");
				String addr = sc.next();
				user.get(key).setAddr(addr);
				break;
			default:
				System.out.println("잘못 선택!");
				break;
			}
		} else {
			System.out.println("해당 유저는 존재하지 않습니다.");
		}
		System.out.println(key + "정보가 수정 되었습니다");
		dataChange = true;
	}

	public void del() {
		System.out.println("삭제할 유저 입력");
		String key = sc.next();
		if (user.containsKey(key)) {
			user.remove(key);
			System.out.println(key + "삭제");
		} else {
			System.out.println("해당 유저는 존재 하지 않습니다");
		}
		dataChange = true;
	}

	public void search() {
		System.out.println("이름 입력 >>");
		String key = sc.next();
		try {
			System.out.printf("이름: %s 전화번호: %s 주소: %s", user.get(key).getName(), user.get(key).getTel(),
					user.get(key).getAddr());
		} catch (NullPointerException e) {
			System.out.println("해당하는 사용자가 없습니다");
		}
	}

	// 파일에 저장된 전화번호 정보를 읽어오는 메서드
	private HashMap<String, Phone> load() {
		HashMap<String, Phone> pMap = null;
		// 예외처리
//		File file = new File(fileName);
//		if (!file.exists()) { // 파일 존재여부 체크
//			return new HashMap<String, Phone>(); // 빈 Map객체를 생성해서 반환
//		}
		ObjectInputStream oin = null;
		try {
			oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			// 파일에 저장된 자료를 읽어와 Map에 저장하기
			// 주의! 1번방법으로 저장된파일이있으면 2번 실행 x / 2번방법으로 저장된파일이있으면 1번 실행 x

			// 1) save()메서드에서 '방법1'로 저장했을 때...
			pMap = (HashMap<String, Phone>) oin.readObject();

			// ---------------------------------------------------------
			// 2) save()메서드에서 '방법2'로 저장했을 때...
//			pMap = new HashMap<String, Phone>();
//			Object obj = null;
//			while ((obj = oin.readObject()) != null) {
//				Phone p = (Phone) obj;
//				user.put(p.getName(), p);
//			}
		} catch (FileNotFoundException e) {
			return new HashMap<String, Phone>();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (EOFException e) {
			System.out.println("읽기 작업 완료...");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 사용했던 스트림 닫기
			if (oin != null) {
				try {
					oin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return pMap;
	}

	public void save() {
		ObjectOutputStream poout = null;
		try {
			// 출력용 스트림, 보조 스트림 객체 생성
			poout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));

			// 방법1 ==> Map객체 자체를 저장하기
			poout.writeObject(user);

			// 방법2 ==> Phone객체를 저장하기
//			for (String key : user.keySet()) {
//				Phone p = user.get(key);
//				poout.writeObject(p);
//			}
//			poout.writeObject(null);
			System.out.println("저장 완료...");
			dataChange = false;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 사용했던 스트림 닫기
			if (poout != null) {
				try {
					poout.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
	}
}

class Phone implements Serializable {
	private String name;
	private String tel;
	private String addr;

	public Phone(String name, String tel, String addr) {
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	@Override
	public String toString() {
		return "\t" + name + "\t" + tel + "\t" + addr;
	}

}