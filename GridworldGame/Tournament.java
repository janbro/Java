package Java.GridworldGame;

import java.util.ArrayList;

public class Tournament {
	private static GridworldGame game;
	private static ArrayList<GridworldGamePlayer> players;
	public static void main(String[] args){
		game=new ConnectFour();
		players=new ArrayList<GridworldGamePlayer>();
	}
	public static void singleElimination(){
		for(int roundNum=1;players.size()>1;roundNum++){
			for(int i=0;i<players.size()-1;i+=2){
				GridworldGamePlayer a=players.get(i),b=players.get(i+1);
				ArrayList<GridworldGamePlayer> roundPlayers=new ArrayList<GridworldGamePlayer>();
				roundPlayers.add(a);
				roundPlayers.add(b);
				int win=game.play(roundPlayers);
				if(win == 0)
					i-=2;
				else if(win < 0)
					players.remove(b);
				else
					players.remove(a);
			}
			System.out.println("Round: "+roundNum);
			for(int i=0;i<players.size();i++)
				System.out.println("\t"+(i+1)+": "+players.get(i));
		}
	}
}
