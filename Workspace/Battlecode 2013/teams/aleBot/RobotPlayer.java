/* Created by: Alejandro Munoz-McDonald
 * aleBot based of team282 RobotPlayer
 */

package aleBot;

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

//TO DO(NO SPECIFIC ORDER):
//-SOLDIER SHIELD ENCAMPMENT USE
//-FIX SURROUNDING HQ WITH FROZEN SOLDIERS BUG
//-ARTILLERY CODE
//-SOLDIER NOT SEEING MEDBAY BUG

//I need to comment more often...
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
	
	//Basic move foundation
	public static void move(Direction targetDir,MapLocation targetNextLoc) throws GameActionException{
		boolean moved=false;
		
		while(!moved){
			if(rc.canMove(targetDir)&&rc.senseMine(targetNextLoc)!=Team.NEUTRAL&&rc.senseMine(targetNextLoc)!=rc.getTeam().opponent()){
				rc.move(targetDir);
				moved=true;
			}
			else if(rc.senseMine(targetNextLoc)==Team.NEUTRAL||rc.senseMine(targetNextLoc)==rc.getTeam().opponent()){
				rc.defuseMine(targetNextLoc);
				moved=true;
			}else{
				targetDir=targetDir.rotateRight();
				targetNextLoc = rc.getLocation().add(targetDir);
			}
		}
	}
	
	private static MapLocation findClosest(RobotController rc,Robot[] enemyRobots) throws GameActionException {
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
	
	private static MapLocation findClosest(RobotController rc,MapLocation[] mapLocs) throws GameActionException {
		
		int closestDist = 1000000;
		MapLocation closestLoc=null;
		for (MapLocation d:mapLocs){
			if(d!=null){
			int dist = d.distanceSquaredTo(rc.getLocation());
			if (dist<closestDist){
				closestDist = dist;
				closestLoc = d;
			}}
		}
		return closestLoc;
	}
	static boolean healing=true;
	
	public static void soldierCode() throws GameActionException{
		
		MapLocation[] allEncampments = rc.senseAlliedEncampmentSquares();
		MapLocation[] allArtillery = new MapLocation[allEncampments.length];
		MapLocation[] allMedBays = new MapLocation[allEncampments.length];
		MapLocation[] allShieldBays = new MapLocation[allEncampments.length];
		MapLocation closestArtillery = null;
		MapLocation closestMedBay = null;
		MapLocation closestShieldBay = null;
		MapLocation closestEncampment = null;
		int mCount=0,sCount=0,aCount=0;
		//Grab all encampment data
		for(MapLocation d:allEncampments){
			if(rc.senseObjectAtLocation(d).equals(RobotType.MEDBAY)){
				allMedBays[mCount]=d;
				mCount++;
			}else if(rc.senseObjectAtLocation(d).equals(RobotType.ARTILLERY)){
				allArtillery[aCount]=d;
				aCount++;
			}else if(rc.senseObjectAtLocation(d).equals(RobotType.SHIELDS)){
				allShieldBays[sCount]=d;
				sCount++;
			}
		}
		//Find closest encampments
		if(allEncampments.length!=0)
			closestEncampment = findClosest(rc,allEncampments);
		if(allArtillery.length!=0)
			closestArtillery = findClosest(rc,allArtillery);
		if(allMedBays.length!=0)
			closestMedBay = findClosest(rc,allMedBays);
		if(allShieldBays.length!=0)
			closestShieldBay = findClosest(rc,allShieldBays);
		Robot[] allies = rc.senseNearbyGameObjects(Robot.class,1000000,rc.getTeam());
		Robot[] nearbyAllies = rc.senseNearbyGameObjects(Robot.class,14,rc.getTeam());
		Robot[] enemies = rc.senseNearbyGameObjects(Robot.class,10000000,rc.getTeam().opponent());
		Robot[] nearbyEnemies = rc.senseNearbyGameObjects(Robot.class,14,rc.getTeam().opponent());
		MapLocation targetMapLoc = new MapLocation(rc.readBroadcast(getChannel()[0]),rc.readBroadcast(getChannel()[1]));
		Direction targetDir = rc.getLocation().directionTo(targetMapLoc);
		MapLocation targetNextLoc = rc.getLocation().add(targetDir);
		
		//Huge mess of if statements to decide movement
		if(rc.isActive()){
			//Check health status
			if(rc.getEnergon()<20||healing) //If less than half total energon or still healing, heal
				healing=true;
			if(rc.getEnergon()==40) //If fully healed, stop healing
				healing=false;
				
			//Move statements
			if(adjacentObj(rc.senseHQLocation(),rc.senseAlliedEncampmentSquares()).size()+adjacentObj(rc.senseHQLocation(),rc.senseMineLocations(rc.senseHQLocation(), 10000, Team.NEUTRAL)).size()<4){ //If amount of nuetral mines plus captured encampment squares is greater than 4, dont go into this statement
				if(rc.senseEncampmentSquare(rc.getLocation())){
					if(rc.getLocation().x%2==0) //Shield encampment or medbay
						rc.captureEncampment(RobotType.MEDBAY);
					else
						rc.captureEncampment(RobotType.SHIELDS);
				}
				else if(rc.getLocation().distanceSquaredTo(closestEncampment)<=8&&!rc.senseObjectAtLocation(closestEncampment).equals(RobotType.SOLDIER)) //If close enough encampment, move towards
					move(rc.getLocation().directionTo(closestEncampment),closestEncampment);
				else //Else move towards enemy HQ?
					move(rc.getLocation().directionTo(rc.senseEnemyHQLocation()),rc.senseEnemyHQLocation());
			}
			
			//Healing move
			else if(healing){
				//if(rc.getLocation().distanceSquaredTo((rc.senseHQLocation()))<8)
				
				if(closestMedBay!=null) //Find closest meday if exists
					move(rc.getLocation().directionTo(closestMedBay),closestMedBay);
				else //Else retreat to HQ(will change this later)
					move(rc.getLocation().directionTo(rc.senseHQLocation()),rc.senseHQLocation());
			}
			else if(enemies.length+2>allies.length){ //If amount of nearby enemies is greater than nearby allies, group up
				move(rc.getLocation().directionTo(findClosest(rc,allies)),findClosest(rc,allies));
			}
			else if(nearbyEnemies.length==0){ //If no enemies around, move towards targetLoc
				move(rc.getLocation().directionTo(targetNextLoc),targetNextLoc);
			}
			else if(nearbyEnemies.length+3<nearbyAllies.length){ //If more nearby allies than nearby enemies, move towards closest enemy
				move(rc.getLocation().directionTo(findClosest(rc,nearbyEnemies)),findClosest(rc,nearbyEnemies));
			}
			else{ //Else move towards HQ?
				move(rc.getLocation().directionTo(rc.senseHQLocation()),rc.senseHQLocation());
			}
		}
	}
	
	public static ArrayList<MapLocation> adjacentObj(MapLocation loc,MapLocation[] objLocs){ 
		ArrayList<MapLocation> adjObjs = new ArrayList<MapLocation>();
		for(MapLocation mapLoc:objLocs){
			if(loc.isAdjacentTo(mapLoc)){
				adjObjs.add(mapLoc);				
			}
		}
		return adjObjs;
	}
	
	public static void hqCode() throws GameActionException{
		
		boolean spawnSoldiers=true;
		
		//Broadcast Target MapLocation
		if(Clock.getRoundNum()<200){
			rc.broadcast(getChannel()[0], rc.getMapHeight()/2);
			rc.broadcast(getChannel()[1], rc.getMapWidth()/2);
		}else {
			rc.broadcast(getChannel()[0], rc.senseEnemyHQLocation().x);
			rc.broadcast(getChannel()[1], rc.senseEnemyHQLocation().y);
		}
		
		if(rc.isActive()){
			//Priorities, upgrade defusion
			if(!rc.hasUpgrade(Upgrade.DEFUSION)){
				rc.researchUpgrade(Upgrade.DEFUSION);
			}
			//Spawn Soldiers in first open adjacent location
			else if(spawnSoldiers){
				for(Direction d: Direction.values()){
					if(rc.canMove(d)&&rc.senseMine(rc.getLocation().add(d))==null){
						rc.spawn(d);
						break;
					}
				}
			}
		}
		
	}
	
	public static int[] getChannel(){ //Return random channel every tick
		int channel1 = Math.abs(((int)(Clock.getRoundNum()*Math.E))%GameConstants.BROADCAST_MAX_CHANNELS);
		int channel2 = Math.abs(((int)(Clock.getRoundNum()*Math.PI))%GameConstants.BROADCAST_MAX_CHANNELS);
		int[] channel = {channel1,channel2};
		System.out.println("Channel: "+channel);
		return channel;
	}
}
