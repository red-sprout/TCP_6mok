package sixmok.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import sixmok.model.dao.GameuserDao;
import sixmok.model.vo.Gameuser;
import sixmok.model.vo.History;
import sixmok.model.vo.Nowuser;
import sixmok.model.vo.Room;
import sixmok.service.RoomService;
import sixmok.view.MessageView;
import sixmok.view.OnlineClient;
import sixmok.view.OnlineServer;

public class GameuserController {
	public Nowuser now = null;
	
	public boolean loginGameuser(String userId, String userPwd) {
		Gameuser user = new GameuserDao().loginGameuser(userId, userPwd);

		if (user == null) {
			MessageView.displayFail("로그인 실패");
			return false;
		} else {
			MessageView.displaySuccess("로그인 성공");
			History history = new GameuserDao().searchHistory(userId);
			now = new Nowuser(user, history);
			return true;
		}
 	}


	public void searchHistory(String userId) {
		History history = new GameuserDao().searchHistory(userId);
		
		if (history == null) {
			MessageView.displayFail("전적을 찾을 수 없습니다.");
		} else {
			MessageView.displayHistory(history);
		}
	}
	
	
	public void insertGameuser(String userId, String userPwd, String userName, String phone) {
		Gameuser user = new Gameuser(userId, userPwd, userName, phone);
		
		int result = new GameuserDao().insertGameuser(user);
		
		if (result > 0) {
			MessageView.displaySuccess("회원가입 성공");
		} else {
			MessageView.displayFail("회원가입 실패");
		}
	}
	
	public void updateGameuser(String userId, String userPwd, String userName, String phone) {
		Gameuser user = new Gameuser(userId, userPwd, userName, phone);
		
		int result = new GameuserDao().updateGameuser(user);
		
		if (result > 0) {
			MessageView.displaySuccess("정보 수정 성공");
		} else {
			MessageView.displayFail("정보 수정 실패");
		}
	}
	
	public void deleteGameuser(String inputId, String userId) {
		if (!inputId.equals(userId)) {
			MessageView.displayFail("탈퇴 실패");
			return;
		}
		
		int result = new GameuserDao().deleteGameuser(userId);
		
		if (result > 0) {
			MessageView.displaySuccess("탈퇴 성공");
		} else {
			MessageView.displayFail("탈퇴 실패");
		}
	}
	
	public void updateHistory(int win, int draw, int lose) {
		int result = new GameuserDao().updateHistory(this.nowId(), win, draw, lose);
		
		if (result > 0) {
			MessageView.displaySuccess("전적에 반영되었습니다.");
		} else {
			MessageView.displayFail("전적 반영되지 않았습니다.");
		}
	}
	
	public void selectRoom() {
		ArrayList<Room> list = new RoomService().selectRoom();
		
		if(list.size() == 0) {
			MessageView.displayFail("접속가능한 방이 없습니다.");
		} else {
			MessageView.displayList(list);
		}
	}
	
	public void selectOneRoom(int row) {
		Room room = new RoomService().selectOneRoom(row);
		
		if(room == null) {
			MessageView.displaySuccess("메인메뉴로 돌아갑니다.");
		} else {
			int[] arr = new OnlineClient().play(now, room);
			this.updateHistory(arr[0], arr[1], arr[2]);
		}
	}
	
	public void insertRoom(String roomName) {
		String userName = now.getUser().getUserName();
		String userId = now.getUser().getUserId();
		
		String serverIP = null;
		try {
			serverIP = InetAddress.getLocalHost().getHostAddress(); // 본인의 IP로 설정
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		int port = 8000; // 포트는 8000으로 우선 설정;
		
		Room room = new Room(roomName, userName, userId, serverIP, port);
		
		int result = new RoomService().insertRoom(room);
		
		if (result > 0) {
			MessageView.displaySuccess("상대를 기다리는 중입니다...");
			int[] arr = new OnlineServer().play(now, room);
			this.updateHistory(arr[0], arr[1], arr[2]);
		} else {
			MessageView.displayFail("방 생성 실패");
		}
	}
	
	public void deleteRoom() {
		String userId = now.getUser().getUserId();
		
		int result = new RoomService().deleteRoom(userId);
		
		if (result > 0) {
			MessageView.displaySuccess("게임이 종료되어 방이 삭제되었습니다.");
		}
	}
	
	public String nowId() {
		return this.now.getUser().getUserId();
	}
	
	public String nowName() {
		return this.now.getUser().getUserName();
	}
}
