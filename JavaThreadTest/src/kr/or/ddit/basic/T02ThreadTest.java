package kr.or.ddit.basic;

/**
 * 멀티스레드 프로그램 생성 예제
 */
public class T02ThreadTest {
	public static void main(String[] args) {
		
		// 방법1: Thread 클래스를 상속한 class의 인스턴스를 생성한 후
		//       이 인스턴스의 start()메서드를 호출한다.
		// 부모에 있는 걸 상속 받아서 나도 Thread가 됨
		// 자바는 하나 상속하면 끝! 다중상속이 안됨! 하나 클래스를 상속하면 Thread를 상속받을 수 없음 그래서 2번째 방법 사용
		Thread th1 = new MyThread1();
		th1.run();
		
		// 방법2: Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후
		//       이 인스턴스를 Thread 객체의 인스턴스를 생성할 때 생성자의 
		//       파라미터로 넘겨준다.
		//       이렇게 생성된 인스턴스의 start()메서드를 호출한다.
		Runnable r = new MyThread2();
		Thread th2 = new Thread(r);
		th2.run();
		
		// 방법3: 익명클래스를 이용하는 방법
		//       Runnable인터페이스를 구현한 익명클래스를 Thread
		//       인스턴스를 생성할 때 매개변수로 넘겨준다.
		// 자주쓰지 않는 클래스는 따로 도출하지 않고 그냥 사용하니까!
		Thread th3 = new Thread(new Runnable() {

			@Override
			public void run() {
				
				for(int i = 1; i <= 200; i++) {
					System.out.print("@");
					
					try {
						// Thread.sleep(시간) => 주어진 시간만큼 잠시 멈춘다.
						// 시간은 밀리세컨드 단위
						// (즉, 1000ms = 1초)
						Thread.sleep(100); // 0.1초
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}); 
		
		th3.run();	
	}
}


class MyThread1 extends Thread {
	
	@Override
	public void run() {

		for(int i = 1; i <= 200; i++) {
			System.out.print("*");
			
			try {
				// Thread.sleep(시간) => 주어진 시간만큼 잠시 멈춘다.
				// 시간은 밀리세컨드 단위
				// (즉, 1000ms = 1초)
				Thread.sleep(100); // 0.1초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		for (int i = 1; i <= 200; i++) {
			System.out.print("$");

			try {
				// Thread.sleep(시간) => 주어진 시간만큼 잠시 멈춘다.
				// 시간은 밀리세컨드 단위
				// (즉, 1000ms = 1초)
				Thread.sleep(100); // 0.1초
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
