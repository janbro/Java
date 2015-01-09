package team153;

import battlecode.common.*;


public class RobotPlayer {

	public static void run(RobotController rc) {

		while (true) {
			try {
				switch(rc.getType()){
				case BEAVER:
					new Beaver(rc);
					break;
				case SOLDIER:
					new Soldier(rc);
					break;
				case HQ:
					new Hq(rc);
					break;
				default:
					break;
				}
			}
			catch(Exception e) {
				System.out.println("RobotPlayer Caught Exception:");
				e.printStackTrace(); 
			}
		}
	}
}
