package Algorithms;

public class Line {
	int[] p1;
	int[] p2;
	double slope;
	
	public Line(int[] p1,int p2[]){
		this.p1=p1;
		this.p2=p2;
		calculateSlope();
	}
	
	public Line(int x1,int y1,int x2, int y2){
		this.p1=new int[]{x1,y1};
		this.p2=new int[]{x2,y2};
		calculateSlope();
	}
	
	public Line(int slope, int[] intercept){
		this.slope=slope;
		this.p1=intercept;
		this.p2=null;
	}
	
	private void calculateSlope(){
		try{
			slope=(double)(p2[1]-p1[1])/(p1[1]-p1[0]);
		}catch(Exception e){
			System.out.println("P2 does not exist.");
		}
		
	}
	
	public static double distance(int[] p1,int[] p2){
		return Math.sqrt(Math.pow(p2[1]-p1[1],2)+Math.pow(p2[0]-p1[0],2));
	}
	
	public String toString(){
		if(p2!=null)
			return "("+p1[0]+","+p1[1]+") ("+p2[0]+","+p2[1]+")";
		return "("+p1[0]+","+p1[1]+") Slope:"+slope;
	}

}
