package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.JDBCBoardServiceImpl;
import kr.or.ddit.vo.BoardVO;

public class JdbcBoardController {
	private JDBCBoardServiceImpl service;
	private Scanner scan;
	
	//생성자
	public JdbcBoardController() {
		scan = new Scanner(System.in);
		service = JDBCBoardServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new JdbcBoardController().boardStart();
	}
 
    //시작메서드
    public void boardStart() {
    	String searchword = null;
    	int choice = 0;
    	while(true) { 		
    		if(choice!=3) {
    			searchword = null;  			
    		}
    		choice = displayMenu(searchword);
    		switch(choice) {
    		case 1:              //새글작성
    			insertBoard(); 
    			break;
    		case 2:              //게시글 보기
    			viewBoard(); 
    			break;
    		case 3:              //검색
    			searchword = searchBoard();
    			break;
    		case 0:              //작업 끝     
    			System.out.println("게시판 프로그램을 종료합니다...");
    			return;
    		default :
    			System.out.println("작업번호를 잘못 입력 했습니다.");
    			System.out.println("다시 입력하세요");
    		}
    	}
    
    }
    //검색할 제목을 입력 받아 반환하는 메서드
    private String searchBoard() {
    	scan.nextLine(); //버퍼 비우기
         System.out.println();
         System.out.println("검색작업");
         System.out.println("------------------------");
         System.out.println(" - 검색할 제목 입력 : ");
         return scan.nextLine();
    }

	//게시글 내용을 보여주는 메서드
    private void viewBoard() {
    	System.out.println("\n보기를 원하는 게시글 번호 입력 >> ");
    	int no = scan.nextInt();
    	
    	//게시글 번호에 맞는 게시글 정보 가져오기
    	BoardVO boardVO = service.getBoard(no);
    	
    	if(boardVO==null) {
    		System.out.println(no +"번의 게시글이 존재하지 않습니다...");
    		return;
    	}
    	
		System.out.println();
		System.out.println(no + "번 글 내용");
		System.out.println("-----------------------------");
		System.out.println("- 제 목 :" + boardVO.getBoard_title());
		System.out.println("- 작성자 :" + boardVO.getBoard_writer());
		System.out.println("- 내 용 :" + boardVO.getBoard_content());
		System.out.println("- 작성일 :" + boardVO.getBoard_date());
		System.out.println("- 조회수 :" + boardVO.getBoard_cnt());
		System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		System.out.println("작업선택 >>");
		int choice = scan.nextInt();
		
		switch(choice) {
		case 1:   //수정
			updateBoard(no); break;
		case 2:   //삭제
			deleteBoard(no); break;
		case 3:   //리스트로 가기
			return;
			
		}
		
	}
    //게시글을 삭제하는 메서드
    private void deleteBoard(int boardNo) {
      int cnt = service.deleteBoard(boardNo);
      
      if(cnt>0) {
    	  System.out.println(boardNo + "번 글이 삭제되었습니다...");
      }else {
    	  System.out.println(boardNo + "번 글 삭제 실패!!!");
      }
		
	}

	// 게시글을 수정하는 메서드
	private void updateBoard(int boardNo) {
		System.out.println();
		scan.nextLine(); //버퍼 비우기
		
		System.out.println("수정 작업하기");
		System.out.println("---------------------");
		
		System.out.print("- 제 목 : ");
		String newTitle = scan.nextLine();
		
		System.out.print("- 내 용 : ");
		String newContent = scan.nextLine();
		
		//입력 받은 정보와 게시글 번호를 VO객체에 저장한다.
		BoardVO boardVO = new BoardVO();
		boardVO.setBoard_title(newTitle);
		boardVO.setBoard_content(newContent);
		boardVO.setBoard_no(boardNo);
		
		int cnt = service.updateBoard(boardVO);
		if(cnt>0) {
			System.out.println(boardNo + "번 글이 수정되었습니다");
		}else {
			System.out.println(boardNo + "번 글 수정 실패 !!!");
		}
		
		
	}

	//새글을 작성하는 메서드	
    private void insertBoard() {
		System.out.println();
		scan.nextLine(); //버퍼 비우기
		
		System.out.println("새 글 작성하기");
		System.out.println("--------------------------");
		System.out.println("- 제목 : ");
		String title = scan.nextLine();
		
		System.out.println("- 작성자 : ");
		String writer = scan.nextLine();
		
		System.out.println("- 내용 : ");
		String content = scan.nextLine();
		
		//입력 받은 자료를 VO에 저장한다
		BoardVO boardVO = new BoardVO();
		boardVO.setBoard_title(title);
		boardVO.setBoard_writer(writer);
		boardVO.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVO);
		
		if(cnt>0) {
			System.out.println("새 글이 추가 되었습니다.");
		}else {
			System.out.println("새 글 추가 실패 !!!");
		}
		
		
	}

		//게시판 목록을 보여주고 메뉴를 나타내며 사용자가 입력한 메뉴 번호를 반환하는 메서드
    private int displayMenu(String searchword) {
    	System.out.println();
    	
    	// 검색할 단어가 null이면 전체 목록을 가져오고
    	// 검색할 단어가 null이 아니면 해당 검색할 단어를 이용하여 검색한 목록을
    	// 가져와서 출력한다.
    	
    	//게시글 목록 가져오기
    	List<BoardVO> boardList = null;    		
    	if(searchword==null) {
    		boardList = service.getAllBoard();    		
    	}else {
    		boardList = service.getSearchBoard(searchword);
    		
    	}
    	
    	System.out.println("------------------------------------");
    	System.out.println("No\t제목\t작성자\t조회수");
    	
    	if(boardList == null || boardList.size() ==0) {
    		System.out.println("게시글 목록이 하나도 없습니다...");
    	}else {
    		for(BoardVO boardVO : boardList) {
    			System.out.println(boardVO.getBoard_no() + "\t"
    					+ boardVO.getBoard_title() + "\t"
    					+ boardVO.getBoard_writer() + "\t"
    					+ boardVO.getBoard_cnt());
    		}
    	}
    	System.out.println("------------------------------------");
    	System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
    	System.out.println("작업선택 >>");
    	return scan.nextInt();
    
	}

}
