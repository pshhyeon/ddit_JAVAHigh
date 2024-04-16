package kr.or.ddit.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImple;
import kr.or.ddit.board.vo.BoardVO;

public class BoardController {
	Scanner sc;
	private BoardServiceImple service;

	public BoardController() {
		sc = new Scanner(System.in);
		service = BoardServiceImple.getInstance();
	}

	public static void main(String[] args) {
		new BoardController().start();
	}

	public void start() {
		boolean printAll = true;
		while (true) {
			printList(printAll);
			printAll = true;
			int sel = menu();
			switch (sel) {
			case 1:
				insert();
				break;
			case 2:
				detail();
				break;
			case 3:
				printAll = false;
				break;
			case 0:
				return;
			}
		}
	}

	public int menu() {
		System.out.print("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝\n>> ");
		return sc.nextInt();
	}

	public void insert() {
		BoardVO boardVo = new BoardVO();
		System.out.println("새글 작성하기\n-----------------------------------");
		System.out.print("- 제  목 : ");
		boardVo.setBoard_title(sc.next());
		System.out.print("- 작성자 : ");
		boardVo.setBoard_writer(sc.next());
		System.out.print("- 내  용 : ");
		boardVo.setBoard_content(sc.next());
		String insertYn = service.insertBoard(boardVo) > 0 ? "새글이 추가되었습니다." : "등록 실패";
		System.out.println(insertYn);
	}

	public void printList(boolean printAll) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		if (printAll) {
			list = service.printBoard(null);
		} else {
			System.out.print("- 검색할 제목 입력 : ");
			sc.nextLine();
			String title = sc.nextLine();
			list = service.printBoard(title);
		}

		if (list.size() == 0) {
			System.out.println("조회된 자료가 없습니다.");
			return;
		}

		System.out.println("-------------------------------------------------------------");
		System.out.println("No\t제 목\t\t작성자\t조회수");
		System.out.println("-------------------------------------------------------------");
		for (BoardVO boardVo : list) {
			System.out.println(boardVo.toString());
		}
		System.out.println("-------------------------------------------------------------");
	}

	public void detail() {
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int boardNo = sc.nextInt();

		BoardVO board = service.detailBoard(boardNo);
		if (board != null) {
			service.setCountIncrement(boardNo);
			System.out.println("-------------------------------------------------------------");
			System.out.println("- 제 목 : " + board.getBoard_title());
			System.out.println("- 작성자 : " + board.getBoard_writer());
			System.out.println("- 내 용 : " + board.getBoard_content());
			System.out.println("- 작성일 : " + board.getBoard_date());
			System.out.println("- 조회수 : " + board.getBoard_cnt());
			System.out.println("-------------------------------------------------------------");
		} else {
			System.out.println("존재하지 않는 게시물 입니다");
			return;
		}
		System.out.print("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기\n작업선택 >> ");
		switch (sc.nextInt()) {
		case 1:
			update(board);
			break;
		case 2:
			String delYn = service.deleteBoard(boardNo) > 0 ? "삭제 완료" : "삭제 실패";
			System.out.println(delYn);
			break;
		case 3:
			return;
		default:
			return;
		}
	}

	public void update(BoardVO board) {
		BoardVO boardVo = board;
		System.out.println("수정 작업하기\n-----------------------------------");
		System.out.print("- 제  목 : ");
		boardVo.setBoard_title(sc.next());
		System.out.print("- 내 용 : ");
		boardVo.setBoard_content(sc.next());

		String updateYn = service.updateBoard(boardVo) > 0 ? "수정 성공" : "수정 실패";
		System.out.println(updateYn);
	}

}
