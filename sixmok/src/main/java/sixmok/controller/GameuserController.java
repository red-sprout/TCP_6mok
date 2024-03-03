package sixmok.controller;

import sixmok.model.dao.GameuserDao;
import sixmok.model.vo.Gameuser;
import sixmok.model.vo.History;
import sixmok.model.vo.Nowuser;
import sixmok.view.MessageView;

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
	
	public String nowId() {
		return this.now.getUser().getUserId();
	}
	
	public String nowName() {
		return this.now.getUser().getUserName();
	}
}
