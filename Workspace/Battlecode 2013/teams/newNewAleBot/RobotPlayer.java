package newNewAleBot;

import battlecode.common.RobotController;

public class RobotPlayer {
	public static void run(RobotController rc) {

        while (true) {
            try {
            	switch(rc.getType()){
					case SOLDIER:
						Soldier.run(rc);
						break;
					case HQ:
						HQ.run(rc);
						break;
					case ARTILLERY:
						break;
					default:
						break;
				}
            }
            catch(Exception e) {
            	System.out.println("newNewAleBot Caught Exception:");
            	e.printStackTrace(); 
            }
        }
    }
}
