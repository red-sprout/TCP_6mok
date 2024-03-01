package sixmok.view;

import sixmok.model.vo.Gameuser;
import sixmok.model.vo.History;

public class MessageView {
	private static final String SUCCESS = "\n[INFO] ";
	private static final String FAIL = "\n[ERROR] ";
	
	public static void displaySuccess(String message) {
		System.out.println(SUCCESS + message);
	}
	
	public static void displayFail(String message) {
		System.out.println(FAIL + message);
	}
	
	public static void displayUser(Gameuser user) {
		System.out.println(SUCCESS);
		System.out.println(user.toString());
	}
	
	public static void displayHistory(History history) {
		System.out.println(SUCCESS);
		System.out.println(history.toString());
	}
}
