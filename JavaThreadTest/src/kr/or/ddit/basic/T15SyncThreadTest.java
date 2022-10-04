package kr.or.ddit.basic;

public class T15SyncThreadTest {
	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		WorkerThread th1 = new WorkerThread("1번 스레드", sObj);
		WorkerThread th2 = new WorkerThread("2번 스레드", sObj);
		
		th1.start();
		th2.start();
	}
}

// 공통으로 사용할 객체
class ShareObject {
	private int sum = 0;

	// 동기화 하는 방법1 : 메서드 자체에 동기화 처리하기
	public void add() {
		
		// 동기화 하는 방법2 : 동기화 블럭으로 설정하기1
		// mutex : Mutal exclusion Object(상호배제 : 동시접근 차단)
		//synchronized (this) {
			for (int i = 0; i < 1000000000; i++) {} // 시간때우기용
			int n = sum;
			n += 10;
			sum = n;

		System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		}
	}
//}

//작업을 수행하는 스레드
class WorkerThread extends Thread {
	private ShareObject sObj;

	public WorkerThread(String name, ShareObject sObj) {
		super(name);
		this.sObj = sObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			// 동기화 하는 방법2 : 동기화 블럭으로 설정하기2
			synchronized (sObj) {
				sObj.add();
			}
		}
	}
}
