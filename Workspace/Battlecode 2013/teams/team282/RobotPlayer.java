/* Contributors: Alejandro Munoz-McDonald, Jared Harkins, Nick Munoz, Katie Gandomi, Max Miller
 * Team282 RobotPlayer
 */

package team282;

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

public class RobotPlayer {

	static RobotController rc;
	
	public static void run(RobotController myRC){
		rc = myRC;
		
		while(true){
			try{
				switch(rc.getType()){
				case SOLDIER:
					soldierCode();
				case HQ:
					hqCode();
				case ARTILLERY:
					artilleryCode();
				}
			}catch (Exception e){
				System.out.println("caught exception before it killed us:");
				e.printStackTrace();
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
	private static MapLocation findClosestToHQ(Robot[] enemyRobots) throws GameActionException {
		int closestDist = 1000000;
		MapLocation closestEnemy=null;
		for (int i=0;i<enemyRobots.length;i++){
			Robot arobot = enemyRobots[i];
			RobotInfo arobotInfo = rc.senseRobotInfo(arobot);
			int dist = arobotInfo.location.distanceSquaredTo(rc.senseHQLocation());
			if (dist<closestDist){
				closestDist = dist;
				closestEnemy = arobotInfo.location;
			}
		}
		return closestEnemy;
	}
	public static int countAdjacentRobots(MapLocation swag, Team team) throws GameActionException {
		return rc.senseNearbyGameObjects(Robot.class,swag,2,team).length;
	}
	private static MapLocation findOptimalArtilleryTarget(Robot[] enemyRobots) throws GameActionException {
		int mostAdjEnemies = 0;
		MapLocation optimalEnemy=null;
		for (int i=0;i<enemyRobots.length;i++){
			Robot arobot = enemyRobots[i];
			RobotInfo arobotInfo = rc.senseRobotInfo(arobot);
			int adjEnemies = countAdjacentRobots(arobotInfo.location,rc.getTeam().opponent());
			int adjAllies  = countAdjacentRobots(arobotInfo.location,rc.getTeam());
			if (adjEnemies>mostAdjEnemies&&adjEnemies>adjAllies){
				mostAdjEnemies = adjEnemies;
				optimalEnemy = arobotInfo.location;
			}
		}
		return optimalEnemy;
	}
	public static void artilleryCode(){
		while(true){
			try{
				Robot[] enemies = rc.senseNearbyGameObjects(Robot.class,58,rc.getTeam().opponent());
				MapLocation optimalTarget = findOptimalArtilleryTarget(enemies);
				if(optimalTarget!=null){
					rc.attackSquare(optimalTarget);
				}
				//find enemy with most number of adjacent enemies 
			}
			catch (Exception e){
				System.out.println("Artillery Exception");
				e.printStackTrace();
			}
			rc.yield();
		}
	}
	
	public static boolean isArtillery(int x, int y, int hqx, int hqy, int otherhqx, int otherhqy) {
        double slope = ((double) hqy - otherhqy) / ((double) hqx - otherhqx + .00001);
        if (Math.sqrt((hqy - otherhqy) * (hqy - otherhqy) + (hqx - otherhqx) * (hqx - otherhqx)) < 7) {
            return true;
        }
        if (Math.abs(hqx - otherhqx) > Math.abs(hqy - otherhqy)) {
            int b = (int) (hqy + 9 - hqx * slope);
            int b2 = (int) (hqy - 9 - hqx * slope);
            double c = slope * x;
            if (y < c + b && y > c + b2) {
                return true;
            }
        } else {
            int b = (int) (hqy - (hqx + 9) * slope);
            int b2 = (int) (hqy - (hqx - 9) * slope);
            double c = slope * x;
            if (y > c + b && y < c + b2 || y < c + b && y > c + b2) {
                return true;
            }
        }
        return false;
    }
	
	private static void soldierCode() throws GameActionException{
		 
		//rc.wearHat();
		boolean rush=false;
		int hqEncampments=0;
		MapLocation[] w = rc.senseEncampmentSquares(rc.getLocation(), 100000, Team.NEUTRAL);
		for(MapLocation i:w){
			if(rc.senseHQLocation().isAdjacentTo(i)){
				hqEncampments++;
			}
		}System.out.println("EncampmentsNextToHQ: "+hqEncampments);
		Direction dirToEnemyHQ=rc.getLocation().directionTo(rc.senseEnemyHQLocation());
		boolean pattern=true,fallBack=false;
		if(rc.senseEnemyHQLocation().x-rc.senseHQLocation().x!=0){
			if(Math.abs((rc.senseEnemyHQLocation().y-rc.senseHQLocation().y)/(rc.senseEnemyHQLocation().x-rc.senseHQLocation().x))>=1){
				pattern=false;
			}
		}else{
			pattern=false;
		}
		
		while(true){
			try{
				Robot[] allies = rc.senseNearbyGameObjects(Robot.class,14,rc.getTeam());
				Robot[] enemies = rc.senseNearbyGameObjects(Robot.class,10000000,rc.getTeam().opponent());
				Robot[] nearbyEnemies = rc.senseNearbyGameObjects(Robot.class,14,rc.getTeam().opponent());
				int surroundingMines[][]=new int[3][3];
				
				MapLocation targetEncampment=null;
				Direction posDirs[] = {Direction.NORTH_WEST,Direction.NORTH,Direction.NORTH_EAST,Direction.WEST,Direction.EAST,Direction.SOUTH_WEST,Direction.SOUTH,Direction.SOUTH_EAST};
				Direction surroundings[] = {Direction.NORTH_WEST,Direction.NORTH,Direction.NORTH_EAST,Direction.WEST,Direction.NONE,Direction.EAST,Direction.SOUTH_WEST,Direction.SOUTH,Direction.SOUTH_EAST};
				Direction dir = rc.getLocation().directionTo(rc.senseEnemyHQLocation());
				int mines=0,hqDist = (int)(Math.sqrt(Math.pow(rc.senseEnemyHQLocation().x-rc.senseHQLocation().x,2)+Math.pow(rc.senseEnemyHQLocation().y-rc.senseHQLocation().y,2)));
				double closestDist=0;
				
				for(int j=0;j<posDirs.length;j++){
					if(posDirs[j]==dir.opposite()){
						posDirs[j]=dir;
					}
				}
				dir = posDirs[(int)(Math.random()*8)];//rc.getLocation().directionTo(rc.senseEnemyHQLocation());
				
				if(enemies.length>0){
					closestDist = rc.senseHQLocation().distanceSquaredTo(findClosestToHQ(enemies));
				}
				
				if((Math.sqrt(Math.pow(rc.senseHQLocation().x-rc.getLocation().x,2)+Math.pow(rc.senseHQLocation().y-rc.getLocation().y,2))>hqDist)||(closestDist!=0&&closestDist<7)){
					fallBack=true;
				}
				if(Math.sqrt(Math.pow(rc.senseHQLocation().x-rc.getLocation().x,2)+Math.pow(rc.senseHQLocation().y-rc.getLocation().y,2))<2){
					fallBack=false;
				}
				if(fallBack)
					dir=rc.getLocation().directionTo(rc.senseHQLocation());
				
				if(rc.isActive()){
					for(int i=0;i<3;i++){
						for(int j=0;j<3;j++){
							if(rc.senseMine(rc.senseHQLocation().add(surroundings[i*3+j]))==Team.NEUTRAL){
								surroundingMines[i][j]=1;
								mines++;
							}
							else{
								surroundingMines[i][j]=0;
							}
						}
					}
					/*for(int i=0;i<3;i++){
						for(int j=0;j<3;j++){
							System.out.print(surroundingMines[i][j]);
						}zzzx
						System.out.println();
					}
					System.out.println("----");*/
					int amountOfSoldiers=0,amountOfOpp=0;

					for(Robot r:allies){
						if(r.getClass().equals(RobotType.SOLDIER)){
							amountOfSoldiers++;
						}
					}
					for(Robot x: enemies)
						if(x.getClass().equals(RobotType.SOLDIER))
							amountOfOpp++; 
					
					MapLocation[] a = rc.senseEncampmentSquares(rc.getLocation(), 100000, Team.NEUTRAL);
					
					for(MapLocation i:a){
						if(rc.getLocation().isAdjacentTo(i)&&!rc.getLocation().isAdjacentTo(rc.senseHQLocation())){
							targetEncampment = i;
						}
					}
					if(rc.readBroadcast(getChannel())==1)
						rush=true;
					if(rush){
						if(rc.senseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation())))==Team.NEUTRAL||rc.senseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation())))==rc.getTeam().opponent()){
							rc.defuseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation())));
						}else{
							if(rc.senseObjectAtLocation(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation())))!=null){
								if(rc.senseObjectAtLocation(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation()).rotateLeft()))==null){
									if(rc.senseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation()).rotateLeft()))==Team.NEUTRAL||rc.senseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation()).rotateLeft()))==rc.getTeam().opponent()){
										rc.defuseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation()).rotateLeft()));
									}
									else{
										rc.move(rc.getLocation().directionTo(rc.senseEnemyHQLocation()).rotateLeft());
									}
								}	
								else{
									if(rc.senseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation()).rotateRight()))==Team.NEUTRAL||rc.senseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation()).rotateRight()))==rc.getTeam().opponent()){
										rc.defuseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation()).rotateRight()));
									}
									else{
										rc.move(rc.getLocation().directionTo(rc.senseEnemyHQLocation()).rotateRight());
									}
								}
							}
							else{
								rc.move(rc.getLocation().directionTo(rc.senseEnemyHQLocation()));
							}
						}
					}
					else if(((rc.senseMine(rc.getLocation())!=Team.NEUTRAL)||(rc.senseMine(rc.getLocation()))!=rc.getTeam().opponent())&&(rc.getTeamPower()>80&&(rc.senseEncampmentSquare(rc.getLocation())&&mines+hqEncampments<8)||(hqEncampments==8&&(rc.senseEncampmentSquare(rc.getLocation())&&(rc.getLocation().x%2==/*1&&*/rc.getLocation().y%2/*==1*/))))){
						if(isArtillery(rc.getLocation().x,rc.getLocation().y,rc.senseHQLocation().x,rc.senseHQLocation().y,rc.senseEnemyHQLocation().x,rc.senseEnemyHQLocation().y)){
							rc.captureEncampment(RobotType.ARTILLERY);
						}else{
							rc.captureEncampment(RobotType.SUPPLIER);
						}
					}
					else if((amountOfSoldiers > amountOfOpp + 1 && hqDist < 64 && !rc.senseEncampmentSquare(rc.getLocation()))){
						if((rc.senseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation())))==Team.NEUTRAL)||(rc.senseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation())))==rc.getTeam().opponent())){
							rc.defuseMine(rc.getLocation().add(rc.getLocation().directionTo(rc.senseEnemyHQLocation())));
						}
						else{
							rc.move(rc.getLocation().directionTo(rc.senseEnemyHQLocation()));
						}
					}
					else if(rc.getTeamPower()>80&&targetEncampment!=null){
						rc.move(rc.getLocation().directionTo(targetEncampment));
					}
					else if(nearbyEnemies.length==0){
						int rx,ry;
						
							rx=rc.getLocation().x;
							ry=rc.getLocation().y;
						if((rx+2*(ry%5))%5==0&&rc.senseMine(rc.getLocation())==null){
							//if(rc.getLocation().x%5==0&&0==rc.getLocation().y%5&&rc.senseMine(rc.getLocation())==null){
							rc.layMine();
						}
						else if(rc.senseMine(rc.getLocation().add(dir))==Team.NEUTRAL||rc.senseMine(rc.getLocation().add(dir))==rc.getTeam().opponent()){
							rc.defuseMine(rc.getLocation().add(dir));
						}
						else if(rc.canMove(dir)){
							if(rc.senseObjectAtLocation(rc.getLocation().add(dir))!=null){
								if((int)(Math.random()*2)>1){
									if(rc.senseMine(rc.getLocation().add(checkDir(rc,dir.rotateLeft())))==Team.NEUTRAL||rc.senseMine(rc.getLocation().add(checkDir(rc,dir.rotateLeft())))==rc.getTeam().opponent()){
										rc.defuseMine(rc.getLocation().add(checkDir(rc,dir.rotateLeft())));
									}
									else{
										rc.move(checkDir(rc,dir.rotateLeft()));
									}
								}
								else{
									if(rc.senseMine(rc.getLocation().add(checkDir(rc,dir.rotateRight())))==Team.NEUTRAL||rc.senseMine(rc.getLocation().add(checkDir(rc,dir.rotateRight())))==rc.getTeam().opponent()){
										rc.defuseMine(rc.getLocation().add(checkDir(rc,dir.rotateRight())));
									}
									else{
										rc.move(checkDir(rc,dir.rotateRight()));
									}
								}
							}
							else{
								if(rc.senseMine(rc.getLocation().add(checkDir(rc,dir)))==Team.NEUTRAL||(rc.senseMine(rc.getLocation().add(checkDir(rc,dir)))==rc.getTeam().opponent())){
									rc.defuseMine(rc.getLocation().add(checkDir(rc,dir)));
								}else{
									rc.move(checkDir(rc,dir));
								}
							}
						}
					}
					else{//someone spotted
						MapLocation closestEnemy = findClosest(nearbyEnemies);
						MapLocation closestAlly = findClosest(allies);
						if(nearbyEnemies.length+3<amountOfSoldiers){
							if(rc.senseObjectAtLocation(rc.getLocation().add(dir))!=null){
								if((int)(Math.random()*2)>1){
									rc.move(rc.getLocation().directionTo(closestEnemy).rotateLeft());
								}
								else{
									rc.move(rc.getLocation().directionTo(closestEnemy).rotateRight());
								}
							}else{
								rc.move(rc.getLocation().directionTo(closestEnemy));
							}
						}
						else{
							if(rc.senseObjectAtLocation(rc.getLocation().add(dir))!=null){
								if((int)(Math.random()*2)>1){
									rc.move(rc.getLocation().directionTo(closestAlly).rotateLeft());
								}
								else{
									rc.move(rc.getLocation().directionTo(closestAlly).rotateRight());
								}
							}else{
								rc.move(rc.getLocation().directionTo(closestAlly));
							}
						}
					}
				}
			}catch (Exception e){
				System.out.println("Soldier Exception");
				e.printStackTrace();
			}
			rc.yield();
		}
	}
	
	private static Direction checkDir(RobotController rc, Direction dir){
		if(rc.senseNearbyGameObjects(Robot.class, rc.getLocation().add(dir), 5, rc.getTeam().opponent()).length>rc.senseNearbyGameObjects(Robot.class, rc.getLocation().add(dir), 5, rc.getTeam()).length){
			return dir.opposite();
		}
		return dir;
	}

	static int nukeCount = 0;
	
	private static void hqCode(){
		MapLocation myLoc = rc.getLocation();
		MapLocation enemyLoc = rc.senseEnemyHQLocation();
		MapLocation rallyPt = myLoc.add(myLoc.directionTo(enemyLoc),5);
		int nukeCounter=0,hqDist = (int)(Math.sqrt(Math.pow(rc.senseEnemyHQLocation().x-rc.senseHQLocation().x,2)+Math.pow(rc.senseEnemyHQLocation().y-rc.senseHQLocation().y,2)));
		while(true){
			try{
				
				if (rc.isActive()) {
					// Spawn a soldier
					Robot[] allies = rc.senseNearbyGameObjects(Robot.class,100000000,rc.getTeam());
					Robot[] enemies = rc.senseNearbyGameObjects(Robot.class,10000000,rc.getTeam().opponent());
					int amountOfSoldiers=0;
					//int amountOfArtillery=0;

					for(Robot r:allies){
						if(rc.senseRobotInfo(r).type.equals(RobotType.SOLDIER)){
							amountOfSoldiers++;
						}
						//if(r.getClass().equals(RobotType.ARTILLERY)){
						//	amountOfArtillery++;
						//}
					}
					if(rc.senseEnemyNukeHalfDone()){
						rc.broadcast(getChannel(), 1);
					}
					if(!rc.hasUpgrade(Upgrade.DEFUSION)&&rc.senseEnemyNukeHalfDone()){
						rc.researchUpgrade(Upgrade.DEFUSION);
					}
					else if(!rc.hasUpgrade(Upgrade.PICKAXE)){
						rc.researchUpgrade(Upgrade.PICKAXE);
					}
					else if(nukeCount>2&&(amountOfSoldiers<15||amountOfSoldiers<enemies.length+10)&&nukeCounter<350){//&&rc.getControlBits()>40){
						lookAround: for (Direction d:Direction.values()){
							if (rc.canMove(d)&&(rc.senseMine(rc.getLocation().add(d))==null||rc.senseMine(rc.getLocation().add(d))==rc.getTeam())){
								rc.spawn(d);
								break lookAround;
							}
						}nukeCount = 0;
					}
					//			Robot[] alliedRobots = rc.senseNearbyGameObjects(Robot.class,100000,rc.getTeam());
					else if (!rc.hasUpgrade(Upgrade.NUKE)){
						rc.researchUpgrade(Upgrade.NUKE);
						nukeCounter++;
						nukeCount++;
					}
					if(!rc.senseEnemyNukeHalfDone()){
						rc.broadcast(getChannel(), 0);
					}
				}
				
				/*if(rc.getEnergon()<300||Clock.getRoundNum()>2000||rc.senseEnemyNukeHalfDone()){//kill enemy if nearing round limit or injured
					rallyPt = enemyLoc;
				}*/
				
			}catch (Exception e){
				System.out.println("Soldier Exception");
				e.printStackTrace();
			}
			rc.yield();
		}
	}

	public static int getChannel(){
		int channel = Math.abs((Clock.getRoundNum()*2432)%GameConstants.BROADCAST_MAX_CHANNELS);
		System.out.println("Channel: "+channel);
		return channel;
	}
}