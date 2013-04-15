package Java;

import java.util.*;

public class Deck {
	private ArrayList<Card> cards;
	
	public boolean isEmpty(){ //Checks if deck is empty
		return cards.isEmpty();
	}
	
	public ArrayList<Card> getCards(){ //Return ArrayList of Cards in deck
		return cards;
	}
	
	public Deck(){
		cards=new ArrayList<Card>();
		String[] standard=BlackJack.getCards(); //Don't understand why getCards() is in blackjack.class, but as long as it works...
		for(String a:standard)
			cards.add(new Card(a));
	}
	
	public boolean hasCard(Card a){
		for(Card b:cards){
			if(a.similarRank(b)&&a.similarSuit(b))
				return true;
		}return false;
	}
	
	public void addCard(Card a){
		cards.add(a);
	}
	
	public void shuffle(){ //Java ArrayList shuffle
		Collections.shuffle(cards);
	}
	public void takeCardOutOfDeck(Card a){ //Takes specified card out of deck
		for(int i=0;i<cards.size();i++)
			if(a.equals(cards.get(i))){
				cards.remove(i);
				return;
			}
	}
	
	public void clearDeck(){ //Clears Entire Deck
		cards.clear();
	}
	
	public Card deal(){ //Deals a card, returns Card
		Card a=cards.get(0);
		takeCardOutOfDeck(a);
		return a;
	}
	
	public int dealRaw(){ //Deals a card, returns cards value(blackjack)
		Card a=cards.get(0);
		takeCardOutOfDeck(a);
		System.out.println(a);
		return a.getValue();
	}
	public int size(){ //Returns how many cards are left in deck
		return cards.size();
	}
	public String toString(){
		String a="";
		for(Card b:cards)
			a+=b.toString()+"\n";
		return a;
	}
}