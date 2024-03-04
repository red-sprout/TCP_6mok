package sixmok.common;

public class Decoration {
	public static String beforeMainMenu = "\n" 
			+ "========================================" + "\n"
			+ "|······································|" + "\n"
			+ "|·····●●●●●●●●●··········○○○○○○○○○·····|" + "\n"
			+ "|····●·········●·········○·······○·····|" + "\n"
			+ "|····●·········●·········○·······○·····|" + "\n"
			+ "|·····●●●●●●●●●··········○○○○○○○○○·····|" + "\n"
			+ "|····●●●●●●●●●●●·············○·········|" + "\n"
			+ "|······●·····●··········○○○○○○○○○○○····|" + "\n"
			+ "|······●·····●·························|" + "\n"
			+ "|·····●●●●●●●●●··········○○○○○○○○○·····|" + "\n"
			+ "|·············●··················○·····|" + "\n"
			+ "|·············●··················○·····|" + "\n"
			+ "|······································|" + "\n"
			+ "========================================" + "\n";
	
	public static String title(String title) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n").append("========================================").append("\n"); // = : 40개
		
		for(int i = 0; i <= 20 - title.length(); i++) {
			sb.append(" ");
		}
		sb.append(title);
		for(int i = 0; i <= 20 - title.length(); i++) {
			sb.append(" ");
		}
		
		sb.append("\n").append("========================================").append("\n");
		
		return sb.toString();
	}
	
	public static String event(String msg) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("****************************************").append("\n"); // = : 40개
		
		for(int i = 0; i <= 22 - msg.length(); i++) {
			sb.append(" ");
		}
		sb.append(msg);
		for(int i = 0; i <= 22 - msg.length(); i++) {
			sb.append(" ");
		}
		
		sb.append("\n").append("****************************************");
		
		return sb.toString();
	}
}
