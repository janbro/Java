
public class Ring {
	
	private int value = 0;
	private int pos = 0;
	
	public Ring(int val,int pos){
		value=val;
		this.pos=pos;
	}
	
	public int getVal(){
		return value;
	}
	
	public int getPos(){
		return pos;
	}
	
	public void setVal(int val){
		value=val;
	}
	
	public void setPos(int pos){
		this.pos=pos;
	}
	
	public String toString(){
		return ""+value;
	}

}
