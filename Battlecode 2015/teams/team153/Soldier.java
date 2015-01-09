package team153;

import battlecode.common.*;

public class Soldier extends BasicPlayer {
	
	public Soldier(RobotController myRc){
		super(myRc,myRc.getID());
		try{
			run();
		}
		catch(Exception e){
			System.out.println("Soldier Exception!");
			e.printStackTrace();
		}
	}

	public static void run() throws GameActionException{

	}
}
