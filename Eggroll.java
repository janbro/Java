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
		statuses = new ArrayList<String>(Arrays.asList("Egg","Vice Egg","Vice President","President"));
		players = new ArrayList<Player>();
		for(int i=0;i<amountOfPlayers;i++){
			players.add(new Player());
		}while(statuses.size()-players.size()!=0){
			statuses.remove(1);
		}
		pile = new Deck();
		pile.clearDeck();
		deck = new Deck();
		deck.shuffle();
		while(deck.size() > 0)
			deck.deal();
	}
	private void dealDeck(){
		int count=0;
		while(!deck.isEmpty()){
			players.get(count%players.size());
			count++;
		}
	}
	private boolean wasLegal(){
		if(pile.getDeck().size()>1){
			if(pile.getDeck().get(pile.size()).similarRank(pile.getDeck().get(pile.size()-1))||pile.getDeck().get(pile.size()).similarSuit(pile.getDeck().get(pile.size()-1))){
				return true;
			}
		}
		return false;
	}

	@Override
	public void playGame() {
		setUp();
		
		dealDeck();
		/*for(int i=0;i<players.size();i++){
			System.out.println(players.get(i).toString());
		}*/
		while(!finished){
			
			
			
			
			
			
			
			for(int i=0;i<players.size();i++){
				if(players.get(i).getHand().size()==0){
					System.out.println("Player "+i+" is "+statuses.get(players.size()));
					players.remove(i);
				}
			}if(players.size()==1){
				finished=true;
			}
		}
		
		
	}
	
	public void gameOver(String winner) {
		
	}

}
