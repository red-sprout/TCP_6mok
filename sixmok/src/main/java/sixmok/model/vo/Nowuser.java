package sixmok.model.vo;

import java.io.Serializable;

import sixmok.common.Dol;

@SuppressWarnings("serial")
public class Nowuser implements Serializable{
	private Dol dol;
	private Gameuser user;
	private History history;
	
	public Nowuser() {
		super();
	}

	public Nowuser(Gameuser user, History history) {
		super();
		this.user = user;
		this.history = history;
	}

	public Gameuser getUser() {
		return user;
	}

	public void setUser(Gameuser user) {
		this.user = user;
	}

	public History getHistory() {
		return history;
	}

	public void setHistory(History history) {
		this.history = history;
	}

	public Dol getDol() {
		return dol;
	}

	public void setDol(Dol dol) {
		this.dol = dol;
	}

	@Override
	public String toString() {
		return  user.getUserName() + "(" + user.getUserId() + ")";
	}
}
