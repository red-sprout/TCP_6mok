package sixmok.model.vo;

public class History {
	private String userId;
	private int win;
	private int draw;
	private int lose;
	
	public History() {
		super();
	}

	public History(String userId, int win, int draw, int lose) {
		super();
		this.userId = userId;
		this.win = win;
		this.draw = draw;
		this.lose = lose;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	@Override
	public String toString() {
		return "아이디 : " + userId 
				+ "\n승리 : " + win 
				+ "\n무승무 : " + draw 
				+ "\n패배 : " + lose;
	}
	
}
