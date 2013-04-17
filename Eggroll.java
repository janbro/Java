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
		pile.addCard(new Card("3 of Clubs"));
		deck = new Deck();
		deck.takeCardOutOfDeck(new Card("3 of Clubs"));
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
		return pile.getCards().get(pile.getCards().size()-1);
	}
	
	private boolean isValidMove(Card a){
		if(pile.getCards().size()>0){
			if(getTopCard().getValue()<=a.getValue()||a.getValue()==2){
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
			int sameCardCount=1;
			String input=null;

			//Main Gameplay
			while(numCardsPlayed<cardPlayLimit){
				if(sameCardCount==4){ //Check for quadruple sets
					System.out.println("Everyone drop a card!");
					sameCardCount=1;
				}
				
				System.out.println("Pile:"+getTopCard().toString()); //Show pile
				boolean validCard=false;
				Card[] inputCard=new Card[cardPlayLimit];
				System.out.println("Player #"+(turns%players.size()+1)+" turn.\nHand:"+players.get(turns%players.size()).getHand().toString());
				while(!validCard){	//Take input from the user
					input = scanner.nextLine();
					inputCard[numCardsPlayed] = new Card(input);
					if(players.get(turns%players.size()).getHand().hasCard(inputCard[numCardsPlayed])){
						if(numCardsPlayed>0)
							if(inputCard[0].getValue()==inputCard[numCardsPlayed].getValue()){
								validCard = true;
								numCardsPlayed++;
							}
							else{
								System.out.println("You have to input the same card!");
								numCardsPlayed=0;
							}
						else{
							validCard=true;
							numCardsPlayed++;
						}
					}else{
						System.out.println("You don't have that card!");
					}
				}if(numCardsPlayed==cardPlayLimit){
					if(isValidMove(inputCard[numCardsPlayed-1])){ //Make move if legal
						if(inputCard[numCardsPlayed-1].getValue()==getTopCard().getValue()){ //Skip player if same card
							turns++;
							sameCardCount++;
						}else
							sameCardCount=1;
						players.get(turns%players.size()).removeCard(inputCard[numCardsPlayed-1]);
						pile.addCard(inputCard[numCardsPlayed-1]);
						turns++;
					}else
						System.out.println("Not a valid move!");
				}else{
					System.out.println("Input next card");
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
			System.out.println();
		}
		
		
	}
	
	public void gameOver(String winner) {
		
	}

}