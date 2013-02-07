package blackJack;

import java.util.Scanner;

public class War implements Play {

	private static Scanner scanner = new Scanner(System.in);
	private static boolean done,tie;
	private static int playerTotal,dealerTotal,playerPoints,dealerPoints,points;
	private static Deck deck;
	private static String input;
	@Override
	public void explainRules() {
		// TODO Auto-generated method stub
		System.out.println("Draw card");
		
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
		deck = new Deck();
		deck.shuffle();
		done = tie = false;
		playerPoints = dealerPoints = playerTotal = dealerTotal = 0; points = 1;
	}

	@Override
	public void playGame() {
		// TODO Auto-generated method stub
		explainRules();
		
		setUp();
		
		while(!done){
			while(true){
				System.out.println("\nPlayer points:"+playerPoints);
				System.out.println("Dealer points:"+dealerPoints);
	
				System.out.print("\nDraw:");
				input = scanner.next();
				if(input.toLowerCase().equals("y")){
					break;
				}
				else if(input.toLowerCase().equals("n")){
					done=true;
					break;
				}
				System.out.println("Not a valid input.");
			}if(done){System.out.print("\n");break;}
			try{
				System.out.print("Player: ");
				playerTotal = deck.deal();
				System.out.print("Dealer: ");
				dealerTotal = deck.deal();
			}
			catch(IndexOutOfBoundsException e){
				System.out.println("\n\nOut of cards!\n");
				done=true;
				break;
			}
			if(playerTotal>dealerTotal){
				playerPoints+=points;
				points = 1;
				System.out.println("You won!");
			}
			else if(playerTotal<dealerTotal){
				dealerPoints+=points;
				points = 1;
				System.out.println("You lost!");
			}
			else{
				points+=3;
				tie=true;
				System.out.println("Tie!");
			}
		}
		if(playerPoints>dealerPoints){
			System.out.println("Player points:"+playerPoints);
			System.out.println("Dealer points:"+dealerPoints+"\n");
			System.out.println("You beat the dealer!");
		}
		else if(playerPoints<dealerPoints){
			System.out.println("Player points:"+playerPoints);
			System.out.println("Dealer points:"+dealerPoints+"\n");
			System.out.println("Dealer beat you!");
		}
		else{
			System.out.println("Player points:"+playerPoints);
			System.out.println("Dealer points:"+dealerPoints+"\n");
			System.out.println("Player and dealer tied!");
		}
	}

	@Override
	public void gameOver(String winner) {
		// TODO Auto-generated method stub
		
	}
	

}
