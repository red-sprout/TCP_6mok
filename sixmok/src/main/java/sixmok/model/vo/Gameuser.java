package sixmok.model.vo;

import java.sql.Date;

public class Gameuser {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String phone;
	private Date enrollDate;
	
	public Gameuser() {
		super();
	}

	public Gameuser(String userId, String userPwd, String userName, String phone) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
	}

	public Gameuser(int userNo, String userId, String userPwd, String userName, String phone, Date enrollDate) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.phone = phone;
		this.enrollDate = enrollDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return "가입번호 : " + userNo 
				+ "\n 아이디 : " + userId 
				+ "\n 비밀번호 : " + userPwd 
				+ "\n 닉네임 : " + userName
				+ "\n 전화번호 : " + phone
				+ "\n 가입일 : " + enrollDate;
	}
}
