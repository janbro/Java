package Algorithms;

import java.util.ArrayList;

public class VoronoiTile {
	
	ArrayList<VoronoiTile> neighbors;
	ArrayList<VoronoiPoint> points;
	
	public VoronoiTile(){
		neighbors=new ArrayList<VoronoiTile>();
		points=new ArrayList<VoronoiPoint>();
	}

}
