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
	
	public Deck getHand(){
		return hand;
	}
	
}