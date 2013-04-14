package Java;

import java.util.*;

public class Player {
	private ArrayList<Card> hand;
	
	public Player(){
		hand = new ArrayList<Card>();
	}
	
	public void addCard(Card a){
		hand.add(a);
	}
	
	public ArrayList<Card> getHand(){
		return hand;
	}
	
}