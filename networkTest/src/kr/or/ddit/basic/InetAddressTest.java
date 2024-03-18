package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException { // Execption이 발생하면 main으로 던져서 JVM이 처리함
		// InetAddress 클래스 ==> IP주소를 다루기 위한 클래스
		// www.naver.com의 IP정보 가져오기
		InetAddress nip = InetAddress.getByName("www.naver.com"); // 예외처리 필수
		System.out.println("Host Name : " + nip.getHostName());
		System.out.println("Host Address : " + nip.getHostAddress());
		System.out.println("nip : " + nip.toString());
		
		// 자신의 컴퓨터의 IP정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("\n\n내 컴의 HostName : " + localIp.getHostName());
		System.out.println("내 컴의 HostAddress : " + localIp.getHostAddress());
		
		// IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] ipArr = InetAddress.getAllByName("www.naver.com");
		for (InetAddress ip : ipArr) {
			System.out.println(ip.toString());
		}
	}
}
