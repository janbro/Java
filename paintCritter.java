package Java;

import java.awt.Color;
import java.util.ArrayList;
import info.gridworld.actor.*;
import info.gridworld.grid.Location;

public class paintCritter extends Critter {
	public paintCritter(){
		super();
		Color[] colors={Color.GREEN,Color.RED,Color.BLUE};
		setColor(colors[(int)(Math.random()*3)]);
	}
	public void processActors(ArrayList<Actor> actors){
		for(Actor a:actors)
			if(a instanceof Bug)
				((Bug)a).setColor(getColor());
	}
	public ArrayList<Actor> getActors(){
		ArrayList<Location> locs=getGrid().getOccupiedLocations();
		ArrayList<Actor> actors=new ArrayList<Actor>();
		for(Location loc:locs)
			if(distance(loc) <= 2)
				actors.add(getGrid().get(loc));
		return actors;
	}
	public int distance(Location loc){
		if(loc.equals(getLocation()))
			return 0;
		return 1+distance(getLocation().getAdjacentLocation(getLocation().getDirectionToward(loc)));
	}
	public ArrayList<Location> getMoveLocations(){
		ArrayList<Location> locs=getGrid().getEmptyAdjacentLocations(getLocation());
		ArrayList<Location> flowers=new ArrayList<Location>();
		for(Location loc:locs)
			if(getGrid().get(loc) instanceof Flower)
				flowers.add(loc);
		return flowers;
	}
}
