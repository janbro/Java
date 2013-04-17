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
	public static String[] getDeck(){
		String[] suit={"Clubs", "Diamonds", "Hearts", "Spades"};
		String[] rank={"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
		String[] cards=new String[suit.length*rank.length];
		for(int i=0;i<suit.length;i++)
			for(int j=0;j<rank.length;j++)
				cards[i*rank.length+j]=rank[j]+" of "+suit[i];
		return cards;
	}
	public Deck(){
		cards=new ArrayList<Card>();
		String[] standard=getDeck(); 
		for(String a:standard)
			cards.add(new Card(a));
	}
	
	public boolean hasCard(Card a){
		for(Card b:cards){
			if(b.similarRank(a)&&b.similarSuit(a))
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
		for(int i=0;i<cards.size();i++){
			System.out.println(a.toString()+":"+cards.get(i).toString());
			if(a.toString().equals(cards.get(i).toString())){
				cards.remove(i);
				return;
			}
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
	
	public int dealRaw(){ //Deals a card, returns cards value(jack == 11, and so on)
		Card a=deal();
		System.out.println(a);
		return a.getValue();
	}
	public int size(){ //Returns how many cards are left in deck
		return cards.size();
	}
	public String toString(){
		String a="";
		for(Card b:cards)
			a+=b.toString()+",";
		return a;
	}
}