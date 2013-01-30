package Java;

public interface Game{

//  This method will print out the rules of the game.
	void explainRules();
//  This method will set up the game. For example, if the game is poker, then the
//	player and the dealer will each be dealt five cards.
	void setUp();

//  This method will run the game.
	void playGame();

//  This method will be passed the name of the winner and print a message telling
//	who won the game.
	void gameOver(String winner);
}