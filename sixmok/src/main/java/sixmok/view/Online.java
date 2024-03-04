package sixmok.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import sixmok.model.vo.Nowuser;
import sixmok.model.vo.Room;

public interface Online {

	int[] play(Nowuser user, Room room);

	void gameAfterInitialTurn(Nowuser user, BufferedReader br, PrintWriter pw) throws IOException;

	void initialTurn(Nowuser user, BufferedReader br, PrintWriter pw) throws IOException;

	void myTurn(PrintWriter pw);

	void opponentTurn(BufferedReader br) throws IOException;

	void win(Nowuser user);

	void lose(Nowuser user);

	String checkPosition(int i);

}