package sixmok.view;

import java.util.Scanner;

import sixmok.controller.GameuserController;

public class GameuserMenu {
	private Scanner sc = new Scanner(System.in);
	private GameuserController gc = new GameuserController();
	
	private static String userId;
	private static String userName;
	
	public void beforeMainMenu() {
		int menu = 0;
		while(true) {
			System.out.println("===육목 게임===");
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("0. 프로그램 종료");
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
		
		System.out.println("\n" + userName + "님 환영합니다!");
		while(true) {
			System.out.println("\n===메인 메뉴===");
			System.out.println("1. 오프라인 플레이");
			System.out.println("2. 온라인 플레이");
			System.out.println("3. 유저정보 수정");
			System.out.println("4. 전적 조회");
			System.out.println("5. 회원 탈퇴");
			System.out.println("0. 로그아웃");
			menu = inputMenu();
			
			switch(menu) {
			case 1:
				new Offline().play();
				break;
			case 2:
				new Online().play();
				break;
			case 3:
				MessageView.displaySuccess("변경 전 다시 로그인합니다");
				validateUser(false);
				break;
			case 4:
				searchMenu();
				break;
			case 5:
				deleteMenu();
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
		System.out.println("===로그인===");
		
		String userId = inputId();
		System.out.print("패스워드 : ");
		String userPwd = sc.nextLine();
		
		return gc.loginGameuser(userId, userPwd);
	}
	
	public void insertMenu() {
		System.out.println("===회원 가입===");
		
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
		System.out.println("===유저정보 수정===");
		System.out.print("변경 패스워드 : ");
		String userPwd = sc.nextLine();
		System.out.print("변경 닉네임 : ");
		String userName = sc.nextLine();
		System.out.print("변경 전화번호(-제외) : ");
		String phone = sc.nextLine();
		
		gc.updateGameuser(userId, userPwd, userName, phone);
	}
	
	public void searchMenu() {
		System.out.println("===전적 조회===");
		String userId = inputId();
		
		gc.searchHistory(userId);
	}
	
	public void deleteMenu() {
		System.out.println("===회원 탈퇴===");
		System.out.println("탈퇴 전 아이디를 다시 입력해주기 바랍니다.");
		String inputId = inputId();
		
		gc.deleteGameuser(inputId, userId);
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
}
