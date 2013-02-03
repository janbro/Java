package Java;

import java.util.*;

public class Deck {
	private ArrayList<Card> cards;
	
	private int cardValue(String input) {
		// TODO Auto-generated method stub
		int num;
		try{
			num=Integer.parseInt(input);
		}catch(Exception e){
			if(input.toLowerCase().equals("ace"))
				num=1;
			else
				num = 10;
		}
		
		return num;
	}
	
	public Deck(){
		cards=new ArrayList<Card>();
		String[] standard=BlackJack.getCards();
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
	
	public int deal(){
		Card a=cards.get(0);
		takeCardOutOfDeck(a);
		System.out.println(a);
		return cardValue((a.toString().substring(a.toString().indexOf(" ")+1)));
	}

	public String toString(){
		String a="";
		for(Card b:cards)
			a+=b.toString()+"\n";
		return a;
	}
}