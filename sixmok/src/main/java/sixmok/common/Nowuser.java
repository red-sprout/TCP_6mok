package sixmok.common;

import sixmok.model.vo.Gameuser;
import sixmok.model.vo.History;

public class Nowuser {
	public static Gameuser user;
	public static History history;

	@Override
	public String toString() {
		return user.getUserName() + "(" + user.getUserId() + ")";
	}
}
