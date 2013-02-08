package Java;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Graphics {
	/**
	 * Set the display mode to be used 
	 * 
	 * @param width The width of the display required
	 * @param height The height of the display required
	 * @param fullscreen True if we want fullscreen mode
	 */
	public void setDisplayMode(int width, int height, boolean fullscreen) {
	    // return if requested DisplayMode is already set
	    if ((Display.getDisplayMode().getWidth() == width) && 
	        (Display.getDisplayMode().getHeight() == height) && 
		(Display.isFullscreen() == fullscreen)) {
		    return;
	    }
	    try {
	        DisplayMode targetDisplayMode = null;
		if (fullscreen){
		    DisplayMode[] modes = Display.getAvailableDisplayModes();
		    int freq = 0;
		    for (int i=0;i<modes.length;i++) {
		        DisplayMode current = modes[i];	
			if ((current.getWidth() == width) && (current.getHeight() == height)) {
			    if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
			        if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
				    targetDisplayMode = current;
				    freq = targetDisplayMode.getFrequency();
	                        }
	                    }
			    // if we've found a match for bpp and frequence against the 
			    // original display mode then it's probably best to go for this one
			    // since it's most likely compatible with the monitor
			    if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
	                        (current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
	                            targetDisplayMode = current;
	                            break;
	                    }
	                }
	            }
	        }else
	            targetDisplayMode = new DisplayMode(width,height);
	        if(targetDisplayMode == null){
	            System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
	            return;
	        }
	        Display.setDisplayMode(targetDisplayMode);
	        Display.setFullscreen(fullscreen);
	    } catch (LWJGLException e) {
	        System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);
	    }
	}
	public long getTime() {
		return System.nanoTime() / 1000000;
	}
	public void setColor(Number red,Number green,Number blue){
		glColor3f(red.floatValue(),green.floatValue(),blue.floatValue());
	}
	public void makeSquare(int x,int y,int width){
		makeRectangle(x,y,width,width);
	}
	public void makeRectangle(int x,int y,int length,int height){
		glBegin(GL_QUADS);
	    glVertex2f(x,y);
		glVertex2f(x+length,y);
		glVertex2f(x+length,y+height);
		glVertex2f(x,y+height);
	    glEnd();
	}
}
