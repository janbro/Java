package team153;

import java.util.ArrayList;
import java.util.Random;

import battlecode.common.*;

public class BasicPlayer {

	public static RobotController rc;
	static Random rand;
	static Direction[] directions = {Direction.NORTH, Direction.NORTH_EAST, Direction.EAST, Direction.SOUTH_EAST, Direction.SOUTH, Direction.SOUTH_WEST, Direction.WEST, Direction.NORTH_WEST};

	public static void spawnUnit(RobotType type) throws GameActionException{
		for(Direction dir:directions){
			if(rc.canSpawn(dir, type)){
				rc.spawn(dir, type);
				break;
			}
		}
	}


	public static void randMove() throws GameActionException{
		ArrayList<Direction> possDirs = new ArrayList<Direction>();
		for(Direction dir:directions){
			possDirs.add(dir);
		}
		while(!possDirs.isEmpty()){
			int randInt = rand.nextInt(possDirs.size());
			Direction moveDir = possDirs.remove(randInt);
			if(rc.canMove(moveDir)&&rc.senseTerrainTile(rc.getLocation().add(moveDir)).equals(TerrainTile.NORMAL)){
				rc.move(moveDir);
				break;
			}
		}
	}

}
