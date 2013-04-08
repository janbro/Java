package Java;
import java.util.Scanner;
public class Eggroll implements Play {
	private static Scanner scanner = new Scanner(System.in);
	private static int playerTotal,dealerTotal;
	private static Deck deck;
	private static String input;
	public void explainRules() {
		System.out.println("Try harder...");
	}
	public void setUp() {
		deck = new Deck();
		deck.shuffle();
		playerTotal = dealerTotal = 0;
	}
	public void playGame() {
		
	}
	public void gameOver(String winner) {
		
	}
}
