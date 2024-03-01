package sixmok.common;

public enum Dol {
	BLACK('●'),
	WHITE('○'),
	BLANK('·');
	
	private char dol;
	
	Dol(char dol) {
		this.dol = dol;
	}
	
	public char getDol() {
		return dol;
	}
}
