package pureNuke;

import battlecode.common.Direction;
import battlecode.common.GameConstants;
import battlecode.common.RobotController;
import battlecode.common.RobotType;
import battlecode.common.Upgrade;

public class RobotPlayer {
	public static void run(RobotController rc) {
		while (true) {
			try {
				
				if (rc.getType() == RobotType.HQ) {
					if (rc.isActive()) {
						rc.researchUpgrade(Upgrade.NUKE);
						// Spawn a soldier
						//Direction dir = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
						//if (rc.canMove(dir))
						// 	rc.spawn(dir);
					}
				} else if (rc.getType() == RobotType.SOLDIER) {
					if (rc.isActive()) {
						
					}
				}

				// End turn
				rc.yield();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
