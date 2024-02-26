package sixmok.repository;

public class BoardRepository {
	private static final BoardRepository instance = new BoardRepository();
	
	public BoardRepository() {
	}
	
	public static BoardRepository getInstance() {
		return instance;
	}
	
	public void saveBoardInfo() {
		
	}
}
