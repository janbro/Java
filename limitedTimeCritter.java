package Java;

import info.gridworld.actor.Critter;

public class limitedTimeCritter extends Critter {
	private int days;
	private final int maxDays;
	public limitedTimeCritter(){
		super();
		maxDays=5+(int)(Math.random()*6);
	}
	public limitedTimeCritter(int max){
		super();
		if(max<5)
			throw new IllegalArgumentException("The maximum lifespan should be at least 5.");
		maxDays=5+(int)(Math.random()*(max-4));
	}
	public void act(){
		if(maxDays <= days)
			new limitedTimeCritter(maxDays*2).putSelfInGrid(getGrid(), getLocation());
		super.act();
		days++;
	}
}
