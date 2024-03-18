package kr.or.ddit.basic;
//쓰레드의 상태를 출력하는 예제

public class ThreadTest09 {
	public static void main(String[] args) {
		StatePrintThread th = new StatePrintThread(new TargetThread());
		th.start();
	}
}

//쓰레드 상태의 감시 대상이 되는 쓰레드
class TargetThread extends Thread{
		@Override
		public void run() {
			for (long i = 1L; i <=20_000_000_000L; i++) {
				long k = i+1;
			}
			
			try { //일시정지 상태 만들기
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
			for (long i = 1L; i <=20_000_000_000L; i++) {
				long k = i+1;
			}
		}
}

//TargetThread의 상태값을 구해서 출력하는 쓰레드
class StatePrintThread extends Thread{
	private TargetThread target;

	public StatePrintThread(TargetThread target) {
		super();
		this.target = target;
	}
	
	public void run() {
		while(true) {
			//TargetThread의 상태값 구하기
			Thread.State state = target.getState();
			
			System.out.println("TargetThread의 상태값: "+state);
			if(state==Thread.State.NEW) {// 쓰레드의 상태가 new상태이면 ...
				target.start();
				
			}
			//쓰레드의 상태가 종료 상태이면...
			if(state==Thread.State.TERMINATED) {
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
}
