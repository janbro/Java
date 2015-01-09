package team153;

import java.util.Random;

import battlecode.common.*;

public class Hq extends BasicPlayer {
	
	public Hq(RobotController myRc){
		rand = new Random(rc.getID());
		rc = myRc;
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
