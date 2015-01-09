package team153;

import battlecode.common.*;

public class Hq extends BasicPlayer {
	
	public Hq(RobotController myRc){
		super(myRc,myRc.getID());
		try{
			run();
		}
		catch(Exception e){
			System.out.println("HQ Exception!");
			e.printStackTrace();
		}
	}

	public static void run() throws GameActionException{

		spawnUnit(RobotType.BEAVER);
		
		rc.yield();
	}

}
