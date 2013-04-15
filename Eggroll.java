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
	public boolean finished;
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
	private int lastMove(){ //Checks if last move was legal
		if(pile.getDeck().size()>1){
			if(pile.getDeck().get(pile.size()).similarRank(pile.getDeck().get(pile.size()-1))){
				return 0;
			}
			else if((pile.getDeck().get(pile.size()).getValue()>pile.getDeck().get(pile.size()-1).getValue())){
				return 1;
			}
		}
		return -1;
	}

	@Override
	public void playGame() {
		int turns=0;
		
		setUp();
		
		dealDeck();
		/*for(int i=0;i<players.size();i++){
			System.out.println(players.get(i).toString());
		}*/
		while(!finished){
			String input=null;
			Card inputCard=null;
			boolean validMove = false;
			//Forgot how to play the game. I'll get back to that later.
			while(!validMove){
				input = scanner.nextLine();
				inputCard = new Card(input);
				if(players.get(turns%players.size()).getHand().hasCard(inputCard))
					validMove=true;
			}
			
			players.get(turns%players.size());
			
			
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
