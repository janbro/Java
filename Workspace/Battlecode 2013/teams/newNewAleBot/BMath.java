package newNewAleBot;

import battlecode.common.MapLocation;

public class BMath {
	
	public static float distance(MapLocation m, MapLocation n){
		return (float) Math.sqrt(Math.pow(m.x+n.x,2)+Math.pow(m.y+n.y,2));
	}
	
	public static float distance(int x1, int y1, int x2, int y2){
		return (float) Math.sqrt(Math.pow(x1+x2,2)+Math.pow(y1+y2,2));
	}
	
}
