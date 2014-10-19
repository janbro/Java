package wardrobe;

import java.awt.Color;

public class ClothesColor extends Color{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClothesColor(int rgb) {
		super(rgb);
		// TODO Auto-generated constructor stub
	}

	public int compareTo(Color other){
		if(this.equals(other))
			return 0;
		return colorToComparable(this)<colorToComparable(other)?-1:1; 
	}
	
	private static int colorToComparable(Color color){
		String[] colors = {"BLACK","DARK GRAY","GRAY","LIGHT GRAY","RED","PINK","ORANGE","YELLOW","GREEN","BLUE","CYAN","MAGENTA","WHITE"};
		for(int i=0;i<colors.length;i++){
			if(colors[i].equals(color.toString()))
				return i;
		}
		return -1;
	}

}
