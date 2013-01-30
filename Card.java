package Java;

public class Card {
	private String suit;
	private String rank;
	public Card(String name){
		int a=name.indexOf(" ");
		suit=name.substring(0,a);
		int b=name.indexOf(" ",a+1);
		rank=name.substring(b+1);
	}
	public Card(String suit,String rank){
		this.suit=suit;
		this.rank=rank;
	}
	public String toString(){
		return rank+" "+suit;
	}
//	public static void main(String[] args){
//		Card a=new Card("Ace of Spades");
//		System.out.println(a.suit+" "+a.rank);
//	}
}
