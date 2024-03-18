package kr.or.ddit.basic;

// wait(), notify()를 이용해서 두 쓰레드가 번갈아 한번씩 실행되는 예제 
public class ThreadTest18 {
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();

		ThreadA th1 = new ThreadA(workObj);
		ThreadB th2 = new ThreadB(workObj);

		th1.start();
		th2.start();
	}
}

// 공통으로 사용할 class
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA() 메서드 실행중...");
		notify();

		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

	public synchronized void methodB() {
		System.out.println("methodB() 메서드 작업중...");

		notify();

		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}

// WorkObject의 methodA()메서드만 호출하는 쓰레드
class ThreadA extends Thread {
	private WorkObject workObj;

	// 생성자
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodA();
		}

		synchronized (workObj) {
			workObj.notify(); // 동기화 영역에서만 실행가능함
		}
	}
}

// WorkObject의 methodB()메서드만 호출하는 쓰레드
class ThreadB extends Thread {
	private WorkObject workObj;

	// 생성자
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}

	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			workObj.methodB();
		}
		synchronized (workObj) {
			workObj.notify(); // 동기화 영역에서만 실행가능함
		}
	}
}
