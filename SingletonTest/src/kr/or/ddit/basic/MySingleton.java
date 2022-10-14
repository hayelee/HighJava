package kr.or.ddit.basic;

import sun.applet.Main;

public class MySingleton {
/*
 	싱글톤 패턴 => 객체(인스턴스)를 한 개만 만들어지도록 하는 프로그래밍 방법
 	
 	 - Singleton 클래스를 구성하는 방법
 	 
 	 1. 자기 자신 class의 참조변수를 멤버변수로 선언한다.
 	 (이 변수는 private static으로 지정한다.)
 	 
 	 2. 생성자를 private으로 한다.(enum도 private(생략되어있음))
 	   (외부에서 생성자에 접근하지 못하게 하기 위해서...)
 	         즉, 외부에서 new 키워드를 이용하여 객체생성을 하지 못하게 하기 위해서...)
 	         
 	 3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하기 위한 메서드를 만든다.
 	   (이 메서드의 이름은 보통 getInstance()로 지정한다.)
 	   static메서드는 static변수에만 접근 가능
 */
	 // 자기 자신의 class 참조 값을 저장하는 멤버변수 선언
	private static MySingleton single;
	
	 // 생성자를 private으로 지정한다.
	private MySingleton() {
		System.out.println("생성자 입니다.");
	}
	
	public static MySingleton getInstance() {
		if(single == null) {
			single = new MySingleton();
		}
		return single;
	}
	
	 // 나머지 내용들은 이 클래스로 처리할 내용들을 기술한다.
	public void displayText() {
		System.out.println("안녕하세요. 싱글턴 객체입니다.");
		
	}
}
