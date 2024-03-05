package sixmok.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import sixmok.model.vo.Nowuser;
import sixmok.model.vo.Room;
import sixmok.service.SixmokGameService;

public interface GamePlay {
	
	public static final SixmokGameService game = new SixmokGameService();
	
	public static final String INPUT_MESSAGE = "착수할 곳을 입력하세요(예시 : Aa) : ";

	int[] play(Nowuser user, Room room);
	
	void initialTurn(Nowuser user, BufferedReader br, PrintWriter pw) throws IOException;

	void gameAfterInitialTurn(Nowuser user, BufferedReader br, PrintWriter pw) throws IOException;

	void myTurn(PrintWriter pw);

	void opponentTurn(BufferedReader br) throws IOException;

	void win(Nowuser user);

	void lose(Nowuser user);

	String checkPosition(int i);

}