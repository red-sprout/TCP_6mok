package sixmok.model.vo;

public class Room {
	private String roomName;
	private String userName;
	private String userId;
	private String userIP;
	private int userPort;
	
	public Room() {
		super();
	}

	public Room(String roomName, String userName, String userId, String userIP, int userPort) {
		super();
		this.roomName = roomName;
		this.userName = userName;
		this.userId = userId;
		this.userIP = userIP;
		this.userPort = userPort;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public int getUserPort() {
		return userPort;
	}

	public void setUserPort(int userPort) {
		this.userPort = userPort;
	}

	@Override
	public String toString() {
		return roomName + " - " + userName + "(" + userId + ")";
	}
}
