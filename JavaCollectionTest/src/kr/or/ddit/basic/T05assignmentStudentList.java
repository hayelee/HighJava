package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class T05assignmentStudentList {

	public static void main(String[] args) {
		//학생클래스
		List<Student> memList = new ArrayList<Student>();

		memList.add(new Student("202205", "농부", 85, 90, 80));
		memList.add(new Student("202209", "새감자", 70, 90, 75));
		memList.add(new Student("202224", "왕감자", 65, 80, 70));
		memList.add(new Student("202245", "홍감자", 80, 60, 80));
		memList.add(new Student("202213", "수감자", 50, 85, 60));
		memList.add(new Student("202215", "통감자", 50, 85, 60));

		Student.Ranking(memList); // 랭킹 구하기

		System.out.println("정렬 전: ");
		for (Student mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-----------------------------------------");

		// 학번으로 오름차순
		Collections.sort(memList);

		System.out.println("학번으로 오름차순 정렬 : ");
		for (Student mem : memList) {
			System.out.println(mem);
		}
		System.out.println("-----------------------------------------");

		// 총점으로 내림차순
		Collections.sort(memList, new Totalsort());
		System.out.println("총점으로 내림차순 정렬: ");
		for (Student str : memList) {
			System.out.println(str);
		}
		System.out.println("=====================================");
	}
}

class Student implements Comparable<Student> {
	private String num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private int rank;

	public Student(String num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = kor + eng + math;
	}

	// 등수 구하기
	public static void Ranking(List<Student> memList) {
		for (Student mem1 : memList) { // 등수를 구할 기준 자료
			int rank = 1;
			for (Student mem2 : memList) { // 비교할 자료
				if (mem1.getTotal() < mem2.getTotal()) {
					rank++;
				}
			}
			mem1.setRank(rank);
		}
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
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

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [학번=" + num + ", 이름=" + name + ", 국어점수=" + kor + ", 영어점수=" + eng + ", 수학점수=" + math + ", 총점="
				+ total + ", 등수=" + rank + "]";
	}

	// 학번 기준 오름차순 설정
	@Override
	public int compareTo(Student mem) {
		return this.getNum().compareTo(mem.getNum());
	}
}

// 총점의 역순으로 정렬하는 부분
// 총점이 같으면 학번의 내림차순으로 정렬
class Totalsort implements Comparator<Student> {  //내가만든객체가 아니지만 내가 원하는 방식으로 정렬하고 싶을때

	@Override
	public int compare(Student stu1, Student stu2) {
		if (stu1.getTotal() > stu2.getTotal()) {
			return -1;
		} else if (stu1.getTotal() == stu2.getTotal()) {
			return stu1.getNum().compareTo(stu2.getNum()) * -1; // 총점이 같으면 학번의 내림차순
		} else {
			return 1;
		}
	}
}