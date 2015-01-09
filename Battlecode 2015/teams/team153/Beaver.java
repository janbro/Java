package team153;

import java.util.Random;

import battlecode.common.GameActionException;
import battlecode.common.RobotController;

public class Beaver extends BasicPlayer{

	public Beaver(RobotController myRc) throws GameActionException{
		rand = new Random(rc.getID());
		rc = myRc;
		try{
			run();
		}
		catch(Exception e){
			System.out.println("Beaver Exception!");
			e.printStackTrace();
		}
	}

	public static void run() throws GameActionException{
		randMove();
		rc.yield();
	}
	
}
