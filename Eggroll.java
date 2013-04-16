/*Author: Alejandro Munoz-McDonald
 *Date Created: 4/12/2013
 **/

package Java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Eggroll implements Play {
	private static Scanner scanner = new Scanner(System.in);
	private static ArrayList<Player> players;
	private static Deck deck;
	private static Deck pile;
	private static String input;
	private boolean finished;
	private int amountOfPlayers;
	ArrayList<String> statuses;
	
	public Eggroll(){
		amountOfPlayers=4;
		playGame();
	}
	
	public Eggroll(int numOfPlayers){
		amountOfPlayers=numOfPlayers;
		playGame();
	}
	
	public void explainRules() {
		System.out.println("Try harder...");
	}
	public void setUp() {
		statuses = new ArrayList<String>(Arrays.asList("Egg","Vice Egg","Vice President","President")); //Currently only up to four players can play, need more labels for more.
		players = new ArrayList<Player>();
		for(int i=0;i<amountOfPlayers;i++){
			players.add(new Player());
		}
		while(statuses.size()-players.size()!=0){ //Remove unneeded statuses
			statuses.remove(statuses.size()/2);
		}
		pile = new Deck(); //Trash pile. Used to check if players move was legal.
		pile.clearDeck();
		deck = new Deck();
		deck.shuffle();
	}
	private void dealDeck(){ //Deals out entire deck to all players
		int count=0;
		while(!deck.isEmpty()){
			players.get(count%players.size()).addCard(deck.deal());
			count++;
		}
	}
	
	private Card getTopCard(){
		return pile.getCards().get(pile.getCards().size());
	}
	
	private boolean isValidMove(Card a){
		if(pile.getCards().size()>1){
			if(getTopCard().similarRank(a)||getTopCard().getValue()<a.getValue()){
				return true;
			}
		}return false;
	}
	

	@Override
	public void playGame() {
		int cardPlayLimit=1;
		int turns=0;
		
		setUp();
		
		dealDeck();
		/*for(int i=0;i<players.size();i++){
			System.out.println(players.get(i).toString());
		}*/
		while(!finished){
			int numCardsPlayed=0;
			String input=null;
			//Forgot how to play the game. I'll get back to that later.
			while(numCardsPlayed<cardPlayLimit){
				Card[] inputCard=new Card[cardPlayLimit];
				System.out.println("Player #"+(turns+1)+" turn.\nHand:"+players.get(turns%players.size()).getHand().toString());
				input = scanner.nextLine();
				inputCard = new Card[numCardsPlayed];
				if(players.get(turns%players.size()).getHand().hasCard(inputCard[numCardsPlayed])){
					if(isValidMove(inputCard[numCardsPlayed])){
						if(inputCard[numCardsPlayed].getValue()==getTopCard().getValue()){
							turns++;
						}players.get(turns%players.size()).removeCard(inputCard[numCardsPlayed]);
						pile.addCard(inputCard[numCardsPlayed]);
						numCardsPlayed++;
						if(inputCard[numCardsPlayed].getValue()==getTopCard().getValue())
							turns++;
					}
				}
			}
			
			
			//Check for empty hand/winner
			for(int i=0;i<players.size();i++){
				if(players.get(i).getHand().size()==0){
					System.out.println("Player "+i+" is "+statuses.get(players.size()));
					players.remove(i);
				}
			}if(players.size()==1){
				finished=true;
			}
			turns++;
		}
		
		
	}
	
	public void gameOver(String winner) {
		
	}

}
