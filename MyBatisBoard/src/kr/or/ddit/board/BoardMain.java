package kr.or.ddit.board;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.service.BoardServiceImpl;
import kr.or.ddit.service.IBoardService;


/*
위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값
		
----------------------------------------------------------

// 시퀀스의 다음 값 구하기
//  시퀀스이름.nextVal


*/
public class BoardMain {

	private IBoardService boardService;

	private Scanner scan = new Scanner(System.in);

	public BoardMain() {
		// boardService = new BoardServiceImpl();  에서 아래 코드로 변경
		boardService = BoardServiceImpl.getInstance();
	}


	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새 글 작성");
		System.out.println("  2. 글 삭제");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 글 검색.");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start() {
		int choice;
		do {
			displayMenu(); // 메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch (choice) {
			case 1: // 새 글 작성
				insertBoard();
				break;
			case 2: // 글 삭제
				deleteBoard();
				break;
			case 3: // 글 수정
				updateBoard();
				break;
			case 4: // 전체 목록 출력
				selectAllBoard();
				break;
			case 5: // 자료검색
				searchBoard();
				break;
			case 6: 
				System.out.println("작업을 마칩니다.");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		} while (choice != 6);
	}

	private void selectAllBoard() {

		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println(" 번호\t제목\t내용\t작성자\t작성날짜");
		System.out.println("--------------------------------------");

		List<BoardVO> boardList = boardService.selectAllBoard(); // 글 정보를 가져옴
		
		if(boardList.size() == 0) {
			System.out.println("해당 글이 존재하지 않습니다.");
		}else {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			for(BoardVO mv : boardList) {
				System.out.println(mv.getBoardNum() + "\t" + mv.getBoardTitle() + "\t" + mv.getBoardContent() + "\t" + mv.getBoardWriter()+ "\t" + formatter.format(mv.getBoardDate()));
			}
		}
		
		System.out.println("--------------------------------------");
		System.out.println("출력 작업 끝.");
	}

	/**
	 * 글을 삭제하기 위한 메서드
	 */
	private void deleteBoard() {

		System.out.println();
		System.out.println("삭제할 글의 번호를 입력하세요.");
		System.out.print("글 번호 >> ");

		int boardNum = scan.nextInt();
		
		
		int cnt = boardService.removeBoard(boardNum);
		
		if (cnt > 0) {
			System.out.println(boardNum + "번 글 삭제 성공~");
		} else {
			System.out.println(boardNum + "번 글 삭제 실패!");
		}

	}

	/**
	 * 글을 수정하기 위한 메서드
	 */
	private void updateBoard() {

		boolean exist = false;

		int boardNum = 0;

		do {
			System.out.println();
			System.out.println("수정할 글 번호를 입력하세요.");
			System.out.print("글 번호 >> ");

			boardNum = scan.nextInt();

			exist = boardService.checkBoard(boardNum);
		
			if (!exist) {
				System.out.println("글 번호가 " + boardNum + "인 글은 존재하지 않습니다.");
				System.out.println("다시 입력해주세요.");
			}
		} while (!exist);

		System.out.print("글 제목 >> ");
		String boardTitle = scan.next();

		System.out.print("글 내용 >> ");
		String bordContent = scan.next();

		scan.nextLine(); // 버퍼 비우기

		BoardVO mv = new BoardVO();
		mv.setBoardNum(boardNum);
		mv.setBoardTitle(boardTitle);
		mv.setBoardContent(bordContent);

		int cnt = boardService.modifyBoard(mv);

		if (cnt > 0) {
			System.out.println(boardNum + "번 글 수정 성공~");
		} else {
			System.out.println(boardNum + "번 글 수정 실패!");
		}
	}

	/**
	 * 새 글을 작성하는 메서드
	 */
	private void insertBoard() {

		
		System.out.println("작성자를 입력하세요.");
		System.out.print("작성자 >> ");
		String bordWriter = scan.next();
		
		System.out.println();
		System.out.println("글 제목을 입력하세요.");
		System.out.print("글 제목 >> ");
		String boardTitle = scan.next();

		System.out.println();
		System.out.println("글 내용을 입력하세요.");
		System.out.print("내용 >> ");
		String bordContent = scan.next();

		scan.nextLine(); // 버퍼 비우기

		BoardVO mv = new BoardVO();
		mv.setBoardWriter(bordWriter);
		mv.setBoardTitle(boardTitle);
		mv.setBoardContent(bordContent);

		int cnt = boardService.registBoard(mv);

		if (cnt > 0) {
			System.out.println(bordWriter + "님의 글 작성 완료~");
		} else {
			System.out.println(bordWriter + "님의 글 작성 실패!");
		}
	}
	
	/**
	 * 글을 검색하기 위한 메서드
	 */
	public void searchBoard() {

		scan.nextLine(); // 버퍼 비우기 System.out.println();
		System.out.println("검색할 검색어를 입력하세요.");
		System.out.print("검색어 >> ");
		String keyWord = scan.nextLine().trim(); // 키워드 생성

		BoardVO mv = new BoardVO();

		mv.setKeyWord(keyWord);

		System.out.println();
		System.out.println("--------------------------------------");
		System.out.println(" 번호\t제목\t내용\t작성자\t작성날짜");
		System.out.println("--------------------------------------");
		
		//mv : BoardVO [boardNum=0, boardTitle=null, boardWriter=null, boardContent=null, keyWord=안녕, boardDate=null]
		List<BoardVO> boardList = boardService.searchBoard(mv);

		if (boardList.size() == 0) {
			System.out.println("해당 검색어 존재하지 않습니다.");

		} else {			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   // yyyy-MM-dd HH:mm:ss
			for (BoardVO mv2 : boardList) {
				System.out.println(mv2.getBoardNum() + "\t" + mv2.getBoardTitle() + "\t" + mv2.getBoardContent() + "\t"
						+ mv2.getBoardWriter() + "\t" + formatter.format(mv2.getBoardDate()));
			}
		}

		System.out.println("--------------------------------------");
		System.out.println("검색 작업 끝.");
	}

	public static void main(String[] args) {
		BoardMain boardObj = new BoardMain();
		boardObj.start();
	}
}
/*
 * 1. 오라클 Driver 설정
 * 
 * JDBC코딩 하려면 이런 interface가 쓰인다. - 이미 만들어진 인터페이스를 쓰기만 하면 된다. - 오라클 Dirver 설정에
 * 필요한 인터페이스 - 아래의 interface를 가지고 코딩하는 것을 JDBC코딩이라고 한다. 1) Connection 2)
 * Statement, PreparedStatement 3) Resultset
 * 
 * 2. Connection 객체를 생성
 * 
 * 
 * 3. Statement, PreparedStatement 객체 생성 - 쿼리 작성 - select는 데이터를 요청 - return 타입은
 * ResultSet 타입이다.
 * 
 * 4. ResultSet 객체의 결과를 받기
 * 
 * 5. 접속 종료 : close()
 */
