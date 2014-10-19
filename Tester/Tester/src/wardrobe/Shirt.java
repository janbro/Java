package wardrobe;

import java.awt.Color;

public class Shirt {

	public static final byte NONE = 3;
	public static final byte BUTTON_DOWN = 1;
	public static final byte BUTTON_COLLAR = 2;
	public static final byte SLEEVE_LONG = 1;
	public static final byte SLEEVE_SHORT = 2;
	
	private ClothesColor color = null;
	private byte buttons = NONE;
	private byte sleeveLength = SLEEVE_SHORT;

	public Shirt(Color color, byte buttons, byte sleeveLength){
		this.color = (ClothesColor) color;
		this.buttons = buttons;
		this.sleeveLength = sleeveLength;
	}
	
	public Shirt(Color color){
		this.color = (ClothesColor) color;
	}
	
	public Color getShirtColor(){
		return this.color;
	}
	
	public byte getButtonType(){
		return this.buttons;
	}
	
	public byte getSleeveLength(){
		return this.sleeveLength;
	}
	
	public String toString(){
		return "This shirt is "+this.color.toString()+" has "+sleeveToString(this.sleeveLength)+" sleeves and has "+buttonsToString(this.buttons)+" buttons.";
	}
	
	private static String sleeveToString(byte sleeveLength){
		switch(sleeveLength){
		case NONE:
			return "no";
		case SLEEVE_SHORT:
			return "short";
		case SLEEVE_LONG:
			return "long";
		default:
			return "NULL";
		}
	}
	
	private static String buttonsToString(byte buttonType){
		switch(buttonType){
		case NONE:
			return "no";
		case BUTTON_DOWN:
			return "button down";
		case BUTTON_COLLAR:
			return "collar";
		default:
			return "NULL";
		}
	}
	
	public int compareTo(Shirt other){
		if(this.color.equals(other.getShirtColor())){
			if(this.sleeveLength==other.getSleeveLength()){
				if(this.buttons==other.getButtonType())
					return 0;
				else if(this.buttons>other.getButtonType())
					return 1;
				else
					return -1;
			}
			else if(this.sleeveLength>other.getSleeveLength())
				return 1;
			else
				return -1;
		}else if(color.compareTo(other.getShirtColor())>0)
			return 1;
		else
			return -1;
		
	}
}
