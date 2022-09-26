package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import sun.applet.Main;

public class T11Hotel {
	private Scanner scan;
	private Map<Integer, Room> roomBookMap;

	public T11Hotel() {
		scan = new Scanner(System.in);
		roomBookMap = new HashMap<Integer, Room>();
	}

	// 메뉴 출력
	public void displayMenu() {
		System.out.println();
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.print("메뉴선택 => ");
	}

	// 프로그램 시작
	public void hotelBookStart() {
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************");
		while (true) {
			displayMenu(); // 메뉴 출력
			int menuNum = scan.nextInt(); // 메뉴번호 입력

			switch (menuNum) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				State();
				break;
			case 4:
				System.out.println("**************************");
				System.out.println("호텔 문을 닫았습니다.");
				System.out.println("**************************");
				return;
			default:
				System.out.println("잘못 입력했습니다.");
			}
		}
	}

	// 체크인
	private void checkIn() {
		System.out.println();
		System.out.println("어느방에 체크인 하시겠습니까?");

		System.out.print("방 번호 입력 => ");
		int num = scan.nextInt();

		// 이미 예약된 방인지 체크하기
		if (roomBookMap.get(num) != null) {
			System.out.println(num + "방에는 이미 사람이 있습니다.");
			return;
		}
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");

		System.out.print("이름 입력 => ");
	 	String name = scan.next();

		Room r = new Room(num, name);

		roomBookMap.put(num, r);
		System.out.println("체크인되었습니다.");

	}

	// 체크아웃
	private void checkOut() {
		System.out.println();
		System.out.println("어느방을 체크아웃 하시겠습니까?");

		System.out.println("방번호 입력 => ");
		int num = scan.nextInt();

		if (roomBookMap.remove(num) == null) {
			System.out.println(num + "방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println("체크아웃 되었습니다.");
		}
	}

	// 룸상태
	private void State() {
		Set<Integer> keySet = roomBookMap.keySet();

		if (keySet.size() == 0) {
			System.out.println("체크인 되어있는 방이 없습니다.");
		} else {
			Iterator<Integer> it = keySet.iterator();

			while (it.hasNext()) {

				Integer name = it.next();

				Room r = roomBookMap.get(name);

				System.out.println("방번호: " + r.getNum() + ", 투숙객: " + r.getName());
			}
		}
	}

	public static void main(String[] args) {
		new T11Hotel().hotelBookStart();
	}
}

//객살정보 저장하기 위한 VO 클래스
class Room {
	private int num;
	private String name;

	public Room(int num, String name) {
		super();
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Room [num=" + num + ", name=" + name + "]";
	}
}