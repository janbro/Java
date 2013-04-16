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
	public void removeCard(Card a){
		hand.takeCardOutOfDeck(a);
	}
	
=======
>>>>>>> 30bb20627c315b8353fc364794ea9b7b2f8623df
	public Deck getHand(){
		return hand;
	}
	
}