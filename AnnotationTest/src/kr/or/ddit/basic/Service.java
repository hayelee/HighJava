package kr.or.ddit.basic;

import java.io.Serializable;

public class Service implements Serializable {
	
	@PrintAnnotation
	public void method1() throws Exception {
		System.out.println("메서드1에서 출럭되었습니다.");
	}
	
	@PrintAnnotation("%") // value값 하나만 있으면 'value='생략 가능
	public void method2() {
		System.out.println("메서드2에서 출럭되었습니다.");
	}
	
	@PrintAnnotation(value = "#", count = 25) // 이렇게 같이 있으면 'value='생략 불가능
	public void method3() {
		System.out.println("메서드3에서 출럭되었습니다.");
	}
}
