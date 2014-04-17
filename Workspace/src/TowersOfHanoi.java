import java.util.ArrayList;
import java.util.Stack;


public class TowersOfHanoi {
	static ArrayList<Stack<Ring>> towers = new ArrayList<Stack<Ring>>();
	static ArrayList<Ring> rings = new ArrayList<Ring>();
	public static void main(String[] args){
		int n=5; //Amnt of Rings
		for(int i=n-1;i>=0;i--){
			towers.add(new Stack<Ring>());
			towers.get(0).add(new Ring(i,0));
			rings.add(new Ring(i,0));
		}
		for(int i=0;i<3;i++)
			System.out.println("Tower "+((char)(97+i))+": "+towers.get(i).toString());
		moveTower(n-1,0,2,1);
	}
	
	static void moveTower(int disk,int source, int dest, int spare){
		if(disk==0){
			//rings.get(disk).setPos(dest);
			towers.get(dest).add(towers.get(source).pop());
			char h = (char) ((char)97+dest);
			System.out.println(towers.get(dest).peek().getVal()+" move to "+h);
			for(int i=0;i<3;i++)
				System.out.println("Tower "+((char)(97+i))+": "+towers.get(i).toString());
		}else{
			moveTower(disk-1,source,spare,dest);
			//rings.get(disk).setPos(dest);
			towers.get(dest).add(towers.get(source).pop());
			char h = (char) ((char)97+dest);
			System.out.println(towers.get(dest).peek().getVal()+" move to "+h);
			for(int i=0;i<3;i++)
				System.out.println("Tower "+((char)(97+i))+": "+towers.get(i).toString());
			moveTower(disk-1,spare,dest,source);
		}
	}
}
