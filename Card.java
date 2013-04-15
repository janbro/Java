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
	
	private String getRank(){ //Returns rank
		return rank;
	}
	
	private String getSuit(){ //Returns suit
		return suit;
	}
	
	public boolean similarRank(Card a){ //Checks if cards ranks are equal
		return getRank().equals(a.getRank());
	}
	
	public boolean similarSuit(Card a){ //Checks if cads suits are equal
		return getSuit().equals(a.getSuit());
	}
	
	public Card(String suit,String rank){
		this.suit=suit;
		this.rank=rank;
	}
	
	public int getValue(){
		int num;
		try{
			num=Integer.parseInt(getRank());
		}catch(Exception e){
			if(getRank().toLowerCase().equals("ace"))
				num=14;
			else if(getRank().toLowerCase().equals("jack"))
				num=11;
			else if(getRank().toLowerCase().equals("queen"))
				num=12;
			else if(getRank().toLowerCase().equals("king"))
				num=13;
			else
				num=-1;
		}
		return num;
	}
	
	public String toString(){
		return rank+" "+suit;
	}
//	public static void main(String[] args){
//		Card a=new Card("Ace of Spades");
//		System.out.println(a.suit+" "+a.rank);
//	}
}