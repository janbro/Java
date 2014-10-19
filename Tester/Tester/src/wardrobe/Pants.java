package wardrobe;

import java.awt.Color;

public class Pants {

	public static final byte NONE = 0;
	public static final byte LONG = 1;
	public static final byte SHORT = 2;
	
	private ClothesColor color = null;
	private byte length = NONE;
	
	public Pants(Color color, byte length){
		this.color = (ClothesColor) color;
		this.length = length;
	}
	
	public Pants(Color color){
		this.color = (ClothesColor) color;
		this.length = SHORT;
	}
	
	public byte getLength(){
		return length;
	}
	
	public Color getColor(){
		return color;
	}
	
	public int compareTo(Pants other){
		if(color.equals(other.getColor())){
			if(length==other.getLength()){
				return 0;
			}else if(length<other.getLength())
				return -1;
			else
				return 1;
		}else if(color.compareTo(other.getColor())<0)
			return -1;
		else
			return 1;
	}
	
	public String toString(){
		return "These pants are "+lengthToString(this.length)+" and "+color.toString()+".";
	}
	
	private static String lengthToString(byte length){
		switch(length){
		case 1:
			return "long";
		case 2:
			return "short";
		default:
			return null;
		}
	}

}
