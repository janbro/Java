
public class Player {
	private Deck hand;
	public Player(){
		hand = new Deck();
		hand.clearDeck();
	}
	public void addCard(Card a){
		hand.addCard(a);
	}
	public void removeCard(Card a){
		hand.takeCardOutOfDeck(a);
	}
	public Deck getHand(){
		return hand;
	}
	
}