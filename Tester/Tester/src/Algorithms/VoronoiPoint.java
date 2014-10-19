package Algorithms;

import java.util.ArrayList;

public class VoronoiPoint {
	
	ArrayList<VoronoiTile> neighbors;
	int[] point;
	
	public VoronoiPoint(int[] p1){
		this.neighbors=new ArrayList<VoronoiTile>();
		this.point=p1;
	}

}
