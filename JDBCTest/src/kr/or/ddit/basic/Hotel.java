package kr.or.ddit.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import kr.or.ddit.util.JDBCUtil;
/*
문제)

호텔 운영을 관리하는 프로그램 작성.(DB 이용)
 - 키값은 방번호 
 
실행 예시)

**************************
호텔 문을 열었습니다.
**************************

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 101 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 홍길동 <-- 입력
체크인 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 성춘향 <-- 입력
체크인 되었습니다

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향
방번호 : 101, 투숙객 : 홍길동

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
체크아웃 되었습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 1 <-- 입력

어느방에 체크인 하시겠습니까?
방번호 입력 => 102 <-- 입력

누구를 체크인 하시겠습니까?
이름 입력 => 허준 <-- 입력
102방에는 이미 사람이 있습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 2 <-- 입력

어느방을 체크아웃 하시겠습니까?
방번호 입력 => 101 <-- 입력
101방에는 체크인한 사람이 없습니다.

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 3 <-- 입력

방번호 : 102, 투숙객 : 성춘향

*******************************************
어떤 업무를 하시겠습니까?
1.체크인  2.체크아웃 3.객실상태 4.업무종료
*******************************************
메뉴선택 => 4 <-- 입력

**************************
호텔 문을 닫았습니다.
**************************


 호텔운영 프로그램 테이블 생성 스크립트 
create table hotel_mng (
    room_num number not null,  -- 방번호
    guest_name varchar2(10) not null -- 투숙객 이름
);

 */


public class Hotel {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.print("메뉴선택 => ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void hotelBookStart() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		while (true) {
			displayMenu(); // 메뉴 출력
			int menuNum = scan.nextInt(); // 메뉴번호 입력

			switch (menuNum) {
			case 1:  // 체크인
				checkIn(); 
				break;
			case 2:  // 체크아웃
				checkOut();
				break;
			case 3:  // 객실상태
				State();
				break;
			case 4:  // 작업 끝
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				return;
			default:
				System.out.println("잘못 입력했습니다.");
			}
		}
	}

	/**
	 * 체크인 하는 메서드
	 */
	private void checkIn() {
		
		boolean exist = false;
		
		int roomNum = 0;
		String memName = "";
		
		
		do {
			System.out.println();
			System.out.println("어느방에 체크인 하시겠습니까?");

			System.out.print("방 번호 입력 => ");
			roomNum = scan.nextInt();

			System.out.println();
			System.out.println("누구를 체크인 하시겠습니까?");

			System.out.print("이름 입력 => ");
		 	memName = scan.next();

		 	scan.nextLine();
		 	
			exist = checkMember(roomNum);
			
		// 이미 예약된 방인지 체크하기
			if(exist) {
			System.out.println(roomNum + "방에는 이미 사람이 있습니다.");
			return;
			}
		}while(exist);
		
	 	try {
	 		conn = JDBCUtil.getConnection(); // 커넥션 가져오기
	 		String sql = " insert into hotel_mng"
	 				+ " (room_num, guest_name)"
	 				+ " values (?, ?)";
	 		pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			pstmt.setString(2, memName);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크인되었습니다.");
			}else {
				System.out.println("체크인 실패!");
			}
			
	 	}catch(SQLException ex){
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	private boolean checkMember(int roomNum) {
		
		boolean exist = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " select count(*) as cnt " 
			+ " from hotel_mng " 
			+ " where room_num = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, roomNum);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			while(rs.next()) { // 한 번 도니까 while 안쓰고 if 써도 됨
				cnt = rs.getInt("cnt");
			}
			
			if(cnt > 0) {
				exist = true;
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		
		return exist; // 중복 안나는 것
	}

	/**
	 * 체크아웃을 위한 메서드
	 */
	private void checkOut() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");

		System.out.println("방번호 입력 => ");
		
		String roomNum = scan.next();

		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " delete from hotel_mng where room_num = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, roomNum);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크아웃 되었습니다.");
			} else {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
			}	
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}
	
	/**
	 * 룸상태 출력을 위한 메서드
	 */
	private void State() {

		try {
			conn = JDBCUtil.getConnection();

			String sql = " select * from hotel_mng";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int roomNum = rs.getInt("room_num");
				String memName = rs.getString("guest_name");

				System.out.println("방번호: " + roomNum + ", 투숙객: " + memName);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	public static void main(String[] args) {
		new Hotel().hotelBookStart();
	}
}