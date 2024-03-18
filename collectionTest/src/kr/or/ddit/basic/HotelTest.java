package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class HotelTest {
	Scanner sc = new Scanner(System.in);
	Map<Integer, Room> room = new TreeMap<Integer, Room>();

	public static void main(String[] args) {
		new HotelTest().process();
	}

	public void process() {
		System.out.println("*********************************************\n" 
				+ "       호텔문을 열었습니다. 어서오십시요.\n"
				+ "*********************************************");
		createRoom();
		while (true) {
			printMenu();
			int sel = sc.nextInt();
			switch (sel) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				roomStatus();
				break;
			case 4:
				System.out.println("*********************************************\n"
						+ "       호텔문을 닫았습니다.\n" 
						+ "*********************************************");
				return;
			default:
				System.out.println("잘못 입력");
				return;
			}
		}
	}

	public void createRoom() {
		room.put(201, new Room(201, "싱글룸"));
		room.put(202, new Room(202, "싱글룸"));
		room.put(203, new Room(203, "싱글룸"));
		room.put(204, new Room(204, "싱글룸"));
		room.put(205, new Room(205, "싱글룸"));
		room.put(206, new Room(206, "싱글룸"));
		room.put(207, new Room(207, "싱글룸"));
		room.put(208, new Room(208, "싱글룸"));
		room.put(209, new Room(209, "싱글룸"));

		room.put(301, new Room(301, "더블룸"));
		room.put(302, new Room(302, "더블룸"));
		room.put(303, new Room(303, "더블룸"));
		room.put(304, new Room(304, "더블룸"));
		room.put(305, new Room(305, "더블룸"));
		room.put(306, new Room(306, "더블룸"));
		room.put(307, new Room(307, "더블룸"));
		room.put(308, new Room(308, "더블룸"));
		room.put(309, new Room(309, "더블룸"));

		room.put(401, new Room(401, "스위트룸"));
		room.put(402, new Room(402, "스위트룸"));
		room.put(403, new Room(403, "스위트룸"));
		room.put(404, new Room(404, "스위트룸"));
		room.put(405, new Room(405, "스위트룸"));
		room.put(406, new Room(406, "스위트룸"));
		room.put(407, new Room(407, "스위트룸"));
		room.put(408, new Room(408, "스위트룸"));
		room.put(409, new Room(409, "스위트룸"));
	}

	public void printMenu() {
		System.out.println("--------------------------------------------\n" + "어떤 업무를 하시겠습니까?\r\n"
				+ "1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료\n"
				+ "--------------------------------------------\n선택>> ");
	}

	public void checkIn() {
		System.out.println(" * 201~209 : 싱글룸\n" + " * 301~309 : 더블룸\n" + " * 401~409 : 스위트룸");
		System.out.println("방 번호 입력 >> ");
		int key = sc.nextInt();
		if (!room.containsKey(key)) {
			System.out.println(key + "객실은 존재하지 않습니다");
			return;
		}
		if (room.get(key).getName() == null || room.get(key).getName().isEmpty()) {
			System.out.println("누구를 체크인 하시겠습니까?");
			room.get(key).setName(sc.next());
			System.out.println("체크인이 완료되었습니다");
		} else {
			System.out.println(key + "호 객실은 이미 손님이 있습니다");
		}
	}

	public void checkOut() {
		System.out.println("방 번호 입력 >> ");
		int key = sc.nextInt();
		if (!room.containsKey(key)) {
			System.out.println(key + "객실은 존재하지 않습니다");
			return;
		}
		if (room.get(key).getName() == null || room.get(key).getName().isEmpty()) {
			System.out.println(key + "호는 예약되지 않은 객실입니다");
		} else {
			System.out.println(key + "호 객실의 " + room.get(key).getName() + "님 체크아웃을 완료 하였습니다.");
			room.get(key).setName("");
		}
	}

	public void roomStatus() {
		Set set = room.keySet();
		Iterator it = set.iterator();
		System.out.println("----------------------------------------");
		System.out.println("방 번호\t방 종류\t투숙객 이름");
		System.out.println("----------------------------------------");
		while (it.hasNext()) {
			System.out.println(room.get(it.next()));
		}
		// 2. 방법
//		List<Integer> keyList = new ArrayList(room.keySet()) ;
//		Collections.sort(keyList);
//		for (int i : keyList) {
//			System.out.println(room.get(i));
//		}
	}
}

class Room {
	// 201~209 싱글룸 / 301~309 더블룸 / 401~409 스위트룸
	private int roomNum; // 방번호
	private String roomType; // 방종류
	private String name; // 투숙객 이름

	public Room(int roomNum, String roomType) {
		this.roomNum = roomNum;
		this.roomType = roomType;
		this.name = "";
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return roomNum + "\t" + roomType + "\t" + name;
	}

}
