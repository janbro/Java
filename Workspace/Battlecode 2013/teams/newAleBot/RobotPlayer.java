/* Created by: Alejandro Munoz-McDonald
 * aleBot based of team282 RobotPlayer
 */

package newAleBot;

import java.util.ArrayList;

import battlecode.common.Clock;
import battlecode.common.Direction;
import battlecode.common.GameActionException;
import battlecode.common.GameConstants;
import battlecode.common.GameObject;
import battlecode.common.MapLocation;
import battlecode.common.Robot;
import battlecode.common.RobotController;
import battlecode.common.RobotInfo;
import battlecode.common.RobotType;
import battlecode.common.Team;
import battlecode.common.Upgrade;
import battlecode.engine.instrumenter.RoboAdapter;

//TO DO:
//-CANT SENSE ENEMY ENCAMPMENT LOCATIONS, MAKE WORK AROUND(CODE FOR ENCAMPMENT RUSH COMMENTED OUT UNTIL FIXED)
//-INCREMENT NUKE RUSH RADIUS BY AMOUNT OF TICKS LEFT UNTIL NUKE UNTIL REACHED HQ DISTANCE RADIUS

public class RobotPlayer {
	
static RobotController rc;

	public static void run(RobotController myRC){
		rc = myRC;
		
		while(true){
			try{
				switch(rc.getType()){
				case SOLDIER:
					//Run soldier Code;
					soldierCode();
					break;
				case HQ:
					//Run hq code
					hqCode();
					break;
				case ARTILLERY:
					//Run artillery code
					break;
				default:
					break;
				}
			}catch (Exception e){
				System.out.println("caught exception before it killed us:");
				e.printStackTrace();
			}
			rc.yield();
		}
	}
	
	static boolean nukeHalfDone=false;
	static int nukeCount=0;
	
	public static void hqCode() throws GameActionException{
		while(true){
			Robot[] soldiers = rc.senseNearbyGameObjects(Robot.class, rc.senseHQLocation(), 99999999, rc.getTeam());
			int soldierCount = soldiers.length;
			if(rc.isActive()){
				if(nukeCount>=200)
					nukeHalfDone = true;
				
				if((rc.senseEnemyNukeHalfDone()||nukeHalfDone||soldierCount>=25))//&&!(findClosest(soldiers,rc.senseEnemyHQLocation()).distanceSquaredTo(rc.senseEnemyHQLocation())<13))
					rc.broadcast(1, 1);
				else
					rc.broadcast(1, 0);
				
				if(!rc.hasUpgrade(Upgrade.DEFUSION))
					rc.researchUpgrade(Upgrade.DEFUSION);
				else if(soldierCount<25){
					for(Direction d: Direction.values()){
						if(rc.canMove(d)&&rc.senseMine(rc.getLocation().add(d))==null){
							rc.spawn(d);
							break;
						}
					}
				}
				else if(!rc.hasUpgrade(Upgrade.NUKE)){
					rc.researchUpgrade(Upgrade.NUKE);
					nukeCount++;
				}
			}
		}
	}
	
	static Boolean move = false;
	
	public static void soldierCode() throws GameActionException{
		Direction dir = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
		MapLocation rallyPt = rc.senseHQLocation().add(dir,5);
		//MapLocation[] enemyEncampments = rc.senseEncampmentSquares(rc.getLocation(), 9999999, rc.getTeam().opponent());
		MapLocation prevLoc = null;
		while(true){
			Robot[] soldiers = rc.senseNearbyGameObjects(Robot.class, rc.senseHQLocation(), 99999999, rc.getTeam());
			int soldierCount = soldiers.length;
			
			if(soldierCount>=10)
				move = true;
			
			if(!move){
				dir = rc.getLocation().directionTo(rallyPt);
				basicMove(dir);
			}
			else if(rc.getLocation().distanceSquaredTo(rc.senseEnemyHQLocation())<13||(rc.readBroadcast(1)==1&&rc.getLocation().distanceSquaredTo(rc.senseEnemyHQLocation())<rc.senseHQLocation().distanceSquaredTo(rc.senseEnemyHQLocation())/2)){ //Rush HQ if within viewing distance
				//rc.broadcast(5, 1); //If seen enemy HQ
				dir = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
				basicMove(dir);
			}
			//else if(rc.getLocation().distanceSquaredTo(findClosest(enemyEncampments))<13){ //Rush enemy encampments
			//	dir = rc.getLocation().directionTo(findClosest(enemyEncampments));
			//	basicMove(dir);
			//}
			else{ //Advanced navigation move
				dir = move(dir,rc.senseEnemyHQLocation());
				if(rc.getLocation().equals(prevLoc)&&(rc.senseMine(rc.getLocation().add(dir))==rc.getTeam().opponent()||rc.senseMine(rc.getLocation().add(dir))==Team.NEUTRAL))
					rc.defuseMine(rc.getLocation().add(dir));
				else
					dir = dir.rotateRight();
				
				prevLoc = rc.getLocation();
			}
		}
	}
	
	public static void basicMove(Direction dir) throws GameActionException{
		if(rc.senseMine(rc.getLocation().add(dir))==rc.getTeam().opponent()||rc.senseMine(rc.getLocation().add(dir))==Team.NEUTRAL)
			rc.defuseMine(rc.getLocation().add(dir));
		else if(rc.senseObjectAtLocation(rc.getLocation().add(dir))==null)
			rc.move(dir);
		else
			basicMove(dir.rotateRight());
	}
	
	public static Direction move(Direction dir,MapLocation mapLoc) throws GameActionException{
		int count = 0;
		while(true){
			if(count>=7)
				break;
			if(rc.getLocation().add(dir).x>0&&rc.getLocation().add(dir).x<rc.getMapWidth()&&rc.getLocation().add(dir).y>0&&rc.getLocation().add(dir).y<rc.getMapHeight()&&rc.senseMine(rc.getLocation().add(dir))==null&&rc.senseObjectAtLocation(rc.getLocation().add(dir))==null){
				rc.move(dir);
				break;
			}
			
			dir = dir.rotateLeft();
			
			count++;
		}return dir;
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
	
	private static MapLocation findClosest(MapLocation[] mapLocs,MapLocation targetMapLoc) throws GameActionException {
		int closestDist = 1000000;
		MapLocation closestEnemy=null;
		for (int i=0;i<mapLocs.length;i++){
			int dist = mapLocs[i].distanceSquaredTo(targetMapLoc);
			if (dist<closestDist){
				closestDist = dist;
				closestEnemy = mapLocs[i];
			}
		}
		return closestEnemy;
	}
	
	private static MapLocation findClosest(Robot[] robots, MapLocation targetMapLoc) throws GameActionException {
		int closestDist = 1000000;
		MapLocation closestEnemy=null;
		for (int i=0;i<robots.length;i++){
			Robot arobot = robots[i];
			RobotInfo arobotInfo = rc.senseRobotInfo(arobot);
			int dist = arobotInfo.location.distanceSquaredTo(targetMapLoc);
			if (dist<closestDist){
				closestDist = dist;
				closestEnemy = arobotInfo.location;
			}
		}
		return closestEnemy;
	}
}
	