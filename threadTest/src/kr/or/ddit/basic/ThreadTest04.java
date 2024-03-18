package kr.or.ddit.basic;
/*
 	1~20억까지의 합계를 구하는 프로그램을 
 	하나의 쓰레드가 단독으로 처리할 때와
 	여러개의 쓰레드가 협력해서 처리할 떄의 결과 시간을 비교해 보자
 */
public class ThreadTest04 {
	public static void main(String[] args) {
       // 단독으로 처리하느 쓰레드
		SumThread sm = new SumThread(1L, 2_000_000_000L);
		
		//협력해서 처리할 쓰레드들...
		SumThread[] smArr = new SumThread[] {
				new SumThread(         1L, 500_000_000L), 
				new SumThread(500_000_001L, 1_000_000_000L), 
				new SumThread(1_000_000_001L, 1_500_000_000L), 
				new SumThread(1_500_000_001L, 2_000_000_000L), 
		};
		//단독으로 처리하기
		long startTime = System.currentTimeMillis();
		sm.start();
		try {
			sm.join();
			// join() 현재 위치에서 대상이되는 쓰레드가 (변수 th)가     
			// 끝날때 까지 기다린다
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		System.out.println("단독으로 처리했을때 의 경과시간 : "+ (endTime-startTime));
		System.out.println();
		System.out.println();
		//------------------------------------------------------------------//
		//여러 쓰레드가 협력해서 처리하기
		startTime = System.currentTimeMillis();
		for (int i = 0; i < smArr.length; i++) {
			smArr[i].start();
		}
		for (SumThread th : smArr) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		endTime = System.currentTimeMillis();
		
		System.out.println("여러쓰레드가 협력해서 처리했을때 의 경과시간 : "+ (endTime-startTime));
		
	}
}


class SumThread extends Thread{
	
	private long start;
	private long end;
	
	
	
	public SumThread(long start, long end) { //생성자 초기화를 시켜야한다
		super();
		this.start = start;
		this.end = end;
	}



	@Override
	public void run() {
		long sum = 0L;
		for(long i=start; i<=end; i++ ) {
			sum =+i;
		}
		System.out.println(start+"부터"+end+"까지의 합계"+sum);
	}
	
}



