package sixmok.view;

import java.util.Scanner;

import sixmok.common.Decoration;
import sixmok.controller.SixmokController;

public class GameuserMenu {
	private Scanner sc = new Scanner(System.in);
	private SixmokController gc = new SixmokController();
	
	private static String userId;
	private static String userName;
	
	public void beforeMainMenu() {
		int menu = 0;
		while(true) {
			System.out.println(Decoration.beforeMainMenu);
			System.out.println("1. 로그인\n");
			System.out.println("2. 회원 가입\n");
			System.out.println("0. 프로그램 종료\n");
			menu = inputMenu();
			switch(menu) {
			case 1:
				validateUser(true);
				break;
			case 2:
				insertMenu();
				break;
			case 0:
				MessageView.displaySuccess("프로그램을 종료합니다.");
				return;
			default: 
				MessageView.displayFail("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}

	public void mainMenu() {
		int menu = 0;
		
		userId = gc.nowId();
		userName = gc.nowName();
		
		System.out.println("\n");
		System.out.println(Decoration.event(userName + "님 환영합니다!"));
		while(true) {
			System.out.println(Decoration.title("메인 메뉴"));
			System.out.println("1. 오프라인 플레이\n");
			System.out.println("2. 온라인 플레이\n");
			System.out.println("3. 유저정보 수정\n");
			System.out.println("4. 전적 조회\n");
			System.out.println("5. 회원 탈퇴\n");
			System.out.println("0. 로그아웃\n");
			menu = inputMenu();
			
			switch(menu) {
			case 1:
				new Offline().play();
				break;
			case 2:
				waitingRoom();
				break;
			case 3:
				MessageView.displaySuccess("변경 전 다시 로그인합니다");
				validateUser(false);
				break;
			case 4:
				searchMenu();
				break;
			case 5:
				if(deleteMenu()) return;
				break;
			case 0:
				MessageView.displaySuccess("로그아웃 성공");
				return;
			default:
				MessageView.displayFail("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void validateUser(boolean isFirstPage) {
		boolean isValidUser = loginMenu();
		
		if(!isValidUser) 
			return;
		else if(isFirstPage) 
			mainMenu();
		else
			updateMenu();
	}
	
	public boolean loginMenu() {
		System.out.println(Decoration.title("로그인"));
		
		String userId = inputId();
		System.out.print("패스워드 : ");
		String userPwd = sc.nextLine();
		
		return gc.loginGameuser(userId, userPwd);
	}
	
	public void insertMenu() {
		System.out.println(Decoration.title("회원 가입"));
		
		String userId = inputId();
		System.out.print("패스워드 : ");
		String userPwd = sc.nextLine();
		System.out.print("닉네임 : ");
		String userName = sc.nextLine();
		System.out.print("전화번호(-제외) : ");
		String phone = sc.nextLine();
		
		gc.insertGameuser(userId, userPwd, userName, phone);
	}
	
	public void updateMenu() {
		System.out.println(Decoration.title("유저정보 수정"));
		System.out.print("변경 패스워드 : ");
		String userPwd = sc.nextLine();
		System.out.print("변경 닉네임 : ");
		String userName = sc.nextLine();
		System.out.print("변경 전화번호(-제외) : ");
		String phone = sc.nextLine();
		
		gc.updateGameuser(userId, userPwd, userName, phone);
	}
	
	public void searchMenu() {
		System.out.println(Decoration.title("전적 조회"));
		String userId = inputId();
		
		gc.selectHistoryByUserId(userId);
	}
	
	public boolean deleteMenu() {
		System.out.println(Decoration.title("회원 탈퇴"));
		System.out.println("탈퇴 전 아이디를 다시 입력해주기 바랍니다.");
		String inputId = inputId();
		
		return gc.deleteGameuser(inputId, userId);
	}

	public String inputId() {
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		return userId;
	}
	
	public int inputMenu() {
		int result = 0;
		try {
			System.out.print("원하시는 메뉴 번호를 입력하세요 : ");
			result = sc.nextInt();
			sc.nextLine();
			return result;
		} catch(Exception e) {
			sc.nextLine();
			return -1;
		}
	}
	
	public void waitingRoom() {
		int menu = 0;
		
		while(true) {
			System.out.println(Decoration.title("대기 메뉴"));
			System.out.println("1. 참여 가능한 방\n");
			System.out.println("2. 방 만들기\n");
			System.out.println("0. 메인 메뉴\n");
			
			menu = inputMenu();
			
			switch(menu) {
			case 1:
				findRoom();
				return;
			case 2:
				makeRoom();
				gc.deleteRoom();
				return;
			case 0:
				MessageView.displaySuccess("메인 메뉴로 돌아갑니다.");
				return;
			default:
				MessageView.displayFail("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void findRoom() {
		System.out.println(Decoration.title("방 목록"));
		gc.selectRoom();
		System.out.println("\n(메인 메뉴로 돌아가려면 숫자 제외 아무거나 입력하세요.)\n");
		
		int menu = inputMenu();
		
		gc.selectOneRoom(menu);
	}
	
	public void makeRoom() {
		System.out.println(Decoration.title("방 만들기"));
		System.out.print("방 이름을 입력하세요 : ");
		String roomName = sc.nextLine();
		gc.insertRoom(roomName);
	}
}
