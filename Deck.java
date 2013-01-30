package Java;
import java.util.*;
public class Deck {
	private ArrayList<Card> cards;
	public Deck(){
		cards=new ArrayList<Card>();
		String[] standard=BlackJackMethods.getCards();
		for(String a:standard)
			cards.add(new Card(a));
	}
	public void shuffle(){
		Collections.shuffle(cards);
	}
	public void takeCardOutOfDeck(Card a){
		for(int i=0;i<cards.size();i++)
			if(a.equals(cards.get(i))){
				cards.remove(i);
				return;
			}
	}
	public void deal(){
		Card a=cards.get(0);
		takeCardOutOfDeck(a);
		System.out.println(a);
	}
	public String toString(){
		String a="";
		for(Card b:cards)
			a+=b.toString()+"\n";
		return a;
	}
//	public static void main(String[] args){
//		Deck a=new Deck();
//		System.out.println(a);
//		a.shuffle();
//		System.out.println(a);
//		a.deal();
//		System.out.println();
//		System.out.println(a);
//	}
}
