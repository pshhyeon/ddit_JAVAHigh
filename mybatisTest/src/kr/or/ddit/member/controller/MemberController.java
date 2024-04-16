package kr.or.ddit.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberController { // +view

	private Scanner sc = new Scanner(System.in);

	private MemberServiceImpl service; // Service객체가 저장될 변수 선언

	public MemberController() {
		sc = new Scanner(System.in);
//		service = new MemberServiceImpl();
		service = MemberServiceImpl.getInstance();
	}

	public static void main(String[] args) {
		new MemberController().start();
	}

	public void start() {
		while (true) {
			int sel = menu();
			switch (sel) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				update2();
				break;
			case 5:
				print();
				break;
			case 0:
				return;
			}
		}
	} // /start()

	public int menu() {
		System.out.println("==== 작업 ====");
		System.out.println("1. 자료 추가");
		System.out.println("2. 자료 삭제");
		System.out.println("3. 자료 전체 수정");
		System.out.println("4. 자료 선택 수정");
		System.out.println("5. 전체 자료 출력");
		System.out.println("0. 작업 끝");
		System.out.print("작업 선택 >> ");
		return sc.nextInt();
	}

	private String idChk() {
		String memId;
		while (true) {
			System.out.print("아이디 입력 >> ");
			memId = sc.next();
			if (service.getMemberIdCount(memId) == 0) {
				System.out.println("존재하지 않는 아이디");
				continue;
			}
			return memId;
		}
	}

	private void insert() {
		MemberVO memVo = new MemberVO();
		String memId;
		while (true) {
			System.out.print("아이디 입력 >> ");
			memId = sc.next();
			if (service.getMemberIdCount(memId) > 0) {
				System.out.println("이미 존재하는 아이디");
				continue;
			}
			break;
		}
		memVo.setMem_id(memId);
		System.out.print("비밀번호 >> ");
		memVo.setMem_pass(sc.next());
		System.out.print("이름 >> ");
		memVo.setMem_name(sc.next());
		System.out.print("전화번호 >> ");
		memVo.setMem_tel(sc.next());
		System.out.print("주소 >> ");
		memVo.setMem_addr(sc.next());
		if (service.insertMember(memVo) > 0) {
			System.out.println(memId + " 등록성공");
		} else {
			System.out.println("등록실패");
		}
	} // /insert()

	private void delete() {
		String memId = idChk();
		if (service.deleteMember(memId) > 0) {
			System.out.println(memId + " 삭제성공");
		} else {
			System.out.println("삭제실패");
		}
	}

	private void update() {
		MemberVO memVo = new MemberVO();
		String memId = idChk();
		memVo.setMem_id(memId);
		System.out.print("비밀번호 >> ");
		memVo.setMem_pass(sc.next());
		System.out.print("이름 >> ");
		memVo.setMem_name(sc.next());
		System.out.print("전화번호 >> ");
		memVo.setMem_tel(sc.next());
		System.out.print("주소 >> ");
		memVo.setMem_addr(sc.next());
		if (service.updateMember(memVo) > 0) {
			System.out.println(memId + " 수정성공");
		} else {
			System.out.println("수정실패");
		}
	}

	// 자료 선택 수정
	public void update2() {
//		service.updateMember2()
		String memId = idChk();

		System.out.println("1. 비밀번호\n2. 이름\n3. 전화번호\n4. 주소");
		System.out.print("수정할 정보 선택 >> ");
		int sel = sc.nextInt();
		String fieldName;
		String updateData;
		switch (sel) {
		case 1:
			fieldName = "MEM_PASS";
			System.out.print("비밀번호 >> ");
			break;
		case 2:
			fieldName = "MEM_NAME";
			System.out.print("이름 >> ");
			break;
		case 3:
			fieldName = "MEM_TEL";
			System.out.print("전화번호 >> ");
			break;
		case 4:
			fieldName = "MEM_ADDR";
			System.out.print("주소 >> ");
			break;
		default:
			return;
		}
		updateData = sc.next();
		Map<String, String> paramMap = new HashMap<String, String>(); // Map객체 생성
		paramMap.put("memID", memId);
		paramMap.put("field", fieldName);
		paramMap.put("data", updateData);

		if (service.updateMember2(paramMap) > 0) {
			System.out.println(memId + " 수정성공");
		} else {
			System.out.println("수정실패");
		}
	}

	private void print() {
		List<MemberVO> memList = service.getAllMember();
		if (memList == null || memList.size() == 0) {
			System.out.println("등록된 회원이 없습니다");
			return;
		}

		System.out.println("ID\tPW\tNAME\tTEL\t\tADDR");
		System.out.println("---------------------------------------------------");
		for (MemberVO mem : memList) {
			String memId = mem.getMem_id();
			String memPass = mem.getMem_pass();
			String memName = mem.getMem_name();
			String memTel = mem.getMem_tel();
			String memAddr = mem.getMem_addr();
			System.out.println(memId + "\t" + memPass + "\t" + memName + "\t" + memTel + "\t" + memAddr);
		}
		System.out.println("---------------------------------------------------");
	}

}
