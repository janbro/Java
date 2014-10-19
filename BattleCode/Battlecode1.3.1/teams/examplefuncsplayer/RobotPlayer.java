package examplefuncsplayer;

import battlecode.common.Direction;
import battlecode.common.GameConstants;
import battlecode.common.RobotController;
import battlecode.common.RobotType;
import battlecode.common.*;
import java.util.*;

public class RobotPlayer {
	static Random rand;
	static RobotController rc;
	
	public static void run(RobotController rcIn) {
		rc = rcIn;
		rand = new Random();
		Direction[] directions = {Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST};
		
		while(true) {
			if (rc.getType() == RobotType.HQ) {
				try {					
					//Check if a robot is spawnable and spawn one if it is
					if (rc.isActive() && rc.senseRobotCount() < 25) {
						Direction toEnemy = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
						if (rc.senseObjectAtLocation(rc.getLocation().add(toEnemy)) == null) {
							rc.spawn(toEnemy);
						}
					}
				} catch (Exception e) {
					System.out.println("HQ Exception");
				}
			}
			
			if (rc.getType() == RobotType.NOISETOWER) {
				try {					
					//Shoot in circle around pastr
					int time = Clock.getRoundNum();
					int dir = time%8;
					int multiplyer = 8/(dir+1);
					rc.attackSquare(rc.getLocation().add(directions[dir], multiplyer));
				} catch (Exception e) {
					System.out.println("HQ Exception");
				}
			}
			
			if (rc.getType() == RobotType.SOLDIER) {
				try {
					if (rc.isActive()) {
						int action = (rc.getRobot().getID()*rand.nextInt(101) + 50)%101;
						if(rc.readBroadcast(1000)!=1&&rc.sensePastrLocations(rc.getTeam())!=null){
							if(rc.getLocation().distanceSquaredTo((findClosest(rc.sensePastrLocations(rc.getTeam()))))<5){
								rc.broadcast(1000, 1);
								rc.construct(RobotType.NOISETOWER);
							}
							if(rc.getLocation().distanceSquaredTo((findClosest(rc.sensePastrLocations(rc.getTeam()))))<rc.getType().sensorRadiusSquared)
								rc.move(rc.getLocation().directionTo(findClosest(rc.sensePastrLocations(rc.getTeam()))));
						}
						//Construct a PASTR
						if (action < 1 && rc.getLocation().distanceSquaredTo(rc.senseHQLocation()) > 2) {
							rc.construct(RobotType.PASTR);
						//Attack a random nearby enemy
						} else if (action < 30) {
							Robot[] nearbyEnemies = rc.senseNearbyGameObjects(Robot.class,10,rc.getTeam().opponent());
							if (nearbyEnemies.length > 0) {
								RobotInfo robotInfo = rc.senseRobotInfo(nearbyEnemies[0]);
								rc.attackSquare(robotInfo.location);
							}
						//Move in a random direction
						} else if (action < 80) {
							Direction moveDirection = directions[rand.nextInt(8)];
							if (rc.canMove(moveDirection)) {
								rc.move(moveDirection);
							}
						//Sneak towards the enemy
						} else {
							Direction toEnemy = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
							if (rc.canMove(toEnemy)) {
								rc.sneak(toEnemy);
							}
						}
					}
				} catch (Exception e) {
					System.out.println("Soldier Exception");
					e.printStackTrace();
				}
			}
			
			rc.yield();
		}
	}
	private static MapLocation findClosest(Robot[] enemyRobots) throws GameActionException { 
        int closestDist = 1000000; 
        MapLocation closestEnemy=null; 
        for (int i=0;i<enemyRobots.length;i++){ 
            Robot arobot = enemyRobots[i]; 
            RobotInfo arobotInfo = rc.senseRobotInfo(arobot); 
            int dist = arobotInfo.location.distanceSquaredTo(rc.getLocation()); 
            if (dist<closestDist){ 
                closestDist = dist; 
                closestEnemy = arobotInfo.location; 
            } 
        } 
        return closestEnemy; 
    } 
	private static MapLocation findClosest(MapLocation[] mapLocs) throws GameActionException {
		int closestDist = 1000000;
		MapLocation closestEnemy=null;
		for (int i=0;i<mapLocs.length;i++){
			int dist = mapLocs[i].distanceSquaredTo(rc.getLocation());
			if (dist<closestDist){
				closestDist = dist;
				closestEnemy = mapLocs[i];
			}
		}
		return closestEnemy;
	}
}
