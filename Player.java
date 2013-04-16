package Java;

import java.util.*;

public class Player {
	private Deck hand;
	
	public Player(){
		hand = new Deck();
		hand.clearDeck();
	}
	
	public void addCard(Card a){
		hand.addCard(a);
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 0e7fa454d5c89459996fcf00318764e4dd7652c9
	public void removeCard(Card a){
		hand.takeCardOutOfDeck(a);
	}
	
<<<<<<< HEAD
=======
>>>>>>> 30bb20627c315b8353fc364794ea9b7b2f8623df
=======
>>>>>>> 0e7fa454d5c89459996fcf00318764e4dd7652c9
	public Deck getHand(){
		return hand;
	}
	
}