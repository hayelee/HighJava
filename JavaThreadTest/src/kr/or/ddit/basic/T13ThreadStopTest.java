package kr.or.ddit.basic;

public class T13ThreadStopTest {
	public static void main(String[] args) {

		/*
		 * ThreadStopEx1 th1 = new ThreadStopEx1(); th1.start();
		 * 
		 * try { Thread.sleep(1000); 
		 * 
		 * }catch(InterruptedException ex) {
		 * 
		 * ex.printStackTrace(); } // th1.stop(); //이런 메소드가 존재하긴 하지만, 앞으로 코딩할때 사용하지 말라는
		 * 의미 (비추) th1.setStop(true);
		 */

		ThreadStopEx2 th2 = new ThreadStopEx2();
		th2.start();

		try {
			Thread.sleep(1000);

		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		th2.interrupt();
	}
}

class ThreadStopEx1 extends Thread {
	
	private boolean stop;

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드 처리중...");
		}
		System.out.println("자원 정리중...");
		System.out.println("실 행 종 료 . . .");
	}
}

/**
 * interrupt() 메서드를 이용하여 스레드를 멈추게 하는 방법
 */
class ThreadStopEx2 extends Thread {
	
	@Override
	public void run() {
	/*/ 방법1 => sleep()메서드나 join()메서드 등을 사용했을 때 
	//         interrupt()메서드를 호출하면 InterruptedException
	//         이 발생한다.
		try {
			while(true){
				System.out.println("스레드 처리중...");
				Thread.sleep(1);
			}
		}catch(InterruptedException ex) {
			
		}*/
		
	// 방법2 => 인터럽트가 걸렸는지 검사하는 방법
	while(true) {
		System.out.println("스레드 처리중. . .");
		
		/*// 검사방법 1 => 스레드의 인스턴스 객체용 메서드를 이용하는 방법
		if(this.isInterrupted()) {
			System.out.println("인스턴스용 isInterrupter()");
			break;
		}*/
		
		// 검사방법2 => 스레드의 정적 메서드를 이용하는 방법
		if(this.interrupted()) {
			System.out.println("정적 메서드 interrupted()");
			break;
		}
	}
		System.out.println("자 원 정 리 중~~!");
		System.out.println("실 행 종 료 ~~!");
	}
}